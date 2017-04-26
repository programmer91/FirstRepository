var totalLeavesAppled="";
var vacationLeaves=0;
var comptimeLeaves=0;
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

function load(e) {
    totalLeavesAppled="";
    vacationLeaves=0;
    comptimeLeaves=0;
    var year = document.getElementById("year").value;
    if(year.length==0){
        alert("please specify year");
        return false;
    } 
  if(year.length==4) {
        var tableId = document.getElementById("tblUpdate");
        clearTable(tableId);
        getTotalLeaves();
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

function getTotalLeaves() {
    var employeeId = document.getElementById("employee").value;
    var year = document.getElementById("year").value;
     if(employeeId=="-1"){
        alert("Please Select the Employee");
        return false;
    }
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, displayLeavesResult);
    var url = CONTENXT_PATH+"/getTotalLeaves.action?employeeId="+employeeId+"&year="+year+"&dummy="+new Date().getTime();
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
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
        //cell.width = 120;
    }
}

function generateRow(tableBody,rowFeildsSplit,index) {
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
    for (var i=1; i<=rowFeildsSplit.length; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild(cell);
        if(i==1){
            //alert(rowFeildsSplit[i].substring(0,rowFeildsSplit[i].indexOf("@")));
            cell.innerHTML = rowFeildsSplit[i].substring(0,rowFeildsSplit[i].indexOf("@"));
            //alert(rowFeildsSplit[i].substring(rowFeildsSplit[i].lastIndexOf("@")));
            if(rowFeildsSplit[i].substring(rowFeildsSplit[i].lastIndexOf("@"))=="@Comptime")
                comptimeLeaves=comptimeLeaves+checkTotalLeaves(rowFeildsSplit[i+2],rowFeildsSplit[i+3]);
            if(rowFeildsSplit[i].substring(rowFeildsSplit[i].lastIndexOf("@"))=="@Vacation")
                vacationLeaves=vacationLeaves+checkTotalLeaves(rowFeildsSplit[i+2],rowFeildsSplit[i+3]);    
        }
        else if(i==3) {
            totalLeaves = checkTotalLeaves(rowFeildsSplit[i],rowFeildsSplit[i+1]);
            //alert(totalLeaves);
            cell.innerHTML = rowFeildsSplit[i];
            totalLeavesAppled = totalLeavesAppled+totalLeaves+",";
        }else if(i==rowFeildsSplit.length) {
            cell.innerHTML = totalLeaves;
            cell.setAttribute("align", "right");
        }else {
            cell.innerHTML = rowFeildsSplit[i];
        }
        if(i==3 || i==4){
             cell.setAttribute("align", "right");
        }
        cell.width = 120;
        //alert(vacationLeaves+"hi"+comptimeLeaves);
    }
}

function checkTotalLeaves(startDateVal,endDateVal) {   
   // startDateVal="2011-08-01 18:31:36.0";
    //endDateVal="2011-08-04 18:31:36.0";
    //alert("startDateVal"+startDateVal);
    //alert("endDateVal"+endDateVal);
    var iWeeks, iDateDiff, iAdjust = 0;
    var fromDateSplit1 = startDateVal.split(" ");  // Date,mm,Year
    var toDateSplit1 = endDateVal.split(" ");
    var fromDateSplit =  fromDateSplit1[0].split("-");
    var toDateSplit =  toDateSplit1[0].split("-");
    var startDate = new Date(fromDateSplit[0],fromDateSplit[1]-1,fromDateSplit[2]);
    var endDate = new Date(toDateSplit[0],toDateSplit[1]-1,toDateSplit[2]);
    //alert(startDate+"startDate");
    //alert(endDate+"endDate");
    // if (endDate < startDate) return -1; // error code if dates transposed
    var iWeekday1 = startDate.getDay(); // day of week
    var iWeekday2 = endDate.getDay();
    
  //  alert("iWeekday1:"+iWeekday1);
  //  alert("iWeekday2:"+iWeekday2);
    iWeekday1 = (iWeekday1 == 0) ? 6 : iWeekday1; //change Sunday from 0 to 7
    iWeekday2 = (iWeekday2 == 0) ? 6 : iWeekday2;
    if ((iWeekday1 > 5) || (iWeekday2 > 5)) {
     //alert("I am in IF");
        iAdjust = 1;
    }//bth days on weekend
    iWeekday1 = (iWeekday1 > 5) ? 5 : iWeekday1; // only count weekdays
    iWeekday2 = (iWeekday2 > 5) ? 5 : iWeekday2;
    // differnece in weeks (1000mS * 60sec * 60min * 24hrs * 7 days = 604800000)
    iWeeks = Math.floor((endDate.getTime() - startDate.getTime()) / 604800000)
    
    if (iWeekday1 <= iWeekday2) {
        iDateDiff = (iWeeks * 5) + (iWeekday2 - iWeekday1)
    } else {
        iDateDiff = ((iWeeks + 1) * 5) - (iWeekday1 - iWeekday2)
    }
    
    iDateDiff = iDateDiff - iAdjust // take into account both days on weekend
    
    return (iDateDiff + 1); // add 1 because dates are inclusive
}



function getDatesDifference(fromDate,t0Date) {
    var one_day=1000*60*60*24;
    dateDifference = Math.ceil((t0Date.getTime()-fromDate.getTime())/(one_day));
    // alert("dateDifference --"+dateDifference);
    return dateDifference;
}

function displayLeavesResult(resText) {
    if(resText.length !=0 && resText!="no data"){
        var oTable = document.getElementById("tblUpdate");
        var headerFields = new Array("S.No","Employee Name","Reports To","Start Date(YY-MM-DD)","End Date(YY-MM-DD)","Leaves")
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
        generateTableHeader(tbody,headerFields);
        var resTextSplit1 = resText.split("!");
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("|");
            generateRow(tbody,resTextSplit2,index);
        }
        generateFooter(tbody);
    }else {
        alert("No Records Found");
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
    cell.colSpan = "2";
    //cell.width = 100;
    //cell.align="right";
    totalLeavesApp = totalLeavesAppled.split(",");
    for(i=0;i<totalLeavesApp.length-1;i++) {
        totalVal = totalVal+parseInt(totalLeavesApp[i]);
    }
    cell.innerHTML = "Total Leaves Applied : "+totalVal;
    row.appendChild(cell);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.colSpan = "2";
    cell.innerHTML = "Vacation Leaves : "+vacationLeaves;
    row.appendChild(cell);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.colSpan = "2";
    cell.innerHTML = "Comptime Leaves : "+comptimeLeaves;
    row.appendChild(cell);
}

Date.prototype.getWeek = function() {
    var onejan = new Date(this.getFullYear(),0,1);
    return Math.ceil((((this - onejan) / 86400000) + onejan.getDay()+1)/7);
} 

