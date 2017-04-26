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
    <title>Hubble Organization Portal :: Employee Ancillary Details</title>
    <sx:head cache="true"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/AncillaryClientValidation.js"/>"></script>    --%>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EducationUtil.js"/>"></script>    
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>    
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
</head>
<body class="bodyGeneral" oncontextmenu="return false;">

<%--  <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String currentEmployeeId;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
        %> --%>

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
    <table class="innerTable1000x515" cellpadding="0" cellspacing="0">
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
                    <s:if test="currentAncillary == null ">
                        <a class="navigationText" href="<s:url action="getEmployee"><s:param name="empId" value="%{id}"/></s:url>"><s:property value="%{employeeName}"/></a>
                    </s:if>
                    <s:else>
                        <a class="navigationText" href="<s:url action="getEmployee"><s:param name="empId" value="%{currentAncillary.empId}"/></s:url>"><s:property value="%{employeeName}"/></a>
                    </s:else>
                </td>
            </tr>
            <tr>
            <td valign="top" style="padding: 5x 5px 5px 5px;"> 
                <!--//START TABBED PANNEL : -->
                <ul id="accountTabs" class="shadetabs" >
                    <li ><a href="#" class="selected" rel="personalDetailsTab"  > Employee Ancillary Details </a></li>
                </ul>                     
                <div  style="border:1px solid gray; width:850px;height: 550px; overflow:auto; margin-bottom: 1em;">
                    <%--  <sx:tabbedpanel id="activityPannel" cssStyle="width:850px; height:550px;padding: 5x 5px 5px 5px;" doLayout="true"> --%>
                                                
                    <!--//START TAB : -->
                    <%-- <sx:div id="personalDetailsTab" label="Employee Ancillary Details" cssStyle="overflow:auto" > --%>
                    <div id="personalDetailsTab" class="tabcontent"  >
                        <%--
                                        <s:form action="ancillaryAdd" theme="simple" onsubmit="return validate(this);">
                                            --%> 
                        <!--onsubmit="return addressValidate();"-->    
                        <s:form name="ancillaryForm" action="%{formAction}" theme="simple">
                            <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                <tr class="headerText">
                                    <td colspan="2" align="left">Under Graduation Details: </td>
                                    <td colspan="5" align="right">
                                        <s:property value="#request.resultMessage"/>
                                        <s:if test="currentAncillary == null">
                                            <s:hidden name="id" value="%{id}"/>
                                        </s:if>
                                        <s:else>
                                            <s:hidden name="id" value="%{currentAncillary.empId}"/>
                                        </s:else>
                                        <s:submit name="submit" value="Save" cssClass="buttonBg"/>
                                        
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td class="fieldLabel" width="200px" align="right">Degree :</td>
                                    <td><s:select name="degree" headerKey="-1" headerValue="--Please Select--" list="{'B.E/B.Tech','B.Sc','B.Com','BA','BBM'}" id="degree" cssClass="inputSelect" value="%{currentAncillary.degree}" onchange="getSpecializations(this.form, this.value);" /></td>
                                    
                                    <td class="fieldLabel" width="200px" align="right">Specialization :</td>
                                    <td><select name="specialization" id="specialization" class="inputSelect"><option value="<s:property value="%{currentAncillary.specialization}"/>"><s:property value="%{currentAncillary.specialization}"/></option></select></td>
                                    
                                    <td class="fieldLabel" width="200px" align="right">Marks(in %) :</td>
                                    <td><s:textfield name="marks" cssClass="inputTextBlue" value="%{currentAncillary.marks}" id="marks" onkeypress="return isNumber(event)" size="30"/></td>
                                </tr>
                                
                                <tr>
                                    <td class="fieldLabel"  width="200px" align="right">University :</td>
                                    <td><s:textfield name="university" cssClass="inputTextBlueLarge" value="%{currentAncillary.university}" id="university" onchange="fieldLengthValidator(this);" size="30"/></td>
                                    
                                    <td class="fieldLabel"  width="200px" align="right">Year of PassOut :</td>
                                    <td><s:textfield name="passOut" cssClass="inputTextBlue" value="%{currentAncillary.passOut}" id="passOut" size="30" onchange="fieldLengthValidator(this);" onblur="return validatenumber(this)"/></td>
                                </tr>
                                
                                <tr>
                                    <td class="fieldLabel"  width="200px" align="right">College :</td>
                                    <td colspan="5"><s:textfield name="college" cssClass="inputTextBlueComment1" value="%{currentAncillary.college}" id="college" onchange="fieldLengthValidator(this);" size="30"/></td>
                                </tr>
                                
                                <tr>
                                    <td colspan="6" class="headerText" align="left">Post Graduation Details: </td>
                                </tr>
                                
                                <tr>
                                    <td class="fieldLabel" width="200px" align="right">PG :</td>
                                    <td><s:select name="pg" headerKey="-1" headerValue="---Please Select---" list="{'M.E/M.Tech','M.C.A','M.Sc','MBA','MHRM','PGDBM'}" id="pg" cssClass="inputSelect" value="%{currentAncillary.pg}" onchange="getPgSpecializations(this.form, this.value);" /></td>
                                    
                                    <td class="fieldLabel" width="200px" align="right">Specialization :</td>
                                    <td><select name="pgSpecialization" id="pgSpecialization" class="inputSelect"><option value="<s:property value="%{currentAncillary.pgSpecialization}"/>"><s:property value="%{currentAncillary.pgSpecialization}"/></option></select></td>
                                    
                                    <td class="fieldLabel" width="200px" align="right">Marks(in %) :</td>
                                    <td><s:textfield name="pgMarks" cssClass="inputTextBlue" value="%{currentAncillary.pgMarks}" id="pgMarks" onkeypress="return isNumber(event)" size="30"/></td>
                                </tr>
                                
                                <tr>
                                    <td class="fieldLabel"  width="200px" align="right">University :</td>
                                    <td><s:textfield name="pgUniversity" cssClass="inputTextBlueLarge" value="%{currentAncillary.pgUniversity}" id="pgUniversity" onchange="fieldLengthValidator(this);" size="30"/></td>
                                    
                                    <td class="fieldLabel"  width="200px" align="right">Year of PassOut :</td>
                                    <td><s:textfield name="pgPassOut" cssClass="inputTextBlue" value="%{currentAncillary.pgPassOut}" id="pgPassOut" size="30" onchange="fieldLengthValidator(this);" onblur="return validatenumber(this)"/></td>
                                </tr>
                                
                                <tr>
                                    <td class="fieldLabel"  width="200px" align="right">College :</td>
                                    <td colspan="5"><s:textfield name="pgCollege" cssClass="inputTextBlueComment1" value="%{currentAncillary.pgCollege}" id="pgCollege" onchange="fieldLengthValidator(this);" size="30"/></td>
                                </tr>
                                
                                <tr>
                                    <td colspan="6" class="headerText" align="left">Parent Details: </td>
                                </tr>
                                
                                <tr>
                                    <td class="fieldLabel"  width="200px" align="right">Father Name :</td>
                                    <td><s:textfield name="fatherName" cssClass="inputTextBlue" value="%{currentAncillary.fatherName}" onchange="fieldLengthValidator(this);" id="fatherName" size="30"/></td>    
                                    
                                    <td class="fieldLabel"  width="200px"align="right">Father Title :</td>                          
                                    <td><s:textfield name="fatherTitle" cssClass="inputTextBlue" value="%{currentAncillary.fatherTitle}" onchange="fatherTitleValidate(document.ancillaryForm.fatherTitle.value);" id="fatherTitle" size="30"/></td>  
                                    
                                    <td class="fieldLabel" width="200px" align="right">Father Phone :</td>                          
                                    <!--onchange="return validatenumber(this)" -->
                                    <td><s:textfield name="fatherPhone" cssClass="inputTextBlue" value="%{currentAncillary.fatherPhone}" onchange="formatPhone(this);" id="fatherPhone" size="30"/></td>   
                                </tr> 
                                
                                <tr>
                                    <td class="fieldLabel"  width="200px" align="right">Father Address :</td>                                                                               
                                    <td colspan="5"><s:textfield name="fatherAddress" cssClass="inputTextBlueComment1" value="%{currentAncillary.fatherAddress}" onchange="fieldLengthValidator(this);" id="fatherAddress" size="255"/></td>    
                                </tr> 
                                
                                <tr>
                                    <td colspan="6" class="headerText" align="left">
                                        Laptop Details:
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td class="fieldLabel"  width="200px" align="right">Laptop Type :</td>  
                                    <td><s:textfield name="laptopType" cssClass="inputTextBlue" value="%{currentAncillary.laptopType}" onchange="fieldLengthValidator(this);" id="laptopType" size="20"/></td>    
                                    
                                    <td class="fieldLabel"  width="200px"align="right">Memory  :</td>       
                                    <td><s:textfield name="memory" cssClass="inputTextBlue" value="%{currentAncillary.memory}" onchange="fieldLengthValidator(this);" id="memory" size="20"/></td>  
                                    
                                    <td class="fieldLabel" width="200px" align="right">Hard Disk :</td>     
                                    <td><s:textfield name="hardDisk" cssClass="inputTextBlue" value="%{currentAncillary.hardDisk}" onchange="fieldLengthValidator(this);" id="hardDisk" size="20"/></td>   
                                </tr>
                                
                                <tr>
                                    <td class="fieldLabel"  width="200px" align="right">Model :</td>                           
                                    <td><s:textfield name="model" cssClass="inputTextBlue" value="%{currentAncillary.model}" onchange="fieldLengthValidator(this);" id="model" size="20"/></td>    
                                    
                                    <td class="fieldLabel"  width="200px"align="right">Serial No :</td>                        
                                    <td><s:textfield name="serialNo" cssClass="inputTextBlue" value="%{currentAncillary.serialNo}" onchange="fieldLengthValidator(this);" id="serialNo" size="20"/></td>  
                                    
                                    <td class="fieldLabel" width="200px" align="right">Purchase Date :</td>
                                    <td><s:textfield name="purchaseDate" cssClass="inputTextBlueSmall" value="%{currentAncillary.purchaseDate}"/><a href="javascript:cal1.popup();">
                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                         width="20" height="20" border="0"></td>
                                </tr>
                                
                                <tr>
                                    <td class="fieldLabel" width="200px" align="right">Warranty Exp :</td>
                                    <td colspan="5"><s:textfield cssClass="inputTextBlueSmall" name="warrantyExp" value="%{currentAncillary.warrantyExp}"/><a href="javascript:cal2.popup();">
                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                         width="20" height="20" border="0"></td>
                                </tr>
                                
                                <tr>
                                    <td colspan="6" class="headerText" align="left">
                                        Referal Details:
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td class="fieldLabel"  width="200px" align="right">Referal Name :</td>                           
                                    <td><s:textfield name="referalName" cssClass="inputTextBlue" value="%{currentAncillary.referalName}" onchange="fieldLengthValidator(this);" id="referalName" size="30"/></td>    
                                    
                                    <td class="fieldLabel"  width="200px"align="right">Referal Relation :</td>                        
                                    <td><s:textfield name="referalRelation" cssClass="inputTextBlue" value="%{currentAncillary.referalRelation}" onchange="fieldLengthValidator(this);" id="referalRelation" size="30"/></td>  
                                    
                                    <td class="fieldLabel" width="200px" align="right">Referal Phone :</td>                           
                                    <!--onchange="return validatenumber(this)"-->
                                    <td><s:textfield name="referalPhone" cssClass="inputTextBlue" value="%{currentAncillary.referalPhone}"  onchange="formatPhone(this);" id="referalPhone" size="20"/></td>   
                                </tr>  
                                
                                <tr>
                                    <td class="fieldLabel"  width="200px" align="right">Referal Email :</td>     
                                    <!--onchange="return checkEmail(ancillaryForm.referalEmail.value);"-->
                                    <td><s:textfield name="referalEmail" cssClass="inputTextBlue" value="%{currentAncillary.referalEmail}" onchange="return checkEmail(this);"   id="referalEmail" size="30"/></td>    
                                    
                                    <td class="fieldLabel"  width="200px"align="right">Referal Alt Phone :</td>                   
                                    <!-- onchange="return validatenumber(this)"-->
                                    <td colspan="3"><s:textfield name="referalAltPhone" cssClass="inputTextBlue" value="%{currentAncillary.referalAltPhone}" onchange="formatPhone(this);" id="referalAltPhone" size="20"/></td>  
                                </tr>
                                
                                <tr>
                                    <td class="fieldLabel" width="200px" align="right">Referal Comments :</td>                           
                                    <td colspan="5"><s:textfield name="referalComments" cssClass="inputTextBlueComment1" value="%{currentAncillary.referalComments}" onchange="fieldLengthValidator(this);" id="referalComments" size="255"/></td>   
                                </tr>
                                
                                <tr>
                                    <th colspan="6" class="headerText" align="left">
                                        Contract Details:
                                    </th>
                                </tr>
                                
                                <tr>
                                    <td class="fieldLabel"  width="200px" align="right">Contract On FileId :</td> 
                                    <!--onchange="return validatenumber(this)"-->
                                    <td><s:textfield name="contractOnField" cssClass="inputTextBlue" value="%{currentAncillary.contractOnField}"  onchange="fieldLengthValidator(this);"  onblur="return validatenumber(this)"  id="contractOnField" size="10"/></td>    
                                    
                                    <td class="fieldLabel"  width="200px" align="right">Contract Period :</td>                           
                                    <td><s:textfield name="contractPeriod" cssClass="inputTextBlue" value="%{currentAncillary.contractPeriod}"  onchange="fieldLengthValidator(this);" id="contractPeriod" size="10"/></td>    
                                    
                                    <td class="fieldLabel"  width="200px"align="right">Train Period :</td>           <!--onchange="return validatenumber(this)"  -->                
                                    <td><s:textfield name="TrainPeriod" cssClass="inputTextBlue" value="%{currentAncillary.TrainPeriod}" onchange="fieldLengthValidator(this);"   onblur="return validatenumber(this)" id="trainPeriod" size="10"/></td>  
                                </tr>
                                
                                <tr>
                                    <td class="fieldLabel" width="200px" align="right">Contract Sign Date :</td>
                                    <td><s:textfield cssClass="inputTextBlueSmall" name="contractSignDate" value="%{currentAncillary.contractSignDate}"/><a href="javascript:cal3.popup();">
                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                         width="20" height="20" border="0"></td> 
                                    
                                    <td class="fieldLabel" width="200px" align="right">Contract Exp Date :</td>
                                    <td><s:textfield cssClass="inputTextBlueSmall" name="contractExpDate" value="%{currentAncillary.contractExpDate}"/><a href="javascript:cal4.popup();">
                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                         width="20" height="20" border="0"></td>  
                                    
                                    <td class="fieldLabel" width="200px" align="right">Train Start Date  :</td>
                                    <td><s:textfield cssClass="inputTextBlueSmall" name="trainStartDate" value="%{currentAncillary.trainStartDate}"/><a href="javascript:cal5.popup();">
                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                         width="20" height="20" border="0"></td>   
                                </tr>
                                
                                <tr>
                                    <td class="fieldLabel"  width="200px" align="right">Bond Provided By :</td>                           
                                    <td><s:textfield name="bondProvidedBy" cssClass="inputTextBlue" value="%{currentAncillary.bondProvidedBy}"  onchange="fieldLengthValidator(this);" id="bondProvidedBy" size="30"/></td>    
                                    
                                    <td class="fieldLabel"  width="200px"align="right">Relation to Employee :</td>                        
                                    <td colspan="3"><s:textfield name="relationToEmployee" cssClass="inputTextBlue" value="%{currentAncillary.relationToEmployee}" onchange="fieldLengthValidator(this);"  id="relationToEmployee" size="30"/></td>  
                                </tr>
                                
                                <tr>
                                    <td colspan="6" class="headerText" align="left">
                                        Previous Job Details:
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td class="fieldLabel" width="200px" align="right"> Job Title :</td>                           
                                    <td><s:textfield name="jobTitle" cssClass="inputTextBlue" value="%{currentAncillary.jobTitle}" onchange="fieldLengthValidator(this);" id="jobTitle" size="30"/></td>  
                                    
                                    <td class="fieldLabel"  width="200px" align="right">Job Company :</td>                         
                                    <td><s:textfield name="jobCompany" cssClass="inputTextBlue" value="%{currentAncillary.jobCompany}" onchange="fieldLengthValidator(this);" id="jobCompany" size="50"/></td>    
                                    
                                    <td class="fieldLabel"  width="200px"align="right">Job Phone :</td>                <!-- onchange="return validatenumber(this)"-->           
                                    <td><s:textfield name="jobPhone" cssClass="inputTextBlue" value="%{currentAncillary.jobPhone}" onchange="return formatPhone(this);" id="jobPhone" size="15"/></td>  
                                </tr>     
                                
                                <tr>
                                    <td class="fieldLabel" width="200px" align="right"> Job Address :</td>                           
                                    <td colspan="5"><s:textfield name="jobAddress" cssClass="inputTextBlueComment1" value="%{currentAncillary.jobAddress}" onchange="fieldLengthValidator(this);" id="jobAddress" size="80"/></td>   
                                </tr>
                                
                                <tr>
                                    <td class="fieldLabel"  width="200px" align="right">Comments  :</td>                           
                                    <td colspan="5"><s:textfield name="comments" cssClass="inputTextBlueComment1" value="%{currentAncillary.comments}" onchange="fieldLengthValidator(this);" id="comment" size="255"/></td>   
                                </tr>
                                
                            </table>
                            
                            
                        </s:form>
                        <!--//END TABBED PANNEL : -->
                                        
                        <!-- this script for After loading Form it will instantiate Calender Objects as you require -->
                        <script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['ancillaryForm'].elements['purchaseDate']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = false;
                                                           
                                                           var cal2 = new CalendarTime(document.forms['ancillaryForm'].elements['warrantyExp']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = false;
                                                           
                                                           var cal3 = new CalendarTime(document.forms['ancillaryForm'].elements['contractSignDate']);
                                                           cal3.year_scroll = true;
                                                           cal3.time_comp = false;
                                                           
                                                            var cal4 = new CalendarTime(document.forms['ancillaryForm'].elements['contractExpDate']);
                                                           cal4.year_scroll = true;
                                                           cal4.time_comp = false;
                                                           
                                                            var cal5 = new CalendarTime(document.forms['ancillaryForm'].elements['trainStartDate']);
                                                           cal5.year_scroll = true;
                                                           cal5.time_comp = false;
                                                           
                                                          
                        </script>
                        <%-- </sx:div > --%>
                    </div>
                    <!--//END TAB : -->
                                        
                    <!--//START TAB : -->
                    <%--    <s:div id="Ancillary Tab" label="Employee Ancillary List" >
                                                    <%
                                                    /* String Variable for storing current position of records in dbgrid*/
                                                    strTmp = request.getParameter("txtCurr");
                                                    
                                                    intCurr = 1;
                                                    
                                                    if (strTmp != null)
                                                        intCurr = Integer.parseInt(strTmp);
                                                    
                                                    /* Specifing Shorting Column */
                                                    strSortCol = request.getParameter("Colname");
                                                    
                                                    if (strSortCol == null) strSortCol = "AccountName";
                                                    
                                                    strSortOrd = request.getParameter("txtSortAsc");
                                                    if (strSortOrd == null) strSortOrd = "ASC";
                                                    
                                                    try{
                                                        
                                                        /* Getting DataSource using Service Locator */
                                                        
                                                        connection = ConnectionProvider.getInstance().getConnection();
                                                        
                                                        /* Sql query for retrieving resultset from DataBase */
                                                        queryString = "Select EmpId,FatherName,ReferalName,PrevJobTitle from tblEmpAncillary";
                                                        //queryString = queryString+" FROM tblEmpAncillary";
                                                    %>
                                                    
                                                    <s:form action="" name="frmDBGrid" theme="simple">
                                                        
                                                        
                                                        
                                                        <table cellpadding="0" cellspacing="0" width="100%">
                                                            <tr align="right">
                                                                <td class="headerText">
                                                                    <img alt="Home" 
                                                                         src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                                                         width="100%" 
                                                                         height="13px" 
                                                                         border="0">
                                                                </td>
                                                            </tr>    
                                                            <!---BEGIN:: DBGrid Specific ---->  
                                                            <tr>
                                                                <td class="subHeader" width="700"></td>
                                                            </tr>
                                                            <tr>
                                                                <td width="100%">
                                                                    
                                                                    <grd:dbgrid id="tblAccountList" name="tblAccountList" width="100" pageSize="7" 
                                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                        
                                                                        <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"/>
                                                                        
                                                                        <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                        imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                                                                        imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>    
                                                                        
                                                                        <grd:imagecolumn headerText="Edit" width="4" HAlign="center" imageSrc="../includes/images/DBGrid/Edit.gif"
                                                                                         linkUrl="getAncillary.action?id={EmpId}" imageBorder="0"
                                                                                         imageWidth="16" imageHeight="16" alterText="Click to edit"></grd:imagecolumn>
                                                                        
                                                                        <grd:textcolumn dataField="FatherName"         headerText="Father Name" width="25" sortable="true"/>
                                                                        <grd:textcolumn dataField="ReferalName"          headerText="Referal Name" width="10" sortable="true"/>
                                                                        <grd:textcolumn dataField="PrevJobTitle"	headerText="Previous JobTitle" width="15" sortable="true"/>
                                                                        
                                                                    </grd:dbgrid>
                                                                    
                                                                    <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                    <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                    <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                                    
                                                                </td>
                                                            </tr>
                                                        </table>                   
                                                        
                                                        
                                                    </s:form>
                                                    <%
                                                    }catch(Exception ex){
                                                        out.println(ex.toString());
                                                    }finally{
                                                        if(connection!= null){
                                                            connection.close();
                                                        }
                                                    }
                                                    %>
                                                </s:div >--%>
                    <!--//END TAB : -->
                                        
                    <%--  </sx:tabbedpanel> --%>
                </div>
                <!--//END TABBED PANNEL : -->
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
