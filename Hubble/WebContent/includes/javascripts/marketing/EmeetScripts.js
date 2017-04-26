/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function hideRow(id) {
    //alert(id);
    var row = document.getElementById(id);
    row.style.display = 'none';
}
function showRow(id) {
    //  alert(id);
    var row = document.getElementById(id);
    row.style.display = '';
} 

function addExecutiveMeet(){
   // alert("hi");
    document.getElementById('resultMessage').innerHTML ='';
    hideRow('addTr');
    hideRow('editTr');
    hideRow("createdTr");
    
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Add Executive Meeting";
    showRow('addTr');

    var overlay = document.getElementById('exeMeetoverlay');
    var specialBox = document.getElementById('exeMeetspecialBox');
    //  hideRow("approvedBy");
    //hideRow("tlcommentsTr");
    // hideRow("hrcommentsTr");
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
            
//window.location = "jobSearch.action";
}
        
function toggleOverlayForExeMeet(){
    //alert("hi");
    document.getElementById('resultMessage').innerHTML ='';
    hideRow('addTr');
    hideRow('editTr');
    hideRow("createdTr");
    
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Add Executive Meeting";
    showRow('addTr');
    var overlay = document.getElementById('exeMeetoverlay');
    var specialBox = document.getElementById('exeMeetspecialBox');
    //  hideRow("approvedBy");
    //hideRow("tlcommentsTr");
    // hideRow("hrcommentsTr");
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
              
        document.getElementById("frmDBGrid").submit();
                
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
            
    //  window.location = "jobSearch.action";
    document.getElementById("frmDBGrid").submit();
}
        
function doAddExecutiveMeeting()
{
     
   
    var executiveMeetingType= document.getElementById("executiveMeetingType").value;
                
    var executiveMeetMonth=document.getElementById("executiveMeetMonth").value;
          
   // var registrationTextforExeMeet=document.getElementById("registrationTextforExeMeet").value;
           
    var exeMeetcreatedDateTo=document.getElementById("exeMeetcreatedDateTo").value;
           
    var executiveMeetingStatus=document.getElementById("executiveMeetingStatus").value;
    var exeMeetStartTime=document.getElementById("exeMeetStartTime").value;
             
    var exeMeetEndTime=document.getElementById("exeMeetEndTime").value;
    var exeMeetStartmidDayFrom=document.getElementById("exeMeetStartmidDayFrom").value;
    var exeMeetEndmidDayTo=document.getElementById("exeMeetEndmidDayTo").value;
    var registrationLinkForEMeet=escape(document.getElementById("registrationLinkForEMeet").value);
    var timeZone=document.getElementById("timeZone").value;
 
    if(executiveMeetingType==-1){
        //alert("please Enter Type");
        document.getElementById('resultMessage').innerHTML = "<font color=red>please Enter Type.</font>";
        return false;
    }
    else if(executiveMeetMonth==-1)
    {
        // alert("please Enter Month");
        document.getElementById('resultMessage').innerHTML = "<font color=red>please Enter Month.</font>";
        return false;
    }
    else if(exeMeetcreatedDateTo==""){
        //alert("please Enter Date");
        document.getElementById('resultMessage').innerHTML = "<font color=red>please Enter Date.</font>";
        return false;
    }
  
    else if(exeMeetStartTime==""){
        document.getElementById('resultMessage').innerHTML = "<font color=red>please Start Time.</font>";
        return false; 
    }
    else if(exeMeetEndTime==""){
        document.getElementById('resultMessage').innerHTML = "<font color=red>please End Time.</font>";
        return false; 
    }
//    else if(registrationTextforExeMeet=="")
//    {
//        //alert("please Enter Registration Text for Web Page");
//        document.getElementById('resultMessage').innerHTML = "<font color=red>please Enter Registration Text for Web Page.</font>";
//        return false;
//    }
    else if(registrationLinkForEMeet==""){
        // alert("please Enter Registration Link");
        document.getElementById('resultMessage').innerHTML = "<font color=red>please Enter Registration Link.</font>";
        return false;
    }

   
    else
    {
        //hideRow('addTr');
        hideRow('editTr');
        setTimeout(disableFunction,1);
        document.getElementById("load").style.display = 'block';
     
        $.ajax({
            // url:'editJobposting.action?jobId='+jobId,//
            //url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
            url:'addExecitiveMeeting.action?executiveMeetingType='+executiveMeetingType+'&executiveMeetMonth='+executiveMeetMonth+"&exeMeetcreatedDateTo="+exeMeetcreatedDateTo+"&executiveMeetingStatus="+executiveMeetingStatus+"&exeMeetStartTime="+exeMeetStartTime+"&exeMeetEndTime="+exeMeetEndTime+"&exeMeetStartmidDayFrom="+exeMeetStartmidDayFrom+"&exeMeetEndmidDayTo="+exeMeetEndmidDayTo+"&registrationLinkForEMeet="+registrationLinkForEMeet+"&timeZone="+timeZone,
            context: document.body,
            success: function(responseText) {
                // displaymessage = responseText;
         
           
                document.getElementById("load").style.display = 'none';
                document.getElementById('resultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
            // document.getElementById('fileType').value = "";
            },
            error: function(e){
           
                document.getElementById("load").style.display = 'none';
                document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
     
                
            }
        });
    
    }
		
    return false;

}

function editExeMeet(Id){
   
    hideRow('addTr');
    showRow('editTr');
    document.getElementById('resultMessage').innerHTML ='';
    document.getElementById("load").style.display = 'block';
    $.ajax({
        url:'editExeMeeting.action?id='+Id,//
        context: document.body,
        success: function(responseText) {
            // alert(responseText);
            var json = $.parseJSON(responseText);
                
            var Id = json["Id"];
            var Type = json["TYPE"];
            var Status = json["STATUS"];
            var StartTime = json["StartTime"];
            var EndTime = json["EndTime"];
            var MidDayTo = json["MidDayTo"];
            var MidDayFrom = json["MidDayFrom"];
            var EMeetDate = json["EMeetDate"];
          //  var EMeetRegistrationText = json["EMeetRegistrationText"];
            var EMeetRegistrationLink = json["EMeetRegistrationLink"];
           // alert(EMeetRegistrationLink);
            var CreatedBy = json["CreatedBy"];
            var CreatedDate = json["CreatedDate"];
            var TimeZone = json["TimeZone"];
            var Month = json["Month"];
           
            if(Status=='Published'){
                document.getElementById("publishedId").style.display='block';
                document.getElementById("statusId").style.display='none';
                document.getElementById("publishedStatus").innerHTML = Status;
                showRow("emeetUrlTr");
                document.getElementById("emeetLink").innerHTML='';
                document.getElementById("emeetLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/events/executive-management-meets.php?emeetId='+Id+'</font>';
                hideRow('updateButton');
            }else{
                document.getElementById("statusId").style.display='block';
                document.getElementById("executiveMeetingStatus").value = Status;
            }
            // alert(CreatedDate+"---"+CreatedBy);
            document.getElementById("executiveMeetingType").value = Type;
            // document.getElementById("executiveMeetMonth").value = jobtitle;
           // document.getElementById("registrationTextforExeMeet").value = EMeetRegistrationText;
            document.getElementById("exeMeetcreatedDateTo").value = EMeetDate;
           
            document.getElementById("exeMeetStartTime").value = StartTime;
            document.getElementById("exeMeetEndTime").value = EndTime;
            document.getElementById("exeMeetStartmidDayFrom").value = MidDayFrom;
            document.getElementById("exeMeetEndmidDayTo").value = MidDayTo;
            document.getElementById("registrationLinkForEMeet").value = EMeetRegistrationLink;
            document.getElementById("exeMeetingId").value = Id;
            document.getElementById("executiveMeetMonth").value = Month;
            document.getElementById("timeZone").value = TimeZone;
            document.getElementById("createdBy").innerHTML = CreatedBy;
            document.getElementById("createdDate").innerHTML = CreatedDate;
           
         
            document.getElementById("load").style.display = 'none';
        // getSlider(4,7);
            
        }, 
        error: function(e){
            document.getElementById("load").style.display = 'none';
            alert("error-->"+e);
        }
    });
    
    
    
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Edit ExecutiveMeeting";
    var overlay = document.getElementById('exeMeetoverlay');
    var specialBox = document.getElementById('exeMeetspecialBox');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}

function doUpdateExeMeeting(){
     hideRow('addTr');
    //hideRow('editTr');
   
    var exeMeetingId= document.getElementById("exeMeetingId").value;
    //  alert("exeMeetingId----"+exeMeetingId)
    var executiveMeetingType= document.getElementById("executiveMeetingType").value;
                
    var executiveMeetMonth=document.getElementById("executiveMeetMonth").value;
          
 //   var registrationTextforExeMeet=document.getElementById("registrationTextforExeMeet").value;
           
    var exeMeetcreatedDateTo=document.getElementById("exeMeetcreatedDateTo").value;
           
    var executiveMeetingStatus=document.getElementById("executiveMeetingStatus").value;
    var exeMeetStartTime=document.getElementById("exeMeetStartTime").value;
             
    var exeMeetEndTime=document.getElementById("exeMeetEndTime").value;
    var exeMeetStartmidDayFrom=document.getElementById("exeMeetStartmidDayFrom").value;
    var exeMeetEndmidDayTo=document.getElementById("exeMeetEndmidDayTo").value;
    var registrationLinkForEMeet=document.getElementById("registrationLinkForEMeet").value;
    var timeZone=document.getElementById("timeZone").value;
    if(executiveMeetingType==-1){
        //alert("please Enter Type");
        document.getElementById('resultMessage').innerHTML = "<font color=red>please Enter Type.</font>";
        return false;
    }
    else if(executiveMeetMonth==-1)
    {
        // alert("please Enter Month");
        document.getElementById('resultMessage').innerHTML = "<font color=red>please Enter Month.</font>";
        return false;
    }
    else if(exeMeetcreatedDateTo==""){
        //alert("please Enter Date");
        document.getElementById('resultMessage').innerHTML = "<font color=red>please Enter Date.</font>";
        return false;
    }
  
    else if(exeMeetStartTime==""){
        document.getElementById('resultMessage').innerHTML = "<font color=red>please Start Time.</font>";
        return false; 
    }
    else if(exeMeetEndTime==""){
        document.getElementById('resultMessage').innerHTML = "<font color=red>please End Time.</font>";
        return false; 
    }

    else if(registrationLinkForEMeet==""){
        // alert("please Enter Registration Link");
        document.getElementById('resultMessage').innerHTML = "<font color=red>please Enter Registration Link.</font>";
        return false;
    }

   
    else
    {
    // alert(jobtechnical);
    document.getElementById("load").style.display = 'block';
   
    $.ajax({
        //  url:'updateJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+'&location='+location+'&jobcountry='+jobcountry+'&jobstatus='+jobstatus+'&jobdescription='+jobdescription+'&jobtechnical='+jobtechnical+'&jobshiftskills='+jobshiftskills+'&attachmentLocation='+file+'&jobId='+jobId,//
        url:'updateExecitiveMeeting.action?executiveMeetingType='+executiveMeetingType+'&executiveMeetMonth='+executiveMeetMonth+"&exeMeetcreatedDateTo="+exeMeetcreatedDateTo+"&executiveMeetingStatus="+executiveMeetingStatus+"&exeMeetStartTime="+exeMeetStartTime+"&exeMeetEndTime="+exeMeetEndTime+"&exeMeetStartmidDayFrom="+exeMeetStartmidDayFrom+"&exeMeetEndmidDayTo="+exeMeetEndmidDayTo+"&registrationLinkForEMeet="+registrationLinkForEMeet+"&id="+exeMeetingId+"&timeZone="+timeZone,
        // alert(url);
        context: document.body,
        success: function(responseText) {
            var displaymessage = responseText;
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = "<font color=green>"+displaymessage+"</font>";//"<font color=green>File uploaded successfully</font>";
        // document.getElementById('fileType').value = "";
        },
        error: function(e){
            // alert('Error:111 ' + e);
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
        // document.getElementById('fileType').value = "";
        //  document.getElementById('profile').value="";
                
        }
    });
    
     }
		
    return false;
}

function enterdTime(element) {
    var x = element;

    var f=x.value;

    if(f.length==1 && f>1){
        //alert("0"+f);
        f="0"+f;
    }
    if(f.length==2){
        var s=f.substring(1,2);
        //alert(s);
        if(s==":" || s==" "){
            x.value ="0"+f.substring(0,1)+":";
        }
        else{
            x.value = f+":";
        }
    }
    else {
        x.value=f;
    }

}


function timeValidator(element) {
    var x = element;
    var f=x.value;

    if(f.length==1){

        f = "0"+f+":";

    }
   
    var s=f.split(':');



    if((s[0]>=0) && (s[0]<13) && ((s[1]>=0) && (s[1]<60)) ){


        if(s[1]==""){

            x.value =s[0]+':'+"00";
        }
        else if(s[1].length==1){
            x.value =s[0]+':'+"0"+s[1];
        // s[1]="0"+s[1];
        }
        else
        {
            x.value =s[0]+':'+s[1];
        }
    }
    else{
        x.value ="";
        alert('Enter time in 12 hours based')
    }
}
function addExecutiveMeetAttendees(){
    //    alert("hi");
    document.getElementById('resultMessage').innerHTML ='';
    hideRow('addTr');
    hideRow('editTr');
    hideRow("createdTr");
    
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Add Executive Meeting Attendees";
    showRow('addTr');
            
   
    document.getElementById("executiveMeetingAttendeeEmail").style.display='block';
            
    var overlay = document.getElementById('exeMeetAttendeesoverlay');
    var specialBox = document.getElementById('exeMeetAttendeesspecialBox');
   
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
            
//window.location = "jobSearch.action";
}
function doAddExecutiveMeetAttendees()
{
     
   
    var eMeetAccessType= document.getElementById("executiveMeetAccessType").value;
             
    var emeetAttendeesEmail=document.getElementById("executiveMeetingAttendeeEmail").value;
          
    var executiveMeetingAccessStatus=document.getElementById("executiveMeetingAccessStatus").value;
    if(emeetAttendeesEmail==-1){
        //  alert("please enter email");
        document.getElementById('resultMessage').innerHTML = "<font color=red>please enter email.</font>";
        return false;
    }
    else if(eMeetAccessType=='')
    {
        // alert("please enter Acccess Type");
        document.getElementById('resultMessage').innerHTML = "<font color=red>please enter Acccess Type.</font>";
        return false;
    }

   
    else
    {
        //hideRow('addTr');
        hideRow('editTr');
        setTimeout(disableFunction,1);
        document.getElementById("load").style.display = 'block';
     
        $.ajax({
            // url:'editJobposting.action?jobId='+jobId,//
            //url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
            url:'addExecitiveMeetAttendees.action?executiveMeetAccessType='+eMeetAccessType+'&executiveMeetingAttendeeEmail='+emeetAttendeesEmail+"&executiveMeetingAccessStatus="+executiveMeetingAccessStatus,
            context: document.body,
            success: function(responseText) {
                // displaymessage = responseText;
         
           
                document.getElementById("load").style.display = 'none';
                document.getElementById('resultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
            // document.getElementById('fileType').value = "";
            },
            error: function(e){
           
                document.getElementById("load").style.display = 'none';
                document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
     
                
            }
        });
    
    }
		
    return false;

}
function editExeMeetAttendees(Id){
    // alert(Id);
    hideRow('addTr');
    showRow('editTr');
    document.getElementById('resultMessage').innerHTML ='';
    document.getElementById("load").style.display = 'block';
    $.ajax({
        url:'editExeMeetingAttendees.action?id='+Id,//
        context: document.body,
        success: function(responseText) {
            // alert(responseText);
            var json = $.parseJSON(responseText);
             
            var Id = json["Id"];
            var Email1 = json["Email1"];
            var AccessType = json["AccessType"];
            var CurrStatus = json["CurrStatus"];
            var CreatedBy = json["CreatedBy"];
            var CreatedDate = json["CreatedDate"];
            var LoginId = json["LoginId"];
            
            // alert(AccessType);
            document.getElementById("executiveMeetAccessType").value=AccessType;
            document.getElementById("previousAttendeeStatus").value=CurrStatus;
            document.getElementById("loginId").value=LoginId;
              
            document.getElementById("executiveMeetingAttendeeEmail").style.display='none';
            document.getElementById("id").value = Id;
          
            document.getElementById("executiveMeetingAccessStatus").value=CurrStatus;
            document.getElementById("createdBy").innerHTML = CreatedBy;
            document.getElementById("createdDate").innerHTML = CreatedDate;
          
            document.getElementById("emailSpanId").innerHTML = '<font color=green size=2px>'+Email1+'</font>';
          
            document.getElementById("load").style.display = 'none';
        // getSlider(4,7);
        //getSlider(parseInt(jobExp[0],10),parseInt(jobExp[1],10));
        }, 
        error: function(e){
            document.getElementById("load").style.display = 'none';
            alert("error-->"+e);
        }
    });
    
    
    
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Edit Executive Meeting Attendees";
    var overlay = document.getElementById('exeMeetAttendeesoverlay');
    var specialBox = document.getElementById('exeMeetAttendeesspecialBox');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}
function doUpdateExeMeetingAttendeesDetails(){
     hideRow('addTr');
    //hideRow('editTr');
   
    var eMeetAccessType= document.getElementById("executiveMeetAccessType").value;
    var executiveMeetingAccessStatus=document.getElementById("executiveMeetingAccessStatus").value;
    var id=document.getElementById("id").value; 
    
     var previousStatus=document.getElementById("previousAttendeeStatus").value;
       var loginId=  document.getElementById("loginId").value;
    if(eMeetAccessType=='')
    {
        // alert("please enter Acccess Type");
        document.getElementById('resultMessage').innerHTML = "<font color=red>please enter Acccess Type.</font>";
        return false;
    }else {

    document.getElementById("updateButton").disabled = 'true';
    document.getElementById("load").style.display = 'block';
    
    $.ajax({
        //  url:'updateJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+'&location='+location+'&jobcountry='+jobcountry+'&jobstatus='+jobstatus+'&jobdescription='+jobdescription+'&jobtechnical='+jobtechnical+'&jobshiftskills='+jobshiftskills+'&attachmentLocation='+file+'&jobId='+jobId,//
        url:'updateExecitiveMeetingAttendeesDetails.action?executiveMeetAccessType='+eMeetAccessType+"&executiveMeetingAccessStatus="+executiveMeetingAccessStatus+"&id="+id+"&loginId="+loginId+"&previousStatus="+previousStatus,
        // alert(url);
        context: document.body,
        success: function(responseText) {
            var displaymessage = responseText;
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = "<font color=green>"+displaymessage+"</font>";//"<font color=green>File uploaded successfully</font>";
        // document.getElementById('fileType').value = "";
        },
        error: function(e){
            // alert('Error:111 ' + e);
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
        // document.getElementById('fileType').value = "";
        //  document.getElementById('profile').value="";
                
        }
    });
    }
    // }
		
    return false;
}

function toggleOverlayForExeMeetAttendees(){
    //alert("hi");
    document.getElementById('resultMessage').innerHTML ='';
    hideRow('addTr');
    hideRow('editTr');
    hideRow("createdTr");
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Add Executive Meeting";
    showRow('addTr');
    var overlay = document.getElementById('exeMeetAttendeesoverlay');
    var specialBox = document.getElementById('exeMeetAttendeesspecialBox');
    //  hideRow("approvedBy");
    //hideRow("tlcommentsTr");
    // hideRow("hrcommentsTr");
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
              
        document.getElementById("frmDBGrid").submit();
                
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
            
    //  window.location = "jobSearch.action";
    document.getElementById("frmDBGrid").submit();
}

function disableFunction() {
    //alert("indisable");
    document.getElementById("addButton").disabled = 'true';
}


function doPublishExecuteMeet(Element,emeetId){ 
   
    document.getElementById("load").style.display = 'block';
    $.ajax({
       
        //  url:'updateJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+'&location='+location+'&jobcountry='+jobcountry+'&jobstatus='+jobstatus+'&jobdescription='+jobdescription+'&jobtechnical='+jobtechnical+'&jobshiftskills='+jobshiftskills+'&attachmentLocation='+file+'&jobId='+jobId,//
        url:'doPublishEmmet.action?id='+emeetId,
        // alert(url);
        context: document.body,
        success: function(responseText) {
            alert(responseText);
            document.getElementById("load").style.display = 'none';
            document.getElementById('publishMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
        // document.getElementById('fileType').value = "";
        },
        error: function(e){
            // alert('Error:111 ' + e);
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
        // document.getElementById('fileType').value = "";
        //  document.getElementById('profile').value="";
                
        }
    });
    
    // }
		
    return false;
}

function editCompletedExeMeet(Id){
   
    hideRow('addTr');
    showRow('editTr');
    document.getElementById('resultMessage').innerHTML ='';
    document.getElementById("load").style.display = 'block';
    $.ajax({
        url:'editExeMeeting.action?id='+Id,//
        context: document.body,
        success: function(responseText) {
            // alert(responseText);
            var json = $.parseJSON(responseText);
                
            var Id = json["Id"];
            var Type = json["TYPE"];
            var Status = json["STATUS"];
            var StartTime = json["StartTime"];
            var EndTime = json["EndTime"];
            var MidDayTo = json["MidDayTo"];
            var MidDayFrom = json["MidDayFrom"];
            var EMeetDate = json["EMeetDate"];
            var EMeetRegistrationLink = json["EMeetRegistrationLink"];
            var CreatedBy = json["CreatedBy"];
            var CreatedDate = json["CreatedDate"];
            var TimeZone = json["TimeZone"];
            var Month = json["Month"];
             var VideoLink = json["VideoLink"];
          
                 
            document.getElementById("executiveMeetingStatus").value = Status;
            
            // alert(CreatedDate+"---"+CreatedBy);
            document.getElementById("executiveMeetingType").innerHTML = Type;
            // document.getElementById("executiveMeetMonth").value = jobtitle;
            document.getElementById("exeMeetcreatedDateTo").innerHTML = EMeetDate;
           
            document.getElementById("exeMeetStartTime").innerHTML = StartTime;
            document.getElementById("exeMeetEndTime").innerHTML = EndTime;
            document.getElementById("exeMeetStartmidDayFrom").innerHTML = MidDayFrom;
            document.getElementById("exeMeetEndmidDayTo").innerHTML = MidDayTo;
            document.getElementById("registrationLinkForEMeet").innerHTML = EMeetRegistrationLink;
            document.getElementById("exeMeetingId").value = Id;
            document.getElementById("executiveMeetMonth").innerHTML = Month;
            document.getElementById("timeZone").innerHTML = TimeZone;
            document.getElementById("createdBy").innerHTML = CreatedBy;
            document.getElementById("createdDate").innerHTML = CreatedDate;
            document.getElementById("videoLinkForEMeet").value = VideoLink;
            if(Status=="Completed"){
                showRow("emeetUrlTr");
                document.getElementById("emeetLink").innerHTML='';
                document.getElementById("emeetLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/events/emeets-replay.php?exeMeetId='+Id+'</font>';
            }
            
           
            document.getElementById("load").style.display = 'none';
       
            
        }, 
        error: function(e){
            document.getElementById("load").style.display = 'none';
            alert("error-->"+e);
        }
    });
    
    
    
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Edit ExecutiveMeeting";
    var overlay = document.getElementById('exeMeetoverlay');
    var specialBox = document.getElementById('exeMeetspecialBox');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}

function doUpdateCompletedExeMeeting(){
    
       hideRow('addTr');
  
   
    var exeMeetingId= document.getElementById("exeMeetingId").value;

           
    var executiveMeetingStatus=document.getElementById("executiveMeetingStatus").value;
    
    var videoLinkForEMeet=escape(document.getElementById("videoLinkForEMeet").value);
    
    if(executiveMeetingStatus=='Completed'){
        if(videoLinkForEMeet.length==0){
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please Enter the video link.</font>"
            return false;
        }
    }
    
    document.getElementById("load").style.display = 'block';
   
    $.ajax({
        url:'updateCompletedExecitiveMeeting.action?executiveMeetingStatus='+executiveMeetingStatus+"&videoLinkForEMeet="+videoLinkForEMeet+"&id="+exeMeetingId,
        // alert(url);
        context: document.body,
        success: function(responseText) {
            var displaymessage = responseText;
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = "<font color=green>"+displaymessage+"</font>";//"<font color=green>File uploaded successfully</font>";
        },
        error: function(e){
            // alert('Error:111 ' + e);
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
        // document.getElementById('fileType').value = "";
        //  document.getElementById('profile').value="";
                
        }
    });
    
    // }
		
    return false;
}


function doActiveExecuteMeet(Element,emeetId){ 
   
    document.getElementById("load").style.display = 'block';
    $.ajax({
       
        //  url:'updateJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+'&location='+location+'&jobcountry='+jobcountry+'&jobstatus='+jobstatus+'&jobdescription='+jobdescription+'&jobtechnical='+jobtechnical+'&jobshiftskills='+jobshiftskills+'&attachmentLocation='+file+'&jobId='+jobId,//
        url:'doActiveEmmet.action?id='+emeetId,
        // alert(url);
        context: document.body,
        success: function(responseText) {
          //  alert(responseText);
            document.getElementById("load").style.display = 'none';
            alert(responseText);
            document.getElementById('publishMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
        // document.getElementById('fileType').value = "";
        },
        error: function(e){
            // alert('Error:111 ' + e);
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
           
        }
    });
    
    // }
		
    return false;
}
//-*---new

function doPublishExeMeet(Element,emeetId,status){
    // alert('status-->'+status);
    if(Element.value = "P")
    {
        if(status=="Published"){
            alert("It is already Published");
        }
        else if(status=="Inactive")
        {
            //alert('in else iff');
            alert("InActive Record Cannot Be Published");
        }
        else{
            if(window.confirm("Do you want to change the status to publish")){
                window.location = "doPublishExeMeet.action?id="+emeetId;
            }
        }
    }
}

function doActiveExeMeet(Element,emeetId,status){
    // alert('status-->'+status);
    if(Element.value = "P")
    {
        if(status=="Active"){
            alert("It is already Active");
        }
        
        else{
            if(window.confirm("Do you want to change the status to Active")){
                window.location = "doActiveExeMeet.action?id="+emeetId;
            }
        }
    }
}

function fieldLengthValidatorr(element){
   //  alert("hi"+element);
  
    if(element.id=="registrationTextforExeMeet"){
        i=5000;
    }
     if(element.id=="registrationLinkForEMeet"){
        i=500;
    }
    if(element.id=="videoLinkForEMeet"){
        i=500;
    }
    if(element.value.replace(/^\s+|\s+$/g,"").length>i) {
            str=new String(element.value);
            element.value=str.substring(0,i);
          //  alert("The "+element.id+" length must be less than "+i+" characters");
             document.getElementById('resultMessage').innerHTML = "<font color=red>The " +element.id+ " length must be less than "+i+" characters</font>";
      
            element.focus();
            return false;
        }
        return true;
}

function isUrl(obj) {
   // alert(obj);
    document.getElementById('resultMessage').innerHTML = '';
  var  url_validate = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
if(!url_validate.test(obj.value)){
     document.getElementById('resultMessage').innerHTML = "<font color=red>Invalid Url</font>";
     obj.value = '';
  // alert('error');
}

}
