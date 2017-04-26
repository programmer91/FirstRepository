// This is a javascript file

var foo = "bar";


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


function readyStateHandler(req,responseHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("loadActMessage").style.display = 'none';
                responseHandler(req.responseText);
            }
        } else {
            document.getElementById("loadActMessage").style.display = 'block';
        //alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }
}


function readyStateHandlerPriority(req,responseHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("loadActMessagePriority").style.display = 'none';
                
                responseHandler(req.responseText);
            }
        } else {
            document.getElementById("loadActMessagePriority").style.display = 'block';
        // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }
}



function clearDatesOppDash(elementBtn) {
    elementBtn = document.getElementById("dateClearbtn");
    document.getElementById("dueStartDate").value = "";
    document.getElementById("dueEndDate").value = "";
    elementBtn.onclick = new Function("setDefaultOppDates()");
    elementBtn.value = "set Default Dates";
}

function setDefaultOppDates() {
    //alert("ok");
    var currentYear = new Date().getFullYear();    
    var currentMonth = new Date().getMonth() +1;    
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
    document.getElementById("dueStartDate").value = firstDayOfMonth;
    document.getElementById("dueEndDate").value = firstDayOfNextMonth;
    //=========================
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

    //var startDate = priorMonth+"/"+priorDay+"/"+priorYear;
    var startDate="";
    if(parseInt(priorMonth)<10){
     startDate = "0"+priorMonth+"/"+"01"+"/"+priorYear;
    }else{
        startDate = priorMonth+"/"+"01"+"/"+priorYear;
    }
    
    document.getElementById('dashBoardStartDateRep').value = startDate;       
    document.getElementById('dashBoardEndDateRep').value = endDate;
    
    document.getElementById('dueStartDate').value = startDate;       
    document.getElementById('dueEndDate').value = endDate;
    
    document.getElementById('reqStartDate').value = startDate;       
    document.getElementById('reqEndDate').value = endDate;
    
 //    document.getElementById('dashBoardAccountsStartDatePriority').value = startDate;       
   // document.getElementById('dashBoardAccountsEndDatePriority').value = endDate;
    //==================================
   // var elementBtn = document.getElementById("dateClearbtn");
//    elementBtn.onclick = new Function("clearDatesOppDash()");
    //elementBtn.value = "clear Dates";
}


function getOpportunities() {
    document.getElementById("opportunitySearchBUtton").disabled=true;
    document.getElementById("noteLableForOpportunity").style.display='none';
    var practiceName=document.getElementById("practice").value;
    var tableId = document.getElementById("tblOppUpdate");
    ClrTable(tableId);
    var skillCatArry2 = [];    
    $("#state :selected").each(function(){
        skillCatArry2.push($(this).val()); 
    });
    document.getElementById("state1").value=skillCatArry2;
    
     document.getElementById("loadOppMessage").style.display='block';
    document.getElementById("totalOppRec").innerHTML = "";
    document.getElementById("totalOppSum").innerHTML = "";
    //document.getElementById("oppDashFooter").innerHTML = "";
    var type = document.getElementById("type").value;
    var stage = document.getElementById("stage").value;
    var dueStartDate = document.getElementById("dueStartDate").value;
    var dueEndDate = document.getElementById("dueEndDate").value;
    var createdBy = document.getElementById("createdBy1").value;
    var state = document.getElementById("state1").value;
   // alert(state);
     var dueDate = document.getElementById("dueDate").value;

    var checkResult = compareDates(dueStartDate,dueEndDate);
    if(!checkResult) {
        return false;
    }
    if(createdBy == '-1') {
        createdBy = 'All';
    }
    
     var sviNum='';
      //alert('sviNum-->'+sviNum)
      if(document.getElementById("sviNum").checked)
          {
            sviNum='Not Null';
          }
      var SVIList=document.getElementById("sviList").value;
    //dueStartDate = replaceDateSplits(dueStartDate);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, displayOppDashBoard);
    //alert(CONTENXT_PATH);
    //var url = CONTENXT_PATH+"/oppDashBoard.action?type="+type+"&stage="+stage+"&dueStartDate="+dueStartDate+"&dueEndDate="+dueEndDate+"&createdBy="+createdBy+"&dummy="+new Date().getTime();
   // var url = CONTENXT_PATH+"/oppDashBoard.action?type="+type+"&stage="+stage+"&dueStartDate="+dueStartDate+"&dueEndDate="+dueEndDate+"&createdBy="+createdBy+"&dummy="+new Date().getTime()+"&state="+state+"&dueDate="+dueDate;
 //  var url = CONTENXT_PATH+"/oppDashBoard.action?type="+type+"&stage="+stage+"&dueStartDate="+dueStartDate+"&dueEndDate="+dueEndDate+"&createdBy="+createdBy+"&practiceName="+practiceName+"&dummy="+new Date().getTime()+"&state="+state+"&dueDate="+dueDate;
 var url = CONTENXT_PATH+"/oppDashBoard.action?type="+type+"&stage="+stage+"&dueStartDate="+dueStartDate+"&dueEndDate="+dueEndDate+"&createdBy="+createdBy+"&practiceName="+practiceName+"&dummy="+new Date().getTime()+"&state="+state+"&dueDate="+dueDate+"&sviNum="+sviNum+"&sviList="+encodeURIComponent(SVIList);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

var isTeamLead =0;
var isUserManager = 0;

function getAccountsByRep() {
    document.getElementById("RepSearchButton").disabled=true;
    document.getElementById("noteLableForRep").style.display='none';
    isTeamLead = document.getElementById("isTeamLead").value;
    isUserManager = document.getElementById("isUserManager").value;
     
    var tableId = document.getElementById("tblAccountSummRep");
    ClrTable(tableId);
    var dashBoardStartDateRep = document.getElementById("dashBoardStartDateRep").value;
    var dashBoardEndDateRep = document.getElementById("dashBoardEndDateRep").value;
    var practiceName = document.getElementById("practiceName").value;
     var teamMemberId= document.getElementById("teamMemberIdRep").value;

    var checkResult = compareDates(dashBoardStartDateRep,dashBoardEndDateRep);
    if(!checkResult) {
        return false;
    }
    if(createdBy == '-1') {
        createdBy = 'All';
    }
    //dueStartDate = replaceDateSplits(dueStartDate);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, displayAccountsByRep);
    //var url = CONTENXT_PATH+"/accountsByRep.action?dashBoardStartDateRep="+dashBoardStartDateRep+"&dashBoardEndDateRep="+dashBoardEndDateRep;
      //var url = CONTENXT_PATH+"/accountsByRep.action?dashBoardStartDateRep="+dashBoardStartDateRep+"&dashBoardEndDateRep="+dashBoardEndDateRep+"&practiceName="+practiceName;
      var url = CONTENXT_PATH+"/accountsByRep.action?dashBoardStartDateRep="+dashBoardStartDateRep+"&dashBoardEndDateRep="+dashBoardEndDateRep+"&practiceName="+practiceName+"&teamMemberId="+teamMemberId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function displayAccountsByRep(response) {
    //alert("by rep");
    var tableId = document.getElementById("tblAccountSummRep");
    // var headerFields = new Array("S.No","Employee Name","ReportsTo","TotalAccounts","Activites","Touched Accounts","Untouched Accounts");
    // var headerFields = new Array("S.No","Employee Name","ReportsTo","TotalAccounts","Activites","Touched Accounts","Untouched Accounts","Feedback Mail");
    var headerFields = new Array("S.No","ReportsTo","Employee Name","TotalAccounts","Activites","Touched Accounts","Untouched Accounts");
    if(isTeamLead == 1 || isUserManager == 1) {
        headerFields = new Array("S.No","ReportsTo","Employee Name","TotalAccounts","Activites","Touched Accounts","Untouched Accounts","Feedback Mail");
    }

    var dataArray = response;
    if(dataArray=="1|"){
        dataArray="";
    }
    //generateHeader(headerArray,tableId);
    ParseAndGenerateHTML(tableId,dataArray, headerFields);
    document.getElementById("noteLableForRep").style.display='block';
     document.getElementById("RepSearchButton").disabled=false;
// document.getElementById(("footer"+tableId.id)).innerHTML = "Total Value is($):  "+dataArray[2];
    
}

//new methods for account list by priority start

function getAccountsByPriority(resText,oTable,tableBody,record,delimiter) {
    //alert("getAccountsByPriority1");
    isTeamLead = document.getElementById("isTeamLead").value;
    isUserManager = document.getElementById("isUserManager").value;
    // alert("restext-->"+resText);
    var tableId = document.getElementById("tblUpdateForAccountsListByPriority");
    ClrTable(tableId);
    var teamMemberId = document.getElementById("teamMemberId").value;
    //alert("teamMemberId-->"+teamMemberId);
    //dueStartDate = replaceDateSplits(dueStartDate);
    if(teamMemberId == "")
    {
        alert("please select team member"); 
        return false;
    }
    else
    {
        /*var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerPriority(req, displayAccountsByPriority);
        var url = CONTENXT_PATH+"/accountsByPriority.action?teamMemberId="+teamMemberId;
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);*/
       
         
        var url = CONTENXT_PATH+"/accountsByPriority.action?teamMemberId="+teamMemberId+"&teamName="+resText;
        //var url = CONTENXT_PATH+"/getEmployeeDetails.action?customerName="+ escape(test);         
        //var req = initRequest(url);
        var req = newXMLHttpRequest();
        req.onreadystatechange = function() {
            if (req.readyState == 4) {
                if (req.status == 200) {      
                    document.getElementById("loadActMessagePriority").style.display = 'none';
                    displayAccountsByPriority(req.responseText,resText);                        
                } 
            }else {
                document.getElementById("loadActMessagePriority").style.display = 'block';
            // alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        };
        req.open("GET", url, true);
        req.send(null);
        
        
    //---
    }
}

function getTeamName()
{
    //alert("getTeamName");
    var teamMemberId = document.getElementById("teamMemberId").value;
    if(teamMemberId == "-1")
    {
        teamMemberId = "-1";
    //alert("please select team member"); 
    //return false;
    }
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerPriority(req, populateTeamName);
    var url = CONTENXT_PATH+"/getTeamName.action?loginId="+teamMemberId;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function populateTeamName(resText) {

    //alert("resText-->"+resText);
    document.getElementById("teamName").innerHTML = "";
    if(resText == ""){
        var tableId = document.getElementById("tblUpdateForAccountsListByPriority");
        ClrTable(tableId);
        alert("Selected team member Team Name is not updated in the profile .Please contact operation team");
        //document.getElementById("teamNames").value = resText;
        //document.getElementById("teamName").innerHTML = "(Team Name:Selected team member Team Name is not updated in the profile .Please contact operation team )";
        //document.getElementById("teamNames").value = "";
        return false;
    }
    else if(resText == "B2B" ||  resText == "BPM" || resText == "" || resText == "E-Commerce" || resText == "SAP" || resText == "QA")
    
    {
        // document.getElementById("teamNames").value = resText;
        document.getElementById("teamName").innerHTML = "(Team Name: "+resText+" )";    
        getAccountsByPriority(resText);
        
    //document.getElementById("teamNames").value = resText;
    //document.getElementById("teamName").innerHTML = "(Team Name:This feature is enabled for specified teams(B2B,BPM,E-Commerce,SAP,QA) only )";
    //document.getElementById("teamNames").value = "";
        
    }
    else if(resText == "All"){
        var teamMemberId = document.getElementById("teamMemberId").value;
        if(teamMemberId == "-1"){
            // document.getElementById("teamNameList").style.display = 'block';
            document.getElementById("priorityId").style.display = 'table-row';
            //  document.getElementById("forSuperUser").style.display='block';
            //  document.getElementById("normalUserButton").style.display='none';
            var teamName = document.getElementById("teamNameList").value;
            if(teamName == "-1"){
                alert("Please select priority");
                return false;
            }else{
                getAccountsByPriority(teamName);
            }
            
        }else{
            //  document.getElementById("normalUserButton").style.display='block';
            //document.getElementById("teamNameList").style.display = 'none';
            document.getElementById("priorityId").style.display = 'none';
            document.getElementById("teamName").innerHTML = "(Team Name: "+resText+" )";    
            getAccountsByPriority(resText);
        }
            
    }
    else {
        var tableId = document.getElementById("tblUpdateForAccountsListByPriority");
        ClrTable(tableId);
        document.getElementById("teamNames").value = resText;
        document.getElementById("teamName").innerHTML = "(Team Name:"+resText+" )";  
        alert("This feature is enabled for specified teams(B2B,BPM,E-Commerce,SAP,QA) only. ");
        //document.getElementById("teamNames").value = "";
        return false;
    }
}
function displayAccountsByPriority(response,resText) {
    //alert("displayAccountsByPriority");
    var tableId = document.getElementById("tblUpdateForAccountsListByPriority");

    
    //headerFields = new Array("S.No","Account Name","B2BPriority","BPMPriority","SAPPriority","ECOMPriority","QAPriority","Last Activity","Alert");
    var headerFields = new Array("S.No","Account Name",resText+"Priority","Last Activity","Email");
    var dataArray = response;
    if(dataArray == "no"){
        alert("Selected team member Team Name is not updated in the profile .Please contact operation team");
    }
    else if(dataArray == "no data")
    {
        alert("No Records Found for this Search");   
    }
    else if(dataArray =="others")
    {
        alert("This feature is enabled for specified teams(B2B,BPM,E-Commerce,SAP,QA) only");   
    }
    else{
        // alert("else");
        
        //generateHeader(headerArray,tableId);
        ParseAndGenerateHTML(tableId,dataArray, headerFields);
    // document.getElementById(("footer"+tableId.id)).innerHTML = "Total Value is($):  "+dataArray[2];
    }
    
    
    
}

//new methods for account list by priority end


function displayOppDashBoard(response) {
    var tableId = document.getElementById("tblOppUpdate");
    var createdBy = document.getElementById("createdBy1").value;
    var headerFields='';
  
      //  headerFields = new Array("S.No","Account Name","Opportunity","Practice","State","Value","Created&nbsp;By","Due&nbsp;Date");
     // headerFields = new Array("S.No","Account Name","Opportunity","State","Practice","Account&nbsp;State","Value","Created&nbsp;By","Due&nbsp;Date");
     headerFields = new Array("S.No","Account Name","Opportunity","State","Practice","Account&nbsp;State","Value","Created&nbsp;By","Due&nbsp;Date","SVI#");
 
      //  headerFields = new Array("S.No","Account Name","Opportunity","Value","Due Date");
    
 
    
   
  //  var dataArray = response.split('addto');
   var dataArray = response.split('^addto');
    document.getElementById("totalOppRec").innerHTML = dataArray[1];
   document.getElementById("totalOppSum").innerHTML = "$ "+moneyFormat(dataArray[2]);
    //generateHeader(headerArray,tableId);
    ParseAndGenerateHTML(tableId,dataArray[0], headerFields);
    document.getElementById(("footer"+tableId.id)).innerHTML = "Total Value is($):  "+moneyFormat(dataArray[2]);
     document.getElementById("loadOppMessage").style.display='none';
      document.getElementById("noteLableForOpportunity").style.display='block';
      document.getElementById("opportunitySearchBUtton").disabled=false;
    
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


function ParseAndGenerateHTML(oTable,responseString,headerFields) {
    
    // alert("ParseAndGenerateHTML");
    var start = new Date();
    var fieldDelimiter = "|";
    var recordDelimiter = "^";   
    //alert('responseString%%%% ******'+responseString);
    //alert('rowCount%%%% ******'+rowCount);
    if(oTable.id == "tblrecUpdate"  ||  oTable.id == "tblStatusReport" ) {
      fieldDelimiter = "#^$";
         recordDelimiter = "*@!";  
    }
    var records = responseString.split(recordDelimiter); 
    
    generateTable(oTable,headerFields,records,fieldDelimiter);
    
    
}

/*
function generateTable(oTable, headerFields,records,fieldDelimiter) {	
    //alert("oTable.id-->"+oTable.id);
   // alert("generateTable");
    var tbody = oTable.childNodes[0];    
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    generateTableHeader(tbody,headerFields);
    
    var rowlength;
    // if(oTable.id == "tblAccountSummRep" || oTable.id == "tblActUpdate"){
  
        rowlength = records.length-1;
   
    // }
    // else {
    //     rowlength = records.length;
    // }
    //alert("rowlength-->^"+records+"rowlength-->"+rowlength);
    if(rowlength >=1 && records!=""){
        //alert("rowlength-->^"+records);
        for(var i=0;i<rowlength;i++) {
            if(oTable.id == "tblUpdate") {
                generateGreensheetRow(tbody,records[i],fieldDelimiter);  
            }else if(oTable.id == "tblAccountSummRep") {
                
                generateAccountSummRep(oTable,tbody,records[i],fieldDelimiter);
            }
            else if(oTable.id == "tblUpdateForAccountsListByPriority")
            {
                //alert("tblUpdateForAccountsListByPriority");
                generateAccountsListByPriority(oTable,tbody,records[i],fieldDelimiter); 
            //generateAccountsListByPriority1(tbody,records[i],fieldDelimiter); 
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
*/

function generateTable(oTable, headerFields,records,fieldDelimiter) {	

  var tbody = oTable.childNodes[0];    
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    generateTableHeader(tbody,headerFields);
    var rowlength;
        rowlength = records.length;
        //alert(records);
    if(rowlength >0 && records!=""){
        //alert("rowlength-->^"+records);
          if(oTable.id == "tblOppUpdate") {
               for(var i=0;i<rowlength;i++){
              generateRow(oTable,tbody,records[i],fieldDelimiter);  
          }
          }
          else if(oTable.id == "tblUpdate"){
              for(var i=0;i<rowlength;i++){
                 generateGreensheetRow(tbody,records[i],fieldDelimiter);
              }
           
           
            }
            else{
        for(var i=0;i<rowlength-1;i++) {
           // alert("i-->"+i);
            if(oTable.id == "tblAccountSummRep") {
                
                generateAccountSummRep(oTable,tbody,records[i],fieldDelimiter);
            }
            else if(oTable.id == "tblUpdateForAccountsListByPriority")
            {
                //alert("tblUpdateForAccountsListByPriority");
                generateAccountsListByPriority(oTable,tbody,records[i],fieldDelimiter); 
            //generateAccountsListByPriority1(tbody,records[i],fieldDelimiter); 
            }
            else if(oTable.id == "tblRecDashBoardSummRep")
                {
                    //alert("2");
                    generateRecDashBoardActivities(oTable,tbody,records[i],fieldDelimiter)
                }
                else if(oTable.id == "tblUpdateForAccountsListByPriority1")
            {
                //alert("tblUpdateForAccountsListByPriority");
                generateAccountsListByPriorities(oTable,tbody,records[i],fieldDelimiter); 
            //generateAccountsListByPriority1(tbody,records[i],fieldDelimiter); 
            }else if(oTable.id == "AccountRenewal1")
            {
                //alert("tblUpdateForAccountsListByPriority");
                generateAccountsListByRenewal(oTable,tbody,records[i],fieldDelimiter); 
            //generateAccountsListByPriority1(tbody,records[i],fieldDelimiter); 
            }else if(oTable.id == "AccountRenewalState")
            {
                //alert("tblUpdateForAccountsListByPriority");
                generateAccountsListByState(oTable,tbody,records[i],fieldDelimiter); 
            //generateAccountsListByPriority1(tbody,records[i],fieldDelimiter); 
            } else if(oTable.id == "tblrecUpdate") {
                generateReqReport(tbody,records[i],fieldDelimiter);  
            }else if(oTable.id == "tblStatusReport") {
                generateMonthlyStatusReport(tbody,records[i],fieldDelimiter);  
            }


            else{
                generateRow(oTable,tbody,records[i],fieldDelimiter);  
            }
        }    
            }
    } else {
        generateNoRecords(tbody,oTable);
    }
    generateFooter(tbody,oTable);
}

function generateAccountSummRep(oTable,tableBody,record,delimiter){
   // alert(record);
    var reportsTo = "";
    var empLoginId = "";
    var empName = "";
    var totalAccounts = 0;
    var workedAccounts = 0;
    var noOfActivities = 0;
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(delimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
    /*if(oTable.id == "tblAccountSummRep"){
        length = fieldLength;
    }
    else {
        length = fieldLength-1;
    }*/
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    //Assigning values start
    empName = fields[2];
    reportsTo = fields[1];
    totalAccounts = fields[3];
    noOfActivities = fields[4];
    workedAccounts = fields[5];
    empLoginId = fields[7];
    //Assigning values End
    for (var i=0;i<length;i++) {
        // cell = document.createElement( "TD" );
        // cell.className="gridColumn";
        if(i!=7) { 
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            cell.innerHTML = fields[i];
        /* if(i==1) {
        empName = fields[i];
        }
       else if(i==2) {
        reportsTo = fields[i];
        }
       else if(i==3) {
        totalAccounts = fields[i];
        }
        else if(i==4) {
        noOfActivities = fields[i];
        }
       else if(i==5) {
        workedAccounts = fields[i];
        }*/
       
        }
        //else if(i==7){
        else if(i==7 && (isTeamLead==1|| isUserManager == 1)){
            //empLoginId = fields[i];
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            //row.appendChild( cell );
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:sendFeedback('"+empLoginId+"',"+"'"+empName+"',"+"'"+reportsTo+"',"+totalAccounts+","+workedAccounts+","+noOfActivities+")");
            j.appendChild(document.createTextNode("Click Here"));
            cell.appendChild(j);
        }
        if(fields[i]!=''){
            row.appendChild( cell );
        }
    }
    
    // Variables reset start
    reportsTo = "";
    empName = "";
    empLoginId = "";
    totalAccounts = 0;
    noOfActivities = 0;
    workedAccounts = 0;
// Variables reset end
                    
//Feed back Mail End
}

//new

function generateAccountsListByPriority(oTable,tableBody,record,delimiter){
    // alert("generateAccountsListByPriority");
    var empLoginId="";
   
    var empLoginId = document.getElementById("teamMemberId").value;
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(delimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
    
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    for (var i=0;i<length;i++) {
        
        
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        if(i==0)
        {
            cell.innerHTML = fields[i];   
        }else if(i==1){
            var anc = document.createElement("a");
            anc.setAttribute("href", "getAccount.action?id="+fields[i]);
            anc.appendChild(document.createTextNode(fields[i+1]));
            //anc.appendChild(document.createTextNode("Click Here"));
            cell.appendChild(anc);
            
        }
        else if(i==4){
            //empLoginId = fields[i];
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            //row.appendChild( cell );
           
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:sendEmail('"+empLoginId+"',"+"'"+fields[2]+"',"+"'"+fields[4]+"')");
            j.appendChild(document.createTextNode("Click Here"));
            cell.appendChild(j);
           
        // cell.innerHTML = "<a href='javascript: sendEmail('"+empLoginId+"' , '"+fields[1]+"','"+fields[3]+"&quot);'><img src='/Hubble/includes/images/DBGrid/reminder.jpg' width=20/></a>";    
            
        //  cell.setAttribute("align","center"); 
           
        }
    
    
        else{
            cell.innerHTML = fields[i+1];  
        }
         
         
        if(fields[i]!=''){
            row.appendChild( cell );
        }
    }
    empLoginId = "";
      
      
}

/*function generateNoRecords(tbody,oTable) {
    var noRecords =document.createElement("TR");
    noRecords.className="gridRowEven";
    tbody.appendChild(noRecords);
    cell = document.createElement("TD");
    cell.className="gridColumn";
    
    if(oTable.id == "tblActUpdate"){
        cell.colSpan = "5";
    }else if(oTable.id == "tblOppUpdate"){
        cell.colSpan = "5";
    }else if(oTable.id == "tblUpdate"){
        cell.colSpan = "7";
    }
    else if(oTable.id == "tblAccountSummRep"){
        if(isTeamLead==1|| isUserManager == 1)
            cell.colSpan = "8";
        else
            cell.colSpan = "7";  
    }
    else if(oTable.id == "tblUpdateForAccountsListByPriority"){
        cell.colSpan = "5";
    }
    
    cell.innerHTML = "No Records Found for this Search";
    noRecords.appendChild(cell);
}*/


function generateNoRecords(tbody,oTable) {
   
    var noRecords =document.createElement("TR");
    noRecords.className="gridRowEven";
    tbody.appendChild(noRecords);
    cell = document.createElement("TD");
    cell.className="gridColumn";
    
    if(oTable.id == "tblActUpdate"){
        cell.colSpan = "5";
    }else if(oTable.id == "tblOppUpdate"){
        cell.colSpan = "10";
    }else if(oTable.id == "tblUpdate"){
        cell.colSpan = "9";
    }
    else if(oTable.id == "tblAccountSummRep"){
        if(isTeamLead==1|| isUserManager == 1)
            cell.colSpan = "8";
        else
            cell.colSpan = "7";  
    }
    else if(oTable.id == "tblUpdateForAccountsListByPriority"){
        cell.colSpan = "5";
    }
    else if(oTable.id == "tblRecDashBoardSummRep"){
        cell.colSpan = "6";
    }
    else if(oTable.id == "tblUpdateForAccountsListByPriority1")
    {
        cell.colSpan = "4";   
    }
    else if(oTable.id == "AccountRenewal1")
    {
        cell.colSpan = "14";   
    } else if(oTable.id == "AccountRenewalState")
    {
        cell.colSpan = "14";   
    } else if(oTable.id == "tblrecUpdate"){
        cell.colSpan = "11";
    }else if(oTable.id == "tblStatusReport"){
        cell.colSpan = "18";
    }else if(oTable.id == "tblActivitySummaryByLoginId"){
        cell.colSpan = "9";
    }

    cell.innerHTML = "No Records Found for this Search";
    noRecords.appendChild(cell);
}


/*function generateFooter(tbody,oTable) {
    //alert(oTable.id);
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.id="footer"+oTable.id;
    //cell.colSpan = "5";
    if(oTable.id == "tblActUpdate"){
        cell.colSpan = "5";
    }else if(oTable.id == "tblOppUpdate"){
        cell.colSpan = "5";
    }else if(oTable.id == "tblUpdate"){
        cell.colSpan = "7";
    }
    else if(oTable.id == "tblAccountSummRep"){
        if(isTeamLead==1|| isUserManager == 1)
            cell.colSpan = "8";
        else
            cell.colSpan = "7"; 
    }
    else if(oTable.id == "tblUpdateForAccountsListByPriority")
    {
        cell.colSpan = "5";   
    }
    footer.appendChild(cell);
}*/
function generateFooter(tbody,oTable) {
    //alert(oTable.id);
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.id="footer"+oTable.id;
    //cell.colSpan = "5";
    if(oTable.id == "tblActUpdate"){
        cell.colSpan = "5";
    }else if(oTable.id == "tblOppUpdate"){
        cell.colSpan = "10";
    }else if(oTable.id == "tblUpdate"){
        cell.colSpan = "9";
    }
    else if(oTable.id == "tblAccountSummRep"){
        if(isTeamLead==1|| isUserManager == 1)
            cell.colSpan = "8";
        else
            cell.colSpan = "7"; 
    }
    else if(oTable.id == "tblUpdateForAccountsListByPriority")
    {
        cell.colSpan = "5";   
    }
    else if(oTable.id == "tblRecDashBoardSummRep"){
        cell.colSpan = "6";  
    }
    else if(oTable.id == "tblUpdateForAccountsListByPriority1")
    {
        cell.colSpan = "6";   
    } else if(oTable.id =="AccountRenewal1") 
     {
           cell.colSpan = "14";   
     }
     else if(oTable.id == "tblActivitySummaryByLoginId"){
        cell.colSpan = "9";  
    } else if(oTable.id == "AccountRenewalState")
    {
        cell.colSpan = "14";   
    } else if(oTable.id == "tblrecUpdate"){
        cell.colSpan = "11";
    }else if(oTable.id == "tblStatusReport"){
        cell.colSpan = "18";
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
        row.appendChild( cell );
        cell.innerHTML = headerFields[i];
        cell.width = 120;
    }
}
/*
function generateRow(oTable,tableBody,record,delimiter) {
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(delimiter);
    fieldLength = fields.length ;
    var length;
    if(oTable.id == "tblAccountSummRep" || oTable.id == "tblUpdateForAccountsListByPriority"){
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
*/

/*
function generateRow(oTable,tableBody,record,delimiter) {
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(delimiter);
    fieldLength = fields.length ;
    var length;
    if(oTable.id == "tblAccountSummRep" || oTable.id == "tblUpdateForAccountsListByPriority"||oTable.id == "tblRecDashBoardSummRep" ){
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
*/

function generateRow(oTable,tableBody,record,delimiter) {
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(delimiter);
    fieldLength = fields.length ;
    var length;
    if(oTable.id == "tblAccountSummRep" || oTable.id == "tblUpdateForAccountsListByPriority"||oTable.id == "tblRecDashBoardSummRep"||oTable.id == "tblrecUpdate" ){
        length = fieldLength;
    } 

    
    else {
        length = fieldLength-1;
    }
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    
    if(oTable.id=="tblOppUpdate"){
        //  alert(length);
        for (var i=1;i<length;i++) {
            //  alert(i+" "+fields[i]);
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
          
           
      
                //alert(fields[i]+"fields[i]");
                cell.innerHTML = fields[i];
        
        
                if(parseInt(i,10)==2){
                    cell.innerHTML = "<a href='javascript:getAccountDetails("+fields[0]+")'>"+fields[i]+"</a>";
                }
        
         if(i==7){
            cell.style="text-align:right;width:auto;";
            cell.innerHTML="$&nbsp;"+moneyFormat(fields[i]);
        }
            
            if(fields[i]!=''){
                row.appendChild( cell );
            }
        }
    }
    else{
    for (var i=0;i<length;i++) {
        // cell = document.createElement( "TD" );
        //  cell.className="gridColumn";
        //alert(fields[i]+"fields[i]");
        //  cell.innerHTML = fields[i];
         if(oTable.id=="tblActUpdate" && i==2){
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            //row.appendChild( cell );
            var j = document.createElement("a");
            //   j.setAttribute("href", "javascript:sendFeedback('"+empLoginId+"',"+"'"+empName+"',"+"'"+reportsTo+"',"+totalAccounts+","+workedAccounts+","+noOfActivities+")");
            j.setAttribute("href", "javascript:getActivityDetailsByLoginId('"+fields[1]+"')");
            j.appendChild(document.createTextNode(fields[i]));
            cell.appendChild(j);
        }else {
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            //alert(fields[i]+"fields[i]");
            cell.innerHTML = fields[i];
        }
        
        
        
       
        //if(fields[i]!=''){
        if(fields[i]!=''|| oTable.id == "tblActUpdate"){
            row.appendChild( cell );
        }
    }
    }
}

var amount;
var temp;
var symbol ="";

function generateGreensheetRow(tableBody,record,delimiter) {
    var row;
    var cell;    
    
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    var fields = record.split(delimiter);
    for (var i=0;i<fields.length-1;i++) {
        cell = document.createElement( "TD" );
        cell.className="gridColumn";        
        row.appendChild( cell );
         if(i==8){
             cell.style="text-align:right;width:auto;";
            cell.innerHTML="$&nbsp;"+moneyFormat(fields[i]);
        }
     else if(i== 10){
            var country = document.getElementById("country").value;            
            if(country == 'India') { 
                symbol = "INR ";
            }else{
                symbol = "$ ";
            }
            var test22 = formatNumber(fields[i]);    
            temp = formattedNumber(test22,symbol);                        
            cell.innerHTML = temp;
        }else{
            cell.innerHTML = fields[i];
        }
        
    }
}




function sendFeedback(loginId,empName,reportsTo,totAccounts,workedAccs,noOfActivities) {
    var r=confirm("Please do confirm to send mail to "+empName);
    if (r==true) {
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandler(req, getResult);
        var url = CONTENXT_PATH+"/sendRepFeedback.action?loginId="+loginId+"&empName="+empName+"&reportsTo="+reportsTo+"&totalAccounts="+totAccounts+"&noOfActivities="+noOfActivities+"&workedAccounts="+workedAccs;
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
}



function sendEmail(loginId,accName,ActivityDate) {
   
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerPriority(req, getResult);
    //alert("HTTP error ---"+req.status+" : "+req.statusText);
    var url = CONTENXT_PATH+"/sendPriorityEmail.action?loginId="+loginId+"&accountName="+accName+"&lastActivityDate="+ActivityDate;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
       
}



function getResult(resText) {
    alert(resText);
}

function hidePriority()
{
    //alert("hello");
    // document.getElementById("teamNameList").style.display='none';
    document.getElementById("priorityId").style.display = 'none';
    document.getElementById("teamNameList").value="-1";
    document.getElementById("teamName").innerHTML="";
}


////new method for rec dashboard
//
//function getRecActivitiesByRep() {
// var startdate = document.getElementById("startDate").value;
//    var enddate = document.getElementById("endDate").value;
//    if(startdate==""||startdate==null||enddate==""||enddate==null)
//        {
//    validateDates();
//    compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value);
//    return false;
//        }
//    var tableId = document.getElementById("tblRecDashBoardSummRep");
//    ClrTable(tableId);
//    var checkResult = compareDates(startdate,enddate);
//    if(!checkResult) {
//        return false;
//    }
//     var createdBy = document.getElementById("createdBy").value;
//     var activityType = document.getElementById("activityType").value;
//    if(createdBy == '-1') {
//        createdBy = '--Please Select--';
//    }
//    //dueStartDate = replaceDateSplits(dueStartDate);
//    var req = newXMLHttpRequest();
//    req.onreadystatechange = readyStateHandler(req, displayRecActivitiesByRep);
//    var url = CONTENXT_PATH+"/consultantActivitiesByRep.action?dashBoardStartDateRep="+startdate+"&dashBoardEndDateRep="+enddate+"&createdBy="+createdBy+"&activityType="+activityType;
//    req.open("GET",url,"true");
//    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
//    req.send(null);
//
//}


function displayRecActivitiesByRep(response) {
    var tableId = document.getElementById("tblRecDashBoardSummRep");
    var headerFields = new Array("S.No","Consultant Name","Activity Type","Activity By","Activity Date","Comments");
    var dataArray = response;
    ParseAndGenerateHTML(tableId,dataArray, headerFields);
// document.getElementById(("footer"+tableId.id)).innerHTML = "Total Value is($):  "+dataArray[2];
}

//new method rec dashboard

function getRecDashBoardComments(comments) {
    var comments = comments;
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,populateRecDashboardComments);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/popupRecDashBoardComments.action?comments="+comments;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateRecDashboardComments(text) {
    var background = "#3E93D4";
    var title = "Activity Comments";
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

function generateRecDashBoardActivities(oTable,tableBody,record,delimiter){
    // alert("1");
    var activityType = "";
    var consultantName = "";
    var activityBy = "";
    var activityDate = 0;
    var comments = 0;
    //var noOfActivities = 0;
    var row;
    var cell;
    var fieldLength;
    //alert(record);
    var fields = record.split(delimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
    /*if(oTable.id == "tblAccountSummRep"){
        length = fieldLength;
    }
    else {
        length = fieldLength-1;
    }*/
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    //Assigning values start
    activityType = fields[1];
    consultantName = fields[2];
    activityBy = fields[3];
    activityDate = fields[4];
    comments = fields[5];
    //alert("-->"+comments+"<--");
    //empLoginId = fields[7];
    //Assigning values End
    //alert(length);
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
           
        if(i>0 && i!=5) { 
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            cell.innerHTML = fields[i];
        //cell.setAttribute('align','center');
        }
        //else if(i==7){
        else if(i==5){
            //empLoginId = fields[i];
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            cell.setAttribute('align','center');
         
            //row.appendChild( cell );
            var j = document.createElement("a");
            
            j.setAttribute("href", "javascript:getRecDashBoardComments('"+comments+"')");
            j.appendChild(document.createTextNode("view"));
            cell.appendChild(j);
        }
        // if(fields[i]!=''){
        row.appendChild( cell );
    // }
    }              
//Feed back Mail End
}


/*
 * Get Activity details by Employee LoginId
 * Author : Santosh Kola
 * Date : 11/15/2014
 * 
 */
function getActivityDetailsByLoginId(loginId) {
    // alert(loginId);
    
    
    var activityStaDate=document.getElementById("dashBoardStartDate").value;
    var activityEndDate=document.getElementById("dashBoardEndDate").value;
    
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,populateActivityDetails);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/getActivityDetailsByLoginId.action?loginId="+loginId+"&activityStaDate="+activityStaDate+"&activityEndDate="+activityEndDate;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populateActivityDetails(text) {
    var background = "#3E93D4";
    var title = "Activity Details";
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



function getsalesRecActivitiesAsGraph() {
    document.getElementById("activitygraphSearch").disabled=true;
   var tableId = document.getElementById("tblActivitySummaryByLoginId");
    var sessionTitleType=document.getElementById("TitleTypeId").value;
    //  alert(tableId)
    ClrTable(tableId);

    document.getElementById("resultMessage").style.display="none";
    document.getElementById("recDashBoardActivitygraph").style.display="none";
    var createdBy = document.getElementById("teamMemberId2").value;
    var activityType = document.getElementById("activitytype1").value;
    var startDate=    document.getElementById("startDateSummaryGraph").value;
    var endDate=    document.getElementById("endDateSummaryGraph").value;
var teamLeanth=document.getElementById("teamMemberId2").length;
var titleType=document.getElementById("titleType").value;
var campaignId=document.getElementById("campaignId").value;

    if(startDate==""||startDate==null||endDate==""||endDate==null)
    {
        validateDates();
    
        compareDates(document.getElementById('startDateSummaryGraph').value,document.getElementById('endDateSummaryGraph').value);
        return false;
    }
     var teamMemberCheck=document.getElementById('teamMemberCheck').checked;
    if(teamMemberCheck==true)
    {
        teamMemberCheck=1;  
    }
    else
    {
        teamMemberCheck=0;    
    }
    var checkResult = compareDates(startDate,endDate);
    if(!checkResult) {
        return false;
    }    
  //  alert(teamLeanth);
    if(teamLeanth==1 ||  createdBy!='-1'){
     // alert("if");
        var req = newXMLHttpRequest();
        req.onreadystatechange = loadActMessageASh(req, displaysalesActivitiesAsGraphInd);
       // var url = CONTENXT_PATH+"/salesActivitiesAsGraphInd.action?startDateSummaryGraph="+startDate+"&endDateSummaryGraph="+endDate+"&createdBy="+createdBy+"&activityType="+activityType +"&teamMemberCheck="+teamMemberCheck;
    //   var url = CONTENXT_PATH+"/salesActivitiesAsGraphInd.action?startDateSummaryGraph="+startDate+"&endDateSummaryGraph="+endDate+"&createdBy="+createdBy+"&activityType="+activityType +"&teamMemberCheck="+teamMemberCheck+"&titleType="+titleType;
    
//var url = CONTENXT_PATH+"/salesActivitiesAsGraphInd.action?startDateSummaryGraph="+startDate+"&endDateSummaryGraph="+endDate+"&createdBy="+createdBy+"&activityType="+activityType +"&teamMemberCheck="+teamMemberCheck+"&titleType="+titleType+"&campaignId="+campaignId;
 var url = CONTENXT_PATH+"/salesActivitiesAsGraphInd.action?startDateSummaryGraph="+startDate+"&endDateSummaryGraph="+endDate+"&createdBy="+createdBy+"&activityType="+activityType +"&teamMemberCheck="+teamMemberCheck+"&titleType="+titleType+"&campaignId="+campaignId+"&sessionTitleType="+sessionTitleType;
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
       
    }else {
          // alert("else");
        var req = newXMLHttpRequest();
        req.onreadystatechange = loadActMessageASh(req, displaysalesActivitiesAsGraph);
       // var url = CONTENXT_PATH+"/salesActivitiesAsGraph.action?startDateSummaryGraph="+startDate+"&endDateSummaryGraph="+endDate+"&createdBy="+createdBy+"&activityType="+activityType;
        var url = CONTENXT_PATH+"/salesActivitiesAsGraph.action?startDateSummaryGraph="+startDate+"&endDateSummaryGraph="+endDate+"&createdBy="+createdBy+"&activityType="+activityType+"&campaignId="+campaignId;
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }

}


function displaysalesActivitiesAsGraphInd(response) {
    //var tableId = document.getElementById("tblRecDashBoardSummGraph");
//alert("Haii");
    if(response=="addto0"){ //alert("injuhjkhbhjvi");
        document.getElementById("resultMessage").style.display="block";
        document.getElementById("resultMessage").innerHTML="No data available"; 
       
      
    }else{
        document.getElementById("recDashBoardActivitygraph").style.display="block";
        //   alert("injuhjkhbhjvi");
        var result = response.split("^");
        var arraydata = [['Activity Name', 'Activity Count']];
        for(var i=0; i<result.length-1; i++){
        
            var res = result[i].split("|");
            var dArray = [res[1],parseInt(res[2])];
            //alert(dArray);
            arraydata.push(dArray);
        }

        var data = google.visualization.arrayToDataTable(arraydata);

        var options = {
            title: 'Activity Summary Graph' ,
            legend: 'left',
            chartArea:{
                width:"100%"
            },
            is3D: true,
         pieSliceText: 'value',
        sliceVisibilityThreshold: 0
        };
        
         //alert("haii");
        
        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
        
        //-------------------
         function selectHandler() {
             
        // alert("poType-->");
        var selectedItem = chart.getSelection()[0];
        if (selectedItem) {
            var selectedActivity = data.getValue(selectedItem.row, 0);
           // alert("selectedActivity-->"+selectedActivity);
             getActivitySummaryByLoginIdInd(selectedActivity);
        }
    }
    google.visualization.events.addListener(chart, 'select', selectHandler);   
        
        //--------------
          
       // chart.draw(data, options,dArray);
        chart.draw(data, options);
    }
    
    
    document.getElementById("activitygraphSearch").disabled=false;
   
}



function displaysalesActivitiesAsGraph(response) {
    //var tableId = document.getElementById("tblRecDashBoardSummGraph");
   
    if(response=="addto0"){
        //alert("injui");
        document.getElementById("resultMessage").style.display="block";
        document.getElementById("resultMessage").innerHTML="No data available";
      
    }else{
       // alert(response);
        document.getElementById("recDashBoardActivitygraph").style.display="block";
        var result = response.split("^");
        var arraydata = [['Emp Name', 'Activity Count']];
        for(var i=0; i<result.length-1; i++){
        
            var res = result[i].split("|");
            var dArray = [res[1],parseInt(res[2])];
            //alert(dArray);
            arraydata.push(dArray);
        }

        var data = google.visualization.arrayToDataTable(arraydata);

        var options = {
            title: 'Activity Summary Graph' ,
            legend: 'left',
            chartArea:{
                width:"100%"
            },
            is3D: true,
            pieSliceText: 'value',
        sliceVisibilityThreshold: 0
         
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
 function selectHandler() {
             
        // alert("poType-->");
        var selectedItem = chart.getSelection()[0];
        if (selectedItem) {
            var loginId = data.getValue(selectedItem.row, 0);
          //  alert("poType-->"+poType);
          getActivitySummaryByLoginId(loginId);
        }
    }
    google.visualization.events.addListener(chart, 'select', selectHandler);   
        chart.draw(data, options,dArray);
    }
    document.getElementById("activitygraphSearch").disabled=false;
}    



function loadActMessageASh(req,responseHandler) {
    // alert("yes");
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("loadActMessageASh").style.display = 'none';
                responseHandler(req.responseText);
            }
        } else {
            document.getElementById("loadActMessageASh").style.display = 'block';
        //alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }
}
function getTeamNames()
{
    //alert("getTeamName");
    var teamMemberId = document.getElementById("teamMemberId1").value;
    if(teamMemberId == "-1")
    {
        teamMemberId = "-1";
    //alert("please select team member"); 
    //return false;
    }
    document.getElementById("loadActMessagePriority1").style.display = 'block';
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerPriority(req, populateTeamNames);
    var url = CONTENXT_PATH+"/getTeamName.action?loginId="+teamMemberId;
    //  alert(url);
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function populateTeamNames(resText) {

    //alert("resText-->"+resText);
    document.getElementById("loadActMessagePriority1").style.display = 'none';
    document.getElementById("loadActMessagePriority").style.display = 'none';
    document.getElementById("teamName1").innerHTML = "";
    // if(resText == ""){
    //       var tableId = document.getElementById("tblUpdateForAccountsListByPriority1");
    //       ClrTable(tableId);
    //     //  alert("Selected team member Team Name is not updated in the profile .Please contact operation team");
    //       alert("No Records Found for this Search");  
    //       return false;
    //    }
    //    else 
    //        if(resText == "B2B" ||  resText == "BPM" || resText == "" || resText == "E-Commerce" || resText == "SAP" || resText == "QA")
    //    
    //        {
    //       // document.getElementById("teamNames").value = resText;
    //       document.getElementById("teamName1").innerHTML = "(Team Name: "+resText+" )";    
    //        getAccountsByPriorities(resText);
    //        
    //        //document.getElementById("teamNames").value = resText;
    //        //document.getElementById("teamName").innerHTML = "(Team Name:This feature is enabled for specified teams(B2B,BPM,E-Commerce,SAP,QA) only )";
    //        //document.getElementById("teamNames").value = "";
    //        
    //        }
    // else 
    //if(resText == "All"){
    var teamMemberId = document.getElementById("teamMemberId1").value;
    //             if(teamMemberId == "-1"){
    //           // document.getElementById("teamNameList").style.display = 'block';
    //             document.getElementById("priorityId").style.display = 'table-row';
    //          //  document.getElementById("forSuperUser").style.display='block';
    //          //  document.getElementById("normalUserButton").style.display='none';
    //            var teamName = document.getElementById("teamNameList").value;
    //            if(teamName == "-1"){
    //                alert("Please select priority");
    //                return false;
    //            }else{
    //            getAccountsByPriorities(teamName);
    //            }
    //            
    //             }else{
    //  document.getElementById("normalUserButton").style.display='block';
    //document.getElementById("teamNameList").style.display = 'none';
    document.getElementById("priorityId").style.display = 'none';
    document.getElementById("teamName1").innerHTML = "(Team Name: "+resText+" )";    
    getAccountsByPriorities(resText);
    //    }
            
    //        }
    //else {
    //var tableId = document.getElementById("tblUpdateForAccountsListByPriority1");
    //ClrTable(tableId);
    ////document.getElementById("teamNames").value = resText;
    //document.getElementById("teamName1").innerHTML = "(Team Name:"+resText+" )";  
    ////alert("This feature is enabled for specified teams(B2B,BPM,E-Commerce,SAP,QA) only. ");
    // alert("No Records Found for this Search");  
    ////document.getElementById("teamNames").value = "";
    //return false;
    //}
    var teamMemberId = document.getElementById("teamMemberId1").value;
    if(teamMemberId=="-1"){
        document.getElementById("teamName1").innerHTML = ""; 
    }
}
function getAccountsByPriorities() {
    // alert("getAccountsByPriority1");
    document.getElementById("prioritySearchbutton").disabled=true;
    document.getElementById("noteLableForproirity").style.display='none';
    isTeamLead = document.getElementById("isTeamLead").value;
    isUserManager = document.getElementById("isUserManager").value;
    // alert("restext-->"+resText);
    var tableId = document.getElementById("tblUpdateForAccountsListByPriority1");
    ClrTable(tableId);
    var teamMemberId = document.getElementById("teamMemberId1").value;
    var teamNameList = document.getElementById("teamNameList1").value;
   // var accountsStartDate = document.getElementById("dashBoardAccountsStartDatePriority").value;
   // var accountsEndDate = document.getElementById("dashBoardAccountsEndDatePriority").value;
    //alert("teamMemberId-->"+teamMemberId);
    //dueStartDate = replaceDateSplits(dueStartDate);
    if(teamMemberId == "")
    {
        alert("please select team member"); 
        return false;
    }
    else
    {
        /*var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerPriority(req, displayAccountsByPriority);
        var url = CONTENXT_PATH+"/accountsByPriority.action?teamMemberId="+teamMemberId;
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);*/
       
         
       var url = CONTENXT_PATH+"/accountsByPriorities.action?teamMemberId="+teamMemberId+"&teamName="+teamNameList;
    //  var url = CONTENXT_PATH+"/accountsByPriorities.action?teamMemberId="+teamMemberId+"&teamName="+teamNameList+"&dashBoardStartDate="+accountsStartDate+"&dashBoardEndDate="+accountsEndDate;

        //var url = CONTENXT_PATH+"/getEmployeeDetails.action?customerName="+ escape(test);         
        //var req = initRequest(url);
        //  alert(url);
        var req = newXMLHttpRequest();
        req.onreadystatechange = function() {
            if (req.readyState == 4) {
                if (req.status == 200) {      
                    document.getElementById("loadActMessagePriority1").style.display = 'none';
                    displayAccountsByPriorities(req.responseText);                        
                } 
            }else {
                document.getElementById("loadActMessagePriority1").style.display = 'block';
            // alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        };
        req.open("GET", url, true);
        req.send(null);
        
        
    //---
    }
}
function displayAccountsByPriorities(response) {
    //alert("displayAccountsByPriority");
    var tableId = document.getElementById("tblUpdateForAccountsListByPriority1");

    var priority = document.getElementById("teamNameList1").value;
    //headerFields = new Array("S.No","Account Name","B2BPriority","BPMPriority","SAPPriority","ECOMPriority","QAPriority","Last Activity","Alert");
    
      var teamMemberId = document.getElementById("teamMemberId1").value;
       var headerFields ="";
      //if(teamMemberId=='-1'){
           headerFields = new Array("S.No","Account Name",priority+"&nbsp;Priority","Done By","Last&nbsp;Activity&nbsp;Type","Last Activity");
//      }
// else{
//      headerFields = new Array("S.No","Account Name",priority+"&nbsp;Priority","Last Activity Type","Last Activity");
// }
    var dataArray = response;
    if(dataArray == "no"){
    //  alert("Selected team member Team Name is not updated in the profile .Please contact operation team");
    }
    else if(dataArray == "no data")
    {
        alert("No Records Found for this Search");   
    }
    //   else if(dataArray =="others")
    //       {
    //        alert("This feature is enabled for specified teams(B2B,BPM,E-Commerce,SAP,QA) only");   
    //       }
    else{
        // alert("else");
        
        //generateHeader(headerArray,tableId);
        ParseAndGenerateHTML(tableId,dataArray, headerFields);
        document.getElementById("noteLableForproirity").style.display='block';
    // document.getElementById(("footer"+tableId.id)).innerHTML = "Total Value is($):  "+dataArray[2];
    }
    document.getElementById("prioritySearchbutton").disabled=false;
  
    
    
}
function generateAccountsListByPriorities(oTable,tableBody,record,delimiter){
    // alert("generateAccountsListByPriority");
    var empLoginId="";
   
    var empLoginId = document.getElementById("teamMemberId1").value;
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(delimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
    
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    for (var i=0;i<length-1;i++) {
        
        
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        if(i==0)
        {
            cell.innerHTML = fields[i];   
        }
        //             else if(i==1){
        //            var anc = document.createElement("a");
        //            anc.setAttribute("href", "getAccount.action?id="+fields[i]);
        //            anc.appendChild(document.createTextNode(fields[i+1]));
        //            //anc.appendChild(document.createTextNode("Click Here"));
        //            cell.appendChild(anc);
        //            
        //         }
        //        else if(i==4){
        //            //empLoginId = fields[i];
        //            cell = document.createElement( "TD" );
        //            cell.className="gridColumn";
        //            //row.appendChild( cell );
        //           
        //            var j = document.createElement("a");
        //            j.setAttribute("href", "javascript:sendEmail('"+empLoginId+"',"+"'"+fields[2]+"',"+"'"+fields[4]+"')");
        //            j.appendChild(document.createTextNode("Click Here"));
        //            cell.appendChild(j);
        //           
        //           // cell.innerHTML = "<a href='javascript: sendEmail('"+empLoginId+"' , '"+fields[1]+"','"+fields[3]+"&quot);'><img src='/Hubble/includes/images/DBGrid/reminder.jpg' width=20/></a>";    
        //            
        //          //  cell.setAttribute("align","center"); 
        //           
        //        }
    
        else if(i==2){
            if(fields[i+1]=='0'){
                cell.innerHTML ='-';
            }
            else{
                cell.innerHTML = fields[i+1];  
            }
        }
    
        else{
            cell.innerHTML = fields[i+1];  
        }
         
         
        if(fields[i]!=''){
            row.appendChild( cell );
        }
    }
    empLoginId = "";
      
      
}


// Account Renewal code changes 11/10/2015

function getaccountRenewalByTeamMember() {
    //alert("AccountRenewal1");
    // alert("restext-->"+resText);
    var tableId = document.getElementById("AccountRenewal1");
    //  alert(tableId)
    ClrTable(tableId);
    var teamMemberId = document.getElementById("teamMemberIdRenewal").value;
    //alert("teamMemberId-->"+teamMemberId);
    //dueStartDate = replaceDateSplits(dueStartDate);
    //    if(teamMemberId == "")
    //    {
    //        alert("please select team member"); 
    //        return false;
    //    }
    //   else
    //  {
    /*var req = newXMLHttpRequest();
			req.onreadystatechange = readyStateHandlerPriority(req, displayAccountsByPriority);
			var url = CONTENXT_PATH+"/accountsByPriority.action?teamMemberId="+teamMemberId;
			req.open("GET",url,"true");
			req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			req.send(null);*/
       
         
    var url = CONTENXT_PATH+"/accountRenewalByTeamMember.action?teamMemberId="+teamMemberId;
    //var url = CONTENXT_PATH+"/getEmployeeDetails.action?customerName="+ escape(test);         
    //var req = initRequest(url);
    //alert(url);
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("loadActMessageRenewal1").style.display = 'none';
                displayAccountsByRenewal(req.responseText);                        
            } 
        }else {
            document.getElementById("loadActMessageRenewal1").style.display = 'block';
        // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    };
    req.open("GET", url, true);
    req.send(null); 
//---
//   }
}


function displayAccountsByRenewal(response) {
    //alert("displayAccountsByPriority");
    var tableId = document.getElementById("AccountRenewal1");
	
    // var priority = document.getElementById("teamNameList1").value;
    //headerFields = new Array("S.No","Account Name","B2BPriority","BPMPriority","SAPPriority","ECOMPriority","QAPriority","Last Activity","Alert");
    var headerFields = new Array("S.No","TeamMember","Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec");
    //system.out.println(response);
    var dataArray = response;
    if(dataArray == "no"){
    //  alert("Selected team member Team Name is not updated in the profile .Please contact operation team");
    }
    else if(dataArray == "no data")
    {
        alert("No Records Found for this Search");   
    }
    //   else if(dataArray =="others")
    //       {
    //        alert("This feature is enabled for specified teams(B2B,BPM,E-Commerce,SAP,QA) only");   
    //       }
    else{
        // alert("else");
        //generateHeader(headerArray,tableId);
        ParseAndGenerateHTML(tableId,dataArray, headerFields);
    // document.getElementById(("footer"+tableId.id)).innerHTML = "Total Value is($):  "+dataArray[2];
    }
}
		
		
		
function generateAccountsListByRenewal(oTable,tableBody,record,delimiter){
    // alert("generateAccountsListByPriority");
    var empLoginId=""; 
    var empLoginId = document.getElementById("teamMemberId1").value;
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(delimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
  //  alert(record);
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    // alert(fields);
    for (var i=0;i<length;i++) {            
        cell = document.createElement( "TD" );
        cell.className="gridColumn";       
        if(i==0)
        {
            cell.setAttribute("align","center");
            cell.innerHTML = fields[i];   
        }
        //             else if(i==1){
        //            var anc = document.createElement("a");
        //            anc.setAttribute("href", "getAccount.action?id="+fields[i]);
        //            anc.appendChild(document.createTextNode(fields[i+1]));
        //            //anc.appendChild(document.createTextNode("Click Here"));
        //            cell.appendChild(anc);
        //            
        //         }
        //        else if(i==4){
        //            //empLoginId = fields[i];
        //            cell = document.createElement( "TD" );
        //            cell.className="gridColumn";
        //            //row.appendChild( cell );
        //           
        //            var j = document.createElement("a");
        //            j.setAttribute("href", "javascript:sendEmail('"+empLoginId+"',"+"'"+fields[2]+"',"+"'"+fields[4]+"')");
        //            j.appendChild(document.createTextNode("Click Here"));
        //            cell.appendChild(j);
        //           
        //           // cell.innerHTML = "<a href='javascript: sendEmail('"+empLoginId+"' , '"+fields[1]+"','"+fields[3]+"&quot);'><img src='/Hubble/includes/images/DBGrid/reminder.jpg' width=20/></a>";    
        //            
        //          //  cell.setAttribute("align","center"); 
        //           
        //        }
        else if(i==1){
            cell.innerHTML = fields[i];  
        }
        else if(i==2){
            if(fields[i]=='0'){            
                cell.innerHTML ='0';
            }
            else{
                cell.innerHTML = fields[i];  
            }
        }
        else{
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
    empLoginId = "";     
}


function getActivitySummaryByLoginId(loginId){
    
     //var createdBy = document.getElementById("teamMemberId2").value;
    var activityType = document.getElementById("activitytype1").value;
    var startDate=    document.getElementById("startDateSummaryGraph").value;
    var endDate=    document.getElementById("endDateSummaryGraph").value;
//alert("jgbhgv");
    var campaignId=document.getElementById("campaignId").value;
    //alert("loginId-->"+loginId);
   
    var tableId = document.getElementById("tblActivitySummaryByLoginId");
    //  alert(tableId)
    ClrTable(tableId);
   // var teamMemberId = document.getElementById("teamMemberIdRenewal").value;
  
  //  var url = CONTENXT_PATH+"/accountRenewalByTeamMember.action?teamMemberId="+teamMemberId;
  var url = CONTENXT_PATH+"/getActivitySummaryByLoginId.action?startDateSummaryGraph="+startDate+"&endDateSummaryGraph="+endDate+"&createdBy="+loginId+"&activityType="+activityType+"&campaignId="+campaignId;
  
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("loadActMessageASh").style.display = 'none';
                displayActivitySummaryByLoginId(req.responseText);                        
            } 
        }else {
            document.getElementById("loadActMessageASh").style.display = 'block';
        // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    };
    req.open("GET", url, true);
    req.send(null); 
//---
//   }
}




function displayActivitySummaryByLoginId(response) {
    
  //  alert("Activity Response-->"+response);
  var tableId = document.getElementById("tblActivitySummaryByLoginId");
// var headerFields = new Array("S.No","CreatedBy","ActivityType","STATUS","Priority","CreatedDate","Comments");
//var headerFields = new Array("S.No","AccountName","CreatedBy","ActivityType","STATUS","Priority","CreatedDate","Description");
var headerFields = new Array("S.No","AccountName","CreatedBy","ActivityType","STATUS","Priority","CreatedDate","ContactName's","Description");
  var dataArray = response;
    if(dataArray == "no"){
    }
    else if(dataArray == "no data")
    {
        alert("No Records Found for this Search");   
    }
    else{
        NewParseAndGenerateHTML(tableId,dataArray, headerFields);
    }
}
		
 function generateActivitySummaryByLoginId(oTable,tableBody,record,delimiter){
    // alert("generateAccountsListByPriority");
 //  alert("starting record---->"+record);
    var row;
    var cell;
    var fieldLength;
  //  var fields = record.split(delimiter);
    var fields = record.split("#^$");
    fieldLength = fields.length ;
    var length = fieldLength;
 //   alert(length);
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
     //alert(fields);
    for (var i=0;i<length;i++) {  
        if(parseInt(i,10)<(parseInt(length,10)-2)){
              cell = document.createElement( "TD" );
        cell.className="gridColumn";       
       
            cell.setAttribute("align","center");
            
            cell.innerHTML = fields[i];   
        }else if(parseInt(i,10)<(parseInt(length,10)-1)){
              cell = document.createElement( "TD" );
        cell.className="gridColumn";       
       //onmouseover="javascript:Tip('<%=contactNames%>');" onmouseout="javascript:UnTip();"
       // alert("fields[7]"+fields[7]);
         cell = document.createElement( "TD" );
        cell.className="gridColumn";       
       
            cell.setAttribute("align","center");
            
            cell.innerHTML = fields[i];   
//        var j = document.createElement("a");
//            j.setAttribute("href", "#");
//            j.setAttribute("onmouseover", "javascript:Tip(escape'("+fields[7]+")')");
//            j.setAttribute("onmouseout", "javascript:UnTip()");
//            j.appendChild(document.createTextNode($escape((fields[7])).text()));
//            cell.appendChild(j);
          
        }else {
             cell = document.createElement( "TD" );
            cell.className="gridColumn";
            //row.appendChild( cell );
           
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:getActivityDecriptionById('"+fields[i]+"')");
            j.appendChild(document.createTextNode("Click Here"));
            cell.appendChild(j);
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
      
}



function NewParseAndGenerateHTML(oTable,responseString,headerFields) {
    
    // alert("ParseAndGenerateHTML");
    var start = new Date();
    var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";   
    //alert('responseString%%%% ******'+responseString);
    //alert('rowCount%%%% ******'+rowCount);
    var records = responseString.split(recordDelimiter); 
    generateActivitySummaryRows(oTable,headerFields,records,fieldDelimiter);
}


function generateActivitySummaryRows(oTable, headerFields,records,fieldDelimiter) {	

  var tbody = oTable.childNodes[0];    
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    generateTableHeader(tbody,headerFields);
    var rowlength;
        rowlength = records.length;
       // alert(rowlength);
    if(rowlength >0 && records!=""){
        //alert("rowlength-->^"+records);
       
        for(var i=0;i<rowlength-1;i++) {
          
                
                generateActivitySummaryByLoginId(oTable,tbody,records[i],fieldDelimiter);
        }
        
    } else {
        generateNoRecords(tbody,oTable);
    }
    generateFooter(tbody,oTable);
}


//------------------

//getActivitySummaryByLoginIdInd


function getActivitySummaryByLoginIdInd(activityType){
   // alert("haii");
     //var createdBy = document.getElementById("teamMemberId2").value;
   // var activityType = document.getElementById("activitytype1").value;
    var teamMemberId2 = document.getElementById("teamMemberId2").value;
    var startDate=    document.getElementById("startDateSummaryGraph").value;
    var endDate=    document.getElementById("endDateSummaryGraph").value;
var teamMemberCheck=document.getElementById('teamMemberCheck').checked;
var campaignId=document.getElementById("campaignId").value;
var titleType=document.getElementById("titleType").value;
    if(teamMemberCheck==true)
    {
        teamMemberCheck=1;  
    }
    else
    {
        teamMemberCheck=0;    
    }
	
//alert("jgbhgv");
    
    //alert("loginId-->"+loginId);
   
    var tableId = document.getElementById("tblActivitySummaryByLoginId");
    //  alert(tableId)
    ClrTable(tableId);
   // var teamMemberId = document.getElementById("teamMemberIdRenewal").value;
  
  //  var url = CONTENXT_PATH+"/accountRenewalByTeamMember.action?teamMemberId="+teamMemberId;
 // var url = CONTENXT_PATH+"/getActivitySummaryByLoginId.action?startDateSummaryGraph="+startDate+"&endDateSummaryGraph="+endDate+"&createdBy="+teamMemberId2+"&activityType="+activityType;
 //var url = CONTENXT_PATH+"/getActivitySummaryByLoginId.action?startDateSummaryGraph="+startDate+"&endDateSummaryGraph="+endDate+"&createdBy="+teamMemberId2+"&activityType="+activityType+"&teamMemberCheck="+teamMemberCheck;
 //var url = CONTENXT_PATH+"/getActivitySummaryByLoginId.action?startDateSummaryGraph="+startDate+"&endDateSummaryGraph="+endDate+"&createdBy="+teamMemberId2+"&activityType="+activityType+"&teamMemberCheck="+teamMemberCheck+"&titleType="+titleType;
  var url = CONTENXT_PATH+"/getActivitySummaryByLoginId.action?startDateSummaryGraph="+startDate+"&endDateSummaryGraph="+endDate+"&createdBy="+teamMemberId2+"&activityType="+activityType+"&teamMemberCheck="+teamMemberCheck+"&titleType="+titleType+"&campaignId="+campaignId;
  
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("loadActMessageASh").style.display = 'none';
                displayActivitySummaryByLoginId(req.responseText);                        
            } 
        }else {
            document.getElementById("loadActMessageASh").style.display = 'block';
        // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    };
    req.open("GET", url, true);
    req.send(null); 
//---
//   }
}

//activityId
function getActivityDecriptionById(activityId){
     var url = CONTENXT_PATH+"/getActivityDecriptionById.action?activityId="+activityId;
  
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
               // document.getElementById("loadActMessageASh").style.display = 'none';
                populateActivityDecription(req.responseText);                        
            } 
        }else {
           // document.getElementById("loadActMessageASh").style.display = 'block';
        // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    };
    req.open("GET", url, true);
    req.send(null); 
}


function populateActivityDecription(text) {
    var background = "#3E93D4";
    var title = "Activity Description";
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
    }else{
         //Create the popup       
        popup = window.open("","window","channelmode=0,width=500,height=300,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}
function getAccountDetails(accountId){
    window.location="getAccount.action?id="+accountId;
}



  function getaccountRenewalByState() {
//alert("entered into getaccountRenewalByState")
    var tableId = document.getElementById("AccountRenewalState");
    //  alert(tableId)
    ClrTable(tableId);
    var renewalState= document.getElementById("statesList1").value;
    //alert("renewalState-->"+renewalState);
        var url = CONTENXT_PATH+"/accountRenewalByState.action?renewalState="+renewalState;
        //alert("the URL"+url);
      var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("loadActMessageRenewalByState").style.display = 'none';
                displayAccountsRenewalByState(req.responseText);                        
            } 
        }else {
            document.getElementById("loadActMessageRenewalByState").style.display = 'block';
        // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    };
    req.open("GET", url, true);
    req.send(null); 
//---
//   }
}
function displayAccountsRenewalByState(response)
{
    
     var tableId = document.getElementById("AccountRenewalState");
  var headerFields = new Array("S.No","State","Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec");
 
    var dataArray = response;
    if(dataArray == "no"){
    //  alert("Selected team member Team Name is not updated in the profile .Please contact operation team");
    }
    else if(dataArray == "no data")
    {
        alert("No Records Found for this Search");   
    }
    //   else if(dataArray =="others")
    //       {
    //        alert("This feature is enabled for specified teams(B2B,BPM,E-Commerce,SAP,QA) only");   
    //       }
    else{
        // alert("else");
        //generateHeader(headerArray,tableId);
        ParseAndGenerateHTML(tableId,dataArray, headerFields);
    // document.getElementById(("footer"+tableId.id)).innerHTML = "Total Value is($):  "+dataArray[2];
    }
}


function generateAccountsListByState(oTable,tableBody,record,delimiter){
    // alert("generateAccountsListByPriority");
    var empLoginId=""; 
    var empLoginId = document.getElementById("teamMemberId1").value;
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(delimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
  //  alert(record);
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    // alert(fields);
    for (var i=0;i<length;i++) {            
        cell = document.createElement( "TD" );
        cell.className="gridColumn";       
        if(i==0)
        {
            cell.setAttribute("align","center");
            cell.innerHTML = fields[i];   
        }
       
        else if(i==1){
            cell.innerHTML = fields[i];  
        }
        else if(i==2){
            if(fields[i]=='0'){            
                cell.innerHTML ='0';
            }
            else{
                cell.innerHTML = fields[i];  
            }
        }
        else{
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
  
}

/*****************Account renewal by state******************************/


/********************Requirement Summary Satart ************************/
function getRequirement()
{
    
    // document.getElementById("requirementSearchBUtton").disabled=true;
    document.getElementById("noteLableForReq").style.display='none';
    var tableId = document.getElementById("tblrecUpdate");
    ClrTable(tableId);
     document.getElementById("loadreqMessage").style.display='block';
     var requirementstatus = document.getElementById("reqstatushide").value;
    var practice = document.getElementById("reqpracticeId").value;
    var startDate = document.getElementById("reqStartDate").value;
    var endDate = document.getElementById("reqEndDate").value;
    var relatedTeam = document.getElementById("createdByTeam").value;
    var reqJobTitle=document.getElementById("jobTitle").value;
    // alert("entered into getRequirement"+requirementstatus+".."+practice+".."+startDate+".."+endDate+".."+relatedTeam+"..."+reqJobTitle);

 
    if(relatedTeam == '-1') {
        relatedTeam = 'All';
    }
    
     var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("loadreqMessage").style.display = 'none';
                displayReqDashBoard(req.responseText);                        
            } 
        }else {
            document.getElementById("loadreqMessage").style.display = 'block';
        // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }; 
    var url = CONTENXT_PATH+"/reqDashBoard.action?requirementstatus="+requirementstatus+"&practice="+practice+"&startDate="+startDate+"&endDate="+endDate+"&relatedTeam="+relatedTeam+"&reqJobTitle="+reqJobTitle+"&dummy="+new Date().getTime();
  // alert(url);
   req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);

    
    
}

function displayReqDashBoard(response)
{
     var oTable = document.getElementById("tblrecUpdate");
    
    ClrTable(oTable);
    
    var dataArray = response;
    
    if(dataArray == " "||dataArray == null )
    {
        alert("No Records Found for this Search");   
    }
    else {
        
       var headerFields = new Array("SNO","AccountName","RequirementName","Status","Duration","Value","CreatedBy","Presales","Recruiter","State","Date&nbsp;Posted");
          ParseAndGenerateHTML(oTable,dataArray, headerFields);
    }    
}
 function generateReqReport(tableBody,record,fieldDelimiter){
    // alert("generateAccountsListByPriority");
    // var empLoginId=""; 
    //var empLoginId = document.getElementById("teamMemberId1").value;
    var row;
    var cell;
    var fieldLength;
    // alert("delimetr-->"+fieldDelimiter);
    var fields = record.split(fieldDelimiter);
    //  alert("fileds-->"+fields)
    fieldLength = fields.length ;
    //   alert("fileds-->"+fieldLength);
    var length = fieldLength;
    
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    // alert(fields);
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



/********************Requirement Summary end ************************/


function getRequirementSetatus(obj){
 //alert("haiii");
    var statusList="";
    var i=0;
    
    $('#reqstatus option:selected').each(function() {
    
    if($(this).val()!='All'){
     if(i==0)
            statusList="'"+$(this).val()+"'";
        else
      statusList=statusList+",'"+$(this).val()+"'";
      i=parseInt(i)+1;
    }
});
    
    
    document.getElementById("reqstatushide").value=statusList;
}


function toogleTeamCheck(obj){
    
    document.getElementById("teamMemberCheck").checked=false;
    if(obj.value=='-1'){
        document.getElementById("checkDiv").style.display='none';
    }else {
        document.getElementById("checkDiv").style.display='block';
    }
}


/*Activity type by BDM 
 * Date : 06/28/2016
 * 
 */
function titleTypeCheck(obj){
    document.getElementById('titleType').value ="";
    document.getElementById('displayTitleType').innerHTML ="";

        if(obj.value=='-1'){
            
            return false;
        }
	
 var url = CONTENXT_PATH+"/doGetTitleType.action?loginId="+obj.value;
  
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
           
                displayTitleType(req.responseText);                        
            } 
        }else {
            
        // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    };
    req.open("GET", url, true);
    req.send(null); 

   
}

function displayTitleType(resTxt){
  //  alert(resTxt);
   
   
    document.getElementById('titleType').value =resTxt;
document.getElementById('displayTitleType').innerHTML = "<font color='green'>"+resTxt+"</font>";

}



function titleTypeCheck1(obj){
    document.getElementById('titleType1').value ="";
    document.getElementById('displayTitleType1').innerHTML ="";
  // alert(obj.value);
   
     

        if(obj.value=='-1'){
            
            return false;
        }
	
//alert("jgbhgv");
    
    
  
 
 var url = CONTENXT_PATH+"/doGetTitleType.action?loginId="+obj.value;
  
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
           
                displayTitleType1(req.responseText);                        
            } 
        }else {
            
        // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    };
    req.open("GET", url, true);
    req.send(null); 

   
}

function displayTitleType1(resTxt){
  //  alert(resTxt);
   
   
    document.getElementById('titleType1').value =resTxt;
document.getElementById('displayTitleType1').innerHTML = "<font color='green'>"+resTxt+"</font>";

}




/********************Monthly Status report Satart ************************/
function getMonthlyStatusReport()
{
    
  
    var tableId = document.getElementById("tblStatusReport");
    ClrTable(tableId);
    var teamMemberId = document.getElementById("teamMemberIdReport").value;
    var year = document.getElementById("year").value;
    var includeTeam=document.getElementById('includeTeam').checked;
    if(year == '') {
        alert("please give year"); 
        return false;
    }
    if(teamMemberId=="-1")
    {
        includeTeam=false;
    }
    if(includeTeam==true)
    {
        includeTeam=1;
    }
    else 
    {
        includeTeam=0;
    }
    //    alert(includeTeam);
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("loadActMessageStatus").style.display = 'none';
                displayMonthlyStatusReport(req.responseText);                        
            } 
        }else {
            document.getElementById("loadActMessageStatus").style.display = 'block';
        // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }; 
    var url = CONTENXT_PATH+"/monthlyStatusReport.action?teamMemberId="+teamMemberId+"&year="+year+"&includeTeamFlag="+includeTeam;
    // alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);

    
    
}

function displayMonthlyStatusReport(response)
{
    var oTable = document.getElementById("tblStatusReport");
    
    ClrTable(oTable);
    
    var dataArray = response;
    
    if(dataArray == " "||dataArray == null )
    {
        alert("No Records Found for this Search");   
    }
    else {
        
        var headerFields = new Array("Monthly Status Report","JAN","FEB","MAR","Q1 Total","APR","MAY","JUN","Q2 Total","JUL","AUG","SEP","Q3 Total","ACT","NOV","DEC","Q4 Total");
        ParseAndGenerateHTML(oTable,dataArray, headerFields);
    }    
}
function generateMonthlyStatusReport(tableBody,record,fieldDelimiter){
    // alert("generateAccountsListByPriority");
    // var empLoginId=""; 
    //var empLoginId = document.getElementById("teamMemberId1").value;
    var row;
    var cell;
    var fieldLength;
    // alert("delimetr-->"+fieldDelimiter);
    var fields = record.split(fieldDelimiter);
    //  alert("fileds-->"+fields)
    fieldLength = fields.length ;
    //   alert("fileds-->"+fieldLength);
    var length = fieldLength;
    
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    // alert(fields);
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
function inclueTeamMembers()
{
    var empLoginId = document.getElementById("teamMemberIdReport").value;
   
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
              
                var response= req.responseText;  
                //  alert(response);
                if(response=="YES")
                {
                    document.getElementById('includeTeam').disabled = false; 
                }
                else
                {
                    document.getElementById('includeTeam').disabled = true;
                }
            } 
        }else {
            document.getElementById('includeTeam').disabled = true; 
        }
    }; 
    var url = CONTENXT_PATH+"/isTeamLead.action?teamMemberId="+empLoginId;
   //  alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}


/********************Monthly Status report end ************************/



