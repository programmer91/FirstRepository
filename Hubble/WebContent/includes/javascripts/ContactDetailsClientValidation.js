function validatenumber(xxxxx) {
   
    	var maintainplus = '';
 	var numval = xxxxx.value
 	if ( numval.charAt(0)=='+' ){ var maintainplus = '+';}
 	curnumbervar = numval.replace(/[\\A-Za-z!"£$%^&*+_={};:'@#~,.¦\/<>?|`¬\]\[]/g,'');
 	xxxxx.value = maintainplus + curnumbervar;
 	var maintainplus = '';
 	xxxxx.focus;
}

function checkEmail(myForm) {

if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(myForm)){
return (true)
}
alert("Invalid E-mail Address! Please re-enter.")
return (false)
}



function firstNameValidate(){
      var firstName= document.contactForm.firstName;
    
    
    if (firstName.value != null && (firstName.value != "")) {
        if(firstName.value.replace(/^\s+|\s+$/g,"").length>30){
            alert("The firstName must be less than 30 characters");
           str = new String( document.contactForm.firstName.value);
             document.contactForm.firstName.value=str.substring(0,30);
              }
       document.contactForm.firstName.focus();
        return (false);
    }
  
    return (true);
};

function lastNameValidate(){
      var lastName= document.contactForm.lastName;
    
    
    if (lastName.value != null && (lastName.value != "")) {
        if(lastName.value.replace(/^\s+|\s+$/g,"").length>30){
            alert("The lastName must be less than 30 characters");
            
                str = new String( document.contactForm.lastName.value);
             document.contactForm.lastName.value=str.substring(0,30);
           
              }
       document.contactForm.lastName.focus();
        return (false);
    }
  
    return (true);
};

function middleNameValidate(){
      var middleName= document.contactForm.middleName;
    
    
    if (middleName.value != null && (middleName.value != "")) {
        if(middleName.value.replace(/^\s+|\s+$/g,"").length>30){
            alert("The middleName must be less than 30 characters");
            
           str = new String( document.contactForm.middleName.value);
             document.contactForm.middleName.value=str.substring(0,30);
           
              }
       document.contactForm.middleName.focus();
        return (false);
    }
  
    return (true);
};

function aliasNameValidate(){
      var aliasName= document.contactForm.aliasName;
    
    
    if (aliasName.value != null && (aliasName.value != "")) {
        if(aliasName.value.replace(/^\s+|\s+$/g,"").length>20){
            alert("The aliasName must be less than 20 characters");
            
               str = new String( document.contactForm.aliasName.value);
             document.contactForm.aliasName.value=str.substring(0,20);
           
              }
       document.contactForm.aliasName.focus();
        return (false);
    }
  
    return (true);
};

function titleValidate(){
      var title= document.contactForm.title;
    
    
    if (title.value != null && (title.value != "")) {
        if(title.value.replace(/^\s+|\s+$/g,"").length>40){
            alert("The title must be less than 40 characters");
            
               str = new String( document.contactForm.title.value);
             document.contactForm.title.value=str.substring(0,40);  
           
              }
       document.contactForm.title.focus();
        return (false);
    }
  
    return (true);
};

function referredByValidate(){
      var referredBy= document.contactForm.referredBy;
    
    
    if (referredBy.value != null && (referredBy.value != "")) {
        if(referredBy.value.replace(/^\s+|\s+$/g,"").length>30){
            alert("The referredBy must be less than 30 characters");
            
              str = new String( document.contactForm.referredBy.value);
             document.contactForm.referredBy.value=str.substring(0,30);
           
              }
       document.contactForm.referredBy.focus();
        return (false);
    }
  
    return (true);
};

function sourceValidate(){
      var source= document.contactForm.source;
    
    
    if (source.value != null && (source.value != "")) {
        if(source.value.replace(/^\s+|\s+$/g,"").length>50){
            alert("The source must be less than 50 characters");
            
             str = new String( document.contactForm.source.value);
             document.contactForm.source.value=str.substring(0,50);
           
              }
       document.contactForm.source.focus();
        return (false);
    }
  
    return (true);
};

function specializationValidate(){
      var specialization= document.contactForm.specialization;
    
    
    if (specialization.value != null && (specialization.value != "")) {
        if(specialization.value.replace(/^\s+|\s+$/g,"").length>40){
            alert("The specialization must be less than 40 characters");
            
               str = new String( document.contactForm.specialization.value);
             document.contactForm.specialization.value=str.substring(0,40);
           
              }
       document.contactForm.specialization.focus();
        return (false);
    }
  
    return (true);
};

function officePhoneValidate(){
      var officePhone= document.contactForm.officePhone;
    
    
    if (officePhone.value != null && (officePhone.value != "")) {
        if(officePhone.value.replace(/^\s+|\s+$/g,"").length>16){
            alert("The officePhone must be less than 16 characters");
            
            str = new String( document.contactForm.officePhone.value);
             document.contactForm.officePhone.value=str.substring(0,16);
           
              }
       document.contactForm.officePhone.focus();
        return (false);
    }
  
    return (true);
};

function cellPhoneValidate(){
      var cellPhone= document.contactForm.cellPhone;
    
    
    if (cellPhone.value != null && (cellPhone.value != "")) {
        if(cellPhone.value.replace(/^\s+|\s+$/g,"").length>16){
            alert("The cellPhonemust be less than 16 characters");
            
               str = new String( document.contactForm.cellPhone.value);
             document.contactForm.cellPhone.value=str.substring(0,16); 
           
              }
       document.contactForm.cellPhone.focus();
        return (false);
    }
  
    return (true);
};

function homePhoneValidate(){
      var homePhone= document.contactForm.homePhone;
    
    
    if (homePhone.value != null && (homePhone.value != "")) {
        if(homePhone.value.replace(/^\s+|\s+$/g,"").length>16){
            alert("The homePhone must be less than 16 characters");
            
               str = new String( document.contactForm.homePhone.value);
             document.contactForm.homePhone.value=str.substring(0,16);
           
              }
       document.contactForm.homePhone.focus();
        return (false);
    }
  
    return (true);
};

function faxValidate(){
      var fax= document.contactForm.fax;
    
    
    if (fax.value != null && (fax.value != "")) {
        if(fax.value.replace(/^\s+|\s+$/g,"").length>16){
            alert("The fax must be less than 16 characters");
            
            str = new String( document.contactForm.fax.value);
             document.contactForm.fax.value=str.substring(0,16);
           
              }
       document.contactForm.fax.focus();
        return (false);
    }
  
    return (true);
};

function officeEmailValidate(){
      var officeEmail= document.contactForm.officeEmail;
    
    
    if (officeEmail.value != null && (officeEmail.value != "")) {
        if(officeEmail.value.replace(/^\s+|\s+$/g,"").length>50){
            alert("The officeEmail must be less than 50 characters");
            
              str = new String( document.contactForm.officeEmail.value);
             document.contactForm.officeEmail.value=str.substring(0,50);
           
              }
       document.contactForm.officeEmail.focus();
        return (false);
    }
  
    return (true);
};

function personalEmailValidate(){
      var personalEmail= document.contactForm.personalEmail;
    
    
    if (personalEmail.value != null && (personalEmail.value != "")) {
        if(personalEmail.value.replace(/^\s+|\s+$/g,"").length>50){
            alert("The personalEmail must be less than 50 characters");
            
               str = new String( document.contactForm.personalEmail.value);
             document.contactForm.personalEmail.value=str.substring(0,50);
           
              }
       document.contactForm.personalEmail.focus();
        return (false);
    }
  
    return (true);
};

function homeAddressLine1Validate(){
      var homeAddressLine1= document.homeAddressLine1;
    
    
    if (homeAddressLine1.value != null && (homeAddressLine1.value != "")) {
        if(homeAddressLine1.value.replace(/^\s+|\s+$/g,"").length>200){
            alert("The homeAddressLine1 must be less than 200 characters");
            
                str = new String( document.contactForm.homeAddressLine1.value);
             document.contactForm.homeAddressLine1.value=str.substring(0,200); 
           
              }
       document.contactForm.homeAddressLine1.focus();
        return (false);
    }
  
    return (true);
};

function homeAddressLine2Validate(){
      var homeAddressLine2= document.contactForm.homeAddressLine2;
    
    
    if (homeAddressLine2.value != null && (homeAddressLine2.value != "")) {
        if(homeAddressLine2.value.replace(/^\s+|\s+$/g,"").length>50){
            alert("The homeAddressLine2 must be less than 50 characters");
            
                str = new String( document.contactForm.homeAddressLine2.value);
             document.contactForm.homeAddressLine2.value=str.substring(0,50);
           
              }
       document.contactForm.homeAddressLine2.focus();
        return (false);
    }
  
    return (true);
};

function homeCityValidate(){
      var homeCity= document.contactForm.homeCity;
    
    
    if (homeCity.value != null && (homeCity.value != "")) {
        if(homeCity.value.replace(/^\s+|\s+$/g,"").length>25){
            alert("The homeCity must be less than 25 characters");
            
             str = new String( document.contactForm.homeCity.value);
             document.contactForm.homeCity.value=str.substring(0,25);
           
              }
       document.contactForm.homeCity.focus();
        return (false);
    }
  
    return (true);
};

function homeMailStopValidate(){
      var homeMailStop= document.contactForm.homeMailStop;
    
    
    if (homeMailStop.value != null && (homeMailStop.value != "")) {
        if(homeMailStop.value.replace(/^\s+|\s+$/g,"").length>10){
            alert("The homeMailStop must be less than 10 characters");
            
               str = new String( document.contactForm.homeMailStop.value);
             document.contactForm.homeMailStop.value=str.substring(0,10);
           
              }
       document.contactForm.homeMailStop.focus();
        return (false);
    }
  
    return (true);
};

function homeZipValidate(){
      var homeZip= document.contactForm.homeZip;
    
    
    if (homeZip.value != null && (homeZip.value != "")) {
        if(homeZip.value.replace(/^\s+|\s+$/g,"").length>15){
            alert("The homeZip must be less than 15 characters");
            
              str = new String( document.contactForm.homeZip.value);
             document.contactForm.homeZip.value=str.substring(0,15);
              }
       document.contactForm.homeZip.focus();
        return (false);
    }
  
    return (true);
};

function commentsValidate(){
      var comments= document.contactForm.comments;
    
    
    if (comments.value != null && (comments.value != "")) {
        if(comments.value.replace(/^\s+|\s+$/g,"").length>255){
            alert("The comments must be less than 255 characters");
            
            str = new String( document.contactForm.comments.value);
             document.contactForm.comments.value=str.substring(0,255); 
              }
       document.contactForm.comments.focus();
        return (false);
    }
  
    return (true);
};