<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<html>
    <head>
        <title>Hubble Organization Portal :: CRE Consultant Details</title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/javascript" language="javascript" src="../includes/scroller/scroll.js"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>  
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/RegistrationClientValidation.js"/>"></script> --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StringUtility.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
       <script type="text/JavaScript" src="<s:url value="/includes/javascripts/cre/EmpStandardClientValidations.js"/>"></script>
       
 <style type="text/css">
		body { font-family: Helvetica, sans-serif; }
		h2, h3 { margin-top:0; }
		form { margin-top: 15px; }
		form > input { margin-right: 15px; }
		#results { float:right; margin:20px; padding:20px; border:1px solid; background:#ccc; }
                
                
                .btn {
  background: #44a3e3;
  background-image: -webkit-linear-gradient(top, #44a3e3, #2980b9);
  background-image: -moz-linear-gradient(top, #44a3e3, #2980b9);
  background-image: -ms-linear-gradient(top, #44a3e3, #2980b9);
  background-image: -o-linear-gradient(top, #44a3e3, #2980b9);
  background-image: linear-gradient(to bottom, #44a3e3, #2980b9);
  -webkit-border-radius: 6;
  -moz-border-radius: 6;
  border-radius: 6px;
  font-family: Arial;
  color: #ffffff;
  font-size: 15px;
  padding: 4px 19px 4px 17px;
  text-decoration: none;
}

.btn:hover {
  background: #3cb0fd;
  background-image: -webkit-linear-gradient(top, #3cb0fd, #3498db);
  background-image: -moz-linear-gradient(top, #3cb0fd, #3498db);
  background-image: -ms-linear-gradient(top, #3cb0fd, #3498db);
  background-image: -o-linear-gradient(top, #3cb0fd, #3498db);
  background-image: linear-gradient(to bottom, #3cb0fd, #3498db);
  text-decoration: none;
}
	</style>
        <script type="text/JavaScript">
           function getLocation(){
               
              // alert("onload");
                var test = document.getElementsByName("attendingInterviewAt");
                var sizes = test.length;
                var loc = "";
                for (i=0; i < sizes; i++) {
                        if (test[i].checked==true) {
                //     alert(test[i].value + ' you got a value'); 
                        loc = test[i].value;
                    init(loc);
                }
            }
            }
            
            function init(val){
                //alert("in it");
                   if(val==1){
                             document.getElementById("loc").innerHTML = "Location&nbsp;:";
                        document.getElementById("mcampusF").style.display="block";
                        document.getElementById("clzF").style.display="none";
                        document.getElementById("JFF").style.display="none";
                   }else if(val==2){
                        document.getElementById("mcampusF").style.display="none";
                        document.getElementById("loc").innerHTML = "College&nbsp;Name :";
                        document.getElementById("clzF").style.display="block";
                        document.getElementById("JFF").style.display="none";
                        
                   }else{
                        document.getElementById("mcampusF").style.display="none";
                        document.getElementById("clzF").style.display="none";
                         document.getElementById("loc").innerHTML = "JobFair&nbsp;Name/Location :";
                        document.getElementById("JFF").style.display="block";
                         
                   }
            }
           
           
       </script>
       
       <script>
           function fileValidation1() {
          
          
    var imagePath =document.getElementById('imagePath').value;
     //alert(imagePath.length);
     if(imagePath.length==0){
         document.getElementById('resultMessage').innerHTML = "<font color=red>Please upload image.</font>";
         return false;
     }
    if(imagePath.length<30){
        // alert("imagePath-->"+imagePath);
        if(imagePath != null && (imagePath !="")) {
                    

            var extension = imagePath.substring(imagePath.lastIndexOf('.')+1);

            if(extension=="pdf"||extension=="jpg"|| extension=="png"||extension=="jpeg"){
                // document.imageForm.imagePath.focus();
                var size = document.getElementById('imagePath').files[0].size;
                //alert("size-->"+size);
                // alert("size in mb-->"+(parseInt(size)/1000000));
                //if((parseInt(size)/1000000)<2) {
                if(parseInt(size)<200000) {
                    return (true);
                }else {
                    document.getElementById('imagePath').value = "";
                    // alert("File size must be less than 1 MB.");
                    document.getElementById('resultMessage').innerHTML = "<font color=red>File size must be less than 200 KB.</font>"
                    return (false);
                }
            }else {
                document.getElementById('imagePath').value = "";
                document.getElementById('resultMessage').innerHTML = "<font color=red>Invalid file extension!Please select pdf or jpg or jpeg or png file.</font>"
                // alert("Invalid file extension!Please select pdf or jpg or png file.");
                return (false);
            }
        }
    }else {
        document.getElementById('resultMessage').innerHTML = "<font color=red>File name must be less than 30 characters!</font>"
        //alert("File name must be less than 30 characters!");
        document.getElementById('imagePath').value = "";
    }
    return (false);
}
            function fileValidation() {
               // alert("hii");
              //  alert("hii");
                var imagePath = document.imageForm.imagePath.value;
                
                
               // alert("imagePath-->"+imagePath);
                if(imagePath != null && (imagePath !="")) {
                   // document.imageForm.imagePath.focus();
                    var size = document.getElementById('imagePath').files[0].size;
                    //alert("size-->"+size);
                   // alert("size in mb-->"+(parseInt(size)/1000000));
                  //if((parseInt(size)/1000000)<2) {
                  if(parseInt(size)<200000) {
                    return (true);
                }else {
                    document.imageForm.imagePath.value = null;
                    alert("File size must be less than 200 KB.");
                    return (false);
                }
                }
                return (false);
            };
        </script> 
		
    </head>
    
   <!-- <body class="bodyGeneral" onload="getLocation();" oncontextmenu="return false;"> -->
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
                            
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" valign="top">
                                <s:include value="/includes/template/LeftMenu.jsp"/>
                            </td>
                            <!--//END USER TIPS : Coloumn for showing tips related to registration process-->
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td class="leftTopBorder" valign="top" width="850px">
                                 <sx:tabbedpanel id="registrationPannel" cssStyle="width: 850px; height: 530px;padding-left:10px;padding-top:5px;" doLayout="true" >
                                        
                                        <!--//START TAB : -->
                                        <sx:div id="personalDetailsTab" label="Consultant Details" cssStyle="overflow: auto;">
                                <!--//START TABBED PANNEL : -->
                                <s:form action="creConsultantEdit" name="registrationForm" theme="simple" method="post" onsubmit="return checkCreRegistrationForm();">
                                    <!--//START TABBED PANNEL : -->
                                   
                                            <table cellpadding="2" cellspacing="2" border="0" width="100%">
                                                
                                                <tr class="headerText">
                                                    <td colspan="6">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                            <tr align="right" class="headerText">
                                                                <td align="left">Interview Information</td>
                                                                <td align="right">
                                                                     <% 
                                                                            if(request.getSession(false).getAttribute("resultMessage") != null){
                                                                            out.println(request.getSession(false).getAttribute("resultMessage"));
                                                                     }
                                                                      request.getSession(false).removeAttribute("resultMessage");
                                                                            
                                                                            %>
                                                                    <s:submit name="submit" tabindex="76" value="Update" cssClass="buttonBg"/>&nbsp;
                                                                  <%--  <s:reset name="reset" value="Reset" cssClass="buttonBg"/>--%>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                                     <tr>
                                                         <td class="fieldLabel" width="100%" align="right" colspan="1">Attending&nbsp;Interview&nbsp;At&nbsp;:</td>
                                                         <td colspan="3" width="100%" align="right" class="fieldLabelLeft1">
                                                             <s:radio name="attendingInterviewAt" id="attendingInterviewAt" onchange="getLocation();" tabindex="1" value="%{attendingInterviewAt}"  list="#@java.util.LinkedHashMap@{'1':'MiracleCampus','2':'CampusRecruitment','3':'JobFair'}"></s:radio>
                                                         </td>  
                                                         
                                                     </tr>
                                                     <tr>
                                                         <td class="fieldLabel" width="100%" align="right">       
                                                           <span id="loc">Location:</span><font color="red" style="bold">&curren;</font>
                                                         </td>
                                   <td colspan="2">
                                   <span id="mcampusF">
                                       <s:select name="campusLocation" tabindex="2" id="campusLocation" value="%{campusLocation}" headerKey="0" headerValue="Select campus" cssClass="inputSelect" list="#@java.util.LinkedHashMap@{'1':'Miracle City','2':'Miracle Heights','3':'Miracle Admin. Office'}" theme="simple"></s:select>
                                   </span>
                                   <%-- <span id="clzF"><s:textfield name="recLocation" tabindex="2" id="recLocation" value="%{recLocation}" cssClass="inputTextBlueReg" theme="simple" onchange="fieldLengthValidator(this);"/></span> --%>
                                       <span id="clzF"><s:select name="recLocation" tabindex="2" id="recLocation" value="%{recLocation}" cssClass="inputSelect" headerKey="-1" headerValue="--Please Select--" list="recLocationList" theme="simple" onchange="fieldLengthValidator(this);"/></span> 
                                   <span id="JFF"><s:textfield name="jfairLocation" tabindex="2" id="jfairLocation" value="%{jfairLocation}" cssClass="inputTextBlueReg" theme="simple" onchange="fieldLengthValidator(this);"/></span>
                                                           
                                                         </td>
                                                     <td class="fieldLabel" width="100px" align="right">Post&nbsp;Applied :<font color="red" style="bold">&curren;</font></td>
                                                    <td>
                                                        <s:select name="category" tabindex="2" id="category" value="%{category}" cssClass="inputSelect" headerKey="-1" headerValue="Select Category" list="#@java.util.LinkedHashMap@{'1':'Software Trainee','2':'Sr.Developer','3':'Sales','4':'IT Recruitment','5':'Operations','6':'Network Engineer','7':'Trainer','8':'Civil','9':'MES','10':'Other','11':'US IT Sales','12':'HR','13':'Finance Executive'}" theme="simple"></s:select>
                                                    </td>  
                                                </tr>
                                                 <tr>
                                                    <td colspan="6" class="headerText" align="left">
                                                        Genaral Details
                                                        <s:hidden name="id" id="id" value="%{id}"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel" width="100px" align="right">First Name :<font color="red" style="font-weight: bold;">&curren;</font></td>                           
                                                    
                                                    <td><s:textfield name="firstName" id="firstName" tabindex="3" value="%{firstName}" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);changeCase(this);"/></td>      
                                                    
                                                    <td class="fieldLabel" width="100px" align="right">Last Name :<font color="red" style="font-weight: bold;">&curren;</font></td>
                                                    
                                                    <td><s:textfield name="lastName" id="lastName" tabindex="4" value="%{lastName}" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);changeCase(this);"/></td>
                                                    
                                                    
                                                    <td  align="center" rowspan="4" colspan="2">                                                      
                                                        <s:url id="image" action="renderConsultantImage?id=%{id}" namespace="/cre">
                                                             
                                                        </s:url>
                                                        <img alt="Employee Image" src="<s:property value="#image" />" style="border-width: 4px 4px 4px 4px;border-spacing: 2px;border-style: double double double double;border-color: black black black black;border-collapse: separate;" height="170" width="150"><br>
                                                        <span id="empname" class="fieldLabelLeft"><s:property value="%{firstName}"/>&nbsp;.&nbsp;<s:property value="%{lastName}"/></span><br>
                                                        <!--  <img class="x" src="../includes/images/noImage.jpg" height="150" width="150">
                                                    <a href="javascript:void(0)" onclick="window.open('EmpImage.jsp','','hemenubar=yes,height= 250, width = 250,location=center,resizable=no,scrollbars=yes,status=no');return false;"><input class="buttonBg" type="button" value="Image"></a>-->
                                                    </td>
                                                    
                                                <%--    <td class="fieldLabel" width="100px" align="right">Middle Name :</td>                           
                                                    
                                                    <td><s:textfield name="middleName" id="midName" tabindex="5" value="%{middleName}" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);changeCase(this);"/></td>     --%>
                                                </tr>
                                                
                                                 <tr>
                                                    <td class="fieldLabel" width="100px" align="right">Middle Name :</td>                           
                                                    
                                                    <td><s:textfield name="middleName" id="midName" tabindex="5" value="%{middleName}" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);changeCase(this);"/></td>    
                                                    <td class="fieldLabel" width="100px" align="right">SSN/PAN :</td>                           
                                                    
                                                    <td><s:textfield name="pan" id="pan" value="%{pan}" tabindex="8" cssClass="inputTextBlue" size="20" onchange="fieldLengthValidator(this);"/></td>             
                                                </tr>
												
                                                <tr>
                                                    <td class="fieldLabel" width="100px" align="right">Gender :</td>                           
                                                    
                                                    <td><s:select label="Select Gender" 
                                                                      name="gender" id="gender"
                                                                      headerKey=""
                                                                      headerValue="-- Please Select --" 
                                                                       tabindex="6"
                                                                       value="%{gender}"
                                                                      list="genderList" cssClass="inputSelect"/>
                                                    </td>                                                      
                                                    
                                                    <td class="fieldLabel" width="100px" align="right">Marital Status :</td>
                                                    
                                                    <td><s:select label="Select Marital Status" 
                                                                      name="maritalStatus" 
                                                                      headerKey="" id="maritalStatus"
                                                                      headerValue="-- Please Select --"
                                                                       tabindex="7"
                                                                       value="%{maritalStatus}"
                                                                  list="maritalStatusList"  cssClass="inputSelect"/></td>
                                                    
                                                 <%--   <td class="fieldLabel" width="100px" align="right">SSN/PAN :</td>                           
                                                    
                                                    <td><s:textfield name="pan" id="pan" value="%{pan}" tabindex="8" cssClass="inputTextBlue" size="20" onchange="fieldLengthValidator(this);"/></td>              --%>
                                                </tr>
                                                
                                                <tr>
                                                       
                                                                  <td class="fieldLabel" width="100px" >DOB<font size="1" color="red">(mm/dd/yyyy)</font> :</td>                           
                                                    
                                                    <td><s:textfield  name="birthDate" value="%{birthDate}" tabindex="9" cssClass="inputTextBlueSmall" id="birthDate"  onChange="return checkDates(this)"/><a href="javascript:cal1.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                    </td>
                                                    
                                                    
                                                    
                                                </tr>
                                                <tr>
                                                     <td class="fieldLabel" width="100px" align="right">Father&nbsp;Name&nbsp;:</td>                           
                                                    
                                                    <td><s:textfield name="fatherName"  value="%{fatherName}" tabindex="10"  id="creFatherName" cssClass="inputTextBlue" size="20" onchange="fieldLengthValidator(this);"/></td>             
                                                    <td class="fieldLabel" width="100px" align="right">Father&nbsp;Occupation&nbsp;:</td>                           
                                                    
                                                    <td><s:textfield name="fatherOccupation" tabindex="11" value="%{fatherOccupation}" id="fatherOccupation" cssClass="inputTextBlue" size="20" onchange="fieldLengthValidator(this);"/></td>             
                                                    
                                                     <td class="fieldLabel" width="100px" align="right">Father&nbsp;Income&nbsp;:</td>
                                                     <td><s:select name="fatherIncome" value="%{fatherIncome}" tabindex="12" id="fatherIncome" cssClass="inputSelect" headerKey="-1" headerValue="--Select Category--" list="{'Below 1 Lakh','> 1 Lakh','2Lakh to 3 Lakh','3Lakh to 5Lakh','5Lakh to 7Lakh','7Lakh to 9Lakh','Above 10 Lakh'}"></s:select></td>
                                                 
                                                 </tr>
                                                   <tr >
                                                     <td class="fieldLabel" width="100px" align="right" rowspan="2">Address :</td>   
                                                     <td rowspan="2">
                                                           <s:textarea cols="17" rows="3" tabindex="13" value="%{address}" name="address" id="address" cssClass="inputTextarea"  onchange="fieldLengthValidator(this);"/>
                                                     </td>
                                                      <td class="fieldLabel" width="100px" align="right">City :</td>  
                                                        <td><s:textfield name="city" id="city" tabindex="14" value="%{city}" cssClass="inputTextBlue" size="20" onchange="fieldLengthValidator(this);"/></td>             
                                                 <td class="fieldLabel" width="100px" align="right">Pin :</td>                           
                                                    
                                                    <td><s:textfield name="pin" id="pin" tabindex="15" value="%{pin}" cssClass="inputTextBlue" size="20" onchange="fieldLengthValidator(this);"/></td>             
                                                    </tr>  
                                                    <tr>
                                                        
                                                        <td class="fieldLabel" width="100px" align="right">State :</td>                           
                                                        
                                                        <td><s:textfield name="state" tabindex="16" id="state" value="%{state}" cssClass="inputTextBlue" size="20" onchange="fieldLengthValidator(this);"/></td>             
                                                    </tr>
                                                    <tr>
                                                   
                                                 </tr>
                                                 
                                                
                                                <tr>
                                                    <td colspan="6" class="headerText" align="left">
                                                        Contact Details
                                                    </td>
                                                </tr>
                                                <tr>
                                                   
                                                    <td class="fieldLabel" width="100px" align="right">Mobile  :<font color="red" style="font-weight: bold;">&curren;</font>
                                                    </td>                           
                                                    
                                                    <td><s:textfield name="cellPhoneNo" tabindex="17" value="%{cellPhoneNo}" cssClass="inputTextBlue" id="cellPhoneNo" onchange="return formatPhone(this);" onblur="return validatenumber(this);"/></td>  
                                                    
                                                  <%--  <td class="fieldLabel" width="100px" align="right">Home-Phone  :</td>                           
                                                    
                                                    <td><s:textfield name="homePhoneNo" cssClass="inputTextBlue"  onchange="return formatPhone(this);" onblur="return validatenumber(this);"/></td>  --%>
                                                    <td class="fieldLabel" width="100px" align="right">Alt-Phone  :</td>                           
                                                    
                                                    <td><s:textfield name="altPhoneNo" tabindex="18" value="%{altPhoneNo}" cssClass="inputTextBlue" onchange="return formatPhone(this);" onblur="return validatenumber(this);"/></td>   
                                                </tr>
                                                
                                            
                                                </tr>
                                            
                                                
                                                <tr>
                                                     <td class="fieldLabel" width="100px" align="right">Personal Email :<font color="red" style="font-weight: bold;">&curren;</font></td>
                                                    
                                                    <td colspan="2"><s:textfield name="personalEmail" value="%{personalEmail}" tabindex="19" cssClass="inputTextBlueReg" id="personalEmail" onchange="return checkEmail(this);allSmalls(this);"/></td>
                                                    
                                                    <td class="fieldLabel" width="100px" align="right">Other Email :</td>
                                                    
                                                    <td colspan="2"><s:textfield name="otherEmail" tabindex="20" value="%{otherEmail}" cssClass="inputTextBlueReg" onchange="return checkEmail(this);allSmalls(this);"/></td>   
                                                    
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
                                                               <s:select name="qualification" value="%{qualification}" tabindex="21" id="qualification" cssClass="inputSelect" headerKey="-1" headerValue="--Please Select--" list="{'SSC','CBSC','ICS','Other'}"></s:select>
                                                               </td>
                                                               <td ><s:textfield name="education" value="%{education}" maxlength="15"  tabindex="22" id="" cssClass="inputTextBlue3" onkeypress="return isNameAlpha(event);" disabled="true"></s:textfield>
                                                                  <%-- <s:hidden name="education" maxlength="15" value="NoStreamForSSC" tabindex="" id="education" onkeypress="return isNameAlpha(event);" readonly="false"></s:hidden>--%></td>
                                                               <td ><s:textfield name="college" value="%{college}" maxlength="40"  tabindex="23" id="college" cssClass="inputTextBlue" onkeypress="return isNameAlpha(event);" ></s:textfield></td>
                                                           <td >
                                                               <s:select name="medium" tabindex="24" value="%{medium}" id="medium" cssClass="inputTextBlue" headerKey="-1" headerValue="--Please Select--" list="{'English','Hindi','Telugu','Other'}"></s:select>
   
                                                           </td>
                                                           <td ><s:textfield name="yearOfPass" value="%{yearOfPass}" maxlength="4"  tabindex="25" id="yearOfPass" cssClass="inputTextBlue2" onkeypress="return isNumberKey(event);"></s:textfield></td>
                                                           <td ><s:textfield name="percentage" value="%{percentage}" maxlength="5"  tabindex="26" id="percentage" cssClass="inputTextBlue2" onchange="validatePercent1(this);"></s:textfield><span class="fieldLabel">%</span></td>
                                                             
                                                       </tr>
                                                       <tr>
                                                           <td >
                                                               <s:select name="ipeCategory" value="%{ipeCategory}" tabindex="27" id="ipeCategory" cssClass="inputSelect" headerKey="-1" headerValue="--Select Category--" list="{'Intermediate','Diploma','Other'}"></s:select>
                                                               </td>
                                                               <td ><s:textfield name="ipeStream" value="%{ipeStream}" maxlength="15"  tabindex="28" id="ipeStream" cssClass="inputTextBlue3" onkeypress="return isNameAlpha(event);" ></s:textfield>
                                                                   
                                                               <td ><s:textfield name="ipeCollege" value="%{ipeCollege}" maxlength="40"  tabindex="29" id="ipeCollege" cssClass="inputTextBlue" onkeypress="return isNameAlpha(event);" ></s:textfield></td>
                                                           <td >
                                                               <s:select name="ipeMedium" value="%{ipeMedium}" tabindex="30" id="ipeMedium" cssClass="inputTextBlue" headerKey="-1" headerValue="--Please Select--" list="{'English','Hindi','Telugu','Other'}"></s:select>
   
                                                           </td>
                                                           <td ><s:textfield name="ipeYearOfPass" value="%{ipeYearOfPass}" maxlength="4"  tabindex="31" id="ipeYearOfPass" cssClass="inputTextBlue2" onkeypress="return isNumberKey(event);"></s:textfield></td>
                                                           <td ><s:textfield name="ipePercentage" value="%{ipePercentage}" maxlength="5"  tabindex="32" id="ipePercentage" cssClass="inputTextBlue2" onchange="validatePercent1(this);"></s:textfield><span class="fieldLabel">%</span></td>
                                                       </tr>
                                                        <tr>
                                                           <td >
                                                               <s:select name="degreeQual" value="%{degreeQual}" tabindex="33" id="degreeQual" cssClass="inputSelect" headerKey="-1" headerValue="--Select Graduation--" list="{'B.Tech','B.E','B.Sc','B.Com','B.A','BBM','Other'}"></s:select>
                                                               </td>
                                                               <td ><s:textfield name="degreeBranch" value="%{degreeBranch}" maxlength="15" tabindex="34" id="degreeBranch" cssClass="inputTextBlue3" onkeypress="return isNameAlpha(event);" ></s:textfield>
                                                                   
                                                               <td ><s:textfield name="degreeCollege"  maxlength="40" value="%{degreeCollege}" tabindex="35" id="degreeCollege" cssClass="inputTextBlue" onkeypress="return isNameAlpha(event);" ></s:textfield></td>
                                                           <td >
                                                               <s:select name="degreeMedium"  value="%{degreeMedium}" tabindex="36" id="degreeMedium" cssClass="inputTextBlue" headerKey="-1" headerValue="--Please Select--" list="{'English','Hindi','Telugu','Other'}"></s:select>
   
                                                           </td>
                                                           <td ><s:textfield name="degreeYearOfPass" value="%{degreeYearOfPass}" maxlength="4"   tabindex="37" id="degreeYearOfPass" cssClass="inputTextBlue2" onkeypress="return isNumberKey(event);"></s:textfield></td>
                                                           <td ><s:textfield name="degreePercentage"  maxlength="5"  value="%{degreePercentage}" tabindex="38" id="degreePercentage" cssClass="inputTextBlue2" onchange="validatePercent1(this);"></s:textfield><span class="fieldLabel">%</span></td>
                                                       </tr>
                                                       <tr>
                                                           <td >
                                                               <s:select name="pgQual" tabindex="39" value="%{pgQual}" id="pgQual" cssClass="inputSelect" headerKey="-1" headerValue="--Select PG--" list="{'MCA','MBA','M.Tech','M.Com','M.Sc','MS','Other'}"></s:select>
                                                               </td>
                                                               <td ><s:textfield name="pgStream"  maxlength="15"  value="%{pgStream}" tabindex="40" id="pgStream" cssClass="inputTextBlue3" onkeypress="return isNameAlpha(event);" ></s:textfield>
                                                                 
                                                               <td ><s:textfield name="pgCollege"  maxlength="40" value="%{pgCollege}"  tabindex="41" id="pgCollege" cssClass="inputTextBlue" onkeypress="return isNameAlpha(event);" ></s:textfield></td>
                                                           <td >
                                                               <s:select name="pgMedium" tabindex="42"  id="pgMedium" value="%{pgMedium}" cssClass="inputTextBlue" headerKey="-1" headerValue="--Please Select--" list="{'English','Hindi','Telugu','Other'}"></s:select>
   
                                                           </td>
                                                           <td ><s:textfield name="pgYearOfPass"  value="%{pgYearOfPass}" maxlength="4"  tabindex="43" id="pgYearOfPass" cssClass="inputTextBlue2" onkeypress="return isNumberKey(event);"></s:textfield></td>
                                                           <td ><s:textfield name="pgPercentage"  value="%{pgPercentage}" maxlength="5"  tabindex="44" id="pgPercentage" cssClass="inputTextBlue2" onchange="validatePercent1(this);"></s:textfield><span class="fieldLabel">%</span></td>
                                                       </tr>
                                                        <tr>
                                                    <td colspan="6" class="headerText" align="left">
                                                        Additional Information
                                                    </td>
                                                </tr>
                                                       <tr>       
                                                               <td class="fieldLabelLeft" width="100px" >Additional Information :</td>    
                                                                
                                                     <td colspan="4">
                                                           <s:textarea cols="72" rows="3" value="%{addInfo}" name="addInfo" id="addInfo" cssClass="inputTextarea" tabindex="45" onchange="fieldLengthValidator(this);"/>
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
                                                       <td ><s:textfield name="skill1"  value="%{skill1}"  tabindex="46" id="skill1" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"  ></s:textfield></td>
                                                        <td ><s:textfield name="scale1"  value="%{scale1}"   tabindex="47" id="scale1" cssClass="inputTextBlue2" onkeypress="return isNumberKey(event);"></s:textfield></td>
                                                         <td ><s:textfield name="skill2"  value="%{skill2}" onfocus="javascript:checkScale();" tabindex="48" id="skill2" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                                          <td ><s:textfield name="scale2"  value="%{scale2}" onfocus="javascript:checkScale();" tabindex="49" id="scale2" cssClass="inputTextBlue2" onkeypress="return isNumberKey(event);"></s:textfield></td>
                                                           <td ><s:textfield name="skill3"  value="%{skill3}" onfocus="javascript:checkScale();" tabindex="50" id="skill3" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                                            <td ><s:textfield name="scale3"  value="%{scale3}" onfocus="javascript:checkScale();" tabindex="51" id="scale3" cssClass="inputTextBlue2" onkeypress="return isNumberKey(event);"></s:textfield></td>
                                                  </tr>  
                                                <tr>
                                                <td ><s:textfield name="skill4" value="%{skill4}"  onfocus="javascript:checkScale();" tabindex="52" id="skill4" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                                <td ><s:textfield name="scale4"  value="%{scale4}" onfocus="javascript:checkScale();" tabindex="53" id="scale4" cssClass="inputTextBlue2" onkeypress="return isNumberKey(event);"></s:textfield></td>
                                                <td ><s:textfield name="skill5" value="%{skill5}" onfocus="javascript:checkScale();"  tabindex="54" id="skill5" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                                <td ><s:textfield name="scale5"  value="%{scale5}" onfocus="javascript:checkScale();" tabindex="55" id="scale5" cssClass="inputTextBlue2" onkeypress="return isNumberKey(event);"></s:textfield></td>
                                                <td ><s:textfield name="skill6" value="%{skill6}" onfocus="javascript:checkScale();"  tabindex="56" id="skill6" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                                <td ><s:textfield name="scale6" value="%{scale6}" onfocus="javascript:checkScale();"  tabindex="57" id="scale6" cssClass="inputTextBlue2" onkeypress="return isNumberKey(event);"></s:textfield></td>
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
                                                       <td ><s:textfield name="company" value="%{company}" tabindex="58" id="company" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                                        <td ><s:textfield name="designation"  value="%{designation}" tabindex="59" id="designation" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                                        <td><s:textfield  name="fromDate" id="fromDate" value="%{fromDate}" cssClass="inputTextBlueSmall" tabindex="60" onChange="return checkDates(this)"/><a href="javascript:cal3.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                    </td>
                                                    <td><s:textfield  name="toDate" id="toDate" value="%{toDate}" cssClass="inputTextBlueSmall" tabindex="61" onChange="return checkDates(this)"/><a href="javascript:cal4.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                    </td>
                                                           <td ><s:textfield name="location" id="location" value="%{location}" tabindex="62"  cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>         
                                                  </tr> 
                                                   <tr>
                                                       <td ><s:textfield name="company1" value="%{company1}"  tabindex="63" id="company1" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                                        <td ><s:textfield name="designation1"  value="%{designation1}" tabindex="64" id="designation1" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                                        <td><s:textfield  name="fromDate1" id="fromDate1" cssClass="inputTextBlueSmall" tabindex="65" value="%{fromDate1}" onChange="return checkDates(this)"/><a href="javascript:cal5.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                    </td>
                                                         <td><s:textfield  name="toDate1" id="toDate1" value="%{toDate1}" cssClass="inputTextBlueSmall" tabindex="66" onChange="return checkDates(this)"/><a href="javascript:cal6.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                    </td>
                                                           <td ><s:textfield name="location1"  value="%{location1}" tabindex="67" id="location1" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                                            
                                                  </tr> 
                                                   <tr>
                                                       <td ><s:textfield name="company2" value="%{company2}"  tabindex="68" id="company2" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                                        <td ><s:textfield name="designation2"  value="%{designation2}" tabindex="69" id="designation2" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                                        <td><s:textfield  name="fromDate2" id="fromDate2" tabindex="70" cssClass="inputTextBlueSmall" value="%{fromDate2}" onChange="return checkDates(this)"/><a href="javascript:cal7.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                    </td>
                                                          <td><s:textfield  name="toDate2" id="toDate2" cssClass="inputTextBlueSmall" value="%{toDate2}" tabindex="71" onChange="return checkDates(this)"/><a href="javascript:cal8.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                    </td>
                                                           <td ><s:textfield name="location2" value="%{location2}" tabindex="72" id="location2" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                                            
                                                  </tr> 
                                                   <tr>
                                                    <td colspan="6" class="headerText" align="left">
                                                        Reference Information
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabelLeft" width="100px" align="left">Name : </td>
                                                    <td ><s:textfield name="refName" value="%{refName}"  tabindex="73" id="refName" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);"></s:textfield></td>
                                                    <td class="fieldLabelLeft" width="100px" align="left" >E Mail : </td>
                                                    <td colspan="3"><s:textfield name="refEmail"  value="%{refEmail}"  tabindex="74" id="refEmail" cssClass="inputTextBlueReg" onchange="return checkEmail(this);allSmalls(this);fieldLengthValidator(this);"></s:textfield></td>
                                                </tr>
                                                 <tr>
                                                    <td class="fieldLabelLeft" width="100px" align="left">Mobile No : </td>
                                                    <td ><s:textfield name="refMobile"  value="%{refMobile}" tabindex="75" id="refMobile" cssClass="inputTextBlue" onchange="return formatPhone(this);" onblur="return validatenumber(this);"></s:textfield></td>
                                                   
                                                </tr> 
                                                
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
                                   
                                    <!--//END TABBED PANNEL : -->
                                </s:form>
                                         </sx:div >
                                        <!--//END TAB : -->
                                    <sx:div id="imageUploadTab" label="Upload Image" cssStyle="overflow: auto;">
                                               <s:form name="imageForm" action="doImageUpload" theme="simple" enctype="multipart/form-data" onsubmit="return fileValidation1();">
                                                   <s:hidden name="consultantId" id="consultantId" value="%{id}"/>
                                                   <s:hidden name="consultentId" id="consultentId" value="%{consultentId}"/>

                                            <table border="0" width="100%" cellpadding="0" cellspacing="0">
                                                <tr class="headerText">
                                                    <td align="right">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0" >                                    
                                                    </td>
                                                </tr>
                                                <tr>
                                                                    <td>
                                                                        <div id="resultMessage"></div>
                                                                    </td>
                                                                </tr>
                                                <tr>
                                                    <td align="center">
                                                        <input type="file" name="imagePath" id="imagePath" class="inputTextBlueLarge" onchange="fileValidation1();"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="padding-left:15px;" colspan="6" align="center">
                                                         <s:submit name="submit" value="Upload" cssClass="buttonBg"/>
                                                       <%-- <input type="submit" value="Upload" class="buttonBg"/> --%>

</td> 
                                                </tr>
                                                <tr class="fieldLabel">
                                                    <td align="center" colspan="6">
                                                        *Image size must be less than 200 KB.
                                                    </td>
                                                </tr>
                                            </table>
                                        </s:form>
                                        </sx:div>
                                        <!-- Webcam upload start -->
                                        <sx:div id="CaptureImage" label="Capture Image" cssStyle="overflow: auto;">
                                          
	
	<h1>Webcam to Capture Image</h1>
	<h3></h3>
	<input type="button" class="btn" value="WantsWebCam" onClick="viewWebCam()"/>
        <div id="results">Your captured image will appear here...
                                             
                                          </div>
	<div id="my_camera"></div>
	
	<!-- First, include the Webcam.js JavaScript Library -->
	 <script type="text/JavaScript" src="<s:url value="/includes/javascripts/webcam.js"/>"></script>
	
	<!-- Configure a few settings and attach camera -->
	<script language="JavaScript">
            function viewWebCam(){
		Webcam.set({
			width: 320,
			height: 240,
			image_format: 'jpeg',
			jpeg_quality: 90
		});
               // alert(Webcam.live);
		Webcam.attach( '#my_camera' );
                //alert(Webcam.live);
            document.getElementById('btnSnap').style.display='block';
       }
	</script>
	
	<!-- A button for taking snaps -->
        
                                               <s:form name="doCaptureImage" id="doCaptureImage" action="CaptureImage" theme="simple" enctype="multipart/form-data" >
                                                   <s:hidden name="consultantId" id="consultantId" value="%{id}"/>
                                                   <s:hidden name="consultentId" id="consultentId" value="%{consultentId}"/>

                                            <table border="0" width="100%" cellpadding="0" cellspacing="0">
                                               
             <input id="mydata" type="hidden" name="mydata" value=""/>
		<input type=button id="btnSnap" class="btn" style="display: none;" value="Take Snapshot" onClick="take_snapshot()">
	
                                              
                                               
                                            </table>
                                        </s:form>
        <script language="JavaScript">
		function take_snapshot() {
			// take snapshot and get image data
                       // alert("hai");
			Webcam.snap( function(data_uri) {
				// display results in page
                                //alert(data_uri);
                                
				document.getElementById('results').innerHTML = 
					'<table>'+'<tr>'+'<td><h2>Here is your image:</h2></td></tr>' + 
					'<tr><td><img src="'+data_uri+'"/></td></tr>'+
                                          '<tr align="center"><td><br><input type="button" id="uploadbtn" class="btn" value="UploadImage" onClick="upload()"/></td></tr></table>';
                                     var raw_image_data = data_uri.replace(/^data\:image\/\w+\;base64\,/, '');

        document.getElementById('mydata').value = raw_image_data;
      //  alert(raw_image_data);
       
			} );
		}
                
                function upload(){
                   // alert("haiUploded");
                     document.getElementById("uploadbtn").disabled = true;
                     document.getElementById("doCaptureImage").submit();
                }
	</script>
                                        </sx:div>
                                        
                                        <!-- Webcam upload end -->
                                    </sx:tabbedpanel>
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
   getLocation();
     });
</script>	
    </body>
</html>