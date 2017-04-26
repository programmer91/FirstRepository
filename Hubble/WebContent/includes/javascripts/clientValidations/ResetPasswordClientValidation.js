// This is a javascript file


function oldPasswordValidate(){
    
    var oldPassword= document.resetPasswordForm.oldPassword;
    
    
    if (oldPassword.value != null && (oldPassword.value != "")) {
        if(oldPassword.value.replace(/^\s+|\s+$/g,"").length>8){
            
            str = new String(document.resetPasswordForm.oldPassword.value);
            document.resetPasswordForm.oldPassword.value=str.substring(0,8);
            
            alert("The oldPassword must be less than 8 characters");
            
        }
        document.resetPasswordForm.oldPassword.focus();
        return (false);
    }
    
    return (true);
};

function newPasswordValidate(){
    
    var newPassword= document.resetPasswordForm.newPassword;
    
    
    if (newPassword.value != null && (newPassword.value != "")) {
        if(newPassword.value.replace(/^\s+|\s+$/g,"").length>8){
            
            str = new String(document.resetPasswordForm.newPassword.value);
            document.resetPasswordForm.newPassword.value=str.substring(0,8);
            
            alert("The newPassword must be less than 8 characters");
            
        }
        document.resetPasswordForm.newPassword.focus();
        return (false);
    }
    
    return (true);
};

function confirmPasswordValidate(){
    
    var confirmPassword= document.resetPasswordForm.confirmPassword;
    
    
    if (confirmPassword.value != null && (confirmPassword.value != "")) {
        if(confirmPassword.value.replace(/^\s+|\s+$/g,"").length>8){
            
            str = new String(document.resetPasswordForm.confirmPassword.value);
            document.resetPasswordForm.confirmPassword.value=str.substring(0,8);
            
            alert("The confirmPassword must be less than 8 characters");
            
        }
        document.resetPasswordForm.confirmPassword.focus();
        return (false);
    }
    
    return (true);
};

/* Reset User password Validation Script */
function loginIdValidate(element){
    
    //var loginId= document.resetUserPwdForm.loginId;
    
    var i = 30;
    if (element.value != null && (element.value != "")) {
        if(element.value.replace(/^\s+|\s+$/g,"").length>i){
            subStringValue(i,element,"The loginId must be less than 30 characters");
        }
        return (true);
    }
};

function newUserPasswordValidate(element){
    
    //var newPassword= document.resetUserPwdForm.newPassword;
    
    var i = 20;
    if (element.value != null && (element.value != "")) {
        if(element.value.replace(/^\s+|\s+$/g,"").length>i){
            subStringValue(i,element,"The newPassword must be less than 20 characters");
        }
        return (true);
    }
};

function confirmUserPasswordValidate(element){
    
    var i = 20;
    var cnfpasswd = document.resetUserPwdForm.cnfPassword.value;
    var newpasswd = document.resetUserPwdForm.newPassword.value;
    
    if (element.value != null && (element.value != "")) {
        
        if(cnfpasswd != newpasswd){
            alert("password not matched!");  
        }
        if(element.value.replace(/^\s+|\s+$/g,"").length>i){
            subStringValue(i,element,"The confirmPassword must be less than 20 characters");
        }
        return (true);
    }
};


function subStringValue(i,element,message) {
    str = new String(element.value);
    element.value=str.substring(0,i);
    
    alert(message);
    element.focus();
    return (false);
}


