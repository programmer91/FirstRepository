/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/*Inside Sales Status 
 * Author : Teja Kadamanti
 * Date : 01/19/2017
 */
function ClrTable(myHTMLTable) { 
    var tbl =  myHTMLTable;
    var lastRow = tbl.rows.length; 
    //document.getElementById('addlabel1').style.display = 'none'; 
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

      function readyStateHandlerInsideSalesBde(req,responseTextHandler) {
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
            
function getInsideSalesStatusList() {
    
     var tableId = document.getElementById("tblInsideSalesStatus");  
  
    ClrTable(tableId);
    document.getElementById("loadingMessage").style.display = 'block';
  
    var insideSalesBDE=document.getElementById("insideSalesBDE").value;
     var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerInsideSalesBde(req,displayInsideSalesStatus); 
    var url = CONTENXT_PATH+"/getInsideSalesStatusList.action?bdeLoginId="+insideSalesBDE;
    //alert(url);
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function displayInsideSalesStatus(response){
   var tableId = document.getElementById("tblInsideSalesStatus");  
    ClrTable(tableId);
    var headerFields = new Array("S.No","BDE Name","TotalAccounts","ActiveOpportunities","LeadName");
      var dataArray = response;
    InsideSalesParseAndGenerateHTML(tableId,dataArray, headerFields);
 
}

 function InsideSalesParseAndGenerateHTML(oTable,responseString,headerFields) {
   
    var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";   
    var records = responseString.split(recordDelimiter); 
    generateInsideSalesStatusTable(oTable,headerFields,records,fieldDelimiter);
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
       // cell.width = 100;
    }
    //generateFooter(tableBody);
}

function generateInsideSalesStatusTable(oTable, headerFields,records,fieldDelimiter) {	

  var tbody = oTable.childNodes[0];    
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    generateTableHeader(tbody,headerFields);
    var rowlength;
        rowlength = records.length;
     if(rowlength >0 && records!=""){
        for(var i=0;i<rowlength-1;i++) {
          
                if(oTable.id=='tblInsideSalesStatus'){
               generateInsideSalesStatusByBDE(oTable,tbody,records[i],fieldDelimiter);
                }else{
                 generateInsideSalesAccountDetailsByBDE(oTable,tbody,records[i],fieldDelimiter);
                 
                }
        }
        
    } else {
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
     if(oTable.id == "tblInsideSalesStatus"){
        cell.colSpan = "6";
    }else{
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
      if(oTable.id == "tblInsideSalesStatus"){
        cell.colSpan = "6";
    }else{
       cell.colSpan = "5";  
    }
     footer.appendChild(cell); 
}	
function generateInsideSalesStatusByBDE(oTable,tableBody,record,delimiter){
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
    
    
     for (var i=0;i<length-1;i++) {
            if(i==2){
               cell = document.createElement( "TD" );
            cell.className="gridColumn"; 
            var j = document.createElement("a");
            cell.innerHTML = "<a href='javascript:getAccountDetailsForInsideSalesStatus(\""+fields[5]+"\",\""+fields[1]+"\")'>"+fields[2]+"</a>";
      
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
    
}


function getAccountDetailsForInsideSalesStatus(bdeLoginId,employeeName){
    
    window.location=CONTENXT_PATH+"/marketing/getAccountDetailsForInsideSalesStatus.action?bdeLoginId="+bdeLoginId+"&backToFlag=No"+"&employeeName="+employeeName;
   
}


function getInsideSalesStatusAccountDetailsList(){
     var tableId = document.getElementById("tblInsideSalesAccounts");  
   ClrTable(tableId);
   
    
      var bdeLoginId=document.getElementById("bdeLoginId").value;
    var consultantId=document.getElementById("consultantId").value;
    var lastActivityFrom=document.getElementById("lastActivityFrom").value;
    var lastActivityTo=document.getElementById("lastActivityTo").value;
     var opportunity=document.getElementById("opportunity").value;
     var touched=document.getElementById("touched").value;
   var employeeName=document.getElementById("employeeName").value;
 
   
    
   if(lastActivityTo!=''){
      if(lastActivityFrom==''){
          alert("please select lastActivityFrom");
          return false;
      } 
   }
   
     
   if(lastActivityFrom!=''){
      if(lastActivityTo==''){
          alert("please select lastActivityTo");
          return false;
      } 
   }
   
    
     
    
    document.getElementById("loadingMessage").style.display = 'block';
  
   
     var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerInsideSalesBde(req,displayInsideSalesAccounts); 
    var url = CONTENXT_PATH+"/getInsideSalesAccountDetailsList.action?bdeLoginId="+bdeLoginId+"&accountId="+consultantId+"&lastActivityFrom="+lastActivityFrom+"&lastActivityTo="+lastActivityTo+"&opportunity="+opportunity+"&touched="+touched+"&employeeName="+employeeName;
    //alert(url);
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function displayInsideSalesAccounts(response){
      var tableId = document.getElementById("tblInsideSalesAccounts");  
       ClrTable(tableId);
    var headerFields = new Array("S.No","Account Name","LastActivityDate","Opportunities ?","Touched (or) Untouched");
      var dataArray = response;
    InsideSalesAccountDetailsParseAndGenerateHTML(tableId,dataArray, headerFields);
}

 function InsideSalesAccountDetailsParseAndGenerateHTML(oTable,responseString,headerFields) {
   
    var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";   
    var records = responseString.split(recordDelimiter); 
    generateInsideSalesStatusTable(oTable,headerFields,records,fieldDelimiter);
}



function generateInsideSalesAccountDetailsByBDE(oTable,tableBody,record,delimiter){
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
    
    
     for (var i=0;i<length-1;i++) {
            if(i==1){
               cell = document.createElement( "TD" );
            cell.className="gridColumn"; 
            var j = document.createElement("a");
            cell.innerHTML = "<a href='javascript:getAccountDetails(\""+fields[5]+"\")'>"+fields[1]+"</a>";
      
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
    
}

function getAccountDetails(accId){
      window.location=CONTENXT_PATH+"/crm/accounts/getAccount.action?id="+accId+"&pri=All"+"&backToFlag=Bde";
 }

var completeTable;
var completeField;
var autorow;
var autorow1;
var req;

function clearTable() {
    if (completeTable) {
        completeTable.setAttribute("bordercolor", "white");
        completeTable.setAttribute("border", "0");
        completeTable.style.visible = false;
        for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
} 

var isIE;


function init1() {
    //alert("rk")
    //getOtherDivs();
    
    //var menu = document.getElementById("auto-row");
    //var menu1 = document.getElementById("auto-row1");
    autorow = document.getElementById("menu-popup");
    autorow.style.display ="none";
    autorow1 = document.getElementById("menu-popup");
    autorow1.style.display ="none";
    //autorow.style.top = getElementY(menu) + "px";
    //autorow1.style.top = getElementY(menu1) + "px";
    var height = document.documentElement.clientHeight - 120;
    autorow1.style.height = Math.max(height, 150);
    autorow1.style.overflowY = "scroll";
    autorow.style.height = Math.max(height, 150);
    autorow.style.overflowY = "scroll";
    
    completeTable = document.getElementById("completeTable");
    completeTable.setAttribute("bordercolor", "white");
}

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



function fillCustomerForBde() {
    var test=document.getElementById("customerName").value; 
 var bdeLoginId=document.getElementById("bdeLoginId").value;
   
//alert("test--->"+test);       
    if (test == "") {
        document.frmInsideSalesAccountSearch.consultantId.value =0;
        clearTable();
    } else {
        if (test.length >2) {
            var url = CONTENXT_PATH+"/getCustomerDetailsByBde.action?customerName="+ escape(test)+"&bdeLoginId="+bdeLoginId;   

            //alert("URL--->"+url);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {                        
                        parseCustMessagesForBde(req.responseXML);                        
                    } else if (req.status == 204){
                        clearTable();
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }
}

function parseCustMessagesForBde(responseXML) {
    clearTable();
    var employees = responseXML.getElementsByTagName("EMPLOYEES")[0];
    if (employees.childNodes.length > 0) {        
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");        
    } else {
        clearTable();
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
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < employees.childNodes.length; loop++) {
            
            var employee = employees.childNodes[loop];
            var customerName = employee.getElementsByTagName("NAME")[0];
            var empid = employee.getElementsByTagName("EMPID")[0];
            appendCustomer(empid.childNodes[0].nodeValue,customerName.childNodes[0].nodeValue);
        }
        var position = findPosition(document.getElementById("customerName"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        document.frmInsideSalesAccountSearch.consultantId.value =0;
        validationMessage.innerHTML = "Account Name like this doesn't exist for the given BDE ";
    }
}

function appendCustomer(empId,empName) {
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
    linkElement.setAttribute("href", "javascript:set_cust('"+empName.replace(/'/g, "\\'") +"','"+ empId +"')");
    
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}
function set_cust(eName,eID){
    clearTable();
    //getProjectNamesList(eID);
    document.frmInsideSalesAccountSearch.customerName.value =eName;
    document.frmInsideSalesAccountSearch.consultantId.value =eID;
    //alert("hii");
}
