/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


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
                document.getElementById("loadOppMessage").style.display = 'none';
                responseHandler(req.responseText);
            }
        } else {
            document.getElementById("loadOppMessage").style.display = 'block';
        //alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }
}
function setDefaultMyOppDates() {
    //alert("ok");
  
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
    
    
    
    document.getElementById('dueStartDate').value = startDate;       
    document.getElementById('dueEndDate').value = endDate;
    //==================================
   // var elementBtn = document.getElementById("dateClearbtn");
//    elementBtn.onclick = new Function("clearDatesOppDash()");
    //elementBtn.value = "clear Dates";
}



function getMyOpportunities() {
   var practiceName=document.getElementById("practice").value;
    document.getElementById("noteLableForOpportunity").style.display='none';
    var tableId = document.getElementById("tblOppUpdate");
    ClrTable(tableId);
   //  document.getElementById("loadOppMessage").style.display='block';
    document.getElementById("totalOppRec").innerHTML = "";
    document.getElementById("totalOppSum").innerHTML = "";
    //document.getElementById("oppDashFooter").innerHTML = "";
    var type = document.getElementById("type").value;
    var state = document.getElementById("state").value;
    var dueStartDate = document.getElementById("dueStartDate").value;
    var dueEndDate = document.getElementById("dueEndDate").value;
    
    
    var checkResult = compareDates(dueStartDate,dueEndDate);
    if(!checkResult) {
        return false;
    }
    document.getElementById("opportunitySearchBUtton").disabled=true;
    //dueStartDate = replaceDateSplits(dueStartDate);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, displayOppDashBoard);
    //alert(CONTENXT_PATH);
  //  var url = CONTENXT_PATH+"/getMyOppDashBoard.action?type="+type+"&state="+state+"&dueStartDate="+dueStartDate+"&dueEndDate="+dueEndDate+"&dummy="+new Date().getTime();
  var url = CONTENXT_PATH+"/getMyOppDashBoard.action?type="+type+"&state="+state+"&dueStartDate="+dueStartDate+"&practiceName="+practiceName+"&dueEndDate="+dueEndDate+"&dummy="+new Date().getTime();
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}



function displayOppDashBoard(response) {
    var tableId = document.getElementById("tblOppUpdate");
   // var createdBy = document.getElementById("createdBy1").value;
    var headerFields='';
  
      //  headerFields = new Array("S.No","Account Name","Opportunity","Practice","State","Value","Created&nbsp;By","Due&nbsp;Date");
      headerFields = new Array("S.No","Account Name","Opportunity","State","Practice","Account&nbsp;State","Value","Created&nbsp;By","Due&nbsp;Date");
 
      //  headerFields = new Array("S.No","Account Name","Opportunity","Value","Due Date");
    
 
    
   
    var dataArray = response.split('addto');
    document.getElementById("totalOppRec").innerHTML = dataArray[1];
   document.getElementById("totalOppSum").innerHTML = "$ "+moneyFormat(dataArray[2]);
    //generateHeader(headerArray,tableId);
    ParseAndGenerateHTML(tableId,dataArray[0], headerFields);
    document.getElementById(("footer"+tableId.id)).innerHTML = "Total Value is($):  "+moneyFormat(dataArray[2]);
     //document.getElementById("loadOppMessage").style.display='none';
      document.getElementById("noteLableForOpportunity").style.display='block';
      document.getElementById("opportunitySearchBUtton").disabled=false;
    
}

function ParseAndGenerateHTML(oTable,responseString,headerFields) {
    
    // alert("ParseAndGenerateHTML");
    var start = new Date();
    var fieldDelimiter = "|";
    var recordDelimiter = "^";   
    //alert('responseString%%%% ******'+responseString);
    //alert('rowCount%%%% ******'+rowCount);
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
        //alert(records);
    if(rowlength >0 && records!=""){
        //alert("rowlength-->^"+records);
          if(oTable.id == "tblOppUpdate") {
               for(var i=0;i<rowlength;i++){
              generateRow(oTable,tbody,records[i],fieldDelimiter);  
          }
          } else  if(oTable.id == "tblOppCustmer") {
               for(var i=0;i<rowlength;i++){
              generateCustRow(oTable,tbody,records[i],fieldDelimiter);  
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
function generateRow(oTable,tableBody,record,delimiter) {
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(delimiter);
    fieldLength = fields.length ;
    var length;
   
        length = fieldLength-1;
   
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
    
}



function generateNoRecords(tbody,oTable) {
   
    var noRecords =document.createElement("TR");
    noRecords.className="gridRowEven";
    tbody.appendChild(noRecords);
    cell = document.createElement("TD");
    cell.className="gridColumn";
    
    if(oTable.id == "tblOppUpdate"){
        cell.colSpan = "9";
    }  else if(oTable.id == "tblOppCustmer"){
        cell.colSpan = "10";
    }
    cell.innerHTML = "No Records Found for this Search";
    noRecords.appendChild(cell);
}


function generateFooter(tbody,oTable) {
    //alert(oTable.id);
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.id="footer"+oTable.id;
    //cell.colSpan = "5";
    if(oTable.id == "tblOppUpdate"){
        cell.colSpan = "9";
    } else if(oTable.id == "tblOppCustmer"){
        cell.colSpan = "10";
    }
    footer.appendChild(cell);
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
function getAccountDetails(accountId){
    window.location="../accounts/getAccount.action?id="+accountId;
}


function getCustOpportunities()
{
    
    
 //   alert("in getCustOpportunities");
    var practiceName=document.getElementById("practice").value;
    document.getElementById("noteLableForCustOpportunity").style.display='none';
    var tableId = document.getElementById("tblOppCustmer");
    ClrTable(tableId);
   //  document.getElementById("loadOppMessage").style.display='block';
    document.getElementById("totalOppRec").innerHTML = "";
    document.getElementById("totalOppSum").innerHTML = "";
    //document.getElementById("oppDashFooter").innerHTML = "";
   // var type ="-1" ;
    var state = document.getElementById("state").value;
    var dueStartDate = document.getElementById("dueStartDate").value;
    var dueEndDate = document.getElementById("dueEndDate").value;
    
    var  sviValue=1;
      if(document.getElementById("svivalue").checked){
         sviValue=1;
          } else {
          sviValue=0; 
        }

    var checkResult = compareDates(dueStartDate,dueEndDate);
    if(!checkResult) {
        return false;
    }
    document.getElementById("opportunitySearchBUtton").disabled=true;
    //dueStartDate = replaceDateSplits(dueStartDate);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, displayCustOppDashBoard);
    //alert(CONTENXT_PATH);
  //  var url = CONTENXT_PATH+"/getMyOppDashBoard.action?type="+type+"&state="+state+"&dueStartDate="+dueStartDate+"&dueEndDate="+dueEndDate+"&dummy="+new Date().getTime();
  var url = CONTENXT_PATH+"/getCustOppDashBoard.action?state="+state+"&dueStartDate="+dueStartDate+"&practiceName="+practiceName+"&dueEndDate="+dueEndDate+"&dummy="+new Date().getTime() +"&sviValue="+sviValue;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null); 
    
}

function displayCustOppDashBoard(response)
{
   // alert(response);
    var tableId = document.getElementById("tblOppCustmer");
   // var createdBy = document.getElementById("createdBy1").value;
    var headerFields='';
  
      //  headerFields = new Array("S.No","Account Name","Opportunity","Practice","State","Value","Created&nbsp;By","Due&nbsp;Date");
      headerFields = new Array("S.No","Account Name","Opportunity","State","Practice","Account&nbsp;State","Value","Created&nbsp;By","Due&nbsp;Date","Type");
 
      //  headerFields = new Array("S.No","Account Name","Opportunity","Value","Due Date");
    
 
    
   
    var dataArray = response.split('addto');
     //alert(dataArray[0]);
     // alert(dataArray[1]);
      // alert(dataArray[2]);
    document.getElementById("totalOppRec").innerHTML = dataArray[1];
   document.getElementById("totalOppSum").innerHTML = "$ "+moneyFormat(dataArray[2]);
    //generateHeader(headerArray,tableId);
    ParseAndGenerateHTML(tableId,dataArray[0], headerFields);
    document.getElementById(("footer"+tableId.id)).innerHTML = "Total Value is($):  "+moneyFormat(dataArray[2]);
     //document.getElementById("loadOppMessage").style.display='none';
      document.getElementById("noteLableForCustOpportunity").style.display='block';
      document.getElementById("opportunitySearchBUtton").disabled=false; 
    
}
function generateCustRow(oTable,tableBody,record,delimiter)
{
   var row;
    var cell;
    var fieldLength;
    var fields = record.split(delimiter);
    fieldLength = fields.length ;
    var length;
   
        length = fieldLength-1;
   
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    
    if(oTable.id=="tblOppCustmer"){
        //  alert(length);
        for (var i=1;i<length;i++) {
            //  alert(i+" "+fields[i]);
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
          
           
      
                //alert(fields[i]+"fields[i]");
                cell.innerHTML = fields[i];
        
        
         if(i==7){
            cell.style="text-align:right;width:auto;";
            cell.innerHTML="$&nbsp;"+moneyFormat(fields[i]);
        }
            
            if(fields[i]!=''){
                row.appendChild( cell );
            }
        }
    }
      
}


