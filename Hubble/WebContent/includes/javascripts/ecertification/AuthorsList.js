 var popup;

function readyStateHandlerText(req,responseTextHandler){
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

function fillAuthor() {
var test='';
   // var topicId = document.getElementById("topicId").value;
    var topicId = document.authorForm.topicId.value;
    var test=document.getElementById("assignedToUID").value;  
        
    if (test == "") {
    
        clearTable();
        hideScrollBar();
         
        var validationMessage=document.getElementById("authorEmpValidationMessage");
        validationMessage.innerHTML = "";
        document.frmAuthorAdd.preAssignEmpId.value="";
      
    } else {
        if (test.length >2) {
            var url = CONTENXT_PATH+"/getEmployeeDetailsForAuthors.action?customerName="+ escape(test)+"&topicId="+topicId;   
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {                        
                        parseEmpMessages(req.responseXML);                        
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

/*   New   */
var isPriEmpExist = false;
var isSecEmpExist = false;
function parseEmpMessages(responseXML) {
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
            appendEmployee(empid.childNodes[0].nodeValue,customerName.childNodes[0].nodeValue);
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





function appendEmployee(empId,empName) {
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
    
    linkElement.setAttribute("href", "javascript:set_emp('"+empName +"','"+ empId +"')");
    
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
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

function set_emp(eName,eID){
    clearTable();
   // document.issuesForm.assignedToUID.value =eName;
    //document.issuesForm.preAssignEmpId.value =eID;
document.getElementById('assignedToUID').value = eName;
document.getElementById('preAssignEmpId').value = eID;
}

//New function for geeting task Description


function getQuestionPopup(Id) {
    //alert("hi-->"+Id);
    var aId = Id;
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populateDescriptionComments);    
   
    var url=CONTENXT_PATH+"/popupQuestionsWindow.action?questionId="+aId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateDescriptionComments(text) {
    var background = "#3E93D4";
    var title = "Description:";
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
    } else {
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=500,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}

/*
Method for Changig Status of Author
Date : 08/22/2013
Author : Santosh Kola
*/

function changeStatus(authorId, topicId, authorLoginId) {
var loginId = authorLoginId;

var r=confirm("Are you sure on inactivating '"+loginId+"' ?");
if (r==true)
  {
  window.location = "deleteAuthor.action?id="+authorId+"&topicId="+topicId;
  }

//deleteAuthor.action?id={Id}&topicId={TopicId}
}


function doDeleteSubtopic(subTopicId, topicId,subtopic) {


var r=confirm("Are you sure on inactivating '"+subtopic+"' ?");
if (r==true)
  {
  window.location = "deleteSubTopic.action?subTopicId="+subTopicId+"&topicId="+topicId;
  }

//deleteAuthor.action?id={Id}&topicId={TopicId}
}
/*
 * 
 * Added by Amar and Harsha
 * Date : 12/18/2015
 */

function deleteQuestion(Id,TopicId){
 
    var r=confirm("Are you sure on inactivating ?");
    if(r==true){
    window.location ="deleteQuestionOpp.action?questionId="+Id+"&topicId="+TopicId;


}
}
