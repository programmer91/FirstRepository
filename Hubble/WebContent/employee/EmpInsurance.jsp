<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<html>
    <head>
        <title>Hubble Organization Portal :: Template</title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>  
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/InsuranceClientValidation.js"/>"></script>  --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    </head>
    
    <body class="bodyGeneral" oncontextmenu="return false;">
        
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">
            
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
                    <table class="innerTable1000x515"  cellpadding="0" cellspacing="0">
                        <tr>
                            
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" valign="top">
                                <s:include value="/includes/template/LeftMenu.jsp"/>
                            </td>
                            
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top">
                                <table cellpadding="0" cellspacing="0" width="100%" border="0">
                                    <tr>
                                        <td>
                                            <span class="fieldLabel">Employee Name :</span>&nbsp;
                                            <s:if test="currentEmpInsurance == null ">
                                                <a class="navigationText" href="<s:url action="getEmployee"><s:param name="empId" value="%{empId}"/></s:url>"><s:property value="%{employeeName}"/></a>
                                            </s:if>
                                            <s:else>
                                                <a class="navigationText" href="<s:url action="getEmployee"><s:param name="empId" value="%{currentEmpInsurance.empId}"/></s:url>"><s:property value="%{employeeName}"/></a>
                                            </s:else>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top" style="padding: 5px 5px 5px 5px;"> 
                                            <ul id="accountTabs" class="shadetabs" >
                                                <li ><a href="#" class="selected" rel="personalDetailsTab"  > Insurance Details </a></li>
                                            </ul>
                                            
                                          <%--  <sx:tabbedpanel id="insurancePannel" cssStyle="width: 845px; height:530px;padding: 5px 5px 5px 5px;" doLayout="true"> --%>
                                             <div  style="border:1px solid gray; width:845px;height: 530px; overflow:auto; margin-bottom: 1em;">
                                                
                                                <!--//START TAB : -->
                                                <%--<sx:div id="personalDetailsTab" label="Insurance Details" > --%>
                                                <div id="personalDetailsTab" class="tabcontent"  >
                                                    <!--onsubmit="return insuranceValidate();" -->
                                                    <s:form name="EmployeeInsurence" action="%{formAction}"  theme="simple">
                                                        
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                            
                                                            <tr class="headerText">
                                                                <td >LicDetails</td>
                                                                <td align="right" colspan="5">
                                                                    <s:property value="#request.resultMessage"/>
                                                                    
                                                                    <s:if test="currentEmpInsurance == null ">
                                                                        <s:hidden name="empId"  value="%{empId}"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:hidden name="empId"  value="%{currentEmpInsurance.empId}"/>
                                                                    </s:else>
                                                                    <s:submit name="submit" value="Save" cssClass="buttonBg"/>                                                        
                                                                </td>
                                                            </tr> 
                                                            
                                                            <!--start of Lic insurance Details-->
                                                
                                                            <tr>
                                                                <td class="fieldLabel">LicPolicyNumber:</td>
                                                                <td><s:textfield name="licPolicyNumber" cssClass="inputTextBlue" value="%{currentEmpInsurance.licPolicyNumber}" onchange="fieldLengthValidator(this);"  id="licPolicyNumber" size="15"/> </td>
                                                                
                                                                <td class="fieldLabel">LicPolicyDate:</td>
                                                                <td><s:textfield name="licPolicyDate" cssClass="inputTextBlueSmall" value="%{currentEmpInsurance.licPolicyDate}"/><a href="javascript:cal1.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></td>
                                                                
                                                                <td class="fieldLabel">LicPolicyValues:</td>
                                                                <td><s:textfield name="licPolicyValues" cssClass="inputTextBlue" value="%{currentEmpInsurance.licPolicyValues}" onchange="fieldLengthValidator(this);"  id="licPolicyValues" size="10"/></td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">LicPolicyComNumber:</td>
                                                                <td colspan="5"><s:textfield name="licPolicyComNumber" cssClass="inputTextBlue" value="%{currentEmpInsurance.licPolicyComNumber}" onchange="fieldLengthValidator(this);" id="licPolicyComNumber" size="15"/></td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td colspan="6" class="headerText" align="left">
                                                                    HealthInsuranceDetails:
                                                                </td>
                                                            </tr> 
                                                            <!--start payroll code-->
          
                                                            <tr>
                                                                <td class="fieldLabel">HealthInsurenceType:</td>
                                                                <td><s:select list="healthInsuranceTypeList" name="healthInsuranceType" cssClass="inputSelect" headerKey="-1" headerValue="--Select Insurance--"  value="%{currentEmpInsurance.healthInsuranceType}"/> </td>
                                                                
                                                                <td class="fieldLabel">HealthInsEffecDate:</td>
                                                                <td><s:textfield name="healthInsEffecDate" cssClass="inputTextBlueSmall" value="%{currentEmpInsurance.healthInsEffecDate}"/><a href="javascript:cal2.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></td>
                                                                
                                                                <td class="fieldLabel">HealthInsCoverage:</td><!--onchange="return validatenumber(this)" -->
                                                                <td><s:textfield name="healthInsuranceCoverage" cssClass="inputTextBlue" value="%{currentEmpInsurance.healthInsuranceCoverage}" onchange="fieldLengthValidator(this);" onblur="return validatenumber(this)"  id="healthInsCoverage" size="10"/></td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">EffecChangeDate:</td>
                                                                <td><s:textfield  name="effecChangeDate" cssClass="inputTextBlueSmall" value="%{currentEmpInsurance.effecChangeDate}"/><a href="javascript:cal3.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></td>
                                                                
                                                                <td class="fieldLabel">HealthInsComName:</td>
                                                                <td><s:textfield name="healthInsComName" cssClass="inputTextBlue" value="%{currentEmpInsurance.healthInsComName}" onchange="fieldLengthValidator(this);" id="healthInsComName" size="30"/> </td>
                                                                
                                                                <td class="fieldLabel">HealthInsPolicyNo:</td>
                                                                <td ><s:textfield name="healthInsurePolicyNum" cssClass="inputTextBlue" value="%{currentEmpInsurance.healthInsurePolicyNum}" onchange="fieldLengthValidator(this);" id="healthInsPolicyNumber" size="15"/></td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">HealthInsPolicyDate:</td>
                                                                <td><s:textfield name="healthInsPolicyDate" cssClass="inputTextBlueSmall" value="%{currentEmpInsurance.healthInsPolicyDate}"/><a href="javascript:cal4.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></td>
                                                                
                                                                <td class="fieldLabel">HealthInsNoDept:</td><!--onchange="return validatenumber(this)"-->
                                                                <td ><s:textfield name="healthInsureNumOfDep" cssClass="inputTextBlue" value="%{currentEmpInsurance.healthInsureNumOfDep}" onchange="fieldLengthValidator(this);" onblur="return validatenumber(this)" id="healthInsNumOfDep" size="10"/></td>
                                                                
                                                                <td class="fieldLabel" >HealthInsDedAmt:</td>
                                                                <td colspan="3" ><s:textfield name="healthInsureDedAmt" cssClass="inputTextBlue" value="%{currentEmpInsurance.healthInsureDedAmt}" onchange="fieldLengthValidator(this);" id="healthInsDedAmt" size="10"/></td>                                                    
                                                            </tr>
                                                            
                                                            <!--Health insurence end of-->
                                                            <tr>
                                                                <td colspan="6" class="headerText" align="left">
                                                                    Cobranotif:
                                                                </td>
                                                            </tr> 
                                                            
                                                            <!--start payroll code-->
                        
                                                            <tr>
                                                                <td class="fieldLabel">CobraNotif:</td>
                                                                <td><s:textfield name="cobraNotif" cssClass="inputTextBlueSmall" value="%{currentEmpInsurance.cobraNotif}" onchange="return validatenumber(this);"/> <FONT  color="red" SIZE="0.5"><em>Ex: xx.xx</em></FONT></td>
                                                                
                                                                <td class="fieldLabel">CobraNotifDate:</td>
                                                                <td><s:textfield  name="cobraNotifDate" cssClass="inputTextBlueSmall" value="%{currentEmpInsurance.cobraNotifDate}"/><a href="javascript:cal5.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></td>
                                                                
                                                                <td class="fieldLabel">CobraStartDate:</td>
                                                                <td><s:textfield  name="cobraStartDate" cssClass="inputTextBlueSmall"  value="%{currentEmpInsurance.cobraStartDate}"/><a href="javascript:cal6.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">CobraPayment:</td>
                                                                <td colspan="5"><s:textfield name="cobraPayment" cssClass="inputTextBlue" value="%{currentEmpInsurance.cobraPayment}" onchange="return validatenumber(this);"/></td>
                                                            </tr>
                                                            <!--end of Helth Insurence-->
                                                
                                                            <!--start of Dental Insurence-->
                                                            <tr>
                                                                <td colspan="6" class="headerText" align="left">
                                                                    DentalInsurence:
                                                                </td>
                                                            </tr> 
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">DentalInsurenceType:</td> <!--onchange="return validatenumber(this)" -->
                                                                <td><s:textfield name="dentalInsureType" cssClass="inputTextBlue" value="%{currentEmpInsurance.dentalInsureType}" onchange="fieldLengthValidator(this);" onblur="return validatenumber(this)"  id="dentalInsurenceType" size="10"/> </td>
                                                                
                                                                <td class="fieldLabel">DentalInsEffecDate:</td>
                                                                <td><s:textfield name="dentalInsEffecDate" cssClass="inputTextBlueSmall" value="%{currentEmpInsurance.dentalInsEffecDate}"/><a href="javascript:cal7.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></td>
                                                                
                                                                <td class="fieldLabel">DentalInsurenceCoverage:</td><!--onchange="return validatenumber(this)"-->
                                                                <td  colspan="3" ><s:textfield name="dentalInsureCoverage" cssClass="inputTextBlue" value="%{currentEmpInsurance.dentalInsureCoverage}" onchange="fieldLengthValidator(this);" onblur="return validatenumber(this)" id="dentalInsurenceCoverage" size="10"/></td>                                                    
                                                            </tr>
                                                            
                                                            <!--End of Dental Insurence -->
                                                            <tr>
                                                                <td colspan="6" class="headerText" align="left">
                                                                    MedicalLeaveDetails:
                                                                </td>
                                                            </tr> 
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">MedicalLeave:</td>
                                                                <td><s:textfield name="medicalLeave" cssClass="inputTextBlue" value="%{currentEmpInsurance.medicalLeave}" onchange="fieldLengthValidator(this);" id="medicalLeave" size="30"/> </td>
                                                                
                                                                <td class="fieldLabel">MedLeaveEffecDate:</td>
                                                                <td><s:textfield  name="medicalLeaveEffecDate" cssClass="inputTextBlueSmall" value="%{currentEmpInsurance.medicalLeaveEffecDate}"/><a href="javascript:cal8.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></td>
                                                                
                                                                <td class="fieldLabel">Medical Hours:</td>
                                                                <td colspan="3"><s:textfield name="medicalHours" cssClass="inputTextBlue" value="%{currentEmpInsurance.medicalHours}"  onchange="return validatenumber(this);"/></td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td colspan="6" class="headerText" align="left">
                                                                    Disability:
                                                                </td>
                                                            </tr>  
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">ShortTermDisability:</td>
                                                                <td colspan="5"><s:textfield name="shortTermDisability" cssClass="inputTextBlueComment1" value="%{currentEmpInsurance.shortTermDisability}" onchange="fieldLengthValidator(this);" id="shortTermDisability" size="80"/> </td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">LongTermDisability:</td>
                                                                <td colspan="5"><s:textfield name="longTermDisability" cssClass="inputTextBlueComment1" value="%{currentEmpInsurance.longTermDisability}" onchange="fieldLengthValidator(this);" id="longTermDisability" size="80"/></td>
                                                            </tr>                      
                                                            
                                                            <tr>
                                                                <td colspan="6" class="headerText">
                                                                    General Details:
                                                                </td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">Comments:</td>
                                                                <td colspan="5"><s:textarea name="comments" cols="80" rows="2" cssClass="inputTextarea" value="%{currentEmpInsurance.comments}" onchange="fieldLengthValidator(this);" id="comment"/> </td>
                                                            </tr>
                                                            
                                                        </table>
                                                        
                                                        
                                                        
                                                    </s:form> 
                                                    <script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['EmployeeInsurence'].elements['licPolicyDate']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = false;
                                                           
                                                           var cal2 = new CalendarTime(document.forms['EmployeeInsurence'].elements['healthInsEffecDate']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = false;
                                                           
                                                           var cal3 = new CalendarTime(document.forms['EmployeeInsurence'].elements['effecChangeDate']);
                                                           cal3.year_scroll = true;
                                                           cal3.time_comp = false;
                                                           
                                                            var cal4 = new CalendarTime(document.forms['EmployeeInsurence'].elements['healthInsPolicyDate']);
                                                           cal4.year_scroll = true;
                                                           cal4.time_comp = false;
                                                           
                                                            var cal5 = new CalendarTime(document.forms['EmployeeInsurence'].elements['cobraNotifDate']);
                                                           cal5.year_scroll = true;
                                                           cal5.time_comp = false;
                                                           
                                                            var cal6 = new CalendarTime(document.forms['EmployeeInsurence'].elements['cobraStartDate']);
                                                           cal6.year_scroll = true;
                                                           cal6.time_comp = false;
                                                           
                                                            var cal7 = new CalendarTime(document.forms['EmployeeInsurence'].elements['dentalInsEffecDate']);
                                                           cal7.year_scroll = true;
                                                           cal7.time_comp = false;
                                                           
                                                            var cal8 = new CalendarTime(document.forms['EmployeeInsurence'].elements['medicalLeaveEffecDate']);
                                                           cal8.year_scroll = true;
                                                           cal8.time_comp = false;
                                                           
                                                           
                                                          
                                                    </script>
                                                    <%--   </sx:div > --%>
                                                
                                                    <!--//START TAB : -->
                                                    <%--     <s:div id="attachmentTab" label="InsuranceDeatils"  >
                                                    <%!
                                                    /* Declarations */
                                                    Connection connection;
                                                    String queryString;
                                                    String currentAccountId;
                                                    String strTmp;
                                                    String strSortCol;
                                                    String strSortOrd;
                                                    
                                                    int intSortOrd = 0;
                                                    int intCurr;
                                                    boolean blnSortAsc = true;
                                                    %>
                                                    
                                                    <%
                                                    /* String Variable for storing current position of records in dbgrid*/
                                                    strTmp = request.getParameter("txtCurr");
                                                    
                                                    intCurr = 1;
                                                    
                                                    if (strTmp != null)
                                                        intCurr = Integer.parseInt(strTmp);
                                                    
                                                    /* Specifing Shorting Column */
                                                    strSortCol = request.getParameter("Colname");
                                                    
                                                    if (strSortCol == null) strSortCol = "AttachmentName";
                                                    
                                                    strSortOrd = request.getParameter("txtSortAsc");
                                                    if (strSortOrd == null) strSortOrd = "ASC";
                                                    
                                                    try{
                                                        
                                                        /* Getting DataSource using Service Locator */
                                                        connection=ConnectionProvider.getInstance().getConnection();
                                                        
                                                        /* Sql query for retrieving resultset from DataBase */
                                                        queryString  =null;
                                                        String  queryString="SELECT EmpId,LicPolicyNumber,HealthInsComName,HealthInsNumOfDep,MedicalLeave,MedicalHours,MedLeaveEffecDate,HealthInsPolicyDate,Comments from tblEmpInsurance";
                                                    %>
                                                    
                                                    
                                                    <form method="post" id="frmDBGrid" name="frmDBGrid" action=""> 
                                                        
                                                        <!-- DataGrid for list all activities -->
                                                        <grd:dbgrid id="tblEmpInsurance" name="tblEmpInsurance" width="50" pageSize="10" 
                                                                    currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                    dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable"
                                                        >
                                                            
                                                            <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                           imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"/>
                                                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" />
                                                            
                                                            <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center" 
                                                                              imageSrc="../includes/images/DBGrid/Edit.gif" 
                                                                              linkUrl="getInsurance.action?empId={EmpId}"                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
                                                                              imageBorder="0" imageWidth="16" imageHeight="16" 
                                                                              alterText="Click to edit" /> 
                                                            
                                                            
                                                            <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                           imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"/>
                                                            
                                                                <grd:textcolumn dataField="EmpId"       headerText="empId" width="5" sortable="true" dataFormat=""/>
                                                            <grd:textcolumn dataField="LicPolicyNumber"       headerText="LicPolicyNumber" width="15" sortable="true"/>  
                                                            <grd:textcolumn dataField="HealthInsComName"  headerText="HealthInsComName"   width="15" sortable="true"/>
                                                            <grd:textcolumn dataField="HealthInsNumOfDep"  headerText="HealthInsNumOfDep"   width="15" sortable="true"/>
                                                            <grd:numbercolumn dataField="MedicalLeave"       headerText="MedicalLeave" width="15" sortable="true" dataFormat=""/> 
                                                <grd:numbercolumn dataField="MedicalHours"       headerText="MedicalHours" width="15" sortable="true" dataFormat=""/>
                                                            <grd:datecolumn dataField="MedLeaveEffecDate"  headerText="MedLeaveEffecDate"   width="10" dataFormat="MM/dd/yyyy" sortable="true"/>
                                                            <grd:datecolumn dataField="HealthInsPolicyDate"  headerText="HealthInsPolicyDate"   width="10" dataFormat="MM/dd/yyyy" sortable="true"/>
                                                            <grd:textcolumn dataField="Comments"       headerText="Comments" width="15" sortable="true"/>
                                                        </grd:dbgrid>
                                                        
                                                        <!-- these components are DBGrid Specific  -->
                                                        <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                        <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                        <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                        <input id="txtDBId" TYPE="hidden" NAME="txtDBId" VALUE="0">
                                                        <input id="txtTRId"TYPE="hidden" NAME="txtTRId" VALUE="1">       
                                                    </form>
                                                    
                                                    <%
                                                    }catch(Exception ex){
                                                        out.println(ex.toString());
                                                    }finally{
                                                        if(connection!= null){
                                                            connection.close();
                                                        }
                                                    }
                                                    %>
                                                    
                                                    
                                                </s:div > --%>
                                                    <%--  </sx:tabbedpanel> --%>
                                                </div>
                                                <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                </script>
                                        </td>
                                    </tr>
                                </table>
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
