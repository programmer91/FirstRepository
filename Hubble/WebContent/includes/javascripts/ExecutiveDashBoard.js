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
    
    if(oTable.id=="tblRequirement" || oTable.id=="tblEmployeeCount" || oTable.id=="tblGreenSheets" || oTable.id=="tblOpertunitiesCount"   || oTable.id=="tblOpertunities"  ||  oTable.id=="tblLostClosedOpertunities"|| oTable.id=="tblEmployeeTypeDetails" || oTable.id=="tblNewsLetters" || oTable.id=="tblNewsLettersImages"){
    
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
           if(oTable.id == "tblRecDashBoardSummRep")
                {
                  generateRecDashBoardActivities(oTable,tbody,records[i],fieldDelimiter)
                }
            else if(oTable.id=="tblRequirement" || oTable.id=="tblEmployeeCount"  || oTable.id=="tblGreenSheets" || oTable.id=="tblOpertunitiesCount" || oTable.id == "tblEmployeeTypeDetails" ){
                generateRow(oTable,tbody,records[i],fieldDelimiter);  
            } else if(oTable.id == "tblOpertunities")
            {
                generateOpertunities(oTable,tbody,records[i],fieldDelimiter)
            }else if(oTable.id == "tblLostClosedOpertunities")
            {
                generateLostClosedOpertunities(oTable,tbody,records[i],fieldDelimiter)
            }else if(oTable.id == "tblNewsLetters" )
            {
                generateNewsLettersData(oTable,tbody,records[i],fieldDelimiter)
            }else if( oTable.id=="tblNewsLettersImages")
            {
                generateNewsLettersImagesData(oTable,tbody,records[i],fieldDelimiter)
            }

            
        }    
    }    
    else {
        generateNoRecords(tbody,oTable);
    }
    
    generateFooter(tbody,oTable);
}

function generateNoRecords(tbody,oTable) {
    var noRecords =document.createElement("TR");
    noRecords.className="gridRowEven";
    tbody.appendChild(noRecords);
    cell = document.createElement("TD");
    cell.className="gridColumn";
    
     if(oTable.id == "tblRecDashBoardSummRep"){
        cell.colSpan = "6";
    }else if(oTable.id == "tblActUpdate"){
        cell.colSpan = "5";
    }else if(oTable.id == "tblOppUpdate"){
        cell.colSpan = "5";
    }else if(oTable.id == "tblRequirementInfo"){
        cell.colSpan = "2";
    }
    else if(oTable.id == "tblToatlProfiles"){
       
            cell.colSpan = "6";  
    }
    else if(oTable.id == "tblInactiveProfiles"||oTable.id == "tblProfilesSubmitted" || oTable.id ==  "tblRequirementsClosed"){
        cell.colSpan = "3";
    }
    else if(oTable.id ==  "tblRequirementsStatus"){
        cell.colSpan = "3";
    }
    else if(oTable.id ==  "tblRequirement"){
        cell.colSpan = "9";
    }
    else if(oTable.id=="tblEmployeeCount"){
        cell.colSpan = "10";
    }
     else if(oTable.id=="tblOpertunities"){
        cell.colSpan = "8";
    }
     else if(oTable.id=="tblGreenSheets"){
        cell.colSpan = "5";
    }
     else if(oTable.id=="tblOpertunitiesCount"){
        cell.colSpan = "5";
    }else if(oTable.id=="tblLostClosedOpertunities"){
        cell.colSpan = "8";
    }else if(  oTable.id == "tblEmployeeTypeDetails" || oTable.id=="tblNewsLetters" || oTable.id=="tblNewsLettersImages")
    {
        cell.colSpan = "2";   
    } else if(  oTable.id == "tblBdmActivitySummaryByLoginId")
    {
        cell.colSpan = "11";   
    }
     
    
    cell.innerHTML = "No Records Found for this Search";
    noRecords.appendChild(cell);
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

function generateFooter(tbody,oTable) {
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.id="footer"+oTable.id;
    
    
     if(oTable.id == "tblEmployeeCount" || oTable.id ==  "tblGreenSheets"){
        cell.colSpan = "10";
    }
    else if(oTable.id == "tblRequirement"){
        cell.colSpan = "9";
    }else if(oTable.id == "tblOpertunities"  || oTable.id == "tblLostClosedOpertunities"){
        cell.colSpan = "8";
    }
    else if(  oTable.id == "tblOpertunitiesCount")
    {
        cell.colSpan = "3";   
    }else if(  oTable.id == "tblEmployeeTypeDetails" || oTable.id=="tblNewsLetters" || oTable.id=="tblNewsLettersImages")
    {
        cell.colSpan = "2";   
    } else if(  oTable.id == "tblBdmActivitySummaryByLoginId")
    {
        cell.colSpan = "11";   
    }else{
        cell.colSpan = "25";   
    }
    footer.appendChild(cell);
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
     
     
     
         
         
     if(oTable.id == "tblEmployeeCount"){
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        cell.innerHTML = fields[0];
        row.appendChild( cell );
        
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        cell.innerHTML = "<a href='javascript:getEmployeeTypeDetails(\""+fields[0]+"\",\""+fields[1]+"\")'>"+fields[1]+"</a>";
        row.appendChild( cell );
        
        for (var i=2;i<length;i++) {
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            cell.innerHTML = fields[i];
            if(fields[i]!=''){
                row.appendChild( cell );
            }
       
        }   
    }else{
     
        for (var i=0;i<length;i++) {
       
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
            cell.innerHTML = fields[i];
            if(fields[i]!=''){
                row.appendChild( cell );
            }
       
        }
    }
}


function getEmpolyeeCount() {
     ClrTable(document.getElementById("tblEmployeeCount"));
      ClrTable(document.getElementById("tblRequirement"));
       ClrTable(document.getElementById("tblOpertunities"));
       ClrTable(document.getElementById("tblGreenSheets"));
       ClrTable(document.getElementById("tblOpertunitiesCount"));
       document.getElementById("loadEmployeeCount").style.display = 'block';
      var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateEmpolyeeCount);
    var url = CONTENXT_PATH+"/getEmpolyeeCount.action";
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
   
}


function populateEmpolyeeCount(response) {
    var tableId = document.getElementById("tblEmployeeCount");
    
  document.getElementById("loadEmployeeCount").style.display = 'none';
 
   var headerFields = new Array("Country","Total","Operations","Recruitment","Sales",
   "Marketing",
   "SSG",
   "GDC",
   "Executive&nbsp;Board",
   "Billing");  
 
   
    var dataArray = response;    
   
    ParseAndGenerateHTML(tableId,dataArray, headerFields);   

    getRequirementCount();
}

function getRequirementCount() {
     ClrTable(document.getElementById("tblRequirement"));
    var pastMonths = document.getElementById("pastMonths").value;
     document.getElementById("loadRequirment").style.display = 'block';
      var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateRequirementCount);
    var url = CONTENXT_PATH+"/getRequirementCount.action?pastMonths="+pastMonths;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
   
}


function populateRequirementCount(response) {
    var tableId = document.getElementById("tblRequirement");
     document.getElementById("loadRequirment").style.display = 'none';
   var headerFields = new Array("Country","Total","Opened","InProgress","Forecast",
   "lost",
   "Withdrawn",
   "Hold",
   "Won");  
 
   
    var dataArray = response;    
   
    ParseAndGenerateHTML(tableId,dataArray, headerFields);   
   
getGreenSheetCount(1);
    
}
function getGreenSheetCount(val) {
     ClrTable(document.getElementById("tblGreenSheets"));
    var pastMonths = document.getElementById("pastMonths").value;
    var external = document.getElementById("external").value;
     document.getElementById("loadGreenSheetsCount").style.display = 'block';
      document.getElementById("opertunitiesClear").value=val;
      var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateGreenSheetCount);
    var url = CONTENXT_PATH+"/getGreenSheetCount.action?pastMonths="+pastMonths+"&external="+external;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
   
}


function populateGreenSheetCount(response) {
    var tableId = document.getElementById("tblGreenSheets");
     document.getElementById("loadGreenSheetsCount").style.display = 'none';
      var external = document.getElementById("external").value;
      var headerFields;
      if(external=="true"){
    headerFields = new Array("Country","Total&nbsp;Placements","Client&nbsp;Billing","Vendor&nbsp;Billing","Total&nbsp;Cost(Profit)");
      }
      else{
            headerFields = new Array("Country","Total&nbsp;Placements","Client&nbsp;Billing");
      }
    var dataArray = response.split('addto');
    ParseAndGenerateHTML(tableId,dataArray[0], headerFields);
    var total=0;
    if(dataArray[1]!= undefined){
        total=dataArray[1];
    }
    
      if(external=="true"){
    document.getElementById(("footer"+tableId.id)).innerHTML = "Total Cost(CLient Billing-Vendor Billing):  "+total+"$"; 
      }
      else{
           document.getElementById(("footer"+tableId.id)).innerHTML = "Total Cost:  "+total+"$"; 
      }
    var val=document.getElementById("opertunitiesClear").value
    if(val==1){

getOpportunitiesCounts();
    }
    
}
function getOpportunitiesCounts() {
    ClrTable(document.getElementById("tblOpertunitiesCount"));
    var pastMonths = document.getElementById("pastMonths").value;
     document.getElementById("loadOpertunitiesCounts").style.display = 'block';
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateOpportunitiesCounts);
      var url = CONTENXT_PATH+"/getOpportunitiesCounts.action?pastMonths="+pastMonths;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function populateOpportunitiesCounts(response) {
    var tableId = document.getElementById("tblOpertunitiesCount");
    var headerFields = new Array("Region","Total&nbsp;Opportunities","Value");
    var dataArray = response.split('addto');
    ParseAndGenerateHTML(tableId,dataArray[0], headerFields);
     var total=0;
     if(dataArray[1]!= undefined){
        total=dataArray[1];
    }
    document.getElementById(("footer"+tableId.id)).innerHTML = "Total Value is($):  "+total;
  //  document.getElementById(("footer"+tableId.id)).innerHTML = "Total Value is($):  "+dataArray[1];
     document.getElementById("loadOpertunitiesCounts").style.display = 'none';
    getOpportunitiesCount(1);
}
 function getOpportunitiesCount(val) {
    ClrTable(document.getElementById("tblOpertunities"));
    var pastMonths = document.getElementById("pastMonths").value;
    var opportunityState = document.getElementById("opportunityState").value;
    
     document.getElementById("loadOpertunitiesCount").style.display = 'block';
      document.getElementById("opertunitiesStateClear").value=val;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateOpportunitiesCount);
      var url = CONTENXT_PATH+"/getOpportunitiesCount.action?pastMonths="+pastMonths+"&opportunityState="+opportunityState;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateOpportunitiesCount(response) {
     var opportunityState = document.getElementById("opportunityState").value;
    
       document.getElementById('statusLabel').innerHTML=opportunityState;  
     
    var tableId = document.getElementById("tblOpertunities");
    var headerFields = new Array("S.No","Account&nbsp;Name","Opportunity","Value","Region","Due Date","Practice","Status");
    var dataArray = response.split('addto');
    ParseAndGenerateHTML(tableId,dataArray[0], headerFields);
    var total=0;
    if(dataArray[1]!= undefined){
        total=dataArray[1];
    }
   // alert("total....in opportunity...."+total);
    document.getElementById(("footer"+tableId.id)).innerHTML = "Total Value is($):  "+total;

   // document.getElementById(("footer"+tableId.id)).innerHTML = "Total Value is($):  "+dataArray[2];
     document.getElementById("loadOpertunitiesCount").style.display = 'none';
    
     var val=document.getElementById("opertunitiesStateClear").value
    if(val==1){
 getLostClosedOpportunitiesDetailsCount();
    }
   
}
function getLostClosedOpportunitiesDetailsCount(){
   ClrTable(document.getElementById("tblLostClosedOpertunities"));
    var pastMonths = document.getElementById("pastMonths").value;
     document.getElementById("loadLostClosedOpertunitiesCount").style.display = 'block';
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateLostClosedOpportunities);
      var url = CONTENXT_PATH+"/getLostClosedOpportunities.action?pastMonths="+pastMonths;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null); 
}

function populateLostClosedOpportunities(response){
   var tableId = document.getElementById("tblLostClosedOpertunities");
    var headerFields = new Array("S.No","Account&nbsp;Name","Opportunity","Value","Region","Due Date","Practice","Status");
    var dataArray = response.split('addto');
    ParseAndGenerateHTML(tableId,dataArray[0], headerFields);
    var total=0;
    if(dataArray[1]!= undefined){
        total=dataArray[1];
    }
    document.getElementById(("footer"+tableId.id)).innerHTML = "Total Value is($):  "+total;

   // document.getElementById(("footer"+tableId.id)).innerHTML = "Total Value is($):  "+dataArray[2];
     document.getElementById("loadLostClosedOpertunitiesCount").style.display = 'none';
      
}



/*Leela Opprtunity popup changes
 * Date : 02/04/2016
 * 
 */
function generateOpertunities(oTable,tableBody,record,delimiter)
{
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(delimiter);
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
            cell.innerHTML = "<a href='javascript:getOpertunityDetails("+fields[0]+")'>"+fields[i]+"</a>";
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
function getOpertunityDetails(Id){
  
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateOpertunityDetaails);
    var url = CONTENXT_PATH+"/popupOpertunitiesWindow.action?empId="+Id;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencodetd");
    req.send(null);
}

function populateOpertunityDetaails(text) {
   // alert(text);
    var background = "#3E93D4";
    var title = "Opportunity Details";
    var text1=text;
    var size = text1.length;

    var strArray = text.split("#^$");
    var size = strArray.length;
    // alert("size "+content.length);
    
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'>"+text1+"<br />\
    </body></html>";
    //alert(content);
    //    //alert("text1"+text1);
    //    // alert("size "+content.length);
    //    var indexof=(content.indexOf("|")+1);
    //    var lastindexof=(content.lastIndexOf("|"));
    var indexof=(content.indexOf("#^$")+1);
    var lastindexof=(content.lastIndexOf("#^$"));
    popup = window.open("","window","channelmode=0,width=500,height=500,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
  
    if(content.indexOf("#^$")){
        popup.document.write("<b>Opertunity Title : </b>"+content.substr(0,content.indexOf("#^$")));
        popup.document.write("<br><br>");
        popup.document.write("<b>State : </b>"+strArray[1]);
        popup.document.write("<br><br>");
        popup.document.write("<b>Practice : </b>"+strArray[2]);
        popup.document.write("<br><br>");
        popup.document.write("<b>Type : </b>"+strArray[3]);
        popup.document.write("<br><br>");
        popup.document.write("<b>Stage : </b>"+strArray[4]);
        popup.document.write("<br><br>");
        popup.document.write("<b>Inside Sales Lead : </b>"+strArray[5]);
        popup.document.write("<br><br>");
        popup.document.write("<b>Regional Manager : </b>"+strArray[6]);
        popup.document.write("<br><br>");
        popup.document.write("<b>Practice Manager : </b>"+strArray[7]);
        popup.document.write("<br><br>");
        popup.document.write("<b>Due Date : </b>"+strArray[8]);
        popup.document.write("<br><br>");
        popup.document.write("<b>Lead Source  : </b>"+strArray[9]);
        
        popup.document.write("<br><br>");
        popup.document.write("<b>Description :</b>"+strArray[10]);
        
    }
//Write content into it.  
//Write content into it.    
    
    
    
}

function generateLostClosedOpertunities(oTable,tableBody,record,delimiter)
{
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(delimiter);
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
            cell.innerHTML = "<a href='javascript:getOpertunityDetails("+fields[0]+")'>"+fields[i]+"</a>";
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
function readyStateHandlerExecutiveDashBoard(req,responseTextHandler,total) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadActMessageAS")).style.display = "none";
                responseTextHandler(req.responseText,total);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
            (document.getElementById("loadActMessageAS")).style.display = "block";
        }
    }
}
	
function getEmployeeTypeDetails(country,total){
    var req = newXMLHttpRequest();
   
    req.onreadystatechange = readyStateHandlerExecutiveDashBoard(req, displayEmployeeTypeDetails,total);
    
    var url = CONTENXT_PATH+"/getEmployeeTypeDetails.action?country="+country;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function displayEmployeeTypeDetails(response,total) {
  
    var tableId = document.getElementById("tblEmployeeTypeDetails");
    ClrTable(tableId);
    var headerFields = new Array("Employee Type","Total");
   
    // alert("responseArray[1]-->"+responseArray[1]);
    //document.getElementById("totalRequirementsFound").innerHTML = responseArray[0];
    var dataArray = response;
    
    //  generateTableHeader(tableId,headerFields)
    ParseAndGenerateHTML(tableId,dataArray, headerFields);
 
 
    document.getElementById("headerLabel1").style.color="white";
    document.getElementById("headerLabel1").innerHTML="Employee Type Details";
        //document.getElementById("total").innerHTML=total;
    var overlay = document.getElementById('overlayExecutiveDashBoard');
    var specialBox = document.getElementById('specialBoxExecutiveDashBoard');
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
    var overlay = document.getElementById('overlayExecutiveDashBoard');
    var specialBox = document.getElementById('specialBoxExecutiveDashBoard');

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






/*  Newsletters Deployment Automation Start*/

function clearTable(tableId) {
    var tbl =  tableId;
    var lastRow = tbl.rows.length; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}
function getAllFilesInDirectory(){
     initSessionTimer();
     var tableId = document.getElementById("tblNewsLetters");
     clearTable(tableId);
    var year=document.getElementById('year').value;
    var month=document.getElementById('month').value;
    var type=document.getElementById('type').value;
    var category=document.getElementById('category').value;
    
    document.getElementById('yearSearch').value=year;
     document.getElementById('monthSearch').value=month;
      document.getElementById('typeSearch').value=type;
       document.getElementById('categorySearch').value=category;
    
      var req = newXMLHttpRequest();
     (document.getElementById("loadActMessageAS")).style.display = "block";
    req.onreadystatechange = readyStateHandler(req, displayAllFilesInDirectory);
    
    var url = CONTENXT_PATH+"/getAllFilesInDirectory.action?year="+year+"&month="+month+"&type="+type+"&category="+category;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function displayAllFilesInDirectory(resText){
   var tableId = document.getElementById("tblNewsLetters");
    clearTable(tableId);
     document.getElementById("loadActMessageAS").style.display = 'none';
   var headerFields = new Array("FileName","Link");  
 
   
    var dataArray = resText;    
    ParseAndGenerateHTML(tableId,dataArray, headerFields);  
}


  function generateNewsLettersData(oTable,tableBody,record,delimiter){
       var row;
    var cell;
 /*   var fieldLength;
    var fields = record.split(delimiter);
    fieldLength = fields.length ;
    var length;
        length = fieldLength; */
 
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
          
    
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        cell.innerHTML = record;
        row.appendChild( cell );
        
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        cell.innerHTML = "<a href='javascript:getFile(\""+record+"\")'>ClickToView</a>";
        row.appendChild( cell );
        
   }
   
   function getFile(fileName){
     var url=document.getElementById('urlNewsletters').value
       var year=document.getElementById('yearSearch').value;
    var month=document.getElementById('monthSearch').value;
    var monthName;
   if(month==0)
        monthName="January";
   else if(month==1)
        monthName="February";
   else if(month==2)
        monthName="March";
   else if(month==3)
        monthName="April";
    else if(month==4)
        monthName="May";
    else if(month==5)
        monthName="June";
    else if(month==6)
        monthName="July";
    else if(month==7)
        monthName="August";
    else if(month==8)
        monthName="September";
    else if(month==9)
        monthName="October";
    else if(month==10)
        monthName="November";
    else if(month==11)
        monthName="December";
    
    var type=document.getElementById('typeSearch').value;
    var category=document.getElementById('categorySearch').value;
    var fullPath=year + '/' + monthName + '/' + type + '/' + category + '/' + fileName;
    
    window.open(url+fullPath);  
   }
   function getNewsLettersOverlay(){
    initSessionTimer();
       document.getElementById('resultMessage').innerHTML='';
       document.getElementById('file').value='';
       document.getElementById("headerLabel1").style.color="white";
            document.getElementById("headerLabel1").innerHTML="Add NewsLetters ";
            
            var overlay = document.getElementById('overlay');
            var specialBox = document.getElementById('specialBox');
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
   
   
   
 function  toggleCloseUploadOverlay(){
      var overlay = document.getElementById('overlay');
            var specialBox = document.getElementById('specialBox');
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
 
 
 
function fileValidationNewsLetters() {
      document.getElementById('resultMessage').innerHTML = '';
    var imagePath =document.getElementById('file').value;
    
        if(isChar(imagePath) )
            {
            
   // if(imagePath.length<30){
        
        if(imagePath != null && (imagePath !="")) {
                    

            var extension = imagePath.substring(imagePath.lastIndexOf('.')+1);

            if(extension=="html"){
                // document.imageForm.imagePath.focus();
                var size = document.getElementById('file').files[0].size;
                //alert("size-->"+size);
                // alert("size in mb-->"+(parseInt(size)/1000000));
                //if((parseInt(size)/1000000)<2) {
               if((parseInt(size)/1000000)<2) {
                    return (true);
                }else {
                    document.getElementById('file').value = "";
                    // alert("File size must be less than 1 MB.");
                    document.getElementById('resultMessage').innerHTML = "<font color=red>File size must be less than 2 MB.</font>"
                    return (false);
                }
            }else {
                document.getElementById('file').value = "";
                document.getElementById('resultMessage').innerHTML = "<font color=red>Invalid file extension!Please select html file.</font>"
                // alert("Invalid file extension!Please select pdf or jpg or png file.");
                return (false);
            }
        }
    
            }else{
              
                 document.getElementById('resultMessage').innerHTML = "<font color=red>File name accepts only Alphabets,numbers and underscore!</font>"
        document.getElementById('file').value = "";
            }
    return (false);
};

 


function isChar(str) {
    var myFileName = str.substr(0,str.lastIndexOf("."));
  return /^[-_0-9a-zA-Z()]+$/.test(myFileName);
}


function doAddNewsLetters(){
        initSessionTimer();
     var year=document.getElementById('yearOverlay').value;
    var month=document.getElementById('monthOverlay').value;
    var type=document.getElementById('typeOverlay').value;
    var category=document.getElementById('categoryOverlay').value;
     var file = document.getElementById('file').value;
     
   if(file == ''){
  displaymessage = "<font color=red>Please upload file.</font>";
      document.getElementById('resultMessage').innerHTML = displaymessage;
   }else{
     $.ajaxFileUpload({
        url:'doAddNewsLetter.action?year='+year+'&month='+month+'&type='+type+'&category='+category,//
        secureuri:false,//false
        fileElementId:'file',//id  <input type="file" id="file" name="file" />
        dataType: 'json',// json
        success: function(data,status){
            
            var displaymessage = "<font color=red>Please try again later</font>";
          
            if(data.indexOf("uploaded")>0){
               
                displaymessage = "<font color=green>NewsLetter uploaded Successfully.</font>";
            }
            if(data.indexOf("Notvalid")>0){
                
                displaymessage = "<font color=red>Not a valid file!,Please check the file and try again.</font>";
            }
            if(data.indexOf("Error")>0){
               
                displaymessage = "<font color=red>Internal Error!, Please try again later.</font>"
            }
           
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = displaymessage;//"<font color=green>File uploaded successfully</font>";
        
        },
        error: function(e){
            
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
       
        }
    });
}
}




function getAllFilesInImagesDirectory(){
            initSessionTimer();
     var tableId = document.getElementById("tblNewsLettersImages");
     clearTable(tableId);
    var year=document.getElementById('yearImages').value;
    var month=document.getElementById('monthImages').value;
    
    document.getElementById('yearSearch').value=year;
     document.getElementById('monthSearch').value=month;
    
      var req = newXMLHttpRequest();
     (document.getElementById("loadActMessageAS1")).style.display = "block";
    req.onreadystatechange = readyStateHandler(req, displayAllFilesInImagesDirectory);
    
    var url = CONTENXT_PATH+"/getAllFilesInImagesDirectory.action?year="+year+"&month="+month;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function displayAllFilesInImagesDirectory(resText){
   var tableId = document.getElementById("tblNewsLettersImages");
    clearTable(tableId);
     document.getElementById("loadActMessageAS1").style.display = 'none';
   var headerFields = new Array("FileName","Link");  
 
   
    var dataArray = resText;    
    ParseAndGenerateHTML(tableId,dataArray, headerFields);  
}

 function getNewsLettersImagesOverlay(){
  initSessionTimer();
      document.getElementById('resultMessage1').innerHTML='';
       document.getElementById('file1').value='';
       document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Add Images ";
            
            var overlay = document.getElementById('overlayImages');
            var specialBox = document.getElementById('specialBoxImages');
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
   
function  toggleCloseUploadOverlay1(){
      var overlay = document.getElementById('overlayImages');
            var specialBox = document.getElementById('specialBoxImages');
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



function fileValidationNewsLettersImages() {
      document.getElementById('resultMessage1').innerHTML = '';
    var imagePath =document.getElementById('file1').value;
    
    
        if(isChar(imagePath) )
            {
            
   // if(imagePath.length<30){
        
        if(imagePath != null && (imagePath !="")) {
                    

            var extension = imagePath.substring(imagePath.lastIndexOf('.')+1);

           if(extension=="JPG" || extension=="jpg" || extension=="png" || extension=="jpeg" || extension=="JPEG" || extension=="PNG" || extension=="bmp" || extension=="BMP"){
                // document.imageForm.imagePath.focus();
                var size = document.getElementById('file1').files[0].size;
              //  alert("size-->"+size);
              //   alert("size in mb-->"+(parseInt(size)/1000000));
                //if((parseInt(size)/1000000)<2) {
                if((parseInt(size)/1000000)<2) {
                    return (true);
                }else {
                    document.getElementById('file1').value = "";
                    // alert("File size must be less than 1 MB.");
                    document.getElementById('resultMessage1').innerHTML = "<font color=red>File size must be less than 2 MB.</font>"
                    return (false);
                }
            }else {
                document.getElementById('file1').value = "";
                 document.getElementById('resultMessage1').innerHTML = "<font color=red>Invalid file extension!Please select pdf or jpg or jpeg or png file.</font>"
                // alert("Invalid file extension!Please select pdf or jpg or png file.");
                return (false);
            }
        }
   
            }else{
               
                 document.getElementById('resultMessage1').innerHTML = "<font color=red>File name accepts only Alphabets,numbers and underscore!</font>"
        document.getElementById('file1').value = "";
            }
    return (false);
};

function doAddNewsLettersImages(){
            initSessionTimer();
     var year=document.getElementById('yearImagesOverlay').value;
    var month=document.getElementById('monthImagesOverlay').value;
   var file = document.getElementById('file1').value;
   if(file==''){
   displaymessage = "<font color=red>Please upload file.</font>";
      document.getElementById('resultMessage1').innerHTML = displaymessage;
   }else{
     $.ajaxFileUpload({
        url:'doAddNewsLetterImages.action?year='+year+'&month='+month,//
        secureuri:false,//false
        fileElementId:'file1',//id  <input type="file" id="file" name="file" />
        dataType: 'json',// json
        success: function(data,status){
            
            var displaymessage = "<font color=red>Please try again later</font>";
          
            if(data.indexOf("uploaded")>0){
               
                displaymessage = "<font color=green>NewsLetter uploaded Successfully.</font>";
            }
            if(data.indexOf("exists")>0){
               
                displaymessage = "<font color=red>file name already existed , please change it.</font>"
            }
            if(data.indexOf("Notvalid")>0){
                
                displaymessage = "<font color=red>Not a valid file!,Please check the file and try again.</font>";
            }
            if(data.indexOf("Error")>0){
               
                displaymessage = "<font color=red>Internal Error!, Please try again later.</font>"
            }
           
            document.getElementById("load1").style.display = 'none';
            document.getElementById('resultMessage1').innerHTML = displaymessage;//"<font color=green>File uploaded successfully</font>";
        
        },
        error: function(e){
            
            document.getElementById("load1").style.display = 'none';
            document.getElementById('resultMessage1').innerHTML = "<font color=red>Please try again later</font>";
       
        }
    });
   }
}




  function generateNewsLettersImagesData(oTable,tableBody,record,delimiter){
       var row;
    var cell;
   
 
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
          
    
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        cell.innerHTML = record;
        row.appendChild( cell );
        
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        cell.innerHTML = "<a href='javascript:getFileImages(\""+record+"\")'>ClickToView</a>";
        row.appendChild( cell );
        
   }
   
   function getFileImages(fileName,url){
     
       var url=document.getElementById('urlImages').value
       var year=document.getElementById('yearSearch').value;
    var month=document.getElementById('monthSearch').value;
    var monthName;
   if(month==0)
        monthName="January";
   else if(month==1)
        monthName="February";
   else if(month==2)
        monthName="March";
   else if(month==3)
        monthName="April";
    else if(month==4 && year==2016)
        monthName="may";
     else if(month==4 && year!=2016)
        monthName="May";
   else if(month==5 && year==2016)
        monthName="june";
     else if(month==5 && year!=2016)
        monthName="June";
    else if(month==6)
        monthName="July";
    else if(month==7)
        monthName="August";
    else if(month==8)
        monthName="September";
    else if(month==9)
        monthName="October";
    else if(month==10 && year==2016)
        monthName="november";
     else if(month==10 && year!=2016)
        monthName="November";
  else if(month==11 && year==2016)
        monthName="december";
     else if(month==11 && year!=2016)
        monthName="December";
  
  
   
      var fullPath=year + '/' + monthName  + '/' + fileName;
    
    window.open(url+fullPath);  
   }
   
   /*  Newsletters Deployment Automation End*/
   
   
   
   /* BDM Statistics code start 12/21/2016*/
   
function readyStateHandlerBDMStatistics(req,responseTextHandler) {
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

function getBDMStatistics(){
  
   var tableId = document.getElementById("tblBdmActivitySummaryByLoginId");
     ClrTable(tableId);

    document.getElementById('resultDisplay').style.display='none';
    document.getElementById("bdmDashBoardActivitygraph").style.display="none";
    
    var req = newXMLHttpRequest();
    var bdmId=document.getElementById('bdmId').value;
    var startDate=document.getElementById('startDateSummaryGraph').value;
    var endDate=document.getElementById('endDateSummaryGraph').value;
    
    document.getElementById('loadActMessageASh').style.display='block';
    req.onreadystatechange = readyStateHandlerBDMStatistics(req, displayBDMStatistics);
    
    var url = CONTENXT_PATH+"/getBDMStatistics.action?bdmId="+bdmId+"&startDate="+startDate+"&endDate="+endDate;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function displayBDMStatistics(response){
    
    document.getElementById('loadActMessageASh').style.display='none';
    if(response=='NoData'){
    document.getElementById("resultDisplay").style.display="block";
        document.getElementById("resultDisplay").innerHTML="No data available";   
    }else{
        document.getElementById("resultDisplay").style.display="none";  
  document.getElementById("bdmDashBoardActivitygraph").style.display="block"; 
   var result = response.split("*@!");
    var arraydata = [['Activity Name', 'Activity Count']];
    
    for(var i=0; i<result.length-1; i++){
        
            var res = result[i].split("#^$");
            var dArray = [res[0],parseInt(res[1])];
           
            arraydata.push(dArray);
        }
        
       var data = google.visualization.arrayToDataTable(arraydata);

        var options = {
            title: 'BDM Statistics Graph' ,
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
             
        var selectedItem = chart.getSelection()[0];
        if (selectedItem) {
            var bdmId=document.getElementById('bdmId').value;
   
            var activityType = data.getValue(selectedItem.row, 0);
           getBdmStatisticsDetailsByLoginId(activityType,bdmId);
        }
    }
    google.visualization.events.addListener(chart, 'select', selectHandler);   
        chart.draw(data, options,dArray);
   
 //   document.getElementById("bdmStatisticsgraphSearch").disabled=false;    
    }
 }
 
 function  getBdmStatisticsDetailsByLoginId(activityType,bdmId){
  var tableId = document.getElementById("tblBdmActivitySummaryByLoginId");
    ClrTable(tableId);
      var startDate=document.getElementById('startDateSummaryGraph').value;
    var endDate=document.getElementById('endDateSummaryGraph').value;
     document.getElementById('loadActMessageASh').style.display='block';
   
   var url = CONTENXT_PATH+"/getBdmStatisticsDetailsByLoginId.action?startDate="+startDate+"&endDate="+endDate+"&activityType="+activityType+"&bdmId="+bdmId;
  
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("loadActMessageASh").style.display = 'none';
                displayBdmStatisticsDetailsByLoginId(req.responseText,activityType);                        
            } 
        }else {
            document.getElementById("loadActMessageASh").style.display = 'block';
        }
    };
    req.open("GET", url, true);
    req.send(null); 
 }
 
 
 function displayBdmStatisticsDetailsByLoginId(response,activityType){
  var tableId = document.getElementById("tblBdmActivitySummaryByLoginId");  
   if(activityType=='Conference Call'){
   var headerFields = new Array("S.No","AccountName","ActivityType","STATUS","Priority","CreatedBy","CreatedDate","Contacts","Description");
   }else{
       var headerFields = new Array("S.No","ReqId","AccountName","JobTitle","STATUS","Location","AssignedDate","NoOfPos's","OtherDetails");
   }
    var dataArray = response;
    bdmStatisticsParseAndGenerateHTML(tableId,dataArray, headerFields,activityType);
 }
 
 function bdmStatisticsParseAndGenerateHTML(oTable,responseString,headerFields,activityType) {
   
    var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";   
    var records = responseString.split(recordDelimiter); 
    generateBdmActivitySummaryRows(oTable,headerFields,records,fieldDelimiter,activityType);
}

function generateBdmActivitySummaryRows(oTable, headerFields,records,fieldDelimiter,activityType) {	

  var tbody = oTable.childNodes[0];    
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    generateTableHeader(tbody,headerFields);
    var rowlength;
        rowlength = records.length;
     if(rowlength >0 && records!=""){
        for(var i=0;i<rowlength-1;i++) {
          
                
                generateBdmActivitySummaryByLoginId(oTable,tbody,records[i],fieldDelimiter,activityType);
        }
        
    } else {
        generateNoRecords(tbody,oTable);
    }
    generateFooter(tbody,oTable);
}


		
function generateBdmActivitySummaryByLoginId(oTable,tableBody,record,delimiter,activityType){
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
    
    
    
     if(activityType=='Conference Call'){
        for (var i=0;i<length;i++) {
            if(i==7){
               cell = document.createElement( "TD" );
            cell.className="gridColumn"; 
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:getActivityContacts('"+fields[7]+"')");
            j.appendChild(document.createTextNode('view'));
            cell.appendChild(j);    
           }else  if(i==8){
               cell = document.createElement( "TD" );
            cell.className="gridColumn"; 
             var j = document.createElement("a");
            j.setAttribute("href", "javascript:populateActivityDescription('"+fields[8]+"')");
            j.appendChild(document.createTextNode('view'));
            cell.appendChild(j);    
           } else{
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
     }else{
        for (var i=0;i<length-1;i++) {  
             if (i==2){
             cell = document.createElement( "TD" );
            cell.className="gridColumn";    
             var accName="-";
            
            if(fields[2]!="null"){
                accName=fields[2];
            }
            cell.innerHTML = "<a href='javascript:AccountTeamDetailsPopupForBdm("+fields[8]+");'>"+accName+"</a>" ;
            cell.setAttribute("align","left");   
            row.appendChild( cell );   
            }else if(i==3){
                
            cell = document.createElement( "TD" );
            cell.className="gridColumn"; 
            var jobTitle = fields[3].substring(0,25);
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:getRequirementSkillsForBdm('"+fields[1]+"')");
            j.setAttribute("onmouseover","javascript:tooltip.show('"+fields[3]+"')");
            j.setAttribute("onmouseout","javascript:tooltip.hide();");
            j.appendChild(document.createTextNode(jobTitle+"..."));      
            cell.appendChild(j);
            row.appendChild( cell );
        } else{
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
        
          cell = document.createElement( "TD" );
    cell.className="gridColumn";       
    cell.innerHTML ="<a href='#' onclick='getRequirementOtherDetailsForBdm("+fields[1]+")'>View</a>";  
     cell.setAttribute("align","center");  
    row.appendChild( cell );
     }
    
    
      
}

function getActivityContacts(activityId) {
    
      var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerBDMStatistics(req,populateActivityContacts);    
    var url=CONTENXT_PATH+"/getActivityContacts.action?activityId="+activityId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

 function populateActivityContacts(resText) {
              var background = "#3E93D4";
    var title = "Contacts :";
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
        
        
        
        
 function populateActivityDescription(resText) {
              var background = "#3E93D4";
    var title = "Description :";
      
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
        
        
        
      
function AccountTeamDetailsPopupForBdm(accId) {
     
     var url = "../crm/accounts/AccDetailsPopup.jsp?accountId="+accId;
    //newwindow=window.open(url,'name','height=350,width=200,top=200,left=250');
    var    newwindow=window.open(url,'name','height=350,width=300,top=200,left=250');
    if (window.focus) {
        newwindow.focus()
        }
}



	
function getRequirementSkillsForBdm(RequirementId) {
    var aId = RequirementId;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerBDMStatistics(req,populateRequirementSkillsForBdm);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/popupReqSkillsWindow.action?requirementId="+aId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateRequirementSkillsForBdm(text) {

     var background = "#3E93D4";
    var title = "Skillset / Description";
    var text1 = text; 
    var size = text1.length;

    var text2 = text.split("^");
    
    var n=text2[1];

    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'>"+text1+"<br />\
    </body></html>";

    if(size < 50){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    

        if(content.indexOf("^")){
             popup.document.write("<b>Skills     : </b>"+content.substr(0,content.indexOf("^")));

            if(n!="null"){
                popup.document.write("<br><br>");
                popup.document.write("<b>Decription :</b>"+n);
            }

            popup.document.write("<br><br>");
            popup.document.write("<b>Created By  :</b>"+content.substr((content.lastIndexOf("^")+1),size));

        }//Write content into it. 



    }
    
    else if(size < 100){
        
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        if(content.indexOf("^")){
           
            popup.document.write("<b>Skills     : </b>"+content.substr(0,content.indexOf("^")));
           
            if(n!="null"){
                popup.document.write("<br><br>");
                popup.document.write("<b>Decription :</b>"+n);
            }

            popup.document.write("<br><br>");
            popup.document.write("<b>Created By  :</b>"+content.substr((content.lastIndexOf("^")+1),size));


       

        }//Write content into it.  
    //New 

       
    }
    
    else if(size < 260){
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
     
        if(content.indexOf("^")){
           

            popup.document.write("<b>Skills     : </b>"+content.substr(0,content.indexOf("^")));
            
            if(n!="null"){
                popup.document.write("<br><br>");
                popup.document.write("<b>Decription :</b>"+n);
            }

            popup.document.write("<br><br>");
            popup.document.write("<b>Created By n :</b>"+content.substr((content.lastIndexOf("^")+1),size));

        }

    }
    
    else{
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=600,height=400,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
       
        if(content.indexOf("^")){
           
            popup.document.write("<b>Skills     : </b>"+content.substr(0,content.indexOf("^")));
            
            if(n!="null"){
                popup.document.write("<br><br>");
                popup.document.write("<b>Decription :</b>"+n);
            }

            popup.document.write("<br><br>");
            popup.document.write("<b>Created By  :</b>"+content.substr((content.lastIndexOf("^")+1),size));

        }

    }
}





function getRequirementOtherDetailsForBdm(reqid){
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerBDMStatistics(req, popupRequirementOtherDetailsForBdm);
    var url = CONTENXT_PATH+"/getRequirementOtherDetails.action?requirementId="+reqid;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function popupRequirementOtherDetailsForBdm(resText){
    var background = "#3E93D4";
    var title = "Other Details";
    var reqDetails=resText;
    var data="<br><b>No.Of Positions</b>:"+reqDetails.split("@")[1];
    data=data+"<br><b>No.Of Resumes Submitted</b>:"+reqDetails.split("@")[2];
    data=data+"<br><b>SubmittedDate</b>:"+reqDetails.split("@")[3];
    data=data+"<br><b>AssignedDate</b>:"+reqDetails.split("@")[0];
     data=data+"<br><b>SalesPerson</b>:"+reqDetails.split("@")[4];
     data=data+"<br><b>Recruiter</b>:"+reqDetails.split("@")[5];
	 
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><b>Other Details:</b><br>"+data+"<br />\
    </body></html>";
    
    popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
  
 
    popup.document.write(content.substr(0,content.length));
}


   /* BDM Statistics code end 12/21/2016*/
   
   
   
function getCustomerContacts(){
  
   var tableId = document.getElementById("tblCustomerContacts");
     ClrTable(tableId);
var teamMemberCheck=0;
 if(document.getElementById("teamMemberCheck").checked){
      teamMemberCheck=1;
 }
    
    var req = newXMLHttpRequest();
    var customerName=document.getElementById('customerName').value;
    var teamMemberId=document.getElementById('teamMemberId').value;
    var startDateContacts=document.getElementById('startDateContacts').value;
    var endDateContacts=document.getElementById('endDateContacts').value;
    var titleType=document.getElementById('titleType').value;
    
    document.getElementById('customerContactsloadMsg').style.display='block';
    req.onreadystatechange = readyStateHandlerCustomerContacts(req, displayCustomerContacts);
    
    var url = CONTENXT_PATH+"/getCustomerContacts.action?customerName="+customerName+"&teamMemberId="+teamMemberId+"&startDateContacts="+startDateContacts+"&endDateContacts="+endDateContacts+"&teamMemberCheck="+teamMemberCheck+"&titleType="+titleType;
   //alert(url);
   req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function readyStateHandlerCustomerContacts(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("customerContactsloadMsg")).style.display = "none";
                responseTextHandler(req.responseText);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
            (document.getElementById("customerContactsloadMsg")).style.display = "block";
        }
    }
}


function displayCustomerContacts(response){
    
 //   alert(response);
   
    var tableId = document.getElementById("tblCustomerContacts");  
 
       var headerFields = new Array("S.No","AccountName","Touched&nbsp;Contacts","Total&nbsp;Contacts");
   
    var dataArray = response;
    CustomerContactsParseAndGenerateHTML(tableId,dataArray, headerFields);
    
}


 function CustomerContactsParseAndGenerateHTML(oTable,responseString,headerFields) {
   
    var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";   
    var records = responseString.split(recordDelimiter); 
    generateCustomerContactsRows(oTable,headerFields,records,fieldDelimiter);
}

function generateCustomerContactsRows(oTable, headerFields,records,fieldDelimiter) {	

  var tbody = oTable.childNodes[0];    
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    generateTableHeader(tbody,headerFields);
    var rowlength;
        rowlength = records.length;
     if(rowlength >0 && records!=""){
        for(var i=0;i<rowlength-1;i++) {
          
                
                generateCustomerContacts(oTable,i,tbody,records[i],fieldDelimiter);
        }
        
    } else {
        generateNoRecords(tbody,oTable);
    }
    generateFooter(tbody,oTable);
}


	
function generateCustomerContacts(oTable,index,tableBody,record,delimiter){
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
    
    
    cell = document.createElement( "TD" );
              cell.className="gridColumn";       
       
                  cell.setAttribute("align","left");
            
                cell.innerHTML = (index+1); 
    row.appendChild( cell );
   
               cell = document.createElement( "TD" );
              cell.className="gridColumn";       
       
                  cell.setAttribute("align","left");
            
                cell.innerHTML = fields[0]; 
    row.appendChild( cell );
       
         if(parseInt(fields[1],10)!=0){
             
      // alert(fields[1]);
      cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    var j = document.createElement("a");
    j.setAttribute("href", "javascript:getContactActivities('"+fields[3]+"')");
    j.innerHTML = fields[1] ;
         
    document.create
    cell.appendChild(j);
    
    row.appendChild(cell);
    cell.setAttribute("width","15%");
    cell.setAttribute('align','left');
         }
         else{
               cell = document.createElement( "TD" );
              cell.className="gridColumn";       
       
                  cell.setAttribute("align","left");
            
                cell.innerHTML = fields[1]; 
    row.appendChild( cell );
    
         }
     cell = document.createElement( "TD" );
              cell.className="gridColumn";       
       
                  cell.setAttribute("align","left");
            
                cell.innerHTML = fields[2]; 
    row.appendChild( cell );
    
      
}





function getContactActivities(customerId){
    
          
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Contact Activities";
    
      
    var teamMemberId=document.getElementById('teamMemberId').value;
    var startDateContacts=document.getElementById('startDateContacts').value;
    var endDateContacts=document.getElementById('endDateContacts').value;
     var titleType=document.getElementById('titleType').value;
    var overlay = document.getElementById('overlayContactsActivites');
    var specialBox = document.getElementById('specialBoxContactsActivites');
          
          
          var teamMemberCheck=0;
 if(document.getElementById("teamMemberCheck").checked){
      teamMemberCheck=1;
 }
          
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
               
    //window.location = 'reviewSearch.action?startDate='+startDate+'&endDate='+endDate+'&reviewType='+reviewType+'&reviewStatus='+reviewStatus+'&empnameById='+empnameById;
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
        //        alert(element);
        
        var oTable = document.getElementById("ContactActivitiesTbl");
       
        ClrTable(oTable);
         document.getElementById('resMessage').innerHTML ="";
        (document.getElementById("loadingMsgContactActivities")).style.display = "block";
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerContactActivities(req,displayContactActivities); 
                    
        var url = CONTENXT_PATH+"/getContactActivities.action?customerId="+customerId+"&teamMemberId="+teamMemberId+"&startDateContacts="+startDateContacts+"&endDateContacts="+endDateContacts+"&teamMemberCheck="+teamMemberCheck+"&titleType="+titleType;
 
        req.open("GET",url,"true");    
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
        
        
    }
}


function readyStateHandlerContactActivities(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadingMsgContactActivities")).style.display = "none";
               
                responseTextHandler(req.responseText);
               
            }
            else {
                
                (document.getElementById("loadingMsgContactActivities")).style.display = "none";
             
                alert("HTTP error ---"+req.status+" : "+req.statusText);
                 
            }
        }else {
            (document.getElementById("loadingMsgContactActivities")).style.display = "block";
        }
    }
}

function displayContactActivities(response){
   
    

  var oTable = document.getElementById("ContactActivitiesTbl");
var headerFields = new Array("S.No","CreatedBy","ActivityType","STATUS","Priority","CreatedDate","ContactName's","Description");
  var dataArray = response;
        ContactActivitiesParseAndGenerateHTML(oTable,dataArray, headerFields);
  
}
    

 function ContactActivitiesParseAndGenerateHTML(oTable,responseString,headerFields) {
   
    var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";   
    var records = responseString.split(recordDelimiter); 
    generateContactActivitiesRows(oTable,headerFields,records,fieldDelimiter);
}


function generateContactActivitiesRows(oTable, headerFields,records,fieldDelimiter) {	

  var tbody = oTable.childNodes[0];    
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    generateTableHeader(tbody,headerFields);
    var rowlength;
        rowlength = records.length;
     if(rowlength >0 && records!=""){
        for(var i=0;i<rowlength-1;i++) {
          
                
                generateContactActivities(oTable,i,tbody,records[i],fieldDelimiter);
        }
        
    } else {
        generateNoRecords(tbody,oTable);
    }
    generateFooter(tbody,oTable);
}


function generateContactActivities(oTable,index,tableBody,record,delimiter){
    
    var row;
    var cell;
    var fieldLength;
 
    var fields = record.split("#^$");
    fieldLength = fields.length ;
    var length = fieldLength;
    
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    // alert(fields);
    
    cell = document.createElement( "TD" );
              cell.className="gridColumn";       
       
                  cell.setAttribute("align","left");
            
                cell.innerHTML = (index+1); 
    row.appendChild( cell );
    
    
    
    for (var i=0;i<length;i++) {  
        if(parseInt(i,10)<(parseInt(length,10)-2)){
              cell = document.createElement( "TD" );
        cell.className="gridColumn";       
       
            cell.setAttribute("align","center");
            
            cell.innerHTML = fields[i];   
        }else if(parseInt(i,10)<(parseInt(length,10)-1)){
              cell = document.createElement( "TD" );
        cell.className="gridColumn";       
      
       if(fields[i]!=''){
      
         var j = document.createElement("a");
            j.setAttribute("href", "#");
            j.setAttribute("onmouseover", "javascript:Tip('"+fields[i]+"')");
            j.setAttribute("onmouseout", "javascript:UnTip()");
            j.appendChild(document.createTextNode($(fields[i]).text().substring(0,15)+".."));
            cell.appendChild(j);
       }
        else{
            alert("else"+fields[i]);
           cell.innerHTML = "-"; 
             cell.setAttribute("align","center"); 
            row.appendChild( cell );
        }
       
        }else {
             cell = document.createElement( "TD" );
            cell.className="gridColumn";
            //row.appendChild( cell );
          
           if(fields[i]!=''){
             //  alert(fields[i]);
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:getDecriptionByActivityId('"+fields[i]+"')");
            j.appendChild(document.createTextNode("Click Here"));
            cell.appendChild(j);}
        else{
            alert("else"+fields[i]);
           cell.innerHTML = "-"; 
             cell.setAttribute("align","center"); 
            row.appendChild( cell );
        }
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




function populateDecriptionByActivityId(text) {
   
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



function titleTypeCheck(obj){
    document.getElementById('titleType').value ="";
    document.getElementById('displayTitleType').innerHTML ="";

        if(obj.value=='All'){
            
            return false;
        }
	//alert(obj.value);
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
   // alert(resTxt);
   
  
    document.getElementById('titleType').value =resTxt;
document.getElementById('displayTitleType').innerHTML = "<font color='green'>"+resTxt+"</font>";
 
}

function showTeamCheck(obj){

    document.getElementById("teamMemberCheck").checked=false;
    if(obj.value=='All'){
        document.getElementById("checkDiv").style.display='none';
    }else {
        document.getElementById("checkDiv").style.display='block';
    }
}


//activityId
function getDecriptionByActivityId(activityId){
//     var url = CONTENXT_PATH+"/popupWindow.action?activityId="+activityId;
 var url = CONTENXT_PATH+"/getActivityDecriptionById.action?activityId="+activityId;
 
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {    
               
               // document.getElementById("loadActMessageASh").style.display = 'none';
                populateDecriptionByActivityId(req.responseText);                        
            } 
        }else {
           // document.getElementById("loadActMessageASh").style.display = 'block';
        // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    };
    req.open("GET", url, true);
    req.send(null); 
}
