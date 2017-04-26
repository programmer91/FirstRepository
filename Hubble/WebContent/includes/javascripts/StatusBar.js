 //var i = 1;
//$('.progress .circle').removeClass().addClass('circle');
//$('.progress .bar').removeClass().addClass('bar');
//setInterval(function() {
//  $('.progress .circle:nth-of-type(' + i + ')').addClass('active');
//  
//  $('.progress .circle:nth-of-type(' + (i-1) + ')').removeClass('active').addClass('done');
//  
// // $('.progress .circle:nth-of-type(' + (i-1) + ') .label').html('&#10003;');
//  
// // $('.progress .bar:nth-of-type(' + (i-1) + ')').addClass('active');
//  
//  //$('.progress .bar:nth-of-type(' + (i-2) + ')').removeClass('active').addClass('done');
//  
//  i++;
//  
//  if (i==0) {
//    $('.progress .bar').removeClass().addClass('bar');
//    $('.progress div.circle').removeClass().addClass('circle');
//    i = 1;
//  }
//}, 100);



function setfunction(e){


var i;
if(e == "Creation"){
     i=1;
}
else if(e == "Submitted"){
    i=2;
}
else if(e == "Reviewed"){
   
    i=3;
}
else if(e == "Approved" || e == "Rejected"){
    i=4;
}

  document.getElementById("stageNum").value =i;
  //alert(document.getElementById("stageNum").value)
  //if($("#requestId").val()>0)
//$('.progress .circle:nth-of-type('+i+')').addClass('done');
if(i==4){
    //alert("Haii");
    $("#finalStatus").html(e);
}
for(var j=0;j<=i;j++){
  

  $('.progress .circle:nth-of-type(' + (j) + ')').removeClass('active').addClass('done');
  
}

}

function eventClick(element){
    //alert(element);
    
     var requestId = document.getElementById("requestId").value;
     var stageNum = document.getElementById("stageNum").value;
     if(element<=stageNum){
   var stage;
if(element == 1){
     stage="Creation";
}
else if(element == 2){
    stage="Submitted";
}
else if(element == 3){
   
    stage="Reviewed";
}
else if(element == 4){
   stage="Approved";
}

    clientStatusStages(requestId,stage);}
}

function clientStatusStages(requestId,stage){
     document.getElementById('stageComments').value ="";
          
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="status Details";
    var overlay = document.getElementById('overlayEmailCampaign');
    var specialBox = document.getElementById('specialBoxEmailCampaign');
          
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
               
    //window.location = 'reviewSearch.action?startDate='+startDate+'&endDate='+endDate+'&reviewType='+reviewType+'&reviewStatus='+reviewStatus+'&empnameById='+empnameById;
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
   
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerText(req,stageRelatedComments); 
                    
        var url = CONTENXT_PATH+"/doGetStageRelatedComments.action?requestId="+requestId+"&stage="+stage;;
 
        req.open("GET",url,"true");    
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
        
        
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


function readyStateHandlerText(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                //(document.getElementById("loadingMessageCam")).style.display = "none";
               
                responseTextHandler(req.responseText);
               
            }
            else {
                
             //   (document.getElementById("loadingMessageCam")).style.display = "none";
              //document.getElementById('restMessage').innerHTML = "<font color='red'>Issue with constant contact service...</font>";
                alert("HTTP error ---"+req.status+" : "+req.statusText);
                 
            }
        }else {
           // (document.getElementById("loadingMessageCam")).style.display = "block";
        }
    }
}


function stageRelatedComments(resText) 
{
   // alert(resText);
    
    document.getElementById('stageComments').value =resText;
}

$(document).ready(function(){
         
                var requestStage = document.getElementById("requestStage").value;
               // alert(requestStage);
               var requestId = document.getElementById("requestId").value;
               // alert(requestStage);
               if(parseInt(requestId,10))
                setfunction(requestStage);
            });