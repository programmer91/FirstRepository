

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

function readyStateHandlerreq(req,responseTextHandler) {

        //alert("ready");
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadingMessage12")).style.display = "none";
                responseTextHandler(req.responseText);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
          
            (document.getElementById("loadingMessage12")).style.display = "block";
        }
    }
}

function getContactSearchList() {
    
   
   // alert("role Id in getREqList:"+AjaxRoleId);
    var req = newXMLHttpRequest();
     req.onreadystatechange = readyStateHandlerreq(req, displayContactSearchResult); 
    
      //alert("path ==="+CONTENXT_PATH);

    var url = CONTENXT_PATH+"/contactSearchAjaxList.action";
 
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}



function displayContactSearchResult(resText) 
{
  
    if(resText.length !=0)
    {
        var oTable = document.getElementById("tblUpdate");
       
         clearTable(oTable);
      
            
       var headerFields = new Array("SNo","Contact Name","Email","Company Name","Last Activity Date");	
               
    
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
       
        var resTextSplit1 = resText.split("*@!");

         generateTableHeader(tbody,headerFields);
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#^$");
            
                generateRow1(tbody,resTextSplit2,index);
            
        }
        generateFooter(tbody);
       
    }
    else {
        alert("No Records Found");
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

        cell.setAttribute("width","5%");
        cell.innerHTML = headerFields[i];
    }
}

function generateRow1(tableBody,rowFeildsSplit,index){
     var row;
    var cell;
    row = document.createElement("TR");
    row.className="gridRowEven";
    cell = document.createElement("TD");
    cell.className="gridRowEven";
    cell.innerHTML = index+1;
     cell.setAttribute("width","5%");
    cell.setAttribute('align','left');
    row.appendChild(cell);
   
   cell = document.createElement( "TD" );
        cell.className="gridRowEven";
       ///
        var j = document.createElement("a");
         
          //  j.setAttribute("href", "../activities/activity.action?accountId="+rowFeildsSplit[2]+"&contactId="+rowFeildsSplit[1]);
          j.setAttribute("href", "../contacts/getContact.action?Id="+rowFeildsSplit[1]+"&designationName=");
            j.appendChild(document.createTextNode(rowFeildsSplit[3]));
           
       
       document.create
            cell.appendChild(j);
              cell.setAttribute("width","15%");
              cell.setAttribute('align','left');
              row.appendChild(cell);
        
        
        
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
       
         cell.appendChild(document.createTextNode(rowFeildsSplit[4]));
        row.appendChild(cell);
         cell.setAttribute("width","20%");
        cell.setAttribute('align','left');

  cell = document.createElement( "TD" );
        cell.className="gridRowEven";
       ///
        var j = document.createElement("a");
          
             j.setAttribute("href", "../accounts/getAccount.action?id="+rowFeildsSplit[2]);
            j.appendChild(document.createTextNode(rowFeildsSplit[5]));
           
       
       ///
        
       document.create
            cell.appendChild(j);
            cell.setAttribute("width","30%");
              cell.setAttribute('align','left');
              row.appendChild(cell);

cell = document.createElement( "TD" );
        cell.className="gridRowEven";
       
         cell.appendChild(document.createTextNode(rowFeildsSplit[6]));
        row.appendChild(cell);
    cell.setAttribute("width","15%");
        cell.setAttribute('align','left');




     tableBody.appendChild(row);
}

function clearTable(tableId) {
    var tbl =  tableId;
    var lastRow = tbl.rows.length; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}

function generateFooter(tbody) {
  
   var cell;
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";

        cell.colSpan = "7";
   

    footer.appendChild(cell);
}

function getContactSearchList1() {
    
     var contactName=document.getElementById("contactName").value;
  var contactEmailID=document.getElementById("contactEmailID").value;
 var  contactPhoneNo=document.getElementById("contactPhoneNo").value;
  var contactCompany=document.getElementById("contactCompany").value;
   // alert("role Id in getREqList:"+AjaxRoleId);
    var req = newXMLHttpRequest();
     req.onreadystatechange = readyStateHandlerreq(req, displayContactSearchResult); 
    
      //alert("path ==="+CONTENXT_PATH);

    var url = CONTENXT_PATH+"/contactSearchAjax.action?contactName="+contactName+"&contactEmailID="+contactEmailID+"&contactPhoneNo="+contactPhoneNo+"&contactCompany="+contactCompany;
 
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}
