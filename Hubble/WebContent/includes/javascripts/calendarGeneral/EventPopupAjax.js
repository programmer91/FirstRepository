/*****************************************
 Author : Hari Krishna Kondala
 Email : hkondala@miraclesoft.com
 ********************************************/

// This is a javascript file
/*Don't Alter these Methods*/

var tempId = '';
function getData(data) {
    tempId = data;
    var month = document.eventForm.month.value;
    var months = parseInt(month);
    months = months+1;
    
    if(months<10) {
        months = '0'+months;
    }
    //if(day<10) {
    //    day = '0'+day;
    //}
    var year = document.eventForm.year.value;
    var eventDate = year+'-'+months;//+'-'+day;
    //alert("entered the url")
    var req = newXMLHttpRequest();
    if(document.getElementById("salesTeamUser") != null) {
        calAccessUserId=document.getElementById("salesTeamUser").value;
    }else {
        calAccessUserId = '';
    }
    req.onreadystatechange = readyStateHandler(req,populate);
    var url=CONTENXT_PATH+"/eventPopupWindow.action?eventYearMonth="+eventDate+"&currentCaluserId="+calAccessUserId+"&dummy="+new Date().getTime();
    req.open("GET",url,true);
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    getStatus(eventDate);
}

function populate(resXML) {
    
    var td = tempId.id;
    var year = resXML.getElementsByTagName("YEAR")[0];
    var eventType = year.getElementsByTagName("EVENTTYPE");
    var date =  resXML.getElementsByTagName("DATE");
    var tempdate1 = "";
    for (var i=0;i<eventType.length;i++) {
        //eventDesc = event[i].firstChild.nodeValue;
        eventType1 = eventType[i].firstChild.nodeValue;
        date1 = date[i].firstChild.nodeValue;
        //var dayPos = date1.indexOf("-",6);
        day = date1.substr(8,date1.length);
        days = parseInt(day);
        if(days == 0 ) {
            days = parseInt(date1.substr(9,date1.length));
        }
        tdVal = td.substr(4,td.length);
        tdVals = parseInt(tdVal);
        val = tdVals+days-1;
        newText = "text"+val;
        //div = document.createElement("div");
        //td = document.getElementById("td35");
        //anch = document.createElement("a");
        //anch.appendChild(document.createTextNode("Events Here"));
        //div.appendChild(anch);
        if(tempdate1 == date1) {
            document.eventForm[newText].value = document.eventForm[newText].value+", "+eventType1;
        }
        else{
            document.eventForm[newText].value = document.eventForm[newText].value+'  '+eventType1;
            document.eventForm[newText].style.backgroundColor="#D8D8D8";
            tempdate1 = date1;
        }
        //temp = document.eventForm[newText];
        //alert("temp val is====="+temp.value);
        //temp.parentNode.appendChild(div);
        
    }
}

function doAdd(data) {
    //var eventType = document.eventForm.eventType.value;
    var months = document.eventForm.month.value;
    var year = document.eventForm.year.value;
    var days = data.value;
    var newDay = days.substr(0,2);
    var month = parseInt(months);
    month = month+1;
    if(month<10) {
        month = '0'+month;
    }
    if(newDay<10) {
        newDay = newDay.substr(0,1);
        newDay = '0'+newDay;
    }
    
    var actualDate = document.getElementById("actualEventDate");
    actualDate.value = month+"/"+newDay+"/"+year;
    if(days != 0) {
        document.getElementById("monthlyView").style.display = 'none';
        document.getElementById("dailyView").style.display = 'block';
        
        getDailyEvents();
        //document.dailyView.dailyEventDate.value = month+"/"+newDay+"/"+year;
    }
}

function getCal() {
    document.getElementById("monthlyView").style.display = 'block'; 
    document.getElementById("dailyView").style.display = 'none'; 
}

//For Calendar Colors

function dayStatus() {
    document.getElementById("status").value = "-please select-";
    document.getElementById("sdate").value = "";
    document.getElementById("popupAddStatus").style.display = 'block'; 
}

function saveStatus() {
    var status = document.getElementById("status").value;
    var sdate = document.getElementById("sdate").value;
    if(status == '-please select-' && sdate == '') {
        alert('Please Enter Date & Status');
        return false
    }
    if(status == '-please select-' && sdate != '') {
        alert('Please Enter Status');
        return false
    }
    if(status != '-please select-' && sdate == '') {
        alert('Please Enter Date');
        return false
    }
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1;//January is 0!
    var yyyy = today.getFullYear();
    if(dd<10){dd='0'+dd}
    if(mm<10){mm='0'+mm}
    var date=mm+'/'+dd+'/'+yyyy;
    
    var mm1 = sdate.substring(0,2);
    var dd1 = sdate.substring(3,5);
    var yyyy1 = sdate.substring(6,10);
    if(yyyy1 < yyyy) {
        alert('Date is older than Current Date');
        return false;
    }
    else if((yyyy1 == yyyy) && (mm1 < mm)) {
        alert('Date is older than Current Date');
        return false;
    }
    else if((yyyy1 == yyyy) && (mm1 == mm) && (dd1 < dd)) {
        alert('Date is older than Current Date');
        return false;
    }
    /*var myDay = new Date(yyyy1,mm1,dd1);
    if(myDay.getTime() < today.getTime()) {
        alert('Date is older than Current Date');
    }*/
    else {
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandler(req,popResult);
        var url=CONTENXT_PATH+"/addStatus.action?status="+status+"&sdate="+sdate+"&dummy="+new Date().getTime();
        req.open("GET",url,true);
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
        document.getElementById("popupAddStatus").style.display = 'none';
    }
}

function popResult(resXML) {
    var valid = resXML.getElementsByTagName("valid")[0];
    var chk = valid.childNodes[0].nodeValue;
    if(chk ==1) {
        alert("Status is successfully saved");
        getMonthlyEvents();
        setMonthYear();
        getDateInfo();
    }
    else if(chk == 5) {
        alert("You have no choice to Reomve!!");
        document.getElementById("popupAddStatus").style.display = 'block';
    }
    else if(chk == 2) {
        alert("Status is successfully updated.");
        getMonthlyEvents();
        setMonthYear();
        getDateInfo();
    }
    else {
        alert("Status is not Saved, Please Try Again!");
        getMonthlyEvents();
        setMonthYear();
        getDateInfo();
    }
}

function getStatus(date) {
    var req = newXMLHttpRequest();
    if(document.getElementById("salesTeamUser") != null) {
        calAccessUserId=document.getElementById("salesTeamUser").value;
    }else {
        calAccessUserId = '';
    }
    req.onreadystatechange = readyStateHandler(req,populateStatus);
    var url=CONTENXT_PATH+"/getStatus.action?eventYearMonth="+date+"&currentCaluserId="+calAccessUserId+"&dummy="+new Date().getTime();
    req.open("GET",url,true);
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    //alert(date);
}

function populateStatus(resXML) {
    var td = tempId.id;
    var year = resXML.getElementsByTagName("YEAR")[0];
    var date =  resXML.getElementsByTagName("DATE");
    var status = year.getElementsByTagName("STATUS");
    
    for (var i=0;i<status.length;i++) {
        date1 = date[i].firstChild.nodeValue;
        status1 = status[i].firstChild.nodeValue;
        
        day = date1.substr(8,date1.length);
        days = parseInt(day);
        if(days == 0 ) {
            days = parseInt(date1.substr(9,date1.length));
        }
        tdVal = td.substr(4,td.length);
        tdVals = parseInt(tdVal);
        val = tdVals+days-1;
        newText = "text"+val;
        
        if(status1 == 'Time Off') {
            document.eventForm[newText].style.backgroundColor="pink";
        }else if(status1 == 'Vacation') {
            document.eventForm[newText].style.backgroundColor="red";
        }else if(status1 == 'On Travel') {
            document.eventForm[newText].style.backgroundColor="#8DC0F1";
        }else if(status1 == 'Busy In Meeting') {
            document.eventForm[newText].style.backgroundColor="#AEB404";
        }
    }
}

