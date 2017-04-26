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


function getActivityDetailsData() {
    //alert('hello');
    //alert('hai');
    
    var activityId= document.frmDBGrid.activityId.value;    
    var activityId = document.getElementById('activityId').value; 
    var accId = document.getElementById('accountActivId').value;    
    var contId = document.getElementById('contactActivId').value;    
    var activityType = document.getElementById('activityType').value;    
    var priority = document.getElementById('priority').value;    
    var campaignId = document.getElementById('campaignId').value;    
    var assignedToId = document.getElementById('assignedToId').value;    
    var status = document.getElementById('status').value;    
    var dueDate = document.getElementById('dueDate').value;    
    var alarm = document.getElementById('alarm').checked;    
    var description = document.getElementById('description').value;    
    var comments = document.getElementById('comments').value;
    
    var req = newXMLHttpRequest();    
    req.onreadystatechange = readyStateHandler(req,collectActivityDetails);

    var url = CONTENXT_PATH+"/saveActivity.action?activityType="+activityType+"&priority="+priority+"&campaignId="+campaignId+"&assignedToId="+assignedToId+"&status="+status+"&dueDate="+dueDate+"&alarm="+alarm+"&description="+description+"&comments="+comments+"&accId="+accId+"&contId="+contId+"&activId="+activityId;
    //var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=marketActivityDetails&activType="+activityType+"&priort="+priority+"&campId="+campaignId+"&assId="+assignedToId+"&stat="+status+"&dDate="+dueDate+"&alarm="+alarm+"&desc="+description+"&comm="+comments+"&accId="+accId+"&contId="+contId+"&activId="+activityId;
    //alert(url);
    
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}

function collectActivityDetails(suggText){
    var output = suggText;
    
    if(output == 'SUCCESS') {
        alert('Activity Details are Saved !');
    }
    if(output == 'FAILURE') {
        alert('Activity Details are Not Saved !');
    }
    
    //document.getElementById("activityId").value=output;
}