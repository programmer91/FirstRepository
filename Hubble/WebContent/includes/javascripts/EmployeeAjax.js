/*Don't Alter these Methods*/
var totalRec=0;
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
function readyStateHandlerXml(req,responseXmlHandler) {
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
    
    var departmentName = document.getElementById("departmentId").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerXml(req, populatePractices);
    var url = CONTENXT_PATH+"/getEmpDepartment.action?departmentName="+departmentName;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populatePractices(resXML) {    
    
    var practiceId = document.getElementById("practiceId");
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
    
    var departmentName = document.employeeForm.departmentId.value;
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateEmpTitles);
    var url = CONTENXT_PATH+"/getEmpForTitles.action?departmentName="+departmentName;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populateEmpTitles(resXML) {
    
    
    var titleId = document.employeeForm.titleId;
    
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
    
    var practiceName = document.getElementById("practiceId").value;

    if(practiceName == 'East' || practiceName == 'West' || practiceName == 'Central'){
        document.getElementById("territory_div").style.display='';
    }else{
        document.getElementById("territory_div").style.display='none';
    }
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerXml(req, populateSubPractices);
    var url = CONTENXT_PATH+"/getEmpPractice.action?practiceName="+practiceName;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}


/*=======================================================
 * 
 *=======================================================
*/
/*START: Methods for Sub Practice Data*/
function getSubPracticeData1(){
    var practiceName = document.getElementById("practiceId").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateSubPractices);
    //var url = CONTENXT_PATH+"/getEmpPractice.action?practiceName="+practiceName;
    var url = CONTENXT_PATH+"/getTerritory.action?practiceName="+practiceName;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}


function populateSubPractices(resXML) {
    // alert(resXML);
    var subPractice = document.getElementById("subPractice");
    
    var practice = resXML.getElementsByTagName("PRACTICE")[0];
    
    var subPractices = practice.getElementsByTagName("SUBPRACTICE");
    subPractice.innerHTML=" ";
    
    for(var i=0;i<subPractices.length;i++) {
        var subPracticeName = subPractices[i];
        
        var name = subPracticeName.firstChild.nodeValue;
        var opt = document.createElement("option");
        if(i==0){
            opt.setAttribute("value","All");
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
    
    var subPracticeName = document.getElementById("subPractice").value;
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateTeams);
    var url = CONTENXT_PATH+"/getEmpSubPractice.action?subPracticeName="+subPracticeName;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populateTeams(resXML) {
    
    var teamId = document.getElementById("teamId");
    var subPractice = resXML.getElementsByTagName("SUBPRACTICE")[0];
    var teams = subPractice.getElementsByTagName("TEAM");
    teamId.innerHTML=" ";
    for(var i=0;i<teams.length;i++) {
        var TeamName = teams[i];
        var name = TeamName.firstChild.nodeValue;
        var opt = document.createElement("option");
        
        if(i==0){
            opt.setAttribute("value","All");
        }else{
            opt.setAttribute("value",name);
        }
        
        opt.appendChild(document.createTextNode(name));
        teamId.appendChild(opt);
    }
}

/*Methods closing Teams by Practices*/

/*Methods for getting TeamMembers by Teams*/

function getTeamMemberData() {
    
    var teamName = document.getElementById("teamId").value;    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateTeamMemeberData);
    var url = CONTENXT_PATH+"/getEmpTeamNames.action?teamName="+teamName;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateTeamMemeberData(resXML) {    
    var teamMemberId = document.getElementById("teamMemberId");
    var team = resXML.getElementsByTagName("TEAM")[0];
    //var teamMember = team.getElementsByTagName("TEAMMEMBER");    
    teamMemberId.innerHTML=" ";
    for(var i=0;i<team.childNodes.length;i++) {
        var teamMember = team.childNodes[i];
        var id =teamMember.getElementsByTagName("TEAMMEMBER-ID")[0];        
        var name = teamMember.getElementsByTagName("TEAMMEMBER-NAME")[0];
        var opt = document.createElement("option");
        opt.setAttribute("value",id.childNodes[0].nodeValue);
        opt.appendChild(document.createTextNode(name.childNodes[0].nodeValue));
        teamMemberId.appendChild(opt);
    }
}

/*Methods closing TeamMembers by Teams*/

/*START: Methods for getting reports to person data*/

function getReportsToDataV1(){
    var deptName = document.employeeForm.departmentId.value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerXml(req, populateReportsTo);
    var url = CONTENXT_PATH+"/getEmpForReportsTo.action?deptName="+deptName;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}

function populateReportsTo(resXML) {
    var reportsTo = document.employeeForm.reportsTo;
    var team = resXML.getElementsByTagName("TEAM")[0];
    var users = team.getElementsByTagName("USER");
    reportsTo.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("userId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        reportsTo.appendChild(opt);
    }
}
/*END: Methods for getting reports to person data*/

function getProjectsForAccountId(){
 
    var accountId = document.getElementById("customerId").value;
 
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateProjects);
    var url = CONTENXT_PATH+"/getProjectsForAccountId.action?accountId="+accountId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}


function populateProjects(resXML) {
  
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





function getSkillSet(empId,currId){
    // alert("empId-->"+empId);
    // alert("currId-->"+currId);
    
    // var departmentName = document.getElementById("departmentId").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, populateSkillset);
    var url = CONTENXT_PATH+"/getEmployeeSkillSet.action?empId="+empId+"&currId="+currId;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function populateSkillset(resText) {
    var background = "#3E93D4";
    var title = "Skill Set";
    // var text1 = text; 
    // var size = text1.length;
    
    
  
    
    var size = resText.length;
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+resText+"<br />\
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
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}
        
//------------------
        
        
        

/*
 * Pmo activity ajax list
 * date : 05/13/2015
 * Author : Santosh Kola
 */

function getpmoActivityList()
{
    var oTable = document.getElementById("tblUpdate");
    clearTable(oTable);
    
     $('span.pagination').empty().remove();
    var NAME=document.getElementById("customerName").value;
    var  ProjectName=document.getElementById("projectName").value;
    var  status=document.getElementById("status").value;                                                            
    var ProjectStartDate=document.getElementById("projectStartDate").value;
    var pmoLoginId = document.getElementById("pmoLoginId").value;
    var preAssignEmpId = document.getElementById("preAssignEmpId").value;
    var practiceId = document.getElementById("practiceId").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerreq(req, displaypmoActivityResult); 

    //var url = CONTENXT_PATH+"/searchpmoActivityAjaxList.action?customerName="+NAME+"&projectName="+ProjectName+"&status="+status+"&projectStartDate="+ProjectStartDate;
    //var url = CONTENXT_PATH+"/searchpmoActivityAjaxList.action?customerName="+NAME+"&projectName="+ProjectName+"&status="+status+"&projectStartDate="+ProjectStartDate+"&pmoLoginId="+pmoLoginId+"&preAssignEmpId="+preAssignEmpId;
    var url = CONTENXT_PATH+"/searchpmoActivityAjaxList.action?customerName="+NAME+"&projectName="+ProjectName+"&status="+status+"&projectStartDate="+ProjectStartDate+"&pmoLoginId="+pmoLoginId+"&preAssignEmpId="+preAssignEmpId+"&practiceId="+practiceId;

    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);


}





function displaypmoActivityResult(resText) 
{
  
    if(resText.length !=0)
    {
        var oTable = document.getElementById("tblUpdate");
       
        clearTable(oTable);
      
            
        // var headerFields = new Array("SNo","NAME","ProjectName","ProjectStartDate","Resources","Status","Activity");	
        // var headerFields = new Array("SNo","ProjectName","Account&nbsp;Name","ProjectStartDate","Resources","Status","Pmo","Activity");	
        var headerFields = new Array("SNo","Account&nbsp;Name","ProjectName","ProjectStartDate","Active&nbsp;Resources","Status","Pmo","Project","ProjectTeam");
               
    
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
       
        var resTextSplit1 = resText.split("^");

        generateTableHeader(tbody,headerFields);
        
         totalRec=resTextSplit1.length-1;
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("|");
            
            generateRow1(tbody,resTextSplit2,index);
            
        }
        generateFooter1(tbody);
       pagerOption();
    }
    else {
        alert("No Records Found");
    }
}
function generateRow1(tableBody,rowFeildsSplit,index){
    var row;
    var cell;
    row = document.createElement("TR");
    row.className="gridRowEven";
    cell = document.createElement("TD");
    cell.className="gridRowEven";
    cell.innerHTML = index+1;
    cell.setAttribute('align','left');
    row.appendChild(cell);
   
    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    cell.appendChild(document.createTextNode(rowFeildsSplit[2]));
    row.appendChild(cell);
   
    cell.setAttribute('align','left');
    cell.setAttribute('width','38%');
        
        
        
    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    cell.appendChild(document.createTextNode(rowFeildsSplit[3]));
    row.appendChild(cell);
    
    cell.setAttribute('align','left');

    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
      
    cell.appendChild(document.createTextNode(rowFeildsSplit[4]));
    row.appendChild(cell);
     
    cell.setAttribute('align','right');

    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
    cell.innerHTML = "<a href='javascript:getResourceTypeDetails(\""+rowFeildsSplit[1]+"\")'>"+rowFeildsSplit[5]+"</a>";
    //cell.appendChild(document.createTextNode(rowFeildsSplit[5]));
    row.appendChild(cell);
    
    cell.setAttribute('align','right');	

    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
        
    cell.appendChild(document.createTextNode(rowFeildsSplit[6]));
    row.appendChild(cell);
     
    cell.setAttribute('align','left');
        
    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
        
    cell.appendChild(document.createTextNode(rowFeildsSplit[7]));
    row.appendChild(cell);
     
    cell.setAttribute('align','left');
        
        
    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
    var j = document.createElement("a");

    j.setAttribute("href", "getCustomerProjectDetails.action?projectId="+rowFeildsSplit[1]+"&accountId="+rowFeildsSplit[8]+"&accountName="+rowFeildsSplit[2]+"&backFlag=1");
           
    j.setAttribute("onmouseover","Tip('"+rowFeildsSplit[4]+"')");
    j.setAttribute("onmouseout","javascript:UnTip();");
    j.innerHTML = "<img SRC='../includes/images/go_21x21.gif' WIDTH=18 HEIGHT=15 BORDER=0 ALTER='Click to Add'>";
         
   
    cell.appendChild(j);
    cell.setAttribute('align','center');
    row.appendChild(cell);


    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
    var j = document.createElement("a");
    j.setAttribute("href", "getProjectTeamQuery.action?projectId="+rowFeildsSplit[1]+"&accountId="+rowFeildsSplit[8]+"&customerName="+escape(rowFeildsSplit[2]));
         
    j.setAttribute("onmouseover","Tip('"+rowFeildsSplit[4]+"')");
    j.setAttribute("onmouseout","javascript:UnTip();");
    j.innerHTML = "<img SRC='../includes/images/go_21x21.gif' WIDTH=18 HEIGHT=15 BORDER=0 ALTER='Click to Add'>";
         
    document.create
    cell.appendChild(j);
    cell.setAttribute('align','center');
    row.appendChild(cell);


    tableBody.appendChild(row);
}



function readyStateHandlerreq(req,responseTextHandler) {

    // alert("ready");
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadingMessage")).style.display = "none";
               
                responseTextHandler(req.responseText);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
          
            (document.getElementById("loadingMessage")).style.display = "block";
        }
    }
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
        row.appendChild( cell );

        cell.setAttribute("width","10000px");
        cell.innerHTML = headerFields[i];
    }
}


function generateFooter(tbody) {
  
    var cell;
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";

    // cell.colSpan = "7";
    
    cell.colSpan = "9";
    
       
   

    footer.appendChild(cell);
}

function clearTable(tableId) {
    var tbl =  tableId;
    var lastRow = tbl.rows.length; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}

function getMyProjectsByAccountId(){
 
    var accountId = document.getElementById("customerId").value;
 
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler33(req, populateMyProjects);
    var url = CONTENXT_PATH+"/getMyProjectsByAccountId.action?accountId="+accountId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}


function populateMyProjects(resXML) {
  
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
function isActiveBridge(){
    $("#correctImg").hide();
    $("#wrongImg").hide();
    var bridgeCode=document.getElementById("bridgeCode").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,isActiveBridgeResponse); 

    //var url=CONTENXT_PATH+"/updateMyReview.action?reviewId="+reviewId+"&overlayReviewType="+overlayReviewType+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay;
    // var url=CONTENXT_PATH+'/upadtePayrollTaxExemption.action?overLayExemptionType='+overLayexemptionType+'&overlayApprovedAmount='+overlayApprovedAmount+'&overLayStatus='+overLaystatus+'&comments='+comments+'&exemptionId='+exemptionId+"&paymentDateEmp="+paymentDateEmp;
    var url=CONTENXT_PATH+'/isActiveBridge.action?bridgeCode='+bridgeCode;
    // alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function isActiveBridgeResponse(resText){
    //alert(resText);
    
    if(resText=='Yes'){
        // document.getElementById("correctImg").style.display="block";
        $("#correctImg").show();
       
    }else{
        //document.getElementById("wrongImg").style.display="block";
        $("#wrongImg").show();
        document.getElementById("bridgeCode").value='';
    }
    
    
}
function taskValidation(){
    var issueRelType=document.getElementById("issueRelType").value;
    var issueType=document.getElementById("issueType").value;
    var bridgeCode=document.getElementById("bridgeCode").value;
    //alert(issueRelType+" "+issueType);
    var chkBoxList = document.getElementsByName("issuerelatedId");
    if(chkBoxList[2].checked){
        // alert("in check cond:")
        var  projectId  =document.getElementById("projectId").value;
        if(projectId==-1){
            alert("please select project name");
            return false;
        }
    }
    if(issueRelType=='3' && issueType=='4'){
        if(bridgeCode==""){
            alert("please enter Bridge Code");
            return false;
        }
    }
    return true;;
}
function getCustomersList()
{
    var oTable = document.getElementById("tblUpdate");
    clearTable(oTable);
    var NAME=document.getElementById("customerName").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerreq(req, displayCustomerProjectsAjaxList); 

        var accountId=document.getElementById('consultantId').value;
        var url = CONTENXT_PATH+"/searchCustomerProjectsAjaxList.action?accId="+accountId;
  //  var url = CONTENXT_PATH+"/searchCustomerProjectsAjaxList.action?customerName="+NAME;

    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);


}
function displayCustomerProjectsAjaxList(resText) 
{
  
    if(resText.length !=0)
    {
        var oTable = document.getElementById("tblUpdate");
       
        clearTableForCustomerProjects(oTable);
      
            
        var headerFields = new Array("SNo","CustomerNAME","No.Of.Projects","Projects List");	
               
    
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
       
        var resTextSplit1 = resText.split("*@!");

        generateTableHeader(tbody,headerFields);
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#^$");
            //alert("resTextSplit2.."+resTextSplit2);
            // clearTable(oTable);
            generateRow2(tbody,resTextSplit2,index);
            
        }
        generateFooter(tbody);
       
    }
    else {
        alert("No Records Found");
    }
    pagerOption();
}

function generateRow2(tableBody,rowFeildsSplit,index){
    var row;
    var cell;
    row = document.createElement("TR");
    row.className="gridRowEven";
    cell = document.createElement("TD");
    cell.className="gridRowEven";
    cell.innerHTML = index+1;
    cell.setAttribute('align','left');
    row.appendChild(cell);
   
    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    cell.appendChild(document.createTextNode(rowFeildsSplit[1]));
    row.appendChild(cell);
   
    cell.setAttribute('align','left');
    cell.setAttribute('width','38%');
        
        
        
        

    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
      
    cell.appendChild(document.createTextNode(rowFeildsSplit[2]));
    row.appendChild(cell);
     
    cell.setAttribute('align','left');


    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
    var j = document.createElement("a");
    j.setAttribute("href", "getCustomerProjectsDetailsList.action?accountId="+rowFeildsSplit[3]+"&customerName="+escape(rowFeildsSplit[1]));
         
           
    j.setAttribute("onmouseover","Tip('"+rowFeildsSplit[4]+"')");
    j.setAttribute("onmouseout","javascript:UnTip();");
    j.innerHTML = "<center><img SRC='../includes/images/go_21x21.gif' WIDTH=18 HEIGHT=15 BORDER=0 ALTER='Click to Add'></center>";
         
    document.create
    cell.appendChild(j);
    cell.setAttribute('align','left');
    row.appendChild(cell);
           

    tableBody.appendChild(row);
}
function clearTableForCustomerProjects(tableId) {
    //alert("clearTable")
    var tbl =  tableId;
    var lastRow = tbl.rows.length; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}

function addProjectToCustomer(){
  
    var accountId=document.getElementById('consultantId').value;
     
    var customerName=document.getElementById('customerName').value;
    if(customerName==""){
        alert("Please select customer name from the suggestion list")
        return false;
    }
    //alert("escape(customerName)"+escape(customerName));
    window.location="getAddProject.action?accountId="+accountId+"&accountName="+escape(customerName)+'&backFlag=3';
    
}

function doAddProjectToCustomer(){
    var accountId=document.getElementById('consultantId').value;
     
    var customerName=document.getElementById('customerName').value;
    var projectName=document.getElementById('projectName').value;
    alert("projectName"+projectName);
    var pmoName=document.getElementById('pmoName').value;
    var projectStartDate=document.getElementById('projectStartDate').value;

    
    window.location="doAddProjectToCustomer.action?accountId="+accountId+"&accountName="+escape(customerName)+"&projectName="+projectName+"&pmoName="+pmoName+"&projectStartDate="+projectStartDate;
}


function doInactiveCustomerProject(projectId,accountId,ProjectEndDate,comments){

    if(ProjectEndDate.trim()!=""){
        if(confirm("Do you want to close the project?")){
            var accountName=document.getElementById('customerName').value;
            // alert("accountName"+accountName)
            window.location="getInActiveCustomerProject.action?projectId="+projectId+"&accountId="+accountId+"&accountName="+escape(accountName)+"&endDateActual="+ProjectEndDate+"&comments="+escape(comments);
        }
    }else{
        alert("Project end date is mandatory to complete the project");
    }
}

function getProjectOverlay(){
    // alert("add");
    //  document.getElementById('resultMessageNoDue').innerHTML = "";
     
    //    document.getElementById("headerLabel").style.color="white";
    //    document.getElementById("headerLabel").innerHTML="Add Event";
 
    var overlay = document.getElementById('empProjectsOverlay');
    var specialBox = document.getElementById('empProjectsSpecialBox');
           
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
       
    } else {
       
        overlay.style.display = "block";
        specialBox.style.display = "block";
        employeeAvailableProjects();
    }
}
function employeeAvailableProjects()
{
    // alert("in ajax");
    var empId=document.getElementById("id").value;
    var status=document.getElementById("status").value;
    var startDate=document.getElementById("projectStartDate").value;
    var endDate=document.getElementById("projectEndDate").value;
    projectCheckDate(startDate);
    // alert(startDate);
    ClrTable(document.getElementById("tblEmpProjectDetails"));
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerreq(req, employeeAvailableProjectsResponse); 

    //var url = CONTENXT_PATH+"/searchpmoActivityAjaxList.action?customerName="+NAME+"&projectName="+ProjectName+"&status="+status+"&projectStartDate="+ProjectStartDate;
    var url = CONTENXT_PATH+"/employeeAvailableProjects.action?empId="+empId+"&status="+status+"&startDate="+startDate+"&endDate="+endDate;

    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);


}

function projectCheckDate() {
                   
    var startDate = document.getElementById('projectStartDate').value;
    var endDate = document.getElementById('projectEndDate').value;
    var mm = startDate.substring(0,2);
    var dd = startDate.substring(3,5);
    var yyyy = startDate.substring(6,10);
    var mm1 = endDate.substring(0,2);
    var dd1 = endDate.substring(3,5);
    var yyyy1 = endDate.substring(6,10);
    if(document.getElementById("projectStartDate").value != '' && document.getElementById("projectEndDate").value != '') {
        if(yyyy1 < yyyy) {
            alert('Start Date is older than End Date');
            document.getElementById('projectEndDate').value = '';
            return false;
        }
        else if((yyyy1 == yyyy) && (mm1 < mm)) {
            alert('Start Date is older than End Date');
            document.getElementById('projectEndDate').value = '';
            return false;
        }
        else if((yyyy1 == yyyy) && (mm1 == mm) && (dd1 < dd)) {
            alert('Start Date is older than End Date');
            document.getElementById('projectEndDate').value = '';
            return false;
        }
    }
}
            




function employeeAvailableProjectsResponse(resText) 
{
  
  
    var tableId = document.getElementById("tblEmpProjectDetails");
    // var headerFields = new Array("SNo","Project&nbsp;Name","StartDate","EndDate","EmpStatus","Utilization","Resource&nbsp;Type");
    var headerFields = new Array("SNo","Project&nbsp;Name","StartDate","EndDate","EmpStatus","Utilization","Billable","Comments");
   
    ParseAndGenerateHTML(tableId,resText, headerFields);

     
     
  
}
function ClrTable(myHTMLTable) { 
    var tbl =  myHTMLTable;
    var lastRow = tbl.rows.length; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}
function ParseAndGenerateHTML(oTable,responseString,headerFields) {
    
    var start = new Date();
    var fieldDelimiter = "|";
    var recordDelimiter = "^";   
    
    if(oTable.id=="tblEmpProjectDetails" || oTable.id == "tblResourceTypeDetails"){
        fieldDelimiter = "#^$";
        recordDelimiter = "*@!"; 
    }
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
        for(var i=0;i<rowlength;i++) {
            if(oTable.id=="tblEmpProjectDetails" || oTable.id == "tblResourceTypeDetails"){
                generateRow(oTable,tbody,records[i],fieldDelimiter);  
            }
            
        }    
    }    
    else {
        generateNoRecords(tbody,oTable);
    }
    
    generateFooter(tbody,oTable);
}
function generateRow(oTable,tableBody,record,delimiter) {
    //alert("In generateRow");
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(delimiter);
    fieldLength = fields.length ;
    var length;
    //if(oTable.id == "tblAccountSummRep" || oTable.id == "tblUpdateForAccountsListByPriority"){
    length = fieldLength;
    //  }
    
    // else {
    //     length = fieldLength-1;
    // }
  
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    // alert("length..."+length);
    if(oTable.id=="tblResourceTypeDetails"){
        for (var i=0;i<length;i++) {
       
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            cell.innerHTML = fields[i];
            if(fields[i]!=''){
                row.appendChild( cell );
            }
       
        }   
    }else{
        for (var i=0;i<length-1;i++) {
       
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            cell.innerHTML = fields[i];
            if(fields[i]!=''){
                row.appendChild( cell );
            }
       
        }
     
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        // cell.innerHTML = fields[8];
        cell.innerHTML ="<CENTER><img SRC='../includes/images/view.jpg' WIDTH=26 HEIGHT=23 BORDER=0 TITLE='"+fields[7]+"' ALTER=''/></CENTER>";
        row.appendChild( cell );
    }
}


function generateNoRecords(tbody,oTable) {
   
    var noRecords =document.createElement("TR");
    noRecords.className="gridRowEven";
    tbody.appendChild(noRecords);
    cell = document.createElement("TD");
    cell.className="gridColumn";
    
    if(oTable.id == "tblEmpProjectDetails"){
        cell.colSpan = "9";
    }else if(  oTable.id == "tblResourceTypeDetails")
    {
        cell.colSpan = "2";   
    }
    cell.innerHTML = "No Records Found for this Search";
    noRecords.appendChild(cell);
}


/***********************************************************
 *PMO suggestion list start
 * ********************************************************/
function EmployeeForProject() {
    var test=document.getElementById("assignedToUID").value;
   
    if (test == "") {

        clearTable1();
        hideScrollBar();
        var validationMessage=document.getElementById("authorEmpValidationMessage");
        validationMessage.innerHTML = "";
        document.frmSearch.preAssignEmpId.value="";
    //frmSearch issuesForm
    } else {
        if (test.length >2) {
            //alert("CONTENXT_PATH-- >"+CONTENXT_PATH)
            var url = CONTENXT_PATH+"/getEmployeeDetailforPMOActivity.action?customerName="+escape(test);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                //    alert("req-->"+req);
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        // alert(req.responseXML);
                    
                        parseEmpMessagesForProject(req.responseXML);
                    } else if (req.status == 204){
                        clearTable1();
                    }
                }
            };
            req.open("GET", url, true);

            req.send(null);
        }
    }
}

function parseEmpMessagesForProject(responseXML) {
    //alert("-->"+responseXML);
    autorow1 = document.getElementById("menu-popup");
    autorow1.style.display ="none";
    autorow = document.getElementById("menu-popup");
    autorow.style.display ="none";
    clearTable1();
    var employees = responseXML.getElementsByTagName("EMPLOYEES")[0];
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
        var position;
        
        
        position = findPosition(document.getElementById("assignedToUID"));
        
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
function appendEmployeeForProject(empId,empName) {
    
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
function set_emp(eName,eID){
    clearTable1();
    document.frmSearch.assignedToUID.value =eName;
    document.frmSearch.preAssignEmpId.value =eID;
}
var isIE;
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
function clearTable1() {
    if (completeTable) {
        completeTable.setAttribute("bordercolor", "white");
        completeTable.setAttribute("border", "0");
        completeTable.style.visible = false;
        for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
}


function hideScrollBar() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
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
/***********************************************************
 *PMO suggestion list end
 * ********************************************************/



/*
 * 
 * Termination Details Overaly scripts start
 * Date : 03/30/2016
 * Author : Teja kadamanti
 */
function checkTerminationDetails(empId,loginId,currStatus){
    if(currStatus=='Inactive'){
        alert("Employee already in Inactive state.");
    }else {
        getTerminationDetails(empId,loginId)
    }
}



function  getTerminationDetails(empId,loginId){
    
   
    $.ajax({
        url:CONTENXT_PATH+"/getTerminationDetails.action?empId="+empId+"&loginId="+loginId,
        context: document.body,
        success: function(responseText) {
            var resTextSplit1 = responseText.split("#^$");
           
            if(resTextSplit1=='reportsToExists'){
                alert("Please change the reports to of his team")  ; 
            }else if(resTextSplit1=='activeInProjects'){
                //     alert("This employee allocated to project please contact to PMO Before Termination! ")  ; 
                alert("This employee is still active in some of the projects .Please contact to PMO Before Termination!")  ; 
            }else{
                setDetails(empId,resTextSplit1);
            }
              
        },
        error: function(e){
            // document.getElementById("loading").style.display = 'none';
            alert("Please try again");
        }
    });
}


function setDetails(empId,resText){
    var today = new Date();
    var dd = today.getDate(); 
    var mm = today.getMonth()+1; 
    // alert("mm"+mm.toString().length);
    if(mm.toString().length==1){
        mm="0"+mm;  
    }
    var yyyy = today.getFullYear(); 
    var now=mm+"/"+dd+"/"+yyyy;
    // alert("now.."+now);
  
    document.getElementById('employeeId').value=empId;
    document.getElementById('designation').value=resText[2];
    document.getElementById('dateOfJoin').value=resText[3];
    if(resText[5]=='Inactive' || resText[5]=='InActive'){
        document.getElementById('dateOfTermination').value=resText[4];
             
    }else if(resText[4]!=null && resText[4]!=""){
        document.getElementById('dateOfTermination').value=resText[4];
    }else{
        document.getElementById('dateOfTermination').value=now;
    }
    if(resText[5]=='Inactive' || resText[5]=='InActive'){
        document.getElementById('reasonsForExit').innerHTML=resText[6];  
    }else if(resText[6]!=null && resText[6]!=""){
        document.getElementById('reasonsForExit').innerHTML=resText[6];  
             
    }else{
        document.getElementById('reasonsForExit').innerHTML="";     
    }
    document.getElementById('employeeName').innerHTML=resText[1];  
    document.getElementById("headerLabel1").style.color="white";
    document.getElementById("headerLabel1").innerHTML="Termination Details";
            
    var overlay = document.getElementById('overlay1');
    var specialBox = document.getElementById('specialBox1');
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


function toggleCloseUploadOverlay2() {
    var overlay = document.getElementById('overlay1');
    var specialBox = document.getElementById('specialBox1');

    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
    document.frmEmpSearch.submit();
            
// window.location="getEmployee.action?empId="+;
}

function toggleCloseUploadOverlayEditPage() {
    var overlay = document.getElementById('overlay1');
    var specialBox = document.getElementById('specialBox1');

    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
    window.location="getEmployee.action?empId="+document.getElementById('id').value;
}

function checkIfStatusInActive(){
    var empId=document.getElementById("id").value;
    var currStatus=document.getElementById("currStatus").value;
    var loginId=document.getElementById("loginId").value;
    var prevCurStatus=document.getElementById("preCurrStatus").value;
    if(currStatus=='Inactive' && currStatus!=prevCurStatus){

        getTerminationDetails(empId,loginId);
    }else{
        // document.getElementById("employeeForm").action="employeeUpdate.action";
        if(empUpdateValidation())
            document.forms["employeeForm"].submit();
    }
     

}

function empUpdateValidation(){
    var country=document.getElementById("country").value;
    var empno=document.getElementById("empno").value;
    var location=document.getElementById("location").value;
    var orgId=document.getElementById("orgId").value;
    var prvExpMnths = document.getElementById("prvexpMnths").value;
    var prvexpYears = document.getElementById("prvexpYears").value;
    var lateralFlag = document.getElementById("lateralFlag");
    var employeeTitleId = document.getElementById("titleId").value;

    //var country=document.getElementById("country").value;
    if(country=='India'){
        if(empno.trim()==''){
            alert("EMPNO is mandatory.");
            return false;
        }
    }else{
        if(empno.trim()==''){
            document.getElementById("empno").value=0;
        }
    }
    
    if(orgId == '' || orgId == null){
        alert("Please select Organization.");
        return false;
    }
    
    // alert(location);
    if(country=='India'||country=='USA'){
        if(location=='-1'){
            alert("Please select location.");
            return false;
        }
    }
    if (isNaN(prvExpMnths)) 
    {
        alert("Please enter numeric values for months");
        return false;
    }
    
    if(parseInt(prvExpMnths,10) > 12 )
    {
            
        alert("Enter values between 1-12");
        return false;
    }
    if (isNaN(prvexpYears)) 
    {
        alert("Please enter numeric values for years");
        return false;
    }
    if(lateralFlag.checked){
        if(prvExpMnths=="" && prvexpYears=="" || prvExpMnths=='0' && prvexpYears=='0' || prvExpMnths==' ' && prvexpYears==' ' || prvExpMnths=='0' && prvexpYears==' ' || prvExpMnths==' ' && prvexpYears=='0'){
           
            alert("enter prev exp values");
            return false;
        }
    }
    //alert(employeeTitleId)
  /*  if(employeeTitleId.trim().length==0){
         alert("Please select Title!");
            return false;
    }*/
    return true;
}



function getTerminationReason(id){
   
    var  reasonsForTerminate=document.getElementById('reasonsForTerminate').value;
  
    var background = "#3E93D4";
    var title = "Reason for exit:";
  
    var size = reasonsForTerminate.length;
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+id.value+"<br />\
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
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}


function  doSaveTermainationDetails(){
    var  empId=document.getElementById('employeeId').value; 
    var  dateOfTermination=document.getElementById('dateOfTermination').value;
    var  reasonsForTerminate=escape(document.getElementById('reasonsForExit').value);
    var size = reasonsForTerminate.length;
    if(dateOfTermination==''){
        document.getElementById('resultMessage2').innerHTML="Date of termination is mandatory";
        document.getElementById('resultMessage2').style.color="red";   
        return false;
    }
    if(size>250){
        document.getElementById('resultMessage2').innerHTML="<font color='red'>Please enter less than 250 characters</font>";
        return false;
    }

    document.getElementById('load').style.display='block';
    // alert("loginId.."+loginId);
    $.ajax({
        url:CONTENXT_PATH+"/doUpdateTerminationDetails.action?empId="+empId+"&dateOfTermination="+dateOfTermination+"&reasonsForTerminate="+reasonsForTerminate,
        context: document.body,
        success: function(responseText) {
            if(responseText=='Success'){
                document.getElementById('load').style.display='none';
                document.getElementById('resultMessage2').innerHTML="Termination Details Updated Successfully";
                document.getElementById('resultMessage2').style.color="green";
            }else{
                document.getElementById('load').style.display='none';
                document.getElementById('resultMessage2').innerHTML="Please try again later";
                document.getElementById('resultMessage2').style.color="red";
            }
              
        },
        error: function(e){
            document.getElementById('load').style.display='none';
            document.getElementById('resultMessage2').innerHTML="Please try again later";
            document.getElementById('resultMessage2').style.color="red";
        }
    });
}


/*
 * termination details overlay end
 * 
 */


function getLocationsByCountry(obj){
     
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateEmployeeLocations);
    var url = CONTENXT_PATH+"/getEmployeeLocations.action?country="+obj.value;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}



function populateEmployeeLocations(resXML) {    
    var locationObj = document.getElementById("location");
    var country = resXML.getElementsByTagName("COUNTRY")[0];
    //var teamMember = team.getElementsByTagName("TEAMMEMBER");    
    locationObj.innerHTML=" ";
    for(var i=0;i<country.childNodes.length;i++) {
        var location = country.childNodes[i];
        var id =location.getElementsByTagName("LOCATION-ID")[0];        
        var name = location.getElementsByTagName("LOCATION-NAME")[0];
        // alert(id.childNodes[0].nodeValue);
        var opt = document.createElement("option");
        opt.setAttribute("value",id.childNodes[0].nodeValue);
        opt.appendChild(document.createTextNode(name.childNodes[0].nodeValue));
        //alert(name.childNodes[0].nodeValue);
        locationObj.appendChild(opt);
    }
}


function readyStateHandler33(req,responseXmlHandler) {
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


function getResourceTypeDetails(projectId){
    var req = newXMLHttpRequest();
   
    req.onreadystatechange = readyStateHandler(req, displayResourceTypeDetails);
    
    var url = CONTENXT_PATH+"/getResourceTypeDetails.action?projectId="+projectId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function displayResourceTypeDetails(response) {
 
    var tableId = document.getElementById("tblResourceTypeDetails");
    ClrTable(tableId);
    var headerFields = new Array("Resource Type","Total");
   
    // alert("responseArray[1]-->"+responseArray[1]);
    //document.getElementById("totalRequirementsFound").innerHTML = responseArray[0];
    var dataArray = response;
    
    //  generateTableHeader(tableId,headerFields)
    ParseAndGenerateHTML(tableId,dataArray, headerFields);
 
 
    document.getElementById("headerLabel1").style.color="white";
    document.getElementById("headerLabel1").innerHTML="Resource Type Details";
            
    var overlay = document.getElementById('overlayResourceType');
    var specialBox = document.getElementById('specialBoxResourceType');
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



function toggleCloseUploadOverlay1() {
    var overlay = document.getElementById('overlayResourceType');
    var specialBox = document.getElementById('specialBoxResourceType');

    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
//window.location="empSearchAll.action";
}



function getTerminationDetailsForWrong(id,loginId){
    
    var empId=document.getElementById("id").value;
    // var empId=id.value;
    var login=document.getElementById("loginId").value;
    // var login=loginId.value;
    $.ajax({
        url:CONTENXT_PATH+"/getTerminationDetailsForInActivePerson.action?empId="+empId+"&loginId="+login,
        context: document.body,
        success: function(responseText) {
            var resTextSplit1 = responseText.split("#^$");
           
            
            setDetails(empId,resTextSplit1);
             
              
        },
        error: function(e){
            // document.getElementById("loading").style.display = 'none';
            alert("Please try again");
        }
    });
}




/*
 * 
 *  Tasks DashBoards Related Methods
 */


function getIssuesDashBoardByStatus()
{
    $('span.pagination').empty().remove();
    var taskStartDate=document.getElementById("taskStartDate").value;
    var taskEndDate=document.getElementById("taskEndDate").value;
    var reportsTo=document.getElementById("reportsTo").value;
    if(taskStartDate.trim().length==0){
        alert("please select start date");
        return false;
    }
    if(taskEndDate.trim().length==0){
        alert("please select end date");
        return false;
    }
    document.getElementById("IssuesDashBoardByStatusPie").style.display="none";
    document.getElementById("IssuesDashBoardByPriorityPie").style.display="none";
    document.getElementById("IssuesDashBoardByAssignmentPie").style.display="none";
    document.getElementById("IssuesDashBoardByCategoryPie").style.display="none";
    var tableId = document.getElementById("tblTasksDashBoard");
    ClrTable(tableId);
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("projectReport").style.display = 'none';
                displayIssuesDashBoardByStatusPieChart(req.responseText); 
                getIssuesDashBoardByPriority();
            } 
        }else {
            document.getElementById("projectReport").style.display = 'block';
        }
    }; 
    var url = CONTENXT_PATH+"/getIssuesDashBoardByStatus.action?reportsTo="+reportsTo+"&taskStartDate="+taskStartDate+"&taskEndDate="+taskEndDate+"&graphId=1";
    
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function displayIssuesDashBoardByStatusPieChart(response) {
    var dataArray = response;
    if(response!=''){
           
        issuesDashBoardByStatusPieChart(response);
   
    }else{
        alert('No Result For This Search...');
        spnFast.innerHTML="No Result For This Search...";                
    }
     
//getIssuesDashBoardByPriority();
}


function issuesDashBoardByStatusPieChart(result){
    document.getElementById("IssuesDashBoardByStatusPie").style.display="block";
      
    var arraydata = [['Status', 'Count']];
    
    var result1=result.split("*@!");
    
    for(var i=0; i<result1.length-1; i++){
        
        var res = result1[i].split("#^$");
        var dArray = [res[0],parseInt(res[1])];
        arraydata.push(dArray);
    }

    var data = google.visualization.arrayToDataTable(arraydata);
    var options = {
        title: 'Tasks by Status' ,
        titleTextStyle: {
            color: ('blue', '#3E93D4'),    // any HTML string color ('red', '#cc00cc')
            fontName: 'Times New Roman', // i.e. 'Times New Roman'
            fontSize: 15, // 12, 18 whatever you want (don't specify px)
            bold: true,    // true or false
            italic: false   // true of false
        },
        legend: 'right',
        chartArea:{
            
            width:"80%",
            height:"80%"
        },
        
        is3D: true,
        pieSliceText: 'value',
        sliceVisibilityThreshold: 0
         
    };
    // align:'center';
    var chart = new google.visualization.PieChart(document.getElementById("IssuesDashBoardByStatusPieChart"));
    function selectHandler() {
             
        var selectedItem = chart.getSelection()[0];
        //alert("selectedItem--"+data.getValue(selectedItem.row, 0));
        if (selectedItem) {
            var activityType = data.getValue(selectedItem.row, 0);
            // getBdmStatisticsDetailsByLoginId(activityType,bdmId);
            getTaskListByStatus(activityType,5);
        }
    }
    google.visualization.events.addListener(chart, 'select', selectHandler);   
    chart.draw(data, options,dArray);
    
    
}	


function getIssuesDashBoardByPriority()
{
    var taskStartDate=document.getElementById("taskStartDate").value;
    var taskEndDate=document.getElementById("taskEndDate").value;
    var reportsTo=document.getElementById("reportsTo").value;
    if(taskStartDate.trim().length==0){
        alert("please select start date");
        return false;
    }
    if(taskEndDate.trim().length==0){
        alert("please select end date");
        return false;
    }
    document.getElementById("IssuesDashBoardByPriorityPie").style.display="none";
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("projectReport").style.display = 'none';
                displayIssuesDashBoardByPriorityPieChart(req.responseText);  
                getIssuesDashBoardByAssignment();
            } 
        }else {
            document.getElementById("projectReport").style.display = 'block';
        }
    }; 
    var url = CONTENXT_PATH+"/getIssuesDashBoardByStatus.action?reportsTo="+reportsTo+"&taskStartDate="+taskStartDate+"&taskEndDate="+taskEndDate+"&graphId=2";
    
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function displayIssuesDashBoardByPriorityPieChart(response) {
    var dataArray = response;
    if(response!=''){
           
        issuesDashBoardByPriorityPieChart(response);
   
    }else{
        alert('No Result For This Search...');
        spnFast.innerHTML="No Result For This Search...";                
    }
    
// getIssuesDashBoardByAssignment();
}


function issuesDashBoardByPriorityPieChart(result){
    document.getElementById("IssuesDashBoardByPriorityPie").style.display="block";
      
    var arraydata = [['Priority', 'Count']];
    
    var result1=result.split("*@!");
    
    for(var i=0; i<result1.length-1; i++){
        
        var res = result1[i].split("#^$");
        var dArray = [res[0],parseInt(res[1])];
        arraydata.push(dArray);
    }

    var data = google.visualization.arrayToDataTable(arraydata);
    var options = {
        title: 'Tasks by Priority' ,
        titleTextStyle: {
            color: ('blue', '#3E93D4'),    // any HTML string color ('red', '#cc00cc')
            fontName: 'Times New Roman', // i.e. 'Times New Roman'
            fontSize: 15, // 12, 18 whatever you want (don't specify px)
            bold: true,    // true or false
            italic: false   // true of false
        },
        legend: 'right',
        chartArea:{
            
            width:"80%",
            height:"80%"
        },
        
        is3D: true,
        pieSliceText: 'value',
        sliceVisibilityThreshold: 0
         
    };
    // align:'center';
    var chart = new google.visualization.PieChart(document.getElementById("IssuesDashBoardByPriorityPieChart"));
    function selectHandler() {
             
        var selectedItem = chart.getSelection()[0];
        //alert("selectedItem--"+data.getValue(selectedItem.row, 0));
        if (selectedItem) {
            var activityType = data.getValue(selectedItem.row, 0);
            // getBdmStatisticsDetailsByLoginId(activityType,bdmId);
            getTaskListByStatus(activityType,6);
        }
    }
    google.visualization.events.addListener(chart, 'select', selectHandler);   
   
    chart.draw(data, options,dArray);
        
    
}


function getIssuesDashBoardByAssignment()
{
    var taskStartDate=document.getElementById("taskStartDate").value;
    var taskEndDate=document.getElementById("taskEndDate").value;
    var reportsTo=document.getElementById("reportsTo").value;
    if(taskStartDate.trim().length==0){
        alert("please select start date");
        return false;
    }
    if(taskEndDate.trim().length==0){
        alert("please select end date");
        return false;
    }
    document.getElementById("IssuesDashBoardByAssignmentPie").style.display="none";
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("projectReport").style.display = 'none';
                displayIssuesDashBoardByAssignmentPieChart(req.responseText);
                getIssuesDashBoardByCategory();
            } 
        }else {
            document.getElementById("projectReport").style.display = 'block';
        }
    }; 
    var url = CONTENXT_PATH+"/getIssuesDashBoardByStatus.action?reportsTo="+reportsTo+"&taskStartDate="+taskStartDate+"&taskEndDate="+taskEndDate+"&graphId=3";
    
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function displayIssuesDashBoardByAssignmentPieChart(response) {
    var dataArray = response;
   
    if(response!=''){
           
        issuesDashBoardByAssignmentPieChart(response);
   
    }else{
        alert('No Result For This Search...');
        spnFast.innerHTML="No Result For This Search...";                
    }
    
//getIssuesDashBoardByCategory();
}


function issuesDashBoardByAssignmentPieChart(result){
    document.getElementById("IssuesDashBoardByAssignmentPie").style.display="block";
      
    var arraydata = [['Assignment', 'Count']];
    
    var result1=result.split("*@!");
    
    for(var i=0; i<result1.length-1; i++){
        
        var res = result1[i].split("#^$");
        var dArray = [res[0],parseInt(res[1])];
        arraydata.push(dArray);
    }

    var data = google.visualization.arrayToDataTable(arraydata);
    var options = {
        title: 'Tasks by Assignmnet' ,
        titleTextStyle: {
            color: ('blue', '#3E93D4'),    // any HTML string color ('red', '#cc00cc')
            fontName: 'Times New Roman', // i.e. 'Times New Roman'
            fontSize: 15, // 12, 18 whatever you want (don't specify px)
            bold: true,    // true or false
            italic: false   // true of false
        },
        legend: 'right',
        chartArea:{
            
            width:"80%",
            height:"80%"
        },
        
        is3D: true,
        pieSliceText: 'value',
        sliceVisibilityThreshold: 0
         
    };
    // align:'center';
    var chart = new google.visualization.PieChart(document.getElementById("IssuesDashBoardByAssignmentPieChart"));

    function selectHandler() {
             
        var selectedItem = chart.getSelection()[0];
        //alert("selectedItem--"+data.getValue(selectedItem.row, 0));
        if (selectedItem) {
            var activityType = data.getValue(selectedItem.row, 0);
            // getBdmStatisticsDetailsByLoginId(activityType,bdmId);
            getTaskListByStatus(activityType,7);
        }
    }
    google.visualization.events.addListener(chart, 'select', selectHandler);   
    chart.draw(data, options,dArray);
    
}


function getIssuesDashBoardByCategory()
{
    var taskStartDate=document.getElementById("taskStartDate").value;
    var taskEndDate=document.getElementById("taskEndDate").value;
    var reportsTo=document.getElementById("reportsTo").value;
    if(taskStartDate.trim().length==0){
        alert("please select start date");
        return false;
    }
    if(taskEndDate.trim().length==0){
        alert("please select end date");
        return false;
    }
    document.getElementById("IssuesDashBoardByCategoryPie").style.display="none";
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("projectReport").style.display = 'none';
                displayIssuesDashBoardByCategoryPieChart(req.responseText);                        
            } 
        }else {
            document.getElementById("projectReport").style.display = 'block';
        }
    }; 
    var url = CONTENXT_PATH+"/getIssuesDashBoardByStatus.action?reportsTo="+reportsTo+"&taskStartDate="+taskStartDate+"&taskEndDate="+taskEndDate+"&graphId=4";
    
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function displayIssuesDashBoardByCategoryPieChart(response) {
    var dataArray = response;
    
   
    if(response!=''){
           
        issuesDashBoardByCategoryPieChart(response);
   
    }else{
        alert('No Result For This Search...');
        spnFast.innerHTML="No Result For This Search...";                
    }
    
//  getResourceUtilizationPieChart();
}


function issuesDashBoardByCategoryPieChart(result){
    document.getElementById("IssuesDashBoardByCategoryPie").style.display="block";
      
    var arraydata = [['Category', 'Count']];
    
    var result1=result.split("*@!");
    
    for(var i=0; i<result1.length-1; i++){
        
        var res = result1[i].split("#^$");
        var dArray = [res[0],parseInt(res[1])];
        arraydata.push(dArray);
    }

    var data = google.visualization.arrayToDataTable(arraydata);
    var options = {
        
        title: 'Tasks by Category' ,
        titleTextStyle: {
            color: ('blue', '#3E93D4'),    // any HTML string color ('red', '#cc00cc')
            fontName: 'Times New Roman', // i.e. 'Times New Roman'
            fontSize: 15, // 12, 18 whatever you want (don't specify px)
            bold: true,    // true or false
            italic: false   // true of false
        },

        legend: 'right',
        chartArea:{
            
            width:"80%",
            height:"80%"
        },
        
        is3D: true,
        pieSliceText: 'value',
        sliceVisibilityThreshold: 0
         
    };
    // align:'center';
    var chart = new google.visualization.PieChart(document.getElementById("IssuesDashBoardByCategoryPieChart"));
    
    function selectHandler() {
             
        var selectedItem = chart.getSelection()[0];
        //alert("selectedItem--"+data.getValue(selectedItem.row, 0));
        if (selectedItem) {
            var activityType = data.getValue(selectedItem.row, 0);
            // getBdmStatisticsDetailsByLoginId(activityType,bdmId);
            getTaskListByStatus(activityType,8);
        }
    }
    google.visualization.events.addListener(chart, 'select', selectHandler);   
   
    chart.draw(data, options,dArray);
    
}

function  getTaskListByStatus(activityType,graphId){
    var taskStartDate=document.getElementById("taskStartDate").value;
    var taskEndDate=document.getElementById("taskEndDate").value;
    var reportsTo=document.getElementById("reportsTo").value;
    if(taskStartDate.trim().length==0){
        alert("please select start date");
        return false;
    }
    if(taskEndDate.trim().length==0){
        alert("please select end date");
        return false;
    }
    var tableId = document.getElementById("tblTasksDashBoard");
    ClrTable(tableId);
    //      var startDate=document.getElementById('startDateSummaryGraph').value;
    //    var endDate=document.getElementById('endDateSummaryGraph').value;
    document.getElementById('projectReport').style.display='block';
   
    var url = CONTENXT_PATH+"/getTaskListByStatus.action?reportsTo="+reportsTo+"&taskStartDate="+taskStartDate+"&taskEndDate="+taskEndDate+"&activityType="+activityType+"&graphId="+graphId;

    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("projectReport").style.display = 'none';
                displayTaskDetailsByStatus(req.responseText,activityType);                        
            } 
        }else {
            document.getElementById("projectReport").style.display = 'block';
        }
    };
    req.open("GET", url, true);
    req.send(null); 
}
 
function displayTaskDetailsByStatus(response,activityType){
    var tableId = document.getElementById("tblTasksDashBoard");  
    var headerFields = new Array("S.No","Title","Status","Severity","StartDate","DueDate","PriAssignTo","SecAssignTo","CreatedBy","Description");
   
    var dataArray = response;
    taskDetailsByStatusParseAndGenerateHTML(tableId,dataArray, headerFields,activityType);
}
 
function taskDetailsByStatusParseAndGenerateHTML(oTable,responseString,headerFields,activityType) {
    var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";   
    var records = responseString.split(recordDelimiter); 
    generateTaskDetailsByStatusRows(oTable,headerFields,records,fieldDelimiter,activityType);
}

function generateTaskDetailsByStatusRows(oTable, headerFields,records,fieldDelimiter,activityType) {	

    var tbody = oTable.childNodes[0];    
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    generateTableHeader(tbody,headerFields);
    var rowlength;
    rowlength = records.length;
    if(rowlength >0 && records!=""){
        for(var i=0;i<rowlength-1;i++) {
                
            generateTaskDetailsByStatus(oTable,tbody,records[i],fieldDelimiter,activityType);
        }
        
    } else {
        generateNoRecords(tbody,oTable);
    }
    generateTaskDashBoardFooter(tbody,oTable);
    pagerOption();
}


function generateTaskDetailsByStatus(oTable,tableBody,record,delimiter,activityType){
    var row;
    var cell;
    var fieldLength;
    //  var fields = record.split(delimiter);
    var fields = record.split("#^$");
    fieldLength = fields.length ;
    var length = fieldLength;
    
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    
    for (var i=0;i<length;i++) {  
        if (i==2){
            cell = document.createElement( "TD" );
            cell.className="gridColumn";    
          
            cell.innerHTML =fields[i] ;
            cell.setAttribute("align","left");   
            row.appendChild( cell );   
        }
        else if(i==9){
            cell = document.createElement( "TD" );
            cell.className="gridColumn";       
            //  alert(i);
            cell.setAttribute("align","center");
            cell.innerHTML = "<a href='javascript:getDescriptionByTaskId("+fields[i]+")'>View</a>";
        }
        else{
            cell = document.createElement( "TD" );
            cell.className="gridColumn";       
       
            cell.setAttribute("align","center");
            
            cell.innerHTML = fields[i]; 
        }
            
        if(fields[i]!=''){
            if(i==1)
            {
                cell.setAttribute("align","left");
            }
            else
            {
                cell.setAttribute("align","center");     
            }
            row.appendChild( cell );
        }
    }   
    row.appendChild( cell );
    
}

function isValidDate(event){
    var date = event.value;
    // var regExp = /^([1-9]|[1][012])\/|-([1-9]|[1][0-9]|[2][0-9]|[3][01])\/|-([1][6-9][0-9][0-9]|[2][0][01][0-9])$/;

    if (date.match(/^(?:(0[1-9]|1[012])[\- \/.](0[1-9]|[12][0-9]|3[01])[\- \/.](19|20)[0-9]{2})$/)){
        // if(regExp.test(event.value)) {   
        return true;
    }else{
        alert("Invalid Date");
        event.value = "";
        
        return false;
    }
}
function generateTaskDashBoardFooter(tbody) {
  
    var cell;
    var footer =document.createElement("TR");
    footer.className="gridPager";
    footer.setAttribute("Id", "taskFooter")
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    

    // cell.colSpan = "7";
    
    cell.colSpan = "10";
    
       
   

    footer.appendChild(cell);
}



function getDescriptionByTaskId(taskId){
    // alert("empId-->"+taskId);
    // alert("currId-->"+currId);
    
    // var departmentName = document.getElementById("departmentId").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, populateDescriptionByTaskId);
    var url = CONTENXT_PATH+"/getDescriptionByTaskId.action?id="+taskId;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function populateDescriptionByTaskId(resText) {
    var background = "#3E93D4";
    var title = "Task description";
    // var text1 = text; 
    // var size = text1.length;
    
    
  
    
    var size = resText.length;
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+resText+"<br />\
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
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}
function getProjectsByAccountId(){
 
    var accountId = document.getElementById("customerName").value;
    if(accountId!=""){
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerXml(req, populateProjectsList);
        var url = CONTENXT_PATH+"/getProjectsByAccountId.action?accountId="+accountId;
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);   
    }else{
        var projects = document.getElementById("projectName");
        projects.innerHTML=" ";
      
        var opt = document.createElement("option");
        opt.setAttribute("value","");
        opt.appendChild(document.createTextNode("All"));
        projects.appendChild(opt);
    }
}
function populateProjectsList(resXML) {
  
    //var projects = document.getElementById("projectName");
    var projects = document.getElementById("projectName");
    //  alert("test")
    
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

function generateFooter1(tbody) {
  
   var cell;
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";

       // cell.colSpan = "7";
    
        cell.colSpan = "9";
    
        cell.innerHTML ="Total&nbsp;Records&nbsp;:"+totalRec;
      
          cell.setAttribute('align','right');
   

    footer.appendChild(cell);
}


/*
 * Appraisals starts
 */
function empQuarterAppraisalAdd(){
    var year=document.getElementById("year").value;
    var quarterly=document.getElementById("quarterly").value;
   
    if(year==""){
        x0p( '','Please Enter Year','info');
        return false;
    }
    if(quarterly==""){
        x0p( '','Please Select Quarter','info');
        return false;
    }
    window.location=CONTENXT_PATH+"/employee/appraisal/myQuarterlyAppraisalAdd.action?year="+year+"&quarterly="+quarterly;
}
function addCommentsForQAppraisal(type,rowCount){
    // alert("type---"+type);
    document.getElementById('resultMessage').innerHTML ='';
    document.getElementById('descriptionCount').innerHTML ='';
    var status=document.getElementById("status").value;
    var curretRole=document.getElementById('curretRole').value;
    var dayCount=document.getElementById('dayCount').value;
    var operationTeamStatus=document.getElementById('operationTeamStatus').value;
   
    /*   if(type=='Employee'){
        hideRow('managerCommentsTr');
        showRow('empDescriptionTr');
        
    }else if(type=='Manager'){
        hideRow('empDescriptionTr');
        showRow('managerCommentsTr');
        
    }*/
    
    // hideRow('editTr');
    //  hideRow("createdTr");
    //  document.getElementById("jobdescription1").value = '';
    //  document.getElementById("jobdescription2").value = '';
    var keyFactorName=document.getElementById("keyFactorName"+rowCount).value;
    document.getElementById("headerLabel").style.color="white";
    if(type=='Employee'){
        document.getElementById("headerLabel").innerHTML=keyFactorName;
    }else  if(type=='Manager'){
        document.getElementById("headerLabel").innerHTML=keyFactorName;
    }
   
    showRow('addTr');
    
    var overlay = document.getElementById('empQuartAppraisalOverlay');
    var specialBox = document.getElementById('empQuartAppraisalSpecialBox');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
        
        if(type=='Employee'){
             
            var desc =  document.getElementById('empDescription'+rowCount).value;
            var placeholder=document.getElementById('empDescription'+rowCount).placeholder;
            if(placeholder.trim()=="_" || placeholder.trim()==""){
                 placeholder="Enter "+keyFactorName+" Description here....";
             }
            document.getElementById('description').value=desc;
            document.getElementById('descriptionId').value='empDescription'+rowCount;
            document.getElementById('description').placeholder=placeholder;
           
         // alert(status+"anand"+curretRole) 
         if(document.getElementById('roleName').value=='Operations'){
                 hideRow('addTr');
               document.getElementById('description').readOnly=true;
         
        }else{
            if(curretRole=="team"){
                hideRow('addTr');
               document.getElementById('description').readOnly=true;
            }else  if(curretRole=="my" && (status.trim()=="Approved" || status.trim()=="Submitted")){
                hideRow('addTr');
                document.getElementById('description').readOnly=true;
            }
            else{
                 document.getElementById('description').readOnly=false;
            }
        }
        }else if(type=='Manager'){
       
            var desc =  document.getElementById('mgrComments'+rowCount).value;
             var placeholder=document.getElementById('mgrComments'+rowCount).placeholder;
            if(placeholder.trim()=="_" || placeholder.trim()==""){
                 placeholder="Enter "+keyFactorName+" Commnets here....";
             }
            document.getElementById('description').value=desc;
            document.getElementById('description').placeholder=placeholder;
            document.getElementById('descriptionId').value='mgrComments'+rowCount;
            if(document.getElementById('roleName').value=='Operations'){
                 hideRow('addTr');
               document.getElementById('description').readOnly=true;
         
        }else{
            if(curretRole=="team"){
                if(status.trim()=="Approved" || status.trim()=="Entered" ){
                    hideRow('addTr');
                    document.getElementById('description').readOnly=true;
                }else{
                    document.getElementById('description').readOnly=false;
                }
                
                if(dayCount==2 && (status=='Submited' || (status=='Approved' && operationTeamStatus=='Rejected'))){
                    showRow('addTr');
                    document.getElementById('description').readOnly=false;
                }
                if(dayCount==1 && status=='Rejected'){
                hideRow('addTr');
                    document.getElementById('description').readOnly=true;
                }
            }else{
                if(status.trim()=="Approved" || status.trim()=="Submitted"){
                    hideRow('addTr');
                    document.getElementById('description').readOnly=true;
                }else{
                     document.getElementById('description').readOnly=false;
                }
            }
            var userId1=document.getElementById('userId1').value;
            if(userId1=='rkalaga'){
                 hideRow('addTr');
                    document.getElementById('description').readOnly=true;
            }
        }
           
        }
    }
            
//window.location = "jobSearch.action";
}
        
function toggleAppraisalOverlay(){
                 
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Add Employee Quarterly Appraisal";
           
    var overlay = document.getElementById('empQuartAppraisalOverlay');
    var specialBox = document.getElementById('empQuartAppraisalSpecialBox');
    //  hideRow("approvedBy");
    //hideRow("tlcommentsTr");
    // hideRow("hrcommentsTr");
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
        document.getElementById("resultMessage").innerHTML='';
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
            
//window.location = "jobSearch.action";
}

function addAppraisalComments(){
    setSessionTimeout();
    var descriptionId=document.getElementById('descriptionId').value;
    var description=document.getElementById('description').value;
    document.getElementById(descriptionId).value=description;
    document.getElementById(descriptionId+"btn").innerHTML="Update";
   document.getElementById("addButton").disabled=true;
  
    document.getElementById("load").style.display='block';
    document.getElementById("resultMessage").innerHTML='';
  //  alert("start");
    if(descriptionId.indexOf("mgrComments")>-1){
       //  alert("descriptionId"); 
         
         var id=descriptionId.replace("mgrComments","id");
         var idValue=document.getElementById(id).value;
           var url = CONTENXT_PATH+"/setQReviewManagerComments.action?id="+idValue+"&comments="+escape(description)+"&rand="+new Date();;
         //  alert(url);
           var req = initRequest(url);
            req.onreadystatechange = function() {
                //    alert("req-->"+req);
                if (req.readyState == 4) {
                    if (req.status == 200) {
                         //alert(req.responseText);
                         if(req.responseText=='success'){
                             document.getElementById("resultMessage").innerHTML="<font color=\"green\" size=\"2\">Comment saved successfully!</font>";
                         }else{
                             document.getElementById("resultMessage").innerHTML="<font color=\"red\" size=\"2\">Sorry! Please try again latter!</font>";
                         }
                   document.getElementById("load").style.display='none';
                    document.getElementById("addButton").disabled=false;
   
                       // parseEmpMessagesForProject(req.responseXML);
                    } 
                }
            };
            req.open("GET", url, true);

            req.send(null);
    }else{
         document.getElementById("finalSaveButton").disabled=true;
        var lineId=document.getElementById("lineId").value;
        var empId=document.getElementById("empId").value;
        var shortTermGoal=document.getElementById("shortTermGoal").value;
        var longTermGoal=document.getElementById("longTermGoal").value;
        var strength=document.getElementById("strength").value;
        var improvements=document.getElementById("improvements").value;
        var quarterly=document.getElementById("quarterly").value;
        
        if(lineId=='0'){
            
         var rowCount = $("#measurementTable td").closest("tr").length;
         document.getElementById("rowCount").value=rowCount;
         var measurementId="";
                var keyFactorId="";
                var keyFactorWeightage="";
                var empDescription="";
                var empRating="";
                var id="";
          if(rowCount>0){
               for(var i=1;i<=rowCount;i++){
                    measurementId=measurementId+ document.getElementById("measurementId"+i).value+"#^$";
                        keyFactorId=keyFactorId+ document.getElementById("keyFactorId"+i).value+"#^$";
                        keyFactorWeightage=keyFactorWeightage+ document.getElementById("keyFactorWeightage"+i).value+"#^$";
                        empDescription=empDescription+ document.getElementById("empDescription"+i).value+"#^$";
                        if(document.getElementById("empRating"+i).value==""){
                             empRating=empRating+"0"+"#^$";
                        }else{
                        empRating=empRating+ document.getElementById("empRating"+i).value+"#^$";
                        }
                         if( document.getElementById("id"+i).value==""){
                             id=id+"0"+"#^$";
                        }else{
                        id=id+ document.getElementById("id"+i).value+"#^$";
                        }
                       
                    
               }
          }
    
        document.getElementById("status").value="Entered";
                    //document.getElementById("frmAppraisal").submit(); 
          var url = CONTENXT_PATH+"/saveQReviewDetails.action?ids="+escape(id)+"&appraisalId=0&empId="+empId+"&measurementId="+escape(measurementId)+"&keyFactorId="+escape(keyFactorId)+"&keyFactorWeightage="+escape(keyFactorWeightage)+"&empDescription="+escape(empDescription)+"&empRating="+escape(empRating)+"&status=Entered&shortTermGoal="+escape(shortTermGoal)+"&longTermGoal="+escape(longTermGoal)+"&strength="+escape(strength)+"&improvements="+escape(improvements)+"&quarterly="+quarterly+"&rowCount="+rowCount+"&rand="+new Date();;
         //  alert(url);
           var req = initRequest(url);
            req.onreadystatechange = function() {
                //    alert("req-->"+req);
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        // alert(req.responseText);
                     var  resultJson = JSON.parse(req.responseText);
                    var QuarterAppraisalDetails = resultJson["QuarterAppraisalDetails"];
                  //  alert(QuarterAppraisalDetails);
                    var LineId = resultJson["LineId"];
                    var AppraisalId = resultJson["AppraisalId"];
                    var individualRowDetails=QuarterAppraisalDetails.split("*@!");
                  //  alert(individualRowDetails.length);
                    for (i = 1; i < individualRowDetails.length; i++) {
                                                    var individualColumn = individualRowDetails[i - 1].split("#^$");
                                                 //   alert(i+"--"+ individualColumn[11]);
                            document.getElementById("id"+i).value=individualColumn[11];                        
                    }
                    
                    document.getElementById("lineId").value=LineId;
                    document.getElementById("appraisalId").value=AppraisalId;
                    document.getElementById("status").value="Entered";
                    
                   // alert("id=="+document.getElementById("id10").value);
                       // parseEmpMessagesForProject(req0.responseXML);
                       var result= resultJson["result"];
                       if(result=='success'){
                            document.getElementById("resultMessage").innerHTML="<font color=\"green\" size=\"2\">Description saved successfully!</font>";
                         }else{
                            document.getElementById("resultMessage").innerHTML='<font color=\"red" size=\"2\">Sorry! Please try again latter!</font>';
                       }
                    document.getElementById("finalSaveButton").disabled=false;    
                document.getElementById("addButton").disabled=false;
    document.getElementById("load").style.display='none';    
               }
                }
            };
            req.open("GET", url, true);

            req.send(null);
        }else{
              
         var id=descriptionId.replace("empDescription","id");
         var idValue=document.getElementById(id).value;
           var url = CONTENXT_PATH+"/setQReviewEmpDescriptions.action?id="+idValue+"&comments="+escape(description)+"&rand="+new Date();;
         //  alert(url);
           var req = initRequest(url);
            req.onreadystatechange = function() {
                //    alert("req-->"+req);
                if (req.readyState == 4) {
                    if (req.status == 200) {
                         //alert(req.responseText);
                         if(req.responseText=='success'){
                             document.getElementById("resultMessage").innerHTML="<font color=\"green\" size=\"2\">Description saved successfully!</font>";
                         }else{
                             document.getElementById("resultMessage").innerHTML=innerHTML="<font color=\"red\" size=\"2\">Sorry! Please try again latter!</font>";
                         }
                         document.getElementById("finalSaveButton").disabled=false;
                    document.getElementById("addButton").disabled=false;
    document.getElementById("load").style.display='none';
                    
                       // parseEmpMessagesForProject(req.responseXML);
                    }
                }
            };
            req.open("GET", url, true);

            req.send(null);
        }
    }
   
    //alert("test")
  //  toggleAppraisalOverlay();
}
function isNumeric(element){
    
    var val=element.value;
    if (isNaN(val)) {
        x0p( '','Please Enter numeric values','info');
        //   element.value=val.substring(0, val.length-1);      
        element.value="";      
        return false;
    }
    else if( val< 0 || val >10){
        x0p( '','Please Enter numeric values between 1 - 10 ','info');
        element.value="";      
        return false;
    }else
        return true;
}
function isNumericYear(element){
    
    var val=element.value;
    if (isNaN(val)) {
        x0p( '','Please Enter numeric values','info');
        //   element.value=val.substring(0, val.length-1);      
        element.value="";      
        return false;
    }
    else
        return true;
}
function weightageCalculation(rowCount){  
   
    
    var w1=document.getElementById("appraisalManagerRating"+rowCount).value;
    var w2=document.getElementById("keyFactorWeightage"+rowCount).value;
    if(w1)
        var w3=(parseFloat(w1)/10)*parseFloat(w2);
    if (isNaN(w3)) {
        document.getElementById("calWeightage"+rowCount).value="";
    }else{
        document.getElementById("calWeightage"+rowCount).value=parseFloat(w3).toFixed(2);
    }
   
    //return (num/100)*per;  
    totalQuarterCalculations();
} 



function totalQuarterCalculations(){
  
    var array = ["keyFactorWeightage", "empRating", "appraisalManagerRating","calWeightage"];
    var keyFactorWeightageArray=[];
    var appraisalEmpRatingArray=[];
    var appraisalManagerRatingArray=[];
    var calWeightageArray=[];
    var keyFactorWeightageArrayValues=[];
    var appraisalEmpRatingArrayValues=[];
    var appraisalManagerRatingArrayValues=[];
    var calWeightageArrayValues=[];
    var curretRole=document.getElementById("curretRole").value;
    var rowCount = $("#measurementTable td").closest("tr").length;
      var status=document.getElementById("status").value;
    for(var i=1;i<=rowCount;i++){
        keyFactorWeightageArray[(i-1)]=array[0]+i;
        appraisalEmpRatingArray[(i-1)]=array[1]+i;
        if(curretRole=="team" || status.trim()=='Approved'){
            appraisalManagerRatingArray[(i-1)]=array[2]+i;
            calWeightageArray[(i-1)]=array[3]+i;
        }
       
        if(document.getElementById(keyFactorWeightageArray[i-1]).value==""){
            keyFactorWeightageArrayValues[(i-1)]=0;
        }else{
            keyFactorWeightageArrayValues[(i-1)]=document.getElementById(keyFactorWeightageArray[i-1]).value;
        }
        
        if(document.getElementById(appraisalEmpRatingArray[i-1]).value==""){
            appraisalEmpRatingArrayValues[(i-1)]=0;
        }else{
            appraisalEmpRatingArrayValues[(i-1)]=document.getElementById(appraisalEmpRatingArray[i-1]).value;
        }
      
        if(curretRole=="team" || status.trim()=='Approved'){
       
            if(document.getElementById(appraisalManagerRatingArray[i-1]).value==""){
                appraisalManagerRatingArrayValues[(i-1)]=0;
            }else{
                appraisalManagerRatingArrayValues[(i-1)]=document.getElementById(appraisalManagerRatingArray[i-1]).value;
            }
        
            if(document.getElementById(calWeightageArray[i-1]).value==""){
                calWeightageArrayValues[(i-1)]=0;
            }else{
                calWeightageArrayValues[(i-1)]=document.getElementById(calWeightageArray[i-1]).value;
            }
        }
       
    }
    var totalKeyFactorCount=0;
    var totalEmpRatingCount=0;
    var totalMgrRatingCount=0;
    var totalCalWeightage=0;
  
    for(var i=0;i<rowCount;i++){
        //  alert(keyFactorWeightageArrayValues[i]+"---"+calWeightageArrayValues[i]);
        totalKeyFactorCount=totalKeyFactorCount+parseFloat(keyFactorWeightageArrayValues[i]);
        totalEmpRatingCount=totalEmpRatingCount+parseFloat(appraisalEmpRatingArrayValues[i]);
        if(curretRole=="team" || status.trim()=='Approved'){
            totalMgrRatingCount=totalMgrRatingCount+parseFloat(appraisalManagerRatingArrayValues[i]);
            totalCalWeightage=totalCalWeightage+parseFloat(calWeightageArrayValues[i]);
        }
    }
     
    document.getElementById("totalKeyFactor").value=parseFloat(totalKeyFactorCount).toFixed(2);
    document.getElementById("totalEmpRating").value=parseFloat(totalEmpRatingCount).toFixed(2);
    if(curretRole=="team" || status.trim()=='Approved'){
         
        document.getElementById("totalMgrRating").value=parseFloat(totalMgrRatingCount).toFixed(2);
        document.getElementById("totalCalWeightage").value=parseFloat(totalCalWeightage).toFixed(2);
    }
    
}

function doAddEmpQuarterAppraisal(){
    var rowCount = $("#measurementTable td").closest("tr").length;
    document.getElementById("rowCount").value=rowCount;
    // window.location=CONTENXT_PATH+"/employee/appraisal/empQuarterlyAppraisalAdd.action";
    document.forms["frmAppraisal"].submit();
}
function quarterAppraisalAdd(status,buttonObj){
    var rowCount = $("#measurementTable td").closest("tr").length;
    document.getElementById("rowCount").value=rowCount;
   
    var result = false;
    if(status=="Submitted" || status=="Resubmitted"){
        for(var i =1;i<=rowCount;i++){
            var empDescription=document.getElementById("empDescription"+i);
            var empRating=document.getElementById("empRating"+i);
            var keyFactorName=document.getElementById("keyFactorName"+i).value;
            
            if(keyFactorName!='Appreciations & Awards'){
            if(empDescription.value.trim()==""){
                x0p({
                    title:'',
                    text:'Please enter <b style="color:red">description</b> for <b>'+keyFactorName+'</b>',
                    icon: '',
                    html: true
                });
                empDescription.focus();
                return false;
            }
            if(empRating.value.trim()==""){
                //    x0p( '','Please Enter rating for '+keyFactorName,'info');
             
                x0p({
                    title:'',
                    text:'Please enter <b style="color:red">rating</b> for <b>'+keyFactorName+'</b>',
                    icon: 'info',
                    html: true
                });
                empRating.focus();
                return false;
            }
        }
        }
        var shortTermGoal=document.getElementById("shortTermGoal").value;
        var longTermGoal=document.getElementById("longTermGoal").value;
        var strength=document.getElementById("strength").value;
        var improvements=document.getElementById("improvements").value;
    
        if(strength.trim()==""){
            x0p({
                title:'',
                text:'Please enter  <b style="color:red">personality details</b> (Click on personality button to enter stregnths)',
                icon: 'info',
                html: true
            });
            return false
        }
        if(improvements.trim()==""){
            x0p({
                title:'',
                text:'Please enter <b style="color:red">personality details</b> (Click on personality button to enter improvements)',
                icon: 'info',
                html: true
            });
            return false
        }
    
        if(shortTermGoal.trim()==""){
            x0p({
                title:'',
                text:'Please enter <b style="color:red">goals</b> (Click on Goals button to enter short term goals)',
                icon: 'info',
                html: true
            });
            return false
        }
        if(longTermGoal.trim()==""){
            x0p({
                title:'',
                text:'Please enter <b style="color:red">goals</b> (Click on Goals button to enter long term goals)',
                icon: 'info',
                html: true
            });
            return false
        }
   
        x0p(' Do you want to submit quarterly review?', '', 'warning',
            function(button) {
       
                if(button == 'warning') {
                    result = true;
                //alert(button)
                }
                if(button == 'cancel') { 
                    result = false;
                }
                if(result){
                   document.getElementById("status").value=status;
                    document.getElementById("frmAppraisal").submit();
                }

            }
            );
    }
    else if(status=="Approved" || status=="Reapproved"){
        for(var i =1;i<=rowCount;i++){
            var mgrComments=document.getElementById("mgrComments"+i);
            var appraisalManagerRating=document.getElementById("appraisalManagerRating"+i);
            var keyFactorName=document.getElementById("keyFactorName"+i).value;
             if(keyFactorName!='Appreciations & Awards'){
            if(mgrComments.value.trim()==""){
                x0p({
                    title:'',
                    text:'Please enter <b style="color:red">manager comments</b> for <b>'+keyFactorName+'</b>',
                    icon: '',
                    html: true
                });
                mgrComments.focus();
                return false;
            }
            if(appraisalManagerRating.value.trim()==""){
                //    x0p( '','Please Enter rating for '+keyFactorName,'info');
             
                x0p({
                    title:'',
                    text:'Please enter <b style="color:red">manager rating</b> for <b>'+keyFactorName+'</b>',
                    icon: 'info',
                    html: true
                });
                empRating.focus();
                return false;
            }
             }
        }
        var shortTermGoalComments=document.getElementById("shortTermGoalComments").value;
        var longTermGoalComments=document.getElementById("longTermGoalComments").value;
        var strengthsComments=document.getElementById("strengthsComments").value;
        var improvementsComments=document.getElementById("improvementsComments").value;
    
        if(strengthsComments.trim()==""){
            x0p({
                title:'',
                text:'Please update <b style="color:red">personality comments</b> (Click on personality button to enter stregnths)',
                icon: 'info',
                html: true
            });
            return false
        }
        if(improvementsComments.trim()==""){
            x0p({
                title:'',
                text:'Please update <b style="color:red">personality comments</b> (Click on personality button to enter improvements)',
                icon: 'info',
                html: true
            });
            return false
        }
    
        if(shortTermGoalComments.trim()==""){
            x0p({
                title:'',
                text:'Please update <b style="color:red">goals comments</b> (Click on Goals button to enter short term goals)',
                icon: 'info',
                html: true
            });
            return false
        }
        if(longTermGoalComments.trim()==""){
            x0p({
                title:'',
                text:'Please enter <b style="color:red">goals comments</b> (Click on Goals button to enter long term goals)',
                icon: 'info',
                html: true
            });
            return false
        }
        x0p(' Do you want to Approve quarterly review?', '', 'warning',
            function(button) {
       
                if(button == 'warning') {
                    result = true;
                //alert(button)
                }
                if(button == 'cancel') { 
                    result = false;
                }
                if(result){
                     document.getElementById("status").value=status;
                      buttonObj.disabled=true;
                    document.getElementById("frmAppraisal").submit();
                   
                }

            }
            );
    
 
    }
    else if(status=="Rejected"){
        
        var rejectedComments=document.getElementById("rejectedComments").value;
    
        if(rejectedComments.trim()==""){
            x0p({
                title:'',
                text:'Please enter <b style="color:red">rejected comments.</b>',
                icon: 'info',
                html: true
            });
            return false
        }else{
             document.getElementById("status").value=status;
              buttonObj.disabled=true;
            document.getElementById("frmAppraisal").submit(); 
        }
    }
    else if(status=="Closed"){
         x0p(' Are you sure to close the the quarterly review ?', '', 'warning',
            function(button) {
       
                if(button == 'warning') {
                    result = true;
                //alert(button)
                }
                if(button == 'cancel') { 
                    result = false;
                }
                if(result){
                     document.getElementById("status").value=status;
                      buttonObj.disabled=true;
                    document.getElementById("frmAppraisal").submit();
                   
                }

            }
            );
    }
    else{
         document.getElementById("status").value=status;
          buttonObj.disabled=true;
        document.getElementById("frmAppraisal").submit();  
    }
   
   
}

function addGoalsOverlay(){
 
     
  
    showRow('shortTeamGoalCommentsTr');
    showRow('longTeamGoalCommentsTr');
     var status=document.getElementById("status").value;
     var curretRole=document.getElementById('curretRole').value;
      var dayCount=document.getElementById('dayCount').value;
    var operationTeamStatus=document.getElementById('operationTeamStatus').value;
    //   document.getElementById('strengthsComments').readOnly=false;
    //   document.getElementById('improvementsComments').readOnly=false;
    
    var overlay = document.getElementById('goalsOverlay');
    var specialBox = document.getElementById('goalsSpecialBox');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
        document.getElementById("resultMessageGoal").innerHTML='';
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
        if(document.getElementById('roleName').value=='Operations'){
             document.getElementById('shortTermGoal').readOnly=true;
                document.getElementById('longTermGoal').readOnly=true;
            document.getElementById('shortTermGoalComments').readOnly=true;
                document.getElementById('longTermGoalComments').readOnly=true;
                hideRow('goalsBtnTr');
        }else{
        if(curretRole=='my'){
            hideRow('shortTeamGoalCommentsTr');
            hideRow('longTeamGoalCommentsTr');
            if(status.trim()=="Submitted" || status.trim()=="Approved"){
                document.getElementById('shortTermGoal').readOnly=true;
                document.getElementById('longTermGoal').readOnly=true;
                hideRow('goalsBtnTr');
            }else{
                document.getElementById('shortTermGoal').readOnly=false;
                document.getElementById('longTermGoal').readOnly=false;
                showRow('goalsBtnTr');
            }
        }else if(curretRole=='team'){
              if(status.trim()=="Approved" || status.trim()=="Entered" ){
                document.getElementById('shortTermGoalComments').readOnly=true;
                document.getElementById('longTermGoalComments').readOnly=true;
                hideRow('goalsBtnTr');
            }else{
                document.getElementById('shortTermGoalComments').readOnly=false;
                document.getElementById('longTermGoalComments').readOnly=false;
                showRow('goalsBtnTr');
            }
            if(dayCount==2 && (status=='Submited' || (status=='Approved' && operationTeamStatus=='Rejected'))){
                document.getElementById('shortTermGoalComments').readOnly=false;
                document.getElementById('longTermGoalComments').readOnly=false;
                showRow('goalsBtnTr');
            }
             if(dayCount==1 && status=='Rejected'){
               document.getElementById('shortTermGoalComments').readOnly=true;
                document.getElementById('longTermGoalComments').readOnly=true;
                hideRow('goalsBtnTr');
            }
        }
         var userId1=document.getElementById('userId1').value;
            if(userId1=='rkalaga'){
                 document.getElementById('shortTermGoalComments').readOnly=true;
                document.getElementById('longTermGoalComments').readOnly=true;
                hideRow('goalsBtnTr');
            }
        }
    }
    document.getElementById('shortTermGoalCount').innerHTML ='';
    document.getElementById('shortTermGoalCommentsCount').innerHTML ='';
    document.getElementById('longTermGoalCount').innerHTML ='';
    document.getElementById('longTermGoalCommentsCount').innerHTML ='';   
//window.location = "jobSearch.action";
}
  
function saveGoals(){
    setSessionTimeout();
    var shortTermGoal=document.getElementById("shortTermGoal").value;
    var shortTermGoalComments=document.getElementById("shortTermGoalComments").value;
    var longTermGoal=document.getElementById("longTermGoal").value;
    var longTermGoalComments=document.getElementById("longTermGoalComments").value;
    var curretRole=document.getElementById("curretRole").value;
    document.getElementById("savebtnGoal").disabled=true;
    document.getElementById("loadGoal").style.display='block';
    document.getElementById("resultMessageGoal").innerHTML='';
    if(curretRole=='my'){
        if(shortTermGoal.trim()==""){
            x0p({
                title:'',
                text:'Please enter <b style="color:red">short term goal</b>',
                icon: 'info',
                html: true
            });
            document.getElementById("savebtnGoal").disabled=false;
    document.getElementById("loadGoal").style.display='none';
            return false;
        }
        if(longTermGoal.trim()==""){
            x0p({
                title:'',
                text:'Please enter <b style="color:red">long term goal</b>',
                icon: 'info',
                html: true
            });
             document.getElementById("savebtnGoal").disabled=false;
    document.getElementById("loadGoal").style.display='none';
            return false;
        }
        
    }
    if(curretRole=='team'){
        if(shortTermGoalComments.trim()==""){
            x0p({
                title:'',
                text:'Please enter <b style="color:red">short term goal comments</b>',
                icon: 'info',
                html: true
            });
             document.getElementById("savebtnGoal").disabled=false;
    document.getElementById("loadGoal").style.display='none';
            return false;
        }
        if(longTermGoalComments.trim()==""){
            x0p({
                title:'',
                text:'Please enter <b style="color:red">long term goal comments</b>',
                icon: 'info',
                html: true
            });
             document.getElementById("savebtnGoal").disabled=false;
    document.getElementById("loadGoal").style.display='none';
            return false;
        }
        
    }
    
     var lineId=document.getElementById("lineId").value;
        var empId=document.getElementById("empId").value;
        var strength=document.getElementById("strength").value;
        var improvements=document.getElementById("improvements").value;
        var quarterly=document.getElementById("quarterly").value;
        
        if(lineId=='0'){
            
         var rowCount = $("#measurementTable td").closest("tr").length;
         document.getElementById("rowCount").value=rowCount;
         var measurementId="";
                var keyFactorId="";
                var keyFactorWeightage="";
                var empDescription="";
                var empRating="";
                var id="";
          if(rowCount>0){
               for(var i=1;i<=rowCount;i++){
                    measurementId=measurementId+ document.getElementById("measurementId"+i).value+"#^$";
                        keyFactorId=keyFactorId+ document.getElementById("keyFactorId"+i).value+"#^$";
                        keyFactorWeightage=keyFactorWeightage+ document.getElementById("keyFactorWeightage"+i).value+"#^$";
                        empDescription=empDescription+ document.getElementById("empDescription"+i).value+"#^$";
                        if(document.getElementById("empRating"+i).value==""){
                             empRating=empRating+"0"+"#^$";
                        }else{
                        empRating=empRating+ document.getElementById("empRating"+i).value+"#^$";
                        }
                         if( document.getElementById("id"+i).value==""){
                             id=id+"0"+"#^$";
                        }else{
                        id=id+ document.getElementById("id"+i).value+"#^$";
                        }
                       
                    
               }
          }
    
        document.getElementById("status").value="Entered";
                    //document.getElementById("frmAppraisal").submit(); 
          var url = CONTENXT_PATH+"/saveQReviewDetails.action?ids="+escape(id)+"&appraisalId=0&empId="+empId+"&measurementId="+escape(measurementId)+"&keyFactorId="+escape(keyFactorId)+"&keyFactorWeightage="+escape(keyFactorWeightage)+"&empDescription="+escape(empDescription)+"&empRating="+escape(empRating)+"&status=Entered&shortTermGoal="+escape(shortTermGoal)+"&longTermGoal="+escape(longTermGoal)+"&strength="+escape(strength)+"&improvements="+escape(improvements)+"&quarterly="+quarterly+"&rowCount="+rowCount+"&rand="+new Date();
         //  alert(url);
           var req = initRequest(url);
            req.onreadystatechange = function() {
                //    alert("req-->"+req);
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        // alert(req.responseText);
                     var  resultJson = JSON.parse(req.responseText);
                    var QuarterAppraisalDetails = resultJson["QuarterAppraisalDetails"];
                  //  alert(QuarterAppraisalDetails);
                    var LineId = resultJson["LineId"];
                    var AppraisalId = resultJson["AppraisalId"];
                    var individualRowDetails=QuarterAppraisalDetails.split("*@!");
                  //  alert(individualRowDetails.length);
                    for (i = 1; i < individualRowDetails.length; i++) {
                                                    var individualColumn = individualRowDetails[i - 1].split("#^$");
                                                 //   alert(i+"--"+ individualColumn[11]);
                            document.getElementById("id"+i).value=individualColumn[11];                        
                    }
                    
                    document.getElementById("lineId").value=LineId;
                    document.getElementById("appraisalId").value=AppraisalId;
                    document.getElementById("status").value="Entered";
                      var result= resultJson["result"];
                       if(result=='success'){
                           document.getElementById("resultMessageGoal").innerHTML="<font color=\"green\" size=\"2\">Details saved successfully!</font>";
                       }else{
                            document.getElementById("resultMessageGoal").innerHTML='<font color=\"red" size=\"2\">Sorry! Please try again latter!</font>';
                       }
                    
                   // alert("id=="+document.getElementById("id10").value);
                       // parseEmpMessagesForProject(req0.responseXML);
                        document.getElementById("savebtnGoal").disabled=false;
    document.getElementById("loadGoal").style.display='none';
                    }
                }
            };
            req.open("GET", url, true);

            req.send(null);
        }else{
           var url = CONTENXT_PATH+"/setQReviewGoalsComments.action?id="+lineId+"&shortTermGoal="+escape(shortTermGoal)+"&longTermGoal="+escape(longTermGoal)+"&shortTermGoalComments="+escape(shortTermGoalComments)+"&longTermGoalComments="+escape(longTermGoalComments)+"&rand="+new Date();;
          // alert(url);
           var req = initRequest(url);
            req.onreadystatechange = function() {
                //    alert("req-->"+req);
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        // alert(req.responseText);
                         if(req.responseText=='success'){
                             document.getElementById("resultMessageGoal").innerHTML="<font color=\"green\" size=\"2\">Details saved successfully!</font>";
                         }else{
                             document.getElementById("resultMessageGoal").innerHTML=innerHTML="<font color=\"red\" size=\"2\">Sorry! Please try again latter!</font>";
                         }
                       document.getElementById("savebtnGoal").disabled=false;
    document.getElementById("loadGoal").style.display='none';
    // parseEmpMessagesForProject(req.responseXML);
                    } else if (req.status == 204){
                        clearTable1();
                    }
                }
            };
            req.open("GET", url, true);

            req.send(null);}
   
   // addGoalsOverlay(); 
}  
function addPersonalOverlay(){
 
    
    showRow('strengthsCommentsTr');
    showRow('improvementsCommentsTr');
   
   
    var status=document.getElementById("status").value;
    var curretRole=document.getElementById('curretRole').value;
     var dayCount=document.getElementById('dayCount').value;
    var operationTeamStatus=document.getElementById('operationTeamStatus').value;
    
    // document.getElementById('strengthsComments').readOnly=false;
    // document.getElementById('improvementsComments').readOnly=false;
   
     
    var overlay = document.getElementById('personalOverlay');
    var specialBox = document.getElementById('personalSpecialBox');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
         document.getElementById("resultMessagePersonality").innerHTML='';
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
         if(document.getElementById('roleName').value=='Operations'){
              document.getElementById('strength').readOnly=true;
                document.getElementById('improvements').readOnly=true;
              document.getElementById('strengthsComments').readOnly=true;
                document.getElementById('improvementsComments').readOnly=true;
                hideRow('personalitybtnTr');
         }else{
        if(curretRole=='my'){
            hideRow('strengthsCommentsTr');
            hideRow('improvementsCommentsTr');
            if(status.trim()=="Submitted" || status.trim()=="Approved"){
                document.getElementById('strength').readOnly=true;
                document.getElementById('improvements').readOnly=true;
                hideRow('personalitybtnTr');
            }else{
                document.getElementById('strength').readOnly=false;
                document.getElementById('improvements').readOnly=false;
                showRow('personalitybtnTr');
            }
        }else if(curretRole=='team'){
              if(status.trim()=="Approved" || status.trim()=="Entered" ){
                document.getElementById('strengthsComments').readOnly=true;
                document.getElementById('improvementsComments').readOnly=true;
                hideRow('personalitybtnTr');
            }else{
                document.getElementById('strengthsComments').readOnly=false;
                document.getElementById('improvementsComments').readOnly=false;
                showRow('personalitybtnTr');
            }
             if(dayCount==2 && (status=='Submited' || (status=='Approved' && operationTeamStatus=='Rejected'))){
                 document.getElementById('strengthsComments').readOnly=false;
                document.getElementById('improvementsComments').readOnly=false;
                showRow('personalitybtnTr');
             }
             if(dayCount==1 && status=='Rejected'){
                document.getElementById('strengthsComments').readOnly=true;
                document.getElementById('improvementsComments').readOnly=true;
                hideRow('personalitybtnTr');
            }
        }
        var userId1=document.getElementById('userId1').value;
            if(userId1=='rkalaga'){
                 document.getElementById('strengthsComments').readOnly=true;
                document.getElementById('improvementsComments').readOnly=true;
                hideRow('personalitybtnTr');
            }
         }
    }
    document.getElementById('personalityCount').innerHTML ='';
    document.getElementById('personalityCommnetsCount').innerHTML ='';
    document.getElementById('improvementsCount').innerHTML ='';
    document.getElementById('improvementsCommentsCount').innerHTML ='';
//window.location = "jobSearch.action";

}

function savePersonality(){
    setSessionTimeout();
    var strength=document.getElementById("strength").value;
    var strengthsComments=document.getElementById("strengthsComments").value;
    var improvements=document.getElementById("improvements").value;
    var improvementsComments=document.getElementById("improvementsComments").value;
    var curretRole=document.getElementById("curretRole").value;
     document.getElementById("savebtnPersonality").disabled=true;
    document.getElementById("loadPersonality").style.display='block';
     document.getElementById("resultMessagePersonality").innerHTML='';
    if(curretRole=='my'){
        if(strength.trim()==""){
            x0p({
                title:'',
                text:'Please enter <b style="color:red">your strengths</b>',
                icon: 'info',
                html: true
            });
            document.getElementById("savebtnPersonality").disabled=false;
    document.getElementById("loadPersonality").style.display='none';
            return false;
        }
        if(improvements.trim()==""){
            x0p({
                title:'',
                text:'Please enter <b style="color:red">your improvements</b>',
                icon: 'info',
                html: true
            });
             document.getElementById("savebtnPersonality").disabled=false;
    document.getElementById("loadPersonality").style.display='none';
            return false;
        }
    }
    
    if(curretRole=='team'){
        if(strengthsComments.trim()==""){
            x0p({
                title:'',
                text:'Please enter <b style="color:red">strengths comments</b>',
                icon: 'info',
                html: true
            });
             document.getElementById("savebtnPersonality").disabled=false;
    document.getElementById("loadPersonality").style.display='none';
            return false;
        }
        if(improvementsComments.trim()==""){
            x0p({
                title:'',
                text:'Please enter <b style="color:red">improvement comments</b>',
                icon: 'info',
                html: true
            });
             document.getElementById("savebtnPersonality").disabled=false;
    document.getElementById("loadPersonality").style.display='none';
            return false;
        }
        
       
    }
    var lineId=document.getElementById("lineId").value;
        var empId=document.getElementById("empId").value;
        var shortTermGoal=document.getElementById("shortTermGoal").value;
        var longTermGoal=document.getElementById("longTermGoal").value;
        var quarterly=document.getElementById("quarterly").value;
        
        if(lineId=='0'){
            
         var rowCount = $("#measurementTable td").closest("tr").length;
         document.getElementById("rowCount").value=rowCount;
         var measurementId="";
                var keyFactorId="";
                var keyFactorWeightage="";
                var empDescription="";
                var empRating="";
                var id="";
          if(rowCount>0){
               for(var i=1;i<=rowCount;i++){
                    measurementId=measurementId+ document.getElementById("measurementId"+i).value+"#^$";
                        keyFactorId=keyFactorId+ document.getElementById("keyFactorId"+i).value+"#^$";
                        keyFactorWeightage=keyFactorWeightage+ document.getElementById("keyFactorWeightage"+i).value+"#^$";
                        empDescription=empDescription+ document.getElementById("empDescription"+i).value+"#^$";
                        if(document.getElementById("empRating"+i).value==""){
                             empRating=empRating+"0"+"#^$";
                        }else{
                        empRating=empRating+ document.getElementById("empRating"+i).value+"#^$";
                        }
                         if( document.getElementById("id"+i).value==""){
                             id=id+"0"+"#^$";
                        }else{
                        id=id+ document.getElementById("id"+i).value+"#^$";
                        }
                       
                    
               }
          }
    
        document.getElementById("status").value="Entered";
                    //document.getElementById("frmAppraisal").submit(); 
          var url = CONTENXT_PATH+"/saveQReviewDetails.action?ids="+escape(id)+"&appraisalId=0&empId="+empId+"&measurementId="+escape(measurementId)+"&keyFactorId="+escape(keyFactorId)+"&keyFactorWeightage="+escape(keyFactorWeightage)+"&empDescription="+escape(empDescription)+"&empRating="+escape(empRating)+"&status=Entered&shortTermGoal="+escape(shortTermGoal)+"&longTermGoal="+escape(longTermGoal)+"&strength="+escape(strength)+"&improvements="+escape(improvements)+"&quarterly="+quarterly+"&rowCount="+rowCount+"&rand="+new Date();;
         //  alert(url);
           var req = initRequest(url);
            req.onreadystatechange = function() {
                //    alert("req-->"+req);
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        // alert(req.responseText);
                     var  resultJson = JSON.parse(req.responseText);
                    var QuarterAppraisalDetails = resultJson["QuarterAppraisalDetails"];
                  //  alert(QuarterAppraisalDetails);
                    var LineId = resultJson["LineId"];
                    var AppraisalId = resultJson["AppraisalId"];
                    var individualRowDetails=QuarterAppraisalDetails.split("*@!");
                  //  alert(individualRowDetails.length);
                    for (i = 1; i < individualRowDetails.length; i++) {
                                                    var individualColumn = individualRowDetails[i - 1].split("#^$");
                                                 //   alert(i+"--"+ individualColumn[11]);
                            document.getElementById("id"+i).value=individualColumn[11];                        
                    }
                    
                    document.getElementById("lineId").value=LineId;
                    document.getElementById("appraisalId").value=AppraisalId;
                    document.getElementById("status").value="Entered";
                    
                   // alert("id=="+document.getElementById("id10").value);
                       // parseEmpMessagesForProject(req0.responseXML);
                       var result= resultJson["result"];
                       if(result=='success'){
                            document.getElementById("resultMessagePersonality").innerHTML="<font color=\"green\" size=\"2\">details saved successfully!</font>";
                         }else{
                            document.getElementById("resultMessagePersonality").innerHTML='<font color=\"red" size=\"2\">Sorry! Please try again latter!</font>';
                       }
                          document.getElementById("savebtnPersonality").disabled=false;
    document.getElementById("loadPersonality").style.display='none';
                    }
                }
            };
            req.open("GET", url, true);

            req.send(null);
        }else{
         var lineId=document.getElementById("lineId").value;
           var url = CONTENXT_PATH+"/setQReviewPersonalityComments.action?id="+lineId+"&strength="+escape(strength)+"&improvements="+escape(improvements)+"&strengthsComments="+escape(strengthsComments)+"&improvementsComments="+escape(improvementsComments)+"&rand="+new Date();;
           //alert(url);
           var req = initRequest(url);
            req.onreadystatechange = function() {
                //    alert("req-->"+req);
                if (req.readyState == 4) {
                    if (req.status == 200) {
                       //  alert(req.responseText);
                        if(req.responseText=='success'){
                             document.getElementById("resultMessagePersonality").innerHTML="<font color=\"green\" size=\"2\">Details saved successfully!</font>";
                         }else{
                             document.getElementById("resultMessagePersonality").innerHTML=innerHTML="<font color=\"red\" size=\"2\">Sorry! Please try again latter!</font>";
                         }
                       // parseEmpMessagesForProject(req.responseXML);
                          document.getElementById("savebtnPersonality").disabled=false;
    document.getElementById("loadPersonality").style.display='none';
                    }
                }
            };
            req.open("GET", url, true);

            req.send(null);
        }
      
  //  addPersonalOverlay();
}
function fieldLengthValidatorQuarter(element){
    var i=0;
 
    if(element.id=="opCode")
    { 
        i=200;
    }
            
    if(element.id=="description" 
        || element.id=="shortTermGoal" 
        ||element.id=="shortTermGoalComments"
        ||element.id=="longTermGoal"
        ||element.id=="longTermGoalComments"
        ||element.id=="strength"
        ||element.id=="strengthsComments"
        ||element.id=="improvements"
        ||element.id=="improvementsComments"
        ||element.id=="rejectedComments"
        )
        { 
        i=500;
    }
            
  
    var temp=0;
   
   
    
   
     
    if(temp==0 && element.value.replace(/^\s+|\s+$/g,"").length>i) {
        str=new String(element.value);
        element.value=str.substring(0,i);
        x0p( '','The '+element.id+' length must be less than '+i+' characters','info');
        // displayErrorMessage('The '+element.id+' length must be less than '+i+' characters!','Error');
        element.focus();
        return false;
    }
    return true;
   
}
function countChar(val,displayId) {
    var len = val.value.length;
    if (len >= 500) {
        val.value = val.value.substring(0, 500);
        document.getElementById(displayId).innerHTML=(0)+'\\500';
    } else {
        document.getElementById(displayId).innerHTML=(500-len)+'\\500';
    }
}
function toggleRejectedCommentsOverlay() {
    var overlay = document.getElementById('rejectedCommentsOverlay');
    var specialBox = document.getElementById('rejectedCommentsSpecialBox');

    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
         showRow('rejectBtnTr');
          document.getElementById('rejectedComments').readOnly=false;
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
        document.getElementById("rejectedComments").value="";
    }
    document.frmEmpSearch.submit();
            
// window.location="getEmployee.action?empId="+;
}
function showRejectedCommentsOverlay(id) {
    var overlay = document.getElementById('rejectedCommentsOverlay');
    var specialBox = document.getElementById('rejectedCommentsSpecialBox');

    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
         showRow('rejectBtnTr');
          document.getElementById('rejectedComments').readOnly=false;
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
        hideRow('rejectBtnTr');
        
       var id= document.getElementById(id).value;
        document.getElementById('rejectedComments').readOnly=true;
         document.getElementById('rejectedComments').value=id;
    }
    document.frmEmpSearch.submit();
            
// window.location="getEmployee.action?empId="+;
}

function checkDoubleQuotes(obj){
   
   obj.value =  obj.value.replace(/"/g , "'");
       
    
    
}
var timeoutId;

function setSessionTimeout(){
clearTimeout(timeoutId);
//alert("test1"+new Date());
timeoutId = setTimeout(

function()

{
history.pushState(null, null, 'pagename');
window.addEventListener('popstate', function(event) {
history.pushState(null, null, 'pagename');
});
alert('You\'re session has timed out. Please re-login.');
window.location = "../../general/logout.action";
}, 1800000); //1800000 -->30 min
}

