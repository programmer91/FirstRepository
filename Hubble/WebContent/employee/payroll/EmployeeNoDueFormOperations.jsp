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
        <title>Hubble Organization Portal :: Employee Search</title>
<link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/new/x0popup.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
  <!--        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/frmNoDueDBGrid.js"/>"></script>-->
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/EmpSearchClientValidation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>   
        <%--  <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/ajaxfileupload.js"/>"></script>  
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/FileUpload.js"/>"></script>  --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/payrollajaxscript.js"/>"></script>
        <%-- <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/payrollclientvalidations.js"/>"></script>--%>
        <script>
           
        </script>

        <s:include value="/includes/template/headerScript.html"/>
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;" >

        <%!
            /* Declarations */
            Connection connection;
            String queryString;
            String queryString1;
            String strTmp;
            String strSortCol;
            String strSortOrd;
            String submittedFrom;
            String searchSubmitValue;
            String strTmp1;
            String strSortCol1;
            String strSortOrd1;
            int intSortOrd1 = 0;
            int intCurr1;
            boolean blnSortAsc1 = true;
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
                                    <!--                                    <li><a href="#"  rel="TaxExemptionsTab"  >TEF</a></li>
                                                                        <li><a href="#" class="selected" rel="PaySlipTab"  >Pay Slip</a></li>-->
                                    <s:if test="#session.noDueApprovers == 1">
                                    <li><a href="#"  rel="NoDuesSettlementTab"  >No Dues</a></li>
                                    </s:if>
                                    <s:if test="#session.noDueRemainders == 1">
                                    <li><a href="#"  rel="NoDuesSettlementRemainderTab"  >No Dues Reminder</a></li>
                                    </s:if>

                                </ul>
                                <div  style="border:1px solid gray; width:840px;height: 500px; overflow:auto; margin-bottom: 1em;">   
                                    <!--//START TAB : -->

                                    <!--//END TAB : -->
                                    <%-- </sx:tabbedpanel> --%>


                                    <div id="NoDuesSettlementTab" class="tabcontent"  >

                                        <div id="noDuesOverlay"></div>              <!-- Start Overlay -->
                                        <div id="noDuesSpecialBox">


                                            <s:form theme="simple"  align="center" name="noDuesOverlay" action="employeeNoDuesSettlement.action" method="post" enctype="multipart/form-data" onsubmit="return validateForm();"   >
                                                <%
                                                    if (session.getAttribute("resultMessageforNoDueOverlay") != null) {
                                                        out.println(session.getAttribute("resultMessageforNoDueOverlay"));
                                                        session.removeAttribute("resultMessageforNoDueOverlay");
                                                    }

                                                %>

                                                <s:hidden name="noDueId" id="noDueId" cssClass="" value=""/>
                                                <s:hidden name="noDueEmpId" id="noDueEmpId" cssClass="" value=""/>
                                                  <s:hidden name="country" id="country" cssClass="" value=""/>
                                                <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                    <tr>                               
                                                        <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="noDueheaderLabel"></span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                            <a href="#" onmousedown="NoDuesSettlementOverlayOperations()" >
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                            </a>  

                                                        </td></tr>
                                                    <tr>
                                                        <td colspan="4">
                                                            <div id="load" style="color: green;display: none;">Loading..</div>
                                                            <div id="resultMessageNoDue"></div>
                                                        </td>
                                                    </tr>
                                                    <tr><td colspan="4">
                                                            <table style="width:100%;" align="right" border="0">
                                                                <tr>

                                                                    <td calspan="" class="fieldLabel" style="">From&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                                    <td><s:textfield name="fromDate1" id="overlayFromDate" cssClass="inputTextBlueSmall" value="" onchange="isValidDate(this)" />
                                                                    </td>


                                                                    <td class="fieldLabel"  style="display: block;text-align: right;">To&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                                    <td>  <s:textfield name="toDate1" id="overlayToDate" cssClass="inputTextBlueSmall" value="" onfocus="getToDate('overlayFromDate','overlayToDate');" onchange="isValidDate(this)" readOnly="true"/>
                                                                       
                                                                    </td>



<!--                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>-->


                                                                </tr>
                                                            </table>
                                                            <table style="width:100%;" align="right" border="0">
                                                                <tr>



                                                                    <td valign="top" align="right">
                                                                        <s:checkbox name="release" id="release"
                                                                                    value="" 
                                                                                    theme="simple" /> 
                                                                    </td> 


                                                                    <td class="fieldLabel" colspan="3">

                                                                        <div style="text-align: justify;text-justify: inter-word; width: 90%">It is hereby acknowledged that the employee hereby remise, release and Forever discharge employer and their heirs, executors,administrators, successors or assigns,together with all other persons, firms, and corporations  whomsoever of and from any and all actions, claims and demands, whatsoever which the employee now has or may hereafter have accrued on account of or in any way arising out of his employment with the employer.</div>
                                                                    </td>


                                                                </tr>


                                                                <tr>

                                                                    <td valign="top" align="right">
                                                                        <s:checkbox name="commissions" id="commissions"
                                                                                    value="" 
                                                                                    theme="simple" /> 
                                                                    </td>

                                                                    <td class="fieldLabel" colspan="3">
                                                                        <div style="text-align: justify;text-justify: inter-word; width: 90%">I also acknowledge that I have received the Commissions or any bonus that the company owes to me.</div>

                                                                    </td>

                                                                </tr>
                                                                <tr>

                                                                    <td valign="top" align="right">
                                                                        <s:checkbox name="settled" id="settled"
                                                                                    value="" 
                                                                                    theme="simple" onchange="getAmount();" /> 


                                                                    </td>

                                                                    <td class="fieldLabel" colspan="3">
                                                                        <div style="text-align: justify;text-justify: inter-word; width: 90%">I am having the following dues that needs to be settled.</div>

                                                                    </td>

                                                                </tr>
                                                                <tr>


                                                                    <td colspan="4">
                                                                        <span class="fieldLabel" id="AmountTd" style="display: none;margin-left: 60px;">Amount:&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield name="dueAmount" id="dueAmount" cssClass="inputTextBlueSmall" value="%{dueAmount}"  onkeypress="return isNumberKeyPayrollAmt(event);"/>

                                                                        </span>
                                                                    </td>
                                                                </tr>

                                                                <tr>
                                                                    <td class="fieldLabel" style="text-align: right;" valign="top" >Comments:</td>
                                                                    <td colspan="3"><s:textarea rows="3" cols="95" style='width: 455px; height: 45px;' name="comments" cssClass="inputTextarea1" value="%{comments}" onchange="fieldLengthValidator(this);" id="comments"/> 
                                                                    </td>


                                                                </tr>
                                                                <tr id="approverCommentsTr" style="">



                                                                    <td class="fieldLabel" style="text-align: left;" valign="top">Approver&nbsp;Comments:</td>
                                                                    <td  colspan="3"><s:textarea rows="3" cols="95" style='width: 454px; height: 45px;' name="approverComments" cssClass="inputTextarea1" value="%{approverComments}" onchange="fieldLengthValidator(this);" id="approverComments"/> 
                                                                    </td>


                                                                </tr>
                                                                <tr>
                                                                    <td colspan="4">
                                                                        <table align="right">
                                                                            <tr>
                                                                                <td  colspan="3" align="right">
                                                                                    <input type="button" value="Approve" style="display: none;" onclick="approveOrRejectNoDue('0');" class="buttonBg" id="approveButton"/>
                                                                                </td>



                                                                                <td colspan="">
                                                                                    <input type="button" value="Reject" style="display: none;" onclick="approveOrRejectNoDue('1');" class="buttonBg" id="rejectButton"/>

                                                                                </td></tr></table>
                                                                    </td>

                                                                </tr>
                                                                <tr>
                                                                    <td  colspan="4"><div id="statusMessageNoDues" style="display:none;"></div></td>
                                                                </tr>



                                                            </table>


                                                        </td>
                                                    </tr>


                                                </table>
                                            </s:form>
                                            <script type="text/javaScript">
               
                                                                                 
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
                                                        }
                                                        if (request.getAttribute(ApplicationConstants.QUERY_STRING) != null) {
                                                            queryString1 = request.getAttribute(ApplicationConstants.QUERY_STRING).toString();
                                                        }

                                                    %>

                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <s:form name="frmNoDuesSettlementOperations" action="employeeNoDueFormSearchOperations.action" theme="simple">
                                                        <%
                                                            if (session.getAttribute("resultMessageforNoDue") != null) {
                                                                out.println(session.getAttribute("resultMessageforNoDue"));
                                                                session.removeAttribute("resultMessageforNoDue");
                                                            }

                                                        %>
                                                        <table cellpadding="1" cellspacing="1" border="0" width="650px" align="center">
                                                            <tr>
                                                                <td class="fieldLabel" width="200px" align="right">Department :</td>
                                                                <td><s:select label="Select Department" 
                                                                          name="departmentId"
                                                                          id="departmenttId"
                                                                          headerKey=""
                                                                          headerValue="All"
                                                                          list="departmentIdList" cssClass="inputSelect" value="%{departmentId}" onchange="getEmployeesByDept();"/></td>

                                                                <td class="fieldLabel">EmpName :</td>
                                                                <td ><s:select list="empnamesList" id="empnameByIdd" name="empnameById" headerKey="" headerValue="All" cssClass="inputSelect" value="%{empnameById}"/></td> 
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel" align="left">Status:</td>
                                                                <td colspan="">
                                                                    <s:select name="status"  id="status" 
                                                                              cssClass="inputSelect"  
                                                                              list="{'Active','InActive'}" value="%{status}" onchange="getEmployeesByDept();"></s:select> 
                                                                    </td>
                                                                    <td class="fieldLabel" align="left">NoDue&nbsp;Status:</td>
                                                                    <td colspan="">
                                                                    <s:select name="noDueStatus"  id="noDueStatus" 
                                                                              cssClass="inputSelect"  
                                                                              list="{'Submitted','Approved','Rejected'}" value="%{noDueStatus}" onchange=""></s:select> 
                                                                    </td>


                                                                </tr>
                                                                <tr>
                                                                    <td class="fieldLabel">Year(YYYY):</td>
                                                                    <td>

                                                                    <s:textfield name="year" id="year" maxlength="4" cssClass="inputTextBlue" value="%{year}" readonly="false"/>
                                                                </td>
                                                                <td class="fieldLabel">Month:</td>
                                                                <td>
                                                                   <%-- <s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="month" id="month" onchange="" headerValue="select" headerKey="" value="%{month}"/> --%>
                                                                   <s:select list="#@java.util.LinkedHashMap@{'1':'Q1','2':'Q2','3':'Q3','4':'Q4'}" name="month" id="month" onchange="" headerValue="select" headerKey="" value="%{month}"/>
                                                                </td>
                                                                <td  align="right">

                                                                    <s:submit cssClass="buttonBg"  align="right"  value="Search" />

                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                  <td class="fieldLabel">Due&nbsp;Amt>0:</td>
                                                                  <td valign="top" align="">
                                                                        <s:checkbox name="withDueAmt" id="withDueAmt"
                                                                                    value="%{withDueAmt}" 
                                                                                    theme="simple"  /> 


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
                                                        strSortCol = request.getParameter("txtSortCol");

                                                        if (strSortCol == null) {
                                                            strSortCol = "AttachmentName";
                                                        }

                                                        strSortOrd = request.getParameter("txtSortAsc");
                                                        if (strSortOrd == null) {
                                                            strSortOrd = "ASC";
                                                        }



                                                        try {

                                                            /* Getting DataSource using Service Locator */
                                                            connection = ConnectionProvider.getInstance().getConnection();



                                                            /* Sql query for retrieving resultset from DataBase */
                                                            /*queryString  =null;*/
                                                            //queryString = "SELECT ReviewType FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id)";
                                                            String empId = session.getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                                                            if (request.getAttribute(ApplicationConstants.QUERY_STRING) != null) {
                                                                queryString = request.getAttribute(ApplicationConstants.QUERY_STRING).toString();
                                                            }

                                                            System.out.println(queryString);

                                                    %>

                                                    <s:form action="" theme="simple" name="frmDBGrid" id="frmDBGrid">   

                                                        <table cellpadding="0" cellspacing="0" width="100%" border="0">


                                                            <tr>
                                                                <td width="100%">





                                                                    <grd:dbgrid id="tblStat" name="tblStat" width="98" pageSize="8"
                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2"
                                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                                        <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif" 


                                                                                       /> 


                                                                        <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                        imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                                                        imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>   

                                                                        <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"
                                                                                         linkUrl="javascript:NoDuesSettlementEditOverlayOperations({Id})" imageBorder="0"
                                                                                         imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>
                                                                        <grd:datecolumn dataField="EmpName" 
                                                                                        headerText="Employee Name" 
                                                                                        width="25"/>


                                                                        <grd:datecolumn dataField="FromDate" 
                                                                                        headerText="Applied Date"  dataFormat="yyyy-MM-dd"  sortable="true"
                                                                                        width="15"/>
                                                                        <grd:datecolumn dataField="ToDate" 
                                                                                        headerText="To Date"  dataFormat="yyyy-MM-dd"  sortable="true"
                                                                                        width="15"/>

                                                                        <grd:datecolumn dataField="DuesAmount" 
                                                                                        headerText="DuesAmount" 
                                                                                        width="15"/>
                                                                        <grd:datecolumn dataField="Status" 
                                                                                        headerText="Status"  
                                                                                        width="15"/>
                                                                        <grd:datecolumn dataField="CreatedDate" 
                                                                                        headerText="Submitted Date"  dataFormat="yyyy-MM-dd"  sortable="true"
                                                                                        width="15"/>
                                                                        <grd:anchorcolumn dataField="Comments" 
                                                                                          headerText="Comments" 
                                                                                          linkUrl="javascript:showNoDuesCommentsPopUp('{Comments}')" linkText="Click To View" width="25"/>
                                                                    </grd:dbgrid>




                                                                    <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                    <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                    <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">

                                                                    <input type="hidden" name="submitFrom" value="dbGrid">
                                                                    <s:hidden  name="year" value="%{year}"/>
                                                                    <s:hidden  name="month" value="%{month}"/>
                                                                    <s:hidden  name="noDueStatus" value="%{noDueStatus}"/>
                                                                    <s:hidden  name="status" value="%{status}"/>
                                                                    <s:hidden  name="empnameById" value="%{empnameById}"/>
                                                                    <s:hidden  name="departmentId" value="%{departmentId}"/>
                                                                    <s:hidden  name="withDueAmt" value="%{withDueAmt}"/>
                                                                    
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
                                    <div id="NoDuesSettlementRemainderTab" class="tabcontent" >
                                        <div id="noDuesOverlayRemainder"></div>              <!-- Start Overlay -->
                                        <div id="noDuesSpecialBoxRemainder">


                                            <s:form theme="simple"  align="center" name="noDuesOverlayRemainder" action="" method="post" enctype=""  >
                                                <%
                                                    if (session.getAttribute("resultMessageforNoDueOverlay") != null) {
                                                        out.println(session.getAttribute("resultMessageforNoDueOverlay"));
                                                        session.removeAttribute("resultMessageforNoDueOverlay");
                                                    }

                                                %>

                                                <s:hidden name="noDueId" id="noDueId" cssClass="" value=""/>
                                                <s:hidden name="noDueEmpId" id="noDueEmpId" cssClass="" value=""/>
                                                
                                                <table align="left" border="0" cellspacing="0" style="width:100%;margin-left: 0px;">
                                                    <tr>                               
                                                        <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="noDueheaderLabelRemainder"></span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                            <a href="#" onmousedown="NoDuesSettlementOverlayOperationsRemainder()" >
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                            </a>  

                                                        </td></tr>
                                                    <tr>
                                                        <td colspan="4">
                                                            <div id="loadForRemainder" style="color: green;display: none;">Loading..</div>
                                                            <div id="resultMessageNoDueRemainder"></div>
                                                        </td>
                                                    </tr>
                                                    <tr>

                                                        <td calspan="" class="fieldLabel" style="">From&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                        <td><s:textfield name="fromDate1" id="overlayFromDateRemainder" cssClass="inputTextBlueWithDatePicker" onchange="isValidDate(this)" value="" /><a href="javascript:cal10.popup();">
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0" ></a>
                                                        </td>


                                                        <td class="fieldLabel"  style="width: 210px;">To&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                        <td>  <s:textfield name="toDate1" id="overlayToDateRemainder" cssClass="inputTextBlueWithDatePicker" value="" onfocus="getToDate('overlayFromDateRemainder','overlayToDateRemainder');" onchange="isValidDate(this)" readOnly="true"/>
                                                            <a href="javascript:cal11.popup();">
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0" ></a>
                                                        </td>



<!--                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>-->


                                                    </tr>


                                                    <tr>
                                                        <td class="fieldLabel" width="200px" align="right">Department :</td>
                                                        <td><s:select label="Select Department" 
                                                                  name="departmentId"
                                                                  id="departmentId"
                                                                  headerKey="All"
                                                                  headerValue="All"
                                                                  list="departmentIdList" cssClass="inputSelect" value="" onchange="getEmployeesByDeptRem();"/></td>

                                                        <td class="fieldLabel">EmpName :</td>
                                                        <td ><s:select list="empnamesList" id="empnameById" name="empnameById" headerKey="All" headerValue="All" cssClass="inputSelect" value="" multiple="true" style="width: 202px;"/></td> 
                                                    </tr>

                                                    <tr>
<!--                                                        <td class="fieldLabel" align="left">Emp&nbsp;Status:</td>-->
                                                        <td colspan="4">
                                                            <s:hidden name="status"  id="statusRem" value="Active"/>
                                                            </td>
                                                        </tr>

                                                        <tr>
                                                            <td class="fieldLabel" style="text-align: right;" valign="top" >Comments:</td>
                                                            <td colspan="3"><s:textarea rows="4" cols="75" style='width: 405px; height: 45px;' name="comments" cssClass="inputTextarea1" value="" onchange="fieldLengthValidator(this);" id="commentsRem"/> 
                                                        </td>


                                                    </tr>

                                                    <tr>
<td></td><td></td><td></td>
<td colspan="4" align="right" style="padding-right: 70px;">
                                                            <input type="button" class="buttonBg" value="Add" id="remianderAddButton" onclick="addRemainderValuesNoDues();"/>
                                                        </td>
                                                        


                                                    </tr>


                                                </table>


                                            </s:form>
                                            <script type="text/javaScript">
                                                var cal10 = new CalendarTime(document.forms['noDuesOverlayRemainder'].elements['overlayFromDateRemainder']);
                                                // document.getElementById('fromDate').focus();
                                                cal10.year_scroll = true;
                                                cal10.time_comp = false;
                                                var cal11 = new CalendarTime(document.forms['noDuesOverlayRemainder'].elements['overlayToDateRemainder']);
                                                cal11.year_scroll = true;
                                                cal11.time_comp = false;
                                                                                 
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
                                                 

                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <s:form name="frmNoDuesSettlementOperationsRemainder" id="frmNoDuesSettlementOperationsRemainder" action="employeeNoDueFormSearchOperationsRemainder.action" theme="simple">
                                                        <%
                                                            if (session.getAttribute("resultMessageforNoDue") != null) {
                                                                out.println(session.getAttribute("resultMessageforNoDue"));
                                                                session.removeAttribute("resultMessageforNoDue");
                                                            }

                                                        %>
                                                        <table cellpadding="1" cellspacing="1" border="0" width="650px" align="center">
                                                            <tr>
                                                        <td colspan="4">
                                                            <div align="right" id="loadForRemainderCloseInSearch" style="color: red;display: none;">Loading..</div>
                                                            <div align="right" id="resultMessageNoDueRemainderCloseInSearch" style="color: green;"></div>
                                                        </td>
                                                    </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Year(YYYY):</td>
                                                                <td>

                                                                    <s:textfield name="year" id="yearForRemainder" maxlength="4" cssClass="inputTextBlue" value="%{year}" readonly="false"/>
                                                                </td>

                                                                <td  align="right">

                                                                    <s:submit cssClass="buttonBg"  align="right"  value="Search" />

                                                                </td>
                                                                <td>

                                                                    <input type="button" class="buttonBg" value="Add" onclick="NoDuesSettlementOverlayOperationsRemainder();"/>

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
                                                    <%
                                                        /* String Variable for storing current position of records in dbgrid*/
                                                        strTmp1 = request.getParameter("txtCurr");

                                                        intCurr1 = 1;

                                                        if (strTmp1 != null) {
                                                            intCurr1 = Integer.parseInt(strTmp1);
                                                        }

                                                        /* Specifing Shorting Column */
                                                        strSortCol1 = request.getParameter("txtSortCol");

                                                        if (strSortCol1 == null) {
                                                            strSortCol1 = "AttachmentName";
                                                        }

                                                        strSortOrd1 = request.getParameter("txtSortAsc");
                                                        if (strSortOrd1 == null) {
                                                            strSortOrd1 = "ASC";
                                                        }



                                                        try {

                                                            /* Getting DataSource using Service Locator */
                                                            connection = ConnectionProvider.getInstance().getConnection();



                                                            /* Sql query for retrieving resultset from DataBase */
                                                            /*queryString  =null;*/
                                                            //queryString = "SELECT ReviewType FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id)";
                                                            String empId = session.getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                                                            if (request.getAttribute(ApplicationConstants.QS_ACTIVITY_QUERY) != null) {
                                                                queryString1 = request.getAttribute(ApplicationConstants.QS_ACTIVITY_QUERY).toString();
                                                            }

                                                          //  System.out.println(queryString1);

                                                    %>

                                                    <s:form action="" theme="simple" name="frmNoDueDBGrid" id="frmNoDuesSettlementOperationsGrid">   

                                                        <table cellpadding="0" cellspacing="0" width="100%" border="0">


                                                            <tr>
                                                                <td width="100%">





                                                                    <grd:dbgrid id="tblStat" name="tblStat" width="98" pageSize="8"
                                                                    currentPage="<%=intCurr1%>" border="0" cellSpacing="1" cellPadding="2"
                                                                    dataMember="<%=queryString1%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                                        <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif" 


                                                                                       /> 


                                                                        <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                        imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                                                        imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>   

                                                                         <grd:datecolumn dataField="StartDate" 
                                                                                        headerText="StartDate"  dataFormat="yyyy-MM-dd"  sortable="true"
                                                                                        width="15"/>
                                                                         <grd:datecolumn dataField="EndDate" 
                                                                                        headerText="EndDate"  dataFormat="yyyy-MM-dd"  sortable="true"
                                                                                        width="15"/>
                                                                         <grd:anchorcolumn dataField="EmpList" 
                                                                                          headerText="EmpList" 
                                                                                          linkUrl="javascript:showEmpListRemainder('{EmpList}','{DepartmentId}')" linkText="Click To View" width="20"/>
  <grd:datecolumn dataField="Status" 
                                                                                        headerText="Status" 
                                                                                        width="25"/>
                                                                        <grd:datecolumn dataField="CreatedBy" 
                                                                                        headerText="CreatedBy" 
                                                                                        width="25"/>


                                                                        <grd:datecolumn dataField="CreatedDate" 
                                                                                        headerText="Submitted Date"  dataFormat="yyyy-MM-dd"  sortable="true"
                                                                                        width="15"/>
                                                                       
                                                                        <grd:anchorcolumn dataField="Comments" 
                                                                                          headerText="Comments" 
                                                                                          linkUrl="javascript:showNoDuesCommentsPopUp('{Comments}')" linkText="Click To View" width="25"/>
                                                                        <grd:imagecolumn headerText="Close" width="4" HAlign="center" imageSrc="../../includes/images/closeButton.png"
                                                                                         linkUrl="javascript:noDuesRemainderClose({Id},'{StartDate}','{EndDate}','{EmpList}','{Flag}','{DepartmentId}')" imageBorder="0"
                                                                                         imageWidth="16" imageHeight="16" alterText="Click to delete"></grd:imagecolumn>
                                                                    </grd:dbgrid>




                                                                    <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr1%>">
                                                                    <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol1%>">
                                                                    <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd1%>">

                                                                    <input type="hidden" name="submitFrom" value="dbGrid">
                                                                 
                                                                   <s:hidden  name="year" value="%{year}"/>
                                                                    <s:hidden  name="month" value="%{month}"/>
                                                                    <s:hidden  name="noDueStatus" value="%{noDueStatus}"/>
                                                                    <s:hidden  name="status" value="%{status}"/>
                                                                    <s:hidden  name="empnameById" value="%{empnameById}"/>
                                                                    <s:hidden  name="departmentId" value="%{departmentId}"/>
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
                                    </div>


                                </div>
                                <script type="text/javascript">

                                    var countries=new ddtabcontent("accountTabs")
                                    countries.setpersist(true)
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
 <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/x0popup.min.js"/>"></script>
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->



    </body>
</html>


