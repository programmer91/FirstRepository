<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<html>
<head>
    <title>Hubble Organization Portal :: Employee Addresses</title>
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
                    <s:if test="currentAddress == null ">
                        <a class="navigationText" href="<s:url action="getEmployee"><s:param name="empId" value="%{id}"/></s:url>"><s:property value="%{employeeName}"/></a>
                    </s:if>
                    <s:else>
                        <a class="navigationText" href="<s:url action="getEmployee"><s:param name="empId" value="%{currentAddress.empId}"/></s:url>"><s:property value="%{employeeName}"/></a>
                    </s:else>
                </td>
            </tr>
            <tr>
            <td valign="top" style="padding:5px 5px 5px 5px;"> 
                <!--//START TABBED PANNEL : -->
                <ul id="accountTabs" class="shadetabs" >
                    <li ><a href="#" class="selected" rel="personalDetailsTab"  > Employee Address </a></li>
                </ul>
                  <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                <%--  <sx:tabbedpanel id="activityPannel" cssStyle="width: 845px; height:500px;padding:5px 5px 5px 5px;" doLayout="true"> --%>
                                            
                <!--//START TAB : -->
               <%-- <sx:div id="personalDetailsTab" label="Employee Address" > --%>
                <div id="personalDetailsTab" class="tabcontent"  >
                    <!--onsubmit="return addressValidate();"-->
                    <s:form name="employeeAddForm" action="%{currentAction}"  theme="simple">
                        
                        <table cellpadding="0" cellspacing="0" width="100%" border="0">
                            <tr> 
                                
                                <td class="headerText" colspan="2" align="right">
                                    <s:property value="#request.resultMessage"/>
                                    <s:submit name="submit" value="Save" cssClass="buttonBg"/>
                                    <s:hidden name="homeAddressId" id="homeAddressId" value="%{currentAddress.homeAddressId}"/>
                                    <s:hidden name="payAddressId" id="payAddressId" value="%{currentAddress.payAddressId}"/>
                                    <s:hidden name="cProAddressId" id="cProAddressId" value="%{currentAddress.cProAddressId}"/>
                                    <s:hidden name="offAddressId" id="offAddressId" value="%{currentAddress.offAddressId}"/>
                                    <s:hidden name="hotelAddressId" id="hotelAddressId" value="%{currentAddress.hotelAddressId}"/>
                                    <s:hidden name="otherAddressId" id="otherAddressId" value="%{currentAddress.otherAddressId}"/>
                                    <s:if test="currentAddress == null">
                                        <s:hidden name="id" value="%{id}"/>
                                    </s:if>
                                    <s:else>
                                        <s:hidden name="id" value="%{currentAddress.empId}"/>
                                    </s:else>
                                </td>
                            </tr>
                            
                            
                            <tr>
                                <%-- START HOME ADDRESS TABLE --%>
                                <td>
                                    <table width="100%" border="0" cellpadding="1" cellspacing="1">
                                        <tr>
                                            <td colspan="4" class="headerText" align="left">
                                                Home Address
                                            </td>
                                        </tr> 
                                        
                                        <tr>
                                            <td class="fieldLabel">Add.L1:</td>
                                            <td colspan="3"><s:textfield name="homeAddLine1" value="%{currentAddress.homeAddLine1}" onchange="fieldLengthValidator(this);" cssClass="inputTextBlueAddressEmp" id="homeAddLine1" size="50"/></td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabel">Add.L2:</td>
                                            <td colspan="3"><s:textfield name="homeAddLine2" value="%{currentAddress.homeAddLine2}" onchange="fieldLengthValidator(this);" cssClass="inputTextBlueAddressEmp" id="homeAddLine2" size="50"/></td>   
                                        </tr>
                                        <tr>
                                            <td class="fieldLabel">City:</td>
                                            <td><s:textfield name="homeCity" value="%{currentAddress.homeCity}" onchange="fieldLengthValidator(this);" cssClass="inputTextBlue" id="homeCity" size="25"/> </td>
                                            <td class="fieldLabel"> State:</td>
                                            <td style="padding-left:10px;"><s:select list="statesList" id="homeState" name="homeState" value="%{currentAddress.homeState}"
                                                                                     headerKey="" headerValue="--Please Select--" cssClass="inputSelect"/></td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabel">Country:</td>
                                            <td><s:select list="countryList" id="homeCountry" name="homeCountry" value="%{currentAddress.homeCountry}" cssClass="inputSelect" 
                                                          headerKey="" headerValue="--Please Select--" onchange="getHomeStates(this);"/></td>
                                            <td class="fieldLabel">Zip:</td>
                                            <td style="padding-left:10px;"><s:textfield name="homeZip" value="%{currentAddress.homeZip}" onchange="fieldLengthValidator(this);" cssClass="inputTextBlue" id="homeZip" size="15"/></td>
                                        </tr>
                                    </table>
                                </td>
                                
                                <%-- END HOME ADDRESS TABLE --%>
                                                
                                <%-- START PAYROLL ADDRESS TABLE --%>
                                <td>
                                    <table width="100%" border="0" cellpadding="1" cellspacing="1">
                                        <tr>
                                            <td colspan="4" class="headerText" align="left">
                                                Payroll Address
                                            </td>
                                        </tr> 
                                        
                                        <tr>
                                            <td class="fieldLabel">Add.L1:</td>
                                            <td colspan="3"><s:textfield name="payrollAddLine1" value="%{currentAddress.payrollAddLine1}" onchange="fieldLengthValidator(this);"  cssClass="inputTextBlueAddressEmp" id="payrollAddLine1" size="50"/></td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabel">Add.L2:</td>
                                            <td colspan="3"><s:textfield name="payrollAddLine2" value="%{currentAddress.payrollAddLine2}" onchange="fieldLengthValidator(this);"   cssClass="inputTextBlueAddressEmp" id="payrollAddLine2" size="50"/></td>   
                                        </tr>
                                        <tr>
                                            <td class="fieldLabel">City:</td>
                                            <td><s:textfield name="payrollCity" value="%{currentAddress.payrollCity}" onchange="fieldLengthValidator(this);" cssClass="inputTextBlue" id="payrollCity" size="25"/> </td>
                                            <td class="fieldLabel"> State:</td>
                                            <td style="padding-left:10px;"><s:select list="statesList" id="payrollState" name="payrollState" value="%{currentAddress.payrollState}"
                                                                                     headerKey="" headerValue="--Please Select--" cssClass="inputSelect"/></td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabel">Country:</td>
                                            <td><s:select list="countryList" id="payrollCountry" name="payrollCountry" value="%{currentAddress.payrollCountry}" cssClass="inputSelect" 
                                                          headerKey="" headerValue="--Please Select--" onchange="getPayrollStates(this);"/></td>
                                            <td class="fieldLabel">Zip:</td>
                                            <td style="padding-left:10px;"><s:textfield name="payrollZip" value="%{currentAddress.payrollZip}" onchange="fieldLengthValidator(this);" cssClass="inputTextBlue" id="payrollZip" size="15"/></td>
                                        </tr>
                                    </table>
                                </td>
                                <%-- END PAYROLL ADDRESS TABLE --%>
                            </tr>
                            
                            <tr>
                                <td colspan="2"></td>
                            </tr>
                            
                            <tr>
                                <%-- START CURRENT PROJECT ADDRESS TABLE --%>
                                <td>
                                    <table width="100%" border="0" cellpadding="1" cellspacing="1">
                                        <tr>
                                            <td colspan="4" class="headerText" align="left">
                                                Current Project Address
                                            </td>
                                        </tr> 
                                        
                                        <tr>
                                            <td class="fieldLabel">Add.L1:</td>
                                            <td colspan="3"><s:textfield name="cprojectAddLine1" value="%{currentAddress.cprojectAddLine1}" onchange="fieldLengthValidator(this);"  cssClass="inputTextBlueAddressEmp" id="cprojectAddLine1" size="50"/></td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabel">Add.L2:</td>
                                            <td colspan="3"><s:textfield name="cprojectAddLine2" value="%{currentAddress.cprojectAddLine2}" onchange="fieldLengthValidator(this);" cssClass="inputTextBlueAddressEmp" id="cprojectAddLine2" size="50"/></td>   
                                        </tr>
                                        <tr>
                                            <td class="fieldLabel">City:</td>
                                            <td><s:textfield name="cprojectCity" value="%{currentAddress.cprojectCity}" onchange="fieldLengthValidator(this);"  cssClass="inputTextBlue" id="cprojectCity" size="25"/> </td>
                                            <td class="fieldLabel"> State:</td>
                                            <td style="padding-left:10px;"><s:select list="statesList" id="cprojectState" name="cprojectState"  value="%{currentAddress.cprojectState}"
                                                                                     headerKey="" headerValue="--Please Select--" cssClass="inputSelect"/></td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabel">Country:</td>
                                            <td><s:select list="countryList" id="cprojectCountry" name="cprojectCountry" value="%{currentAddress.cprojectCountry}"
                                                          headerKey="" headerValue="--Please Select--" cssClass="inputSelect" onchange="getCprojectStates(this);"/></td>
                                            <td class="fieldLabel">Zip:</td>
                                            <td style="padding-left:10px;"><s:textfield name="cprojectZip" value="%{currentAddress.cprojectZip}" onchange="fieldLengthValidator(this);" cssClass="inputTextBlue" id="cprojectZip" size="15"/></td>
                                        </tr>
                                    </table>
                                </td>
                                <%-- END CURRENT PROJECT ADDRESS TABLE --%>
                                                
                                <%-- START HOTEL ADDRESS TABLE --%>
                                <td>
                                    <table width="100%" border="0" cellpadding="1" cellspacing="1">
                                        <tr>
                                            <td colspan="4" class="headerText" align="left">
                                                Hotel Address
                                            </td>
                                        </tr> 
                                        
                                        <tr>
                                            <td class="fieldLabel">Add.L1:</td>
                                            <td colspan="3"><s:textfield name="hotelAddLine1" value="%{currentAddress.hotelAddLine1}" onchange="fieldLengthValidator(this);" cssClass="inputTextBlueAddressEmp" id="hotelAddLine1" size="50"/></td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabel">Add.L2:</td>
                                            <td colspan="3"><s:textfield name="hotelAddLine2" value="%{currentAddress.hotelAddLine2}" onchange="fieldLengthValidator(this);"  cssClass="inputTextBlueAddressEmp" id="hotelAddLine2" size="50"/></td>   
                                        </tr>
                                        <tr>
                                            <td class="fieldLabel">City:</td>
                                            <td><s:textfield name="hotelCity" value="%{currentAddress.hotelCity}" onchange="fieldLengthValidator(this);" cssClass="inputTextBlue" id="hotelCity" size="25"/> </td>
                                            <td class="fieldLabel"> State:</td>
                                            <td style="padding-left:10px;"><s:select list="statesList" id="hotelState" name="hotelState" value="%{currentAddress.hotelState}" 
                                                                                     headerKey="" headerValue="--Please Select--" cssClass="inputSelect"/></td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabel">Country:</td>
                                            <td><s:select list="countryList" id="hotelCountry" name="hotelCountry" value="%{currentAddress.hotelCountry}" cssClass="inputSelect" 
                                                          headerKey="" headerValue="--Please Select--" onchange="getHotelStates(this);"/></td>
                                            <td class="fieldLabel">Zip:</td>
                                            <td style="padding-left:10px;"><s:textfield name="hotelZip" value="%{currentAddress.hotelZip}" onchange="fieldLengthValidator(this);" cssClass="inputTextBlue" id="hotelZip" size="15"/></td>
                                        </tr>
                                    </table>
                                </td>
                                <%-- END HOTEL ADDRESS TABLE --%>
                            </tr>
                            
                            <tr>
                                <td colspan="2"></td>
                            </tr>
                            
                            <tr>
                                <%-- START OFFSHORE ADDRESS TABLE --%>
                                <td>
                                    <table width="100%" border="0" cellpadding="1" cellspacing="1">
                                        <tr>
                                            <td colspan="4" class="headerText" align="left">
                                                OffShore Address
                                            </td>
                                        </tr> 
                                        
                                        <tr>
                                            <td class="fieldLabel">Add.L1:</td>
                                            <td colspan="3"><s:textfield name="offShoreAddLine1" value="%{currentAddress.offShoreAddLine1}" onchange="fieldLengthValidator(this);"  cssClass="inputTextBlueAddressEmp" id="offShoreAddLine1" size="50"/></td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabel">Add.L2:</td>
                                            <td colspan="3"><s:textfield name="offShoreAddLine2" value="%{currentAddress.offShoreAddLine2}" onchange="fieldLengthValidator(this);"  cssClass="inputTextBlueAddressEmp" id="offShoreAddLine2" size="50"/></td>   
                                        </tr>
                                        <tr>
                                            <td class="fieldLabel">City:</td>
                                            <td><s:textfield name="offShoreCity" value="%{currentAddress.offShoreCity}" onchange="fieldLengthValidator(this);"  cssClass="inputTextBlue" id="offShoreCity" size="25"/> </td>
                                            <td class="fieldLabel"> State:</td>
                                            <td style="padding-left:10px;"><s:select list="statesList" name="offShoreState" id="offShoreState" value="%{currentAddress.offShoreState}"
                                                                                     headerKey="" headerValue="--Please Select--" cssClass="inputSelect"/></td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabel">Country:</td>
                                            <td><s:select list="countryList" name="offShoreCountry" id="offShoreCountry" value="%{currentAddress.offShoreCountry}"
                                                          headerKey="" headerValue="--Please Select--" cssClass="inputSelect" onchange="getOffShoreStates(this);"/></td>
                                            <td class="fieldLabel">Zip:</td>
                                            <td style="padding-left:10px;"><s:textfield name="offShoreZip" value="%{currentAddress.offShoreZip}" onchange="fieldLengthValidator(this);" cssClass="inputTextBlue" id="offShoreZip" size="15"/></td>
                                        </tr>
                                    </table>
                                </td>
                                
                                <%-- END OFFSHORE ADDRESS TABLE --%>
                                                
                                <%-- START OTHER ADDRESS TABLE --%>
                                <td>
                                    <table width="100%" border="0" cellpadding="1" cellspacing="1" >
                                        
                                        <tr>
                                            <td colspan="4" class="headerText" align="left">
                                                Other Address
                                            </td>
                                        </tr> 
                                        
                                        <tr>
                                            <td class="fieldLabel">Add.L1:</td>
                                            <td colspan="3"><s:textfield name="otherAddLine1" value="%{currentAddress.otherAddLine1}" onchange="fieldLengthValidator(this);"  cssClass="inputTextBlueAddressEmp" id="otherAddLine1" size="50"/></td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabel">Add.L2:</td>
                                            <td colspan="3"><s:textfield name="otherAddLine2" value="%{currentAddress.otherAddLine2}" onchange="fieldLengthValidator(this);"  cssClass="inputTextBlueAddressEmp" id="otherAddLine2" size="50"/></td>   
                                        </tr>
                                        <tr>
                                            <td class="fieldLabel">City:</td>
                                            <td><s:textfield name="otherCity" value="%{currentAddress.otherCity}" onchange="fieldLengthValidator(this);" cssClass="inputTextBlue" id="otherCity" size="25"/> </td>
                                            <td class="fieldLabel"> State:</td>
                                            <td style="padding-left:10px;"><s:select list="statesList" id="otherState" name="otherState" value="%{currentAddress.otherState}" 
                                                                                     headerKey="" headerValue="--Please Select--" cssClass="inputSelect"/></td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabel">Country:</td>
                                            <td><s:select list="countryList" id="otherCountry" name="otherCountry" value="%{currentAddress.otherCountry}" cssClass="inputSelect" 
                                                          headerKey="" headerValue="--Please Select--" onchange="getOtherStates(this);"/></td>
                                            <td class="fieldLabel">Zip:</td>
                                            <td style="padding-left:10px;"><s:textfield name="otherZip" value="%{currentAddress.otherZip}" onchange="fieldLengthValidator(this);" cssClass="inputTextBlue" id="otherZip" size="15"/></td>
                                        </tr>
                                        
                                    </table>
                                </td>
                                <%-- END OTHER ADDRESS TABLE --%>
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
</body>
</html>