<%@ page contentType="text/html; charset=UTF-8"%>
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
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>  
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StringUtility.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/cre/EmpStandardClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
        <script type="text/JavaScript">
            function scalesize(val){
                if(val.value >10){
                    alert("scale out of 10 for given skill!")
                }   
            }
            
            function getLocation(){
                var test = document.getElementsByName("attendingInterviewAt");
                var sizes = test.length;
                var loc = "";
                for (i=0; i < sizes; i++) {
                    if (test[i].checked==true) {
                        // alert(test[i].value + ' you got a value'); 
                        loc = test[i].value;
                        init(loc);
                    }
                }
            }
            function init(val){
                  document.getElementById("jfairLocation").value="";
                    document.getElementById("recLocation").value="";
                    document.getElementById("campusLocation").value="0";
                if(val==1){
                    document.getElementById("loc").innerHTML = "Location :";
                    document.getElementById("mcampusF").style.display="block";
                    document.getElementById("clzF").style.display="none";
                    document.getElementById("JFF").style.display="none";
                    
                  
                    
                }else if(val==2){
                    document.getElementById("mcampusF").style.display="none";
                    document.getElementById("loc").innerHTML = "College Name :";
                    document.getElementById("clzF").style.display="block";
                    document.getElementById("JFF").style.display="none";
                }else{
                    document.getElementById("mcampusF").style.display="none";
                    document.getElementById("clzF").style.display="none";
                    document.getElementById("loc").innerHTML = "Job Fair Name/Location :";
                    document.getElementById("JFF").style.display="block";
                }
            }
        </script>
<script>
            function fileValidation() {
              //  alert("hii");
                var imagePath = document.registrationForm.imagePath.value;
               // alert("imagePath-->"+imagePath);
                if(imagePath != null && (imagePath !="")) {
                    //document.registrationForm.imagePath.focus();
                    
                    
                       var size = document.getElementById('imagePath').files[0].size;
                    //alert("size-->"+size);
                   // alert("size in mb-->"+(parseInt(size)/1000000));
                 // if((parseInt(size)/1000000)<2) {
                 if(parseInt(size)<200000) {
                    return (true);
                }else {
                    document.registrationForm.imagePath.value = null;
                    alert("File size must be less than 200 KB");
                    return (false);
                }
                }
                return (false);
            };
        </script> 
		
    </head>
   <!-- <body class="bodyGeneral" onload="init(1);" oncontextmenu="return false;"> -->
    <body class="bodyGeneral" oncontextmenu="return false;">

        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTableLogin" align="center" cellpadding="0" cellspacing="0">

            <!--//START HEADER : Record for Header Background and Mirage Logo-->
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/CreHeader.jsp"/>
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
                             <%--   <s:form action="creRegistrationSubmit" name="registrationForm" theme="simple" method="post" onsubmit="return checkCreRegistrationForm();"> --%>
                             <s:form action="creRegistrationSubmit" name="registrationForm" id="registrationForm" theme="simple" onsubmit="return checkCreRegistrationForm();" enctype="multipart/form-data" >
                                    <!--//START TABBED PANNEL : -->
                                    <sx:tabbedpanel id="registrationPannel" cssStyle="width: 990px; height: 530px;padding-left:10px;padding-top:5px;" doLayout="true" >

                                        <!--//START TAB : -->
                                        <sx:div id="personalDetailsTab" label="Register yourself" cssStyle="overflow: auto;">
                                            <table cellpadding="2" cellspacing="2" border="0" width="100%">

                                                <tr class="headerText">
                                                    <td colspan="6">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">


                                                            <tr align="right" class="headerText">
                                                                <td align="left">Interview Information</td>
                                                                <td align="right">
                                                                    <% if (request.getAttribute("resultMessage") != null) {
                                                                             out.println(request.getAttribute("resultMessage"));
                                                                         }%>
                                                                    <s:submit name="submit" tabindex="76" value="Register" cssClass="buttonBg"/>&nbsp;
                                                                    <s:reset name="reset" value="Reset" cssClass="buttonBg"/>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel" width="100px" align="right" colspan="1">Attending&nbsp;Interview&nbsp;At&nbsp;:</td>
                                                    <td colspan="3" width="100%" align="right" class="fieldLabelLeft">
                                                        <s:radio  name="attendingInterviewAt" id="attendingInterviewAt" tabindex="1" onchange="getLocation();"  list="#@java.util.LinkedHashMap@{'1':'Miracle Campus','2':'Campus Recruitment','3':'Job Fair'}" value="1"></s:radio>
                                                    </td>


                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel" width="100px" align="right">       
                                                        <span id="loc">Location:</span><font color="red" style="bold">&curren;</font>
                                                    </td>
                                                    <td colspan="2">
                                                        <span id="mcampusF">
                                                            <s:select name="campusLocation" headerKey="0" headerValue="Select campus" tabindex="2" id="campusLocation"  cssClass="inputSelect" list="#@java.util.LinkedHashMap@{'1':'Miracle City','2':'Miracle Heights','3':'Miracle Admin. Office'}" theme="simple"></s:select>
                                                            </span>
                                                            <span id="clzF">
                                                           <%-- <s:textfield name="recLocation" tabindex="2" id="recLocation" cssClass="inputTextBlueReg" theme="simple"/> --%>
                                                            <s:select name="recLocation" id="recLocation" cssClass="inputSelect" headerKey="" headerValue="--Please Select--" list="recLocationList" ></s:select> 
                                                            </span>
                                                        <span id="JFF"><s:textfield name="jfairLocation" tabindex="2" id="jfairLocation" cssClass="inputTextBlueReg" theme="simple"/></span>

                                                    </td>
                                                    <td class="fieldLabel" width="100px" align="right">Post&nbsp;Applied :<font color="red" style="bold">&curren;</font></td>
                                                    <td>
                                                       <%-- <s:select name="category" tabindex="2" id="category"  cssClass="inputSelect" headerKey="-1" headerValue="Select Category" list="#@java.util.LinkedHashMap@{'1':'Software Trainee','2':'Sr.Developer','3':'Sales','4':'IT Recruitment','5':'Operations','6':'Network Engineer','7':'Trainer','8':'Civil','9':'MES','10':'Other','11':'US IT Sales','12':'HR','13':'Finance Executive'}" theme="simple"></s:select> --%>
                                                        <s:select name="category" tabindex="2" id="category"  cssClass="inputSelect" headerKey="-1" headerValue="Select Category" list="postAppliedMap" theme="simple"></s:select>
                                                        </td>  
                                                    </tr>
                                                    <tr>
                                                        <td colspan="6" class="headerText" align="left">
                                                            Genaral Details
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel" width="100px" align="right">First Name :<font color="red" style="bold">&curren;</font></td>                           

                                                        <td><s:textfield name="firstName" id="firstName" tabindex="3"  cssClass="inputTextBlue" onchange="fieldLengthValidator(this);changeCase(this);"/></td>      

                                                    <td class="fieldLabel" width="100px" align="right">Last Name :<font color="red" style="bold">&curren;</font></td>

                                                    <td><s:textfield name="lastName" id="lastName" tabindex="4"  cssClass="inputTextBlue" onchange="fieldLengthValidator(this);changeCase(this);"/></td>

                                                    <td class="fieldLabel" width="100px" align="right">Middle Name :</td>                           

                                                    <td><s:textfield name="middleName" id="midName" tabindex="5"  cssClass="inputTextBlue" onchange="fieldLengthValidator(this);changeCase(this);"/></td>    
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel" width="100px" align="right">Gender :<font color="red" style="bold">&curren;</font></td>                           

                                                    <td><s:select label="Select Gender" 
                                                              name="gender" id="gender"
                                                              headerKey=""
                                                              headerValue="-- Please Select --" 
                                                              tabindex="6"
                                                              list="genderList" cssClass="inputSelect"/>
                                                    </td>                                                      

                                                    <td class="fieldLabel" width="100px" align="right">Marital Status :</td>

                                                    <td><s:select label="Select Marital Status" 
                                                              name="maritalStatus" 
                                                              headerKey="" id="maritalStatus"
                                                              headerValue="-- Please Select --"
                                                              tabindex="7"
                                                              list="maritalStatusList"  cssClass="inputSelect"/></td>

                                                    <td class="fieldLabel" width="100px" align="right">SSN/PAN :</td>                           

                                                    <td><s:textfield name="pan" id="pan"  tabindex="8" cssClass="inputTextBlue" size="20" onchange="fieldLengthValidator(this);"/></td>             
                                                </tr>

                                                <tr>

                                                    <td class="fieldLabel" width="100px" >DOB<font size="1" color="red">(mm/dd/yyyy)</font> :</td>                           

                                                    <td><s:textfield  name="birthDate"  tabindex="9" cssClass="inputTextBlueSmall" id="birthDate"  onChange="return checkDates(this)"/>
                                                        <%-- <a href="javascript:cal1.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>  --%>
                                                    </td>



                                                </tr>
                                                
                                                <tr>
                                                    <td class="fieldLabel" width="100px" align="right">Father&nbsp;Name&nbsp;:</td>                           

                                                    <td><s:textfield name="fatherName" tabindex="10"  id="creFatherName" cssClass="inputTextBlue" size="20" onchange="fieldLengthValidator(this);"/></td>             
                                                    <td class="fieldLabel" width="100px" align="right">Father&nbsp;Occupation&nbsp;:</td>                           

                                                    <td><s:textfield name="fatherOccupation" tabindex="11"  id="fatherOccupation" cssClass="inputTextBlue" size="20" onchange="fieldLengthValidator(this);"/></td>             

                                                    <td class="fieldLabel" width="100px" align="right">Father&nbsp;Income&nbsp;:</td>
                                                    <td><s:select name="fatherIncome"  tabindex="12" id="fatherIncome" cssClass="inputSelect" headerKey="-1" headerValue="--Select Category--" list="{'Below 1 Lakh','> 1 Lakh','2Lakh to 3 Lakh','3Lakh to 5Lakh','5Lakh to 7Lakh','7Lakh to 9Lakh','Above 10 Lakh'}"></s:select></td>

                                                    </tr>
                                                    <tr >
                                                        <td class="fieldLabel" width="100px" align="right" rowspan="2">Address :</td>   
                                                        <td rowspan="2">
                                                        <s:textarea cols="17" rows="3" tabindex="13"  name="address" id="address" cssClass="inputTextarea"  onchange="fieldLengthValidator(this);"/>
                                                    </td>
                                                    <td class="fieldLabel" width="100px" align="right">City :</td>  
                                                    <td><s:textfield name="city" id="city" tabindex="14" cssClass="inputTextBlue" size="20" onchange="fieldLengthValidator(this);"/></td>             
                                                    <td class="fieldLabel" width="100px" align="right">Pin :</td>                           

                                                    <td><s:textfield name="pin" id="pin" tabindex="15" cssClass="inputTextBlue" size="20" onchange="fieldLengthValidator(this);"/></td>             
                                                </tr>  
                                                <tr>

                                                    <td class="fieldLabel" width="100px" align="right">State :</td>                           

                                                    <td><s:textfield name="state" tabindex="16" id="state" cssClass="inputTextBlue" size="20" onchange="fieldLengthValidator(this);"/></td>             
                                                </tr>
                                                <tr>

                                                </tr>

<tr>
                                   <td colspan="6" class="headerText" align="left">
                                                Photograph
                                            </td>
                                        </tr>
  <tr>
                                                    <td colspan="6">
                                                        <input type="file" name="imagePath" id="imagePath" class="inputTextBlueLarge" onchange="fileValidation();"/> 
                                                <%--     <input type="file" name="imagePath" id="imagePath" class="inputTextBlueLarge" onchange="fileValidation(document.imageForm.imagePath.value);"/>     --%>
                                                 <%--   <s:file name="imagePath" id="imagePath" cssClass="inputTextBlueLarge" onchange="fileValidation(document.registrationForm.imagePath.value);"/> --%>
                                                    </td>
                                                </tr>
                                            <%--    <tr>
                                                    <td style="padding-left:15px;" colspan="6" align="center"><input type="submit" value="Upload" class="buttonBg"/></td>
                                                </tr> --%>
                                                <tr class="fieldLabel">
                                                    <td colspan="6" align="left" >
                                                        *Image size must be less than 200 KB.
                                                    </td>
                                                </tr>
												
                                                <tr>
                                                    <td colspan="6" class="headerText" align="left">
                                                        Contact Details
                                                    </td>
                                                </tr>
                                                <tr>

                                                    <td class="fieldLabel" width="100px" align="right">Mobile  :<font color="red" style="bold">&curren;</font>
                                                    </td>                           

                                                    <td><s:textfield name="cellPhoneNo" tabindex="17" cssClass="inputTextBlue" id="cellPhoneNo" onchange="return formatPhone(this);" onblur="return validatenumber(this);"/></td>  


                                                    <td class="fieldLabel" width="100px" align="right">Alt-Phone  :</td>                           

                                                    <td><s:textfield name="altPhoneNo" tabindex="18" cssClass="inputTextBlue" onchange="return formatPhone(this);" onblur="return validatenumber(this);"/></td>   
                                                </tr>


                                    </tr>


                                    <tr>
                                        <td class="fieldLabel" width="100px" align="right">Personal Email :<font color="red" style="bold">&curren;</font></td>

                                        <td colspan="2"><s:textfield name="personalEmail" tabindex="19" cssClass="inputTextBlueReg" id="personalEmail" onchange="return checkEmail(this);allSmalls(this);"/></td>

                                        <td class="fieldLabel" width="100px" align="right">Other Email :</td>

                                        <td colspan="2"><s:textfield name="otherEmail" tabindex="20" cssClass="inputTextBlueReg" onchange="return checkEmail(this);allSmalls(this);"/></td>   

                                    </tr>
                                    <tr>
                                        <td colspan="6" class="headerText" align="left">
                                            Academic Information
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="fieldLabelLeft" width="100px" align="right">Academic Qualification</td>
                                        <td class="fieldLabelLeft" width="100px" align="right">Stream</td>
                                        <td class="fieldLabelLeft" width="100px" align="right">College/University</td>
                                        <td class="fieldLabelLeft" width="100px" align="right">Medium Of Education</td>
                                        <td class="fieldLabelLeft" width="100px" align="right">Yr.Of Pass</td>
                                        <td class="fieldLabelLeft" width="100px" align="right">Percentage</td>
                                    </tr>
                                    <tr>
                                        <td >
                                            <s:select name="qualification"  tabindex="21" id="qualification" cssClass="inputSelect" headerKey="-1" headerValue="--Please Select--" list="{'SSC','CBSC','ICSE','Other'}"></s:select>
                                            </td>
                                            <td ><s:textfield name="education"  maxlength="15"  tabindex="22" id="" cssClass="inputTextBlue3" onkeypress="return isNameAlpha(event);" disabled="true"></s:textfield>
                                            <%-- <s:hidden name="education" maxlength="15" value="NoStreamForSSC" tabindex="" id="education" onkeypress="return isNameAlpha(event);" readonly="false"></s:hidden>--%></td>
                                        <td ><s:textfield name="college"  maxlength="40"  tabindex="23" id="college" cssClass="inputTextBlue" onkeypress="return isNameAlpha(event);" ></s:textfield></td>
                                            <td >
                                            <s:select name="medium" tabindex="24"  id="medium" cssClass="inputTextBlue" headerKey="-1" headerValue="--Please Select--" list="{'English','Hindi','Telugu','Other'}"></s:select>

                                            </td>
                                            <td ><s:textfield name="yearOfPass"  maxlength="4"  tabindex="25" id="yearOfPass" cssClass="inputTextBlue2" onkeypress="return isNumberKey(event);"></s:textfield></td>
                                        <td ><s:textfield name="percentage"  maxlength="5"  tabindex="26" id="percentage" cssClass="inputTextBlue2" onchange="validatePercent1(this);"></s:textfield><span class="fieldLabel">%</span></td>

                                        </tr>
                                        <tr>
                                            <td >
                                            <s:select name="ipeCategory"  tabindex="27" id="ipeCategory" cssClass="inputSelect" headerKey="-1" headerValue="--Select Category--" list="{'Intermediate','Diploma','Other'}"></s:select>
                                            </td>
                                            <td ><s:textfield name="ipeStream"  maxlength="15"  tabindex="28" id="ipeStream" cssClass="inputTextBlue3" onkeypress="return isNameAlpha(event);" ></s:textfield>

                                            <td ><s:textfield name="ipeCollege"  maxlength="40"  tabindex="29" id="ipeCollege" cssClass="inputTextBlue" onkeypress="return isNameAlpha(event);" ></s:textfield></td>
                                            <td >
                                            <s:select name="ipeMedium"  tabindex="30" id="ipeMedium" cssClass="inputTextBlue" headerKey="-1" headerValue="--Please Select--" list="{'English','Hindi','Telugu','Other'}"></s:select>

                                            </td>
                                            <td ><s:textfield name="ipeYearOfPass"  maxlength="4"  tabindex="31" id="ipeYearOfPass" cssClass="inputTextBlue2" onkeypress="return isNumberKey(event);"></s:textfield></td>
                                        <td ><s:textfield name="ipePercentage"  maxlength="5"  tabindex="32" id="ipePercentage" cssClass="inputTextBlue2" onchange="validatePercent1(this);"></s:textfield><span class="fieldLabel">%</span></td>
                                        </tr>
                                        <tr>
                                            <td >
                                            <s:select name="degreeQual"  tabindex="33" id="degreeQual" cssClass="inputSelect" headerKey="-1" headerValue="--Select Graduation--" list="{'B.Tech','B.E','B.Sc','B.Com','B.A','BBM','Other'}" onchange="getDegreeStream(this.form, this.value);"></s:select>
                                            </td>
                                            <td >
                                            <select name="degreeBranch" id="degreeBranch" class="inputSelect" ><option value="">--Please Select--</option></select>
                                              <%--  <s:textfield name="degreeBranch"  maxlength="15" value="" tabindex="34" id="degreeBranch" cssClass="inputTextBlue3" onkeypress="return isNameAlpha(event);" ></s:textfield> --%>

                                            <td ><s:textfield name="degreeCollege"  maxlength="40" value="" tabindex="35" id="degreeCollege" cssClass="inputTextBlue" onkeypress="return isNameAlpha(event);" ></s:textfield></td>
                                            <td >
                                            <s:select name="degreeMedium"  tabindex="36" id="degreeMedium" cssClass="inputTextBlue" headerKey="-1" headerValue="--Please Select--" list="{'English','Hindi','Telugu','Other'}"></s:select>

                                            </td>
                                            <td ><s:textfield name="degreeYearOfPass"  maxlength="4"   tabindex="37" id="degreeYearOfPass" cssClass="inputTextBlue2" onkeypress="return isNumberKey(event);"></s:textfield></td>
                                        <td ><s:textfield name="degreePercentage"  maxlength="5"   tabindex="38" id="degreePercentage" cssClass="inputTextBlue2" onchange="validatePercent1(this);"></s:textfield><span class="fieldLabel">%</span></td>
                                        </tr>
                                        <tr>
                                            <td >
                                            <s:select name="pgQual" tabindex="39"  id="pgQual" cssClass="inputSelect" headerKey="-1" headerValue="--Select PG--" list="{'MCA','MBA','M.Tech','M.Com','M.Sc','MS','Other'}" onchange="getPgStream(this.form, this.value);"></s:select>
                                            </td>
                                            <td >
                                            <%-- <s:textfield name="pgStream"  maxlength="15"   tabindex="40" id="pgStream" cssClass="inputTextBlue3" onkeypress="return isNameAlpha(event);" ></s:textfield> --%>
                                            <select name="pgStream" id="pgStream" class="inputSelect" ><option value="">--Please Select--</option></select>

                                            <td ><s:textfield name="pgCollege"  maxlength="40"   tabindex="41" id="pgCollege" cssClass="inputTextBlue" onkeypress="return isNameAlpha(event);" ></s:textfield></td>
                                            <td >
                                            <s:select name="pgMedium" tabindex="42"  id="pgMedium" cssClass="inputTextBlue" headerKey="-1" headerValue="--Please Select--" list="{'English','Hindi','Telugu','Other'}"></s:select>

                                            </td>
                                            <td ><s:textfield name="pgYearOfPass"  maxlength="4"  tabindex="43" id="pgYearOfPass" cssClass="inputTextBlue2" onkeypress="return isNumberKey(event);"></s:textfield></td>
                                        <td ><s:textfield name="pgPercentage"  maxlength="5"  tabindex="44" id="pgPercentage" cssClass="inputTextBlue2" onchange="validatePercent1(this);"></s:textfield><span class="fieldLabel">%</span></td>
                                        </tr>
                                        <tr>
                                            <td colspan="6" class="headerText" align="left">
                                                Additional Information
                                            </td>
                                        </tr>
                                        <tr>       
                                            <td class="fieldLabelLeft" width="100px" >Additional Information :</td>    

                                            <td colspan="4">
                                            <s:textarea cols="72" rows="3" name="addInfo" id="addInfo" cssClass="inputTextarea" tabindex="45" onchange="fieldLengthValidator(this);"/>
                                        </td>
                                    </tr>    

                                    <tr>
                                        <td colspan="6" class="headerText" align="left">
                                            Skill Set
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="fieldLabel" width="100px" align="right">**Scale out of 10</td>
                                    </tr>
                                    <tr>
                                        <td class="fieldLabelLeft" width="100px" align="left">Skill</td>
                                        <td class="fieldLabelLeft" width="100px" align="right">Scale</td>
                                        <td class="fieldLabelLeft" width="100px" align="right">Skill</td>
                                        <td class="fieldLabelLeft" width="100px" align="right">Scale</td>
                                        <td class="fieldLabelLeft" width="100px" align="right">Skill</td>
                                        <td class="fieldLabelLeft" width="100px" align="right">Scale</td>
                                    </tr>
                                    <tr>
                                        <td ><s:textfield name="skill1"    tabindex="46" id="skill1" cssClass="inputTextBlue"  onchange="fieldLengthValidator(this);"  ></s:textfield></td>
                                        <td ><s:textfield name="scale1" size="2"   tabindex="47" id="scale1" cssClass="inputTextBlue2" onkeypress="return isNumberKey(event);" onchange="return scalesize(this);"></s:textfield></td>
                                        <td ><s:textfield name="skill2"  onfocus="javascript:checkScale();"  tabindex="48" id="skill2" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                        <td ><s:textfield name="scale2" size="2" onfocus="javascript:checkScale();"  tabindex="49" id="scale2" cssClass="inputTextBlue2" onkeypress="return isNumberKey(event);"></s:textfield></td>
                                        <td ><s:textfield name="skill3"  onfocus="javascript:checkScale();"  tabindex="50" id="skill3" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                        <td ><s:textfield name="scale3" size="2" onfocus="javascript:checkScale();"   tabindex="51" id="scale3" cssClass="inputTextBlue2" onkeypress="return isNumberKey(event);"></s:textfield></td>
                                        </tr>  
                                        <tr>
                                            <td ><s:textfield name="skill4"  onfocus="javascript:checkScale();"  tabindex="52" id="skill4" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                        <td ><s:textfield name="scale4" size="2"  onfocus="javascript:checkScale();"  tabindex="53" id="scale4" cssClass="inputTextBlue2" onkeypress="return isNumberKey(event);"></s:textfield></td>
                                        <td ><s:textfield name="skill5" onfocus="javascript:checkScale();"   tabindex="54" id="skill5" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                        <td ><s:textfield name="scale5" size="2"  onfocus="javascript:checkScale();"  tabindex="55" id="scale5" cssClass="inputTextBlue2" onkeypress="return isNumberKey(event);"></s:textfield></td>
                                        <td ><s:textfield name="skill6"  onfocus="javascript:checkScale();"  tabindex="56" id="skill6" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                        <td ><s:textfield name="scale6" size="2"  onfocus="javascript:checkScale();"  tabindex="57" id="scale6" cssClass="inputTextBlue2" onkeypress="return isNumberKey(event);"></s:textfield></td>
                                        </tr> 
                                        <tr>
                                            <td colspan="6" class="headerText" align="left">
                                                Experience
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabelLeft" width="100px" align="left">Company Name</td>
                                            <td class="fieldLabelLeft" width="100px" align="right">Designation</td>
                                            <td class="fieldLabelLeft" width="100px" align="right">From</td>
                                            <td class="fieldLabelLeft" width="100px" align="right">To</td>
                                            <td class="fieldLabelLeft" width="100px" align="right">Location</td>
                                        </tr>
                                        <tr>
                                            <td ><s:textfield name="company"   tabindex="58" id="company" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                        <td ><s:textfield name="designation" tabindex="59" id="designation" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                        <td><s:textfield  name="fromDate" id="fromDate" cssClass="inputTextBlueSmall" tabindex="60" onChange="return checkDates(this)"/><a href="javascript:cal3.popup();">
                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                        </td>
                                        <td><s:textfield  name="toDate" id="toDate" cssClass="inputTextBlueSmall" tabindex="61" onChange="return checkDates(this)"/><a href="javascript:cal4.popup();">
                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                        </td>
                                        <td ><s:textfield name="location"  value="" tabindex="62" id="location" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>         
                                        </tr> 
                                        <tr>
                                            <td ><s:textfield name="company1"   tabindex="63" id="company1" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                        <td ><s:textfield name="designation1"  value="" tabindex="64" id="designation1" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                        <td><s:textfield  name="fromDate1" id="fromDate1" cssClass="inputTextBlueSmall" tabindex="65" value="%{fromDate1}" onChange="return checkDates(this)"/><a href="javascript:cal5.popup();">
                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                        </td>
                                        <td><s:textfield  name="toDate1" id="toDate1" cssClass="inputTextBlueSmall" tabindex="66" onChange="return checkDates(this)"/><a href="javascript:cal6.popup();">
                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                        </td>
                                        <td ><s:textfield name="location1"   tabindex="67" id="location1" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>

                                        </tr> 
                                        <tr>
                                            <td ><s:textfield name="company2"   tabindex="68" id="company2" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                        <td ><s:textfield name="designation2"   tabindex="69" id="designation2" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                        <td><s:textfield  name="fromDate2" id="fromDate2" tabindex="70" cssClass="inputTextBlueSmall"  onChange="return checkDates(this)"/><a href="javascript:cal7.popup();">
                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                        </td>
                                        <td><s:textfield  name="toDate2" id="toDate2" cssClass="inputTextBlueSmall" tabindex="71" onChange="return checkDates(this)"/><a href="javascript:cal8.popup();">
                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                        </td>
                                        <td ><s:textfield name="location2"  tabindex="72" id="location2" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>

                                        </tr> 
                                        <tr>
                                            <td colspan="6" class="headerText" align="left">
                                                Reference Information
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabelLeft" width="100px" align="left">Name : </td>
                                            <td ><s:textfield name="refName"   tabindex="73" id="refName" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                            <td class="fieldLabelLeft" width="100px" align="left" >E Mail : </td>
                                            <td colspan="3"><s:textfield name="refEmail"    tabindex="74" id="refEmail" cssClass="inputTextBlueReg" onchange="return checkEmail(this);allSmalls(this);fieldLengthValidator(this);"></s:textfield></td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabelLeft" width="100px" align="left">Mobile No : </td>
                                            <td ><s:textfield name="refMobile"   tabindex="75" id="refMobile" cssClass="inputTextBlue" onchange="return formatPhone(this);" onblur="return validatenumber(this);"></s:textfield></td>

                                        </tr> 

                            </tr>


                        </table>
                        <script type="text/JavaScript">
                            var cal1 = new CalendarTime(document.forms['registrationForm'].elements['birthDate']);
                            cal1.year_scroll = true;
                            cal1.time_comp = false;
                                                           
                                               
                            var cal3 = new CalendarTime(document.forms['registrationForm'].elements['fromDate']);
                            cal3.year_scroll = true;
                            cal3.time_comp = false;
                            var cal4 = new CalendarTime(document.forms['registrationForm'].elements['toDate']);
                            cal4.year_scroll = true;
                            cal4.time_comp = false;
                                                
                            var cal5 = new CalendarTime(document.forms['registrationForm'].elements['fromDate1']);
                            cal5.year_scroll = true;
                            cal5.time_comp = false;
                                                
                            var cal6 = new CalendarTime(document.forms['registrationForm'].elements['toDate1']);
                            cal6.year_scroll = true;
                            cal6.time_comp = false;
                                                
                            var cal7 = new CalendarTime(document.forms['registrationForm'].elements['fromDate2']);
                            cal7.year_scroll = true;
                            cal7.time_comp = false;
                                                
                            var cal8 = new CalendarTime(document.forms['registrationForm'].elements['toDate2']);
                            cal8.year_scroll = true;
                            cal8.time_comp = false;
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
<script>
$(document).ready(function() {
    init(1);
     });
</script>	
</body>
</html>