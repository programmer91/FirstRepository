var isIE;

// creating the Object -- based on the browser used
function initRequest(url) {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    }
    else
        if (window.ActiveXObject) {
            isIE = true;
            return new ActiveXObject("Microsoft.XMLHTTP");
        }       
}

/*function clearTable() {
    completeTable = document.getElementById("completeTable");
    //alert("here---"+completeTable)
    if (completeTable) {
        completeTable.setAttribute("bordercolor", "white");
        completeTable.setAttribute("border", "5");
        completeTable.style.visible = false;
        //alert("Length is ----"+completeTable.childNodes.length)
        for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
}*/

// for account name

var completeTable;
var completeTable1;
var completeField;
var autorow;
var autorow1;
var req;

function getElementY(element){
    var targetTop = 0;
    if (element.offsetParent) {
        while (element.offsetParent) {
            targetTop += element.offsetTop;
            element = element.offsetParent;
        }
    } else if (element.y) {
        targetTop += element.y;
    }
    return targetTop;
}

function init() {
    
    var menu = document.getElementById("auto-row");
    var menu1 = document.getElementById("auto-row1");
    autorow = document.getElementById("menu-popup");
    autorow1 = document.getElementById("menu-popup");
    autorow.style.top = getElementY(menu) + "px";
    autorow1.style.top = getElementY(menu1) + "px";
    var height = document.documentElement.clientHeight - 120;
    autorow1.style.height = Math.max(height, 150);
    autorow1.style.overflowY = "scroll";
    autorow.style.height = Math.max(height, 150);
    autorow.style.overflowY = "scroll";
    completeTable = document.getElementById("completeTable");
    completeTable1 = document.getElementById("completeTable1");
    completeTable.setAttribute("bordercolor", "white");
}

// Make A Request -- U tell XMLHttpRequest object what sort of 
// HTTP request u want to make and which URL u want to request

function fillAccount() {
    
    var test=document.getElementById('accName').value;    
    //alert(test);
    if (test == "") {
        //   alert(" data is not");
        clearTable();
    } else {
        //alert("")
        if (test.length >2) {       
            var url = CONTENXT_PATH+"/getMarketAccount.action?accountName="+escape(test);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        parseCustMessages(req.responseXML);
                    } 
                    else if (req.status == 204){
                        clearTable();
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }
}

function clearTable() {
    completeTable = document.getElementById("completeTable");
    completeTable1 = document.getElementById("completeTable1");
    if (completeTable) {
        completeTable.setAttribute("bordercolor", "white");
        completeTable.setAttribute("border", "0");
        completeTable.style.visible = false;
        for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
    
    if (completeTable1) {
        completeTable1.setAttribute("bordercolor", "white");
        completeTable1.setAttribute("border", "0");
        completeTable1.style.visible = false;
        for (loop = completeTable1.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable1.removeChild(completeTable1.childNodes[loop]);
        }
    }
    
    
}

function parseCustMessages(responseXML) {
    
    clearTable();    
    //alert("responseXML--"+responseXML);
    var autorow;
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'block';
    var accounts = responseXML.getElementsByTagName("ACCOUNTS")[0];
    var completeTable = document.getElementById("completeTable");
    //alert("before");
    //alert("accounts---"+accounts)
    //alert("accounts length---"+accounts.length)
    if(accounts.childNodes.length > 0) {
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");
    } else {
        clearTable();
    }
    //alert("before---1")
    if(accounts.childNodes.length<10) {         
        autorow.style.overflowY = "hidden";
    }
    else {
        autorow.style.overflowY = "scroll";
    }
    //alert("before---2")
    var account = accounts.childNodes[0];
    var chk=account.getElementsByTagName("VALID")[0];    
    if(chk.childNodes[0].nodeValue =="true") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "";        
        
        for (loop = 0; loop < accounts.childNodes.length; loop++) {
            var account = accounts.childNodes[loop];
            var accountName = account.getElementsByTagName("NAME")[0];   
            var accid = account.getElementsByTagName("ACCID")[0]; 
            appendAccount(accid.childNodes[0].nodeValue,accountName.childNodes[0].nodeValue);
        }
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "  Name  is inValid ";
    }
}

function appendAccount(accId,accName) {    
    var row;
    var nameCell;
    var completeTable = document.getElementById("completeTable");
    if (!isIE) {
        row = completeTable.insertRow(completeTable.rows.length);
        nameCell = row.insertCell(0);
    } else {
        row = document.createElement("tr");
        nameCell = document.createElement("td");
        row.appendChild(nameCell);
        completeTable.appendChild(row);
    }
    row.className = "popupRow";
    
    nameCell.setAttribute("bgcolor", "#3E93D4");
    
    var linkElement = document.createElement("a");
    linkElement.className = "popupItem";
    linkElement.setAttribute("href", "javascript:set_acct('"+accName +"','"+ accId +"')");
    linkElement.appendChild(document.createTextNode(accName));
    linkElement["onclick"] = new Function("hideScrollBar(),getTeam("+accId+")");
    nameCell.appendChild(linkElement);  
}


function hideScrollBar() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
}

function set_acct(accName,accId){
    clearTable();
    document.getElementById("accName").value =accName;
    document.getElementById("accountId").value =accId;
    document.getElementById('getAccountId').value = accId;    
}

function fillContact(){
    //alert('hai');
    var accountId = document.getElementById('getAccountId').value;
    //alert('contact accid >> '+accountId);
    
    if (accountId >2) {   
        //alert('if');
        var url = CONTENXT_PATH+"/getMarketContact.action?accountId="+accountId;
        //var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=ScreenContact&accountId="+accountId;
        //alert(url);
        var req = initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4) {
                if (req.status == 200) {
                    parseCustomMessages(req.responseXML);
                } 
                
                else if (req.status == 204){
                    clearTable();
                }
            }
        };
        req.open("GET", url, true);
        req.send(null);
    } //if
}


function parseCustomMessages(responseXML) {
    
    clearTable(); 
    var autorow1;
    autorow1 = document.getElementById("menu-popup1");  
    autorow1.style.display ="block";
    var contacts = responseXML.getElementsByTagName("CONTACTS")[0];    
    var completeTable = document.getElementById("completeTable1");
    
    
    if(contacts.childNodes.length > 0) {
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");
    } else {
        clearTable();
    }
    
    if(contacts.childNodes.length<10) {         
        autorow.style.overflowY = "hidden";
    }
    else {
        autorow.style.overflowY = "scroll";
    }
    
    var contact = contacts.childNodes[0];    
    var chk=contact.getElementsByTagName("VALID")[0];    
    
    if(chk.childNodes[0].nodeValue =="true") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "";        
        
        for (loop = 0; loop < contacts.childNodes.length; loop++) {
            var contact = contacts.childNodes[loop];
            var contactName = contact.getElementsByTagName("NAME")[0];   
            var contactid = contact.getElementsByTagName("CONTID")[0]; 
            
            //alert('contact id is>> '+contactid.childNodes[0].nodeValue);
            //alert('contact name is>> '+contactName.childNodes[0].nodeValue);
            appendContact(contactid.childNodes[0].nodeValue,contactName.childNodes[0].nodeValue);
        }
    } //if
    
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "  Name  is inValid ";
    }
}

function appendContact(contId,contName) {    
    var row;
    var nameCell;
    var completeTable = document.getElementById("completeTable1");
    
    //alert('complete table length >> '+completeTable.rows.length);
    
    if (!isIE) {
        //alert("ie");
        row = completeTable.insertRow(completeTable.rows.length);
        nameCell = row.insertCell(0);
    } else {
        //alert("not ie");
        row = document.createElement("tr");
        nameCell = document.createElement("td");
        row.appendChild(nameCell);
        completeTable.appendChild(row);
    }
    row.className = "popupRow";
    nameCell.setAttribute("bgcolor", "#3E93D4");
    
    var linkElement = document.createElement("a");
    linkElement.className = "popupItem";
    
    linkElement.setAttribute("href", "javascript:set_custacct('"+contId +"','"+ contName +"')");
    linkElement.appendChild(document.createTextNode(contName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
    
}

function set_custacct(contId,contName){
    clearTable();
    //alert('contName isis>> '+contName);
    document.getElementById("contactName").value =contName;
    document.getElementById("contactsId").value =contId;
}