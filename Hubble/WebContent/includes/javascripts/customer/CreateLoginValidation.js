// This is a javascript file

function customerPwdValidate(){
    
    var password= document.ContactLoginForm.password;
    
    
    if (password.value != null && (password.value != "")) {
        if(password.value.replace(/^\s+|\s+$/g,"").length>8){
            document.ContactLoginForm.password.value="";
            alert("Password limit is 8 characters");
        }
        document.ContactLoginForm.password.focus();
        return (false);
    }
    
    return (true);
};

function customerUserIdValidate(){
    
    var userId= document.ContactLoginForm.loginId;
    
    
    if (userId.value != null && (userId.value != "")) {
        if(userId.value.replace(/^\s+|\s+$/g,"").length>30){
            document.ContactLoginForm.loginId.value="";
            alert("The userId must be less than 30 characters");
            
        }
        document.ContactLoginForm.loginId.focus();
        return (false);
    }
    
    return (true);
};
function emailValidate(){
    
    var otherEmail= document.ContactLoginForm.emailId;
    
    if (otherEmail.value != null && (otherEmail.value != "")) {
        if(otherEmail.value.replace(/^\s+|\s+$/g,"").length>60){
            
            document.ContactLoginForm.emailId.value="";    
            
            alert("The otherEmail must be less than 60 characters");
            
        }
        document.ContactLoginForm.emailId.focus();
        return (false);
    }
    
    return (true);
};