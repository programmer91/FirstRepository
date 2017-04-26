 function userIdValidate(){
    
   var userId= document.userLoginForm.userId;
   
       
    if (userId.value != null && (userId.value != "")) {
        if(userId.value.replace(/^\s+|\s+$/g,"").length>30){
            
             str = new String(document.userLoginForm.userId.value);
             document.userLoginForm.userId.value=str.substring(0,30);
            
            alert("The userId must be less than 30 characters");
            
           }
       document.userLoginForm.userId.focus();
        return (false);
    }
  
    return (true);
};

function passwordValidate(){
    
   var password= document.userLoginForm.password;
   
       
    if (password.value != null && (password.value != "")) {
        if(password.value.replace(/^\s+|\s+$/g,"").length>20){
            
              str = new String(document.userLoginForm.password.value);
             document.userLoginForm.password.value=str.substring(0,20);
             
            alert("Please make sure your Password");
            
               }
       document.userLoginForm.password.focus();
        return (false);
    }
  
    return (true);
};

 function customerUserIdValidate(){
    
   var userId= document.custLoginForm.customerId;
   
       
    if (userId.value != null && (userId.value != "")) {
        if(userId.value.replace(/^\s+|\s+$/g,"").length>30){
            
             str = new String(document.custLoginForm.customerId.value);
             document.custLoginForm.customerId.value=str.substring(0,30);
            
            alert("The userId must be less than 30 characters");
            
           }
       document.custLoginForm.customerId.focus();
        return (false);
    }
  
    return (true);
};

function customerPasswordValidate(){
    
   var password= document.custLoginForm.customerPwd;
   
       
    if (password.value != null && (password.value != "")) {
        if(password.value.replace(/^\s+|\s+$/g,"").length>8){
            
              str = new String(document.custLoginForm.customerPwd.value);
             document.custLoginForm.customerPwd.value=str.substring(0,8);
             
            alert("Please make sure your Password");
            
               }
       document.custLoginForm.customerPwd.focus();
        return (false);
    }
  
    return (true);
};

function partnerUserIdValidate() {

var userId= document.partnerLoginForm.partnerId;
   
       
    if (userId.value != null && (userId.value != "")) {
        if(userId.value.replace(/^\s+|\s+$/g,"").length>30){
            
             str = new String(document.partnerLoginForm.partnerId.value);
             document.partnerLoginForm.partnerId.value=str.substring(0,30);
            
            alert("The partnerId must be less than 30 characters");
            
           }
       document.partnerLoginForm.partnerId.focus();
        return (false);
    }
  
    return (true);
};
function partnerPasswordValidate() {

 var password= document.partnerLoginForm.partnerPwd;
   
       
    if (password.value != null && (password.value != "")) {
        if(password.value.replace(/^\s+|\s+$/g,"").length>8){
            
              str = new String(document.partnerLoginForm.partnerPwd.value);
             document.partnerLoginForm.partnerPwd.value=str.substring(0,8);
             
            alert("Please make sure your Password");
            
               }
       document.partnerLoginForm.partnerPwd.focus();
        return (false);
    }
  
    return (true);
};