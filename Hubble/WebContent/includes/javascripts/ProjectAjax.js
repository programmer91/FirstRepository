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

/*Methods for getting SubProjects by Project*/
function getSubProject() {
    
    var project = document.getElementById("categoryId").value; 
    
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateSubProjects);
    var url = CONTENXT_PATH+"/project.action?project="+project;
    
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateSubProjects(resXML) {
    var  subProjectId = document.getElementById("subCategoryId");   
    
    var project = resXML.getElementsByTagName("PROJECT")[0];
    
    var subProjects = project.getElementsByTagName("SUBPROJECT");
    
    subProjectId.innerHTML="";
    for (var i=0;i<subProjects.length;i++) {
        var subProjectName = subProjects[i];
        var name = subProjectName.firstChild.nodeValue;        
        var opt = document.createElement("option");          
        opt.setAttribute("value",name);
        opt.appendChild(document.createTextNode(name));
        subProjectId.appendChild(opt);
    }
}

/*Methods for getting SubProjects by Project*/
function getProjEmployees() {
    
    var project = document.getElementById("categoryId").value;
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateProjEmployees);
    var url = CONTENXT_PATH+"/projectEmp.action?project="+project;
    
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateProjEmployees(resXML) {
    var  assignedEmpId = document.getElementById("assignedToUID");   
    
    var project = resXML.getElementsByTagName("PROJECT")[0];
    
    var assignedEmpIds = project.getElementsByTagName("EMPNAME");
    
    assignedEmpId.innerHTML="";
    for (var i=0;i<assignedEmpIds.length;i++) {
        var assignedEmpName = assignedEmpIds[i];
        var name = assignedEmpName.firstChild.nodeValue;        
        var opt = document.createElement("option");          
        opt.setAttribute("value",name);
        opt.appendChild(document.createTextNode(name));
        assignedEmpId.appendChild(opt);
    }
}


var projects = new Array();
function getStatus() {
    
    var project = document.getElementById('projects').value;
    if(project != '-1') {
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandler(req, populateProjectStatus);
        document.getElementById("loadMessage").style.display = 'block';
        var url = CONTENXT_PATH+"/getProjectStatus.action?project="+project;
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-forum-urlencoded");
        req.send(null);
    }else {
        document.statusForm.projectId.value = "";
        projects['--please select--'] = new Array('');
        projectList = projects['--please select--'];
        if(projectList != null){
            setStatus('status', projectList, projectList);
        }
    }
    
}

function populateProjectStatus(resXML) {
    var project = resXML.getElementsByTagName("PROJECT")[0];
    var id = project.getElementsByTagName("ID")[0];
    var projStatus = project.getElementsByTagName("STATUS")[0];
    var modifiedDate = project.getElementsByTagName("MDATE")[0];
    var reason = project.getElementsByTagName("REASON")[0];
    var status = projStatus.childNodes[0].nodeValue;
    var projId = id.childNodes[0].nodeValue;
    document.statusForm.projectId.value = projId;
    if(status == '1') {
        projects['Created'] = new Array('Created','Active','Canceled');
        projectList = projects['Created'];
    }else if(status == '2') {
        projects['Active'] = new Array('Active','Completed','Canceled');
        projectList = projects['Active'];
    }else if(status == '3') {
        projects['Completed'] = new Array('Completed');
        projectList = projects['Completed'];
    }else if(status == '4') {
        projects['Canceled'] = new Array('Canceled');
        document.statusForm.reason.value = reason.childNodes[0].nodeValue;
        projectList = projects['Canceled'];
    }
    
    if(projectList != null){
        setStatus('status', projectList, projectList);
    }
}

function setStatus(fieldId, newOptions, newValues) {
    selectedField = document.getElementById(fieldId);
    selectedField.options.length = 0;
    for (i=0; i<newOptions.length; i++) {
        selectedField.options[selectedField.length] = new Option(newOptions[i], newValues[i]);
    }
    divHide();
    document.getElementById("loadMessage").style.display = 'none';
}

function divHide() {
    if(document.statusForm.status.value == 'Canceled') {
        document.getElementById('reasonDiv').style.display = 'block';
    }else {
        document.statusForm.reason.value = '';
        document.getElementById('reasonDiv').style.display = 'none';
    }
}

function doAddStatus() {
    var status = document.statusForm.status.value;
    if(status == 'Created')
        statusCode = 1;
    else if(status == 'Active')
        statusCode = 2;
    else if(status == 'Completed')
        statusCode = 3;
    else if(status == 'Canceled')
        statusCode = 4;
    else if(status == 'Closed')
        statusCode = 5;
    var projectId = document.statusForm.projectId.value;
    var reason = document.statusForm.reason.value;
    if (reason != null && (reason != "")) {
        if(reason.replace(/^\s+|\s+$/g,"").length>200){
            
            str = new String(document.statusForm.reason.value);
            document.statusForm.reason.value=str.substring(0,200);
            
            alert("The accountName must be less than 250 characters");
            
            document.statusForm.reason.focus();
            return (false);
        }
    }
    if(projectId != null && (projectId != "")) {
        req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandler(req, populateResult);
        document.getElementById("loadMessage").style.display = 'block';
        var url = CONTENXT_PATH+"/addProjStatus.action?projectId="+projectId+"&statusCode="+statusCode+"&reason="+reason+"&dummy="+new Date().getTime();
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-forum-urlencoded");
        req.send(null);
    }else {
        alert('Please Check The Fields');
    }
}

function populateResult(resXML) {
    
    document.getElementById("loadMessage").style.display = 'none';
    var valid = resXML.getElementsByTagName("valid")[0];
    var chk = valid.childNodes[0].nodeValue;
    if(chk ==1)
        alert('Project Status has been saved');
    else
        alert('Sorry, Please Try Again!');
    
}


