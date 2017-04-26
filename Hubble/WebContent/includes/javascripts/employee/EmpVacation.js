// This is a javascript file
var startDate="";
var endDate="";
var department="";
var organization="";
var date1 = new Date();

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


function readyStateHandler(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                responseTextHandler(req.responseText);
            } else {
                alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }
    }
}


function getVacationList(){
    var req = newXMLHttpRequest();
    startDate = document.frmSearch.startDate.value;
    
    if(startDate == '') {
        startDate="00/00/0000 00:00:00";
    }
    else {
        startDate = convert(startDate);
    }
    
    endDate = document.frmSearch.endDate.value;
    
    if(endDate == '') {
        endDate="00/00/0000 00:00:00";
    }
    else{
        endDate = convert(endDate);
    }
    
    department = document.frmSearch.Department.value;
    
    if(department == '') {
        department="no";
    }
    
    organization = document.frmSearch.organization.value;
    
    if(organization == '') {
        organization="no";
    }
    req.onreadystatechange = readyStateHandler(req,  populateRecords);
    var url = CONTENXT_PATH+"/getEmpVacationList.action?"+ "startDate="+startDate+"&endDate="+endDate+"&department="+department+"&organization="+organization;
    req.open("POST",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function populateRecords(totalData) {
    var str = totalData;
    if(str=='') {
        ClrTable();
        var  revenueSummery = document.getElementById('RevenueSummery');   
        var gridHbody = document.createElement("TBODY");
        var gridHtr  = document.createElement("tr");
        gridHtr.setAttribute("colspan","5");
        gridHtr.className="gridHeader";
        var gridHtd2 = document.createElement("td"); 
        gridHtd2.className="gridHeader";
        
        gridHtd2.setAttribute("width","20%");
        gridHtd2.setAttribute("ALIGN","left");
        gridHtd2.appendChild(document.createTextNode("No Records To Display ..."));  
        
        gridHtr.appendChild(gridHtd2);
        gridHbody.appendChild(gridHtr)
        revenueSummery.appendChild(gridHbody);
    }
    else{
        ClrTable();
        var  revenueSummery = document.getElementById('RevenueSummery');   
        var gridHbody = document.createElement("TBODY");
        var gridHtr  = document.createElement("tr");
        gridHtr.className="gridHeader";
        var gridHtd2 = document.createElement("td"); 
        gridHtd2.className="gridHeader";
        gridHtd2.setAttribute("width","20%");
        gridHtd2.setAttribute("ALIGN","left");
        gridHtd2.appendChild(document.createTextNode("StartDate"));  
        
        var gridHtd3 = document.createElement("td"); 
        gridHtd3.className="gridHeader";
        gridHtd3.setAttribute("width","20%");
        gridHtd3.setAttribute("ALIGN","left");
        gridHtd3.appendChild(document.createTextNode("EndDate"));  
        
        var gridHtd4 = document.createElement("td"); 
        gridHtd4.className="gridHeader";
        gridHtd4.setAttribute("width","40%");
        gridHtd4.setAttribute("ALIGN","center");
        gridHtd4.appendChild(document.createTextNode("Organization"));  
        
        
        var gridHtd5 = document.createElement("td"); 
        gridHtd5.className="gridHeader";
        gridHtd5.setAttribute("width","10%");
        gridHtd5.setAttribute("ALIGN","left");
        gridHtd5.appendChild(document.createTextNode("Department"));
        
        var gridHtd6 = document.createElement("td"); 
        gridHtd6.className="gridHeader";
        gridHtd6.setAttribute("width","20%");
        gridHtd6.setAttribute("ALIGN","left");
        gridHtd6.appendChild(document.createTextNode("EmployeeName"));
        
        gridHtr.appendChild(gridHtd2);
        gridHtr.appendChild(gridHtd3);
        gridHtr.appendChild(gridHtd4);
        gridHtr.appendChild(gridHtd5);
        gridHtr.appendChild(gridHtd6);
        gridHbody.appendChild(gridHtr)
        revenueSummery.appendChild(gridHbody);
        
        var caps = new Array();
        caps = str.split("@");
        
        var records = new Array();
        
        for(var i=0;i<caps.length;i++) {
            var gridHtr1  = document.createElement("tr");
            gridHtr1.className="gridRowEven";
            records = caps[i].split("!");
            
            for(var j=0; j<records.length-1;j++) {
                var gridHtdd1 = document.createElement("td"); 
                gridHtdd1.className="gridColumn";
                gridHtdd1.setAttribute("ALIGN","left");
                gridHtdd1.appendChild(document.createTextNode(records[j] ));
                gridHtr1.appendChild(gridHtdd1); 
                gridHbody.appendChild(gridHtr1);
                revenueSummery.appendChild(gridHbody);   
                
            } // 2nd for 
            
        } // End for
        
    }
    
    
    
} // PopulateRecords Function Over




function ClrTable() { 
    var tbl = document.getElementById('RevenueSummery'); 
    var lastRow = tbl.rows.length; 
    while (lastRow > 0) { tbl.deleteRow(lastRow - 1);  lastRow = tbl.rows.length; } 
    
}


function convert(str) {
    
    var dt = str.substring(0,2);
    
    var mn = str.substring(3,5);
    
    var year = str.substring(6,10);
    
    var time = str.substring(11,str.length);
    
    var sp=' ';
    var sqlDate = year+"-"+dt+"-"+mn+sp+time;
    return sqlDate;
}


