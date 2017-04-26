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

function checkCreLoginForm() {
    consultantId = document.getElementById("consultantId").value;
    consultantEmail = document.getElementById("consultantEmail").value;
    if (consultantId == "") {
        hideAllErrors();
        document.getElementById("idError").style.display = "inline";
        document.getElementById("consultantId").select();
        document.getElementById("consultantId").focus();
        return false;
    }else if (consultantEmail == "") {
        hideAllErrors();
        document.getElementById("emailError").style.display = "inline";
        document.getElementById("consultantEmail").select();
        document.getElementById("consultantEmail").focus();
        return false;
    }else if(consultantEmail != ""){
       var result = checkEmail();
       return result;
    }
    return true;
};
function hideAllErrors() {
    document.getElementById("idError").style.display = "none"
    document.getElementById("emailError").style.display = "none"   
    document.getElementById("invalidEmail").style.display = "none"  
};




function checkEmail()
{
hideAllErrors();
var x=document.consultantLoginForm.consultantEmail.value;
//var x=document.forms["myForm"]["email"].value;

var atpos=x.indexOf("@");
var dotpos=x.lastIndexOf(".");
if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
  {
       // document.consultantLoginForm.consultantEmail.value="";
        document.getElementById("invalidEmail").style.display = "inline";
        document.getElementById("consultantEmail").select();
        document.getElementById("consultantEmail").focus();
     return false;
  }
}
