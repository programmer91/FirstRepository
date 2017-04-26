function fieldLengthValidator(element) {
    var i=0;
 //alert("In Field Length validator ESCV");
    if(element.value!=null&&(element.value!="")) {
        if(element.id=="ssn") { 
            i=12;
        }
        
         if(element.id == 'billingManager') { 
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
          if(element.id=="creFatherName"||element.id=="refName"||element.id=="jfairLocation"||element.id=="recLocation"){
            i=60;
        }
        if(element.id=="pin"){
            i=6;
            validatenumber(element);
        }
        if(element.id=="addInfo"){
            i = 200;
        }
        if(element.id=="skill1" || element.id=="scale1"||element.id=="skill2" || element.id=="scale2"||element.id=="skill3" || element.id=="scale3"||element.id=="skill4" || element.id=="scale4"||element.id=="skill5" || element.id=="scale5"||element.id=="skill6" || element.id=="scale6")
        {
            i = 30;
        }
        if(element.id=="company"||element.id=="company1"||element.id=="company2")
        {
            i = 80;
        }
        if(element.id=="designation"||element.id=="designation1"||element.id=="designation2")
        {
            i = 30;
        }
        if(element.id=="location"||element.id=="location1"||element.id=="location2")
        {
            i = 40;
        }

if(element.id=="itgBatch")
        {i=8;
        }if(element.id=="firstName"||element.id=="lastName"||element.id=="fatherName"||element.id=="fatherTitle"||element.id=="referalName"||element.id=="referalRelation"||element.id=="bondProvidedBy"||element.id=="relationToEmployee"||element.id=="jobTitle"||element.id=="portOfEntry"||element.id=="i94No"||element.id=="healthInsComName"||element.id=="medicalLeave"||element.id=="middleName"||element.id=="lastContactBy"||element.id=="fatherOccupation"||element.id=="city"||element.id=="state"){i=30;
        }if(element.id=="IssueDescription"||element.id=="address"){i=150;
        }if(element.id=="IssueComments" || element.id=="resolution" || element.id=="comments1" || element.id=="description"){i=1500;
        }if(element.id=="issueName" ||element.id=="title"){i=100;
        }if(element.id=="txtNotes"||element.id=="comment"||element.id=="fatherAddress"||element.id=="referalComments"||element.id=="queryComments"){i=255;
        }if(element.id=="homeAddLine1"||element.id=="homeAddLine2"||element.id=="payrollAddLine1"||element.id=="payrollAddLine2"||element.id=="cprojectAddLine1"||element.id=="cprojectAddLine2"||element.id=="hotelAddLine1"||element.id=="hotelAddLine2"||element.id=="offShoreAddLine1"||element.id=="offShoreAddLine2"||element.id=="otherAddLine1"||element.id=="otherAddLine2"||element.id=="jobCompany"||element.id=="passportIssuedAt"||element.id=="h1Lin"||element.id=="h1Title"||element.id=="gcRef"||element.id=="gcTitle"||element.id=="skillSet"||element.id=="refferedBy"||element.id=="refEmail"){i=50;
        }if(element.id=="homeCity"||element.id=="payrollCity"||element.id=="cprojectCity"||element.id=="hotelCity"||element.id=="offShoreCity"||element.id=="otherCity"){i=25;
        }if(element.id=="homeZip"||element.id=="payrollZip"||element.id=="cprojectZip"||element.id=="hotelZip"||element.id=="offShoreZip"||element.id=="otherZip"){i=15;
        validatenumber(element);
        }
        if(element.id=="pan"){
            i=15;
}
if(element.id=="h1LcaEtaNo"||element.id=="licPolicyNumber"||element.id=="licPolicyComNumber"||element.id=="healthInsPolicyNumber"||element.id=="aliasName"||element.id=="nsrno"){i=15;
        }if(element.id=="passOut"){i=4;
        }
        if(element.id=="laptopType"||element.id=="memory"||element.id=="hardDisk"||element.id=="model"||element.id=="serialNo"||element.id=="passportNo"||element.id=="faxNo"||element.id=="midName"){i=20;
        }if(element.id=="contractOnField"||element.id=="contractPeriod"||element.id=="trainPeriod"||element.id=="licPolicyValues"||element.id=="healthInsCoverage"||element.id=="healthInsNumOfDep"||element.id=="healthInsDedAmt"||element.id=="dentalInsurenceType"||element.id=="dentalInsurenceCoverage"||element.id=="empno"){i=10;
        }if(element.id=="jobAddress"||element.id=="shortTermDisability"||element.id=="longTermDisability"){i=80;
        }if(element.id=="ptUniversity"){i=40;
        }if(element.id=="reason")
        {i=500;
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
/*
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
*/
function checkDates(input)
{     
    //var birthDate = input.value;
    /*
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
    }*/
    
var validformat=/^\d{2}\/\d{2}\/\d{4}$/ //Basic check for format validity
var returnval=false
if (!validformat.test(input.value)) {
alert("Invalid Date Format. Please correct.");
input.value = "";
}
else{ //Detailed check for valid date ranges
var monthfield=input.value.split("/")[0]
var dayfield=input.value.split("/")[1]
var yearfield=input.value.split("/")[2]
var dayobj = new Date(yearfield, monthfield-1, dayfield)
if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield)) {
    alert("Invalid Day, Month, or Year range detected. Please correct .");
    input.value = "";
    
}

else
returnval=true
}
if (returnval==false) input.select()
return returnval
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
            document.consultantsearchForm.location.value=""; 
            document.consultantsearchForm.workAuthorization.value=""; 
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
             return false;
        }


function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
         {
            alert("Please enter numeric value");
            return false;
           } 

         return true;
      }
//For Percentage validation
function validatePercent(Percent) {
  // Percent = document.frmPost.percent.value
  
  if (isNaN(Percent)==true) {
    alert("Enter Numeric values!");
    return false;
  }	
  if ((Percent.indexOf(".") == -1) && (Percent.length >= 3)) {
    alert("Invalid Percentage!");
    return false;
  }
  if ((Percent.indexOf(".")) == 4 || (Percent.indexOf(".")) == 3 || (Percent.indexOf(".")) == 0) {
    alert("Invalid Percentage!");
    return false;
  }
  
  return true;
}	
		
function validatePercent1(frmObj) {

  if (!(validatePercent(frmObj.value))) {
  frmObj.value = "";
  setTimeout(function(){
    frmObj.focus();
  },10);
   return false;
  }
  
}
 
//For on submit validation
function checkCreRegistrationForm()
{
    
   // alert("checkCreRegistrationForm");
 var qualification = document.getElementById("qualification").value;
 var medium = document.getElementById("medium").value;
 var ipeMedium = document.getElementById("ipeMedium").value;
 var degreeMedium = document.getElementById("degreeMedium").value;
 //var pgMedium = document.getElementById("pgMedium").value;

var college = document.getElementById("college").value;
var yearOfPass = document.getElementById("yearOfPass").value;
var percentage = document.getElementById("percentage").value;

var ipeCategory = document.getElementById("ipeCategory").value;
var ipeStream = document.getElementById("ipeStream").value;
var ipeCollege = document.getElementById("ipeCollege").value;
 
var ipeYearOfPass = document.getElementById("ipeYearOfPass").value;
var ipePercentage = document.getElementById("ipePercentage").value;

var degreeQual = document.getElementById("degreeQual").value;
var degreeBranch = document.getElementById("degreeBranch").value;
var degreeCollege = document.getElementById("degreeCollege").value;
var degreeYearOfPass = document.getElementById("degreeYearOfPass").value;
var degreePercentage = document.getElementById("degreePercentage").value;
var firstName = document.getElementById("firstName").value;
var lastName = document.getElementById("lastName").value;







var birthDate = document.getElementById("birthDate").value;
var category = document.getElementById("category").value;
var cellPhoneNo = document.getElementById("cellPhoneNo").value;
var personalEmail = document.getElementById("personalEmail").value;

var skill1 = document.getElementById("skill1").value;

var scale1 = document.getElementById("scale1").value;

var skill2 = document.getElementById("skill2").value;

var scale2 = document.getElementById("scale2").value;
var skill3 = document.getElementById("skill3").value;

var scale3 = document.getElementById("scale3").value;
var skill4 = document.getElementById("skill4").value;

var scale4 = document.getElementById("scale4").value;
var skill5 = document.getElementById("skill5").value;

var scale5 = document.getElementById("scale5").value;
var skill6 = document.getElementById("skill6").value;

var scale6 = document.getElementById("scale6").value;

//if((document.getElementById("company").value != null && document.getElementById("company").value != "") || (document.getElementById("company1").value != null && document.getElementById("company1").value != "") || (document.getElementById("company2").value !=null && document.getElementById("company2").value !=""))
//{
  //  alert("experience");
//alert(document.getElementById("fromDate").value);
var fromDate = document.getElementById("fromDate").value;
//alert("after fromDate1");
var toDate = document.getElementById("toDate").value;
//alert("after toDate");
var fromDate1 = document.getElementById("fromDate1").value;
//alert("after fromDate2");
var toDate1 = document.getElementById("toDate1").value;
//alert("after toDate2");
var fromDate2 = document.getElementById("fromDate2").value;
//alert("after fromDate3");
var toDate2 = document.getElementById("toDate2").value;
//alert("after toDate3");

//}*/
//new start
//alert("after if");
var test = document.getElementsByName("attendingInterviewAt");
                var sizes = test.length;
                var loc = "";
                for (i=0; i < sizes; i++) {
                    if (test[i].checked==true) {
                        // alert(test[i].value + ' you got a value'); 
                        loc = test[i].value;
                        
                      //  campusLocation
                       // recLocation
                      //  jFairLocation
                        if(loc==1){
                           // alert("in 1");
                            document.getElementById("recLocation").value="";
                            document.getElementById("jfairLocation").value="";
                             if(document.getElementById("campusLocation").value == "0"){
                                alert("Please select Location.");
                                return false;
                            }
                        }else if(loc==2){
                            //alert("in 2");
                            document.getElementById("campusLocation").value="0";
                            document.getElementById("jfairLocation").value="";
                            if(document.getElementById("recLocation").value == "" || document.getElementById("recLocation").value == null){
                                alert("Please Enter College name");
                                return false;
                            }
                        }else{
                            //alert("in 3");
                            document.getElementById("campusLocation").value="0";
                            document.getElementById("recLocation").value="";
                             if(document.getElementById("jfairLocation").value == "" || document.getElementById("jfairLocation").value == null){
                                alert("Please Enter Job fair name/location");
                                return false;
                            }
                        }
                        
                    }
                }


//new end



if(category == "-1")
{
    alert("Select Post applied.");
    return false;
}

if(firstName == null || firstName == "")
{
    alert("Enter First Name.");
    return false;
}

if(lastName == null || lastName == "")
{
    alert("Enter Last Name.");
    return false;
}
if(birthDate == null || birthDate == "")
{
    alert("Enter date of birth!");
    return false;
}

if(cellPhoneNo == null || cellPhoneNo == "")
{
    alert("Enter Mobile number!");
    return false;
}

if(personalEmail == null || personalEmail == "")
{
    alert("Enter Personnel Email!");
    return false;
}
if(qualification == "-1")
{
    alert("Fill atleast 3 Level(s) of Academic Qualification Details !");
    return false;
}

if(college == null || college == "")
{
    alert("Enter "+qualification+" College/University .");
    return false;
}
if(medium == "-1")
{
    alert("Enter "+qualification+" Medium ");
    return false;
}
if(yearOfPass == null || yearOfPass == "")
{
    alert("Enter "+qualification+" Year of Passout .");
    return false;
}
if(percentage == null || percentage == "")
{
    alert("Enter "+qualification+" Percentage .");
    return false;
}

if(ipeCategory == "-1")
{
    alert("Fill atleast 3 Level(s) of Academic Qualification Details !");
    return false;
}

if(ipeStream == null || ipeStream == "")
{
    alert("Enter "+ipeCategory+" Stream .");
    return false;
}

if(ipeCollege == null || ipeCollege == "")
{
    alert("Enter "+ipeCategory+" College .");
    return false;
}
if(ipeMedium == "-1")
{
    alert("Enter "+ipeCategory+" Medium .");
    return false;
}
if(ipeYearOfPass == null || ipeYearOfPass == "")
{
    alert("Enter "+ipeCategory+" Year of Pass .");
    return false;
}

if(ipePercentage == null || ipePercentage == "")
{
    alert("Enter "+ipeCategory+" Percentage .");
    return false;
}

if(degreeQual == "-1")
{
    alert("Fill atleast 3 Level(s) of Academic Qualification Details !");
    return false;
}

if(degreeBranch == null || degreeBranch == "")
{
    alert("Enter "+degreeQual+" Branch .");
    return false;
}

if(degreeCollege == null || degreeCollege == "")
{
    alert("Enter "+degreeQual+" College .");
    return false;
}
if(degreeMedium == "-1")
{
    alert("Enter "+degreeQual+" Medium .");
    return false;
}
if(degreeYearOfPass == null || degreeYearOfPass == "")
{
    alert("Enter "+degreeQual+" Year of Pass .");
    return false;
}
if(degreePercentage == null || degreePercentage == "")
{
    alert("Enter "+degreeQual+" Percentage .");
    return false;
}

 //alert(skill1);
 if( scale1 != "" && scale1 != null )
{
    if(skill1 == null || skill1 == ""){
    alert("Enter Skill also for the scale given.");
    document.getElementById("skill1").focus();
    return false;
        }
}

 if( scale2 != "" && scale2 != null )
{
    if(skill2 == null || skill2 == ""){
    alert("Enter Skill also for the scale given.");
    document.getElementById("skill2").focus();
    return false;
        }
}
 if( scale3 != "" && scale3 != null )
{
    if(skill3 == null || skill3 == ""){
    alert("Enter Skill also for the scale given.");
    document.getElementById("skill3").focus();
    return false;
        }
}

 if( scale4 != "" && scale4 != null )
{
    if(skill4 == null || skill4 == ""){
    alert("Enter Skill also for the scale given.");
    document.getElementById("skill4").focus();
    return false;
        }
}

 if( scale5 != "" && scale5 != null )
{
    if(skill5 == null || skill5 == ""){
    alert("Enter Skill also for the scale given.");
    document.getElementById("skill5").focus();
    return false;
        }
}

 if( scale6 != "" && scale6 != null )
{
    if(skill6 == null || skill6 == ""){
    alert("Enter Skill also for the scale given.");
    document.getElementById("skill6").focus();
    return false;
        }
}


if((fromDate != "" || fromDate != null) && (toDate != "" || toDate != null)) {
if( (new Date(fromDate) > new Date(toDate))){
    alert("From date must be less than to date!");
    return false;
}
}
if((fromDate1 != "" || fromDate1 != null) && (toDate1 != "" || toDate1 != null)) {
if( (new Date(fromDate1) > new Date(toDate1))){
    alert("From date must be less than to date!");
    return false;
}
}
if((fromDate2 != "" || fromDate2 != null) && (toDate2 != "" || toDate2 != null)) {
if( (new Date(fromDate2) > new Date(toDate2))){
    alert("From date must be less than to date!");
    return false;
}
}

//resetLocationValue();

    //return true;
}

function resetLocationValue(){
    alert("resetLocationValue");
                var test = document.getElementsByName("attendingInterviewAt");
                var sizes = test.length;
                var loc = "";
                for (i=0; i < sizes; i++) {
                    if (test[i].checked==true) {
                        // alert(test[i].value + ' you got a value'); 
                        loc = test[i].value;
                        
                      //  campusLocation
                       // recLocation
                      //  jFairLocation
                        if(loc==1){
                            document.getElementsByName("recLocation").value="";
                            document.getElementsByName("jFairLocation").value="";
                        }else if(loc==2){
                            document.getElementsByName("campusLocation").value="0";
                            document.getElementsByName("jFairLocation").value="";
                        }else{
                            document.getElementsByName("campusLocation").value="0";
                            document.getElementsByName("recLocation").value="";
                        }
                        
                    }
                }
                return true;
}


            

function checkScale()
{
   var skill1= document.getElementById("skill1").value;     
   var scale1= document.getElementById("scale1").value;   
     if(skill1 == null || scale1 == "" || skill1 == "" || scale1 == null)
     {
        alert("Please fill Skill1 and scale1 First");
        document.getElementById("skill1").focus();
     }
}

var stream = new Array();
function streamAssign(){
    stream = new Array();
//alert("assign");
stream['B.Tech'] = new Array('','CSE','EEE','ECE','IT','MECH','Civil','Others');
stream['B.E'] = new Array('CSE','EEE','ECE','IT','MECH','Civil','Others');
stream['B.Sc'] = new Array('Computers','Electronics','Chemistry','Others');
stream['B.Com'] = new Array('Computers','Commerce','Accountancy');
stream['B.A'] = new Array('');
stream['BBM'] = new Array('Finance','Marketing','HR');
stream['Other'] = new Array('');
stream['MCA'] = new Array('Computers');
stream['MBA'] = new Array('HR','Finance','Marketing','IT','Logistics','Retail','Hospitality and Tourism');
stream['M.Tech'] = new Array('Computer Science','Information Technology','Information Security','Software Engineering','Distributed Computing','Image Processing');
stream['M.Com'] = new Array('');
stream['M.Sc'] = new Array('');
stream['MS'] = new Array('');
stream['-1'] = new Array('');

}

function setStreams(fieldId, newOptions, newValues) {
    var selectedField = document.getElementById(fieldId);
    selectedField.options.length = 0;
    //alert(newOptions.length);
    for (i=0; i<newOptions.length; i++) {
        selectedField.options[selectedField.length] = new Option(newOptions[i], newValues[i]);
    }
}  

function getDegreeStream(form,index) {
  var  cntrySel = document.getElementById('degreeQual');
 // alert(cntrySel.value);
    streamAssign();
    var streamsList = stream[cntrySel.value];
    
   // alert(statesList);
    if(streamsList != null) {
        setStreams('degreeBranch', streamsList, streamsList);
    }
}

function getPgStream(form,index) {
  var  cntrySel = document.getElementById('pgQual');
 // alert(cntrySel.value);
    streamAssign();
    var streamsList = stream[cntrySel.value];
    
   // alert(statesList);
    if(streamsList != null) {
        setStreams('pgStream', streamsList, streamsList);
    }
}

function getStream(form,index) {
  var  cntrySel = document.getElementById('course');
 // alert(cntrySel.value);
    streamAssign();
    var streamsList = stream[cntrySel.value];
    
   // alert(statesList);
    if(streamsList != null) {
        setStreams('creStream', streamsList, streamsList);
    }
}