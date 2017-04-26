function validatenumber(xxxxx) {
	var maintainplus = '';
 	var numval = xxxxx.value
 	if ( numval.charAt(0)=='+' ){ var maintainplus = '+';}
 	curnumbervar = numval.replace(/[\\A-Za-z!"£$%^&*+_={};:'@#~,.¦\/<>?|`¬\]\[]/g,'');
 	xxxxx.value = maintainplus + curnumbervar;
 	var maintainplus = '';
        // alert("enter integers only");
 	xxxxx.focus;
}

function firstNameValidate(){
    
   var firstName= document.frmEmpSearch.firstName;
   
       
    if (firstName.value != null && (firstName.value != "")) {
        if(firstName.value.replace(/^\s+|\s+$/g,"").length>30){
            
            str = new String(document.frmEmpSearch.firstName.value);
             document.frmEmpSearch.firstName.value=str.substring(0,30);
            
            alert("The firstName must be less than 30 characters");
            
              
           
              }
       document.frmEmpSearch.firstName.focus();
        return (false);
    }
  
    return (true);
};


function workPhoneNoValidate(){
    
   var workPhoneNo= document.frmEmpSearch.workPhoneNo;
   
       
    if (workPhoneNo.value != null && (workPhoneNo.value != "")) {
        if(workPhoneNo.value.replace(/^\s+|\s+$/g,"").length>20){
            
                 str = new String(document.frmEmpSearch.workPhoneNo.value);
            document.frmEmpSearch.workPhoneNo.value=str.substring(0,20);   
            
            alert("The workPhoneNo must be less than 20 characters");
            
             
           
              }
       document.frmEmpSearch.workPhoneNo.focus();
        return (false);
    }
  
    return (true);
};

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
    
    var departmentName = document.frmEmpSearch.departmentId.value;
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populatePractices);
     var url = CONTENXT_PATH+"/getEmpDepartment.action?departmentName="+departmentName;
    req.open("POST",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populatePractices(resXML) {    
    
    var practiceId = document.frmEmpSearch.practiceId;
    
    var department = resXML.getElementsByTagName("DEPARTMENT")[0];
    
    var practices = department.getElementsByTagName("PRACTICE");
    practiceId.innerHTML=" ";
    
    for(var i=0;i<practices.length;i++) {
        var practiceName = practices[i];
        
        var name = practiceName.firstChild.nodeValue;
        var opt = document.createElement("option");
        if(i==0){
            opt.setAttribute("value","");
        }else{
            opt.setAttribute("value",name);
        }
        opt.appendChild(document.createTextNode(name));
        practiceId.appendChild(opt);
    }
}

/*Methods closing Practices by Department*/


/*Methods for getting Employee Titles by Department*/

function getEmpTitleDataV1() {
    
    var departmentName = document.frmEmpSearch.departmentId.value;
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateEmpTitles);
   var url = CONTENXT_PATH+"/getEmpForTitles.action?departmentName="+departmentName;
    req.open("POST",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populateEmpTitles(resXML) {
    
    
    var titleId = document.frmEmpSearch.titleId;
    
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

/*Methods closing Employee Titles by Department*/

/*START: Methods for Sub Practice Data*/
function getSubPracticeData(){
    
var practiceName = document.frmEmpSearch.practiceId.value;
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateSubPractices);
     var url = CONTENXT_PATH+"/getEmpPractice.action?practiceName="+practiceName;
    req.open("POST",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populateSubPractices(resXML) {    
    
    var subPractice = document.frmEmpSearch.subPractice;
    
    var practice = resXML.getElementsByTagName("PRACTICE")[0];
    
    var subPractices = practice.getElementsByTagName("SUBPRACTICE");
    subPractice.innerHTML=" ";
    
    for(var i=0;i<subPractices.length;i++) {
        var subPracticeName = subPractices[i];
        
        var name = subPracticeName.firstChild.nodeValue;
        var opt = document.createElement("option");
        if(i==0){
            opt.setAttribute("value","");
        }else{
            opt.setAttribute("value",name);
        }
        opt.appendChild(document.createTextNode(name));
        subPractice.appendChild(opt);
    }
}
/*CLOSE: Methods for Sub Practice Data*/

/*Methods for getting Teams by Practices*/

function getTeamData() {
    
    var subPracticeName = document.frmEmpSearch.subPractice.value;
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateTeams);
    var url = CONTENXT_PATH+"/getEmpSubPractice.action?subPracticeName="+subPracticeName;
    req.open("POST",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populateTeams(resXML) {
    
    var teamId = document.frmEmpSearch.teamId;
    var subPractice = resXML.getElementsByTagName("SUBPRACTICE")[0];
    var teams = subPractice.getElementsByTagName("TEAM");
    teamId.innerHTML=" ";
    for(var i=0;i<teams.length;i++) {
        var TeamName = teams[i];
        var name = TeamName.firstChild.nodeValue;
        var opt = document.createElement("option");
        
        if(i==0){
            opt.setAttribute("value","");
        }else{
            opt.setAttribute("value",name);
        }
        
        opt.appendChild(document.createTextNode(name));
        teamId.appendChild(opt);
    }
}

/*Methods closing Teams by Practices*/

/*START: Methods for getting reports to person data*/

/*END: Methods for getting reports to person data*/

