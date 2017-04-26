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
var isIE;
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

/*function init1(cl) {
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

    if(cl==0){
        checkedAlert();
    }else {
       startAndEndOfWeek(cl);
    }
}*/
function init1() {
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


function init2() {
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

/** To set start date and end date of a week based on current date in Task search page ...**/

function startAndEndOfWeek(sampleValue) {

  if(sampleValue == null || sampleValue=="null")
  {

 var today = new Date();
var endDay   = new Date();
var day = today.getDay() || 7; // Get current day number, converting Sun. to 7
if( day !== 1 ) {               // Only manipulate the date if it isn't Mon.
    today.setHours(-24 * (day - 1));
endDay.setHours(-24*(day -7));


} // Set the hours to day number minus 1
var cDate = today.getDate();
var cMonth = today.getMonth()+1;
var cYear = today.getFullYear();

var sDate = endDay.getDate();
var sMonth = endDay.getMonth()+1;
var sYear = endDay.getFullYear();

var datefrom =cMonth+"/"+cDate+"/"+cYear;
var dateto = sMonth+"/"+sDate+"/"+sYear;

document.getElementById("startDate").value = datefrom;
document.getElementById("endDate").value = dateto;

}
}

/*
function initRequest(url) {
    if (window.XMLHttpRequest) {

        return new XMLHttpRequest();
    }
    else
        if (window.ActiveXObject) {

            isIE = true;
            return new ActiveXObject("Microsoft.XMLHTTP");
        }

} */

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
    var projectName1 = document.issuesForm.projectName;
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
var projectID = document.getElementById("projectId").value;

    if(assignedToType=='pre') {
    var test=document.getElementById("assignedToUID").value;
    }else {
    test=document.getElementById("postAssignedToUID").value;
    }
    if (test == "") {
if(assignedToType=='pre') {
   document.getElementById("preAssignEmpId").value='';
    }else {
  document.getElementById("postAssignEmpId").value='';
    }
        clearTable();
        hideScrollBar();
         if(assignedToType=='pre') {
        var validationMessage=document.getElementById("assignEmpValidationMessage");
        validationMessage.innerHTML = "";
        document.issuesForm.preAssignEmpId.value="";
        }else {
                var validationMessage=document.getElementById("postAssignEmpValidationMessage");
                validationMessage.innerHTML = "";
                document.issuesForm.postAssignEmpId.value="";
            }
    } else {
        if (test.length >2) {
            
            var url = "";
             if(projectID=="-1" || projectID=="") {
            //    alert("");
             url = CONTENXT_PATH+"/getEmployeeDetails.action?customerName="+ escape(test);
            }else {
             //   alert("in else");
               url = CONTENXT_PATH+"/getEmployeeDetailsByProjectId.action?customerName="+ escape(test)+"&projectId="+projectID;
            }
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

          if(assignedToType == 'pre')
        {
           document.getElementById("assignedToUID").value = "";
           document.getElementById("preAssignEmpId").value = "";

        }
        else
        {
            document.getElementById("postAssignedToUID").value = "";
            document.getElementById("postAssignEmpId").value = "";

        }
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
/*function set_emp(eName,eID){
  
    clearTable();
  
document.getElementById('assignedToUID').value = eName;
document.getElementById('preAssignEmpId').value = eID;

    fillWorkPhone(eID);
}*/
 function set_emp(eName,eID){
    clearTable();
    document.getElementById('assignedToUID').value = eName;
    document.getElementById('preAssignEmpId').value = eID;
    getEmployeeDeatilsBasedOnStatus(eName,eID)
}
function set_postEmp(eName,eID){
     //alert("1234");
    clearTable();
    //document.issuesForm.postAssignedToUID.value =eName;
   // document.issuesForm.postAssignEmpId.value =eID;
document.getElementById('postAssignedToUID').value = eName;
document.getElementById('postAssignEmpId').value = eID;
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
         document.getElementById("projectName").value = "";
        document.getElementById("projectId").value = "";
        //document.issuesForm.customerId.value="";
        document.getElementById("customerId").value = "";
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

    linkElement.setAttribute("href", "javascript:set_cust_issue('"+empName +"','"+ empId +"');getProjectNamesList('"+empId+"');");
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
        getProjectsForAccountId();
        
}

// end issue..

/** New Methods for customer projects  **/


function fillProject() {
//alert("hello");
   // var accName=document.getElementById("customerName").value;
    var accId=document.getElementById("customerId").value;
    var projectName=document.getElementById("projectName").value;
if(accId == "" || accId == null)
{
    alert("Enter Customer Name First .");
    document.getElementById("projectName").value= "";
    document.getElementById("customerName").focus();


        clearTable();
        hideScrollBar();
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "";
}else
{
     if (projectName == "") {
//alert("in if");
        document.getElementById("projectName").value="";
document.getElementById("projectId").value="";
        clearTable();
        hideScrollBar();
        var validationMessage=document.getElementById("ProjectvalidationMessage");
        validationMessage.innerHTML = "";


    } else {
        if (projectName.length >2) {
           // alert("in else if");
           // var url = CONTENXT_PATH+"/getCustomerDetails.action?customerName="+ escape(test);
            var url = CONTENXT_PATH+"/getProjectList.action?accId="+accId+"&projectName="+projectName;
            //alert("URL--->"+url);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
parseProjectMessagesForissue(req.responseXML);
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
//alert("---- accid--->"+accId+"---project name--->"+projectName);
}



//--===============================================================================================================

function parseProjectMessagesForissue(responseXML) {
//alert("in responseXml-->"+responseXML.toString());
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
        var validationMessage=document.getElementById("ProjectvalidationMessage");
        validationMessage.innerHTML = "";
        isCustomerExist = true;
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < employees.childNodes.length; loop++) {

            var employee = employees.childNodes[loop];
            var projectName = employee.getElementsByTagName("NAME")[0];
            var empid = employee.getElementsByTagName("EMPID")[0];
appendProjectforissue(empid.childNodes[0].nodeValue,projectName.childNodes[0].nodeValue);
        }
        var position = findPosition(document.getElementById("projectName"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("ProjectvalidationMessage");
        isCustomerExist = false;
        document.getElementById("projectName").value = "";
        document.getElementById("projectId").value = "";
        validationMessage.innerHTML = " Name  is InValid ";
    }
}
function appendProjectforissue(empId,empName) {
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

   // linkElement.setAttribute("href", "javascript:set_cust_issue('"+empName +"','"+ empId +"')");
    linkElement.setAttribute("href", "javascript:set_Project_issue('"+empName +"','"+ empId +"')");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}



function set_Project_issue(eName,eID){
    clearTable();
        document.getElementById("projectName").value = eName;
        document.getElementById("projectId").value = eID;
}

// end of projects
//new by Ajay Tummapala for Project team
/*
function fillEmployeeForProject() {

    var test=document.getElementById("assignedToUID").value;

    var empType = document.getElementById("resType").value;
     var projectId = document.getElementById("projectId").value;
    var accountId = document.getElementById("accountId").value;
    if (test == "") {

        clearTable();
        hideScrollBar();
        var validationMessage=document.getElementById("assignEmpValidationMessage");
        validationMessage.innerHTML = "";
        document.issuesForm.preAssignEmpId.value="";

    } else {
        if (test.length >2) {
            //alert("CONTENXT_PATH-- >"+CONTENXT_PATH)
             var url = CONTENXT_PATH+"/getEmployeeDetailsForProject.action?customerName="+escape(test)+"&empType="+empType+"&projectId="+projectId+"&accountId="+accountId;
            // alert("url-->"+url);
            var req = initRequest(url);
            req.onreadystatechange = function() {
            //    alert("req-->"+req);
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        alert(req.responseXML);
                    
parseEmpMessagesForProject(req.responseXML);
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


var isPriEmpExist = false;
var isSecEmpExist = false;
function parseEmpMessagesForProject(responseXML) {
    //alert("in responseXml-->"+responseXML);
    clearTable();
   // alert("in responseXml-->"+responseXML.toString());
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

validationMessage=document.getElementById("assignEmpValidationMessage");
        isPriEmpExist = true;

        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < employees.childNodes.length; loop++) {

            var employee = employees.childNodes[loop];
            var customerName = employee.getElementsByTagName("NAME")[0];
            var empid = employee.getElementsByTagName("EMPID")[0];
appendEmployeeForProject(empid.childNodes[0].nodeValue,customerName.childNodes[0].nodeValue);
        }
        //var position = findPosition(document.getElementById("customerName"));
        var position;


        position = findPosition(document.getElementById("assignedToUID"));

        //var position = findPosition(document.getElementById("assignedToUID"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
    var validationMessage = '';

     isPriEmpExist = false;
//validationMessage=document.getElementById("assignEmpValidationMessage");
     //validationMessage.innerHTML = " Name  is InValid ";
document.getElementById('assignEmpValidationMessage').innerHTML=" Name  is InValid ";
        var oldHTML = document.getElementById('assignEmpValidationMessage').innerHTML;
    var newHTML = "<span class='fieldLabel'>" + oldHTML + "</span>";
    document.getElementById('assignEmpValidationMessage').innerHTML = newfHTML;


           document.getElementById("assignedToUID").value = "";
           document.getElementById("preAssignEmpId").value = "";


    }
    //alert(validationMessage);
}
*/
function appendEmployeeForProject(empId,empName) {
    //alert("");
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
   // if(assignedToType=='pre'){
    linkElement.setAttribute("href", "javascript:set_emp('"+empName +"','"+ empId +"')");

    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
    //fillWorkPhone(empId);
}


function isNumber(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode!=46 && charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    
    return true;
}

function checkValue() {
var s=document.getElementById("utilisation").value;
  // use a regular expression to test the value is only digits
  var re = /^\d+$/;

  if (re.test(s) && s>=0 && s<=100) {
    return true;

  } else {
      document.getElementById("utilisation").value="";
      alert('Percentage should be between 0 and 100');
    // do failed stuff
  } 
}

//new



function fillEmployeeForCustomerLogin(assignedToType) {
var test='';
var projectID = document.getElementById("projectId").value;
var resourceType = document.getElementById("resourceType").value;
if(resourceType == 'c'||resourceType == 'v') {
    

if(projectID== "-1"||projectID== "")
    {
        alert("please select project");
        document.getElementById("assignedToUID").value="";
        document.getElementById("postAssignedToUID").value="";
        return false;
    }else {
        fillEmployee(assignedToType);
    }
    }
    
  
    
}

  function fillWorkPhone(empId)
    {
       // alert("");
        document.getElementById("workPhone").value="";
        document.getElementById("mobilePhone").value="";
   var test=empId;
    var empType = document.getElementById("resType").value;
     var projectId = document.getElementById("projectId").value;
    var accountId = document.getElementById("accountId").value;
    //alert("test-->"+test+" empType-->"+empType+" projectId-->"+projectId+" accountId-->"+accountId);
     var url = CONTENXT_PATH+"/getEmployeePhoneNumber.action?customerName="+escape(test)+"&empType="+empType+"&projectId="+projectId+"&accountId="+accountId;
            // alert("url-->"+url);
            var req = initRequest(url);
            req.onreadystatechange = function() {
            //    alert("req-->"+req);
                if (req.readyState == 4) {
                    if (req.status == 200) {
parseEmpPhoneNumbersProject(req.responseXML);
                    } else if (req.status == 204){
                        clearTable();
                    }
                }
            };
            req.open("GET", url, true);

            req.send(null);
        
    }
    
    function parseEmpPhoneNumbersProject(responseXML) {
//alert("in responseXml-->"+responseXML.toString());
    clearTable();
    var res = responseXML.getElementsByTagName("EMPLOYEE")[0];
var workPhone=res.getElementsByTagName("EMPWORKPHONE");
//alert(workPhone.length);
 for(var i=0;i<workPhone.length;i++) {
     var workNum = workPhone[i];
      var work = workNum.firstChild.nodeValue;
     // alert(name);
      document.getElementById("workPhone").value=work;
    
}


var cellphn=res.getElementsByTagName("EMPCELLPHONE");
//alert(cellphn.length);
 for(var i=0;i<cellphn.length;i++) {
     var cellNum = cellphn[i];
      var cell = cellNum.firstChild.nodeValue;
     // alert(cell);
      document.getElementById("mobilePhone").value=cell;
    
}
var utilization=res.getElementsByTagName("UTILIZATION");

for(var i=0;i<utilization.length;i++) {
     var utilizationNum = utilization[i];
      var util = utilizationNum.firstChild.nodeValue;
      //alert(util);
     // document.getElementById("utilisation").value=util;
      document.getElementById("availableUtl").value = util;
    document.getElementById("availUtl").innerHTML ="(Avl.Utilization:"+util+")";
    
}


 var empEmail=res.getElementsByTagName("EMPEMAIL");

    for(var i=0;i<empEmail.length;i++) {
        var officialEmail = empEmail[i];
        var employeeEmail = officialEmail.firstChild.nodeValue;
        //alert(employeeEmail);
        // document.getElementById("utilisation").value=util;
        //   document.getElementById("displayEmail").value = employeeEmail;
        var empEmail1=employeeEmail.split("@");
        document.getElementById("displayEmpEmail").innerHTML ="("+empEmail1[0]+")";
    
    }
}


function fillEmployeeForProject() {

    var test=document.getElementById("assignedToUID").value;

    var empType = document.getElementById("resType").value;
     var projectId = document.getElementById("projectId").value;
    var accountId = document.getElementById("accountId").value;
    if (test == "") {

        clearTable();
        hideScrollBar();
        var validationMessage=document.getElementById("authorEmpValidationMessage");
        validationMessage.innerHTML = "";
        document.issuesForm.preAssignEmpId.value="";

    } else {
        if (test.length >2) {
            //alert("CONTENXT_PATH-- >"+CONTENXT_PATH)
             var url = CONTENXT_PATH+"/getEmployeeDetailsForProject.action?customerName="+escape(test)+"&empType="+empType+"&projectId="+projectId+"&accountId="+accountId;
            // alert("url-->"+url);
            var req = initRequest(url);
            req.onreadystatechange = function() {
            //    alert("req-->"+req);
                if (req.readyState == 4) {
                    if (req.status == 200) {
                       // alert(req.responseXML);
                    
parseEmpMessagesForProject(req.responseXML);
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

function parseEmpMessagesForProject(responseXML) {
   // alert("-->"+responseXML);
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
        
        validationMessage=document.getElementById("authorEmpValidationMessage");
        isPriEmpExist = true;
         
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < employees.childNodes.length; loop++) {
            
            var employee = employees.childNodes[loop];
            var customerName = employee.getElementsByTagName("NAME")[0];
            var empid = employee.getElementsByTagName("EMPID")[0];
            appendEmployeeForProject(empid.childNodes[0].nodeValue,customerName.childNodes[0].nodeValue);
        }
        //var position = findPosition(document.getElementById("customerName"));
        var position;
        
        
        position = findPosition(document.getElementById("assignedToUID"));
        
        //var position = findPosition(document.getElementById("assignedToUID"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
    var validationMessage = '';
      
     isPriEmpExist = false;
     validationMessage=document.getElementById("authorEmpValidationMessage");
    
 
        validationMessage.innerHTML = " Invalid ! Select from Suggesstion List. ";
        validationMessage.style.color = "green";
        validationMessage.style.fontSize = "12px";
       
           document.getElementById("assignedToUID").value = "";
           document.getElementById("preAssignEmpId").value = "";
            
        
    }
}

/*Newly added 09262014 New Timesheet changes
 *
 */

function readyStateHandlerText(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                //(document.getElementById("loadingMessage")).style.display = "none";
                responseTextHandler(req.responseText);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }
        /*else {
            (document.getElementById("loadingMessage")).style.display = "block";
        }*/
    }
}

function checkPrimary(obj) {
    //alert(obj.checked);
     var objectId = document.getElementById("preAssignEmpId").value;
    var projectId = document.getElementById("projectId").value;
    
   // alert(objectId+" -->"+projectId);
    
    if(obj.checked) {
      // alert("-->"+objectId+"<--");
       //alert("-->"+objectId.length);
    if(objectId.length > 0){
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, populatePrimaryResult);
    var url = CONTENXT_PATH+"/checkPrimary.action?contactId="+objectId+"&projectId="+projectId;
    req.open("GET",url,"true");
req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
        
    }else {
        obj.checked = false;
        alert("Select resource name from suggestion list!");
    }
    }

}

function populatePrimaryResult(resText) {

    if(resText != 'None') {
        document.getElementById("isPrimary").checked = false;
        alert("This resource already Primary in "+resText+"! ");
    }
}

	 
/*CurrentStatusNewChanges start	*/
        
     function getEmployeeDeatilsBasedOnStatus(empName,empId){
    var empType = document.getElementById("resType").value;
    var projectId = document.getElementById("projectId").value;
    var accountId = document.getElementById("accountId").value;
    var  preAssignEmpId= document.getElementById("preAssignEmpId").value;
    var assignedToUID=empName;
     
    var url = CONTENXT_PATH+"/getEmployeeProjectDetailsBasedOnStatus.action?empType="+empType+"&projectId="+projectId+"&accountId="+accountId+"&preAssignEmpId="+empId;
   
    var req = initRequest(url);
    req.onreadystatechange = function() {
       
        if (req.readyState == 4) {
            if (req.status == 200) {
                setEmployeeProjectDetailsBasedOnStatus(req.responseText);
            } else if (req.status == 204){
                clearTable();
            }
        }
    };
    req.open("GET", url, true);

    req.send(null);
}

function setEmployeeProjectDetailsBasedOnStatus(response){
    
      var  preAssignEmpId= document.getElementById("preAssignEmpId").value;
    
    if(response=='StatusNotActive')  {
        
        fillWorkPhone(preAssignEmpId);
    } else{
        document.getElementById("assignedToUID").value='';
       
        document.getElementById("workPhone").value='';
        document.getElementById("mobilePhone").value='';
	 
        document.getElementById("availableUtl").value = '';
        document.getElementById("availUtl").innerHTML ="";
        document.getElementById('resultMessage1').innerHTML='<font color=red size=2px>Employee already in Active status for this project</font>';  
       
    }
    

}



function getStatusOfTheEmployee(){
    var empType = document.getElementById("resType").value;
    var projectId = document.getElementById("projectId").value;
    var accountId = document.getElementById("accountId").value;
    var  preAssignEmpId= document.getElementById("preAssignEmpId").value;
     var url = CONTENXT_PATH+"/getEmployeeProjectDetailsBasedOnStatus.action?empType="+empType+"&projectId="+projectId+"&accountId="+accountId+"&preAssignEmpId="+preAssignEmpId;
     var req = initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                setStatusOfTheEmployee(req.responseText);
            } else if (req.status == 204){
                clearTable();
            }
        }
    };
    req.open("GET", url, true);

    req.send(null);
}

function setStatusOfTheEmployee(response){
      if(response=='StatusNotActive')  {
       
    } else{
       
        document.getElementById('resultMessage1').innerHTML='<font color=red size=2px>Employee already in Active status for this project</font>';  
        document.getElementById('status').value='InActive';
        return false;    
    }
    
}


function checkBillable(obj){
   
    if(obj.checked) {
        document.getElementById('isBillableTr').style.display='';  
        if(document.getElementById("billableStartDate").value==''){
            var today = new Date();
            var dd = today.getDate();
            var mm = today.getMonth()+1;//January is 0!
            var yyyy = today.getFullYear();
            if(dd<10){
                dd='0'+dd
                }
            if(mm<10){
                mm='0'+mm
                }
             var curDate=mm+'/'+dd+'/'+yyyy;
             document.getElementById("billableStartDate").value=curDate;
        }
    } else{
        document.getElementById('isBillableTr').style.display='none';  

    }
}
   
 /*CurrentStatusNewChanset_empges end	*/  
function checkProjectStatus(obj){
    if(obj.value=='Main') {
        document.getElementById('isMainTr').style.visibility='visible';  
    
    } else{
        document.getElementById('isMainTr').style.visibility='hidden';  
        document.getElementById('isBillableTr').style.display='none';  
        document.getElementById('checkAlert').checked=false;
        document.getElementById("billableStartDate").value='';
        document.getElementById("billableEndDate").value='';
    }
}
function checkEmpProjectStatus(){
    var empProjStatus=document.getElementById("empProjectStatus").value;
    if(empProjStatus=='Main'){
        document.getElementById('isMainTr').style.visibility='visible'; 
    }else{
        document.getElementById('isMainTr').style.visibility='hidden';  
        document.getElementById('isBillableTr').style.display='none';  
        document.getElementById('checkAlert').checked=false;
        document.getElementById("billableStartDate").value='';
        document.getElementById("billableEndDate").value='';
    }
  
}
	