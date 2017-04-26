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
document.ancillaryForm.referalEmail.value="";
alert("Invalid E-mail Address! Please re-enter.")
return (false)
}


function fatherNameValidate(){
    
   var fatherName= document.ancillaryForm.fatherName;
   
       
    if (fatherName.value != null && (fatherName.value != "")) {
        if(fatherName.value.replace(/^\s+|\s+$/g,"").length>30){
            
            str = new String(document.ancillaryForm.fatherName.value);
           document.ancillaryForm.fatherName.value=str.substring(0,30);
            
            alert("The fatherName must be less than 30 characters");
            
           
           
              }
       document.ancillaryForm.fatherName.focus();
        return (false);
    }
  
    return (true);
};

function fatherTitleValidate(){
      var fatherTitle= document.ancillaryForm.fatherTitle;
    
    if (fatherTitle.value != null && (fatherTitle.value != "")) {
        if(fatherTitle.value.replace(/^\s+|\s+$/g,"").length>30){
            
            str = new String(document.ancillaryForm.fatherTitle.value);
           document.ancillaryForm.fatherTitle.value=str.substring(0,30);
            
            alert("The fatherTitle must be less than 30 characters");
            
        
           
              }
       document.ancillaryForm.fatherTitle.focus();
        return (false);
    }
  
    return (true);
};

function fatherPhoneValidate(){
    var fatherPhone= document.ancillaryForm.fatherPhone;
    
    
    if (fatherPhone.value != null && (fatherPhone.value != "")) {
        if(fatherPhone.value.replace(/^\s+|\s+$/g,"").length>30){
            
             str = new String(document.ancillaryForm.fatherPhone.value);
           document.ancillaryForm.fatherPhone.value=str.substring(0,30);
            
            alert("The fatherPhone must be less than 30 characters");
              
         
           
              }
       document.ancillaryForm.fatherPhone.focus();
        return (false);
    }
  
    return (true);
};

function fatherAddressValidate(){
      var fatherAddress= document.ancillaryForm.fatherAddress;
    
    
    if (fatherAddress.value != null && (fatherAddress.value != "")) {
        if(fatherAddress.value.replace(/^\s+|\s+$/g,"").length>255){
            
              str = new String(document.ancillaryForm.fatherAddress.value);
           document.ancillaryForm.fatherAddress.value=str.substring(0,255);  
            
            alert("The fatherAddress must be less than 255 characters");
            
       
           
              }
       document.ancillaryForm.fatherAddress.focus();
        return (false);
    }
  
    return (true);
};

function laptopTypeValidate(){
     var laptopType= document.ancillaryForm.laptopType;
    
    
    
    if (laptopType.value != null && (laptopType.value != "")) {
        if(laptopType.value.replace(/^\s+|\s+$/g,"").length>20){
            
               str = new String(document.ancillaryForm.laptopType.value);
           document.ancillaryForm.laptopType.value=str.substring(0,20);
            
            alert("The laptopType must be less than 20 characters");
       
           
              }
       document.ancillaryForm.laptopType.focus();
        return (false);
    }
  
    return (true);
};

function memoryValidate(){
    var memory= document.ancillaryForm.memory;
    
    if (memory.value != null && (memory.value != "")) {
        if(memory.value.replace(/^\s+|\s+$/g,"").length>20){
            
              str = new String(document.ancillaryForm.memory.value);
           document.ancillaryForm.memory.value=str.substring(0,20);
           
            alert("The memory must be less than 20 characters");
            
           
           
              }
       document.ancillaryForm.memory.focus();
        return (false);
    }
  
    return (true);
};

function hardDiskValidate(){
     var hardDisk= document.ancillaryForm.hardDisk;
    
    
    if (hardDisk.value != null && (hardDisk.value != "")) {
        if(hardDisk.value.replace(/^\s+|\s+$/g,"").length>20){
            
              str = new String(document.ancillaryForm.hardDisk.value);
           document.ancillaryForm.hardDisk.value=str.substring(0,20);
            
            alert("The hardDisk must be less than 20 characters");
            
          
           
              }
       document.ancillaryForm.hardDisk.focus();
        return (false);
    }
  
    return (true);
};

function modelValidate(){
    var model= document.ancillaryForm.model;
    
    
    if (model.value != null && (model.value != "")) {
        if(model.value.replace(/^\s+|\s+$/g,"").length>20){
            
           str = new String(document.ancillaryForm.model.value);
           document.ancillaryForm.model.value=str.substring(0,20);
            
            alert("The model must be less than 20 characters");
            
          
              }
       document.ancillaryForm.model.focus();
        return (false);
    }
  
    return (true);
};

function serialNoValidate(){
     var serialNo= document.ancillaryForm.serialNo;
    
    
    if (serialNo.value != null && (serialNo.value != "")) {
        if(serialNo.value.replace(/^\s+|\s+$/g,"").length>20){
            
            str = new String(document.ancillaryForm.serialNo.value);
           document.ancillaryForm.serialNo.value=str.substring(0,20); 
            
            alert("The serialNo must be less than 20 characters");
            
         
           
              }
       document.ancillaryForm.serialNo.focus();
        return (false);
    }
  
    return (true);
};

function referalNameValidate(){
      var referalName= document.ancillaryForm.referalName;
    
    
    if (referalName.value != null && (referalName.value != "")) {
        if(referalName.value.replace(/^\s+|\s+$/g,"").length>30){
            
           str = new String(document.ancillaryForm.referalName.value);
           document.ancillaryForm.referalName.value=str.substring(0,30);
            
            alert("The referalName must be less than 30 characters");
            
          
           
              }
       document.ancillaryForm.referalName.focus();
        return (false);
    }
  
    return (true);
};

function referalRelationValidate(){
      var referalRelation= document.ancillaryForm.referalRelation;
    
    
    if (referalRelation.value != null && (referalRelation.value != "")) {
        if(referalRelation.value.replace(/^\s+|\s+$/g,"").length>30){
            
               str = new String(document.ancillaryForm.referalRelation.value);
           document.ancillaryForm.referalRelation.value=str.substring(0,30);
            
            alert("The referalRelation must be less than 30 characters");
            
         
              }
       document.ancillaryForm.referalRelation.focus();
        return (false);
    }
  
    return (true);
};

function referalPhoneValidate(){
      var referalPhone= document.ancillaryForm.referalPhone;
    
    
    if (referalPhone.value != null && (referalPhone.value != "")) {
        if(referalPhone.value.replace(/^\s+|\s+$/g,"").length>20){
            
               str = new String(document.ancillaryForm.referalPhone.value);
           document.ancillaryForm.referalPhone.value=str.substring(0,20);
            
            alert("The referalPhone must be less than 20 characters");
            
        
           
              }
       document.ancillaryForm.referalPhone.focus();
        return (false);
    }
  
    return (true);
};

function referalEmailValidate(){
       var referalEmail= document.ancillaryForm.referalEmail;
    
    
    if (referalEmail.value != null && (referalEmail.value != "")) {
        if(referalEmail.value.replace(/^\s+|\s+$/g,"").length>30){
            
             str = new String(document.ancillaryForm.referalEmail.value);
           document.ancillaryForm.referalEmail.value=str.substring(0,30);  
            
            alert("The referalEmail must be less than 30 characters");
            
         
           
              }
       document.ancillaryForm.referalEmail.focus();
        return (false);
    }
  
    return (true);
};

function referalAltPhoneValidate(){
    var referalAltPhone= document.ancillaryForm.referalAltPhone;
    
    
    if (referalAltPhone.value != null && (referalAltPhone.value != "")) {
        if(referalAltPhone.value.replace(/^\s+|\s+$/g,"").length>20){
            
            str = new String(document.ancillaryForm.referalAltPhone.value);
           document.ancillaryForm.referalAltPhone.value=str.substring(0,20);
            
            alert("The referalAltPhone must be less than 20 characters");
            
           
           
              }
       document.ancillaryForm.referalAltPhone.focus();
        return (false);
    }
  
    return (true);
};

function referalCommentsValidate(){
     var referalComments= document.ancillaryForm.referalComments;
    
    
    if (referalComments.value != null && (referalComments.value != "")) {
        if(referalComments.value.replace(/^\s+|\s+$/g,"").length>255){
            
           str = new String(document.ancillaryForm.referalComments.value);
           document.ancillaryForm.referalComments.value=str.substring(0,255);
            
            alert("The referalComments must be less than 255 characters");
            
             
           
              }
       document.ancillaryForm.referalComments.focus();
        return (false);
    }
  
    return (true);
};

function contractOnFieldValidate(){
       var contractOnField= document.ancillaryForm.contractOnField;
    
    
    if (contractOnField.value != null && (contractOnField.value != "")) {
        if(contractOnField.value.replace(/^\s+|\s+$/g,"").length>10){
            
               str = new String(document.ancillaryForm.contractOnField.value);
           document.ancillaryForm.contractOnField.value=str.substring(0,10);
            
            alert("The contractOnField must be less than 10 characters");
            
        
           
              }
       document.ancillaryForm.contractOnField.focus();
        return (false);
    }
  
    return (true);
};

function contractPeriodValidate(){
      var contractPeriod= document.ancillaryForm.contractPeriod;
    
    
    
    if (contractPeriod.value != null && (contractPeriod.value != "")) {
        if(contractPeriod.value.replace(/^\s+|\s+$/g,"").length>10){
            
               str = new String(document.ancillaryForm.contractPeriod.value);
           document.ancillaryForm.contractPeriod.value=str.substring(0,10);
            
            alert("The contractPeriod must be less than 10 characters");
            
      
           
              }
       document.ancillaryForm.contractPeriod.focus();
        return (false);
    }
  
    return (true);
};

function trainPeriodValidate(){
       var trainPeriod= document.ancillaryForm.trainPeriod;
    
    
    if (trainPeriod.value != null && (trainPeriod.value != "")) {
        if(trainPeriod.value.replace(/^\s+|\s+$/g,"").length>10){
            
              str = new String(document.ancillaryForm.trainPeriod.value);
           document.ancillaryForm.trainPeriod.value=str.substring(0,10);
            
            alert("The trainPeriod must be less than 10 characters");
            
          
           
              }
       document.ancillaryForm.trainPeriod.focus();
        return (false);
    }
  
    return (true);
};

function bondProvidedByValidate(){
         var bondProvidedBy= document.ancillaryForm.bondProvidedBy;
    
    
    if (bondProvidedBy.value != null && (bondProvidedBy.value != "")) {
        if(bondProvidedBy.value.replace(/^\s+|\s+$/g,"").length>30){
            
             str = new String(document.ancillaryForm.bondProvidedBy.value);
           document.ancillaryForm.bondProvidedBy.value=str.substring(0,30);
            
            alert("The bondProvidedBy must be less than 30 characters");
            
             
           
              }
       document.ancillaryForm.bondProvidedBy.focus();
        return (false);
    }
  
    return (true);
};

function relationToEmployeeValidate(){
       var relationToEmployee= document.ancillaryForm.relationToEmployee;
    
    
    if (relationToEmployee.value != null && (relationToEmployee.value != "")) {
        if(relationToEmployee.value.replace(/^\s+|\s+$/g,"").length>30){
            
             str = new String(document.ancillaryForm.relationToEmployee.value);
           document.ancillaryForm.relationToEmployee.value=str.substring(0,30);
            
            alert("The relationToEmployee must be less than 30 characters");
            
        
           
              }
       document.ancillaryForm.relationToEmployee.focus();
        return (false);
    }
  
    return (true);
};

function jobTitleValidate(){
      var jobTitle= document.ancillaryForm.jobTitle;
    
    
    if (jobTitle.value != null && (jobTitle.value != "")) {
        if(jobTitle.value.replace(/^\s+|\s+$/g,"").length>30){
            
                str = new String(document.ancillaryForm.jobTitle.value);
           document.ancillaryForm.jobTitle.value=str.substring(0,30);
            
            alert("The jobTitle must be less than 30 characters");
            
         
              }
       document.ancillaryForm.jobTitle.focus();
        return (false);
    }
  
    return (true);
};

function jobCompanyValidate(){
    var jobCompany= document.ancillaryForm.jobCompany;
    
    
    
    if (jobCompany.value != null && (jobCompany.value != "")) {
        if(jobCompany.value.replace(/^\s+|\s+$/g,"").length>50){
            
           str = new String(document.ancillaryForm.jobCompany.value);
           document.ancillaryForm.jobCompany.value=str.substring(0,50);
            
            alert("The jobCompany must be less than 50 characters");
            
           
           
              }
       document.ancillaryForm.jobCompany.focus();
        return (false);
    }
  
    return (true);
};

function jobPhoneValidate(){
      var jobPhone= document.ancillaryForm.jobPhone;
    
    
    if (jobPhone.value != null && (jobPhone.value != "")) {
        if(jobPhone.value.replace(/^\s+|\s+$/g,"").length>15){
            
               str = new String(document.ancillaryForm.jobPhone.value);
           document.ancillaryForm.jobPhone.value=str.substring(0,15);  
            
            alert("The jobPhone must be less than 15 characters");
            
         
           
              }
       document.ancillaryForm.jobPhone.focus();
        return (false);
    }
  
    return (true);
};

function jobAddressValidate(){
      var jobAddress= document.ancillaryForm.jobAddress;
    
    
    if (jobAddress.value != null && (jobAddress.value != "")) {
        if(jobAddress.value.replace(/^\s+|\s+$/g,"").length>80){
            
            str = new String(document.ancillaryForm.jobAddress.value);
           document.ancillaryForm.jobAddress.value=str.substring(0,80);
            
            alert("The jobAddress must be less than 80 characters");
            
          
              }
       document.ancillaryForm.jobAddress.focus();
        return (false);
    }
  
    return (true);
};

function commentsValidate(){
    var comments= document.ancillaryForm.comments;
    
    
    if (comments.value != null && (comments.value != "")) {
        if(comments.value.replace(/^\s+|\s+$/g,"").length>255){
            
           str = new String(document.ancillaryForm.comments.value);
           document.ancillaryForm.comments.value=str.substring(0,255);  
            
            alert("The comments must be less than 255 characters");
            
            
           
              }
       document.ancillaryForm.comments.focus();
        return (false);
    }
  
    return (true);
};

function passOutValidate() {
    var passOut = document.ancillaryForm.passOut;

    if (passOut.value != null && (passOut.value != "")) {
        if(passOut.value.replace(/^\s+|\s+$/g,"").length != 4){

             str = new String(document.ancillaryForm.passOut.value);
           document.ancillaryForm.passOut.value=str.substring(0,4);
            
            alert("The Value must be an year");
              
         
           
              }
       document.ancillaryForm.passOut.focus();
        return (false);
    }
  
    return (true);
};


function pgPassOutValidate() {
    var pgPassOut = document.ancillaryForm.pgPassOut;

    if (pgPassOut.value != null && (pgPassOut.value != "")) {
        if(pgPassOut.value.replace(/^\s+|\s+$/g,"").length != 4){

             str = new String(document.ancillaryForm.pgPassOut.value);
           document.ancillaryForm.pgPassOut.value=str.substring(0,4);
            
            alert("The Value must be an year");
              
         
           
              }
       document.ancillaryForm.pgPassOut.focus();
        return (false);
    }
  
    return (true);
};

