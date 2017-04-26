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
    <sx:head cache="true"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/new/x0popup.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/EmpSearchClientValidation.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery.min.js"/>"></script>
       <s:include value="PayrollCalculations.jsp"/>
    <script>
       
    </script>

 <script  type="text/javascript" >
    function performRoleAction() {
        
        var roleTypeId=document.getElementById("roleTypeIdd").value;
        //roleTypeId=2;
        //alert(roleTypeId);
        url = CONTENXT_PATH+"/general/roleSubmit.action?roleTypeId="+roleTypeId;
        //alert(url);
        document.location = url;
    }
</script>

    <s:include value="/includes/template/headerScript.html"/>
</head>
<%-- <body class="bodyGeneral" oncontextmenu="return false;" onload="initForTefSearch();"> --%>
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
                                <li ><a href="#" class="selected" rel="employeeSearchTab"  >Employee List </a></li>
                            </ul>
                            <%--<sx:tabbedpanel id="employeeSearchPannel" cssStyle="width: 840px; height: 500px;padding: 5px 5px 5px 5px;" doLayout="true"> --%>
                            <div  style="border:1px solid gray; width:840px;height: 500px; overflow:auto; margin-bottom: 1em;">   
                                <!--//START TAB : -->
                                <%--  <sx:div id="employeeSearchTab" label="Employee Search" cssStyle="overflow:auto;"  > --%>
                                <div id="employeeSearchTab" class="tabcontent"  >
                                    
                                     <s:if test='#session.payrollAuthCheck == "0"'>
                                     <%--<s:if test="%{payrollAuthCheck==0}"> --%>
                                    <div id="payRollAuthOverlay"></div>
                                    <div id="payRollAuthOverlaybox" align="center">
                                        <s:form theme="simple" action="payrollAuthenticationLogin.action" name="AuthenticationForm" id="AuthenticationForm">
                                            <table style="width: 100%;">

                                                <tr class="overlayHeader">
                                                    <td colspan="2">
                                                        
 
                                                        <font style="vertical-align: middle;font-family:myHeaderFonts;color: white; font-size:20px ;text-align: left;">
                                                        Payroll Authentication </font> 
                                                        <!--                                                    <div  class="closeButton" align="right">
                                                                                                                <a href="#" onclick="toogleTef();" style="color:#869fa3;font-family: myHeaderFonts;text-decoration: none; ">CLOSE</a>
                                                                                                            </div> -->
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <div id="payRollAuthenticationMsg" style="font-size: 15px;"></div>
                                                        <div id="loadingMessageAuth" style="display: none;color: red;font-size: 15px;">Loading..</div>
                                                        <s:hidden id="roleTypeIdd"  name="roleTypeId" value="%{roleTypeId}"  />
                                                        <%
                                                                                                                if (session.getAttribute("payrollAuthcheckresultMessage") != null) {
                                                                                                                    out.println(session.getAttribute("payrollAuthcheckresultMessage"));
                                                                                                                    session.removeAttribute("payrollAuthcheckresultMessage");
                                                                                                                }

                                                                                                            %>
                                                    </td>

                                                </tr>
                                                <tr>
                                                    <td>

                                                        <table style="width:84%;">
                                                          <%--  <tr>     <td class="fieldLabel" align="right">payrollAuthRegister&nbsp;:</td> <td> <s:textfield name="payrollAuthRegister" id="payrollAuthRegister" value="%{payrollAuthRegister}" maxlength="4" cssClass=""/></td>  

                                                            </tr>
                                                            <tr>     <td class="fieldLabel" align="right">payrollAuthCheck&nbsp;:</td> <td> <s:textfield name="payrollAuthCheck" id="payrollAuthCheck" value="%{payrollAuthCheck}" maxlength="4" cssClass=""/></td>  

                                                            </tr>--%>
                                                          <tr id="authPasswordTr">     <td class="fieldLabel" align="right">Payroll&nbsp;Password&nbsp;:</td> <td> <s:password name="payrollPassword" id="payrollPassword"  cssClass="" onblur="passwordValidation();" /></td> 
                                                              <s:if test="%{payrollAuthRegister!=0}">
                                                              <td> <img title="Forget Password" 
                                                     src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/forgotPasword.png" onclick="forgetPayRollPassword();"
                                                     width="150%" 
                                                     height="25px" 
                                                     border="0"></td>
                                                              </s:if>
                                                            </tr>
                                                            <s:if test="%{payrollAuthRegister==0}">
                                                                <tr>     <td class="fieldLabel" align="right">Conform&nbsp;Password&nbsp;:</td> <td> <s:password name="payrollConformPassword" id="payrollConformPassword"  cssClass=""/></td>  

                                                                </tr>
                                                            </s:if>          
                                                                <tr id="EmailTr" style="display: none;">
                                                                     <td class="fieldLabel" align="right">Enter&nbsp;Email&nbsp;Id&nbsp;:</td> <td> <s:textfield name="authEmailId" id="authEmailId"  cssClass=""/></td>  

                                                                
                                                                </tr>

                                                            <tr id="buttonsTr">
                                                                <td colspan="2" align="right">
                                                                    <div id="tefloadingMessage" style="color: red;font-size: 15px;display: none;">Loading please wait..</div>
                                                                    <input type="button" class="button_payroll" value="Cancel" onclick="performRoleAction()"/>
                                                                    <s:if test="%{payrollAuthRegister==0}">
                                                                        <input type="button" class="button_payroll" value="Register" onclick="payrollAuthenticationRegister();"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:submit cssClass="button_payroll" value="Login"/>
                                                                          
                                                                    </s:else>
                                                                </td>
                                                            </tr>
                                                            <tr id="buttonsTrForget" style="display:none;">
                                                                <td colspan="2" align="right">
                                                                    <div id="tefloadingMessage" style="color: red;font-size: 15px;display: none;">Loading please wait..</div>
                                                                    <input type="button" class="button_payroll" value="Cancel" onclick="performRoleAction()"/>
                                                                   
                                                                        <input type="button" class="button_payroll" value="Get Password" onclick="generatePayRollPassword();"/>
                                                                   
                                                                    
                                                                </td>
                                                            </tr>

                                                        </table>

                                                    </td>
                                                </tr>

                                            </table>

                                        </s:form>
                                    </div>
                                     </s:if>
                                    
                                    
                                    
                                    <div id="payrollOverlay"></div>
                                    <div id="hubblePayrollOverlay" align="center">
                                        <s:form theme="simple" >
                                            <table style="width: 100%;">

                                                <tr class="overlayHeader">
                                                    <td colspan="2">

                                                        <font style="vertical-align: middle;font-family:myHeaderFonts;color: white; font-size:20px ;text-align: left;">
                                                        Payroll Add</font> 
                                                       
                                                            <a href="#" onclick="toggleOverlayForProfile();" style="color:#869fa3;font-family: myHeaderFonts;text-decoration: none; "><img  align="right" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> </a>
                                                       
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                       <br>                                 <table style="width:70%;">
                                                            <tr>
                                                            <td colspan="4">
                                                                    <div id="loadingMessage" style="color: red;font-size: 15px;display: none;">Loading please wait..</div>
                                                                    <div id="loadingMessageForFreeze" style="color: red;font-size: 15px;display: none;"></div>
                                                                    <div id="resultMessage"></div>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                               <td class="overlayFieldLabels">Employee&nbsp;Id:</td>

                                                                <td colspan="">
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
                                                <% if (request.getAttribute(ApplicationConstants.RESULT_MSG) != null) {
                                                        out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                    }

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
                                                <s:form name="frmEmpSearch" action="searchPayroll" theme="simple">
                                                    <table cellpadding="1" cellspacing="1" border="0" width="750px">
                                                        <tr> 

                                                             <td class="fieldLabel">Employee No.</td>
                                                            <td ><s:textfield name="empNo" id="empNo" cssClass="inputTextBlue" value="%{empNo}" onchange="firstNameValidate(document.frmEmpSearch.firstName.value);"/></td>
                                                         
                                                            <td class="fieldLabel">EmpName :</td>
                                                            <td><s:textfield name="employeeName" id="employeeName1" value="%{employeeName}" autocomplete="off" cssClass="inputTextBlue" onkeyup="getEmplyeeNamesForTefSearch();"/><span id="empNoSpan"></span><label id="rejectedId"></label><div class="fieldLabelLeft" id="validationMessage1"></div>

                                                                                                <div style="display: none; position: absolute; overflow:auto; z-index: 500;" id="menu-popup1">
                                                                                                    <table id="completeTable1" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                                                                </div>
                                                                                                <s:hidden id="userId" name="userId"/></td> 


                                                        </tr>
                                                        <tr>
                                                            <td  class="fieldLabel">CurrentStatus:</td>
                                                            <td><s:select list="currStatusList" id="currStatus" name="currStatus" headerKey="" headerValue="All" cssClass="inputSelect" value="%{currStatus}" /></td>
 <td  class="fieldLabel">Organization:</td>
                                                            <td><s:select list="orgIdMap" id="orgId" name="orgId" headerKey="" headerValue="All" cssClass="inputSelect" value="%{orgId}" /></td>
 
                                                          </tr>
                                                           <tr>
                                                              <td class="fieldLabel"  align="right">Department&nbsp;:</td>
                                                        <td><s:select 
                                                                name="departmentId" 
                                                                id="departmentId"
                                                                headerKey=""
                                                                headerValue="All"
                                                                list="departmentIdList" cssClass="inputSelect" value="%{departmentId}" onchange="getEmpTitleData();getPracticeDataV1();" tabindex="3"/></td>
                    <td class="fieldLabel"  align="right">Type&nbsp;Select&nbsp;:</td>
                                                        <td><s:select 
                                                                name="selectType" 
                                                                id="selectType"
                                                                headerKey=""
                                                                headerValue="PleaseSelect"
                                                                list="#@java.util.LinkedHashMap@{'PF':'PF','OnProject':'OnProject','ESI':'ESI','NoSalary':'NoSalary'}" cssClass="inputSelect" value="%{selectType}"  tabindex="3"/></td>
    
                                                          </tr>


                                                        <%--  <tr>
                                                          <td class="fieldLabel">Year(YYYY):</td>
                                                          <td>
                                                            
                                                              <s:textfield name="year" id="year" maxlength="4" cssClass="inputTextBlue" value="%{year}" onblur="yearValidation(this.value,event)" onkeypress="yearValidation(this.value,event)"/>
                                                          </td>
                                                          <td class="fieldLabel">Month:</td>
                                                          <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="month" id="month" onchange="" headerValue="select" headerKey="0" value="%{month}"/></td>
                                                          
                                                      </tr> 
                                                        
                                                         <tr>
                                                            
                                                            <td class="fieldLabel"   >PF&nbsp;Flag&nbsp;:</td>
                                                            <td > <s:checkbox name="isPfFlag" id="isPfFlag"
                                                                        value="%{isPfFlag}" 
                                                                        theme="simple" /> </td>
                                                          
   <td class="fieldLabel" >OnProjectInd.:</td>    

                                                            <td><s:checkbox name="onProjectInd" id="onProjectInd"
                                                                        value="%{onProjectInd}" 
                                                                        theme="simple" onchange="calVariablepay();" />
                                                            </td>
                                                        </tr>


                                                        <tr>

                                                           
                                                            
                                                            <td class="fieldLabel" >OnSitetInd.:</td>    

                                                            <td><s:checkbox name="onSiteIndex" id="onSiteIndex"
                                                                        value="%{onSiteIndex}"
                                                                        theme="simple" onchange="calVariablepay();" />
                                                            </td> --%>
                                                           
                                                            
                                                       <tr>
                                                            <td colspan="4" align="right">
                                                                <%-- <s:reset name="reset" value="Reset" cssClass="buttonBg"/> --%>
                                                                <input type="reset" class="btnPayroll blue" value="Reset" onclick="clearPayrollSearch();"/>
                                                                <s:submit cssClass="btnPayroll blue" value="Search"/>
                                                                <input type="button" class="btnPayroll blue" value="Add" onclick="toggleOverlayForProfile();"/>
                                                                <input type="hidden" name="submitFrom" value="<%=searchSubmitValue%>">
                                                            </td>
                                                        </tr>


                                            </tr>
                                        </table>
                                    </s:form>
                                    <script>
                                        var cal1 = new CalendarTime(document.forms['frmEmpSearch'].elements['dateOfJoining']);
                                        cal1.year_scroll = true;
                                        cal1.time_comp = false;
                                        var cal2 = new CalendarTime(document.forms['frmEmpSearch'].elements['dateOfRevision']);
                                        cal2.year_scroll = true;
                                        cal2.time_comp = false;
                                                 
                                    </script>
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
//.println("in sfdcad ");
                                                    /* Getting DataSource using Service Locator */
                                                    connection = ConnectionProvider.getInstance().getConnection();

                                                    /* Sql query for retrieving resultset from DataBase */
                                                    queryString = null;
                                                    queryString = session.getAttribute(ApplicationConstants.QS_EMP_LIST).toString();

                                                   // out.println(queryString);

                                                    //out.println("--------"+submittedFrom);
                                            %>

                                            <s:form action="" theme="simple" name="frmDBGrid">   

                                                <table cellpadding="0" cellspacing="0" width="100%" border="0">


                                                    <tr>
                                                        <td width="100%">

                                                            <grd:dbgrid id="tblStat" name="tblStat" width="98" pageSize="10"
                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2"
                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                                <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                               imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                               addImage="../../includes/images/DBGrid/Add.png"  addAction="javascript:toggleOverlayForProfile()"
                                                                               />

                                                                <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                                                imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>   

                                                                <grd:rownumcolumn headerText="SNo" width="4" HAlign="right"/>

                                                               <%-- <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"
                                                                                 linkUrl="editEmployeePayroll.action?payRollId={PayRollId}&empId={Id}&tdsId={TdsId}" imageBorder="0"
                                                                                 imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>  --%>
                                                                <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"
                                                                                 linkUrl="editEmployeePayroll.action?payRollId={PayRollId}&empId={Id}" imageBorder="0"
                                                                                 imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>
                                                                <grd:datecolumn dataField="PayRollId" headerText="EmpNo" HAlign="center" width="4" />
                                                                <grd:textcolumn dataField="EmployeeName" headerText="EmployeeName" width="18"/>
                                                                <grd:textcolumn dataField="WorkPhoneNo" headerText="WorkPhoneNo" width="13"/>
                                                                <grd:textcolumn dataField="Email1" headerText="EmailId" width="20"/>
                                                                <grd:textcolumn dataField="DepartmentId" headerText="Department" width="12"/>

                                                            </grd:dbgrid>

                                                            <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                            <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                            <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">

                                                            <input type="hidden" name="submitFrom" value="dbGrid">
                                                            <s:hidden  name="employeeName" value="%{employeeName}"/>
                                                            <s:hidden  name="currStatus" value="%{currStatus}"/>
                                                            <s:hidden  name="departmentId" value="%{departmentId}"/>
                                                            <s:hidden  name="empNo" value="%{empNo}"/> 
                                                              <s:hidden  name="isPfFlag" value="%{isPfFlag}"/> 
                                                                <s:hidden  name="onSiteIndex" value="%{onSiteIndex}"/> 
                                                                  <s:hidden  name="onProjectInd" value="%{onProjectInd}"/> 
                                                                  <s:hidden  name="orgId" value="%{orgId}"/>
                                                                  <s:hidden  name="employeeName" value="%{employeeName}"/>
                                                                  <s:hidden  name="userId" value="%{userId}"/>
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
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
<script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
<script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/nameSuggestion.js"/>"></script>
  <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/x0popup.min.js"/>"></script>
    </table>
    <!--//END MAIN TABLE : Table for template Structure-->



<script type="text/javascript">
		$(window).load(function(){
      initForTefSearch();
		});
		</script>
</body>
</html>

