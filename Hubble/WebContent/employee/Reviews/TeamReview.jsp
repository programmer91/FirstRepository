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
        <title>Hubble Organization Portal :: Team Review Search</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css?ver=1.0"/>">
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
                        <li ><a href="#" class="selected" rel="employeeSearchTab"  >Review Search </a></li>
                    </ul>
                    <%--<sx:tabbedpanel id="employeeSearchPannel" cssStyle="width: 840px; height: 500px;padding: 5px 5px 5px 5px;" doLayout="true"> --%>
                    <div  style="border:1px solid gray; width:840px;height: 500px; overflow:auto; margin-bottom: 1em;">   
                        <!--//START TAB : -->
                        <%--  <sx:div id="employeeSearchTab" label="Employee Search" cssStyle="overflow:auto;"  > --%>
                        <div id="employeeSearchTab" class="tabcontent"  >


                            <div id="overlay"></div>              <!-- Start Overlay -->
                            <div id="specialBox">


                                <s:form theme="simple"  align="center" name="addReviewForm" action="%{currentAction}" method="post" enctype="multipart/form-data" onsubmit="return validateForm();"  >
                                    <s:hidden id="reviewId" name="reviewId" value=""/>
                                    <s:hidden id="reviewFlag" name="reviewFlag" value=""/>
                                    <s:hidden id="roleName" name="roleName" value="%{#session.roleName}"/> 
                                    <s:hidden id="addType" name="addType" value="Team"/>
                                    <s:hidden id="isManager" name="isManager" value="%{#session.isUserManager}"/>
                                    <s:hidden id="livingCountry" name="livingCountry" value="%{#session.livingCountryList}"/>
                                    <s:hidden id="tempoverlayReviewType" name="tempoverlayReviewType" value=""/>
                                    <table align="center" border="0" cellspacing="0" style="width:100%;">
                                        <tr>                               
                                        <td colspan="2" style="background-color: #288AD1" >
                                            <h3 style="color:white;" align="left">

                                                <!--  Review Edit -->
                                                <span id="headerLabel"></span>
                                            </h3></td>
                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                            <a href="#" onmousedown="teamtoggleOverlay()" >
                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                            </a>  

                                        </td></tr>
                                        <tr>
                                        <td colspan="4">
                                            <div id="load" style="color: green;display: none;">Loading..</div>
                                            <div id="resultMessage"></div>
                                        </td>
                                        </tr>    
                                        <tr><td><table style="width:100%;" align="center" border="0">
                                                <tr>
                                                <td   class="fieldLabel">EmpName :<font style="color:red;">*</font></td>
                                                <td ><s:select list="empnamesList" id="userId" name="userId" headerKey="-1" headerValue="--Please Select--" cssClass="inputSelect"/></td> 

                                                <td class="fieldLabel" >Type of Reviews :<FONT color="red"  ><em>*</em></FONT> </td>
                                                <td> 

                                                    <s:select id="overlayReviewType"  name="overlayReviewType"  list="reviewList" headerKey="" headerValue="--Please Select--" cssClass="inputSelect" onchange="getBasePoints(this);" disabled="False" /> 
                                                <span id="reviewTypeValue"></span>
                                        </td>

                                        </tr>
                                        <tr id="RequirementClosedTR">
                                        <td class="fieldLabel">No.of Log's added</td>
                                        <td><s:textfield id="overlayReviewLogAdded" name="overlayReviewLogAdded" cssClass="inputTextBlue" value="%{overlayReviewLogAdded}" onkeypress="return isNumberKeyExam(event)"/></td>
                                        <td class="fieldLabel">Billing&nbsp;Amount:<FONT color="red"  ><em>*</em></FONT></td>
                                        <td><s:textfield id="overlayReviewBilling" name="overlayReviewBilling" cssClass="inputTextBlue" value="%{overlayReviewBilling}" onkeypress="return isNumberKeyExam(event)"/><font color="darkblue">$</font></td>
                                        </tr>
                                        <tr>
                                        <td class="fieldLabel">Review Name :<font style="color:red;">*</font></td>
                                        <td colspan="3"><s:textfield name="overlayReviewName" id="overlayReviewName" cssClass="inputTextareaOverlay1" value="%{currentReview.reviewName}" onchange="reviewFieldLengthValidator(this);" style="height: 28px;width: 526px;"/></td>
                                        </tr>
                                        <tr>
                                            <%--  <td align="right" class="fieldLabel" >Created&nbsp;Date:</td>
                                              <td><s:textfield id="overlayReviewCreatedDate" name="overlayReviewCreatedDate" cssClass="inputTextBlue" value="%{endDate}" />
                                                  <a href="javascript:cal1.popup();">
                                                      <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                           width="20" height="20" border="0" ></a>
                                              </td> --%>
                                        <td align="left" class="fieldLabel" >Reviewed&nbsp;Date:<font style="color:red;">*</font></td>
                                        <td colspan="0"><s:textfield id="overlayReviewDate" name="overlayReviewDate" cssClass="inputTextBlue" value="%{}" />
                                            <a class="underscore" href="javascript:cal4.popup();">
                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                     width="20" height="20" border="0" ></a>
                                        </td>
                                        <%--         <td class="fieldLabel" >State :<FONT color="red"  ><em>*</em></FONT> </td>
                                                 <td > <s:select id="reviewStatusOverlay"  name="reviewStatusOverlay"  list="{'Approved','Denied'}" value="%{reviewStatus}" cssClass="inputSelect"  disabled="False"/></td>
                                        --%>
                                        </tr>

                                        <tr>
                                        <td class="fieldLabel" class="formfield">Description :</td>
                                        <td colspan="3" valign="top"><s:textarea rows="3" cols="65" name="overlayDescription" cssClass="inputTextareaOverlay1" value="%{currentReview.notes}" style="width: 527px;" onchange="reviewFieldLengthValidator(this);" id="overlayDescription"/></td>
                                        </tr>

                                        <tr id="tlcommentsTr" >
                                        <td class="fieldLabel" class="formfield">Lead Comments :</td>
                                        <td colspan="3" valign="top"><s:textarea rows="3" cols="65" name="tlComments" cssClass="inputTextareaOverlay1" style="width: 527px;" value="%{currentReview.notes}" onchange="reviewFieldLengthValidator(this);" id="tlComments"/></td>
                                        </tr>
                                        <tr>
                                        <td class="fieldLabel" >Status :<FONT color="red"  ><em>*</em></FONT> </td>
                                        <td > <s:select id="reviewStatusOverlay"  name="reviewStatusOverlay"  list="{'Opened','Approved','Denied'}" value="%{reviewStatus}" cssClass="inputSelect"  disabled="False"/></td>


                                        <td align="left" class="fieldLabel" >Lead&nbsp;Rating:<FONT color="red"  ><em>*</em></FONT></td>
                                        <td >

                                            <s:textfield name="leadRating" id="leadRating" cssClass="inputTextBlue2" onkeypress="return isNumberKeyExam(event)"/>
                                            <s:hidden id="maxPoints" name="maxPoints"/>

                                        <span id="leadpointsInfo"></span></td>


                                        </tr>
                                        <tr id="tlApprovedDetails" style="display: none">
                                        <td class="fieldLabel">
                                            Approved&nbsp;By#1:
                                        </td>
                                        <td style="color:green;">
                                            <div id="tlId" ></div>
                                        </td>
                                        <td class="fieldLabel">
                                            Approved&nbsp;Date:
                                        </td>
                                        <td style="color:green;">
                                            <div id="ApprovedBy1Date" ></div>
                                        </td>
                                        </tr>

                                        <tr id="hrcommentsTr" >
                                        <td class="fieldLabel" class="formfield">Hr Comments :</td>
                                        <td colspan="3" valign="top"><s:textarea rows="3" cols="65" name="hrComments" style="width: 527px;" cssClass="inputTextareaOverlay1" value="%{currentReview.notes}" onchange="reviewFieldLengthValidator(this);" id="hrComments"/></td>
                                        </tr>
                                        <tr id="hrratingsTr">
                                        <td class="fieldLabel">Status&nbsp;:<FONT color="red"  ><em>*</em></FONT> </td>
                                        <td > <s:select id="reviewHrStatusOverlay"  name="reviewHrStatusOverlay"  list="{'Opened','Approved','Denied'}" value="%{reviewStatus}" cssClass="inputSelect"  disabled="False"/></td>


                                        <td align="left" class="fieldLabel" >Hr&nbsp;Rating:<FONT color="red"  ><em>*</em></FONT></td>
                                        <td><s:textfield name="hrRating" id="hrRating" cssClass="inputTextBlue2" onkeypress="return isNumberKeyExam(event)"/>


                                        <span id="hrpointsInfo"></span></td>
                                        </tr>

                                        <tr id="hrApprovedDetails" style="display: none">
                                        <td class="fieldLabel">
                                            Approved&nbsp;By#2:
                                        </td>
                                        <td style="color:green;">
                                            <div id="hrId"></div>
                                        </td>
                                        <td class="fieldLabel">
                                            Approved&nbsp;Date:
                                        </td>
                                        <td style="color:green;">
                                            <div id="ApprovedBy2Date" ></div>
                                        </td>
                                        </tr> 

                                        <!-- onsite mgr comments start -->
                                        <tr id="onsiteMgrcommentsTr">
                                        <td class="fieldLabel" valign="top">OnsiteMgr&nbsp;Comments :</td>
                                        <td colspan="3" valign="top"><s:textarea rows="3" cols="65" name="onsiteMgrComments" style="width: 527px;" cssClass="inputTextareaOverlay1" value="%{currentReview.notes}" onchange="reviewFieldLengthValidator(this);" id="onsiteMgrComments"/></td>
                                        </tr>
                                        <tr id="onsiteMgrratingsTr">
                                        <td class="fieldLabel" >Status&nbsp;:<FONT color="red"  ><em>*</em></FONT> </td>
                                        <td > <s:select id="reviewOnsiteMgrStatusOverlay"  name="reviewOnsiteMgrStatusOverlay"  list="{'Opened','Approved','Denied'}" value="%{reviewStatus}" cssClass="inputSelect"  disabled="False"/></td>


                                        <td align="left" class="fieldLabel" >OnsiteMgr&nbsp;Rating:<FONT color="red"  ><em>*</em></FONT></td>
                                        <td><s:textfield name="onsiteMgrRating" id="onsiteMgrRating" cssClass="inputTextBlue2" onkeypress="return isNumberKeyExam(event)"/>


                                        <span id="onsiteMgrpointsInfo"></span></td>
                                        </tr>
                                        <tr id="onsiteMgrApprovedDetails" style="display: none">
                                        <td class="fieldLabel">
                                            Approved&nbsp;By#3:
                                        </td>
                                        <td style="color:green;">
                                            <div id="onsiteMgrId"></div>
                                        </td>
                                        <td class="fieldLabel">
                                            Approved&nbsp;Date:
                                        </td>
                                        <td style="color:green;">
                                            <div id="ApprovedBy3Date" ></div>
                                        </td>
                                        </tr> 

                                        <!-- onsite mgr comments end -->

                                        <tr id="uploadTr" style="display: none"> 

                                        <td class="fieldLabel">Upload File :</td>
                                        <td colspan="2" ><s:file name="file" label="file" cssClass="inputTextarea" id="file" onchange="putFileName();"/></td> 
                                        <td  align="left" >
                                            <input type="button" value="Save" onclick="return ajaxFileUpload();" class="buttonBg" id="addButton" style="margin-left: 88px;"/> 


                                        </td>
                                        </tr> 

                                        <tr style="display: none" id="downloadTr">

                                        <td class="fieldLabel">Attachments :</td> 
                                        <td colspan="2">   <a class="navigationText" href="#" onclick="downloadReviewFile();" id="downloadLink" style="display: none">

                                                <span id="downloadFile"></span>
                                            </a></td><td> <input type="button" value="Update" onclick="return upadtereview('1');" class="buttonBg" style="margin-left: 85px;"/> 
                                            </tr>      
                                    </table>
                                    </td></tr>
                                    </table>
                                </s:form>              

                                <script type="text/JavaScript">
                                    var cal4 = new CalendarTime(document.forms['addReviewForm'].elements['overlayReviewDate']);
                                    cal4.year_scroll = true;
                                    cal4.time_comp = false;
                                    /*var cal1 = new CalendarTime(document.forms['addReviewForm'].elements['overlayReviewCreatedDate']);
                                                                      cal1.year_scroll = true;
                                                                      cal1.time_comp = false;
                                     */                                       
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
                                    <s:form name="frmEmpSearch" action="teamReviewSearch.action" theme="simple">
                                        <s:hidden id="reviewFlag" name="reviewFlag" value="%{reviewFlag}"/>
                                        <s:hidden id="roleName" name="roleName" value="%{#session.roleName}"/>
                                        <table cellpadding="1" cellspacing="1" border="0" width="650px" align="center">
                                            <tr>
                                            <td class="fieldLabel">Start&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                            <td><s:textfield name="startDate" id="startDate" cssClass="inputTextBlueSmall" value="%{startDate}"/><a class="underscore" href="javascript:cal2.popup();">
                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                            </td>

                                            <td class="fieldLabel">End&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                            <td>  <s:textfield name="endDate" id="endDate" cssClass="inputTextBlueSmall" value="%{endDate}"/><a class="underscore" href="javascript:cal3.popup();">
                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                            </td>
                                            </tr>

                                            <tr>
                                            <td class="fieldLabel" >Review Type :<FONT color="red"  ><em>*</em></FONT> </td>
                                            <td width="15%"> <s:select id="reviewType"  name="reviewType"  list="searchReviewMap" headerKey="" headerValue="All"   value="%{reviewType}" cssClass="inputSelect" onchange="getAssignedReviews(this);" disabled="False"/></td>
                                            <%--   <td class="fieldLabel" >State :<FONT color="red"  ><em>*</em></FONT> </td>
                                               <td width="15%"> <s:select id="reviewStatus"  name="reviewStatus"  list="{'Opened','Approved','Denied'}" headerKey="" headerValue="All"   value="%{reviewStatus}" cssClass="inputSelect" onchange="getAssignedReviews(this);" disabled="False"/></td>
                                            --%>
                                            <td class="fieldLabel" >TL Status :<FONT color="red"  ><em>*</em></FONT> </td>
                                            <td width="15%"> <s:select id="reviewTlStatus"  name="reviewTlStatus"  list="{'Opened','Approved','Denied'}" headerKey="" headerValue="All"   value="%{reviewTlStatus}" cssClass="inputSelect" onchange="getAssignedReviews(this);" disabled="False"/></td>

                                            </tr>



                                            <tr>
                                            <td   class="fieldLabel">EmpName :</td>
                                            <td ><s:select list="empnamesList" id="empnameById" name="empnameById" headerKey="-1" headerValue="All" cssClass="inputSelectLarge" value="%{empnameById}"/></td> 
                                            <% if (session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Operations")) {%>
                                            <td class="fieldLabel" >HR Status :<FONT color="red"  ><em>*</em></FONT> </td>
                                            <td width="15%"> <s:select id="reviewHrStatus"  name="reviewHrStatus"  list="{'Opened','Approved','Denied'}" headerKey="" headerValue="All"   value="%{reviewHrStatus}" cssClass="inputSelect" onchange="getAssignedReviews(this);" disabled="False"/></td>
                                            <%}%>
                                            <td></td>
                                            </tr>


                                            <tr>
                                            <td colspan="4" align="right">
                                                <input class="buttonBg" type="button" value="Add" onclick="teamtoggleOverlay()"/>
                                                <s:submit cssClass="buttonBg"  align="right"  value="Search" />

                                            </td>
                                            </tr>
                                        </table>
                                    </s:form>

                                    <script type="text/JavaScript">
                                        var cal2 = new CalendarTime(document.forms['frmEmpSearch'].elements['startDate']);
                                        cal2.year_scroll = true;
                                        cal2.time_comp = false;
                                        var cal3 = new CalendarTime(document.forms['frmEmpSearch'].elements['endDate']);
                                        cal3.year_scroll = true;
                                        cal3.time_comp = false;
                                                                                 
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

                                            //out.println(queryString);

                                            /* else {
                                             if(session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Employee")){
                                             queryString="SELECT  CONCAT(tblEmployee.FName,' ',tblEmployee.MName,'.',tblEmployee.LName) AS EmployeeName,ReviewType,EmpId,ReviewTypeId,EmpComments,tblEmpReview.CreatdBy,tblEmpReview.CreatedDate,AttachmentName,AttachmentLocation,ReviewName,tblEmpReview.Id AS Id,HrComments FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id) JOIN tblEmployee ON (tblEmpReview.EmpId = tblEmployee.Id) WHERE tblEmpReview.EmpId="+empId;
                                             }else if(session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Hr")) {
                                             queryString="SELECT  CONCAT(tblEmployee.FName,' ',tblEmployee.MName,'.',tblEmployee.LName) AS EmployeeName,ReviewType,EmpId,ReviewTypeId,EmpComments,tblEmpReview.CreatdBy,tblEmpReview.CreatedDate,AttachmentName,AttachmentLocation,ReviewName,tblEmpReview.Id AS Id,HrComments FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id) JOIN tblEmployee ON (tblEmpReview.EmpId = tblEmployee.Id) WHERE WorkingCountry='"+session.getAttribute(ApplicationConstants.WORKING_COUNTRY).toString()+"'";                                  
                                
                                             }
                            
                                             }*/
                                            // queryString="SELECT ReviewType,EmpId,ReviewTypeId,EmpComments,CreatdBy,CreatedDate,AttachmentName,AttachmentLocation,ReviewName,tblEmpReview.Id as Id FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id) where EmpId="+empId;
                                            // out.println(queryString);

                                            //out.println("--------"+submittedFrom);
%>

                                    <s:form action="" theme="simple" name="frmDBGrid">   

                                        <table cellpadding="0" cellspacing="0" width="100%" border="0">


                                            <tr>
                                            <td width="100%">


                                                <% if (session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Employee")) {%>


                                                <grd:dbgrid id="tblStat" name="tblStat" width="98" pageSize="15"
                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2"
                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                   addImage="../../includes/images/DBGrid/Add.png"  addAction="javascript:teamtoggleOverlay();"
                                                                   /> 
                                                    <%--    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                       imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                     
                                                                       />          --%>  

                                                    <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                    imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                                    imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>   

                                                    <%--  <grd:anchorcolumn dataField="EmployeeName" 
                                                                         headerText="EmployeeName" 
                                                                         linkUrl="javascript:toggleTeamReviewEditOverlay({Id},{ReviewTypeId},'{ReviewName}','{ReviewDate}','{EmpComments}','{AttachmentName}','{CreatedDate}','{Status}','{TLComments}',{TLRating},{HRRating},{MaxPoints},'{HrComments}','{UserId}','{ReviewType}')" linkText="{EmployeeName}" width="20"/> --%>
                                                    <grd:anchorcolumn dataField="EmployeeName" 
                                                                      headerText="EmployeeName" 
                                                                      linkUrl="javascript:toggleTeamReviewEditOverlay({Id})" linkText="{EmployeeName}" width="20"/>
                                                    <grd:textcolumn dataField="ReviewName" 
                                                                    headerText="Review Title" 
                                                                    width="20"/>
                                                    <grd:textcolumn dataField="ReviewType" headerText="ReviewType" width="18"/>


                                                    <grd:textcolumn dataField="Status" headerText="Status" width="18"/> 


                                                    <grd:datecolumn dataField="ReviewDate"  headerText="ReviewDate"  HAlign="right" dataFormat="MM-dd-yyyy" width="15" /> 



                                                    <%--<grd:imagecolumn headerText="Delete" width="4" HAlign="center" 
                                                                     imageSrc="../../includes/images/DBGrid/Delete.png"
                                                                     linkUrl="deleteReview.action?reviewId={Id}" imageBorder="0"
                                                                     imageWidth="51" imageHeight="20" alterText="Delete"></grd:imagecolumn> 
                                                    --%>


                                                </grd:dbgrid>

                                                <%--  
                                                <grd:dbgrid id="tblStat" name="tblStat" width="98" pageSize="10"
                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2"
                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                    
                                                            
                                                                     <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                 
                                                                   />
                                                    
                                                    <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                    imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                                    imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>   
                                                    
                                                    <grd:rownumcolumn headerText="SNo" width="4" HAlign="right"/>
                                                    
                                                  
                                                    <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"
                                                                     linkUrl="javascript:toggleTeamReviewEditOverlay({Id},{ReviewTypeId},'{ReviewName}','{ReviewDate}','{EmpComments}','{AttachmentName}')" imageBorder="0"
                                                                     imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>
                                                     <grd:textcolumn dataField="EmployeeName" headerText="EmployeeName" width="18"/>
                                                     <grd:textcolumn dataField="EmpTitle" headerText="Title" width="18"/>
                                                      <grd:textcolumn dataField="ReviewType" headerText="ReviewType" width="18"/>
                                                     <grd:textcolumn dataField="STATUS" headerText="Status" width="18"/>
                                                    
                                                  
                                                      <grd:datecolumn dataField="ReviewDate"  headerText="ReviewDate"  dataFormat="MM-dd-yyyy" width="15" sortable="true"/>
                                          
                                                                                
                                                
                                                    
                                                </grd:dbgrid> --%>

                                                <%} else if (session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Operations")) {%>


                                                <grd:dbgrid id="tblStat" name="tblStat" width="98" pageSize="10"
                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2"
                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                    <%--    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                       imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                       addImage="../../includes/images/DBGrid/Add.png"  addAction="<%=empReviewAction %>"
                                                                       /> --%>
                                                    <%--   <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                      imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                    
                                                                      />       --%>  
                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                   addImage="../../includes/images/DBGrid/Add.png"  addAction="javascript:teamtoggleOverlay();"
                                                                   /> 

                                                    <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                    imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                                    imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>   

                                                    <%--  <grd:anchorcolumn dataField="EmployeeName" 
                                                                         headerText="EmployeeName" 
                                                                         linkUrl="javascript:toggleTeamReviewEditOverlay({Id},{ReviewTypeId},'{ReviewName}','{ReviewDate}','{EmpComments}','{AttachmentName}','{CreatedDate}','{Status}','{TLComments}',{TLRating},{HRRating},{MaxPoints},'{HrComments}','{UserId}','{ReviewType}')" linkText="{EmployeeName}" width="20"/> --%>
                                                    <grd:anchorcolumn dataField="EmployeeName" 
                                                                      headerText="EmployeeName" 
                                                                      linkUrl="javascript:toggleTeamReviewEditOverlay({Id})" linkText="{EmployeeName}" width="20"/>
                                                    <grd:textcolumn dataField="ReviewName" 
                                                                    headerText="Review Title" 
                                                                    width="20"/>
                                                    <grd:textcolumn dataField="ReviewType" headerText="ReviewType" width="18"/>


                                                    <grd:textcolumn dataField="Status" headerText="TL Status" width="18"/> 
                                                    <grd:textcolumn dataField="HRStatus" headerText="HR Status" width="18"/> 
                                                    <grd:datecolumn dataField="ReviewDate"  headerText="ReviewDate"  dataFormat="MM-dd-yyyy" width="15" /> 



                                                    <%--<grd:imagecolumn headerText="Delete" width="4" HAlign="center" 
                                                                     imageSrc="../../includes/images/DBGrid/Delete.png"
                                                                     linkUrl="deleteReview.action?reviewId={Id}" imageBorder="0"
                                                                     imageWidth="51" imageHeight="20" alterText="Delete"></grd:imagecolumn> 
                                                    --%>


                                                </grd:dbgrid>
                                                <%}%>



                                                <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">

                                                <input type="hidden" name="submitFrom" value="dbGrid">
                                                <s:hidden  name="startDate" value="%{startDate}"/>
                                                <s:hidden  name="endDate" value="%{endDate}"/>
                                                <s:hidden  name="reviewType" value="%{reviewType}"/>
                                                <%-- <s:hidden  name="reviewStatus" value="%{reviewStatus}"/> --%>
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



</body>
</html>



