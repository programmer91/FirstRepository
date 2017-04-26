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


/*Methods for getting Practices by  Catagory*/

function getCatagory() {
    
    var catagoryName = document.issuesForm.categoryId.value;
   
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateAssignIdS);
    var url = CONTENXT_PATH+"/ajaxHandle/catagory.action?from=empIssue&catagoryName="+catagoryName;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
   
}

function populateAssignIdS(resXML) {    
    
    var practiceId = document.issuesForm.assignedToUID;
    var catagory = resXML.getElementsByTagName("CATAGORY")[0];
    var assigIds = catagory.getElementsByTagName("ASSIGNID");
    practiceId.innerHTML=" ";
    //var tempOpt = document.createElement("option")
    //tempOpt.appendChild(document.createTextNode("---Please Select---"));
    //practiceId.appendChild(tempOpt);
   
    for(var i=0;i<assigIds.length;i++) {
        var practiceName = assigIds[i];
        var name = practiceName.firstChild.nodeValue;
        var opt = document.createElement("option");
        //var headertext=document.createTextNode("Welcome to JavaScript Kit");
        
        opt.setAttribute("value",name);
        opt.appendChild(document.createTextNode(name));
        practiceId.appendChild(opt);
    }
}