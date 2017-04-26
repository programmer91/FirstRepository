/********************************************
 Author : Praveen Kumar Mattaparthi
 Email : pmattaparthi@miraclesoft.com
 ********************************************/


/************** Check Wheather the action is from Access Calendar or individual Calendar ----Start *******************/

var currentActionValue = '';
var currentCaluserId = '';

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
    //alert('hi')
    if(document.getElementById("salesTeamUser") != null) {
        calAccessUserId=document.getElementById("salesTeamUser").value;
        currentCaluserId = calAccessUserId;
        //alert(calAccessUserId)
    }else {
        calAccessUserId = '';
    }
    // alert("current Calendar user  ----"+currentCaluserId);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,populateCalendar);    
    var url = CONTENXT_PATH+"/populateCalendarAccess.action?currentCaluserId="+calAccessUserId+"&dummy="+new Date().getTime();    //dummy="+new Date().getTime();
    //alert("populate Callendar---"+url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populateCalendar(resXML){
    var accessId=resXML.getElementsByTagName("MEMBERS");
    // alert(accessId.length);
    for(var i=0;i<accessId.length; i++){
        var access=accessId[i];
        //        alert("Access Type --"+access.firstChild.nodeValue)
        var accessType=access.getElementsByTagName("MEMBER-ACCESS")[0].firstChild.nodeValue;
        //alert(accessType);
    }
    if(accessType=="RW"){
        //alert('both aceeses');
        document.getElementById("access").style.display = 'block';
        document.getElementById("access1").style.display = 'block';
    }
    else {
        //alert('only Read access')
        
        document.getElementById("access1").style.display ='none';
        document.getElementById("access").style.display = 'none';
    }
    setMonthYear();
    getDateInfo();
    getMonthlyEvents();
}

function getAccessUserCalendar1() {
    //alert('hi')
    if(document.getElementById("salesTeamUser") != null) {
        calAccessUserId=document.getElementById("salesTeamUser").value;
        currentCaluserId = calAccessUserId;
        //alert(calAccessUserId)
    }else {
        calAccessUserId = '';
    }
    // alert("current Calendar user  ----"+currentCaluserId);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,populateCalendar1);    
    var url = CONTENXT_PATH+"/populateCalendarAccess.action?currentCaluserId="+calAccessUserId+"&dummy="+new Date().getTime();    //dummy="+new Date().getTime();
    //alert("populate Callendar---"+url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populateCalendar1(resXML){
    var accessId=resXML.getElementsByTagName("MEMBERS");
    // alert(accessId.length);
    for(var i=0;i<accessId.length; i++){
        var access=accessId[i];
        //        alert("Access Type --"+access.firstChild.nodeValue)
        var accessType=access.getElementsByTagName("MEMBER-ACCESS")[0].firstChild.nodeValue;
        //alert(accessType);
    }
  //  if(accessType=="RW"){
        //alert('both aceeses');
        document.getElementById("access").style.display = 'block';
        document.getElementById("access1").style.display = 'block';
    //}
   // else {
        //alert('only Read access')
        
       // document.getElementById("access1").style.display ='none';
       // document.getElementById("access").style.display = 'none';
  //  }
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

function addCalendarEvent() {
    //alert("Adding")
    clear();
    var weekView = document.getElementById("displayWeeklyResult");
    weekView.style.display = 'none';
    var dailyView = document.getElementById("dailyView");
    dailyView.style.display = 'none';
    var editEvent = document.getElementById("editEvent");
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
function validateEventAdd(eventDate,eventType,eventEndDate) {
    //alert("in validate")
    if(eventDate==null || eventDate == "" || eventDate ==" " ) {
        //alert("in validate---false")
        return false;
    }else if(eventType == null || eventType=="" || eventType==" " || eventType=="-please select-") {
        //alert("in validate---false")
        return false;
    }else if(eventEndDate == null || eventEndDate=="" || eventEndDate==" ") {
        //alert("in validate---false")
        return false;
    }else {
        return true;
    }
}




/********** Validate Event Add ***************/
/****** List Events for daily View Code ---Start *******/

var actualDate = null;
function getDailyEvents() {
    //alert("getDailyEvents()---Daily View ")
    var eventDate = document.getElementById("dailyEventDate").value;
    actualDate = document.getElementById("actualEventDate");
    //    actualDate.value = eventDate;
    clearDailyTable();
    if(eventDate == "" || eventDate== null) {
        //alert("Event Date--"+eventDate);
        eventDate = actualDate.value;
    }
    
    document.getElementById("todayEventsId").innerHTML = "<b>Events List on Date --- "+eventDate+"</b>";
    //  alert("Date--"+eventDate);
    if(document.getElementById("salesTeamUser") != null) {
        calAccessUserId=document.getElementById("salesTeamUser").value;
    }else {
        calAccessUserId = '';
    }
    if(eventDate != "" || eventDate != null) {
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandler(req, generateDailyEvents);
        var url = CONTENXT_PATH+"/dailyEventsView.action?eventDate="+eventDate+"&currentCaluserId="+calAccessUserId+"&dummy="+new Date().getTime();;
        //alert("Daily--"+url)
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
        alert(" No Events on this Day ");
        document.getElementById("dailyView").style.display = 'none';
        document.getElementById("editEvent").style.display = 'block';
        clear();
        var d = new Date();
        document.addEventForm.eventAddDate.value = actualDate.value+" "+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
        //document.addEventForm.eventEndDate.value = actualDate.value+" "+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
    } else {
     //   var headerFields = new Array("EventType","Description","Event Time","Created By","Modified By");
           var headerFields = new Array("Event Start Time","Event End Time","EventType","Description","Created By","Modified By");
        var gridTableId  = document.getElementById("dailyViewTable");
        generateTableHeader(gridTableId,headerFields);
        for(loop = 0; loop < dailyEvents.childNodes.length; loop++) {
            var event = dailyEvents.childNodes[loop];
            var eventId = event.getElementsByTagName("EVENTID")[0];
            var eventType = event.getElementsByTagName("EVENTTYPE")[0];
            //alert("eventType--"+eventType);
            
            var description = event.getElementsByTagName("DESCRIPTION")[0]; 
            var createdByid = event.getElementsByTagName("CREATEDBYID")[0];
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
            appendDailyEvent(eventDate.childNodes[0].nodeValue,eventEndDate,eventType.childNodes[0].nodeValue,description.childNodes[0].nodeValue,createdByid.childNodes[0].nodeValue,modifiedById.childNodes[0].nodeValue,eventId.childNodes[0].nodeValue);
            if(loop == dailyEvents.childNodes.length-1) {
                var footer =document.createElement("TR");
                footer.className="gridPager";
                gridTableId.appendChild(footer);
                cell = document.createElement("TD");
                cell.className="gridFooter";
                cell.height = 8;
                footer.appendChild(cell);
            }
        }
    }
}

function appendDailyEvent(eventDate,eventEndDate,eventType,description,createdByid,modifiedById,eventId) {
    var gridTableId  = document.getElementById("dailyViewTable");
    var eventDateDup = eventDate.split(" ");
    var eventEndDateDup=eventEndDate.split(" ");
    var eventData = new Array(eventDateDup[1],eventEndDateDup[1],eventType,description,createdByid,modifiedById,eventId);
    eventData = checkForData(eventData);
    //alert("Id If Table---"+gridTableId)
    //alert("Id If Table---"+gridTableId)
    generateRow(eventData,gridTableId);
}


function clearDailyTable() {
    tableId = document.getElementById("dailyViewTable");
    //alert("gird table Name----"+tableId.id);
    var lastRow = tableId.rows.length;
    //alert("last row length ---"+lastRow)
    while (lastRow > 0) { 
        tableId.deleteRow(lastRow - 1);  
        lastRow = tableId.rows.length; 
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

