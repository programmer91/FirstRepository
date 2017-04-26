/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



function fileValidation() {
    var imagePath =document.getElementById('file').value;
    // alert(imagePath);
    if(imagePath.length<30){
        // alert("imagePath-->"+imagePath);
        if(imagePath != null && (imagePath !="")) {
                    

            var extension = imagePath.substring(imagePath.lastIndexOf('.')+1);

            if(extension=="pdf"||extension=="jpg"|| extension=="png"||extension=="jpeg"){
                // document.imageForm.imagePath.focus();
                var size = document.getElementById('file').files[0].size;
                //alert("size-->"+size);
                // alert("size in mb-->"+(parseInt(size)/1000000));
                //if((parseInt(size)/1000000)<2) {
                if(parseInt(size)<20000000) {
                    return (true);
                }else {
                    document.getElementById('file').value = "";
                    // alert("File size must be less than 1 MB.");
                    document.getElementById('resultMessage').innerHTML = "<font color=red>File size must be less than 20 MB.</font>"
                    return (false);
                }
            }else {
                document.getElementById('file').value = "";
                document.getElementById('resultMessage').innerHTML = "<font color=red>Invalid file extension!Please select pdf or jpg or jpeg or png file.</font>"
                // alert("Invalid file extension!Please select pdf or jpg or png file.");
                return (false);
            }
        }
    }else {
        document.getElementById('resultMessage').innerHTML = "<font color=red>File name must be less than 30 characters!</font>"
        //alert("File name must be less than 30 characters!");
        document.getElementById('file').value = "";
    }
    return (false);
};

function doJobPost()
{
     //alert("in file uploading ------");
    /* var objType = document.getElementById("fileType").value;
    
   
     if(objType=="-1"){
         document.getElementById('resultMessage').innerHTML = "<font color=red>Please select file type!!</font>"
        return false;
     }
    */    hideRow('addTr');
    hideRow('editTr');
   
            var jobtitle= document.getElementById("jobtitle").value;
                 //alert(jobtitle);
                var jobtagline= document.getElementById("jobtagline").value;
                //alert(jobtagline);
            var jobposition= document.getElementById("jobposition").value ;
           // alert(jobposition);
             var jobqulification=document.getElementById("jobqulification").value;
           //  alert(jobqulification);
             var jobshifits=document.getElementById("jobshifits").value;
            // alert(jobshifits);
             var location=document.getElementById("location").value;
            // alert(location);
              var jobcountry=document.getElementById("jobcountry").value;
            //  alert(jobcountry);
             var jobstatus=document.getElementById("jobstatus").value;
            // alert(jobstatus);
             var jobdescription=escape(document.getElementById("jobdescription").value);
             
             
             
             
             
             
            // alert(jobdescription);
             var jobtechnical=escape(document.getElementById("jobtechnical").value);
            // alert(jobtechnical);
             var jobshiftskills=escape(document.getElementById("jobshiftskills").value);
           //  alert(jobshiftskills);
               // var file=document.getElementById("file").value;
                             //alert(file);
   if(jobtitle.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter jobtitle.</font>";
   }else if(jobtagline.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter jobtagline.</font>";
   }else if(jobposition.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  jobposition.</font>";
   }else if(jobqulification.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  jobqulification.</font>";
   }
   else if(jobshifits.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter jobshifits.</font>";
   }else if(location.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  location.</font>";
   }
   else if(jobdescription.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  jobdescription.</font>";
   }else if(jobtechnical.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter jobtechnical.</font>";
   }
   else if(jobshiftskills.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  jobshiftskills.</font>";
   }
   
        //setTimeout(disableFunction,1);
   
   else
       {
   
    document.getElementById("load").style.display = 'block';
  /*  $.ajaxFileUpload({
    //    url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills+"&attachmentLocation="+file,//
        url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,//
        secureuri:false,//false
        fileElementId:'file',//id  <input type="file" id="file" name="file" />
        dataType: 'json',// json
        success: function(data,status){
           */ 
         $.ajax({
           // url:'editJobposting.action?jobId='+jobId,//
           url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
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
                document.getElementById("load").style.display = 'none';
                responseHandler(req.responseText);
            }
        }
        else {
            document.getElementById("load").style.display = 'block';
        //alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }
}




        
              function toggleOverlay(){
                  //alert("hi");
                   document.getElementById('resultMessage').innerHTML ='';
               hideRow('addTr');
    hideRow('editTr');
     hideRow("createdTr");
            // hideRow('downloadTr');
            // showRow('uploadTr');
            //document.getElementById("downloadTr").style.display = 'none';
            //document.getElementById("uploadTr").style.display = '';
            document.getElementById("jobtitle").value = '';
            document.getElementById("jobtagline").value = '';
            document.getElementById("jobposition").value = '';
            document.getElementById("jobqulification").value = '';
            document.getElementById("jobshifits").value = '';
            document.getElementById("location").value = '';
             document.getElementById("jobcountry").value = '';
            document.getElementById("jobstatus").value = '';
            document.getElementById("jobdescription").value = '';
            document.getElementById("jobtechnical").value = '';
            document.getElementById("jobshiftskills").value = '';
            //document.getElementById("overlayjobposition").value = '';
            
           // document.getElementById("overlayReviewDate").value = '';
           // document.getElementById("overlayDescription").value = '';
            document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Add JobPosting";
            showRow('addTr');
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
            
            window.location = "jobSearch.action";
        }
        

/*
 * Js method for job post edit
 * Date : 03/06/2015
 * Author : santosh Kola
 */


function editJobPost(jobId){
    hideRow('addTr');
    hideRow('editTr');
     document.getElementById('resultMessage').innerHTML ='';
    
    
    /* $.ajaxFileUpload({
        url:'editJobposting.action?jobId='+jobId,//
        secureuri:false,//false
      fileElementId:'file',//id  <input type="file" id="file" name="file" />
        dataType: 'json',// json
        success: function(data,status){
            alert(data);
//var json = $.parseJSON(data);
//alert(json.JobTitle);
         //  var displaymessage = "<font color=red>Please try again later</font>";
            //  alert("success-->11111-->"+data);
            // var json = $.parseJSON(data);
           // if(data.indexOf("uploaded")>0){ 
                // alert("uploaded");
                //alert(data);
                //displaymessage = data;
            //}
         
           
            //alert(data.indexOf("uploaded"));
           
          //  document.getElementById("load").style.display = 'none';
           // document.getElementById('resultMessage').innerHTML = displaymessage;//"<font color=green>File uploaded successfully</font>";
        // document.getElementById('fileType').value = "";
        },
        error: function(e){
            alert("error-->"+e);
            // alert('Error:111 ' + e);
         //  / document.getElementById("load").style.display = 'none';
           // document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
        // document.getElementById('fileType').value = "";
        //  document.getElementById('profile').value="";
                
        }
    });
    
    
    
    
    */
    document.getElementById("load").style.display = 'block';
   $.ajax({
            url:'editJobposting.action?jobId='+jobId,//
            context: document.body,
            success: function(responseText) {
               // alert(responseText);
                var json = $.parseJSON(responseText);
                
                var jobtitle = json["JobTitle"];
                var jobtagline = json["JobTagline"];
                var jobposition = json["JobPosition"];
                var jobqulification = json["JobQualification"];
                var jobshifits = json["JobShifts"];
                var location = json["Location"];
                var jobcountry = json["JobCountry"];
                var jobstatus = json["JobStatus"];
                var jobdescription = unescape(json["JobDescription"]);
                var jobtechnical = unescape(json["JobTechnical"]);
                var jobshiftskills = unescape(json["JobSoftSkills"]);
              
                var jobCreatedDate = json["JobCreatedDate"];
                var createdBy = json["CreatedBy"];
                var jobId = json["JobId"];
                var id = json["Id"];
                
                 document.getElementById("jobtitle").value = jobtitle;
            document.getElementById("jobtagline").value = jobtagline;
            document.getElementById("jobposition").value = jobposition;
            document.getElementById("jobqulification").value = jobqulification;
            document.getElementById("jobshifits").value = jobshifits;
            document.getElementById("location").value = location;
             document.getElementById("jobcountry").value = jobcountry;
            document.getElementById("jobstatus").value = jobstatus;
            document.getElementById("jobdescription").value = jobdescription;
            document.getElementById("jobtechnical").value = jobtechnical;
            document.getElementById("jobshiftskills").value = jobshiftskills;
             document.getElementById('createdBy').innerHTML = createdBy;
             document.getElementById('createdDate').innerHTML = jobCreatedDate;
             document.getElementById("overlayJobId").value = jobId;
             document.getElementById("id").value =id;
             showRow('editTr');
                        //$("#response-div").html(responseText);
               // $("#response-div").find("script").each(function(i) {
                  //  eval($(this).text());
                //});
                 document.getElementById("load").style.display = 'none';
            }, error: function(e){
                document.getElementById("load").style.display = 'none';
                alert("error-->"+e);
            }
        });
    
    
    
   document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Edit JobPosting";
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
  
  
  
  function doUpdateJobPost(){
       //alert("in file uploading ------");
    /* var objType = document.getElementById("fileType").value;
    
   
     if(objType=="-1"){
         document.getElementById('resultMessage').innerHTML = "<font color=red>Please select file type!!</font>"
        return false;
     }
    */    hideRow('addTr');
    //hideRow('editTr');
   
            var jobtitle= document.getElementById("jobtitle").value;
                 //alert(jobtitle);
                var jobtagline= document.getElementById("jobtagline").value;
                //alert(jobtagline);
            var jobposition= document.getElementById("jobposition").value ;
           // alert(jobposition);
             var jobqulification=document.getElementById("jobqulification").value;
           //  alert(jobqulification);
             var jobshifits=document.getElementById("jobshifits").value;
            // alert(jobshifits);
             var location=document.getElementById("location").value;
            // alert(location);
              var jobcountry=document.getElementById("jobcountry").value;
            //  alert(jobcountry);
             var jobstatus=document.getElementById("jobstatus").value;
            // alert(jobstatus);
             var jobdescription=escape(document.getElementById("jobdescription").value);
            // alert(jobdescription);
             var jobtechnical=escape(document.getElementById("jobtechnical").value);
            // alert(jobtechnical);
             var jobshiftskills=escape(document.getElementById("jobshiftskills").value);
           //  alert(jobshiftskills);
               // var file=document.getElementById("file").value;
                
                 var jobId=document.getElementById("overlayJobId").value;
                             //alert(file);
                             
                             //alert(escape(jobdescription));
   if(jobtitle.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter jobtitle.</font>";
   }else if(jobtagline.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter jobtagline.</font>";
   }else if(jobposition.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  jobposition.</font>";
   }else if(jobqulification.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  jobqulification.</font>";
   }
   else if(jobshifits.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter jobshifits.</font>";
   }else if(location.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  location.</font>";
   }
   else if(jobdescription.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  jobdescription.</font>";
   }else if(jobtechnical.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter jobtechnical.</font>";
   }
   else if(jobshiftskills.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter  jobshiftskills.</font>";
   }
   
        //setTimeout(disableFunction,1);
   
   else
       {
  // alert(jobtechnical);
    document.getElementById("load").style.display = 'block';
   /* $.ajaxFileUpload({
        url:'updateJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills+"&attachmentLocation="+file+"&jobId="+jobId,//
        secureuri:false,//false
        fileElementId:'file',//id  <input type="file" id="file" name="file" />
        dataType: 'json',// json
        */
          $.ajax({
        //  url:'updateJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+'&location='+location+'&jobcountry='+jobcountry+'&jobstatus='+jobstatus+'&jobdescription='+jobdescription+'&jobtechnical='+jobtechnical+'&jobshiftskills='+jobshiftskills+'&attachmentLocation='+file+'&jobId='+jobId,//
            url:'updateJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+'&location='+location+'&jobcountry='+jobcountry+'&jobstatus='+jobstatus+'&jobdescription='+jobdescription+'&jobtechnical='+jobtechnical+'&jobshiftskills='+jobshiftskills+'&jobId='+jobId,//
         // alert(url);
            context: document.body,
            success: function(responseText) {
      
            
         //  var displaymessage = "<font color=red>Please try again later</font>";
            //  alert("success-->11111-->"+data);
            // var json = $.parseJSON(data);
           // if(data.indexOf("uploaded")>0){ 
                // alert("uploaded");
                //alert(data);
               var displaymessage = responseText;
            //}
          /*  if(data.indexOf("Notvalid")>0){
                // alert("not valid");
                displaymessage = "<font color=red>Not a valid file!,Please check the file and try again.</font>";
            }
            if(data.indexOf("Error")>0){
                // alert("Erro");
                displaymessage = "<font color=red>Internal Error!, Please try again later.</font>"
            }*/
           
            //alert(data.indexOf("uploaded"));
           
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
  
  
  
  

        
              function addJobPost(){
              //    alert("hi");
                   document.getElementById('resultMessage').innerHTML ='';
               hideRow('addTr');
    hideRow('editTr');
     hideRow("createdTr");
            // hideRow('downloadTr');
            // showRow('uploadTr');
            //document.getElementById("downloadTr").style.display = 'none';
            //document.getElementById("uploadTr").style.display = '';
            document.getElementById("jobtitle").value = '';
            document.getElementById("jobtagline").value = '';
            document.getElementById("jobposition").value = '';
            document.getElementById("jobqulification").value = '';
            document.getElementById("jobshifits").value = '';
            document.getElementById("location").value = '';
             document.getElementById("jobcountry").value = '';
            document.getElementById("jobstatus").value = '';
            document.getElementById("jobdescription").value = '';
            document.getElementById("jobtechnical").value = '';
            document.getElementById("jobshiftskills").value = '';
            //document.getElementById("overlayjobposition").value = '';
            
           // document.getElementById("overlayReviewDate").value = '';
           // document.getElementById("overlayDescription").value = '';
            document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Add JobPosting";
            showRow('addTr');
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
        
        
        
/*Method for displaying applicants detsils
 * Date : 03/17/2015
 * Author : santosh Kola
 */





  function toggleApplicantOverlay(){
                 
            document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Add JobPosting";
           
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
        
        
        

function getApplicantDetails(applicantId){
 
    document.getElementById("load").style.display = 'block';
   $.ajax({
            url:'getApplicantDetails.action?applicantId='+applicantId,//
            context: document.body,
            success: function(responseText) {
               // alert(responseText);
                var json = $.parseJSON(responseText);
                
                
                
                var Id = json["Id"];
                var JobId = json["JobId"];
                var FirstName = json["FirstName"];
                var LastName = json["LastName"];
                var MiddleName = json["MiddleName"];
                var PhoneNumber = json["PhoneNumber"];
                var EmailId = json["EmailId"];
                var NativePlace = json["NativePlace"];
               
                var CurrentLocation = json["CurrentLocation"];
                var NoticePeriod = json["NoticePeriod"];
                var ResumePath = json["ResumePath"];
                var ResumeName = json["ResumeName"];
                var AppliedDate = json["AppliedDate"];
                var NativeDistrict = json["NativeDistrict"];
                var NativeState = json["NativeState"];
                var NativeZipcode = json["NativeZipcode"];
                var CurentDistrict = json["CurentDistrict"];
                var CurrentState = json["CurrentState"];
                var CurrentZipcode = json["CurrentZipcode"];
              
                
                 document.getElementById("firstName").value = FirstName;
            document.getElementById("middleName").value = MiddleName;
            document.getElementById("lastName").value = LastName;
            document.getElementById("phone").value = PhoneNumber;
            document.getElementById("email").value = EmailId;
            document.getElementById("nativeCity").value = NativeDistrict;
             document.getElementById("nativeState").value = NativeState;
            document.getElementById("nativeZip").value = NativeZipcode;
            document.getElementById("currentCity").value = CurentDistrict;
            document.getElementById("currentState").value = CurrentState;
            document.getElementById("currentZip").value = CurrentZipcode;
            // alert("ResumeName-->"+ResumeName);
            // alert("ResumePath-->"+ResumePath);
            
            
             if(ResumeName.trim() != ''){
                 var resumePath = escape(ResumePath);
                 //alert(resumePath);
                 document.getElementById("resumeLink").innerHTML = '<a href=resumeDownload.action?path='+resumePath+'  class=navigationText>'+ResumeName+'</a>';
             }
         
           
                        //$("#response-div").html(responseText);
               // $("#response-div").find("script").each(function(i) {
                  //  eval($(this).text());
                //});
                 document.getElementById("load").style.display = 'none';
            }, error: function(e){
                document.getElementById("load").style.display = 'none';
                alert("error-->"+e);
            }
        });
    
    
    
   document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Consultant Details";
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
