/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function clearData() {
    document.getElementById("event_id").value = '';
document.getElementById("eventType").value = '';
document.getElementById("eventStatus").value = '';
document.getElementById("eventtitle").value = '';
document.getElementById('selectDateFrom').value = '';
document.getElementById('selectDateTo').value = '';
document.getElementById("timeZone").value = '';
document.getElementById("eventLocation").value = '';
document.getElementById("transportation").value = '';
document.getElementById("eventDescription").value ='';
document.getElementById("startTime").value = '';
document.getElementById("midDayFrom").value ='';
document.getElementById("endTime").value = '';
document.getElementById("midDayTo").value ='';
document.getElementById('createdBy').innerHTML = '';
document.getElementById('createdDate').innerHTML = '';
document.getElementById("eventTypeLabel").innerHTML = '';
document.getElementById("eventBoldtitle").value = '';
           //  document.getElementById("eventRegluarTitle").value ='';
              document.getElementById("eventRegistrationLink").value = '';
             document.getElementById("contactUsEmail").value ='';
             
             document.getElementById("primaryTrack").value ='';
             document.getElementById("secondaryTrack").value ='';
             document.getElementById("eventDepartment").value ='';
}




function toggleOverlay(){
            document.getElementById('resultMessage').innerHTML ='';
            hideRow('createdTr');
           /* hideRow('editTr');
            hideRow("createdTr");
*/
clearData();

            document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Add EventPosting";
          //  showRow('addTr');
            var overlay = document.getElementById('overlay');
            var specialBox = document.getElementById('specialBox');

            overlay.style.opacity = .8;
            if(overlay.style.display == "block"){
            overlay.style.display = "none";
            specialBox.style.display = "none";

         //   document.getElementById("frmDBGrid").submit();

            }
            else {
            overlay.style.display = "block";
            specialBox.style.display = "block";
            }

         
           document.getElementById("frmDBGrid").submit();
        }
        
        
        
function addEventPost(){
     showRow("addTr");
    hideRow("editTr");
    document.getElementById("eventType").style.display='block';
            document.getElementById("eventTypeLabel").style.display='none';
    clearData();
            /*  //    alert("hi");
                   document.getElementById('resultMessage').innerHTML ='';
              // hideRow('addTr');
   // hideRow('editTr');
   //  hideRow("createdTr");
          
            document.getElementById("jobtitle").value = '';
           
            document.getElementById("jobqulification").value = '';
           
            document.getElementById("location").value = '';
           
            document.getElementById("jobstatus").value = '';
            document.getElementById("jobdescription1").value = '';
            document.getElementById("jobdescription2").value = '';
            document.getElementById("jobDepartment").value = '';
            document.getElementById("jobHireType").value = '';*/
           
            document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Add EventPosting";
           // showRow('addTr');
            
            //------------------- Slider code start ---------------
            
            //getSlider(4,10);
            
          
            
            
            //--------------------Slider Code end -------------------
            var overlay = document.getElementById('overlay');
            var specialBox = document.getElementById('specialBox');
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
        




function doEventPost()
{
   
 /*  Quarterly meet data start*/   
var eventType= document.getElementById("eventType").value;
var eventStatus=document.getElementById("eventStatus").value;
var eventtitle=document.getElementById("eventtitle").value;
var selectDateFrom=document.getElementById("selectDateFrom").value;
var selectDateTo=document.getElementById("selectDateTo").value;
var timeZone=document.getElementById("timeZone").value;
var eventLocation=document.getElementById("eventLocation").value;
var transportation=document.getElementById("transportation").value;
var eventDescription=document.getElementById("eventDescription").value;
var event_time_from= document.getElementById("startTime").value;
var midday_from= document.getElementById("midDayFrom").value ;
var event_time_to = document.getElementById("endTime").value;
var midday_to = document.getElementById("midDayTo").value;
/*  Quarterly meet data end*/   
 
/* External/Internal extra data start */
var eventBoldtitle = document.getElementById("eventBoldtitle").value;
//var eventRegluarTitle = document.getElementById("eventRegluarTitle").value;
var eventRegistrationLink = document.getElementById("eventRegistrationLink").value;
var contactUsEmail = document.getElementById("contactUsEmail").value;

var primaryTrack = document.getElementById("primaryTrack").value;
var secondaryTrack = document.getElementById("secondaryTrack").value;
var eventDepartment = document.getElementById("eventDepartment").value;

/* External extra data start */
  
 /* Conference data */ 
 var conferenceUrl = document.getElementById("conferenceUrl").value;
 /* Conference data */
  
  var eventAfterVideoUrl = document.getElementById("eventAfterVideoUrl").value;
  
  
if(eventType.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please select Event Type.</font>";
   }else if(selectDateFrom.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please select  Date from.</font>";
   }
   else if(selectDateTo.length==0&&eventType=='C'){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please select  Date To.</font>";
   }
   else if(eventLocation.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  location.</font>";
   }else if(transportation.trim().length==0 && eventType == 'Q'){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  transportation.</font>";
   } else if(eventDescription.trim().length==0 && (eventType != 'C'&&eventType != 'IEE')){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  description.</font>";
   } else if((eventType == 'I' || eventType == 'E')&&eventBoldtitle.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  Event bold title for Webinar before/After pages.</font>";
       } /*else if((eventType == 'I' || eventType == 'E')&&eventRegluarTitle.trim().length==0){
           
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  Event Regular title for Webinar before/After pages.</font>";
       }*/ else if((eventType == 'I' || eventType == 'E')&&eventRegistrationLink.trim().length==0){
           
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  Event Registration link for Webinar before/After pages.</font>";
       } else if((eventType == 'I' || eventType == 'E')&&contactUsEmail.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  Contact Us Email for Webinar before page.</font>";
       }else if((eventType == 'I' || eventType == 'E')&&primaryTrack.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please select  Primary Track for event.</font>";
       }else if((eventType == 'I' )&&eventDepartment.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please select  Department for the event.</font>";
       } else if((eventType == 'C')&&conferenceUrl.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter Conference website url.</font>";
       }else if((eventType == 'I'||eventType == 'E')&&eventAfterVideoUrl.trim().length==0&&eventStatus=='Closed'){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter after video url.</font>";
       }
   
   //eventAfterVideoUrl
        //setTimeout(disableFunction,1);
   
   else
       {
           if(eventType!='C'){
               selectDateTo = selectDateFrom;
           }
       
    document.getElementById("load").style.display = 'block';
    hideRow("addTr");
        $.ajax({
             
           // url:'editJobposting.action?jobId='+jobId,//
           //url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
        //   url:'addEventposting.action?eventTitle='+escape(eventtitle)+'&eventType='+eventType+"&eventStatus="+eventStatus+"&startDate="+selectDateFrom+"&endDate="+selectDateTo+"&timeZone="+timeZone+"&eventLocation="+escape(eventLocation)+"&transportation="+escape(transportation)+"&eventDescription="+escape(eventDescription)+"&startTime="+event_time_from+"&endTime="+event_time_to+"&midDayFrom="+midday_from+"&midDayTo="+midday_to+"&eventBoldtitle="+escape(eventBoldtitle)+"&eventRegluarTitle="+escape(eventRegluarTitle)+"&eventRegistrationLink="+escape(eventRegistrationLink)+"&contactUsEmail="+escape(contactUsEmail)+"&conferenceUrl="+escape(conferenceUrl)+"&primaryTrack="+primaryTrack+"&secondaryTrack="+secondaryTrack+"&eventDepartment="+eventDepartment+"&eventAfterVideoUrl="+escape(eventAfterVideoUrl),
           url:'addEventposting.action?eventTitle='+escape(eventtitle)+'&eventType='+eventType+"&eventStatus="+eventStatus+"&startDate="+selectDateFrom+"&endDate="+selectDateTo+"&timeZone="+timeZone+"&eventLocation="+escape(eventLocation)+"&transportation="+escape(transportation)+"&eventDescription="+escape(eventDescription)+"&startTime="+event_time_from+"&endTime="+event_time_to+"&midDayFrom="+midday_from+"&midDayTo="+midday_to+"&eventBoldtitle="+escape(eventBoldtitle)+"&eventRegistrationLink="+escape(eventRegistrationLink)+"&contactUsEmail="+escape(contactUsEmail)+"&conferenceUrl="+escape(conferenceUrl)+"&primaryTrack="+primaryTrack+"&secondaryTrack="+secondaryTrack+"&eventDepartment="+eventDepartment+"&eventAfterVideoUrl="+escape(eventAfterVideoUrl),
            context: document.body,
            success: function(responseText) {
               // displaymessage = responseText;
         
           
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
        // document.getElementById('fileType').value = "";
        },
        error: function(e){
           showRow("addTr");
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
     
                
        }
    });
    
       }
		
    return false;

}



function editEventPost(eventId){
 
     document.getElementById('resultMessage').innerHTML ='';
  
    document.getElementById("load").style.display = 'block';
   $.ajax({
            url:'editEventposting.action?eventId='+eventId,//
            context: document.body,
            success: function(responseText) {
               // alert(responseText);
                var json = $.parseJSON(responseText);
                
                var event_id = json["event_id"];
              
                var event_title = json["event_title"];
                var event_description = json["event_description"];
               
                var event_startdate = json["event_startdate"];
             
                var evetnt_enddate = json["evetnt_enddate"];
                var event_time_from = unescape(json["event_time_from"]);
             
                var event_time_to = json["event_time_to"];
                var midday_from = json["midday_from"];
                var midday_to = json["midday_to"];
                var timezone = json["timezone"];
                var location = json["location"];
                var transport = json["transport"];
                var createdby = json["createdby"];
                var createddate = json["createddate"];
                var STATUS = json["STATUS"];
                var WebinarType = json["WebinarType"];
                var VideoLink = json["VideoLink"];
                 var event_bold_Title = '';
                var event_tagline = '';
                var OrganizerEmail = '';
                var RegistrationLink = '';
                
                var PrimaryTrack = '';
                var SecondaryTrack = '';
                var Department = '';
             //   var SeriesId = 0;
               // var IsAssociated = '';
               // var AssociatedEventId = '';
                 document.getElementById("event_id").value = event_id;
                 document.getElementById("seriesId").value = json["SeriesId"];;
            document.getElementById("eventType").value = WebinarType;
            
             document.getElementById("tempEventDate").value = event_startdate;
             document.getElementById("currentDate").value = json["CurrentDate"];
             
             //CurrentDate
                // External /Internal webinars fields start
                if(WebinarType=='Q'){
                    showRow("transportationTr");
                    hideRow("eventBoldTr");
                // hideRow("eventRegularTr");
                 hideRow("eventRegistrationLinkTr");
                 hideRow("contactUsTr");
                  showRow("eventDescriptionTr");
                  hideRow("conferenceLinkTr");
                   hideRow("eventTrackTr");
       hideRow("eventDepartmentTr");
        showRow("timeTr");
         document.getElementById("timeZoneTextId").style.display='block';
       document.getElementById("timeZoneLabelId").style.display='block';
                }else if(WebinarType=='I'||WebinarType=='E'){
                     hideRow("transportationTr");
                     showRow("timeTr");
                      document.getElementById("timeZoneTextId").style.display='block';
       document.getElementById("timeZoneLabelId").style.display='block';
                    // showRow("seriesTr");
                     
                   // IsAssociated = json["IsAssociated"];
                  //  document.getElementById("isAssociated").value = IsAssociated;
                   //  if(IsAssociated=='YES'){
                       //  AssociatedEventId = json["AsociatedEventId"];
                          
                        //  getEventSeries(AssociatedEventId);
                          //alert(AssociatedEventId);
                          
                     //}
                    // 
                    
                 event_bold_Title = json["Page_Title"];
                 //event_tagline = json["event_tagline"];
                 OrganizerEmail = json["OrganizerEmail"];
                 RegistrationLink = json["RegistrationLink"];
                 PrimaryTrack = json["PrimaryTrack"];
                 SecondaryTrack = json["SecondaryTrack"];
                 Department = json["Department"];
                 
                  document.getElementById("eventBoldtitle").value = event_bold_Title;
            // document.getElementById("eventRegluarTitle").value =event_tagline;
              document.getElementById("eventRegistrationLink").value = RegistrationLink;
             document.getElementById("contactUsEmail").value =OrganizerEmail;
             
             document.getElementById("primaryTrack").value =PrimaryTrack;
             document.getElementById("secondaryTrack").value =SecondaryTrack;
             document.getElementById("eventDepartment").value =Department;
             
             
                 showRow("eventBoldTr");
                // showRow("eventRegularTr");
                 showRow("eventRegistrationLinkTr");
                 showRow("contactUsTr");
                 showRow("eventDescriptionTr");
                  hideRow("conferenceLinkTr");
                  showRow("eventTrackTr");
                  if(WebinarType=='I'){
                       showRow("eventDepartmentTr");
                        document.getElementById("depotLink").innerHTML='';
                        document.getElementById("depotLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/events/internal-webinar-depot.php?eventId='+event_id+'</font>';   
                        showRow("depotUrlTr");
                        
                        document.getElementById("beforeLink").innerHTML='';
                        document.getElementById("beforeLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/events/internal-webinar-before.php?eventId='+event_id+'</font>';   
                        showRow("beforeUrlTr");
                        
                       
                  }else if(WebinarType=='E'){
                      document.getElementById("depotLink").innerHTML='';
                       document.getElementById("depotLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/events/external-webinar-depot.php?eventId='+event_id+'</font>';   
                        showRow("depotUrlTr");
                        
                         document.getElementById("beforeLink").innerHTML='';
                        document.getElementById("beforeLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/events/webinar-before.php?eventId='+event_id+'</font>';   
                        showRow("beforeUrlTr");
                  }
                }
                if(WebinarType=='Q'){
                  //  showRow("timeTr");
                      document.getElementById("depotLink").innerHTML='';
                       document.getElementById("depotLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/events/qmeet-rsvp.php?eventId='+event_id+'</font>';   
                        showRow("depotUrlTr");
                  }
               // Conference event field
               var event_redirect = '';
                if(WebinarType=='C'){
                    hideRow("timeTr");
                   hideRow("eventBoldTr");
 //   hideRow("eventRegularTr");
    hideRow("transportationTr");
    hideRow("eventRegistrationLinkTr");
    hideRow("contactUsTr");
    showRow("conferenceLinkTr");
     hideRow("eventDescriptionTr");
      hideRow("eventTrackTr");
       hideRow("eventDepartmentTr");
                    event_redirect = json["event_redirect"];
                    document.getElementById("conferenceUrl").value =event_redirect;
                     document.getElementById("timeZoneTextId").style.display='none';
       document.getElementById("timeZoneLabelId").style.display='none';
                }
                
                if(WebinarType=='IEE'){
                     document.getElementById("timeZoneTextId").style.display='block';
       document.getElementById("timeZoneLabelId").style.display='block';
                }
                // External /Internal webinars fields end
                
                
            
            document.getElementById("eventType").style.display='none';
            document.getElementById("eventTypeLabel").style.display='block';
            //eventTypeLabel
            
            if(WebinarType=='Q'){
             document.getElementById("eventTypeLabel").innerHTML = '<font color=green size=2px>Quarterly Meet</font>';   
            }else if(WebinarType=='I'){
                document.getElementById("eventTypeLabel").innerHTML = '<font color=green size=2px>Internal Webinar</font>';
            }else if(WebinarType=='E'){
                document.getElementById("eventTypeLabel").innerHTML = '<font color=green size=2px>External Webinar</font>';
            }else if(WebinarType=='IEE'){
                document.getElementById("eventTypeLabel").innerHTML = '<font color=green size=2px>Internal Employee Experience</font>';
            }else if(WebinarType=='C'){
                document.getElementById("eventTypeLabel").innerHTML = '<font color=green size=2px>Conferences</font>';
            }
           document.getElementById("eventAfterVideoUrl").value = VideoLink;
           if(STATUS=='Closed'){
                showRow("afterVideoTr");
                
            }else {
                hideRow("afterVideoTr");
              //  document.getElementById("eventAfterVideoUrl").value ='';
            }
            
             getDateFieldAppear(WebinarType);
         
            document.getElementById("eventStatus").value = STATUS;
        
            document.getElementById("eventtitle").value = event_title;
           
             document.getElementById('selectDateFrom').value = event_startdate;
             document.getElementById('selectDateTo').value = evetnt_enddate;
             document.getElementById("timeZone").value = timezone;
             document.getElementById("eventLocation").value = location;
             document.getElementById("transportation").value = transport;
             document.getElementById("eventDescription").value =event_description;
             
              document.getElementById("startTime").value = event_time_from;
             document.getElementById("midDayFrom").value =midday_from;
              document.getElementById("endTime").value = event_time_to;
             document.getElementById("midDayTo").value =midday_to;
             
              document.getElementById('createdBy').innerHTML = createdby;
              document.getElementById('createdDate').innerHTML = createddate;
              
              
              
             
              
              
              
           
                 document.getElementById("load").style.display = 'none';
                 checkEventDate();
             
                 
            }, error: function(e){
                document.getElementById("load").style.display = 'none';
                alert("error-->"+e);
            }
        });
    
    
    hideRow("addTr");
    showRow("editTr");
     showRow('createdTr');
   document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Edit EventPosting";
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}





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


function doUpdateEventPost()
{
    var event_id= document.getElementById("event_id").value;
            var eventType= document.getElementById("eventType").value;
          
             var eventStatus=document.getElementById("eventStatus").value;
          
             var eventtitle=document.getElementById("eventtitle").value;
         
             var selectDateFrom=document.getElementById("selectDateFrom").value;
           
             var selectDateTo=document.getElementById("selectDateTo").value;
              var timeZone=document.getElementById("timeZone").value;
             
             var eventLocation=document.getElementById("eventLocation").value;
             var transportation=document.getElementById("transportation").value;
             
             var eventDescription=document.getElementById("eventDescription").value;
           
           var event_time_from= document.getElementById("startTime").value;
            var midday_from= document.getElementById("midDayFrom").value ;
              var event_time_to = document.getElementById("endTime").value;
             var midday_to = document.getElementById("midDayTo").value;
            
           /* External extra data start */
var eventBoldtitle = document.getElementById("eventBoldtitle").value;
//var eventRegluarTitle = document.getElementById("eventRegluarTitle").value;
var eventRegistrationLink = document.getElementById("eventRegistrationLink").value;
var contactUsEmail = document.getElementById("contactUsEmail").value;


var primaryTrack = document.getElementById("primaryTrack").value;
var secondaryTrack = document.getElementById("secondaryTrack").value;
var eventDepartment = document.getElementById("eventDepartment").value;
/* External extra data start */
                
      /* Conference data */ 
 var conferenceUrl = document.getElementById("conferenceUrl").value;
 /* Conference data */          
     var eventAfterVideoUrl = document.getElementById("eventAfterVideoUrl").value;            
                
          //   var isAssociated = document.getElementById("isAssociated").value;        
             // var associatedEventId = document.getElementById("associatedEventId").value;        
            
            var seriesId = document.getElementById("seriesId").value;         
            
            
             var dateDiff = checkEventDate();
         //alert(dateDiff);
   if(eventType.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please select Event Type.</font>";
   }else if(selectDateFrom.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please select  Date from.</font>";
   }
   else if(selectDateTo.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please select  Date To.</font>";
   }
   else if(eventLocation.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  location.</font>";
   }else if(transportation.trim().length==0 && eventType == 'Q'){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  transportation.</font>";
   } else if(eventDescription.trim().length==0 && (eventType != 'C'&&eventType != 'IEE')){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  description.</font>";
   }else if((eventType == 'I' || eventType == 'E')&&eventBoldtitle.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  Event page title for Webinar before/After pages.</font>";
       } /*else if((eventType == 'I' || eventType == 'E')&&eventRegluarTitle.trim().length==0){
           
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  Event Regular title for Webinar before/After pages.</font>";
       }*/ else if((eventType == 'I' || eventType == 'E')&&eventRegistrationLink.trim().length==0){
           
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  Event Registration link for Webinar before/After pages.</font>";
       } else if((eventType == 'I' || eventType == 'E')&&contactUsEmail.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  Contact Us Email for Webinar before page.</font>";
       }else if((eventType == 'I' || eventType == 'E')&&primaryTrack.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please select  Primary Track for event.</font>";
       }else if((eventType == 'I' )&&eventDepartment.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please select  Department for the event.</font>";
       }else if((eventType == 'C')&&conferenceUrl.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter Conference website url.</font>";
       } else if((eventType == 'I'||eventType == 'E')&&eventAfterVideoUrl.trim().length==0&&eventStatus=='Closed'){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter after video url.</font>";
       }else if(parseInt(dateDiff,10)<0){
           document.getElementById('resultMessage').innerHTML = "<font color=red>Event date you selected is not valid.</font>";
            document.getElementById('selectDateFrom').value = document.getElementById("tempEventDate").value;
       }
   
        //setTimeout(disableFunction,1);
   
   else
       {
    
           if(eventType!='C'){
               selectDateTo = selectDateFrom;
           }
      
   // hideRow('addTr');
   // hideRow('editTr');
    document.getElementById("load").style.display = 'block';
  /*  $.ajaxFileUpload({
    //    url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills+"&attachmentLocation="+file,//
        url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,//
        secureuri:false,//false
        fileElementId:'file',//id  <input type="file" id="file" name="file" />
        dataType: 'json',// json
        success: function(data,status){
           */ 
         //  alert(eventAfterVideoUrl);
         $.ajax({
            
           // url:'editJobposting.action?jobId='+jobId,//
           //url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
         //  url:'updateEventposting.action?eventId='+event_id+'&eventTitle='+escape(eventtitle)+'&eventType='+eventType+"&eventStatus="+eventStatus+"&startDate="+selectDateFrom+"&endDate="+selectDateTo+"&timeZone="+timeZone+"&eventLocation="+escape(eventLocation)+"&transportation="+escape(transportation)+"&eventDescription="+escape(eventDescription)+"&startTime="+event_time_from+"&endTime="+event_time_to+"&midDayFrom="+midday_from+"&midDayTo="+midday_to+"&eventBoldtitle="+escape(eventBoldtitle)+"&eventRegluarTitle="+escape(eventRegluarTitle)+"&eventRegistrationLink="+escape(eventRegistrationLink)+"&contactUsEmail="+contactUsEmail+"&conferenceUrl="+escape(conferenceUrl)+"&primaryTrack="+primaryTrack+"&secondaryTrack="+secondaryTrack+"&eventDepartment="+eventDepartment+"&eventAfterVideoUrl="+escape(eventAfterVideoUrl),
           url:'updateEventposting.action?eventId='+event_id+'&eventTitle='+escape(eventtitle)+'&eventType='+eventType+"&eventStatus="+eventStatus+"&startDate="+selectDateFrom+"&endDate="+selectDateTo+"&timeZone="+timeZone+"&eventLocation="+escape(eventLocation)+"&transportation="+escape(transportation)+"&eventDescription="+escape(eventDescription)+"&startTime="+event_time_from+"&endTime="+event_time_to+"&midDayFrom="+midday_from+"&midDayTo="+midday_to+"&eventBoldtitle="+escape(eventBoldtitle)+"&eventRegistrationLink="+escape(eventRegistrationLink)+"&contactUsEmail="+contactUsEmail+"&conferenceUrl="+escape(conferenceUrl)+"&primaryTrack="+primaryTrack+"&secondaryTrack="+secondaryTrack+"&eventDepartment="+eventDepartment+"&eventAfterVideoUrl="+escape(eventAfterVideoUrl)+"&seriesId="+seriesId,
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


function getDateFieldAppear(objValue){
if(objValue=='C'){
    document.getElementById("selectDateTo").value='';
     document.getElementById("fromDateLabelId").innerHTML = 'Event&nbsp;Date&nbspFrom:'
     var toDateLabelId = document.getElementById("toDateLabelId");
            if (toDateLabelId.style.display == "none")
                toDateLabelId.style.display = "block";
      var toDateTextId = document.getElementById("toDateTextId");
            if (toDateTextId.style.display == "none")
                toDateTextId.style.display = "block";      
     
  
 }else {
     document.getElementById("selectDateTo").value='';
     document.getElementById("fromDateLabelId").innerHTML = 'Event&nbsp;Date:'
     var toDateLabelId = document.getElementById("toDateLabelId");
            if (toDateLabelId.style.display == "block")
                toDateLabelId.style.display = "none";
      var toDateTextId = document.getElementById("toDateTextId");
            if (toDateTextId.style.display == "block")
                toDateTextId.style.display = "none";   
 }
}

        
function getEventType(obj){
//eventRegistrationLinkTr
 getDateFieldAppear(obj.value);
    if(obj.value=='I'){
        document.getElementById("eventtitle").value='Internal Webinar :'
      //   hideRow("addTr");
    showRow("eventBoldTr");
  //  showRow("eventRegularTr");
    hideRow("transportationTr");
     showRow("eventRegistrationLinkTr");
      showRow("contactUsTr");
     hideRow("conferenceLinkTr");
     
       showRow("eventDescriptionTr");
       showRow("eventTrackTr");
       showRow("eventDepartmentTr");
       showRow("timeTr");
       document.getElementById("timeZoneTextId").style.display='block';
       document.getElementById("timeZoneLabelId").style.display='block';
       //timeZoneTextId
       //timeZoneLabelId
        //eventBoldTr
        //eventRegularTr
        
    }else if(obj.value=='E'){
        document.getElementById("eventtitle").value='Technical Webinar :'
      //   hideRow("addTr");
    showRow("eventBoldTr");
  //  showRow("eventRegularTr");
    hideRow("transportationTr");
    showRow("eventRegistrationLinkTr");
    showRow("contactUsTr");
    hideRow("conferenceLinkTr");
      showRow("eventDescriptionTr");
       showRow("eventTrackTr");
       hideRow("eventDepartmentTr");
       showRow("timeTr");
       document.getElementById("timeZoneTextId").style.display='block';
       document.getElementById("timeZoneLabelId").style.display='block';
    //contactUsTr
        //eventBoldTr
        //eventRegularTr
        
    }else if(obj.value=='C'){
        document.getElementById("eventtitle").value=''
      //   hideRow("addTr");
    hideRow("eventBoldTr");
  //  hideRow("eventRegularTr");
    hideRow("transportationTr");
    hideRow("eventRegistrationLinkTr");
    hideRow("contactUsTr");
    showRow("conferenceLinkTr");
     hideRow("eventDescriptionTr");
     hideRow("eventTrackTr");
       hideRow("eventDepartmentTr");
       hideRow("timeTr");
     document.getElementById("timeZoneTextId").style.display='none';
       document.getElementById("timeZoneLabelId").style.display='none';
    //contactUsTr
        //eventBoldTr
        //eventRegularTr
        
    }else if(obj.value=='Q'){
        document.getElementById("eventtitle").value='';
         hideRow("eventBoldTr");
   // hideRow("eventRegularTr");
    showRow("transportationTr");
    hideRow("eventRegistrationLinkTr");
    hideRow("contactUsTr");
     hideRow("conferenceLinkTr");
       showRow("eventDescriptionTr");
        hideRow("eventTrackTr");
       hideRow("eventDepartmentTr");
       showRow("timeTr");
        document.getElementById("timeZoneTextId").style.display='block';
       document.getElementById("timeZoneLabelId").style.display='block';
    }else if(obj.value=='IEE'){
         hideRow("eventBoldTr");
    //hideRow("eventRegularTr");
    hideRow("transportationTr");
    hideRow("eventRegistrationLinkTr");
    hideRow("contactUsTr");
     hideRow("conferenceLinkTr");
       hideRow("eventDescriptionTr");
        hideRow("eventTrackTr");
       hideRow("eventDepartmentTr");
        showRow("timeTr");
         document.getElementById("timeZoneTextId").style.display='block';
       document.getElementById("timeZoneLabelId").style.display='block';
    }
    document.getElementById('resultMessage').innerHTML = '';
}


function getEventSpeakers(eventId,ObjectType){
    window.location = "getEventSpeakers.action?eventId="+eventId+"&objectType="+ObjectType;
}


function addEventSpeaker() {
    showRow("addTr");
    hideRow('editTr');
            /*  //    alert("hi");
                   document.getElementById('resultMessage').innerHTML ='';
              // hideRow('addTr');
   // hideRow('editTr');
   showRow("addTr");
          
            document.getElementById("jobtitle").value = '';
           
            document.getElementById("jobqulification").value = '';
           
            document.getElementById("location").value = '';
           
            document.getElementById("jobstatus").value = '';
            document.getElementById("jobdescription1").value = '';
            document.getElementById("jobdescription2").value = '';
            document.getElementById("jobDepartment").value = '';
            document.getElementById("jobHireType").value = '';*/
           
            document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Add Speaker";
           // showRow('addTr');
            
            //------------------- Slider code start ---------------
            
            //getSlider(4,10);
            
          
            
            
            //--------------------Slider Code end -------------------
            var overlay = document.getElementById('overlay');
            var specialBox = document.getElementById('specialBox');
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


function closeSpeakerOverlay() {
      document.getElementById('resultMessage').innerHTML ='';


            document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Add Speaker";
          //  showRow('addTr');
            var overlay = document.getElementById('overlay');
            var specialBox = document.getElementById('specialBox');

            overlay.style.opacity = .8;
            if(overlay.style.display == "block"){
            overlay.style.display = "none";
            specialBox.style.display = "none";

         //   document.getElementById("frmDBGrid").submit();
         window.location = "getEventSpeakers.action?eventId="+document.getElementById('eventId').value;
            }
            else {
            overlay.style.display = "block";
            specialBox.style.display = "block";
            }
}


function doAddSpeaker() {
    



   var speakerName= document.getElementById("speakerName").value;
var designation=document.getElementById("designation").value;
var company=escape(document.getElementById("company").value);
var speakerType=document.getElementById("speakerType").value;
var eventId=document.getElementById("eventId").value;
var status = document.getElementById("status").value;
var primarySpeakerExist = document.getElementById("primarySpeakerExist").value;
            
if(speakerName.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter speaker name.</font>";
   }else if(designation.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  designation.</font>";
   }
   else if(company.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter company name.</font>";
   }
   else if(speakerType.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please select speaker type.</font>";
   }else if(parseInt(speakerType,10)==1&&primarySpeakerExist=='YES'){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Primary speaker already exsit.Please Change SpeakerType.</font>";
   }
   
  
   else
       {
    document.getElementById("load").style.display = 'block';
    hideRow("addTr");
 
        $.ajax({
            
           // url:'editJobposting.action?jobId='+jobId,//
           //url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
           url:'addEventSpeaker.action?speakerName='+speakerName+'&designation='+designation+"&company="+company+"&speakerType="+speakerType+"&eventId="+eventId+"&status="+status,
            context: document.body,
            success: function(responseText) {
               // displaymessage = responseText;
         
           
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
        // document.getElementById('fileType').value = "";
        },
        error: function(e){
            showRow("addTr");
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
     
                
        }
    });
    
       }
		
    return false;
}


function getSpeakerDetails(speakerId){
 hideRow("addTr");
    hideRow("editTr");
     document.getElementById('resultMessage').innerHTML ='';
  
    document.getElementById("load").style.display = 'block';
   $.ajax({
            url:'editEventSpeaker.action?speakerId='+speakerId,//
            context: document.body,
            success: function(responseText) {
               // alert(responseText);
                var json = $.parseJSON(responseText);
                //Id,event_id,event_speaker,event_designation,primary_speaker,Company
                var Id = json["Id"];
              
                var event_id = json["event_id"];
                var event_speaker = json["event_speaker"];
               
                var event_designation = json["event_designation"];
             
                var primary_speaker = json["primary_speaker"];
                var Company = unescape(json["Company"]);
              var Status = json["Status"];
             
                
                
                document.getElementById("speakerId").value = Id;
                document.getElementById("eventId").value = event_id;
                document.getElementById("speakerName").value = event_speaker;
                document.getElementById("designation").value = event_designation;
                document.getElementById("company").value = Company;
                document.getElementById("speakerType").value = primary_speaker;
                document.getElementById("status").value = Status;
           
                 document.getElementById("load").style.display = 'none';
              showRow("editTr");
                 
            }, error: function(e){
                document.getElementById("load").style.display = 'none';
                alert("error-->"+e);
            }
        });
    
    
    
     
   document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Edit EventPosting";
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}



function doUpdateSpeaker() {
     var speakerName= document.getElementById("speakerName").value;
var designation=document.getElementById("designation").value;
var company=escape(document.getElementById("company").value);
var speakerType=document.getElementById("speakerType").value;
var eventId=document.getElementById("eventId").value;
var status = document.getElementById("status").value;
var speakerId = document.getElementById("speakerId").value;
var primarySpeakerExist = document.getElementById("primarySpeakerExist").value;
            
if(speakerName.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter speaker name.</font>";
   }else if(designation.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  designation.</font>";
   }
   else if(company.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter company name.</font>";
   }
   else if(speakerType.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please select speaker type.</font>";
   }else if(parseInt(speakerType,10)==1&&primarySpeakerExist=='YES'){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Primary speaker already exsit.Please Change SpeakerType.</font>";
   }
   
  
   else
       {
    document.getElementById("load").style.display = 'block';
    
        $.ajax({
           // url:'editJobposting.action?jobId='+jobId,//
           //url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
           url:'updateEventSpeaker.action?speakerName='+speakerName+'&designation='+designation+"&company="+company+"&speakerType="+speakerType+"&eventId="+eventId+"&status="+status+"&speakerId="+speakerId,
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


function getVideoTr(videoObj){

var eventType = document.getElementById("eventType").value;

if(videoObj.value=='Closed'&&(eventType=='I'||eventType=='E')){
    showRow("afterVideoTr");
  //  document.getElementById("afterVideoTr").style.display='block'
}else {
    document.getElementById("eventAfterVideoUrl").value = '';
    hideRow("afterVideoTr");
}
}
//getDetails


function getDetails(id){
    
    var tempTableName = document.getElementById('tempTableName').value;
    
 //hideRow("addTr");
    //hideRow("editTr");
     document.getElementById('resultMessage').innerHTML ='';
  
    document.getElementById("load").style.display = 'block';
   $.ajax({
            url:'getWebsiteInfoDetails.action?infoId='+id+"&tableName="+tempTableName,//
            context: document.body,
            success: function(responseText) {
               // alert(responseText);
                var json = $.parseJSON(responseText);
                //Id,event_id,event_speaker,event_designation,primary_speaker,Company
                
                if(tempTableName=='tblContactus') {
                    var Id = json["Id"];
                    var Fname = json["Fname"];
                    var Lname = json["Lname"];
                    var Email = json["Email"];
                    var Organization = json["Organization"];
                    var Designation = unescape(json["Designation"]);
                    var WorkPhone = json["WorkPhone"];
                    var City = json["City"];
                    var State = json["State"];
                    var Country = json["Country"];
                    var Zip = json["Zip"];
                    var Description = json["Description"];
                    var CreatedDate = json["CreatedDate"];


                    document.getElementById("firstName").value = Fname;
                    document.getElementById("lastName").value = Lname;
                    document.getElementById("email").value = Email;
                    document.getElementById("organization").value = Organization;
                    document.getElementById("designation").value = Designation;
                    document.getElementById("workPhone").value = WorkPhone;
                    document.getElementById("city").value = City;
                    document.getElementById("state").value = State;
                    document.getElementById("country").value = Country;
                    document.getElementById("zip").value = Zip;
                    document.getElementById("createdDate").innerHTML = '<font size=2px color=green>'+CreatedDate+'</font>';
                    document.getElementById("comments").value = Description;

                    document.getElementById("load").style.display = 'none';
                    document.getElementById("headerLabel").innerHTML="Contact Details";
                }else if(tempTableName=='tblEmpVerfication'){
                      var Id = json["ID"];
                    var FirstName = json["FirstName"];
                    var LastName = json["LastName"];
                    var EmailId = json["EmailId"];
                    var Organization = json["Organization"];
                    var Designation = unescape(json["Designation"]);
                    var Phone = json["Phone"];
                    var EmployeeName = json["EmployeeName"];
                    var EmployeeDesignation = json["EmployeeDesignation"];
                    var Department = json["Department"];
                    var EmployeeID = json["EmployeeID"];
                    var EmploymentStarted = json["EmploymentStarted"];
                    var EmploymentEnded = json["EmploymentEnded"];
                    var EmploymentLocation = json["EmploymentLocation"];
                    var CreatedDate = json["CreatedDate"];
                    
                    
                     document.getElementById("firstName").value = FirstName;
                    document.getElementById("lastName").value = LastName;
                    document.getElementById("email").value = EmailId;
                    document.getElementById("organization").value = Organization;
                    document.getElementById("designation").value = Designation;
                    document.getElementById("workPhone").value = Phone;
                    document.getElementById("employeeName").value = EmployeeName;
                    document.getElementById("verifyDesignation").value = EmployeeDesignation;
                    document.getElementById("department").value = Department;
                    document.getElementById("employeeId").value = EmployeeID;
                    document.getElementById("employeeStarted").value = EmploymentStarted;
                    document.getElementById("employeeEnded").value = EmploymentEnded;
                    document.getElementById("employeeLocation").value = EmploymentLocation;
                    document.getElementById("createdDate").innerHTML = '<font size=2px color=green>'+CreatedDate+'</font>';
                       document.getElementById("load").style.display = 'none';
                    document.getElementById("headerLabel").innerHTML="Employee Verfication Details";
                }else if(tempTableName=='tblEventAttendies'){
                      var Id = json["Id"];
                    var firstname = json["firstname"];
                    var EventName = json["EventName"];
                    var email_id = json["email_id"];
                    var phone_no = json["phone_no"];
                    var department = unescape(json["department"]);
                    var location = json["location"];
                    var foodpref = json["foodpref"];
                    var alongmember = json["alongmember"];
                    var cor_transport = json["cor_transport"];
                    var DropPoint = json["DropPoint"];
                    var CreatedDate = json["CreatedDate"];
                   
                    
                    
                     document.getElementById("fullName").value = firstname;
                    document.getElementById("emailId").value = email_id;
                    document.getElementById("workPhone").value = phone_no;
                    document.getElementById("department").value = department;
                    document.getElementById("foodPreference").value = foodpref;
                    document.getElementById("corporateTransport").value = cor_transport;
                    document.getElementById("dropPoint").value = DropPoint;
                    document.getElementById("bringingAlong").value = alongmember;
                    
                  
                    document.getElementById("createdDate").innerHTML = '<font size=2px color=green>'+CreatedDate+'</font>';
                       document.getElementById("load").style.display = 'none';
                    document.getElementById("headerLabel").innerHTML="QuarterlyMeet Attendee Details";
                }else if(tempTableName=='tblResourceDepotDetails'){
                      var Id = json["Id"];
                    var DocType = json["DocType"];
                    var DocTitle = json["DocTitle"];
                    var FName = json["FName"];
                    var LName = json["LName"];
                    var Organization = unescape(json["Organization"]);
                    var Designation = json["Designation"];
                    var Email = json["Email"];
                    var WorkPhone = json["WorkPhone"];
                    var AppliedDate = json["AppliedDate"];
                    
                   
                    
                    
                     document.getElementById("firstName").value = FName;
                    document.getElementById("lastName").value = LName;
                    document.getElementById("organization").value = Organization;
                    document.getElementById("designation").value = Designation;
                    document.getElementById("email").value = Email;
                    document.getElementById("workPhone").value = WorkPhone;
                   
                     
                   document.getElementById("docType").innerHTML = '<font size=2px color=green>'+DocType+'</font>';
                    document.getElementById("docTitle").innerHTML = '<font size=2px color=green>'+DocTitle+'</font>';
                    document.getElementById("createdDate").innerHTML = '<font size=2px color=green>'+AppliedDate+'</font>';
                       document.getElementById("load").style.display = 'none';
                    document.getElementById("headerLabel").innerHTML="Resource Depot Details";
                }else if(tempTableName=='tblSuggestions'){
                      var Id = json["Id"];
                    var FirstName = json["FirstName"];
                    var LastName = json["LastName"];
                    var Anonymously = json["Anonymously"];
                    var Suggestions = json["Suggestions"];
                    var CreatedDate = json["CreatedDate"];
                   
                    var isAnonymously = '';
                   //hideRow("nameTr");
                   if(Anonymously=='0'){
                       showRow("nameTr");
                       isAnonymously = 'Yes';
                   }else {
                       isAnonymously = 'No';
                       hideRow("nameTr");
                   }
                    
                    
                     document.getElementById("firstName").value = FirstName;
                    document.getElementById("lastName").value = LastName;
                    document.getElementById("suggestions").value = Suggestions;
                   
                   
                     
                   document.getElementById("postedAnonymously:").innerHTML = '<font size=2px color=green>'+isAnonymously+'</font>';
                   
                    document.getElementById("createdDate").innerHTML = '<font size=2px color=green>'+CreatedDate+'</font>';
                       document.getElementById("load").style.display = 'none';
                    document.getElementById("headerLabel").innerHTML="Suggestion Box Details";
                }else if(tempTableName=='tblIot') {
               
                var Id = json["ID"];
                var Fname = json["FirstName"];
                var Lname = json["LastName"];
                var Email = json["EmailId"];
                var Organization = json["Organization"];
                var Designation = unescape(json["Designation"]);
                var WorkPhone = json["Phone"];
                var City = json["City"];
                var ZipCode = json["ZipCode"];
                var Description = json["Description"];
                var CreatedDate = json["CreatedDate"];


                document.getElementById("firstName").value = Fname;
                document.getElementById("lastName").value = Lname;
                document.getElementById("email").value = Email;
                document.getElementById("organization").value = Organization;
                document.getElementById("designation").value = Designation;
                document.getElementById("workPhone").value = WorkPhone;
                document.getElementById("city").value = City;
                //document.getElementById("state").value = State;
                //document.getElementById("country").value = Country;
                // alert("zip--"+ZipCode);
                document.getElementById("zip").value = ZipCode;
                document.getElementById("createdDate").innerHTML = '<font size=2px color=green>'+CreatedDate+'</font>';
                document.getElementById("description:").value = Description;

                document.getElementById("load").style.display = 'none';
                document.getElementById("headerLabel").innerHTML="IOT Details";
            }
else if(tempTableName=='tblSignature') {
//               Id,EmployeeName,Designation,Email,WorkPhone,Mobile,Location,CreatedDate,Activity
                var Id = json["ID"];
              
                var Empname = json["EmployeeName"];
                 var Designation = unescape(json["Designation"]);
                var Email = json["EmailId"];
                 var WorkPhone = json["WorkPhone"];
                 var MobilePhone = json["Mobile"];
                var Location = json["Location"];
                var CreatedDate = json["CreatedDate"];
                var Activity = json["Activity"];


                document.getElementById("employeeName").value = Empname;
                document.getElementById("designation").value = Designation;
                document.getElementById("email").value = Email;
                document.getElementById("workPhone").value = WorkPhone;
                document.getElementById("mobilePhone").value = MobilePhone;
                document.getElementById("location").value = Location;
                //document.getElementById("state").value = State;
                //document.getElementById("country").value = Country;
                // alert("zip--"+ZipCode);
//                document.getElementById("zip").value = ZipCode;
                document.getElementById("createdDate").innerHTML = '<font size=2px color=green>'+CreatedDate+'</font>';
                document.getElementById("activity").value = Activity;

                document.getElementById("load").style.display = 'none';
                document.getElementById("headerLabel").innerHTML="Signature Details";
            }
			
             // showRow("editTr");
                 
            }, error: function(e){
                document.getElementById("load").style.display = 'none';
                alert("error-->"+e);
            }
        });
    
    
    
     
   document.getElementById("headerLabel").style.color="white";
            
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}



function checkMandatory(){
    var tableName = document.getElementById('tableName').value;
    var qmeetTitle = document.getElementById('qmeetTitleId').value;
    
    if(tableName=='tblEventAttendies'&&qmeetTitle.trim().length==0){
        alert("Please select Qmeet !");
        return false;
    }else {
        return true;
    }

    
   
}



// Get details for Completed events


function getCompletedEventDetails(eventId,detailsType){
     
 hideRow("editTr"); 
     document.getElementById('resultMessage').innerHTML ='';
  
    document.getElementById("load").style.display = 'block';
   $.ajax({
            url:'editEventposting.action?eventId='+eventId,//
            context: document.body,
            success: function(responseText) {
               // alert(responseText);
                var json = $.parseJSON(responseText);
                
                var event_id = json["event_id"];
              
                var event_title = json["event_title"];
                var event_description = json["event_description"];
               
                var event_startdate = json["event_startdate"];
             
                var evetnt_enddate = json["evetnt_enddate"];
                var event_time_from = unescape(json["event_time_from"]);
             
                var event_time_to = json["event_time_to"];
                var midday_from = json["midday_from"];
                var midday_to = json["midday_to"];
                var timezone = json["timezone"];
                var location = json["location"];
                var transport = json["transport"];
                var createdby = json["createdby"];
                var createddate = json["createddate"];
                var STATUS = json["STATUS"];
                var WebinarType = json["WebinarType"];
                var VideoLink = json["VideoLink"];
                 var event_bold_Title = '';
                var event_tagline = '';
                var OrganizerEmail = '';
                var RegistrationLink = '';
                
                var PrimaryTrack = '';
                var SecondaryTrack = '';
                var Department = '';
                var After_Description = ''
                // External /Internal webinars fields start
                
                if(WebinarType=='Q'){
                    showRow("transportationTr");
                    hideRow("eventBoldTr");
                // hideRow("eventRegularTr");
                 hideRow("eventRegistrationLinkTr");
                 hideRow("contactUsTr");
                  showRow("eventDescriptionTr");
                  hideRow("conferenceLinkTr");
                   hideRow("eventTrackTr");
       hideRow("eventDepartmentTr");
                }else if(WebinarType=='I'||WebinarType=='E'){
                     hideRow("transportationTr");
                     After_Description = json["After_Description"];
                 event_bold_Title = json["Page_Title"];
                 //event_tagline = json["event_tagline"];
                 OrganizerEmail = json["OrganizerEmail"];
                 RegistrationLink = json["RegistrationLink"];
                 PrimaryTrack = json["PrimaryTrack"];
                 SecondaryTrack = json["SecondaryTrack"];
                 Department = json["Department"];
                 
                  //document.getElementById("eventBoldtitle").value = event_bold_Title;
                  document.getElementById("eventTitlePageLabel").innerHTML=event_bold_Title;
                  
            // document.getElementById("eventRegluarTitle").value =event_tagline;
              //document.getElementById("eventRegistrationLink").value = RegistrationLink;
                 document.getElementById("registrationLinkLabel").innerHTML=RegistrationLink;
              
             document.getElementById("contactUsEmail").value =OrganizerEmail;
             
          document.getElementById("primaryTrack").value =PrimaryTrack;
           //  document.getElementById("primaryTrackLabel").innerHTML=PrimaryTrack;
             
             document.getElementById("secondaryTrack").value =SecondaryTrack;
             //document.getElementById("secondaryTrackLabel").innerHTML=SecondaryTrack;
             
           //  document.getElementById("eventDepartment").value =Department;
             document.getElementById("departmentLabel").innerHTML=Department;
             document.getElementById("eventAfterDescription").value =After_Description;
             if(detailsType!='publish'){
                 showRow("eventBoldTr");
                // showRow("eventRegularTr");
                 showRow("eventRegistrationLinkTr");
                // showRow("contactUsTr");
                 showRow("eventDescriptionTr");
                  hideRow("conferenceLinkTr");
                  showRow("eventTrackTr");
                  //showRow("seriesTr");
                }
                  if(WebinarType=='I'){
                      if(detailsType!='publish'){
                       showRow("eventDepartmentTr");
                      }
                        document.getElementById("depotLink").innerHTML='';
                        document.getElementById("depotLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/events/internal-webinar-after.php?eventId='+event_id+'</font>';   
                       
                     if(STATUS=='Published'){
                             showRow("depotUrlTr");
                     }
                            showRow("eventAfterDescriptionTr");
                showRow("afterVideoTr");
                if(detailsType=='publish')
                        showRow("editTr");
                        //}
                        
                       
                  }else if(WebinarType=='E'){
                      document.getElementById("depotLink").innerHTML='';
                   //    document.getElementById("depotLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/resource/get-resource.php?eventId='+event_id+'&objectId=4&refId='+json["LibraryId"]+'</font>';   
                       document.getElementById("depotLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/events/webinar-after.php?eventId='+event_id+'</font>';   
                        
                         
                            // if(STATUS=='Completed'|| STATUS=='Published'){
                             //LibraryId
                              if(STATUS=='Published'){
                             showRow("depotUrlTr");
                              }
                            showRow("eventAfterDescriptionTr");
                showRow("afterVideoTr");
                 if(detailsType=='publish')
                        showRow("editTr");
                       // }
                  }
                  
                }
                if(WebinarType=='Q'){
                      document.getElementById("depotLink").innerHTML='';
                       document.getElementById("depotLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/events/qmeet-rsvp.php?eventId='+event_id+'</font>';   
                        showRow("depotUrlTr");
                  }
               // Conference event field
               var event_redirect = '';
                if(WebinarType=='C'){
                   hideRow("eventBoldTr");
 //   hideRow("eventRegularTr");
    hideRow("transportationTr");
    hideRow("eventRegistrationLinkTr");
    hideRow("contactUsTr");
    showRow("conferenceLinkTr");
     hideRow("eventDescriptionTr");
      hideRow("eventTrackTr");
       hideRow("eventDepartmentTr");
                    event_redirect = json["event_redirect"];
                    document.getElementById("conferenceUrl").value =event_redirect;
                }
               
                
                // External /Internal webinars fields end
                
                 document.getElementById("event_id").value = event_id;
          
            document.getElementById("eventType").value = WebinarType;
            
         //   document.getElementById("eventType").style.display='none';
            document.getElementById("eventTypeLabel").style.display='block';
            //eventTypeLabel
            
            if(WebinarType=='Q'){
             document.getElementById("eventTypeLabel").innerHTML = '<font color=green size=2px>Quarterly Meet</font>';   
            }else if(WebinarType=='I'){
                document.getElementById("eventTypeLabel").innerHTML = '<font color=green size=2px>Internal Webinar</font>';
            }else if(WebinarType=='E'){
                document.getElementById("eventTypeLabel").innerHTML = '<font color=green size=2px>External Webinar</font>';
            }else if(WebinarType=='IEE'){
                document.getElementById("eventTypeLabel").innerHTML = '<font color=green size=2px>Internal Employee Experience</font>';
            }else if(WebinarType=='C'){
                document.getElementById("eventTypeLabel").innerHTML = '<font color=green size=2px>Conferences</font>';
            }
           document.getElementById("eventAfterVideoUrl").value = VideoLink;
          
            
             getDateFieldAppear(WebinarType);
         
            document.getElementById("eventStatus").value = STATUS;
            if(detailsType!='publish'){
                document.getElementById('eventStatusLabel').innerHTML =STATUS;
                document.getElementById("eventStatus").style.display='none';
                document.getElementById("eventStatusLabel").style.display='block';
            }
            //eventStatusLabel
            
        
           // document.getElementById("eventtitle").value = event_title;
            document.getElementById('eventUpcomingPageLabel').innerHTML =event_title
           
            // document.getElementById('selectDateFrom').value = event_startdate;
             document.getElementById('eventDateFromLabel').innerHTML =event_startdate
             
             document.getElementById('selectDateTo').value = evetnt_enddate;
            // document.getElementById("timeZone").value = timezone;
              document.getElementById('timeZoneLabel').innerHTML = timezone;
             
             //document.getElementById("eventLocation").value = location;
              document.getElementById('locationLabel').innerHTML =location;
             
             document.getElementById("transportation").value = transport;
             document.getElementById("eventDescription").value =event_description;
             
            //  document.getElementById("startTime").value = event_time_from;
           //  document.getElementById("midDayFrom").value =midday_from;
             document.getElementById('startTimeLabel').innerHTML = event_time_from+" "+midday_from;
             
             
             // document.getElementById("endTime").value = event_time_to;
            // document.getElementById("midDayTo").value =midday_to;
             document.getElementById('endTimeLabel').innerHTML = event_time_to+" "+midday_to;
             
              document.getElementById('createdBy').innerHTML = createdby;
              document.getElementById('createdDate').innerHTML = createddate;
              
              
             if(detailsType=='publish'){
                   hideRow("zoneTr");
 hideRow("timeTr");
 hideRow("datesTr");
              }
              
              
              
           
                 document.getElementById("load").style.display = 'none';
             
                 
            }, error: function(e){
                document.getElementById("load").style.display = 'none';
                alert("error-->"+e);
            }
        });
    
    
 //   hideRow("addTr");
 
 

    
     showRow('createdTr');
   document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Edit Event After Page";
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}






function closeCompletedToogle(){
            document.getElementById('resultMessage').innerHTML ='';
            hideRow('createdTr');
           /* hideRow('editTr');
            hideRow("createdTr");
*/
//clearData();

            document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Add EventPosting";
          //  showRow('addTr');
            var overlay = document.getElementById('overlay');
            var specialBox = document.getElementById('specialBox');

            overlay.style.opacity = .8;
            if(overlay.style.display == "block"){
            overlay.style.display = "none";
            specialBox.style.display = "none";

         //   document.getElementById("frmDBGrid").submit();

            }
            else {
            overlay.style.display = "block";
            specialBox.style.display = "block";
            }

         
           document.getElementById("frmDBGrid").submit();
        }
        
        
        
        function checkAfterFields(obj){
            if(obj.value=='Completed'|| obj.value=='Published'){
               // document.getElementById("load").style.display = 'block';
               // document.getElementById("load").style.display = 'block';
                showRow("eventAfterDescriptionTr");
                showRow("afterVideoTr");
                showRow("editTr");
                
            }else {
                 hideRow("eventAfterDescriptionTr");
                hideRow("afterVideoTr");
                 hideRow("editTr");
            }
        }
        
        
        
        
function doUpdateAfterEventPost() {
    //eventStatus 
    //eventAfterDescription
    //primaryTrack
    //secondaryTrack
    //eventAfterVideoUrl
    //event_id
    var eventStatus = document.getElementById("eventStatus").value;
    var eventAfterDescription = document.getElementById("eventAfterDescription").value;
    var primaryTrack = document.getElementById("primaryTrack").value;
    var secondaryTrack = document.getElementById("secondaryTrack").value;
    var eventAfterVideoUrl = document.getElementById("eventAfterVideoUrl").value;
    var event_id = document.getElementById("event_id").value;
    var eventType = document.getElementById("eventType").value;
    if(eventAfterDescription.trim().length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter after description.</font>"; 
    }else if(eventAfterVideoUrl.trim().length==0){
    document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter after video url.</font>"; 
    }else {
        
    document.getElementById("load").style.display = 'block';
    
        $.ajax({
           // url:'editJobposting.action?jobId='+jobId,//
           //url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
           url:'updateAfterEvent.action?eventId='+event_id+'&eventAfterDescription='+escape(eventAfterDescription)+"&eventAfterVideoUrl="+escape(eventAfterVideoUrl)+"&eventStatus="+eventStatus+"&primaryTrack="+primaryTrack+"&secondaryTrack="+secondaryTrack+"&eventType="+eventType,
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


function getCompletedEventSpeakers(eventId){
window.location = "getCompletedEventSpeakers.action?eventId="+eventId;
}



function getQmeetList(obj){
 
    //var accountId = document.getElementById("customerId").value;
    
 //alert(obj.value);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateQmeets);
    var url = CONTENXT_PATH+"/getQmeetMapByYear.action?year="+obj.value;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}


function populateQmeets(resXML) {
  
    var qmeetTitle = document.getElementById("qmeetTitleId");
    
    var projectsList = resXML.getElementsByTagName("QMEETS")[0];
   
    var users = projectsList.getElementsByTagName("QMEET");
    qmeetTitle.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("qmeetId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        qmeetTitle.appendChild(opt);
    }
}

function readyStateHandler(req,responseXmlHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                responseXmlHandler(req.responseXML);
            } else {
                alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }
    }
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



function checkDoubleQuotes(obj){
   // if(obj.value.indexOf('"') > -1){
   
   obj.value =  obj.value.replace(/"/g , "'");
       
      //  document.getElementById('resultMessage').innerHTML = "<font color=red>Please replace with single quotes by double quotes..</font>";
        //document.getElementById("eventDescription").value = '';
   // }
    
    
}




/*  Populate Events start */
function getEventSeries(AssociatedEventId){
    
    var isAssociated = document.getElementById("isAssociated").value 
 if(isAssociated=='YES'){
    var event_id = document.getElementById("event_id").value;
    var eventType = document.getElementById("eventType").value;
    
 //alert(obj.value);
   /* var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateSeriesEvents);
    var url = CONTENXT_PATH+"/getEventSeries.action?eventType="+eventType+"&eventId="+event_id;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    */
    
     var url = CONTENXT_PATH+"/getEventSeries.action?eventType="+eventType+"&eventId="+event_id;
        //alert(url);
         var req = newXMLHttpRequest();
        req.onreadystatechange = function() {
            if(req.readyState == 4) {
                if(req.status == 200) {
                    populateSeriesEvents(req.responseXML,AssociatedEventId);
                }else if(req.status == 204){
                   // clearTable1();
                }
            }
        };
        req.open("GET", url, true);
        req.send(null);
    
    
    
    
   
 }else {
     var associatedEventObj = document.getElementById("associatedEventId");
     associatedEventObj.innerHTML=" ";
      var opt = document.createElement("option");
        opt.setAttribute("value","");
        opt.appendChild(document.createTextNode("--Please Select--"));
        associatedEventObj.appendChild(opt);
 }
}


function populateSeriesEvents(resXML,eventId) {
  
    var associatedEventObj = document.getElementById("associatedEventId");
    
    var projectsList = resXML.getElementsByTagName("EVENTS")[0];
   
    var users = projectsList.getElementsByTagName("EVENT");
    associatedEventObj.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("eventId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        associatedEventObj.appendChild(opt);
    }
    
     document.getElementById("associatedEventId").value = eventId;
}

/* Populate Event End */



/*  Webinar Series */


function addSeries(){
    hideRow('createdTr');
            hideRow('editTr');
            hideRow("createdTr");
            
  showRow('addTr');
           
            document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Add Webinar Series";
           // showRow('addTr');
            
            //------------------- Slider code start ---------------
            
            //getSlider(4,10);
            
          
            
            
            //--------------------Slider Code end -------------------
            var overlay = document.getElementById('overlay');
            var specialBox = document.getElementById('specialBox');
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
        
        
 
function closeToggleOverlay(){
            //document.getElementById('resultMessage').innerHTML ='';
        // hideRow('createdTr');
            //hideRow('editTr');
            //hideRow("createdTr");

//clearData();

          //  document.getElementById("headerLabel").style.color="white";
          //  document.getElementById("headerLabel").innerHTML="Add EventPosting";
           // showRow('addTr');
            var overlay = document.getElementById('overlay');
            var specialBox = document.getElementById('specialBox');

            overlay.style.opacity = .8;
            if(overlay.style.display == "block"){
            overlay.style.display = "none";
            specialBox.style.display = "none";

         //   document.getElementById("frmDBGrid").submit();

            }
            else {
            overlay.style.display = "block";
            specialBox.style.display = "block";
            }

         
           document.getElementById("frmDBGrid").submit();
        }       
        
        
function doCreateWebinarSeries() {
    

   var seriesTitle= document.getElementById("seriesTitle").value;
var seriesType=document.getElementById("seriesType").value;
var seriesTrack=document.getElementById("seriesTrack").value;
var seriesStatus=document.getElementById("seriesStatus").value;

            
if(seriesTitle.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter series title.</font>";
   }else if(seriesType.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter series type.</font>";
   }
   else if(seriesTrack.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter series track.</font>";
   }
   else if(seriesStatus.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please select series status.</font>";
   }
   
  
   else
       {
    document.getElementById("load").style.display = 'block';
    
        $.ajax({
           // url:'editJobposting.action?jobId='+jobId,//
           //url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
           url:'doCreateWebinarSeries.action?seriesTitle='+seriesTitle+'&seriesType='+seriesType+"&seriesTrack="+seriesTrack+"&seriesStatus="+seriesStatus,
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


function getSeriesDetails(seriesId){
hideRow("addTr");
    hideRow("editTr");
     document.getElementById('resultMessage').innerHTML ='';
  
    document.getElementById("load").style.display = 'block';
   $.ajax({
            url:'getSeriesDetails.action?seriesId='+seriesId,//
            context: document.body,
            success: function(responseText) {
               // alert(responseText);
                var json = $.parseJSON(responseText);
                //Id,event_id,event_speaker,event_designation,primary_speaker,Company
                var Id = json["Id"];
              
                var SeriesTitle = json["SeriesTitle"];
                var SeriesType = json["SeriesType"];
               
                var SeriesTrack = json["SeriesTrack"];
             
                var SeriesStatus = json["SeriesStatus"];
                var CreatedDate = json["CreatedDate"];
              var CreatedBy = json["CreatedBy"];
             
                
                
                document.getElementById("seriesId").value = Id;
                document.getElementById("seriesTitle").value = SeriesTitle;
                document.getElementById("seriesType").value = SeriesType;
                document.getElementById("seriesTrack").value = SeriesTrack;
                document.getElementById("seriesStatus").value = SeriesStatus;
                document.getElementById("createdBy").innerHTML = CreatedBy;
                document.getElementById("createdDate").innerHTML = CreatedDate;
           
                 document.getElementById("load").style.display = 'none';
              showRow("editTr");
              showRow("createdTr");
                 
            }, error: function(e){
                document.getElementById("load").style.display = 'none';
                alert("error-->"+e);
            }
        });
    
    
    
     
   document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Edit Series Details";
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
    
}

function doUpdateWebinarSeries() {
    //eventStatus 
    //eventAfterDescription
    //primaryTrack
    //secondaryTrack
    //eventAfterVideoUrl
    //event_id
    var seriesId= document.getElementById("seriesId").value;
     var seriesTitle= document.getElementById("seriesTitle").value;
var seriesType=document.getElementById("seriesType").value;
var seriesTrack=document.getElementById("seriesTrack").value;
var seriesStatus=document.getElementById("seriesStatus").value;

            
if(seriesTitle.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter series title.</font>";
   }else if(seriesType.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter series type.</font>";
   }
   else if(seriesTrack.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter series track.</font>";
   }
   else if(seriesStatus.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please select series status.</font>";
   }
   
  
   else
       {
    document.getElementById("load").style.display = 'block';
    
        $.ajax({
           // url:'editJobposting.action?jobId='+jobId,//
           //url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
           url:'doUpdateWebinarSeries.action?seriesTitle='+seriesTitle+'&seriesType='+seriesType+"&seriesTrack="+seriesTrack+"&seriesStatus="+seriesStatus+"&seriesId="+seriesId,
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


function getSeriesEvents(seriesId){
window.location = "getEventsBySeries.action?seriesId="+seriesId;
}

function addEventToSeries() {
    hideRow('createdTr');
            hideRow('editTr');
            hideRow("createdTr");
            
  showRow('addTr');
           
            document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Add Event to Series";
           // showRow('addTr');
            
            //------------------- Slider code start ---------------
            
            //getSlider(4,10);
            
          
            
            
            //--------------------Slider Code end -------------------
            var overlay = document.getElementById('overlay');
            var specialBox = document.getElementById('specialBox');
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
            
}



function getRemoveEventFromSeries(eventId,seriesId){
    
   
    if (confirm("Do you want to remove Event from series!") == true) {
       window.location = "removeEventFromSeries?seriesId="+seriesId+"&eventId="+eventId;
    } 
    
}


function checkSeriesEventMandatory(){
    var eventId = document.getElementById('associatedEventId').value;
    if(eventId.trim().length==0){
        alert("Please select Webinar!");
        return false;
    }else {
        return true;
    }
}



function checkEventDate() {
   // alert("hii");
    
 var one_day=1000*60*60*24;
    var selectDateFrom  = new Date(document.getElementById('selectDateFrom').value);
    var actualEventDate  = document.getElementById('tempEventDate').value;
    var currentDate  = new Date(document.getElementById('currentDate').value);
       var timeDiff = parseInt((selectDateFrom.getTime() - currentDate.getTime()),10);
var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
return diffDays;
    
    
}


function isUrl(obj) {
    document.getElementById('resultMessage').innerHTML = '';
  var  url_validate = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
if(!url_validate.test(obj.value)){
     document.getElementById('resultMessage').innerHTML = "<font color=red>Invalid Url</font>";
     obj.value = '';
  // alert('error');
}

}


//-------------------


function addTrackName() {
    showRow("addTr");
  
           
            document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Add TrackName";
          
            var overlay = document.getElementById('overlay');
            var specialBox = document.getElementById('specialBox');
            document.getElementById("addButton").style.visibility="visible";
           document.getElementById("update").style.visibility="hidden";
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


function closeTrackNamesOverlay() {
      document.getElementById('resultMessage').innerHTML ='';


            document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Add Track Name";
          //  showRow('addTr');
            var overlay = document.getElementById('overlay');
            var specialBox = document.getElementById('specialBox');

            overlay.style.opacity = .8;
            if(overlay.style.display == "block"){
            overlay.style.display = "none";
            specialBox.style.display = "none";

         //   document.getElementById("frmDBGrid").submit();
         window.location = "getLkpTrackNames.action";
            }
            else {
            overlay.style.display = "block";
            specialBox.style.display = "block";
            }
}


function doAddTrackName() {
    



   var trackName= document.getElementById("trackName").value;
   //alert(trackName);

if(trackName.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter track name.</font>";
   }
   
  
   else
       {
    document.getElementById("load").style.display = 'block';
    
        $.ajax({
          
           url:'addTrackName.action?trackName='+trackName,
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


function editTrackName(Id,trackName) {
    showRow("addTr");
  
           
            document.getElementById("headerLabel").style.color="white";
            document.getElementById("trackName").value=trackName;
            document.getElementById("trackId").value=Id;
            document.getElementById("headerLabel").innerHTML="Edit Track Name";
           document.getElementById("addButton").style.visibility="hidden";
           document.getElementById("update").style.visibility="visible";
            var overlay = document.getElementById('overlay');
            var specialBox = document.getElementById('specialBox');
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


function doEditTrackName() {
    



   var trackName= document.getElementById("trackName").value;
   var trackId= document.getElementById("trackId").value;
   
   //alert(trackName);

if(trackName.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter Track Name.</font>";
   }
   
  
   else
       {
    document.getElementById("load").style.display = 'block';
    
        $.ajax({
          
           url:'editTrackName.action?trackName='+trackName+'&trackId='+trackId,
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



/*People add methods
 * date : 08/05/2015
 * Author : Santosh kola
 */

function addPeople() {
      //hideRow('createdTr');
        //    hideRow('editTr');
        //    hideRow("createdTr");
            
  showRow('addTr');
           
            document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Add People";
           // showRow('addTr');
            
            //------------------- Slider code start ---------------
            
            //getSlider(4,10);
            
          
            
            
            //--------------------Slider Code end -------------------
            var overlay = document.getElementById('overlay');
            var specialBox = document.getElementById('specialBox');
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
}

function toggleClosePeopleOverlay() {
    var overlay = document.getElementById('overlay');
            var specialBox = document.getElementById('specialBox');

            overlay.style.opacity = .8;
            if(overlay.style.display == "block"){
            overlay.style.display = "none";
            specialBox.style.display = "none";

         //   document.getElementById("frmDBGrid").submit();

            }
            else {
            overlay.style.display = "block";
            specialBox.style.display = "block";
            }

         
           document.getElementById("frmDBGrid").submit();
}


function doAddPeople() {
    
   var name= document.getElementById("name").value;
var emailId=document.getElementById("emailId").value;
var designation=document.getElementById("designation").value;
var organization=document.getElementById("organization").value;
var status=document.getElementById("status").value;


            
if(name.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter Name.</font>";
   }else if(emailId.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter email.</font>";
   }
   else if(designation.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter designation.</font>";
   }
   else if(organization.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter organization.</font>";
   }
   
  
   else
       {
    document.getElementById("load").style.display = 'block';
    hideRow('addTr');
        $.ajax({
           // url:'editJobposting.action?jobId='+jobId,//
           //url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
           url:'doAddPeople.action?peopleName='+name+'&organization='+organization+"&designation="+designation+"&email="+emailId+"&status="+status,
            context: document.body,
            success: function(responseText) {
               // displaymessage = responseText;
         
           
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
        // document.getElementById('fileType').value = "";
        },
        error: function(e){
           showRow('addTr');
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
     
                
        }
    });
    
       }
		
    return false;
}


function getPeopleDetails(Id){
hideRow("addTr");
    hideRow("editTr");
     document.getElementById('resultMessage').innerHTML ='';
  
    document.getElementById("load").style.display = 'block';
   $.ajax({
            url:'getPeopleDetails.action?id='+Id,//
            context: document.body,
            success: function(responseText) {
               // alert(responseText);
                var json = $.parseJSON(responseText);
                //Id,event_id,event_speaker,event_designation,primary_speaker,Company
                var Id = json["Id"];
              
                var NAME = json["NAME"];
                var Designation = json["Designation"];
               
                var Organization = json["Organization"];
             
                var EmailId = json["EmailId"];
                var STATUS = json["STATUS"];
              var CreatedDate = json["CreatedDate"];
              var CreatedBy = json["CreatedBy"];
              var ModifiedDate = json["ModifiedDate"];
              var ModifiedBy = json["ModifiedBy"];
             
             document.getElementById("peopleId").value=Id;
                   document.getElementById("name").value=NAME;
document.getElementById("emailId").value=EmailId;
document.getElementById("emailId").readOnly=true;
document.getElementById("designation").value=Designation;
document.getElementById("organization").value=Organization;
document.getElementById("status").value=STATUS;
                
             
                document.getElementById("createdBy").innerHTML = CreatedBy;
                document.getElementById("createdDate").innerHTML = CreatedDate;
           
                 document.getElementById("load").style.display = 'none';
              showRow("editTr");
              showRow("createdTr");
                 
            }, error: function(e){
                document.getElementById("load").style.display = 'none';
                alert("error-->"+e);
            }
        });
    
    
    
     
   document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Edit People Details";
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
    
}



function doUpdatePeopleDetails() {
    //eventStatus 
    //eventAfterDescription
    //primaryTrack
    //secondaryTrack
    //eventAfterVideoUrl
    //event_id
   var Id = document.getElementById("peopleId").value;
                   var NAME = document.getElementById("name").value;
var EmailId=document.getElementById("emailId").value;
var Designation = document.getElementById("designation").value;
var Organization = document.getElementById("organization").value;
var STATUS = document.getElementById("status").value;

            
if(NAME.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter Name.</font>";
   }else if(EmailId.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter email.</font>";
   }
   else if(Designation.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter designation.</font>";
   }
   else if(Organization.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter organization.</font>";
   }
  
   else
       {
    document.getElementById("load").style.display = 'block';
    
        $.ajax({
           // url:'editJobposting.action?jobId='+jobId,//
           //url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
           url:'doUpdatePeopleDetails.action?peopleName='+NAME+'&organization='+Organization+"&designation="+Designation+"&email="+EmailId+"&status="+STATUS+"&id="+Id,
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


function closeIOTOverlay() {
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
  //  document.getElementById("frmDBGrid").submit();
}

/*Event functionality new enhancements start
 * Date : 01/23/2017
 * Author : Santosh Kola/Phani Kanuri
 */


function addQmeetEventPost() {
      showRow("addTr");
    hideRow("editTr");
    
        //    document.getElementById("eventTypeLabel").style.display='none';
    //clearQmeetData();
           
           
            document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Add QuarterlyMeet";
          
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
         
}


function clearEventData() {
    if($('#event_id').length) 
    document.getElementById("event_id").value = '';
if($('#eventType').length) 
document.getElementById("eventType").value = '';
if($('#eventStatus').length) 
document.getElementById("eventStatus").value = '';
if($('#eventtitle').length) 
document.getElementById("eventtitle").value = '';
if($('#selectDateFrom').length) 
document.getElementById('selectDateFrom').value = '';
if($('#selectDateTo').length) 
document.getElementById('selectDateTo').value = '';
if($('#timeZone').length) 
document.getElementById("timeZone").value = '';
if($('#eventLocation').length) 
document.getElementById("eventLocation").value = '';
if($('#transportation').length) 
document.getElementById("transportation").value = '';
if($('#eventDescription').length) 
document.getElementById("eventDescription").value ='';
if($('#startTime').length) 
document.getElementById("startTime").value = '';
if($('#midDayFrom').length) 
document.getElementById("midDayFrom").value ='';
if($('#endTime').length) 
document.getElementById("endTime").value = '';
if($('#midDayTo').length) 
document.getElementById("midDayTo").value ='';
if($('#createdBy').length) 
document.getElementById('createdBy').innerHTML = '';
if($('#createdDate').length) 
document.getElementById('createdDate').innerHTML = '';
if($('#eventTypeLabel').length) 
document.getElementById("eventTypeLabel").innerHTML = '';
if($('#eventBoldtitle').length) 
document.getElementById("eventBoldtitle").value = '';
           //  document.getElementById("eventRegluarTitle").value ='';
           if($('#eventRegistrationLink').length) 
              document.getElementById("eventRegistrationLink").value = '';
          if($('#contactUsEmail').length) 
             document.getElementById("contactUsEmail").value ='';
             if($('#primaryTrack').length) 
             document.getElementById("primaryTrack").value ='';
         if($('#secondaryTrack').length) 
             document.getElementById("secondaryTrack").value ='';
         if($('#eventDepartment').length) 
             document.getElementById("eventDepartment").value ='';
}





function doUpdateQmeetEventPost()
{
    var event_id= document.getElementById("event_id").value;
           // var eventType= document.getElementById("eventType").value;
            var eventType= "Q";
          
             var eventStatus=document.getElementById("eventStatus").value;
          
             var eventtitle=document.getElementById("eventtitle").value;
         
             var selectDateFrom=document.getElementById("selectDateFrom").value;
           
            // var selectDateTo=document.getElementById("selectDateTo").value;
              var timeZone=document.getElementById("timeZone").value;
             
             var eventLocation=document.getElementById("eventLocation").value;
             var transportation=document.getElementById("transportation").value;
             
             var eventDescription=document.getElementById("eventDescription").value;
           
           var event_time_from= document.getElementById("startTime").value;
            var midday_from= document.getElementById("midDayFrom").value ;
              var event_time_to = document.getElementById("endTime").value;
             var midday_to = document.getElementById("midDayTo").value;
            
           
            
            
             var dateDiff = checkEventDate();
         //alert(dateDiff);
  if(selectDateFrom.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please select  Date from.</font>";
   }
  
   else if(eventLocation.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  location.</font>";
   }else if(transportation.trim().length==0 && eventType == 'Q'){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  transportation.</font>";
   } else if(eventDescription.trim().length==0 && (eventType != 'C'&&eventType != 'IEE')){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  description.</font>";
   }else if(parseInt(dateDiff,10)<0){
           document.getElementById('resultMessage').innerHTML = "<font color=red>Event date you selected is not valid.</font>";
            document.getElementById('selectDateFrom').value = document.getElementById("tempEventDate").value;
       }
   
        //setTimeout(disableFunction,1);
   
   else
       {
    
   
    document.getElementById("load").style.display = 'block';
  
         $.ajax({
           
           url:'updateQmeetEventPost.action?eventId='+event_id+'&eventTitle='+escape(eventtitle)+'&eventType='+eventType+"&eventStatus="+eventStatus+"&startDate="+selectDateFrom+"&timeZone="+timeZone+"&eventLocation="+escape(eventLocation)+"&transportation="+escape(transportation)+"&eventDescription="+escape(eventDescription)+"&startTime="+event_time_from+"&endTime="+event_time_to+"&midDayFrom="+midday_from+"&midDayTo="+midday_to,
            context: document.body,
            success: function(responseText) {
            
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
        
        },
        error: function(e){
           
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
     
                
        }
    });
    
       }
		
    return false;

}




function doAddQmeetEventPost()
{
   
 /*  Quarterly meet data start*/   

var eventStatus=document.getElementById("eventStatus").value;
var eventtitle=document.getElementById("eventtitle").value;
var selectDateFrom=document.getElementById("selectDateFrom").value;

var timeZone=document.getElementById("timeZone").value;
var eventLocation=document.getElementById("eventLocation").value;
var transportation=document.getElementById("transportation").value;
var eventDescription=document.getElementById("eventDescription").value;
var event_time_from= document.getElementById("startTime").value;
var midday_from= document.getElementById("midDayFrom").value ;
var event_time_to = document.getElementById("endTime").value;
var midday_to = document.getElementById("midDayTo").value;
/*  Quarterly meet data end*/   
 
  
   if(eventLocation.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  location.</font>";
   }else if(transportation.trim().length==0 ){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  transportation.</font>";
   } else if(eventDescription.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  description.</font>";
   } /*else if((eventType == 'I' || eventType == 'E')&&eventRegluarTitle.trim().length==0){
           
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  Event Regular title for Webinar before/After pages.</font>";
       }*/ 
   
   //eventAfterVideoUrl
        //setTimeout(disableFunction,1);
   
   else
       {
         
    document.getElementById("load").style.display = 'block';
    hideRow("addTr");
        $.ajax({
             
           // url:'editJobposting.action?jobId='+jobId,//
           //url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
        //   url:'addEventposting.action?eventTitle='+escape(eventtitle)+'&eventType='+eventType+"&eventStatus="+eventStatus+"&startDate="+selectDateFrom+"&endDate="+selectDateTo+"&timeZone="+timeZone+"&eventLocation="+escape(eventLocation)+"&transportation="+escape(transportation)+"&eventDescription="+escape(eventDescription)+"&startTime="+event_time_from+"&endTime="+event_time_to+"&midDayFrom="+midday_from+"&midDayTo="+midday_to+"&eventBoldtitle="+escape(eventBoldtitle)+"&eventRegluarTitle="+escape(eventRegluarTitle)+"&eventRegistrationLink="+escape(eventRegistrationLink)+"&contactUsEmail="+escape(contactUsEmail)+"&conferenceUrl="+escape(conferenceUrl)+"&primaryTrack="+primaryTrack+"&secondaryTrack="+secondaryTrack+"&eventDepartment="+eventDepartment+"&eventAfterVideoUrl="+escape(eventAfterVideoUrl),
           url:'doAddQmeetEventPost.action?eventTitle='+escape(eventtitle)+"&eventStatus="+eventStatus+"&startDate="+selectDateFrom+"&timeZone="+timeZone+"&eventLocation="+escape(eventLocation)+"&transportation="+escape(transportation)+"&eventDescription="+escape(eventDescription)+"&startTime="+event_time_from+"&endTime="+event_time_to+"&midDayFrom="+midday_from+"&midDayTo="+midday_to,
            context: document.body,
            success: function(responseText) {
               // displaymessage = responseText;
         
           
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
        // document.getElementById('fileType').value = "";
        },
        error: function(e){
           showRow("addTr");
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
     
                
        }
    });
    
       }
		
    return false;

}



function editQmeetEventPost(eventId){
 
     document.getElementById('resultMessage').innerHTML ='';
  
    document.getElementById("load").style.display = 'block';
   $.ajax({
            url:'editEventpost.action?eventId='+eventId,//
            context: document.body,
            success: function(responseText) {
               // alert(responseText);
                var json = $.parseJSON(responseText);
                
                var event_id = json["event_id"];
              
                var event_title = json["event_title"];
                var event_description = json["event_description"];
               
                var event_startdate = json["event_startdate"];
             
                
                var event_time_from = unescape(json["event_time_from"]);
             
                var event_time_to = json["event_time_to"];
                var midday_from = json["midday_from"];
                var midday_to = json["midday_to"];
                var timezone = json["timezone"];
                var location = json["location"];
                var transport = json["transport"];
                var createdby = json["createdby"];
                var createddate = json["createddate"];
                var STATUS = json["STATUS"];
               
            
                 document.getElementById("event_id").value = event_id;
              
             document.getElementById("tempEventDate").value = event_startdate;
             document.getElementById("currentDate").value = json["CurrentDate"];
           
                      document.getElementById("depotLink").innerHTML='';
                       document.getElementById("depotLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/events/qmeet-rsvp.php?eventId='+event_id+'</font>';   
                        showRow("depotUrlTr");
                  
              
         
            document.getElementById("eventStatus").value = STATUS;
        
            document.getElementById("eventtitle").value = event_title;
           
             document.getElementById('selectDateFrom').value = event_startdate;
            // document.getElementById('selectDateTo').value = evetnt_enddate;
             document.getElementById("timeZone").value = timezone;
             document.getElementById("eventLocation").value = location;
             document.getElementById("transportation").value = transport;
             document.getElementById("eventDescription").value =event_description;
             
              document.getElementById("startTime").value = event_time_from;
             document.getElementById("midDayFrom").value =midday_from;
              document.getElementById("endTime").value = event_time_to;
             document.getElementById("midDayTo").value =midday_to;
             
              document.getElementById('createdBy').innerHTML = createdby;
              document.getElementById('createdDate').innerHTML = createddate;
              
           
            if(STATUS!='Completed'&&STATUS!='Published')
    showRow("editTr");
                 document.getElementById("load").style.display = 'none';
                 checkEventDate();
             
                 
            }, error: function(e){
                document.getElementById("load").style.display = 'none';
                alert("error-->"+e);
            }
        });
    
    
    hideRow("addTr");
   
     showRow('createdTr');
   document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Edit Qmeet";
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}




function toggleQmeetOverlay(){
            document.getElementById('resultMessage').innerHTML ='';
            hideRow('createdTr');
           /* hideRow('editTr');
            hideRow("createdTr");
*/


            document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Add QuarterlyMeet";
          //  showRow('addTr');
            var overlay = document.getElementById('overlay');
            var specialBox = document.getElementById('specialBox');

            overlay.style.opacity = .8;
            if(overlay.style.display == "block"){
            overlay.style.display = "none";
            specialBox.style.display = "none";

         //   document.getElementById("frmDBGrid").submit();

            }
            else {
            overlay.style.display = "block";
            specialBox.style.display = "block";
            }

         
           document.getElementById("frmQMeetDBGrid").submit();
        }
        


function addExternalWebinarEventPost() {
      showRow("addTr");
    hideRow("editTr");
    //document.getElementById("eventType").style.display='block';
            //document.getElementById("eventTypeLabel").style.display='none';
    //clearEventData();
            /*  //    alert("hi");
                   document.getElementById('resultMessage').innerHTML ='';
              // hideRow('addTr');
   // hideRow('editTr');
   //  hideRow("createdTr");
          
            document.getElementById("jobtitle").value = '';
           
            document.getElementById("jobqulification").value = '';
           
            document.getElementById("location").value = '';
           
            document.getElementById("jobstatus").value = '';
            document.getElementById("jobdescription1").value = '';
            document.getElementById("jobdescription2").value = '';
            document.getElementById("jobDepartment").value = '';
            document.getElementById("jobHireType").value = '';*/
           
            document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Add External Webinar";
           // showRow('addTr');
            
            //------------------- Slider code start ---------------
            
            //getSlider(4,10);
            
          
            
            
            //--------------------Slider Code end -------------------
            var overlay = document.getElementById('overlay');
            var specialBox = document.getElementById('specialBox');
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

function closeExternalWebinarOverlay(){
            document.getElementById('resultMessage').innerHTML ='';
            hideRow('createdTr');
           /* hideRow('editTr');
            hideRow("createdTr");
*/
clearEventData();

            document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Add External Webinar";
          //  showRow('addTr');
            var overlay = document.getElementById('overlay');
            var specialBox = document.getElementById('specialBox');

            overlay.style.opacity = .8;
            if(overlay.style.display == "block"){
            overlay.style.display = "none";
            specialBox.style.display = "none";

         //   document.getElementById("frmDBGrid").submit();

            }
            else {
            overlay.style.display = "block";
            specialBox.style.display = "block";
            }

         
           document.getElementById("frmDBGrid").submit();
        }
        
        
function doAddExternalWebinarEventPost()
{
   
 /*  Quarterly meet data start*/   
//var eventType= document.getElementById("eventType").value;
var eventStatus=document.getElementById("eventStatus").value;
var eventtitle=document.getElementById("eventtitle").value;
var selectDateFrom=document.getElementById("selectDateFrom").value;
//var selectDateTo=document.getElementById("selectDateTo").value;
var timeZone=document.getElementById("timeZone").value;
var eventLocation=document.getElementById("eventLocation").value;
//var transportation=document.getElementById("transportation").value;
var eventDescription=document.getElementById("eventDescription").value;
var event_time_from= document.getElementById("startTime").value;
var midday_from= document.getElementById("midDayFrom").value ;
var event_time_to = document.getElementById("endTime").value;
var midday_to = document.getElementById("midDayTo").value;
/*  Quarterly meet data end*/   
 
/* External/Internal extra data start */
var eventBoldtitle = document.getElementById("eventBoldtitle").value;
//var eventRegluarTitle = document.getElementById("eventRegluarTitle").value;
var eventRegistrationLink = document.getElementById("eventRegistrationLink").value;
var contactUsEmail = document.getElementById("contactUsEmail").value;

var primaryTrack = document.getElementById("primaryTrack").value;
var secondaryTrack = document.getElementById("secondaryTrack").value;
//var eventDepartment = document.getElementById("eventDepartment").value;

/* External extra data start */
  
 /* Conference data */ 
 //var conferenceUrl = document.getElementById("conferenceUrl").value;
 /* Conference data */
  
  //var eventAfterVideoUrl = document.getElementById("eventAfterVideoUrl").value;
  
  
 if(selectDateFrom.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please select  Date from.</font>";
   }
  
   else if(eventLocation.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  location.</font>";
   }else if(eventDescription.trim().length==0 ){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  description.</font>";
   } else if(eventBoldtitle.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  Event bold title for Webinar before/After pages.</font>";
       } /*else if((eventType == 'I' || eventType == 'E')&&eventRegluarTitle.trim().length==0){
           
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  Event Regular title for Webinar before/After pages.</font>";
       }*/ else if(eventRegistrationLink.trim().length==0){
           
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  Event Registration link for Webinar before/After pages.</font>";
       } else if(contactUsEmail.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  Contact Us Email for Webinar before page.</font>";
       }else if(primaryTrack.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please select  Primary Track for event.</font>";
       } else if(timeZone.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please select TimeZone.</font>";
       } else if(eventStatus.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please select status.</font>";
       } else if(event_time_from.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please select  start time.</font>";
       } else if(midday_from.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please select midday from.</font>";
       } else if(event_time_to.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please select end Time.</font>";
       } else if(midday_to.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please select  midday to.</font>";
       } 
   
   //eventAfterVideoUrl
        //setTimeout(disableFunction,1);
   
   else
       {
          
       
    document.getElementById("load").style.display = 'block';
    hideRow("addTr");
        $.ajax({
             
           // url:'editJobposting.action?jobId='+jobId,//
           //url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
        //   url:'addEventposting.action?eventTitle='+escape(eventtitle)+'&eventType='+eventType+"&eventStatus="+eventStatus+"&startDate="+selectDateFrom+"&endDate="+selectDateTo+"&timeZone="+timeZone+"&eventLocation="+escape(eventLocation)+"&transportation="+escape(transportation)+"&eventDescription="+escape(eventDescription)+"&startTime="+event_time_from+"&endTime="+event_time_to+"&midDayFrom="+midday_from+"&midDayTo="+midday_to+"&eventBoldtitle="+escape(eventBoldtitle)+"&eventRegluarTitle="+escape(eventRegluarTitle)+"&eventRegistrationLink="+escape(eventRegistrationLink)+"&contactUsEmail="+escape(contactUsEmail)+"&conferenceUrl="+escape(conferenceUrl)+"&primaryTrack="+primaryTrack+"&secondaryTrack="+secondaryTrack+"&eventDepartment="+eventDepartment+"&eventAfterVideoUrl="+escape(eventAfterVideoUrl),
           url:'doAddExternalWebinarEventPost.action?eventTitle='+escape(eventtitle)+"&eventStatus="+eventStatus+"&startDate="+selectDateFrom+"&timeZone="+timeZone+"&eventLocation="+escape(eventLocation)+"&eventDescription="+escape(eventDescription)+"&startTime="+event_time_from+"&endTime="+event_time_to+"&midDayFrom="+midday_from+"&midDayTo="+midday_to+"&eventBoldtitle="+escape(eventBoldtitle)+"&eventRegistrationLink="+escape(eventRegistrationLink)+"&contactUsEmail="+escape(contactUsEmail)+"&primaryTrack="+primaryTrack+"&secondaryTrack="+secondaryTrack,
            context: document.body,
            success: function(responseText) {
               // displaymessage = responseText;
         
           
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
        // document.getElementById('fileType').value = "";
        },
        error: function(e){
           showRow("addTr");
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
     
                
        }
    });
    
       }
		
    return false;

}


function editExternalWebinarEventPost(eventId){
 
     document.getElementById('resultMessage').innerHTML ='';
  
    document.getElementById("load").style.display = 'block';
   $.ajax({
            url:'editExternalEventpost.action?eventId='+eventId,//
            context: document.body,
            success: function(responseText) {
               // alert(responseText);
                var json = $.parseJSON(responseText);
                
                var event_id = json["event_id"];
              
                var event_title = json["event_title"];
                var event_description = json["event_description"];
               
                var event_startdate = json["event_startdate"];
             
               // var evetnt_enddate = json["evetnt_enddate"];
                var event_time_from = unescape(json["event_time_from"]);
             
                var event_time_to = json["event_time_to"];
                var midday_from = json["midday_from"];
                var midday_to = json["midday_to"];
                var timezone = json["timezone"];
                var location = json["location"];
               // var transport = json["transport"];
                var createdby = json["createdby"];
                var createddate = json["createddate"];
                var STATUS = json["STATUS"];
               // var WebinarType = json["WebinarType"];
               // var VideoLink = json["VideoLink"];
                 var event_bold_Title = '';
                var event_tagline = '';
                var OrganizerEmail = '';
                var RegistrationLink = '';
                
                var PrimaryTrack = '';
                var SecondaryTrack = '';
               // var Department = '';
             //   var SeriesId = 0;
               // var IsAssociated = '';
               // var AssociatedEventId = '';
                 document.getElementById("event_id").value = event_id;
                 document.getElementById("seriesId").value = json["SeriesId"];;
           // document.getElementById("eventType").value = WebinarType;
            
             document.getElementById("tempEventDate").value = event_startdate;
             document.getElementById("currentDate").value = json["CurrentDate"];
             
            // if(WebinarType=='I'||WebinarType=='E'){
                    // hideRow("transportationTr");
                    // showRow("timeTr");
                     // document.getElementById("timeZoneTextId").style.display='block';
      // document.getElementById("timeZoneLabelId").style.display='block';
                    // showRow("seriesTr");
                     
                   // IsAssociated = json["IsAssociated"];
                  //  document.getElementById("isAssociated").value = IsAssociated;
                   //  if(IsAssociated=='YES'){
                       //  AssociatedEventId = json["AsociatedEventId"];
                          
                        //  getEventSeries(AssociatedEventId);
                          //alert(AssociatedEventId);
                          
                     //}
                    // 
                    
                 event_bold_Title = json["Page_Title"];
                 //event_tagline = json["event_tagline"];
                 OrganizerEmail = json["OrganizerEmail"];
                 RegistrationLink = json["RegistrationLink"];
                 PrimaryTrack = json["PrimaryTrack"];
                 SecondaryTrack = json["SecondaryTrack"];
                // Department = json["Department"];
                 
                  document.getElementById("eventBoldtitle").value = event_bold_Title;
            // document.getElementById("eventRegluarTitle").value =event_tagline;
              document.getElementById("eventRegistrationLink").value = RegistrationLink;
             document.getElementById("contactUsEmail").value =OrganizerEmail;
             
             document.getElementById("primaryTrack").value =PrimaryTrack;
             document.getElementById("secondaryTrack").value =SecondaryTrack;
           //  document.getElementById("eventDepartment").value =Department;
             
             
                // showRow("eventBoldTr");
                // showRow("eventRegularTr");
                // showRow("eventRegistrationLinkTr");
               //  showRow("contactUsTr");
                // showRow("eventDescriptionTr");
                //  hideRow("conferenceLinkTr");
                //  showRow("eventTrackTr");
                 // if(WebinarType=='E'){
                 if(STATUS=='Active') {
                      document.getElementById("depotLink").innerHTML='';
                       document.getElementById("depotLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/events/external-webinar-depot.php?eventId='+event_id+'</font>';   
                        showRow("depotUrlTr");
                        
                         document.getElementById("beforeLink").innerHTML='';
                        document.getElementById("beforeLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/events/webinar-before.php?eventId='+event_id+'</font>';   
                        showRow("beforeUrlTr");
                 }else if(STATUS=='Published'){
                     document.getElementById("ewafterLink").innerHTML='';
                        document.getElementById("ewafterLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/events/webinar-after.php?eventId='+event_id+'</font>';   
                        showRow("ewafterUrlTr");
                 }
                 // }
                //}
              
           
               // document.getElementById("eventTypeLabel").innerHTML = '<font color=green size=2px>External Webinar</font>';
       
            
            // getDateFieldAppear(WebinarType);
         
            document.getElementById("eventStatus").value = STATUS;
        
            document.getElementById("eventtitle").value = event_title;
           
             document.getElementById('selectDateFrom').value = event_startdate;
            // document.getElementById('selectDateTo').value = evetnt_enddate;
             document.getElementById("timeZone").value = timezone;
             document.getElementById("eventLocation").value = location;
             //document.getElementById("transportation").value = transport;
             document.getElementById("eventDescription").value =event_description;
             
              document.getElementById("startTime").value = event_time_from;
             document.getElementById("midDayFrom").value =midday_from;
              document.getElementById("endTime").value = event_time_to;
             document.getElementById("midDayTo").value =midday_to;
             
              document.getElementById('createdBy').innerHTML = createdby;
              document.getElementById('createdDate').innerHTML = createddate;
              
              
              
             
              
               if(STATUS!='Completed'&&STATUS!='Published')
    showRow("editTr");
              
           
                 document.getElementById("load").style.display = 'none';
                 checkEventDate();
             
                 
            }, error: function(e){
                document.getElementById("load").style.display = 'none';
                alert("error-->"+e);
            }
        });
    
    
    hideRow("addTr");
   
     showRow('createdTr');
   document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Edit External Webinar";
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}


function doUpdateExternalWebinarEventPost()
{
    var event_id= document.getElementById("event_id").value;
          //  var eventType= document.getElementById("eventType").value;
          
             var eventStatus=document.getElementById("eventStatus").value;
          
             var eventtitle=document.getElementById("eventtitle").value;
         
             var selectDateFrom=document.getElementById("selectDateFrom").value;
           
            // var selectDateTo=document.getElementById("selectDateTo").value;
              var timeZone=document.getElementById("timeZone").value;
             
             var eventLocation=document.getElementById("eventLocation").value;
          //   var transportation=document.getElementById("transportation").value;
             
             var eventDescription=document.getElementById("eventDescription").value;
           
           var event_time_from= document.getElementById("startTime").value;
            var midday_from= document.getElementById("midDayFrom").value ;
              var event_time_to = document.getElementById("endTime").value;
             var midday_to = document.getElementById("midDayTo").value;
            
           /* External extra data start */
var eventBoldtitle = document.getElementById("eventBoldtitle").value;
//var eventRegluarTitle = document.getElementById("eventRegluarTitle").value;
var eventRegistrationLink = document.getElementById("eventRegistrationLink").value;
var contactUsEmail = document.getElementById("contactUsEmail").value;


var primaryTrack = document.getElementById("primaryTrack").value;
var secondaryTrack = document.getElementById("secondaryTrack").value;
//var eventDepartment = document.getElementById("eventDepartment").value;
/* External extra data start */
                
      /* Conference data */ 
 //var conferenceUrl = document.getElementById("conferenceUrl").value;
 /* Conference data */          
    // var eventAfterVideoUrl = document.getElementById("eventAfterVideoUrl").value;            
                
          //   var isAssociated = document.getElementById("isAssociated").value;        
             // var associatedEventId = document.getElementById("associatedEventId").value;        
            
            var seriesId = document.getElementById("seriesId").value;         
            
            
              var one_day=1000*60*60*24;
    var tempselectDateFrom  = new Date(document.getElementById('selectDateFrom').value);
    var actualEventDate  = document.getElementById('tempEventDate').value;
    var currentDate  = new Date(document.getElementById('currentDate').value);
       var timeDiff = parseInt((tempselectDateFrom.getTime() - currentDate.getTime()),10);
var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
         //alert(dateDiff);
  
 if(selectDateFrom.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please select  Date from.</font>";
   }
  
   else if(eventLocation.trim().length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  location.</font>";
   }else if(eventDescription.trim().length==0 ){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  description.</font>";
   } else if(eventBoldtitle.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  Event bold title for Webinar before/After pages.</font>";
       } /*else if((eventType == 'I' || eventType == 'E')&&eventRegluarTitle.trim().length==0){
           
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  Event Regular title for Webinar before/After pages.</font>";
       }*/ else if(eventRegistrationLink.trim().length==0){
           
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  Event Registration link for Webinar before/After pages.</font>";
       } else if(contactUsEmail.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  Contact Us Email for Webinar before page.</font>";
       }else if(primaryTrack.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please select  Primary Track for event.</font>";
       } else if(timeZone.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please select TimeZone.</font>";
       } else if(eventStatus.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please select status.</font>";
       } else if(event_time_from.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please select  start time.</font>";
       } else if(midday_from.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please select midday from.</font>";
       } else if(event_time_to.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please select end Time.</font>";
       } else if(midday_to.trim().length==0){
          
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please select  midday to.</font>";
       } else if(parseInt(diffDays,10)<0){
           document.getElementById('resultMessage').innerHTML = "<font color=red>Event date you selected is not valid.</font>";
            document.getElementById('selectDateFrom').value = document.getElementById("tempEventDate").value;
       }
   
        //setTimeout(disableFunction,1);
   
   else
       {
    
          
      
   // hideRow('addTr');
   // hideRow('editTr');
    document.getElementById("load").style.display = 'block';
  /*  $.ajaxFileUpload({
    //    url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills+"&attachmentLocation="+file,//
        url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,//
        secureuri:false,//false
        fileElementId:'file',//id  <input type="file" id="file" name="file" />
        dataType: 'json',// json
        success: function(data,status){
           */ 
         //  alert(eventAfterVideoUrl);
         $.ajax({
            
           // url:'editJobposting.action?jobId='+jobId,//
           //url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
         //  url:'updateEventposting.action?eventId='+event_id+'&eventTitle='+escape(eventtitle)+'&eventType='+eventType+"&eventStatus="+eventStatus+"&startDate="+selectDateFrom+"&endDate="+selectDateTo+"&timeZone="+timeZone+"&eventLocation="+escape(eventLocation)+"&transportation="+escape(transportation)+"&eventDescription="+escape(eventDescription)+"&startTime="+event_time_from+"&endTime="+event_time_to+"&midDayFrom="+midday_from+"&midDayTo="+midday_to+"&eventBoldtitle="+escape(eventBoldtitle)+"&eventRegluarTitle="+escape(eventRegluarTitle)+"&eventRegistrationLink="+escape(eventRegistrationLink)+"&contactUsEmail="+contactUsEmail+"&conferenceUrl="+escape(conferenceUrl)+"&primaryTrack="+primaryTrack+"&secondaryTrack="+secondaryTrack+"&eventDepartment="+eventDepartment+"&eventAfterVideoUrl="+escape(eventAfterVideoUrl),
           url:'updateExternalWebinarEventPost.action?eventId='+event_id+'&eventTitle='+escape(eventtitle)+"&eventStatus="+eventStatus+"&startDate="+selectDateFrom+"&timeZone="+timeZone+"&eventLocation="+escape(eventLocation)+"&eventDescription="+escape(eventDescription)+"&startTime="+event_time_from+"&endTime="+event_time_to+"&midDayFrom="+midday_from+"&midDayTo="+midday_to+"&eventBoldtitle="+escape(eventBoldtitle)+"&eventRegistrationLink="+escape(eventRegistrationLink)+"&contactUsEmail="+contactUsEmail+"&primaryTrack="+primaryTrack+"&secondaryTrack="+secondaryTrack+"&seriesId="+seriesId,
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
/*
* phani methods

* */

        
function addIeeEventPost() {
    showRow("addTrIee");
    hideRow("editTrIee");
    
    //    document.getElementById("eventTypeLabel").style.display='none';
    //clearQmeetData();
           
           
    document.getElementById("headerLabelForIee").style.color="white";
    document.getElementById("headerLabelForIee").innerHTML="Add IEE Event";
          
    var overlay = document.getElementById('overlayforIeeTab');
    var specialBox = document.getElementById('specialBoxforIeeTab');
          
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
function toggleIEEOverlay(){
    document.getElementById('ieeResultMessage').innerHTML ='';
    hideRow('createdTrIee');
    /* hideRow('editTr');
            hideRow("createdTr");
*/
    //clearData();

    document.getElementById("headerLabelForIee").style.color="white";
    document.getElementById("headerLabelForIee").innerHTML="Add IEE Event";
    //  showRow('addTr');
    var overlay = document.getElementById('overlayforIeeTab');
    var specialBox = document.getElementById('specialBoxforIeeTab');

    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";

    //   document.getElementById("frmDBGrid").submit();

    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }

         
    document.getElementById("frmIeeDBGrid").submit();
}
        
        
function doAddIEEEventPost()
{
   
    /*  Quarterly meet data start*/   

    var eventStatus=document.getElementById("ieeEventStatus").value;
    var eventtitle=document.getElementById("ieeEventtitle").value;
    var selectDateFrom=document.getElementById("ieeSelectDateFrom").value;
    var timeZone=document.getElementById("ieeTimeZone").value;
    var eventLocation=document.getElementById("ieeEventLocation").value;
    var event_time_from= document.getElementById("ieeStartTime").value;
    var midday_from= document.getElementById("ieeMidDayFrom").value ;
    var event_time_to = document.getElementById("ieeEndTime").value;
    var midday_to = document.getElementById("ieeMidDayTo").value;
    /*  Quarterly meet data end*/   
 
  
    if(eventLocation.trim().length==0){
        document.getElementById('ieeResultMessage').innerHTML = "<font color=red>Please enter  location.</font>";
    } /*else if((eventType == 'I' || eventType == 'E')&&eventRegluarTitle.trim().length==0){
           
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  Event Regular title for Webinar before/After pages.</font>";
       }*/ 
   
    //eventAfterVideoUrl
    //setTimeout(disableFunction,1);
   
    else
    {
         
        document.getElementById("loadIee").style.display = 'block';
        hideRow("addTrIee");
        $.ajax({
             
            // url:'editJobposting.action?jobId='+jobId,//
            //url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
            //   url:'addEventposting.action?eventTitle='+escape(eventtitle)+'&eventType='+eventType+"&eventStatus="+eventStatus+"&startDate="+selectDateFrom+"&endDate="+selectDateTo+"&timeZone="+timeZone+"&eventLocation="+escape(eventLocation)+"&transportation="+escape(transportation)+"&eventDescription="+escape(eventDescription)+"&startTime="+event_time_from+"&endTime="+event_time_to+"&midDayFrom="+midday_from+"&midDayTo="+midday_to+"&eventBoldtitle="+escape(eventBoldtitle)+"&eventRegluarTitle="+escape(eventRegluarTitle)+"&eventRegistrationLink="+escape(eventRegistrationLink)+"&contactUsEmail="+escape(contactUsEmail)+"&conferenceUrl="+escape(conferenceUrl)+"&primaryTrack="+primaryTrack+"&secondaryTrack="+secondaryTrack+"&eventDepartment="+eventDepartment+"&eventAfterVideoUrl="+escape(eventAfterVideoUrl),
            url:'doAddIEEEventPost.action?eventTitle='+escape(eventtitle)+"&eventStatus="+eventStatus+"&startDate="+selectDateFrom+"&timeZone="+timeZone+"&eventLocation="+escape(eventLocation)+"&startTime="+event_time_from+"&endTime="+event_time_to+"&midDayFrom="+midday_from+"&midDayTo="+midday_to,
            context: document.body,
            success: function(responseText) {
                // displaymessage = responseText;
         
           
                document.getElementById("loadIee").style.display = 'none';
                document.getElementById('ieeResultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
            // document.getElementById('fileType').value = "";
            },
            error: function(e){
                showRow("addTrIee");
                document.getElementById("loadIee").style.display = 'none';
                document.getElementById('ieeResultMessage').innerHTML = "<font color=red>Please try again later</font>";
     
                
            }
        });
    
    }
		
    return false;

}    

function editIEEEventPost(eventId){
 
    document.getElementById('ieeResultMessage').innerHTML ='';
  
    document.getElementById("loadIee").style.display = 'block';
    $.ajax({
        url:'editEventpost.action?eventId='+eventId,//
        context: document.body,
        success: function(responseText) {
            // alert(responseText);
            var json = $.parseJSON(responseText);
                
            var event_id = json["event_id"];
            var event_title = json["event_title"];
            var event_startdate = json["event_startdate"];
            var event_time_from = unescape(json["event_time_from"]);
            var event_time_to = json["event_time_to"];
            var midday_from = json["midday_from"];
            var midday_to = json["midday_to"];
            var timezone = json["timezone"];
            var location = json["location"];
            var createdby = json["createdby"];
            var createddate = json["createddate"];
            var STATUS = json["STATUS"];
               
              if(STATUS!='Completed'&&STATUS!='Published')
    showRow("editTrIee");
            document.getElementById("event_idforIee").value = event_id;
              
            document.getElementById("tempIeeEventDate").value = event_startdate;
            document.getElementById("currentDateIee").value = json["CurrentDate"];
           
//            document.getElementById("depotLink").innerHTML='';
//            document.getElementById("depotLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/events/qmeet-rsvp.php?eventId='+event_id+'</font>';   
//            showRow("depotUrlTr");
//                  
              
         
            document.getElementById("ieeEventStatus").value = STATUS;
        
            document.getElementById("ieeEventtitle").value = event_title;
           
            document.getElementById('ieeSelectDateFrom').value = event_startdate;
            // document.getElementById('selectDateTo').value = evetnt_enddate;
            document.getElementById("ieeTimeZone").value = timezone;
            document.getElementById("ieeEventLocation").value = location;
            document.getElementById("ieeStartTime").value = event_time_from;
            document.getElementById("ieeMidDayFrom").value =midday_from;
            document.getElementById("ieeEndTime").value = event_time_to;
            document.getElementById("ieeMidDayTo").value =midday_to;
             
            document.getElementById('createdByIEE').innerHTML = createdby;
            document.getElementById('createdDateIEE').innerHTML = createddate;
              
           
            document.getElementById("loadIee").style.display = 'none';
            //checkEventDate();
             
                 
        }, 
        error: function(e){
            document.getElementById("loadIee").style.display = 'none';
            alert("error-->"+e);
        }
    });
    
    
    hideRow("addTrIee");
     
    showRow('createdTrIee');
    document.getElementById("headerLabelForIee").style.color="white";
    document.getElementById("headerLabelForIee").innerHTML="Edit IEE Event";
    var overlay = document.getElementById('overlayforIeeTab');
    var specialBox = document.getElementById('specialBoxforIeeTab');
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


 function doUpdateIEEEventPost()
{
   // alert("update");
    var event_id= document.getElementById("event_idforIee").value;
    // var eventType= document.getElementById("eventType").value;
    var eventType= "IEE";
          
    var eventStatus=document.getElementById("ieeEventStatus").value;
          
    var eventtitle=document.getElementById("ieeEventtitle").value;
         
    var selectDateFrom=document.getElementById("ieeSelectDateFrom").value;
           
    // var selectDateTo=document.getElementById("selectDateTo").value;
    var timeZone=document.getElementById("ieeTimeZone").value;
             
    var eventLocation=document.getElementById("ieeEventLocation").value;

           
    var event_time_from= document.getElementById("ieeStartTime").value;
    var midday_from= document.getElementById("ieeMidDayFrom").value ;
    var event_time_to = document.getElementById("ieeEndTime").value;
    var midday_to = document.getElementById("ieeMidDayTo").value;
            
           
            
            
    //var dateDiff = checkEventDate();
    //alert(dateDiff);
    if(selectDateFrom.length==0){
        document.getElementById('ieeResultMessage').innerHTML = "<font color=red>Please select  Date from.</font>";
    }
  
    else if(eventLocation.trim().length==0){
        document.getElementById('ieeResultMessage').innerHTML = "<font color=red>Please enter  location.</font>";
    }
//    else if(transportation.trim().length==0 && eventType == 'Q'){
//        document.getElementById('ieeResultMessage').innerHTML = "<font color=red>Please enter  transportation.</font>";
//    } else if(eventDescription.trim().length==0 && (eventType != 'C'&&eventType != 'IEE')){
//        document.getElementById('ieeResultMessage').innerHTML = "<font color=red>Please enter  description.</font>";
//    }else if(parseInt(dateDiff,10)<0){
//        document.getElementById('ieeResultMessage').innerHTML = "<font color=red>Event date you selected is not valid.</font>";
//        document.getElementById('selectDateFrom').value = document.getElementById("tempEventDate").value;
//    }
   
    //setTimeout(disableFunction,1);
   
    else
    {
    
   
        document.getElementById("loadIee").style.display = 'block';
  
        $.ajax({
           
            //url:'updateQmeetEventPost.action?eventId='+event_id+'&eventTitle='+escape(eventtitle)+'&eventType='+eventType+"&eventStatus="+eventStatus+"&startDate="+selectDateFrom+"&timeZone="+timeZone+"&eventLocation="+escape(eventLocation)+"&transportation="+escape(transportation)+"&eventDescription="+escape(eventDescription)+"&startTime="+event_time_from+"&endTime="+event_time_to+"&midDayFrom="+midday_from+"&midDayTo="+midday_to,
            url:'updateIEEEventPost.action?eventId='+event_id+'&eventTitle='+escape(eventtitle)+'&eventType='+eventType+"&eventStatus="+eventStatus+"&startDate="+selectDateFrom+"&timeZone="+timeZone+"&eventLocation="+escape(eventLocation)+"&startTime="+event_time_from+"&endTime="+event_time_to+"&midDayFrom="+midday_from+"&midDayTo="+midday_to,
            context: document.body,
            success: function(responseText) {
            
                document.getElementById("loadIee").style.display = 'none';
                document.getElementById('ieeResultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
        
            },
            error: function(e){
           
                document.getElementById("loadIee").style.display = 'none';
                document.getElementById('ieeResultMessage').innerHTML = "<font color=red>Please try again later</font>";
     
                
            }
        });
    
    }
		
    return false;

}




function addInternalWebinarEventPost() {
     showRow("iwaddTr");
    hideRow("iweditTr");
    
    //    document.getElementById("eventTypeLabel").style.display='none';
    //clearQmeetData();
           
            document.getElementById("iweventtitle").value='Internal Webinar :'
    document.getElementById("iwheaderLabel").style.color="white";
    document.getElementById("iwheaderLabel").innerHTML="Add Internal Webinar";
          
    var overlay = document.getElementById('overlayIw');
    var specialBox = document.getElementById('specialBoxIw');
          
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

function closeInternalWebinarOverlay() {
    
    /* hideRow('editTr');
            hideRow("createdTr");
*/
    //clearData();

    document.getElementById("iwheaderLabel").style.color="white";
    document.getElementById("iwheaderLabel").innerHTML="Add IEE Event";
    //  showRow('addTr');
    var overlay = document.getElementById('overlayIw');
    var specialBox = document.getElementById('specialBoxIw');

    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";

    //   document.getElementById("frmDBGrid").submit();

    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }

         
    document.getElementById("frmInternalWebinarDBGrid").submit();
}




function doAddInternalWebinarEventPost()
{
   document.getElementById('iwresultMessage').innerHTML ='';
 /*  Quarterly meet data start*/   

var eventStatus=document.getElementById("iweventStatus").value;
var eventtitle=document.getElementById("iweventtitle").value;
var selectDateFrom=document.getElementById("iwinternalWebinarDateFrom").value;

var timeZone=document.getElementById("iwtimeZone").value;
var eventLocation=document.getElementById("iweventLocation").value;

var eventDescription=document.getElementById("iweventDescription").value;
var event_time_from= document.getElementById("iwstartTime").value;
var midday_from= document.getElementById("iwmidDayFrom").value ;
var event_time_to = document.getElementById("iwendTime").value;
var midday_to = document.getElementById("iwmidDayTo").value;
/*  Quarterly meet data end*/   
 
/* External/Internal extra data start */
var eventBoldtitle = document.getElementById("iweventBoldtitle").value;
//var eventRegluarTitle = document.getElementById("eventRegluarTitle").value;
var eventRegistrationLink = document.getElementById("iweventRegistrationLink").value;
var contactUsEmail = document.getElementById("iwcontactUsEmail").value;

var primaryTrack = document.getElementById("iwprimaryTrack").value;
var secondaryTrack = document.getElementById("iwsecondaryTrack").value;
var eventDepartment = document.getElementById("iweventDepartment").value;

/* External extra data start */
  
  
if(selectDateFrom.length==0){
       document.getElementById('iwresultMessage').innerHTML = "<font color=red>Please select  Date from.</font>";
   }
  
   else if(eventLocation.trim().length==0){
       document.getElementById('iwresultMessage').innerHTML = "<font color=red>Please enter  location.</font>";
   }else if(eventDescription.trim().length==0){
       document.getElementById('iwresultMessage').innerHTML = "<font color=red>Please enter  description.</font>";
   } else if(eventBoldtitle.trim().length==0){
          
           document.getElementById('iwresultMessage').innerHTML = "<font color=red>Please enter  Event bold title for Webinar before/After pages.</font>";
       } /*else if((eventType == 'I' || eventType == 'E')&&eventRegluarTitle.trim().length==0){
           
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  Event Regular title for Webinar before/After pages.</font>";
       }*/ else if(eventRegistrationLink.trim().length==0){
           
           document.getElementById('iwresultMessage').innerHTML = "<font color=red>Please enter  Event Registration link for Webinar before/After pages.</font>";
       } else if(contactUsEmail.trim().length==0){
          
           document.getElementById('iwresultMessage').innerHTML = "<font color=red>Please enter  Contact Us Email for Webinar before page.</font>";
       }else if(primaryTrack.trim().length==0){
          
           document.getElementById('iwresultMessage').innerHTML = "<font color=red>Please select  Primary Track for event.</font>";
       }else if(eventDepartment.trim().length==0){
          
           document.getElementById('iwresultMessage').innerHTML = "<font color=red>Please select  Department for the event.</font>";
       }
   
   //eventAfterVideoUrl
        //setTimeout(disableFunction,1);
   
   else
       {
           
    document.getElementById("iwload").style.display = 'block';
    hideRow("iwaddTr");
        $.ajax({
             
           // url:'editJobposting.action?jobId='+jobId,//
           //url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
        //   url:'addEventposting.action?eventTitle='+escape(eventtitle)+'&eventType='+eventType+"&eventStatus="+eventStatus+"&startDate="+selectDateFrom+"&endDate="+selectDateTo+"&timeZone="+timeZone+"&eventLocation="+escape(eventLocation)+"&transportation="+escape(transportation)+"&eventDescription="+escape(eventDescription)+"&startTime="+event_time_from+"&endTime="+event_time_to+"&midDayFrom="+midday_from+"&midDayTo="+midday_to+"&eventBoldtitle="+escape(eventBoldtitle)+"&eventRegluarTitle="+escape(eventRegluarTitle)+"&eventRegistrationLink="+escape(eventRegistrationLink)+"&contactUsEmail="+escape(contactUsEmail)+"&conferenceUrl="+escape(conferenceUrl)+"&primaryTrack="+primaryTrack+"&secondaryTrack="+secondaryTrack+"&eventDepartment="+eventDepartment+"&eventAfterVideoUrl="+escape(eventAfterVideoUrl),
           url:'doAddInternalWebinarEventPost.action?eventTitle='+escape(eventtitle)+"&eventStatus="+eventStatus+"&startDate="+selectDateFrom+"&timeZone="+timeZone+"&eventLocation="+escape(eventLocation)+"&eventDescription="+escape(eventDescription)+"&startTime="+event_time_from+"&endTime="+event_time_to+"&midDayFrom="+midday_from+"&midDayTo="+midday_to+"&eventBoldtitle="+escape(eventBoldtitle)+"&eventRegistrationLink="+escape(eventRegistrationLink)+"&contactUsEmail="+escape(contactUsEmail)+"&primaryTrack="+primaryTrack+"&secondaryTrack="+secondaryTrack+"&eventDepartment="+eventDepartment,
            context: document.body,
            success: function(responseText) {
               // displaymessage = responseText;
         
           
            document.getElementById("iwload").style.display = 'none';
            document.getElementById('iwresultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
        // document.getElementById('fileType').value = "";
        },
        error: function(e){
           showRow("iwaddTr");
            document.getElementById("iwload").style.display = 'none';
            document.getElementById('iwresultMessage').innerHTML = "<font color=red>Please try again later</font>";
     
                
        }
    });
    
       }
		
    return false;

}



function editInternalWebinarEventPost(eventId){
 
     document.getElementById('iwresultMessage').innerHTML ='';
  
    document.getElementById("iwload").style.display = 'block';
   $.ajax({
            url:'editEventpost.action?eventId='+eventId,//
            context: document.body,
            success: function(responseText) {
               // alert(responseText);
                var json = $.parseJSON(responseText);
                
                var event_id = json["event_id"];
              
                var event_title = json["event_title"];
                var event_description = json["event_description"];
               
                var event_startdate = json["event_startdate"];
             
                var evetnt_enddate = json["evetnt_enddate"];
                var event_time_from = unescape(json["event_time_from"]);
             
                var event_time_to = json["event_time_to"];
                var midday_from = json["midday_from"];
                var midday_to = json["midday_to"];
                var timezone = json["timezone"];
                var location = json["location"];
                var transport = json["transport"];
                var createdby = json["createdby"];
                var createddate = json["createddate"];
                var STATUS = json["STATUS"];
                var WebinarType = json["WebinarType"];
                var VideoLink = json["VideoLink"];
                 var event_bold_Title = '';
                var event_tagline = '';
                var OrganizerEmail = '';
                var RegistrationLink = '';
                
                var PrimaryTrack = '';
                var SecondaryTrack = '';
                var Department = '';
             //   var SeriesId = 0;
               // var IsAssociated = '';
               // var AssociatedEventId = '';
                 document.getElementById("iwevent_id").value = event_id;
                 document.getElementById("iwseriesId").value = json["SeriesId"];;
           // document.getElementById("eventType").value = WebinarType;
            
             document.getElementById("iwinternalWebinarDateFrom").value = event_startdate;
             document.getElementById("iwcurrentDate").value = json["CurrentDate"];
             
             //CurrentDate
                // External /Internal webinars fields start
                 //if(WebinarType=='I'||WebinarType=='E'){
                    // hideRow("transportationTr");
                    // showRow("timeTr");
                     // document.getElementById("timeZoneTextId").style.display='block';
      // document.getElementById("timeZoneLabelId").style.display='block';
                    // showRow("seriesTr");
                     
                   // IsAssociated = json["IsAssociated"];
                  //  document.getElementById("isAssociated").value = IsAssociated;
                   //  if(IsAssociated=='YES'){
                       //  AssociatedEventId = json["AsociatedEventId"];
                          
                        //  getEventSeries(AssociatedEventId);
                          //alert(AssociatedEventId);
                          
                     //}
                    // 
                    
                 event_bold_Title = json["Page_Title"];
                 //event_tagline = json["event_tagline"];
                 OrganizerEmail = json["OrganizerEmail"];
                 RegistrationLink = json["RegistrationLink"];
                 PrimaryTrack = json["PrimaryTrack"];
                 SecondaryTrack = json["SecondaryTrack"];
                 Department = json["Department"];
               //  alert(SecondaryTrack);
                  document.getElementById("iweventBoldtitle").value = event_bold_Title;
            // document.getElementById("eventRegluarTitle").value =event_tagline;
              document.getElementById("iweventRegistrationLink").value = RegistrationLink;
             document.getElementById("iwcontactUsEmail").value =OrganizerEmail;
             
             document.getElementById("iwprimaryTrack").value =PrimaryTrack;
             document.getElementById("iwsecondaryTrack").value =SecondaryTrack;
             document.getElementById("iweventDepartment").value =Department;
             
             
                 // if(WebinarType=='I'){
                     //  showRow("eventDepartmentTr");
                        
                        
                        
                        if(STATUS=='Published'){
                        document.getElementById("iwafterLink").innerHTML='';
                        document.getElementById("iwafterLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/events/internal-webinar-after.php?eventId='+event_id+'</font>';   
                        showRow("iwafterUrlTr");
                        }else if(STATUS=='Active'){
                            document.getElementById("iwdepotLink").innerHTML='';
                        document.getElementById("iwdepotLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/events/internal-webinar-depot.php?eventId='+event_id+'</font>';   
                        showRow("iwdepotUrlTr");
                            
                            document.getElementById("iwbeforeLink").innerHTML='';
                        document.getElementById("iwbeforeLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/events/internal-webinar-before.php?eventId='+event_id+'</font>';   
                        showRow("iwbeforeUrlTr");
                        }
                  
              //  }
                
              
             
                // External /Internal webinars fields end
                
                
            //eventTypeLabel
           
          
            
            
            document.getElementById("iweventStatus").value = STATUS;
        
            document.getElementById("iweventtitle").value = event_title;
           
             document.getElementById('iwinternalWebinarDateFrom').value = event_startdate;
             
             document.getElementById("iwtimeZone").value = timezone;
             document.getElementById("iweventLocation").value = location;
             //document.getElementById("transportation").value = transport;
             document.getElementById("iweventDescription").value =event_description;
             
              document.getElementById("iwstartTime").value = event_time_from;
             document.getElementById("iwmidDayFrom").value =midday_from;
              document.getElementById("iwendTime").value = event_time_to;
             document.getElementById("iwmidDayTo").value =midday_to;
             
              document.getElementById('iwcreatedBy').innerHTML = createdby;
              document.getElementById('iwcreatedDate').innerHTML = createddate;
              
              
              
              if(STATUS!='Completed'&&STATUS!='Published')
                showRow("iweditTr");
              
              
              
           
                 document.getElementById("iwload").style.display = 'none';
                 //checkEventDate();
             
                 
            }, error: function(e){
                document.getElementById("iwload").style.display = 'none';
                alert("error-->"+e);
            }
        });
    
    
    hideRow("iwaddTr");
   
     showRow('iwcreatedTr');
   document.getElementById("iwheaderLabel").style.color="white";
            document.getElementById("iwheaderLabel").innerHTML="Edit EventPosting";
    var overlay = document.getElementById('overlayIw');
    var specialBox = document.getElementById('specialBoxIw');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}



function doUpdateInternalWebinarEventPost()
{
    var event_id= document.getElementById("iwevent_id").value;
          //  var eventType= document.getElementById("eventType").value;
          
             var eventStatus=document.getElementById("iweventStatus").value;
          
             var eventtitle=document.getElementById("iweventtitle").value;
         
             var selectDateFrom=document.getElementById("iwinternalWebinarDateFrom").value;
          // alert(selectDateFrom);
            // var selectDateTo=document.getElementById("selectDateTo").value;
              var timeZone=document.getElementById("iwtimeZone").value;
             
             var eventLocation=document.getElementById("iweventLocation").value;
          //   var transportation=document.getElementById("transportation").value;
             
             var eventDescription=document.getElementById("iweventDescription").value;
           
           var event_time_from= document.getElementById("iwstartTime").value;
            var midday_from= document.getElementById("iwmidDayFrom").value ;
              var event_time_to = document.getElementById("iwendTime").value;
             var midday_to = document.getElementById("iwmidDayTo").value;
            
           /* External extra data start */
var eventBoldtitle = document.getElementById("iweventBoldtitle").value;
//var eventRegluarTitle = document.getElementById("eventRegluarTitle").value;
var eventRegistrationLink = document.getElementById("iweventRegistrationLink").value;
var contactUsEmail = document.getElementById("iwcontactUsEmail").value;


var primaryTrack = document.getElementById("iwprimaryTrack").value;
var secondaryTrack = document.getElementById("iwsecondaryTrack").value;
var eventDepartment = document.getElementById("iweventDepartment").value;
/* External extra data start */
                
      /* Conference data */ 
 //var conferenceUrl = document.getElementById("conferenceUrl").value;
 /* Conference data */          
    // var eventAfterVideoUrl = document.getElementById("eventAfterVideoUrl").value;            
                
          //   var isAssociated = document.getElementById("isAssociated").value;        
             // var associatedEventId = document.getElementById("associatedEventId").value;        
            
            var seriesId = document.getElementById("iwseriesId").value;      
            // For calculating given date and current date diff start 
            var one_day=1000*60*60*24;
    var tempselectDateFrom  = new Date(document.getElementById('iwinternalWebinarDateFrom').value);
    var actualEventDate  = document.getElementById('iwtempEventDate').value;
    var currentDate  = new Date(document.getElementById('iwcurrentDate').value);
       var timeDiff = parseInt((tempselectDateFrom.getTime() - currentDate.getTime()),10);
var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
            // For calculating given date and current date diff end 
            // var dateDiff = checkEventDate();
         
   if(selectDateFrom.length==0){
       document.getElementById('iwresultMessage').innerHTML = "<font color=red>Please select  Date from.</font>";
   }
  
   else if(eventLocation.trim().length==0){
       document.getElementById('iwresultMessage').innerHTML = "<font color=red>Please enter  location.</font>";
   } else if(eventDescription.trim().length==0 ){
       document.getElementById('iwresultMessage').innerHTML = "<font color=red>Please enter  description.</font>";
   }else if(eventBoldtitle.trim().length==0){
          
           document.getElementById('iwresultMessage').innerHTML = "<font color=red>Please enter  Event page title for Webinar before/After pages.</font>";
       } /*else if((eventType == 'I' || eventType == 'E')&&eventRegluarTitle.trim().length==0){
           
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  Event Regular title for Webinar before/After pages.</font>";
       }*/ else if(eventRegistrationLink.trim().length==0){
           
           document.getElementById('iwresultMessage').innerHTML = "<font color=red>Please enter  Event Registration link for Webinar before/After pages.</font>";
       } else if(contactUsEmail.trim().length==0){
          
           document.getElementById('iwresultMessage').innerHTML = "<font color=red>Please enter  Contact Us Email for Webinar before page.</font>";
       }else if(primaryTrack.trim().length==0){
          
           document.getElementById('iwresultMessage').innerHTML = "<font color=red>Please select  Primary Track for event.</font>";
       }else if(eventDepartment.trim().length==0){
          
           document.getElementById('iwresultMessage').innerHTML = "<font color=red>Please select  Department for the event.</font>";
       }else if(parseInt(diffDays,10)<0){
           document.getElementById('iwresultMessage').innerHTML = "<font color=red>Event date you selected is not valid.</font>";
            document.getElementById('iwinternalWebinarDateFrom').value = document.getElementById("iwtempEventDate").value;
       }
   
        //setTimeout(disableFunction,1);
   
   else
       {
    
      //alert("-->"+selectDateFrom);
   // hideRow('addTr');
   // hideRow('editTr');
    document.getElementById("iwload").style.display = 'block';
  /*  $.ajaxFileUpload({
    //    url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills+"&attachmentLocation="+file,//
        url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,//
        secureuri:false,//false
        fileElementId:'file',//id  <input type="file" id="file" name="file" />
        dataType: 'json',// json
        success: function(data,status){
           */ 
         //  alert(eventAfterVideoUrl);
         $.ajax({
            
           // url:'editJobposting.action?jobId='+jobId,//
           //url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
         //  url:'updateEventposting.action?eventId='+event_id+'&eventTitle='+escape(eventtitle)+'&eventType='+eventType+"&eventStatus="+eventStatus+"&startDate="+selectDateFrom+"&endDate="+selectDateTo+"&timeZone="+timeZone+"&eventLocation="+escape(eventLocation)+"&transportation="+escape(transportation)+"&eventDescription="+escape(eventDescription)+"&startTime="+event_time_from+"&endTime="+event_time_to+"&midDayFrom="+midday_from+"&midDayTo="+midday_to+"&eventBoldtitle="+escape(eventBoldtitle)+"&eventRegluarTitle="+escape(eventRegluarTitle)+"&eventRegistrationLink="+escape(eventRegistrationLink)+"&contactUsEmail="+contactUsEmail+"&conferenceUrl="+escape(conferenceUrl)+"&primaryTrack="+primaryTrack+"&secondaryTrack="+secondaryTrack+"&eventDepartment="+eventDepartment+"&eventAfterVideoUrl="+escape(eventAfterVideoUrl),
           url:'updateInternalWebinarEvent.action?eventId='+event_id+'&eventTitle='+escape(eventtitle)+"&eventStatus="+eventStatus+"&startDate="+selectDateFrom+"&timeZone="+timeZone+"&eventLocation="+escape(eventLocation)+"&eventDescription="+escape(eventDescription)+"&startTime="+event_time_from+"&endTime="+event_time_to+"&midDayFrom="+midday_from+"&midDayTo="+midday_to+"&eventBoldtitle="+escape(eventBoldtitle)+"&eventRegistrationLink="+escape(eventRegistrationLink)+"&contactUsEmail="+contactUsEmail+"&primaryTrack="+primaryTrack+"&secondaryTrack="+secondaryTrack+"&eventDepartment="+eventDepartment+"&seriesId="+seriesId,
            context: document.body,
            success: function(responseText) {
               // displaymessage = responseText;
         
           
            document.getElementById("iwload").style.display = 'none';
            document.getElementById('iwresultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
        // document.getElementById('fileType').value = "";
        },
        error: function(e){
           
            document.getElementById("iwload").style.display = 'none';
            document.getElementById('iwresultMessage').innerHTML = "<font color=red>Please try again later</font>";
     
                
        }
    });
    
       }
		
    return false;

}


function addExternalConferenceEventPost() {
    showRow("addTrConference");
    hideRow("editTrConference");
    hideRow("createdTrConference");
    document.getElementById("conferenceHeaderLabel").style.color="white";
    document.getElementById("conferenceHeaderLabel").innerHTML="Add Conference Event";
    var overlay = document.getElementById('overlayforConferenceTab');
    var specialBox = document.getElementById('specialBoxforConferenceTab');
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

function closeExternalConferenceOverlay(){
    document.getElementById('conferenceResultMessage').innerHTML ='';
    document.getElementById("conferenceHeaderLabel").style.color="white";
    document.getElementById("conferenceHeaderLabel").innerHTML="Add Conference Event";
    //  showRow('addTr');
    var overlay = document.getElementById('overlayforConferenceTab');
    var specialBox = document.getElementById('specialBoxforConferenceTab');

    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";

    //   document.getElementById("frmDBGrid").submit();

    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }

    // alert("externalConferenceEventSearch");
    document.getElementById("frmDBConferenceGrid").submit();
}

function editExternalConferenceEventPost(eventId){
 hideRow("createdTrConference");
    document.getElementById('conferenceResultMessage').innerHTML ='';
  
    document.getElementById("loadConference").style.display = 'block';
    $.ajax({
        url:'editExternalEventpost.action?eventId='+eventId,//
        context: document.body,
        success: function(responseText) {
            // alert(responseText);
            var json = $.parseJSON(responseText);
                
            var event_id = json["event_id"];
            var event_title = json["event_title"];
            // var event_description = json["event_description"];
            var event_startdate = json["event_startdate"];
            var evetnt_enddate = json["evetnt_enddate"];
            var location = json["location"];
            var event_redirect = json["event_redirect"];
            var createdby = json["createdby"];
            var createddate = json["createddate"];
            var STATUS = json["STATUS"];
            if(STATUS!='Completed'&&STATUS!='Published')
    showRow("editTrConference");
            document.getElementById("conferenceEvent_id").value = event_id;
            document.getElementById("conferenceSeriesId").value = json["SeriesId"];
            
            document.getElementById("conferenceEventStatus").value = STATUS;
        
            document.getElementById("conferenceEventtitle").value = event_title;
           
            document.getElementById('selectConferenceDateFrom').value = event_startdate;
            document.getElementById('selectConferenceDateTo').value = evetnt_enddate;
            document.getElementById("conferenceEventLocation").value = location;
            //document.getElementById("transportation").value = transport;
            document.getElementById("conferenceUrl").value =event_redirect;
            document.getElementById('createdByConference').innerHTML = createdby;
            document.getElementById('createdDateConference').innerHTML = createddate;
            document.getElementById("loadConference").style.display = 'none';
        //   checkEventDate();
        }, 
        error: function(e){
            document.getElementById("load").style.display = 'none';
            alert("error-->"+e);
        }
    });
    
    
    hideRow("addTrConference");
   
    showRow('createdTrConference');
    document.getElementById("conferenceHeaderLabel").style.color="white";
    document.getElementById("conferenceHeaderLabel").innerHTML="Edit Conference Event";
    var overlay = document.getElementById('overlayforConferenceTab');
    var specialBox = document.getElementById('specialBoxforConferenceTab');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}


function doUpdateExternalConferenceEventPost()
{
 //   alert("doUpdateExternalConferenceEventPost");
    var event_id= document.getElementById("conferenceEvent_id").value;
    var eventStatus=document.getElementById("conferenceEventStatus").value;
    var eventtitle=document.getElementById("conferenceEventtitle").value;
    var selectDateFrom=document.getElementById("selectConferenceDateFrom").value;
    var selectDateTo=document.getElementById("selectConferenceDateTo").value;
    var eventLocation=document.getElementById("conferenceEventLocation").value;
    /* Conference data */ 
    var conferenceUrl = document.getElementById("conferenceUrl").value;
    var seriesId = document.getElementById("conferenceSeriesId").value;         
            
         //alert("inupdate");
  //  var dateDiff = checkEventDate();
    //alert(dateDiff);
  
    if(selectDateFrom.length==0){
        document.getElementById('conferenceResultMessage').innerHTML = "<font color=red>Please select  Date from.</font>";
    }
  
    else if(eventLocation.trim().length==0){
        document.getElementById('conferenceResultMessage').innerHTML = "<font color=red>Please enter  location.</font>";
    }
     else if(eventStatus.trim().length==0){
          
        document.getElementById('conferenceResultMessage').innerHTML = "<font color=red>Please select status.</font>";
    } 
   
    //setTimeout(disableFunction,1);
   
    else
    {
        document.getElementById("loadConference").style.display = 'block';
       
        $.ajax({
            url:'updateExternalConferenceEventPost.action?eventId='+event_id+'&eventTitle='+escape(eventtitle)+"&eventStatus="+eventStatus+"&startDate="+selectDateFrom+"&endDate="+selectDateTo+"&eventLocation="+escape(eventLocation)+"&conferenceUrl="+escape(conferenceUrl),
          
          context: document.body,
          
            success: function(responseText) {
                // displaymessage = responseText;
         
           //alert(responseText);
                document.getElementById("loadConference").style.display = 'none';
                document.getElementById('conferenceResultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
            // document.getElementById('fileType').value = "";
            },
            error: function(e){
           
                document.getElementById("loadConference").style.display = 'none';
                document.getElementById('conferenceResultMessage').innerHTML = "<font color=red>Please try again later</font>";
     
                
            }
        });
    
    }
		
    return false;

}

        
function doAddExternalConferenceEventPost()
{
    
    document.getElementById('conferenceResultMessage').innerHTML ='';
    var eventStatus=document.getElementById("conferenceEventStatus").value;
    var eventtitle=document.getElementById("conferenceEventtitle").value;
    var selectDateFrom=document.getElementById("selectConferenceDateFrom").value;
    var selectDateTo=document.getElementById("selectConferenceDateTo").value;
    var eventLocation=document.getElementById("conferenceEventLocation").value;
    var conferenceUrl = document.getElementById("conferenceUrl").value;
   
  
    if(selectDateFrom.length==0){
        document.getElementById('conferenceResultMessage').innerHTML = "<font color=red>Please select  Date from.</font>";
    }else  if(selectDateTo.length==0){
        document.getElementById('conferenceResultMessage').innerHTML = "<font color=red>Please select  Date To.</font>";
    }
  
    else if(eventLocation.trim().length==0){
        document.getElementById('conferenceResultMessage').innerHTML = "<font color=red>Please enter  location.</font>";
    }else if(eventStatus.trim().length==0){
          
        document.getElementById('conferenceResultMessage').innerHTML = "<font color=red>Please select status.</font>";
    } else if(conferenceUrl.trim().length==0){
          
        document.getElementById('conferenceResultMessage').innerHTML = "<font color=red>Please enter conferenceUrl.</font>";
    } 
   
    //eventAfterVideoUrl
    //setTimeout(disableFunction,1);
   
    else
    {
          
       
        document.getElementById("loadConference").style.display = 'block';
        hideRow("addTrConference");
        $.ajax({
            url:'doAddExternalConferenceEventPost.action?eventTitle='+escape(eventtitle)+"&eventStatus="+eventStatus+"&startDate="+selectDateFrom+"&endDate="+selectDateTo+"&eventLocation="+escape(eventLocation)+"&conferenceUrl="+escape(conferenceUrl),
            context: document.body,
            success: function(responseText) {
                // displaymessage = responseText;
                document.getElementById("loadConference").style.display = 'none';
                document.getElementById('conferenceResultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
            // document.getElementById('fileType').value = "";
            },
            error: function(e){
                showRow("addTrConference");
                document.getElementById("loadConference").style.display = 'none';
                document.getElementById('conferenceResultMessage').innerHTML = "<font color=red>Please try again later</font>";
     
                
            }
        });
    
    }
		
    return false;

}



function doPublishInternalExeMeet(Element,emeetId,status){
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
                window.location = "doPublishInternalExeMeet.action?id="+emeetId;
            }
        }
    }
}

function doActiveInternalExeMeet(Element,emeetId,status){
    // alert('status-->'+status);
    if(Element.value = "P")
    {
        if(status=="Active"){
            alert("It is already Active");
        }
        
        else{
            if(window.confirm("Do you want to change the status to Active")){
                window.location = "doActiveInternalExeMeet.action?id="+emeetId;
            }
        }
    }
}


/*Internal Executive Meet
 * Date : 01/11/2017
 * 
 */

function addInternalExecutiveMeet(){
   // alert("hi");
    document.getElementById('emeetResultMessage').innerHTML ='';
   
    
    document.getElementById("emeetHeaderLabel").style.color="white";
    document.getElementById("emeetHeaderLabel").innerHTML="Add Executive Meeting";
    showRow('emmetAddTr');

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
    document.getElementById('emeetResultMessage').innerHTML ='';
    hideRow('emmetAddTr');
    hideRow('emeetEditTr');
    hideRow("emeetCreatedTr");
    
    document.getElementById("emeetHeaderLabel").style.color="white";
    document.getElementById("emeetHeaderLabel").innerHTML="Add Executive Meeting";
    showRow('emmetAddTr');
    var overlay = document.getElementById('exeMeetoverlay');
    var specialBox = document.getElementById('exeMeetspecialBox');
    //  hideRow("approvedBy");
    //hideRow("tlcommentsTr");
    // hideRow("hrcommentsTr");
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
              
        document.getElementById("frmEmeetDBGrid").submit();
                
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
            
    //  window.location = "jobSearch.action";
    document.getElementById("frmEmeetDBGrid").submit();
}
        
function doAddInternalExecutiveMeeting()
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
    var timeZone=document.getElementById("emeetTimeZone").value;
 
    if(executiveMeetingType==-1){
        //alert("please Enter Type");
        document.getElementById('emeetResultMessage').innerHTML = "<font color=red>please Enter Type.</font>";
        return false;
    }
    else if(executiveMeetMonth==-1)
    {
        // alert("please Enter Month");
        document.getElementById('emeetResultMessage').innerHTML = "<font color=red>please Enter Month.</font>";
        return false;
    }
    else if(exeMeetcreatedDateTo==""){
        //alert("please Enter Date");
        document.getElementById('emeetResultMessage').innerHTML = "<font color=red>please Enter Date.</font>";
        return false;
    }
  
    else if(exeMeetStartTime==""){
        document.getElementById('emeetResultMessage').innerHTML = "<font color=red>please Start Time.</font>";
        return false; 
    }
    else if(exeMeetEndTime==""){
        document.getElementById('emeetResultMessage').innerHTML = "<font color=red>please End Time.</font>";
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
        document.getElementById('emeetResultMessage').innerHTML = "<font color=red>please Enter Registration Link.</font>";
        return false;
    }

   
    else
    {
        //hideRow('addTr');
        hideRow('emeetEditTr');
        setTimeout(disableFunction,1);
        document.getElementById("emeetLoad").style.display = 'block';
     
        $.ajax({
            // url:'editJobposting.action?jobId='+jobId,//
            //url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
            url:'addExecitiveMeeting.action?executiveMeetingType='+executiveMeetingType+'&executiveMeetMonth='+executiveMeetMonth+"&exeMeetcreatedDateTo="+exeMeetcreatedDateTo+"&executiveMeetingStatus="+executiveMeetingStatus+"&exeMeetStartTime="+exeMeetStartTime+"&exeMeetEndTime="+exeMeetEndTime+"&exeMeetStartmidDayFrom="+exeMeetStartmidDayFrom+"&exeMeetEndmidDayTo="+exeMeetEndmidDayTo+"&registrationLinkForEMeet="+registrationLinkForEMeet+"&timeZone="+timeZone,
            context: document.body,
            success: function(responseText) {
                // displaymessage = responseText;
         
           
                document.getElementById("emeetLoad").style.display = 'none';
                document.getElementById('emeetResultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
            // document.getElementById('fileType').value = "";
            },
            error: function(e){
           
                document.getElementById("emeetLoad").style.display = 'none';
                document.getElementById('emeetResultMessage').innerHTML = "<font color=red>Please try again later</font>";
     
                
            }
        });
    
    }
		
    return false;

}

function editInternalExeMeet(Id){
   
    hideRow('emmetAddTr');
    
    document.getElementById('emeetResultMessage').innerHTML ='';
    document.getElementById("emeetLoad").style.display = 'block';
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
                hideRow('emeetUpdateButton');
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
            document.getElementById("emeetTimeZone").value = TimeZone;
            document.getElementById("emeetCreatedBy").innerHTML = CreatedBy;
            document.getElementById("emeetCreatedDate").innerHTML = CreatedDate;
           
         
            document.getElementById("emeetLoad").style.display = 'none';
            showRow('emeetEditTr');
            showRow('emeetCreatedTr');
        // getSlider(4,7);
            
        }, 
        error: function(e){
            document.getElementById("emeetLoad").style.display = 'none';
            alert("error-->"+e);
        }
    });
    
    
    
    document.getElementById("emeetHeaderLabel").style.color="white";
    document.getElementById("emeetHeaderLabel").innerHTML="Edit ExecutiveMeeting";
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

function doUpdateInternalExeMeeting(){
    // hideRow('emeetEditTr');
      document.getElementById('emeetResultMessage').innerHTML ='';
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
    var timeZone=document.getElementById("emeetTimeZone").value;
    if(executiveMeetingType==-1){
        //alert("please Enter Type");
        document.getElementById('emeetResultMessage').innerHTML = "<font color=red>please Enter Type.</font>";
        return false;
    }
    else if(executiveMeetMonth==-1)
    {
        // alert("please Enter Month");
        document.getElementById('emeetResultMessage').innerHTML = "<font color=red>please Enter Month.</font>";
        return false;
    }
    else if(exeMeetcreatedDateTo==""){
        //alert("please Enter Date");
        document.getElementById('emeetResultMessage').innerHTML = "<font color=red>please Enter Date.</font>";
        return false;
    }
  
    else if(exeMeetStartTime==""){
        document.getElementById('emeetResultMessage').innerHTML = "<font color=red>please Start Time.</font>";
        return false; 
    }
    else if(exeMeetEndTime==""){
        document.getElementById('emeetResultMessage').innerHTML = "<font color=red>please End Time.</font>";
        return false; 
    }

    else if(registrationLinkForEMeet==""){
        // alert("please Enter Registration Link");
        document.getElementById('emeetResultMessage').innerHTML = "<font color=red>please Enter Registration Link.</font>";
        return false;
    }

   
    else
    {
    // alert(jobtechnical);
    document.getElementById("emeetLoad").style.display = 'block';
   
    $.ajax({
        //  url:'updateJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+'&location='+location+'&jobcountry='+jobcountry+'&jobstatus='+jobstatus+'&jobdescription='+jobdescription+'&jobtechnical='+jobtechnical+'&jobshiftskills='+jobshiftskills+'&attachmentLocation='+file+'&jobId='+jobId,//
        url:'updateExecitiveMeeting.action?executiveMeetingType='+executiveMeetingType+'&executiveMeetMonth='+executiveMeetMonth+"&exeMeetcreatedDateTo="+exeMeetcreatedDateTo+"&executiveMeetingStatus="+executiveMeetingStatus+"&exeMeetStartTime="+exeMeetStartTime+"&exeMeetEndTime="+exeMeetEndTime+"&exeMeetStartmidDayFrom="+exeMeetStartmidDayFrom+"&exeMeetEndmidDayTo="+exeMeetEndmidDayTo+"&registrationLinkForEMeet="+registrationLinkForEMeet+"&id="+exeMeetingId+"&timeZone="+timeZone,
        // alert(url);
        context: document.body,
        success: function(responseText) {
            var displaymessage = responseText;
            document.getElementById("emeetLoad").style.display = 'none';
            document.getElementById('emeetResultMessage').innerHTML = "<font color=green>"+displaymessage+"</font>";//"<font color=green>File uploaded successfully</font>";
        // document.getElementById('fileType').value = "";
        },
        error: function(e){
            // alert('Error:111 ' + e);
            document.getElementById("emeetLoad").style.display = 'none';
            document.getElementById('emeetResultMessage').innerHTML = "<font color=red>Please try again later</font>";
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

function disableFunction() {
    //alert("indisable");
    document.getElementById("emeetAddButton").disabled = 'true';
}





function editCompletedInternalExeMeet(Id){
   
    hideRow('emmetAddTr');
    hideRow('emeetEditTr');
    document.getElementById('emeetResultMessage').innerHTML ='';
    document.getElementById("emeetLoad").style.display = 'block';
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
            document.getElementById("emeetTimeZone").innerHTML = TimeZone;
            document.getElementById("emeetCreatedBy").innerHTML = CreatedBy;
            document.getElementById("emeetCreatedDate").innerHTML = CreatedDate;
            document.getElementById("videoLinkForEMeet").value = VideoLink;
            if(Status=="Completed"){
                showRow("emeetUrlTr");
                document.getElementById("emeetLink").innerHTML='';
                document.getElementById("emeetLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/events/emeets-replay.php?exeMeetId='+Id+'</font>';
            }
            
           showRow('emeetEditTr');
           showRow('emeetCreatedTr');
            document.getElementById("emeetLoad").style.display = 'none';
       
            
        }, 
        error: function(e){
            document.getElementById("emeetLoad").style.display = 'none';
            alert("error-->"+e);
        }
    });
    
    
    
    document.getElementById("emeetHeaderLabel").style.color="white";
    document.getElementById("emeetHeaderLabel").innerHTML="Edit ExecutiveMeeting";
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

function doUpdateInternalCompletedExeMeeting(){
    
       hideRow('emmetAddTr');
  
   
    var exeMeetingId= document.getElementById("exeMeetingId").value;

           
    var executiveMeetingStatus=document.getElementById("executiveMeetingStatus").value;
    
    var videoLinkForEMeet=escape(document.getElementById("videoLinkForEMeet").value);
    
    if(executiveMeetingStatus=='Completed' ){
        if(videoLinkForEMeet.length==0){
            document.getElementById('emeetResultMessage').innerHTML = "<font color=red>Please Enter the video link.</font>"
            return false;
        }
    }
    
    document.getElementById("emeetLoad").style.display = 'block';
   
    $.ajax({
        url:'updateCompletedExecitiveMeeting.action?executiveMeetingStatus='+executiveMeetingStatus+"&videoLinkForEMeet="+videoLinkForEMeet+"&id="+exeMeetingId,
        // alert(url);
        context: document.body,
        success: function(responseText) {
            var displaymessage = responseText;
            document.getElementById("emeetLoad").style.display = 'none';
            document.getElementById('emeetResultMessage').innerHTML = "<font color=green>"+displaymessage+"</font>";//"<font color=green>File uploaded successfully</font>";
        },
        error: function(e){
            // alert('Error:111 ' + e);
            document.getElementById("emeetLoad").style.display = 'none';
            document.getElementById('emeetResultMessage').innerHTML = "<font color=red>Please try again later</font>";
        // document.getElementById('fileType').value = "";
        //  document.getElementById('profile').value="";
                
        }
    });
    
    // }
		
    return false;
}



function getCompletedWebinarEventDetails(eventId,detailsType){
     
    hideRow("webinarEditTr"); 
    document.getElementById('resultMessageAfterVideo').innerHTML ='';
  
    document.getElementById("loadAfterVideo").style.display = 'block';
    $.ajax({
        url:'editEventpost.action?eventId='+eventId,//
        context: document.body,
        success: function(responseText) {
            // alert(responseText);
            var json = $.parseJSON(responseText);
                
            var event_id = json["event_id"];
              
            var event_title = json["event_title"];
               
           
            var createdby = json["createdby"];
            var createddate = json["createddate"];
            var STATUS = json["STATUS"];
            var WebinarType = json["WebinarType"];
            var VideoLink = json["VideoLink"];
            var event_bold_Title = '';
            var event_tagline = '';
            var OrganizerEmail = '';
            var RegistrationLink = '';
                
            var PrimaryTrack = '';
            var SecondaryTrack = '';
            var Department = '';
            var After_Description = ''
            // External /Internal webinars fields start
       if(WebinarType=='I'||WebinarType=='E'){
                After_Description = json["After_Description"];
                event_bold_Title = json["Page_Title"];
               
                Department = json["Department"];
                 
                //document.getElementById("eventBoldtitle").value = event_bold_Title;
                document.getElementById("webinarEventUpcomingPageLabel").innerHTML=event_bold_Title;
                document.getElementById("webinarDepartmentLabel").innerHTML=Department;
                document.getElementById("webinarEventAfterDescription").value =After_Description;
                if(detailsType!='publish'){
                    showRow("webinarEventDescriptionTr");
                }
                if(WebinarType=='I'){
                    if(detailsType!='publish'){
                        showRow("webinarEventDepartmentTr");
                    }
                    document.getElementById("webinarDepotLink").innerHTML='';
                    document.getElementById("webinarDepotLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/events/internal-webinar-after.php?eventId='+event_id+'</font>';   
                       
                    if(STATUS=='Published'){
                        showRow("webinarDepotUrlTr");
                    }
                    showRow("webinarEventAfterDescriptionTr");
                    showRow("webinarAfterVideoTr");
                    if(detailsType=='publish')
                        showRow("webinarEditTr");
                }else if(WebinarType=='E'){
                    document.getElementById("webinarDepotLink").innerHTML='';
                    //    document.getElementById("depotLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/resource/get-resource.php?eventId='+event_id+'&objectId=4&refId='+json["LibraryId"]+'</font>';   
                    document.getElementById("webinarDepotLink").innerHTML = '<font color=green size=2px>http://www.miraclesoft.com/events/webinar-after.php?eventId='+event_id+'</font>';   
                        
                         
                    // if(STATUS=='Completed'|| STATUS=='Published'){
                    //LibraryId
                    if(STATUS=='Published'){
                        showRow("webinarDepotUrlTr");
                    }
                    showRow("webinarEventAfterDescriptionTr");
                    showRow("webinarAfterVideoTr");
                    if(detailsType=='publish')
                        showRow("webinarEditTr");
                // }
                }
                  
            }
            document.getElementById("webinar_event_id").value = event_id;
          
          //  document.getElementById("webinarEventType").value = WebinarType;
            
            //   document.getElementById("eventType").style.display='none';
         //   document.getElementById("webinarEventTypeLabel").style.display='block';
            //eventTypeLabel
            
           // if(WebinarType=='I'){
             //   document.getElementById("webinarEventTypeLabel").innerHTML = '<font color=green size=2px>Internal Webinar</font>';
            //}else if(WebinarType=='E'){
            //    document.getElementById("webinarEventTypeLabel").innerHTML = '<font color=green size=2px>External Webinar</font>';
            //}
            document.getElementById("webinarEventAfterVideoUrl").value = VideoLink;
          
            
           // getDateFieldAppear(WebinarType);
         
            document.getElementById("webinarEventStatus").value = STATUS;
            if(detailsType!='publish'){
                document.getElementById('webinarEventStatusLabel').innerHTML =STATUS;
                document.getElementById("webinarEventStatus").style.display='none';
                document.getElementById("webinarEventStatusLabel").style.display='block';
            }
            document.getElementById('webinarEventCreatedBy').innerHTML = createdby;
            document.getElementById('webinarEventCreatedDate').innerHTML = createddate;
            document.getElementById("loadAfterVideo").style.display = 'none';
             
                 
        }, 
        error: function(e){
            document.getElementById("loadAfterVideo").style.display = 'none';
            alert("error-->"+e);
        }
    });
    showRow('webinarEventCreatedTr');
    document.getElementById("headerLabelForPublishButton").style.color="white";
    document.getElementById("headerLabelForPublishButton").innerHTML="Edit Event After Page";
    var overlay = document.getElementById('overlayForPublishButton');
    var specialBox = document.getElementById('specialBoxForPublishButton');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}

function closeCompletedWebinarToogle(fromName){
    //document.getElementById('resultMessageForPublishButton').innerHTML ='';
   // hideRow('webinarEventCreatedTr');
    /* hideRow('editTr');
            hideRow("createdTr");
*/
    //clearData();

    document.getElementById("headerLabelForPublishButton").style.color="white";
    document.getElementById("headerLabelForPublishButton").innerHTML="Add EventPosting";
    //  showRow('addTr');
    var overlay = document.getElementById('overlayForPublishButton');
    var specialBox = document.getElementById('specialBoxForPublishButton');

    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";

    //   document.getElementById("frmDBGrid").submit();

    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }

         document.getElementById(fromName).submit();
    
}


function doUpdateWebinarAfterEventPost(eventType) {
    var eventStatus = document.getElementById("webinarEventStatus").value;
    var eventAfterDescription = document.getElementById("webinarEventAfterDescription").value;
//    var primaryTrack = document.getElementById("primaryTrack").value;
//    var secondaryTrack = document.getElementById("secondaryTrack").value;
    var eventAfterVideoUrl = document.getElementById("webinarEventAfterVideoUrl").value;
    var event_id = document.getElementById("webinar_event_id").value;
    if(eventAfterDescription.trim().length==0){
        document.getElementById('resultMessageAfterVideo').innerHTML = "<font color=red>Please enter after description.</font>"; 
    }else if(eventAfterVideoUrl.trim().length==0){
        document.getElementById('resultMessageAfterVideo').innerHTML = "<font color=red>Please enter after video url.</font>"; 
    }else {
        
        document.getElementById("loadAfterVideo").style.display = 'block';
    
        $.ajax({
          //  url:'updateAfterEvent.action?eventId='+event_id+'&eventAfterDescription='+escape(eventAfterDescription)+"&eventAfterVideoUrl="+escape(eventAfterVideoUrl)+"&eventStatus="+eventStatus+"&primaryTrack="+primaryTrack+"&secondaryTrack="+secondaryTrack+"&eventType="+eventType,
            url:'updateWebinarAfterEvent.action?eventId='+event_id+'&eventAfterDescription='+escape(eventAfterDescription)+"&eventAfterVideoUrl="+escape(eventAfterVideoUrl)+"&eventStatus="+eventStatus+"&eventType="+eventType,
            context: document.body,
            success: function(responseText) {
                // displaymessage = responseText;
         
           
                document.getElementById("loadAfterVideo").style.display = 'none';
                document.getElementById('resultMessageAfterVideo').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
            // document.getElementById('fileType').value = "";
            },
            error: function(e){
           
                document.getElementById("loadAfterVideo").style.display = 'none';
                document.getElementById('resultMessageAfterVideo').innerHTML = "<font color=red>Please try again later</font>";
     
                
            }
        });
    
    }
		
    return false;
}


function getWebinarSpeakers(eventId,ObjectType){
    window.location = "../getEventSpeakers.action?eventId="+eventId+"&objectType="+ObjectType;
}



function clearSeriesTable(tableId) {
    var tbl =  tableId;
    var lastRow = tbl.rows.length; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}
var tbody ;
function getWebinarSeriesList(seriesType){
    var oTable = document.getElementById("tblSeriesList");
    clearSeriesTable(oTable);
    document.getElementById("seriesLoading").style.display = 'block';
    $.ajax({
          //  url:'updateAfterEvent.action?eventId='+event_id+'&eventAfterDescription='+escape(eventAfterDescription)+"&eventAfterVideoUrl="+escape(eventAfterVideoUrl)+"&eventStatus="+eventStatus+"&primaryTrack="+primaryTrack+"&secondaryTrack="+secondaryTrack+"&eventType="+eventType,
            url:'getWebinarSeriesList.action?seriesType='+seriesType,
            context: document.body,
            success: function(responseText) {
                // displaymessage = responseText;
         var mainJson = $.parseJSON(responseText);
          // alert('length-->'+Object.keys(mainJson).length);
           
           
           
           
            if(Object.keys(mainJson).length > 0){
                 var headerFields = new Array("Sno","Title","TrackName","Status","CreatedDate","AddWebinars");
                  tbody = document.createElement("TBODY");
                  generateSeriesTableHeader(tbody,headerFields);
        oTable.appendChild(tbody);
                  var key;
                  var index=0;
               // for (key in mainJson) {
                     for (key = 0; key < Object.keys(mainJson).length; key++) { 
                    if (mainJson.hasOwnProperty(key)) {
                        var subJson =mainJson[key];
                       // alert(subJson["SeriesTitle"]);
                     generateRowForWebinarSeries(tbody,subJson,index);
                       
                       // alert(subJson["SeriesTitle"]);
                        index++;
                    }
                }
                
                 generateSeriesFooter(tbody);
                 
            }else {
        alert("No Records Found");
    }
           
          
           
                document.getElementById("seriesLoading").style.display = 'none';
               // document.getElementById('resultMessageAfterVideo').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
            
            },
            error: function(e){
           
                document.getElementById("seriesLoading").style.display = 'none';
               // document.getElementById('resultMessageAfterVideo').innerHTML = "<font color=red>Please try again later</font>";
     
                
            }
        });
    
}

function generateRowForWebinarSeries(tableBody,seriesJson,index){

       // alert("role Id in genarate row:"+AjaxRoleId);
    
   // alert("rowFeildsSplit---------------------"+rowFeildsSplit[rowFeildsSplit.length-5]);
    var row;
    var cell;
    row = document.createElement("TR");
    row.className="gridRowEven";
    cell = document.createElement("TD");
    cell.className="gridRowEven";
    cell.innerHTML = index+1;
  //  cell.setAttribute('align','center');
    row.appendChild(cell);
    tableBody.appendChild(row);
        //SeriesTitle
            cell = document.createElement( "TD" );
            cell.className="gridRowEven";
            row.appendChild(cell)
           // cell.setAttribute('align','center');
            //cell.innerHTML = seriesJson["SeriesTitle"];
            cell.innerHTML = " ";
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:getWebinarSeriesDetails("+seriesJson["Id"]+")");
            j.appendChild(document.createTextNode(seriesJson["SeriesTitle"]));
            cell.appendChild(j);
           // cell.align="center";
             //TrackName
            cell = document.createElement( "TD" );
            cell.className="gridRowEven";
            row.appendChild(cell)
           // cell.setAttribute('align','center');
            cell.innerHTML = seriesJson["SeriesTrack"];
            
             //Status
            cell = document.createElement( "TD" );
            cell.className="gridRowEven";
            row.appendChild(cell)
          //  cell.setAttribute('align','center');
            cell.innerHTML = seriesJson["SeriesStatus"];
            
             //CreatedDate
            cell = document.createElement( "TD" );
            cell.className="gridRowEven";
            row.appendChild(cell)
           // cell.setAttribute('align','center');
            cell.innerHTML = seriesJson["CreatedDate"];
            
             //AddWebinars
            cell = document.createElement( "TD" );
            cell.className="gridRowEven";
            row.appendChild(cell)
             cell.innerHTML = "";
            var j = document.createElement("a");
            //j.setAttribute("href", "javascript:getWebinarsBySeriesId("+seriesJson["Id"]+")");
            j.setAttribute("href", "javascript:addWebinarsToSeries("+seriesJson["Id"]+")");
            
            var element = document.createElement("img");
            element.setAttribute("src", "../../includes/images/go_21x21.gif");
            j.appendChild(element);
            cell.appendChild(j);
            cell.setAttribute('align','center');
           // cell.innerHTML = "<IMG src='../../includes/images/go_21x21.gif'/>";
            
        
        //cell.width = 120;
    
}



function generateSeriesTableHeader(tableBody,headerFields) {
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridHeader";
    tableBody.appendChild( row );
    for (var i=0; i<headerFields.length; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridHeader";
        row.appendChild( cell );

        cell.setAttribute("width","10000px");
        cell.innerHTML = headerFields[i];
    }
}



function generateSeriesFooter(tbody) {
   // alert(tbody);
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";

        cell.colSpan = "6";

    footer.appendChild(cell);
}


function addWebinarSeries() {
     // alert("hi");
    document.getElementById('seriesResultMessage').innerHTML ='';
   
    
    document.getElementById("seriesHeaderLabel").style.color="white";
    document.getElementById("seriesHeaderLabel").innerHTML="Add Webinar Series";
    showRow('seriesAddTr');

    var overlay = document.getElementById('overlayForWebinarSeries');
    var specialBox = document.getElementById('specialBoxWebinarSeries');
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
}



function doAddWebinarSeries() {
    document.getElementById('seriesResultMessage').innerHTML ='';

   var seriesTitle= document.getElementById("seriesTitle").value;
var seriesType=document.getElementById("seriesType").value;
var seriesTrack=document.getElementById("seriesTrack").value;
var seriesStatus=document.getElementById("seriesStatus").value;

            
if(seriesTitle.trim().length==0){
       document.getElementById('seriesResultMessage').innerHTML = "<font color=red>Please enter series title.</font>";
   }else if(seriesType.trim().length==0){
       document.getElementById('seriesResultMessage').innerHTML = "<font color=red>Please enter series type.</font>";
   }
   else if(seriesTrack.trim().length==0){
       document.getElementById('seriesResultMessage').innerHTML = "<font color=red>Please enter series track.</font>";
   }
   else if(seriesStatus.trim().length==0){
       document.getElementById('seriesResultMessage').innerHTML = "<font color=red>Please select series status.</font>";
   }
   
  
   else
       {
    document.getElementById("seriesLoad").style.display = 'block';
    
        $.ajax({
           // url:'editJobposting.action?jobId='+jobId,//
           //url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
           url:'doAddWebinarSeries.action?seriesTitle='+seriesTitle+'&seriesType='+seriesType+"&seriesTrack="+seriesTrack+"&seriesStatus="+seriesStatus,
            context: document.body,
            success: function(responseText) {
               // displaymessage = responseText;
         
           
            document.getElementById("seriesLoad").style.display = 'none';
            document.getElementById('seriesResultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
        // document.getElementById('fileType').value = "";
        },
        error: function(e){
           
            document.getElementById("seriesLoad").style.display = 'none';
            document.getElementById('seriesResultMessage').innerHTML = "<font color=red>Please try again later</font>";
     
                
        }
    });
    
       }
		
    return false;
}


function getWebinarSeriesDetails(seriesId){
hideRow("seriesAddTr");
    hideRow("seriesEditTr");
     document.getElementById('seriesResultMessage').innerHTML ='';
  
    document.getElementById("seriesLoad").style.display = 'block';
   $.ajax({
            url:'getWebinarSeriesDetails.action?seriesId='+seriesId,//
            context: document.body,
            success: function(responseText) {
               // alert(responseText);
                var json = $.parseJSON(responseText);
                //Id,event_id,event_speaker,event_designation,primary_speaker,Company
                var Id = json["Id"];
              
                var SeriesTitle = json["SeriesTitle"];
                var SeriesType = json["SeriesType"];
               
                var SeriesTrack = json["SeriesTrack"];
             
                var SeriesStatus = json["SeriesStatus"];
                var CreatedDate = json["CreatedDate"];
              var CreatedBy = json["CreatedBy"];
             
                
                
                document.getElementById("webinarSerieId").value = Id;
                document.getElementById("seriesTitle").value = SeriesTitle;
                document.getElementById("seriesType").value = SeriesType;
                document.getElementById("seriesTrack").value = SeriesTrack;
                document.getElementById("seriesStatus").value = SeriesStatus;
                document.getElementById("createdBySeries").innerHTML = CreatedBy;
                document.getElementById("createdDateSeries").innerHTML = CreatedDate;
           
                 document.getElementById("seriesLoad").style.display = 'none';
              showRow("seriesEditTr");
              //showRow("seriesAddTr");
                 
            }, error: function(e){
                document.getElementById("seriesLoad").style.display = 'none';
                alert("error-->"+e);
            }
        });
    
    
    
     
   document.getElementById("seriesHeaderLabel").style.color="white";
            document.getElementById("seriesHeaderLabel").innerHTML="Edit Series Details";
    var overlay = document.getElementById('overlayForWebinarSeries');
    var specialBox = document.getElementById('specialBoxWebinarSeries');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
    
}

function doUpdateWebinarSeriesDetails() {
    //eventStatus 
    //eventAfterDescription
    //primaryTrack
    //secondaryTrack
    //eventAfterVideoUrl
    //event_id
    var seriesId= document.getElementById("webinarSerieId").value;
     var seriesTitle= document.getElementById("seriesTitle").value;
var seriesType=document.getElementById("seriesType").value;
var seriesTrack=document.getElementById("seriesTrack").value;
var seriesStatus=document.getElementById("seriesStatus").value;

            
if(seriesTitle.trim().length==0){
       document.getElementById('seriesResultMessage').innerHTML = "<font color=red>Please enter series title.</font>";
   }else if(seriesType.trim().length==0){
       document.getElementById('seriesResultMessage').innerHTML = "<font color=red>Please enter series type.</font>";
   }
   else if(seriesTrack.trim().length==0){
       document.getElementById('seriesResultMessage').innerHTML = "<font color=red>Please enter series track.</font>";
   }
   else if(seriesStatus.trim().length==0){
       document.getElementById('seriesResultMessage').innerHTML = "<font color=red>Please select series status.</font>";
   }
   
  
   else
       {
    document.getElementById("seriesLoad").style.display = 'block';
    
        $.ajax({
           // url:'editJobposting.action?jobId='+jobId,//
           //url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
           url:'doUpdateWebinarSeriesDetails.action?seriesTitle='+seriesTitle+'&seriesType='+seriesType+"&seriesTrack="+seriesTrack+"&seriesStatus="+seriesStatus+"&seriesId="+seriesId,
            context: document.body,
            success: function(responseText) {
               // displaymessage = responseText;
         
           
            document.getElementById("seriesLoad").style.display = 'none';
            document.getElementById('seriesResultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
        // document.getElementById('fileType').value = "";
        },
        error: function(e){
           
            document.getElementById("seriesLoad").style.display = 'none';
            document.getElementById('seriesResultMessage').innerHTML = "<font color=red>Please try again later</font>";
     
                
        }
    });
    
       }
		
    return false;
}


function closeSeriesToggleOverlay(seriesType) {
 document.getElementById("seriesHeaderLabel").style.color="white";
    document.getElementById("seriesHeaderLabel").innerHTML="Add Webinar Series";
    //  showRow('addTr');
    var overlay = document.getElementById('overlayForWebinarSeries');
    var specialBox = document.getElementById('specialBoxWebinarSeries');

    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";

    //   document.getElementById("frmDBGrid").submit();

    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
    
   // getWebinarSeriesList(seriesType);
if(seriesType=='E')
         document.getElementById("frmDBGrid").submit();
     else
         document.getElementById("frmInternalWebinarDBGrid").submit();
         
}


function getWebinarsBySeriesId(seriesId){
    window.location="getWebinarsListBySeriesId.action?seriesId="+seriesId;
}

function removeWebinarsFromSeries(eventId,seriesId){
    
   
    if (confirm("Do you want to remove Event from series!") == true) {
       window.location = "removeWebinarsFromSeries.action?seriesId="+seriesId+"&eventId="+eventId;
    } 
    
}



function getQmeetAttendees(eventId){
    
    window.location="getQmeetAttendees?eventId="+eventId;
    //alert(eventId);
}



function closeQmeetAttendeeOverlay() {
      document.getElementById('resultMessage').innerHTML ='';


            document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Add Speaker";
          //  showRow('addTr');
            var overlay = document.getElementById('overlay');
            var specialBox = document.getElementById('specialBox');

            overlay.style.opacity = .8;
            if(overlay.style.display == "block"){
            overlay.style.display = "none";
            specialBox.style.display = "none";

         //   document.getElementById("frmDBGrid").submit();
         
            }
            else {
            overlay.style.display = "block";
            specialBox.style.display = "block";
            }
}




function getQmeetAttendeeDetails(attendeeId){
    document.getElementById("load").style.display = 'block';
    $.ajax({
            url:'getQmeetAttendeeDetails.action?infoId='+attendeeId,//
            context: document.body,
            success: function(responseText) {
               // alert(responseText);
                var json = $.parseJSON(responseText);
                
                
                var Id = json["Id"];
                    var firstname = json["firstname"];
                    var EventName = json["EventName"];
                    var email_id = json["email_id"];
                    var phone_no = json["phone_no"];
                    var department = unescape(json["department"]);
                    var location = json["location"];
                    var foodpref = json["foodpref"];
                    var alongmember = json["alongmember"];
                    var cor_transport = json["cor_transport"];
                    var DropPoint = json["DropPoint"];
                    var CreatedDate = json["CreatedDate"];
                   
                    
                    
                     document.getElementById("fullName").value = firstname;
                    document.getElementById("emailId").value = email_id;
                    document.getElementById("workPhone").value = phone_no;
                    document.getElementById("department").value = department;
                    document.getElementById("foodPreference").value = foodpref;
                    document.getElementById("corporateTransport").value = cor_transport;
                    document.getElementById("dropPoint").value = DropPoint;
                    document.getElementById("bringingAlong").value = alongmember;
                    
                  
                    document.getElementById("createdDate").innerHTML = '<font size=2px color=green>'+CreatedDate+'</font>';
                       document.getElementById("load").style.display = 'none';
                    document.getElementById("headerLabel").innerHTML="QuarterlyMeet Attendee Details";
                    }, error: function(e){
                document.getElementById("load").style.display = 'none';
                alert("error-->"+e);
            }
        });
        
        
   document.getElementById("headerLabel").style.color="white";
            
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}


 
function editExecuteMeetAttendees(Id){
    // alert(Id);
    hideRow('eMeetAtndAddTr');
    showRow('eMeetAtndEditTr');
    document.getElementById('resultMessageforexecuteMeetAttendees').innerHTML ='';
    document.getElementById("loadforexecuteMeetAttendees").style.display = 'block';
    $.ajax({
        url:'editAttendeesforExeMeeting.action?id='+Id,//
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
           // alert("CreatedBy--"+CreatedBy);
            
            // alert(AccessType);
            document.getElementById("executiveMeetAccessType").value=AccessType;
            document.getElementById("previousAttendeeStatus").value=CurrStatus;
            document.getElementById("eMeetAttendeeloginId").value=LoginId;
              
            document.getElementById("executiveMeetingAttendeeEmail").style.display='none';
            document.getElementById("eMeetAttendeeId").value = Id;
          
            document.getElementById("executiveMeetingAccessStatus").value=CurrStatus;
            document.getElementById("createdByEmeetAttnd").innerHTML = CreatedBy;
            document.getElementById("createdDateEmeetAttnd").innerHTML = CreatedDate;
          
            document.getElementById("emailSpanId").innerHTML = '<font color=green size=2px>'+Email1+'</font>';
          
            document.getElementById("loadforexecuteMeetAttendees").style.display = 'none';
        // getSlider(4,7);
        //getSlider(parseInt(jobExp[0],10),parseInt(jobExp[1],10));
        }, 
        error: function(e){
            document.getElementById("loadforexecuteMeetAttendees").style.display = 'none';
            alert("error-->"+e);
        }
    });
    
    
    
    document.getElementById("headerLabelforexecuteMeetAttendees").style.color="white";
    document.getElementById("headerLabelforexecuteMeetAttendees").innerHTML="Edit Executive Meeting Attendees";
    var overlay = document.getElementById('executeMeetAttendeesoverlay');
    var specialBox = document.getElementById('executeMeetAttendeesspecialBox');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}

function toggleOverlayForExecuteMeetAttendees(){
    //alert("hi");
    document.getElementById('resultMessageforexecuteMeetAttendees').innerHTML ='';
    hideRow('eMeetAtndAddTr');
    hideRow('eMeetAtndEditTr');
    hideRow("eMeetAtndCreatedTr");
    document.getElementById("headerLabelforexecuteMeetAttendees").style.color="white";
    document.getElementById("headerLabelforexecuteMeetAttendees").innerHTML="Add Executive Meeting";
    showRow('eMeetAtndAddTr');
    var overlay = document.getElementById('executeMeetAttendeesoverlay');
    var specialBox = document.getElementById('executeMeetAttendeesspecialBox');
    //  hideRow("approvedBy");
    //hideRow("tlcommentsTr");
    // hideRow("hrcommentsTr");
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
              
        //document.getElementById("frmDBGrid").submit();
                
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
            
    //  window.location = "jobSearch.action";
    document.getElementById("frmDBGrid").submit();
}

function addAttendeesForExecutiveMeet(){
    //    alert("hi");
    document.getElementById('resultMessageforexecuteMeetAttendees').innerHTML ='';
    hideRow('eMeetAtndAddTr');
    hideRow('eMeetAtndEditTr');
    hideRow("eMeetAtndCreatedTr");
    
    document.getElementById("headerLabelforexecuteMeetAttendees").style.color="white";
    document.getElementById("headerLabelforexecuteMeetAttendees").innerHTML="Add Executive Meeting Attendee";
    showRow('eMeetAtndAddTr');
            
   
    document.getElementById("executiveMeetingAttendeeEmail").style.display='block';
            
    var overlay = document.getElementById('executeMeetAttendeesoverlay');
    var specialBox = document.getElementById('executeMeetAttendeesspecialBox');
   
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

function doAddAttendeesforExecutiveMeetAttendees()
{
     
   
    var eMeetAccessType= document.getElementById("executiveMeetAccessType").value;
             
    var emeetAttendeesEmail=document.getElementById("executiveMeetingAttendeeEmail").value;
          
    var executiveMeetingAccessStatus=document.getElementById("executiveMeetingAccessStatus").value;
    if(emeetAttendeesEmail==-1){
        //  alert("please enter email");
        document.getElementById('resultMessageforexecuteMeetAttendees').innerHTML = "<font color=red>please enter email.</font>";
        return false;
    }
    else if(eMeetAccessType=='')
    {
        // alert("please enter Acccess Type");
        document.getElementById('resultMessageforexecuteMeetAttendees').innerHTML = "<font color=red>please enter Acccess Type.</font>";
        return false;
    }

   
    else
    {
        //hideRow('addTr');
        hideRow('eMeetAtndEditTr');
      //  setTimeout(disableFunction,1);
        document.getElementById("loadforexecuteMeetAttendees").style.display = 'block';
     hideRow('eMeetAtndAddTr');
        $.ajax({
            // url:'editJobposting.action?jobId='+jobId,//
            //url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
            url:'addAttendeesForExecitiveMeet.action?executiveMeetAccessType='+eMeetAccessType+'&executiveMeetingAttendeeEmail='+emeetAttendeesEmail+"&executiveMeetingAccessStatus="+executiveMeetingAccessStatus,
            context: document.body,
            success: function(responseText) {
                // displaymessage = responseText;
         
           
                document.getElementById("loadforexecuteMeetAttendees").style.display = 'none';
                document.getElementById('resultMessageforexecuteMeetAttendees').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
            // document.getElementById('fileType').value = "";
            },
            error: function(e){
           
                document.getElementById("loadforexecuteMeetAttendees").style.display = 'none';
                document.getElementById('resultMessageforexecuteMeetAttendees').innerHTML = "<font color=red>Please try again later</font>";
     
                
            }
        });
    
    }
		
    return false;

}

function doUpdateAttendeesDetailsforExecitiveMeeting(){
    
     hideRow('eMeetAtndAddTr');
    //hideRow('editTr');
   
    var eMeetAccessType= document.getElementById("executiveMeetAccessType").value;
    var executiveMeetingAccessStatus=document.getElementById("executiveMeetingAccessStatus").value;
    var id=document.getElementById("eMeetAttendeeId").value; 
    
     var previousStatus=document.getElementById("previousAttendeeStatus").value;
       var loginId=  document.getElementById("eMeetAttendeeloginId").value;
    //   alert("hiii");
    if(eMeetAccessType=='')
    {
        // alert("please enter Acccess Type");
        document.getElementById('resultMessageforexecuteMeetAttendees').innerHTML = "<font color=red>please enter Acccess Type.</font>";
        return false;
    }else {

    document.getElementById("updateButtonEmeetAttendee").disabled = 'true';
    document.getElementById("loadforexecuteMeetAttendees").style.display = 'block';
   // hideRow('eMeetAtndEditTr');
    $.ajax({
        //  url:'updateJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+'&location='+location+'&jobcountry='+jobcountry+'&jobstatus='+jobstatus+'&jobdescription='+jobdescription+'&jobtechnical='+jobtechnical+'&jobshiftskills='+jobshiftskills+'&attachmentLocation='+file+'&jobId='+jobId,//
        url:'updateAttendeesDetailsforExecitiveMeeting.action?executiveMeetAccessType='+eMeetAccessType+"&executiveMeetingAccessStatus="+executiveMeetingAccessStatus+"&id="+id+"&loginId="+loginId+"&previousStatus="+previousStatus,
        // alert(url);
        context: document.body,
        success: function(responseText) {
            var displaymessage = responseText;
            document.getElementById("loadforexecuteMeetAttendees").style.display = 'none';
            document.getElementById('resultMessageforexecuteMeetAttendees').innerHTML = "<font color=green>"+displaymessage+"</font>";//"<font color=green>File uploaded successfully</font>";
        // document.getElementById('fileType').value = "";
        },
        error: function(e){
            // alert('Error:111 ' + e);
            document.getElementById("loadforexecuteMeetAttendees").style.display = 'none';
            document.getElementById('resultMessageforexecuteMeetAttendees').innerHTML = "<font color=red>Please try again later</font>";
        // document.getElementById('fileType').value = "";
        //  document.getElementById('profile').value="";
                
        }
    });
    }
    // }
		
    return false;
}





function eventsLengthValidator(element,errMsgId) {
document.getElementById(errMsgId).innerHTML ='';

    var i=0;
    var lableName='';
 //alert("In Field Length validator ESCV");
    if(element.value!=null&&(element.value!="")) {
        if(element.id=="eventtitle" || element.id=="conferenceEventtitle" || element.id=="ieeEventtitle" || element.id=="iweventtitle") { 
            i=200;
            lableName = 'Upcoming Page Title';
        }else  if(element.id=="eventBoldtitle" || element.id=="iweventBoldtitle") { 
            i=1000;
            lableName = 'Page Title';
        }else  if(element.id=="eventLocation" || element.id=="conferenceEventLocation" || element.id=="ieeEventLocation"|| element.id=="iweventLocation" ) { 
            i=80;
            lableName = 'Location';
        }else  if(element.id=="eventDescription" || element.id=="iweventDescription") { 
            i=5000;
            lableName = 'Event Description';
        }else  if(element.id=="webinarEventAfterDescription") { 
            i=5000;
            lableName = 'Event After Description';
        }else  if(element.id=="seriesTitle") { 
            i=500;
            lableName = 'Series Title';
        }else  if(element.id=="transportation") { 
            i=1000;
            lableName = 'Transportation';
        }
        
        
     
     
        if(element.value.replace(/^\s+|\s+$/g,"").length>i) {
            str=new String(element.value);
            element.value=str.substring(0,i);
            document.getElementById(errMsgId).innerHTML = "<font color=red>The "+lableName+" length must be less than "+i+" characters</font>";
            //alert("The "+lableName+" length must be less than "+i+" characters");
            element.focus();
            return false;
        }
        return true;
        
        
    }
}


function isValidUrl(obj,errMsg) {
    document.getElementById(errMsg).innerHTML = '';
  var  url_validate = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
if(!url_validate.test(obj.value)){
     document.getElementById(errMsg).innerHTML = "<font color=red>Invalid Url</font>";
     obj.value = '';
  // alert('error');
}

}


function checkEventsEmail(element,errMsg){
    if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(element.value)){return(true);
 }element.value="";
 //alert("Invalid E-mail Address! Please re-enter.");
 document.getElementById(errMsg).innerHTML = "<font color=red>Invalid E-mail Address! Please re-enter.</font>";
 return(false);
 }
 
 
 function addWebinarsToSeries(seriesId){
    //    alert("hi");
    document.getElementById('seriesWebinarResultMessage').innerHTML ='';
    hideRow("associateTr");
    
    document.getElementById("associateWebiarHeaderLabel").style.color="white";
    document.getElementById("associateWebiarHeaderLabel").innerHTML="Associate Webianr to Series";
    //showRow('eMeetAtndAddTr');
            
   
    //document.getElementById("executiveMeetingAttendeeEmail").style.display='block';
            
    var overlay = document.getElementById('overlayforAssociateWebinatToSeriesTab');
    var specialBox = document.getElementById('specialBoxforAssociateWebinatToSeriesTab');
   
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
            
            
    
    getWebinarsListBySeriesId(seriesId);
    
       
            
            
            
            
//window.location = "jobSearch.action";
}


function closeOverlayForWebinarsToSeries(){
    //alert("hi");
    document.getElementById('seriesWebinarResultMessage').innerHTML ='';
   
    document.getElementById("associateWebiarHeaderLabel").style.color="white";
    document.getElementById("associateWebiarHeaderLabel").innerHTML="Associate Webianr to Series";
    
    
    
    var overlay = document.getElementById('overlayforAssociateWebinatToSeriesTab');
    var specialBox = document.getElementById('specialBoxforAssociateWebinatToSeriesTab');
    //  hideRow("approvedBy");
    //hideRow("tlcommentsTr");
    // hideRow("hrcommentsTr");
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
              
        //document.getElementById("frmDBGrid").submit();
                
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
    
    
            
    //  window.location = "jobSearch.action";
    
    //document.getElementById("frmDBGrid").submit();
    document.getElementById($("#frmName").val()).submit();
}




function getWebinarsListBySeriesId(seriesId) {
    document.getElementById('seriesWebinarResultMessage').innerHTML ='';
    document.getElementById("seriesWebiarLoad").style.display = 'block';
    document.getElementById("associateSeriesId").value=seriesId;
     var oTable = document.getElementById("tblWebinarsList");
     clearSeriesTable(oTable);
     $("#associateAddButton").attr("disabled", "disabled");
    // alert("Haii-->"+seriesId);
     $.ajax({
          //  url:'updateAfterEvent.action?eventId='+event_id+'&eventAfterDescription='+escape(eventAfterDescription)+"&eventAfterVideoUrl="+escape(eventAfterVideoUrl)+"&eventStatus="+eventStatus+"&primaryTrack="+primaryTrack+"&secondaryTrack="+secondaryTrack+"&eventType="+eventType,
            url:'getAjaxWebinarsListBySeriesId.action?seriesId='+seriesId,
            context: document.body,
            success: function(responseText) {
                //alert("Haiii");
            
                // displaymessage = responseText;
             //   alert(responseText);
         var mainJson = $.parseJSON(responseText);
        // var mainJson1 =  '{"0":{"WebinarType":"E","event_id":"205","SeriesId":"34","event_title":"Technical Webinar  Building Microservices with the Mulesoft Anypoint Platform","event_startdate":"2017-01-18"}}';
          //var mainJson = JSON.stringify(responseText);
          //var mainJson = JSON.stringify(eval("(" + mainJson1 + ")"));
          //alert(Object.keys(mainJson).length);
          
           //UnAssociatedWebinars
           var unasociatedWebinars=mainJson["UnAssociatedWebinars"];
           var key;
          // $('#associatedEventId').empty();
           var associatedEventId = document.getElementById("associatedEventId");
           //var len=associatedEventId.options.length
    while (associatedEventId.options.length) {
        
        associatedEventId.remove(0);
 
    }
  $('#associatedEventId').append($('<option>').text("--Please Select--").attr('value', ""));
  
           //alert(Object.keys(unasociatedWebinars).length)
           if(Object.keys(unasociatedWebinars).length > 0){
               for (key = 0; key < Object.keys(unasociatedWebinars).length; key++) { 
                //   alert(key);
                if (unasociatedWebinars.hasOwnProperty(key)) {
                       var eventsJson =unasociatedWebinars[key];
                   
                        $('#associatedEventId').append($('<option>').text(eventsJson["event_title"]).attr('value', eventsJson["event_id"]));
                 }
                   
               }
               
           }
          // showRow("seriesTitleTr");
           showRow("associateTr");
           key=0;
         if(Object.keys(mainJson).length > 1){
                 var headerFields = new Array("Sno","EventTitle","EventDate","Remove");
                  tbody = document.createElement("TBODY");
                  generateSeriesTableHeader(tbody,headerFields);
        oTable.appendChild(tbody);
                  
                  var index=0;
               // for (key in mainJson) {
                     for (key = 0; key < Object.keys(mainJson).length-1; key++) { 
                    if (mainJson.hasOwnProperty(key)) {
                        var subJson =mainJson[key];
                       // alert(subJson["SeriesTitle"]);
                     // generateRowForWebinarSeries(tbody,subJson,index);
                         generateRowForAssociatedWebinars(tbody,subJson,index);
                      //  alert(subJson["event_title"]);
                        index++;
                    }
                }
                
                 generateSeriesFooter(tbody);
                 
            }else {
        alert("No Records Found");
    }
    
           
          
           $("#associateAddButton").removeAttr("disabled");
                document.getElementById("seriesWebiarLoad").style.display = 'none';
               // document.getElementById('resultMessageAfterVideo').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
            
            },
            error: function(e){
           alert("error");
                document.getElementById("seriesWebiarLoad").style.display = 'none';
                document.getElementById('seriesWebinarResultMessage').innerHTML = "<font color=red>Please try again later</font>";
                $("#associateAddButton").removeAttr("disabled");
     
                
            }
        });
    
}



function generateRowForAssociatedWebinars(tableBody,seriesJson,index){

       // alert("role Id in genarate row:"+AjaxRoleId);
    
   // alert("rowFeildsSplit---------------------"+rowFeildsSplit[rowFeildsSplit.length-5]);
    var row;
    var cell;
    row = document.createElement("TR");
    row.className="gridRowEven";
    cell = document.createElement("TD");
    cell.className="gridRowEven";
    cell.innerHTML = index+1;
  //  cell.setAttribute('align','center');
    row.appendChild(cell);
    tableBody.appendChild(row);
        
             //EventTitle
            cell = document.createElement( "TD" );
            cell.className="gridRowEven";
            row.appendChild(cell)
           // cell.setAttribute('align','center');
            cell.innerHTML = seriesJson["event_title"];
            
             //EventDate
            cell = document.createElement( "TD" );
            cell.className="gridRowEven";
            row.appendChild(cell)
          //  cell.setAttribute('align','center');
            cell.innerHTML = seriesJson["event_startdate"];
            
             
            
             //Remove Webinar
            cell = document.createElement( "TD" );
            cell.className="gridRowEven";
            row.appendChild(cell)
             cell.innerHTML = "";
            var j = document.createElement("a");
            j.setAttribute("href", "#");
            j.setAttribute("onclick", "addOrRemoveWebinarToSeries('remove',"+seriesJson["event_id"]+")");
            
            var element = document.createElement("img");
            element.setAttribute("src", "../../includes/images/go_21x21.gif");
            j.appendChild(element);
            cell.appendChild(j);
            cell.setAttribute('align','center');
           // cell.innerHTML = "<IMG src='../../includes/images/go_21x21.gif'/>";
            
        
        //cell.width = 120;
    
}




function addOrRemoveWebinarToSeries(opertaionType,eventId) {
     
         
    //hideRow('editTr');
   document.getElementById('seriesWebinarResultMessage').innerHTML ='';
    var seriesId= document.getElementById("associateSeriesId").value;
    
        
    var webinarId=document.getElementById("associatedEventId").value;
    if(opertaionType=='remove'){
        seriesId = 0;
        webinarId=eventId;
        
    }
    
    if(webinarId==''&&opertaionType=='add'){
        document.getElementById('seriesWebinarResultMessage').innerHTML = "<font color=red>Please select webinar from Associate&nbsp;Event </font>";
    }
    
    
    else if(confirm("Do you want to "+opertaionType+" webinar !") == true){
$("#associateAddButton").attr("disabled", "disabled");
 var oTable = document.getElementById("tblWebinarsList");
     clearSeriesTable(oTable);
  //  document.getElementById("associateAddButton").disabled = 'true';
    document.getElementById("seriesWebiarLoad").style.display = 'block';
   // hideRow('eMeetAtndEditTr');
    $.ajax({
        //  url:'updateJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+'&location='+location+'&jobcountry='+jobcountry+'&jobstatus='+jobstatus+'&jobdescription='+jobdescription+'&jobtechnical='+jobtechnical+'&jobshiftskills='+jobshiftskills+'&attachmentLocation='+file+'&jobId='+jobId,//
        url:'addOrRemoveWebinarToSeries.action?seriesId='+seriesId+"&eventId="+webinarId,
        // alert(url);
        context: document.body,
        success: function(responseText) {
            var displaymessage = responseText;
            document.getElementById("seriesWebiarLoad").style.display = 'none';
            
            getWebinarsListBySeriesId(document.getElementById("associateSeriesId").value);
           // document.getElementById('seriesWebinarResultMessage').innerHTML = "<font color=green>"+displaymessage+"</font>";//"<font color=green>File uploaded successfully</font>";
        // document.getElementById('fileType').value = "";
        
        $("#associateAddButton").removeAttr("disabled");
        },
        error: function(e){
            // alert('Error:111 ' + e);
            document.getElementById("seriesWebiarLoad").style.display = 'none';
            document.getElementById('seriesWebinarResultMessage').innerHTML = "<font color=red>Please try again later</font>";
        // document.getElementById('fileType').value = "";
        //  document.getElementById('profile').value="";
        
        $("#associateAddButton").removeAttr("disabled");
                
        }
        
    });
    //document.getElementById("associateAddButton").disabled = 'false';
    }
    // }
		
    return false;
    
}
	
	

/*Event functionality new enhancements end
 * Date : 01/23/2017
 * Author : Santosh Kola/Phani Kanuri
 */