/********************************************
 Author : Praveen Kumar Mattaparthi
 Email : pmattaparthi@miraclesoft.com
 ********************************************/


/************** Check Wheather the action is from Access Calendar or individual Calendar ----Start *******************/

var currentActionValue = '';
var currentCaluserId = '';
var tempId= '';
function checkAccessCalendarAction() {
    var currentAction = document.getElementById("currentAction");
    //alert("currentAction is ---"+currentAction.value);
    currentActionValue = currentAction.value;
    if(currentAction.value == 'AccessCalendar') {
        document.getElementById("accessUserCal").style.display = 'block';
        document.getElementById("monthlyView").style.display = 'none';
    } else {
        document.getElementById("accessUserCal").style.display = 'none';
        getMonthlyEvents();
        setMonthYear();
        getDateInfo();
    }
}


function getAccessUserCalendar() {
    currentCaluserId = document.getElementById("salesTeamUser").value;
    //alert("current Calendar user  ----"+currentCaluserId);
    setMonthYear();
    getDateInfo();
    getMonthlyEvents();
}

/********************  Check Wheather the action is from Access Calendar or individual Calendar-----End  ************/

/************  Switdhing views form dialy,monthly and  weekly ----Start *********/ 

function displayTeamCalAdd() {
    var checkTeamCalAdd = document.getElementById("checkTeamCalAdd");
    if(checkTeamCalAdd.style.display == 'block') {
        checkTeamCalAdd.style.display = 'none';
        document.getElementById("teamCalAdd1").style.display = 'block';
        //document.getElementById("teamCalAdd2").style.display = 'block';
        document.getElementById("checkTeamAdd").checked = false;
    } 
}


function dontDisplayTeamCalAdd() {
    
    var teamCalAdd1 = document.getElementById("teamCalAdd1");
    if(teamCalAdd1.style.display == 'block') {
        teamCalAdd1.style.display = 'none';
        //document.getElementById("teamCalAdd2").style.display = 'none';
        document.getElementById("checkTeamCalAdd").style.display = 'block';
        document.getElementById("checkTeamAdd1").checked = false;
    } 
}


function addDailyCalendarEvent(event) {
    //alert("Adding")
    clear();
    var weekView = document.getElementById("displayWeeklyResult");
    weekView.style.display = 'none';
    var dailyView = document.getElementById("dailyView");
    dailyView.style.display = 'block';
    var editEvent = document.getElementById("editEvent");
    document.getElementById("eventEndDate").value=document.getElementById("dailySelectedDate").value;
    document.getElementById("eventAddDate").value=document.getElementById("dailySelectedDate").value;
    if(event.target == undefined) {
        timePosId = window.event.srcElement; 
    }else {
        timePosId = event.target; 
    }
    time = (timePosId.innerHTML).split(".");
    if(parseInt(time[0]) < 10) {
        hour = "0"+time[0];
    }else {
        hour = time[0];
    }
    document.getElementById("eventStartTime").value=hour+":00:00";;
    document.getElementById("eventEndTime").value= hour+":30:00";
    fireMyPopup("editEvent");
    editEvent.style.display = 'block';
    var monthlyView = document.getElementById("monthlyView");
    monthlyView.style.display = 'none';
}

function addCalendarEvent() {
    //alert("Adding")
    clear();
    var weekView = document.getElementById("displayWeeklyResult");
    weekView.style.display = 'none';
    var dailyView = document.getElementById("dailyView");
    dailyView.style.display = 'none';
    var editEvent = document.getElementById("editEvent");
    document.getElementById("eventEndDate").value=document.getElementById("dailySelectedDate").value;
    document.getElementById("eventAddDate").value=document.getElementById("dailySelectedDate").value;
    
    //document.getElementById("eventEndTime").value=;
    //document.getElementById("eventStartTime").value=;
    editEvent.style.display = 'block';
    
    var monthlyView = document.getElementById("monthlyView");
    monthlyView.style.display = 'none';
}


function getDailyview() {
    //alert("---Back To Daily View-- ")
    var weekView = document.getElementById("displayWeeklyResult");
    weekView.style.display = 'none';
    var dailyView = document.getElementById("dailyView");
    dailyView.style.display = 'block';
    var editEvent = document.getElementById("editEvent");
    editEvent.style.display = 'none';
    var monthlyView = document.getElementById("monthlyView");
    monthlyView.style.display = 'none';
}

function getMonthlyEvents() {
    var weekView = document.getElementById("displayWeeklyResult");
    weekView.style.display = 'none';
    var dailyView = document.getElementById("dailyView");
    dailyView.style.display = 'none';
    var editEvent = document.getElementById("editEvent");
    editEvent.style.display = 'none';
    var monthlyView = document.getElementById("monthlyView");
    monthlyView.style.display = 'block';
}

function backtoWeeklyView() {
    //alert("---Back To Weekly View ")
    var weekView = document.getElementById("displayWeeklyResult");
    weekView.style.display = 'block';
    var editEvent = document.getElementById("editEvent");
    editEvent.style.display = 'none';
    var dailyView = document.getElementById("dailyView");
    dailyView.style.display = 'none';
    var monthlyView = document.getElementById("monthlyView");
    monthlyView.style.display = 'none';
}


function getEventDetails(eventId) {
    //alert("----"+eventId)
    getEventView(eventId);
    var weekView = document.getElementById("displayWeeklyResult");
    weekView.style.display = 'none';
    var dailyView = document.getElementById("dailyView");
    dailyView.style.display = 'none';
    var editEvent = document.getElementById("editEvent");
    editEvent.style.display = 'block';
}

/************  Switdhing views form dialy,monthly and  weekly ----End *********/ 


/********** Validate Event Add ***************/
function validateEventAdd(eventDate,eventType) {
    //alert("in validate")
    if(eventDate==null || eventDate == "" || eventDate ==" " ) {
        //alert("in validate---false")
        return false;
    } else if(eventType == null || eventType=="" || eventType==" " || eventType=="-please select-") {
        //alert("in validate---false")
        return false;
    } else {
        return true;
    }
}


/********** Validate Event Add ***************/
/****** List Events for daily View Code ---Start *******/


function getDailyEvents() {
    //alert("getDailyEvents()---Daily View ")
    var eventDate = document.getElementById("dailyEventDate").value;
    var actualDate = document.getElementById("actualEventDate")
    //    actualDate.value = eventDate;
    clearDailyTable();
    if(eventDate == "" || eventDate== null) {
        //alert("Event Date--"+eventDate);
        eventDate = actualDate.value;
    }
    document.getElementById("dailySelectedDate").value = eventDate;
    document.getElementById("todayEventsId").innerHTML = "<b>Events List on Date --- "+eventDate+"</b>";
    //  alert("Date--"+eventDate);
    if(eventDate != "" || eventDate != null) {
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandler(req, generateDailyEvents);
        var url = CONTENXT_PATH+"/dailyEventsView.action?eventDate="+eventDate+"&currentCaluserId="+currentCaluserId+"&dummy="+new Date().getTime();;
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
        document.getElementById("dailyEventDate").value='';
    } else {
        alert("Please Select Date");
    }
}

function generateDailyEvents(responseXML) {
    //    alert("got response---"+responseXML)
    var dailyEvents = responseXML.getElementsByTagName("DAILYEVENTS")[0];
    var chk = dailyEvents.getElementsByTagName("VALID")[0];
    if(chk.childNodes[0].nodeValue == "false" ) {
        alert(" No Events on this Week ");
    } else {
        var headerFields = new Array("EventType","Description","Event Time","Created By","Modified By");
        var gridTableId  = document.getElementById("dailyViewTable");
        tempId="";
        //generateTableHeader(gridTableId,headerFields);
        for(loop = 0; loop < dailyEvents.childNodes.length; loop++) {
            var event = dailyEvents.childNodes[loop];
            var eventId = event.getElementsByTagName("EVENTID")[0];
            var eventType = event.getElementsByTagName("EVENTTYPE")[0];
            //alert("eventType--"+eventType);
            
            var description = event.getElementsByTagName("DESCRIPTION")[0]; 
            var createdByid = event.getElementsByTagName("CREATEDBYID")[0];
            var eventDate = event.getElementsByTagName("EVENTCREATEDDATE")[0]; 
            var eventEndDate = event.getElementsByTagName("EVENTENDDATE")[0]; 
            var modifiedById = event.getElementsByTagName("MODIFIEDBYID")[0];
            appendDailyEvent(eventType.childNodes[0].nodeValue,description.childNodes[0].nodeValue,createdByid.childNodes[0].nodeValue,eventDate.childNodes[0].nodeValue,eventEndDate.childNodes[0].nodeValue,modifiedById.childNodes[0].nodeValue,eventId.childNodes[0].nodeValue);
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

/*function appendDailyEvent(eventType,description,createdByid,eventDate,modifiedById,eventId) {
    var gridTableId  = document.getElementById("dailyViewTable");
    var eventDateDup = eventDate.split(" ");
    var eventData = new Array(eventType,description,eventDateDup[1],createdByid,modifiedById,eventId);
    eventData = checkForData(eventData);
    //alert("Id If Table---"+gridTableId)
    //alert("Id If Table---"+gridTableId)
    generateRow(eventData,gridTableId);
}*/

function appendDailyEvent(eventType,description,createdByid,eventDate,eventEndDate,modifiedById,eventId) {
    
    var dateTime = eventDate.split(" ");
    var time = dateTime[1];
    timeSplit = time.split(":");
    /*if(parseInt(timeSplit[0])%2 == 0) {
        for(var index=0;index<24;index=+2) {
     
        }
    }else {
        for(var index=1;index<24;index=+2) {
     
        }
    }*/
    var tdId = document.getElementById(timeSplit[0]);
    //alert((parseInt(timeSplit[1])+1)+"----"+parseInt(timeSplit[1]));
    if(parseInt(timeSplit[1]) < 30) {
        divId = document.getElementById(timeSplit[0]+"."+"1");
        /*if(tempId != "") {
            tempIdSplit = (tempId.id).split(".");
            alert("1--"+tempIdSplit[1])
            alert("again temp id ==="+tempId.id);
            if(tempIdSplit[1] == "1") {
                //tempNewDivId.style.float = "left";
                //tempNewDivId.style.textAlign = "left";
            }
        }*/
    } else {
        divId = document.getElementById(timeSplit[0]+"."+"2");
        //alert("Checking temp Id");
        //alert("temporary id ="+tempId+"----")
        /*if(tempId != "" ) {
            tempIdSplit = (tempId.id).split(".");
            // alert("1--"+tempIdSplit[1])
            //alert("again temp id ==="+tempId.id);
            if(tempIdSplit[1] == "2") {
/*                alert(tempNewDivId.id);
                alert(tempNewDivId.innerHTML);
                tempNewDivId.style.float = "left";
                tempNewDivId.style.textAlign = "left";
            }
        }*/
    }
    var newDiv = document.createElement("td");
    newDiv.id = eventId;
    newDiv.style.backgroundColor = "white";
    newDiv.className = "fieldLabelLeft";
    newDiv.style.fontSize ="10px"
    
    
    /*newDiv.style.float = "left";
    newDiv.style.textAlign = "left";*/
    //newDiv.style.backgroundColor ="yellow";
    newDiv.onclick = new Function("getEventDetailsPop('"+eventId+"','"+eventType+"','"+description+"','"+createdByid+"','"+eventDate+"','"+eventEndDate+"')");
    newDiv.innerHTML = eventType;
    divId.appendChild(newDiv);
    /*tempNewDivId= newDiv;
    tempId = divId;
    alert("Assigned tempId--"+tempId.id)
    alert("Assigned New tempId--"+tempNewDivId.id)*/
}


function getEventDetailsPop(eventId,eventType,description,createdByid,eventStartDate,eventEndDate) {
    //position = findMousePosition(eventId,window.event);
    //alert(event)
    fireMyPopup("editEvent")
    //  Assign the values to the respective fields
    var eventStartDateDummy = eventStartDate.split(" ");
    var eventEndDateDummy=eventEndDate.split(" ");
    document.getElementById("eventType").value = eventType;
    document.getElementById("eventEndDate").value=eventEndDateDummy[0];
    document.getElementById("eventAddDate").value=eventStartDateDummy[0];
    document.getElementById("eventEndTime").value=eventEndDateDummy[1];
    document.getElementById("eventStartTime").value=eventStartDateDummy[1];
    if(description=="no records")
        document.getElementById("description").value="";
    else 
        document.getElementById("description").value=description;
    //document.getElementById("description").value=desc.childNodes[0].nodeValue;
    document.getElementById("calEveId").value=eventId;
    document.getElementById("createdBy").value = createdByid;
    var editEvent = document.getElementById("editEvent");
    editEvent.style.display = 'block';
    
}
function clearDailyTable() {
    /*tableId = document.getElementById("dailyViewTable");
    //alert("gird table Name----"+tableId.id);
    var lastRow = tableId.rows.length;
    //alert("last row length ---"+lastRow)
    while (lastRow > 0) { 
        tableId.deleteRow(lastRow - 1);  
        lastRow = tableId.rows.length; 
    } */
    tabRowId = '';
    //alert("in clear daily table")
    for(var index=0;index<24;index++) {
        //alert("index is---"+index)
        tabRowId = index;
        if(index<10) {
            tabRowId = "0"+index;
        }
        tr1 = document.getElementById((tabRowId+".1"));
        //alert(tr1+"--"+tr1.id);
        cell1 = tr1.cells.length;
        
        while(cell1 > 0) {
            tr1.deleteCell(cell1 - 1);  
            cell1 = tr1.cells.length; 
        }
        tr1 = document.getElementById((tabRowId+".2"));
        //alert(tr1+"--"+tr1.id);
        cell1 = tr1.cells.length;
        
        while(cell1 > 0) {
            tr1.deleteCell(cell1 - 1);  
            cell1 = tr1.cells.length; 
        }
        //tr1.deleteCell(lastNameCell - 1);
    }
    var weekButton = document.getElementById("weeklyViewBut");
    weekButton.onclick = new Function("getWeeklyEvents()");
    //alert("in clear table Daily---"+weekButton.onclick)
    document.getElementById("todayEventsId").innerHTML = ''
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


function updateEventDetails(eventId) {
    //alert("in update Event Details ---"+eventId);
    if(document.getElementById("eventId").value == eventId) {
        //alert("both are same")
        save();
    }
}