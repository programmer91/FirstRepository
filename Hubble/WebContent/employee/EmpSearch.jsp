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
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/EmpSearchClientValidation.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
  <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js?version=1.2"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script> 
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>

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
                <li ><a href="#" class="selected" rel="employeeSearchTab"  >Employee Search </a></li>
            </ul>
            <%--<sx:tabbedpanel id="employeeSearchPannel" cssStyle="width: 840px; height: 500px;padding: 5px 5px 5px 5px;" doLayout="true"> --%>
            <div  style="border:1px solid gray; width:840px;height: 500px; overflow:auto; margin-bottom: 1em;">   
                <!--//START TAB : -->
                <%--  <sx:div id="employeeSearchTab" label="Employee Search" cssStyle="overflow:auto;"  > --%>
                <div id="employeeSearchTab" class="tabcontent"  >
                    
                    <!-- Termination overlay start -->
                    <div id="overlay1"></div> 
                                    <div id="specialBox1" style="margin-left: 10vw;">
                                        <s:form theme="simple"  align="center" name="terminate">
                                            <s:hidden id="employeeId" name="employeeId"/>
                                            <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                <tr>                               
                                                    <td colspan="2" style="background-color: #288AD1" >
                                                        <h3 style="color:darkblue;" align="left">
                                                            <span id="headerLabel1"></span>


                                                        </h3></td>
                                                    <td colspan="2" style="background-color: #288AD1" align="right">

                                                        <a href="#" onmousedown="toggleCloseUploadOverlay2()" >
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                        </a>  

                                                    </td></tr>
                                                <tr>
                                                    <td colspan="4">
                                                        <div id="load" style="color: green;display: none;">Loading..</div>
                                                        <div id="resultMessage2"></div>
                                                    </td>
                                                </tr>    
                                                <tr><td colspan="4">
                                                        <table style="width:80%;" align="center">
                                                            <tr style="font-family: arial,verdana; font-size:12px;" >
                                                                <td class="fieldLabel" >Name&nbsp;:</td>
                                                                <td align="left" class="fieldLabelLeft">
                                                                    <span id="employeeName"></span>
                                                                </td> 
                                                            </tr>

                                                            <tr style="font-family: arial,verdana; font-size:12px;">

                                                                <td  class="fieldLabel" >Designation&nbsp;:</td>
                                                                <td><s:textfield name="designation" id="designation" cssClass="inputTextBlueLarge"  readonly="true"/></td>

                                                            </tr>

                                                            <tr style="font-family: arial,verdana; font-size:12px;">
                                                                <td class="fieldLabel">Date of Join&nbsp;:</td>
                                                                <td> <s:textfield name="dateOfJoin" id="dateOfJoin" cssClass="inputTextBlueLarge" readonly="true"/>
                                                                </td>
                                                            </tr>

                                                            <tr style="font-family: arial,verdana; font-size:12px;">
                                                                <td class="fieldLabel">Date of Termination&nbsp;:</td>
                                                                <td><s:textfield name="dateOfTermination" id="dateOfTermination" cssClass="inputTextBlueLarge"/>
                                                                    <a href="javascript:calTerminate.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0" style="margin-bottom: -4px"></a> 
                                                                </td>
                                                            </tr>
                                                            <tr style="font-family: arial,verdana; font-size:12px;">
                                                                <td class="fieldLabel" >Reasons for Exit&nbsp;:</td>  
                                                                <td ><s:textarea name="reasonsForTerminate" id="reasonsForExit" rows="4" cols="33" cssClass="inputTextArea" /></td>
                                                            </tr>
                                                            <tr>
                                                                <td align="right" colspan="5">
                                                                    <input type="button" label="Submit" value="Save" class="buttonBg" onclick="doSaveTermainationDetails();" style="margin-right: 30px;"/>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </s:form>   
                                        <script type="text/JavaScript">
                                                          
                                            var calTerminate = new CalendarTime(document.forms['terminate'].elements['dateOfTermination']);
                                            calTerminate.year_scroll = true;
                                            calTerminate.time_comp = false;
                                        </script>  
                                    </div>

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
                                <% if(request.getAttribute(ApplicationConstants.RESULT_MSG) != null){
                                    out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                }
                                
                                if(request.getAttribute("submitFrom") != null){
                                    submittedFrom = request.getAttribute("submitFrom").toString();
                                }
                                
                                if(!"dbGrid".equalsIgnoreCase(submittedFrom)){
                                    searchSubmitValue = submittedFrom;
                                }
                                
                                %>
                                
                            </td>
                        </tr>
                        <tr>
                            <td>
                                  <s:hidden name="roleName" value="%{#session.roleName}" id="roleName"/>

                            <s:form name="frmEmpSearch" action="getQuery" theme="simple">
                            <table cellpadding="1" cellspacing="1" border="0" width="750px">
                            <tr> 
                               
                                <td class="fieldLabel">Name&nbsp;:</td>
                                <td><s:textfield name="firstName" id="firstName" cssClass="inputTextBlue" value="%{firstName}" onchange="firstNameValidate(document.frmEmpSearch.firstName.value);"/> </td>
                                 <td class="fieldLabel">Employee&nbsp;No&nbsp;:</td>
                                                            <td ><s:textfield name="empno" id="empno" cssClass="inputTextBlue" value="%{empno}" onkeyup="isNumberKey(this);"/> </td>

                                <%--<td class="fieldLabel">Last Name :</td>                  
                                                                <td><s:textfield name="lastName" id="lastName" cssClass="inputTextBlue" value="%{lastName}"/></td> --%>
                                 </tr>
                            <tr>
                                <td  class="fieldLabel">CurrentStatus&nbsp;:</td>
                                <td><s:select list="currStatusList" id="currStatus" name="currStatus"  cssClass="inputSelect" value="%{currStatus}" /></td>
                                 <td class="fieldLabel">Skills&nbsp;:</td>
                                                            <td><s:textfield name="skillSet" id="skillSet" cssClass="inputTextBlue" value="%{skillSet}" onkeyup="skillCheck();"/><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/info.jpg" onmouseover="fixedtooltip('Search for only one skill<br><b>Ex: java</b><br><br>Search for both skills use \'&\' between skills <br><b>Ex: java&unix</b><br><br>Search for either one of the skill then use \'|\' between skills<br><b>Ex: java&unix|dotnet</b><br><br>Search for skills excluding some skills use \'-\'<br><b>Ex: java&unix|oracle-db2</b>',this,event, 200,-20,40)" onmouseout="delayhidetip()" height="18" width="20"></td> 

                               
                            </tr>
                            <tr>
                                <td class="fieldLabel">Organization&nbsp;:</td>
                                <td><s:select list="orgIdList" id="orgId" name="orgId" headerKey="All" headerValue="All" cssClass="inputSelectExtraLarge" value="%{orgId}"/></td>
                               <td  class="fieldLabel">Work-Phone&nbsp;No&nbsp;:</td>                  
                                <td><s:textfield name="workPhoneNo" id="workPhoneNo" cssClass="inputTextBlue" value="%{workPhoneNo}" onchange="workPhoneNoValidate(document.frmEmpSearch.workPhoneNo.value);" onblur="return validatenumber(this)"/></td> 
                           
                            </tr>
                            <tr>
                                <td  class="fieldLabel" width="200px" align="right">Department&nbsp;:</td>
                                <td><s:select label="Select Department" 
                                                  name="departmentId" 
                                                  id="departmentId"
                                                  headerKey=""
                                                  headerValue="--Please Select--"
                                              list="departmentIdList" cssClass="inputSelect" value="%{departmentId}" onchange="getPracticeDataV1();"/></td>
                                <td class="fieldLabel"  align="right">Practice Name&nbsp;:</td>
                                        <td><s:select label="Select Practice Name" 
                                                  name="practiceId"  id="practiceId"
                                                  headerKey=""
                                                  headerValue="-- Please Select --"
                                                  list="practiceIdList" cssClass="inputSelect" value="%{practiceId}" /> <%--onchange="getTeamData();" --%></td>  
                                
                                
                            </tr>
                            <tr>
                                  <td class="fieldLabel" >Operation&nbsp;Contact&nbsp;:</td>                           
                    
                    <td>
                      
               
                        <s:select label="Select Point of Contact" 
                                  name="opsContactId" 
                                  id="opsContactId"
                                  headerKey="1"
                                  headerValue="All"
                                  list="opsContactIdMap" 
                                  cssClass="inputSelect" 
                                  value="%{opsContactId}" />
                    </td>               <td class="fieldLabel">Reporting&nbsp;Person&nbsp;:</td>
                                <td colspan="2"><s:select list="reportingPersons" id="reportingpersonId" name="reportingpersonId" headerKey="All" headerValue="All" cssClass="inputSelectLarge" value="%{reportingpersonId}"/></td>
                                           
                                                            
                                                        </tr>
                                                       <%-- <s:if test="#session.roleName == 'Admin'">
                                                        <tr>
                                 <td class="fieldLabel" width="200px" align="right">Live in :</td>    
                            <td><s:select label="Select Country" 
                                      name="country" id="country" headerKey=""            
                                      headerValue="-- Please Select --"
                                  list="countryList" cssClass="inputSelect"/></td>  
                            </tr>  
                            </s:if> --%>    <tr>
                                        <td class="fieldLabel" align="left">Location&nbsp;:</td>
                                        <td>
                                            <s:select name="location" id="location" 
                                                      cssClass="inputSelect" headerKey="All" headerValue="All" 
                                                      list="locationsMap" value="%{location}"></s:select> 
                                            </td> 
                                            <td  class="fieldLabel">Itg&nbsp;Batch&nbsp;:</td>                  
                                            <td><s:textfield name="itgBatch" id="itgBatch" cssClass="inputTextBlue" value="%{itgBatch}" /></td> 
                                        </tr>

                                       <tr>


                                        <td class="fieldLabel">Is TeamLead&nbsp;:</td>
                                        <td colspan="1">
                                            <s:checkbox name="isTeamLead" id="isTeamLead"
                                                        value="%{isTeamLead}" 
                                                        theme="simple" /> 
                                        </td>  
                                        <td class="fieldLabel">Is Manager&nbsp;:</td>
                                        <td colspan="1">
                                            <s:checkbox name="isManager" id="isManager"
                                                        value="%{isManager}" 
                                                        theme="simple" /> 
                                        </td> </tr>

                                        <s:if test="#session.roleName == 'Admin'">
                                            <tr>
                                            <td class="fieldLabel" width="200px" align="right">Live in&nbsp;:</td>    
                                            <td><s:select label="Select Country" 
                                                      name="country" id="country" headerKey=""            
                                                      headerValue="-- Please Select --"
                                                      list="countryList" cssClass="inputSelect"/></td> 
                                            </tr>  
                                        </s:if>
										
                            
                            <tr>
                                <td colspan="3">
                                    <span class="messageNote">WildCard : * </span>
                                </td>
                                <td colspan="3">
                                <%-- <s:reset name="reset" value="Reset" cssClass="buttonBg"/> --%>
                                 <input type="button" class="buttonBg" value="Reset" onclick="clearTimeSheetSearch();"/>
                                    <s:submit cssClass="buttonBg" value="Search"/>
                                    <input type="hidden" name="submitFrom" value="<%=searchSubmitValue%>">
                                </td>
                            </tr>
                            
                       
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
                    
                    if (strTmp != null)
                        intCurr = Integer.parseInt(strTmp);
                    
                    /* Specifing Shorting Column */
                    strSortCol = request.getParameter("Colname");
                    
                    if (strSortCol == null) strSortCol = "Fname";
                    
                    strSortOrd = request.getParameter("txtSortAsc");
                    if (strSortOrd == null) strSortOrd = "ASC";
                    
                    
                    try{
                        
                        /* Getting DataSource using Service Locator */
                        connection = ConnectionProvider.getInstance().getConnection();
                        
                        /* Sql query for retrieving resultset from DataBase */
                        queryString  =null;
                        queryString = session.getAttribute(ApplicationConstants.QS_EMP_LIST).toString();
                        
                     //   out.println(queryString);
                        
                        //out.println("--------"+submittedFrom);
                    %>
                    
                    <s:form action="" theme="simple" name="frmDBGrid">   
                        
                        <table cellpadding="0" cellspacing="0" width="100%" border="0">
                            
                            
                            <tr>
                                <td width="100%">
                                    
                                    <grd:dbgrid id="tblStat" name="tblStat" width="98" pageSize="10"
                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2"
                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                        
                                        <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                       imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"/>
                                        
                                        <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                        imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                                        imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>   
                                        
                                      
                                        <grd:rownumcolumn headerText="SNo" width="4" HAlign="right"/>
                                        
                                        <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../includes/images/DBGrid/newEdit_17x18.png"
                                                         linkUrl="getEmployee.action?empId={Id}" imageBorder="0"
                                                         imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>
                                        
                                        <grd:textcolumn dataField="Fname" headerText="FirstName" width="18"/>
                                        <grd:textcolumn dataField="Lname" headerText="LastName" width="12"/>
                                        <grd:datecolumn dataField="EmpNo" headerText="EmployeeNo" width="12"/>
                                        <grd:textcolumn dataField="WorkPhoneNo" headerText="WorkPhone" width="13"/>
                                        <grd:textcolumn dataField="CellPhoneNo" headerText="CellPhone" width="13"/>
                                        <grd:textcolumn dataField="Email1" headerText="EmailId" width="20"/>
                                        <grd:textcolumn dataField="CurStatus" headerText="Status" width="12"/>
                                       <%-- <grd:imagecolumn headerText="Termination" width="4" HAlign="center" 
                                                         imageSrc="../includes/images/DBGrid/terminate.png"
                                                         linkUrl="deleteEmployee.action?empId={Id}" imageBorder="0"
                                                         imageWidth="69" imageHeight="20" alterText="Delete"></grd:imagecolumn> --%>
                                        
                                        <grd:imagecolumn headerText="Termination" width="4" HAlign="center" 
                                                                                 imageSrc="../includes/images/DBGrid/terminate.png"
                                                                                 linkUrl="javascript:checkTerminationDetails({Id},'{LoginId}','{CurStatus}')" imageBorder="0"
                                                                                 imageWidth="69" imageHeight="20" alterText="Delete"></grd:imagecolumn>
                                    </grd:dbgrid>
                                    
                                    <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                    <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                    <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                    
                                    <input type="hidden" name="submitFrom" value="dbGrid">
                                    <s:hidden  name="firstName" value="%{firstName}"/>
                                    <s:hidden  name="currStatus" value="%{currStatus}"/>
                                    <s:hidden  name="orgId" value="%{orgId}"/>
                                    <s:hidden  name="reportingpersonId" value="%{reportingpersonId}"/>
                                    <s:hidden  name="departmentId" value="%{departmentId}"/>
                                    <s:hidden  name="isTeamLead" value="%{isTeamLead}"/>
                                    <s:hidden  name="isManager" value="%{isManager}"/>
                                    <s:hidden  name="empno" value="%{empno}"/>
                                    <s:hidden  name="opsContactId" value="%{opsContactId}"/>
                                    <s:hidden  name="skillSet" value="%{skillSet}"/>
                                    <s:hidden  name="workPhoneNo" value="%{workPhoneNo}"/>
                                    <s:hidden  name="country" value="%{country}"/>
                                    <s:hidden  name="practiceId" value="%{practiceId}"/>
                                    <s:hidden  name="location" value="%{location}"/>
                                        <s:hidden  name="itgBatch" value="%{itgBatch}"/>
                                    
                                </td>
                            </tr>
                        </table>                                
                        
                    </s:form>
                    
                    <%
                    connection.close();
                    connection = null;
                    }catch(Exception ex){
                        out.println(ex.toString());
                    }finally{
                        if(connection!= null){
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

</table>
<!--//END MAIN TABLE : Table for template Structure-->
        
        

</body>
</html>
