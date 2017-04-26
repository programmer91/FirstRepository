//Author : Hari Krishna Kondala
//Email :  hkondala@miraclesoft.com


function checkDates() {
    
    var w1 = document.getElementById("weekDate1").value;
    var w2 = document.getElementById("weekDate2").value;
    var w3 = document.getElementById("weekDate3").value;
    var w4 = document.getElementById("weekDate4").value;
    var w5 = document.getElementById("weekDate5").value;
    var w6 = document.getElementById("weekDate6").value;
    var w7 = document.getElementById("weekDate7").value;
    
    day2 = w2.substr(3,2);
    day3 = w3.substr(3,2);
    day4 = w4.substr(3,2);
    day5 = w5.substr(3,2);
    day6 = w6.substr(3,2);
    day7 = w7.substr(3,2);
    
    month2 = w2.substr(0,2);
    month3 = w3.substr(0,2);
    month4 = w4.substr(0,2);
    month5 = w5.substr(0,2);
    month6 = w6.substr(0,2);
    month7 = w7.substr(0,2);
    
    year2 = w2.substr(6,10);
    year3 = w3.substr(6,10);
    year4 = w4.substr(6,10);
    year5 = w5.substr(6,10);
    year6 = w6.substr(6,10);
    year7 = w7.substr(6,10);
    
    var prsYear = new Date().getYear();
    if (prsYear < 2000) prsYear += 1900;
    var prsMonth = new Date().getMonth()+1;
    if (prsMonth < 10) {
        prsMonth = '0'+prsMonth;
    }
    var prsDate = new Date().getDate();
    if(year2 > prsYear) {
        disableAllMonday();
    } else if(year2 == prsYear) {
        if(parseInt(month2,10) > parseInt(prsMonth,10)) {
            disableAllMonday();
        }else if(month2 == prsMonth) {
            if(day2 > prsDate) {
                disableAllMonday();
            }
        }
    }
    
    if(year3 > prsYear) {
        disableAllTuesday();
    }else if(year3 == prsYear) {
        if(month3 > prsMonth) {
            disableAllTuesday();
        }else if(month3 == prsMonth) {
            if(day3 > prsDate) {
                disableAllTuesday();
            }
        }
    }
    
    if(year4 > prsYear) {
        disableAllWednesday();
    }else if(year4 == prsYear) {
        if(month4 > prsMonth) {
            disableAllWednesday();
        }else if(month4 == prsMonth) {
            if(day4 > prsDate) {
                disableAllWednesday();
            }
        }
    }
    
    if(year5 > prsYear) {
        disableAllThursday();
    }else if(year5 == prsYear) {
        if(month5 > prsMonth) {
            disableAllThursday()
        }else if(month5 == prsMonth) {
            if(day5 > prsDate) {
                disableAllThursday();
            }
        }
    }
    
    if(year6 > prsYear) {
        disableAllFriday();
    }else if(year6 == prsYear) {
        if(parseInt(month6,10) > parseInt(prsMonth,10)) {
            disableAllFriday();
        }else if(month6 == prsMonth) {
            if(day6 > prsDate) {
                disableAllFriday();
            }
        }
    }
    
    if(year7 > prsYear) {
        disableAllSaturday();
    }else if(year7 == prsYear) {
        if(parseInt(month7,10)> parseInt(prsMonth,10)) {
            disableAllSaturday(); 
        }else if(parseInt(month7,10) == parseInt(prsMonth,10)) {
            if(day7 > prsDate) {
                disableAllSaturday();
            }
        }
    }
}

function disableAllMonday() {
    document.getElementById("proj1Mon").disabled = true;
    document.getElementById("proj2Mon").disabled = true;
    document.getElementById("proj3Mon").disabled = true;
    document.getElementById("proj4Mon").disabled = true;
    document.getElementById("proj5Mon").disabled = true;
    document.getElementById("internalMon").disabled = true;
    document.getElementById("vacationMon").disabled = true;
    document.getElementById("holiMon").disabled = true;
    document.getElementById("compMon").disabled = true;
}

function disableAllTuesday() {
    document.getElementById("proj1Tus").disabled = true;
    document.getElementById("proj2Tus").disabled = true;
    document.getElementById("proj3Tus").disabled = true;
    document.getElementById("proj4Tus").disabled = true;
    document.getElementById("proj5Tus").disabled = true;
    document.getElementById("internalTus").disabled = true;
    document.getElementById("vacationTus").disabled = true;
    document.getElementById("holiTus").disabled = true;
    document.getElementById("compTus").disabled = true;
}

function disableAllWednesday() {
    document.getElementById("proj1Wed").disabled = true;
    document.getElementById("proj2Wed").disabled = true;
    document.getElementById("proj3Wed").disabled = true;
    document.getElementById("proj4Wed").disabled = true;
    document.getElementById("proj5Wed").disabled = true;
    document.getElementById("internalWed").disabled = true;
    document.getElementById("vacationWed").disabled = true;
    document.getElementById("holiWed").disabled = true;
    document.getElementById("compWed").disabled = true;
    
}
function disableAllThursday() {
    document.getElementById("proj1Thur").disabled = true;
    document.getElementById("proj2Thur").disabled = true;
    document.getElementById("proj3Thur").disabled = true;
    document.getElementById("proj4Thur").disabled = true;
    document.getElementById("proj5Thur").disabled = true;
    document.getElementById("internalThur").disabled = true;
    document.getElementById("vacationThur").disabled = true;
    document.getElementById("holiThur").disabled = true;
    document.getElementById("compThur").disabled = true;
}
function disableAllFriday() {
    document.getElementById("proj1Fri").disabled = true;
    document.getElementById("proj2Fri").disabled = true;
    document.getElementById("proj3Fri").disabled = true;
    document.getElementById("proj4Fri").disabled = true;
    document.getElementById("proj5Fri").disabled = true;
    document.getElementById("internalFri").disabled = true;
    document.getElementById("vacationFri").disabled = true;
    document.getElementById("holiFri").disabled = true;
    document.getElementById("compFri").disabled = true;
}

function disableAllSaturday() {
    document.getElementById("proj1Sat").disabled = true;
    document.getElementById("proj2Sat").disabled = true;
    document.getElementById("proj3Sat").disabled = true;
    document.getElementById("proj4Sat").disabled = true;
    document.getElementById("proj5Sat").disabled = true;
    document.getElementById("internalSat").disabled = true;
    document.getElementById("vacationSat").disabled = true;
    document.getElementById("holiSat").disabled = true;
    document.getElementById("compSat").disabled = true;
}


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

/*
function readyStateHandlerText(req,responseTextHandler){
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
*/
function readyStateHandlerText(req,responseTextHandler) {
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

function getProjectsDetails() {
    
    var iflag = document.getElementById("iflag").value;
    if(parseInt(iflag,10) == 0) {
        
    
        var totalProjects = document.getElementById("associatedProjectsCount").value;
        if(totalProjects > 0){
            var req = newXMLHttpRequest();
            req.onreadystatechange = readyStateHandlerText(req,populateProjectDetails);
            var url=CONTENXT_PATH+"/getEmpAssociatedProjectsList.action";
            req.open("GET",url,"true");
            req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            req.send(null);

        }else {
            document.getElementById("timeSheetDetails").style.display = 'block';
        }
    }else {
    
        var proj1Id = document.getElementById("proj1Id").value;
    
        var proj2Id = document.getElementById("proj2Id").value;
        var proj3Id = document.getElementById("proj3Id").value;
        var proj4Id = document.getElementById("proj4Id").value;
        var proj5Id = document.getElementById("proj5Id").value;
    
        var priProjId = document.getElementById("priProjId").value;
        //alert("proj5Id-->"+proj5Id);
        if(proj1Id!=0.0){
            //var proj1Detailes = projects[0].split("||");
            //document.getElementById("proj1Id").value = proj1Detailes[0];
            if(priProjId == proj1Id)
                document.getElementById("proj1Label").innerHTML = "<b>"+document.getElementById("proj1Name").value+"</b>";
            else
                document.getElementById("proj1Label").innerHTML = document.getElementById("proj1Name").value;
            showRow("project1");
        }
    
        if(proj2Id!=0.0){
            //var proj1Detailes = projects[0].split("||");
            //document.getElementById("proj1Id").value = proj1Detailes[0];
            if(priProjId == proj2Id)
                document.getElementById("proj2Label").innerHTML = "<b>"+document.getElementById("proj2Name").value+"</b>";
            else
                document.getElementById("proj2Label").innerHTML = document.getElementById("proj2Name").value;
            showRow("project2");
        }
        if(proj3Id!=0.0){
            //var proj1Detailes = projects[0].split("||");
            //document.getElementById("proj1Id").value = proj1Detailes[0];
            if(priProjId == proj3Id)
                document.getElementById("proj3Label").innerHTML = "<b>"+document.getElementById("proj3Name").value+"</b>"; 
            else
                document.getElementById("proj3Label").innerHTML = document.getElementById("proj3Name").value;
            showRow("project3");
        }
        if(proj4Id!=0.0){
            //var proj1Detailes = projects[0].split("||");
            //document.getElementById("proj1Id").value = proj1Detailes[0];
            if(priProjId == proj4Id)
                document.getElementById("proj4Label").innerHTML = "<b>"+document.getElementById("proj4Name").value+"</b>";  
            else
                document.getElementById("proj4Label").innerHTML = document.getElementById("proj4Name").value;
            showRow("project4");
        }
        if(proj5Id!=0.0){
            //var proj1Detailes = projects[0].split("||");
            //document.getElementById("proj1Id").value = proj1Detailes[0];
            if(priProjId == proj5Id)
                document.getElementById("proj5Label").innerHTML = "<b>"+document.getElementById("proj5Name").value+"</b>";
            else
                document.getElementById("proj5Label").innerHTML = document.getElementById("proj5Name").value;
            showRow("project5");
        }
    
        document.getElementById("timeSheetDetails").style.display = 'block';
    
    }
}

function populateProjectDetails(resText){
    //alert(resText);
    var projectDetails = resText.split("@");
    var primaryProjectId = projectDetails[1];
    document.getElementById("priProjId").value = primaryProjectId;
    // var projects = resText.split("^"),i;
    var projects = projectDetails[0].split("^"),i;
    for (i = 0; i < projects.length; i++) {
        if(i==5){
            break;
        }
        if(i==0){
            var proj1Detailes = projects[0].split("||");
            document.getElementById("proj1Id").value = proj1Detailes[0];
            if(primaryProjectId == proj1Detailes[0])
                document.getElementById("proj1Label").innerHTML = "<b>"+proj1Detailes[1]+"</b>";
            else
                document.getElementById("proj1Label").innerHTML = proj1Detailes[1];
            showRow("project1");
        }else if(i==1){
            var proj2Detailes = projects[1].split("||");
            document.getElementById("proj2Id").value = proj2Detailes[0];
            if(primaryProjectId == proj2Detailes[0])
                document.getElementById("proj2Label").innerHTML = "<b>"+proj2Detailes[1]+"</b>";
            else
                document.getElementById("proj2Label").innerHTML = proj2Detailes[1];
            showRow("project2");
        }else if(i==2){
            var proj3Detailes = projects[2].split("||");
            document.getElementById("proj3Id").value = proj3Detailes[0];
            if(primaryProjectId == proj3Detailes[0])
                document.getElementById("proj3Label").innerHTML = "<b>"+proj3Detailes[1]+"</b>";
            else
                document.getElementById("proj3Label").innerHTML = proj3Detailes[1];
            showRow("project3");
        }else if(i==3){
            var proj4Detailes = projects[3].split("||");
            document.getElementById("proj4Id").value = proj4Detailes[0];
            if(primaryProjectId == proj4Detailes[0])
                document.getElementById("proj4Label").innerHTML = "<b>"+proj4Detailes[1]+"</b>";
            else
                document.getElementById("proj4Label").innerHTML = proj4Detailes[1];
            showRow("project4");
        }else if(i==4){
            var proj5Detailes = projects[4].split("||");
            document.getElementById("proj5Id").value = proj5Detailes[0];
            if(primaryProjectId == proj4Detailes[0])
                document.getElementById("proj5Label").innerHTML = "<b>"+proj5Detailes[1]+"</b>";
            else
                document.getElementById("proj5Label").innerHTML = proj5Detailes[1];
            showRow("project5");
        }
    }
    document.getElementById("timeSheetDetails").style.display = 'block';
}

function hideRow(id) {
    var row = document.getElementById(id);
    row.style.display = 'none';
}
function showRow(id) {
    var row = document.getElementById(id);
    row.style.display = '';
}  
    
    
    
//-----------


function isReportsTo(roleName,custRole) {
    if(roleName == 'Operations'|| custRole == 8) {
    // alert("hii");
    }else {
        document.getElementById("approveButton").disabled = true;
        document.getElementById("rejectButton").disabled = true;
        var empId = document.getElementById("empID").value;
        var resourceType = document.getElementById("resourceType").value;

        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerText(req,populateIsReportsTo);
        var url=CONTENXT_PATH+"/getReportsToAccess.action?empId="+empId+"&resourceType="+resourceType;
        req.open("POST",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
   
}


function populateIsReportsTo(resText){
    //alert(resText);
    // var test = resText.split(".")[0];
    if(resText=='YES'){
        // if(test=='YES'){
        document.getElementById("approveButton").disabled = false;
        document.getElementById("rejectButton").disabled = false;
    }
}



/*
 * Timesheet attachement upload changes..
 * 
 */


function uploadTimeSheetAttachement(){
    //  alert("uploadtrvlAcmdAttachement");
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Upload Customer Time Sheet";
         
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');
    //document.getElementById('empId').value=empId.value;
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}

function timeSheetAttachmentUpload(){
   
  
    var empId = document.getElementById("empID").value;
  
    var file =document.getElementById('file');

    
    var timeSheetID =document.getElementById('timeSheetID').value;
    // alert('dfgfgfg');
    if(file == null || (file =="")){
        document.getElementById('resultMessage').innerHTML = "<font color=red>please upload file</font>"
        return false;
    }

    document.getElementById("load").style.display = 'block';
    if(window.XMLHttpRequest){
        xmlHttpRequest=new XMLHttpRequest();
    }
    else if(window.ActiveXObject){
        xmlHttpRequest=new ActiveXObject("Microsoft.XMLHttp");
          
    }
    else{
        alert("browser does not support ajax");
        return false;
    }
        
    xmlHttpRequest.onreadystatechange=stateChange;

     
    var data = new FormData();
    data.append('file', file.files[0]);
        
    var url="timsheetAttachmentUpload.action?empId="+empId+"&timeSheetID="+timeSheetID;
    //'timsheetAttachmentUpload.action?empId='+empId+"&timeSheetID="+timeSheetID,
    xmlHttpRequest.open("POST", url, true);
	    
    xmlHttpRequest.send(data);
}


function stateChange(){
    
    if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200) {
        //document.getElementById("msg").innerHTML =data;
        var data=xmlHttpRequest.responseText;
        if(data=="uploaded")
        {
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = "<font color=green>Uploaded Successfully</font>"
        }
    //    alert(data);
       
    }
}


function timeSheetFileValidation()
{
    document.getElementById('resultMessage').innerHTML = '';
    var fullPath = document.getElementById('file').value;
    var size = document.getElementById('file').files[0].size;
    var extension = fullPath.substring(fullPath.lastIndexOf('.')+1).toLowerCase();
    
    if(extension=="pdf"||extension=="jpg"|| extension=="png"||extension=="gif" || extension=="bif" || extension=="tif" || extension=="fpx"||extension=="jpeg" ||extension=="jp2"||extension=="jpx"||extension=="j2k"||extension=="j2c" || extension=="tiff" || extension=="pcd"|| extension=="jif" || extension=="jfif"|| extension=="docx" ||extension=="doc"|| extension=="odt" || extension=="dotx"|| extension=="rtf"|| extension=="xlsx"|| extension=="xlsm"|| extension=="xlsb"|| extension=="xltx"|| extension=="xltm"|| extension=="xls"|| extension=="xlt"|| extension=="xml"|| extension=="xlam"|| extension=="xla"|| extension=="xlw" ){
        var size = document.getElementById('file').files[0].size;
                  
        if(fullPath.length>50){
            document.getElementById('file').value = '';
            document.getElementById('resultMessage').innerHTML = "<font color=red>File name length must be less than 50 characters!</font>"
            return (false);
        }else {
            if(parseInt(size)<2097152) {
                
                  
            }else {
                document.getElementById('file').value = '';
                document.getElementById('resultMessage').innerHTML = "<font color=red>File size must be less than 2 MB.</font>"
                return (false);
            }
        }
    }else {
         
        document.getElementById('file').value = "";
        //document.getElementById('resultMessage').innerHTML = "<font color=red>Invalid file extension!Please select pdf or jpg or jpeg or tif or png file.</font>"
        document.getElementById('resultMessage').innerHTML = "<font color=red>Invalid file extension!Please select pdf or jpg or jpeg or png or gif or bif or tif or tiff or jif or jfif or fpx or jp2 or jpx or j2k or j2c or pcd or docx or doc or odt or dotx or rtf or xlsx or xls file.</font>";
        return false;
    }
}
function toggleCloseTimeSheetAttachmentOverlay() {
    var empId=document.getElementById("empID").value;
    var timeSheetID=document.getElementById("timeSheetID").value;
    var empType=document.getElementById("empType").value;
    var timeSheetSecStat=document.getElementById("timeSheetSecStat").value;
    var timeSheetStat=document.getElementById("timeSheetStat").value;
    if(timeSheetSecStat=="Enter"){
        timeSheetSecStat="Entered";
    }
    if(timeSheetStat=="Enter"){
        timeSheetStat="Entered";
    }
    if(empType=="e"){
        empType="E";
    }
    // alert("empType-->"+empType+"--timeSheetSecStat--"+timeSheetSecStat+"--timeSheetStat--"+timeSheetStat);
     
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');

    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
    window.location="newgetTimesheet.action?empID="+empId+"&timeSheetID="+timeSheetID+"&resourceType="+empType+"&statusValue="+timeSheetStat+"&secStatusValue="+timeSheetSecStat;
}
function getTimeSheetAttachementDownload(){
    var empId=document.getElementById("empID").value;
    var timeSheetID=document.getElementById("timeSheetID").value;
    // alert(empId+"  "+timeSheetID);
    window.location="getTimeSheetAttachementDownload.action?empID="+empId+"&timeSheetID="+timeSheetID;
}

function removeTimeSheetAttachement(){
    // var empID = $('#empID').val();
    var empId=document.getElementById("empID").value;
    var timeSheetID=document.getElementById("timeSheetID").value;
    // alert(timeSheetID);
    var empType=document.getElementById("empType").value;
    var timeSheetSecStat=document.getElementById("timeSheetSecStat").value;
    var timeSheetStat=document.getElementById("timeSheetStat").value;
    if (confirm("Do you want to remove attachment?")) {
        //document.getElementById("loading").style.display = 'block';
        $.ajax({
            url:'removeTimeSheetAttachement.action?empId='+empId+"&timeSheetID="+timeSheetID,
            context: document.body,
            success: function(responseText) {
                alert("Attachment removed successfully");
                if(parseInt(responseText,10)>0){
                    window.location="newgetTimesheet.action?empID="+empId+"&timeSheetID="+timeSheetID+"&resourceType="+empType+"&statusValue="+timeSheetStat+"&secStatusValue="+timeSheetSecStat;
                }
            },
            error: function(e){
                //  document.getElementById("loading").style.display = 'none';
                alert("Please try again");
            }
        });
    } 
}

function showAttachmentFun(){
   
    var timeSheetID = document.getElementById("timeSheetID").value;
    
    // alert(document.getElementById("fileFlagValue").value);
    //alert(empLocation);
    //alert(empType+" --- "+empLocation);
    
    //var resourceType = document.getElementById("resourceType").value;
    //alert(empLocation);
    // alert("timeSheetID-->"+timeSheetID);
    if(parseInt(timeSheetID,10)>0){
        // var empLocation = document.getElementById("employeeLocation").value;
        var empType = document.getElementById("empType").value;
        if(empType.toUpperCase() == 'E' ){
            // alert("If");
            showRow("attachmentTR");
        
        }else {
            // alert("Else");
            hideRow("attachmentTR");
        
        }
    }
}



function showTlAttachmentFun(){
   
    var timeSheetID = document.getElementById("timeSheetID").value;
    
    // alert(document.getElementById("fileFlagValue").value);
    //alert(empLocation);
    //alert(empType+" --- "+empLocation);
    
    //var resourceType = document.getElementById("resourceType").value;
    //alert(empLocation);
    //alert("timeSheetID-->"+timeSheetID);
    if(parseInt(timeSheetID,10)>0){
        //var empLocation = document.getElementById("employeeLocation").value;
        var empType = document.getElementById("empType").value;
        //  alert(empType);
        if(empType.toUpperCase() == 'E' ){
            //   alert("If");
            showRow("attachmentTR");
        
        }else {
            //   alert("Else");
            hideRow("attachmentTR");
        
        }
    }
}


/*
 * Biometric Changes
 * Date : 02/18/2016
 */
function displayBiometricHrs(){
    var empLocation = document.getElementById("employeeLocation").value;
    var empType = document.getElementById("empType").value;
    // alert(empType);
    
    //var resourceType = document.getElementById("resourceType").value;
    //alert(empLocation);
  
    if((empLocation.trim()=='Miracle City' || empLocation.trim()=='Miracle Heights' || empLocation.trim()=='LB Office') && empType.toUpperCase() != 'c'.toUpperCase() ){
        
        showRow("biometricDailyTr");
        showRow("biometricTotalTr");
    }else {
        hideRow("biometricDailyTr");
        hideRow("biometricTotalTr");
        
    }
}


/*validation methods for leave date alert
 * Author : Tirumala Teja Kadamanti
 * Date : 03/06/2017
 */



function getAlertOnLeave(id,weak){
    var empType=document.getElementById('empType').value;
    if(empType=='e'){
        var response=document.getElementById('leaveDates').value; 
   
        var resTextSplit1 = response.split("^");
    
        for(var index=0;index<resTextSplit1.length-1;index++) {
            var   result = resTextSplit1[index].split("|");
           
            var startDate=result[0];
    
            var endDate=result[1];
            var leaveType=result[2];
    
            var dates = getDates(new Date(startDate), new Date(endDate));
   
            if(leaveType=='Vacation'){
                for(var loop=0;loop<dates.length;loop++){
       
                    var leaveDate=formatDate(dates[loop]);

                    if(leaveDate==weak.value){
                        if(id.value>=8){
               
                            alert("You have taken leave on these day("+leaveDate+"). Please verify and enter the hours.");  
                   
                        }
                    }
 
                }

            }
    
            
            
        }   
    }
  
    
    
    
}


var getDates = function(startDate, endDate) {
    var dates = [],
    currentDate = startDate,
    addDays = function(days) {
        var date = new Date(this.valueOf());
        date.setDate(date.getDate() + days);
        return date;
    };
    while (currentDate <= endDate) {
        var day =currentDate.getDay();
        if(day!=0 && day!=6){
            dates.push(currentDate);
        }
        currentDate = addDays.call(currentDate, 1);
    
    }
    return dates;
};

function formatDate(value)
{
    var month=value.getMonth()+1;
    var date=value.getDate();
    if(month<10)
    {
        month="0"+month;    
    }
    if(date<10)
    {
        date="0"+date;    
    }
    return month + "/" + date + "/" + value.getFullYear();
}
