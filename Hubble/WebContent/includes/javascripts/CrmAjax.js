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


/*Methods for getting Territories by Region*/

function getTerritoryData() {
    
    var region = document.getElementById("region").value;
     
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateTerritories);
    var url = CONTENXT_PATH+"/ajaxHandle/getTerritoryData.action?from=accounts&region="+region;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);

}

function populateTerritories(resXML) {
  
    var  territoryId = document.getElementById("territory");   
       
    var region = resXML.getElementsByTagName("REGION")[0];
    
    var territories = region.getElementsByTagName("TERRITORY");
 
    territoryId.innerHTML="";
    for (var i=0;i<territories.length;i++) {
    var territoryName = territories[i];
    var name = territoryName.firstChild.nodeValue;        
    var opt = document.createElement("option");          
    opt.setAttribute("value",name);
    opt.appendChild(document.createTextNode(name));
    territoryId.appendChild(opt);
    }
}

