//Hari's Code
var isIE;
var completeTable;
var autorow;
var autorow1;
var isExist = "false";
var EmplId;
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

function getElementY(element){
    var targetTop = 0;
    if (element.offsetParent) {
        while (element.offsetParent) {
            targetTop += element.offsetTop;
            element = element.offsetParent;
        }
    } else if (element.y) {
        targetTop += element.y;
    }
    return targetTop;
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

function getConsultant() {
    var test = document.getElementById("email2").value;
    pos = test.indexOf('@');
    pos1 = pos+2;
    test1 = test.substr(0,pos1);
    //alert(test1);
    if (test1 == "") {
        clearTable();
    } else {
        if (test1.length >2) {
            var url = CONTENXT_PATH+"/getConsultantDetails.action?consultantEmail="+ escape(test)+"&dummy="+new Date().getTime();
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
        document.addConsultantForm.fiName.value ="";
        document.addConsultantForm.laName.value ="";
        document.addConsultantForm.miName.value ="";
        document.addConsultantForm.titleTypeId.value ="";
        document.addConsultantForm.gender.value ="1";
        document.addConsultantForm.workPhoneNo.value ="";
        document.addConsultantForm.homePhoneNo.value ="";
        document.addConsultantForm.cellPhoneNo.value ="";
        document.addConsultantForm.workAuthorization.value="1";
        document.addConsultantForm.skills.value ="";
        document.addConsultantForm.ratePerHour.value ="";
        document.addConsultantForm.practiceId.value ="1";
        document.addConsultantForm.country.value ="1";
        document.addConsultantForm.refferedBy.value ="";
        document.addConsultantForm.comments.value ="";
        document.addConsultantForm.industryId.value="";
        document.addConsultantForm.attachmentName.value="";
        document.addConsultantForm.source.value="";
        
        //Nag
        document.addConsultantForm.description.value="";
        document.addConsultantForm.availableFrom.value="";
        
         //Adde by Ajay
document.addConsultantForm.exp.value ="1";
 document.addConsultantForm.techEval.value ="1";
 document.addConsultantForm.available.value ="1";
 document.addConsultantForm.preferredState.value ="1";
        
        
       // alert("clrend");
        
      //  document.getElementById('resumeList').style.display = 'none';
       // document.getElementById('activities').style.display = 'none';
        
        document.addConsultantForm.save.value = "Add";
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = " ";
        
        for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
}

function parseCustMessages(responseXML) {
    clearTable();
    var consultants = responseXML.getElementsByTagName("CONSULTANTS")[0];
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
            var empid = consultant.getElementsByTagName("ID")[0];
            var fname = consultant.getElementsByTagName("FNAME")[0];
            var lname = consultant.getElementsByTagName("LNAME")[0];
            var mname = consultant.getElementsByTagName("MNAME")[0];
            var title = consultant.getElementsByTagName("TITLE")[0];
            var gender = consultant.getElementsByTagName("GENDER")[0];
            var wphone = consultant.getElementsByTagName("WPHONE")[0];
            var hphone = consultant.getElementsByTagName("HPHONE")[0];
            var cphone = consultant.getElementsByTagName("CPHONE")[0];
            var workauth = consultant.getElementsByTagName("WORK")[0];
            var skills = consultant.getElementsByTagName("SKILLS")[0];
            var rate = consultant.getElementsByTagName("RATE")[0];
            var practice = consultant.getElementsByTagName("PRACTICE")[0];
            var country = consultant.getElementsByTagName("COUNTRY")[0];
            var reffer = consultant.getElementsByTagName("REFFER")[0];
            var comments = consultant.getElementsByTagName("COMMENTS")[0];
            var industry = consultant.getElementsByTagName("INDUSTRY")[0];
            var source = consultant.getElementsByTagName("SOURCE")[0];

            //Nag

            var description = consultant.getElementsByTagName("DESCRIPTION")[0];
            var availableFrom = consultant.getElementsByTagName("AVAILABLEFROM")[0];
             var exp = consultant.getElementsByTagName("EXP")[0];
              var techeval = consultant.getElementsByTagName("TECHVAL")[0];
               var available = consultant.getElementsByTagName("AVAILABLE")[0];
                var prefstate = consultant.getElementsByTagName("PREFSTATE")[0];

            //alert("desc"+description+"----------"+availableFrom);
            //alert("mname--"+mname);
            
            if(mname.childNodes[0].nodeValue == 'NoRecord') {
                mname.childNodes[0].nodeValue="";
            }
            
            if(hphone.childNodes[0].nodeValue == 'NoRecord') {
                hphone.childNodes[0].nodeValue="";
            }
            
            if(wphone.childNodes[0].nodeValue == 'NoRecord') {
                wphone.childNodes[0].nodeValue="";
            }
            
            if(title.childNodes[0].nodeValue == 'NoRecord') {
                title.childNodes[0].nodeValue="";
            }
            
            if(cphone.childNodes[0].nodeValue == 'NoRecord') {
                cphone.childNodes[0].nodeValue="";
            }
            
            if(workauth.childNodes[0].nodeValue == 'NoRecord') {
                workauth.childNodes[0].nodeValue="1";
            }
            
            if(skills.childNodes[0].nodeValue == 'NoRecord') {
                skills.childNodes[0].nodeValue="";
            }
            
            if(rate.childNodes[0].nodeValue == 'NoRecord') {
                rate.childNodes[0].nodeValue="";
            }
            
            if(practice.childNodes[0].nodeValue == 'NoRecord') {
                practice.childNodes[0].nodeValue="";
            }
            
            if(reffer.childNodes[0].nodeValue == 'NoRecord') {
                reffer.childNodes[0].nodeValue="";
            }
            
            if(country.childNodes[0].nodeValue == 'NoRecord') {
                country.childNodes[0].nodeValue="1";
            }
            
            if(comments.childNodes[0].nodeValue == 'NoRecord') {
                comments.childNodes[0].nodeValue="";
            }
            
            if(industry.childNodes[0].nodeValue == 'NoRecord') {
                industry.childNodes[0].nodeValue=""; 
            }
            
            if(source.childNodes[0].nodeValue == 'NoRecord') {
                source.childNodes[0].nodeValue="";
            }
            

            //Nag
            if(description.childNodes[0].nodeValue == 'NoRecord') {
                description.childNodes[0].nodeValue="";
            }
            if(availableFrom.childNodes[0].nodeValue == 'NoRecord') {
                availableFrom.childNodes[0].nodeValue="";
            }
            
  //Ajay
             if(techeval.childNodes[0].nodeValue == 'NoRecord') {
                techeval.childNodes[0].nodeValue="1";
            }
            
            if(available.childNodes[0].nodeValue == 'NoRecord') {
                available.childNodes[0].nodeValue="1";
            }
            
            if(prefstate.childNodes[0].nodeValue == 'NoRecord') {
                prefstate.childNodes[0].nodeValue="1";
            }


            //Nag -- appended at end of this statement
            appendCustomer(empid.childNodes[0].nodeValue,email.childNodes[0].nodeValue,fname.childNodes[0].nodeValue,
            lname.childNodes[0].nodeValue,mname.childNodes[0].nodeValue,title.childNodes[0].nodeValue,
            gender.childNodes[0].nodeValue,wphone.childNodes[0].nodeValue,hphone.childNodes[0].nodeValue,
            cphone.childNodes[0].nodeValue,workauth.childNodes[0].nodeValue,skills.childNodes[0].nodeValue,
            rate.childNodes[0].nodeValue,practice.childNodes[0].nodeValue,country.childNodes[0].nodeValue,
            reffer.childNodes[0].nodeValue,comments.childNodes[0].nodeValue,industry.childNodes[0].nodeValue,source.childNodes[0].nodeValue,description.childNodes[0].nodeValue,availableFrom.childNodes[0].nodeValue,
            exp.childNodes[0].nodeValue,techeval.childNodes[0].nodeValue,available.childNodes[0].nodeValue,prefstate.childNodes[0].nodeValue);
            EmplId = empid.childNodes[0].nodeValue;
        }
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "  Consultant Doesn't Exists ";
        
        document.addConsultantForm.action = "addingConsultant.action";
        document.addConsultantForm.save.value = "Add";
        //document.getElementById("consultantDiv").value = "Add Consultant";
        
    }
}

/*Nag start */


/** Nag END */

// add at the end of the function description,availableFrom

function appendCustomer(empId,empMail,fName,lName,mName,titleId,genders,wPhone,hPhone,cPhone,workAuth,skillSet,rateHr,practiceId,country,reffered,comment,indust,source,description,availableFrom,exp,techeval,available,prefstate) {
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
    //Nag Add at the End of the next linea description,availableFrom

    //alert("desc---"+description+"---------"+availableFrom);

    linkElement.setAttribute("href",
   "javascript:set_cust('"+empMail +"','"+ empId +"','"+ fName +"','"+ lName +"','"+ mName +"','"+ titleId +"','"+ genders +"','"+ wPhone +"','"+ hPhone +"','"+ cPhone +"','"+ workAuth +"','"+ skillSet +"','"+ rateHr +"','"+ practiceId +"','"+ country +"','"+ reffered +"','"+ comment +"','"+ indust +"','"+ source +"','"+description+"','"+availableFrom+"','"+exp+"','"+techeval+"','"+available+"','"+prefstate+"')");
    linkElement.appendChild(document.createTextNode(empMail));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}
function set_cust(eMail,eId,fiName,laName,miName,titlId,gend,wPh,hPh,cPh,wAuth,skil,hRate,pract,countr,reffe,commn,industr,source,description,availableFrom,exp,techeval,available,prefstate){
    clearTable();
    document.addConsultantForm.email2.value =eMail;
    document.addConsultantForm.objectId.value =eId;
    document.addConsultantForm.fiName.value =fiName;
    document.addConsultantForm.laName.value =laName;
    document.addConsultantForm.miName.value =miName;
    document.addConsultantForm.titleTypeId.value =titlId;
    document.addConsultantForm.gender.value =gend;
    document.addConsultantForm.workPhoneNo.value =wPh;
    document.addConsultantForm.homePhoneNo.value =hPh;
    document.addConsultantForm.cellPhoneNo.value =cPh;
    document.addConsultantForm.workAuthorization.value=wAuth;
    document.addConsultantForm.skills.value =skil;
    document.addConsultantForm.ratePerHour.value =hRate;
    document.addConsultantForm.practiceId.value =pract;
    document.addConsultantForm.country.value =countr;
    document.addConsultantForm.refferedBy.value =reffe;
    document.addConsultantForm.comments.value =commn;
    document.addConsultantForm.industryId.value =industr;
    document.addConsultantForm.source.value =source;

    //Nag added description,availableFrom
    
    document.addConsultantForm.description.value=description;
        
   //Ajay
 document.addConsultantForm.exp.value =exp;
 document.addConsultantForm.techEval.value =techeval;
 document.addConsultantForm.available.value =available;
 document.addConsultantForm.preferredState.value =prefstate;


        
        

//alert("Before Condition"+availableFrom);
if(availableFrom == "")
{
//alert("in if");
document.addConsultantForm.availableFrom.value="";
}
else
{
//alert("in else");
//alert("availableFrom----------"+availableFrom);
     //date format :: 2012-06-28
    var fromDateSplit =  availableFrom.split("-");
    
      var y1=fromDateSplit[0];
      var m1=fromDateSplit[1];
      var d1=fromDateSplit[2];
      
      // converted format :: 06/28/2012
      var changed_availableFrom = m1+"/"+d1+"/"+y1;

      document.addConsultantForm.availableFrom.value=changed_availableFrom;
}
 
    
    //document.getElementById("consultantDiv").value = "Edit Consultant";
    //alert(document.getElementById("consultantDiv").label);
    document.addConsultantForm.action = "editConsultant.action";
    document.addConsultantForm.save.value = "Update";
    document.getElementById('resumeList').style.display = 'block';
    document.getElementById('activities').style.display = 'block';
}

function hideScrollBar() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
}

function getResumeDetails() {
    document.addConsultantForm.action=CONTENXT_PATH+"/recruitment/viewConsultantDetails.action?consultantId="+EmplId;
}   

function getActivities() {
    document.addConsultantForm.action=CONTENXT_PATH+"/recruitment/consultant/activity.action?consultantId="+EmplId;
}

function getResumeDetails1() {
    var objId1 = document.addConsultantForm.objectId.value;
    document.addConsultantForm.action=CONTENXT_PATH+"/recruitment/viewConsultantDetails.action?consultantId="+objId1;
}

function getActivities1() {
    var objId1 = document.addConsultantForm.objectId.value;
    document.addConsultantForm.action=CONTENXT_PATH+"/recruitment/consultant/activity.action?consultantId="+objId1;
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