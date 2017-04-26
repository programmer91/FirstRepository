/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function newXMLHttpRequest() {
    
    //alert("In newXMLHttpRequest");
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
    //alert("In readyStateHandler");
    return function() {
        
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("loadActMessagePriority").style.display = 'none';
                responseHandler(req.responseText);
            }
        } else {
            document.getElementById("loadActMessagePriority").style.display = 'block';
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


function ParseAndGenerateHTML(oTable,responseString,headerFields) {
    //alert("In ParseAndGenerateHTML");
    var start = new Date();
    var fieldDelimiter = "|";
    var recordDelimiter = "^";   
    //alert('responseString%%%% ******'+responseString);
    //alert('rowCount%%%% ******'+rowCount);
    // alert("In ParseAndGenerateHTML");
    var records = responseString.split(recordDelimiter); 
    generateTable(oTable,headerFields,records,fieldDelimiter);
}

function generateTable(oTable, headerFields,records,fieldDelimiter) {	
    //alert("In generateTable");
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
            if(oTable.id == "tblActivitiesManagerDetails")   
                {                    
                    generateActiveManagerDetails(oTable,tbody,records[i],fieldDelimiter)
                }
            
//            else if(oTable.id == "tblAccountSummRep") {
//                
//                generateAccountSummRep(oTable,tbody,records[i],fieldDelimiter);
//            }
//            else if(oTable.id == "tblUpdateForAccountsListByPriority")
//            {
//                //alert("tblUpdateForAccountsListByPriority");
//                generateAccountsListByPriority(oTable,tbody,records[i],fieldDelimiter); 
//            //generateAccountsListByPriority1(tbody,records[i],fieldDelimiter); 
//            }
//            else if(oTable.id == "tblUpdate") {
//                generateGreensheetRow(tbody,records[i],fieldDelimiter);  
//            }
            else{
                generateRow(oTable,tbody,records[i],fieldDelimiter);  
            }
        }    
    } else {
        generateNoRecords(tbody,oTable);
    }
    generateFooter(tbody,oTable);
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



function generateActiveManagerDetails(oTable,tableBody,record,delimiter){
    //alert("In generateActiveManagerDetails");
    var SNo = "";
    var AccountName = "";
    var ModifiedBy = "";
    var ModifiedDate = 0;
    //var comments = 0;
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
//    SNo = fields[0];
//    AccountName = fields[1];
//    ModifiedBy = fields[2];
//    ModifiedDate = fields[3];  
//    alert("SNo-->"+SNo+"<--");
//    alert("AccountName-->"+AccountName+"<--");
//    alert("ModifiedBy-->"+ModifiedBy+"<--");
//    alert("ModifiedDate-->"+ModifiedDate+"<--");        
    //Assigning values End
    //alert(length);
    for (var i=0;i<length-1;i++) {
       // cell = document.createElement( "TD" );
       // cell.className="gridColumn";
       if(i==0)
           {
        //alert("In if 1-->"+i+"<--");
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        cell.innerHTML = fields[i];
        cell.setAttribute('align','center');
           }
           
        if(i>0 && i!=5) { 
        //alert("In if 2-->"+i+"<--");
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        cell.innerHTML = fields[i];
         //cell.setAttribute('align','center');
        }
        //else if(i==7){
//        else if(i==5){
//            //empLoginId = fields[i];
//             alert("In if 3-->"+i+"<--");
//            cell = document.createElement( "TD" );
//            cell.className="gridColumn";
//             cell.setAttribute('align','center');
//         
//            //row.appendChild( cell );
//            var j = document.createElement("a");
//            
//            j.setAttribute("href", "javascript:getRecDashBoardComments('"+comments+"')");
//            j.appendChild(document.createTextNode("view"));
//            cell.appendChild(j);
//        }
       // if(fields[i]!=''){
        row.appendChild( cell );
       // }
    }              
        //Feed back Mail End
}


function generateRow(oTable,tableBody,record,delimiter) {
    //alert("In generateRow");
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(delimiter);
    fieldLength = fields.length ;
    var length;    
    //     if(oTable.id == "tblAccountSummRep" || oTable.id == "tblUpdateForAccountsListByPriority"||oTable.id == "tblActivitiesManagerDetails" ){
//        length = fieldLength;
//    }
    
    if(oTable.id == "tblActivitiesManagerDetails" ){
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


function generateNoRecords(tbody,oTable) {
    //alert("In generateNoRecords");
    var noRecords =document.createElement("TR");
    noRecords.className="gridRowEven";
    tbody.appendChild(noRecords);
    cell = document.createElement("TD");
    cell.className="gridColumn";
    
    if(oTable.id == "tblActivitiesManagerDetails"){
        cell.colSpan = "4";
    }
//    else if(oTable.id == "tblOppUpdate"){
//        cell.colSpan = "5";
//    }else if(oTable.id == "tblUpdate"){
//        cell.colSpan = "7";
//    }
//    else if(oTable.id == "tblAccountSummRep"){
//        if(isTeamLead==1|| isUserManager == 1)
//            cell.colSpan = "8";
//        else
//            cell.colSpan = "7";  
//    }
//    else if(oTable.id == "tblUpdateForAccountsListByPriority"){
//        cell.colSpan = "5";
//    }
//     else if(oTable.id == "tblActUpdate"){
//        cell.colSpan = "5";
//    }
    cell.innerHTML = "No Records Found for this Search";
    noRecords.appendChild(cell);
}


function generateFooter(tbody,oTable) {
    //alert("In generateFooter");
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.id="footer"+oTable.id;
    //cell.colSpan = "4";
    if(oTable.id == "tblActivitiesManagerDetails"){
          cell.colSpan = "4";  
     }
//    else if(oTable.id == "tblOppUpdate"){
//        cell.colSpan = "5";
//    }else if(oTable.id == "tblUpdate"){
//        cell.colSpan = "7";
//    }
//    else if(oTable.id == "tblAccountSummRep"){
//        if(isTeamLead==1|| isUserManager == 1)
//            cell.colSpan = "8";
//        else
//            cell.colSpan = "7"; 
//    }
//    else if(oTable.id == "tblUpdateForAccountsListByPriority")
//    {
//        cell.colSpan = "5";   
//    }
//     else if(oTable.id == "tblActUpdate"){
//        cell.colSpan = "5";
//    }
    footer.appendChild(cell);
}



function getActiveManagerDetails() {
    //alert("In getActiveManagerDetails");
    var startdate = document.getElementById("startDate").value;
    var enddate = document.getElementById("endDate").value;
    if(startdate==""||startdate==null||enddate==""||enddate==null)
        {
    validateDates();
    compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value);
    return false;
        }
    var tableId = document.getElementById("tblActivitiesManagerDetails");
    ClrTable(tableId);
    var checkResult = compareDates(startdate,enddate);
    if(!checkResult) {
        return false;
    }      
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, displayActivitiesManagerDetails);  
    //var url = CONTENXT_PATH+"/consultantActivitiesByRep.action?dashBoardStartDateRep="+startdate+"&dashBoardEndDateRep="+enddate+"&createdBy="+createdBy+"&activityType="+activityType;
    var url = CONTENXT_PATH+"/activeManagerDetailsByDates.action?dashBoardStartDateRep="+startdate+"&dashBoardEndDateRep="+enddate;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function displayActivitiesManagerDetails(response) {
    //alert("In displayActivitiesManagerDetails");
    var tableId = document.getElementById("tblActivitiesManagerDetails"); 
    var headerFields = new Array("S.No","Account Name","Modified By","Modified Date");
    var dataArray = response;
    // alert("in displayActivitiesManagerDetails...");
    ParseAndGenerateHTML(tableId,dataArray, headerFields);
    // document.getElementById(("footer"+tableId.id)).innerHTML = "Total Value is($):  "+dataArray[2];
}






