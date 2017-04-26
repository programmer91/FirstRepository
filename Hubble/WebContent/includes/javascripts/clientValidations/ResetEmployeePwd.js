 function NewpasswordValidate(){
   
   var password= document.resetForm.newPassword;
  // alert(password.value)
    var decimal=  /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{4,15}$/;  
if(password.value.match(decimal))   
{   

 document.getElementById("payRollAuthenticationMsg").innerHTML=" ";
return true;  
}  
else  
{   

   document.getElementById("payRollAuthenticationMsg").innerHTML="<font color='red' size='2'>New Password must contain Minimum 4 characters,Maximum 15 characters, at least 1 Uppercase Alphabet, 1 Lowercase Alphabet, 1 Number and 1 Special Character!</font>";
    
return false;  
}  
    return (true);
};

 function CnfpasswordValidate(){
    var password= document.resetForm.confirmPassword;
    var newpassword = document.resetForm.newPassword.value;
    var cnfpasswd = document.resetForm.confirmPassword.value;
      var passwordregex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}";
//    
//    if(password.value.match(passwordregex)){
//        return true;
//    }else{
//      document.getElementById("payRollAuthenticationMsg1").innerHTML="<font color='red' size='2'>Password must contain Minimum 8 characters at least 1 Uppercase Alphabet, 1 Lowercase Alphabet, 1 Number and 1 Special Character!</font>";
//       return false;    
//    }
    
//    if (password.value != null && (password.value != "")) {
        if(newpassword != cnfpasswd){
          document.getElementById("payRollAuthenticationMsg").innerHTML="<font color='red' size='2'>New Password and confirm password must be same!</font>";
    
        }
        
//        if(password.value.replace(/^\s+|\s+$/g,"").length>20){
//            
//            str = new String(document.resetForm.confirmPassword.value);
//            //str.substring(0,8);
//            document.resetForm.confirmPassword.value="";
//            
//            alert("The Password must be less than 20 characters");
//            
//        }
//        document.resetForm.confirmPassword.focus();
//        return (false);
//    }
    
    return (true);
};



function resetEmpPwdSubmit(){
    var cofirmPwd = document.resetForm.confirmPassword.value;
    var newPassword = document.resetForm.newPassword.value;
    var oldPassword = document.resetForm.oldPassword.value;
    if(cofirmPwd=="" || cofirmPwd==null || newPassword=="" || newPassword==null || oldPassword=="" || oldPassword==null){
        alert("Enter Details");
        return (false);
    }else if(newPassword != cofirmPwd){
        alert("password not matched!");
        document.resetForm.newPassword.value = '';
        document.resetForm.confirmPassword.value = '';
        return (false);
    }else {
        return (true);
    }
}


function NewCustpasswordValidate(){
    
    var password= document.resetForm.newCustPassword;
    
    
    if (password.value != null && (password.value != "")) {
        if(password.value.replace(/^\s+|\s+$/g,"").length>8){
            
            str = new String(document.resetForm.newCustPassword.value);
            //str.substring(0,8);
            document.resetForm.newCustPassword.value="";
            
            alert("The Password must be less than 8 characters");
            
        }
        document.resetForm.newCustPassword.focus();
        return (false);
    }
    
    return (true);
};

function CnfCustpasswordValidate(){
    
    var password= document.resetForm.confirmCustPassword;
    var conPassword = document.resetForm.confirmCustPassword.value
    var newPassword = document.resetForm.newCustPassword.value
    
    if (password.value != null && (password.value != "")) {
        
        /*if(conPassword != newPassword ) {
            document.resetForm.confirmCustPassword.value="";
            alert("password not matched");
        }*/
        if(password.value.replace(/^\s+|\s+$/g,"").length>8){
            
            str = new String(document.resetForm.confirmCustPassword.value);
            ////str.substring(0,8);
            document.resetForm.confirmCustPassword.value="";
            
            alert("The Password must be less than 8 characters");
            
        }
        
        document.resetForm.confirmCustPassword.focus();
        return (false);
    }
    
    return (true);
};


function resetCustPwdSubmit(){
    var cofirmCustPwd = document.resetForm.confirmCustPassword.value;
    var newCustPassword = document.resetForm.newCustPassword.value;
    var oldCustPassword = document.resetForm.oldCustPassword.value;
    if(cofirmCustPwd=="" || cofirmCustPwd==null || newCustPassword=="" || newCustPassword==null || oldCustPassword=="" || oldCustPassword==null){
        alert("Enter Details");
        return (false);
    }else if(newCustPassword != cofirmCustPwd){
        alert("password not matched!");
        document.resetForm.confirmCustPassword.value = '';
        document.resetForm.newCustPassword.value = '';
        return (false);
    }else {
        return (true);
    }
}
