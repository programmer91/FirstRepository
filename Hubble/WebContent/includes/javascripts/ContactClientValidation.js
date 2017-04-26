//PHONE NUMBER FORMAT SCRIPT :START
  function homeFormatPhone(num)
{ 

str = new String(document.contactForm.homePhone.value);
document.contactForm.homePhone.value=str.replace(/[\(\)\.\-\s,]/g, "");
num=document.contactForm.homePhone.value;
   var _return; 
  if(num.length == 10) 
  { 
    
  _return="(";
	var ini = num.substring(0,3);
	_return+=ini+")";
	var st = num.substring(3,6);
	_return+="-"+st+"-";
	var end = num.substring(6,10);
	_return+=end;
  
 document.contactForm.homePhone.value ="";
   document.contactForm.homePhone.value =_return;
         
  }else if(num.length > 10)
  {
   _return="(";
	var ini = num.substring(0,3);
	_return+=ini+")";
	var st = num.substring(3,6);
	_return+="-"+st+"-";
	var end = num.substring(6,10);
	_return+=end+"x";
	var ext = num.substring(10,num.length);
	_return+=ext;
          
           document.contactForm.homePhone.value ="";
          document.contactForm.homePhone.value =_return;
            return false;
 }else if(num.length < 10)
 {
   alert('Please give atleast  10 charcters in PhoneNumber');
   document.contactForm.homePhone.value ="";
 }

return _return;
}  

  function officeFormatPhone(num)
{ 

str = new String(document.contactForm.officePhone.value);
document.contactForm.officePhone.value=str.replace(/[\(\)\.\-\s,]/g, "");
num=document.contactForm.officePhone.value;
var _return;  
  if(num.length == 10) 
  { 
    
  _return="(";
	var ini = num.substring(0,3);
	_return+=ini+")";
	var st = num.substring(3,6);
	_return+="-"+st+"-";
	var end = num.substring(6,10);
	_return+=end;
  
 document.contactForm.officePhone.value ="";
   document.contactForm.officePhone.value =_return;
         
  }else if(num.length > 10)
  {
   _return="(";
	var ini = num.substring(0,3);
	_return+=ini+")";
	var st = num.substring(3,6);
	_return+="-"+st+"-";
	var end = num.substring(6,10);
	_return+=end+"x";
	var ext = num.substring(10,num.length);
	_return+=ext;
          
           document.contactForm.officePhone.value ="";
          document.contactForm.officePhone.value =_return;
            return false;
 }else if(num.length < 10)
 {
   alert('Please give atleast  10 charcters in PhoneNumber');
   document.contactForm.officePhone.value ="";
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
      //  alert("enter integers only");
 	xxxxx.focus;
}

function checkEmail(myForm) {
    
    if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(myForm)){
        return (true)
    }
     document.contactForm.officeEmail.value="";
    alert("Invalid E-mail Address! Please re-enter.")
   
    return (false)
}

function checkEmail1(myForm) {
    
    if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(myForm)){
        return (true)
    }
     document.contactForm.personalEmail.value="";
    alert("Invalid E-mail Address! Please re-enter.")
   
    return (false)
}
     
function firstNameValidate(){
      var firstName= document.contactForm.firstName;
    
    
    if (firstName.value != null && (firstName.value != "")) {
        if(firstName.value.replace(/^\s+|\s+$/g,"").length>30){
            
             str = new String( document.contactForm.firstName.value);
             document.contactForm.firstName.value=str.substring(0,30);
            
            alert("The firstName must be less than 30 characters");
          
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
            
               str = new String( document.contactForm.lastName.value);
             document.contactForm.lastName.value=str.substring(0,30);
            
            alert("The lastName must be less than 30 characters");
           
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
            
               str = new String( document.contactForm.middleName.value);
             document.contactForm.middleName.value=str.substring(0,30);
            
            alert("The middleName must be less than 30 characters");
         
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
            
              str = new String( document.contactForm.aliasName.value);
             document.contactForm.aliasName.value=str.substring(0,20);
            
            alert("The aliasName must be less than 20 characters");
            
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
            
              str = new String( document.contactForm.title.value);
             document.contactForm.title.value=str.substring(0,40);
            
            alert("The title must be less than 40 characters");
            
          
           
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
            
                str = new String( document.contactForm.referredBy.value);
             document.contactForm.referredBy.value=str.substring(0,30);
            
            alert("The referredBy must be less than 30 characters");
            
          
           
              }
       document.contactForm.referredBy.focus();
        return (false);
    }
  
    return (true);
};

/*function sourceValidate(){
      var source= document.contactForm.source;
    
    
    if (source.value != null && (source.value != "")) {
        if(source.value.replace(/^\s+|\s+$/g,"").length>50){
            
              str = new String( document.contactForm.source.value);
             document.contactForm.source.value=str.substring(0,50);
            
            alert("The source must be less than 50 characters");
            
          
           
              }
       document.contactForm.source.focus();
        return (false);
    }
  
    return (true);
};*/

function specializationValidate(){
      var specialization= document.contactForm.specialization;
    
    
    if (specialization.value != null && (specialization.value != "")) {
        if(specialization.value.replace(/^\s+|\s+$/g,"").length>40){
            
            str = new String( document.contactForm.specialization.value);
             document.contactForm.specialization.value=str.substring(0,40);
            
            alert("The specialization must be less than 40 characters");
            
              
       
           
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
            
               str = new String( document.contactForm.officePhone.value);
             document.contactForm.officePhone.value=str.substring(0,16);
            
            alert("The officePhone must be less than 16 characters");
            
          
           
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
            
                 str = new String( document.contactForm.cellPhone.value);
             document.contactForm.cellPhone.value=str.substring(0,16); 
            
            alert("The cellPhonemust be less than 16 characters");
            
        
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
            
               str = new String( document.contactForm.homePhone.value);
             document.contactForm.homePhone.value=str.substring(0,16);
            
            alert("The homePhone must be less than 16 characters");
            
         
           
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
            
               str = new String( document.contactForm.fax.value);
             document.contactForm.fax.value=str.substring(0,16);
            
            alert("The fax must be less than 16 characters");
            
         
           
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
            
              str = new String( document.contactForm.officeEmail.value);
             document.contactForm.officeEmail.value=str.substring(0,50);
            
            alert("The officeEmail must be less than 50 characters");
            
          
           
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
            
            str = new String( document.contactForm.personalEmail.value);
             document.contactForm.personalEmail.value=str.substring(0,50);
            
            alert("The personalEmail must be less than 50 characters");
            
            
           
              }
       document.contactForm.personalEmail.focus();
        return (false);
    }
  
    return (true);
};

function resAddressLine1Validate(){
      var resAddressLine1= document.contactForm.resAddressLine1;
    
    
    if (resAddressLine1.value != null && (resAddressLine1.value != "")) {
        if(resAddressLine1.value.replace(/^\s+|\s+$/g,"").length>200){
            
              str = new String( document.contactForm.resAddressLine1.value);
             document.contactForm.resAddressLine1.value=str.substring(0,200); 
            
            alert("The resAddressLine1 must be less than 200 characters");
            
            
           
              }
       document.contactForm.resAddressLine1.focus();
        return (false);
    }
  
    return (true);
};

function resAddressLine2Validate(){
      var resAddressLine2= document.contactForm.resAddressLine2;
    
    
    if (resAddressLine2.value != null && (resAddressLine2.value != "")) {
        if(resAddressLine2.value.replace(/^\s+|\s+$/g,"").length>50){
            
             str = new String( document.contactForm.resAddressLine2.value);
             document.contactForm.resAddressLine2.value=str.substring(0,50);
            
            alert("The resAddressLine2 must be less than 50 characters");
            
            
           
              }
       document.contactForm.resAddressLine2.focus();
        return (false);
    }
  
    return (true);
};

function resCityValidate(){
      var resCity= document.contactForm.resCity;
    
    
    if (resCity.value != null && (resCity.value != "")) {
        if(resCity.value.replace(/^\s+|\s+$/g,"").length>25){
            
              str = new String( document.contactForm.resCity.value);
             document.contactForm.resCity.value=str.substring(0,25);
            
            alert("The resCity must be less than 25 characters");
            
           
           
              }
       document.contactForm.resCity.focus();
        return (false);
    }
  
    return (true);
};

function resMailStopValidate(){
      var resMailStop= document.contactForm.resMailStop;
    
    
    if (resMailStop.value != null && (resMailStop.value != "")) {
        if(resMailStop.value.replace(/^\s+|\s+$/g,"").length>10){
            
               str = new String( document.contactForm.resMailStop.value);
             document.contactForm.resMailStop.value=str.substring(0,10);
            
            alert("The resMailStop must be less than 10 characters");
            
         
           
              }
       document.contactForm.resMailStop.focus();
        return (false);
    }
  
    return (true);
};

function resZipValidate(){
      var resZip= document.contactForm.resZip;
    
    
    if (resZip.value != null && (resZip.value != "")) {
        if(resZip.value.replace(/^\s+|\s+$/g,"").length>15){
            
               str = new String( document.contactForm.resZip.value);
             document.contactForm.resZip.value=str.substring(0,15);
            
            alert("The resZip must be less than 15 characters");
            
           
           
              }
       document.contactForm.resZip.focus();
        return (false);
    }
  
    return (true);
};

function commentsValidate(){
      var comments= document.contactForm.comments;
    
    
    if (comments.value != null && (comments.value != "")) {
        if(comments.value.replace(/^\s+|\s+$/g,"").length>255){
            
                 str = new String( document.contactForm.comments.value);
             document.contactForm.comments.value=str.substring(0,255);       
            
            alert("The comments must be less than 255 characters");
            
             
                          
           
              }
       document.contactForm.comments.focus();
        return (false);
    }
  
    return (true);
};

function leadSourceValidate(){
      var leadSource= document.contactForm.leadSource;
    
    
    if (leadSource.value != null && (leadSource.value != "")) {
        if(leadSource.value.replace(/^\s+|\s+$/g,"").length>50){
            alert("The leadSource must be less than 50 characters");
            
            str = new String( document.contactForm.leadSource.value);
             document.contactForm.leadSource.value=str.substring(0,50);
           
              }
       document.contactForm.leadSource.focus();
        return (false);
    }
  
    return (true);
};


