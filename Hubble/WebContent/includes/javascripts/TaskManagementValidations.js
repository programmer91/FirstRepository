/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var HRPayrollTitle='HR Payroll Issue';
var HRLeavesTitle='HR Leaves Deduction Issue';
var HRAppraisalsTitle='HR Appraisals Issue';
var HROthersTitle='HR Other Issue';
var HubbleProfile='adoddi@miraclesoft.com';
var HubbleAccesing='vbammidi@miraclesoft.com';
var HubbleRole='skola2@miraclesoft.com';
var HubbleGeneral='vkandregula@miraclesoft.com';
var HubbleProfileTitle='Hubble Profile Issue';
var HubbleAccesingTitle='Hubble Accessing Issue';
var HubbleRoleTitle='Hubble Role Request';
var HubbleGeneralTitle='Hubble General Issue';
var ProjectBug='vkandregula@miraclesoft.com';
var ProjectError='vkandregula@miraclesoft.com';
var ProjectBugTitle='Bug Issue for the project ';
var ProjectErrorTitle='Error Issue for the project ';
var ProjectWorkTitle='Work Issue for the project ';
var ProjectEnhancementTitle='Work Issue for the project ';
var NetworkAcc='adoddi@miraclesosft.com';
var NetworkSystem='vmalla2@miraclesoft.com';
var NetworkIssue='skola2@miraclesoft.com';
var NetworkAccTitle='Network Accessing Issue';
var NetworkSystemTitle='System Issue';
var NetworkIssueTitle='Network Issue';
var InfraHostel1='adoddi@miraclesosft.com';
var InfraHostel2='vmalla2@miraclesoft.com';
var InfraApp1='skola2@miraclesoft.com';
var InfraApp2='vbammidi@miraclesoft.com';
var InfraHostelTitle='Hostel Issue';
var InfraApp1Title='Appartment1 Issue';
var InfraApp2Title='Appartment2 Issue';
var InfraApp3Title='Appartment3 Issue';
var InfraApp4Title='Appartment4 Issue';
var InfraApp5Title='Appartment5 Issue';
var CC1Title = 'CC1 Issue';
var CC2Title = 'CC2 Issue';
var CC3Title = 'CC3 Issue';
var CC4Title = 'CC4 Issue';
var StaffQuartersTitle = 'Staff Quarters Issue';
var CC1PowerRoom= 'CC1 Power Room Issue';
var AppPowerRoom= 'Appartments Power Room Issue';
var CC1pumpHouse = 'CC1 Pump House Issue';
var LbOffice ='LB Office Issue';
var HeightsOffice ='Hieghts Office Issue';
var HeightsPower = 'Heights Power Issue';
var HeightsQuarter = 'Heights Quarter Issue';
var PMO ='PMO Issues';
var Sales='Sales Issues';
var Recuriter='Recuriter Issues';
var Others='Others Issues';
var BridgeIssue='Bridge Issue';
var popup;
var isIE;
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
function getAssignedToBasedOnIssueType(element)
{
     var issue=document.getElementById("issueType").value;
    //var issue=element.value;
    //document.getElementById('primaryAssignTo').value="";
          document.getElementById('title').value="";
    if(document.getElementById("hr").checked==true && issue=='1')
        {
    
    
    document.getElementById('title').value=HRPayrollTitle;
        }
        if(document.getElementById("hr").checked==true && issue=='2')
        {
    
    
        document.getElementById('title').value=HRLeavesTitle;
        }
        if(document.getElementById("hr").checked==true && issue=='3')
        {

    
    document.getElementById('title').value=HRAppraisalsTitle;
        }
        if(document.getElementById("hr").checked==true && issue=='4')
        {
  
    
     document.getElementById('title').value=HROthersTitle;
        }
}

function getAssignedToBasedOnIssueTypeForHubble(element)
{
    var issue=document.getElementById("issueTypeHubble").value;
    //var issue=element.value;
   // document.getElementById('primaryAssignToHubble').value="";
      document.getElementById('titleHubble').value="";
    if(document.getElementById("hubble").checked==true && issue=='1')
        {
    
     document.getElementById('titleHubble').value=HubbleProfileTitle;
        }
        if(document.getElementById("hubble").checked==true && issue=='2')
        {
    
      document.getElementById('titleHubble').value=HubbleAccesingTitle;
        }
        if(document.getElementById("hubble").checked==true && issue=='3')
        {
    
     document.getElementById('titleHubble').value=HubbleRoleTitle;
        }
        if(document.getElementById("hubble").checked==true && issue=='4')
        {
    
      document.getElementById('titleHubble').value=HubbleGeneralTitle;
        }
}

function getAssignedToBasedOnIssueTypeForProject(element)
{
    var issue=document.getElementById("issueTypeProject").value;
   // var issue=element.value;
    //document.getElementById('primaryAssignToProject').value="";
      document.getElementById('titleProject').value="";
      
     var projectId=document.getElementById('projectId').value;
     var projects = document.getElementById('projectId');
    var projectName="";

for (var i = 0; i < projects.length; i++) { // iterate through all option tags
    if (projects[i].value == projectId){ // if this option is selected
      projectName= projects[i].innerHTML; // store key of selected option her
    }
}
    if(document.getElementById("project").checked==true && issue=='1')
        {
   
     document.getElementById('titleProject').value=ProjectBugTitle+projectName;
        }
        if(document.getElementById("project").checked==true && issue=='2')
        {
    
      document.getElementById('titleProject').value=ProjectErrorTitle;
        }
     
}

function getAssignedToBasedOnIssueTypeForNetwork(element)
{
     var issue=document.getElementById("issueTypeNetwork").value;
   // var issue=element.value;
   // document.getElementById('primaryAssignToNetwork').value="";
      document.getElementById('titleNetwork').value="";
      
     
    if(document.getElementById("network").checked==true && issue=='1')
        {
    
     document.getElementById('titleNetwork').value=NetworkSystemTitle;
        }
        if(document.getElementById("network").checked==true && issue=='2')
        {
    
      document.getElementById('titleNetwork').value=NetworkIssueTitle;
        }
        if(document.getElementById("network").checked==true && issue=='3')
        {
    
      document.getElementById('titleNetwork').value=NetworkAccTitle;
        }
}
function getAssignedToBasedOnIssueTypeForInfra(element)
{
      var issue=document.getElementById("issueTypeInfra").value;	
   // var issue=element.value;
   // document.getElementById('primaryAssignToInfra').value="";
      document.getElementById('titleInfra').value="";
    if(document.getElementById("infra").checked==true && issue=='1')
        {
    
     document.getElementById('titleInfra').value=CC1Title;
        }
        if(document.getElementById("infra").checked==true && issue=='2')
        {
    
      document.getElementById('titleInfra').value=CC2Title;
        }
        if(document.getElementById("infra").checked==true && issue=='3')
        {
    
     document.getElementById('titleInfra').value=CC3Title;
        }
        if(document.getElementById("infra").checked==true && issue=='4')
        {
    
      document.getElementById('titleInfra').value=CC4Title;
        }
        if(document.getElementById("infra").checked==true && issue=='5')
        {
    
     document.getElementById('titleInfra').value=InfraHostelTitle;
        }
        if(document.getElementById("infra").checked==true && issue=='6')
        {
    
     document.getElementById('titleInfra').value=StaffQuartersTitle;
        }
        if(document.getElementById("infra").checked==true && issue=='7')
        {
    
     document.getElementById('titleInfra').value=InfraApp1Title;
        }
        if(document.getElementById("infra").checked==true && issue=='8')
        {
    
     document.getElementById('titleInfra').value=InfraApp2Title;
        }
        if(document.getElementById("infra").checked==true && issue=='9')
        {
    
     document.getElementById('titleInfra').value=InfraApp3Title;
        }
        if(document.getElementById("infra").checked==true && issue=='10')
        {
    
     document.getElementById('titleInfra').value=InfraApp4Title;
        }
        if(document.getElementById("infra").checked==true && issue=='11')
        {
    
     document.getElementById('titleInfra').value=InfraApp5Title;
        }
        if(document.getElementById("infra").checked==true && issue=='12')
        {
    
     document.getElementById('titleInfra').value=CC1PowerRoom;
        }
        if(document.getElementById("infra").checked==true && issue=='13')
        {
    
     document.getElementById('titleInfra').value=AppPowerRoom;
        }
        if(document.getElementById("infra").checked==true && issue=='14')
        {
    
     document.getElementById('titleInfra').value=CC1pumpHouse;
        }
        if(document.getElementById("infra").checked==true && issue=='15')
        {
    
     document.getElementById('titleInfra').value=LbOffice;
        }
        if(document.getElementById("infra").checked==true && issue=='16')
        {
    
     document.getElementById('titleInfra').value=HeightsOffice;
        }if(document.getElementById("infra").checked==true && issue=='17')
        {
    
     document.getElementById('titleInfra').value=HeightsPower;
        }
        if(document.getElementById("infra").checked==true && issue=='18')
        {
    
     document.getElementById('titleInfra').value=HeightsQuarter;
        }
        
}

function getTitleBasedOnIssueType()
{
     var issue=document.getElementById("issueType").value;
    var issueRelated=document.getElementsByName('issuerelatedId');
          document.getElementById('title').value="";
        //  alert(issueRelated[0].checked);
         // alert(issue)
    if( issueRelated[0].checked && issue=='1')
        {
           // alert("in 1")
        
    document.getElementById('title').value=HRPayrollTitle;
        }
        if( issueRelated[0].checked && issue=='2')
        {
        document.getElementById('title').value=HRLeavesTitle;
        }
        if( issueRelated[0].checked && issue=='3')
        {

    
    document.getElementById('title').value=HRAppraisalsTitle;
        }
        if( issueRelated[0].checked && issue=='4')
        {
  
    
     document.getElementById('title').value=HROthersTitle;
        }
        
        if( issueRelated[1].checked && issue=='1')
        {
    
     document.getElementById('title').value=HubbleProfileTitle;
        }
        if( issueRelated[1].checked && issue=='2')
        {
    
      document.getElementById('title').value=HubbleAccesingTitle;
        }
        if(issueRelated[1].checked && issue=='3')
        {
    
     document.getElementById('title').value=HubbleRoleTitle;
        }
        if(issueRelated[1].checked && issue=='4')
        {
    
      document.getElementById('title').value=HubbleGeneralTitle;
        }
        
        if(issueRelated[2].checked && issue=='1')
        {
   
     document.getElementById('title').value=ProjectBugTitle;
        }
        if(issueRelated[2].checked && issue=='2')
        {
    
      document.getElementById('title').value=ProjectErrorTitle;
        }
         if(issueRelated[2].checked && issue=='3')
        {
    
      document.getElementById('title').value=ProjectEnhancementTitle;
        }
        if(issueRelated[2].checked && issue=='4')
        {
    
      document.getElementById('title').value=ProjectWorkTitle;
        }
          if(issueRelated[4].checked && issue=='1')
        {
    
     document.getElementById('title').value=CC1Title;
        }
        if(issueRelated[4].checked && issue=='2')
        {
    
      document.getElementById('title').value=CC2Title;
        }
        if(issueRelated[4].checked && issue=='3')
        {
    
     document.getElementById('title').value=CC3Title;
        }
        if(issueRelated[4].checked && issue=='4')
        {
    
      document.getElementById('title').value=CC4Title;
        }
        if(issueRelated[4].checked  && issue=='5')
        {
    
     document.getElementById('title').value=InfraHostelTitle;
        }
        if(issueRelated[4].checked  && issue=='6')
        {
    
     document.getElementById('title').value=StaffQuartersTitle;
        }
        if(issueRelated[4].checked && issue=='7')
        {
    
     document.getElementById('title').value=InfraApp1Title;
        }
        if(issueRelated[4].checked && issue=='8')
        {
    
     document.getElementById('title').value=InfraApp2Title;
        }
        if(issueRelated[4].checked && issue=='9')
        {
    
     document.getElementById('title').value=InfraApp3Title;
        }
        if(issueRelated[4].checked && issue=='10')
        {
    
     document.getElementById('title').value=InfraApp4Title;
        }
        if(issueRelated[4].checked  && issue=='11')
        {
    
     document.getElementById('title').value=InfraApp5Title;
        }
        if(issueRelated[4].checked  && issue=='12')
        {
    
     document.getElementById('title').value=CC1PowerRoom;
        }
        if(issueRelated[4].checked  && issue=='13')
        {
    
     document.getElementById('title').value=AppPowerRoom;
        }
        if(issueRelated[4].checked  && issue=='14')
        {
    
     document.getElementById('title').value=CC1pumpHouse;
        }
        if(issueRelated[4].checked  && issue=='15')
        {
    
     document.getElementById('title').value=LbOffice;
        }
        if(issueRelated[4].checked  && issue=='16')
        {
    
     document.getElementById('title').value=HeightsOffice;
        }if(issueRelated[4].checked  && issue=='17')
        {
    
     document.getElementById('title').value=HeightsPower;
        }
        if(issueRelated[4].checked  && issue=='18')
        {
    
     document.getElementById('title').value=HeightsQuarter;
        }
      if(issueRelated[3].checked  && issue=='1')
        {
    
     document.getElementById('title').value=NetworkSystemTitle;
        }
        if(issueRelated[3].checked  && issue=='2')
        {
    
      document.getElementById('title').value=NetworkIssueTitle;
        }
        if(issueRelated[3].checked  && issue=='3')
        {
    
      document.getElementById('title').value=NetworkAccTitle; 
        }
         if(issueRelated[3].checked  && issue=='4')
        {
    
      document.getElementById('title').value=BridgeIssue; 
      $("#bridgeTr").show();
      $("#bridgeTextTr").show();
      //alert("test");
        }
        else{
              $("#bridgeTr").hide();
              $("#bridgeTextTr").hide();
        }
         if(issueRelated[5].checked  && issue=='1')
        {
    
     document.getElementById('title').value=PMO;
        }
        if(issueRelated[5].checked  && issue=='2')
        {
    
      document.getElementById('title').value=Sales;
        }
        if(issueRelated[5].checked  && issue=='3')
        {
    
      document.getElementById('title').value=Recuriter; 
        }
        if(issueRelated[5].checked  && issue=='4')
        {
    
      document.getElementById('title').value=Others; 
        }
    }
   
    
                        function getTitleBasedOnCusIssueType()
{
     var issue=document.getElementById("issueType").value;
    var issueRelated=document.getElementsByName('issuerelatedId');
          document.getElementById('title').value="";
        //alert(issueRelated[0].checked);
         // alert(issue);
                  if( issueRelated[0].checked && issue=='1')
        {
    
     document.getElementById('title').value=HubbleProfileTitle;
        }
         //alert(issue);
        if( issueRelated[0].checked && issue=='2')
        {
    
      document.getElementById('title').value=HubbleAccesingTitle;
        }
        if(issueRelated[0].checked && issue=='3')
        {
    
     document.getElementById('title').value=HubbleRoleTitle;
        }
        if(issueRelated[0].checked && issue=='4')
        {
    
      document.getElementById('title').value=HubbleGeneralTitle;
        }
             if(issueRelated[1].checked && issue=='1')
        {
   
     document.getElementById('title').value=ProjectBugTitle;
        }
        if(issueRelated[1].checked && issue=='2')
        {
    
      document.getElementById('title').value=ProjectErrorTitle;
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
    var test = document.getElementById("secondaryAssignTo").value;

    if (test == "") {
        clearTable();
    } else {
        if (test.length >2) {
            var url = CONTENXT_PATH+"/getAllEmpNames.action?techName="+ escape(test);
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
            var loginId = consultant.getElementsByTagName("LoginId")[0];
            var empName = consultant.getElementsByTagName("NAME")[0];
            appendCustomer(empName.childNodes[0].nodeValue,loginId.childNodes[0].nodeValue);
        }
          var position;
        position = findPosition(document.getElementById("secondaryAssignTo"));
        
        //var position = findPosition(document.getElementById("assignedToUID"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } 
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "  Employee doesn't Exists ";
    }
}

function appendCustomer(empName,loginId) {
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
   "javascript:set_cust('"+empName +"','"+ loginId +"')");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}
function set_cust(empName,loginId){
    clearTable();
   document.getElementById("secondaryAssignTo").value =empName;
   document.getElementById("secondaryAssignToLoginId").value =loginId;
   
}

function hideScrollBar() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
}





function getAllEmpNamesForPrimaryOthers() {
    var test = document.getElementById("primaryAssignToforOthers").value;

    if (test == "") {
        clearTable();
    } else {
        if (test.length >2) {
            
            var url = CONTENXT_PATH+"/getAllEmpNames.action?techName="+ escape(test);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        parseCustMessagesForPrimaryOthers(req.responseXML);
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


function parseCustMessagesForPrimaryOthers(responseXML) {
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
            var loginId = consultant.getElementsByTagName("LoginId")[0];
            var empName = consultant.getElementsByTagName("NAME")[0];
            appendCustomerForPrimaryOthers(empName.childNodes[0].nodeValue,loginId.childNodes[0].nodeValue);
        }
          var position;
        position = findPosition(document.getElementById("primaryAssignToforOthers"));
        
        //var position = findPosition(document.getElementById("assignedToUID"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } 
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "  Employee doesn't Exists ";
    }
}

function appendCustomerForPrimaryOthers(empName,loginId) {
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
   "javascript:set_custForPrimaryOthers('"+empName +"','"+ loginId +"')");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBarForPrimaryOthers()");
    nameCell.appendChild(linkElement);
}
function set_custForPrimaryOthers(empName,loginId){
    clearTable();
   document.getElementById("primaryAssignToforOthers").value =empName;
   document.getElementById("primaryAssignToLoginIdforOthers").value =loginId;
   
}

function hideScrollBarForPrimaryOthers() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
}




function attachmentFileNameValidate(){
    //alert("attachmentFileNameValidate");
   var attachmentFileName= document.getElementById('uploadFileName').value;
   
       
    if (attachmentFileName.value != null && (attachmentFileName.value != "")) {
        if(attachmentFileName.value.length>40){
                       
                 
               document.getElementById('uploadFileName').value = "";
               alert("File name must be less than 40 characters.Please rename !");
           
              }
       document.getElementById('uploadFileName').focus();
        return (false);
    }
  
    return (true);
}

function getTaskEmpDetailsBasedOnIssueRel(type) {
    clearTable();
     var issueType=document.getElementById("issueType").value;

        getAssignedToBasedOnIssueType(issueType);
   if( document.getElementById("hr").checked==true)
       {
           var type1="hr";   
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler23(req, populateEmployeesList);
    var url = CONTENXT_PATH+"/getTaskEmpDetailsBasedOnIssueRel.action?issueRel="+type1;
    req.open("GET",url,"true");
    //req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
       }
}

function populateEmployeesList(resXML) {
  
    var primaryAssignTo = document.getElementById("primaryAssignTo");

    var employeesList = resXML.getElementsByTagName("EMPLOYEES")[0];
   
    var users = employeesList.getElementsByTagName("USER");
    primaryAssignTo.innerHTML=" ";
  
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("loginId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        primaryAssignTo.appendChild(opt);
        
    }    
}


function getTaskEmpDetailsBasedOnProjIssue(type) {
    clearTable();
       var issueType=document.getElementById("issueType").value;
    //getAssignedToBasedOnIssueTypeForProject(issueType);
    var projectId=document.getElementById("projectId").value;

           var type1="project";   
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler23(req, populateEmployeesListForProject);
    var url = CONTENXT_PATH+"/getTaskEmpDetailsBasedOnPrjIssue.action?issueRel="+type1+"&projectId="+projectId;
    req.open("GET",url,"true");
   // req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   

}

function populateEmployeesListForProject(resXML) {   
    var primaryAssignToProject = document.getElementById("primaryAssignTo");
  //  var secondaryAssignToProject = document.getElementById("secondaryAssignToProject");
    var employeesList = resXML.getElementsByTagName("EMPLOYEES")[0];
    var users = employeesList.getElementsByTagName("USER");
    primaryAssignToProject.innerHTML=" ";
  //  secondaryAssignToProject.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("loginId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        primaryAssignToProject.appendChild(opt);
        
    }

    
}

function getTaskEmpDetailsBasedOnHubbleIssue(type) {
    clearTable();
    var issueType=document.getElementById("issueTypeHubble").value;
    getAssignedToBasedOnIssueTypeForHubble(issueType);
   if( document.getElementById("hubble").checked==true)
       {
           var type1="hubble";      
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler23(req, populateEmployeesListBasedOnHubble);
    var url = CONTENXT_PATH+"/getTaskEmpDetailsBasedOnHubbleNetworkInfraIssue.action?issueRel="+type1;
    req.open("GET",url,"true");
    //req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
       }
}

function populateEmployeesListBasedOnHubble(resXML) {
    var primaryAssignToHubble = document.getElementById("primaryAssignToHubble");
    //var secondaryAssignToHubble = document.getElementById("secondaryAssignToHubble");
    var employeesList = resXML.getElementsByTagName("EMPLOYEES")[0];
    var users = employeesList.getElementsByTagName("USER");
    primaryAssignToHubble.innerHTML=" ";
    //secondaryAssignToHubble.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("loginId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        primaryAssignToHubble.appendChild(opt);
    }
  /*     var opt1 = document.createElement("option");
        opt1.setAttribute("value","");
        opt1.appendChild(document.createTextNode("--Please Select--"));
        secondaryAssignToHubble.appendChild(opt1);
     for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("loginId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        secondaryAssignToHubble.appendChild(opt);
    }*/
}

function getTaskEmpDetailsBasedOnNetworkIssue(type) {
    clearTable();
    var issueType=document.getElementById("issueTypeNetwork").value;
    getAssignedToBasedOnIssueTypeForNetwork(issueType);
 //   alert(type);
   if( document.getElementById("network").checked==true)
       {
           var type1="network";    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler23(req, populateEmployeesListBasedOnNetwork);
    var url = CONTENXT_PATH+"/getTaskEmpDetailsBasedOnHubbleNetworkInfraIssue.action?issueRel="+type1;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
       }
}

function populateEmployeesListBasedOnNetwork(resXML) {
    var primaryAssignToNetwork = document.getElementById("primaryAssignToNetwork");
   // var secondaryAssignToNetwork = document.getElementById("secondaryAssignToNetwork");
    var employeesList = resXML.getElementsByTagName("EMPLOYEES")[0];
    var users = employeesList.getElementsByTagName("USER");
    primaryAssignToNetwork.innerHTML=" ";
  //  secondaryAssignToNetwork.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("loginId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        primaryAssignToNetwork.appendChild(opt);
    }
    /*   var opt1 = document.createElement("option");
        opt1.setAttribute("value","");
        opt1.appendChild(document.createTextNode("--Please Select--"));
        secondaryAssignToNetwork.appendChild(opt1);
     for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("loginId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        secondaryAssignToNetwork.appendChild(opt);
    }*/
    
}

function getTaskEmpDetailsBasedOnInfraIssue(type) {
    clearTable();
      var issueType=document.getElementById("issueTypeInfra").value;
    getAssignedToBasedOnIssueTypeForInfra(issueType);
   if( document.getElementById("infra").checked==true)
       {
           var type1="infra";       
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler23(req, populateEmployeesListBasedOnInfra);
    var url = CONTENXT_PATH+"/getTaskEmpDetailsBasedOnHubbleNetworkInfraIssue.action?issueRel="+type1;
    req.open("GET",url,"true");
   // req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
       }
}

function populateEmployeesListBasedOnInfra(resXML) {
    var primaryAssignToInfra = document.getElementById("primaryAssignToInfra");
   // var secondaryAssignToInfra = document.getElementById("secondaryAssignToInfra");
    var employeesList = resXML.getElementsByTagName("EMPLOYEES")[0];
    var users = employeesList.getElementsByTagName("USER");
    primaryAssignToInfra.innerHTML=" ";
    //secondaryAssignToInfra.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("loginId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        primaryAssignToInfra.appendChild(opt);
    }
   /*  var opt1 = document.createElement("option");
        opt1.setAttribute("value","");
        opt1.appendChild(document.createTextNode("--Please Select--"));
        secondaryAssignToInfra.appendChild(opt1);
     for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("loginId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        secondaryAssignToInfra.appendChild(opt);
    }
    */
}

  
function getAllEmpNamesForHubble() {
    var test = document.getElementById("secondaryAssignToMapForHubble").value;

    if (test == "") {
        clearTable();
    } else {
        if (test.length >2) {
            var url = CONTENXT_PATH+"/getAllEmpNames.action?techName="+ escape(test);
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
            var loginId = consultant.getElementsByTagName("LoginId")[0];
            var empName = consultant.getElementsByTagName("NAME")[0];
            appendCustomer1(empName.childNodes[0].nodeValue,loginId.childNodes[0].nodeValue);
        }
          var position;
        position = findPosition(document.getElementById("secondaryAssignToMapForHubble"));
        
        //var position = findPosition(document.getElementById("assignedToUID"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } 
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "  Employee doesn't Exists ";
    }
}

function appendCustomer1(empName,loginId) {
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
   "javascript:set_cust1('"+empName +"','"+ loginId +"')");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar1()");
    nameCell.appendChild(linkElement);
}
function set_cust1(empName,loginId){
    clearTable();
   document.getElementById("secondaryAssignToMapForHubble").value =empName;
   document.getElementById("secondaryAssignToLoginIdForHubble").value =loginId;
   
}

function hideScrollBar1() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
}

function getAllEmpNamesForProject() {
    var test = document.getElementById("secondaryAssignToMapForProject").value;

    if (test == "") {
        clearTable();
    } else {
        if (test.length >2) {
            var url = CONTENXT_PATH+"/getAllEmpNames.action?techName="+ escape(test);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        parseCustMessages2(req.responseXML);
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



function parseCustMessages2(responseXML) {
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
            var loginId = consultant.getElementsByTagName("LoginId")[0];
            var empName = consultant.getElementsByTagName("NAME")[0];
            appendCustomer2(empName.childNodes[0].nodeValue,loginId.childNodes[0].nodeValue);
        }
          var position;
        position = findPosition(document.getElementById("secondaryAssignToMapForProject"));
        
        //var position = findPosition(document.getElementById("assignedToUID"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } 
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "  Employee doesn't Exists ";
    }
}

function appendCustomer2(empName,loginId) {
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
   "javascript:set_cust2('"+empName +"','"+ loginId +"')");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar2()");
    nameCell.appendChild(linkElement);
}
function set_cust2(empName,loginId){
    clearTable();
   document.getElementById("secondaryAssignToMapForProject").value =empName;
   document.getElementById("secondaryAssignToLoginIdForProject").value =loginId;
   
}

function hideScrollBar2() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
}

function getAllEmpNamesForNetwork() {
    var test = document.getElementById("secondaryAssignToMapForNetwork").value;

    if (test == "") {
        clearTable();
    } else {
        if (test.length >2) {
            var url = CONTENXT_PATH+"/getAllEmpNames.action?techName="+ escape(test);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        parseCustMessages3(req.responseXML);
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



function parseCustMessages3(responseXML) {
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
            var loginId = consultant.getElementsByTagName("LoginId")[0];
            var empName = consultant.getElementsByTagName("NAME")[0];
            appendCustomer3(empName.childNodes[0].nodeValue,loginId.childNodes[0].nodeValue);
        }
          var position;
        position = findPosition(document.getElementById("secondaryAssignToMapForNetwork"));
        
        //var position = findPosition(document.getElementById("assignedToUID"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } 
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "  Employee doesn't Exists ";
    }
}

function appendCustomer3(empName,loginId) {
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
   "javascript:set_cust3('"+empName +"','"+ loginId +"')");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar3()");
    nameCell.appendChild(linkElement);
}
function set_cust3(empName,loginId){
    clearTable();
   document.getElementById("secondaryAssignToMapForNetwork").value =empName;
   document.getElementById("secondaryAssignToLoginIdForNetwork").value =loginId;
   
}

function hideScrollBar3() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
}

function getAllEmpNamesForInfra() {
    var test = document.getElementById("secondaryAssignToMapForInfra").value;

    if (test == "") {
        clearTable();
    } else {
        if (test.length >2) {
            var url = CONTENXT_PATH+"/getAllEmpNames.action?techName="+ escape(test);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        parseCustMessages4(req.responseXML);
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



function parseCustMessages4(responseXML) {
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
            var loginId = consultant.getElementsByTagName("LoginId")[0];
            var empName = consultant.getElementsByTagName("NAME")[0];
            appendCustomer4(empName.childNodes[0].nodeValue,loginId.childNodes[0].nodeValue);
        }
          var position;
        position = findPosition(document.getElementById("secondaryAssignToMapForInfra"));
        
        //var position = findPosition(document.getElementById("assignedToUID"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } 
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "  Employee doesn't Exists ";
    }
}

function appendCustomer4(empName,loginId) {
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
   "javascript:set_cust4('"+empName +"','"+ loginId +"')");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar4()");
    nameCell.appendChild(linkElement);
}
function set_cust4(empName,loginId){
    clearTable();
   document.getElementById("secondaryAssignToMapForInfra").value =empName;
   document.getElementById("secondaryAssignToLoginIdForInfra").value =loginId;
   
}

function hideScrollBar4() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
}

function getIssueTypeBasedOnIssueRel(type)
{
    
clearTable();
         if(type=="5"){
         document.getElementById("othersFlag").value="1";    
         }else {
             document.getElementById("othersFlag").value="0";  
         }
         
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler23(req, populateIssuesList);
    var url = CONTENXT_PATH+"/getIssueTypeBasedOnIssueRel.action?issueRel="+type;
    req.open("GET",url,false);
    //req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);       
    
}

function populateIssuesList(resXML) {
  
    var issueType = document.getElementById("issueType");

    var issuesList = resXML.getElementsByTagName("ISSUESRELATEDTO")[0];
   
    var issue = issuesList.getElementsByTagName("Issue");
    var otherFlag=document.getElementById("othersFlag").value;
    if(otherFlag=="1")
        {
            document.getElementById("others").style.display="block";
            document.getElementById("notOthers").style.display="none";
        }
        else
            {
                document.getElementById("notOthers").style.display="block";
                document.getElementById("others").style.display="none";
            }
    issueType.innerHTML=" ";
  
    for(var i=0;i<issue.length;i++) {
        var issueName = issue[i];
        var att = issueName.getAttribute("IssueTypeId");
        var name = issueName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        issueType.appendChild(opt);
        
    }   
    
    //alert("hii-->"+document.getElementById("empType").value);
    if(document.getElementById("empType").value=="e"){  
      getTitleBasedOnIssueType();
    }
      
    else {
         getTitleBasedOnCusIssueType();
    }     
      
}

    function getTaskEmpDetailsBasedOnIssueRel1(type) {
    clearTable();
     var issueType=document.getElementById("issueType").value;

        //getAssignedToBasedOnIssueType(issueType);
 
         
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler23(req, populateEmployeesList);
    var url = CONTENXT_PATH+"/getTaskEmpDetailsBasedOnIssueRel1.action?issueRel="+type;
    req.open("GET",url,"true");
    //req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
       
}

function populateEmployeesList(resXML) {
  
    var primaryAssignTo = document.getElementById("primaryAssignTo");

    var employeesList = resXML.getElementsByTagName("EMPLOYEES")[0];
   
    var users = employeesList.getElementsByTagName("USER");
    primaryAssignTo.innerHTML=" ";
  
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("loginId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        primaryAssignTo.appendChild(opt);
        
    }    
}



////////////////////////////////////////////////////////////////////////////



function getTaskEmpDetailsBasedOnOthersIssue(type) {
    clearTable();
      var issueType=document.getElementById("issueTypeOthers").value;
    getAssignedToBasedOnIssueTypeForOthers(issueType);
   if( document.getElementById("others").checked==true)
       {
           var type5="others";       
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler23(req, populateEmployeesListBasedOnOthers);
    var url = CONTENXT_PATH+"/getTaskEmpDetailsBasedOnHubbleNetworkOthersIssue.action?issueRel="+type5;
    req.open("GET",url,"true");
   // req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
       }
}

function populateEmployeesListBasedOnOthers(resXML) {
    var primaryAssignToOthers = document.getElementById("primaryAssignToOthers");
   // var secondaryAssignToInfra = document.getElementById("secondaryAssignToInfra");
    var employeesList = resXML.getElementsByTagName("EMPLOYEES")[0];
    var users = employeesList.getElementsByTagName("USER");
    primaryAssignToOthers.innerHTML=" ";
    //secondaryAssignToInfra.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("loginId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        primaryAssignToOthers.appendChild(opt);
    }
}

function getAllEmpNamesForOthers() {
    var test = document.getElementById("secondaryAssignToMapForOthers").value;

    if (test == "") {
        clearTable();
    } else {
        if (test.length >2) {
            var url = CONTENXT_PATH+"/getAllEmpNames.action?techName="+ escape(test);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 5) {
                    if (req.status == 200) {
                        parseCustMessages5(req.responseXML);
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



function parseCustMessages5(responseXML) {
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
            var loginId = consultant.getElementsByTagName("LoginId")[0];
            var empName = consultant.getElementsByTagName("NAME")[0];
            appendCustomer5(empName.childNodes[0].nodeValue,loginId.childNodes[0].nodeValue);
        }
          var position;
        position = findPosition(document.getElementById("secondaryAssignToMapForInfra"));
        
        //var position = findPosition(document.getElementById("assignedToUID"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } 
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "  Employee doesn't Exists ";
    }
}

function appendCustomer5(empName,loginId) {
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
   "javascript:set_cust5('"+empName +"','"+ loginId +"')");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar5()");
    nameCell.appendChild(linkElement);
}
function set_cust5(empName,loginId){
    clearTable();
   document.getElementById("secondaryAssignToMapForOthers").value =empName;
   document.getElementById("secondaryAssignToLoginIdForOthers").value =loginId;
   
}

function hideScrollBar5() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
}







//----------------





function getAllEmpNamesSecondaryOthers() {
    var test = document.getElementById("secondaryAssignToOthers").value;

    if (test == "") {
        clearTable();
    } else {
        if (test.length >2) {
            
            var url = CONTENXT_PATH+"/getAllEmpNames.action?techName="+ escape(test);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        parseCustMessagesForSecondaryOthers(req.responseXML);
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


function parseCustMessagesForSecondaryOthers(responseXML) {
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
            var loginId = consultant.getElementsByTagName("LoginId")[0];
            var empName = consultant.getElementsByTagName("NAME")[0];
            appendCustomerForSecondaryOthers(empName.childNodes[0].nodeValue,loginId.childNodes[0].nodeValue);
        }
          var position;
        position = findPosition(document.getElementById("secondaryAssignToOthers"));
        
        //var position = findPosition(document.getElementById("assignedToUID"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } 
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "  Employee doesn't Exists ";
    }
}

function appendCustomerForSecondaryOthers(empName,loginId) {
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
   "javascript:set_custForSecondaryOthers('"+empName +"','"+ loginId +"')");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBarForSecondaryOthers()");
    nameCell.appendChild(linkElement);
}
function set_custForSecondaryOthers(empName,loginId){
    clearTable();
   document.getElementById("secondaryAssignToOthers").value =empName;
   document.getElementById("secondaryAssignToLoginIdForOthers").value =loginId;
   
}

function hideScrollBarForSecondaryOthers() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
}

function readyStateHandler23(req,responseXmlHandler) {
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



function getIssueTypeBasedOnIssueRelType(type)
{
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler23(req, populateIssuesListForSearch);
    var url = CONTENXT_PATH+"/getIssueTypeBasedOnIssueRel.action?issueRel="+type.value;
    req.open("GET",url,false);
    //req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);       
    
}

function populateIssuesListForSearch(resXML) {
  
    var issueType = document.getElementById("issueType");
   var val=document.getElementById("issueTypehidden").value;
    var issuesList = resXML.getElementsByTagName("ISSUESRELATEDTO")[0];
   
    var issue = issuesList.getElementsByTagName("Issue");
   
   // issueType.innerHTML=" ";
   if(issue.length>0 || document.getElementById('issueRelType').value=='-1'){
 $('#issueType').find('option:gt(0)').remove();
  }
  
    for(var i=0;i<issue.length;i++) {
        var issueName = issue[i];
        var att = issueName.getAttribute("IssueTypeId");
        var name = issueName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        issueType.appendChild(opt);
        
    }
    
      if(val!=''){
        document.getElementById("issueType").value =val; 
     }
}


function readyStateHandlerText(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
              //  (document.getElementById("load2")).style.display = "none";
                responseTextHandler(req.responseText);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
         // alert("HTTP error ---"+req.status+" : "+req.statusText);
           
          //  (document.getElementById("load2")).style.display = "block";
        }
    }
}
function getTaskNotesDetails(action,notesId,taskid){
    document.notesForm.action=action;
       document.getElementById('taskIdOverlayNotes').value=taskid;
        document.getElementById('notesIdOverlayNotes').value=notesId;
     
       
      var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, setTaskNotesDetails);
    var url = CONTENXT_PATH+'/getTaskNotesDetails.action?notesId='+notesId;
   // alert(url);
    req.open("GET",url,false);
    //req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
    
}

function setTaskNotesDetails(res){
 //   alert("res..."+res);
    
    
    document.getElementById('notes').value=res;
    
    var type=document.getElementById('type').value;
    var projectId=document.getElementById('projectId').value;
    
     document.getElementById('resultMessage2').innerHTML='';
       
     
        document.getElementById('typeOverlayNotes').value=type;
        if(projectId==''){
             document.getElementById('projectIdOverlayNotes').value=0;
        }else{
         document.getElementById('projectIdOverlayNotes').value=projectId;
        }
       
       document.getElementById("headerLabel2").style.color="white";
            document.getElementById("headerLabel2").innerHTML="Update Notes ";
            
            var overlay = document.getElementById('overlayTaskNotes');
            var specialBox = document.getElementById('specialBoxTaskNotes');
            overlay.style.opacity = .8;
            if(overlay.style.display == "block"){
                overlay.style.display = "none";
                specialBox.style.display = "none";
            }
               else {
                overlay.style.display = "block";
                specialBox.style.display = "block";
            }
    
}


