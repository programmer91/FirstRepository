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
    document.employeeForm.officeEmail.value="";
    alert("Invalid E-mail Address! Please re-enter.")
    
    return (false)
}


/*function checkEmailOfficial(myForm) {
 
            if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(myForm)){
            return (true)
                }
            alert("Invalid E-mail Address! Please re-enter.");
            document.employeeForm.officeEmail.value="";
            return (false)
 }*/

function valueCheck(myForm) {
    
    var is_value = myForm.indexOf('miraclesoft.com');
    if (is_value==-1) { 
        document.employeeForm.officeEmail.value="";
        alert('You should enter valid Miracle Mail Id!');
    }    else{
        checkEmail(myForm)
    }
    
}

function checkOtherEmail(myForm) {
    
    if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(myForm)){
        return (true)
    }
    document.employeeForm.personalEmail.value="";
    alert("Invalid E-mail Address! Please re-enter.")
   
    return (false)
}

function checkOtherEmail1(myForm) {
    
    if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(myForm)){
        return (true)
    }
    document.employeeForm.otherEmail.value="";
    alert("Invalid E-mail Address! Please re-enter.")
   
    return (false)
}





function firstNameValidate(){
    
    var firstName= document.employeeForm.firstName;
    
    
    if (firstName.value != null && (firstName.value != "")) {
        if(firstName.value.replace(/^\s+|\s+$/g,"").length>30){
            
              str = new String(document.employeeForm.firstName.value);
              document.employeeForm.firstName.value=str.substring(0,30);
            
            alert("The firstName must be less than 30 characters");
            
           
            
        }
        document.employeeForm.firstName.focus();
        return (false);
    }
    
    return (true);
};

function lastNameValidate(){
    
    var lastName= document.employeeForm.lastName;
    
    
    if (lastName.value != null && (lastName.value != "")) {
        if(lastName.value.replace(/^\s+|\s+$/g,"").length>30){
            
             str = new String(document.employeeForm.lastName.value);
             document.employeeForm.lastName.value=str.substring(0,30);     
            
            alert("The lastName must be less than 30 characters");
            
      
            
        }
        document.employeeForm.lastName.focus();
        return (false);
    }
    
    return (true);
};

function middleNameValidate(){
    
    var middleName= document.employeeForm.middleName;
    
    
    if (middleName.value != null && (middleName.value != "")) {
        if(middleName.value.replace(/^\s+|\s+$/g,"").length>20){
            
            str = new String( document.employeeForm.middleName.value);
            document.employeeForm.middleName.value=str.substring(0,20);
            
            alert("The middleName must be less than 20 characters");
            
         
            
        }
        document.employeeForm.middleName.focus();
        return (false);
    }
    
    return (true);
};

function aliasNameValidate(){
    
    var aliasName= document.employeeForm.aliasName;
    
    
    if (aliasName.value != null && (aliasName.value != "")) {
        if(aliasName.value.replace(/^\s+|\s+$/g,"").length>15){
            
             str = new String(document.employeeForm.aliasName.value);
             document.employeeForm.aliasName.value=str.substring(0,15);   
            
            alert("The aliasName must be less than 15 characters");
            
          
            
        }
        document.employeeForm.aliasName.focus();
        return (false);
    }
    
    return (true);
};

function ssnValidate(){
    
    var ssn= document.employeeForm.ssn;
    
    
    if (ssn.value != null && (ssn.value != "")) {
        if(ssn.value.replace(/^\s+|\s+$/g,"").length>12){
            
             str = new String(document.employeeForm.ssn.value);
             document.employeeForm.ssn.value=str.substring(0,12);
            
            alert("The ssn must be less than 12 characters");
            
       
            
        }
        document.employeeForm.ssn.focus();
        return (false);
    }
    
    return (true);
};

function workPhoneNoValidate(){
    
    var workPhoneNo= document.employeeForm.workPhoneNo;
    
    
    if (workPhoneNo.value != null && (workPhoneNo.value != "")) {
        if(workPhoneNo.value.replace(/^\s+|\s+$/g,"").length>20){
            
             str = new String(document.employeeForm.workPhoneNo.value);
             document.employeeForm.workPhoneNo.value=str.substring(0,20);   
            
            alert("The workPhoneNo must be less than 20 characters");
            
           
            
        }
        document.employeeForm.workPhoneNo.focus();
        return (false);
    }
    
    return (true);
};

function altPhoneNoValidate(){
    
    var altPhoneNo= document.employeeForm.altPhoneNo;
    
    
    if (altPhoneNo.value != null && (altPhoneNo.value != "")) {
        if(altPhoneNo.value.replace(/^\s+|\s+$/g,"").length>20){
            
             str = new String(document.employeeForm.altPhoneNo.value);
             document.employeeForm.altPhoneNo.value=str.substring(0,20);
            
            alert("The altPhoneNo must be less than 20 characters");
            
       
            
        }
        document.employeeForm.altPhoneNo.focus();
        return (false);
    }
    
    return (true);
};

function homePhoneNoValidate(){
    
    var homePhoneNo= document.employeeForm.homePhoneNo;
    
    
    if (homePhoneNo.value != null && (homePhoneNo.value != "")) {
        if(homePhoneNo.value.replace(/^\s+|\s+$/g,"").length>20){
            
              str = new String(document.employeeForm.homePhoneNo.value);
              document.employeeForm.homePhoneNo.value=str.substring(0,20); 
            
            alert("The homePhoneNo must be less than 20 characters");
            
      
            
        }
        document.employeeForm.homePhoneNo.focus();
        return (false);
    }
    
    return (true);
};

function cellPhoneNoValidate(){
    
    var cellPhoneNo= document.employeeForm.cellPhoneNo;
    
    
    if (cellPhoneNo.value != null && (cellPhoneNo.value != "")) {
        if(cellPhoneNo.value.replace(/^\s+|\s+$/g,"").length>20){
            
              str = new String(document.employeeForm.cellPhoneNo.value);
              document.employeeForm.cellPhoneNo.value=str.substring(0,20);
            
            alert("The cellPhoneNo must be less than 20 characters");
            
          
            
        }
        document.employeeForm.cellPhoneNo.focus();
        return (false);
    }
    
    return (true);
};

function officeEmailValidate(){
    
    var officeEmail= document.employeeForm.officeEmail;
    
    
    if (officeEmail.value != null && (officeEmail.value != "")) {
        if(officeEmail.value.replace(/^\s+|\s+$/g,"").length>60){
            
             str = new String( document.employeeForm.officeEmail.value);
            document.employeeForm.officeEmail.value=str.substring(0,60);     
            
            alert("The officeEmail must be less than 60 characters");
            
       
            
        }
        document.employeeForm.officeEmail.focus();
        return (false);
    }
    
    return (true);
};

function hotelPhoneNoValidate(){
    
    var hotelPhoneNo= document.employeeForm.hotelPhoneNo;
    
    
    if (hotelPhoneNo.value != null && (hotelPhoneNo.value != "")) {
        if(hotelPhoneNo.value.replace(/^\s+|\s+$/g,"").length>20){
            
              str = new String(document.employeeForm.hotelPhoneNo.value);
              document.employeeForm.hotelPhoneNo.value=str.substring(0,20);
            
            alert("The hotelPhoneNo must be less than 20 characters");
            
         
            
        }
        document.employeeForm.hotelPhoneNo.focus();
        return (false);
    }
    
    return (true);
};

function personalEmailValidate(){
    
    var personalEmail= document.employeeForm.personalEmail;
    
    
    if (personalEmail.value != null && (personalEmail.value != "")) {
        if(personalEmail.value.replace(/^\s+|\s+$/g,"").length>60){
            
               str = new String(document.employeeForm.personalEmail.value);
               document.employeeForm.personalEmail.value=str.substring(0,60);   
            
            alert("The personalEmail must be less than 60 characters");
            
         
            
        }
        document.employeeForm.personalEmail.focus();
        return (false);
    }
    
    return (true);
};

function indiaPhoneNoValidate(){
    
    var indiaPhoneNo= document.employeeForm.indiaPhoneNo;
    
    
    if (indiaPhoneNo.value != null && (indiaPhoneNo.value != "")) {
        if(indiaPhoneNo.value.replace(/^\s+|\s+$/g,"").length>20){
            
             str = new String(document.employeeForm.indiaPhoneNo.value);
             document.employeeForm.indiaPhoneNo.value=str.substring(0,20);
            
            alert("The indiaPhoneNo must be less than 20 characters");
            
         
            
        }
        document.employeeForm.indiaPhoneNo.focus();
        return (false);
    }
    
    return (true);
};

function otherEmailValidate(){
    
    var otherEmail= document.employeeForm.otherEmail;
    
    
    if (otherEmail.value != null && (otherEmail.value != "")) {
        if(otherEmail.value.replace(/^\s+|\s+$/g,"").length>60){
            
               str = new String(document.employeeForm.otherEmail.value);
              document.employeeForm.otherEmail.value=str.substring(0,60);     
            
            alert("The otherEmail must be less than 60 characters");
            
         
            
        }
        document.employeeForm.otherEmail.focus();
        return (false);
    }
    
    return (true);
};

function faxNoValidate(){
    
    var faxNo= document.employeeForm.faxNo;
    
    
    if (faxNo.value != null && (faxNo.value != "")) {
        if(faxNo.value.replace(/^\s+|\s+$/g,"").length>20){
            
              str = new String(document.employeeForm.faxNo.value);
              document.employeeForm.faxNo.value=str.substring(0,20);
            
            alert("The faxNo must be less than 20 characters");
            
        
            
        }
        document.employeeForm.faxNo.focus();
        return (false);
    }
    
    return (true);
};

function lastContactByValidate(){
    
    var lastContactBy= document.employeeForm.lastContactBy;
    
    
    if (lastContactBy.value != null && (lastContactBy.value != "")) {
        if(lastContactBy.value.replace(/^\s+|\s+$/g,"").length>30){
            
              str = new String(document.employeeForm.lastContactBy.value);
             document.employeeForm.lastContactBy.value=str.substring(0,30);  
            
            alert("The lastContactBy must be less than 30 characters");
            
         
            
        }
        document.employeeForm.lastContactBy.focus();
        return (false);
    }
    
    return (true);
};

function skillSetValidate(){
    alert("slil");
    var skillSet= document.currentStatusForm.skillSet;
    
    
    if (skillSet.value != null && (skillSet.value != "")) {
        if(skillSet.value.replace(/^\s+|\s+$/g,"").length>50){
            
              str = new String(document.currentStatusForm.skillSet.value);
             document.currentStatusForm.skillSet.value=str.substring(0,50);   
            
            alert("The skillSet must be less than 50 characters");
            
           
            
        }
        document.currentStatusForm.skillSet.focus();
        return (false);
    }
    
    return (true);
};
function validateCtc() {
//alert("validate");
var ctcPerYaer=document.getElementById('ctcPerYear1').value;
if(ctcPerYaer <= 24)  {



//alert(ctcPerYaer);
var ctcResult=(((ctcPerYaer)*(100000))/(2080*42));
var strCtcResult=String(ctcResult);
//alert(strCtcResult.length);

 document.getElementById('intRatePerHour').value=strCtcResult.substring(0,4);

}
else {
alert('enter range between 0 to 24');
document.getElementById('ctcPerYear1').value="";
document.getElementById('intRatePerHour').value="";
}
}
