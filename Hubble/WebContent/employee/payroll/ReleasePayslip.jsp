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
        <title>Hubble Organization Portal :: Release Employee Payslip </title>
    <sx:head cache="true"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/new/x0popup.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/EmpSearchClientValidation.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/payrollajaxscript.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/payrollclientvalidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>   



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
                                <li ><a href="#" class="selected" rel="employeeSearchTab">Release Payslip</a></li>
                            </ul>
                            
                  
                            
                            
                            <%--<sx:tabbedpanel id="employeeSearchPannel" cssStyle="width: 840px; height: 500px;padding: 5px 5px 5px 5px;" doLayout="true"> --%>
                            <div  style="border:1px solid gray; width:840px;height: 500px; overflow:auto; margin-bottom: 1em;">   
                                
                                        <div id="releasePayslipSpecailbox"></div>
                                <div id="releasePayslipOverlay" align="center">
                                    <s:form theme="simple" action="" name="releaseOverlay" id="">
                                        <table style="width: 100%;">

                                            <tr class="overlayHeader">
                                               
  <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="headerLabel"></span>


                                                            </h3>
  </td>
  <td colspan="2" style="background-color: #288AD1" align="right">
                                                    <a href="#" onmousedown="toggleReleasesOverlay()" >
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                            </a>  

                                                      
                                               </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                     <div id="releasePayslipResultMessage" style="font-size: 15px;"></div>
                                                </td>
                                                    
                                            </tr>
                                            <tr>
                                                <td>
                                                    <br><br>
                                                    <table style="width:70%;">
                                                        
                                                        <tr>
                                                            <td colspan="2">
                                                                <div id="loading" style="color: red;font-size: 15px;"></div>
                                                            </td>   
                                                            
                                                        </tr>
                                                        <tr>
                                                               <%-- <td class="fieldLabel">Release For :</td>
                                                                <td width="40%"><s:textfield name="releaseFor" value="%{releaseFor}" id="releaseFor" cssClass="inputTextBlue"/><a href="javascript:cal3.popup();" style="text-decoration: none;">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></a></td> --%>
                                                               
                                                                <td class="overlayFieldLabels">Year(YYYY):</td>
                                                                <td>
                                                                    <s:textfield name="year" id="year" maxlength="4" value="%{year}" />
                                                                </td>
                                                                <td class="overlayFieldLabels">Month:</td>
                                                                <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="month" id="month" style="width:70px" value="%{month}"  headerValue="select" headerKey="0"  cssClass="inputSelectSmall" contenteditable="false"/></td>
                                                             </tr>
                                                             <tr>
                                                                 
                                                            <td class="overlayFieldLabels">Status:</td>
                                                                <td><s:select list="#@java.util.LinkedHashMap@{'Release':'Release','Revoke':'Revoke'}" name="status" id="status"  headerKey="0" value="%{status}" style="width:70px" cssClass="inputSelectSmall" contenteditable="false"/></td>
                                                              
                                                                 <input type="hidden" name="Id" id="Id" value="Id">
                                                                 
                                                            <td colspan="2">
                                                                <input type="button" class="buttonBg" id="add" value="Release" onclick="return doAddPayslipReleases();"/>
                                                            </td>
                                                            <td colspan="2">
                                                                <input type="button" class="buttonBg" id="edit" value="EditRelease" onclick="return doEditPayslipReleases();"/>
                                                                </td>
                                                                                             </tr>
                                        
                                                      

                                                    </table>

                                                </td>
                                            </tr>

                                        </table>

                                    </s:form>
                                </div>
                        
                            
                                
                                
                                <!--//START TAB : -->
                                <%--  <sx:div id="employeeSearchTab" label="Employee Search" cssStyle="overflow:auto;"  > --%>
                                <div id="employeeSearchTab" class="tabcontent"  >
                                 
                                    <table cellpadding="0" cellspacing="0" border="0" width="100%">

                                        <tr align="right">
                                            <td class="headerText">
                                                <img alt="Home" 
                                                     src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                                     width="100%" 
                                                     height="13px" 
                                                     border="0">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <% /*if (request.getAttribute(ApplicationConstants.RESULT_MSG) != null) {
                                                    out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                    }*/

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
                                                <s:form name="frmEmpSearch" action="releasePayslipSearch" theme="simple">
                                                    <div id="loadingMessageForFreeze" style="color: red;font-size: 15px;display: none;">Loading please wait..</div>
                                                    <%
                                                        if (request.getAttribute("resultMessage") != null) {
                                                            out.println(request.getAttribute("resultMessage"));

                                                        }

                                                    %>
                                                    <table cellpadding="1" cellspacing="1" border="0" width="750px">
                                                       
                                                        <tr>
                                                            <td  class="fieldLabel">From :</td>
                                                                   
                                                                <td width="30%"><s:textfield name="fromDate" value="%{fromDate}" id="fromDate" onchange="isValidDate(this)" cssClass="inputTextBlue"/><a href="javascript:cal1.popup();" style="text-decoration: none;">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></a></td>
                                                                <td class="fieldLabel">To :</td>
                                                                <td><s:textfield name="toDate" id="toDate" value="%{toDate}" onchange="isValidDate(this)" cssClass="inputTextBlue"/><a href="javascript:cal2.popup();" style="text-decoration: none;">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></a></td>
                                                            
                                                       
                                                            <td colspan="2" align="center">
                                                                <%-- <s:reset name="reset" value="Reset" cssClass="buttonBg"/> --%>
                                                                <INPUT type="button" CLASS="btnPayrollWage" value="Back to List" onClick="history.go(-1)" tabindex="">
                                                                <input type="button" id="buttonOverlay" class="btnPayrollWage" value="Add" onclick="toggleReleasesOverlay('0');"/>
                                                                 
                                                               
                                                                <s:submit cssClass="btnPayrollWage" value="Search"/>

                                                                <input type="hidden" name="submitFrom" value="<%=searchSubmitValue%>">
                                                            </td>
                                                        </tr>

                                    
                                        </table>
                                    </s:form>  
                                                    <script>
                                        var cal1 = new CalendarTime(document.forms['frmEmpSearch'].elements['fromDate']);
                                        cal1.year_scroll = true;
                                        cal1.time_comp = false;
                                        var cal2 = new CalendarTime(document.forms['frmEmpSearch'].elements['toDate']);
                                        cal2.year_scroll = true;
                                        cal2.time_comp = false;
                                        
                                                 
                                    </script>
                                  
                                                      </td>
                                    </tr>
                                    <tr>
                                        <td>
                                                      <script type="text/JavaScript">
                                       
                                  
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
//.println("in sfdcad ");
                                                    /* Getting DataSource using Service Locator */
                                                    connection = ConnectionProvider.getInstance().getConnection();

                                                    /* Sql query for retrieving resultset from DataBase */
                                                    queryString = null;
                                                    queryString = request.getAttribute(ApplicationConstants.QUERY_STRING).toString();

                                              //       out.println(queryString);

                                                    //out.println("--------"+submittedFrom);
                                            %>
</script>
                                            <s:form action="" theme="simple" name="frmDBGrid">   

                                                <table cellpadding="0" cellspacing="0" width="100%" border="0">


                                                    <tr>
                                                        <td width="100%">

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
                                                                                 linkUrl="javascript:toggleReleasesOverlay({Id},'{ReleasedFor}','{STATUS}')" imageBorder="0"
                                                                                 imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn> 

                                                                <grd:datecolumn dataField="ReleasedFor" headerText="ReleasedFor" dataFormat="MM-dd-yyyy" sortable="true" width="18"/>
                                                                <grd:datecolumn dataField="ReleasedDate" headerText="ReleasedDate" dataFormat="MM-dd-yyyy" sortable="true" width="12"/>
                                                                <grd:textcolumn dataField="ReleasedBy" headerText="ReleasedBy" width="6"/>
                                                                 <grd:textcolumn dataField="STATUS" headerText="Status" width="6"/>
                                                                </grd:dbgrid>

                                                            <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                            <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                            <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">

                                                            <input type="hidden" name="submitFrom" value="dbGrid">
                                                       <input type="hidden" name="Id" value="Id">
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
 <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/x0popup.min.js"/>"></script>
    </table>
    <!--//END MAIN TABLE : Table for template Structure-->



</body>
</html>

