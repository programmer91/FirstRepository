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
        <title>Hubble Organization Portal :: Quarterly  Review Search</title>
    <sx:head cache="true"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/EmpSearchClientValidation.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js?version=1.0"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script> 
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
  <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/new/x0popup.min.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/x0popup.min.js"/>"></script>
    <script>
        function clearTimeSheetSearch()
        {
            document.getElementById('firstName').value="";
            document.getElementById("workPhoneNo").value="";
            document.getElementById("currStatus").value="Active";
            // document.getElementById("departmentId").value="All";
            document.getElementById("orgId").value="All";
            document.getElementById("reportingpersonId").value="All";
            document.getElementById("isManager").checked=false;
            document.getElementById("isTeamLead").checked=false;
            document.getElementById('empno').value="";
            document.getElementById("skillSet").value="";
            document.getElementById("opsContactId").value="1";
            document.getElementById("departmentId").value="";
            getPracticeDataV1();
            if(document.getElementById("roleName").value=='Admin')
                document.getElementById("country").value="";
                
        }
       
       
        function skillCheck(){
            var skill=document.getElementById("skillSet").value;
            if(skill.indexOf('-') > 0)
            {
                var ch=skill.substring(skill.length-1,skill.length);
                var first=skill.substring(skill.length-2,skill.length-1);
                var second=skill.substring(skill.length-1,skill.length);
                if(first==second && second=="-"){
                    skill=skill.substring(0,skill.length-1);
                    document.getElementById("skillSet").value=skill; 
                }
                if(ch=="&" || ch=="|"){
                    skill=skill.substring(0,skill.length-1);
                    document.getElementById("skillSet").value=skill;  
                }
            }
        }

    </script>



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
                    <li ><a href="#" class="selected" rel="employeeSearchTab"  >Quarterly  Review Search</a></li>
                </ul>
                <%--<sx:tabbedpanel id="employeeSearchPannel" cssStyle="width: 840px; height: 500px;padding: 5px 5px 5px 5px;" doLayout="true"> --%>
                <div  style="border:1px solid gray; width:840px;height: 500px; overflow:auto; margin-bottom: 1em;">   
                    <!--//START TAB : -->
                    <%--  <sx:div id="employeeSearchTab" label="Employee Search" cssStyle="overflow:auto;"  > --%>
                    <div id="employeeSearchTab" class="tabcontent"  >

                        <!-- Termination overlay start -->


                        <!-- Termination overlay end -->
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

                              if(session.getAttribute(ApplicationConstants.RESULT_MSG) != null){
                session.removeAttribute(ApplicationConstants.RESULT_MSG);
            }     

                                %>

                            </td>
                            </tr>
                            <tr>
                            <td>
                                <s:hidden name="roleName" value="%{#session.roleName}" id="roleName"/>

                                <s:form name="frmEmpSearch" action="teamQuaterAppraisalSearch" theme="simple">
                                    <table border="0" cellpadding="1" cellspacing="1" align="center" width="80%">
                                       
                                        <tr>

                                        <td class="fieldLabel"> Year:</td>
                                        <td><s:textfield name="year" id="year" value="%{year}" cssClass="inputTextBlue" onkeyup="isNumericYear(this);" maxLength="4"/></td>
                                        <td class="fieldLabel">Quarter:</td>
                                        <td >

                                     <s:select headerKey="" headerValue="All" id="quarterly"  name="quarterly" list="#@java.util.LinkedHashMap@{'Q1':'Q1','Q2':'Q2','Q3':'Q3','Q4':'Q4'}" cssClass="inputSelect"  disabled="False"/>
                                        </td>
                                  
                                       <td class="fieldLabel">Status:</td>
                                        <td >

                                     <s:select headerKey="" headerValue="All" id="status"  name="status" list="#@java.util.LinkedHashMap@{'Entered':'Entered','Submitted':'Submitted','Approved':'Approved','Rejected':'Rejected'}" cssClass="inputSelect"  disabled="False"/>
                                        </td>
                                        <s:if test="%{#session.roleName == 'Operations' || #session.userId =='rkalaga'}">
                                        <tr>
                                        <td class="fieldLabel">EmpName:</td>
                                        <td>

                                            <s:select name="loginId" id="loginId" headerKey="" headerValue="All"  list="myTeamMembers" cssClass="inputSelect"  /></td>


                                </td>
                                </tr>
                                <tr>
                                <td  class="fieldLabel" width="200px" align="right">Department&nbsp;:</td>
                                <td><s:select label="Select Department" 
                                          name="departmentId" 
                                          id="departmentId"
                                          headerKey=""
                                          headerValue="All"
                                          list="{'GDC','SSG'}" cssClass="inputSelect" value="%{departmentId}" onchange="getPracticeDataV1();"/></td>
                                <td class="fieldLabel"  align="right">Practice&nbsp;Name&nbsp;:</td>
                                <td><s:select label="Select Practice Name" 
                                          name="practiceId"  id="practiceId"
 headerKey=""
                                              headerValue="All"
                                          list="{}" cssClass="inputSelect" value="%{practiceId}" onchange="getSubPracticeData();"/> <%--onchange="getTeamData();" --%></td> 
                                <td class="fieldLabel" width="200px" align="right">SubPractice&nbsp;Name&nbsp;:</td>

                                <td nowrap>


                                    <s:select name="subPractice"  id="subPractice"
 headerKey=""
                                              headerValue="All"
                                              list="{}" cssClass="inputSelectNew" value="%{currentEmployee.subPractice}" theme="simple" />
                                    <!--<div style="display:none;" id="territory_div" align="justify" theme="simple">-->

                                    <a HREF="javaScript:territoryPopup('TerritoryInfo.jsp');" id="territory_div" style="display:none;">
                                        <img SRC="../includes/images/help.gif" WIDTH=16 HEIGHT=16 BORDER=0 ALTER="Territory">
                                    </a>
                                    <!-- </div>-->
                                </td>
                                </tr>
                                <tr><td class="fieldLabel" >Operation&nbsp;Contact&nbsp;:</td>                           

                                <td>


                                    <s:select label="Select Point of Contact" 
                                              name="opsContactId" 
                                              id="opsContactId"
                                              headerKey=""
                                              headerValue="All"
                                              list="opsContactIdMap" 
                                              cssClass="inputSelect" 
                                              value="%{opsContactId}" />
                                </td>
                                <td class="fieldLabel" align="left">Location&nbsp;:</td>
                                <td>
                                    <s:select name="location" id="location" 
                                              cssClass="inputSelect" headerKey="" headerValue="All" 
                                              list="locationsMap" value="%{location}"></s:select> 
                                    </td> 
                                    <td class="fieldLabel">Resource&nbsp;Type:</td>
                                    <td >

                                    <s:select headerKey="" headerValue="All" id="titleId"  name="titleId" list="{'Employee','Management'}" cssClass="inputSelect"  disabled="False"/>
                                </td>
                                
                                </tr>
                                <tr>
                                <td colspan="6" align="right">


                                    <s:submit name="search" value="Search" cssClass="buttonBg"/>

                                </td>
                                </tr>
                                    </s:if>
                                    <s:else>
                                   <tr>
                                        <td class="fieldLabel">EmpName:</td>
                                        <td>

                                            <s:select name="loginId" id="loginId" headerKey="" headerValue="All"  list="myTeamMembers" cssClass="inputSelect"  /></td>


                                </td>
                                <td colspan="4" align="right" style="padding-right: 27px;">


                                    <s:submit name="search" value="Search" cssClass="buttonBg"/>

                                </td>
                                </tr>     
                                    </s:else>
                            </table>
                        </s:form>
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

                                /* Sql query for retrieving resultset from DataBase */
                                queryString = session.getAttribute(ApplicationConstants.QS_EMP_APPRAISAL_LIST).toString();
                             //   queryString = session.getAttribute(ApplicationConstants.QS_EMP_LIST).toString();
queryString=queryString+"  ORDER BY Quarterly";
                                  // out.println(queryString);

                                //out.println("--------"+submittedFrom);
%>

                        <s:form action="" theme="simple" name="frmDBGrid">   

                            <table cellpadding="0" cellspacing="0" width="100%" border="0">


                                <tr>
                                <td width="100%">

                                    <grd:dbgrid id="tblStat" name="tblStat" width="98" pageSize="15"
                                        currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2"
                                        dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                            <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                           imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"/>

                                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                            imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                            imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>   


                                            <grd:rownumcolumn headerText="SNo" width="4" HAlign="right"/>

                                            <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../../includes/images/DBGrid/newEdit_17x18.png"
                                                             linkUrl="myQuarterlyAppraisalEdit.action?empId={EmpId}&lineId={Id}&appraisalId={appraisalId}&status={status}&curretRole=team&quarterly={quarterly}" imageBorder="0"
                                                             imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>

                                            <grd:textcolumn dataField="EmpName" headerText="EmpName" width="20"/>
                                            <%-- <grd:datecolumn dataField="mnth" 
                                                                         headerText="Month"  HAlign="left"   sortable="true"
                                                                         width="5"/> --%>
                                            <grd:datecolumn dataField="CreatedDate" 
                                                            headerText="Year"  HAlign="RIGHT" dataFormat="yyyy"  
                                                            width="5"/>
                                            <grd:datecolumn dataField="Quarterly" 
                                                            headerText="Quarter"  HAlign="center"   
                                                            width="5"/>
                                            <grd:datecolumn dataField="SubmittedDate" 
                                                            headerText="SubmittedDate"  HAlign="right" dataFormat="yyyy-MM-dd"  
                                                            width="3"/>
                                                <grd:textcolumn dataField="ApprovedBy" headerText="Approved By" width="15"/>
                                            <grd:datecolumn dataField="ApprovedDate" 
                                                            headerText="ApprovedDate"  HAlign="right" dataFormat="yyyy-MM-dd"  
                                                            width="5"/>

                                            <grd:textcolumn dataField="STATUS" headerText="Status" width="5"/>
                                            <grd:textcolumn dataField="OpperationTeamStatus" headerText="OperationStatus" width="5"/>
                                            <%--
                                                                                    <grd:imagecolumn headerText="Termination" width="4" HAlign="center" 
                                                                                                     imageSrc="../includes/images/DBGrid/terminate.png"
                                                                                                     linkUrl="javascript:checkTerminationDetails({Id},'{LoginId}','{CurStatus}')" imageBorder="0"
                                                                                                     imageWidth="69" imageHeight="20" alterText="Delete"></grd:imagecolumn> --%>
                                        </grd:dbgrid>

                                        <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                        <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                        <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">

                                        <input type="hidden" name="submitFrom" value="dbGrid">
                                        <s:hidden  name="year" value="%{year}"/>
                                        <s:hidden  name="quarterly" value="%{quarterly}"/>
                                        <s:hidden  name="loginId" value="%{loginId}"/>
                                        <s:hidden  name="status" value="%{status}"/>
                                        <s:hidden  name="departmentId" value="%{departmentId}"/>
                                        <s:hidden  name="practiceId" value="%{practiceId}" id="dbGridpracticeId"/>
                                        <s:hidden  name="subPractice" value="%{subPractice}" id="dbGridsubPractice"/>
                                        <s:hidden  name="opsContactId" value="%{opsContactId}"/>
                                        <s:hidden  name="location" value="%{location}"/>
                                        <s:hidden  name="titleId" value="%{titleId}"/>

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
        <script>
                    $(document).ready(function(){
        
                        getPracticeDataV1();
                        getSubPracticeData();
                        setTimeout(

                        function()

                        {
                            var dbGridpracticeId=document.getElementById("dbGridpracticeId").value;
  
    
                            document.getElementById("practiceId").value=dbGridpracticeId;
                            getSubPracticeData();
                            setTimeout(

                            function()

                            {
   
                                var dbGridsubPractice=document.getElementById("dbGridsubPractice").value;
    
                               if(dbGridsubPractice==""){
                                   dbGridsubPractice="All";
                               }
                              
                                document.getElementById("subPractice").value=dbGridsubPractice;
                            }, 1000);
   
                        }, 1000);
                     
                    });

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
