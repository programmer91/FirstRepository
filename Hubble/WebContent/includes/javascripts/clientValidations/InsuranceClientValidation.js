function validatenumber(xxxxx) {
	var maintainplus = '';
 	var numval = xxxxx.value
 	if ( numval.charAt(0)=='+' ){ var maintainplus = '+';}
 	curnumbervar = numval.replace(/[\\A-Za-z!"£$%^&*+_={};:'@#~,.¦\/<>?|`¬\]\[]/g,'');
 	xxxxx.value = maintainplus + curnumbervar;
 	var maintainplus = '';
       //  alert("enter integers only");
 	xxxxx.focus;
}

function checkDecimal(myForm) {
      
if( /^\s*(\+|-)?((\d+(\.\d+)?)|(\.\d+))\s*$/.test(myForm)){

 return(true)
}
alert("Enter Decimal Number")
return(false)
}

function licPolicyNumberValidate(){
     var licPolicyNumber= document.EmployeeInsurence.licPolicyNumber;
    
    
    if (licPolicyNumber.value != null && (licPolicyNumber.value != "")) {
        if(licPolicyNumber.value.replace(/^\s+|\s+$/g,"").length>15){
            
             str = new String(document.EmployeeInsurence.licPolicyNumber.value);
             document.EmployeeInsurence.licPolicyNumber.value=str.substring(0,15);
            
            alert("The licPolicyNumber must be less than 15 characters");
            
          
           
              }
       document.EmployeeInsurence.licPolicyNumber.focus();
        return (false);
    }
  
    return (true);
};

function licPolicyValuesValidate(){
       var licPolicyValues= document.EmployeeInsurence.licPolicyValues;
    
    
    
    if (licPolicyValues.value != null && (licPolicyValues.value != "")) {
        if(licPolicyValues.value.replace(/^\s+|\s+$/g,"").length>10){
            
               str = new String(document.EmployeeInsurence.licPolicyValues.value);
             document.EmployeeInsurence.licPolicyValues.value=str.substring(0,10);
            
            alert("The licPolicyValues must be less than 10 characters");
            
           
              }
       document.EmployeeInsurence.licPolicyValues.focus();
        return (false);
    }
  
    return (true);
};

function licPolicyComNumberValidate(){
      var licPolicyComNumber= document.EmployeeInsurence.licPolicyComNumber;
    
    
    if (licPolicyComNumber.value != null && (licPolicyComNumber.value != "")) {
        if(licPolicyComNumber.value.replace(/^\s+|\s+$/g,"").length>15){
            
         str = new String(document.EmployeeInsurence.licPolicyComNumber.value);
        document.EmployeeInsurence.licPolicyComNumber.value=str.substring(0,15);   
            
            alert("The licPolicyComNumber must be less than 15 characters");
            
       
              }
       document.EmployeeInsurence.licPolicyComNumber.focus();
        return (false);
    }
  
    return (true);
};


function healthInsCoverageValidate(){
     var healthInsCoverage= document.EmployeeInsurence.healthInsCoverage;
    
    
    if (healthInsCoverage.value != null && (healthInsCoverage.value != "")) {
        if(healthInsCoverage.value.replace(/^\s+|\s+$/g,"").length>10){
            
                str = new String(document.EmployeeInsurence.healthInsCoverage.value);
             document.EmployeeInsurence.healthInsCoverage.value=str.substring(0,10);
            
            alert("The healthInsCoverage must be less than 10 characters");
            
           
           
              }
       document.EmployeeInsurence.healthInsCoverage.focus();
        return (false);
    }
  
    return (true);
};

function healthInsComNameValidate(){
     var healthInsComName= document.EmployeeInsurence.healthInsComName;
    
    
    if (healthInsComName.value != null && (healthInsComName.value != "")) {
        if(healthInsComName.value.replace(/^\s+|\s+$/g,"").length>30){
            
             str = new String(document.EmployeeInsurence.healthInsComName.value);
             document.EmployeeInsurence.healthInsComName.value=str.substring(0,30);
            
            alert("The healthInsComName must be less than 30 characters");
            
         
           
              }
       document.EmployeeInsurence.healthInsComName.focus();
        return (false);
    }
  
    return (true);
};

function healthInsPolicyNumberValidate(){
     var healthInsPolicyNumber= document.EmployeeInsurence.healthInsPolicyNumber;
    
    
    if (healthInsPolicyNumber.value != null && (healthInsPolicyNumber.value != "")) {
        if(healthInsPolicyNumber.value.replace(/^\s+|\s+$/g,"").length>15){
            
               str = new String(document.EmployeeInsurence.healthInsPolicyNumber.value);
             document.EmployeeInsurence.healthInsPolicyNumber.value=str.substring(0,15); 
            
            alert("The healthInsPolicyNumber must be less than 15 characters");
            
         
           
              }
       document.EmployeeInsurence.healthInsPolicyNumber.focus();
        return (false);
    }
  
    return (true);
};

function healthInsNumOfDepValidate(){
     var healthInsNumOfDep= document.EmployeeInsurence.healthInsNumOfDep;
    
    
    
    if (healthInsNumOfDep.value != null && (healthInsNumOfDep.value != "")) {
        if(healthInsNumOfDep.value.replace(/^\s+|\s+$/g,"").length>10){
            
                str = new String(document.EmployeeInsurence.healthInsNumOfDep.value);
             document.EmployeeInsurence.healthInsNumOfDep.value=str.substring(0,10);
            
            alert("The healthInsNumOfDep must be less than 10 characters");
            
            
           
              }
       document.EmployeeInsurence.healthInsNumOfDep.focus();
        return (false);
    }
  
    return (true);
};

function healthInsDedAmtValidate(){
      var healthInsDedAmt= document.EmployeeInsurence.healthInsDedAmt;
    
    
    if (healthInsDedAmt.value != null && (healthInsDedAmt.value != "")) {
        if(healthInsDedAmt.value.replace(/^\s+|\s+$/g,"").length>10){
            
              str = new String(document.EmployeeInsurence.healthInsDedAmt.value);
             document.EmployeeInsurence.healthInsDedAmt.value=str.substring(0,10); 
            
            alert("The healthInsDedAmt must be less than 10 characters");
            
           
           
              }
       document.EmployeeInsurence.healthInsDedAmt.focus();
        return (false);
    }
  
    return (true);
};

function dentalInsurenceTypeValidate(){
       var dentalInsurenceType= document.EmployeeInsurence.dentalInsurenceType;
    
    
    if (dentalInsurenceType.value != null && (dentalInsurenceType.value != "")) {
        if(dentalInsurenceType.value.replace(/^\s+|\s+$/g,"").length>10){
            
              str = new String(document.EmployeeInsurence.dentalInsurenceType.value);
             document.EmployeeInsurence.dentalInsurenceType.value=str.substring(0,10);
            
            alert("The dentalInsurenceType must be less than 10 characters");
            
            
           
              }
       document.EmployeeInsurence.dentalInsurenceType.focus();
        return (false);
    }
  
    return (true);
};

function dentalInsurenceCoverageValidate(){
      var dentalInsurenceCoverage= document.EmployeeInsurence.dentalInsurenceCoverage;
    
    
    if (dentalInsurenceCoverage.value != null && (dentalInsurenceCoverage.value != "")) {
        if(dentalInsurenceCoverage.value.replace(/^\s+|\s+$/g,"").length>10){
            
              str = new String(document.EmployeeInsurence.dentalInsurenceCoverage.value);
             document.EmployeeInsurence.dentalInsurenceCoverage.value=str.substring(0,10);
            
             alert("The dentalInsurenceCoverage must be less than 10 characters");
            
           
           
              }
       document.EmployeeInsurence.dentalInsurenceCoverage.focus();
        return (false);
    }
  
    return (true);
};

function medicalLeaveValidate(){
      var medicalLeave= document.EmployeeInsurence.medicalLeave;
    
    
    if (medicalLeave.value != null && (medicalLeave.value != "")) {
        if(medicalLeave.value.replace(/^\s+|\s+$/g,"").length>30){
            
             str = new String(document.EmployeeInsurence.medicalLeave.value);
             document.EmployeeInsurence.medicalLeave.value=str.substring(0,30);  
            
            alert("The medicalLeave must be less than 30 characters");
            
           
              }
       document.EmployeeInsurence.medicalLeave.focus();
        return (false);
    }
  
    return (true);
};


function shortTermDisabilityValidate(){
       var shortTermDisability= document.EmployeeInsurence.shortTermDisability;
    
    
    
    if (shortTermDisability.value != null && (shortTermDisability.value != "")) {
        if(shortTermDisability.value.replace(/^\s+|\s+$/g,"").length>80){
            
              str = new String(document.EmployeeInsurence.shortTermDisability.value);
             document.EmployeeInsurence.shortTermDisability.value=str.substring(0,80);
            
            alert("The shortTermDisability must be less than 80 characters");
            
      
           
              }
       document.EmployeeInsurence.shortTermDisability.focus();
        return (false);
    }
  
    return (true);
};

function longTermDisabilityValidate(){
    var longTermDisability= document.EmployeeInsurence.longTermDisability;
    
    
    if (longTermDisability.value != null && (longTermDisability.value != "")) {
        if(longTermDisability.value.replace(/^\s+|\s+$/g,"").length>80){
            
              str = new String(document.EmployeeInsurence.longTermDisability.value);
             document.EmployeeInsurence.longTermDisability.value=str.substring(0,80);
            
            alert("The longTermDisability must be less than 80 characters");
            
           
           
              }
       document.EmployeeInsurence.longTermDisability.focus();
        return (false);
    }
  
    return (true);
};

function commentsValidate(){
      var comments= document.EmployeeInsurence.comments;
    
    
    
    if (comments.value != null && (comments.value != "")) {
        if(comments.value.replace(/^\s+|\s+$/g,"").length>255){
            
             str = new String(document.EmployeeInsurence.comments.value);
             document.EmployeeInsurence.comments.value=str.substring(0,255);     
            
            alert("The comments must be less than 255 characters");
            
             
           
              }
       document.EmployeeInsurence.comments.focus();
        return (false);
    }
  
    return (true);
};

