var url= null;


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
/*function readyStateHandlerText1(req,responseTextHandler){
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {               
                responseTextHandler(req.responseText);
            } else {
                alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }
    }
} */ 

function readyStateHandler(req,responseTextHandler) {
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
/**
 * To get the Cre Consultant Comments in pop
 */

function getCreConsultantComments(consId,consLevel){
   var level1 = consLevel;
 if ( level1 == "Registration" ) {
  populateComments("Constultant is in Registration Level");
} else if (level1 == "Exam") {

     populateComments("Constultant is in Exam Level");
} else {
   var req = newXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateComments); 

    var url=CONTENXT_PATH+"/popupCreCommentsWindow.action?creConsultantId="+consId+"&creConsultantLevel="+consLevel;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

}


function populateComments(text) {

    var background = "#3E93D4";
    var title = "Comments";
    var text1 = "^"+text+"^"; 

    var size = text1.length;

    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'>"+text1+"<br />\
    </body></html>";
   var str= text1;
       var strArray=str.split("^");

var indexof=(content.indexOf("^")+1);
    var lastindexof=(content.lastIndexOf("^"));

   
   popup = window.open("","window","channelmode=0,width=400,height=150,top=200,left=350,scrollbars=yes,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
         if(content.indexOf("^")){
        popup.document.write("<b>Comments : </b>"+content.substr(0,content.indexOf("^"))+text);
        popup.document.write("<br><br>"+content.substr((content.lastIndexOf("^")+1),content.length));
        
  } 
}


// ============================================================================================================================
//   New functions for cre in Hubble
// ===========================================================================================================================

/*
To activate the records between two dates
*/



function init() {
var currentdate = new Date(); 

var datetime = (currentdate.getMonth()+1) + "/"+ currentdate.getDate()   + "/"+ currentdate.getFullYear();
document.creSearchForm.startDate.value = datetime;
document.creSearchForm.toDate.value = datetime;

}


/*
New Method to get Cre Records with check boxes
*/





//----------------------

 /** checkbox event **/
           
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

 function readyStateHandler(req,responseTextHandler) {
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

/***  to get the Cre  List ***/
function getCreRecords(){
            
//var tempCreConsultantName = document.getElementById('creConsultantName').value;

var tempCreConsultantId = document.getElementById('creConsultantId').value;
var tempStartDate = document.getElementById('startDate').value;
var tempToDate = document.getElementById('toDate').value;
var tempStatus = document.getElementById('creConsultantStatus').value;
//var tempCategory = document.getElementById('category').value;
//var tempLevel = document.getElementById('level').value;
var tempInvAt = document.getElementById('interviewAt').value;
var tempCreConsultantId1 = document.getElementById('creConsultantId1').value;
var course = document.getElementById('course').value;
var creStream = document.getElementById('creStream').value;

 if(tempStartDate!="" && tempToDate!=""){

    var sd = new Date(tempStartDate);
       var td = new Date(tempToDate);
    if(sd>td){
         alert("Start date must be less then To date");
         return false;
     }
 } 
  if(tempCreConsultantId!=""){
     if(tempCreConsultantId1 ==""){
    alert("Please Enter To !!");
return false;
}
 }
  if(tempCreConsultantId1!=""){
     if(tempCreConsultantId ==""){
    alert("Please Enter From !!");
return false;
}
 }
    if(tempStatus =="")
{
alert("Please select Status !!");
return false;
}  var tempCollegeName="";
   var CollegeName=document.getElementById('recLocation').value;
   //var otherCollege=document.getElementById('othertextfield').value;
 
 
       tempCollegeName= document.getElementById("recLocation").value.trim();
                 
  

       var req = newXMLHttpRequest();
        //req.onreadystatechange = readyStateHandlerText(req,populateRecords); 
        req.onreadystatechange = readyStateHandler44(req,populateRecords); 
        //var url = CONTENXT_PATH+"/getCreRecordsList.action?creConsultantName="+tempCreConsultantName+"&creConsultantId="+tempCreConsultantId+"&creConsultantId1="+tempCreConsultantId1+"&creStartDate="+tempStartDate +"&creToDate="+tempToDate+"&creConsultantStatus="+tempStatus+"&category="+tempCategory+"&level="+tempLevel+"&interviewAt="+tempInvAt;
        var url = CONTENXT_PATH+"/getCreRecordsList.action?creConsultantId="+tempCreConsultantId+"&creConsultantId1="+tempCreConsultantId1+"&creStartDate="+tempStartDate +"&creToDate="+tempToDate+"&creConsultantStatus="+tempStatus+"&interviewAt="+tempInvAt+"&creCollegeName="+escape(tempCollegeName)+"&course="+escape(course)+"&creStream="+escape(creStream);
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
}

function populateRecords(totalData){
    //alert(totalData)
    var str = totalData;
    var n=0;
    var n1 =0;
    var n2 =0;
  setUnCheckboxes();
  document.getElementById('text').checked=false;
  
  var status=document.getElementById('creConsultantStatus').value;
   if(status=="Registered" || status=="Rejected")
           {
               document.getElementById('activeButton').style.display = 'block'; 
               document.getElementById('examDetails').style.display = 'block';
               
                
           }
           
           else
               {
                   document.getElementById('activeButton').style.display = 'none'; 
                   document.getElementById('examDetails').style.display = 'none';
               }
   if(str.indexOf("@")>=1){
       if(status!="Rejected") {
        document.getElementById('rejectButton').style.display = 'block';
       }else {
           document.getElementById('rejectButton').style.display = 'none';
       }
       document.getElementById('creConsultantList').style.display = 'block'; 
        
       
      
          for(var r=1;r<=100;r++){
                document.getElementById('gridRow_'+r).style.display = 'block'; 
          }
        var caps = new Array();
       caps = str.split("@");
   //-----------------------------------------------------------------  populating records  */
        var ids = new Array();
        var consultantNames = new Array();
        var consultantStatus = new Array();
         var consultantCategory = new Array();
         var consultantCourseStream = new Array();
        ids = caps[0].split("!");
        consultantNames = caps[1].split("!");
        consultantStatus = caps[2].split("!");
        consultantCategory = caps[3].split("!");
        consultantCourseStream = caps[4].split("!");
        var n1=0;
    for(var j=0; j<ids.length;j++) {
        n1 = parseInt(j)+1;
       // alert(n1);
        document.getElementById('text'+n1).value= ids[j];
        document.getElementById('texti'+n1).value= 'MSSCRE'+ids[j];
        document.getElementById('texta'+n1).value= consultantNames[j];
        document.getElementById('texts'+n1).value= consultantStatus[j];
        document.getElementById('textc'+n1).value= consultantCategory[j];
        document.getElementById('textcourse'+n1).value= consultantCourseStream[j].split("#^$")[0];
         document.getElementById('textstream'+n1).value= consultantCourseStream[j].split("#^$")[1];
    }


/*==================================
 * Empty Remaining Fields
 *==================================
 */
    var count=n1;
   // alert(count);
    var s = 0;
    var res=0;
    for(k=0;k<=100-n1;k++){
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
         if(document.getElementById('texts'+s)!=null){
            document.getElementById('texts'+s).value="";
        }

        if(document.getElementById('textc'+s)!=null){
            document.getElementById('textc'+s).value="";
        }else{
            break;
        } 
        
        if(document.getElementById('textcourse'+s)!=null){
            document.getElementById('textcourse'+s).value="";
        }else{
            break;
        } 
         if(document.getElementById('textstream'+s)!=null){
            document.getElementById('textstream'+s).value="";
        }else{
            break;
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

function getCreData(chk, status){
var resultString = "";

    for(i=0;i<100;i++){

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
    
            getCreDetails(resultString, status);
        
    }else{
        alert("Please select atleast one Consultant!");
    }
} 


function getCreDetails(vals, status){  

      var input_box = confirm("Please Confirm to "+status+" selected canditates!"); 
     if(input_box==true) {
         doStatusUpdate(vals, status);      
     }
     else{
       alert("Status updation canceled!");
     }
}

function doStatusUpdate(consultantId, status){
 //  alert("selectedArray--"+selectedArray);
 var req = newXMLHttpRequest();
       // req.onreadystatechange = readyStateHandlerText(req,getUpdateResult); 
        req.onreadystatechange = readyStateHandler(req,getUpdateResult); 
        var url = CONTENXT_PATH+"/creRecordStatusUpdate.action?creConsultantId="+consultantId+"&creConsultantStatus="+status;
    //var url = CONTENXT_PATH+"/putConsultantIdsList.action?creConsultantId="+consultantId;
 
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null); 
}

function getUpdateResult(result) {
    var resultText=result;
    
    alert("Rejected Consultant Ids Are "+result);
    setUnCheckboxes();
    getCreRecords();
    unselectList();
}
function popupWindow() {
 window.open("../cre/SubtopicsWindow.jsp", "_blank", "scrollbars=1,resizable=1,height=440,width=700");
}

//GEt tech review Comments

 function getTechLeadComments(techReviewId){
    var req = newXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateTechLeadComments); 

    var url=CONTENXT_PATH+"/popupTechLeadComments.action?techReviewId="+techReviewId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
      }

function populateTechLeadComments(text) {

    var background = "#3E93D4";
    var title = "Tech Lead Comments";
    var text1 = "^"+text+"^"; 

    var size = text1.length;
    
   
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'>"+text1+"<br />\
    </body></html>";
   var str= text1;
       var strArray=str.split("^");

var indexof=(content.indexOf("^")+1);
    var lastindexof=(content.lastIndexOf("^"));

   
   popup = window.open("","window","channelmode=0,width=400,height=150,top=200,left=350,scrollbars=yes,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
         if(content.indexOf("^")){
        popup.document.write("<b>Comments : </b>"+content.substr(0,content.indexOf("^"))+text);
        popup.document.write("<br><br>"+content.substr((content.lastIndexOf("^")+1),content.length));
        
  }
    
}

// hr comments

 function getHrComments(hrReviewId){
    var req = newXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateHrComments); 

    var url=CONTENXT_PATH+"/popupHrLeadComments.action?hrReviewId="+hrReviewId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
      }

function populateHrComments(text) {

    var background = "#3E93D4";
    var title = "Hr Comments";
    var text1 = "^"+text+"^"; 

    var size = text1.length;
    
   
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'>"+text1+"<br />\
    </body></html>";
   var str= text1;
       var strArray=str.split("^");

var indexof=(content.indexOf("^")+1);
    var lastindexof=(content.lastIndexOf("^"));

   
   popup = window.open("","window","channelmode=0,width=400,height=150,top=200,left=350,scrollbars=yes,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
         if(content.indexOf("^")){
        popup.document.write("<b>Comments : </b>"+content.substr(0,content.indexOf("^"))+text);
        popup.document.write("<br><br>"+content.substr((content.lastIndexOf("^")+1),content.length));
        
  }
    
}


// Vp comments




 function getVpComments(vpReviewId){
    var req = newXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateVpComments); 

    var url=CONTENXT_PATH+"/popupVpComments.action?vpReviewId="+vpReviewId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
      }

function populateVpComments(text) {

    var background = "#3E93D4";
    var title = "VP Comments";
    var text1 = "^"+text+"^"; 

    var size = text1.length;
    
   
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'>"+text1+"<br />\
    </body></html>";
   var str= text1;
       var strArray=str.split("^");

var indexof=(content.indexOf("^")+1);
    var lastindexof=(content.lastIndexOf("^"));

   
   popup = window.open("","window","channelmode=0,width=400,height=150,top=200,left=350,scrollbars=yes,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
         if(content.indexOf("^")){
        popup.document.write("<b>Comments : </b>"+content.substr(0,content.indexOf("^"))+text);
        popup.document.write("<br><br>"+content.substr((content.lastIndexOf("^")+1),content.length));
        
  }
    
}


function doDeleteTechLeadReview(consultantId, techReviewId) {
var r=confirm("Are you want to Delete ?");
if (r==true)
  {
  
window.location="deleteTechLeadReview.action?id="+consultantId+"&techReviewId="+techReviewId;
  }
else
  {
  alert("Review Deletion Canceled!")
  }
}

function doDeleteHrReview(consultantId, hrReviewId) {
var r=confirm("Are you want to Delete ?");
if (r==true)
  {
  
window.location="deleteHrReview.action?id="+consultantId+"&hrReviewId="+hrReviewId;
  }
else
  {
  alert("Review Deletion Canceled!")
  }
}

function doDeleteVpReview(consultantId, vpReviewId) {
var r=confirm("Are you want to Delete ?");
if (r==true)
  {
  
window.location="deleteVpReview.action?id="+consultantId+"&vpReviewId="+vpReviewId;
  }
else
  {
  alert("Review Deletion Canceled!")
  }
}

/*
For making Employee
Date : 09/03/2013
*/

function doAddAsEmployee(consultantId, status, level) {
if((status == "Selected") && (level == "VP Level")||(status == "Selected") && (level == "HR Level")) {
    var retVal = prompt("Enter Consultant Miracle mailId");
            if(retVal != null) {
                var atpos=retVal.indexOf("@");
                var dotpos=retVal.lastIndexOf(".");
                if (atpos<1 || dotpos<atpos+2 || dotpos+2>=retVal.length)
                {
                    alert("Please check the given email Id");
                }else {
                       // alert(retVal);
                        var is_miralce = retVal.indexOf('miraclesoft.com');
                          //  alert(is_miralce);
                        if(is_miralce != -1){
                            var req = newXMLHttpRequest();
                            req.onreadystatechange = readyStateHandlerText(req,makeConsultantAsEmployee); 
                            var url=CONTENXT_PATH+"/ConsultentAsEmp.action?email="+retVal+"&consultantId="+consultantId;
                            req.open("GET",url,"true");
                            req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
                            req.send(null);
                        }else{
                            alert('You should enter valid Miracle EmailId');
                        }
                }
            }

    }else{
      alert("He/She is not Completed his selection process");
   }
}

function makeConsultantAsEmployee(resultText){
   if(resultText == "E"){
     alert("Email  Existed. Please contact Itteam for new email Id !");
   }else if(resultText == "A"){
      //creConsultantList.action?resultMessage=${resultMessage}
      window.location="creConsultantList.action?resultMessage=<font color=\"green\" size=\"1.5\">Consultant Changed as a Employee!</font>";
   }else{
     alert(resultText);
   }
}

function getCreAvailableQuestionCount() {
    
    var selectedArray = new Array();

        var selObj = document.getElementById('subtopics');
        var count = 0;
        
        for (var i=0; i<selObj.options.length; i++) {
        if (selObj.options[i].selected) {
            
          selectedArray[count] = selObj.options[i].value;
          //alert("Name-->"+selObj.options[i].name);
          count++;
        }
        
        }if(count>0) {
                            var req = newXMLHttpRequest();
                            req.onreadystatechange = readyStateHandlerText(req,displayCreQuestCount); 
                            var url=CONTENXT_PATH+"/getCreAvailCreQuestions.action?subTopicsList="+selectedArray;
                            req.open("GET",url,"true");
                            req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
                            req.send(null);
}if(count == 0) {
    alert("Please select Subtopics!");
}
     
}

function displayCreQuestCount(resText) {
    if(resText != "") {
        document.getElementById("availableQues").value = resText;
        document.getElementById("availQuest").innerHTML = "(Available Questions:"+resText+" )";
    


}else {
document.getElementById("availableQues").value = parseInt(0);
document.getElementById("availQuest").innerHTML = "(Available Questions: 0 )";
}
}

function resetNoOfQuest() {
   document.getElementById("availableQues").value = "";
    document.getElementById("availQuest").innerHTML = "";
    document.getElementById("totalQues").value = "";
    document.getElementById("totalNoOfQuestions").value = "";
    document.getElementById("totDuration").value = "";
    document.getElementById("minMarks").value = "";
    var examName = document.getElementById("examType").value ;
    if(examName.length<1) {
        unselectList();
        alert("Please mention exam type!");
        
    }
    
}
/*function displayExamDetails() {
    var resultString = "";

    for(i=0;i<100;i++){

    var res = document.creSearchForm.check_List[i].checked;
//var status = texts1
    if(res == true){

     var k = i + 1;
     var resultString1 = document.getElementById('text'+k).value;

     resultString = resultString.concat(resultString1,'!');
    }
    }
    if( resultString.indexOf("!") >= 1 ){  
     document.getElementById('examDetails').style.display = 'block'; 
     document.getElementById('activeButton').style.display = 'none'; 
     document.getElementById('creConsultantList').style.display = 'none'; 
     document.getElementById('getCreSearch').style.display = 'none'; 
    }else {
         alert("Please select atleast one Consultant!");
    }
     
}*/
 function displayExamDetails() {
    var resultString = "";
    var activatedResult="";
    var activatedResult2="";
    var flag = "false";
var id="";

    for(i=0;i<100;i++){

    var res = document.creSearchForm.check_List[i].checked;
    
    if(res == true){
        
     var k = i + 1;
     var resultString1 = document.getElementById('text'+k).value;
    
     resultString = resultString.concat(resultString1,'!');
     
     var status=document.getElementById('texts'+k).value;
     
     
     var activatedResult1= activatedResult.concat(activatedResult,"Please check before activating."+"\n");        
        if(status == "Active")
            {
                var id1=document.getElementById('texti'+k).value;
      
     id=id.concat(id1,',');
                activatedResult2=activatedResult1.concat(activatedResult,""+id+"  is already assigned with exam.\n");
                flag="true";
            }
              
    }
      
    }
    
    if( resultString.indexOf("!") >= 1 ){
        if(flag == "false"){
     document.getElementById('examDetails').style.display = 'none'; 
     document.getElementById('activeButton').style.display = 'none'; 
     document.getElementById('creConsultantList').style.display = 'none'; 
     document.getElementById('getCreSearch').style.display = 'none'; 
        }else
            {
                alert(activatedResult2);
            }
    }else {
         alert("Please select atleast one Consultant!");
    }
     
}


function displayBack() {
     document.getElementById('examDetails').style.display = 'none'; 
     document.getElementById('activeButton').style.display = 'block'; 
     document.getElementById('creConsultantList').style.display = 'block'; 
     document.getElementById('getCreSearch').style.display = 'block'; 
}
//New method for placing Total number of questions
function getTotalQuestions() {
    var totalQues = document.getElementById('totalQues').value;
    if(totalQues.length>0) {
    var availableQuestions = document.getElementById('availableQues').value;
     var questArr = availableQuestions.split(",");
     
     var isValidNoOfQuest = true;
    for(var j=0;j<questArr.length;j++){
       if(parseInt(totalQues)>parseInt(questArr[j]))  {
           isValidNoOfQuest = false;
           break;
       }
            
    }
     if(isValidNoOfQuest) {
    var totalQuestions = parseInt(totalQues)*questArr.length;
   document.getElementById('totalNoOfQuestions').value = totalQuestions;
   }else {
         
         alert("Number of questions from each subtopic must be less than available questions from each subtopic!");
         document.getElementById('totalQues').value = "";
     }
    }
}

function insertExamDetails() {
    
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
  
    for(var i=0;i<100;i++){

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
        req.onreadystatechange = readyStateHandler66(req,displayActivatedIds);
        var url = CONTENXT_PATH+"/creRecordStatusUpdate.action?creConsultantId="+resultString+"&creConsultantStatus=Active&ExamNameIdList="+selectedArray;
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
  /*  
    var totalQues = document.getElementById('totalQues').value;
    var totDuration = document.getElementById('totDuration').value;
    var minMarks = document.getElementById('minMarks').value;
    var availableQuestions = document.getElementById('availableQues').value;
    var totalNoOfQuestions = document.getElementById('totalNoOfQuestions').value;
    if((totalQues.length>0) && (totDuration.length>0) && (minMarks.length>0)) {
   var resultString = "";
   var isCount = true;
   var questArr = availableQuestions.split(",");
   //var totalQuestions = parseInt(totalQues)*questArr.length;
   
   for(var j=0;j<questArr.length;j++){
       if(parseInt(totalQues)>parseInt(questArr[j])) 
            isCount = false;
    }
   
   
   
//if(parseInt(totalQues)<parseInt(availableQuestions)) {
if(isCount) {
    if(parseInt(minMarks)<parseInt(totalNoOfQuestions)) {
    for(var i=0;i<100;i++){

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
        }
    /* For getting subtopic list
    
    
if( resultString.indexOf("!") >= 1 ){  
 var input_box = confirm("Please Confirm to Activate selected canditates!"); 
     if(input_box==true) {
         //doStatusUpdate(resultString, 'Active',selectedArray);  
          //  alert("selectedArray--"+selectedArray);
 var req = newXMLHttpRequest();
        //req.onreadystatechange = readyStateHandlerText(req,displayActivatedIds); 
        req.onreadystatechange = readyStateHandler(req,displayActivatedIds); 
        var url = CONTENXT_PATH+"/creRecordStatusUpdate.action?creConsultantId="+resultString+"&creConsultantStatus=Active&subTopicsList="+selectedArray+"&totalQues="+totalNoOfQuestions+"&totDuration="+totDuration+"&minMarks="+minMarks;
    //var url = CONTENXT_PATH+"/putConsultantIdsList.action?creConsultantId="+consultantId;
 
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
     }
     else{
       alert("Status updation canceled!");
     }
}
    }else {
        alert("Minimum marks must be less than total number of questions !");
    }
}else {
    alert("Number of questions from each subtopic must be less than available questions from each subtopic!");
}
    }
    else {
       alert("Please enter manadatory fields!"); 
       
    }
}*/
function displayActivatedIds(resText) {
      // document.getElementById('availQuest').innerHTML = "";
       //document.getElementById('totalNoOfQuestions').value = "";
       //document.getElementById('totalQues').value = "";
   //document.getElementById('totDuration').value = "";
    // document.getElementById('minMarks').value = "";
    // document.getElementById('examDetails').style.display = 'none'; 
   // document.getElementById('getCreSearch').style.display = 'block'; 
    alert("Activated Consultant Ids Are "+resText);
    setUnCheckboxes();
    getCreRecords();
    unselectList();
   
     
}

/*Scripts Cre Exam
 *Date : 06/11/2013
 * Author : Santosh Kola
 */
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


//function getQuestion(questionNumber,selectedAns,navigation,onClickStatus,remainingQuestions,subtopicId){
 function getQuestion(questionNumber,selectedAns,navigation,onClickStatus,remainingQuestions,subtopicId,specficQuestion){
             // alert("QuestionId-->"+questionNumber+"-----------------selectedAns--------------->"+selectedAns);
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerXml(req, populateQuestions);
      //  url = CONTENXT_PATH+"/getQuestion.action?questionNo="+questionNumber+"&selectedAns="+selectedAns+"&navigation="+navigation+"&onClickStatus="+onClickStatus+"&remainingQuestions="+remainingQuestions+"&random="+Math.random()+"&subTopicId="+subtopicId;
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

 //For Cre Consultant Details exam result pop up for CRE Team tracking..
// Ajay Tummapala
function showDetailResult(examKeyId)
{ 
   var req = newXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateDetailExamResult); 
   var url=CONTENXT_PATH+"/getCreDetailExamInfo.action?examKeyId="+examKeyId;
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



function getCandidateName(empId,topicName){
    
    var req = newXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateCandidateName); 
   var url=CONTENXT_PATH+"/getExamCandidateName.action?empId="+empId+"&topicName="+topicName;
   req.open("GET",url,"true");
   req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
   req.send(null);  
}

function populateCandidateName(text) {
    var background = "#3E93D4";
    var title = "Consultant Name";
    var text1 = text; 
    var size = text1.length;
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+text1+"<br />\
    </body></html>";
    //Create the popup       
    popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
    popup.document.write(content); //Write content into it.    
     
    
    
}


function validateMandatoryFields()
{
   
    var examType=document.getElementById("examType").value;
    
    var totalQuestFromSubtopic=document.getElementById("totalQues").value;
    var totalNoOfQuestions=document.getElementById("totalNoOfQuestions").value;
    var totDuration=document.getElementById("totDuration").value;
    var minMarks=document.getElementById("minMarks").value;
    var subtopics=document.getElementById("subtopics").value;
   //var isExamExsit = validateExamType();
  // alert("isExamExsit-->"+isExamExsit);
    if(examType==""||examType==null)
        {
            alert("Please provide examType");
            return false;
        }
    else if(subtopics=="")
        {
            alert("Please provide subtopic's");
            return false;
        }
    else if(totalQuestFromSubtopic=="")
        {
            alert("Please provide number of questions from each subtopic!");
            return false;
        }
    else if(totDuration=="")
        {
            alert("Please provide Duration");
            return false;
        }   
    else if(minMarks=="")
        {
            alert("Please provide Minimum Marks");
            return false;
        }
    else if(parseInt(minMarks)>=parseInt(totalNoOfQuestions))
        {
            alert("Minimum marks must be less than total number of questions");
            return false;
        }
         
    
}
function validateExamType()
{
 //  alert("");
    var examType=document.getElementById("examType").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populateExamType); 
    var url=CONTENXT_PATH+"/getExamType.action?examType="+examType;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateExamType(resText)
{
  var result = resText;
 // alert("---------->"+result);
  if(result == "1")
      {
          alert("Exam name already exist! Please try with other name");
          unselectList();
          document.getElementById("examType").value = "";
          //return false;
      }
  

  
}

function examDetailsPopup(){
 
   var req = newXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateDetailCreExam); 
   var url=CONTENXT_PATH+"/getCreDetailExamDetails.action";
   req.open("GET",url,"true");
   req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
   req.send(null); 
    
}
function populateDetailCreExam(text){
   var background = "#3E93D4";
    var title = "CRE Exam Details";
    var text1 = text; 
    var size = text1.length;
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>"+
    "<body bgcolor='"+background +"' style='color:white;'><h4 align='center'>"+title+"</h4><br />";
   content+="<table border='0'>";
  var strArray=text1.split("|");
   content+="<tr><th><u>Exam Type</u></th><th colspan='3' align='center'><u>Topics</u></th></tr>";
       for (var i=0;i<strArray.length;i++)
           {
               //alert(strArray);
               content+="<tr>";
               var innerArray = strArray[i].split(":");
              // alert(innerArray);
                        content+="<th>"+innerArray[0]+":-</th>"
                       // alert(innerArray[0]);
                       // alert(innerArray[1]);
                        var innerArray1 = innerArray[1].split(",");
                            for (var k=0;k<innerArray1.length;k++)
                                {
                                    content+="<td>&nbsp;</td>";
                                    content+="<td>"+innerArray1[k]+"</td>"
                                }      
                content+="</tr>";    
           }
     content+="<table>";
    content+="</body></html>";
 //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=350,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
}


function checkSubtopics() {
       var selectedArray = new Array();

        var selObj = document.getElementById('subtopics');
        var count = 0;
        
        for (var i=0; i<selObj.options.length; i++) {
        if (selObj.options[i].selected) {
            
          selectedArray[count] = selObj.options[i].value;
          //alert("Name-->"+selObj.options[i].name);
          count++;
        }
        
        }
        
        if(count == 0) {
            alert("Please select Subtopics!");
        }
    

}

 
   /*
 * New method for pouplating Operating Comments
 * Date : 08/15/2014
 * Author : Santosh Kola
 */

 function getOnboardComments(onboardReviewId){
    var req = newXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateVpComments); 

    var url=CONTENXT_PATH+"/popupOnboardComments.action?onboardReviewId="+onboardReviewId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
      }

function populateOnboardComments(text) {

    var background = "#3E93D4";
    var title = "Onboard Comments";
    var text1 = "^"+text+"^"; 

    var size = text1.length;
    
   
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'>"+text1+"<br />\
    </body></html>";
   var str= text1;
       var strArray=str.split("^");

var indexof=(content.indexOf("^")+1);
    var lastindexof=(content.lastIndexOf("^"));

   
   popup = window.open("","window","channelmode=0,width=400,height=150,top=200,left=350,scrollbars=yes,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
         if(content.indexOf("^")){
        popup.document.write("<b>Comments : </b>"+content.substr(0,content.indexOf("^"))+text);
        popup.document.write("<br><br>"+content.substr((content.lastIndexOf("^")+1),content.length));
        
  }
    
}

function downloadOnboardForm(creId,level){
   // if(level != 'Onboard'){
      //  alert("Please Add/Update onboard comments!");
    //}else {
        window.location="printOnboardForm.action?creId="+creId;
   // }
  
}



var stream = new Array();
function streamAssign(){
    stream = new Array();
//alert("assign");
stream['B.Tech'] = new Array('CSE','EEE','ECE','IT','MECH','Civil','Others');
stream['B.E'] = new Array('AP','MH','ND','WB','TN','KA');
stream['B.Sc'] = new Array('Computers','Electronics','Chemistry','Others');
stream['B.Com'] = new Array('Computers','Commerce','Accountancy');
stream['B.A'] = new Array('');
stream['BBM'] = new Array('');
stream['Other'] = new Array('');
stream['MCA'] = new Array('Computers');
stream['MBA'] = new Array('HR','Finance','Marketing','IT','Logistics','Retail','Hospitality and Tourism');
stream['M.Tech'] = new Array('Computer Science','Information Technology','Information Security','Software Engineering','Distributed Computing','Image Processing');
stream['M.Com'] = new Array('');
stream['M.Sc'] = new Array('');
stream['MS'] = new Array('');

}

function setStreams(fieldId, newOptions, newValues) {
    selectedField = document.getElementById(fieldId);
    selectedField.options.length = 0;
    //alert(newOptions.length);
    for (i=0; i<newOptions.length; i++) {
        selectedField.options[selectedField.length] = new Option(newOptions[i], newValues[i]);
    }
}  


/*
 * Method For Consultant reports search fields 
 * Author : Santosh Kola
 * date : 02/18/2016 04:45Pm
 */
/*function checkSearchFields() {
  //  alert("Haii");
    var examType=document.getElementById("examType").value;
    var reportStatus=document.getElementById("reportStatus").value;
    
    if(parseInt(examType.trim().length)>0){
      //  alert(reportStatus);
         if(parseInt(reportStatus.trim().length)>0&&(reportStatus=='PASS'||reportStatus=='FAIL')){
             return true;
             
         }else {
             alert("Please Select Status as PASS or FAIL.");
             return false;
         }
    }else {
        return true;
    }
}*/
function checkSearchFields() {
  //  alert("Haii");
    var examType=document.getElementById("examType").value;
    var reportStatus=document.getElementById("reportStatus").value;
    
    if(parseInt(examType.trim().length)==0){
      //  alert(reportStatus);
         if(parseInt(reportStatus.trim().length)>0){
             alert("Please Select Exam Type.");
             return false;
             
         }else {
             //alert("Please Select Status as PASS or FAIL.");
             return true;
         }
    }else {
        return true;
    }
}


var temp ="0";
function setCreDefaultDatesForReport(){
    
    var currentYear = new Date().getFullYear();    
    var currentMonth = new Date().getMonth() +1;    
    var currentDay = "01";
    if(currentMonth <10 ){
        currentMonth = temp+ currentMonth;
    }
    var firstDayOfMonth = currentMonth + '/' + currentDay + '/' + currentYear;
    var intCurrentYear = parseInt(currentYear);
    
    var now = new Date();// Add 30 days   
    now.setDate(1);
    now.setDate(now.getDate() + 29);
    
    var currentYear = now.getFullYear();    
    var currentMonth = now.getMonth() +1;    
    var currentDay = now.getDate();
    if(currentDay <10 ){
        currentDay = temp+ currentDay;
    }
    if(currentMonth <10 ){
        currentMonth = temp+ currentMonth;
    }
    var lastDate;    
    var lastDate = currentMonth + '/' + currentDay + '/' + currentYear;    
    var currentDate = new Date();
    var day = currentDate.getDate();
    var month = currentDate.getMonth() + 1;
    var year = currentDate.getFullYear();

    var endDate =  month+"/"+day+"/"+year;


    var today = new Date();
    var priorDate = new Date(endDate);
    priorDate.setDate(priorDate.getDate()-30);
    var priorDay = priorDate.getDate();
    var priorMonth = priorDate.getMonth() + 1;
    var priorYear = priorDate.getFullYear();
    var startDate="";
    
    if(parseInt(priorDay)<10){
        priorDay = "0"+priorDay;
    }
    if(parseInt(priorMonth)<10){
        startDate = "0"+priorMonth+"/"+priorDay+"/"+priorYear;
    }else{
        startDate = priorMonth+"/"+priorDay+"/"+priorYear;
    }
    
    /*Start date before last month first date and end date as current date */
    
    document.getElementById('startDate').value = startDate;       
    document.getElementById('endDate').value = endDate;
  //  document.getElementById('dueStartDate').value = startDate;       
  //  document.getElementById('dueEndDate').value = endDate;  

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


function readyStateHandler66(req,responseTextHandler) {
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
