function fieldLengthValidator(element){
 //alert("In Field Length validator RSCV");
var i=0;
if(element.value!=null&&(element.value!="")){if(element.id=="FirstName"||element.id=="LastName"||element.id=="searchName"){i=30;
}if(element.id=="MiddleName"||element.id=="searchPhone"){i=20;
}if(element.id=="aliasName"||element.id=="Zip"||element.id=="curZip"||element.id=="offZip"||element.id=="othZip"){i=15;
}if(element.id=="preferedAnswer"){i=16;
}if(element.id=="ssn"){i=12;
}if(element.id=="MailStop"||element.id=="curMailStop"||element.id=="offMailStop"||element.id=="othMailStop"){i=10;
}if(element.id=="City"||element.id=="curCity"||element.id=="offCity"||element.id=="othCity"){i=25;
}if(element.id=="refferedBy"||element.id=="AddressLine1"||element.id=="AddressLine2"||element.id=="curAddressLine1"||element.id=="curAddressLine2"||element.id=="offAddressLine1"||element.id=="offAddressLine2"||element.id=="othAddressLine1"||element.id=="othAddressLine2" ||element.id=="titleTypeId"||element.id=="refferedBy"||element.id=="source"){i=50;
}if(element.id=="currentEmployer"||element.id=="industryId"){i=100;
}if(element.id=="skills"||element.id=="rateNegotiated"||element.id=="description"){i=250;
}if(element.id=="ratePerHour"){i=200;
}if(element.id=="comments"){i=1000;
}if(element.value.replace(/^\s+|\s+$/g,"").length>i){str=new String(element.value);
element.value=str.substring(0,i);
alert("The "+element.id+" must be less than "+i+" characters");
element.focus();
return false;
}return true;
}}function skillValidate(element){element.value=filterNum(element.value);
function filterNum(str){re=/\$|@|~|`|\%|\^|\+|\=|\[|\_|\]|\[|\}|\{|\;|\:|\'|\"|\<|\>|\?|\||\\|\!|\$/g;
return str.replace(re,",");
}}
function formatPhone(element){


str=new String(element.value);
element.value=str.replace(/[A-Za-z\(\)\.\-\x\s,]/g,"");
num=element.value;
var _return;
if(num.length==10){_return="(";
var ini=num.substring(0,3);
_return+=ini+")";
var st=num.substring(3,6);
_return+="-"+st+"-";
var end=num.substring(6,10);
_return+=end;
element.value="";
element.value=_return;
}else{if(num.length==16){_return="(";
var ini=num.substring(0,3);
_return+=ini+")";
var st=num.substring(3,6);
_return+="-"+st+"-";
var last=num.substring(6,10);
_return+=last+" X";
var end=num.substring(10,16);
_return+=end;
element.value="";
element.value=_return;
}else{if(num.length>16){alert("Phone Number should be 16 characters");
element.value=_return;
element.value=num.substring(0,16);
element.value=num.substring(0,16);
_return="(";
var ini=num.substring(0,3);
_return+=ini+")";
var st=num.substring(3,6);
_return+="-"+st+"-";
var last=num.substring(6,10);
_return+=last+" X";
var end=num.substring(10,16);
_return+=end;
element.value="";
element.value=_return;
element.focus();
return false;
}else{if(num.length<10){alert("Please give atleast  10 charcters in PhoneNumber");
return false;
}else{if(num.length>10&&num.length<16){alert("Please give atleast  16 charcters in PhoneNumber");
return false;
}}}}
}
if(num == "1111111111" || num == "1234567890"){
    
    alert("Enter Valid Number.");
    element.value = "";
    return false;
}

return _return;
}


function checkEmail(element){if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(element.value)){return(true);
}element.value="";
alert("Invalid E-mail Address! Please re-enter.");
return(false);
}function validatenumber(xxxxx){var maintainplus="";
var numval=xxxxx.value;
if(numval.charAt(0)=="+"){var maintainplus="+";
}curnumbervar=numval.replace(/[\\A-Za-z!"�$%^&*+_={};:'@#~,�\/<>?|`�\]\[]/g,"");
xxxxx.value=maintainplus+curnumbervar;
var maintainplus="";
xxxxx.focus;
}function attachmentValidate(element){if(element.value==null||(element.value=="")){alert("Please Enter valid File Path");
return(true);
}return(false);
}function handleEnter(field,event){var keyCode=event.keyCode?event.keyCode:event.which?event.which:event.charCode;
if(keyCode==13){var i;
for(i=0;
i<field.form.elements.length;
i++){if(field==field.form.elements[i]){break;
}}i=(i+1)%field.form.elements.length;
field.form.elements[i].focus();
return false;
}else{return true;
}}


function checkConsultantAddForm() {
    //alert("hiii");
    var fname = document.addConsultantForm.fiName.value;
    var lname = document.addConsultantForm.laName.value;
    var email = document.addConsultantForm.email2.value;
    var offPhone = document.addConsultantForm.workPhoneNo.value;
    var cellPhone = document.addConsultantForm.cellPhoneNo.value;
    var homPhone = document.addConsultantForm.homePhoneNo.value;
    var gender = document.addConsultantForm.gender.value;
    var source= document.addConsultantForm.source.value;
    var country= document.addConsultantForm.country.value;
    var exp= document.addConsultantForm.exp.value;
    /*if(offPhone.length < 10 || cellPhone.length < 10 || homPhone.length < 10) {
        alert('Please give atleast  10 charcters in PhoneNumber');
        return false;
    }else {
        return true;
    }*/
    
    if(gender == '1') {
        alert("Enter Mandatory Fields");
        return false;
    }else if(fname=="" || lname=="" || email=="" && gender == '1') {
        alert("Enter Mandatory Fields");
        return false;
    }else if(fname=="" || lname=="" || email==""  || (offPhone=="" && homPhone=="" && cellPhone=="")) {
        alert("Enter atleast One Phone No.");
        return false;
    }else if(country == ""){
        alert("Please select state");
        return false;
    }
    else if(source=="")
   {
   alert("Enter source");
   return false;
   } else if(exp=='1'){
   alert("Enter Experience");
   return false;
   }else if((isExist == "true") && (document.addConsultantForm.save.value == "Add")) {
        alert("Consultant Already Exists!!!please select from suggestion list..");
        return false;
    }
    else {
        return true;
    }

}

//=================
//for date checking
//=================

function checkDates(element)
{     
    var birthDate = element.value;
    
    if(birthDate.length > 10){
        alert("Invalid Date Format");
        element.value = "";
        element.focus();
        return;
    }
    var split = birthDate.split(/[^\d]+/);
    var year = parseFloat(split[2]);
    var month = parseFloat(split[0]);
    var day = parseFloat(split[1]);
    if(birthDate != null){
        if (!/\d{2}\/\d{2}\/\d{4}/.test(birthDate)) {
            alert("Invalid Date Format");
            element.value = "";
            element.focus();
             return;
         }
         if(month > 13 || day > 32){
            alert("Invalid Date Format");
            element.value = "";
            element.focus();     
             return;
        }
    }
}

// New validations for resume name and attachment name validation in AddConsultent.jsp

//new functions for consultant resume attachment validations

function attachmentNameValidate(){
    
   var attachmentName= document.addConsultantForm.attachmentName;
   
       
    if (attachmentName.value != null && (attachmentName.value != "")) {
        if(attachmentName.value.replace(/^\s+|\s+$/g,"").length>50){
                       
                 str = new String(document.addConsultantForm.attachmentName.value);
             document.addConsultantForm.attachmentName.value=str.substring(0,50);
             
               alert("The AttachResumeName must be less than 50 characters");
           
              }
       document.addConsultantForm.attachmentName.focus();
        return (false);
    }
  
    return (true);
};


function attachmentFileNameValidate(){
    //alert("attachmentFileNameValidate");
   var attachmentFileName= document.addConsultantForm.upload;
   
       
    if (attachmentFileName.value != null && (attachmentFileName.value != "")) {
        if(attachmentFileName.value.length>40){
                       
                 
               document.getElementById('upload').value = "";
               alert("File name must be less than 40 characters.Please rename !");
           
              }
       document.addConsultantForm.upload.focus();
        return (false);
    }
  
    return (true);
};