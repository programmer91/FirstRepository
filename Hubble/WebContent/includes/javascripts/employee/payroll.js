
/**
 * Author : Nagireddy seerapu
 * 
**/

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
 

function getcurrentYear(){
// alert("hi");
var today=new Date();
var currentYear = today.getFullYear();
document.getElementById('payroll_year').value = currentYear;
}



function getPayRolles(){
//alert("hiiiiii");
    var year = document.getElementById('payroll_year').value;
    if(year.length==4) {
        var tableId = document.getElementById("tblUpdate");
        clearTable(tableId);
        getTotalPdfs();
    }
}


function getTotalPdfs(){
var year = document.getElementById("payroll_year").value;
var UserId = document.getElementById("emp_loginId").value;
//alert("year-->"+year);
var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, displayPDFSList);
    var url = CONTENXT_PATH+"/getPayrollPdfsList.action?payrollyear="+year+"&employeeId="+UserId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function readyStateHandler(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadingMessage")).style.display = "none";
                responseTextHandler(req.responseXML);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
            (document.getElementById("loadingMessage")).style.display = "block";
        }
    }
}

function displayPDFSList(responseXML){
   var pdfs = responseXML.getElementsByTagName("PDFS")[0];
   var pdf = pdfs.childNodes[0];

    var check=pdfs.getElementsByTagName("COUNT")[0];
   //alert("check-->"+check.childNodes[0].nodeValue);
 if(check.childNodes[0].nodeValue > 0) { 
     var pTable = document.getElementById("tblUpdate");
        var headerFields = new Array("S.No","FileName","DownLoad")
        tbody = document.createElement("TBODY");
        pTable.appendChild(tbody);

        generateTableHeader(tbody,headerFields);
        for(var index=0;index<pdfs.childNodes.length-1;index++) {
        var pdf = pdfs.childNodes[index];
              var sno = 0;
            var fileName = pdf.getElementsByTagName("FILENAME")[0].childNodes[0].nodeValue;
            var filePath = pdf.getElementsByTagName("FILEPATH")[0].childNodes[0].nodeValue;
            resTextSplit2 =sno+","+fileName+","+filePath; 
                      
           generateRow(tbody,resTextSplit2,index);
         }
        generateFooter(tbody);
     }else {
         alert("No Records Found");
     }
}
function clearTable(tableId) {
    var tbl =  tableId;
    var lastRow = tbl.rows.length; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
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
    }
}
function generateRow(tableBody,rowFeildsSplit2,index) {

        rowFeildsSplit = rowFeildsSplit2.split(",");

    var row;
    var cell;
    row = document.createElement("TR");
    row.className="gridRowEven";
    cell = document.createElement("TD");
    cell.className="gridRowEven";
    cell.innerHTML = index+1;
    row.appendChild(cell);
    tableBody.appendChild(row);
    var totalLeaves;
    for (var i=1; i<=rowFeildsSplit.length-1; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild(cell);
        if(i==1) {
             cell.innerHTML = rowFeildsSplit[i];
         }
        else if(i==2){
            var anc = document.createElement("a");
           anc.setAttribute("href", "javascript:getPDF('"+rowFeildsSplit[rowFeildsSplit.length-1]+"')");
           anc.appendChild(document.createTextNode("Download"));
            cell.appendChild(anc);
        }
        cell.width = 100;
    }
}
function generateFooter(tbody) {
    var row;
    var cell;
    var totalVal =0;
    row = document.createElement("TR");
    row.className="gridPager";
    tbody.appendChild(row);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.colSpan = "3";
    //cell.width = 100;
    cell.align="right";
   
    row.appendChild(cell);
}


function getPDF(filePath){
alert("filePath---->"+filePath);
var pth = filePath;
   
 document.location=CONTENXT_PATH+"/employee/payroll/downloadPayRoll.action?locationAvailable="+filePath;
   
}
