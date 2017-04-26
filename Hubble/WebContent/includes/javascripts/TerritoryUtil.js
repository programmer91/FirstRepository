
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
 

function load()
{
alert("hi");

  // var empId = document.getElementById("emp_loginId").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, getTeritoryAndStatesList);
    var url = CONTENXT_PATH+"/getTeritoryAndStatesList.action";
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function readyStateHandler(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
               // (document.getElementById("loadingMessage")).style.display = "none";
                responseTextHandler(req.responseXML);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }/*else {
            (document.getElementById("loadingMessage")).style.display = "block";
        }*/
    }
}

function getTeritoryAndStatesList(responseXML){

//alert("responseXML--->"+responseXML);
var teritory = responseXML.getElementsByTagName("TERITORY")[0];

var Teritorrycount=teritory.getElementsByTagName("TERITORYCOUNT")[0];
//check.childNodes[0].nodeValue
alert("Teritorrycount--->"+Teritorrycount.childNodes[0].nodeValue);

var states = responseXML.getElementsByTagName("STATES")[0];

var statecount=states.getElementsByTagName("STATECOUNT")[0];
alert("statecount--->"+statecount.childNodes[0].nodeValue);



}