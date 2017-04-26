function fieldLengthValidator(element) {
    var i=0;
 //alert("In Field Length validator ESCV");
    if(element.value!=null&&(element.value!="")) {
        if(element.id=="ssn") { 
            i=12;
        }
        
       //  if(element.id == 'billingManager'|| element.id == 'bankName') { 
       if(element.id == 'billingManager'|| element.id == 'bankName' || element.id == 'uanNo' || element.id == 'pfno') { 
            i=50;
            }
if(element.id=="perCompleted")
{
     i=3;
            // var pc = element.value;
                if(element.value>100){
                    alert("Percent should be below 100.")
                 element.value = "";
                 return false;
                }
}


  

        if(element.id=="days"){
            i=3;
            // var pc = element.value;
                if(element.value>100){
                    alert("Days should be below 50.")
                 element.value = "";
                 return false;
                }
            //alert("pc--->"+pc);
        }
        if(element.id=="itgBatch")
        {i=8;
        }if(element.id=="firstName"||element.id=="lastName"||element.id=="fatherName"||element.id=="fatherTitle"||element.id=="referalName"||element.id=="referalRelation"||element.id=="bondProvidedBy"||element.id=="relationToEmployee"||element.id=="jobTitle"||element.id=="portOfEntry"||element.id=="i94No"||element.id=="healthInsComName"||element.id=="medicalLeave"||element.id=="middleName"||element.id=="lastContactBy"||element.id== "accNum"){i=30;
        }if(element.id=="IssueDescription" ){i=150;
        }if(element.id=="IssueComments" || element.id=="resolution" || element.id=="comments1" || element.id=="description" || element.id=="comments" || element.id=="resolutionOthers" || element.id=="commentsOthers" || element.id=="resolutionInfra" || element.id=="commentsInfra" || element.id=="resolutionNetwork" || element.id=="commentsNetwork" || element.id=="resolutionProject" || element.id=="commentsProject" || element.id=="commentsHubble" || element.id=="resolutionHubble"){i=1500;
        }if(element.id=="issueName" ||element.id=="title"|| element.id=="nameAsPerAcc" || element.id=="nameAsPerPan" || element.id=="aadharName"){i=100;
        }if(element.id=="txtNotes"||element.id=="comment"||element.id=="fatherAddress"||element.id=="referalComments"||element.id=="queryComments"){i=255;
        }if(element.id=="homeAddLine1"||element.id=="homeAddLine2"||element.id=="payrollAddLine1"||element.id=="payrollAddLine2"||element.id=="cprojectAddLine1"||element.id=="cprojectAddLine2"||element.id=="hotelAddLine1"||element.id=="hotelAddLine2"||element.id=="offShoreAddLine1"||element.id=="offShoreAddLine2"||element.id=="otherAddLine1"||element.id=="otherAddLine2"||element.id=="jobCompany"||element.id=="passportIssuedAt"||element.id=="h1Lin"||element.id=="h1Title"||element.id=="gcRef"||element.id=="gcTitle" ||element.id=="refferedBy"){i=50;
        }if(element.id=="homeCity"||element.id=="payrollCity"||element.id=="cprojectCity"||element.id=="hotelCity"||element.id=="offShoreCity"||element.id=="otherCity" || element.id=="ifscCode"){i=25;
        }if(element.id=="homeZip"||element.id=="payrollZip"||element.id=="cprojectZip"||element.id=="hotelZip"||element.id=="offShoreZip"||element.id=="otherZip"){i=15;
        validatenumber(element);
        }if(element.id=="h1LcaEtaNo"||element.id=="licPolicyNumber"||element.id=="licPolicyComNumber"||element.id=="healthInsPolicyNumber"||element.id=="aliasName"||element.id=="nsrno"|| element.id=="aadharNum"){i=15;
        }if(element.id=="passOut" || element.id=="pgPassOut"){i=4;
        }
        if(element.id=="laptopType"||element.id=="memory"||element.id=="hardDisk"||element.id=="model"||element.id=="serialNo"||element.id=="passportNo"||element.id=="faxNo"||element.id=="midName"){i=20;
        }if(element.id=="contractOnField"||element.id=="contractPeriod"||element.id=="trainPeriod"||element.id=="licPolicyValues"||element.id=="healthInsCoverage"||element.id=="healthInsNumOfDep"||element.id=="healthInsDedAmt"||element.id=="dentalInsurenceType"||element.id=="dentalInsurenceCoverage"||element.id=="empno"){i=10;
        }if(element.id=="jobAddress"||element.id=="shortTermDisability"||element.id=="longTermDisability"){i=80;
        }if(element.id=="ptUniversity"){i=40;
        }if(element.id=="reason" ||element.id=="skillSet" || element.id=="aboutMe")
        {i=500;
        }
        //New Change
        if(element.id=="university" || element.id=="pgUniversity")
        {i=30;
        }if(element.id=="college" || element.id=="pgCollege")
        {i=100;
        }
        if(element.id=="roomNo"){
            i=6;
        }
         if(element.id=="profileSkillSet"){
            i=1000;
        }
        if(element.value.replace(/^\s+|\s+$/g,"").length>i) {
            str=new String(element.value);
            element.value=str.substring(0,i);
            alert("The "+element.id+" length must be less than "+i+" characters");
            element.focus();
            return false;
        }
        return true;
        
        
    }
}


function formatPhone(element){str=new String(element.value);
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
 }else{if(num.length>10){alert("Phone Number should be 10 characters");
 element.value=_return;
 element.value="";
 element.focus();
 return false;
 }else{if(num.length<10){alert("Please give atleast  10 charcters in PhoneNumber");
 element.value="";
 }}}return _return;
 }function checkEmail(element){if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(element.value)){return(true);
 }element.value="";
 alert("Invalid E-mail Address! Please re-enter.");
 return(false);
 }function valueCheck(myForm){email=myForm.value;
 var is_value=email.indexOf("miraclesoft.com");
 if(is_value==-1){myForm.value="";
 alert("You should enter valid Miracle Mail Id!");
 }else{checkEmail(myForm);
 }}function validatenumber(xxxxx){var maintainplus="";
 var numval=xxxxx.value;
 if(numval.charAt(0)=="+"){var maintainplus="+";
 }curnumbervar=numval.replace(/[\\A-Za-z!"?$%^&*+_={};:'@#~,?\/<>?|`?\]\[]/g,"");
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
 }}function validateCtc(){
var practice = document.getElementById("practiceId").value;
//alert("hi-->"+practice);
if(practice == 'East' || practice == 'West' || practice == 'Central'){
        
        document.getElementById("territory_div").style.display='';
        
        
    }else{
         document.getElementById("territory_div").style.display='none';
         
   }
var ctcPerYaer=document.getElementById("ctcPerYear1").value;
 if(ctcPerYaer<=24){var ctcResult=(((ctcPerYaer)*(100000))/(2080*42));
 var strCtcResult=String(ctcResult);
 document.getElementById("intRatePerHour").value=strCtcResult.substring(0,4);
 }else{alert("enter range between 0 to 24");
 document.getElementById("ctcPerYear1").value="";
 document.getElementById("intRatePerHour").value="";
 }}
 function fieldValidation(){
     var categoryId = document.getElementById("categoryId").value;
     if(categoryId==""){
         
         alert("Please provide category type !");
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

//======================
//for Timestamp checking
//======================
function validateTimestamp(element) {
    //alert("hi...");
    timeStamp = element.value;    
    if(timeStamp.length > 19){
        alert("Invalid Timestamp Format");
        element.value = "";
        element.focus();
         return;
    }
    var split = timeStamp.split(/[^\d]+/);

    var year = parseFloat(split[2]);
    var month = parseFloat(split[0]);
    var day = parseFloat(split[1]);

    var hour = parseFloat(split[3]);
    var minute = parseFloat(split[4]);
    var second = parseFloat(split[5]);
    if(timeStamp != null){
         if (!/\d{2}\/\d{2}\/\d{4} \d{2}:\d{2}:\d{2}/.test(timeStamp)) {
            alert("Invalid Timestamp Format");
            element.value = "";
            element.focus();
             return;
         }
         if(hour > 25 || minute > 61 || second > 61 || month > 13 || day > 32){
            alert("Invalid Timestamp Format");
            element.value = "";
            element.focus();    
             return;
        }
    }
}

//=================================
//No Select in ReAssignAccounts.jsp
//=================================

function validateReassign()
{
    var from = document.getElementById("frmLoginId").value;
    var to = document.getElementById("toLoginId").value;        
    if(from.length == 0 || to.length == 0){
        alert("Please Select the Teams for Reassigning..");
        return false;
    }
    else 
        return true;
}


//=================================================
//Reset method for ConsultantSearch page(08/05/2013)
//==================================================

function resetvalues()
        {
            document.consultantsearchForm.fiName.value="";
            document.consultantsearchForm.skillSet.value="";
            document.consultantsearchForm.email1.value=""; 
            document.consultantsearchForm.comments.value=""; 
            document.consultantsearchForm.source.value=""; 
            document.consultantsearchForm.assignedTo.value=""; 
            document.consultantsearchForm.YrsExp.value=""; 
            document.consultantsearchForm.technology.value=""; 
            document.consultantsearchForm.inputRowData.value=""; 
            document.consultantsearchForm.AvailableFrom.value=""; 
            document.consultantsearchForm.availableTo.value=""; 
            document.consultantsearchForm.startDate.value=""; 
            document.consultantsearchForm.endDate.value=""; 
            document.consultantsearchForm.org.value="All";
            document.consultantsearchForm.locationList.selectedIndex = -1;
            document.consultantsearchForm.workAuthorizationList.selectedIndex = -1;
             return false;
        }
        
//New Changes

function isNumber(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode
    //alert(charCode);
    if (charCode!=46 && charCode > 31 && (charCode < 48 || charCode > 57) && charCode >= 1 && charCode <=122)
        return false;
    
    return true;
}





function untouchedResetvalues()
{
//    alert("reset");
      document.unTouchedConsultantsearchForm.AvailableFrom.value=""; 
      document.unTouchedConsultantsearchForm.fiName.value=""; 
      document.unTouchedConsultantsearchForm.skillSet.value=""; 
      document.unTouchedConsultantsearchForm.email1.value=""; 
      document.unTouchedConsultantsearchForm.comments.value=""; 
      document.unTouchedConsultantsearchForm.locationList.selectedIndex = -1;
      document.unTouchedConsultantsearchForm.workAuthorizationList.selectedIndex = -1; 
      document.unTouchedConsultantsearchForm.source.value=""; 
      document.unTouchedConsultantsearchForm.assignedTo.value=""; 
      document.unTouchedConsultantsearchForm.YrsExp.value=""; 
      document.unTouchedConsultantsearchForm.available.value=""; 
      document.unTouchedConsultantsearchForm.availableTo.value=""; 
      document.unTouchedConsultantsearchForm.startDate.value=""; 
      document.unTouchedConsultantsearchForm.endDate.value=""; 
      document.unTouchedConsultantsearchForm.org.value="All"; 
      document.unTouchedConsultantsearchForm.titleTypeId.value=""; 
      document.unTouchedConsultantsearchForm.activityFromDate.value=""; 
             return false;
}



function fieldLengthValidatorTaskComments(element){
    if(element.value!=null&&(element.value!="")) {
   var i=2000;
      if(element.value.replace(/^\s+|\s+$/g,"").length>i) {
            str=new String(element.value);
            element.value=str.substring(0,i);
            alert("The "+element.id+" length must be less than "+2000+" characters");
            element.focus();
            return false;
        }
        return true;
    
    }
}
