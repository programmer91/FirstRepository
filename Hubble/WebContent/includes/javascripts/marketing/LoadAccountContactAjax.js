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

/*Methods for getting Account Details by Account Name*/

function getAccDetailsData() {
    
    if(document.getElementById('accountId').value > 0) {
        document.getElementById('getAccountId').value = document.getElementById('accountId').value;
    }
    
    if(document.getElementById('addlabel1').style.display == 'none') { 
        document.getElementById('addlabel1').style.display = 'inline'; 
    }
    else {    
        document.getElementById('addlabel1').style.display = 'none'; 
    }
    
    var accName = document.getElementById("accName").value;
    //alert('acount name>>'+accName);
    
    var accName12 = document.frmAccount.accName.value;
    //alert('acount name12 >>'+accName12);
    
    var req = newXMLHttpRequest();
    
    req.onreadystatechange = readyStateHandler(req,collectAccDetails);
    
    var url = CONTENXT_PATH+"/getAccountDetails.action?accountName="+accName;
    //var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=accountDetails&accName="+accName;
    //alert(url);
    
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    //alert("Iam @ getAccDetailsData()");
    
}

function collectAccDetails(suggText) {
    var accDetails =suggText;
    
    var temp = new Array();
    temp = accDetails.split('*');
    
    var aTemp;
    aTemp= " ";
    
    if(accDetails == ''){
        document.getElementById("accountName").value = aTemp;
        document.getElementById("urlPath").value = aTemp;
        document.getElementById("homePhone").value = aTemp;
        document.getElementById("stockSymbol").value = aTemp;
        document.getElementById("lastModifyBy").value = aTemp;
        document.getElementById("lastModifyDate").value = aTemp;
    }
    
    if(temp[0] =='null') {        
        document.getElementById("accountName").value = aTemp;
    }else{
        document.getElementById("accountName").value = temp[0];
    }
    
    if(temp[1] =='null'||temp[1] =='undefined') {        
        document.getElementById("urlPath").value = aTemp;
    }else{
        document.getElementById("urlPath").value = temp[1];
    }
    
    if(temp[2] =='null'||temp[2] =='undefined') {        
        document.getElementById("homePhone").value = aTemp;
    }else{
        document.getElementById("homePhone").value = temp[2];
    }
    
    if(temp[3] =='null'||temp[3] =='undefined') {        
        document.getElementById("stockSymbol").value = aTemp;
    }else{
        document.getElementById("stockSymbol").value = temp[3];
    }
    
    if(temp[4] =='null'||temp[4] =='undefined') {        
        document.getElementById("lastModifyBy").value = aTemp;
    }else{
        document.getElementById("lastModifyBy").value = temp[4];
    }
    
    if(temp[5] =='null'||temp[5] =='undefined') {        
        document.getElementById("lastModifyDate").value = aTemp;
    }else{
        document.getElementById("lastModifyDate").value = temp[5];
    }
    
    if(temp[6] == 'true') {
        document.getElementById("gentran").checked = true;
    }
    
    if(temp[7] == 'true') {
        document.getElementById("harbinger").checked = true;
    }
    
    if(temp[8] == 'true') {
        document.getElementById("mercator").checked = true;
    }
    
    if(temp[9] == 'true') {
        document.getElementById("seeBeyond").checked = true;
    } 
    
    if(temp[10] == 'true') {
        document.getElementById("webMethods").checked = true;
    }
    
    if(temp[11] == 'true') {
        document.getElementById("wdi").checked = true;
    }
    
    if(temp[12] == 'true') {
        document.getElementById("ics").checked = true;
    }
    
    if(temp[13] == 'true') {
        document.getElementById("messageBroker").checked = true;
    }
    
    if(temp[14] == 'true') {
        document.getElementById("tibco").checked = true;
    }
    
    if(temp[15] == 'true') {
        document.getElementById("vitria").checked = true;
    }
    
    if(temp[16] == 'true') {
        document.getElementById("wps").checked = true;
    }
    
    if(temp[17] == 'true') {
        document.getElementById("biztalkServer").checked = true;
    }
    
    
    if(temp[18] == 'true') {
        document.getElementById("jdEdwards").checked = true;
    }
    
    if(temp[19] == 'true') {
        document.getElementById("oracleApps").checked = true;
    }
    
    if(temp[20] == 'true') {
        document.getElementById("peopleSoft").checked = true;
    }
    
    if(temp[21] == 'true') {
        document.getElementById("sap").checked = true;
    }    
    //alert("Iam @ collectAccDetails()");
    
    if(temp[22] == 'true') {
        document.getElementById("siebel").checked = true;
    }
    
    if(temp[23] == 'true') {
        document.getElementById("baan").checked = true;
    }
    
    if(temp[24] == 'true') {
        document.getElementById("beaPortals").checked = true;
    }
    
    if(temp[25] == 'true') {
        document.getElementById("oraclePortals").checked = true;
    }
    
    if(temp[26] == 'true') {
        document.getElementById("ibmPortals").checked = true;
    }
    
    if(temp[27] == 'true') {
        document.getElementById("sharePoint").checked = true;
    }
    
    if(temp[28] == 'true') {
        document.getElementById("sapPortals").checked = true;
    }
    
    if(temp[29] == 'true') {
        document.getElementById("microsoft").checked = true;
    }
    
}

/*Methods for getting Contact Details by Account Id*/

function getContDetailsData() {
    
    var acid = document.getElementById('getAccountId').value;
    //alert(acid);
    
    var conid = document.getElementById('contactsId').value;
    //alert(conid);
    
    //document.getElementById('contactActivId').value = conid;
    //document.getElementById('accountActivId').value = acid;
    
    document.frmDBGrid.contactActivId.value = conid;
    document.frmDBGrid.accountActivId.value = acid;
    
    if(document.getElementById('addlabel2').style.display == 'none') { 
        document.getElementById('addlabel2').style.display = 'block'; 
    }
    else {    
        document.getElementById('addlabel2').style.display = 'none'; 
    }
    
    var contactName = document.getElementById("contactName").value;
    //alert('contact name>>'+contactName);
    
    var contactsId = document.getElementById("contactsId").value;
    //alert('contactsId id is>>'+contactsId);
    
    var req = newXMLHttpRequest();
    
    req.onreadystatechange = readyStateHandler(req,collectContactDetails);

    var url = CONTENXT_PATH+"/getContactDetails.action?contactId="+contactsId;
    //var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=contactDetails&contactId="+contactsId;
    //var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=contactdetails&contactName="+contactName+"&contactId=+contactsId;
    //alert(url);
    
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function collectContactDetails(suggText) {
    var contactDetails =suggText;    

    var temp = new Array();
    temp = contactDetails.split('*');
    //alert(temp);
    
    var aTemp;
    
    if(temp[0] =='null') {
        aTemp= " ";
        document.getElementById("firstNames").value = aTemp;
    }else{
        document.getElementById("firstNames").value = temp[0];
    }
    
    if(temp[1] =='null') {
        aTemp= " ";
        document.getElementById("lastNames").value = aTemp;
    }else{
        document.getElementById("lastNames").value = temp[1];
    }
    
    if(temp[2] =='null') {
        aTemp= " ";
        document.getElementById("middleName").value = aTemp;
    }else{
        document.getElementById("middleName").value = temp[2];
    }
    
    if(temp[3] =='null') {
        aTemp= " ";
        document.getElementById("emails").value = aTemp;
    }else{
        document.getElementById("emails").value = temp[3];
    }
    
    if(temp[4] =='null') {
        aTemp= " ";
        document.getElementById("homePhoneNo").value = aTemp;
    }else{
        document.getElementById("homePhoneNo").value = temp[4];
    }
    
    if(temp[5] =='null') {
        aTemp= " ";
        document.getElementById("source").value = aTemp;  
    }else{
        document.getElementById("source").value = temp[5];  
    }
    
}

