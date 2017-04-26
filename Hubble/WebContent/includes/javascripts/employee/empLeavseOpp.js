var totalLeavesAppled="";
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
    var year = document.getElementById("year").value;
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
    var month = document.getElementById("month").value;
    var year = document.getElementById("year").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, displayLeavesResult);
    var url = CONTENXT_PATH+"/getEmpLeavesOpp.action?month="+month+"&year="+year+"&dummy="+new Date().getTime();
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
    
    //alert("rowFeildsSplit"+rowFeildsSplit[rowFeildsSplit.length-5]);
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
        if(i==6) {
            totalLeaves = checkTotalLeaves(rowFeildsSplit[i],rowFeildsSplit[i+1]);
            cell.innerHTML = rowFeildsSplit[i];
            totalLeavesAppled = totalLeavesAppled+totalLeaves+",";
        }else if(i==rowFeildsSplit.length-1) {
            cell.innerHTML = totalLeaves;
        }else if(i==1){
            //cell.innerHTML = rowFeildsSplit[i];
            var anc = document.createElement("a");
           // anc.href = CONTENXT_PATH+"/employee/getLeaves.action?id="+rowFeildsSplit[rowFeildsSplit.length-1]+"&empName="+rowFeildsSplit[rowFeildsSplit.length-5];
             //alert("---------------->"+rowFeildsSplit[rowFeildsSplit.length-1]+"----"+ rowFeildsSplit[rowFeildsSplit.length-5]);
             //alert("idd--->"+rowFeildsSplit[rowFeildsSplit.length-1]);
             //alert("name--->"+rowFeildsSplit[rowFeildsSplit.length-8]);
          anc.setAttribute("href", "javascript:getLeaveDetails('"+rowFeildsSplit[rowFeildsSplit.length-1]+"' ,'"+rowFeildsSplit[rowFeildsSplit.length-8]+"')");
            anc.appendChild(document.createTextNode(rowFeildsSplit[i]));
            cell.appendChild(anc);
        }else if(i==5){
            var anc = document.createElement("a");
            //anc.setAttribute("href", "javascript:getReason('"+rowFeildsSplit[5]+"')");
            anc.setAttribute("href", "javascript:getReason('"+rowFeildsSplit[5].replace(/'/g, "\\'")+"')");
            anc.appendChild(document.createTextNode("Click Here"));
            cell.appendChild(anc);
        }else {
            cell.innerHTML = rowFeildsSplit[i];
        }
        cell.width = 120;
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
//---------------------------


/**
 * date diff caliculation funcations 
 * NAgireddy s
 * date : 12 jun 2012
 */

function calculate(frmdt,todt)
{
        
	//alert("d1----------"+form.date1.value);
	//var date1 = new Date(form.date1.value);
	//var date2 = new Date(form.date2.value);
        var date1 = new Date(frmdt);
        var date2 = new Date(todt);
	//alert("date1::::"+date1+"date2::::"+date2)
	var sec = date2.getTime() - date1.getTime();
	if (isNaN(sec))
	{
		alert("Input data is incorrect!");
		return;
	}
	if (sec < 0)
	{
		alert("The second date ocurred earlier than the first one!");
		return;
	}

	var second = 1000, minute = 60 * second, hour = 60 * minute, day = 24 * hour;

	//form.result_h.value = trunc(sec / hour);
	//form.result_m.value = trunc(sec / minute);
	//form.result_s.value = trunc(sec / second);

	var days = Math.floor(sec / day);
	sec -= days * day;
	var hours = Math.floor(sec / hour);
	sec -= hours * hour;
	var minutes = Math.floor(sec / minute);
	sec -= minutes * minute;
	var seconds = Math.floor(sec / second);
	//form.result.value = days + " day" + (days != 1 ? "s" : "") + ", " + hours + " hour" + (hours != 1 ? "s" : "") + ", " + minutes + " minute" + (minutes != 1 ? "s" : "") + ", " + seconds + " second" + (seconds != 1 ? "s" : "");
	var res = days + " day" + (days != 1 ? "s" : "") + ", " + hours + " hour" + (hours != 1 ? "s" : "") + ", " + minutes + " minute" + (minutes != 1 ? "s" : "") + ", " + seconds + " second" + (seconds != 1 ? "s" : "");
        
	//alert("dayes-- res-->"+days);

            res=days+1;
        return res;


}

function p (i)
{
	return Math.floor(i / 10) + "" + i % 10;
}

function init ()
{
	var form = document.getElementById('form');
	var date = new Date();
	var s = p(date.getMonth() + 1) + "/" + p(date.getDate()) + "/" + date.getFullYear() + " " + p(date.getHours()) + ":" + p(date.getMinutes()) + ":" + p(date.getSeconds());
	if (form.date1.value == "")
		form.date1.value = s;
	if (form.date2.value == "")
		form.date2.value = s;
}

function trunc (i)
{
	var j = Math.round(i * 100);
	return Math.floor(j / 100) + (j % 100 > 0 ? "." + p(j % 100) : "");
}


//-------------------------
function displayLeavesResult(resText) {
     
    if(resText.length !=0 && resText!="no data"){
        var oTable = document.getElementById("tblUpdate");
        clearTable(oTable);
       // var headerFields = new Array("S.No","Employee Name","Reports To","Start Date(YY-MM-DD)","End Date(YY-MM-DD)","Leaves")
 //var headerFields = new Array("S.No","Employee Name","Reports To","Start Date(YY-MM-DD)","End Date(YY-MM-DD)","Status","Leaves")
  var headerFields = new Array("S.No","Employee Name","Reports To","Leave Type","Status","Reason","Start Date(YY-MM-DD)","End Date(YY-MM-DD)","Leaves")
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
        generateTableHeader(tbody,headerFields);
       
        var resTextSplit1 = resText.split("*@!");
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#^$");
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
    cell.colSpan = "9";
    //cell.width = 100;
    cell.align="right";
    totalLeavesApp = totalLeavesAppled.split(",");
    for(i=0;i<totalLeavesApp.length-1;i++) {
        totalVal = totalVal+parseInt(totalLeavesApp[i]);
    }
    cell.innerHTML = "Total Leaves Applied : "+totalVal;
    row.appendChild(cell);
}

Date.prototype.getWeek = function() {
    var onejan = new Date(this.getFullYear(),0,1);
    return Math.ceil((((this - onejan) / 86400000) + onejan.getDay()+1)/7);
} 


/**
 *   Nagireddy Seerapu
 *   Date: 22 Nov 2011
 */


function loadByDate(e) {
  // alert("hi");
    totalLeavesAppled="";
    var startdate = document.getElementById("startdate").value;
    var enddate = document.getElementById("enddate").value;
    var country = document.getElementById("country").value;
     var opsContactId = document.getElementById("opsContactId").value;
    if(country == -1)
    {
      alert("please select country");
      return false;
    }
    if(startdate.length == 0)
    {
     alert("Please Enter Start Date");
     return false;
    }
    else if(enddate.length == 0)
    {
     alert("Please Enter End Date");     
      return false;
    }  
    

     
   var checkResult = compareDates(startdate,enddate);
  // alert(checkResult);
    if(!checkResult) {
        return false;
    }

    //else
   // {
      // alert("out if");
        var tableId = document.getElementById("tblUpdate1");
        clearTable(tableId);
        getTotalLeavesByDate();
    //}
  // alert("country:"+country);
    //getTotalLeavesByDate();
}

function getTotalLeavesByDate() {
  
    var fname = document.getElementById("fname").value;
    var lname = document.getElementById("lname").value;
    var startdate = document.getElementById("startdate").value;
    var enddate = document.getElementById("enddate").value;
    var country = document.getElementById("country").value;
    var opsContactId = document.getElementById("opsContactId").value;

    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler1(req, displayLeavesResult1);
    //var url = CONTENXT_PATH+"/getEmpLeavesOppByDate.action?month="+month+"&year="+year+"&dummy="+new Date().getTime();
    // alert("coming");
   // alert(fname+"   "+lname+"  "+startdate+"  "+enddate+"  "+country);
   
   //  var url = "Hubble/getEmpLeavesOppByDate.action?fname="+fname+"&lname="+lname+"&country="+country+"&startdate="+startdate+"&enddate="+enddate;
    var url = "Hubble/getEmpLeavesOppByDate.action?fname="+fname+"&lname="+lname+"&country="+country+"&startdate="+startdate+"&enddate="+enddate+"&opsContactId="+opsContactId;

    
  
    
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function displayLeavesResult1(resText) {
     
    if(resText.length !=0 && resText!="no data"){
        var oTable = document.getElementById("tblUpdate1");
        clearTable(oTable);
        var headerFields = new Array("S.No","Employee Name","Reports To","Leave Type","Status","Reason","Start Date(YY-MM-DD)","End Date(YY-MM-DD)","Leaves")
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
        generateTableHeader(tbody,headerFields);
       
        var resTextSplit1 = resText.split("*@!");
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#^$");
            generateRow1(tbody,resTextSplit2,index);
        }
        generateFooter(tbody);
    }else {
        alert("No Records Found");
    }
}

function readyStateHandler1(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadingMessage1")).style.display = "none";
                responseTextHandler(req.responseText);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
            (document.getElementById("loadingMessage1")).style.display = "block";
        }
    }
}

/**
 * New changes leaves by date
 * To genarate Data in grid 
 */

function generateRow1(tableBody,rowFeildsSplit,index) {
   // alert("table row"+rowFeildsSplit);
    //alert("rowFeildsSplit"+rowFeildsSplit[rowFeildsSplit.length-5]);
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
        if(i==6) {
            totalLeaves = checkTotalLeaves(rowFeildsSplit[i],rowFeildsSplit[i+1]);
            cell.innerHTML = rowFeildsSplit[i];
            totalLeavesAppled = totalLeavesAppled+totalLeaves+",";
        }else if(i==rowFeildsSplit.length-1) {
            cell.innerHTML = totalLeaves;
        }else if(i==1){
            //cell.innerHTML = rowFeildsSplit[i];
            var anc = document.createElement("a");
           // anc.href = CONTENXT_PATH+"/employee/getLeaves.action?id="+rowFeildsSplit[rowFeildsSplit.length-1]+"&empName="+rowFeildsSplit[rowFeildsSplit.length-5];
             //alert("---------------->"+rowFeildsSplit[rowFeildsSplit.length-1]+"----"+ rowFeildsSplit[rowFeildsSplit.length-5]);
             //alert("idd--->"+rowFeildsSplit[rowFeildsSplit.length-1]);
             //alert("name--->"+rowFeildsSplit[rowFeildsSplit.length-8]);
          anc.setAttribute("href", "javascript:getLeaveDetails('"+rowFeildsSplit[rowFeildsSplit.length-1]+"' ,'"+rowFeildsSplit[rowFeildsSplit.length-8]+"')");
            anc.appendChild(document.createTextNode(rowFeildsSplit[i]));
            cell.appendChild(anc);
        }else if(i==5){
            var anc = document.createElement("a");
            //anc.setAttribute("href", "javascript:getReason('"+rowFeildsSplit[5]+"')");
            anc.setAttribute("href", "javascript:getReason('"+rowFeildsSplit[5].replace(/'/g, "\\'")+"')");
            anc.appendChild(document.createTextNode("Click Here"));
            cell.appendChild(anc);
        }else {
            cell.innerHTML = rowFeildsSplit[i];
        }
        cell.width = 120;
    }
}
