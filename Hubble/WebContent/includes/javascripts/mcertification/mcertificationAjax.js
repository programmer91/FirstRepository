/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
          
function addEvent(obj, evType, fn){ 
	if (obj.addEventListener){ 
   		obj.addEventListener(evType, fn, false); 
   		return true; 
 	} else if (obj.attachEvent){ 
   		var r = obj.attachEvent("on"+evType, fn); 
   		return r; 
 	} else { 
   		return false; 
 	} 
}
 
addEvent(window, 'load', initCheckboxes);
 
function initCheckboxes() {
	addEvent(document.getElementById('text'), 'click', setCheckboxes);
}
 
function setCheckboxes() {
           var cb = document.creSearchForm.check_List;
	for (var i = 0; i < cb.length; i++) {
		cb[i].checked = document.getElementById('text').checked;
	}
}

function setUnCheckboxes() {
           var cb = document.creSearchForm.check_List;
	for (var i = 0; i < cb.length; i++) {
		cb[i].checked = false;
	}
}

 function readyStateHandler44(req,responseTextHandler) {
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

function getMcertRecords(){
            
//var tempCreConsultantName = document.getElementById('creConsultantName').value;

//alert("hii");
var tempStartDate = document.getElementById('startDate').value;
var tempToDate = document.getElementById('toDate').value;
var mcertConsultantStatus = document.getElementById('mcertConsultantStatus').value;

 if(tempStartDate!="" && tempToDate!=""){

    var sd = new Date(tempStartDate);
       var td = new Date(tempToDate);
    if(sd>td){
         alert("Start date must be less then To date");
         return false;
     }
 } 
  

       var req = newXMLHttpRequest();
        //req.onreadystatechange = readyStateHandlerText(req,populateRecords); 
        req.onreadystatechange = readyStateHandler44(req,populateMcertRecords); 
        //var url = CONTENXT_PATH+"/getCreRecordsList.action?creConsultantName="+tempCreConsultantName+"&creConsultantId="+tempCreConsultantId+"&creConsultantId1="+tempCreConsultantId1+"&creStartDate="+tempStartDate +"&creToDate="+tempToDate+"&creConsultantStatus="+tempStatus+"&category="+tempCategory+"&level="+tempLevel+"&interviewAt="+tempInvAt;
        var url = CONTENXT_PATH+"/getMcertRecordsList.action?mcertStartDate="+tempStartDate +"&mcertToDate="+tempToDate+"&mcertConsultantStatus="+mcertConsultantStatus;
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
}

function populateMcertRecords(totalData){
    //alert(totalData)
    var str = totalData;
    var n=0;
    var n1 =0;
    var n2 =0;
  setUnCheckboxes();
  document.getElementById('text').checked=false;
  
  var status=document.getElementById('mcertConsultantStatus').value;
  
   if(status=="Registered" || status=="Rejected" || status=="PASS" || status=="FAIL")
           {
               document.getElementById('activeButton').style.display = 'block'; 
               document.getElementById('examDetails').style.display = 'block';
               
                
           }
           
           else
               {
                   document.getElementById('activeButton').style.display = 'none'; 
                   document.getElementById('examDetails').style.display = 'none';
               }
               
          
   if(str.indexOf("#^$")>=1){
      
        if(status!="Rejected") {
        document.getElementById('rejectButton').style.display = 'block';
       }else {
           document.getElementById('rejectButton').style.display = 'none';
       }
       
       document.getElementById('creConsultantList').style.display = 'block'; 
        
       
      
          for(var r=1;r<=30;r++){
                document.getElementById('gridRow_'+r).style.display = 'block'; 
          }
        var caps = new Array();
       caps = str.split("#^$");
   //-----------------------------------------------------------------  populating records  */
        var ids = new Array();
         var consultantLoginId = new Array();
        var consultantNames = new Array();
        var consultantEmail = new Array();
         var consultantStatus=new Array();
        ids = caps[0].split("!");
        consultantNames = caps[1].split("!");
        
        consultantEmail = caps[2].split("!");
        consultantLoginId = caps[3].split("!");
        consultantStatus=caps[4].split("!") ;
        var n1=0;
    for(var j=0; j<ids.length;j++) {
        n1 = parseInt(j)+1;
       // alert(n1);
        document.getElementById('text'+n1).value= ids[j];
        document.getElementById('texti'+n1).value= consultantLoginId[j];
        document.getElementById('texta'+n1).value= consultantNames[j];
        document.getElementById('textc'+n1).value= consultantEmail[j];
          document.getElementById('texts'+n1).value= consultantStatus[j];
        
    }


/*==================================
 * Empty Remaining Fields
 *==================================
 */
    var count=n1;
   // alert(count);
    var s = 0;
    var res=0;
    for(k=0;k<=30-n1;k++){
        s= parseInt(count)+parseInt(k);
      //  alert(s)
        if(document.getElementById('text'+s)!=null){
            document.getElementById('text'+s).value="";
           
            document.getElementById('gridRow_'+s).style.display = 'none'; 
        }else{
            break;
        }
        if(document.getElementById('texti'+s)!=null){
            document.getElementById('texti'+s).value="";
        }else{
            break;
        } 


        if(document.getElementById('texta'+s)!=null){
            document.getElementById('texta'+s).value="";
        }else{
            break;
        } 
         if(document.getElementById('textc'+s)!=null){
            document.getElementById('textc'+s).value="";
        }else{
            break;
        } 
        if(document.getElementById('texts'+s)!=null){
            document.getElementById('texts'+s).value="";
        }
        
    } 
    
    
    }else{
      
    
      document.getElementById('creConsultantList').style.display = 'none'; 
      document.getElementById('activeButton').style.display = 'none';
      document.getElementById('examDetails').style.display = 'none';
      document.getElementById('rejectButton').style.display = 'none';
      alert("No records to display");
    }
     
}



//unselect list start
function unselectList() {
 var selObj = document.getElementById('subtopics');
        
        for (var i=0; i<selObj.options.length; i++) {
        if (selObj.options[i].selected) {
          
          selObj.options[i].selected = false;
          
        }
        }
}

//unselect list end
//---for activating records

function getMcertData(chk, status){
var resultString = "";

    for(i=0;i<30;i++){

    var res = document.creSearchForm.check_List[i].checked;

    if(res == true){

     var k = i + 1;
     var resultString1 = document.getElementById('text'+k).value;

     resultString = resultString.concat(resultString1,'!');
    }
    }
var selectedArray = new Array();
/*
        var selObj = document.getElementById('subtopics');
        var count = 0;
        
        for (var i=0; i<selObj.options.length; i++) {
        if (selObj.options[i].selected) {
          selectedArray[count] = selObj.options[i].value;
          count++;
        }
        }*/

          // alert("select box count---->"+count+"------------------->"+selectedArray+"----"+resultString.indexOf("!"));

    if( resultString.indexOf("!") >= 1 ){  
      /*
        if(count > 0 ){
            getCreDetails(resultString, status,selectedArray);
            
        }else if((count<= 0) && (status == "Active")){
           alert("Please select Subtopics!"); 
        }else if(status == "Rejected") {
            getCreDetails(resultString, status,selectedArray);
        }*/
    
            getMcertDetails(resultString, status);
        
    }else{
        alert("Please select atleast one Consultant!");
    }
} 


function getMcertDetails(vals, status){  

      var input_box = confirm("Please Confirm to "+status+" selected canditates!"); 
     if(input_box==true) {
         doMcertStatusUpdate(vals, status);      
     }
     else{
       alert("Status updation canceled!");
     }
}


function doMcertStatusUpdate(consultantId, status){
 //  alert("selectedArray--"+selectedArray);
 var req = newXMLHttpRequest();
       // req.onreadystatechange = readyStateHandlerText(req,getUpdateResult); 
        req.onreadystatechange = readyStateHandler44(req,getMcertUpdateResult); 
        var url = CONTENXT_PATH+"/mcertRecordStatusUpdate.action?mcertConsultantId="+consultantId+"&mcertConsultantStatus="+status;
    //var url = CONTENXT_PATH+"/putConsultantIdsList.action?creConsultantId="+consultantId;
 
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null); 
}

function getMcertUpdateResult(result) {
    alert("Rejected Consultant Ids Are "+result);
    setUnCheckboxes();
    getMcertRecords();
    unselectList();
}

function insertMcertExamDetails() {
    
    var selectedArray = new Array();
    var ExamNameId = document.getElementById('subtopics');
    var count = 0;
       // alert("ExamNameId.options.length"+ExamNameId.length);
        for (var i=0; i<ExamNameId.options.length; i++) {
        if (ExamNameId.options[i].selected) {
            
          selectedArray[count] = ExamNameId.options[i].value;
          count++;
        }
        }
    
//alert(selectedArray);
  
   var resultString = "";
   var isCount = true;
  
   //var totalQuestions = parseInt(totalQues)*questArr.length;
   
   
       if(ExamNameId==null) {
            isCount = false;
       }
   
   //alert("isCount-->"+isCount+"--count-->"+count);
   
//if(parseInt(totalQues)<parseInt(availableQuestions)) {
if(isCount) {
  
    for(var i=0;i<30;i++){

    var res = document.creSearchForm.check_List[i].checked;

    if(res == true){

     var k = i + 1;
     var resultString1 = document.getElementById('text'+k).value;

     resultString = resultString.concat(resultString1,'!');
    }
    }
    /*For getting subtopic list 
     var selectedArray = new Array();

        var selObj = document.getElementById('subtopics');
        var count = 0;
        
        for (var i=0; i<selObj.options.length; i++) {
        if (selObj.options[i].selected) {
            
          selectedArray[count] = selObj.options[i].value;
          count++;
        }
        }*/
       // alert(selectedArray);
      //  alert(resultString);
    /* For getting subtopic list*/
    
    
if( resultString.indexOf("!") >= 1 && parseInt(count)>0){  
 var input_box = confirm("Please Confirm to Activate selected canditates!"); 
     if(input_box==true) {
         //doStatusUpdate(resultString, 'Active',selectedArray);  
          //  alert("selectedArray--"+selectedArray);
 var req = newXMLHttpRequest();
        //req.onreadystatechange = readyStateHandlerText(req,displayActivatedIds); 
      //  req.onreadystatechange = readyStateHandler(req,displayActivatedIds); 
        req.onreadystatechange = readyStateHandler44(req,displayActivatedIds);
        var url = CONTENXT_PATH+"/mcertRecordStatusUpdate.action?mcertConsultantId="+resultString+"&mcertConsultantStatus=Active&ExamNameIdList="+selectedArray;
    //var url = CONTENXT_PATH+"/putConsultantIdsList.action?creConsultantId="+consultantId;
 
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
     }
     
}
//resultString.indexOf("!") <1
else if(resultString.indexOf("!") <1)
    {
        alert("Please select atleast one Consultant!");
        
    }else {
        alert("Please select atleast one Exam!");
    }
 
}
}


function displayActivatedIds(resText) {
      
    alert("Activated Consultant Ids Are "+resText);
    setUnCheckboxes();
    getMcertRecords();
    unselectList();
   
     
}


function setUnCheckboxes() {
           var cb = document.creSearchForm.check_List;
	for (var i = 0; i < cb.length; i++) {
		cb[i].checked = false;
	}
}


function unselectList() {
 var selObj = document.getElementById('subtopics');
        
        for (var i=0; i<selObj.options.length; i++) {
        if (selObj.options[i].selected) {
          
          selObj.options[i].selected = false;
          
        }
        }
}
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

function readyStateHandlerXml(req,responseXmlHandler) {
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


function getQuestion(questionNumber,selectedAns,navigation,onClickStatus,remainingQuestions,subtopicId,specficQuestion){
             // alert("QuestionId-->"+questionNumber+"-----------------selectedAns--------------->"+selectedAns);
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerXml(req, populateQuestions);
      //  url = CONTENXT_PATH+"/getQuestion.action?questionNo="+questionNumber+"&selectedAns="+selectedAns+"&navigation="+navigation+"&onClickStatus="+onClickStatus+"&remainingQuestions="+remainingQuestions+"&random="+Math.random()+"&subTopicId="+subtopicId;
url = CONTENXT_PATH+"/getMcertQuestion.action?questionNo="+questionNumber+"&selectedAns="+selectedAns+"&navigation="+navigation+"&onClickStatus="+onClickStatus+"&remainingQuestions="+remainingQuestions+"&random="+Math.random()+"&subTopicId="+subtopicId+"&specficQuestionNo="+specficQuestion;
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
        
        /*Subtopic name*/
         var sectionTagObj = questDetails.getElementsByTagName("SECTION");
        var sectionTag = sectionTagObj[0];
        var section  = sectionTag.firstChild.nodeValue;
        
        document.getElementById("sectionName").innerHTML = section;
        
        
        
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

function showDetailResult(examKeyId)
{ 
   var req = newXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateDetailExamResult); 
   var url=CONTENXT_PATH+"/getMcertDetailExamInfo.action?examKeyId="+examKeyId;
   req.open("GET",url,"true");
   req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
   req.send(null);  
}

function populateDetailExamResult(text) {
   // alert(text);
    
   var background = "#3E93D4";
    var title = "Consultant Details Exam Result";
    var text1 = text; 
    var size = text1.length;
    var totalmarks =0;
    var marksObtained=0;
    var attemptedQues=0;
    var totalPercentage=0;
    // alert("text "+text1);
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'>"+text1+"<br />\
    ";
    
    //alert("text1"+text1);
       
       var str= text1;
       var temp = "<br> <b><u>Result in Detail </u>:</b> <br><br><table border='2'><tr><th>Topic Name </th><th>Total Marks </th><th> Marks Obtained</th><th>Attempted Questions </th><th>Percentage </th></tr>";
       var strArray=str.split("@");
       for (var i=0;i<strArray.length;i++)
           {
               var innerStrArray = strArray[i].split("!");
             //  for (var j=0;j<innerStrArray.length;j++){
             //  alert(strArray[i]);
             var percentage = ((innerStrArray[2]/innerStrArray[1])*100).toFixed(2);
             temp+="<tr><td>"+innerStrArray[0]+"</td><td>"+innerStrArray[1]+"</td><td>"+innerStrArray[2]+"</td><td>"+innerStrArray[3]+"</td><td>"+percentage+" % </td></tr>";
              // content
             //  }
            // alert();
             totalmarks=parseInt(totalmarks)+parseInt(innerStrArray[1]);
             marksObtained+=parseInt(innerStrArray[2]);
             attemptedQues+=parseInt(innerStrArray[3]);
 //             totalPercentage+=parseInt(percentage);
          
      }
 totalPercentage = ((marksObtained/totalmarks)*100).toFixed(2);
temp+="</table><br> <b><u>Total Summary</u>:</b> <br><br><table border='2'>";
temp+= "<tr><th>Topic Name </th><th>Total Marks </th><th> Marks Obtained</th><th>Attempted Questions </th><th>Percentage </th></tr>";

    temp+="<tr><td>Summary</td><td>"+totalmarks+"</td><td>"+marksObtained+"</td><td>"+attemptedQues+"</td><td>"+totalPercentage+" % </td></tr>";
    //alert("indexof"+indexof+"-------"+"lastindexof"+lastindexof);
    
    popup = window.open("","window","channelmode=0,width=400,height=250,top=200,left=350,scrollbars=yes,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
   // if(content.indexOf("^")){
      //  popup.document.write("<b>Employee Name : </b>"+content.substr(0,content.indexOf("^")));
      popup.document.write("<html><head><title>"+title+"</title></head> <body bgcolor='"+background +"' style='color:white;'><br />");
     // popup.document.write("<table border='2'>");
      
      //popup.document.write("<table border='1'>");
        popup.document.write(temp);
       // popup.document.write("<b>Reports To : </b>");
        
     //   popup.document.write("<table>");
        popup.document.write("</body></html>")
       
  //  }//Write content into it.      
   
}