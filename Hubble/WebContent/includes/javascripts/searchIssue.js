//new methods for getting projectNames List

var isCustomerExist = true;

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
function findPosition( oElement ) {
    if( typeof( oElement.offsetParent ) != undefined ) {
        for( var posX = 0, posY = 0; oElement; oElement = oElement.offsetParent ) {
            posX += oElement.offsetLeft;
            posY += oElement.offsetTop;
        }
        return posX+","+posY;
    } else {
        return oElement.x+","+oElement.y;
    }
}
function hideScrollBar() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
}
var isIE;
function init1() {
    //alert("rk")
    //getOtherDivs();
    
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

function clearTable() {
    if (completeTable) {
        completeTable.setAttribute("bordercolor", "white");
        completeTable.setAttribute("border", "0");
        completeTable.style.visible = false;
        for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
}




/*START: Methods for getting Projects Names  data*/

function getProjectNamesList(accId){

   // var deptName = document.employeeForm.departmentId.value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateProjectNamesList);
    var url = CONTENXT_PATH+"/getProjectList.action?accId="+accId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}

function populateProjectNamesList(resXML) {
    var projectName1 = document.frmSearch.projectName;
    var customer = resXML.getElementsByTagName("CUSTOMER")[0];
    var projectNameList = customer.getElementsByTagName("PROJECT");
    projectName1.innerHTML=" ";

    for(var i=0;i<projectNameList.length;i++) {
        var projects = projectNameList[i];
        
        var att = projects.getAttribute("projectId");
        var name = projects.firstChild.nodeValue;
        
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        projectName1.appendChild(opt);
    }


}



/*END: Methods for getting reports to person data*/


/*For fill Employee Details*/
//new
function fillEmployee(assignedToType) {
var test='';
    if(assignedToType=='pre') {
    var test=document.getElementById("assignedToUID").value;  
    }else {
    test=document.getElementById("postAssignedToUID").value;  
    }      
    if (test == "") {
    
        clearTable();
        hideScrollBar();
         if(assignedToType=='pre') {
        var validationMessage=document.getElementById("assignEmpValidationMessage");
        validationMessage.innerHTML = "";
        document.frmSearch.preAssignEmpId.value="";
        }else {
                var validationMessage=document.getElementById("postAssignEmpValidationMessage");
                validationMessage.innerHTML = "";
                document.frmSearch.postAssignEmpId.value="";
            }
    } else {
        if (test.length >2) {
            var url = CONTENXT_PATH+"/getEmployeeDetails.action?customerName="+ escape(test);         
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {                        
                        parseEmpMessages(req.responseXML,assignedToType);                        
                    } else if (req.status == 204){
                        clearTable();
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }
}



//new
var isPriEmpExist = false;
var isSecEmpExist = false;
function parseEmpMessages(responseXML,assignedToType) {
    clearTable();
    var employees = responseXML.getElementsByTagName("EMPLOYEES")[0];
    if (employees.childNodes.length > 0) {        
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");        
    } else {
        clearTable();
    }
    if(employees.childNodes.length<10) {
        autorow1.style.overflowY = "hidden";
        autorow.style.overflowY = "hidden";        
    }
    else {    
        autorow1.style.overflowY = "scroll";
        autorow.style.overflowY = "scroll";
    }
    
    var employee = employees.childNodes[0];
    var chk=employee.getElementsByTagName("VALID")[0];
    if(chk.childNodes[0].nodeValue =="true") {
        //var validationMessage=document.getElementById("validationMessage");
        var validationMessage;
        if(assignedToType == 'pre') {
        validationMessage=document.getElementById("assignEmpValidationMessage");
        isPriEmpExist = true;
        }else {
        validationMessage=document.getElementById("postAssignEmpValidationMessage");
        isSecEmpExist = true;
        }
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < employees.childNodes.length; loop++) {
            
            var employee = employees.childNodes[loop];
            var customerName = employee.getElementsByTagName("NAME")[0];
            var empid = employee.getElementsByTagName("EMPID")[0];
            appendEmployee(empid.childNodes[0].nodeValue,customerName.childNodes[0].nodeValue,assignedToType);
        }
        //var position = findPosition(document.getElementById("customerName"));
        var position;
        
        if(assignedToType == 'pre') {
        position = findPosition(document.getElementById("assignedToUID"));
        }else {
        position = findPosition(document.getElementById("postAssignedToUID"));
        }
        //var position = findPosition(document.getElementById("assignedToUID"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
    var validationMessage = '';
     if(assignedToType == 'pre') {
     isPriEmpExist = false;
     validationMessage=document.getElementById("assignEmpValidationMessage");
    }else {
    isSecEmpExist = false;
    validationMessage=document.getElementById("postAssignEmpValidationMessage");
    }
     //validationMessage=document.getElementById("assignEmpValidationMessage");
        //var validationMessage=document.getElementById("assignEmpValidationMessage");
        validationMessage.innerHTML = " Name  is InValid ";
    }
}


//new
function appendEmployee(empId,empName,assignedToType) {
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
    if(assignedToType=='pre'){
    linkElement.setAttribute("href", "javascript:set_emp('"+empName +"','"+ empId +"')");
    }else {
    linkElement.setAttribute("href", "javascript:set_postEmp('"+empName +"','"+ empId +"')");
    }
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}


//new
function set_emp(eName,eID){
    clearTable();
    document.frmSearch.assignedToUID.value =eName;
    document.frmSearch.preAssignEmpId.value =eID;
}
function set_postEmp(eName,eID){
    clearTable();
    document.frmSearch.postAssignedToUID.value =eName;
    document.frmSearch.postAssignEmpId.value =eID;
}



//for getting Subcatgoery

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


/*Methods for getting Practices by Department*/

function getPracticeDataV1() {
    
    var departmentName = document.getElementById("categoryId").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populatePractices);
    var url = CONTENXT_PATH+"/getEmpDepartment.action?departmentName="+departmentName;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populatePractices(resXML) {    
    
    var practiceId = document.getElementById("subCategoryId");
    var department = resXML.getElementsByTagName("DEPARTMENT")[0];
    var practices = department.getElementsByTagName("PRACTICE");
    practiceId.innerHTML=" ";
    
    for(var i=0;i<practices.length;i++) {
        var practiceName = practices[i];
        
        var name = practiceName.firstChild.nodeValue;
        var opt = document.createElement("option");
        if(i==0){
            opt.setAttribute("value","All");
        }else{
            opt.setAttribute("value",name);
        }
        opt.appendChild(document.createTextNode(name));
        practiceId.appendChild(opt);
    }
}

/*Methods closing Practices by Department*/





// Start for issue..
function fillCustomerInIssue() {
    var test=document.getElementById("customerName").value; 

//alert("test--->"+test);       
    if (test == "") {
        document.frmSearch.customerId.value="";
        clearTable();
        hideScrollBar();
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "";
        

    } else {
        if (test.length >2) {
            var url = CONTENXT_PATH+"/getCustomerDetails.action?customerName="+ escape(test);   

            //alert("URL--->"+url);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {                        
                        parseCustMessagesForissue(req.responseXML);                        
                    } else if (req.status == 204){
                        clearTable();
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }
}

function parseCustMessagesForissue(responseXML) {
    clearTable();
    var employees = responseXML.getElementsByTagName("EMPLOYEES")[0];
    if (employees.childNodes.length > 0) {        
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");        
    } else {
        clearTable();
    }
    if(employees.childNodes.length<10) {
        autorow1.style.overflowY = "hidden";
        autorow.style.overflowY = "hidden";        
    }
    else {    
        autorow1.style.overflowY = "scroll";
        autorow.style.overflowY = "scroll";
    }
    
    var employee = employees.childNodes[0];
    var chk=employee.getElementsByTagName("VALID")[0];
    if(chk.childNodes[0].nodeValue =="true") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "";
        isCustomerExist = true;
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < employees.childNodes.length; loop++) {
            
            var employee = employees.childNodes[loop];
            var customerName = employee.getElementsByTagName("NAME")[0];
            var empid = employee.getElementsByTagName("EMPID")[0];
            appendCustomerforissue(empid.childNodes[0].nodeValue,customerName.childNodes[0].nodeValue);
        }
        var position = findPosition(document.getElementById("customerName"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        isCustomerExist = false;
        validationMessage.innerHTML = " Name  is InValid ";
    }
}
function appendCustomerforissue(empId,empName) {
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

    linkElement.setAttribute("href", "javascript:set_cust_issue('"+empName +"','"+ empId +"');getProjectNamesList('"+empId+"');");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}



function set_cust_issue(eName,eID){
    clearTable();
    document.frmSearch.customerName.value =eName;
    document.frmSearch.customerId.value =eID;

}

// end issue..
