/********************************************
 Author : Praveen Kumar Mattaparthi
 Email : pmattaparthi@miraclesoft.com
 ********************************************/

var foo = "bar";
var monthEnd = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
var weekDays = new Array("sunday","monday","tuesday","wednesday","thursday","friday","saturday");
var mondayDate ='';
var tuesdayDate ='';
var wednesdayDate ='';
var thursdayDate ='';
var fridayDate ='';
var sundayDate ='';
var saturdayDate ='';
var dupWeekEndDate =''
var previousDay = '';

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


function readyStateHandler(req,responseHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("loadMessage").style.display = 'none';
                //document.getElementById("loadMessage2").style.display = 'none';
                responseHandler(req.responseXML);
            }
        } else {
            //alert("HTTP error ---"+req.status+" : "+req.statusText);
            //alert("please wait");
            document.getElementById("loadMessage").style.display = 'block';
            //document.getElementById("loadMessage2").style.display = 'block';
        }
    }
}


function getWeeklyEvents() {
    // alert("getWeeklyEvents()---- Weekly Events ");
    var eventDate = document.getElementById("eventDate").value;
    var actualDate = document.getElementById("actualEventDate");
    if(document.getElementById("salesTeamUser") != null) {
        calAccessUserId=document.getElementById("salesTeamUser").value;
    }else {
        calAccessUserId = '';
    }
    //alert("calAccessUserId---"+calAccessUserId);
    document.getElementById("eventDate").value = "";
    backtoWeeklyView();
    if(eventDate == "" || eventDate == null) {
        if(actualDate.value == "" || actualDate.value == null) {
            alert("Please Enter Date");
            return false;
        }else {
            eventDate = actualDate.value;
            clearTables();
            var weekButton = document.getElementById("weeklyViewBut");
            //        alert("Week button Function --Before---"+weekButton.onclick)
            weekButton.onclick = new Function("backtoWeeklyView()");
        }
        //        alert("Week button Function --After---"+weekButton.onclick)
    } else {
        clearTables();
    }
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, getWeekDates);
    var url = CONTENXT_PATH+"/weekDates.action?weekDate="+eventDate+"&currentCaluserId="+calAccessUserId+"&dummy="+new Date().getTime();
  // alert("weekly --"+url)
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function getWeekDates(responseXML) {
    //alert("got response---"+responseXML);
    var weekDates = responseXML.getElementsByTagName("WEEKDATES")[0];
    //alert("first tag--"+weekDates);
     if(document.getElementById("salesTeamUser") != null) {
        calAccessUserId=document.getElementById("salesTeamUser").value;
    }else {
        calAccessUserId = '';
    }
    var days = weekDates.getElementsByTagName("DAY");
    //alert("Days length---"+days.length);
    //alert((days[0]).firstChild.nodeValue)
    sundayDate = (days[0]).firstChild.nodeValue;
    mondayDate = (days[1]).firstChild.nodeValue;
    tuesdayDate = (days[2]).firstChild.nodeValue;
    wednesdayDate = (days[3]).firstChild.nodeValue;
    thursdayDate = (days[4]).firstChild.nodeValue;
    fridayDate = (days[5]).firstChild.nodeValue;
    saturdayDate = (days[6]).firstChild.nodeValue;
    dupWeekEndDate = (days[7]).firstChild.nodeValue;
    
    appendWeekDates(days);
    
    //alert("end date--"+dupWeekEndDate);
    //alert("duplicate week end---"+dupWeekEndDate);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, generateWeeklyEvents);
    //alert("new calll")
    var url = CONTENXT_PATH+"/weeklyEvents.action?weekStartDate="+sundayDate+"&weekEndDate="+dupWeekEndDate+"&currentCaluserId="+calAccessUserId+"&dummy="+new Date().getTime();
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}



function generateWeeklyEvents(responseXML) {
    //alert("got response---"+responseXML)
    var weeklyEvents = responseXML.getElementsByTagName("WEEKLYEVENTS")[0];
    var chk = weeklyEvents.getElementsByTagName("VALID")[0];
    if(chk.childNodes[0].nodeValue == "false" ) {
        alert(" No Events on this Week ");
    } else {
        
        for(loop = 0; loop < weeklyEvents.childNodes.length; loop++) {
            var event = weeklyEvents.childNodes[loop];
            var eventId = event.getElementsByTagName("EVENTID")[0];
            var eventType = event.getElementsByTagName("EVENTTYPE")[0];
            //alert("eventType--"+eventType);
            
            var description = event.getElementsByTagName("DESCRIPTION")[0]; 
            var createdByid = event.getElementsByTagName("CREATEDBYID")[0];
            var eventDate = event.getElementsByTagName("EVENTCREATEDDATE")[0];
            var eventEndDate=event.getElementsByTagName("EVENTENDDATE")[0];
            var modifiedById = event.getElementsByTagName("MODIFIEDBYID")[0];
            appendEvent(eventDate.childNodes[0].nodeValue,eventEndDate.childNodes[0].nodeValue,eventType.childNodes[0].nodeValue,description.childNodes[0].nodeValue,createdByid.childNodes[0].nodeValue,modifiedById.childNodes[0].nodeValue,eventId.childNodes[0].nodeValue);
        }
    }
    addFooter();
}

function appendEvent(eventDate,eventEndDate,eventType,description,createdByid,modifiedById,eventId) {    
    eventDateDup = eventDate.split(" ");
    eventEndDateDup=eventEndDate.split(" ");
    var formattedDate = formatEventDate(eventDateDup[0]);
    //alert("event Id is----"+eventId)
    var eventData = new Array(eventDateDup[1],eventEndDateDup[1],eventType,description,createdByid,modifiedById,eventId);
    if(formattedDate == sundayDate ){
        //alert("will add in sunday");
        var girdTableId = document.getElementById("sundayGridTable");
        previousDay = appendWeeklyEvent(eventData,girdTableId,weekDays[0])
    } else if(formattedDate == mondayDate) {
        //alert("will add in monday");
        var girdTableId = document.getElementById("mondayGridTable");
        previousDay = appendWeeklyEvent(eventData,girdTableId,weekDays[1])
    }  else if(formattedDate == tuesdayDate) {
        //alert("will add in tuesday")
        var girdTableId = document.getElementById("tuesdayGridTable");
        previousDay = appendWeeklyEvent(eventData,girdTableId,weekDays[2])
    }  else if(formattedDate == wednesdayDate) {
        //alert("will add in wednesday")
        var girdTableId = document.getElementById("wednesdayGridTable");
        previousDay = appendWeeklyEvent(eventData,girdTableId,weekDays[3])
        
    } else if(formattedDate == thursdayDate) {
        //alert("will add in thursday")
        var girdTableId = document.getElementById("thursdayGridTable");
        previousDay = appendWeeklyEvent(eventData,girdTableId,weekDays[4])
    } else if(formattedDate == fridayDate) {
        //alert("will add in friday")
        var girdTableId = document.getElementById("fridayGridTable");
        previousDay = appendWeeklyEvent(eventData,girdTableId,weekDays[5])
    } else if(formattedDate == saturdayDate) {
        //alert("will add in saturday")
        var girdTableId = document.getElementById("saturdayGridTable");
        previousDay = appendWeeklyEvent(eventData,girdTableId,weekDays[6]);
    }
}

function formatEventDate(eventDateDup) {
    dateArray = eventDateDup.split("-");
    return dateArray[1]+"/"+dateArray[2]+"/"+dateArray[0];
}

function appendWeeklyEvent(eventData,girdTableId,day) {
    var headerFields = new Array("Event Start Time","Event End Time","EventType","Description","Created By ","Modified By");
    //alert("previousDay is ---"+previousDay+"---Days is--"+day)
    if(previousDay != day ) {
        generateTableHeader(girdTableId,headerFields);
    }
    eventData = checkForData(eventData);
    generateRow(eventData,girdTableId);
    return day;
}


function generateTableHeader(girdTableId,headerFields) {
    var row;
    var cell;
    row = document.createElement("TR");
    row.className="gridHeader";
    girdTableId.appendChild(row);
    //alert("here");
    for (var i=0; i<headerFields.length; i++) {
        cell = document.createElement("TD");
        cell.className="gridHeader";
        row.appendChild(cell);
        cell.innerHTML = headerFields[i];
        //cell.width = 50;
    }
}


function generateRow(eventData,girdTableId) {
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    girdTableId.appendChild(row);
    //alert("in row")
    for (var index=0;index<eventData.length-1;index++) {
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild(cell);
        if(index == 0) {
            cell.style.cursor = 'pointer';
            //            if(currentActionValue == "" || currentActionValue == " " || currentActionValue == null) {
            cell.onclick = new Function("getEventDetails("+eventData[eventData.length-1]+")");
/*            } else {
                cell.onclick = new Function("alert('Sorry Access denied to Edit this event')");
            }*/
            //alert("id is ----"+eventData[eventData.length-1])
            //alert("getEventDetails("+eventData[eventData.length-1]+")");
            //alert("Data 1---"+eventData[index]);
            cell.innerHTML = eventData[index];
        } else {
            cell.innerHTML = eventData[index];
        }
    }
}

function clearTables() {
    
    var gridTable = new Array(document.getElementById("sundayGridTable"),document.getElementById("mondayGridTable"),document.getElementById("tuesdayGridTable"),document.getElementById("wednesdayGridTable"),document.getElementById("thursdayGridTable"),document.getElementById("fridayGridTable"),document.getElementById("saturdayGridTable"))
    var tableId = "";
    for(var index=0;index<gridTable.length;index++) {
        tableId = gridTable[index];
        //alert("gird table Name----"+tableId.id);
        var lastRow = tableId.rows.length;
        //alert("last row length ---"+lastRow)
        while (lastRow > 0) { 
            tableId.deleteRow(lastRow - 1);  
            lastRow = tableId.rows.length; 
        } 
    }
    previousDay="";
    var weekButton = document.getElementById("weeklyViewBut");
    weekButton.onclick = new Function("getWeeklyEvents()");
    //    alert("in clear table Week---"+weekButton.onclick)
    
}

function addFooter() {
    var gridTable = new Array(document.getElementById("sundayGridTable"),document.getElementById("mondayGridTable"),document.getElementById("tuesdayGridTable"),document.getElementById("wednesdayGridTable"),document.getElementById("thursdayGridTable"),document.getElementById("fridayGridTable"),document.getElementById("saturdayGridTable"))
    var tableId = "";
    var lastRow ='';
    var footer ='';
    for(var index=0;index<gridTable.length;index++) {
        tableId = gridTable[index];
        lastRow = tableId.rows.length;
        if(lastRow > 0){
            footer = document.createElement("TR");
            //alert("in footer")
            footer.className="gridPager";
            tableId.appendChild(footer);
            for(index1 = 0; index1<5;index1++) {
                cell = document.createElement("TD");
                cell.className="gridFooter";
                //alert("class Name---"+cell.className+"---Colspan---"+cell.colspan)
                cell.height = 8 ;
                footer.appendChild(cell);
            }
        }
    }
}

function appendWeekDates(days) {
    document.getElementById("sunday").innerHTML =  "<b>Sunday ----"+days[0].firstChild.nodeValue+"</b>";
    document.getElementById("monday").innerHTML = "<b>Monday----"+days[1].firstChild.nodeValue+"</b>";
    document.getElementById("tuesday").innerHTML = "<b>Tuesday----"+days[2].firstChild.nodeValue+"</b>";
    document.getElementById("wednesday").innerHTML = "<b>Wednesday----"+days[3].firstChild.nodeValue+"</b>";
    document.getElementById("thursday").innerHTML = "<b>Thursday----"+days[4].firstChild.nodeValue+"</b>";
    document.getElementById("friday").innerHTML = "<b>Friday----"+days[5].firstChild.nodeValue+"</b>";
    document.getElementById("saturday").innerHTML = "<b>Saturday----"+days[6].firstChild.nodeValue+"</b>";
}

