<%-- 
    Document   : EmpExpenses
    Created on : Nov 18, 2015, 12:41:40 AM
    Author     : miracle
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<html>
    <head>
        <title>Hubble Organization Portal :: Employee Expenses</title>
    <sx:head cache="true"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAddAjax.js"/>"></script>
    <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/AddressClientValidation.js"/>"></script>--%>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>

</head>

<%-- <body class="bodyGeneral" oncontextmenu="return false;" onload="isCafeteria();isTransportation();selectedAccomdation()" > --%>
<body class="bodyGeneral" oncontextmenu="return false;" >
    
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
                        <td width="850px" class="cellBorder" valign="top" >
                            <table cellpadding="0" cellspacing="0" width="100%" border="0">
                                <tr>
                                    <td>
                                        <span class="fieldLabel">Employee Name :</span>&nbsp;
                                        <s:if test="currentExpenses == null ">
                                            <a class="navigationText" href="<s:url action="getEmployee"><s:param name="empId" value="%{id}"/></s:url>"><s:property value="%{employeeName}"/></a>
                                        </s:if>
                                        <s:else>
                                            <a class="navigationText" href="<s:url action="getEmployee"><s:param name="empId" value="%{currentExpenses.empId}"/></s:url>"><s:property value="%{employeeName}"/></a>
                                        </s:else>
                                    </td>
                                </tr>
                                <tr>
                                    <td valign="top" style="padding:5px 5px 5px 5px;"> 
                                        <!--//START TABBED PANNEL : -->
                                        <ul id="accountTabs" class="shadetabs" >
                                            <li ><a href="#" class="selected" rel="personalDetailsTab"  > Employee Expenses </a></li>
                                        </ul>
                                        <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                            <%--  <sx:tabbedpanel id="activityPannel" cssStyle="width: 845px; height:500px;padding:5px 5px 5px 5px;" doLayout="true"> --%>

                                            <!--//START TAB : -->
                                            <%-- <sx:div id="personalDetailsTab" label="Employee Address" > --%>
                                            <div id="personalDetailsTab" class="tabcontent"  >
                                                
                                                <s:form name="employeeExpenses" action="%{currentAction}"  theme="simple" onsubmit="return expensesValidatator();">

                                                    <table cellpadding="0" cellspacing="0" width="100%" border="0">

                                                        <tr><td class="headerText" align="right" colspan="4">
                                                                <s:property value="#request.resultMessage"/>

                                                            </td>     </tr>
                                                        <tr>
                                                            <%-- START HOME ADDRESS TABLE --%>
                                                            <td>
                                                                <table width="100%" border="0" cellpadding="1" cellspacing="1">
                                                                    <tr>
                                                                        <td colspan="6"><div id="expensesResultMsg"></div></td>
                                                                    </tr>  
                                                                    <tr>
                                                                        <td class="fieldLabel"> Accommodation: </td>                                                                        
                                                                        <td><s:select list="accommodationList" id="homeState" name="accommodation" value="%{currentExpenses.accommodation}"
                                                                                  headerKey="" headerValue="--Please Select--" cssClass="inputSelect" onchange="selectedAccomdation()"/></td>

                                                                        <td class="fieldLabel">Date&nbsp;of&nbsp;Occupancy&nbsp;:</td>
                                                                        <td ><s:textfield name="dateOfOccupancy" value="%{currentExpenses.dateOfOccupancy}" cssClass="inputTextBlue" id="dateOfOccupancy" size="50"/><a href="javascript:cal1.popup();">
                                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                     width="20" height="20" border="0"></td>
                                                                                <td class="fieldLabel">Occupancy&nbsp;Type&nbsp;:</td>
                                                                                <td >
                                                                                    
                                                                                    <s:select name="occupancyType" id="occupancyType" cssClass="inputSelect" headerKey="" headerValue="--Please Select--" list="{'Single','Family'}" value="%{currentExpenses.occupancyType}"></s:select>


                                                                                    </td></tr><tr>

                                                                            <td class="fieldLabel">Room No:</td>
                                                                            <td ><s:textfield name="roomNo" value="%{currentExpenses.roomNo}" onchange="fieldLengthValidator(this);" cssClass="inputTextBlue" id="roomNo" size="50"/></td>
                                                                        <td class="fieldLabel">Room Fee:</td>
                                                                        <td><s:textfield name="roomFee" value="%{currentExpenses.roomFee}" onkeypress="return isNumber(event)" cssClass="inputTextBlue" id="roomFee" size="50"/></td>

                                                                        <td class="fieldLabel">Electrical&nbsp;Charges&nbsp;:</td>
                                                                        <td ><s:textfield name="electricalCharges" value="%{currentExpenses.electricalCharges}" cssClass="inputTextBlue" id="electricalCharges" size="50" onkeypress="return isNumber(event)"/></td>

                                                                    </tr>

                                                                    <tr>
                                                                        <td class="fieldLabel">Cafeteria:</td>
                                                                        <td > <s:radio id="cafeteria" name="cafeteria" value="%{cafeteria}" list="#@java.util.LinkedHashMap@{'Yes':'Yes','No':'No'}"  onchange="isCafeteria();" /></td>  
                                                                        <td class="fieldLabel">Cafeteria Fee:</td>
                                                                        <td ><s:textfield name="cafeteriaFee" value="%{currentExpenses.cafeteriaFee}" onkeypress="return isNumber(event)" cssClass="inputTextBlue" id="cafeteriaFee" size="50"/></td>


                                                                    </tr>
                                                                    <tr>
                                                                        <td class="fieldLabel">Transportation:</td>
                                                                        <td ><s:radio id="transportation" name="transportation" value="%{transportation}" list="#@java.util.LinkedHashMap@{'Yes':'Yes','No':'No'}" onchange="isTransportation();"/></td>  
                                                                        <td class="fieldLabel">Transport Location:</td>
                                                                        <td><s:select list="transportLocationList" id="transportLocation" name="transportLocation" value="%{currentExpenses.transportLocation}" cssClass="inputSelect" 
                                                                                  headerKey="" headerValue="--Please Select--" /></td>

                                                                        <td class="fieldLabel">Transportation Fee:</td>
                                                                        <td ><s:textfield name="transportFee" value="%{currentExpenses.transportFee}" onkeypress="return isNumber(event)" cssClass="inputTextBlue" id="transportFee" size="50"/></td>
                                                                    </tr>
                                                                    <tr> 

                                                                        <td  colspan="5" align="right">

                                                                            <s:if test="%{currentAction == 'editEmpExpenses'}">
                                                                                <s:submit name="submit" value="Update" cssClass="buttonBg"/>

                                                                                <s:hidden name="expId" value="%{expId}"/>
                                                                            </s:if>
                                                                            <s:else>
                                                                                <s:submit id="addStatus" name="addStatus" value="Add" cssClass="buttonBg"/>
                                                                                <s:if test="%{!getOtherDetailExpenses.isEmpty()}">
                                                                                    <input type="button" name="addStatus" value="Get Last Record" Class="buttonBg" onclick="expensesValues()"/>
                                                                                </s:if>
                                                                            </s:else>
                                                                            <s:if test="currentExpenses == null">
                                                                                <s:hidden name="id" id="id" value="%{id}"/>
                                                                            </s:if>
                                                                            <s:else>
                                                                                <s:hidden name="id" id="id"  value="%{currentExpenses.empId}"/>
                                                                            </s:else>
                                                                        </td>
                                                                    </tr>
                                                                    <s:if test="%{!getOtherDetailExpenses.isEmpty()}">   
                                                                        <tr>

                                                                            <td colspan="6">
                                                                                <table cellpadding="2" cellspacing="1" width="98%" class="gridTable">
                                                                                    <tr class="gridHeader">
                                                                                        <td width="4%" class="gridHeader" ALIGN="center">Edit</td>
                                                                                        <td width="6%" class="gridHeader">Accommodation</td>
                                                                                        <td width="8%" class="gridHeader">RoomNo</td>
                                                                                        <td width="8%" class="gridHeader">RoomFee</td>                                                                  
                                                                                        <td width="8%" class="gridHeader">Created Date</td>                                  
                                                                                    </tr>

                                                                                    <s:iterator value="#request.getOtherDetailExpenses">
                                                                                        <tr class="gridRowEven">
                                                                                            <td class="gridColumn" align="center"><a href="expensesCheck.action?id=<s:property value="EmpId"/>&expId=<s:property value="id"/>&flag=edit"><img src="../includes/images/DBGrid/newEdit_17x18.png"></a></td>

                                                                                            <td class="gridColumn" ><s:property value="Accommodation"/></td>
                                                                                            <td class="gridColumn" align="left"><s:property value="RoomNo"/></td>
                                                                                            <td class="gridColumn" align="left"><s:property value="RoomFee"/></td>                                        
                                                                                            <td class="gridColumn" align="left"><s:property value="createdDate"/></td>                                      
                                                                                        </tr>
                                                                                    </s:iterator>
                                                                                    <tr class="gridPager">
                                                                                        <td colspan="8">&nbsp;</td>
                                                                                    </tr>
                                                                                </table>
                                                                            </td>
                                                                        </tr>
                                                                    </s:if>                                                    

                                                        </tr>
                                                        <tr>


                                                        </tr>

                                                    </table>

                                                    </td>


                                                    </tr>

                                                    <tr>
                                                        <td colspan="2"></td>
                                                    </tr>



                                                    <tr>
                                                        <td colspan="2"></td>
                                                    </tr>



                                                    </table>

                                                </s:form>
                                                <%--  </sx:div> --%>
                                            </div>
                                            <!--//START TAB : -->

                                            <%--  </sx:tabbedpanel> --%>
                                        </div>
                                        <script type="text/javascript">

                                            var countries=new ddtabcontent("accountTabs")
                                            countries.setpersist(false)
                                            countries.setselectedClassTarget("link") //"link" or "linkparent"
                                            countries.init()
                                            var cal1 = new CalendarTime(document.forms['employeeExpenses'].elements['dateOfOccupancy']);
                                            cal1.year_scroll = true;
                                            cal1.time_comp = false;

                                        </script>

                                        <!--//END TABBED PANNEL : -->
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <!--//END DATA COLUMN : Coloumn for Screen Content-->
                    </tr>

                </table>
            </td>
        </tr>
        <!--//END DATA RECORD : Record for LeftMenu and Screen Content-->

        <!--//START FOOTER : Record for Footer Background and Content-->
        <tr class="footerBg">
            <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
        </tr>
        <!--//END FOOTER : Record for Footer Background and Content-->
        <script type="text/javascript">
		$(window).load(function(){
		isCafeteria();
		isTransportation();
		selectedAccomdation();

		});
		</script>
</body>
</html>
