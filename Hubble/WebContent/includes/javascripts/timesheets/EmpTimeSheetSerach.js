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
function init() {
  //  alert("rk--->"+cl);
    autorow = document.getElementById("menu-popup");
    autorow.style.display = "none";
    autorow1 = document.getElementById("menu-popup");
    autorow1.style.display ="none";
    var height = document.documentElement.clientHeight - 120;
    autorow1.style.height = Math.max(height, 150);
    autorow1.style.overflowY = "scroll";
    autorow.style.height = Math.max(height, 150);
    autorow.style.overflowY = "scroll";

    completeTables = document.getElementById("completeTable");
    completeTables.setAttribute("bordercolor", "white");

  
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



// Start for issue..
function fillCustomer() {
   
    var test=document.getElementById("customerName").value;


    if (test == "") {
        //document.issuesForm.customerId.value="";
        
        document.getElementById("customerId").value = "";
        
         var projects = document.getElementById("projectId");
        // alert(projects);
         projects.innerHTML=" ";
         var opt = document.createElement("option");
        // alert("1");
         opt.setAttribute("value",-1);
        // alert("2");
         opt.appendChild(document.createTextNode("--Please Select--"));
        // alert("3");
         projects.appendChild(opt);
        
        
        
        
        
        getEmployeesByProjectId();
        clearTable();
        hideScrollBar();
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "";


    } else {
        if (test.length >2) {
            var url = CONTENXT_PATH+"/getCustomerDetails.action?customerName="+ escape(test);

            
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
  document.getElementById("customerName").value = "";
        document.getElementById("customerId").value = "";
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

   // linkElement.setAttribute("href", "javascript:set_cust_issue('"+empName +"','"+ empId +"');getProjectNamesList('"+empId+"');");
    linkElement.setAttribute("href", "javascript:set_cust_issue('"+empName +"','"+ empId +"');");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}



function set_cust_issue(eName,eID){
   
    clearTable();
   // document.issuesForm.customerName.value =eName;
   // document.issuesForm.customerId.value =eID;
        document.getElementById("customerName").value = eName;
        document.getElementById("customerId").value = eID;
        getProjectsByAccountId();
        getEmployeesByAccountId();
}



function getProjectsByAccountId(){
 
    var accountId = document.getElementById("customerId").value;
 
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateProjectsList);
    var url = CONTENXT_PATH+"/getProjectsByAccountId.action?accountId="+accountId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}


function populateProjectsList(resXML) {
  
    //var projects = document.getElementById("projectName");
    var projects = document.getElementById("projectId");
   
    
    var projectsList = resXML.getElementsByTagName("PROJECTS")[0];
   
    var users = projectsList.getElementsByTagName("USER");
    projects.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("projectId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        projects.appendChild(opt);
    }
}


var oprType;
function getEmployeesByProjectId(type) {
     var projectId = document.getElementById("projectId").value;
     oprType = type;
     if(type == 'search') {
         projectId = document.getElementById("projectId").value;
     }else {
         projectId = document.getElementById("projectId2").value;
     }
      
 
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateEmployeesList);
    var url = CONTENXT_PATH+"/getEmployeesByProjectId.action?projectId="+projectId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}



function getEmployeesByAccountId() {
     var projectId = document.getElementById("projectId").value;
     var accountId = document.getElementById("customerId").value;
 
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateEmployeesList);
    var url = CONTENXT_PATH+"/getEmployeesByAccountId.action?accountId="+accountId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}




function populateEmployeesList(resXML) {
  
    //var projects = document.getElementById("projectName");
   // var projects = document.getElementById("empnameById");
   
    var employees = document.getElementById("empnameById");
    if(oprType=='search') {
        employees = document.getElementById("empnameById");
    }else {
        employees = document.getElementById("empnameById2");
    }
    
    
    //var projectsList = resXML.getElementsByTagName("EMPLOYEES")[0];
    var employeesList = resXML.getElementsByTagName("EMPLOYEES")[0];
   
    var users = employeesList.getElementsByTagName("USER");
    employees.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("empId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        employees.appendChild(opt);
    }
    oprType = '';
}


/*For Employee Teamlead timesheet report
 * Getting Employees by projectId and reoprtsToId
 * Date : 09/02/2014
 * Author : Santosh Kola
 */



function getTeamByProjectId() {
   // alert("hii");
    var projectId = document.getElementById("projectId").value;
    
     
 
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateProjectTeamList);
    var url = CONTENXT_PATH+"/getTeamByProjectId.action?projectId="+projectId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);  
}


function populateProjectTeamList(resXML) {
  
    //var projects = document.getElementById("projectName");
   // var projects = document.getElementById("empnameById");
   
    var employees = document.getElementById("teamMemberId");
    
    
    
    //var projectsList = resXML.getElementsByTagName("EMPLOYEES")[0];
    var employeesList = resXML.getElementsByTagName("EMPLOYEES")[0];
   
    var users = employeesList.getElementsByTagName("USER");
    employees.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        //alert("123");
        var userName = users[i];
        var att = userName.getAttribute("empId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        employees.appendChild(opt);
    }
    
}



/*Java scripts for Dual reporting
 * Date : 10/28/2014
 * Author : Santosh Kola
 */
function getTeamByReportsToType(event) {
                var teamType = event.value;
               
                
               var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateReportsToTypeTeamList);
    var url = CONTENXT_PATH+"/getTeamByReportsToType.action?teamType="+teamType;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);  
            }








function populateReportsToTypeTeamList(resXML) {
  
    //var projects = document.getElementById("projectName");
   // var projects = document.getElementById("empnameById");
   var empType = document.getElementById("currentEmpType").value;
   //alert(empType);
     var employees;
  // if(empType == 'e')
    // employees = document.getElementById("empnameById"); 
   // else
        employees = document.getElementById("custnameById");
       
    
    
    //var projectsList = resXML.getElementsByTagName("EMPLOYEES")[0];
    var employeesList = resXML.getElementsByTagName("EMPLOYEES")[0];
   
    var users = employeesList.getElementsByTagName("USER");
    employees.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        //alert("123");
        var userName = users[i];
        var att = userName.getAttribute("empId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        employees.appendChild(opt);
    }
    
}



function readyStateHandlerText(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                //(document.getElementById("loadingMessage")).style.display = "none";
                responseTextHandler(req.responseText);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
            //(document.getElementById("loadingMessage")).style.display = "block";
        }
    }
}



function isDualReportsTo(projectId) {
   // alert("ProjectId-->"+projectId.value);
 document.getElementById("printTeamType").value="0";
 
 //alert( document.getElementById("printTeamType").value);
  /*  if(roleName == 'Operations'|| custRole == 8) {
       // alert("hii");
    }else {
        document.getElementById("approveButton").disabled = true;
        document.getElementById("rejectButton").disabled = true;
    var empId = document.getElementById("empID").value;
     var resourceType = document.getElementById("resourceType").value;
*/
if(projectId.value !=0 && projectId.value !=-1) {
   var req = newXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateIsDualReportsTo);
    var url=CONTENXT_PATH+"/isDualReportsTo.action?projectId="+projectId.value;
    req.open("POST",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}else {
       document.getElementById("printTeamTypeTd").style.display = 'none';
        document.getElementById("printTeamTypeListTd").style.display = 'none';
}
//}
   
}

function populateIsDualReportsTo(resText) {
    if(resText == "YES") {
        document.getElementById("printTeamTypeTd").style.display = '';
        document.getElementById("printTeamTypeListTd").style.display = '';
    }else {
         document.getElementById("printTeamTypeTd").style.display = 'none';
        document.getElementById("printTeamTypeListTd").style.display = 'none';
    }
    
    resText = "NO";
}




function getPrintTeamByReportsToType(event) {
                var teamType = event.value;
               var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populatePrintReportsToTypeTeamList);
    var url = CONTENXT_PATH+"/getTeamByReportsToType.action?teamType="+teamType;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);  
            }








function populatePrintReportsToTypeTeamList(resXML) {
  
    //var projects = document.getElementById("projectName");
   // var projects = document.getElementById("empnameById");
   var empType = document.getElementById("currentEmpType").value;
   //alert(empType);
     var employees;
/*   if(empType == 'e')
     employees = document.getElementById("custnameById");
    else
       employees = document.getElementById("empnameById"); */
   employees = document.getElementById("teamMemberId");
    
    
    //var projectsList = resXML.getElementsByTagName("EMPLOYEES")[0];
    var employeesList = resXML.getElementsByTagName("EMPLOYEES")[0];
   
    var users = employeesList.getElementsByTagName("USER");
    employees.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        //alert("123");
        var userName = users[i];
        var att = userName.getAttribute("empId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        employees.appendChild(opt);
    }
    
}


function isOprDualReportsTo(projectId) {
  //  alert("ProjectId-->"+projectId.value);
 document.getElementById("teamType").value="0";
 
 //alert( document.getElementById("printTeamType").value);
  /*  if(roleName == 'Operations'|| custRole == 8) {
       // alert("hii");
    }else {
        document.getElementById("approveButton").disabled = true;
        document.getElementById("rejectButton").disabled = true;
    var empId = document.getElementById("empID").value;
     var resourceType = document.getElementById("resourceType").value;
*/
if(projectId.value !=0 && projectId.value !=-1) {
   var req = newXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateOprIsDualReportsTo);
    var url=CONTENXT_PATH+"/isDualReportsTo.action?projectId="+projectId.value;
    req.open("POST",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}else {
    document.getElementById("isDualReportingRequired").value = false;
       document.getElementById("teamTypeListTd").style.display = 'none';
        document.getElementById("teamTypeTd").style.display = 'none';
}
//}
   
}

function populateOprIsDualReportsTo(resText) {
    if(resText == "YES") {
        
        document.getElementById("isDualReportingRequired").value = true;
        document.getElementById("teamTypeListTd").style.display = '';
        document.getElementById("teamTypeTd").style.display = '';
    }else {
        document.getElementById("isDualReportingRequired").value = false;
         document.getElementById("teamTypeListTd").style.display = 'none';
        document.getElementById("teamTypeTd").style.display = 'none';
    }
    
    resText = "NO";
}



function isSelectProject() {
    
    projectId=document.getElementById("projectId").value;
    if(projectId !=-1) {
        hideRow("operationContactSearchTr");
        document.getElementById("opsContactId").value = '-1';
        document.getElementById("locationId").value = '-1'
    
    }else {
        showRow("operationContactSearchTr");
   
    }
   
}

function hideRow(id) {
    //alert(id);
    var row = document.getElementById(id);
    row.style.display = 'none';
}
function showRow(id) {
    //  alert(id);
    var row = document.getElementById(id);
    row.style.display = '';
} 
