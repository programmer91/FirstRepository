/****************************************************************
 COMMON VALIDATION  INITIALIZATION SCRIPT
 
 In order for mainvalidation to know which validation rules to apply, 
 you must first initialize the validation process. 
 This is done by setting properties on the form fields you wish to validate
 note:
 Supported browsers IE5, Mozilla,Firefox
 
 Author: MrutyumajayaRao.Chenn<mchennu@miraclesoft.com>
 *****************************************************************/

/***********************************************
 * Disable "Enter" key in Form script- By MrutyumjayaRao.Chennu
 * Mail: mchennu@miraclesoft.com
 ***********************************************/

function handleEnter(field, event) {
  //  alert("hii");
    var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
    if (keyCode == 13) {
        var i;
        for (i = 0; i < field.form.elements.length; i++)
            if (field == field.form.elements[i])
                break;
        i = (i + 1) % field.form.elements.length;
        field.form.elements[i].focus();
        return false;
    } 
    else
        return true;
};   
/*****************************************************************
 * Validation  script for Login.jsp page - User Form
 /******************************************************************/

function checkMcertLoginForm() {
   // alert("hii");
    consultantId = document.getElementById("loginId").value;
    consultantEmail = document.getElementById("password").value;
   // alert("consultantId.."+consultantId);
    if (consultantId == "") {
        hideAllErrors();
        document.getElementById("idError").style.display = "inline";
        document.getElementById("loginId").select();
        document.getElementById("loginId").focus();
        return false;
    }else if (consultantEmail == "") {
        hideAllErrors();
        document.getElementById("pwdError").style.display = "inline";
        document.getElementById("password").select();
        document.getElementById("password").focus();
        return false;
    }
    return true;
};
function hideAllErrors() {
    document.getElementById("idError").style.display = "none"
    document.getElementById("pwdError").style.display = "none"   
   
};



//
//function checkEmail()
//{
//hideAllErrors();
//var x=document.consultantLoginForm.consultantEmail.value;
////var x=document.forms["myForm"]["email"].value;
//
//var atpos=x.indexOf("@");
//var dotpos=x.lastIndexOf(".");
//if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
//  {
//       // document.consultantLoginForm.consultantEmail.value="";
//        document.getElementById("invalidEmail").style.display = "inline";
//        document.getElementById("password").select();
//        document.getElementById("password").focus();
//     return false;
//  }
//}
