function loadXMLDoc(url,callback) {
    
    // branch for native XMLHttpRequest object
    if (window.XMLHttpRequest) {
        req = new XMLHttpRequest();
        req.onreadystatechange = callback;
        req.open("GET", url, true);
        req.send(null);
    } 
    // branch for IE/Windows ActiveX version
    else if (window.ActiveXObject) {
        isIE = true;
        req = new ActiveXObject("Microsoft.XMLHTTP");
        if (req) {
            req.onreadystatechange = callback;
            req.open("GET", url, true);
            req.send(null);
        }
    }
}


//====================================================================================
//	This Function generates the HTML <TR> and <TD> for a Set of Fields in a Record
//====================================================================================
var itemNum;
var fields = '';

var name;

/*function generateRow(tableBody,record,delimiter) {
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
            
        cell.innerHTML = fields[i];        
    }
}*/

/*function generateTableHeader(tableBody,headerFields) {
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
}*/

/*function generateTable(oTable, headerFields,records,fieldDelimiter) {	    
    
    var tbody = oTable.childNodes[0];    
    // oTable.outerHTML = str;
    
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    if(records.length >=1 && records!=""){
        generateTableHeader(tbody,headerFields);
    }    
    
    
    for(var i=0;i<records.length;i++) {
        generateRow(tbody,records[i],fieldDelimiter);               
    }    
    
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    footer.appendChild(cell);
    
}*/

/*function ParseAndGenerateHTML(oTable,responseString,headerFields) {
    var start = new Date();
    var fieldDelimiter = "|";
    var recordDelimiter = "^";   
    alert('responseString%%%% ******'+responseString);
    //alert('rowCount%%%% ******'+rowCount);
    var records = responseString.split(recordDelimiter); 
    generateTable(oTable,headerFields,records,fieldDelimiter);    
}*/