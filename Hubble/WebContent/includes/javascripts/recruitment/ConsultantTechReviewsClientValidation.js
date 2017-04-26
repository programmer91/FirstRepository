
var popup;
var isIE;
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
function attachmentNameValidate(){
    
   var attachmentName= document.frmAttach.attachmentName;
   
       
    if (attachmentName.value != null && (attachmentName.value != "")) {
        if(attachmentName.value.replace(/^\s+|\s+$/g,"").length>50){
                       
                 str = new String(document.frmAttach.attachmentName.value);
             document.frmAttach.attachmentName.value=str.substring(0,50);
             
               alert("The TechReviewFileName must be less than 50 characters");
           
              }
       document.frmAttach.attachmentName.focus();
        return (false);
    }
  
    return (true);
};

function attachmentFileNameValidate(){
    //alert("attachmentFileNameValidate");
   var attachmentFileName= document.frmAttach.attachmentFileName;
   
       
    if (attachmentFileName.value != null && (attachmentFileName.value != "")) {
        if(attachmentFileName.value.length>40){
                       
                 
               document.getElementById('attachmentFileName').value = "";
               alert("The Resume file name must be less than 40 characters.Please rename the resume file name to less than 40 characters.");
           
              }
       document.frmAttach.attachmentFileName.focus();
        return (false);
    }
  
    return (true);
};

function checkManadatory() {
 var attachmentFileName= document.getElementById('attachmentFileName').value;
 var attachmentName= document.frmAttach.attachmentName;
   if(attachmentFileName.length<=0) {
  alert("Please browse file!");
    document.frmAttach.attachmentFileName.focus();
    return (false);
    }else if(attachmentName.value == null || attachmentName.value == "") {
        alert("Please Enter Attachment Name !");
        document.frmAttach.attachmentName.focus();
        return (false);
    }
  else {
 
    return (true);
    }

}
function getComments(Id) {
    var aId = Id;
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populateComments);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/popupTechCommentsWindow.action?consultantId="+aId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateComments(text) {
    var background = "#3E93D4";
    var title = "Consultant Skillset";
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


//For Techie suggestion list

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

function getConsultantTechReviewsName() {
    var test = document.getElementById("techName").value;

    if (test == "") {
        clearTable();
    } else {
        if (test.length >2) {
            var url = CONTENXT_PATH+"/getTechEmployeeDetails.action?techName="+ escape(test);
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
       // document.frmTechReview.techName.value ="";
      //  document.frmTechReview.referTo.value ="";

       // alert("clrend");
        
      //  document.getElementById('resumeList').style.display = 'none';
       // document.getElementById('activities').style.display = 'none';
        
      //  document.addConsultantForm.save.value = "Add";
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = " ";
        
        for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
}

function parseCustMessages(responseXML) {
    clearTable();
    var consultants = responseXML.getElementsByTagName("TECHIES")[0];
    if (consultants.childNodes.length > 0) {
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");
    } else {
        clearTable();
    }
    if(consultants.childNodes.length<10) {
        autorow1.style.overflowY = "hidden";
        autorow.style.overflowY = "hidden";
    }
    else {
        autorow1.style.overflowY = "scroll";
        autorow.style.overflowY = "scroll";
    }
    var consultant = consultants.childNodes[0];
    var chk=consultant.getElementsByTagName("VALID")[0];
    isExist = chk.childNodes[0].nodeValue;
    if(chk.childNodes[0].nodeValue =="true") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < consultants.childNodes.length; loop++) {
            var consultant = consultants.childNodes[loop];
            var email = consultant.getElementsByTagName("EMAIL")[0];
            var empName = consultant.getElementsByTagName("NAME")[0];

            //Nag -- appended at end of this statement
            appendCustomer(empName.childNodes[0].nodeValue,email.childNodes[0].nodeValue);
           // EmplId = empid.childNodes[0].nodeValue;
        }
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "  Employee doesn't Exists ";
        
      
        //document.getElementById("consultantDiv").value = "Add Consultant";
        
    }
}

function appendCustomer(empName,empMail) {
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
   "javascript:set_cust('"+empName +"','"+ empMail +"')");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}
function set_cust(fiName,eMail){
    clearTable();
    document.frmTechReview.referTo.value =eMail;
    document.frmTechReview.techName.value =fiName;
}

function hideScrollBar() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
}

//for Mail send
function sendMailToTech(Id,conId,referTo,techName) {
    var aId = Id;
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,mailCallBack);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/sendTechMail.action?id="+Id+"&consultantId="+conId+"&referTo="+referTo+"&techName="+techName;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function mailCallBack(text)
{
    var result = text;
    alert(result);
}