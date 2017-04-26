/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var isIE;
var completeTable;
var autorow;
var autorow1;
var autorow2;
var autorow3;
var completeField;
var completeTable1;

function init() {
    //var menu = document.getElementById("auto-row");
    //var menu1 = document.getElementById("auto-row1");
    autorow = document.getElementById("menu-popup");
    autorow.style.display ="none";
   
    
    //autorow.style.top = getElementY(menu) + "px";
    //autorow1.style.top = getElementY(menu1) + "px";
    var height = document.documentElement.clientHeight - 120;
    // autorow1.style.height = Math.max(height, 150);
    //    autorow1.style.height = "auto";
    //    autorow1.style.overflowY = "scroll";
    //    autorow.style.height = "auto";
    //    autorow.style.overflowY = "scroll";
    
    completeTable = document.getElementById("completeTable");
    completeTable.setAttribute("bordercolor", "white");
   
}
function initForTefSearch() {
    //var menu = document.getElementById("auto-row");
    //var menu1 = document.getElementById("auto-row1");
  
    autorow1 = document.getElementById("menu-popup1");
    autorow1.style.display ="none";
    
    //autorow.style.top = getElementY(menu) + "px";
    //autorow1.style.top = getElementY(menu1) + "px";
    var height = document.documentElement.clientHeight - 120;
    // autorow1.style.height = Math.max(height, 150);
    //    autorow1.style.height = "auto";
    //    autorow1.style.overflowY = "scroll";
    //    autorow.style.height = "auto";
    //    autorow.style.overflowY = "scroll";
    
    completeTable1 = document.getElementById("completeTable1");
    completeTable1.setAttribute("bordercolor", "white");
   
}
function initWage() {
    //var menu = document.getElementById("auto-row");
    //var menu1 = document.getElementById("auto-row1");
  
    autorow1 = document.getElementById("menu-popupWage");
    autorow1.style.display ="none";
    
    //autorow.style.top = getElementY(menu) + "px";
    //autorow1.style.top = getElementY(menu1) + "px";
    var height = document.documentElement.clientHeight - 120;
    // autorow1.style.height = Math.max(height, 150);
    //    autorow1.style.height = "auto";
    //    autorow1.style.overflowY = "scroll";
    //    autorow.style.height = "auto";
    //    autorow.style.overflowY = "scroll";
    
    completeTable2 = document.getElementById("completeTableWage");
    completeTable2.setAttribute("bordercolor", "white");
   
}
function initTef() {
    //var menu = document.getElementById("auto-row");
    //var menu1 = document.getElementById("auto-row1");
  
    autorow1 = document.getElementById("menu-popupTef");
    autorow1.style.display ="none";
    
    //autorow.style.top = getElementY(menu) + "px";
    //autorow1.style.top = getElementY(menu1) + "px";
    var height = document.documentElement.clientHeight - 120;
    // autorow1.style.height = Math.max(height, 150);
    //    autorow1.style.height = "auto";
    //    autorow1.style.overflowY = "scroll";
    //    autorow.style.height = "auto";
    //    autorow.style.overflowY = "scroll";
    
    completeTable3 = document.getElementById("completeTableTef");
    completeTable3.setAttribute("bordercolor", "white");
   
}
function initFreeze() {
    //var menu = document.getElementById("auto-row");
    //var menu1 = document.getElementById("auto-row1");
  
    autorow1 = document.getElementById("menu-popupFreeze");
    autorow1.style.display ="none";
    
    //autorow.style.top = getElementY(menu) + "px";
    //autorow1.style.top = getElementY(menu1) + "px";
    var height = document.documentElement.clientHeight - 120;
    // autorow1.style.height = Math.max(height, 150);
    //    autorow1.style.height = "auto";
    //    autorow1.style.overflowY = "scroll";
    //    autorow.style.height = "auto";
    //    autorow.style.overflowY = "scroll";
    
    completeTable4 = document.getElementById("completeTableFreeze");
    completeTable4.setAttribute("bordercolor", "white");
   
}
function initUnFreeze() {
    //var menu = document.getElementById("auto-row");
    //var menu1 = document.getElementById("auto-row1");
  
    autorow1 = document.getElementById("menu-popupUnFreeze");
    autorow1.style.display ="none";
    
    //autorow.style.top = getElementY(menu) + "px";
    //autorow1.style.top = getElementY(menu1) + "px";
    var height = document.documentElement.clientHeight - 120;
    // autorow1.style.height = Math.max(height, 150);
    //    autorow1.style.height = "auto";
    //    autorow1.style.overflowY = "scroll";
    //    autorow.style.height = "auto";
    //    autorow.style.overflowY = "scroll";
    
    completeTable5 = document.getElementById("completeTableUnFreeze");
    completeTable5.setAttribute("bordercolor", "white");
   
}
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
function getEmplyeeNames() {
    completeField = document.getElementById("employeeName");
    // alert(completeField.value)
     autorow = document.getElementById("menu-popup");
    autorow.style.display ="none";
    //alert(test);
     completeTable = document.getElementById("completeTable");
    completeTable.setAttribute("bordercolor", "white");
    if (completeField.value== "") {
         var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = " ";
        clearTableSuggestion(completeTable);
    } else {
        var test = document.getElementById("employeeName").value;
       // alert(test)
        if (test.length >2) {
            //  var url = CONTENXT_PATH+"/getConsultantList.action?email="+ escape(test);
            var url = CONTENXT_PATH+"/getEmployeeNames.action?employeeName="+ escape(test);
            //alert(url);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        parseCustMessages(req.responseXML);
                    } else if (req.status == 204){
                        clearTableSuggestion(completeTable);
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }
}

function clearTableSuggestion(completeTable) {
    if (completeTable) {
        //alert("In Clear Table");
        completeTable.setAttribute("bordercolor", "white");
        completeTable.setAttribute("border", "0");
        completeTable.style.visible = false;
        
        //document.consultantRequirementForm.email2.value ="";
       for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
}


function parseCustMessages(responseXML) {
    //alert("beforeClear");
    var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = " ";
    clearTableSuggestion(completeTable);
    //  alert(responseXML.getElementsByTagName("ACCOUNTNAMES")[0]);
  
    var accountNames = responseXML.getElementsByTagName("EMPLOYEES")[0];
    // alert("test");
    if (accountNames.childNodes.length > 0) {
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");
    } else {
        clearTableSuggestion(completeTable);
    }
    if(accountNames.childNodes.length<10) {
       
        autorow.style.overflowY = "hidden";
    //alert("in If");
    }
    else {
       
        autorow.style.overflowY = "scroll";
    //alert("in Else");
    }
    var employee = accountNames.childNodes[0];
    var chk=employee.getElementsByTagName("VALID")[0];
    //alert("Before If");
    if(chk.childNodes[0].nodeValue =="true") {
        //alert("Again In If");
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < accountNames.childNodes.length; loop++) {
            //alert("in For");
            var employee = accountNames.childNodes[loop];
//            var id = accountName.getElementsByTagName("ID")[0];
//            var accName = accountName.getElementsByTagName("ACCNAME")[0];
             var empName = employee.getElementsByTagName("NAME")[0];
            var empLoginId = employee.getElementsByTagName("EMPLOGINID")[0];
            //  alert(id.childNodes[0].nodeValue+" "+accName.childNodes[0].nodeValue);
            // alert(targetRate.childNodes[0].nodeValue);
            // appendCustomer(email.childNodes[0].nodeValue,id.childNodes[0].nodeValue,phone.childNodes[0].nodeValue,availableFrom.childNodes[0].nodeValue,isReject.childNodes[0].nodeValue,targetRate.childNodes[0].nodeValue,workauthorization.childNodes[0].nodeValue);
            appendEmployeeNames(empName.childNodes[0].nodeValue,empLoginId.childNodes[0].nodeValue)
            //appendCustomer(email.childNodes[0].nodeValue,id.childNodes[0].nodeValue);
            vmessage = 1;
        }
        
    } //if
    //alert("After IF");
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "Employee Name is INVALID ";
        vmessage = 2;
       
    }
}

function appendEmployeeNames(empName,empLoginId){
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
    linkElement.setAttribute("href","javascript:set_EmployeeName('"+empName+"','"+empLoginId+"')");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}
function set_EmployeeName(empName,empLoginId){
    
    completeTable = document.getElementById("completeTable");
    clearTableSuggestion(completeTable);
    
    document.getElementById("empLoginId").value=empLoginId;
    document.getElementById("employeeName").value=empName;
    getEmployeeNumberByLoginId(document.getElementById("empLoginId"));
}

function getEmplyeeNamesForTefSearch() {
    completeField = document.getElementById("employeeName1");
  //  alert(test);
   autorow = document.getElementById("menu-popup1");
    autorow.style.display ="none";
    //alert(test);
     completeTable = document.getElementById("completeTable1");
    completeTable.setAttribute("bordercolor", "white");
    var validationMessage=document.getElementById("validationMessage1");
        validationMessage.innerHTML = " ";
    if (completeField.value== "") {
      
        clearTableSuggestion(completeTable);
    } else {
        var test = document.getElementById("employeeName1").value;
        if (test.length >2) {
            //  var url = CONTENXT_PATH+"/getConsultantList.action?email="+ escape(test);
            var url = CONTENXT_PATH+"/getEmployeeNames.action?employeeName="+ escape(test);
            //alert(url);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        parseCustMessagesTefSearch(req.responseXML);
                    } else if (req.status == 204){
                        clearTableSuggestion(completeTable);
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }
}
//function clearTableSuggestionTefSearch() {
//    if (completeTable1) {
//        //alert("In Clear Table");
//        completeTable1.setAttribute("bordercolor", "white");
//        completeTable1.setAttribute("border", "0");
//        completeTable1.style.visible = false;
//        
//        //document.consultantRequirementForm.email2.value ="";
//        
//        
//        var validationMessage=document.getElementById("validationMessage1");
//        validationMessage.innerHTML = " ";
//        
//      
//        
//       
//        
//        for (loop = completeTable1.childNodes.length -1; loop >= 0 ; loop--) {
//            completeTable1.removeChild(completeTable1.childNodes[loop]);
//        }
//    }
//}

function parseCustMessagesTefSearch(responseXML) {
   var validationMessage=document.getElementById("validationMessage1");
        validationMessage.innerHTML = " ";
    clearTableSuggestion(completeTable);
    //  alert(responseXML.getElementsByTagName("ACCOUNTNAMES")[0]);
  
    var accountNames = responseXML.getElementsByTagName("EMPLOYEES")[0];
    // alert("test");
    if (accountNames.childNodes.length > 0) {
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");
    } else {
        
       clearTableSuggestion(completeTable);
    }
    if(accountNames.childNodes.length<10) {
       
        autorow.style.overflowY = "hidden";
    //alert("in If");
    }
    else {
       
        autorow.style.overflowY = "scroll";
    //alert("in Else");
    }
    var employee = accountNames.childNodes[0];
    var chk=employee.getElementsByTagName("VALID")[0];
    //alert("Before If");
    if(chk.childNodes[0].nodeValue =="true") {
        //alert("Again In If");
        var validationMessage=document.getElementById("validationMessage1");
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup1").style.display = "block";
        for (loop = 0; loop < accountNames.childNodes.length; loop++) {
            //alert("in For");
            var employee = accountNames.childNodes[loop];
//            var id = accountName.getElementsByTagName("ID")[0];
//            var accName = accountName.getElementsByTagName("ACCNAME")[0];
             var empName = employee.getElementsByTagName("NAME")[0];
            var empLoginId = employee.getElementsByTagName("EMPLOGINID")[0];
            //  alert(id.childNodes[0].nodeValue+" "+accName.childNodes[0].nodeValue);
            // alert(targetRate.childNodes[0].nodeValue);
            // appendCustomer(email.childNodes[0].nodeValue,id.childNodes[0].nodeValue,phone.childNodes[0].nodeValue,availableFrom.childNodes[0].nodeValue,isReject.childNodes[0].nodeValue,targetRate.childNodes[0].nodeValue,workauthorization.childNodes[0].nodeValue);
            appendEmployeeNamesforTefSearch(empName.childNodes[0].nodeValue,empLoginId.childNodes[0].nodeValue)
            //appendCustomer(email.childNodes[0].nodeValue,id.childNodes[0].nodeValue);
            vmessage = 1;
        }
        
    } //if
    //alert("After IF");
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage1");
        validationMessage.innerHTML = "Employee Name is INVALID ";
        vmessage = 2;
       
    }
}
function appendEmployeeNamesforTefSearch(empName,empLoginId){
    
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
    linkElement.setAttribute("href","javascript:set_EmployeeNameForTefSearch('"+empName+"','"+empLoginId+"')");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}
function set_EmployeeNameForTefSearch(empName,empLoginId){
   clearTableSuggestion(completeTable);
    
    document.getElementById("userId").value=empLoginId;
    document.getElementById("employeeName1").value=empName;
   
}


/*  *********************************************************************************  */

function getEmployeeNames1(){
    completeField = document.getElementById("empNameWage");
  //  alert(test);
   autorow = document.getElementById("menu-popupWage");
    autorow.style.display ="none";
    //alert(test);
     completeTable = document.getElementById("completeTableWage");
    completeTable.setAttribute("bordercolor", "white");
    var validationMessage=document.getElementById("validationMessageWage");
        validationMessage.innerHTML = " ";
    if (completeField.value== "") {
       clearTableSuggestion(completeTable);
    } else {
        var test = document.getElementById("empNameWage").value;
        if (test.length >2) {
            //  var url = CONTENXT_PATH+"/getConsultantList.action?email="+ escape(test);
            var url = CONTENXT_PATH+"/getEmployeeNames.action?employeeName="+ escape(test);
            //alert(url);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        parseEmpCustMessages(req.responseXML);
                    } else if (req.status == 204){
                        clearTableSuggestion(completeTable);
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }
}


function parseEmpCustMessages(responseXML) {
   var validationMessage=document.getElementById("validationMessageWage");
        validationMessage.innerHTML = " ";
    clearTableSuggestion(completeTable);
    //  alert(responseXML.getElementsByTagName("ACCOUNTNAMES")[0]);
  
    var accountNames = responseXML.getElementsByTagName("EMPLOYEES")[0];
    // alert("test");
    if (accountNames.childNodes.length > 0) {
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");
    } else {
        
       clearTableSuggestion(completeTable);
    }
    if(accountNames.childNodes.length<10) {
       
        autorow.style.overflowY = "hidden";
    //alert("in If");
    }
    else {
       
        autorow.style.overflowY = "scroll";
    //alert("in Else");
    }
    var employee = accountNames.childNodes[0];
    var chk=employee.getElementsByTagName("VALID")[0];
    //alert("Before If");
    if(chk.childNodes[0].nodeValue =="true") {
        //alert("Again In If");
        var validationMessage=document.getElementById("validationMessageWage");
        validationMessage.innerHTML = "";
        document.getElementById("menu-popupWage").style.display = "block";
        for (loop = 0; loop < accountNames.childNodes.length; loop++) {
            //alert("in For");
            var employee = accountNames.childNodes[loop];
//            var id = accountName.getElementsByTagName("ID")[0];
//            var accName = accountName.getElementsByTagName("ACCNAME")[0];
             var empName = employee.getElementsByTagName("NAME")[0];
            var empLoginId = employee.getElementsByTagName("EMPLOGINID")[0];
            //  alert(id.childNodes[0].nodeValue+" "+accName.childNodes[0].nodeValue);
            // alert(targetRate.childNodes[0].nodeValue);
            // appendCustomer(email.childNodes[0].nodeValue,id.childNodes[0].nodeValue,phone.childNodes[0].nodeValue,availableFrom.childNodes[0].nodeValue,isReject.childNodes[0].nodeValue,targetRate.childNodes[0].nodeValue,workauthorization.childNodes[0].nodeValue);
            appendEmployeeNamesList(empName.childNodes[0].nodeValue,empLoginId.childNodes[0].nodeValue)
            //appendCustomer(email.childNodes[0].nodeValue,id.childNodes[0].nodeValue);
            vmessage = 1;
        }
        
    } //if
    //alert("After IF");
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessageWage");
        validationMessage.innerHTML = "Employee Name is INVALID ";
        vmessage = 2;
       
    }
}
function appendEmployeeNamesList(empName,empLoginId){
    
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
    linkElement.setAttribute("href","javascript:setEmployeeName('"+empName+"','"+empLoginId+"')");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}
function setEmployeeName(empName,empLoginId){
   clearTableSuggestion(completeTable);
    
    document.getElementById("empNameById").value=empLoginId;
    document.getElementById("empNameWage").value=empName;
   
}
/*  *********************************************************************************  */

function getEmployeeNamesTef(){
    completeField = document.getElementById("empNameTef");
  //  alert(test);
   autorow = document.getElementById("menu-popupTef");
    autorow.style.display ="none";
    //alert(test);
     completeTable = document.getElementById("completeTableTef");
    completeTable.setAttribute("bordercolor", "white");
    var validationMessage=document.getElementById("validationMessageTef");
        validationMessage.innerHTML = " ";
    if (completeField.value== "") {
       clearTableSuggestion(completeTable);
    } else {
        var test = document.getElementById("empNameTef").value;
        if (test.length >2) {
            //  var url = CONTENXT_PATH+"/getConsultantList.action?email="+ escape(test);
            var url = CONTENXT_PATH+"/getEmployeeNames.action?employeeName="+ escape(test);
            //alert(url);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        parseEmpCustMessagesTef(req.responseXML);
                    } else if (req.status == 204){
                        clearTableSuggestion(completeTable);
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }
}


function parseEmpCustMessagesTef(responseXML) {
   var validationMessage=document.getElementById("validationMessageTef");
        validationMessage.innerHTML = " ";
    clearTableSuggestion(completeTable);
    //  alert(responseXML.getElementsByTagName("ACCOUNTNAMES")[0]);
  
    var accountNames = responseXML.getElementsByTagName("EMPLOYEES")[0];
    // alert("test");
    if (accountNames.childNodes.length > 0) {
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");
    } else {
        
       clearTableSuggestion(completeTable);
    }
    if(accountNames.childNodes.length<10) {
       
        autorow.style.overflowY = "hidden";
    //alert("in If");
    }
    else {
       
        autorow.style.overflowY = "scroll";
    //alert("in Else");
    }
    var employee = accountNames.childNodes[0];
    var chk=employee.getElementsByTagName("VALID")[0];
    //alert("Before If");
    if(chk.childNodes[0].nodeValue =="true") {
        //alert("Again In If");
        var validationMessage=document.getElementById("validationMessageTef");
        validationMessage.innerHTML = "";
        document.getElementById("menu-popupTef").style.display = "block";
        for (loop = 0; loop < accountNames.childNodes.length; loop++) {
            //alert("in For");
            var employee = accountNames.childNodes[loop];
//            var id = accountName.getElementsByTagName("ID")[0];
//            var accName = accountName.getElementsByTagName("ACCNAME")[0];
             var empName = employee.getElementsByTagName("NAME")[0];
            var empLoginId = employee.getElementsByTagName("EMPLOGINID")[0];
            //  alert(id.childNodes[0].nodeValue+" "+accName.childNodes[0].nodeValue);
            // alert(targetRate.childNodes[0].nodeValue);
            // appendCustomer(email.childNodes[0].nodeValue,id.childNodes[0].nodeValue,phone.childNodes[0].nodeValue,availableFrom.childNodes[0].nodeValue,isReject.childNodes[0].nodeValue,targetRate.childNodes[0].nodeValue,workauthorization.childNodes[0].nodeValue);
            appendEmployeeNamesListTef(empName.childNodes[0].nodeValue,empLoginId.childNodes[0].nodeValue)
            //appendCustomer(email.childNodes[0].nodeValue,id.childNodes[0].nodeValue);
            vmessage = 1;
        }
        
    } //if
    //alert("After IF");
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessageTef");
        validationMessage.innerHTML = "Employee Name is INVALID ";
        vmessage = 2;
       
    }
}
function appendEmployeeNamesListTef(empName,empLoginId){
    
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
    linkElement.setAttribute("href","javascript:setEmployeeNameTef('"+empName+"','"+empLoginId+"')");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}
function setEmployeeNameTef(empName,empLoginId){
   clearTableSuggestion(completeTable);
    
    document.getElementById("empNameByIdTef").value=empLoginId;
    document.getElementById("empNameTef").value=empName;
   
}


/*  *********************************************************************************  */

function getEmployeeNamesFreeze(){
    completeField = document.getElementById("empNameFreeze");
  //  alert(test);
   autorow = document.getElementById("menu-popupFreeze");
    autorow.style.display ="none";
    //alert(test);
     completeTable = document.getElementById("completeTableFreeze");
    completeTable.setAttribute("bordercolor", "white");
    var validationMessage=document.getElementById("validationMessageFreeze");
        validationMessage.innerHTML = " ";
    if (completeField.value== "") {
       clearTableSuggestion(completeTable);
    } else {
        var test = document.getElementById("empNameFreeze").value;
        if (test.length >2) {
            //  var url = CONTENXT_PATH+"/getConsultantList.action?email="+ escape(test);
            var url = CONTENXT_PATH+"/getEmployeeNames.action?employeeName="+ escape(test);
            //alert(url);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        parseEmpCustMessagesFreeze(req.responseXML);
                    } else if (req.status == 204){
                        clearTableSuggestion(completeTable);
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }
}


function parseEmpCustMessagesFreeze(responseXML) {
   var validationMessage=document.getElementById("validationMessageFreeze");
        validationMessage.innerHTML = " ";
    clearTableSuggestion(completeTable);
    //  alert(responseXML.getElementsByTagName("ACCOUNTNAMES")[0]);
  
    var accountNames = responseXML.getElementsByTagName("EMPLOYEES")[0];
    // alert("test");
    if (accountNames.childNodes.length > 0) {
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");
    } else {
        
       clearTableSuggestion(completeTable);
    }
    if(accountNames.childNodes.length<10) {
       
        autorow.style.overflowY = "hidden";
    //alert("in If");
    }
    else {
       
        autorow.style.overflowY = "scroll";
    //alert("in Else");
    }
    var employee = accountNames.childNodes[0];
    var chk=employee.getElementsByTagName("VALID")[0];
    //alert("Before If");
    if(chk.childNodes[0].nodeValue =="true") {
        //alert("Again In If");
        var validationMessage=document.getElementById("validationMessageFreeze");
        validationMessage.innerHTML = "";
        document.getElementById("menu-popupFreeze").style.display = "block";
        for (loop = 0; loop < accountNames.childNodes.length; loop++) {
            //alert("in For");
            var employee = accountNames.childNodes[loop];
//            var id = accountName.getElementsByTagName("ID")[0];
//            var accName = accountName.getElementsByTagName("ACCNAME")[0];
             var empName = employee.getElementsByTagName("NAME")[0];
            var empLoginId = employee.getElementsByTagName("EMPLOGINID")[0];
            //  alert(id.childNodes[0].nodeValue+" "+accName.childNodes[0].nodeValue);
            // alert(targetRate.childNodes[0].nodeValue);
            // appendCustomer(email.childNodes[0].nodeValue,id.childNodes[0].nodeValue,phone.childNodes[0].nodeValue,availableFrom.childNodes[0].nodeValue,isReject.childNodes[0].nodeValue,targetRate.childNodes[0].nodeValue,workauthorization.childNodes[0].nodeValue);
            appendEmployeeNamesListFreeze(empName.childNodes[0].nodeValue,empLoginId.childNodes[0].nodeValue)
            //appendCustomer(email.childNodes[0].nodeValue,id.childNodes[0].nodeValue);
            vmessage = 1;
        }
        
    } //if
    //alert("After IF");
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessageFreeze");
        validationMessage.innerHTML = "Employee Name is INVALID ";
        vmessage = 2;
       
    }
}
function appendEmployeeNamesListFreeze(empName,empLoginId){
    
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
    linkElement.setAttribute("href","javascript:setEmployeeNameFreeze('"+empName+"','"+empLoginId+"')");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}
function setEmployeeNameFreeze(empName,empLoginId){
   clearTableSuggestion(completeTable);
    
    document.getElementById("empNameByIdFreeze").value=empLoginId;
    document.getElementById("empNameFreeze").value=empName;
   
}



/*  *********************************************************************************  */

function getEmployeeNamesUnFreeze(){
    completeField = document.getElementById("empNameUnFreeze");
  //  alert(test);
   autorow = document.getElementById("menu-popupUnFreeze");
    autorow.style.display ="none";
    //alert(test);
     completeTable = document.getElementById("completeTableUnFreeze");
    completeTable.setAttribute("bordercolor", "white");
    var validationMessage=document.getElementById("validationMessageUnFreeze");
        validationMessage.innerHTML = " ";
    if (completeField.value== "") {
       clearTableSuggestion(completeTable);
    } else {
        var test = document.getElementById("empNameUnFreeze").value;
        if (test.length >2) {
            //  var url = CONTENXT_PATH+"/getConsultantList.action?email="+ escape(test);
            var url = CONTENXT_PATH+"/getEmployeeNames.action?employeeName="+ escape(test);
            //alert(url);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        parseEmpCustMessagesUnFreeze(req.responseXML);
                    } else if (req.status == 204){
                        clearTableSuggestion(completeTable);
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }
}


function parseEmpCustMessagesUnFreeze(responseXML) {
   var validationMessage=document.getElementById("validationMessageUnFreeze");
        validationMessage.innerHTML = " ";
    clearTableSuggestion(completeTable);
    //  alert(responseXML.getElementsByTagName("ACCOUNTNAMES")[0]);
  
    var accountNames = responseXML.getElementsByTagName("EMPLOYEES")[0];
    // alert("test");
    if (accountNames.childNodes.length > 0) {
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");
    } else {
        
       clearTableSuggestion(completeTable);
    }
    if(accountNames.childNodes.length<10) {
       
        autorow.style.overflowY = "hidden";
    //alert("in If");
    }
    else {
       
        autorow.style.overflowY = "scroll";
    //alert("in Else");
    }
    var employee = accountNames.childNodes[0];
    var chk=employee.getElementsByTagName("VALID")[0];
    //alert("Before If");
    if(chk.childNodes[0].nodeValue =="true") {
        //alert("Again In If");
        var validationMessage=document.getElementById("validationMessageUnFreeze");
        validationMessage.innerHTML = "";
        document.getElementById("menu-popupUnFreeze").style.display = "block";
        for (loop = 0; loop < accountNames.childNodes.length; loop++) {
            //alert("in For");
            var employee = accountNames.childNodes[loop];
//            var id = accountName.getElementsByTagName("ID")[0];
//            var accName = accountName.getElementsByTagName("ACCNAME")[0];
             var empName = employee.getElementsByTagName("NAME")[0];
            var empLoginId = employee.getElementsByTagName("EMPLOGINID")[0];
            //  alert(id.childNodes[0].nodeValue+" "+accName.childNodes[0].nodeValue);
            // alert(targetRate.childNodes[0].nodeValue);
            // appendCustomer(email.childNodes[0].nodeValue,id.childNodes[0].nodeValue,phone.childNodes[0].nodeValue,availableFrom.childNodes[0].nodeValue,isReject.childNodes[0].nodeValue,targetRate.childNodes[0].nodeValue,workauthorization.childNodes[0].nodeValue);
            appendEmployeeNamesListUnFreeze(empName.childNodes[0].nodeValue,empLoginId.childNodes[0].nodeValue)
            //appendCustomer(email.childNodes[0].nodeValue,id.childNodes[0].nodeValue);
            vmessage = 1;
        }
        
    } //if
    //alert("After IF");
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessageUnFreeze");
        validationMessage.innerHTML = "Employee Name is INVALID ";
        vmessage = 2;
       
    }
}
function appendEmployeeNamesListUnFreeze(empName,empLoginId){
    
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
    linkElement.setAttribute("href","javascript:setEmployeeNameUnFreeze('"+empName+"','"+empLoginId+"')");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}
function setEmployeeNameUnFreeze(empName,empLoginId){
   clearTableSuggestion(completeTable);
    
    document.getElementById("empNameByIdUnFreeze").value=empLoginId;
    document.getElementById("empNameUnFreeze").value=empName;
   
}
function hideScrollBar() {
    autorow = document.getElementById("menu-popup");
    autorow = document.getElementById("menu-popupUnFreeze");
    autorow = document.getElementById("menu-popupFreeze");
    autorow = document.getElementById("menu-popupTef");
    autorow = document.getElementById("menu-popupWage");
    autorow = document.getElementById("menu-popup1");
    autorow.style.display = 'none';
}
