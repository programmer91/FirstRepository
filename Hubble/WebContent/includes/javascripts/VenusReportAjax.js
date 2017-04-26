/* Following functions are used for displaying Employee Venus Report  **/
/* author Venkateswara Rao Nukala  **/

function ParseAndGenerateHTML(oTable,responseString,headerFields,rowCount) {
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
        cell.width = 120;
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
    else {
        generateNoRecords(tbody,oTable);
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
    cell.className="";
    footer.appendChild(cell);
    
    
}  


function generateNoRecords(tbody,oTable) {
    var noRecords =document.createElement("TR");
    noRecords.className="gridRowEven";
    tbody.appendChild(noRecords);
    cell = document.createElement("TD");
    cell.className="gridColumn";
    
    if(oTable.id == "tblActUpdate"){
        cell.colSpan = "5";
    }else if(oTable.id == "tblOppUpdate"){
        cell.colSpan = "5";
    }else if(oTable.id == "tblUpdate"){
        cell.colSpan = "7";
    }
    
    
    cell.innerHTML = "No Records Found for this Search";
    noRecords.appendChild(cell);
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
        else if(i==3){
            var j = document.createElement("a");
            j.appendChild(document.createTextNode(fields[i]));
            cell.appendChild(j);
            
        }
        else if(i==4){
            var j = document.createElement("a");
            
            j.appendChild(document.createTextNode(fields[i]));
            cell.appendChild(j);
            
        }
        else if(i==6){
            
            var j = document.createElement("a");
            j.appendChild(document.createTextNode(fields[i]));
            if((fields[i].substring(1,2)==0) && (fields[i].substring(0,1)==0)) {
                
                row.className="gridRowEvenYellow";
                
            }
            else {
                if((fields[i].substring(1,2)!=9)){
                    if((fields[i].substring(1,2)  < 8 && fields[i].substring(0,1)==0))
                        row.className="gridRowEvenRed";
                }
            }
            cell.appendChild(j);
        }
        
        else{
            cell.innerHTML = fields[i];        
        }
        
        
    }
}
function GenerateHTML(oTable,responseString,headerFields,rowCount){
    var start = new Date();
    var fieldDelimiter = "|";
    var recordDelimiter = "^";
    
    // alert('responseString*******'+responseString);
    var records = responseString.split(recordDelimiter);
    //	Generate the HTML Code for the Table Creation using HeaderFields & Records
    generateTable1(oTable,headerFields,records,fieldDelimiter); 
}
function generateTable1(oTable, headerFields,records,fieldDelimiter) {	    
    
    var tbody = oTable.childNodes[0];
    // oTable.outerHTML = str;
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    if(records.length >=1 && records!=""){
        generateTableHeader1(tbody,headerFields);
    }
    
    //alert('records.length>> '+records.length);
    
    for(var i=0;i<records.length;i++) {
        generateRow1(tbody,records[i],fieldDelimiter);               
    }
    //oTable.row[0].column[0].onclick=getData();
    
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    footer.appendChild(cell);
    
    
} 
function generateTableHeader1(tableBody,headerFields) {
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
function generateRow1(tableBody,record,delimiter) {
    
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
        else if(i==3){
            var j = document.createElement("a");
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
        
        else{
            cell.innerHTML = fields[i];        
        }
        
        
    }
}


function GenerateTimeSheetHTMLPage(oTable,responseString,headerFields,rowCount) {
    
    var start = new Date();
    var fieldDelimiter = "|";
    var recordDelimiter = "^";
    
    // alert('responseString*******'+responseString);
    var records = responseString.split(recordDelimiter);
    //	Generate the HTML Code for the Table Creation using HeaderFields & Records
    generateTable2(oTable,headerFields,records,fieldDelimiter);
    
}

function generateTable2(oTable, headerFields,records,fieldDelimiter) {	    
    
    var tbody = oTable.childNodes[0];
    // oTable.outerHTML = str;
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    if(records.length >=1 && records!=""){
        generateTableHeader2(tbody,headerFields);
    }
    
    //alert('records.length>> '+records.length);
    
    for(var i=0;i<records.length;i++) {
        generateRow2(tbody,records[i],fieldDelimiter);               
    }
    //oTable.row[0].column[0].onclick=getData();
    
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="";
    footer.appendChild(cell);
    
    
} 
function generateTableHeader2(tableBody,headerFields) {
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


function GenerateNotApprovedTimeSheetHTMLPage(oTable,responseString,headerFields,rowCount){
    var start = new Date();
    var fieldDelimiter = "|";
    var recordDelimiter = "^";
    
    //alert('responseString*******'+responseString);
    var records = responseString.split(recordDelimiter);
    //	Generate the HTML Code for the Table Creation using HeaderFields & Records
    generateTable3(oTable,headerFields,records,fieldDelimiter); 
}

function generateTable3(oTable, headerFields,records,fieldDelimiter) {	    
    
    var tbody = oTable.childNodes[0];
    // oTable.outerHTML = str;
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    if(records.length >=1 && records!=""){
        generateTableHeader3(tbody,headerFields);
    }
    
    //alert('records.length>> '+records.length);
    
    for(var i=0;i<records.length;i++) {
        generateRow3(tbody,records[i],fieldDelimiter);               
    }
    //oTable.row[0].column[0].onclick=getData();
    
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="";
    footer.appendChild(cell);
    
} 

function generateTableHeader3(tableBody,headerFields) {
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

function generateRow3(tableBody,record,delimiter) {
    
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

