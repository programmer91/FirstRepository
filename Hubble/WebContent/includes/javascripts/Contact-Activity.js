 /*Don't Alter these Methods*/
var contactId;
var accId;
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

function getContactaddContactList()
{
   // alert("Contact details Activity");
      var accId=document.getElementById("accId").value;
    //alert("id::"+accId);
     var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler1(req); 
    var url = CONTENXT_PATH+"/getcontactList.action?conAccId="+accId+"&dummy="+new Date().getTime();
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function readyStateHandler1(req,responseHandler) {
    return function() {
        var  myHTMLTable = document.getElementById("tblUpdate");
        ClrTable(myHTMLTable);
        if (req.readyState == 4) {
            if (req.status == 200) {
               // document.getElementById("loadMessage").style.display = 'none';
                var headerFields0 = new Array("Sno","Sal","FirstName","LastName","AliasName","Title","Office Phone","Email");	
                var getResponseData;
                getResponseData = req.responseText;
                // alert('getResponseData******'+getResponseData);
                var temp = new Array();
                temp = getResponseData.split('addto');
                if(req.responseText!=''){
                     // document.forMycop.inputRowData.style.display="block";
                   //document.forMycop.inputRowData.value= "Records Found "+temp[1];
                   // alert(temp[0]);
                    ParseAndGenerateHTML(myHTMLTable,temp[0], headerFields0,temp[1]);   //this function implementation in VenusReportAjax.js
                    
                    // myHeadTable.innerHTML = "Total Rows"+temp[1];
                }else{
                    alert('No Result For This Search...');
                    spnFast.innerHTML="No Result For This Search...";                
                }
                //document.getElementById("loadMessage2").style.display = 'none';
                
            } else {
                //alert("HTTP error ---"+req.status+" : "+req.statusText);
                alert("please wait");
                
                 //document.getElementById("loadMessage").style.display = 'block';
                //document.getElementById("loadMessage2").style.display = 'block';
            }
        }
        else { //document.getElementById("loadMessage").style.display = 'block';
	 }
    }
}
//for contacts
function ClrTable(myHTMLTable) { 
    var tbl =  myHTMLTable;
    var lastRow = tbl.rows.length; 
    //document.getElementById('addlabel1').style.display = 'none'; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}

//for contacts
function ParseAndGenerateHTML(oTable,responseString,headerFields0,rowCount) {
    var start = new Date();
//    var fieldDelimiter = "|";
//    var recordDelimiter = "^";
var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";
    
    // alert('responseString*******'+responseString);
    var records = responseString.split(recordDelimiter);
    //	Generate the HTML Code for the Table Creation using HeaderFields & Records
    generateTable(oTable,headerFields0,records,fieldDelimiter);
}

//for contacts
function generateTable(oTable, headerFields0,records,fieldDelimiter) {	    
    
    var tbody = oTable.childNodes[0];
    // oTable.outerHTML = str;
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    if(records.length >=1 && records!=""){
        generateTableHeader(tbody,headerFields0);
    }
    
    //alert('records.length>> '+records.length);
    
    for(var i=0;i<records.length-1;i++) {
        generateRow(tbody,records[i],fieldDelimiter);               
    }
    //oTable.row[0].column[0].onclick=getData();
    
   var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.colSpan = "9";
    footer.appendChild(cell); 
  // generateFooter(tbody); 
    
}  

function generateTableHeader(tableBody,headerFields0) {
    var row;
    var cell;
    
    row = document.createElement( "TR" );
    row.className="gridHeader";
    tableBody.appendChild( row );
    
    for (var i=0; i<headerFields0.length; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridHeader";
        row.appendChild( cell );
        cell.innerHTML = headerFields0[i];
        cell.width = 100;
    }
    //generateFooter(tableBody);
}

function generateRow(tableBody,record,delimiter) {
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    
    var fields = record.split(delimiter);   
    for (var i=0;i<fields.length-2;i++) {
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        row.appendChild( cell );
        
        
        if(i==1){         
            //alert('fields[2]----'+fields[2]);setAttribute("href", "javascript:set_userToList('"+userId +"','"+ userName +"')");
            var j = document.createElement("a");
            j.appendChild(document.createTextNode(fields[i]));
            cell.appendChild(j);
            
        }
        
     /*   else if(i==2){            
            
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:getContactData('"+fields[8]+"')");
            j.appendChild(document.createTextNode(fields[i]));
            cell.appendChild(j);
        }
        else if(i==3){
            var j = document.createElement("a");
           j.setAttribute("href", "javascript:getContactData('"+fields[8]+"')");
            j.appendChild(document.createTextNode(fields[i]));
            cell.appendChild(j);
            
        }*/
         else if(i==2){            
            
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:getContactData('"+fields[8]+"','"+fields[11]+"')");
            j.appendChild(document.createTextNode(fields[i]));
            cell.appendChild(j);
        }
        else if(i==3){
            var j = document.createElement("a");
           j.setAttribute("href", "javascript:getContactData('"+fields[8]+"','"+fields[11]+"')");
            j.appendChild(document.createTextNode(fields[i]));
            cell.appendChild(j);
            
        }
        else if(i==4){
            var j = document.createElement("a");
            
            j.appendChild(document.createTextNode(fields[i]));
            cell.appendChild(j);
            
        }
         else if(i==5){
            var j = document.createElement("a");
            
            j.appendChild(document.createTextNode(fields[i]));
            cell.appendChild(j);
            
        }
        else if(i==6){
            var j = document.createElement("a");
            j.appendChild(document.createTextNode(fields[i]));
          //  j.setAttribute("href", "mailto:'"+fields[i]+"'");
            cell.appendChild(j);
        }
        else if(i==7){
            var j = document.createElement("a");
            
            j.appendChild(document.createTextNode(fields[i]));
            //j.setAttribute("href", "mailto:'"+fields[i]+"'");
            cell.appendChild(j);
           
            
        }
        /* else if(i==8){
            var j = document.createElement("a");
            
            j.appendChild(document.createTextNode("create"));
            j.setAttribute("href", "javascript:doEdit('"+fields[8]+"','"+fields[9]+"')");
            cell.appendChild(j);
            cell.align = "center";
            
        }*/
        else if(i==0){
            cell.innerHTML = fields[i];        
        }
        
        
    }
    
}

//For Activities
function getContactActivityList() {
    //alert("contact listActivity");
    var contactId=document.getElementById("curContactId").value;
    var accId=document.getElementById("accId").value;
    //alert(contactId+accId);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerActivities(req); 
    var url = CONTENXT_PATH+"/getContactActivityList.action?curcontactId="+contactId+"&actAccId="+accId+"&dummy="+new Date().getTime();
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}
//for activities
function readyStateHandlerActivities(req,responseHandler) {
    return function() {
        var  myHTMLTable1 = document.getElementById("tblUpdate11");
        ClrTable1(myHTMLTable1);
        if (req.readyState == 4) {
            if (req.status == 200) {
               // document.getElementById("loadMessage").style.display = 'none';
                
               // var headerFields1 = new Array("SNo","DateCreated","Activity Type","Description","AssignedTo","DueDate");	
               var headerFields1 = new Array("SNo","DateCreated","Activity Type","Description","Created&nbsp;By");	

                var getResponseData;
                getResponseData = req.responseText;
                // alert('getResponseData******'+getResponseData);
                var temp = new Array();
                temp = getResponseData.split('addto');
                if(req.responseText!=''){
                     // document.forMycop.inputRowData.style.display="block";
                   //document.forMycop.inputRowData.value= "Records Found "+temp[1];
                   // alert(temp[0]);
                    ParseAndGenerateHTML1(myHTMLTable1,temp[0], headerFields1,temp[1]);   //this function implementation in VenusReportAjax.js
                    
                    // myHeadTable.innerHTML = "Total Rows"+temp[1];
                }else{
                    alert('No Result For This Search...');
                    spnFast.innerHTML="No Result For This Search...";                
                }
                //document.getElementById("loadMessage2").style.display = 'none';
                
            } else {
                //alert("HTTP error ---"+req.status+" : "+req.statusText);
                alert("please wait");
                
                 //document.getElementById("loadMessage").style.display = 'block';
                //document.getElementById("loadMessage2").style.display = 'block';
            }
        }
        else { //document.getElementById("loadMessage").style.display = 'block';
	 }
    }
}

//for activities
function ClrTable1(myHTMLTable1) { 
    var tbl =  myHTMLTable1;
    var lastRow = tbl.rows.length; 
    //document.getElementById('addlabel1').style.display = 'none'; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}

//for activities

function ParseAndGenerateHTML1(oTable,responseString,headerFields1,rowCount) {
    var start = new Date();
//    var fieldDelimiter = "|";
//    var recordDelimiter = "^";
var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";
    
    // alert('responseString*******'+responseString);
    var records = responseString.split(recordDelimiter);
    //	Generate the HTML Code for the Table Creation using HeaderFields & Records
    generateTable1(oTable,headerFields1,records,fieldDelimiter);
}

//for activities

function generateTable1(oTable, headerFields1,records,fieldDelimiter) {	    
    
    var tbody = oTable.childNodes[0];
    // oTable.outerHTML = str;
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    if(records.length >=1 && records!=""){
        generateTableHeader1(tbody,headerFields1);
    }
    
    //alert('records.length>> '+records.length);
    
    for(var i=0;i<records.length-1;i++) {
        generateRow1(tbody,records[i],fieldDelimiter);               
    }
    //oTable.row[0].column[0].onclick=getData();
    
   var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.colSpan = "9";
    footer.appendChild(cell); 
  // generateFooter(tbody); 
    
} 

//for activities
function generateTableHeader1(tableBody,headerFields1) {
    var row;
    var cell;
    
    row = document.createElement( "TR" );
    row.className="gridHeader";
    tableBody.appendChild( row );
    
    for (var i=0; i<headerFields1.length; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridHeader";
        row.appendChild( cell );
        cell.innerHTML = headerFields1[i];
        cell.width = 100;
    }
    //generateFooter(tableBody);
}


//for Activities

function generateRow1(tableBody,record,delimiter) {
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    
    var fields = record.split(delimiter);   
    for (var i=0;i<fields.length-2;i++) {
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        row.appendChild( cell );
        //alert(fields[1]+'----'+fields[2]+'--'+fields[3]+'--'+fields[4]);
        
        if(i==1){         
            
            var j = document.createElement("a");
            
            j.appendChild(document.createTextNode(fields[i].substring(0,10)));
            cell.appendChild(j);
            
        }
        
        else if(i==2){            
            
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:getActivityData('"+fields[6]+"')");
            j.appendChild(document.createTextNode(fields[i]));
            cell.appendChild(j);
        }
        else if(i==3){
          
            var j = document.createElement("a");
            
            j.appendChild(document.createTextNode("Click To View"));
            j.setAttribute("href", "javascript:doGetComments('"+fields[6]+"')");
            cell.appendChild(j);
            //cell.align = "center";

        }
        /*else if(i==4){
      
         var j = document.createElement("a");
            
            j.appendChild(document.createTextNode("Click To View"));
             j.setAttribute("href", "javascript:doGetComments('"+fields[8]+"')");
            cell.appendChild(j);
            cell.align = "center";
            
        }*/
         else if(i==4){
            var j = document.createElement("a");
            
            j.appendChild(document.createTextNode(fields[i+1]));
            cell.appendChild(j);
            
        }
        /*else if(i==6){
            var j = document.createElement("a");
            j.appendChild(document.createTextNode(fields[i]));
          //  j.setAttribute("href", "mailto:'"+fields[i]+"'");
            cell.appendChild(j);
        }*/
        else if(i==5){
            var j = document.createElement("a");
            
            j.appendChild(document.createTextNode(fields[i+2].substring(0,10)));
            //j.setAttribute("href", "mailto:'"+fields[i]+"'");
            cell.appendChild(j);
           
            
        }
         /*else if(i==8){
            var j = document.createElement("a");
            
            j.appendChild(document.createTextNode("create"));
            j.setAttribute("href", "javascript:doEdit('"+fields[8]+"','"+fields[9]+"')");
            cell.appendChild(j);
            cell.align = "center";
            
        }*/
        else if(i==0){
            cell.innerHTML = fields[i];        
        }
        
        
    }
    
}

// NEw function for addActivityList genaration

function getAddActivityList() {
   // alert("add listActivity");
   document.getElementById("campaignId").disabled = true;
    
    var accId=document.getElementById("accId").value;
    //alert("contactid"+contactId+"-- accid--"+accId);
    var req = newXMLHttpRequest();

    req.onreadystatechange = readyStateHandlerAddActivities(req); 
  
    var url = CONTENXT_PATH+"/getAddActivityList.action?actAccId="+accId+"&dummy="+new Date().getTime();
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}


//For Contact id==0

 function readyStateHandlerAddActivities(req,responseHandler) {
    //alert("ready0");
    return function() {
        var  myHTMLTable1 = document.getElementById("tblUpdate2");
        ClrTableAdd(myHTMLTable1);
        if (req.readyState == 4) {
            if (req.status == 200) {
               // document.getElementById("loadMessage").style.display = 'none';
                
              //  var headerFields1 = new Array("SNo","DateCreated","Activity Type","Description","AssignedTo","Status","DueDate");	
              //var headerFields1 = new Array("SNo","DateCreated","Activity Type","Description","Status","CreatedBy");	
              var headerFields1 = new Array("SNo","DateCreated","ActivityType","Description","Status","CreatedBy","Opportunity");	
                var getResponseData;
                getResponseData = req.responseText;
                // alert('getResponseData******'+getResponseData);
                var temp = new Array();
                temp = getResponseData.split('addto');
                if(req.responseText!=''){
                     // document.forMycop.inputRowData.style.display="block";
                   //document.forMycop.inputRowData.value= "Records Found "+temp[1];
                   // alert(temp[0]);
                    ParseAndGenerateHTML0(myHTMLTable1,temp[0], headerFields1,temp[1]);   //this function implementation in VenusReportAjax.js
                    
                    // myHeadTable.innerHTML = "Total Rows"+temp[1];
                }else{
                    alert('No Result For This Search...');
                    spnFast.innerHTML="No Result For This Search...";                
                }
                //document.getElementById("loadMessage2").style.display = 'none';
                
            } else {
                //alert("HTTP error ---"+req.status+" : "+req.statusText);
                alert("please wait");
                
                 //document.getElementById("loadMessage").style.display = 'block';
                //document.getElementById("loadMessage2").style.display = 'block';
            }
        }
        else { //document.getElementById("loadMessage").style.display = 'block';
	 }
    }
}


function ClrTableAdd(myHTMLTable1) { 
    var tbl =  myHTMLTable1;
    var lastRow = tbl.rows.length; 
    //document.getElementById('addlabel1').style.display = 'none'; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}
//-----------------------------------------------------------------------------------------------
function ParseAndGenerateHTML0(oTable,responseString,headerFields1,rowCount) {
   // alert("parse0");
    var start = new Date();
//    var fieldDelimiter = "|";
//    var recordDelimiter = "^";
var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";
    //alert("parse0");
    // alert('responseString*******'+responseString);
    var records = responseString.split(recordDelimiter);
    //	Generate the HTML Code for the Table Creation using HeaderFields & Records
    generateTable0(oTable,headerFields1,records,fieldDelimiter);
}

function generateTable0(oTable, headerFields1,records,fieldDelimiter) {	    
    //alert("genTable0");
    var tbody = oTable.childNodes[0];
    // oTable.outerHTML = str;
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    if(records.length >=1 && records!=""){
        generateTableHeader1(tbody,headerFields1);
    }
    
    //alert('records.length>> '+records.length);
    
    for(var i=0;i<records.length-1;i++) {
        generateRowADD1(tbody,records[i],fieldDelimiter);               
    }
    //oTable.row[0].column[0].onclick=getData();
    
   var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.colSpan = "9";
    footer.appendChild(cell); 
  // generateFooter(tbody); 
    
} 

function generateRowADD1(tableBody,record,delimiter) {
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
  // alert("generaterowADD0");
    var fields = record.split(delimiter);   
    for (var i=0;i<fields.length-2;i++) {
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        row.appendChild( cell );
        //alert(fields[1]+'----'+fields[2]+'--'+fields[3]+'--'+fields[4]);
        
        if(i==1){         
            
            var j = document.createElement("a");
            
            j.appendChild(document.createTextNode(fields[3].substring(0,10)));
              // j.appendChild(document.createTextNode(fields[3]));
            cell.appendChild(j);
            
        }
        
        else if(i==2){            
            
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:getActivityData('"+fields[1]+"')");
            j.appendChild(document.createTextNode(fields[i]));
            cell.appendChild(j);
        }
        else if(i==3){
            var j = document.createElement("a");
             j.appendChild(document.createTextNode("Click To View"));
             j.setAttribute("href", "javascript:doGetComments('"+fields[1]+"')");
            // j.appendChild(document.createTextNode(fields[6]));
             cell.appendChild(j);  
        }
        else if(i==4){
            
            var j = document.createElement("a");
             
            j.appendChild(document.createTextNode(fields[5]));
            cell.appendChild(j);
        }
        else if(i==5){
            var j = document.createElement("a");
            j.appendChild(document.createTextNode(fields[6]));
            cell.appendChild(j);
        }
         else if(i==6){
             var j = document.createElement("a");
            // alert(fields[7]);
             j.appendChild(document.createTextNode(fields[7]));
             cell.appendChild(j);   
        }
        /*else if(i==7){
            var j = document.createElement("a");
            j.appendChild(document.createTextNode(fields[i]));
            cell.appendChild(j);
        }*/
        
        else if(i==0){
            cell.innerHTML = fields[i];        
        }
        
        
    }
    
}


//----------------------------------------------------------------------------------------------
//For Contact Id!=0
function readyStateHandlerAddActivities1(req,responseHandler) {
    //alert(1);
    return function() {
        var  myHTMLTable1 = document.getElementById("tblUpdate2");
        ClrTableAdd1(myHTMLTable1);
        if (req.readyState == 4) {
            if (req.status == 200) {
               // document.getElementById("loadMessage").style.display = 'none';
                
                var headerFields1 = new Array("SNo","DateCreated","Activity Type","Description","AssignedTo","DueDate","ContactFName","ContactLName");	
                var getResponseData;
                getResponseData = req.responseText;
                // alert('getResponseData******'+getResponseData);
                var temp = new Array();
                temp = getResponseData.split('addto');
                if(req.responseText!=''){
                     // document.forMycop.inputRowData.style.display="block";
                   //document.forMycop.inputRowData.value= "Records Found "+temp[1];
                   // alert(temp[0]);
                    ParseAndGenerateHTML2(myHTMLTable1,temp[0], headerFields1,temp[1]);   //this function implementation in VenusReportAjax.js
                    
                    // myHeadTable.innerHTML = "Total Rows"+temp[1];
                }else{
                    alert('No Result For This Search...');
                    spnFast.innerHTML="No Result For This Search...";                
                }
                //document.getElementById("loadMessage2").style.display = 'none';
                
            } else {
                //alert("HTTP error ---"+req.status+" : "+req.statusText);
                alert("please wait");
                
                 //document.getElementById("loadMessage").style.display = 'block';
                //document.getElementById("loadMessage2").style.display = 'block';
            }
        }
        else { //document.getElementById("loadMessage").style.display = 'block';
	 }
    }
}

function ClrTableAdd1(myHTMLTable1) { 
//alert("add1");
    var tbl =  myHTMLTable1;
    var lastRow = tbl.rows.length; 
    //document.getElementById('addlabel1').style.display = 'none'; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}
// Contact Id!=0 -------------------------------------------------------------------------------------
function ParseAndGenerateHTML2(oTable,responseString,headerFields1,rowCount) {
    var start = new Date();
//    var fieldDelimiter = "|";
//    var recordDelimiter = "^";
 var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";
    //alert("parse1");
    // alert('responseString*******'+responseString);
    var records = responseString.split(recordDelimiter);
    //	Generate the HTML Code for the Table Creation using HeaderFields & Records
    generateTable2(oTable,headerFields1,records,fieldDelimiter);
}

function generateTable2(oTable, headerFields1,records,fieldDelimiter) {	    
    //alert("genTable1");
    var tbody = oTable.childNodes[0];
    // oTable.outerHTML = str;
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    if(records.length >=1 && records!=""){
        generateTableHeader1(tbody,headerFields1);
    }
    
    //alert('records.length>> '+records.length);
    
    for(var i=0;i<records.length-1;i++) {
        generateRowADD(tbody,records[i],fieldDelimiter);               
    }
    //oTable.row[0].column[0].onclick=getData();
    
   var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.colSpan = "9";
    footer.appendChild(cell); 
  // generateFooter(tbody); 
    
} 

function generateRowADD(tableBody,record,delimiter) {
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
   // alert("generaterowADD");
    var fields = record.split(delimiter);   
    for (var i=0;i<fields.length-2;i++) {
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        row.appendChild( cell );
        //alert(fields[1]+'----'+fields[2]+'--'+fields[3]+'--'+fields[4]);
        
        if(i==1){         
            
            var j = document.createElement("a");
            
            j.appendChild(document.createTextNode(fields[4].substring(0,10)));
            cell.appendChild(j);
            
        }
        
        else if(i==2){            
            
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:getActivityData('"+fields[1]+"')");
            j.appendChild(document.createTextNode(fields[i]));
            cell.appendChild(j);
        }
        else if(i==3){
            var j = document.createElement("a");
             j.appendChild(document.createTextNode("Click To View"));
             j.setAttribute("href", "javascript:doGetAll('"+fields[1]+"')");
             //j.appendChild(document.createTextNode(fields[8]));
             cell.appendChild(j);
             //cell.align = "center";    
        }
        else if(i==4){
            
            var j = document.createElement("a");
            j.appendChild(document.createTextNode(fields[3]));
            cell.appendChild(j);
        }
        else if(i==5){
      
         var j = document.createElement("a");
            j.appendChild(document.createTextNode(fields[5].substring(0,10)));
            cell.appendChild(j);   
        }
         else if(i==6){
            var j = document.createElement("a");
            
            j.appendChild(document.createTextNode(fields[i]));
            cell.appendChild(j);
            
        }
        else if(i==7){
            var j = document.createElement("a");
            j.appendChild(document.createTextNode(fields[i]));
          //  j.setAttribute("href", "mailto:'"+fields[i]+"'");
            cell.appendChild(j);
        }
        
        else if(i==0){
            cell.innerHTML = fields[i];        
        }
        
        
    }
    
}

//----------------------------------------------------------------------------------------------------

function generateFooter(tbody) {
   // alert(tbody);
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
//    cell.id="footer"+oTable.id;
    cell.colSpan = "9";
    footer.appendChild(cell);
}
/*
function getContactData(contactId,accId) {
    //alert('hi');
    document.location="/Hubble/crm/contacts/getContact.action?Id="+contactId;
    }
*/
function getContactData(contactId,designationName) {
    //alert('hi------>'+designationName);
    var designation = "";
    if(designationName=="TL")
        {
            
            designation = "TeamLead";
        }
   else if(designationName=="CU")
    {
        designation = "Customer";
    }
   else if(designationName=="CN")
        {
             designation = "Consultant";
        }
    else if(designationName=="MN")
        {
             designation = "Manager";
        }
     else if(designationName=="DR")
        {
             designation = "Director";
        }
         else if(designationName=="OR")
        {
             designation = "Operations";
        }
       // alert("designation-->"+designation);
    document.location="/Hubble/crm/contacts/getContact.action?Id="+contactId+"&designationName="+designation;
    }

function getActivityData(accId) {
    //alert('hi');
    //document.location="/getActivity.action?id="+accId;
    document.location="/Hubble/crm/activities/getActivity.action?id="+accId;
    }
    
    
     function doUnSubscribeChanges() {
          var contactStatus=document.getElementById("contactStatus").value;
          if(contactStatus == 'Unsubscribe'){
              document.getElementById("comments").value = 'Unsubscribed Contact Please donot send any news letters /emails to this contact';
              
          }
    }
    
   function readyStateTxtHandler(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
              //  (document.getElementById("loadActMessageAS")).style.display = "none";
                responseTextHandler(req.responseText);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
            //(document.getElementById("loadActMessageAS")).style.display = "block";
        }
    }
}
 function accountContactsDescription(id) {
    // alert(id);
    var comments = comments;
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateTxtHandler(req,accountContactsDescriptionComments);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/accountContactsDescription.action?id="+id;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function accountContactsDescriptionComments(text) {
    var background = "#3E93D4";
    var title = "Description";
    var text1 = text; 
    var size = text1.length;
   // alert(text);
    //Now create the HTML code that is required to make the popup
     var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+text1+"<br />\
    </body></html>";
    alert("size=="+size);
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
    
    else {
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}
	
 
	
// new methods

function dispalyDatePicker()
{   // alert("Haii");
   var res=document.getElementById('activityType').value;  
   //alert("res-->"+res);
     if(res=="Alarm")
        {          
          //  document.getElementById('enbale').style.display = "block";  
            showRow("enableTr");
        }
        else {   
          //  alert();
          document.getElementById('dueDate').value="";
        //  document.getElementById('enbale').style.display = "none";   
        hideRow("enableTr");
                
    }
}


function saveContactDetails(){
  
    document.getElementById("activityForm").submit();
}
function enableCampaignName(res)
{
  //  alert(res);
   if(res=="Campaign")
       {         
            document.getElementById("campaignId").value="";
           document.getElementById("campaignId").disabled = false;          
            $('#accContacts').selectivity('clear');
       } 
       else{
           document.getElementById("campaignId").value="";
           document.getElementById("campaignId").disabled = true;
           
       }
}

function disableCampaignName()
{
   // alert("test");
 
   // alert(document.getElementById("activityType").value)
    var activityType=document.getElementById("activityType").value;
      // alert(activityType);
    if(activityType=="Campaign"){
     document.getElementById("campaignId").disabled = false;
 }
 else{
     document.getElementById("campaignId").value="";
           document.getElementById("campaignId").disabled = true;
 }
 
}	

function duedate()
{   
     var activityType=document.getElementById('activityType').value;
    var res=document.getElementById("dueDate").value;
    if(activityType=="Alarm")
   {
    if(res=="")
        {
        //   document.getElementById('resultMessage').innerHTML = "<font color=red>please enter Alarm Date!!</font>";    
            alert("Please enter alarm Date!");
            return false;
        }
        else
            {
                return true;
            }
   }
   return true;
}


function hideRow(id) {    
    var row = document.getElementById(id);
    row.style.display = 'none';
}

function showRow(id) {   
    var row = document.getElementById(id);
    row.style.display = '';
} 


  function checkvalidation(){
   // alert("hai");
    var oppurtunities=document.getElementById('oppurtunityId').value;
    var activityType=document.getElementById('activityType').value;
    var campainName=document.getElementById('campaignId').value;
    var date=document.getElementById('dueDate').value;
    var contacts=document.getElementById("accContacts").value;
   // alert("con--"+contacts);
        var contactsHidden=[];
        $("#accContacts :selected").each(function(){
            contactsHidden.push($(this).val());
        });
        var contactsIdLength=contactsHidden.length;
        if(activityType=='Campaign'){
            if((campainName==-1)||(contactsIdLength==0)){
               document.getElementById('resultMessage').innerHTML = "<font color=red>Please Select Campaign Name and give atleast one contact!!</font>";
               //alert("Please Select Campaign Name and give atleast one contact");
                return false;
            }
        
    }

        if(oppurtunities==-1){
         document.getElementById('resultMessage').innerHTML = "<font color=red>please Select Oppurtunities!!</font>";
       // alert("please Select Oppurtunities");
        return false;   
    }
      var activityType=document.getElementById('activityType').value;
    var res=document.getElementById("dueDate").value;
    if(activityType=="Alarm")
   {
    if(res=="")
        {   document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter alarm Date!</font>";
            
            return false;
        }
        
   }
    
   return true;
}


function displayConferenceDropdowns(){
     var activityType=document.getElementById("activityType").value;
 if(activityType=="Conference Call")  {        
            showRow("contactTypeTr");
        } else {   
        hideRow("contactTypeTr");
}
}	

/*Author : Santosh Kola
 * DAte : 04/19/2017
 * For returning with out email contact
 */

function isContactEmailExist(contactId){
     var contactWotEmailMap = document.getElementById("wotEmailContacts");
     
     var wotEmailContact="";
  for (var i = 0; i < contactWotEmailMap.length; i++){
    var option = contactWotEmailMap.options[i];
    if(option.value==contactId){
        wotEmailContact = option.text;
        break;
    }
   // alert(option.text+" "+option.value);
    // now have option.text, option.value
  }
  return wotEmailContact;
}
