
//PHONE NUMBER FORMAT SCRIPT :START        
function homePhoneNoFormat(num) { 
    
    str = new String(document.registrationForm.homePhoneNo.value);
    document.registrationForm.homePhoneNo.value=str.replace(/[\(\)\.\-\x\s,]/g, "");
    num=document.registrationForm.homePhoneNo.value;
    
    if(num.length == 10) { 
        _return="(";
        var ini = num.substring(0,3);
        _return+=ini+")";
        var st = num.substring(3,6);
        _return+="-"+st+"-";
        var end = num.substring(6,10);
        _return+=end;
        
        document.registrationForm.homePhoneNo.value ="";
        document.registrationForm.homePhoneNo.value =_return;
        
    }else if(num.length > 10) {
        _return="(";
        var ini = num.substring(0,3);
        _return+=ini+")";
        var st = num.substring(3,6);
        _return+="-"+st+"-";
        var end = num.substring(6,10);
        _return+=end+"x";
        var ext = num.substring(10,num.length);
        _return+=ext;
        
        document.registrationForm.homePhoneNo.value ="";
        document.registrationForm.homePhoneNo.value =_return;
        return false;
    }else if(num.length < 10) {
        alert('Please give atleast  10 charcters in PhoneNumber');
        document.registrationForm.homePhoneNo.value ="";
    }
    
    return _return;
}  


function workPhoneNoFormat(num) { 
    
    str = new String(document.registrationForm.workPhoneNo.value);
    document.registrationForm.workPhoneNo.value=str.replace(/[\(\)\.\-\s,]/g, "");
    num=document.registrationForm.workPhoneNo.value;
    var _return;
    if(num.length == 10) {
        
        _return="(";
        var ini = num.substring(0,3);
        _return+=ini+")";
        var st = num.substring(3,6);
        _return+="-"+st+"-";
        var end = num.substring(6,10);
        _return+=end;
        
        document.registrationForm.workPhoneNo.value ="";
        document.registrationForm.workPhoneNo.value =_return;
        
    }else if(num.length > 10) {
        _return="(";
        var ini = num.substring(0,3);
        _return+=ini+")";
        var st = num.substring(3,6);
        _return+="-"+st+"-";
        var end = num.substring(6,10);
        _return+=end+"x";
        var ext = num.substring(10,num.length);
        _return+=ext;
        
        document.registrationForm.workPhoneNo.value ="";
        document.registrationForm.workPhoneNo.value =_return;
        return false;
    }else if(num.length < 10) {
        alert('Please give atleast  10 charcters in PhoneNumber');
        document.registrationForm.workPhoneNo.value ="";
    }
    
    return _return;
}   
//PHONE NUMBER FORMAT SCRIPT :END        
function validatenumber(xxxxx) {
    var maintainplus = '';
    var numval = xxxxx.value
    if ( numval.charAt(0)=='+' ){ var maintainplus = '+';}
    curnumbervar = numval.replace(/[\\A-Za-z!"£$%^&*+_={};:'@#~,.¦\/<>?|`¬\]\[]/g,'');
    xxxxx.value = maintainplus + curnumbervar;
    var maintainplus = '';
    // alert("enter integers only");
    xxxxx.focus;
}

function checkEmail(myForm) {
    
    if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(myForm)){
        return (true)
    }
    document.registrationForm.officeEmail.value="";
    alert("Invalid E-mail Address! Please re-enter.")
    return (false)
}

function valueCheck(myForm) {
    
    var is_miralce = myForm.indexOf('miraclesoft.com');
    var is_chikiniki = myForm.indexOf('chikiniki.com');
    
    if (is_miralce==-1 && is_chikiniki==-1) { 
        
        document.registrationForm.officeEmail.value="";
        alert('You should enter valid Miracle EmailId');
        
    }
    else
        checkEmail(myForm)
        
}

function checkOtherEmail(myForm) {
    
    if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(myForm)){
        return (true)
    }
    document.registrationForm.personalEmail.value="";
    alert("Invalid E-mail Address! Please re-enter.")
    
    return (false)
}

function checkOtherEmail1(myForm) {
    
    if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(myForm)){
        return (true)
    }
    document.registrationForm.otherEmail.value="";
    alert("Invalid E-mail Address! Please re-enter.")
    
    return (false)
}



function firstNameValidate(){
    
    var firstName= document.registrationForm.firstName;
    
    
    if (firstName.value != null && (firstName.value != "")) {
        if(firstName.value.replace(/^\s+|\s+$/g,"").length>30){
            
            str = new String(document.registrationForm.firstName.value);
            document.registrationForm.firstName.value=str.substring(0,30);
            
            alert("The firstName must be less than 30 characters");
            
        }
        document.registrationForm.firstName.focus();
        return (false);
    }
    
    return (true);
};
function lastNameValidate(){
    
    var lastName= document.registrationForm.lastName;
    
    
    if (lastName.value != null && (lastName.value != "")) {
        if(lastName.value.replace(/^\s+|\s+$/g,"").length>30){
            
            str = new String(document.registrationForm.lastName.value);
            document.registrationForm.lastName.value=str.substring(0,30);   
            
            alert("The lastName must be less than 30 characters");
            
            
        }
        document.registrationForm.lastName.focus();
        return (false);
    }
    
    return (true);
};

function middleNameValidate(){
    
    var middleName= document.registrationForm.middleName;
    
    
    if (middleName.value != null && (middleName.value != "")) {
        if(middleName.value.replace(/^\s+|\s+$/g,"").length>20){
            
            str = new String( document.registrationForm.middleName.value);
            document.registrationForm.middleName.value=str.substring(0,20);
            
            alert("The middleName must be less than 20 characters");
            
        }
        document.registrationForm.middleName.focus();
        return (false);
    }
    
    return (true);
};


function ssnValidate(){
    
    var ssn= document.registrationForm.ssn;
    
    
    if (ssn.value != null && (ssn.value != "")) {
        if(ssn.value.replace(/^\s+|\s+$/g,"").length>12){
            
            str = new String(document.registrationForm.ssn.value);
            document.registrationForm.ssn.value=str.substring(0,12);
            
            alert("The ssn must be less than 12 characters");
            
        }
        document.registrationForm.ssn.focus();
        return (false);
    }
    
    return (true);
};

function workPhoneNoValidate(){
    
    var workPhoneNo= document.registrationForm.workPhoneNo;
    
    
    if (workPhoneNo.value != null && (workPhoneNo.value != "")) {
        if(workPhoneNo.value.replace(/^\s+|\s+$/g,"").length>20){
            
            str = new String(document.registrationForm.workPhoneNo.value);
            document.registrationForm.workPhoneNo.value=str.substring(0,20);    
            
            alert("The workPhoneNo must be less than 20 characters");
            
        }
        document.registrationForm.workPhoneNo.focus();
        return (false);
    }
    
    return (true);
};

function altPhoneNoValidate(){
    
    var altPhoneNo= document.registrationForm.altPhoneNo;
    
    
    if (altPhoneNo.value != null && (altPhoneNo.value != "")) {
        if(altPhoneNo.value.replace(/^\s+|\s+$/g,"").length>20){
            
            str = new String(document.registrationForm.altPhoneNo.value);
            document.registrationForm.altPhoneNo.value=str.substring(0,20);
            
            alert("The altPhoneNo must be less than 20 characters");
            
        }
        document.registrationForm.altPhoneNo.focus();
        return (false);
    }
    
    return (true);
};

function homePhoneNoValidate(){
    
    var homePhoneNo= document.registrationForm.homePhoneNo;
    
    
    if (homePhoneNo.value != null && (homePhoneNo.value != "")) {
        if(homePhoneNo.value.replace(/^\s+|\s+$/g,"").length>20){
            
            str = new String(document.registrationForm.homePhoneNo.value);
            document.registrationForm.homePhoneNo.value=str.substring(0,20); 
            
            alert("The homePhoneNo must be less than 20 characters");
            
        }
        document.registrationForm.homePhoneNo.focus();
        return (false);
    }
    
    return (true);
};

function cellPhoneNoValidate(){
    
    var cellPhoneNo= document.registrationForm.cellPhoneNo;
    
    
    if (cellPhoneNo.value != null && (cellPhoneNo.value != "")) {
        if(cellPhoneNo.value.replace(/^\s+|\s+$/g,"").length>20){
            
            str = new String(document.registrationForm.cellPhoneNo.value);
            document.registrationForm.cellPhoneNo.value=str.substring(0,20);
            
            alert("The cellPhoneNo must be less than 20 characters");
            
        }
        document.registrationForm.cellPhoneNo.focus();
        return (false);
    }
    
    return (true);
};

function indiaPhoneValidate(){
    
    var indiaPhone= document.registrationForm.indiaPhone;
    
    
    if (indiaPhone.value != null && (indiaPhone.value != "")) {
        if(indiaPhone.value.replace(/^\s+|\s+$/g,"").length>20){
            
            str = new String(document.registrationForm.indiaPhone.value);
            document.registrationForm.indiaPhone.value=str.substring(0,20); 
            
            alert("The indiaPhone must be less than 20 characters");
            
        }
        document.registrationForm.indiaPhone.focus();
        return (false);
    }
    
    return (true);
};

function hotelPhoneValidate(){
    
    var hotelPhone= document.registrationForm.hotelPhone;
    
    
    if (hotelPhone.value != null && (hotelPhone.value != "")) {
        if(hotelPhone.value.replace(/^\s+|\s+$/g,"").length>20){
            
            str = new String(document.registrationForm.hotelPhone.value);
            document.registrationForm.hotelPhone.value=str.substring(0,20);
            
            alert("The hotelPhone must be less than 20 characters");
            
        }
        document.registrationForm.hotelPhone.focus();
        return (false);
    }
    
    return (true);
};

function refferedByValidate(){
    
    var refferedBy= document.registrationForm.refferedBy;
    
    
    if (refferedBy.value != null && (refferedBy.value != "")) {
        if(refferedBy.value.replace(/^\s+|\s+$/g,"").length>50){
            
            str = new String(document.registrationForm.refferedBy.value);
            document.registrationForm.refferedBy.value=str.substring(0,50);
            
            alert("The refferedBy must be less than 50 characters");
            
        }
        document.registrationForm.refferedBy.focus();
        return (false);
    }
    
    return (true);
};


function faxNoValidate(){
    
    var faxNo= document.registrationForm.faxNo;
    
    
    if (faxNo.value != null && (faxNo.value != "")) {
        if(faxNo.value.replace(/^\s+|\s+$/g,"").length>20){
            
            str = new String(document.registrationForm.faxNo.value);
            document.registrationForm.faxNo.value=str.substring(0,20);
            
            alert("The faxNo must be less than 20 characters");
            
        }
        document.registrationForm.faxNo.focus();
        return (false);
    }
    
    return (true);
};

function officeEmailValidate(){
    
    var officeEmail= document.registrationForm.officeEmail;
    
    
    if (officeEmail.value != null && (officeEmail.value != "")) {
        if(officeEmail.value.replace(/^\s+|\s+$/g,"").length>60){
            
            str = new String( document.registrationForm.officeEmail.value);
            document.registrationForm.officeEmail.value=str.substring(0,60);    
            
            alert("The officeEmail must be less than 60 characters");
            
        }
        document.registrationForm.officeEmail.focus();
        return (false);
    }
    
    return (true);
};



function personalEmailValidate(){
    
    var personalEmail= document.registrationForm.personalEmail;
    
    
    if (personalEmail.value != null && (personalEmail.value != "")) {
        if(personalEmail.value.replace(/^\s+|\s+$/g,"").length>60){
            
            str = new String(document.registrationForm.personalEmail.value);
            document.registrationForm.personalEmail.value=str.substring(0,60);   
            
            alert("The personalEmail must be less than 60 characters");
            
        }
        document.registrationForm.personalEmail.focus();
        return (false);
    }
    
    return (true);
};


function otherEmailValidate(){
    
    var otherEmail= document.registrationForm.otherEmail;
    
    
    if (otherEmail.value != null && (otherEmail.value != "")) {
        if(otherEmail.value.replace(/^\s+|\s+$/g,"").length>60){
            
            str = new String(document.registrationForm.otherEmail.value);
            document.registrationForm.otherEmail.value=str.substring(0,60);    
            
            alert("The otherEmail must be less than 60 characters");
            
        }
        document.registrationForm.otherEmail.focus();
        return (false);
    }
    
    return (true);
};