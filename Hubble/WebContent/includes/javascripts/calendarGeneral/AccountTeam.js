/********************************************
 Author : Venkateswara Rao Nukala
 Email : vnukala@miraclesoft.com
 ********************************************/

function getTeam(accId) {
    var id=document.getElementById("accountId").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,populateMembers);    
    var url = CONTENXT_PATH+"/getAccountTeamId.action?accTeamId="+accId+"&dummy="+new Date().getTime();
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function populateMembers(resXML) {
    var teamMemberId=document.getElementById("accTeamMembers");
    var memberIds=resXML.getElementsByTagName("MEMBER-IDS")[0];
    //var memberId=memberIds.getElementsByTagName("MEMBER-ID");
    var member=memberIds.getElementsByTagName("MEMBERS");
    teamMemberId.innerHTML=" ";
    for(var i=0;i<member.length;i++) {
        //var practiceName = member.childNodes[i];
        var practiceName = member[i];
        var key = practiceName.getElementsByTagName("MEMBER-KEY")[0];
        //var name = practiceName.firstChild.nodeValue;
        var name = practiceName.getElementsByTagName("MEMBER-ID")[0];
        var opt = document.createElement("option");//this one is for creating option 
        opt.setAttribute("value",key.childNodes[0].nodeValue);
        opt.appendChild(document.createTextNode(name.childNodes[0].nodeValue));
        teamMemberId.appendChild(opt);
    }
}

function getEventTypes() {
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,populateEventTypes);
    var url = CONTENXT_PATH+"/getEventType.action?accTeamId="+accId+"&dummy="+new Date().getTime();
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function populateResult(resXML) {
    var valid = resXML.getElementsByTagName("valid")[0];
    var chk = valid.childNodes[0].nodeValue;
    if(chk ==1) {
        alert("Event is successfully saved");
        clear();
        /*getMonthlyEvents();
        setMonthYear();
        getDateInfo();*/
        getDailyview();
        getDailyEvents();
    } else if(chk >1){
        alert("Event is successfully updated.");
        clear();
        //getMonthlyEvents();
        //setMonthYear();
        //getDateInfo();
        getDailyview();
        getDailyEvents();
    }else {
        alert("Event is not saved. Please Try Again");
        clear();
    }
}

function save() {
    // alert("here")
    var calEveId=document.getElementById("calEveId").value;
    var eventDate=document.getElementById("eventAddDate").value;
    var eventDate1=document.getElementById("eventEndDate").value;
    var eventStartTime=document.getElementById("eventStartTime").value;
    var eventEndTime=document.getElementById("eventEndTime").value;
    var createdById = document.getElementById("createdBy").value;
    var currentUser = document.getElementById("currentuser").value;
    var eventType=document.getElementById("eventType").value;
    var description=document.getElementById("description").value;
    eventDate = (eventDate)+" "+(eventStartTime);
    eventDate1= (eventDate1)+" "+(eventEndTime);
    //alert("start---"+eventDate+"--end---"+eventDate1)
    //alert(eventDate);
    //if(calEveId==0 || ){   // this is for checking the date
    var today = new Date();    
    var dd = today.getDate();
    var mm = today.getMonth()+1;//January is 0!
    var currentYear = today.getFullYear();    
    var checkStartYear = eventDate.substring(6,10);
    var checkEndYear = eventDate1.substring(6,10);    
    if(dd<10){
        dd='0'+dd;
    }
    if(mm<10){
        mm='0'+mm;
    }
    var currentDate=mm+'/'+dd+'/'+currentYear;
    
    if((currentYear <= checkStartYear)&& (currentYear <= checkEndYear)) {
        if(checkStartYear < checkEndYear || checkStartYear == checkEndYear){
            
            if(eventDate1.substring(0,5) < eventDate.substring(0,5)) {    
                alert('Event EndDate is older than StartDate');
                return false;
            }else if (eventDate.substring(0,5) < currentDate.substring(0,5)) {
                alert('Event startDate is older than currentDate');
                return false;
            }else if (eventDate1.substring(0,5) < currentDate.substring(0,5)) {
                alert('Event EndDate is older than currentDate');
                return false;
            }
        }else {
            alert('Event End Date is older than Current Date');
            return false;
        }
    }
    else{
        //alert('false');
        alert('Event Date is older than Current Date');
        return false;
    }
    if(document.getElementById("salesTeamUser") != null) {
        calAccessUserId=document.getElementById("salesTeamUser").value;
    }else {
        calAccessUserId = '';
    }
    //alert('venkiii'+calAccessUserId);
    // alert("current USer is---"+currentUser);
    //alert("current USer is---"+createdById);
    //if(currentUser != createdById)
    
    //alert("iddd==="+calEveId);
    //alert(validateEventAdd(eventDate,eventType));
    if(!validateEventAdd(eventDate,eventType,eventDate1)) {
        alert("Please enter Event Date, Event Type and Event End Date");
        return false;
    } 
    
/*    if(currentUser!=createdById && createdById !="") {
        alert("You are not Authorized to Update this event ")
        return false;
    }*/
    /*var accName=document.getElementById("accName").value;
    var accId=document.getElementById("accountId").value;
    var contactName=document.getElementById("contactName").value;
    var contactsId=document.getElementById("contactsId").value;
    var teamMembers=new Array(document.getElementById("accTeamMembers").length);*/
    //it returns total length of array.
    /*for(var i=0;i<teamMembers.length;i++) {
        if(document.getElementById("accTeamMembers")[i].selected == true) {
            var accTeam=accTeam+(document.getElementById("accTeamMembers")[i].value)+"|";
     
        }
    }*/
    /*var accTeam=accTeam.substring(0,(accTeam.length-1));
    if(accTeam!=null && accTeam!="" && accTeam!=" "  )   accTeam=accTeam+"|";
    if(accName==""){
        accName="no records";    
    }*/
    var accTeam="";
    var accId="";
    var contactsId="";
    if(accId=="" || accId==null) accId=0; 
    if(contactsId=="" || contactsId==null) contactsId=0;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,populateResult);
    var url = CONTENXT_PATH+"/addEvent.action?CalEventDate="+eventDate+"&CalEventDate1="+eventDate1+"&eventType="+eventType+"&EveAccId= "+accId+"&contactsId="+contactsId+"&accTeam="+accTeam+"&EventDesc="+description+"&calEveId="+calEveId+"&calAccessUserId="+calAccessUserId+"&dummy="+new Date().getTime();
    //alert(url);
    req.open("POST",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    //alert("done")
}
function getEventView(EventId) {
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,populateEventDetails);  
    var url=CONTENXT_PATH+"/getEventDetails.action?eventId="+EventId+"&dummy="+new Date().getTime();
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateEventDetails(resXML) {
    var eventDetails=resXML.getElementsByTagName("EVENT-DETAILS")[0];
    //var calEveId=eventDetails.getElementsByTagName("EVENT-ASSIGEND-IDS")[0];
    var eventType=eventDetails.getElementsByTagName("EVENT-TYPE")[0];
    var eventStartDate=eventDetails.getElementsByTagName("EVENT-DATE")[0];
    var eventEndDate=eventDetails.getElementsByTagName("EVENT-END-DATE")[0];
    /*var accName=eventDetails.getElementsByTagName("EVENT-ACCNAME")[0];
    var conName=eventDetails.getElementsByTagName("EVENT-CONNAME")[0];
    var accTeam=eventDetails.getElementsByTagName("EVENT-ASSIGNED")[0];*/
    var desc=eventDetails.getElementsByTagName("EVENT-DESC")[0];
    var createdById = eventDetails.getElementsByTagName("EVENTCREATEDBY")[0];
    document.getElementById("eventType").value=eventType.childNodes[0].nodeValue; //mandatory fields  eventtype and date
    eventDateValue = eventStartDate.childNodes[0].nodeValue;
    var eventDateDummy = eventDateValue.split("  ");
    //if(eventEndDate.childNodes[0].nodeValue="no records") document.getElementById("eventEndDate").value="";
    //else {
    var eventEndDateDummy=eventEndDate.childNodes[0].nodeValue.split("  ");
    document.getElementById("eventEndDate").value=eventEndDateDummy[0];
    document.getElementById("eventEndTime").value=eventEndDateDummy[1];
    // }
    // alert(eventEndDateDummy);
    //salert("length---"+eventDateDummy.length);
    //alert("first string---"+eventDateDummy[0]+"---second"+eventDateDummy[1]+"--third"+eventDateDummy[2]);
    document.getElementById("eventAddDate").value=eventDateDummy[0];
    document.getElementById("eventStartTime").value=eventDateDummy[1];
    /*if(accName.childNodes[0].nodeValue=="no records")  accName.childNodes[0].nodeValue="";
    else document.getElementById("accName").value=accName.childNodes[0].nodeValue;
    document.getElementById("accName").readonly;
    if(conName.childNodes[0].nodeValue=="no records") document.getElementById("contactName").value="";
    else document.getElementById("contactName").value=conName.childNodes[0].nodeValue;
    if(accTeam.childNodes[0].nodeValue=="no records") document.getElementById("accTeamMembers").value="";
    else {
        var teamMemberId=document.getElementById("accTeamMembers");
        teamMemberId.disabled = "true";
        teamMemberId.innerHTML=" ";
        displayTeamCalAdd();
        for(var i=0;i<accTeam.childNodes[0].nodeValue.split("|").length-1;i++) {
            var j=0;
            var temp1=accTeam.childNodes[0].nodeValue.split("|");
            var temp2=temp1[i].split("^");
            var opt = document.createElement("option");
            opt.setAttribute("value",temp2[j]);
            opt.appendChild(document.createTextNode(temp2[j+1]));
            teamMemberId.appendChild(opt);
        }
    }
    document.getElementById("accName").value=accName.childNodes[0].nodeValue;
    document.getElementById("accName").disabled=true*/
    if(desc.childNodes[0].nodeValue=="no records")
        document.getElementById("description").value="";
    else 
        document.getElementById("description").value=desc.childNodes[0].nodeValue;
    //document.getElementById("description").value=desc.childNodes[0].nodeValue;
    document.getElementById("calEveId").value=calEveId.childNodes[0].nodeValue;
    document.getElementById("createdBy").value = createdById.childNodes[0].nodeValue;
}

function clear() {
    document.getElementById("calEveId").value=0;
    document.getElementById("createdBy").value = "";
    //alert("id is ----"+document.getElementById("calEveId").value);
    document.getElementById("eventType").value="";
    document.getElementById("eventAddDate").value="";
    document.getElementById("eventEndDate").value="";
    document.getElementById("eventStartTime").value="";
    document.getElementById("eventEndTime").value="";
    document.getElementById("description").value="";
    /*var accNameId = document.getElementById("accName");
    accNameId.value="";
    accNameId.removeAttribute("readonly");
    if(accNameId.readonly) {
        accNameId.readonly = "false";
    }
    document.getElementById("contactName").value="";
    document.getElementById("accTeamMembers").value="";*/
    
    /*var teamMemberId=document.getElementById("accTeamMembers");
    teamMemberId.removeAttribute("disabled");
    if(teamMemberId.disabled) {
        teamMemberId.readonly = "false";
    }
    document.getElementById("accName").disabled=false;
    //alert("team length--"+teamMemberId.length)
    teamMemberId.innerHTML=" ";
    //teamMemberId.appendChild("");
    dontDisplayTeamCalAdd();*/
}
// calendar access for users
/*function accessCalendar(){
    alert('hiiii');
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,populateTeam);    
    var url = CONTENXT_PATH+"/accessCalendar1.action?&dummy="+new Date().getTime();
    alert(url);
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function populateTeam(resXML){
    alert('hi');
    var teamUesr=document.getElementById("salesTeamUser");
    var meberIds=resXML.getElementsByTagName("MEMBERS");
    alert(meberIds.length);
    teamUesr.innerHTML=" ";
    for(var i = 0; i < meberIds.length; i++) {
        var memberId=meberIds[i];
        var name=memberId.getElementsByTagName("MEMBER-NAME")[0].firstChild.nodeValue;
        var id=memberId.getElementsByTagName("MEMBER-ID")[0].firstChild.nodeValue
        alert(name);
        var opt = document.createElement("option");
        opt.setAttribute("value",id);
 
        opt.appendChild(document.createTextNode(name));
        teamUesr.appendChild(opt);
 
    }
 
}*/