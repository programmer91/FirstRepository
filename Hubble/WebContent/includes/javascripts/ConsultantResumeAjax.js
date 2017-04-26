function loadXMLResumeDoc(url,callback) {
    //alert("URL: " + url);
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


function generateRowResume(tableBody,record,delimiter) {
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    
    var fields = record.split(delimiter);    
    
    //alert('fields lenght-resume is '+fields.length);
    
    
    // var hide = fields[6];
    // alert('hiding >>'+hide);
    
    
    for (var i=0;i<fields.length;i++) {
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        row.appendChild( cell );
        var home ='file:///';
        
        if(i==2){
            //cell.innerHTML = "<a href='javascript: getConsultantId(" + fields[i] + ");'>List Of Resumes</a>";
            // file:///D:/ProjectFiles/ResumeAttachments/MirageV2/A/2008/Aug/3/omsairam.jpg
            // cell.innerHTML = "<a href='javascript: getDownload("+fields[0] +','+ fields[i] + ");'>Download</a>";
            //cell.innerHTML = "<a href='javascript: getDownload(\" "+fields[0] +"\", "+ fields[i] +");'>Download</a>";
            cell.innerHTML = "<a href='javascript: getDownload("+ fields[i] +");'>Download / View</a>";
            
            //cell.innerHTML = "<a target='_blank' href= '"fields[i]+"'>Download </a>";
            //window.open("newpage.html",'TheNewpop','toolbar=1,location=1,directories=1,status=1,menubar=1,scrollbars=1,resizable=1');
            //cell.innerHTML = "<a target='_blank' href= '"D:\omsairam.jpg",'TheNewpop','toolbar=1,location=1,directories=1,status=1,menubar=1,scrollbars=1,resizable=1''>Download </a>"
        }      
        else{            
            cell.innerHTML = fields[i];  
        }
        
        /*
        if(i==2){          
            var j = document.createElement("a");
            //var j = document.createTextNode(fields[i]);
            //alert(j);
            //j.style.cursor = pointer;
            j.href = '#';
            //var x = fields[i];
            //alert("getData("+fields[i]+")");
            //var consname;
            j.setAttribute("name",fields[i]);
            j.appendChild(document.createTextNode(fields[i]));
         
            j["onclick"]= new Function("loadDiv(this.name)");
            //j["onclick"]= new Function("loadPage(this.name)");
         
            //alert('hiii*** ' + j.getAttribute("name") );
            //alert("getData("+fields[i]+")");
            cell.appendChild(j);
         
            //cell.innerHTML = fields[i];  
         
            //cell[0].innerHTML = "<a href='javascript:return true;' onclick='getData(); return false;'>fields[i]</a>";            
            //row.cells[3].innerHTML = "<a href='javascript:return true;' onclick='saverow(this); return false;'>save</a>";
            //cell.innerHTML = "<a href='javascript: remove(" + itemNum + ");'>Remove</a>";
            //cell.onclick = getData();
            //td.attachEvent('onclick', 'alert("blabla")');
            //document.onfocus = getData();            
            //document.getElementById("cellid"+i).attachEvent('onclick', function(){getData()});   		
        }
        else{
            cell.innerHTML = fields[i];        
        }  */    
        
    }
}




function generateTableHeaderResume(tableBody,headerFields) {
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

function generateTableResume(oTable, headerFields,records,fieldDelimiter) {	    
    
    var tbody = oTable.childNodes[0];
    // oTable.outerHTML = str;
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    if(records.length >=1 && records!=""){
        generateTableHeaderResume(tbody,headerFields);
    }
    
    //alert('records.length>> '+records.length);
    
    for(var i=0;i<records.length;i++) {
        generateRowResume(tbody,records[i],fieldDelimiter);               
    }
    //oTable.row[0].column[0].onclick=getData();
    
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    footer.appendChild(cell);
}

function ParseAndGenerateResumeHTML(oTable,responseString,headerFields) {
    var start = new Date();
    var fieldDelimiter = "|";
    var recordDelimiter = "^";
    
    // alert('responseString in resume*******'+responseString);
    
    var records = responseString.split(recordDelimiter);
    
    //	Generate the HTML Code for the Table Creation using HeaderFields & Records
    generateTableResume(oTable,headerFields,records,fieldDelimiter);
    
    //oTable.row.cell[0].innerHTML = "<a href='javascript:return true;' onclick='saverow(this); return false;'>save</a>";
    
    //oTable.row[1].cell[0].innerHTML = "<a href='javascript:return true;' onclick='getData(); return false;'></a>";
    
    //document.getElementById("table").innerHTML = strTableHTML;
    
    //var end = new Date();
    //spnFast1.innerHTML = "This Search took " + (end.getTime()-start.getTime()) + " milliseconds.";
}



function getDownload(objectId){
    //alert('nono');
    //alert('attachName'+attachName);
    //alert('objectId'+objectId);
    
    document.location ="getDownload.action?id="+objectId;
}

