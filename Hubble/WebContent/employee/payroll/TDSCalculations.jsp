<%-- 
   Document   : tds calculation 
   Created on : April 28, 2016, 3:13:29 PM
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
        <title>Hubble Organization Portal :: Employee TDS Calculations</title>
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
              <s:include value="PayrollCalculations.jsp"/>

        <s:include value="/includes/template/headerScript.html"/>
         <style>
            a.tooltip {outline:none; }
            a.tooltip strong {line-height:30px;}
            a.tooltip:hover {text-decoration:none;} 
            a.tooltip span {
                z-index:15;display:none; padding:14px 20px;
               
                width:220px; line-height:16px;
            }

            a.tooltip:hover span{
                display:inline; position:absolute; color:#111;
                border:1px solid #DCA; background:rgb(40, 138, 209); font-family: Verdana; font-size: 9px;
                font-weight: normal;
                color: white;}
            .callout {z-index:20;position:absolute;top:30px;border:0;left:-12px;}

            /*CSS3 extras*/
            
            
         

        </style>
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">

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
                                    <li><a href="#"  rel="NoDuesSettlementTab"  >TDS Calculation</a></li>

                                </ul>
                                <div  style="border:1px solid gray; width:840px;height: 500px; overflow:auto; margin-bottom: 1em;">   
                                    <!--//START TAB : -->

                                    <!--//END TAB : -->
                                    <%-- </sx:tabbedpanel> --%>


                                    <div id="NoDuesSettlementTab" class="tabcontent"  >
                                        <div id="loadingMessage12"></div>   
                                        <div id="noDuesOverlay"></div>              <!-- Start Overlay -->
                                        <div id="noDuesSpecialBox">


                                            <s:form theme="simple"  align="center" name="noDuesOverlay" action="employeeNoDuesSettlement.action" method="post" enctype="multipart/form-data" onsubmit="return validateForm();"   >
                                                <%
                                                    if (session.getAttribute("resultMessageforNoDueOverlay") != null) {
                                                        out.println(session.getAttribute("resultMessageforNoDueOverlay"));
                                                        session.removeAttribute("resultMessageforNoDueOverlay");
                                                    }

                                                %>

                                                
                                                <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                    <tr>                               
                                                        <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="noDueheaderLabel">TDS Calculation</span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                            <a href="#" onmousedown="tdsCalculationOverlay()" >
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

                                                                <td calspan="3" class="fieldLabel" style="text-align: left;padding-left: 45px">Emp Id:</td><td><s:textfield name="empNo" id="empNo" cssClass="inputTextBlueSmall" value=""  />
                                                                    </td>


                                                                    <td class="fieldLabel"  style="display: block;text-align: right;">Employee Name:</td>
                                                                    <td>  <s:textfield name="empName" id="empName" cssClass="inputTextBlueSmall" value=""  readOnly="true"/>
                                                                        
                                                                    </td>

                                                                </tr>
                                                                <tr>

                                                                <td calspan="3" class="fieldLabel" style="text-align: left;padding-left: 45px">Tution Fee:</td>
                                                                <td><s:textfield name="tutionfees" id="tutionfees" cssClass="inputTextBlueSmall" value="%{tutionfees}"  /></td>


                                                                    <td class="fieldLabel"  style="display: block;text-align: right;">NSC:</td>
                                                                    <td>  <s:textfield name="nsc" id="nsc" cssClass="inputTextBlueSmall" value="%{nsc}"  />
                                                                        
                                                                    </td>

                                                                </tr>
                                                                <tr>

                                                                <td calspan="3" class="fieldLabel" style="text-align: left;padding-left: 45px">Life Ins. Premium :</td>
                                                                <td><s:textfield name="lifeIns" id="lifeIns" cssClass="inputTextBlueSmall" value="%{lifeIns}"  />
                                                                    </td>


                                                                    <td class="fieldLabel"  style="display: block;text-align: right;">FD - Tax Saving:</td>
                                                                    <td>  <s:textfield name="fd" id="fd" cssClass="inputTextBlueSmall" value="%{fd}" />
                                                                        
                                                                    </td>

                                                                </tr>
                                                                <tr>

                                                                <td calspan="3" class="fieldLabel" style="text-align: left;padding-left: 45px">HB Loan Priciple:</td>
                                                                <td><s:textfield name="hbLoanPrinciple" id="hbLoanPrinciple" cssClass="inputTextBlueSmall" value="%{hbLoanPrinciple}"  />
                                                                    </td>


                                                                    <td class="fieldLabel"  style="display: block;text-align: right;">HB Loan  Interest:</td>
                                                                    <td>  <s:textfield name="hbLoanInterst" id="hbLoanInterst" cssClass="inputTextBlueSmall" value="%{hbLoanInterst}" />
                                                                        
                                                                    </td>

                                                                </tr>
                                                                <tr>

                                                                <td calspan="3" class="fieldLabel" style="text-align: left;padding-left: 45px">Medical Ins.:</td>
                                                                <td><s:textfield name="medicalIns" id="medicalIns" cssClass="inputTextBlueSmall" value="%{medicalIns}" />
                                                                    </td>


                                                                    <td class="fieldLabel"  style="display: block;text-align: right;">Mutual Funds:</td>
                                                                    <td>  <s:textfield name="mutualFunds" id="mutualFunds" cssClass="inputTextBlueSmall" value="%{mutualFunds}" />
                                                                        
                                                                    </td>

                                                                </tr>
                                                                <tr>

                                                                <td calspan="3" class="fieldLabel" style="text-align: left;padding-left: 45px">Edu.Loan Int.:</td>
                                                                <td><s:textfield name="eduInterest" id="eduInterest" cssClass="inputTextBlueSmall" value="%{eduInterest}" />
                                                                    </td>


                                                                    <td class="fieldLabel"  style="display: block;text-align: right;">PPF:</td>
                                                                    <td>  <s:textfield name="ppf" id="ppf" cssClass="inputTextBlueSmall" value="%{ppf}" />
                                                                        
                                                                    </td>

                                                                </tr>
                                                                <tr>

                                                                <td calspan="3" class="fieldLabel" style="text-align: left;padding-left: 45px">HRA.:</td>
                                                                <td><s:textfield name="hra" id="hra" cssClass="inputTextBlueSmall" value="%{hra}" />
                                                                    </td>
                                                </tr>
                                                <tr>
                                                <td calspan="3" class="fieldLabel" style="text-align: left;padding-left: 45px">TDS:</td>
                                                <td><s:textfield name="tds" id="tds" cssClass="inputTextBlueSmall" value="%{tds}" />
                                                                    </td>
                                                </tr>
                                                <tr>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                   
                                                                    <td>   <input type="button" class="buttonBg" value="Calculate" onclick="tdsCalculation()"/>&nbsp;<input type="button" class="buttonBg" value="Add" onclick="tdsCalculationOverlay()"/>
                                                                        
                                                                    </td>

                                                                </tr>
                                                            </table>
                                                            
                                                        </td>
                                                    </tr>


                                                </table>
                                            </s:form>
<!--                                            <script type="text/javaScript">
                                                var cal7 = new CalendarTime(document.forms['noDuesOverlay'].elements['overlayFromDate']);
                                               // document.getElementById('fromDate').focus();
                                                cal7.year_scroll = true;
                                                cal7.time_comp = false;
                                                var cal8 = new CalendarTime(document.forms['noDuesOverlay'].elements['overlayToDate']);
                                                cal8.year_scroll = true;
                                                cal8.time_comp = false;
                                                                                 
                                            </script>-->



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
                                                    <s:form name="frmNoDuesSettlement" action="employeeNoDueFormSearch.action" theme="simple">
                                                        <%
                                                            if (session.getAttribute("resultMessageforNoDue") != null) {
                                                                out.println(session.getAttribute("resultMessageforNoDue"));
                                                                session.removeAttribute("resultMessageforNoDue");
                                                            }

                                                        %>
                                                        <table cellpadding="1" cellspacing="1" border="0" width="650px" align="center">
                                                            <tr>
                                                                 <td class="fieldLabel">Year(YYYY):</td>
                                                <td>

                                                    <s:textfield name="year" id="year" maxlength="4" cssClass="inputTextBlue" value="%{year}"/>
                                                </td>
                                                <td class="fieldLabel">Month:</td>
                                                <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="month" id="month" onchange="load(event);" headerValue="select" headerKey="0" value="%{month}" cssClass="inputSelectSmall"/></td>
                                                                <td colspan="" align="right">

                                                                    <s:submit cssClass="buttonBg"  align="right"  value="Search" />

                                                                </td>
                                                                <td>
                                                                    <input type="button" class="buttonBg" value="Add" onclick="tdsCalculationOverlay()"/> <a href="#" class="tooltip">  <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/information.png" width="15px" height="15px" />
                                                        <!--  <span>By default mail is generated to <strong>FROM Address as BCC</strong>. So,If required any other Employee in the BCC, You can Add them.   </span> -->
                                                        <span>Life Insurance Premium , Housing Loan Repayment , NSC , PPF Contribution , Tution Fee , ELSS , Post Office Term Deposit , Bank Deposit , LIC from Salary , Others , Contribution to PF , 80CCD-NPS-Employees contribution <br>The Sum of all these employee savings should not exceed greater than Rs 150000/- </span>
                                                        </a>
                                                                </td>
                                                            </tr>

                                                        </table>
                                                    </s:form>

<!--                                                    <script type="text/javaScript">
                                                        var cal5 = new CalendarTime(document.forms['frmNoDuesSettlement'].elements['fromDate']);
                                                      //  document.getElementById('fromDate').focus();
                                                        cal5.year_scroll = true;
                                                        cal5.time_comp = false;
                                                        var cal6 = new CalendarTime(document.forms['frmNoDuesSettlement'].elements['toDate']);
                                                        cal6.year_scroll = true;
                                                        cal6.time_comp = false;
                                                                                 
                                                    </script>-->

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
                                        
                                        if (strTmp != null)
                                        intCurr = Integer.parseInt(strTmp);
                                        
                                        /* Specifing Shorting Column */
                                        strSortCol = request.getParameter("txtSortCol");
                                        
                                        if (strSortCol == null) strSortCol = "AttachmentName";
                                        
                                        strSortOrd = request.getParameter("txtSortAsc");
                                        if (strSortOrd == null) strSortOrd = "ASC";



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

                                                    <s:form action="" theme="simple" name="frmDBGrid">   

                                                        <table cellpadding="0" cellspacing="0" width="100%" border="0">


                                                            <tr>
                                                                <td width="100%">





                                                                    <grd:dbgrid id="tblStat" name="tblStat" width="98" pageSize="8"
                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2"
                                                                    dataMember="<%=queryString1%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                                        <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif" 

                                                                                       /> 


                                                                        <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                        imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                                                        imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>   

                                                                       
                                                                        <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"
                                                                                         linkUrl="javascript:NoDuesSettlementEditOverlay({Id})" imageBorder="0"
                                                                                         imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>

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
                                                                                        headerText="Created Date"  dataFormat="yyyy-MM-dd"  sortable="true"
                                                                                        width="15"/>
                                                                        <grd:anchorcolumn dataField="Comments" 
                                                                                          headerText="Comments" 
                                                                                          linkUrl="javascript:showNoDuesCommentsPopUp('{Comments}')" linkText="Click To View" width="25"/>
                                                                    </grd:dbgrid>




                                                                    <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                    <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                    <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">

                                                                    <input type="hidden" name="submitFrom" value="dbGrid">
                                                                    <s:hidden  name="fromDate" value="%{fromDate}"/>
                                                                    <s:hidden  name="toDate" value="%{toDate}"/>
                                                                    
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


