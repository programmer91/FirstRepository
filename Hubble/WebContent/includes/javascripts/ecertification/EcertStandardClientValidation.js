function chekValuesForCreateExam() {
var topicId = document.getElementById("topicId").value;
var domainId = document.getElementById("domainId").value;
/*var subtopic = document.getElementById("subtopic").value;
var totQuestions = document.getElementById("totQuestions").value;
var totDuration = document.getElementById("totDuration").value;
var totMarks = document.getElementById("totMarks").value;
var minMarks = document.getElementById("minMarks").value;*/
//alert("domainId-->"+domainId);

if(domainId =='')
{
alert("Please select Practice !!");
return false;
}
if(topicId==-1){
alert("Please select Topic !");
return false;
}
//alert("subtopic length-->"+subtopic.length);
/*if(subtopic.length==0)
{
alert("Please enter subtopic");
return false;
}

if(totQuestions.length==0)
{
alert("Please enter total questions");
return false;
}

if(totDuration.length==0)
{
alert("Please enter duration");
return false;
}

if(totMarks.length==0)
{
alert("Please enter total marks");
return false;
}
if(minMarks.length==0)
{
alert("Please enter minimum marks");
return false;
}*/

}


 function isNumberKey(evt) {
 var totMarks=document.getElementById("totMarks").value;
var minMarks=document.getElementById("minMarks").value;
 //alert("maxmarks"+totMarks);
 //alert("min marks"+minMarks);
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode!=46 && charCode > 31 && (charCode < 48 || charCode > 57))
{
      alert("Please enter numeric value");
      return false;
}

    else
{
    return true;
}
};

/* new function for create exam validations */

function fieldLenghtValidator(element){
  //var totMarks=document.getElementById("totMarks").value;
//var minMarks=document.getElementById("minMarks").value;

    var i = 0;
    if (element.value != null && (element.value != "")) {
        //alert("In if standard client validation");
        if(element.id == 'subtopic') i = 30;
       // else if(element.id == 'totQuestions' || element.id == 'totDuration' || element.id == 'totMarks' || element.id == 'minMarks') i = 3;
        else if(element.id == 'question') i = 1000;
        else if(element.id == 'option1' || element.id == 'option2' || element.id == 'option3' || element.id == 'option4') i = 500;
        else if(element.id == 'description') i = 1000;
      
        if(element.value.replace(/^\s+|\s+$/g,"").length>i){
            //subStringValue(i,element,"The "+element.id+" must be less than "+i+" characters");
            str = new String(element.value);
            element.value=str.substring(0,i);
            
            alert("The "+element.id+" must be less than "+i+" characters");
            element.focus();
            return false;
        }
       
       
      }



};
//function to escape html tags 
function escapeHTML(escapeHTML_str) {
escapeHTML_str1 =escapeHTML_str.value;
escapeHTML_id =escapeHTML_str.id;
//alert("value-->"+escapeHTML_str1);
//alert("id-->"+escapeHTML_id);
//escapeHTML_str1=escapeHTML_str1.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/\"/g, '&quot;').replace(/\'/g, '&#39;'); 
//alert("value after-->"+escapeHTML_str1);
escapeHTML_str1=escapeHTML_str1.replace(/</g, '&lt;').replace(/>/g, '&gt;'); 
if(escapeHTML_id == 'question')
{
document.getElementById("question").value=escapeHTML_str1;
}
if(escapeHTML_id == 'option1')
{
document.getElementById("option1").value=escapeHTML_str1;
}
if(escapeHTML_id == 'option2')
{
document.getElementById("option2").value=escapeHTML_str1;
}
if(escapeHTML_id == 'option3')
{
document.getElementById("option3").value=escapeHTML_str1;
}
if(escapeHTML_id == 'option4')
{
document.getElementById("option4").value=escapeHTML_str1;
}
if(escapeHTML_id == 'description')
{
document.getElementById("description").value=escapeHTML_str1;
}
//return escapeHTML_str;
};

function allowAnswer(evt)
{
var answer=document.getElementById("answer").value;
// alert("vlaue-->"+answer);
   if(answer==1 || answer==2 || answer==3 || answer==4)
   {
   return true;
   }
    else
    {
    document.getElementById("answer").value="";
    alert("Answer should be 1 to 4 numerics only");
    return false;
    }
};

/*
 function isNumberKeyAndMaxMarksValidation(evt) {
 var totMarks=document.getElementById("totMarks").value;
var minMarks=document.getElementById("minMarks").value;
 //alert("maxmarks"+totMarks);
 //alert("min marks"+minMarks);
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode!=46 && charCode > 31 && (charCode < 48 || charCode > 57))
{
      alert("Please enter numeric value");
      return false;
}
 else if(minMarks > totMarks)
{

alert("Minimum marks should be less than max marks");
document.getElementById("minMarks").value="";
return false;
}
    else
{
    return true;
}
};*/


  function autoresize(textarea) {
        textarea.style.height = '49px';
        textarea.style.height = textarea.scrollHeight + 12 + 'px';
    }

function checkMandatoryFields() {
 var text = document.getElementById("question").value;
       
text = text.replace(/\r?\n/g, '<br/>');

document.getElementById("question").value = text;

        var text = document.getElementById("description").value;
        
text = text.replace(/\r?\n/g, '<br/>');

document.getElementById("description").value = text;

var tempQuestion = document.getElementById("question").value;
var tempOption1 = document.getElementById("option1").value;
var tempOption2 = document.getElementById("option2").value;
var tempOption3 = document.getElementById("option3").value;
var tempOption4 = document.getElementById("option4").value;
var tempAnswer = document.getElementById("answer").value;
var subtopic = document.getElementById("subTopicId").value;

if(subtopic == null || subtopic == "") {
alert("Select Subtopic!");
return false;
}

if(tempQuestion == null || tempQuestion == "") {
alert("Enter Question!");
return false;
}

if(tempOption1 == null || tempOption1 == "") {
alert("Enter Option1!");
return false;
}
if(tempOption2 == null || tempOption2 == "") {
alert("Enter Option2!");
return false;
}
if(tempOption3 == null || tempOption3 == "") {
alert("Enter Option3!");
return false;
}
if(tempOption4 == null || tempOption4 == "") {
alert("Enter Opti0n4!");
return false;
}
if(tempAnswer == null || tempAnswer == "") {
alert("Enter Answer!");
return false;
}
return true;
}

function validateExamKeyInputs() {

var domainId1 = document.getElementById("domainId").value;
var topicId1 = document.getElementById("topicId").value;
var totDuration1 = document.getElementById("totDuration").value;
var minMarks1 = document.getElementById("minMarks").value;
var totalQues1 = document.getElementById("totalQues").value;
var noOfKeys1 = document.getElementById("noOfKeys").value;
var availableQues1 = document.getElementById("availableQues").value;

if(domainId1 == null || domainId1 == "") {
alert("Select Practice!");
return false;
}

if(topicId1 == "-1") {
alert("Select Topic!");
return false;
}
if(totDuration1 == null || totDuration1 == "") {
alert("Enter Duration!");
return false;
}
if(minMarks1 == null || minMarks1 == "") {
alert("Enter Minimum Marks!");
return false;
}
if(totalQues1 == null || totalQues1 == "") {
alert("Enter No. Of Questions!");
return false;
}
if(noOfKeys1 == null || noOfKeys1 == "") {
alert("Enter No. Of Keys!");
return false;
}
if(parseInt(availableQues1) < parseInt(totalQues1)) {
alert("No. of questions must be less than AvailableQuestions!");
return false;
}
if(parseInt(totalQues1) < parseInt(minMarks1)) {
alert("Minimum marks must be less than No. of Questions!");
return false;
}
return true;

}

function isNumberKeyExam(evt) {
 
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode!=46 && charCode > 31 && (charCode < 48 || charCode > 57))
    {
      alert("Please enter numeric value");
      return false;
    }else {
        return true;
        }
    }
    
    //client validation for Add practice screeen

   function practiceClientValidation()
    {
    var practicename=document.getElementById('domainName').value;
    var topicname =document.getElementById('topicName').value;
    
   
     if(practicename.length >30 )
        {
            str = new String(document.addPracticeForm.domainName.value);
            document.addPracticeForm.domainName.value=str.substring(0,30);
             alert("The PracticeName should be less than 30");
            document.addPracticeForm.custName.focus();
            
           
            //document.getElementById('domainName').value="";
            return false;
        }
     else if(topicname.length >100 )
        {
            str = new String(document.addPracticeForm.topicName.value);
            document.addPracticeForm.topicName.value=str.substring(0,100);
           alert("TopicName should be less than 100");
            document.addPracticeForm.topicName.focus();
            
            
            //document.getElementById('topicName').value="";
            return false;
        }
        
    }
    
    function practiceClientValidation1()
    {
    var practicename=document.getElementById('domainName').value;
    var topicname =document.getElementById('topicName').value;
    
    if(practicename==null || practicename=="" )
    {
      alert("Please Enter practice name");  
     return false;
    }
     if(topicname ==null || topicname=="")
        {
            alert("Please Enter topic name");
            return false;
        }
  
       
    }
