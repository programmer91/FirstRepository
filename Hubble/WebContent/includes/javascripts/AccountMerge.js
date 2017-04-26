//=========================================================================
//  This Function is responsible for generating the URL
//  based on all the various form variables and make sure that
//  you escape those Variable before appending to URL.
//  and then invoking the URL and passing the callback function
//=========================================================================
var isIE;
var completeTable;
var completeTable1;
var autorow;
var autorow1;
var autorow2;
var autorow3;

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

function init() {
    //var menu = document.getElementById("auto-row");
    //var menu1 = document.getElementById("auto-row1");
    autorow = document.getElementById("menu-popup");
    autorow.style.display ="none";
    autorow1 = document.getElementById("menu-popup");
    autorow1.style.display ="none";
    //autorow.style.top = getElementY(menu) + "px";
    //autorow1.style.top = getElementY(menu1) + "px";
    var height = document.documentElement.clientHeight - 120;
    autorow1.style.height = Math.max(height, 150);
    autorow1.style.overflowY = "scroll";
    autorow.style.height = Math.max(height, 150);
    autorow.style.overflowY = "scroll";
    completeTable = document.getElementById("completeTable");
    completeTable.setAttribute("bordercolor", "white");
    
    autorow2 = document.getElementById("menu-popup1");
    autorow2.style.display ="none";
    autorow3 = document.getElementById("menu-popup1");
    autorow3.style.display ="none";
    //autorow.style.top = getElementY(menu) + "px";
    //autorow1.style.top = getElementY(menu1) + "px";
    var height = document.documentElement.clientHeight - 120;
    autorow3.style.height = Math.max(height, 150);
    autorow3.style.overflowY = "scroll";
    autorow2.style.height = Math.max(height, 150);
    autorow2.style.overflowY = "scroll";
    completeTable1 = document.getElementById("completeTable1");
    completeTable1.setAttribute("bordercolor", "white");
}

function mergeAccount(form) {
    
    //spnFast=document.getElementById("spnFast");  
    //spnFast.innerHTML = " ";
    var accountName = escape(document.searchForm.accountName.value);
    var accountName2 = escape(document.searchForm.accountName2.value);
    if(accountName == accountName2){
        alert("ACCOUNT NAMES CANNOT BE SAME..");
        document.searchForm.accountName.value = "";
        document.searchForm.accountName2.value = "";
    }
    else{
        var x=accountName+"$"+accountName2;
        //alert("accountName--"+accountName)
        //alert("accountName2"+accountName2)
        loadXMLDoc(CONTENXT_PATH+"/mergeAccount.action?accountName="+x,CBFunc_MergeAccount);
    }
}

//=========================================================================
//  Define the CallBack Function for the Employee Search URL Response
//=========================================================================

function CBFunc_MergeAccount() {
    var responseText;
    var  myHTMLTable = document.getElementById("tblUpdate");
    var spnFast=document.getElementById("spnFast");
    /*Cleaning Rows*/
    ClrTable(myHTMLTable); 
    spnFast.innerHTML = '';
    if (req.readyState == 4) {
        if (req.status == 200) {
            var headerFields = new Array("Account Name","State ","LastModified Date");			            
            if(req.responseText!=''){                
                ParseAndGenerateHTML(myHTMLTable,req.responseText, headerFields);
            }else{
                
                spnFast.innerHTML="No Result For This Search...";                
            }
            //spnFast.innerText="No Result For This Search...";
            //alert("No Records Found..");
        }            
        
    } 
    else {
        //alert("Please enter a valid zip code:\n" + req.statusText);
    }
}    

function ClrTable(myHTMLTable) { 
    var tbl =  myHTMLTable;
    var lastRow = tbl.rows.length; 
    
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}


function enableEnter(e) {
    var keynum;
    var keychar;
    var numcheck;
    
    if(window.event) {
        keynum = e.keyCode;
    }
    else if(e.which) // Netscape/Firefox/Opera
    {
        keynum = e.which;
    }
    try{
        if(keynum==13){
            mergeAccount();
            return false;
        }
    }catch(e){
        alert("Error"+e);
    }
};


function getAccountName(element) {
  
    var nameOfAccount = element.value;
    
    //alert(nameOfAccount+nameOfAccount.length);
    if(nameOfAccount.length > 2){
        //alert("Hi Yar");
        if(element.id=="accountName"){
            //alert(element.id);
            var url = CONTENXT_PATH+"/getNameOfAccount.action?accountName="+ escape(element.value)+"&dummy="+new Date().getTime();
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
        else{
            // alert(element.id);         
            var url = CONTENXT_PATH+"/getNameOfAccount.action?accountName2="+ escape(element.value)+"&dummy="+new Date().getTime();
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        parseCustMessages1(req.responseXML);
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
    else{
     
        clearTable();
    }
}


//New Account list popup
function getAccountName3(element){

//alert("nag");
 var nameOfAccount = element.value;
    
    //alert(nameOfAccount+nameOfAccount.length);
    if(nameOfAccount.length > 2){
        //alert("Hi Yar");
        if(element.id=="accountName"){
            //alert(element.id);
            var url = CONTENXT_PATH+"/getNameOfAccountbyLoginId.action?accountName="+ escape(element.value)+"&dummy="+new Date().getTime();
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
        else{
            // alert(element.id);         
            var url = CONTENXT_PATH+"/getNameOfAccountbyLoginId.action?accountName2="+ escape(element.value)+"&dummy="+new Date().getTime();
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        parseCustMessages1(req.responseXML);
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
    else{
     
        clearTable();
    }
}

//new
//script for employee list


function getEmployeeName(element) {
  
    var nameOfEmployee = element.value;
    
    //alert(nameOfEmployee+nameOfEmployee.length);
    if(nameOfEmployee.length > 2){
        //alert("Hi Yar");
        if(element.id=="employeeName"){
            //alert(element.id);
            var url = CONTENXT_PATH+"/getNameOfEmployee.action?employeeName="+ escape(element.value)+"&dummy="+new Date().getTime();
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
        else{
            // alert(element.id);         
            var url = CONTENXT_PATH+"/getNameOfEmployee.action?employeeName2="+ escape(element.value)+"&dummy="+new Date().getTime();
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        parseCustMessages1(req.responseXML);
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
    else{
     
        clearTable();
    }
}


function clearTable() {
    //alert("In Clear Table");
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
    completeTable.setAttribute("bordercolor", "black");
    completeTable.setAttribute("border", "0");
    var cnt = responseXML.getElementsByTagName("COUNT")[0].childNodes[0];    
    for (loop = 0; loop < cnt.nodeValue; loop++) {
        var name = responseXML.getElementsByTagName("NAME")[loop].childNodes[0];
        //alert(name.nodeValue);
        appendAccount(name.nodeValue);
    }
}

function appendAccount(name) {
    
    var row;
    var nameCell;
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
    linkElement.setAttribute("href",
    "javascript:set_cust('"+name+"')");
    linkElement.appendChild(document.createTextNode(name));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
    document.getElementById("menu-popup").style.display = "block";
    autorow1.style.overflowY = "hidden";
    autorow.style.overflowY = "hidden";
    //alert(name);
}

function hideScrollBar() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
}

function set_cust(name){
    //alert(name);
    clearTable();
    document.searchForm.accountName.value =name;
}

function parseCustMessages1(responseXML) {
    clearTable();     
    completeTable1.setAttribute("bordercolor", "black");
    completeTable1.setAttribute("border", "0");
    var cnt = responseXML.getElementsByTagName("COUNT")[0].childNodes[0];    
    for (loop = 0; loop < cnt.nodeValue; loop++) {
        var name = responseXML.getElementsByTagName("NAME")[loop].childNodes[0];
        //alert(name.nodeValue);
        appendAccount1(name.nodeValue);
    }
}

function appendAccount1(name) {
    
    var row;
    var nameCell;
    if (!isIE) {
        row = completeTable1.insertRow(completeTable1.rows.length);
        nameCell = row.insertCell(0);
    } else {
        row = document.createElement("tr");
        nameCell = document.createElement("td");
        row.appendChild(nameCell);
        completeTable1.appendChild(row);
    }
    row.className = "popupRow";
    nameCell.setAttribute("bgcolor", "#3E93D4");
    var linkElement = document.createElement("a");
    linkElement.className = "popupItem";  
    linkElement.setAttribute("href",
    "javascript:set_cust1('"+name+"')");
    linkElement.appendChild(document.createTextNode(name));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
    document.getElementById("menu-popup1").style.display = "block";
    autorow3.style.overflowY = "hidden";
    autorow2.style.overflowY = "hidden";
    //alert(name);
}

function set_cust1(name){
    //alert(name);
    clearTable();
    document.searchForm.accountName2.value =name;
}

