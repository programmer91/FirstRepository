var url= null;

/*Don't Alter these Methods*/
function newXMLHttpRequest() {
    var xmlreq = false;
    if(window.XMLHttpRequest) {
        xmlreq = new XMLHttpRequest();
    } else if(window.ActiveXObject) {
        try {
            xmlreq = new ActiveXObject("MSxm12.XMLHTTP");
        } catch(e1) {
            try {
                xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
            } catch(e2) {
                xmlreq = false;
            }
        }
    }
    return xmlreq;
}

function readyStateHandler(req,responseXmlHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                responseXmlHandler(req.responseXML);
            } else {
                alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }
    }
}


function readyStateHandlerText(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadingMessage")).style.display = "none";
                responseTextHandler(req.responseText);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
            (document.getElementById("loadingMessage")).style.display = "block";
        }
    }
}

/*Methods for getting Topics by Domain*/

function getTopicData() {
    
    var domainId = document.getElementById("domainId").value;

    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler99(req, populateTopics);
    var url = CONTENXT_PATH+"/getDomainTopics.action?domainId="+domainId;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populateTopics(resXML) {    
    //alert("resXML---->"+resXML.toString());
    var topicId = document.getElementById("topicId");
    var topicsMain = resXML.getElementsByTagName("TOPICS")[0];
    var topicSub = topicsMain.getElementsByTagName("TOPIC");
    topicId.innerHTML=" ";
    
    for(var i=0;i<topicSub.length;i++) {
        var topicName = topicSub[i];
        
        var name = topicName.firstChild.nodeValue;
        var id = topicName.getAttribute("TOPICID");
    
        var opt = document.createElement("option");
        //if(i==0){
          //  opt.setAttribute("value","All");
       // }else{
            opt.setAttribute("value",id);
       // }
        opt.appendChild(document.createTextNode(name));
        topicId.appendChild(opt);
    }
}
/* For getting topics in generate Report keys*/
function getTopicDataReport() {
    
    var domainId = document.getElementById("domainIdReport").value;

    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateTopicsReport);
    var url = CONTENXT_PATH+"/getDomainTopics.action?domainId="+domainId;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populateTopicsReport(resXML) {    
    //alert("resXML---->"+resXML.toString());
    var topicId = document.getElementById("topicIdReport");
    var topicsMain = resXML.getElementsByTagName("TOPICS")[0];
    var topicSub = topicsMain.getElementsByTagName("TOPIC");
    topicId.innerHTML=" ";
    
    for(var i=0;i<topicSub.length;i++) {
        var topicName = topicSub[i];
        
        var name = topicName.firstChild.nodeValue;
        var id = topicName.getAttribute("TOPICID");
    
        var opt = document.createElement("option");
        //if(i==0){
          //  opt.setAttribute("value","All");
       // }else{
            opt.setAttribute("value",id);
       // }
        opt.appendChild(document.createTextNode(name));
        topicId.appendChild(opt);
    }
}

function chekValues() {
var topicId = document.getElementById("topicId").value;
if(topicId==-1){
alert("Please select Topic !");
return false;
}
else {
return true;
}
}
//instructionDiv
//empSearchTab
/** To get exam total time,Total questions **/

 function checkExamKey() {
var vkey = prompt("Please enter Exam Key","");
if(vkey!=null && vkey !="") {
 var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, validateTopicId);
    var url = CONTENXT_PATH+"/validateExam.action?examValidationKey="+vkey;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
}
 function validateTopicId(resText)  {

if(resText != "Invalid") {
var topicId1 = document.getElementById("topicId").value;
var str = resText.split("|");
if(topicId1 == str[1]) {

var minMarks = str[2];
var duration = str[3];
var noOfQues = str[4];

getInstructions(str[0],minMarks,duration,noOfQues);
}else {
alert("Exam key is not matched with Topic!");
}
}else {
alert("Please enter valid exam key!");
}
}           
            
  function getInstructions(valExamKey,minMarks,duration,noOfQues){
        document.getElementById("examValidationKey").value = valExamKey;
        
        var domainId1 = document.getElementById("domainId").value;
        var topicId1 = document.getElementById("topicId").value;
        
        // alert(topicId1);
        (document.getElementById("empSearchTab")).style.display = "none";
        (document.getElementById("instructionDiv")).style.display = "block";

        var domainId = document.getElementById("domainId");
        var domainName1 = domainId.options[domainId.selectedIndex].text;

        var topicId = document.getElementById("topicId");
        var topicName1 = topicId.options[topicId.selectedIndex].text;


        /** Ids **/
        document.getElementById("insdomainId").value = domainId.value; 
        document.getElementById("insTopicId").value = topicId.value;

        /* name */
        document.getElementById('insdomainName').innerHTML = domainName1;
        document.getElementById('insTopicName').innerHTML = topicName1;

        document.getElementById('domainName').value=domainName1;
        document.getElementById('topicName').value=topicName1;
        
        /**  total question and duration */

            document.getElementById('totalQuestions').innerHTML=noOfQues;
            document.getElementById('duration').innerHTML=duration;

            document.getElementById('totalQuest').value=noOfQues;
            document.getElementById('durationTime').value=duration;

       // getTime(topicId1);
        } 

function readyStateHandlerText(req,responseTextHandler){
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {               
                responseTextHandler(req.responseText);
            } else {
                alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }
    }
}

function getTime(topicId1){

    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, populateTime);
    var url = CONTENXT_PATH+"/getExamTimeAndCount.action?topicId="+topicId1;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateTime(resTEXT) { 
   // var strArray = resTEXT.split("|");
     document.getElementById('totalQuestions').innerHTML = 5;
     //document.getElementById('duration').innerHTML = strArray[1];
     document.getElementById('duration').innerHTML = resTEXT;
   
     document.getElementById('totalQuest').value=5;
     document.getElementById('durationTime').value=resTEXT;
}


/***
*
*
*
***/

        //function getQuestion(questionNumber,selectedAns,navigation,onClickStatus,remainingQuestions,subtopicId){
         function getQuestion(questionNumber,selectedAns,navigation,onClickStatus,remainingQuestions,subtopicId,specficQuestion){
             // alert("QuestionId-->"+questionNumber+"-----------------selectedAns--------------->"+selectedAns);
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandler(req, populateQuestions);
       // url = CONTENXT_PATH+"/getQuestion.action?questionNo="+questionNumber+"&selectedAns="+selectedAns+"&navigation="+navigation+"&onClickStatus="+onClickStatus+"&remainingQuestions="+remainingQuestions+"&random="+Math.random()+"&subTopicId="+subtopicId;
url = CONTENXT_PATH+"/getQuestion.action?questionNo="+questionNumber+"&selectedAns="+selectedAns+"&navigation="+navigation+"&onClickStatus="+onClickStatus+"&remainingQuestions="+remainingQuestions+"&random="+Math.random()+"&subTopicId="+subtopicId+"&specficQuestionNo="+specficQuestion;
          //alert("url----->"+url);

        req.open("GET",url,"true");    
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);

                }



function populateQuestions(resXML) { 
    
     var questDetails = resXML.getElementsByTagName("QUESTIONDETAILS")[0];
     
        /** Xml status  **/
            var questionStatusObj = questDetails.getElementsByTagName("QUESTIONSTATUS");
            var questionStatusId = questionStatusObj[0];
            var qStatus  = questionStatusId.firstChild.nodeValue;  
       // alert("qStatus---->"+qStatus);

        if(qStatus == "true"){
     
        /*For Getting Question Id */
          var questionIdObj = questDetails.getElementsByTagName("QUESTIONID");
        var questionId = questionIdObj[0];
        var id  = questionId.firstChild.nodeValue;
        document.getElementById("questionId").value=id;
        
        /*For Getting Question */
        var questionNameObj = questDetails.getElementsByTagName("QUESTIONNAME");
        var questionName = questionNameObj[0];
        var question  = questionName.firstChild.nodeValue; 

        document.getElementById("qname").innerHTML = question;
        
        /*For Getting Option One */

        var option1TagObj = questDetails.getElementsByTagName("OPTION1");
        var option1Tag = option1TagObj[0];
        var option1  = option1Tag.firstChild.nodeValue; 
        document.getElementById("opt1").innerHTML = option1;
        
        /*For Getting Option Two*/

        var option2TagObj = questDetails.getElementsByTagName("OPTION2");
        var option2Tag = option2TagObj[0];
        var option2  = option2Tag.firstChild.nodeValue;
        document.getElementById("opt2").innerHTML = option2;

        /* For Getting Option three */

        var option3TagObj = questDetails.getElementsByTagName("OPTION3");
        var option3Tag = option3TagObj[0];
        var option3  = option3Tag.firstChild.nodeValue;
        document.getElementById("opt3").innerHTML = option3;
        
        /* For Getting Option four */

        var option4TagObj = questDetails.getElementsByTagName("OPTION4");
        var option4Tag = option4TagObj[0];
        var option4  = option4Tag.firstChild.nodeValue;
        document.getElementById("opt4").innerHTML = option4;
        
        /* For Getting Question Serial Number */

        var MapQIDObj = questDetails.getElementsByTagName("MAPQUESTIONID");
        var MqTag = MapQIDObj[0];
        var mapQId  = MqTag.firstChild.nodeValue;
        document.getElementById("ques").innerHTML = mapQId;
        document.getElementById("questionId").value = mapQId;
        
      //-----------------------------------------
      var SubtopicIDObj = questDetails.getElementsByTagName("SUBTOPICID");
        var subtopicTag = SubtopicIDObj[0];
        var subtopicId1  = subtopicTag.firstChild.nodeValue;
        //document.getElementById("ques").innerHTML = mapQId;
        document.getElementById("subtopicId").value = subtopicId1;



      //---------------------------------------




        /*For Getting Answer */

        var empAnsTagObj = questDetails.getElementsByTagName("EMPANSWER");
        var empAnsTag = empAnsTagObj[0];
        var empAns  = empAnsTag.firstChild.nodeValue;
        empAns=empAns-1;

        /* Remaining Questions Count */
        var remainigQuesTagObj = questDetails.getElementsByTagName("REMAININGQUESTIONS");
        var remainigQuesTag = remainigQuesTagObj[0];
        var remainigQues  = remainigQuesTag.firstChild.nodeValue;
        document.getElementById("remainingQuestions").innerHTML = remainigQues;
        document.getElementById("hideremainingQuestions").value = remainigQues;
        

        var nextObj = document.getElementById("next");
        if(document.getElementById("totalQuest").value == mapQId){
        nextObj.style.display = 'none';
        }else {
        nextObj.style.display = '';
        }

        var previousObj = document.getElementById("previous");
        if(mapQId == 1){
        previousObj.style.display = 'none';
        }else {
        previousObj.style.display = '';
        }
          var radiooptions = document.getElementsByName('option');
           
        for (var i = 0, length = radiooptions.length; i < length; i++){
             radiooptions[i].checked = false;
        }
        if(empAns >= 0){
        radiooptions[empAns].checked = true;
        }
     }else{
        /* Remaining Questions Count */
        var remainigQuesTagObj = questDetails.getElementsByTagName("REMAININGQUESTIONS");
        var remainigQuesTag = remainigQuesTagObj[0];
        var remainigQues  = remainigQuesTag.firstChild.nodeValue;
        document.getElementById("remainingQuestions").innerHTML = remainigQues;
        document.getElementById("hideremainingQuestions").value = remainigQues;

        document.forms["ecertificationForm"].submit();
     }
   //   alert("URL1111111--->"+url);  
                
}


/**
 * To Delete cookies in IE7 and IE8 for session maintainence 
 *
 **/



/*
for Subtopic

*/



/*
*Method for getting count of available questions in questios table for Re adding
*Date : 07/29/2013
*/
function reAddQuestions(subtopicid,totalques) {
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, compareQuestions);
    var url = CONTENXT_PATH+"/getCurrentQuestionsCount.action?subTopicId="+subtopicid+"&totalQuestions="+totalques;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
        }

function compareQuestions(resText) {

if(resText != "Equal") {
var str = resText.split("|");
var r=confirm("You have to add "+str[1]+" Questions!Do you want to add now ?");
if (r==true)
  {
  document.location="/Hubble/ecertification/reAddQuestions.action?subTopicId="+str[0]+"&totQuestions="+str[1];
  }


 
}else if(resText == "Equal") {
alert("Available questions are equal to total questions!");
}else {
alert("Error occured");
}

}



/*
*Method to get Available Questions based on selection of Topic
*Date : 08/05/2013
*/

function getAvailableQuestions() {
var topicId = document.getElementById("topicId").value;

 var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, populateAvailableQuestions);
    var url = CONTENXT_PATH+"/getAvailableQuestions.action?topicId="+topicId;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function populateAvailableQuestions(resText) {
if(resText != "None") {
document.getElementById("availableQues").value = resText;
document.getElementById("availQuest").innerHTML = "(Available Questions:"+resText+" )";

}else {
document.getElementById("availableQues").value = parseInt(0);
document.getElementById("availQuest").innerHTML = "(Available Questions: 0 )";
}
}



/*
*Method for getting Employee Names List
*Date : 08/05/2013
*
*/

var isIE;

function fillEmployee() {
var test='';
   
    var test=document.getElementById("empName").value;  
        
    if (test == "") {
    
        clearTable();
        hideScrollBar();
        
        var validationMessage=document.getElementById("empValidationMessage");
        validationMessage.innerHTML = "";
        document.frmExamResultSearch.loginId.value="";
        
    } else {
        if (test.length >2) {
            var url = CONTENXT_PATH+"/getEmployeeDetails.action?customerName="+ escape(test);         
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {                        
                        parseEmpMessages(req.responseXML);                        
                    } else if (req.status == 204){
                        clearTable();
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }
}




//--------
function parseEmpMessages(responseXML) {
    clearTable();
    var employees = responseXML.getElementsByTagName("EMPLOYEES")[0];
    if (employees.childNodes.length > 0) {        
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");        
    } else {
        clearTable();
    }
    if(employees.childNodes.length<10) {
        
        autorow.style.overflowY = "hidden";        
    }
    else {    
        
        autorow.style.overflowY = "scroll";
    }
    
    var employee = employees.childNodes[0];
    var chk=employee.getElementsByTagName("VALID")[0];
    if(chk.childNodes[0].nodeValue =="true") {
        
        var validationMessage;
      
        validationMessage=document.getElementById("empValidationMessage");
        isPriEmpExist = true;
        
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < employees.childNodes.length; loop++) {
            
            var employee = employees.childNodes[loop];
            var customerName = employee.getElementsByTagName("NAME")[0];
            var empid = employee.getElementsByTagName("EMPID")[0];
            appendEmployee(empid.childNodes[0].nodeValue,customerName.childNodes[0].nodeValue);
        }
        
        var position;
        
        position = findPosition(document.getElementById("empName"));
        
        
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
    var validationMessage = '';
     
     isPriEmpExist = false;
     validationMessage=document.getElementById("empValidationMessage");
   
    
        validationMessage.innerHTML = " Name  is InValid ";

        
           document.getElementById("empName").value = "";
           document.getElementById("loginId").value = "";
            
       
       
    }
}


//new
function appendEmployee(empId,empName) {
    var row;
    var nameCell;
    if (!isIE) {
        row = completeTable.insertRow(completeTable.rows.length);
        nameCell = row.insertCell(0);
    } else {
        row = document.createElement("tr");
        nameCell = document.createElement("td");
        row.appendChild(nameCell);
        completeTable.appendChild(row);
    }
    row.className = "popupRow";
    nameCell.setAttribute("bgcolor", "#3E93D4");
    var linkElement = document.createElement("a");
    linkElement.className = "popupItem";
    
    linkElement.setAttribute("href", "javascript:set_emp('"+empName +"','"+ empId +"')");
    
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}


//new
function set_emp(eName,eID){
    clearTable();
  // alert(eID);
 document.getElementById("empName").value = eName;
document.getElementById("loginId1").value = eID;
}




function clearTable() {
    if (completeTable) {
        completeTable.setAttribute("bordercolor", "white");
        completeTable.setAttribute("border", "0");
        completeTable.style.visible = false;
        for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
}



function findPosition( oElement ) {
    if( typeof( oElement.offsetParent ) != undefined ) {
        for( var posX = 0, posY = 0; oElement; oElement = oElement.offsetParent ) {
            posX += oElement.offsetLeft;
            posY += oElement.offsetTop;
        }
        return posX+","+posY;
    } else {
        return oElement.x+","+oElement.y;
    }
}
function hideScrollBar() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
}
/*   For getting the report in Excel format */
function getKeysReport()
{
        
var practice = document.getElementById("domainIdReport").value;
var topic = document.getElementById("topicIdReport").value;
var createdDate = document.getElementById("createdDate").value;
//alert(practice);

//alert(topic);
if( practice == "-1"){
 alert("Please select Practice Name!");
return false;
}
else if(topic == "-1" )
{
    alert("Please Select topic Name!");
    return false;
}
else if(createdDate == "" || createdDate == null){
    alert("createdDate should not be empty");
    return false;
}


else
{
 window.location="../ecertification/generateExcelReportForKeys.action?practiceId="+practice+"&topicId="+topic+"&createdDate="+createdDate;;
 return true;
}

}
 function getExamReport(){
    var startDate = document.getElementById('startDate').value;
    var endDate = document.getElementById('endDate').value;
    var empType=document.getElementById('empType').value;
    var empName=document.getElementById('empName').value;
    var consultantId=document.getElementById('conId1').value;
    var loginId=document.getElementById('loginId1').value;
  var domainId=document.getElementById('domainId').value;
    var topicId=document.getElementById('topicId').value;
    
    if(startDate.length == 0 && startDate.length == 0)
    {
        alert("please enter start and end dates");
        return false;
    }
    else{
        
        window.location = "generateExamReport.action?startDate="+startDate+"&endDate="+endDate+"&empType="+empType+"&empName="+empName+"&consultantId="+consultantId+"&loginId="+loginId +"&domainId="+domainId+"&topicId="+topicId;
    }
    
}



function readyStateHandler99(req,responseXmlHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                responseXmlHandler(req.responseXML);
            } else {
                alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }
    }
}

function getDomainByExamType(){
    var et = document.getElementById("empType").value;
                  
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler99(req, populatePractices);
    var url = CONTENXT_PATH+"/getDomainByExamType.action?pgNo="+et;
   // alert(url)
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
            }
            
function populatePractices(resXML) {    
    
  
   var topicId = document.getElementById("domainId");
    var topicsMain = resXML.getElementsByTagName("TOPICS");
    var topicSub = topicsMain.getElementsByTagName("TOPIC");
    topicId.innerHTML=" ";
    
    for(var i=0;i<topicSub.length;i++) {
        var topicName = topicSub[i];
        
        var name = topicName.firstChild.nodeValue;
        var id = topicName.getAttribute("TOPICID");
    
        var opt = document.createElement("option");
        //if(i==0){
          //  opt.setAttribute("value","All");
       // }else{
            opt.setAttribute("value",id);
       // }
        opt.appendChild(document.createTextNode(name));
        topicId.appendChild(opt);
    }
}



function ajaxQuestionsFileUpload()
{
// var domainId = document.getElementById("domainIdReport").value;
 var topicId = document.getElementById("topicId").value;
 var e = document.getElementById("subTopicIdUpload");
var subTopicId = e.options[e.selectedIndex].value;
  // var subTopicId = document.getElementById("subTopicId").value;
  // alert(subTopicId)
    var attachmentFileName = document.getElementById('file').value;  
   // alert(attachmentFileName)
       if(subTopicId.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please Select subTopic.</font>";
       return false;
   }
   if(attachmentFileName.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please upload File.</font>";
   }
   else {  
   $.ajaxFileUpload({        
        url:'questionsFileUpload.action?subTopicId='+subTopicId+"&topicId="+topicId,      
        secureuri:false,//false
        fileElementId:'file',//id  <input type="file" id="file" name="file" />
        dataType: 'json',// json
        success: function(data,status){            
            var displaymessage = "<font color=red>Please try again later</font>";    
           // alert(data);
            if(data.indexOf("uploaded")>0){   
            //if(data=="uploaded"){               
                displaymessage = "<font color=green>Uploaded Successfully.......</font>";
            } 
             if(data.indexOf("Error")>0){ 
            //if(data=="Error"){               
                displaymessage = "<font color=red>Internal Error!, Please try again later.</font>"
            } //if(data=="Exceded"){               
            if(data.indexOf("Empty")>0){ 
                displaymessage = "<font color=red>Please upload the excelsheet where no column parellel to the headerfields are empty.</font>"
            }  
            if(data.indexOf("InvalidFormat")>0){ 
            //if(data=="InvalidFormat"){               
                displaymessage = "<font color=red>Please upload excelsheet with specified header fields.</font>"
            }                    
            document.getElementById('resultMessage').innerHTML = displaymessage;        
        },
        error: function(e){                     
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";       
        }
    }); 
    
    
   }
}
