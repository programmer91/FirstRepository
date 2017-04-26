/*
 File Name: StandardClientValidations.js
    Author: Hari Krishna Kondala
    E-Mail: hkondala@miraclesoft.com
 */


//PHONE NUMBER FORMAT SCRIPT :START   
function formatPhone(element) {     
    str = new String(element.value);       
    if(str == "undefined" ) { 
        alert('Please give atleast  10 charcters in PhoneNumber');
        return false;        
    }else{        
        element.value=str.replace(/[A-Za-z\(\)\.\-\x\s,]/g, "");
        num=element.value;
        var _return;
        if(num.length == 10) { 
            _return="(";
            var ini = num.substring(0,3);
            _return+=ini+")";
            var st = num.substring(3,6);
            _return+="-"+st+"-";
            var end = num.substring(6,10);
            _return+=end;
            
            element.value ="";
            element.value =_return;
            
        }else if(num.length < 10) {
            alert('Please give atleast  10 charcters in PhoneNumber');
            return false;
        }else if(num.length > 10 && num.length < 18) { 
            _return="(";
            var ini = num.substring(0,3);
            _return+=ini+")";
            var st = num.substring(3,6);
            _return+="-"+st+"-";
            var last = num.substring(6,10);
            _return+=last+" X";
            var end = num.substring(10,num.length);
            _return+=end;
            
            element.value ="";
            element.value =_return;
            
        }else if(num.length > 18) {
            alert('Phone Number should be 18 characters');
            element.value =_return;
            //element.value ="";
            element.value = num.substring(0,16);
            
            element.value =num.substring(0,16);
            _return="(";
            var ini = num.substring(0,3);
            _return+=ini+")";
            var st = num.substring(3,6);
            _return+="-"+st+"-";
            var last = num.substring(6,10);
            _return+=last+" X";
            var end = num.substring(10,num.length);
            _return+=end;
            element.value ="";
            element.value =_return;
            
            element.focus();
            return false;
        }else if(num.length < 10 || num=='undefined') {
            alert('Please give atleast  10 charcters in PhoneNumber');
            return false;
        }else if(num.length > 10 && num.length < 16) {
            alert('Please give atleast  16 charcters in PhoneNumber');
            return false;
        }
        
        return _return;
    }
}  

function checkEmail(element) {
    
    if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(element.value)){
        return (true)
    }
    element.value="";
    alert("Invalid E-mail Address! Please re-enter.")
    
    return (false)
}
// || element.id == 'conatctFName' || element.id == 'conatctLName'
function fieldValidator() {
if(document.getElementById("accountName").value.length == 0 ) {
        alert("Enter Account Name!!");
        return false;
}

if((document.getElementById("state").value == "-1") || (document.getElementById("state").value == "--Please Select--")) {
        alert("Please select state!!");
        return false;
}

}
function fieldLengthValidator(element){
  //alert("In Field Length validator SCV");
    var url = element.form.action;
    var action1 = url.lastIndexOf("/");
    var action2 = url.substr(action1+1,url.length);
    var action3 = action2.lastIndexOf(".");
    var action = action2.substr(0,action3);
    var i = 0;
    if (element.value != null && (element.value != "")) {
        //alert("In if standard client validation");
        if(element.id == 'accountName' || element.id == 'synonyms') i = 60;
        else if(element.id == 'url' || element.id == 'leadSource' || element.id == 'taxId' || element.id == 'addressLine2' || element.id == 'resAddressLine2'
        || element.id == 'attachmentName' || element.id == 'leadSource' || element.id == 'officeEmail' || element.id == 'personalEmail' || element.id == 'reportingManager'
        || element.id == 'fName' || element.id == 'lName' || element.id == 'mName' || element.id == 'vendorContactPerson' || element.id == 'billingManager'
        || element.id == 'opportunity' || element.id == 'jobTitle' || element.id == 'conatctFName' || element.id == 'conatctLName' || element.id == 'softwarePartNumber' ) i = 50;
        else if(element.id == 'fax') i = 16;
        else if(element.id == 'zip' || element.id == 'resZip') {
            i = 15;
            validatenumber(element);
        }
        else if(element.id == 'accountTeam' || element.id == 'aliasName') i = 20;
        else if(element.id == 'noOfEmployees' || element.id ==  'mailStop' || element.id == 'resMailStop') i = 10;
        else if(element.id == 'stockSymbol1' || element.id == 'stockSymbol2') i = 12;
        else if(element.id == 'description') i = 250;
        else if(element.id == 'Description') i = 1500;
        else if(element.id == 'leadDescription') i = 1000;
        else if(element.id == 'leadTitle') i = 140;
        
        else if(element.id == 'applications' || element.id == 'hardware' || element.id == 'softwares' || element.id == 'databases' || element.id == 'recSkills' || element.id=="secondarySkills") i = 150;
        else if(element.id == 'addressLine1' || element.id == 'resAddressLine1') i = 200;
        else if(element.id == 'city' || element.id == 'resCity' || element.id == 'duration') i = 25;
        else if(element.id == 'loginId' || element.id == 'password' || element.id == 'firstName' || element.id == 'lastName' || element.id == 'middleName'
        || element.id == 'referredBy' || element.id == 'recCity' || element.id == 'customerId' || element.id == 'partnerId' || element.id == 'typeOfResale' || element.id == 'targetRate') i = 30;
        else if(element.id == 'customerPwd' || element.id == 'partnerPwd') i = 8;
        else if(element.id == 'comments' && (action == 'addContact' || action == 'editContact')) i = 255;
        else if(element.id == 'comments' || element.id == 'equipmentNeeded') i = 1000;
        else if(element.id == 'title' || element.id == 'specialization' || element.id == 'mspVendor') i = 40;
        else if(element.id == 'reportingAddress' || (element.id == 'comments' && action == 'newGreenSheetSubmit') || element.id == 'billingAddress' || element.id == 'expensesDetails' || element.id == 'recRecruiterComments') i = 500;
        else if(element.id == 'functions' || element.id == 'responsibilities' || element.id == 'recComments' || element.id=="functions") i = 5000;
        else if(element.id == 'education') i = 250;
        else if(element.id == 'addressLine1') i = 200;
        else if(element.id == 'city') i = 25;
        else if(element.id == 'ibmPPANo') i=40;
        else if(element.id == 'ibmSiteNo') i=40;
        else if(element.id == 'comments1') i = 200;
        else if(element.id == 'practiceName') i = 50;
        else if(element.id == 'sviNum') i = 15;
        
        if(element.value.replace(/^\s+|\s+$/g,"").length>i){
            //subStringValue(i,element,"The "+element.id+" must be less than "+i+" characters");
            str = new String(element.value);
            element.value=str.substring(0,i);
            
            alert("The "+element.id+" must be less than "+i+" characters");
            element.focus();
            return false;
        }

       
   if(textbox1.text.containts("@") || textbox1.text.containes("-"))
        return true;
    }    
};

/* new function for green sheets validations */
function fieldLengthValidator1(element){
  //alert("In Field Length validator SCV11111111111");
    var url = element.form.action;
    var action1 = url.lastIndexOf("/");
    var action2 = url.substr(action1+1,url.length);
    var action3 = action2.lastIndexOf(".");
    var action = action2.substr(0,action3);
    var i = 0;
    if (element.value != null && (element.value != "")) {
        //alert("In if standard client validation");
        if(element.id == 'accountName' || element.id == 'synonyms') i = 60;
        else if(element.id == 'url' || element.id == 'leadSource' || element.id == 'taxId' || element.id == 'addressLine2' || element.id == 'resAddressLine2'
        || element.id == 'attachmentName' || element.id == 'leadSource' || element.id == 'officeEmail' || element.id == 'personalEmail' || element.id == 'reportingManager'
        || element.id == 'fName' || element.id == 'lName' || element.id == 'mName' || element.id == 'vendorContactPerson' || element.id == 'billingManager'
        || element.id == 'opportunity' || element.id == 'jobTitle' || element.id == 'conatctFName' || element.id == 'conatctLName' || element.id == 'contactName' || element.id == 'partNumber') i = 50;
        else if(element.id == 'fax' || element.id == 'contactFaxNumber' || element.id == 'vendorFaxNumber') i = 16;
        else if(element.id == 'zip' || element.id == 'resZip') {
            i = 15;
            validatenumber(element);
        }
        else if(element.id == 'accountTeam' || element.id == 'aliasName') i = 20;
        else if(element.id == 'noOfEmployees' || element.id ==  'mailStop' || element.id == 'resMailStop') i = 10;
        else if(element.id == 'stockSymbol1' || element.id == 'stockSymbol2') i = 12;
        else if(element.id == 'description') i = 100;
        else if(element.id == 'Description') i = 250;
        
        else if(element.id == 'applications' || element.id == 'hardware' || element.id == 'softwares' || element.id == 'databases' || element.id == 'recSkills') i = 150;
        else if(element.id == 'addressLine1' || element.id == 'resAddressLine1') i = 200;
        else if(element.id == 'city' || element.id == 'resCity') i = 25;
        else if(element.id == 'loginId' || element.id == 'password' || element.id == 'firstName' || element.id == 'lastName' || element.id == 'middleName'
        || element.id == 'referredBy' || element.id == 'recCity' || element.id == 'customerId' || element.id == 'partnerId' || element.id == 'typeOfResale' || element.id == 'vendorTaxId' || element.id == 'invFaxNumber') i = 30;
        else if(element.id == 'customerPwd' || element.id == 'partnerPwd') i = 8;
        else if(element.id == 'comments' && (action == 'addContact' || action == 'editContact')) i = 255;
        else if(element.id == 'equipmentNeeded') i = 1000;
        else if(element.id == 'vendorComments' || element.id == 'invAttnComments' || element.id == 'rejectesReason') i = 1500;
        else if(element.id == 'vendorPaymentAddress') i=400;
        else if(element.id == 'title' || element.id == 'specialization' || element.id == 'mspVendor') i = 40;
       else if(element.id == 'reportingAddress' || (element.id == 'comments' && (action == 'newGreenSheetSubmit' || action == 'editGeensheet')) || element.id == 'billingAddress' || element.id == 'expensesDetails' || element.id == 'softDetails') i = 500;
        else if(element.id == 'functions' || element.id == 'responsibilities' || element.id == 'recComments') i = 5000;
        else if(element.id == 'education') i = 250;
        else if(element.id == 'addressLine1') i = 200;
        else if(element.id == 'city') i = 25;
        else if(element.id == 'iBMPPANo') i=40;
        else if(element.id == 'iBMSITENo') i=40;
        else if(element.id == 'comments1') i = 200;
        if(element.value.replace(/^\s+|\s+$/g,"").length>i){
            //subStringValue(i,element,"The "+element.id+" must be less than "+i+" characters");
            str = new String(element.value);
            element.value=str.substring(0,i);
            
            alert("The "+element.id+" must be less than "+i+" characters");
            element.focus();
            return false;
        }

       
   if(textbox1.text.containts("@") || textbox1.text.containes("-"))
        return true;
    }    
};



/* fuction to restrict the | symbol in description section */
function symbolRestForDesc(str)
{
//alert(str);

if(str.indexOf("|") != -1)
{
alert("| symbol not allowed ");
//str = document.getElementById('description').value;
//last character in the string
//alert( "Last character:"+str.charAt( str.length-1 ) );
// remove last character from string
if(str.charAt(str.length-1) == "|") {
//alert( str.slice(0, -1) );
//str =str.slice(0, -1);

//alert("After removing last char:"+str);

activityForm.description.value = str.slice(0, -1);
}
}
};

/* fuction to restrict the | symbol in Comments section */
function symbolRestForComments(str)
{
//alert(str);

if(str.indexOf("|") != -1)
{
alert("| symbol not allowed ");
//str = document.getElementById('description').value;
//last character in the string
//alert( "Last character:"+str.charAt( str.length-1 ) );
// remove last character from string
if(str.charAt(str.length-1) == "|") {
//alert( str.slice(0, -1) );
//str =str.slice(0, -1);

//alert("After removing last char:"+str);

activityForm.comments.value = str.slice(0, -1);
}
}
};



/*function subStringValue(i,element,message) {
    str = new String(element.value);
    element.value=str.substring(0,i);
 
    alert("The "+element.id+" must be less than "+i+" characters");
    element.focus();
    return false;
}*/

function validatenumber(xxxxx) {
    
    var maintainplus = '';
    var numval = xxxxx.value
    if ( numval.charAt(0)=='+' ){ var maintainplus = '+';}
    curnumbervar = numval.replace(/[\\A-Za-z!"?$%^&*+_={};:'@#~,?\/<>?|`?\]\[]/g,'');
    xxxxx.value = maintainplus + curnumbervar;
    var maintainplus = '';
    // alert("enter integers only");
    xxxxx.focus;
}

function attachmentValidate(element) {
    if (element.value == null || (element.value == "")) {
        alert("Please Enter valid File Path");
        return(true);
    }
    return (false);
}


function OpportunityValidation(){
//    if(document.getElementById("opportunity").value==null || document.getElementById("opportunity").value=='' ||
//    document.getElementById("architectId").value=='-1' || document.getElementById("regionalMgrId").value=='-1' || 
//    document.getElementById("insideSalesId").value=='' ||
//    document.getElementById("bdmId").value=='-1'  || document.getElementById("dueDate").value=='' ||
//    document.getElementById("offshorePMId").value=='-1' ||  document.getElementById("type").value=='-1' ||
//    document.getElementById("vpId").value=='-1' ||
//    document.getElementById("stage").value=='-1' ||
//    document.getElementById("practiceMgrId").value=='-1' || 
//    document.getElementById("value").value=='' ||
//    document.getElementById("description").value==''
//    ){
//        alert('enter mandatory fields');
//        return false;
//    } 
      if(document.getElementById("opportunity").value==null || document.getElementById("opportunity").value=='' ||
//    document.getElementById("architectId").value=='-1' ||  
    document.getElementById("insideSalesId").value=='-1' ||
    document.getElementById("dueDate").value=='' ||
      document.getElementById("type").value=='-1' ||
    document.getElementById("stage").value=='-1' || document.getElementById("value").value=='' ||
    document.getElementById("description").value=='' ||  document.getElementById("practiceName").value=='' ||  document.getElementById("state").value==''||  (document.getElementById("regionalMgrId").value=='-1'&&document.getElementById("type").value!='Software Renewal') ){
        alert('enter mandatory fields');
        return false;
    } 
    else true;
    
}

function requirementCheck() {

// alert("location ====="+document.getElementById("location").value);
   /*alert("assignedTo ====="+document.getElementById("assignedTo").value);
   alert("assignedTo ====="+document.getElementById("assignedTo1").value);
   alert("assignedTo ====="+document.getElementById("assignedTo2").value);
   alert("assignedTo ====="+document.getElementById("assignedTo3").value);*/

    if(document.getElementById("jobTitle").value=='' || document.getElementById("practiceId").value=='-1' ||
    document.getElementById("recSkills").value=='' || document.getElementById("country").value=='-1'  ||
    
    document.getElementById("startDate").value=='' || document.getElementById("location").value=='-1' || document.getElementById("functions").value=='' || document.getElementById("targetRate").value=='') {
        alert('enter mandatory fields');
        return false;
    }
    var status=document.requirementForm.status.value;
     if(status=='lost'){
        if(document.getElementById("rejectReason").value=='-1'){
              alert('Please select Reject Reason');
            return false;
        }
    }
    if(requirementForm.assignedTo.value =='' && requirementForm.assignedTo1.value =='' && requirementForm.assignedTo2.value =='' && requirementForm.assignedTo3.value =='' && requirementForm.roleName.value == 'Recruitment' )
   {
   alert("Please select one of the value from Recruiter1 ,Recruiter2 ,Presales1, Presales2");
   return false;
   }

if(document.getElementById("startDate").value != '' && document.getElementById("endDate").value != ''){
        var startDate = document.getElementById('startDate').value;
        var endDate = document.getElementById('endDate').value;
        var mm = startDate.substring(0,2);
        var dd = startDate.substring(3,5);
        var yyyy = startDate.substring(6,10);
        var mm1 = endDate.substring(0,2);
        var dd1 = endDate.substring(3,5);
        var yyyy1 = endDate.substring(6,10);
        if(yyyy1 < yyyy) {
            alert('Start Date is older than End Date');
            return false;
        }
        else if((yyyy1 == yyyy) && (mm1 < mm)) {
            alert('Start Date is older than End Date');
            return false;
        }
        else if((yyyy1 == yyyy) && (mm1 == mm) && (dd1 < dd)) {
            alert('Start Date is older than End Date');
            return false;
        }
    }
   
else return true;
}

function requirementCheckUpdate() {
    if(document.getElementById("jobTitle").value=='' || document.getElementById("practiceId").value=='-1' ||
    document.getElementById("recSkills").value=='' || document.getElementById("country").value=='-1'  ||
    document.getElementById("startDate").value=='' || document.getElementById("functions").value=='' ) {
        alert('enter mandatory fields');
        return false;
    }
    if(document.getElementById("status").value=='lost'){
        if(document.getElementById("status").value=='-1'){
              alert('Please select Reject Reason');
            return false;
        }
    }
    if(document.getElementById("startDate").value != '' && document.getElementById("endDate").value != ''){
        var startDate = document.getElementById('startDate').value;
        var endDate = document.getElementById('endDate').value;
        var mm = startDate.substring(0,2);
        var dd = startDate.substring(3,5);
        var yyyy = startDate.substring(6,10);
        var mm1 = endDate.substring(0,2);
        var dd1 = endDate.substring(3,5);
        var yyyy1 = endDate.substring(6,10);
        if(yyyy1 < yyyy) {
            alert('Start Date is older than End Date');
            return false;
        }
        else if((yyyy1 == yyyy) && (mm1 < mm)) {
            alert('Start Date is older than End Date');
            return false;
        }
        else if((yyyy1 == yyyy) && (mm1 == mm) && (dd1 < dd)) {
            alert('Start Date is older than End Date');
            return false;
        }
    }else return true;
}

function handleEnter(field, event) {
    var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
    if (keyCode == 13) {
        var i;
        for (i = 0; i < field.form.elements.length; i++)
            if (field == field.form.elements[i])
                break;
        i = (i + 1) % field.form.elements.length;
        field.form.elements[i].focus();
        return false;
    } 
    else
        return true;
};

// DOUBLE VALUE : FORMAT START
var curnumbervar;
function onCheck(element) {
    if(element.value != "0.0"){ 
        var numval = element.value;
        curnumbervar = numval.replace(/[\\A-Za-z!\-"$%^&*+_={};:'@#~,\/<>?|`\]\[]/g,'');
        element.value = curnumbervar;
        element.focus;
    }
}
function validateCountry(){
    var country = document.getElementById("country").value;
    var accName = document.getElementById("accountName").value;
    if(country == "-1"){
        alert("Enter Country");
        return false;
    } if(accName.trim().length == 0)
        {
             alert("Enter AccountName");
        return false;
        }
    
}

//  Data disbling
function validateByAddress(element){
  //alert("hi"+document.getElementById("conatctFName").value.length+"-----------"+document.getElementById("conatctLName").value.length);

    if(document.getElementById("conatctFName").value.length > 0 || document.getElementById("conatctLName").value.length > 0){
    //alert("if1");
      document.getElementById("state").value = "";
      document.getElementById("state").disabled="true";
      
      document.getElementById("zip").value = "";
      document.getElementById("zip").disabled="true" ;
      
    }
    if(document.getElementById("conatctFName").value.length == 0 && document.getElementById("conatctLName").value.length == 0){
        //alert("dis1");
        document.getElementById("state").disabled="false";
        document.getElementById("zip").disabled="false" ;
    }
};

 function lengthAndNamevalidator(element){
// alert(element.value);
 var i = 60;
 if(element.value.replace(/^\s+|\s+$/g,"").length>60){
    str = new String(element.value);
    element.value=str.substring(0,i);
    alert("The "+element.Id+" must be less than "+i+" characters");
    element.focus();
   return false;
}
var res = element.value;
//alert("&index"+res.indexOf("&"));
element.value=res.replace("&"," &amp; ");
//  alert(element);
}





function fieldLengthValidator2(element){
      // alert("hi");
    if(element.id=="jobtitle"){
        i=40;
    }
    if(element.id=="jobtagline"){
        i=40;
    }
    if(element.id=="jobposition"){
        i=60;
    }
    if(element.id=="jobqulification"){
        i=60;
    }
    if(element.id=="jobcountry"){
        i=10;
    }
       if(element.id=="jobstatus"){
        i=10;
    }
    if(element.id=="jobshifits"){
        i=50;
    }
    if(element.id=="location"){
        i=40;
    }
     if(element.id=="jobdescription1"){
        i=2450;
    }if(element.id=="jobdescription2"){
        i=2450;
    }
    if(element.id=="jobtechnical"){
        i=500;
    }
      if(element.id=="jobshiftskills"){
        i=500;
    }
      if(element.id=="notes"){
        i=200;
    }if(element.id=="jobDepartment"){
        i=100;
    }if(element.id=="jobHireType"){
        i=100;
    }
    
    if(element.value.replace(/^\s+|\s+$/g,"").length>i) {
            str=new String(element.value);
            element.value=str.substring(0,i);
            alert("The "+element.id+" length must be less than "+i+" characters");
            element.focus();
            return false;
        }
        return true;
}



function displayMandatory(obj){
    if(obj.value=='Software Renewal'){
        $("#practiceSalesStar").hide();
        
    }else {
        $("#practiceSalesStar").show();
    }
}