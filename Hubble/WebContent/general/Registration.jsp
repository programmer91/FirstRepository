<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<html>
    <head>
        <title>Hubble Organization Portal :: User Registration</title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/javascript" language="javascript" src="../includes/scroller/scroll.js"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js?version=1.2"/>"></script>  
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/RegistrationClientValidation.js"/>"></script> --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StringUtility.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
          <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
    
    </head>
   <!-- <body class="bodyGeneral" oncontextmenu="return false;" onload="DrawCaptcha();"> -->
    <body class="bodyGeneral" oncontextmenu="return false;">
        
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
                            <!--//START USER TIPS : Coloumn for showing tips related to registration process-->
                            <td width="150px" class="topBorder">
                                <SCRIPT LANGUAGE="JavaScript">Tscroll_init (0)</SCRIPT>
                            </td>
                            <!--//END USER TIPS : Coloumn for showing tips related to registration process-->
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td class="leftTopBorder" valign="top" width="850px">
                                <!--//START TABBED PANNEL : -->
                                <s:form action="registrationSubmit" name="registrationForm" id="registrationForm" theme="simple" method="post" onsubmit="return checkRegistrationForm();">
                                    <!--//START TABBED PANNEL : -->
                                    <sx:tabbedpanel id="registrationPannel" cssStyle="width: 850px; height: 400px;padding-left:10px;padding-top:5px;" doLayout="true" >
                                        
                                        <!--//START TAB : -->
                                        <sx:div id="personalDetailsTab" label="Register yourself">
                                            <table cellpadding="2" cellspacing="2" border="0" width="100%">
                                                <tr class="headerText">
                                                    <td colspan="6">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                            <tr align="right" class="headerText">
                                                                <td align="left">Genaral Details</td>
                                                                <td align="right">
                                                                    <s:submit name="submit" value="Register" cssClass="buttonBg" />&nbsp;
                                                                  <%--  <s:reset name="reset" value="Reset" cssClass="buttonBg"/> --%>
                                                                     <input type="button" class="buttonBg" onclick="resetForm();" value="Reset" />
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel" width="100px" align="right">First Name :<font color="red" style="bold">&curren;</font></td>                           
                                                    
                                                    <td><s:textfield name="firstName" id="firstName" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);changeCase(this);"/></td>      
                                                    
                                                    <td class="fieldLabel" width="100px" align="right">Last Name :<font color="red" style="bold">&curren;</font></td>
                                                    
                                                    <td><s:textfield name="lastName" id="lastName" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);changeCase(this);"/></td>
                                                    
                                                    <td class="fieldLabel" width="100px" align="right">Middle Name :</td>                           
                                                    
                                                    <td><s:textfield name="middleName" id="midName" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);changeCase(this);"/></td>    
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel" width="100px" align="right">Gender :<font color="red" style="bold">&curren;</font></td>                           
                                                    
                                                    <td><s:select label="Select Gender" 
                                                                      name="gender" id="gender"
                                                                      headerKey=""
                                                                      headerValue="-- Please Select --"
                                                                      list="genderList" cssClass="inputSelect"/>
                                                    </td>                                                      
                                                    
                                                    <td class="fieldLabel" width="100px" align="right">Marital Status :<font color="red" style="bold">&curren;</font></td>
                                                    
                                                    <td><s:select label="Select Marital Status" 
                                                                      name="maritalStatus" 
                                                                      headerKey="" id="maritalStatus"
                                                                      headerValue="-- Please Select --"
                                                                  list="maritalStatusList"  cssClass="inputSelect"/></td>
                                                    
                                                    <td class="fieldLabel" width="100px" align="right">SSN/PAN :</td>                           
                                                    
                                                    <td><s:textfield name="ssn" id="ssn" cssClass="inputTextBlue" size="20" onchange="fieldLengthValidator(this);"/></td>             
                                                </tr>
                                                
                                                <tr>
                                                    <td class="fieldLabel" width="100px" align="right">Country :<font color="red" style="bold">&curren;</font></td>                           
                                                    
                                                    <td><s:select label="Select Country" 
                                                                      name="country" 
                                                                      headerKey="" id="country"
                                                                      headerValue="-- Please Select --"
                                                                  list="coutryList" cssClass="inputSelect"/></td>    
                                                                  <td class="fieldLabelLeft" width="100px" >DOB<font size="1" color="red">(mm/dd/yyyy)</font> :</td>                           
                                                    
                                                    <td><s:textfield  name="birthDate" cssClass="inputTextBlueSmall" value="" onChange="return checkDates(this)"/><a href="javascript:cal1.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                    </td>
                                                    
                                                    <td class="fieldLabel" width="100px" align="right">Official DOB :</td>
                                                    
                                                    <td><s:textfield name="offBirthDate" cssClass="inputTextBlueSmall" value=""  onChange="return checkDates(this)" /><a href="javascript:cal2.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                    </td>
                                                    
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel" width="100px" align="right">Reffered By :</td>                           
                                                    
                                                    <td colspan="5"><s:textfield name="refferedBy" id="refferedBy" cssClass="inputTextBlueReg" onchange="fieldLengthValidator(this);changeCase(this);"/></td> 
                                                </tr>
                                                <tr>
                                                    <td colspan="6" class="headerText" align="left">
                                                        Contact Details
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel" width="100px" align="right">Work-Phone  :<font color="red" style="bold">&curren;</font>
                                                    </td>                           
                                                    
                                                    <td><s:textfield name="workPhoneNo" id="workPhoneNo" cssClass="inputTextBlue" onchange="return formatPhone(this);" onblur="return validatenumber(this);"/></td>  
                                                    
                                                    <td class="fieldLabel" width="100px" align="right">Mobile  :<font color="red" style="bold">&curren;</font>
                                                    </td>                           
                                                    
                                                    <td><s:textfield name="cellPhoneNo" cssClass="inputTextBlue" id="cellPhoneNo" onchange="return formatPhone(this);" onblur="return validatenumber(this);"/></td>  
                                                    
                                                    <td class="fieldLabel" width="100px" align="right">Home-Phone  :</td>                           
                                                    
                                                    <td><s:textfield name="homePhoneNo" cssClass="inputTextBlue"  onchange="return formatPhone(this);" onblur="return validatenumber(this);"/></td>  
                                                </tr>
                                                
                                                <tr>
                                                    <td class="fieldLabel" width="100px" align="right">Alt-Phone  :</td>                           
                                                    
                                                    <td><s:textfield name="altPhoneNo" cssClass="inputTextBlue" onchange="return formatPhone(this);" onblur="return validatenumber(this);"/></td>   
                                                    
                                                    <td class="fieldLabel" width="100px" align="right">Hotel-Phone  :</td>                           
                                                    
                                                    <td><s:textfield name="hotelPhoneNo" cssClass="inputTextBlue" onchange="return formatPhone(this);" onblur="return validatenumber(this);"/></td>   
                                                    
                                                    <td class="fieldLabel" width="100px" align="right">India-Phone :</td>
                                                    
                                                    <td><s:textfield name="indiaPhoneNo" cssClass="inputTextBlue" onchange="return formatPhone(this);" onblur="return validatenumber(this);"/></td>
                                                    
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel" width="100px" align="right">Fax :</td>                           
                                                    
                                                    <td><s:textfield name="faxNo" cssClass="inputTextBlue"  onchange="return validatenumber(this);"/></td>   
                                                    
                                                    <td class="fieldLabel" width="100px" align="right">Office Email :<font color="red" style="bold">&curren;</font></td>
                                                    
                                                    <td colspan="3"><s:textfield name="officeEmail" id="officeEmail" cssClass="inputTextBlueReg" onchange="return valueCheck(this);allSmalls(this);"/></td>   
                                                </tr>
                                                
                                                <tr>
                                                    <td class="fieldLabel" width="100px" align="right">Other Email :</td>
                                                    
                                                    <td colspan="2"><s:textfield name="otherEmail" cssClass="inputTextBlueReg" onchange="return checkEmail(this);allSmalls(this);"/></td>   
                                                    
                                                    <td class="fieldLabel" width="100px" align="right">Personal Email :</td>
                                                    
                                                    <td colspan="2"><s:textfield name="personalEmail" cssClass="inputTextBlueReg"  onchange="return checkEmail(this);allSmalls(this);"/></td>
                                                </tr>
                                                
                                              <%--  <tr>
                                                    <td class="headerText" align="left" colspan="6">
                                                        Security Details 
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel" width="100px" align="right">Pref Question :<font color="red" style="bold">&curren;</font></td> 
                                                    <td colspan="5"><s:select cssClass="inputSelectExtraLarge" label="Select Country" 
                                                                                  name="prefQuestion" 
                                                                                  headerKey="" id="prefQuestion"
                                                                                  headerValue="-- Please Select --"
                                                                              list="prefferedQuestionsMap"/> </td>
                                                </tr>
                                                
                                                <tr>
                                                    <td class="fieldLabel" width="100px" align="right">Pref Answer :<font color="red" style="bold">&curren;</font></td>
                                                    
                                                    <td colspan="5"><s:textfield id="prefAnswer" name="prefAnswer" cssClass="inputTextBlueReg"/></td>
                                                    
                                                </tr> --%>
                                               <tr>
                                                
                                                <td class="fieldLabel"> Re-Enter Captcha &nbsp;: </td> 
                                               
       <td class="fieldLabel">
           
          <s:textfield readonly="true" id="txtCaptcha" cssClass="inputTextBlue" style="background-image:url(../includes/images/captcha.jpg); text-align:center;border: none;font-weight:bold; font-family:Modern"/>  
          
       
       </td>
       
        <td> <button type="button" style="background-image:url(../includes/images/refresh1.jpg);background-size: 30px;width:30px;height: 30px;border: none"  onclick="DrawCaptcha();" /> </td>
   
                                                    </tr>
                                                    <tr>
   
    <td class="fieldLabel"></td> 
   
       <td class="fieldLabel"><s:textfield id="txtInput" name="txtInput" cssClass="inputTextBlue"/></td>
       <td>&nbsp;</td>
   
</tr>

                                                
                                                <tr>
                                                    <td align="center" colspan="6">
                                                        <% if(request.getAttribute("resultMessage") != null){
                                                            out.println(request.getAttribute("resultMessage"));
                                                        }%>
                                                        
                                                    </td>
                                                </tr>
                                            </table>
                                            <script type="text/JavaScript">
                                                var cal1 = new CalendarTime(document.forms['registrationForm'].elements['birthDate']);
                                                cal1.year_scroll = true;
                                                cal1.time_comp = false;
                                                           
                                                var cal2 = new CalendarTime(document.forms['registrationForm'].elements['offBirthDate']);
                                                cal2.year_scroll = true;
                                                cal2.time_comp = false;
                                            </script>
                                        </sx:div >
                                        <!--//END TAB : -->
                                    
                                    </sx:tabbedpanel>
                                    <!--//END TABBED PANNEL : -->
                                </s:form>
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
        <script type="text/javascript">
		$(window).load(function(){
		DrawCaptcha();

		});
		</script>
        
    </body>
</html>