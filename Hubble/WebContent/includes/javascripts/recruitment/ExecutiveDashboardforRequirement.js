 /* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function readyStateHandler(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadActMessageAS")).style.display = "none";
                responseTextHandler(req.responseText);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
            (document.getElementById("loadActMessageAS")).style.display = "block";
        }
    }
}

function readyStateHandlerReport(req,responseHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("loadActMessageSubmitReport").style.display = 'none';
                responseHandler(req.responseText);
            }
        } else {
            document.getElementById("loadActMessageSubmitReport").style.display = 'block';
        //alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }
}
function ClrTable(myHTMLTable) { 
    //alert("In ClrTable");
    var tbl =  myHTMLTable;
    var lastRow = tbl.rows.length; 
    //document.getElementById('addlabel1').style.display = 'none'; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}
function generateTableHeader(tableBody,headerFields) {
    //alert("In generateTableHeader");
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

function ParseAndGenerateHTML(oTable,responseString,headerFields) {
    
    //alert("In ParseAndGenerateHTML");
    var start = new Date();
    var fieldDelimiter = "|";
    var recordDelimiter = "^";   
    //alert('responseString%%%% ******'+responseString);
    //alert('rowCount%%%% ******'+rowCount);      
    if(oTable.id=="tblSubmitReport" || oTable.id=="tblAddedConsultant" || oTable.id=="tblstatusReport"){
    
        fieldDelimiter = "#^$";
        recordDelimiter = "*@!"; 
    }
    var records = responseString.split(recordDelimiter); 
    //alert(records);
    generateTable(oTable,headerFields,records,fieldDelimiter);
//alert("End Of ParseAndGenerateHTML");
}

function generateTable(oTable, headerFields,records,fieldDelimiter) {	
    //alert("oTable.id-->"+oTable.id);
    //alert("IN generateTable");
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
          
            if(oTable.id ==  "tblSubmitReport" || oTable.id ==  "tblAddedConsultant" || oTable.id=="tblstatusReport"){
                generateRow(oTable,tbody,records[i],fieldDelimiter);  
            }
            
        }    
    }    
    else {
        generateNoRecords(tbody,oTable);
    }
    
    generateFooter(tbody,oTable);
//alert("End Of generateTable");
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
    if(oTable.id=="tblSubmitReport"){
        //  alert(length);
        for (var i=1;i<length;i++) {
         //  alert(i+" ---- "+fields[i]);
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
          
            if(parseInt(i,10)!=1){
      
                //alert(fields[i]+"fields[i]");
                cell.innerHTML = fields[i];
        
        
                if(parseInt(i,10)==23){
                    cell.innerHTML = "<a href='javascript:getConsultantDetails("+fields[0]+")'>"+fields[i]+"</a>";
                }
        
        
            }else {
                var jobTitle = fields[i].substring(0,10);
                cell.innerHTML = jobTitle+ "<a href='#' onMouseOver='Tip(\"" + fields[i] + "\");' onmouseout='UnTip();'> ..</a>";
           
            }
            if(fields[i]!=''){
                row.appendChild( cell );
            }
        }
    } else if(oTable.id=="tblAddedConsultant"){
        for (var i=0;i<length;i++) {
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            if(parseInt(i,10)==15){
                var jobTitle = fields[i].substring(0,10);
                cell.innerHTML = jobTitle+ "<a href='#' onMouseOver='Tipp(\"" + fields[i] + "\");' onmouseout='UnTipp();'> ..</a>";
        
            }
            else{
                cell.innerHTML = fields[i];
            }
            row.appendChild( cell );
        }
    }
     
    else {
         
         
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
}
function generateNoRecords(tbody,oTable) {
    //alert("In generateNoRecords");
    var noRecords =document.createElement("TR");
    noRecords.className="gridRowEven";
    tbody.appendChild(noRecords);
    cell = document.createElement("TD");
    cell.className="gridColumn";
    
    if(oTable.id ==  "tblSubmitReport"){
        cell.colSpan = "23";
    }
    else if(oTable.id== "tblAddedConsultant"){
        cell.colSpan = "16";
    }
    else if(oTable.id =="tblstatusReport"){
          cell.colSpan = "9";
    }
    cell.innerHTML = "No Records Found for this Search";
    noRecords.appendChild(cell);
}
function generateFooter(tbody,oTable) {
    //alert("In generateFooter");
    //alert(oTable.id);
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.id="footer"+oTable.id;
    if(oTable.id ==  "tblRequirementsClosed")
    {
        cell.colSpan = "3";   
    }else{
        cell.colSpan = "12.5";   
    }
    footer.appendChild(cell);
}
function getSubmissionReport(element) {
     document.getElementById("button_pageNation").innerHTML="";
    var practiceId = document.getElementById("practiceId").value;
    var status = document.getElementById("status").value;
    var techMgrId = document.getElementById("techMgrId").value;
    var clientId = document.getElementById("clientId").value;
    var teamLeadLoginId = document.getElementById("teamLeadLoginId").value;
    var recruiterLoginId = document.getElementById("recruiterLoginId").value;
    var startDate = document.getElementById("startDateReport").value;
    var endDate = document.getElementById("endDateReport").value;
    var tableId = document.getElementById("tblSubmitReport");
    var region = document.getElementById("region").value;
    var modifiedStartDate = document.getElementById("modifiedStartDate").value;
    var modifiedEndDate = document.getElementById("modifiedEndDate").value;
    ClrTable(tableId);
     var pgNo=document.getElementById("pgNo").value;
        var pGflag=element;
        if(pGflag=='1'){
            pgNo='1';  
        }
 var req = newXMLHttpRequest();
 req.onreadystatechange = function() {
            if (req.readyState == 4) {
                if (req.status == 200) {      
                    document.getElementById("loadActMessageSubmitReport").style.display = 'none';
                    //    alert(element);
                    populateReport(req.responseText,element);   
                    pagerOption();
                } 
            }else {
                document.getElementById("loadActMessageSubmitReport").style.display = 'block';
            }
        }; 
 //   alert("hiii")
  //  req.onreadystatechange = readyStateHandlerReport(req, populateReport);
    var url = CONTENXT_PATH+"/getSubmissionReport.action?startDate="+startDate+"&endDate="+endDate+"&practiceid="+practiceId+"&status="+status+"&techMgrId="+techMgrId+"&clientId="+clientId+"&teamLeadLoginId="+teamLeadLoginId+"&recruiterLoginId="+recruiterLoginId+"&region="+region+"&modifiedStartDate="+modifiedStartDate+"&modifiedEndDate="+modifiedEndDate;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
    
}


function populateReport(response,flag) {
 // alert("response-->"+response); 
    var tableId = document.getElementById("tblSubmitReport");
      document.getElementById("button_pageNation").innerHTML="";
       
    // var headerFields = new Array("Sourcing Person","B2B","BPM","SAP");
    //  var headerFields = new Array("JobTitle","POS","Posted","Interview Reject","Offered","Joined","Dropped/Backout","Hold","GrandTotal");
    var headerFields = new Array("JobTitle","POS","Posted","Assigned",
        "Evaluation Reject",
        "Tech Screen  - Phone",
        "Tech Screen  - Skype/F2F",
        "Face to Face",
        "Tech Screen Shotlisted",
        "Tech Screen Reject",
        "Submitted to Sales",
        "Sales Accepted",
        "Sales Reject",
        "Client Submission",
        "Client Reject",
        "Client Interview",
        "Client Interview Rejectt",
        "Offered",
        "Not Joined",
        "Joined",
        "Dropped/Backout",
        "Hold",
        "GrandTotal");  
 
    //submitReportDiv
    document.getElementById("submitReportDiv").style.display="";
    //$$$
    //totalPositions
    document.getElementById("totalPositions").innerHTML="<font color=red size=2px>"+response.split("$$$")[1]+"</font>";
   
    var dataArray = response.split("$$$")[0];    
   if(flag=='1'){
      //alert(dataArray[1])
        document.getElementById("pgNo").value=1;
        document.getElementById("totalRecords").value=dataArray[1];
    }
    ParseAndGenerateHTML(tableId,dataArray, headerFields);   

     $("#tblSubmitReport").tableHeadFixer({
        'left' : 4, 
        'foot' : false, 
        'head' : true
    });
}
function getConsultantDetails(requirementId) {
    //alert("Get list-->"+requirementId);
    
  
    var tableId = document.getElementById("tblAddedConsultant");
    ClrTable(tableId);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerReport(req, populateConsulatntReport);
    var url = CONTENXT_PATH+"/getConsultantListForRequirement.action?requirementId="+requirementId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
    
}

function populateConsulatntReport(response) {
    
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Consultant List";
    var overlay = document.getElementById('overlayRecruitment');
    var specialBox = document.getElementById('specialBoxRecruitment');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
                
    // window.location = 'teamReviewSearch.action?startDate='+startDate+'&endDate='+endDate+'&reviewType='+reviewType+'&empnameById='+empnameById;
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
    //alert("response-->"+response);
    var tableId = document.getElementById("tblAddedConsultant");
    
    // var headerFields = new Array("Sourcing Person","B2B","BPM","SAP");
    // var headerFields = new Array("Practice","Sales Manager","Requirment & Date of Posting","Requirement Status","Candidate Name","Location","Total ","Educational","Notice","Status","Date of Submission","Date of MSS Evaluation","Date of client Submission","Date of client Interview","Team Lead","Recruiter","Remarks");
    var headerFields = new Array("S.No","Practice","Pre&nbsp;sales1","Client","Requirment","POS"," Date of Posting","Requirement Status","Candidate Name","Total Experience","Status","Date of Submission","Consultant Added by","Recruiter1","Recruiter2","Remarks");
    //submitReportDiv
    document.getElementById("addedConultantDiv").style.display="";
    //$$$
    //totalPositions
    //  document.getElementById("totalPositions").innerHTML="<font color=red size=2px>"+response.split("$$$")[1]+"</font>";
   
    //var dataArray = response.split("$$$")[0];    
    var dataArray = response;    
   
    ParseAndGenerateHTML(tableId,dataArray, headerFields);   

    
}

function closeConsultantList(){
    // hideRow('downloadTr');
    // showRow('uploadTr');
          
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Consultant List";
    var overlay = document.getElementById('overlayRecruitment');
    var specialBox = document.getElementById('specialBoxRecruitment');
          
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
               
    //window.location = 'reviewSearch.action?startDate='+startDate+'&endDate='+endDate+'&reviewType='+reviewType+'&reviewStatus='+reviewStatus+'&empnameById='+empnameById;
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
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
function getTeamMap() {
    var teamLeadId = document.getElementById("teamLeadLoginId").value;
    var departmentId = document.getElementById("departmentId").value;
     
 
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerXml(req, populateTeam);
    var url = CONTENXT_PATH+"/getMyTeam.action?loginId="+teamLeadId+"&departmentName="+departmentId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
    
}

function populateTeam(resXML) {
    var reportsTo = document.getElementById("recruiterLoginId");
    var team = resXML.getElementsByTagName("EMPLOYEES")[0];
    var users = team.getElementsByTagName("USER");
    reportsTo.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("empId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        reportsTo.appendChild(opt);
    }
}
function getTeamMapInStatus() {
    var teamLeadId = document.getElementById("teamLeadLoginId1").value;
    var departmentId = document.getElementById("departmentId1").value;
     
 
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerXml(req, populateTeamINStatus);
    var url = CONTENXT_PATH+"/getMyTeam.action?loginId="+teamLeadId+"&departmentName="+departmentId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
    
}

function populateTeamINStatus(resXML) {
    var reportsTo = document.getElementById("recruiterLoginId1");
    var team = resXML.getElementsByTagName("EMPLOYEES")[0];
    var users = team.getElementsByTagName("USER");
    reportsTo.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("empId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        reportsTo.appendChild(opt);
    }
}
function getStatusReport(element) {
   //  document.getElementById("button_pageNation1").innerHTML="";
    var practiceId = document.getElementById("practiceId1").value;
    var status = document.getElementById("status1").value;
    var techMgrId = document.getElementById("techMgrId1").value;
    var clientId = document.getElementById("clientId1").value;
    var teamLeadLoginId = document.getElementById("teamLeadLoginId1").value;
    var recruiterLoginId = document.getElementById("recruiterLoginId1").value;
    var startDate = document.getElementById("startDateReport1").value;
    var endDate = document.getElementById("endDateReport1").value;
    var tableId = document.getElementById("tblstatusReport");
    var region = document.getElementById("region1").value;
    var modifiedStartDate = document.getElementById("modifiedStartDate1").value;
    var modifiedEndDate = document.getElementById("modifiedEndDate1").value;
    document.getElementById("loadActMessageStatusReport").style.display = 'block';
    document.getElementById("totalRequirememts").innerHTML="<font color=red size=2px>0</font>";
    ClrTable(tableId);
//     var pgNo=document.getElementById("pgNo1").value;
//        var pGflag=element;
//        if(pGflag=='1'){
//            pgNo='1';  
//        }
 var req = newXMLHttpRequest();
// req.onreadystatechange = function() {
//            if (req.readyState == 4) {
//                if (req.status == 200) {      
//                    document.getElementById("loadActMessageStatusReport").style.display = 'none';
//                    //    alert(element);
//                    populateStatusReport(req.responseText,element);   
//                    pagerOption1();
//                } 
//            }else {
//                document.getElementById("loadActMessageStatusReport").style.display = 'block';
//            }
//        };
  //  var req = newXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerReport(req, populateStatusReport);
    var url = CONTENXT_PATH+"/getRequirementStatusReport.action?startDate="+startDate+"&endDate="+endDate+"&practiceid="+practiceId+"&status="+status+"&techMgrId="+techMgrId+"&clientId="+clientId+"&teamLeadLoginId="+teamLeadLoginId+"&recruiterLoginId="+recruiterLoginId+"&region="+region+"&modifiedStartDate="+modifiedStartDate+"&modifiedEndDate="+modifiedEndDate;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
    
}


function populateStatusReport(response,flag) {
    //alert("response-->"+response);
    var tableId = document.getElementById("tblstatusReport");
  //   document.getElementById("button_pageNation1").innerHTML="";
    
    // var headerFields = new Array("Sourcing Person","B2B","BPM","SAP");
    //  var headerFields = new Array("JobTitle","POS","Posted","Interview Reject","Offered","Joined","Dropped/Backout","Hold","GrandTotal");
    var headerFields = new Array("Country","Total","Opened","InProgress","Forecast",
   "lost",
   "Withdrawn",
   "Hold",
   "Won");
 
    //submitReportDiv
    document.getElementById("submitStatusDiv").style.display="";
    //$$$
    //totalPositions
    
   
   var dataArray = response.split('$$$');
   //alert(dataArray[0]);
//   if(flag=='1'){
//      //alert(dataArray[1])
//        document.getElementById("pgNo").value=1;
//        document.getElementById("totalRecords").value=dataArray[1];
//    }
    var total=0;
    if(dataArray[1]!= undefined){
        total=dataArray[1];
    }
    document.getElementById("totalRequirememts").innerHTML="<font color=red size=2px>"+total+"</font>";
 ParseAndGenerateHTML(tableId,dataArray[0], headerFields);
   document.getElementById("loadActMessageStatusReport").style.display = 'none'; 
//     $("#tblstatusReport").tableHeadFixer({
//        'left' : 4, 
//        'foot' : false, 
//        'head' : true
//    });
}