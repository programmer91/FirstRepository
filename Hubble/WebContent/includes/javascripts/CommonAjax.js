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


/*Methods for getting States depend on Country*/

function getStateData() {
    
    var country = document.getElementById("country").value;
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateStates);
    var url = CONTENXT_PATH+"/getStateData.action?country="+country;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populateStates(resXML) {
    
    var stateId = document.getElementById("state");
    var country = resXML.getElementsByTagName("COUNTRY")[0];
    var states = country.getElementsByTagName("STATE");
    stateId.innerHTML="";
    for (var i=0;i<states.length;i++) {
        var stateName = states[i]; 
        
        var name = stateName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",name);
        opt.appendChild(document.createTextNode(name));
        stateId.appendChild(opt);
        
    }
}

/*Methods for getting States depend on Country -- Residence*/

function getResStateData() {
    
    var resCountry = document.getElementById("resCountry").value;
    
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateResStates);
    var url = CONTENXT_PATH+"/getStateData.action?country="+resCountry;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    
    req.send(null);
    
}

function populateResStates(resXML) {
    
    var stateId = document.getElementById("resState");   
    
    var country = resXML.getElementsByTagName("COUNTRY")[0];
    
    var states = country.getElementsByTagName("STATE");
    
    stateId.innerHTML="";
    for (var i=0;i<states.length;i++) {
        var stateName = states[i]; 
        
        var name = stateName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",name);
        opt.appendChild(document.createTextNode(name));
        stateId.appendChild(opt);
    }
}

function checkCountry() {
    
    var country = document.getElementById("country").value;
    if(country=='India'){
        //document.getElementById("hideRegion").style.display ='block';
        document.getElementById("region").disabled =true;
        document.getElementById("territory").disabled =true;
        
    }
    else {
        document.getElementById("region").disabled =false;
        document.getElementById("territory").disabled =false;
    }
}
/*function getContactsList() {
    var name=document.getElementById("contactName").value;
    var accId=document.getElementById("accId").value;
    //alert(name+accId);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler1(req); 
    var url = CONTENXT_PATH+"/contactSearch.action?contactSearchName="+name+"&conAccId="+accId+"&dummy="+new Date().getTime();
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}*/
function getContactsList() {
    var name=document.getElementById("contactName").value;
    var accId=document.getElementById("accId").value;
    var title=document.getElementById("contacttitle").value;
    //alert(name+accId);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler1(req); 
    //var url = CONTENXT_PATH+"/contactSearch.action?contactSearchName="+name+"&conAccId="+accId+"&dummy="+new Date().getTime();
     var url = CONTENXT_PATH+"/contactSearch.action?contactSearchName="+name+"&contactTitle="+title+"&conAccId="+accId+"&dummy="+new Date().getTime();
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}
/*
function readyStateHandler1(req,responseHandler) {
    return function() {
        var  myHTMLTable = document.getElementById("tblUpdate");
        ClrTable(myHTMLTable);
        if (req.readyState == 4) {
            if (req.status == 200) {
               // document.getElementById("loadMessage").style.display = 'none';
                var headerFields0 = new Array("SNo","Sal","FName","LName","AliasName","Title","Office Phone","Email","Create Login");	
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
*/
function readyStateHandler1(req,responseHandler) {
    return function() {
        var  myHTMLTable = document.getElementById("tblUpdate");
        ClrTable(myHTMLTable);
        if (req.readyState == 4) {
            if (req.status == 200) {
               // document.getElementById("loadMessage").style.display = 'none';
                var headerFields0 = new Array("SNo","Sal","FName","LName","AliasName","Title","Office Phone","Email","Create Login");	
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
//For Activities
function getActivityList() {
    
   // document.getElementById("activityLoad").style.display='block';
    //var name=document.getElementById("contactName").value;
    var accId=document.getElementById("accId").value;
    //alert(name+accId);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerActivities(req); 
    //var url = CONTENXT_PATH+"/activitySearch.action?contactSearchName="+name+"&conAccId="+accId+"&dummy="+new Date().getTime();
    //var url = CONTENXT_PATH+"/activitySearch.action";
    var url = CONTENXT_PATH+"/activitySearch.action?&actAccId="+accId;
 
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
                //var headerFields1 = new Array("SNo","Sal","FName","LName","AliasName","Title","Office Phone","Email","Create Login");	
                //var headerFields1 = new Array("SNo","DateCreated","Activity Type","Description","Comments","AssignedTo","Status","DueDate");	
               // var headerFields1 = new Array("SNo","DateCreated","Activity Type","Comments","Status","CreatedBy","Opportunity");
               var headerFields1 = new Array("SNo","DateCreated","CreatedBy","Contacts","Activity Type","Comments","Status","Opportunity");

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


//For all Activities
function getAllActivityList() {
    var name=document.getElementById("activityName").value;
    var accId=document.getElementById("accId").value;
    //alert(name+accId);
    //alert("name::"+name);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerAllActivities(req); 
    
      var url = CONTENXT_PATH+"/allActivitySearch.action?activitySearchName="+name+"&actAccId="+accId;
     //var url = CONTENXT_PATH+"/allActivitySearch.action?&actAccId="+accId;
 
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

//for all activities
function readyStateHandlerAllActivities(req,responseHandler) {
    return function() {
        var  myHTMLTable2 = document.getElementById("tblUpdate22");
        ClrTable2(myHTMLTable2);
        if (req.readyState == 4) {
            if (req.status == 200) {
                //document.getElementById("loadMessage").style.display = 'none';
                 //(document.getElementById("loadingMessage")).style.display = "none";
                // var headerFields2 = new Array("SNo","Sal","FName","LName","AliasName","Title","Office Phone","Email","Create Login");	
                
               	var headerFields2 = new Array("SNo","Date Created","Activity Type","Description","Comments","AssignedTo","ContactName","ContactTitle","Due Date");
                var getResponseData;
                getResponseData = req.responseText;
                //alert('getResponseData******'+getResponseData);
                var temp = new Array();
                temp = getResponseData.split('addto');
                if(req.responseText!=''){
                     // document.forMycop.inputRowData.style.display="block";
                   //document.forMycop.inputRowData.value= "Records Found "+temp[1];
                   // alert(temp[0]);
                    ParseAndGenerateHTML2(myHTMLTable2,temp[0], headerFields2,temp[1]);   //this function implementation in VenusReportAjax.js
                    
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

//for allactivities
function ClrTable2(myHTMLTable2) { 
    var tbl =  myHTMLTable2;
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

//for all activities

function ParseAndGenerateHTML2(oTable,responseString,headerFields2,rowCount) {
    var start = new Date();
//    var fieldDelimiter = "|";
//    var recordDelimiter = "^";
 var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";
    
    // alert('responseString*******'+responseString);
    var records = responseString.split(recordDelimiter);
    //	Generate the HTML Code for the Table Creation using HeaderFields & Records
    generateTable2(oTable,headerFields2,records,fieldDelimiter);
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


//for all activities

function generateTable2(oTable, headerFields2,records,fieldDelimiter) {	    
    
    var tbody = oTable.childNodes[0];
    // oTable.outerHTML = str;
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    if(records.length >=1 && records!=""){
        generateTableHeader2(tbody,headerFields2);
    }
    
    //alert('records.length>> '+records.length);
    
    for(var i=0;i<records.length-1;i++) {
        generateRow2(tbody,records[i],fieldDelimiter);               
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
//for contacts
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

//for all activities
function generateTableHeader2(tableBody,headerFields2) {
    var row;
    var cell;
    
    row = document.createElement( "TR" );
    row.className="gridHeader";
    tableBody.appendChild( row );
    
    for (var i=0; i<headerFields2.length; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridHeader";
        row.appendChild( cell );
        cell.innerHTML = headerFields2[i];
        cell.width = 100;
    }
    //generateFooter(tableBody);
}

/*
//for contacts
function generateRow(tableBody,record,delimiter) {
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
        
        
        if(i==1){         
            //alert('fields[2]----'+fields[2]);setAttribute("href", "javascript:set_userToList('"+userId +"','"+ userName +"')");
            var j = document.createElement("a");
            //j.onclick="";
            //j.setAttribute("href", "javascript:getData('"+fields[7]+"')");
            j.appendChild(document.createTextNode(fields[i]));
            cell.appendChild(j);
            
        }
        
        else if(i==2){            
            
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
         else if(i==8){
            var j = document.createElement("a");
            
            j.appendChild(document.createTextNode("create"));
            j.setAttribute("href", "javascript:doEdit('"+fields[8]+"','"+fields[9]+"')");
            cell.appendChild(j);
            cell.align = "center";
            
        }
        else if(i==0){
            cell.innerHTML = fields[i];        
        }
        
        
    }
    
}
*/
function generateRow(tableBody,record,delimiter) {
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
        
        
        if(i==1){         
            //alert('fields[2]----'+fields[2]);setAttribute("href", "javascript:set_userToList('"+userId +"','"+ userName +"')");
            var j = document.createElement("a");
            //j.onclick="";
            //j.setAttribute("href", "javascript:getData('"+fields[7]+"')");
            j.appendChild(document.createTextNode(fields[i]));
            cell.appendChild(j);
            
        }
        
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
         else if(i==8){
            var j = document.createElement("a");
            
            j.appendChild(document.createTextNode("create"));
            j.setAttribute("href", "javascript:doEdit('"+fields[8]+"','"+fields[9]+"','"+fields[7]+"','"+fields[10]+"')");
            cell.appendChild(j);
            cell.align = "center";
            
        }
        else if(i==0){
            cell.innerHTML = fields[i];        
        }
        
        
    }
    
}
//for Activities

 function generateRow1(tableBody,record,delimiter) {
    
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
    
  
        if(i==1){         

            var j = document.createElement("a");
            //j.onclick="";
            //j.setAttribute("href", "javascript:getActivityData('"+fields[8]+"')");
            j.appendChild(document.createTextNode(fields[i].substring(0,10)));
            cell.appendChild(j);
            
        }
        else if(i==2){
            var j = document.createElement("a");
            j.appendChild(document.createTextNode(fields[5]));
          //  j.setAttribute("href", "mailto:'"+fields[i]+"'");
            cell.appendChild(j);
       }
      
      

          else if(i==3){
             var j = document.createElement("a");
            // alert( fields[8])
              
          
            if(fields[8]=="-"){
               j.setAttribute("onmouseover","Tip('No Contacts')");
                j.setAttribute("onmouseout","javascript:UnTip();");
               j.appendChild(document.createTextNode("-"));
           }else{
                var contactName = fields[8].split(',');
           j.setAttribute("onmouseover","Tip('"+fields[8]+"')");
            j.setAttribute("onmouseout","javascript:UnTip();");
               j.appendChild(document.createTextNode(contactName[0]+"..."));}
         
            cell.appendChild(j);
            cell.align = "center";
             
        }
         
        else if(i==4){
         

        
            
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:getActivityData('"+fields[7]+"')");
            j.appendChild(document.createTextNode(fields[2]));
            cell.appendChild(j);
            
        }
         else if(i==5){
             var j = document.createElement("a");
            
            j.appendChild(document.createTextNode("Click To View"));
             j.setAttribute("href", "javascript:doGetComments('"+fields[7]+"')");
            cell.appendChild(j);
            cell.align = "center";
             
        }
       
        
          else if(i==6){            
            
           
            
            var j = document.createElement("a");
            
            j.appendChild(document.createTextNode(fields[4]));
            cell.appendChild(j);
        }
       else if(i==7){
            var j = document.createElement("a");
            j.appendChild(document.createTextNode(fields[6]));
          //  j.setAttribute("href", "mailto:'"+fields[i]+"'");
            cell.appendChild(j);
       }
      
      /*  else if(i==7){
            var j = document.createElement("a");
            
            j.appendChild(document.createTextNode(fields[i].substring(0,10)));
            //j.setAttribute("href", "mailto:'"+fields[i]+"'");
            cell.appendChild(j);
           
            
        }*/
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


//for all Activities

function generateRow2(tableBody,record,delimiter) {
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
        //alert(fields[1]+'----'+fields[2]+'--'+fields[3]+'--'+fields[4]);
        //Date Created
        if(i==1){         
            //alert('fields[2]----'+fields[2]);setAttribute("href", "javascript:set_userToList('"+userId +"','"+ userName +"')");
            var j = document.createElement("a");
            //j.onclick="";
            //j.setAttribute("href", "javascript:getActivityData('"+fields[8]+"')");
            j.appendChild(document.createTextNode(fields[i].substring(0,10)));
            cell.appendChild(j);
            
        }
       //Activity Type
        else if(i==2){            
            
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:getActivityData('"+fields[9]+"')");
            j.appendChild(document.createTextNode(fields[i]));
            cell.appendChild(j);
        }
       //Description
        else if(i==3){
          
            var j = document.createElement("a");
            
            j.appendChild(document.createTextNode("Click To View"));
            j.setAttribute("href", "javascript:doGetAll('"+fields[9]+"')");
            cell.appendChild(j);
            cell.align = "center";

        }
       //comments
        else if(i==4){
         

         var j = document.createElement("a");
            
            j.appendChild(document.createTextNode("Click To View"));
             j.setAttribute("href", "javascript:doGetComments('"+fields[9]+"')");
            cell.appendChild(j);
            cell.align = "center";
            
        }
       //Assigned To
         else if(i==5){
            var j = document.createElement("a");
            
            j.appendChild(document.createTextNode(fields[i]));
            cell.appendChild(j);
            
        }
       //ContactName
        else if(i==6){
           var j = document.createElement("a");
            
            j.appendChild(document.createTextNode(fields[i]));
            //j.setAttribute("href", "mailto:'"+fields[i]+"'");
            cell.appendChild(j);
        }
       // Contact Title
        else if(i==7){
           var j = document.createElement("a");
            
            j.appendChild(document.createTextNode(fields[i]));
            //j.setAttribute("href", "mailto:'"+fields[i]+"'");
            cell.appendChild(j);
           
            
        }
         //Date Due
         else if(i==8){
            var j = document.createElement("a");
            
            j.appendChild(document.createTextNode(fields[i].substring(0,10)));
            //j.setAttribute("href", "mailto:'"+fields[i]+"'");
            cell.appendChild(j);
            
        }
        else if(i==0){
            cell.innerHTML = fields[i];        
        }
        
        
    }
    
}


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

/*function getContactData(contactId) {
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



/* Lead Details display start */
 function getLeadDetailsList() {
   // alert("ahha");
   // var name=document.getElementById("activityName").value;
    var accId=document.getElementById("accId").value;
     //var roleName = document.getElementById("roleName").value;

    //alert(name+accId);
    //alert("name::"+name);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerLeadDetails(req); 
    
   //   var url = CONTENXT_PATH+"/getLeadDetailsList.action?accountId="+accId;
       var url = CONTENXT_PATH+"/getLeadDetailsList.action?accountId="+accId;
     //var url = CONTENXT_PATH+"/allActivitySearch.action?&actAccId="+accId;
   //  alert(url);
 
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function readyStateHandlerLeadDetails(req,responseHandler) {
    return function() {
         var roleName = document.getElementById("roleName").value;
        var  myHTMLTable2 = document.getElementById("tblUpdate111");
        ClrTable2(myHTMLTable2);
        if (req.readyState == 4) {
            if (req.status == 200) {
                //document.getElementById("loadMessage").style.display = 'none';
                 //(document.getElementById("loadingMessage")).style.display = "none";
                // var headerFields2 = new Array("SNo","Sal","FName","LName","AliasName","Title","Office Phone","Email","Create Login");	
                
                //totalStream = totalStream + Id + "#^$" + Title + "#^$" + CreatedBy + "#^$" + CurrStatus + "*@!";
                

               	//var headerFields2 = new Array("SNo","Title","CreatedDate","Investment&nbsp;Value","Expiry&nbsp;Date","Status");
                var headerFields2 ='';
                 if(roleName == "Sales"){
               	 headerFields2 = new Array("SNo","Title","CreatedDate","Oppertunity?","Expiry&nbsp;Date","Status");
                }
                else {
                     headerFields2 = new Array("SNo","Title","CreatedDate","Investment&nbsp;Value","Oppertunity?","Expiry&nbsp;Date","Status");
              }
                
                var getResponseData;
                getResponseData = req.responseText;
              //  alert('getResponseData******'+getResponseData);
                var temp = new Array();
                temp = getResponseData.split('addto');
                if(req.responseText!=''){
                     // document.forMycop.inputRowData.style.display="block";
                   //document.forMycop.inputRowData.value= "Records Found "+temp[1];
                   // alert(temp[0]);
                    ParseAndGenerateHTMLLeads(myHTMLTable2,temp[0], headerFields2,temp[1]);   //this function implementation in VenusReportAjax.js
                    
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

 function ParseAndGenerateHTMLLeads(oTable,responseString,headerFields2,rowCount) {
    var start = new Date(); 
    var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";
    
    // alert('responseString*******'+responseString);
    var records = responseString.split(recordDelimiter);
    //	Generate the HTML Code for the Table Creation using HeaderFields & Records
    generateLeadTable(oTable,headerFields2,records,fieldDelimiter);
}


 function generateLeadTable(oTable, headerFields2,records,fieldDelimiter) {	    
    
    var tbody = oTable.childNodes[0];
    // oTable.outerHTML = str;
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    if(records.length >=1 && records!=""){
        generateTableHeader2(tbody,headerFields2);
    }
    
   // alert('records.length>> '+records.length);
   // alert('records>> '+records);
    
    for(var i=0;i<records.length-1;i++) {
        generateLeadRow(tbody,records[i],fieldDelimiter);               
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
 function generateLeadRow(tableBody,record,delimiter) {
    var roleName = document.getElementById("roleName").value;
    var accId=document.getElementById("accId").value;
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
   //
   //  alert(record);
    var fields = record.split(delimiter);   
    for (var i=0;i<fields.length-1;i++) {
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        row.appendChild( cell );
        //alert(fields[1]+'----'+fields[2]+'--'+fields[3]+'--'+fields[4]);
        
       if(i==1){            
            
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:getLeadData('"+fields[1]+"','"+accId+"')");
            j.appendChild(document.createTextNode(fields[2]));
            cell.appendChild(j);
        }
       
        else  if(i==0){  
            cell.innerHTML = fields[0];        
        } else  if(i==2){  
            cell.innerHTML = fields[3];        
        }
      else  if(i==3){  
          
            if(roleName == "Sales"){
                 cell.innerHTML = fields[4]; 
            }
            else{
                  cell.innerHTML = "$ "+fields[4];    
            }
        }

         else  if(i==4){  
            cell.innerHTML = fields[5];        
        } else  if(i==5){  
            cell.innerHTML = fields[6];        
        }  else if (i==6){
            cell.innerHTML = fields[7]; 
        }
    }
    
}
function getLeadData(id,accId){  
    window.location=CONTENXT_PATH+"/marketing/doEditLeads.action?accountId=" + accId+"&leadId="+id;
}


function poplatingvalues()
{
   // alert("a");
    var skillCatArry = [];    
    $("#accContacts :selected").each(function(){
        skillCatArry.push($(this).val()); 

    });
    document.getElementById("contactsHidden").value=skillCatArry;
}

function saveLeadDetails(){
    
    poplatingvalues();
     //alert("aaa");
     if(checkAddLeads()){
    document.getElementById("addLeads").submit();
     }
}
function updateLeadDetails(){
    poplatingvalues();
    if(checkAddLeads()){
    document.getElementById("addLeads").submit();
    }
}
function updateLeadData(id,accId){
    //window.location="../../marketing/doUpdateLeads.action?accountId=" + accId+"&leadId="+id;
        window.location=CONTEXT_PATH+"/marketing/doUpdateLeads.action?accountId=" + accId+"&leadId="+id;
}

function checkAddLeads()
{
 
    var title=document.getElementById('title').value;
    var investmentId=document.getElementById('investmentId').value;
    var contactsHidden=document.getElementById('contactsHidden').value;
    var description=document.getElementById('description').value;
     var accountId=document.getElementById('accountId').value;
     var passedBy1hidden=document.getElementById('passedBy1hidden').value;
     var comments1=document.getElementById('comments1').value;
     var nextSteps1=document.getElementById('nextSteps1').value;
  
    
     // 
      var row2 = document.getElementById('row2');
      var row4 = document.getElementById('row4');
     
      //  alert("row2.style.display"+row2.style.display);
       // alert("row2.style.display"+row4.style.display);
  if(row2.style.display == ""){
   
         var passedBy2hidden=document.getElementById('passedBy2hidden').value;
     var comments2=document.getElementById('comments2').value;
     var nextSteps2=document.getElementById('nextSteps2').value;
  }
  if(row4.style.display == ""){
         //alert("row2.style.display"+row4.style.display);
         var passedBy3hidden=document.getElementById('passedBy3hidden').value;
     var comments3=document.getElementById('comments3').value;
     var nextSteps3=document.getElementById('nextSteps3').value;
  }
    
     
     var investmentType=document.getElementById('investmentType').value;
    
    
    if(title=="" || description=="" || title==null || description==null || investmentId.trim().length=="" || contactsHidden.trim().length=="" ||((passedBy1hidden.trim().length==""  || comments1.trim().length=="" || nextSteps1.trim().length=="") && investmentType.trim()=='P')
|| (row2.style.display == "" && ((passedBy2hidden.trim().length==""  || comments2.trim().length=="" || nextSteps2.trim().length=="")&& investmentType.trim()=='P'))
|| (row4.style.display == "" && ((passedBy3hidden.trim().length==""  || comments3.trim().length=="" || nextSteps3.trim().length=="")&& investmentType.trim()=='P')))
    {
        alert("Please enter all the details!");
        return false;
    }else if(parseInt(accountId,10)==0){
        var consultantId=document.getElementById('consultantId').value;
         var customerName=document.getElementById('customerName').value;
        if(customerName!=null&&customerName!=''&&customerName.trim().length>0){
            if(consultantId!=''){
                document.getElementById('accountId').value=consultantId;
                return true;
            }else {
                 alert("Please select account name from suggestion list.");
        return false;
            }
        }else {
             alert("Please select account name from suggestion list.");
        return false;
        }
        
        
    }
   
    else
    {
        return true;
    }

}
function createOpportunity(){
    var accountId=document.getElementById("accountId").value;
    var leadId=document.getElementById("leadId").value;
    
    window.location = "../crm/opportunities/opportunity.action?accountId="+accountId+"&leadSourceId="+leadId+"&addingOppurtunties=addOppur";
}



/*Get Opportunity List
 * Author : Santosh Kola
 * Date : 04/12/2016
 */

function getOpportunityList() {
    var opportunityState=document.getElementById("opportunityState").value;
    var accId=document.getElementById("accId").value;
    //alert(name+accId);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerOpportunity(req); 
    var url = CONTENXT_PATH+"/getOpportunityList.action?opportunityState="+opportunityState+"&conAccId="+accId+"&dummy="+new Date().getTime();
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}


function readyStateHandlerOpportunity(req,responseHandler) {
    return function() {
        var  myHTMLTable = document.getElementById("tblOpprtunity");
        ClrTable(myHTMLTable);
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("opploadMessage").style.display = 'none';
                var headerFields0 = new Array("SNo","State","Title","Description","Value","Due Date","CreatedDate");	
                var getResponseData;
                getResponseData = req.responseText;
                // alert('getResponseData******'+getResponseData);
                var temp = new Array();
                temp = getResponseData.split('addto');
                if(req.responseText!=''){
                     // document.forMycop.inputRowData.style.display="block";
                   //document.forMycop.inputRowData.value= "Records Found "+temp[1];
                   // alert(temp[0]);
                    ParseAndGenerateHTMLOpportunity(myHTMLTable,temp[0], headerFields0,temp[1]);   //this function implementation in VenusReportAjax.js
                    
                    // myHeadTable.innerHTML = "Total Rows"+temp[1];
                }else{
                 //   alert('No Result For This Search...');
                    //spnFast.innerHTML="No Result For This Search...";                
                }
                document.getElementById("opploadMessage").style.display = 'none';
                
            } else {
                //alert("HTTP error ---"+req.status+" : "+req.statusText);
                alert("please wait");
                
                 document.getElementById("opploadMessage").style.display = 'block';
                //document.getElementById("loadMessage2").style.display = 'block';
            }
        }
        else { document.getElementById("opploadMessage").style.display = 'block';
	 }
    }
}


 function ParseAndGenerateHTMLOpportunity(oTable,responseString,headerFields2,rowCount) {
    var start = new Date(); 
    var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";
    
    // alert('responseString*******'+responseString);
    var records = responseString.split(recordDelimiter);
    //	Generate the HTML Code for the Table Creation using HeaderFields & Records
    generateOpportunityTable(oTable,headerFields2,records,fieldDelimiter);
}

 function generateOpportunityTable(oTable, headerFields2,records,fieldDelimiter) {	    
    
    var tbody = oTable.childNodes[0];
    // oTable.outerHTML = str;
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    if(records.length >=1 && records!=""){
        generateTableHeader2(tbody,headerFields2);
    }
    
   // alert('records.length>> '+records.length);
   // alert('records>> '+records);
    
    for(var i=0;i<records.length-1;i++) {
        generateOpportunityRow(tbody,records[i],fieldDelimiter);               
    }
    //oTable.row[0].column[0].onclick=getData();
    
   var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.colSpan = "7";
    footer.appendChild(cell); 
  // generateFooter(tbody); 
    
} 


function generateOpportunityRow(tableBody,record,delimiter) {
    var roleName = document.getElementById("roleName").value;
    var accId=document.getElementById("accId").value;
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
   //
   //  alert(record);
    var fields = record.split(delimiter);   
    for (var i=0;i<fields.length;i++) {
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        row.appendChild( cell );
        //alert(fields[1]+'----'+fields[2]+'--'+fields[3]+'--'+fields[4]);
        
       if(i==2){            
            
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:getOpportunityData('"+fields[1]+"','"+accId+"')");
            j.appendChild(document.createTextNode(fields[3]));
            cell.appendChild(j);
        }
       
        else  if(i==0){  
            cell.innerHTML = fields[0];        
        } else  if(i==1){  
            cell.innerHTML = fields[2];        
        }
      else  if(i==3){  
          
          var j = document.createElement("a");
            j.setAttribute("href", "javascript:getOpportunityDescription('"+fields[1]+"')");
            j.appendChild(document.createTextNode("Click To View"));
            cell.appendChild(j);
        }

         else  if(i==4){  
            cell.innerHTML = "$"+fields[4];        
        } else  if(i==5){  
            cell.innerHTML = fields[5];        
        }  else if (i==6){
            cell.innerHTML = fields[6]; 
        }
    }
    
}
function getOpportunityData(id,accId){  
    window.location="../opportunities/getOpportunity.action?id="+id+"&accountId=" + accId+"&addingOppurtunties=editOppur&pri=All";
}




/*
 * Requirement Ajax table
 * Author : Leela
 * Date : 0512/2016
 * 
 */


function getRequirementList() {
  
  
   
    
      var skillCatArry2 = [];    
    $("#requirementstatus :selected").each(function(){
        skillCatArry2.push($(this).val()); 
    });
    document.getElementById("requirementstatus1").value=skillCatArry2;
     var requirementStatus=document.getElementById("requirementstatus1").value;
    var accId=document.getElementById("accId").value;
 //   alert(name+accId);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerRequirement(req); 
    var url = CONTENXT_PATH+"/getRequirementList.action?requirementstatus="+requirementStatus+"&conAccId="+accId;
  //   alert("url"+url);
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}


function readyStateHandlerRequirement(req,responseHandler) {
    return function() {

        var  myHTMLTable = document.getElementById("tblRequirement222");
        ClrTable(myHTMLTable);
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("reqloadMessage").style.display = 'none';
                var headerFields0 = new Array("SNo","Req Id","Job&nbsp;Title","Location","Status","Start&nbsp;Date","Recruiter","Pre-Sales");	
                var getResponseData;
                getResponseData = req.responseText;
               //  alert('getResponseData******'+getResponseData);
                var temp = new Array();
                temp = getResponseData.split('addto');
                if(req.responseText!=''){
                     // document.forMycop.inputRowData.style.display="block";
                   //document.forMycop.inputRowData.value= "Records Found "+temp[1];
                   // alert(temp[0]);
                    ParseAndGenerateHTMLRequirement(myHTMLTable,temp[0], headerFields0,temp[1]);   //this function implementation in VenusReportAjax.js
                    
                    // myHeadTable.innerHTML = "Total Rows"+temp[1];
                }else{
                 //   alert('No Result For This Search...');
                    //spnFast.innerHTML="No Result For This Search...";                
                }
                document.getElementById("reqloadMessage").style.display = 'none';
                
            } else {
                //alert("HTTP error ---"+req.status+" : "+req.statusText);
                alert("please wait");
                
                 document.getElementById("reqloadMessage").style.display = 'block';
                //document.getElementById("loadMessage2").style.display = 'block';
            }
        }
        else { document.getElementById("reqloadMessage").style.display = 'block';
	 }
    }
}

 function ParseAndGenerateHTMLRequirement(oTable,responseString,headerFields2,rowCount) {
    var start = new Date(); 
    var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";
    
    // alert('responseString*******'+responseString);
    var records = responseString.split(recordDelimiter);
    //	Generate the HTML Code for the Table Creation using HeaderFields & Records
    generateRequirementTable(oTable,headerFields2,records,fieldDelimiter);
}

function generateRequirementTable(oTable, headerFields2,records,fieldDelimiter) {	    
    
    var tbody = oTable.childNodes[0];
    // oTable.outerHTML = str;
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    if(records.length >=1 && records!=""){
        generateTableHeader2(tbody,headerFields2);
    }
    
   // alert('records.length>> '+records.length);
  //alert('records>> '+records);
    
    for(var i=0;i<records.length-1;i++) {
        generateRequirementRow(tbody,records[i],fieldDelimiter);               
    }
    //oTable.row[0].column[0].onclick=getData();
    
   var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.colSpan = "8";
    footer.appendChild(cell); 
  // generateFooter(tbody); 
    
} 

function generateRequirementRow(tableBody,record,delimiter) {
    var roleName = document.getElementById("roleName").value;
    var accId=document.getElementById("accId").value;
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
   //
   //  alert(record);
    var fields = record.split(delimiter);
  //  alert(fields);
    for (var i=0;i<fields.length-1;i++) {
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        row.appendChild( cell );
    //    alert(fields[1]+'----'+fields[2]+'--'+fields[3]+'--'+fields[4]);
        
       if(i==2){            
           
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:getRequirementData('"+fields[1]+"','"+ fields[2]+"')");
           j.appendChild(document.createTextNode(fields[3]));
            cell.appendChild(j);
        }
      
        else  if(i==0){  
            cell.innerHTML = fields[0];        
        }
        //else  if(i==1){  
        //    cell.innerHTML = fields[1];        
       // }
//      else  if(i==3){  
//          
//          var j = document.createElement("a");
//            j.setAttribute("href", "javascript:getOpportunityDescription('"+fields[1]+"')");
//            j.appendChild(document.createTextNode("Click To View"));
//            cell.appendChild(j);
//        }
   else  if(i==1){  
            cell.innerHTML = fields[1];        
        } 
         else  if(i==3){  
            cell.innerHTML = fields[4];        
        } else  if(i==4){  
            cell.innerHTML = fields[5];        
        } else  if(i==5){  
            cell.innerHTML = fields[6];        
        }  else if (i==6){
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:getRecruiterDetails('"+fields[7]+"')");
           j.appendChild(document.createTextNode(fields[7]));
            cell.appendChild(j);
        }else if (i==7){
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:getRecruiterDetails('"+fields[8]+"')");
           j.appendChild(document.createTextNode(fields[8]));
            cell.appendChild(j);
        }
    }
    
}

function getRequirementData(requirementId,accId){  
 //   alert(requirementId)
    window.location="../requirement/getRequirement.action?objectId="+requirementId+"&accId="+accId+"&pri=All";
}


function getRecruiterDetails(Recruiter) {
    
    var test = Recruiter;
    //alert(test.substr(0,1));
    //alert("test --"+test);
    //alert(indexOf("."));
    // alert(test.substr(test.indexOf(".")+1,test.length));
    //var loginId=(test.substr(0,1)+test.substr(test.indexOf(".")+1,test.length));
      var loginId=Recruiter;
    //alert("loginId"+loginId);
    var aId = Recruiter;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populateReqPersonSkills);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/popupReqRecruiterWindow.action?recruiterId="+loginId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function populateReqPersonSkills(text) {
    var background = "#3E93D4";
    var title = "Employee Details";
    var text1 = text; 
    var size = text1.length;
    
    // alert("text "+text1);
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'>"+text1+"<br />\
    </body></html>";
    
    //alert("text1"+text1);
    // alert("size "+content.length);
    var indexof=(content.indexOf("^")+1);
    var lastindexof=(content.lastIndexOf("^"));
    
    popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
    if(content.indexOf("^")){
        //alert(content.substr(0,content.indexOf("//")));
        popup.document.write("<b>Employee Name : </b>"+content.substr(0,content.indexOf("^")));
        popup.document.write("<br><br>");
        popup.document.write("<b>Work Phone No :</b>"+content.substr((content.indexOf("^")+1),(lastindexof-indexof)));
        popup.document.write("<br><br>");
        popup.document.write("<b>Email Address :</b>"+content.substr((content.lastIndexOf("^")+1),content.length));
    }//Write content into it.  
    //Write content into it.    
    
    
    
}

