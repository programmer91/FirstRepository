function ParseAndGenerateHTML(oTable,responseString,headerFields,rowCount,activityCount) {
    var start = new Date();
    var fieldDelimiter = "|";
    var recordDelimiter = "^";
    
    // alert('responseString*******'+responseString);
    var records = responseString.split(recordDelimiter);
    //	Generate the HTML Code for the Table Creation using HeaderFields & Records
    generateTable(oTable,headerFields,records,fieldDelimiter);
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
        cell.width = 50;
    }
}
function generateTable(oTable, headerFields,records,fieldDelimiter) {	    
    
    var tbody = oTable.childNodes[0];
    // oTable.outerHTML = str;
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    if(records.length >=1 && records!=""){
        generateTableHeader(tbody,headerFields);
    }
    
    //alert('records.length>> '+records.length);
    
    for(var i=0;i<records.length;i++) {
        generateRow(tbody,records[i],fieldDelimiter);               
    }
    //oTable.row[0].column[0].onclick=getData();
    
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    footer.appendChild(cell);
    
    
}  
function generateRow(tableBody,record,delimiter) {
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    
    var fields = record.split(delimiter);   
    for (var i=0;i<fields.length;i++) {
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        row.appendChild( cell );
        
        
        if(i==1){         
            //alert('fields[2]----'+fields[2]);
            var j = document.createElement("a");
            
            j.appendChild(document.createTextNode(fields[i]));
            
            cell.appendChild(j);
           
        }
        
        else if(i==2){            
            
            var j = document.createElement("a");
            
            j.appendChild(document.createTextNode(fields[i]));
            cell.appendChild(j);
        }
         else{
            cell.innerHTML = fields[i];        
        }
       
        }
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