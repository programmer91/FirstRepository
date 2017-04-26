function loadXMLDoc(url,callback) {
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



function generateRow(tableBody,record,delimiter) {
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    
    var fields = record.split(delimiter);    
    
    //alert('fields lenght is '+fields.length);
    
    
    // var hide = fields[6];
    // alert('hiding >>'+hide);
    
    
    for (var i=0;i<fields.length;i++) {
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        row.appendChild( cell );
        
        
        if(i==1){         
             //alert('fields[2]----'+fields[2]);
            var j = document.createElement("a");
            //var j = document.createTextNode(fields[i]);
            //alert(j);
            //j.style.cursor = pointer;
            j.href = '#';
            //var x = fields[i];
            //alert("getData("+fields[i]+")");
            //var consname;
            j.setAttribute("name",fields[5]);
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
        
        else if(i==2){            
            // cell.innerHTML = "<a href='mailto:' (" + fields[i] + ");'>"+fields[i]"+</a>";
            // '<a href="mailto:' + theName + '@' + theDomain + '">' + theName + '@' + theDomain + '<\/a>'
            
            var j = document.createElement("a");
            j.href = 'mailto:'+fields[i];
            
//            j.setAttribute("name",fields[i]);
            j.appendChild(document.createTextNode(fields[i]));
            
//            j["onclick"]= new Function("loadMail(this.name)");
            cell.appendChild(j);
        }
        else if(i==4){
            var x = fields[i];
            var y;
            if(x.length>20){
                var part1 = x.substring(0,20);
                var part2 = x.substring(20);   
                 cell.innerHTML = part1+ "<a href='#' onMouseOver='getPart2(\"" + fields[i] + "\");' onmouseout='closepopUpWindow();'> ..</a>";
                //cell.innerHTML = part1+ "<a href="#" onMouseOver="getPart2(\"" + x + "\")" onMouseOut="closepopUpWindow()"> ..</a>";
                //cell.innerHTML = part1+ "<A href="javascript:void(0)" onMouseover="getPart2(\"" + x + "\")" onMouseout="closepopUpWindow()">";
                // <a HREF='' onmouseover='"+this.mstrFunction+"("+resolveFields(this.mstrPrimaryId)+");' onmouseout='closepopUpWindow();'>
                // <A href="javascript:void(0)" onMouseover="lightup('pic1')" onMouseout="turnoff('pic1')" onMouseDown="clickdown('pic1')" onMouseUp="lightup('pic1')"> 
                // cell.innerHTML = part1+ "<a href='javascript: getPart2(\"" + x + "\");'> ..</a>";
            }else
                cell.innerHTML = x;                    
        }
        else if(i==5){
            
            // cell.innerHTML="<img src=CONTENXT_PATH+'/includes/images/add.gif'/>";                        
            // cell.innerHTML = "<a href='"+fields[i]+"'>List </a>";
            // cell.innerHTML = "<a href='javascript: getConsId(" + fields[i] + ");'><img src=CONTENXT_PATH+'/includes/images/add.gif'/></a>";
            cell.innerHTML = "<a href='javascript: getConsultantId(" + fields[i] + ");'> List </a>";
        }
        
        else{
            cell.innerHTML = fields[i];        
        }
        
        
        
        // cell.innerHTML = "<a href='"+this.src+"'>Download this file</a>";
        // cell.innerHTML = "<a href='"+fields[i]+"'>Download this file</a>";
        // cell.innerHTML = "<a href='"+fields[i]+"'>List</a>";
        // cell.innerHTML="<img src=CONTENXT_PATH+'/includes/images/add.gif'/>";
        // /MirageV2/includes/images/showCalendar.gif
        
        
    }
}

var popup;
function getPart2(data){
    // alert('data is ---='+data);
    
    var background = "#3E93D4";
    var title = "Skillset Description";
    var text1 = data.substring(0,40);  
    var text2 = data.substring(40,80);  
    var text3 = data.substring(80,120);  
    var text4 = data.substring(120,160);  
    var text5 = data.substring(160,200);  
    
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+text1+"<br />"+text2+"<br />"+text3+"<br />"+text4+"<br />"+text5+"<br />\
    </body></html>";
    // "+text4+"<br /
    // alert('content is ---='+content);
    
    //Create the popup       
        popup = window.open("","window","channelmode=0,width=350,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    
    //window.open('jex5.htm','jav','width=300,height=200,resizable=yes'); 
    //document.write("a HREF='' onmouseover='"+populate+"("+resolveFields(data)+");' onmouseout='closepopUpWindow();'");
    
}

function closepopUpWindow(){
    popup.close();
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

function ParseAndGenerateHTML(oTable,responseString,headerFields,rowCount) {
    var start = new Date();
    var fieldDelimiter = "|";
    var recordDelimiter = "^";
    
    // alert('responseString*******'+responseString);
    var records = responseString.split(recordDelimiter);
    //	Generate the HTML Code for the Table Creation using HeaderFields & Records
    generateTable(oTable,headerFields,records,fieldDelimiter);
    
    //oTable.row.cell[0].innerHTML = "<a href='javascript:return true;' onclick='saverow(this); return false;'>save</a>";
    
    //oTable.row[1].cell[0].innerHTML = "<a href='javascript:return true;' onclick='getData(); return false;'></a>";
    
    //document.getElementById("table").innerHTML = strTableHTML;
    //var end = new Date();
    //spnFast.innerHTML = "This Search took " + (end.getTime()-start.getTime()) + " milliseconds.";
    
    // spnFast.innerHTML = "This Search took " + (end.getTime()-start.getTime()) + " milliseconds.";
    // spnFast.innerHTML = "This Search took " + rowCount + " milliseconds.";
}


function loadDiv(name) {
     //alert('hello>>>@@@ '+name);
    //document.location ="getConsultant.action?email1="+name;
   // document.location ="getDetailsOfConsultant.action?email1="+name;
    document.location ="getConsultant.action?empId="+name;
    
    getDetails();
    }


