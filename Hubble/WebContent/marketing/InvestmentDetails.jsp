<%-- 
    Document   : InvestmentDetails
    Created on : Dec 30, 2015, 11:53:01 PM
    Author     : miracle
--%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: InvestmentDetails Search</title>
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
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/InvestmentScripts.js?version=1.5"/>"></script>

        <s:include value="/includes/template/headerScript.html"/> 
    </head>
    <%-- <body class="bodyGeneral" oncontextmenu="return false;" onload="getRequirementsList1();"> --%>
  
        <body class="bodyGeneral">
    

        <%!    /*
             * Declarations
             */
            Connection connection;
            String queryString;
            //StringBuffer queryString;
            String strTmp;
            String strSortCol;
            String strSortOrd;
            String userId;
            String submittedFrom;
            String action;
            //new
            String userRoleName;
            int role;
            int intSortOrd = 0;
            int intCurr;
            boolean blnSortAsc = true;
        %>


        <!-- Start oif the table -->
        <table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">


            <!--//START HEADER : Record for Header Background and Mirage Logo-->
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/Header.jsp"/>                    
                </td>
            </tr>
            <!--//END HEADER : Record for Header Background and Mirage Logo-->

            <s:hidden name="isInvestmentRecordsExist" id="isInvestmentRecordsExist" value="%{isInvestmentRecordsExist}"/>
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
                            <td width="850px" class="cellBorder" valign="top" style="padding:10px 5px 5px 5px;">
                                <!--//START TABBED PANNEL : -->
                                <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="requirementList"  > Investment Search</a></li>
                                    <!-- <li ><a href="#" rel="searchDiv">Requirement Search</a></li>-->
                                </ul>
                                
                                 <div id="overlay" ></div>
                                    <div id="specialBoxInvestment" style="width: 807px;">
                                    <s:form theme="simple" align="center" name="eventForm" id="eventForm">


                                        <table align="center" border="0" cellspacing="0" style="width:100%;" >
                                            <tr>
                                                <td colspan="2"  style="background-color: #288AD1" >
                                                    <h3 style="color:darkblue;" align="left">
                                                        <span id="headerLabel"></span>


                                                    </h3></td>
                                                <td colspan="2" style="background-color: #288AD1" align="right">

                                                    <a onmousedown="investmentToggleOverlay();" >
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

                                                    <table style="width:92%;" align="center" border="0">


                                                        <tr>

                                                            <s:hidden name="tempEventDate" id="tempEventDate"/>

                                                            <td class="fieldLabel" id="investmentNameLabelId">Title&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                            <td colspan="3"><s:textfield name="investmentName" id="investmentName" cssClass="inputTextareaOverlay"  onchange="investmentFieldLengthValidator(this);"></s:textfield>
                                                                <%--    <td><s:textfield name="selectDateFrom" id="selectDateFrom" cssClass="inputTextBlue" onchange="checkEventDate();validateTimestamp(this);" />
                                                                   <a href="javascript:cal3.popup();">
                                                                          <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                               width="20" height="20" border="0"></a>  --%>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="fieldLabel">Start&nbsp;Date&nbsp;(mm/dd/&nbsp;yyyy)&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                            <!-- <td colspan="2"  style="padding-right: 110px;" align="right"> -->
                                                            <td><s:textfield name="startDateInvestment" id="startDateInvestment" cssClass="inputTextBlueSmall" onmouseout="return compareDatesOverlay();" readonly="true"/><a href="javascript:cal4.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                            </td>

                                                            <td class="fieldLabel">End&nbsp;Date&nbsp;(mm/dd/&nbsp;yyyy)&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                            <td>  <s:textfield name="endDateInvestment" id="endDateInvestment" cssClass="inputTextBlueSmall" onmouseout="return compareDatesOverlay();" readonly="true"/><a href="javascript:cal5.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left" class="fieldLabel" >Country:<FONT color="red"  ><em>*</em></FONT></td>
                                                            <%-- <td ><s:textfield name="totalExpenseAmount" id="totalExpenseAmount" cssClass="inputTextBlueSmall" readonly="true"/></td> --%>
                                                            <td> <s:select list="{'USA','IND'}"  name="countryInvestment" id="countryInvestment" cssClass="inputSelectInvestement" value="%{reportType}"/></td>

                                                            <td align="left" class="fieldLabel" >Type:<FONT color="red"  ><em>*</em></FONT></td>
                                                            <%-- <td ><s:textfield name="totalExpenseAmount" id="totalExpenseAmount" cssClass="inputTextBlueSmall" readonly="true"/></td> --%>
                                                            <td> <s:select list="#@java.util.LinkedHashMap@{'S':'Lead Source','P':'Lead Pass'}" headerKey="" headerValue="--Please Select--" name="investmentType" id="investmentType" cssClass="inputSelect"/></td>

                                                            <s:hidden name="invType" id="invType"/>
                                                        </tr>
                                                        <tr>
                                                            <td align="left" class="fieldLabel" >Total Expenses:<FONT color="red"  ><em>*</em></FONT></td>
                                                            <td ><s:textfield name="totalExpenseAmount" id="totalExpenseAmount" cssClass="inputTextBlueSmall" onkeypress="return isNumberKey(event);" onchange="decimalsValidations(this.value);"/></td>
                                                            <td class="fieldLabel">Currency : <FONT color="red" SIZE="0.5"><em>*</em></FONT>   </td><td>                                                           
                                                                <s:select list="clientCurrencyList" name="usd" id="usd"  cssClass="inputSelectSmallCurr" /><FONT color="red" SIZE="0.5"><em>*</em></FONT>
                                                                <s:submit value="...."  cssClass="buttonBg" onclick="return currencyPopup('../crm/greensheets/CurrencyInfo.jsp')" align="left"/>
                                                            </td>

                                                        </tr>

                                                        <tr>
                                                            <td align="left" class="fieldLabel" >Location:<FONT color="red"  ><em>*</em></FONT></td>
                                                            <td ><s:textfield name="locationInvestment" id="locationInvestment" cssClass="inputTextInvestment"  onchange="investmentFieldLengthValidator(this);"/></td>
                                                            <td align="left" class="fieldLabel" >Conference:</td>
                                                            <%-- <td ><s:textfield name="totalExpenseAmount" id="totalExpenseAmount" cssClass="inputTextBlueSmall" readonly="true"/></td> --%>
                                                            <td>
                                                            <s:select list="upcommingConferencesList" headerKey="" headerValue="--Please Select--" name="upcpmingConferenceId" id="upcpmingConferenceId" cssClass="inputSelect" style="display:none"/>
                                                            <s:select list="totalConferencesList" headerKey="" headerValue="--Please Select--" name="totalConferenceId" id="totalConferenceId" cssClass="inputSelect" style="display:none"/>
                                                            <s:select list="upcommingConferencesList" headerKey="" headerValue="--Please Select--" name="conferenceId" id="conferenceId" cssClass="inputSelect"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="fieldLabel" valign="top">Description:</td>
                                                            <td colspan="3" valign="top"><s:textarea rows="3" cols="77" name="investmentDesc" cssClass="inputTextareaOverlay" id="investmentDesc"  onchange="investmentFieldLengthValidator(this);"/></td>


                                                        </tr>
                                                       

                                                         <tr id="createdTr" style="display: none;"> 
   
                                         <td align="left" class="fieldLabel">Created&nbsp;By :</td>
                                         <td ><span id="createdBy" class="fieldLabel" style="color: green;"> </span></td>
                                         <td class="fieldLabel" >Created&nbsp;Date&nbsp;:</td>
                                         <td ><span id="createdDate" class="fieldLabel" style="color: green;"> </span></td>
                                         </tr>



                                                        <tr id="uploadTr" style="display: none">

                                                            <td class="fieldLabel"><span id="fileTag"></span></td>
                                                            <td colspan="2" ><s:file name="file" cssClass="inputTextarea" id="file"/></td>
                                                            <td colspan="1" align="center" >
                                                                <input type="button" value="Save" onclick="return addInvestmentdetails();" class="buttonBg" id="addButton"/>





                                                            </td>
                                                            <td><div id="resetDiv" style="display: none"><s:reset cssClass="buttonBg"  align="right" value="Reset"/></div></td>
                                                        </tr>




                                                        <%--     <tr id="uploadTr">

                                                                <td class="fieldLabel">Upload File :</td>
                                                                <td colspan="2" ><s:file name="file" label="file" cssClass="inputTextarea" id="file" onchange="putFileName();"/></td>

                                                            </tr>
                                                            <tr id="addTr" style="display: none" >
                                                                <td colspan="2" align="right">
                                                              

                                                            <input type="button"  value="Save"  class="buttonBg" id="addButton" onclick="addInvestmentdetails()"/>
                                                        <s:reset cssClass="buttonBg"  align="right" value="Reset" /></td></tr>  --%>
                                                        <s:hidden id="investmentId" name="investmentId" value=""/>

                                                        <tr style="display: none" id="downloadTr">

                                                            <td class="fieldLabel">Attachments :</td>
                                                            <td>   <a class="navigationText" onclick="downloadInvestmentAttachment();" id="downloadLink" style="display: none">

                                                                    <span id="downloadFile"></span>

                                                                </a>
                                                                <span id="downloadFileNoAttachment"></span>

                                                            </td>
                                                            <td style="padding-left: 75px;">  <input type="button" value="Update" class="buttonBg" id="updateButtonInvs" onclick="return updateInvestmentdetails();"/></td>
                                                            <td   style="padding-right: 90px;">   <input type="button" class="buttonBg" onclick="deleteInvestment();" value="Delete" /></td>
                                                            <!--    <td style="display: block">  <table><tr><td ><div id="update" ><input type="button" value="Update" class="buttonBg" id="updateButtonInvs" onclick="return updateInvestmentdetails();"/>  </div></td>

                                                                            <td><div id="delete"><input type="button" class="buttonBg" onclick="deleteInvestment();" value="Delete" /></div></td></tr></table></td> -->
                                                        </tr>



                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                    </s:form>
                                </div>
                                
                                <div  style="border:1px solid gray; width:845px;height: 530px; overflow:auto; margin-bottom: 1em;">
                                    <%--  <sx:tabbedpanel id="attachmentPannel2" cssStyle="width: 845px; height: 530px;padding:10px 5px 5px 5px" doLayout="true"> --%>

                                    <!--//START TAB : -->
                                    <%--  <sx:div id="List" label="" cssStyle="overflow:auto;"> --%>
                                   

                                                 <s:form name="frmInvSearch"  action="investmentSearch.action" theme="simple" >

                                                    <table cellpadding="1" cellspacing="1" border="0" width="650px" align="center">
                                                        <tr>
                                                            <!-- <td style :align > -->
                                                            <td>
                                                                <div id="resultMessage123"></div>
                                                            </td>
                                                        

                                            </tr>
                                            <tr>
                                                <td align="left" class="fieldLabel" >Name :</td>
                                                <td ><s:textfield name="investmentName1" id="investmentName1" cssClass="inputTextBlue"/>
                                                <td align="left" class="fieldLabel" >Country:</td>
                                                <%-- <td ><s:textfield name="totalExpenseAmount" id="totalExpenseAmount" cssClass="inputTextBlueSmall" readonly="true"/></td> --%>
                                                <td> <s:select list="{'IND','USA'}"  name="countryInvestment" headerKey="-1" headerValue="All" id="countryInvestment" cssClass="inputSelectInvestement" /></td>
                                            </tr>
                                            <tr>
                                                <td class="fieldLabel">Start&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                <td><s:textfield name="startDate" id="startDate" cssClass="inputTextBlueSmall" value="%{startDate}"/><a href="javascript:cal2.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                </td>

                                                <td class="fieldLabel">End&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                <td>  <s:textfield name="endDate" id="endDate" cssClass="inputTextBlueSmall" value="%{endDate}"/><a href="javascript:cal3.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                </td>

                                            <tr>
                                                <td class="fieldLabel">Expenses Amount From :</td>
                                                <td> 
                                                    <s:textfield name="expensesAmountFrom" id="expensesAmountFrom" cssClass="inputTextBlueSmall" value="" onchange="isNumeric(this);"/>                                                                   
                                                </td>
                                                <td class="fieldLabel">Expenses Amount To:</td>
                                                <td> 
                                                    <s:textfield name="expensesAmountTo" id="expensesAmountTo" cssClass="inputTextBlueSmall" value="" onchange="isNumeric(this);"/>                                                                   
                                                </td>
                                            </tr>
                                            

                                            <script type="text/javaScript">
                                                var cal2 = new CalendarTime(document.forms['frmInvSearch'].elements['startDate']);
                                                cal2.year_scroll = true;
                                                cal2.time_comp = false;
                                                var cal3 = new CalendarTime(document.forms['frmInvSearch'].elements['endDate']);
                                                cal3.year_scroll = true;
                                                cal3.time_comp = false;
                                                var cal4 = new CalendarTime(document.forms['eventForm'].elements['startDateInvestment']);
                                                cal4.year_scroll = true;
                                                cal4.time_comp = false;
                                                var cal5 = new CalendarTime(document.forms['eventForm'].elements['endDateInvestment']);
                                                cal5.year_scroll = true;
                                                cal5.time_comp = false;
                                                                                 
                                            </script>
                                            <tr>
                                                <td class="fieldLabel">BDM :</td>
                                                   
                                                <td><s:select list="bdmMap" headerKey="" headerValue="--Please Select--" name="bdmId" id="bdmId" cssClass="inputSelect"/></td>
                                                <td align="right">
                                                    <input type="button" class="buttonBg"  align="right"  value="Add" onclick="investmentToggleOverlay();" />                     
                                                 <s:submit cssClass="buttonBg"  align="right"  value="Search" onclick="return compareDates()"/>
                                                  </td><td>  <input type="button"   class="buttonBg"  align="right"  value="Generate Excel" onclick="generateInvestmentXls();" />  
                                                </td>
                                            </tr>
                                        </table>
                                    </s:form>
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


                                            connection = ConnectionProvider.getInstance().getConnection();
                                            if (session.getAttribute(ApplicationConstants.INVESTMENT_DETAILS) != null) {
                                                queryString = session.getAttribute(ApplicationConstants.INVESTMENT_DETAILS).toString();
                                            }
                                            //out.println(queryString);
                                              //System.out.println("queryString--***>" + queryString);
%>


                                    <s:form action="" theme="simple" name="frmDBGrid">   

                                        <table cellpadding="0" cellspacing="0" width="100%" border="0">


                                            <tr>
                                                <td width="100%">


                                                    <grd:dbgrid id="tblInvestments" name="tblInvestments" width="98" pageSize="15"
                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2"
                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                        <%--    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                           addImage="../../includes/images/DBGrid/Add.png"  addAction="<%=empReviewAction %>"
                                                                           /> --%>
                                                        <grd:gridpager imgFirst="./../includes/images/DBGrid/First.gif" imgPrevious="./../includes/images/DBGrid/Previous.gif" 
                                                                       imgNext="./../includes/images/DBGrid/Next.gif" imgLast="./../includes/images/DBGrid/Last.gif"/>
                                                        <!--   addImage="./../includes/images/DBGrid/Add.png"  addAction="javascript:investmentToggleOverlay()" -->


                                                        <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                        imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                                        imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>   
                                                        <!--    Inv_Id Inv_Name Country StartDate EndDate TotalExpenses Currency Location Description AttachmentFileName AttachmentLocation CreatedBy CreatedDate ModifiedBy ModifiedDate -->
                                                        <%-- <grd:anchorcolumn dataField="Inv_Id" 
                                                                           headerText="InvestmentId" 
                                                                           linkUrl="javascript:toggleEditInvestmentOverlay({Inv_Id})" linkText="{Inv_Id}" width="20"/> --%>
                                                        <grd:anchorcolumn dataField="Inv_Name" 
                                                                          headerText="InvestmentName"
                                                                          linkUrl="javascript:toggleEditInvestmentOverlay({Inv_Id})" linkText="{Inv_Name}" width="15"/>

                                                        <grd:datecolumn  dataField="TotalExpenses"  headerText="TotalExpenses" width="5" /> 
                                                        <grd:datecolumn dataField="StartDate" headerText="StartDate" dataFormat="MM-dd-yyyy" width="12"/> 
                                                        <grd:datecolumn dataField="EndDate"  headerText="EndDate"  dataFormat="MM-dd-yyyy" width="12" /> 

                                                        <grd:textcolumn dataField="Location"  headerText="Location"  width="15" /> 
                                                        <grd:decodecolumn  decodeValues ="P,S" valueSeperator ="," displayValues ="Lead Pass,Lead Source" dataField="InvestmentType"  headerText="InvestmentType"  width="15" /> 
                                                        
                                                                    <grd:anchorcolumn dataField="TotalOpprtunity" 
                                                                          headerText="TotalOpprtunity"
                                                                          linkUrl="getOppurtunityList.action?investmentId={Inv_Id}&investmentName={Inv_Name}" linkText="{TotalOpprtunity}" width="5"/> 
                                                                     <grd:datecolumn  dataField="TotalLeads"  headerText="TotalLeads" width="5" /> 
                                                 <grd:imagecolumn headerText="BDMs" HAlign="center" 
                                                                         imageSrc="../includes/images/DBGrid/Add.png"
                                                                         linkUrl="getInvestmentBdms.action?investmentId={Inv_Id}" imageBorder="0"
                                                                         alterText="Add BDMs"></grd:imagecolumn> 
                                                        

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
                                                    <s:hidden name="investmentName1" value="%{investmentName1}"/>
                                                    <s:hidden name="countryInvestment" value="%{countryInvestment}"/>
                                                    <s:hidden name="startDate" value="%{startDate}"/>
                                                    <s:hidden name="endDate" value="%{endDate}"/>
                                                    <s:hidden name="expensesAmountFrom" value="%{expensesAmountFrom}"/>
                                                    <s:hidden name="expensesAmountTo" value="%{expensesAmountTo}"/>
                                                    <s:hidden name="bdmId" value="%{bdmId}"/>
                                                    <%--         <s:hidden  name="startDate" value="%{StartDate}"/>
                                                              <s:hidden  name="endDate" value="%{EndDate}"/>
                                                              <s:hidden  name="reviewType" value="%{reviewType}"/>
                                                    <%--  <s:hidden  name="reviewStatus" value="%{reviewStatus}"/> --%>
                                                    <%--     <s:hidden  name="empnameById" value="%{empnameById}"/>
                                                         <s:hidden  name="reviewTlStatus" value="%{reviewTlStatus}"/>
                                                         <s:hidden  name="reviewHrStatus" value="%{reviewHrStatus}"/>  --%>
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


                                    <%--   </sx:div> --%>


                                    <script type="text/javascript">

                                    
                                        var countries=new ddtabcontent("accountTabs")
                                        countries.setpersist(false)
                                        countries.setselectedClassTarget("link") //"link" or "linkparent"
                                        countries.init()

                                    </script>
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

        </table>
        <!--//END MAIN TABLE : Table for template Structure-->

        <!--  End of the main table -->        


    </body>
</html>
 

