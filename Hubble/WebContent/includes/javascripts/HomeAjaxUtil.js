// This is a javascript file

/********************************************
 Author : Hari Krishna Kondala
 Email  : hkondala@miraclesoft.com
 *********************************************/


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
                responseTextHandler(req.responseXML);
            } else {
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }
    }
}

var months = new Array("January","February","March","April","May","June","July","August","September","October","November","December");
var weekDay = new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday");
var tDays = 0;
var mDays = 0;
var wDays = 0;

function getTotalUsableTeamHours() {
    var y = new Date().getYear();
    if (y < 2000) y += 1900;
    var m = new Date().getMonth();
    var d = new Date().getDate();
    m = m+1;
    //alert('Year--'+y+' Month---'+m+' Day----'+d);
    
    for(month=1;month<=m;month++) {
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            mDays = 31;
            tDays = tDays+mDays;
        }else if(month == 2) {
            if(y % 4 == 0) {
                mDays = 29;
            }else mDays = 28;
            tDays = tDays+mDays;
        }else if(month == 4 || month == 6 || month == 9 || month == 11) {
            mDays = 30;
            tDays = tDays+mDays;
        }
    }
    //alert('Total Days---'+tDays);
    for(month=0;month<m;month++) {
        if(month == 0 || month == 2 || month == 4 || month == 6 || month == 7 || month == 9 || month == 11){
            for(i=1;i<=31;i++) {
                var c = new Date(y,month,i);
                var dayOfWeek = c.getDay();
                var myday = weekDay[dayOfWeek];
                if(myday == 'Saturday' || myday == 'Sunday') {
                    wDays = wDays+1;
                    //alert(wDays);
                }
            }
        }else if(month == 1) {
            if(y % 4 == 0) {
                for(i=1;i<=29;i++) {
                    var c = new Date(y,month,i);
                    var dayOfWeek = c.getDay();
                    var myday = weekDay[dayOfWeek];
                    if(myday == 'Saturday' || myday == 'Sunday') {
                        wDays = wDays+1;
                        //alert(wDays);
                    }
                }
            }else {
                for(i=1;i<=28;i++) {
                    var c = new Date(y,month,i);
                    var dayOfWeek = c.getDay();
                    var myday = weekDay[dayOfWeek];
                    if(myday == 'Saturday' || myday == 'Sunday') {
                        wDays = wDays+1;
                        //alert(wDays);
                    }
                }
            }
        }
        else if(month == 3 || month == 5 || month == 8 || month == 10) {
            for(i=1;i<=30;i++) {
                var c = new Date(y,month,i);
                var dayOfWeek = c.getDay();
                var myday = weekDay[dayOfWeek];
                if(myday == 'Saturday' || myday == 'Sunday') {
                    wDays = wDays+1;
                    //alert(wDays);
                }
            }
        }
        //alert('Weekend Days---'+wDays);
    }
    //alert('Total Working Days---'+(tDays-wDays));
    //alert('Total Usable hours excluding Weekends---'+(tDays-wDays)*8);
    var uDays = tDays-wDays;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,populate);
    var url=CONTENXT_PATH+"/getTotalUsableHours.action?year="+y+"&month="+m+"&usableDays="+uDays+"&dummy="+new Date().getTime();
    //alert(url);
    req.open("GET",url,true);
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populate(resXML) {
    var valid = resXML.getElementsByTagName("valid")[0];
    var totalHours = valid.childNodes[0].nodeValue;
    totalHoursID = document.getElementById("totalHours");
    totalHoursID.innerHTML = totalHours;
    //alert('Holidays---'+holidays);
}
var date;
var year = new Date().getYear();
var month = new Date().getMonth();
var day = new Date().getDate();
function getCurrentEvents() {
    if (year < 2000) year += 1900;
    if(month<10)
        month = '0'+month;
    if(day<10)
        day = '0'+day;
    date = month+'/'+day+'/'+year;
    var cal = '';
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,displayEvents);
    var url = CONTENXT_PATH+"/dailyEventsView.action?eventDate="+date+"&currentCaluserId="+cal+"&dummy="+new Date().getTime();
    //alert(url);
    req.open("GET",url,true);
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function displayEvents(responseXML) {
    var dailyEvents = responseXML.getElementsByTagName("DAILYEVENTS")[0];
    var chk = dailyEvents.getElementsByTagName("VALID")[0];
    if(chk.childNodes[0].nodeValue == "false" ) {
        alert(" No Events on this Day ");
        //document.getElementById("dailyView").style.display = 'none';
        //document.getElementById("editEvent").style.display = 'block';
        //clear();
        //var d = new Date();
        //document.addEventForm.eventAddDate.value = actualDate.value+" "+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
        //document.addEventForm.eventEndDate.value = actualDate.value+" "+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
    } else {
        //   var headerFields = new Array("EventType","Description","Event Time","Created By","Modified By");
        var headerFields = new Array("Date","Start Time","End Time","Activity Type","Description","Requested By","Status");
        var gridTableId  = document.getElementById("eventsList");
        generateTableHeader(gridTableId,headerFields);
        for(loop = 0; loop < dailyEvents.childNodes.length; loop++) {
            var event = dailyEvents.childNodes[loop];
            var eventId = event.getElementsByTagName("EVENTID")[0];
            var eventType = event.getElementsByTagName("EVENTTYPE")[0];
            //alert("eventType--"+eventType);
            
            var description = event.getElementsByTagName("DESCRIPTION")[0]; 
            var createdByid = event.getElementsByTagName("CREATEDBYID")[0];
            var requestedBy = createdByid.childNodes[0].nodeValue;
            if(document.getElementById("uName").value == requestedBy) {
                requestedBy = 'Self';
            }
            var eventDate = event.getElementsByTagName("EVENTCREATEDDATE")[0]; 
            var eventEndDate=event.getElementsByTagName("EVENTENDDATE")[0]; 
            if(eventEndDate.childNodes[0].nodeValue=="no records") {
                // alert('hi');
                var eventEndDate=" ";
            }
            else {
                var eventEndDate=eventEndDate.childNodes[0].nodeValue;
            }
            var modifiedById = event.getElementsByTagName("MODIFIEDBYID")[0];
            appendDailyEvent(eventDate.childNodes[0].nodeValue,eventEndDate,eventType.childNodes[0].nodeValue,description.childNodes[0].nodeValue,requestedBy,'Accepted',eventId.childNodes[0].nodeValue);
            /*if(loop == dailyEvents.childNodes.length-1) {
                var footer =document.createElement("TR");
                footer.className="gridPager";
                gridTableId.appendChild(footer);
                cell = document.createElement("TD");
                cell.className="gridFooter";
                cell.height = 8;
                footer.appendChild(cell);
            }*/
        }
    }
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

function appendDailyEvent(eventDate,eventEndDate,eventType,description,createdByid,modifiedById,eventId) {
    var gridTableId  = document.getElementById("eventsList");
    var eventDateDup = eventDate.split(" ");
    var eventEndDateDup=eventEndDate.split(" ");
    var month1 = new Date().getMonth();
    if(month1<10)
        month1 = '0'+month1;
    //alert(month1);
    month1 = checkStrMonth(month1);
    date = day+'-'+month1+'-'+year;
    var eventData = new Array(date,eventDateDup[1],eventEndDateDup[1],eventType,description,createdByid,modifiedById,eventId);
    eventData = checkForData(eventData);
    //alert("Id If Table---"+gridTableId)
    //alert("Id If Table---"+gridTableId)
    generateRow(eventData,gridTableId);
}


function checkForData(eventData) {
    var tempData = '';
    for(var index = 0;index<eventData.length;index++ ) {
        tempData = eventData[index];
        if(tempData == 'no records') {  // Dont Change this 'no records' If you do please change this in corresponding Method in ajax Handle Class
            eventData[index] = "";
        }
    }
    return eventData;
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
        cell.innerHTML = eventData[index];
    }
}


