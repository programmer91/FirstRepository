 /* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



function clearTable(tableId) {
    var tbl =  tableId;
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


function getProjectProtfolioReport()
{
    var tableId = document.getElementById("tblProjectProtfolioReport");
    ClrDashBordTable(tableId);
     document.getElementById("button_pageNation").innerHTML="";
    var customerName = document.getElementById("clientId").value;
    var startDate = document.getElementById("postedDate1").value;
    var orderBy = document.getElementById("orderBy").value;
    var status = document.getElementById("status").value;
//     var pgNo=document.getElementById("pgNo").value;
//        var pGflag=element;
//        if(pGflag=='1'){
//            pgNo='1';  
//        }
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("loadReqMessage").style.display = 'none';
                displayProjectProtfolioReport(req.responseText); 
//                pagerOption();
            } 
        }else {
            document.getElementById("loadReqMessage").style.display = 'block';
        }
    }; 
    var url = CONTENXT_PATH+"/getProjectProtfolioReport.action?customerName="+customerName+"&startDate="+startDate+"&orderBy="+orderBy+"&status="+status;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function displayProjectProtfolioReport(response) {
  //  alert(response);
    var oTable = document.getElementById("tblProjectProtfolioReport");
    ClrDashBordTable(oTable);
    
   // var dataArray = response;
     var dataArray = response.split("###");
//     if(flag=='1'){
//      
//        document.getElementById("pgNo").value=1;
//        document.getElementById("totalRecords").value=dataArray[1];
//    }
    if(dataArray == "no data")
    {
        alert("No Records Found for this Search");   
    }
    else {
        var headerFields = new Array("Customer","ProjectName","Cost model","Sector","Practice","Project type","Actual Start Date","Actual End Date","Overall State","Software","Description","Comments");

        ParseAndGenerateHTML(oTable,response, headerFields);
       
    } 
    $("#tblProjectProtfolioReport").tableHeadFixer({
        'left' : 4, 
        'foot' : false, 
        'head' : true
    });
}

function ParseAndGenerateHTML(oTable,responseString,headerFields) {
    var start = new Date();
    var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";   
    var records = responseString.split(recordDelimiter); 
   // alert(records);
    generateTable(oTable,headerFields,records,fieldDelimiter);
}


function generateTable(oTable, headerFields,records,fieldDelimiter) {	

    
    
    //----------
    var tbody;    
      
    if(oTable.id == "tblProjectProtfolioReport") {
        generateTableDynamicHeader(oTable,headerFields);
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
    }else {
        tbody = oTable.childNodes[0];    
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
        generateTableHeader(tbody,headerFields);
    }
//        tbody = oTable.childNodes[0];    
//        tbody = document.createElement("TBODY");
//        oTable.appendChild(tbody);
//        generateTableHeader(tbody,headerFields);
    
    //--------
    //generateTableDynamicHeader
 //   alert(records)
    var rowlength;
    rowlength = records.length;
    if(rowlength >0 && records!=""){
        
         

        if(oTable.id == "tblProjectProtfolioReport") {
            for(var i=0;i<rowlength-1;i++){
                generateProjectProtfolioReport(oTable,tbody,records[i],fieldDelimiter);  
            }
        } 
         
            
    } else {
        generateNoRecords(tbody,oTable);
    }
    generateFooter(tbody,oTable);
}


//function generateTableHeader(tableBody,headerFields) {
//    var row;
//    var cell;
//    row = document.createElement( "TR" );
//    row.className="gridHeader";
//    tableBody.appendChild( row );
//    
//    for (var i=0; i<headerFields.length; i++) {
//        cell = document.createElement( "TD" );
//        cell.className="gridHeader";
//        row.appendChild( cell );
//        cell.innerHTML = headerFields[i];
//        cell.width = 120;
//    }
//}


function generateProjectProtfolioReport(oTable,tableBody,record,fieldDelimiter){
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(fieldDelimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
    row = document.createElement("TR");
    row.className="gridRowEven";
    tableBody.appendChild(row);
    for (var i=1;i<length-1;i++) {            
       var prjId;
      // alert(fields[0]);
       prjId = fields[0];
             if(i==11){
            //alert("Description field")
            cell = document.createElement( "TD" );
    cell.className="gridColumn";       
    cell.innerHTML ="<a href='#' onclick='getProjectDescriptionDetails("+prjId+")'>View</a>";  
    row.appendChild( cell );
        }
        else if(i==12){
           // alert("Comments field")
            cell = document.createElement( "TD" );
    cell.className="gridColumn";       
    cell.innerHTML ="<a href='#' onclick='getProjectCommentDetails("+prjId+")'>View</a>";  
    row.appendChild( cell );
        }
        else{
             cell = document.createElement( "TD" );
        cell.className="gridColumn";       
        cell.innerHTML = fields[i];  
                cell.setAttribute("align","left");
            row.appendChild( cell );
        }
        }
       
    
     
}

function generateNoRecords(tbody,oTable) {
   
    var noRecords =document.createElement("TR");
    noRecords.className="gridRowEven";
    tbody.appendChild(noRecords);
    cell = document.createElement("TD");
    cell.className="gridColumn";
    
    
    
    if(oTable.id == "tblProjectProtfolioReport"){
        cell.colSpan = "12";
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
    if(oTable.id == "tblProjectProtfolioReport"){
        cell.colSpan = "7";
    }
    
    footer.appendChild(cell);
}


function generateTableDynamicHeader(tableBody,headerFields) {
    var row;
    var cell;
    
    var thead=document.createElement("thead");
    tableBody.appendChild(thead);
    row = document.createElement( "TR" );
    row.className="dashBoardgridHeader";
    thead.appendChild(row);
    
    for (var i=0; i<headerFields.length; i++) {
        cell = document.createElement( "TH" );
        cell.className="dashBoardgridHeader";
        row.appendChild( cell );
        cell.innerHTML = headerFields[i];
        cell.width = 120;
    }
}


 function ClrDashBordTable(myHTMLTable) { 
    var tbl =  myHTMLTable;
    
tbl.deleteTHead();
    var lastRow = tbl.rows.length; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}


function getProjectDescriptionDetails(prjid){
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, popupProjectDescriptionDetails);
    var url = CONTENXT_PATH+"/getProjectDescriptionDetails.action?prjId="+prjid;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function popupProjectDescriptionDetails(resText){
 var background = "#3E93D4";
    var title = "Project Description";
    var text1 = resText; 
    var size = text1.length;
    //Now create the HTML code that is required to make the popup
      var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><b>"+title+":</b><br>"+text1+"<br />\
    </body></html>";
     popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
  popup.document.write(content);
 
}



function getProjectCommentDetails(prjid){
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, popupProjectCommentDetails);
    var url = CONTENXT_PATH+"/getProjectCommentDetails.action?prjId="+prjid;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function popupProjectCommentDetails(resText){
 var background = "#3E93D4";
    var title = "Project Comments";
    var text1 = resText; 
    var size = text1.length;
    //Now create the HTML code that is required to make the popup
      var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><b>"+title+":</b><br>"+text1+"<br />\
    </body></html>";
     popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
  popup.document.write(content);
 
}
