/*Don't Alter these Methods*/
var temp;
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
function readyStateHandlers(req,responseHandler) {
    
    return function() {
        var  myHTMLTable = document.getElementById("tblActUpdate");
        ClrTable(myHTMLTable);
        if (req.readyState == 4) {
            
            if (req.status == 200) {
                document.getElementById("loadActivityMessage").style.display = 'none';
             //   var headerFields = new Array("SNo","CreatedById","Activity Count","Average Count","ContactNo");	
                var headerFields = new Array("SNo","CreatedById","Activity Count","Average Count");	
                var getResponseData;
                getResponseData = req.responseText;
                temp = new Array();
                temp = getResponseData.split('addto');
                if(req.responseText!=''){
                    document.getElementById("totalActivityRec").innerHTML = temp[1];
                    ParseAndGenerateHTML(myHTMLTable,temp[0], headerFields);
                    document.getElementById(("footer"+myHTMLTable.id)).innerHTML = "Total Activities Found:  "+temp[1];
                    
                }else{
                    
                    alert('No Result For This Search...');
                    spnFast.innerHTML="No Result For This Search...";                
                }
                
            } else {
                alert('No Result For This Search...');
                spnFast.innerHTML="No Result For This Search...";   
            }
        }else {
            document.getElementById("loadActivityMessage").style.display = 'block';
        } 
    }
    document.getElementById("rows").value=temp[1];
}

function getActivityList() {
    var activityStaDate=document.getElementById("dashBoardStartDate").value;
    var activityEndDate=document.getElementById("dashBoardEndDate").value;
    var checkResult = compareDates(activityStaDate,activityEndDate);
if(!checkResult) {
    return false;
}
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlers(req);  
    var url = CONTENXT_PATH+"/getActivityDashBoard.action?activityStaDate="+activityStaDate+"&activityEndDate="+activityEndDate+"&dummy="+new Date().getTime();
    
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
        
}
