/*
File:       validate.js
Author:     Venkateswara Rao Nukala
Version:    1.0
Email:      vnukala@miraclesoft.com
Web:        www.miraclesoft.com
Description:    This file contains the list of Validation routines to be used for Client Side
                Validations.
 */


//********************************************************************************
// Sample code to put in Jsp Page for Validation
//<script type=�text/javascript� language="�javascript�" xsrc=�validate.js� mce_src=�validate.js� ></script>
//<script type=�text/javascript� language="�Javascript�">
//<!�
////Create an array of ControlDetails that need validating.
////Format(elementid,required,maxlength,minlength,email,decimal,nonnegdecimal)var controlsToValidate =
//new Array(
///* Name is required has no max must be longer than 3 characters*/
//new ControlDetail(�txtName�,1,-1,3,0,0,0),
///* Age is not required but must be a non negative number */
//new ControlDetail(�txtAge�,0,-1,-1,0,0,1),
///* IQ Required but can be negative */
//new ControlDetail(�txtIQ�,1,-1,-1,0,1,0),
///* Email required must be under 20 characters */
//new ControlDetail(�txtEmail�,1,20,-1,1,0,0)
//)
//
//
//�>
//</script>
//********************************************************************************

//********************************************************************************
//
// Also have to add this to the Form Elemtn in JSP Page for Trigeting the Validation
//  on submit
//onSubmit=�return Validate(controlsToValidate);�
//
//********************************************************************************

function ControlDetail( elementid, required, maxlength, minlength, email, decimal, nonnegdecimal, ipaddress) {
    this.elementid = elementid;
    this.required = required;
    this.maxlength = maxlength;
    this.minlength = minlength;
    this.email = email;
    this.decimal = decimal;
    this.nonnegdecimal = nonnegdecimal;
    this.ipaddress = ipaddress;
}

//********************************************************************************
//** Begin All Validation Functions **
function ContainsValue(element) {
    return document.getElementById(element).value.length != 0;
}

function IsOverMaxLength(element,maxlength) {
    return document.getElementById(element).value.length > maxlength;
}

function IsUnderMinLength(element,minlength) {
    return document.getElementById(element).value.length < minlength;
}

function RegExTest(element,expression) {
    //alert(element.value)
    //alert(expression)
    return element.value.match(expression) != null;
}

function IsEmail(element) {
    var emailRE = "([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})";
    //alert(emailRE)
    return RegExTest(element,emailRE);
}

function IsDecimal(element) {
    var decimalRE = "^(\\+|-)?[0-9][0-9]*(\\.[0-9]*)?$";
    return RegExTest(element,decimalRE);
}

function IsNonNegDecimal(element) {
    var nonnegdecimalRE = "^[0-9][0-9]*(\\.[0-9]*)?$";
    return RegExTest(element,nonnegdecimalRE);
}
//** End All Validation Functions **
//********************************************************************************

//********************************************************************************
//** Main Validation Routine **

function Validate(controlsToValidate) {
    valid = true;
    
    var message = "Warning:\n\n";
    
    for(var i = 0;i < controlsToValidate.length;i++) {
        if(controlsToValidate[i].required == 1) {
            //Validate that a value has been entered
            if(ContainsValue(controlsToValidate[i].elementid) == false) {
                valid = false;
                message += document.getElementById(controlsToValidate[i].elementid).name + " is required.\n";
            }
        }
        
        if(controlsToValidate[i].maxlength != -1) {
            //Validate that the value is not over max
            if(IsOverMaxLength(controlsToValidate[i].elementid, controlsToValidate[i].maxlength) == true) {
                valid = false;
                message += document.getElementById(controlsToValidate[i].elementid).name + " is to long.\n";
            }
        }
        
        if(controlsToValidate[i].minlength != -1) {
            
            //Validate that the value is not under min
            if(IsUnderMinLength(controlsToValidate[i].elementid, controlsToValidate[i].minlength) == true) {
                valid = false;
                message += document.getElementById(controlsToValidate[i].elementid).name + " is to short.\n";
            }
        }
        
        if(controlsToValidate[i].email == 1) {
            //Validate that the value is an email
            if(IsEmail(document.getElementById(controlsToValidate[i].elementid)) == false) {
                valid = false;
                message += document.getElementById(controlsToValidate[i].elementid).name + " is not a valid email address.\n";
            }
        }
        
        if(controlsToValidate[i].decimal == 1) {
            //Validate that the value is decimal
            if(IsDecimal(document.getElementById(controlsToValidate[i].elementid)) == false) {
                valid = false;
                message += document.getElementById(controlsToValidate[i].elementid).name + " is not a decimal.\n";
            }
        }
        
        if(controlsToValidate[i].nonnegdecimal == 1) {
            //Validate that the value is decimal
            if(IsNonNegDecimal(document.getElementById(controlsToValidate[i].elementid)) == false) {
                valid = false;
                message += document.getElementById(controlsToValidate[i].elementid).name + " is not a positive decimal.\n";
            }
        }
        
        if(controlsToValidate[i].ipaddress == 1) {
            //Validate that the value is decimal
            if(IsIpAddress(document.getElementById(controlsToValidate[i].elementid)) == false) {
                valid = false;
                message += document.getElementById(controlsToValidate[i].elementid).name + " is not an IP Address.\n";
            }
        }
    }
    
    if (valid == false) {
        alert(message);
    }
    return valid;
}

function checkDecimal(myForm) {
 
    if( /^\s*(\+|-)?((\d+(\.\d+)?)|(\.\d+))\s*$/.test(myForm)){     
        return(true)
    }
    document.frmAddGreenSheet.clientBillingRate.value="";
    alert("Enter Decimal Number")
    return(false)
}

//** END Main Validation Routine **
//********************************************************************************


/*
 
//PHONE NUMBER FORMAT SCRIPT :START
 
function Miracle_CheckTextFieldLength(txtField,len,msg){
 
    if (txtField.value != null && (txtField.value != "")) {
        if(txtField.value.replace(/^\s+|\s+$/g,"").length>len){
 
            str = new String(txtField.value);
            txtField.value=str.substring(0,len);
            if (msg == ""){
                alert("The "+txtField.id+" Cannot Exceed" + len + "Characters");
                txtField.focus();
            }
            else {
                alert(Msg);
            }
        }
        return (false);
    }
    return (true);
};
 
function phoneNoFormat(txtField) {
 
    //  Replace [ or ( or ) or . or - or x or Space or comma with Nothing 
    //  the Regular Expression is encapsulated in / and /
    //  the g at the end is to do global replacement in the string    
    var str = txtField.value;
 
    txtField.value=str.replace(/[\(\)\.\-\x\s,]/g, "");
    txtField.value=str.replace(/x/g, "");
 
    var num=txtField.value;
    var _return = txtField.value;
    //alert(txtField.value);
    //  If the Phone Number is Less than 10 Characters than we have to assume
    //  that there is no Country Code Present and proceed ahead
    if(num.length == 10) { 
        _return="(";
        var ini = num.substring(0,3);
        _return+=ini+")";
        var st = num.substring(3,6);
        _return+="-"+st+"-";
        var end = num.substring(6,10);
        _return+=end;
 
        txtField.value ="";
        txtField.value =_return;        
    }
    //  This case handles the Situation when the Country code is provides or
    //  may be there is an Phone No. Extension.
    /*else if(num.length > 10) {
        var country;
        //  Check if the First Char is 001 or 1 then it is a US Phone Number
        if (num.substring(0,1) == "1") {
            num = num.substring(1);
            country = "USA";
        }
        else if (num.substring(0,3) == "001") {
            num = num.substring(3);
            country = "USA";
        }
        //  Checking for India Phone Numbers
        else if (num.substring(0,3) == "091") {
            num = num.substring(3);
            country = "INDIA";
        }
        //  Checking for UK Phone Numbers
        else if (num.substring(0,3) == "044") {
            num = num.substring(3);
            country = "UK";
        }
 
        //  Now append the Country Code to the Phone No and format
        //  according to Country Format
        if (country == "USA")
            _return = "001-";
        var ini = num.substring(0,3);
        _return= _return + "(" + ini + ")";
        var st = num.substring(3,6);
        _return+="-" + st + "-";
        var end = num.substring(6,10);
        _return+=end;
        // Check if there is an Extension
        if (num.length > 10) {            
            var ext = num.substring(10);
            _return+= "x" + ext;
        }
        //  Now append the Country Code for INDIA to the Phone No and format
        //  according to Country Format
        else if (country == "INDIA") {
            _return = "091-";
            //  It is a Cellular Phone No in India
            if (num.substring(0,1) == "9") {
                var ini = num.substring(0,4);
                _return= _return + "(" + ini + ")";
                var st = num.substring(4,7);
                _return+="-" + st + "-";
                var end = num.substring(7,10);
                _return+=end;                
            }
            else {
                var ini = num.substring(0,4);
                _return= _return + "(" + ini + ")";
                var st = num.substring(4,7);
                _return+="-" + st + "-";
                var end = num.substring(7,10);
                _return+=end;
                // Check if there is an Extension
                if (num.length > 10) {            
                    var ext = num.substring(10);
                    _return+= "x" + ext;
                }
            }
        }
        else if (country == "UK")
            _return = "044-";
 
        txtField.value ="";
        txtField.value =_return;
        return false;
    }
    return _return;
}*/


//PHONE NUMBER FORMAT SCRIPT :END

function checkGreenSheetForm(){    
    
    var poType = document.frmAddGreenSheet.poType.value;   
    
    var billingAddress = document.frmAddGreenSheet.billingAddress.value;   
    if(billingAddress == ""){
        alert("Enter Billing Address");
        return false;
    }
    
    var primarySalesPerson = document.frmAddGreenSheet.primarySalesPerson.value; // select
    if(primarySalesPerson == "-1"){
        alert("Enter PrimarySales Person Name");
        return false;
    }
    
    var primarySalesManager = document.frmAddGreenSheet.primarySalesManager.value; // select
    if(primarySalesManager == "-1"){
        alert("Enter PrimarySales Manager Name");
        return false;
    }
    
    var secondarySalesPerson = document.frmAddGreenSheet.secondarySalesPerson.value; // select    
    if(secondarySalesPerson == "-1"){
        alert("Enter SecondarySales Person Name");
        return false;
    }

    var reportingMGRPhon = document.getElementById('reportingMGRPhone');    
    var reportingMGRPhone = reportingMGRPhon.value;
    if(reportingMGRPhone.length > 1) {
        return formatPhone(reportingMGRPhon);
    }
    var custName = document.frmAddGreenSheet.customerName.value;
    if(custName == ""){
        alert("Enter Customer Name");
        return false;
    }   
    
    var country = document.frmAddGreenSheet.country.value;
    if(country == "-1"){
        alert("Enter Country");
        return false;
    }
    
    if(poType == 'Services'){        
        var startDate = document.frmAddGreenSheet.startDate.value;                
        var endDate = document.frmAddGreenSheet.endDate.value;        
        
        if(startDate==""){
            alert("Enter PO StartDate");
            return false;
        }
        if(endDate==""){
            alert("Enter PO EndDate");
            return false;
        }
    }
    
    if(poType == 'Expenses') {
        var startDate = document.frmAddGreenSheet.startDate.value;                          
        
        if(startDate==""){
            alert("Enter PO StartDate");
            return false;
        }
    }
    
    if(poType == 'FixedBid'){
        var startDate = document.frmAddGreenSheet.startDate.value;        
        var endDate = document.frmAddGreenSheet.endDate.value;  
        if(startDate==""){
            alert("Enter Proj. Start Date");
            return false;
        }        
        if(endDate==""){
            alert("Enter Proj. EndDate");
            return false;
        }        
    }
    
    if(poType == 'Software' || poType == 'Support' || poType == 'Others'){
        var startDate = document.frmAddGreenSheet.startDate.value;        
        
        if(startDate==""){
            alert("Enter PO Issue Date");
            return false;
        }
        document.getElementById('consStartDate').value = startDate;  
    }
    
    if(poType == 'Services' || poType == 'Expenses' || poType == 'Software'){
        // consultant tab - visible
        var fName = document.frmAddGreenSheet.fName.value;
        if(fName == ""){
            alert("Enter First Name");
            return false;
        }
        
        var lastName = document.frmAddGreenSheet.lastName.value;
        if(lastName == ""){
            alert("Enter Last Name");
            return false;
        }
        
        //var units = document.frmAddGreenSheet.units.value;
        
        var clientBillingRate = document.frmAddGreenSheet.clientBillingRate.value;
      /*  if(poType!='Software'){
                    if(clientBillingRate == "0.0"){
                        alert("Enter Client Billing Rate");
                        return false;
                    }
        }*/
        //var clientBillingRateType = document.frmAddGreenSheet.clientBillingRateType.value;    
        
        var consStartDate = document.frmAddGreenSheet.consStartDate.value;
        if(consStartDate == ""){
            alert("Enter Consultant StartDate");
            return false;
        }
    }
/*Newly Added 11062013 start*/
if(poType == 'Services' || poType == 'Expenses') {
      var clientBillingRate = document.frmAddGreenSheet.clientBillingRate.value;
        if(clientBillingRate == "0.0"){
            alert("Enter Client Billing Rate");
            return false;
        }
}
   
/*Newly Added 11062013 end*/

    if(document.frmAddGreenSheet.consultantType[1].checked == true ) {
        var agencyName = document.frmAddGreenSheet.agencyName.value;
        //var vendorUnits = document.frmAddGreenSheet.vendorUnits.value;
        var vendorRate = document.frmAddGreenSheet.vendorRate.value;    
        if(agencyName == "-1"){
            alert("Enter Agency Name");
            return false;
        }
        if(vendorRate == "0.0" || vendorRate == ""){
            alert("Enter Vendor Rate");
            return false;
        }
    }    



    var gencomments = document.frmAddGreenSheet.rejectesReason.value; 
    //alert("action ----- "+document.frmAddGreenSheet.action);
    if(gencomments == ""){
        alert("Enter Comments");
        return false;
    }
    
    
}    
    
    /*
    var startDate = document.frmAddGreenSheet.startDate.value;     
    var consStartDate = document.frmAddGreenSheet.consStartDate.value;
    var billingAddress = document.frmAddGreenSheet.billingAddress.value;     
    var primarySalesPerson = document.frmAddGreenSheet.primarySalesPerson.value; // select
    var primarySalesManager = document.frmAddGreenSheet.primarySalesManager.value; // select
    var secondarySalesPerson = document.frmAddGreenSheet.secondarySalesPerson.value; // select     
    if(document.frmAddGreenSheet.consultantType[1].checked == true ) {
        var clientBillingRate = document.frmAddGreenSheet.clientBillingRate.value;
        var clientBillingRateType = document.frmAddGreenSheet.clientBillingRateType.value;
     
        var agencyName = document.frmAddGreenSheet.agencyName.value;
        var vendorUnits = document.frmAddGreenSheet.vendorUnits.value;
        var vendorRate = document.frmAddGreenSheet.vendorRate.value;
        if(clientBillingRate == "0.0" || clientBillingRateType == "-1" || agencyName == "-1" || vendorUnits == "-1" || vendorRate== " ") {
            alert("Enter Mandatory Fields");
            return false;
        }
    }    
    if(document.frmAddGreenSheet.consultantType[0].checked == true ) {
     
        var clientBillingRate = document.frmAddGreenSheet.clientBillingRate.value;
        var clientBillingRateType = document.frmAddGreenSheet.clientBillingRateType.value;
        //var units = document.frmAddGreenSheet.units.value;
        if(agencyName == "-1"){
            document.frmAddGreenSheet.agencyName.value = "";
            }
        if(vendorUnits == "-1"){
            document.frmAddGreenSheet.vendorUnits.value = "";
            }
     
        if(clientBillingRate == "0.0" || clientBillingRateType == "-1") {
            alert("Enter Mandatory Fields");
            return (false);
        }        
    }    
    if(document.frmAddGreenSheet.id.value > 0){
        if(fName=="" || lastName==""|| phone==""|| reportingManager==""|| reportingMGRPhone==""|| expenses=="-1"|| reportingManagerEmail==""|| expensesDetails==""||  billingManager==""|| billingphone==""|| billingManagerEmail==""|| scopeOfWork=="-1"|| billingAddress==""|| invoicing=="-- Please Select --" ||  renewalEmail=="" || renewalIntEmail=="" || poType=="-- Please Select --" || poNumber=="" || poLineno=="" || intRefcode=="" || primarySalesPerson=="-- Please Select --" || primarySalesManager=="-- Please Select --" || secondarySalesPerson=="-- Please Select --" || startDate=="" || endDate=="" || consStartDate=="" || consEndDate=="" || nocDate== ""
        || fName==null || lastName==null ||  phone==null || reportingManager==null || reportingMGRPhone==null || expenses==null || reportingManagerEmail==null || expensesDetails==null || billingManager==null || billingphone==null || billingManagerEmail==null || scopeOfWork==null || billingAddress==null || invoicing==null || empCode==null || renewalEmail==null || renewalIntEmail==null || poType==null || poNumber==null || poLineno==null || intRefcode==null || primarySalesPerson==null || primarySalesManager==null || secondarySalesPerson==null || startDate==null || endDate==null || consStartDate==null || consEndDate==null || nocDate== null){
            alert("Enter Mandatory Fields");
            return false;
        }else{
            return true;
        }
    }else{
        if(file=="" || fName=="" || lastName==""|| phone==""|| reportingManager==""|| reportingMGRPhone==""|| expenses=="-1"|| reportingManagerEmail==""|| expensesDetails==""||  billingManager==""|| billingphone==""|| billingManagerEmail==""|| scopeOfWork=="-1"|| billingAddress==""|| invoicing=="-- Please Select --" || renewalEmail=="" || renewalIntEmail=="" || poType=="-- Please Select --" || poNumber=="" || poLineno=="" || intRefcode=="" || primarySalesPerson=="-- Please Select --" || primarySalesManager=="-- Please Select --" || secondarySalesPerson=="-- Please Select --" || startDate=="" || endDate=="" || consStartDate=="" || consEndDate=="" || nocDate== ""
        || fName==null || lastName==null ||  phone==null || reportingManager==null || reportingMGRPhone==null || expenses==null || reportingManagerEmail==null || expensesDetails==null || billingManager==null || billingphone==null || billingManagerEmail==null || scopeOfWork==null || billingAddress==null || invoicing==null || empCode==null || renewalEmail==null || renewalIntEmail==null || poType==null || poNumber==null || poLineno==null || intRefcode==null || primarySalesPerson==null || primarySalesManager==null || secondarySalesPerson==null || startDate==null || endDate==null || consStartDate==null || consEndDate==null || nocDate== null){
            alert("Enter Mandatory Fields");
            return false;
        }else{
            return true;
        }
    }
    if(file=="" || fName=="" || lastName==""|| phone==""|| reportingManager==""|| reportingMGRPhone==""|| expenses=="-1"|| reportingManagerEmail==""|| expensesDetails==""||  billingManager==""|| billingphone==""|| billingManagerEmail==""|| scopeOfWork=="-1"|| billingAddress==""|| invoicing=="-- Please Select --"  
    || fName==null || lastName==null ||  phone==null || reportingManager==null || reportingMGRPhone==null || expenses==null || reportingManagerEmail==null || expensesDetails==null || billingManager==null || billingphone==null || billingManagerEmail==null || scopeOfWork==null || billingAddress==null || invoicing==null){
        alert("Enter Mandatory Fields");
        return false;
    }else{
        return true;
    }*/




function checkGreenSheetSearchForm(){
    var custName = document.frmAddGreenSheet.customerName.value;
    
    if(custName=="" || custName==null){
        alert("Enter Mandatory Fields");
        return false;
    }else{
        return true;
    }
}


function custNameValidate(){
    var custName= document.frmGreenSheetSearch.custName;
    
    
    if (custName.value != null && (custName.value != "")) {
        if(custName.value.replace(/^\s+|\s+$/g,"").length>60){
            str = new String(document.frmGreenSheetSearch.custName.value);
            document.frmGreenSheetSearch.custName.value=str.substring(0,60);
            alert("The customerName must be less than 60 characters");
            document.frmGreenSheetSearch.custName.focus();
        }
        return (false);
    }
    
    return (true);
};
/*function validatenumber(xxxxx) {
 
    var numval = xxxxx.value
    curnumbervar = numval.replace(/[\\A-Za-z!"$%^&*+_={};:'@#~,.\/<>?|`\]\[]/g,'');
    xxxxx.value = curnumbervar;
    var maintainplus = '';
    //alert("enter integers only");
    xxxxx.focus;
}
 
function validateEmail(element) {
    //  Eliminate all the Blanks and Commas and Other Special Characters
 
    //  Check if the Length is less than 60 if not trim the String to 60 Characters
    //  and place it back into the Element
    if (element.value != null && (element.value != "")) {
 
        if(element.value.replace(/^\s+|\s+$/g,"").length>60){            
            str = new String(element.value);
            element.value=str.substring(0,60);   
 
            alert("The EmailAddress must be less than 60 characters");
 
        }
        //element.focus();
        Miracle_checkEmail(element);
        return (false);
    }     
    return (true);
 
}
function Miracle_checkEmail(myForm) {
 
    if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(myForm.value)){
 
    }
    else {
        myForm.value="";
        alert("Invalid E-mail Address! Please re-enter.")
 
        return (false);
    }
    return (true);
}
 
 
 
/*function valueCheck(myForm)  
              {
 
              var is_value = myForm.indexOf('miraclesoft.com');
              if (is_value==-1)
                { 
               document.frmAddGreenSheet.reportingManagerEmail.value="";
              document.frmAddGreenSheet.billingManagerEmail.value="";
 
                  alert('You should enter valid Miracle EmailId');
                  }
 
               else
 
                checkEmail(myForm)
}*/


/*function attachmentValidate() {
    var file = document.frmAddGreenSheet.upload;
    if (file.value == null || (file.value == "")) {
        alert("Please Enter valid File Path");
        return(true);
    }
    return (false);
}
 
 
function firstNameValidate(txtField){
    var firstName= txtField;
 
    if (firstName.value != null && (firstName.value != "")) {
        if(firstName.value.replace(/^\s+|\s+$/g,"").length>50){
 
            str = new String(txtField.value);
            txtField.value=str.substring(0,50);
 
            alert("The firstName must be less than 50 characters");
            txtField.focus();
        }
        return (false);
    }
    return (true);
};
 
function lastNameValidate(txtField){        
    firstNameValidate(txtField)        
};
 
function middleNameValidate(txtField){
    firstNameValidate(txtField)
};
 
function phoneValidate(){
    var phone= document.frmAddGreenSheet.phone;
 
 
    if (phone.value != null && (phone.value != "")) {
        if(phone.value.replace(/^\s+|\s+$/g,"").length>20){
 
            str = new String(document.frmAddGreenSheet.phone.value);
            document.frmAddGreenSheet.phone.value=str.substring(0,20); 
 
            alert("The phone must be less than 20 characters");
            document.frmAddGreenSheet.phone.focus();
        }
        return (false);
    }
 
    return (true);
};
 
function reportingManagerValidate(){
    var reportingManager= document.frmAddGreenSheet.reportingManager;
 
 
    if (reportingManager.value != null && (reportingManager.value != "")) {
        if(reportingManager.value.replace(/^\s+|\s+$/g,"").length>50){
 
            str = new String(document.frmAddGreenSheet.reportingManager.value);
            document.frmAddGreenSheet.reportingManager.value=str.substring(0,50);
 
            alert("The reportingManager must be less than 50 characters");
            document.frmAddGreenSheet.reportingManager.focus();
        }
        return (false);
    }
 
    return (true);
};
 
function reportingMGRPhoneValidate(){
    var reportingMGRPhone= document.frmAddGreenSheet.reportingMGRPhone;
 
 
    if (reportingMGRPhone.value != null && (reportingMGRPhone.value != "")) {
        if(reportingMGRPhone.value.replace(/^\s+|\s+$/g,"").length>25){
 
            str = new String(document.frmAddGreenSheet.reportingMGRPhone.value);
            document.frmAddGreenSheet.reportingMGRPhone.value=str.substring(0,25);
 
            alert("The reportingMGRPhone must be less than 25 characters");
            document.frmAddGreenSheet.reportingMGRPhone.focus();
        }
        return (false);
    }
 
    return (true);
};
 
function reportingManagerEmailValidate(){
    var reportingManagerEmail= document.frmAddGreenSheet.reportingManagerEmail;
 
 
    if (reportingManagerEmail.value != null && (reportingManagerEmail.value != "")) {
        if(reportingManagerEmail.value.replace(/^\s+|\s+$/g,"").length>50){
 
            str = new String(document.frmAddGreenSheet.reportingManagerEmail.value);
            document.frmAddGreenSheet.reportingManagerEmail.value=str.substring(0,50);
 
            alert("The reportingManagerEmail must be less than 50 characters");
            document.frmAddGreenSheet.reportingManagerEmail.focus();
        }
        return (false);
    }
 
    return (true);
};
 
function expensesDetailsValidate(){
    var expensesDetails= document.frmAddGreenSheet.expensesDetails;    
 
    if (expensesDetails.value != null && (expensesDetails.value != "")) {
        if(expensesDetails.value.replace(/^\s+|\s+$/g,"").length>500){
 
            str = new String(document.frmAddGreenSheet.expensesDetails.value);
            document.frmAddGreenSheet.expensesDetails.value=str.substring(0,500); 
 
            alert("The ExpensesDetails must be less than 500 characters");
            document.frmAddGreenSheet.expensesDetails.focus();
            return (false);
        }else{
            return (true);
        }
    }else{
        return (false);
    }
};
 
function equipmentNeededValidate(){
    var equipmentNeeded= document.frmAddGreenSheet.equipmentNeeded;
    if (equipmentNeeded.value != null && (equipmentNeeded.value != "")) {
        if(equipmentNeeded.value.replace(/^\s+|\s+$/g,"").length>1000){
            str = new String(document.frmAddGreenSheet.equipmentNeeded.value);
            document.frmAddGreenSheet.equipmentNeeded.value=str.substring(0,1000);
            alert("The equipmentNeeded must be less than 1000 characters");
            document.frmAddGreenSheet.equipmentNeeded.focus();
        }
        return (false);
    }
    return (true);
};
 
function reportingAddressValidate(){
    var reportingAddress= document.frmAddGreenSheet.reportingAddress;
 
 
    if (reportingAddress.value != null && (reportingAddress.value != "")) {
        if(reportingAddress.value.replace(/^\s+|\s+$/g,"").length>500){
 
            str = new String(document.frmAddGreenSheet.reportingAddress.value);
            document.frmAddGreenSheet.reportingAddress.value=str.substring(0,500);
 
            alert("The reportingAddress must be less than 500 characters");
            document.frmAddGreenSheet.reportingAddress.focus();
        }
        return (false);
    }
 
    return (true);
};
 
function billingManagerValidate(){
    var billingManager= document.frmAddGreenSheet.billingManager;
 
 
    if (billingManager.value != null && (billingManager.value != "")) {
        if(billingManager.value.replace(/^\s+|\s+$/g,"").length>50){
 
            str = new String(document.frmAddGreenSheet.billingManager.value);
            document.frmAddGreenSheet.billingManager.value=str.substring(0,50);
            alert("The billingManager must be less than 50 characters");
            document.frmAddGreenSheet.billingManager.focus();
        }
 
        return (false);
    }
 
    return (true);
};
 
function billingphoneValidate(){
    var billingphone= document.frmAddGreenSheet.billingphone;
 
 
    if (billingphone.value != null && (billingphone.value != "")) {
        if(billingphone.value.replace(/^\s+|\s+$/g,"").length>25){
            str = new String(document.frmAddGreenSheet.billingphone.value);
            document.frmAddGreenSheet.billingphone.value=str.substring(0,25); 
            alert("The billingphone must be less than 25 characters");
            document.frmAddGreenSheet.billingphone.focus();
        }
        return (false);
    }
    return (true);
};
 
function billingManagerEmailValidate(){
    var billingManagerEmail= document.frmAddGreenSheet.billingManagerEmail;
    if (billingManagerEmail.value != null && (billingManagerEmail.value != "")) {
        if(billingManagerEmail.value.replace(/^\s+|\s+$/g,"").length>30){
            str = new String(document.frmAddGreenSheet.billingManagerEmail.value);
            document.frmAddGreenSheet.billingManagerEmail.value=str.substring(0,30);   
            alert("The billingManagerEmail must be less than 30 characters");
            document.frmAddGreenSheet.billingManagerEmail.focus();
 
        }
        return (false);
    }
 
    return (true);
};
 
function billingAddressValidate(){
    var billingAddress= document.frmAddGreenSheet.billingAddress;
    if (billingAddress.value != null && (billingAddress.value != "")) {
        if(billingAddress.value.replace(/^\s+|\s+$/g,"").length>500){
            str = new String(document.frmAddGreenSheet.billingAddress.value);
            document.frmAddGreenSheet.billingAddress.value=str.substring(0,500); 
            alert("The billingAddress must be less than 500 characters");
            document.frmAddGreenSheet.billingAddress.focus();
        }
        return (false);
    }
    return (true);
};
 
function agencyNameValidate(){
    var agencyName= document.frmAddGreenSheet.agencyName;    
 
    if (agencyName.value != null && (agencyName.value != "")) {
        if(agencyName.value.replace(/^\s+|\s+$/g,"").length>50){
            str = new String(document.frmAddGreenSheet.agencyName.value);
            document.frmAddGreenSheet.agencyName.value=str.substring(0,50);
            alert("The agencyName must be less than 50 characters");
            document.frmAddGreenSheet.agencyName.focus();
        }
        return (false);
    }
    return (true);
};
 
function vendorPhoneValidate(){
    var vendorPhone= document.frmAddGreenSheet.vendorPhone;
 
 
    if (vendorPhone.value != null && (vendorPhone.value != "")) {
        if(vendorPhone.value.replace(/^\s+|\s+$/g,"").length>25){
            str = new String(document.frmAddGreenSheet.vendorPhone.value);
            document.frmAddGreenSheet.vendorPhone.value=str.substring(0,25);
            alert("The vendorPhone must be less than 25 characters");
            document.frmAddGreenSheet.vendorPhone.focus();
        }
        return (false);
    }
    return (true);
};
 
function vendorEmailValidate(txtField){
    var vendorEmail= txtField;
    if (vendorEmail.value != null && (vendorEmail.value != "")) {
        if(vendorEmail.value.replace(/^\s+|\s+$/g,"").length>30){
 
            str = new String(txtField.value);
            txtField.value=str.substring(0,30);
            alert("The vendorEmail must be less than 30 characters");           
            txtField.focus();
        }
        return (false);
    }
    return (true);
};
 
function vendorContactPersonValidate(){
    var vendorContactPerson= document.frmAddGreenSheet.vendorContactPerson;
    if (vendorContactPerson.value != null && (vendorContactPerson.value != "")) {
        if(vendorContactPerson.value.replace(/^\s+|\s+$/g,"").length>50){
            str = new String(document.frmAddGreenSheet.vendorContactPerson.value);
            document.frmAddGreenSheet.vendorContactPerson.value=str.substring(0,50);
            alert("The vendorContactPerson must be less than 50 characters");
            document.frmAddGreenSheet.vendorContactPerson.focus();
        }
        return (false);
    }
    return (true);
};
 
function vendorUnitsValidate(){
    var vendorUnits= document.frmAddGreenSheet.vendorUnits;
 
    if (vendorUnits.value != null && (vendorUnits.value != "")) {
        if(vendorUnits.value.replace(/^\s+|\s+$/g,"").length>10){
 
            str = new String(document.frmAddGreenSheet.vendorUnits.value);
            document.frmAddGreenSheet.vendorUnits.value=str.substring(0,10);
            alert("The vendorUnits must be less than 10 characters");
            document.frmAddGreenSheet.vendorUnits.focus();
        }
        return (false);
    }
 
    return (true);
};*/




//new function to validate file length
//Date : 07/26/2013

function attachmentFileNameValidate(){
    //alert("attachmentFileNameValidate");
   var attachmentFileName= document.frmAddGreenSheet.attachmentFileName;
   
       
    if (attachmentFileName.value != null && (attachmentFileName.value != "")) {
        if(attachmentFileName.value.length>40){
                       
                 
               document.getElementById('attachmentFileName').value = "";
               alert("The Resume file name must be less than 40 characters.Please rename the resume file name to less than 40 characters.");
           
              }
       document.frmAddGreenSheet.attachmentFileName.focus();
        return (false);
    }
  
    return (true);
};