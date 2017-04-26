/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function isNumber(evt) {
    //    alert("this-->"+evt.value);
 
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode!=46 && charCode > 31 && (charCode < 48 || charCode > 57))
    {
        alert("Please enter numeric value");
        return false;
    }else {
        return true;
    }
}
function newXMLHttpRequest() {
    var xmlreq = false;
    if(window.XMLHttpRequest) {
        xmlreq = new XMLHttpRequest();
    }else if(window.ActiveXObject) {
        try {
            xmlreq = new ActiveXObject("MSxm12.XMLHTTP");
        }catch(e1) {
            try {
                xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
            }catch(e2) {
                xmlreq = false;
            }
        }
    }
    return xmlreq;
}


function readyStateHandler(req,responseHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("loadActMessage").style.display = 'none';
                responseHandler(req.responseText);
            }
        }
        else {
            document.getElementById("loadActMessage").style.display = 'block';
        //alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }
}


function readyStateHandlerXML(req,responseXmlHandler) {
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
var tempTitletypeId = '';
function getEmpTitleDataVForTitleEdit(titletypeId) {
    tempTitletypeId = titletypeId;
    var departmentName="";
 
    departmentName = document.getElementById('departmentId').value;
    var url ="";
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerXML(req, populateEmpTitlesForPerformanceTitleEdit);

    //url = CONTENXT_PATH+"/getEmpForTitles.action?departmentName="+departmentName+"&title="+titletypeId;
    url = CONTENXT_PATH+"/getEmpForTitles.action?departmentName="+departmentName;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populateEmpTitlesForPerformanceTitleEdit(resXML) {
    // alert("hi");
    
    var titleId = document.getElementById('titleId');
    
    var department = resXML.getElementsByTagName("DEPARTMENT")[0];
    var titles = department.getElementsByTagName("TITLE");
    var actTitlesID=department.getElementsByTagName("TITLEID");
    //alert(actTitlesID.length);
    titleId.innerHTML=" ";
    
    for(var i=0;i<titles.length;i++) {
        var titleName = titles[i];
        
        var name = titleName.firstChild.nodeValue;
        var opt = document.createElement("option");
        if(i==0){
            opt.setAttribute("value","");
        }else{
            opt.setAttribute("value",name);
        }
        opt.appendChild(document.createTextNode(name));
        titleId.appendChild(opt);
    }
    var setTitleName ="";
    for(var i=0;i<actTitlesID.length;i++) {
        var titleName = actTitlesID[i];
        //  alert(titleName);
        setTitleName = titleName.firstChild.nodeValue;
    }
    //alert(setTitleName);
    // document.getElementById('titleId').value=setTitleName;
    document.getElementById('titleId').value=tempTitletypeId;
     document.getElementById('searchTitleId').value=tempTitletypeId;
   //  alert(tempTitletypeId);
      document.getElementById('searchTitleIdForPerformance').value=tempTitletypeId;
    
    tempTitletypeId = '';
   
}

function getEmpTitleDataVForPerformanceEdit(titletypeId,deptId) {
    tempTitletypeId = titletypeId;
    var departmentName="";
 
    departmentName = deptId;
    var url ="";
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerXML(req, populateEmpTitleDataVForPerformanceEdit);

    //url = CONTENXT_PATH+"/getEmpForTitles.action?departmentName="+departmentName+"&title="+titletypeId;
    url = CONTENXT_PATH+"/getEmpForTitles.action?departmentName="+departmentName;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populateEmpTitleDataVForPerformanceEdit(resXML) {
    // alert("hi");
    
    var titleId = document.getElementById('titleId');
    
    var department = resXML.getElementsByTagName("DEPARTMENT")[0];
    var titles = department.getElementsByTagName("TITLE");
    var actTitlesID=department.getElementsByTagName("TITLEID");
    //alert(actTitlesID.length);
    titleId.innerHTML=" ";
    
    for(var i=0;i<titles.length;i++) {
        var titleName = titles[i];
        
        var name = titleName.firstChild.nodeValue;
        var opt = document.createElement("option");
        if(i==0){
            opt.setAttribute("value","");
        }else{
            opt.setAttribute("value",name);
        }
        opt.appendChild(document.createTextNode(name));
        titleId.appendChild(opt);
    }
    var setTitleName ="";
    for(var i=0;i<actTitlesID.length;i++) {
        var titleName = actTitlesID[i];
        //  alert(titleName);
        setTitleName = titleName.firstChild.nodeValue;
    }
    //alert(setTitleName);
    // document.getElementById('titleId').value=setTitleName;
   // document.getElementById('titleId').value=tempTitletypeId;
    // document.getElementById('searchTitleId').value=tempTitletypeId;
   //  alert(tempTitletypeId);
      document.getElementById('searchTitleIdForPerformance').value=tempTitletypeId;
    
    tempTitletypeId = '';
   
}

function getEmpTitleDataV1() {

    var departmentName="";
 
    departmentName = document.getElementById('departmentId').value;
 
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerXML(req, populateEmpTitles);
   
    var url = CONTENXT_PATH+"/getEmpForTitles.action?departmentName="+departmentName;

    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populateEmpTitles(resXML) {
    // alert("hi");
    
    var titleId = document.getElementById('titleId');
    
    var department = resXML.getElementsByTagName("DEPARTMENT")[0];
    var titles = department.getElementsByTagName("TITLE");
    titleId.innerHTML=" ";
    
    for(var i=0;i<titles.length;i++) {
        var titleName = titles[i];
        
        var name = titleName.firstChild.nodeValue;
        var opt = document.createElement("option");
        if(i==0){
            opt.setAttribute("value","");
        }else{
            opt.setAttribute("value",name);
        }
        opt.appendChild(document.createTextNode(name));
        titleId.appendChild(opt);
    }
}


function getEmpTitleDataV11() {
    //  alert("");
    var departmentName = document.getElementById('searchDepartmentId').value;
    // alert("-------->"+departmentName);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerXML(req, populateEmpTitles1);
    var url = CONTENXT_PATH+"/getEmpForTitles.action?departmentName="+departmentName;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populateEmpTitles1(resXML) {
    
    
    var titleId = document.getElementById('searchTitleId');
    
    var department = resXML.getElementsByTagName("DEPARTMENT")[0];
    var titles = department.getElementsByTagName("TITLE");
    titleId.innerHTML=" ";
    
    for(var i=0;i<titles.length;i++) {
        var titleName = titles[i];
        
        var name = titleName.firstChild.nodeValue;
        var opt = document.createElement("option");
        if(i==0){
            opt.setAttribute("value","");
        }else{
            opt.setAttribute("value",name);
        }
        opt.appendChild(document.createTextNode(name));
        titleId.appendChild(opt);
    }
}

function getEmpTitleDataV12() {
    //  alert("");
    var departmentName = document.getElementById('searchDepartmentForPerformance').value;
    // alert("-------->"+departmentName);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerXML(req, populateEmpTitles12);
    var url = CONTENXT_PATH+"/getEmpForTitles.action?departmentName="+departmentName;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populateEmpTitles12(resXML) {
    
    
    var titleId = document.getElementById('searchTitleIdForPerformance');
    
    var department = resXML.getElementsByTagName("DEPARTMENT")[0];
    var titles = department.getElementsByTagName("TITLE");
    titleId.innerHTML=" ";
    
    for(var i=0;i<titles.length;i++) {
        var titleName = titles[i];
        
        var name = titleName.firstChild.nodeValue;
        var opt = document.createElement("option");
        if(i==0){
            opt.setAttribute("value","");
        }else{
            opt.setAttribute("value",name);
        }
        opt.appendChild(document.createTextNode(name));
        titleId.appendChild(opt);
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
function getAllEmpNames() {
    //alert("");
    var test = document.getElementById("empName").value;
    //alert(test);
    if (test == "") {
        clearTable();
    } else {
        if (test.length >2) {
            var url = CONTENXT_PATH+"/getAllEmpNamesAlongWithTitle1.action?techName="+ escape(test);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        parseCustMessages(req.responseXML);
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

function clearTable() {
    //alert("In Clear Table");
    if (completeTable) {
        completeTable.setAttribute("bordercolor", "white");
        completeTable.setAttribute("border", "0");
        completeTable.style.visible = false;
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = " ";
        
        for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
}

function parseCustMessages(responseXML) {
    clearTable();
    var employees = responseXML.getElementsByTagName("TECHIES")[0];
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
    var consultant = employees.childNodes[0];
    var chk=consultant.getElementsByTagName("VALID")[0];
    isExist = chk.childNodes[0].nodeValue;
    if(chk.childNodes[0].nodeValue =="true") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < employees.childNodes.length; loop++) {
            var consultant = employees.childNodes[loop];
            var loginId = consultant.getElementsByTagName("LoginId")[0];
            var deptId = consultant.getElementsByTagName("DeptId")[0];
            var titleId = consultant.getElementsByTagName("TitleId")[0];
            var empName = consultant.getElementsByTagName("NAME")[0];
            var callOutBound=consultant.getElementsByTagName("CallOutBound")[0];
            var appointment=consultant.getElementsByTagName("Appointment")[0];
            var conferenceCalls=consultant.getElementsByTagName("ConferenceCalls")[0];
            var meeting=consultant.getElementsByTagName("Meeting")[0];
            var oppurtunity=consultant.getElementsByTagName("Oppurtunity")[0];
            var requirement=consultant.getElementsByTagName("Requirement")[0];
            //Nag -- appended at end of this statement
            //  alert(callOutBound+"-----"+appointment+"-----"+conferenceCalls+"-----"+meeting+"-----"+oppurtunity+"-----"+requirement);
            appendCustomer(empName.childNodes[0].nodeValue,loginId.childNodes[0].nodeValue,deptId.childNodes[0].nodeValue,titleId.childNodes[0].nodeValue
                ,callOutBound.childNodes[0].nodeValue,
                appointment.childNodes[0].nodeValue,
                conferenceCalls.childNodes[0].nodeValue,
                meeting.childNodes[0].nodeValue,
                oppurtunity.childNodes[0].nodeValue,
                requirement.childNodes[0].nodeValue
                );
        // EmplId = empid.childNodes[0].nodeValue;
        }
        var position;
        position = findPosition(document.getElementById("empName"));
        
        //var position = findPosition(document.getElementById("assignedToUID"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "  Employee doesn't Exists ";
        
      
    //document.getElementById("consultantDiv").value = "Add Consultant";
        
    }
}

function appendCustomer(empName,loginId,deptId,titleId,callOutBound,appointment,conferenceCalls,meeting,oppurtunity,requirement) {
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
        "javascript:set_cust('"+empName +"','"+ loginId +"','"+deptId+"','"+titleId+"','"+callOutBound+"','"+appointment+"','"+conferenceCalls+"','"+meeting+"','"+oppurtunity+"','"+requirement+"')");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}
function set_cust(empName,loginId,deptId,titleId,callOutBound,appointment,conferenceCalls,meeting,oppurtunity,requirement){
    clearTable();
    // alert(callOutBound+"-----"+appointment+"-----"+conferenceCalls+"-----"+meeting+"-----"+oppurtunity+"-----"+requirement);
    document.getElementById("empName").value =empName;
    document.getElementById("empNameLoginId").value =loginId;
    document.getElementById("performanceDiv").style.display="block";
    document.getElementById("departmentName").value =deptId;
    document.getElementById("empTitle").value =titleId;
    document.getElementById("callOutBound").value =callOutBound;
    document.getElementById("appointment").value =appointment;
    document.getElementById("conferenceCalls").value =conferenceCalls;
    document.getElementById("meeting").value =meeting;
    document.getElementById("oppurtunity").value =oppurtunity;
    document.getElementById("requirement").value =requirement;
          
    setDefaultOppDates();
    setMetricRange(deptId,titleId);
}

function hideScrollBar() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
}
//for search Review suggestion list

/*
function init1() {
    //var menu = document.getElementById("auto-row");
    //var menu1 = document.getElementById("auto-row1");
    autorow = document.getElementById("menu-popup1");
    autorow.style.display ="none";
    autorow1 = document.getElementById("menu-popup1");
    autorow1.style.display ="none";
    //autorow.style.top = getElementY(menu) + "px";
    //autorow1.style.top = getElementY(menu1) + "px";
    var height = document.documentElement.clientHeight - 120;
    autorow1.style.height = Math.max(height, 150);
    autorow1.style.overflowY = "scroll";
    autorow.style.height = Math.max(height, 150);
    autorow.style.overflowY = "scroll";
    completeTable1 = document.getElementById("completeTable1");
    completeTable1.setAttribute("bordercolor", "white");
}*/
function getAllEmpNames1() {
    //alert("");
    var test = document.getElementById("empNameForSearch").value;
    //alert(test);
    if (test == "") {
        clearTable();
    } else {
        if (test.length >2) {
            var url = CONTENXT_PATH+"/getAllEmpNamesAlongWithTitle.action?techName="+ escape(test);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        parseCustMessages1(req.responseXML);
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

function parseCustMessages1(responseXML) {
    clearTable1();
    var employees = responseXML.getElementsByTagName("TECHIES")[0];
    if (employees.childNodes.length > 0) {
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");
    } else {
        clearTable1();
    }
    if(employees.childNodes.length<10) {
        autorow1.style.overflowY = "hidden";
        autorow.style.overflowY = "hidden";
    }
    else {
        autorow1.style.overflowY = "scroll";
        autorow.style.overflowY = "scroll";
    }
    var consultant = employees.childNodes[0];
    var chk=consultant.getElementsByTagName("VALID")[0];
    isExist = chk.childNodes[0].nodeValue;
    if(chk.childNodes[0].nodeValue =="true") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < employees.childNodes.length; loop++) {
            var consultant = employees.childNodes[loop];
            var loginId = consultant.getElementsByTagName("LoginId")[0];
            var titleId = consultant.getElementsByTagName("TitleId")[0];
            var empName = consultant.getElementsByTagName("NAME")[0];
            
            //Nag -- appended at end of this statement
            appendCustomer1(empName.childNodes[0].nodeValue,loginId.childNodes[0].nodeValue,titleId.childNodes[0].nodeValue);
        // EmplId = empid.childNodes[0].nodeValue;
        }
        var position;
        position = findPosition(document.getElementById("empNameForSearch"));
        
        //var position = findPosition(document.getElementById("assignedToUID"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage1");
        validationMessage.innerHTML = "  Employee doesn't Exists ";
        
      
    //document.getElementById("consultantDiv").value = "Add Consultant";
        
    }
}

function clearTable1() {
    //alert("In Clear Table");
    if (completeTable) {
        completeTable.setAttribute("bordercolor", "white");
        completeTable.setAttribute("border", "0");
        completeTable.style.visible = false;
        var validationMessage=document.getElementById("validationMessage1");
        validationMessage.innerHTML = " ";
        
        for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
}
function appendCustomer1(empName,loginId,titleId) {
    
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
        "javascript:set_cust1('"+empName +"','"+ loginId +"','"+titleId+"')");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar1()");
    nameCell.appendChild(linkElement);
}
function set_cust1(empName,loginId,titleId){
    // alert("11-->"+empName);
    clearTable1();
    document.getElementById("empNameForSearch").value =empName;
    document.getElementById("empNameLoginIdForSearch").value =loginId;
  
    document.getElementById("empTitleForSearch").value =titleId;

}

function hideScrollBar1() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
}

function setDefaultOppDates() {
    //alert("ok");
    var currentYear = new Date().getFullYear();    
    var currentMonth = new Date().getMonth();    
    var currentDay = new Date().getDate();
    
    var nextMonth = "";
    var nextYear  = currentYear;
    if(currentMonth <10 ){
        currentMonth = '0'+ currentMonth;
        nextMonth = '0'+(parseInt(currentMonth)+1);
    } else if(currentMonth == 12) {
        nextMonth = '01';
        nextYear = parseInt(currentYear)+1 ;
    } else {
        nextMonth = (parseInt(currentMonth)+1);
    }
    
    // month-date-year
    var firstDayOfMonth = (currentMonth) + '/01/' + currentYear;
    var firstDayOfNextMonth = (nextMonth) + '/01/' + nextYear;
    document.getElementById("fromDate").value = firstDayOfMonth;
    document.getElementById("toDate").value = firstDayOfNextMonth;
   
}



function setDefaultOppDates1() {
    //alert("ok");
    var currentYear = new Date().getFullYear();    
    var currentMonth = new Date().getMonth();    
    var currentDay = new Date().getDate();
    
    var nextMonth = "";
    var nextYear  = currentYear;
    if(currentMonth <10 ){
        currentMonth = '0'+ currentMonth;
        nextMonth = '0'+(parseInt(currentMonth)+1);
    } else if(currentMonth == 12) {
        nextMonth = '01';
        nextYear = parseInt(currentYear)+1 ;
    } else {
        nextMonth = (parseInt(currentMonth)+1);
    }
    
    // month-date-year
    var firstDayOfMonth = (currentMonth) + '/01/' + currentYear;
    var firstDayOfNextMonth = (nextMonth) + '/01/' + nextYear;
    document.getElementById("fromDateForSearch").value = firstDayOfMonth;
    document.getElementById("toDateForSearch").value = firstDayOfNextMonth;
    getEmpTitleDataV12();
}


function getMetricRecords() {
    var metricName = document.getElementById("metricNameForSearch").value;
    var statusId = document.getElementById("statusIdForSearch").value;
    //alert(metricName+"---------"+statusId);
    var tableId = document.getElementById("tblEmpMetrics");
    ClrTable(tableId);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, displayMetrics);
    var url = CONTENXT_PATH+"/getMetricsList.action?metricName="+metricName+"&statusId="+statusId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function displayMetrics(response) {
    //alert("by rep");
    var tableId = document.getElementById("tblEmpMetrics");
    // var headerFields = new Array("S.No","Employee Name","ReportsTo","TotalAccounts","Activites","Touched Accounts","Untouched Accounts");
    // var headerFields = new Array("S.No","Employee Name","ReportsTo","TotalAccounts","Activites","Touched Accounts","Untouched Accounts","Feedback Mail");
    var headerFields = new Array("S.No","Metrics Name","Min Value","Max Value","Status","Creatde By","Description");
    var dataArray = response;
    
    //generateHeader(headerArray,tableId);
    ParseAndGenerateHTML(tableId,dataArray, headerFields);
// document.getElementById(("footer"+tableId.id)).innerHTML = "Total Value is($):  "+dataArray[2];
    
}

function ParseAndGenerateHTML(oTable,responseString,headerFields) {
    
    // alert("ParseAndGenerateHTML");
    var start = new Date();
    var fieldDelimiter = "|";
    var recordDelimiter = "^";   
    //alert('responseString%%%% ******'+responseString);
    //alert('rowCount%%%% ******'+rowCount);
    var records = responseString.split(recordDelimiter); 
    generateTable(oTable,headerFields,records,fieldDelimiter);
}

function generateTable(oTable, headerFields,records,fieldDelimiter) {	

    var tbody = oTable.childNodes[0];    
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    generateTableHeader(tbody,headerFields);
    var rowlength;
    rowlength = records.length-1;
    if(rowlength >=1 && records!=""){
        //alert("rowlength-->^"+records);
        for(var i=0;i<rowlength;i++) {
            // alert("i-->"+i);
            if(oTable.id == "tblEmpMetrics") {
                generateMetricsRow(oTable,tbody,records[i],fieldDelimiter);  
            }
            else if(oTable.id == "tblTitleMetrics") {
                generateTitleMetricsRow(oTable,tbody,records[i],fieldDelimiter);  
            }
            else if(oTable.id == "tblEmpPerformance") {
                generateReviewedPerformanceRow(oTable,tbody,records[i],fieldDelimiter);  
            }
            
            else{
                generateRow(oTable,tbody,records[i],fieldDelimiter);  
            }
        }    
    } else {
        generateNoRecords(tbody,oTable);
    }
    generateFooter(tbody,oTable);
}
function generateMetricsRow(oTable,tableBody,record,delimiter){
    var metricName = "";
    var min = "";
    var max = "";
    var status="";
    var createdBy="";
    var description=""
    var id=0;
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(delimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    //Assigning values start
    metricName = fields[1];
    min = fields[2];
    max = fields[3];
    status = fields[4];
    createdBy = fields[5];
    description = fields[6];
    id=fields[7];
    //Assigning values End
    for (var i=0;i<length-1;i++) {
        // cell = document.createElement( "TD" );
        // cell.className="gridColumn";
        if(i==0)
        {
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            cell.innerHTML = fields[i];
            cell.setAttribute('align','center');
        }
        if(i==1){
            //empLoginId = fields[i];
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            cell.setAttribute('align','center');
         
            //row.appendChild( cell );
            var j = document.createElement("a");
            
            j.setAttribute("href", "javascript:editMetricValues('"+metricName+"','"+id+"')");
            j.appendChild(document.createTextNode(metricName));
            cell.appendChild(j);
        }
        if(i>1 && i!=6) { 
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            cell.innerHTML = fields[i];
            cell.setAttribute('align','center');
        }
        else if(i==6){
            //empLoginId = fields[i];
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            cell.setAttribute('align','center');
         
            //row.appendChild( cell );
            var j = document.createElement("a");
            
            j.setAttribute("href", "javascript:getMetricComments('"+description+"')");
            j.appendChild(document.createTextNode("view"));
            cell.appendChild(j);
        }
        if(fields[i]!=''||fields[i]!=null){
            row.appendChild( cell );
        }
        
            
        
    }
    
    // Variables reset start
    metricName = "";
    min = "";
    max = "";
    status = "";
    createdBy = "";
    description = "";
// Variables reset end
                    
//Feed back Mail End
}

function generateNoRecords(tbody,oTable) {
    var noRecords =document.createElement("TR");
    noRecords.className="gridRowEven";
    tbody.appendChild(noRecords);
    cell = document.createElement("TD");
    cell.className="gridColumn";
    
    if(oTable.id == "tblEmpMetrics"){
        cell.colSpan = "7";
    }
    if(oTable.id == "tblTitleMetrics"){
        cell.colSpan = "8";
    }
    if(oTable.id == "tblEmpPerformance"){
        cell.colSpan = "6";
    }
    cell.innerHTML = "No Records Found for this Search";
    noRecords.appendChild(cell);
}

function generateFooter(tbody,oTable) {
    //alert(oTable.id);
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.id="footer"+oTable.id;
    //cell.colSpan = "5";
    if(oTable.id == "tblEmpMetrics"){
        cell.colSpan = "7";
    }
    if(oTable.id == "tblTitleMetrics"){
        cell.colSpan = "8";
    }
    if(oTable.id == "tblEmpPerformance"){
        cell.colSpan = "6";
    }
    
    footer.appendChild(cell);
}


function generateTableHeader(tableBody,headerFields) {
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridHeader";
    tableBody.appendChild( row );
    
    for (var i=0; i<headerFields.length; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridHeader";
        cell.setAttribute('align','center');
        row.appendChild( cell );
      
        cell.innerHTML = headerFields[i];
        cell.width = 120;
    }
}

function generateRow(oTable,tableBody,record,delimiter) {
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(delimiter);
    fieldLength = fields.length ;
    var length;
    if(oTable.id == "tblEmpMetrics" ){
        length = fieldLength;
    }
    else if(oTable.id == "tblTitleMetrics" ){
        length = fieldLength;
    }
    
    else {
        length = fieldLength-1;
    }
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    
    for (var i=0;i<length;i++) {
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        //alert(fields[i]+"fields[i]");
        cell.innerHTML = fields[i];
       
        if(fields[i]!=''){
            row.appendChild( cell );
        }
    }
}

function ClrTable(myHTMLTable) { 
    var tbl =  myHTMLTable;
    var lastRow = tbl.rows.length; 
    //document.getElementById('addlabel1').style.display = 'none'; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}

function getMetricComments(comments) {
    var comments = comments;
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,populateMetricComments);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/getMetricComments.action?comments="+comments;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateMetricComments(text) {
    var background = "#3E93D4";
    var title = "Comments";
    var text1 = text; 
    var size = text1.length;
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+text1+"<br />\
    </body></html>";
    
    if(size < 50){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 100){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 260){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}

function editMetricValues(name,id)
{
    
    // window.location="editMetrics.action?id="+id+"&metricName="+name;    
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,populateMetricValues);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/editMetricValues.action?metricId="+id;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populateMetricValues(res)
{
   // alert(res);
    document.getElementById("metricResultMessage").innerHTML=" ";
    var caps = new Array();
    caps = res.split("@");

    var id=caps[0];
   
    var metricName=caps[1];
    var minValue=caps[2];
    var maxValue=caps[3];
    var status=caps[4];
    var desc=caps[5];
   
   
    document.getElementById("metrics").checked=true;
    document.getElementById("titleAssoc").checked=false;
    document.getElementById("performanceReview").checked=false;
    document.getElementById("addMetrics").checked=true;
    document.getElementById("searchMetrics").checked=false;
    document.getElementById("metricsLabel").style.fontWeight = 'bold';
    document.getElementById("addMetricsLabel").innerHTML="Edit Metrics";
    document.getElementById("addMetricsLabel").style.fontWeight = 'bold';
    document.getElementById("searchMetricsLabel").style.fontWeight = 'normal';
    document.getElementById("metricSubmit").style.display='none';
    document.getElementById("searchMetricsDiv").style.display='none';
    document.getElementById("metricsDiv").style.display='block';
    document.getElementById("metricUpdate").style.display='block';
    document.getElementById("addMetricsDiv").style.display='block';

   
    document.getElementById("metricName").value=metricName;
  
    document.getElementById("minValue").value=minValue;
    document.getElementById("maxValue").value=maxValue;
    document.getElementById("id").value=id;
 
    document.getElementById("statusId").value =status;
    document.getElementById("comments").value =desc;


  
}
//update metric
function updateMetricValuesThroughAjax()
{
    
    var id = document.getElementById("id").value;
    var metricName = document.getElementById("metricName").value;
    var minValue = document.getElementById("minValue").value;
    var maxValue = document.getElementById("maxValue").value;
    var statusId = document.getElementById("statusId").value;
    var comments = document.getElementById("comments").value;
   
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,updateMetricValues);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/updateMetricValues.action?metricId="+id+"&metricName="+metricName+"&minValue="+minValue+"&maxValue="+maxValue+"&status="+statusId+"&comments="+comments;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function updateMetricValues(res){
    //  alert(res);
    document.getElementById("resM").innerHTML="";
    document.getElementById("metricResultMessage").innerHTML=res;
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
/*
function init2() {
    //var menu = document.getElementById("auto-row");
    //var menu1 = document.getElementById("auto-row1");
    autorow = document.getElementById("menu-popup2");
    autorow.style.display ="none";
    autorow1 = document.getElementById("menu-popup2");
    autorow1.style.display ="none";
    //autorow.style.top = getElementY(menu) + "px";
    //autorow1.style.top = getElementY(menu1) + "px";
    var height = document.documentElement.clientHeight - 120;
    autorow1.style.height = Math.max(height, 150);
    autorow1.style.overflowY = "scroll";
    autorow.style.height = Math.max(height, 150);
    autorow.style.overflowY = "scroll";
    completeTable = document.getElementById("completeTable2");
    completeTable.setAttribute("bordercolor", "white");
}*/

function getAllMetricNames() {
    var test = document.getElementById("metricNameForTitle").value;

    if (test == "") {
        clearTable();
    } else {
        if (test.length >2) {
            var url = CONTENXT_PATH+"/getAllMetricNames.action?metricName="+ escape(test);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        parseCustMessages2(req.responseXML);
                    } else if (req.status == 204){
                        clearTable2();
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }
}

function clearTable2() {
    if (completeTable) {
        completeTable.setAttribute("bordercolor", "white");
        completeTable.setAttribute("border", "0");
        completeTable.style.visible = false;
        var validationMessage=document.getElementById("validationMessage2");
        validationMessage.innerHTML = " ";
        for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
}

function parseCustMessages2(responseXML) {
    clearTable2();
    var metrics = responseXML.getElementsByTagName("Metrics")[0];
    if (metrics.childNodes.length > 0) {
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");
    } else {
        clearTable2();
    }
    if(metrics.childNodes.length<10) {
        autorow1.style.overflowY = "hidden";
        autorow.style.overflowY = "hidden";
    }
    else {
        autorow1.style.overflowY = "scroll";
        autorow.style.overflowY = "scroll";
    }
    var consultant = metrics.childNodes[0];
    var chk=consultant.getElementsByTagName("VALID")[0];
    isExist = chk.childNodes[0].nodeValue;
    if(chk.childNodes[0].nodeValue =="true") {
        var validationMessage=document.getElementById("validationMessage2");
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < metrics.childNodes.length; loop++) {
            var consultant = metrics.childNodes[loop];
            var id = consultant.getElementsByTagName("MetricId")[0];
            var min = consultant.getElementsByTagName("Min")[0];
            var max = consultant.getElementsByTagName("Max")[0];
            var name = consultant.getElementsByTagName("NAME")[0];
            appendCustomer2(name.childNodes[0].nodeValue,id.childNodes[0].nodeValue,min.childNodes[0].nodeValue,max.childNodes[0].nodeValue);
        }
        var position;
        position = findPosition(document.getElementById("metricNameForTitle"));
        
        //var position = findPosition(document.getElementById("assignedToUID"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } 
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage2");
        validationMessage.innerHTML = "  Employee doesn't Exists ";
    }
}

function appendCustomer2(name,id,min,max) {
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
        "javascript:set_cust2('"+name +"','"+ id +"','"+ min +"','"+ max +"')");
    linkElement.appendChild(document.createTextNode(name));
    linkElement["onclick"] = new Function("hideScrollBar2()");
    nameCell.appendChild(linkElement);
}
function set_cust2(name,id,min,max){
    clearTable2();
    document.getElementById("metricNameForTitle").value =name;
    document.getElementById("metricId").value =id;
    document.getElementById("minValueForTitle").value=min;
    document.getElementById("maxValueForTitle").value=max;
}

function hideScrollBar2() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
}


function getAllTitleAssociations() {

    var departmentId = document.getElementById("searchDepartmentId").value;
    var title = document.getElementById("searchTitleId").value;
    var metricId = document.getElementById("metricIdForTitleSearch").value;
    var statusId = document.getElementById("statusIdForTitleSearch").value;
   // alert(departmentId+"---------"+statusId+"-------------"+title+"----------"+metricId);
    var tableId = document.getElementById("tblTitleMetrics");
    ClrTable(tableId);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, displayTitleAssoc);
    var url = CONTENXT_PATH+"/getTitlesList.action?metricId="+metricId+"&statusId="+statusId+"&departmentId="+departmentId+"&title="+title;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function displayTitleAssoc(response) {
    var tableId = document.getElementById("tblTitleMetrics");
    var headerFields = new Array("S.No","Metric Name","Department Id","Title Type","Weightage","Creatde By","Status","Description");
    var dataArray = response;
    ParseAndGenerateHTML(tableId,dataArray, headerFields);
}

function generateTitleMetricsRow(oTable,tableBody,record,delimiter){
    var deptId = "";
    var titleId = "";
    var Weightage = "";
    var status="";
    var createdBy="";
    var description="";
    var metrciName="";
    var id=0;
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(delimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    //Assigning values start
    metrciName=fields[1];
    deptId = fields[2];
    titleId = fields[3];
    Weightage = fields[4];
    status = fields[5];
    createdBy = fields[6];
    description = fields[7];
    //alert(length);
    id=fields[8];
    //Assigning values End
    for (var i=0;i<length-1;i++) {
        // cell = document.createElement( "TD" );
        // cell.className="gridColumn";
        if(i==0)
        {
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            cell.innerHTML = fields[i];
            cell.setAttribute('align','center');
        }
        if(i==1){
            //empLoginId = fields[i];
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            cell.setAttribute('align','center');
         
            //row.appendChild( cell );
            var j = document.createElement("a");
            
            j.setAttribute("href", "javascript:editTitleValues('"+id+"')");
            j.appendChild(document.createTextNode(metrciName));
            cell.appendChild(j);
        }
        if(i>1 && i<7) { 
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            cell.innerHTML = fields[i];
            cell.setAttribute('align','center');
        }
        else if(i==7){
            
            
            //empLoginId = fields[i];
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            cell.setAttribute('align','center');
         
            //row.appendChild( cell );
            var j = document.createElement("a");
            
            j.setAttribute("href", "javascript:getMetricComments('"+description+"')");
            j.appendChild(document.createTextNode("view"));
            cell.appendChild(j);
        }
        if(fields[i]!=''||fields[i]!=null){
            row.appendChild( cell );
        }
        
            
        
    }
    
    // Variables reset start
    deptId = "";
    titleId = "";
    Weightage = "";
    status = "";
    createdBy = "";
    description = "";
    metrciName="";
// Variables reset end
                    
//Feed back Mail End
}




function getAllMetricNames1() {
    var test = document.getElementById("metricNameForTitleSearch").value;
    var deptName=document.getElementById("searchDepartmentId").value;
    var searchTitleId=document.getElementById("searchTitleId").value;
    if(searchTitleId==''||searchTitleId=='-- Please Select --'||searchTitleId==null)
        {
            alert("please select title..");
            document.getElementById("metricNameForTitleSearch").value="";
            return false;
        }
        else
            {
    if (test == "") {
        clearTable3();
    } else {
        if (test.length >2) {
            var url = CONTENXT_PATH+"/getAllMetricNames1.action?metricName="+ escape(test)+"&departmentId="+deptName+"&title="+searchTitleId;
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        parseCustMessages3(req.responseXML);
                    } else if (req.status == 204){
                        clearTable3();
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }
}
}
function clearTable3() {
    if (completeTable) {
        completeTable.setAttribute("bordercolor", "white");
        completeTable.setAttribute("border", "0");
        completeTable.style.visible = false;
        var validationMessage=document.getElementById("validationMessage3");
        validationMessage.innerHTML = " ";
        for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
}

function parseCustMessages3(responseXML) {
    clearTable3();
    var metrics = responseXML.getElementsByTagName("Metrics")[0];
    if (metrics.childNodes.length > 0) {
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");
    } else {
        clearTable3();
    }
    if(metrics.childNodes.length<10) {
        autorow1.style.overflowY = "hidden";
        autorow.style.overflowY = "hidden";
    }
    else {
        autorow1.style.overflowY = "scroll";
        autorow.style.overflowY = "scroll";
    }
    var consultant = metrics.childNodes[0];
    var chk=consultant.getElementsByTagName("VALID")[0];
    isExist = chk.childNodes[0].nodeValue;
    if(chk.childNodes[0].nodeValue =="true") {
        var validationMessage=document.getElementById("validationMessage3");
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < metrics.childNodes.length; loop++) {
            var consultant = metrics.childNodes[loop];
            var id = consultant.getElementsByTagName("MetricId")[0];
            var name = consultant.getElementsByTagName("NAME")[0];
            appendCustomer3(name.childNodes[0].nodeValue,id.childNodes[0].nodeValue);
        }
        var position;
        position = findPosition(document.getElementById("metricNameForTitleSearch"));
        
        //var position = findPosition(document.getElementById("assignedToUID"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } 
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage3");
        validationMessage.innerHTML = "  Employee doesn't Exists ";
    }
}

function appendCustomer3(name,id) {
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
        "javascript:set_cust3('"+name +"','"+ id +"')");
    linkElement.appendChild(document.createTextNode(name));
    linkElement["onclick"] = new Function("hideScrollBar3()");
    nameCell.appendChild(linkElement);
}
function set_cust3(name,id){
    clearTable3();
    document.getElementById("metricNameForTitleSearch").value =name;
    document.getElementById("metricIdForTitleSearch").value =id;
 
}

function hideScrollBar3() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
}

function editTitleValues(id)
{
   
    //  window.location="editTitleMetrics.action?id="+id+"&temp=ta";    
  
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,populateTitleValues);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/editTitleValues.action?titleId="+id;
    req.open("GET",url,"false");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populateTitleValues(res)
{
    // alert(res);
    document.getElementById("titleResultMessage").innerHTML=" ";
    var caps = new Array();
    caps = res.split("@");
    //id + "@" + deptId + "@" + titleTypeId + "@" + metricId + "@" + metricName + "@" + minValue +"@"+maxValue +"@"+status+ "@" +desc+ "@" +weightage  
    var id=caps[0];
    var dept=caps[1];
    var titletypeId=caps[2];
    var metricId=caps[3];
    var metricName=caps[4];
    var minValue=caps[5];
    var maxValue=caps[6];
    var status=caps[7];
    var desc=caps[8];
    var weightage=caps[9];
  
    document.getElementById("metrics").checked=false;
    document.getElementById("titleAssoc").checked=true;
    document.getElementById("performanceReview").checked=false;
    document.getElementById("addWeightage").checked=true;
    document.getElementById("searchWeightage").checked=false;
    document.getElementById("titleAssocLabel").style.fontWeight = 'bold';
    document.getElementById("addWeightageLabel").innerHTML="Edit Weightage";
    document.getElementById("addWeightageLabel").style.fontWeight = 'bold';
    document.getElementById("searchWeightageLabel").style.fontWeight = 'normal';
    document.getElementById("titleSubmit").style.display='none';
    document.getElementById("searchWeightageDiv").style.display='none';
    document.getElementById("titleAssocDiv").style.display='block';
    document.getElementById("titleUpdate").style.display='block';
    document.getElementById("addWeightageDiv").style.display='block';
    
   
    document.getElementById("metricNameForTitle").value=metricName;
    document.getElementById("metricId").value=metricId;
    document.getElementById("minValueForTitle").value=minValue;
    document.getElementById("maxValueForTitle").value=maxValue;
    document.getElementById("id").value=id;
    document.getElementById("weightage").value =weightage;
    document.getElementById("statusIdForTitle").value =status;
    document.getElementById("commentsForTitle").value =desc;

    document.getElementById("metricNameForTitle").readOnly=true;
    document.getElementById("metricId").readOnly=true;
    document.getElementById("minValueForTitle").readOnly=true;
    document.getElementById("maxValueForTitle").readOnly=true;
       
    //   document.getElementById("defaultbutton").click();
    //  setInterval(function () {alert("Please wait..")}, 3000);
    // setSelectedIndex(document.getElementById('titleId'),titletypeId);
    document.getElementById("departmentId").value=dept;
    
    getEmpTitleDataVForTitleEdit(titletypeId);
//confirm("are you sure?");
// sleep1(10000,titletypeId);
//
}

function sleep1(milliseconds,titletypeId) {
    
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds){
            break;
        }
    }
    document.getElementById("titleId").value=titletypeId;
}
function addMetricValuesThroughAjax()
{
    
    // var id = document.getElementById("id").value;
    var metricName = document.getElementById("metricName").value;
    var minValue = document.getElementById("minValue").value;
    var maxValue = document.getElementById("maxValue").value;
    var statusId = document.getElementById("statusId").value;
    var comments = document.getElementById("comments").value;
   
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,updateMetricValues);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/addMetricValues.action?metricName="+metricName+"&minValue="+minValue+"&maxValue="+maxValue+"&status="+statusId+"&comments="+comments;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function addMetricValues(res){
    //  alert(res);
    document.getElementById("resM").innerHTML="";
    document.getElementById("titleResultMessage").innerHTML=res;
}

function addTitleValuesThroughAjax()
{
    
    var id = document.getElementById("id").value;
    var departmentId = document.getElementById("departmentId").value;
    var titleId = document.getElementById("titleId").value;
    var metricNameForTitle = document.getElementById("metricNameForTitle").value;
    var metricId = document.getElementById("metricId").value;
    var minValueForTitle = document.getElementById("minValueForTitle").value;
    var maxValueForTitle = document.getElementById("maxValueForTitle").value;
    var statusIdForTitle = document.getElementById("statusIdForTitle").value;
    var weightage = document.getElementById("weightage").value;
    var commentsForTitle=document.getElementById("commentsForTitle").value;
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,addTitleValues);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/addTitleValues.action?departmentName="+departmentId+"&title="+titleId+"&metricName="+metricNameForTitle+"&metricId="+metricId+"&minValue="+minValueForTitle+"&maxValue="+maxValueForTitle+"&status="+statusIdForTitle+"&weightage="+weightage+"&comments="+commentsForTitle;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function addTitleValues(res){
    //  alert(res);
    document.getElementById("resM").innerHTML="";
    document.getElementById("titleResultMessage").innerHTML=res;
}

function updateTitleValuesThroughAjax()
{
    
    var id = document.getElementById("id").value;
    var departmentId = document.getElementById("departmentId").value;
    var titleId = document.getElementById("titleId").value;
    var metricNameForTitle = document.getElementById("metricNameForTitle").value;
    var metricId = document.getElementById("metricId").value;
    var minValueForTitle = document.getElementById("minValueForTitle").value;
    var maxValueForTitle = document.getElementById("maxValueForTitle").value;
    var statusIdForTitle = document.getElementById("statusIdForTitle").value;
    var weightage = document.getElementById("weightage").value;
    var commentsForTitle=document.getElementById("commentsForTitle").value;
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,updateTitleValues);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/updateTitleValues.action?titleId="+id+"&departmentName="+departmentId+"&title="+titleId+"&metricName="+metricNameForTitle+"&metricId="+metricId+"&minValue="+minValueForTitle+"&maxValue="+maxValueForTitle+"&status="+statusIdForTitle+"&weightage="+weightage+"&comments="+commentsForTitle;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function updateTitleValues(res){
    //  alert(res);
    document.getElementById("resM").innerHTML="";
    document.getElementById("titleResultMessage").innerHTML=res;
}



function setMetricRange(dept,titleId)
{
    // alert("in js"+dept);
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,populateMetricRange);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/setMetricRange.action?departmentId="+dept+"&title="+titleId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    
}
function populateMetricRange(totalData){
    //alert(totalData)
    var str = totalData;
    var n=0;
    var n1 =0;
    var n2 =0; 
 
    if(str.indexOf("@")>=1){
        document.getElementById('creConsultantList').style.display = 'block'; 
        //    for(var r=1;r<=100;r++){
        //    document.getElementById('gridRow_'+r).style.display = 'block'; 
        // }
          
        var caps = new Array();
        caps = str.split("@");
        //-----------------------------------------------------------------  populating records  */
        var id=new Array();
        var metricName = new Array();
        var minValue = new Array();
        var max = new Array();
        var weightage = new Array();
        //alert();
        var count="";
        count = caps[0];
        document.getElementById('count').value= count;
        id=caps[1].split("!");
        metricName = caps[2].split("!");
        minValue = caps[3].split("!");
        max = caps[4].split("!");
        weightage = caps[5].split("!");
        var n1=0;
        // alert(id.length);
        for(var j=0; j<id.length;j++) {
            n1 = parseInt(j)+1;
            //  alert(n1);
            //   alert(id[j]);
            if( id[j].length>0 ) {
                document.getElementById('text'+n1).value= id[j];
                document.getElementById('div'+n1).style.display="block";
                document.getElementById('textm'+n1).value= max[j];
                document.getElementById('texti'+n1).value= metricName[j];
                document.getElementById('textc'+n1).value= 0;
                document.getElementById('texts'+n1).value=0;
                document.getElementById('textw'+n1).value= weightage[j];
                document.getElementById('slider'+n1).min= minValue[j];
                document.getElementById('slider'+n1).max= max[j];
                document.getElementById('slider'+n1).value=minValue[j];
            }
        }


        /*==================================
 * Empty Remaining Fields
 *==================================
 */
        var count=n1;
        // alert(count);
        var s = 0;
        var res=0;
        for(k=0;k<=100-n1;k++){
            s= parseInt(count)+parseInt(k);
            //  alert(s)
            if(document.getElementById('text'+s)!=null){
                document.getElementById('text'+s).value="";
           
                document.getElementById('gridRow_'+s).style.display = 'none'; 
            }else{
                break;
            }
            if(document.getElementById('texti'+s)!=null){
                document.getElementById('texti'+s).value="";
            }else{
                break;
            } 


            if(document.getElementById('textw'+s)!=null){
                document.getElementById('textw'+s).value="";
            }else{
                break;
            }
        
            if(document.getElementById('textm'+s)!=null){
                document.getElementById('textm'+s).value="";
            }else{
                break;
            } 
            if(document.getElementById('texts'+s)!=null){
                document.getElementById('texts'+s).value="";
            }

            if(document.getElementById('textc'+s)!=null){
                document.getElementById('textc'+s).value="";
            }else{
                break;
            } 
        } 
    }else{
        alert("No records to display");
    }
     
}


function getAllReviewedPerformances() {

    var departmentId = document.getElementById("searchDepartmentForPerformance").value;
    var empLoginId = document.getElementById("empNameLoginIdForSearch").value;
    var title = document.getElementById("searchTitleIdForPerformance").value;
    var fromDate = document.getElementById("fromDateForSearch").value;
    var toDate = document.getElementById("toDateForSearch").value;
    //alert(metricName+"---------"+statusId);
    var tableId = document.getElementById("tblEmpPerformance");
    ClrTable(tableId);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, displayReviewedPerformances);
    var url = CONTENXT_PATH+"/getAllReviewedPerformances.action?loginId="+empLoginId+"&startDate="+fromDate+"&endDate="+toDate+"&title="+title+"&departmentId="+departmentId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function displayReviewedPerformances(response) {
    var tableId = document.getElementById("tblEmpPerformance");
    var headerFields = new Array("S.No","Employee Name","Department","Title Type","Creatde Date","Created By");
    var dataArray = response;
    ParseAndGenerateHTML(tableId,dataArray, headerFields);
}

function generateReviewedPerformanceRow(oTable,tableBody,record,delimiter){
    var empName="";
  
    var createdDate="";
    var deptId = "";
    var titleId = "";
   

    var createdBy="";
    var id=0;
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(delimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    //Assigning values start
    empName=fields[1];
    deptId = fields[2];
    titleId = fields[3];
    createdDate = fields[4];
    createdBy = fields[5];
    id=fields[6];
    //Assigning values End
    for (var i=0;i<length-1;i++) {
        // cell = document.createElement( "TD" );
        // cell.className="gridColumn";
        if(i==0)
        {
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            cell.innerHTML = fields[i];
            cell.setAttribute('align','center');
        }
        if(i==1){
            //empLoginId = fields[i];
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            cell.setAttribute('align','center');
         
            //row.appendChild( cell );
            var j = document.createElement("a");
            
            j.setAttribute("href", "javascript:editPerformanceValues('"+id+"')");
            j.appendChild(document.createTextNode(empName));
            cell.appendChild(j);
        }
        if(i>1 && i<=5) { 
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            cell.innerHTML = fields[i];
            cell.setAttribute('align','center');
        }

        if(fields[i]!=''){
            row.appendChild( cell );
        }
        
            
        
    }
    
    // Variables reset start
    deptId = "";
    titleId = "";
   
    empName = "";
  
    createdDate = "";
    createdBy = "";
// Variables reset end
                    
//Feed back Mail End
}


function editPerformanceValues(id)
{
    // alert("1");
    
    
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,populatePerformanceValues);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/editPerformanceValues.action?perfId="+id;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
   
// window.location="editPerformanceValues.action?id="+id;    
    
}

function populatePerformanceValues(totalData){
    //  alert("2");
    document.getElementById("performanceResultMessage").innerHTML=" ";
    var caps = new Array();
    caps = totalData.split("@");
    var id=caps[0];
    var dept=caps[2];
    var titleId=caps[3];
    var empName=caps[1];
    var empLoginId=caps[6];
    var fromDate=caps[4];
    var toDate=caps[5];
    var callOutBound=caps[7];
    var appointment=caps[8];
    var conferenceCalls=caps[9];
    var meeting=caps[10];
    var oppurtunity=caps[11];
    var requirement=caps[12];
    var comments=caps[13];
    document.getElementById("metrics").checked=false;
    document.getElementById("titleAssoc").checked=false;
    document.getElementById("performanceReview").checked=true;
    document.getElementById("addReview").checked=true;
    document.getElementById("searchReview").checked=false;
    document.getElementById("performanceReviewLabel").style.fontWeight = 'bold';
    document.getElementById("addReviewLabel").innerHTML="Edit Review";
    document.getElementById("addReviewLabel").style.fontWeight = 'bold';
    document.getElementById("searchReviewLabel").style.fontWeight = 'normal';
    document.getElementById("performanceSubmit").style.display='none';
    document.getElementById("searchReviewDiv").style.display='none';
    document.getElementById("performanceReviewDiv").style.display='block';
    document.getElementById("performanceUpdate").style.display='block';
   
    document.getElementById("addReviewDiv").style.display='block';
    document.getElementById("performanceDiv").style.display='block';
    document.getElementById("creConsultantList").style.display='block';
    document.getElementById("empName").value=empName;
    document.getElementById("empNameLoginId").value=empLoginId;
    document.getElementById("departmentName").value=dept;
    document.getElementById("empTitle").value=titleId;
    document.getElementById("fromDate").value=fromDate;
    document.getElementById("toDate").value=toDate;
    document.getElementById("id").value=id;
    document.getElementById("callOutBound").value =callOutBound;
    document.getElementById("appointment").value =appointment;
    document.getElementById("conferenceCalls").value =conferenceCalls;
    document.getElementById("meeting").value =meeting;
    document.getElementById("oppurtunity").value =oppurtunity;
    document.getElementById("requirement").value =requirement;
    document.getElementById("commentsForPerformance").value =comments;
    document.getElementById("fromDate").readOnly=true;
    document.getElementById("toDate").readOnly=true;
    document.getElementById("empName").readOnly=true;
    document.getElementById("empNameLoginId").readOnly=true;
     
    setMetricRange1(id,dept,titleId)
}
function setMetricRange1(id,dept,titleId)
{
    // alert("in js"+dept);
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,populateMetricRange1);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/setMetricRange1.action?departmentId="+dept+"&title="+titleId+"&perfId="+id;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    
}
function populateMetricRange1(totalData){
    //alert(totalData)
    var str = totalData;
    var n=0;
    var n1 =0;
    var n2 =0; 
 
    if(str.indexOf("@")>=1){
        document.getElementById('creConsultantList').style.display = 'block'; 
        //    for(var r=1;r<=100;r++){
        //    document.getElementById('gridRow_'+r).style.display = 'block'; 
        // }
          
        var caps = new Array();
        caps = str.split("@");
        //-----------------------------------------------------------------  populating records  */
        var id=new Array();
        var metricName = new Array();
        var minValue = new Array();
        var max = new Array();
        var weightage = new Array();
        var weighageObtained= new Array();
        var rating = new Array();
        var perfId=new Array();
        //alert();
        var count="";
        count = caps[0];
        document.getElementById('count').value= count;
        id=caps[1].split("!");
        metricName = caps[2].split("!");
        minValue = caps[3].split("!");
        max = caps[4].split("!");
        weightage = caps[5].split("!");
        rating = caps[6].split("!");
        weighageObtained = caps[7].split("!");
        perfId = caps[8].split("!");
        var n1=0;
        // alert(id.length);
        for(var j=0; j<id.length;j++) {
            n1 = parseInt(j)+1;
            //  alert(n1);
            //   alert(id[j]);
            if( id[j].length>0 ) {
                document.getElementById('textp'+n1).value= perfId[j];
                document.getElementById('text'+n1).value= id[j];
                document.getElementById('div'+n1).style.display="block";
                document.getElementById('textm'+n1).value= max[j];
                document.getElementById('texti'+n1).value= metricName[j];
                document.getElementById('textc'+n1).value= weighageObtained[j];
                document.getElementById('texts'+n1).value=rating[j];
                document.getElementById('textw'+n1).value= weightage[j];
                document.getElementById('slider'+n1).min= minValue[j];
                document.getElementById('slider'+n1).max= max[j];
                document.getElementById('slider'+n1).value=rating[j];
            }
        }


        /*==================================
 * Empty Remaining Fields
 *==================================
 */
        var count=n1;
        // alert(count);
        var s = 0;
        var res=0;
        for(k=0;k<=100-n1;k++){
            s= parseInt(count)+parseInt(k);
            //  alert(s)
            if(document.getElementById('textp'+s)!=null){
                document.getElementById('textp'+s).value="";
           
            //document.getElementById('gridRow_'+s).style.display = 'none'; 
            }else{
                break;
            }
            if(document.getElementById('text'+s)!=null){
                document.getElementById('text'+s).value="";
           
                document.getElementById('gridRow_'+s).style.display = 'none'; 
            }else{
                break;
            }
            if(document.getElementById('texti'+s)!=null){
                document.getElementById('texti'+s).value="";
            }else{
                break;
            } 


            if(document.getElementById('textw'+s)!=null){
                document.getElementById('textw'+s).value="";
            }else{
                break;
            }
        
            if(document.getElementById('textm'+s)!=null){
                document.getElementById('textm'+s).value="";
            }else{
                break;
            } 
            if(document.getElementById('texts'+s)!=null){
                document.getElementById('texts'+s).value="";
            }

            if(document.getElementById('textc'+s)!=null){
                document.getElementById('textc'+s).value="";
            }else{
                break;
            } 
        } 
    }else{
        alert("No records to display");
    }
     
}

function updatePerformanceValues()
{
    var id = document.getElementById("id").value;
    var empName = document.getElementById("empName").value;
    var empNameLoginId = document.getElementById("empNameLoginId").value;
    var departmentName = document.getElementById("departmentName").value;
    var empTitle = document.getElementById("empTitle").value;
    var fromDate = document.getElementById("fromDate").value;
    var toDate = document.getElementById("toDate").value;
    var count = document.getElementById("count").value;
    var comments = document.getElementById("commentsForPerformance").value;
    var perfLineId="";
    var metricId="";
    var maxValue="";
    var totWeightage="";
    var rating = "";
    var resultantWeightage="";
    var stringdata ='{   "MyResponse": {  "perfId":'+id+',"count":'+count+',"empName":'+empName+',"empNameLoginId":'+empNameLoginId+',"departmentName":'+departmentName+',"empTitle":'+empTitle+',"fromDate":'+fromDate+',"toDate":'+toDate+',"comments":'+comments+',"listTsm": [';
    //alert(stringdata);
    for(var i=1;i<=count;i++)
    {
        if(i==1)
        {
            perfLineId = document.getElementById("textp1").value;
            metricId = document.getElementById("text1").value;
            maxValue = document.getElementById("textm1").value;
            totWeightage = document.getElementById("textw1").value;
            resultantWeightage = document.getElementById("textc1").value;
            rating=document.getElementById("texts1").value;
            stringdata = stringdata + '{"perfLineId":'+perfLineId+',"metricId":'+metricId+',"maxValue":'+maxValue+',"totWeightage":'+totWeightage+',rating:"'+rating+'","resultantWeightage":"'+resultantWeightage+'"},';   
                   
        }
        if(i==2)
        {
            perfLineId = document.getElementById("textp2").value;
            metricId = document.getElementById("text2").value;
            maxValue = document.getElementById("textm2").value;
            totWeightage = document.getElementById("textw2").value;
            resultantWeightage = document.getElementById("textc2").value;
            rating=document.getElementById("texts2").value;
            stringdata = stringdata + '{"perfLineId":'+perfLineId+',"metricId":'+metricId+',"maxValue":'+maxValue+',"totWeightage":'+totWeightage+',rating:"'+rating+'","resultantWeightage":"'+resultantWeightage+'"},';   
                   
        }
        if(i==3)
        {
            perfLineId = document.getElementById("textp3").value;
            metricId = document.getElementById("text3").value;
            maxValue = document.getElementById("textm3").value;
            totWeightage = document.getElementById("textw3").value;
            resultantWeightage = document.getElementById("textc3").value;
            rating=document.getElementById("texts3").value;
            stringdata = stringdata + '{"perfLineId":'+perfLineId+',"metricId":'+metricId+',"maxValue":'+maxValue+',"totWeightage":'+totWeightage+',rating:"'+rating+'","resultantWeightage":"'+resultantWeightage+'"},';   
                   
        }
        if(i==4)
        {
            perfLineId = document.getElementById("textp4").value;
            metricId = document.getElementById("text4").value;
            maxValue = document.getElementById("textm4").value;
            totWeightage = document.getElementById("textw4").value;
            resultantWeightage = document.getElementById("textc4").value;
            rating=document.getElementById("texts4").value;
            stringdata = stringdata + '{"perfLineId":'+perfLineId+',"metricId":'+metricId+',"maxValue":'+maxValue+',"totWeightage":'+totWeightage+',"rating":"'+rating+'","resultantWeightage":"'+resultantWeightage+'"},';   
                   
        }
        if(i==5)
        {
            perfLineId = document.getElementById("textp5").value;
            metricId = document.getElementById("text5").value;
            maxValue = document.getElementById("textm5").value;
            totWeightage = document.getElementById("textw5").value;
            resultantWeightage = document.getElementById("textc5").value;
            rating=document.getElementById("texts5").value;
            stringdata = stringdata + '{"perfLineId":'+perfLineId+',"metricId":'+metricId+',"maxValue":'+maxValue+',"totWeightage":'+totWeightage+',"rating":"'+rating+'","resultantWeightage":"'+resultantWeightage+'"},';   
                   
        }
        if(i==6)
        {
            perfLineId = document.getElementById("textp6").value;
            metricId = document.getElementById("text6").value;
            maxValue = document.getElementById("textm6").value;
            totWeightage = document.getElementById("textw6").value;
            resultantWeightage = document.getElementById("textc6").value;
            rating=document.getElementById("texts6").value;
            stringdata = stringdata + '{"perfLineId":'+perfLineId+',"metricId":'+metricId+',"maxValue":'+maxValue+',"totWeightage":'+totWeightage+',"rating":"'+rating+'","resultantWeightage":"'+resultantWeightage+'"},';   
                   
        }
        if(i==7)
        {
            perfLineId = document.getElementById("textp7").value;
            metricId = document.getElementById("text7").value;
            maxValue = document.getElementById("textm7").value;
            totWeightage = document.getElementById("textw7").value;
            resultantWeightage = document.getElementById("textc7").value;
            rating=document.getElementById("texts7").value;
            stringdata = stringdata + '{"perfLineId":'+perfLineId+',"metricId":'+metricId+',"maxValue":'+maxValue+',"totWeightage":'+totWeightage+',"rating":"'+rating+'","resultantWeightage":"'+resultantWeightage+'"},';   
                   
        }
        if(i==8)
        {
            perfLineId = document.getElementById("textp8").value;
            metricId = document.getElementById("text8").value;
            maxValue = document.getElementById("textm8").value;
            totWeightage = document.getElementById("textw8").value;
            resultantWeightage = document.getElementById("textc8").value;
            rating=document.getElementById("texts8").value;
            stringdata = stringdata + '{"perfLineId":'+perfLineId+',"metricId":'+metricId+',"maxValue":'+maxValue+',"totWeightage":'+totWeightage+',"rating":"'+rating+'","resultantWeightage":"'+resultantWeightage+'"},';   
                   
        }
        if(i==9)
        {
            perfLineId = document.getElementById("textp9").value;
            metricId = document.getElementById("text9").value;
            maxValue = document.getElementById("textm9").value;
            totWeightage = document.getElementById("textw9").value;
            resultantWeightage = document.getElementById("textc9").value;
            rating=document.getElementById("texts9").value;
            stringdata = stringdata + '{"perfLineId":'+perfLineId+',"metricId":'+metricId+',"maxValue":'+maxValue+',"totWeightage":'+totWeightage+',"rating":"'+rating+'","resultantWeightage":"'+resultantWeightage+'"},';   
                   
        }
        if(i==10)
        {
            perfLineId = document.getElementById("textp10").value;
            metricId = document.getElementById("text10").value;
            maxValue = document.getElementById("textm10").value;
            totWeightage = document.getElementById("textw10").value;
            resultantWeightage = document.getElementById("textc10").value;
            rating=document.getElementById("texts10").value;
            stringdata = stringdata + '{"perfLineId":'+perfLineId+',"metricId":'+metricId+',"maxValue":'+maxValue+',"totWeightage":'+totWeightage+',"rating":"'+rating+'","resultantWeightage":"'+resultantWeightage+'"},';   
                   
        }
        if(i==11)
        {
            perfLineId = document.getElementById("textp11").value;
            metricId = document.getElementById("text11").value;
            maxValue = document.getElementById("textm11").value;
            totWeightage = document.getElementById("textw11").value;
            resultantWeightage = document.getElementById("textc11").value;
            rating=document.getElementById("texts11").value;
            stringdata = stringdata + '{"perfLineId":'+perfLineId+',"metricId":'+metricId+',"maxValue":'+maxValue+',"totWeightage":'+totWeightage+',"rating":"'+rating+'","resultantWeightage":"'+resultantWeightage+'"},';   
                   
        }
        if(i==12)
        {
            perfLineId = document.getElementById("textp12").value;
            metricId = document.getElementById("text12").value;
            maxValue = document.getElementById("textm12").value;
            totWeightage = document.getElementById("textw12").value;
            resultantWeightage = document.getElementById("textc12").value;
            rating=document.getElementById("texts12").value;
            stringdata = stringdata + '{"perfLineId":'+perfLineId+',"metricId":'+metricId+',"maxValue":'+maxValue+',"totWeightage":'+totWeightage+',"rating":"'+rating+'","resultantWeightage":"'+resultantWeightage+'"},';   
                   
        }
        if(i==13)
        {
            perfLineId = document.getElementById("textp13").value;
            metricId = document.getElementById("text13").value;
            maxValue = document.getElementById("textm13").value;
            totWeightage = document.getElementById("textw13").value;
            resultantWeightage = document.getElementById("textc13").value;
            rating=document.getElementById("texts13").value;
            stringdata = stringdata + '{"perfLineId":'+perfLineId+',"metricId":'+metricId+',"maxValue":'+maxValue+',"totWeightage":'+totWeightage+',"rating":"'+rating+'","resultantWeightage":"'+resultantWeightage+'"},';   
                   
        }
        if(i==14)
        {
            perfLineId = document.getElementById("textp14").value;
            metricId = document.getElementById("text14").value;
            maxValue = document.getElementById("textm14").value;
            totWeightage = document.getElementById("textw14").value;
            resultantWeightage = document.getElementById("textc14").value;
            rating=document.getElementById("texts14").value;
            stringdata = stringdata + '{"perfLineId":'+perfLineId+',"metricId":'+metricId+',"maxValue":'+maxValue+',"totWeightage":'+totWeightage+',"rating":"'+rating+'","resultantWeightage":"'+resultantWeightage+'"},';   
                   
        }
               
        if(i==15)
        {
            perfLineId = document.getElementById("textp15").value;
            metricId = document.getElementById("text15").value;
            maxValue = document.getElementById("textm15").value;
            totWeightage = document.getElementById("textw15").value;
            resultantWeightage = document.getElementById("textc15").value;
            rating=document.getElementById("texts15").value;
            stringdata = stringdata + '{"perfLineId":'+perfLineId+',"metricId":'+metricId+',"maxValue":'+maxValue+',"totWeightage":'+totWeightage+',"rating":"'+rating+'","resultantWeightage":"'+resultantWeightage+'"},';   
                   
        }
               
            
    }
    stringdata = stringdata.substring(0,stringdata.length-1) + '] }}';
    // stringdata = "hiiii"
    // Convert a string to an JavaScript object
    // var arrayData = JSON.parse(stringdata);
    // Convert a JavaScript object to a string
    // stringdata = JSON.stringify(arrayData);
    //alert(stringdata);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,populatePerformanceValues1);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/updatePerformanceValues.action?stringdata="+stringdata;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
   
// window.location="editPerformanceValues.action?id="+id;    
    
}

function populatePerformanceValues1(rssMsg){
    document.getElementById("resM").innerHTML="";
    document.getElementById("performanceResultMessage").innerHTML=rssMsg;
    
}


function changeEditToAddMetrics()
{
    //  alert(document.getElementById("addMetricsLabel").innerHTML);
    if(document.getElementById("addMetricsLabel").innerHTML == 'Edit Metrics')
    {
        document.getElementById("metricResultMessage").innerHTML="";
        document.getElementById("addMetricsLabel").innerHTML = 'Add Metrics';
        document.getElementById("metricName").value='';
        document.getElementById("minValue").value='';
        document.getElementById("maxValue").value='';
        document.getElementById("statusId").value='';
        document.getElementById("comments").value='';
        document.getElementById("metricUpdate").style.display='none';
        document.getElementById("metricSubmit").style.display='block';
          getMetricRecords();          
            
    // alert("ddddddd");
    }
}

function changeEditToAddTitle()
{
      if(document.getElementById("addWeightageLabel").innerHTML == 'Edit Weightage')
    {
  // alert("hii");
  var deptId=document.getElementById("departmentId").value;
  //alert(deptId)
  var title=document.getElementById("titleId").value;
  //alert(title)
   document.getElementById("titleResultMessage").innerHTML="";
        document.getElementById("addWeightageLabel").innerHTML = 'Add Weightage';
        document.getElementById("departmentId").value='Executive Board';
        document.getElementById("metricNameForTitle").value='';
        document.getElementById("titleId").value='';
        document.getElementById("metricId").value='';
        document.getElementById("minValueForTitle").value='';
        document.getElementById("maxValueForTitle").value='';
        document.getElementById("weightage").value='';
        document.getElementById("statusIdForTitle").value='';
        document.getElementById("commentsForTitle").value='';
        document.getElementById("titleUpdate").style.display='none';
        document.getElementById("titleSubmit").style.display='block';
         document.getElementById("metricNameForTitle").readOnly=false;
         document.getElementById("minValueForTitle").readOnly=false;
        document.getElementById("maxValueForTitle").readOnly=false;
       // getEmpTitleDataV1();
         getEmpTitleDataVForTitleEdit(title);
         getAllTitleAssociationsAfterUpdate(title);
          //getMetricRecords();          
    
}

}



function getAllTitleAssociationsAfterUpdate(title) {

    var departmentId = document.getElementById("searchDepartmentId").value;
   
    var metricId = document.getElementById("metricIdForTitleSearch").value;
    var statusId = document.getElementById("statusIdForTitleSearch").value;
 //   alert(departmentId+"---------"+statusId+"-------------"+title+"----------"+metricId);
    var tableId = document.getElementById("tblTitleMetrics");
    ClrTable(tableId);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, displayTitleAssoc);
    var url = CONTENXT_PATH+"/getTitlesList.action?metricId="+metricId+"&statusId="+statusId+"&departmentId="+departmentId+"&title="+title;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function changeEditToAddPerformance()
{
      if(document.getElementById("addReviewLabel").innerHTML == 'Edit Review')
    {
  // alert("hii");
  var deptId=document.getElementById("departmentName").value;
  //alert(deptId)
  var title=document.getElementById("empTitle").value;
  //alert(title)
   document.getElementById("performanceResultMessage").innerHTML="";
        document.getElementById("addReviewLabel").innerHTML = 'Add Review';
         document.getElementById("empName").readOnly=false;
        document.getElementById("empName").value="";
        document.getElementById("empNameLoginId").value="";
        document.getElementById("departmentName").value="";
        document.getElementById("empTitle").value="";
        document.getElementById("fromDate").value="";
        document.getElementById("toDate").value="";
        document.getElementById("callOutBound").value="";
        document.getElementById("appointment").value="";
        document.getElementById("conferenceCalls").value="";
        document.getElementById("meeting").value="";
        document.getElementById("oppurtunity").value="";
        document.getElementById("requirement").value="";
         document.getElementById("commentsForPerformance").value="";
        
        document.getElementById("performanceDiv").style.display='none';
        document.getElementById("performanceUpdate").style.display='none';
        document.getElementById("performanceSubmit").style.display='block';
       // getEmpTitleDataV1();
         getEmpTitleDataVForPerformanceEdit(title,deptId);
         getAllReviewedPerformancesAfterUpdate(title);
          //getMetricRecords();          
    
}

}

function getAllReviewedPerformancesAfterUpdate(title) {

    var departmentId = document.getElementById("searchDepartmentForPerformance").value;
    var empLoginId = document.getElementById("empNameLoginIdForSearch").value;
    
    var fromDate = document.getElementById("fromDateForSearch").value;
    var toDate = document.getElementById("toDateForSearch").value;
    //alert(metricName+"---------"+statusId);
    var tableId = document.getElementById("tblEmpPerformance");
    ClrTable(tableId);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, displayReviewedPerformances);
    var url = CONTENXT_PATH+"/getAllReviewedPerformances.action?loginId="+empLoginId+"&startDate="+fromDate+"&endDate="+toDate+"&title="+title+"&departmentId="+departmentId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}