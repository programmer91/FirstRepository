/*Don't Alter these Methods*/
// This Script is used to get the text(String) from servlet not xml.


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

function readyStateHandler(req,responseTextHandler) {
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








/*
 
 function loadMail(mailId){
    alert('mailId*****'+mailId);
    document.write( '<a href="mailto:' + mailId + '">' '</a>' );
    }
 
function loadDiv(name) {
            
    var emailId = name;
    //alert('first name is@@@!!!   '+emailId);
    
    if(document.getElementById('addlabel1').style.display == 'none') { 
        document.getElementById('addlabel1').style.display = 'inline'; 
        //document.getElementById('addlabel2').style.display = 'none'; 
        //if(document.getElementById('addlabel2').style.display == 'block') { 
          //  document.getElementById('addlabel2').style.display = 'none';
        //}
    }
    else {    
        document.getElementById('addlabel1').style.display = 'none'; 
        //document.getElementById('addlabel2').style.display = 'inline'; 
        //document.getElementById('addlabel2').style.display = 'inline'; 
    }
    
    //document.getElementById(fiName).value = ch;
    //document.consultantsearchForm.fiName.value = ch;
    
    
    var req = newXMLHttpRequest();    
    req.onreadystatechange = readyStateHandler(req,collectConsultDetails);    
    var url = CONTENXT_PATH+"/getConsultDetails.action?consultantMail="+emailId;    
    alert(url);    
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}
 
function collectConsultDetails(suggText) {
    var consDetails =suggText;
    //alert('details%%%%%%%'+consDetails);
    
    var temp = new Array();
    temp = consDetails.split('*');
    
    var aTemp;
    aTemp= " ";
    
    if(consDetails == ''){
        document.getElementById("fiName").value = aTemp;
        document.getElementById("laName").value = aTemp;
        document.getElementById("miName").value = aTemp;
        
        document.getElementById("email2").value = aTemp;
        document.getElementById("titleTypeId").value = aTemp;
        document.getElementById("gender").value = aTemp;
        
        document.getElementById("workPhoneNo").value = aTemp;
        document.getElementById("homePhoneNo").value = aTemp;
        document.getElementById("cellPhoneNo").value = aTemp;
        
        document.getElementById("skills").value = aTemp;
        document.getElementById("ratePerHour").value = aTemp;
        document.getElementById("practiceId").value = aTemp;
        
        document.getElementById("comments").value = aTemp;
    }
    
    if(temp[0] =='null'||temp[0] =='undefined') {
        document.getElementById("fiName").value = aTemp;
    }else{
        document.getElementById("fiName").value = temp[0];
    }
    
    if(temp[1] =='null'||temp[1] =='undefined') {        
        document.getElementById("laName").value = aTemp;
    }else{
        document.getElementById("laName").value = temp[1];
    }
    
    if(temp[2] =='null'||temp[2] =='undefined') {        
        document.getElementById("miName").value = aTemp;
    }else{
        document.getElementById("miName").value = temp[2];
    }
    
    if(temp[3] =='null'||temp[3] =='undefined') {
        document.getElementById("email2").value = aTemp;
    }else{
        document.getElementById("email2").value = temp[3];
    }
    
    if(temp[4] =='null'||temp[4] =='undefined') {        
        document.getElementById("titleTypeId").value = aTemp;
    }else{
        document.getElementById("titleTypeId").value = temp[4];
    }
    
    if(temp[5] =='null'||temp[5] =='undefined') {        
        document.getElementById("gender").value = aTemp;
    }else{
        document.getElementById("gender").value = temp[5];
    }
    
    if(temp[6] =='null'||temp[6] =='undefined') {
        document.getElementById("workPhoneNo").value = aTemp;
    }else{
        document.getElementById("workPhoneNo").value = temp[6];
    }
    
    if(temp[7] =='null'||temp[7] =='undefined') {        
        document.getElementById("homePhoneNo").value = aTemp;
    }else{
        document.getElementById("homePhoneNo").value = temp[7];
    }
    
    if(temp[8] =='null'||temp[8] =='undefined') {        
        document.getElementById("cellPhoneNo").value = aTemp;
    }else{
        document.getElementById("cellPhoneNo").value = temp[8];
    }
    
    if(temp[9] =='null'||temp[9] =='undefined') {   
        document.getElementById("skills").value = aTemp;
    }else{
        document.getElementById("skills").value = temp[9];
    }
    
    if(temp[10] =='null'||temp[10] =='undefined') {        
        document.getElementById("ratePerHour").value = aTemp;
    }else{
        document.getElementById("ratePerHour").value = temp[10];
    }
    
    if(temp[11] =='null'||temp[11] =='undefined') {        
        document.getElementById("practiceId").value = aTemp;
    }else{
        document.getElementById("practiceId").value = temp[11];
    }
    
    if(temp[12] =='null'||temp[12] =='undefined') {        
        document.getElementById("comments").value = aTemp;
    }else{
        document.getElementById("comments").value = temp[12];
    }
    
}*/


