/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function clearTable(myHTMLTable) { 
    var tbl =  myHTMLTable;
    var lastRow = tbl.rows.length; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
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

function readyStateHandlerText(req,responseHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                responseHandler(req.responseText);
            } else {
                alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }
    }
}

function ParseAndGenerateHTML(oTable,responseString,headerFields) {
    var start = new Date();
    var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";   
    var records = responseString.split(recordDelimiter); 
    generateTable(oTable,headerFields,records,fieldDelimiter);
}

function generateTable(oTable, headerFields,records,fieldDelimiter) {	

    var tbody = oTable.childNodes[0];    
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    generateTableHeader(tbody,headerFields);
    var rowlength;
    rowlength = records.length;
    if(rowlength >0 && records!=""){
       
        if(oTable.id == "tblDMProjects") {
            for(var i=0;i<rowlength-1;i++){
                generateProject(oTable,tbody,records[i],fieldDelimiter);  
            }
        }
      
        if(oTable.id == "tblProjectMembers") {
            for(var i=0;i<rowlength-1;i++){
                generateProjectOverLay(tbody,records[i],fieldDelimiter);  
            }
        } 
            
    } else {
        generateNoRecords(tbody,oTable);
    }
    generateFooter(tbody,oTable);
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
        cell.innerHTML = headerFields[i];
        cell.width = 120;
    }
}


function generateNoRecords(tbody,oTable) {
   
    var noRecords =document.createElement("TR");
    noRecords.className="gridRowEven";
    tbody.appendChild(noRecords);
    cell = document.createElement("TD");
    cell.className="gridColumn";
    
 
    if(oTable.id == "tblDMProjects"){
        cell.colSpan = "7";
    }
  
    if(oTable.id == "tblProjectMembers"){
        cell.colSpan = "5";
    }
    cell.innerHTML = "No Records Found for this Search";
    noRecords.appendChild(cell);
}


function generateFooter(tbody,oTable) {
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.id="footer"+oTable.id;
   
    if(oTable.id == "tblDMProjects"){
        cell.colSpan = "7";
    }
   
     if(oTable.id == "tblProjectMembers"){
        cell.colSpan = "5";
    }
    footer.appendChild(cell);
}


/*==================================================================
 *                Project dashboard start
 ===================================================================*/

function getDeliverManagerProjects()
{
   
   
    document.getElementById("noteLableForProject").style.display='none';
    var tableId = document.getElementById("tblDMProjects");
    clearTable(tableId);
   
    var startDate = document.getElementById("startDate").value;
    var endDate = document.getElementById("endDate").value;
    var teamMemberId = document.getElementById("managerTeamMember").value;
    var state = document.getElementById("status").value;
   
   // alert(startDate+" "+endDate+" "+teamMemberId+" "+state);
    var checkResult = compareDates(startDate,endDate);
    if(!checkResult) {
        return false;
    }
 
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("loadProjectMessage").style.display = 'none';
                displayManagerProjectDashBoard(req.responseText);                        
            } 
        }else {
            document.getElementById("loadProjectMessage").style.display = 'block';
        }
    }; 
    var url = CONTENXT_PATH+"/getPreSalesProjectDashBoard.action?startDate="+startDate+"&endDate="+endDate+"&state="+state+"&teamMemberId="+teamMemberId+"&dummy="+new Date().getTime();
   req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function displayManagerProjectDashBoard(response) {
    var oTable = document.getElementById("tblDMProjects");
    clearTable(oTable);
    
    var dataArray = response;
    
    if(dataArray == "no data")
    {
        alert("No Records Found for this Search");   
    }
    else {
        var headerFields = new Array("S.No","Customer&nbsp;Name","Project&nbsp;name","Total&nbsp;Res","No&nbsp;of&nbsp;Billable","Start&nbsp;Date","End&nbsp;Date");        
        ParseAndGenerateHTML(oTable,response, headerFields);       
    } 

}
		
		
		
function generateProject(oTable,tableBody,record,fieldDelimiter){
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(fieldDelimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
    
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    for (var i=1;i<length;i++) {            
        cell = document.createElement( "TD" );
        cell.className="gridColumn";       
        cell.innerHTML = fields[i];  
        if(parseInt(i,10)==3){
            cell.innerHTML = "<a href='javascript:getProjectMembers("+fields[0]+")'>"+fields[i]+"</a>";
        }
        if(fields[i]!=''){
            if(i==1)
            {
                cell.setAttribute("align","left");
            }
            else
            {
                cell.setAttribute("align","left");     
            }
            row.appendChild( cell );
        }
    }
}

function getProjectMembers(prjId)
{
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, populateProjectMembers);
    var url = CONTENXT_PATH+"/getProjectMembers.action?projectId="+prjId;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateProjectMembers(resText) {
    
   // alert(resText);
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Project Details";
    var overlay = document.getElementById('overlayProjects');
    var specialBox = document.getElementById('specialBoxProject');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
    var tableId = document.getElementById("tblProjectMembers");
    clearTable(tableId);
    var headerFields = new Array("Presales&nbsp;Person","PMO","Delivary&nbsp;Manager","OnSite&nbsp;Manager","OffShore&nbsp;Manager");
    document.getElementById("addedProjects").style.display="";
    var dataArray = resText;    
    ParseAndGenerateHTML(tableId,dataArray, headerFields);   
    
}

function closeProjectMembers(){
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Consultant List";
    var overlay = document.getElementById('overlayProjects');
    var specialBox = document.getElementById('specialBoxProject');
          
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}
function generateProjectOverLay(tableBody,record,fieldDelimiter){
    var row;
    var cell;
    var fieldLength;
  //  alert(record);
    var fields = record.split(fieldDelimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    for (var i=0;i<length;i++) {            
        cell = document.createElement( "TD" );
        cell.className="gridColumn";       
        cell.innerHTML = fields[i];  
       
        if(fields[i]!=''){
            if(i==1)
            {
                cell.setAttribute("align","left");
            }
            else
            {
                cell.setAttribute("align","left");     
            }
            row.appendChild( cell );
        }
    }
}

/*==================================================================
 *                Project  dashboard end
 ===================================================================*/

var temp ="0";
function defaultDates(){
    
    var currentYear = new Date().getFullYear();    
    var currentMonth = new Date().getMonth() +1;    
    var currentDay = "01";
    if(currentMonth <10 ){
        currentMonth = temp+ currentMonth;
    }
    var firstDayOfMonth = currentMonth + '/' + currentDay + '/' + currentYear;
    var intCurrentYear = parseInt(currentYear);
    var now = new Date();// Add 30 days   
    now.setDate(1);
    now.setDate(now.getDate() + 29);
    var currentYear = now.getFullYear();    
    var currentMonth = now.getMonth() +1;    
    var currentDay = now.getDate();
    if(currentDay <10 ){
        currentDay = temp+ currentDay;
    }
    if(currentMonth <10 ){
        currentMonth = temp+ currentMonth;
    }
    var lastDate;    
    var lastDate = currentMonth + '/' + currentDay + '/' + currentYear;    
    var currentDate = new Date();
    var day = currentDate.getDate();
    var month = currentDate.getMonth() + 1;
    var year = currentDate.getFullYear();

    var endDate =  month+"/"+day+"/"+year;
    var today = new Date();
    var priorDate = new Date(endDate);
    priorDate.setDate(priorDate.getDate()-60);
    var priorDay = priorDate.getDate();
    var priorMonth = priorDate.getMonth() + 1;
    var priorYear = priorDate.getFullYear();
    var startDate="";
    if(parseInt(priorMonth)<10){
        startDate = "0"+priorMonth+"/"+"01"+"/"+priorYear;
    }else{
        startDate = priorMonth+"/"+"01"+"/"+priorYear;
    }
    
    /*Start date before last month first date and end date as current date */
    
    document.getElementById('startDate').value = startDate;       
    document.getElementById('endDate').value = endDate;
}

