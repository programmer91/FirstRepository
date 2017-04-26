function validatenumber(xxxxx) {
	var maintainplus = '';
 	var numval = xxxxx.value
 	if ( numval.charAt(0)=='+' ){ var maintainplus = '+';}
 	curnumbervar = numval.replace(/[\\A-Za-z!"�$%^&*+_={};:'@#~,.�\/<>?|`�\]\[]/g,'');
 	xxxxx.value = maintainplus + curnumbervar;
 	var maintainplus = '';
        // alert("enter integers only");
 	xxxxx.focus;
}

function firstNameValidate(){
    
   var firstName= document.frmEmpSearch.firstName;
   
       
    if (firstName.value != null && (firstName.value != "")) {
        if(firstName.value.replace(/^\s+|\s+$/g,"").length>30){
            
            str = new String(document.frmEmpSearch.firstName.value);
             document.frmEmpSearch.firstName.value=str.substring(0,30);
            
            alert("The firstName must be less than 30 characters");
            
              
           
              }
       document.frmEmpSearch.firstName.focus();
        return (false);
    }
  
    return (true);
};


function workPhoneNoValidate(){
    
   var workPhoneNo= document.frmEmpSearch.workPhoneNo;
   
       
    if (workPhoneNo.value != null && (workPhoneNo.value != "")) {
        if(workPhoneNo.value.replace(/^\s+|\s+$/g,"").length>20){
            
                 str = new String(document.frmEmpSearch.workPhoneNo.value);
            document.frmEmpSearch.workPhoneNo.value=str.substring(0,20);   
            
            alert("The workPhoneNo must be less than 20 characters");
            
             
           
              }
       document.frmEmpSearch.workPhoneNo.focus();
        return (false);
    }
  
    return (true);
};


 function isNumberKey(element){
    var val=element.value;
    if (isNaN(val)) {
        alert("enter numeric values");
        element.value=val.substring(0, val.length-1);
        return false;
    }
    else
        return true;
}