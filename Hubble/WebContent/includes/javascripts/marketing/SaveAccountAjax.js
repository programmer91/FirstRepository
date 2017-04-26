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

function getAccountDetails() {
    //alert('hai');
    
    var accountId = document.getElementById("accountId").value;
    
    var accName = document.getElementById("accountName").value;    
    if(accName == 'undefined') {
        accName = " ";
    }
    var urlPath = document.getElementById("urlPath").value;    
    if(urlPath == 'undefined') {
        urlPath = " ";
    }
    var hPhone = document.getElementById("homePhone").value;    
    if(hPhone == 'undefined') {
        hPhone = " ";
    }
    var sSymbol = document.getElementById("stockSymbol").value;    
    if(sSymbol == 'undefined') {
        sSymbol = " ";
    }
    //var lModify = document.getElementById("lastModifyBy").value;    
    //var lMDate = document.getElementById("lastModifyDate").value;
    
    
    var gentran = document.getElementById("gentran").checked;    
    var harbinger = document.getElementById("harbinger").checked;    
    var mercator = document.getElementById("mercator").checked;    
    var seeBeyond = document.getElementById("seeBeyond").checked;    
    var webMethods = document.getElementById("webMethods").checked;    
    var wdi = document.getElementById("wdi").checked;
    
    
    var ics = document.getElementById("ics").checked;    
    var messageBroker = document.getElementById("messageBroker").checked;    
    var tibco = document.getElementById("tibco").checked;    
    var vitria = document.getElementById("vitria").checked;    
    var wps = document.getElementById("wps").checked;    
    var biztalkServer = document.getElementById("biztalkServer").checked;
    
    
    var jdEdwards = document.getElementById("jdEdwards").checked;    
    var oracleApps = document.getElementById("oracleApps").checked;    
    var peopleSoft = document.getElementById("peopleSoft").checked;    
    var sap = document.getElementById("sap").checked;    
    var siebel = document.getElementById("siebel").checked;    
    var baan = document.getElementById("baan").checked;
    
    
    var beaPortals = document.getElementById("beaPortals").checked;    
    var oraclePortals = document.getElementById("oraclePortals").checked;    
    var ibmPortals = document.getElementById("ibmPortals").checked;    
    var sharePoint = document.getElementById("sharePoint").checked;    
    var sapPortals = document.getElementById("sapPortals").checked;    
    var microsoft = document.getElementById("microsoft").checked;
    
    
    var req = newXMLHttpRequest();    
    req.onreadystatechange = readyStateHandler(req,accountDetails);    
    
    var url = CONTENXT_PATH+"/saveAccount.action?accId="+accountId+"&accountName="+accName+"&urlPath="+urlPath+"&homePhone="+hPhone+"&stockSymbol1="+sSymbol+"&gentran="+gentran+
            "&harbinger="+harbinger+"&mercator="+mercator+"&seeBeyond="+seeBeyond+"&webMethods="+webMethods+"&WDI="+wdi+"&ICS="+ics+"&messageBroker="+messageBroker+
            "&tibco="+tibco+"&vitria="+vitria+"&WPS="+wps+"&biztalkServer="+biztalkServer+"&jdEdwards="+jdEdwards+"&oracleApps="+oracleApps+"&peopleSoft="+peopleSoft+
            "&SAP="+sap+"&siebel="+siebel+"&baan="+baan+"&beaPortals="+beaPortals+"&oraclePortals="+oraclePortals+"&ibmPortals="+ibmPortals+"&sharePoint="+sharePoint+
            "&sapPortals="+sapPortals+"&microsoft="+microsoft;  
    
    //var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=allAccountDetails&accId="+accountId+"&accName="+accName+"&url="+urlPath+"&hPon="+hPhone+"&sS1="+sSymbol+"&gen="+gentran+"&harb="+harbinger+"&merc="+mercator+"&seBy="+seeBeyond+"&webM="+webMethods+"&wdi="+wdi+"&ics="+ics+"&mb="+messageBroker+"&tib="+tibco+"&vit="+vitria+"&wps="+wps+"&biz="+biztalkServer+"&jd="+jdEdwards+"&oapp="+oracleApps+"&psoft="+peopleSoft+"&sap="+sap+"&sib="+siebel+"&ban="+baan+"&bea="+beaPortals+"&oport="+oraclePortals+"&ibmP="+ibmPortals+"&shareP="+sharePoint+"&sapP="+sapPortals+"&micr="+microsoft;    
    //alert(url);
    
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function accountDetails(suggText) {
    var out = suggText;    

    // parse the input as an integer
    var intValue = parseInt(out);
    
    // if this is not an integer
    if (isNaN(intValue)) {        
        // clear text box
        alert('your account details are not saved !');
        document.getElementById("contactsId").value = 0;
        document.getElementById("contactActivId").value = 0;         
    }   
    else{
        if(intValue > 0){
            alert('your account details are saved!')
            document.getElementById("accountId").value = out;    
            document.getElementById("getAccountId").value = out;
        }
        else{
            alert('your account details not saved !');    
        }
    }
    
    //alert(out);
}
