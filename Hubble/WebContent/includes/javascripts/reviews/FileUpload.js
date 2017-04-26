

function putFileName()
{
	       document.getElementById('resultMessage').innerHTML = '';
    var fullPath = document.getElementById('file').value;
        
   // alert(fullPath);
            
            var size = document.getElementById('file').files[0].size;
                 
             if(fullPath.length>50){
                  document.getElementById('file').value = '';
                    document.getElementById('resultMessage').innerHTML = "<font color=red>File name length must be less than 50 characters!</font>"
               // showAlertModal("File size must be less than 2 MB");
                return (false);
             }else {
            if(parseInt(size)<2097152) {
                
                  
            }else {
                document.getElementById('file').value = '';
                    document.getElementById('resultMessage').innerHTML = "<font color=red>File size must be less than 2 MB.</font>"
               // showAlertModal("File size must be less than 2 MB");
                return (false);
            }
}
}

/** file validations **/

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

function ajaxFileUpload()
{
  
   var overlayReviewType = document.getElementById('overlayReviewType').value;
   var overlayReviewName = document.getElementById('overlayReviewName').value;
   var overlayReviewDate = document.getElementById('overlayReviewDate').value;
    var overlayDescription = document.getElementById('overlayDescription').value;
 
   var reviewStatusOverlay = document.getElementById('reviewStatusOverlay').value;
   var reviewOnsiteMgrStatusOverlay = "";
    var reviewHrStatusOverlay = "";
    
   var leadRating = document.getElementById('leadRating').value;
   var hrRating = document.getElementById('hrRating').value;
   var onsiteMgrRating = "";
   var maxPoints = document.getElementById('maxPoints').value;
   var addType = document.getElementById('addType').value;
   var userId= "";
   var tlComments ="";
   var hrComments ="";
   var onsiteMgrComments ="";
   var overlayReviewLogAdded =0;
   var overlayReviewBilling =0;
  if(overlayReviewType=='55'){
       overlayReviewLogAdded = document.getElementById('overlayReviewLogAdded').value;
       overlayReviewBilling = document.getElementById('overlayReviewBilling').value;
        if(overlayReviewBilling.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please Enter Billing amount.</font>";
       return false
        }
   //    alert(overlayReviewBilling);
       if(parseInt(overlayReviewBilling)<=0){
           document.getElementById('resultMessage').innerHTML = "<font color=red>Billing amount must be greater than zero..</font>";
           return false;
       }
   }

  var isManager=0;
   var livingCountry='';
   if(addType=='Team'){
       //alert("team");
       userId = document.getElementById('userId').value;
       tlComments = document.getElementById('tlComments').value;
       hrComments = document.getElementById('hrComments').value;
       onsiteMgrComments = document.getElementById('onsiteMgrComments').value;
        reviewOnsiteMgrStatusOverlay = document.getElementById('reviewOnsiteMgrStatusOverlay').value;
       onsiteMgrRating = document.getElementById('onsiteMgrRating').value;
       reviewHrStatusOverlay = document.getElementById('reviewHrStatusOverlay').value;
       var roleName =document.getElementById('roleName').value;
      // alert(roleName);
         isManager = document.getElementById("isManager").value;
         livingCountry = document.getElementById("livingCountry").value;
       
      
   }
  // alert(parseInt(maxPoints,10)+" "+parseInt(leadRating,10));
  // if(addType=='Team' && userId=="-1"){
       // document.getElementById('resultMessage').innerHTML = "<font color=red>Please select employee name.</font>";
   //}else {
   if(overlayReviewType.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please select review type.</font>";
   }else if(overlayReviewDate.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please select review date.</font>";
   }else if(overlayReviewName.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter review title.</font>";
   }else if(overlayDescription.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter review description.</font>";
   }else if(addType=='Team' && userId=="-1"){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please select employee name.</font>";
   }
   else if(addType=='Team' && roleName=="Employee" && reviewStatusOverlay=='Opened'){
         
        document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Lead state must be Approved or Denied.</font>";
       
    }
   else if(addType=='Team' && roleName=="Employee" && reviewOnsiteMgrStatusOverlay=='Opened' && isManager=='1' && livingCountry=='USA'){
          document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Onsite Manager state must be Approved or Denied.</font>";
           
     }
    else if(addType=='Team' && roleName=="Operations" && reviewHrStatusOverlay=='Opened' ){
        
               document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Hr state must be Approved or Denied.</font>";
             
           
       }
   else if(addType=='Team' && roleName=="Operations" && reviewHrStatusOverlay=='Approved' && parseInt(hrRating,10)==0){
        
               document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Hr rating should not be Zero.</font>";
              
           
       }
        else if(addType=='Team' && roleName=="Employee" && reviewStatusOverlay=='Approved' && parseInt(leadRating,10)==0){
        
               document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Lead rating should not be Zero.</font>";
              
           
       }
     else   if(addType=='Team' && roleName=="Employee" && reviewOnsiteMgrStatusOverlay=='Approved' && isManager=='1' && livingCountry=='USA' && parseInt(onsiteMgrRating,10)==0){
          document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Onsite Manager rating should not be Zero.</font>";
     }
   else if(parseInt(onsiteMgrRating,10)>parseInt(maxPoints,10)){
          document.getElementById("onsiteMgrRating").value = maxPoints;
        document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Onsite Manager rating must be less than or equal max points.</font>";
       
     }
    //&&(hrRating<=maxPoints)
    
   else if(parseInt(leadRating,10)>parseInt(maxPoints,10)){
        document.getElementById("leadRating").value = maxPoints;
        document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Lead rating must be less than or equal max points.</font>";
    
         }   
       else  if(parseInt(hrRating,10)>parseInt(maxPoints,10)){
             
                document.getElementById("hrRating").value = maxPoints;
        document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Hr rating must be less than or equal max points.</font>";
      
       
             
         }
   else {
        overlayReviewName = escape(overlayReviewName);
       overlayDescription = escape(overlayDescription);
       tlComments = escape(tlComments);
       hrComments = escape(hrComments);
       onsiteMgrComments = escape(onsiteMgrComments);
     setTimeout(disableFunction,1);
    document.getElementById("load").style.display = 'block';
    $.ajaxFileUpload({
        url:'addReview.action?overlayReviewType='+overlayReviewType+'&overlayReviewName='+overlayReviewName+'&overlayReviewDate='+overlayReviewDate+'&overlayDescription='+overlayDescription+'&reviewStatusOverlay='+reviewStatusOverlay+'&reviewOnsiteMgrStatusOverlay='+reviewOnsiteMgrStatusOverlay+'&reviewHrStatusOverlay='+reviewHrStatusOverlay+"&leadRating="+leadRating+"&hrRating="+hrRating+"&onsiteMgrRating="+onsiteMgrRating+"&maxPoints="+maxPoints+"&userId="+userId+"&addType="+addType+"&tlComments="+tlComments+"&hrComments="+hrComments+"&onsiteMgrComments="+onsiteMgrComments+"&overlayReviewLogAdded="+overlayReviewLogAdded+"&overlayReviewBilling="+overlayReviewBilling,//
        secureuri:false,//false
        fileElementId:'file',//id  <input type="file" id="file" name="file" />
        dataType: 'json',// json
        success: function(data,status){
            
            var displaymessage = "<font color=red>Please try again later</font>";
          
            if(data.indexOf("uploaded")>0){
               
                displaymessage = "<font color=green>Review added Successfully.</font>";
            }
            if(data.indexOf("Notvalid")>0){
                
                displaymessage = "<font color=red>Not a valid file!,Please check the file and try again.</font>";
            }
            if(data.indexOf("Error")>0){
               
                displaymessage = "<font color=red>Internal Error!, Please try again later.</font>"
            }
           
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = displaymessage;//"<font color=green>File uploaded successfully</font>";
        
        },
        error: function(e){
            
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
       
        }
    });
    
   }
   //}	
    return false;

}
/*
 function ajaxFileUpload()
{
   
    var overlayReviewType = document.getElementById('overlayReviewType').value;
    var overlayReviewName = document.getElementById('overlayReviewName').value;
    var overlayReviewDate = document.getElementById('overlayReviewDate').value;
    var overlayDescription = document.getElementById('overlayDescription').value;
  
    var reviewStatusOverlay = document.getElementById('reviewStatusOverlay').value;
    var reviewOnsiteMgrStatusOverlay = "";
    var reviewHrStatusOverlay = "";
    
   var leadRating = document.getElementById('leadRating').value;
   var hrRating = document.getElementById('hrRating').value;
   var onsiteMgrRating = "";
   var maxPoints = document.getElementById('maxPoints').value;
   var addType = document.getElementById('addType').value;
   var userId= "";
   var tlComments ="";
   var hrComments ="";
   var onsiteMgrComments ="";
   var overlayReviewLogAdded =0;
   var overlayReviewBilling =0;
  if(overlayReviewType=='55'){
       overlayReviewLogAdded = document.getElementById('overlayReviewLogAdded').value;
       overlayReviewBilling = document.getElementById('overlayReviewBilling').value;
        if(overlayReviewBilling.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please Enter Billing amount.</font>";
       return false
        }
   //    alert(overlayReviewBilling);
       if(parseInt(overlayReviewBilling)<=0){
           document.getElementById('resultMessage').innerHTML = "<font color=red>Billing amount must be greater than zero..</font>";
           return false;
       }
   }

  var isManager=0;
   var livingCountry='';
   if(addType=='Team'){
       //alert("team");
       userId = document.getElementById('userId').value;
       tlComments = document.getElementById('tlComments').value;
       hrComments = document.getElementById('hrComments').value;
       onsiteMgrComments = document.getElementById('onsiteMgrComments').value;
        reviewOnsiteMgrStatusOverlay = document.getElementById('reviewOnsiteMgrStatusOverlay').value;
       onsiteMgrRating = document.getElementById('onsiteMgrRating').value;
       reviewHrStatusOverlay = document.getElementById('reviewHrStatusOverlay').value;
       var roleName =document.getElementById('roleName').value;
      // alert(roleName);
         isManager = document.getElementById("isManager").value;
         livingCountry = document.getElementById("livingCountry").value;
       
      
   }
  // alert(parseInt(maxPoints,10)+" "+parseInt(leadRating,10));
  // if(addType=='Team' && userId=="-1"){
       // document.getElementById('resultMessage').innerHTML = "<font color=red>Please select employee name.</font>";
   //}else {
   if(overlayReviewType.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please select review type.</font>";
   }else if(overlayReviewDate.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please select review date.</font>";
   }else if(overlayReviewName.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter review title.</font>";
   }else if(overlayDescription.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter review description.</font>";
   }else if(addType=='Team' && userId=="-1"){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please select employee name.</font>";
   }
   else if(addType=='Team' && roleName=="Employee" && reviewStatusOverlay=='Opened'){
         
        document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Lead state must be Approved or Denied.</font>";
       
    }
   else if(addType=='Team' && roleName=="Employee" && reviewOnsiteMgrStatusOverlay=='Opened' && isManager=='1' && livingCountry=='USA'){
          document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Onsite Manager state must be Approved or Denied.</font>";
           
     }
    else if(addType=='Team' && roleName=="Operations" && reviewHrStatusOverlay=='Opened' ){
        
               document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Hr state must be Approved or Denied.</font>";
             
           
       }
   else if(addType=='Team' && roleName=="Operations" && reviewHrStatusOverlay=='Approved' && parseInt(hrRating,10)==0){
        
               document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Hr rating should not be Zero.</font>";
              
           
       }
        else if(addType=='Team' && roleName=="Employee" && reviewStatusOverlay=='Approved' && parseInt(leadRating,10)==0){
        
               document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Lead rating should not be Zero.</font>";
              
           
       }
     else   if(addType=='Team' && roleName=="Employee" && reviewOnsiteMgrStatusOverlay=='Approved' && isManager=='1' && livingCountry=='USA' && parseInt(onsiteMgrRating,10)==0){
          document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Onsite Manager rating should not be Zero.</font>";
     }
   else if(parseInt(onsiteMgrRating,10)>parseInt(maxPoints,10)){
          document.getElementById("onsiteMgrRating").value = maxPoints;
        document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Onsite Manager rating must be less than or equal max points.</font>";
       
     }
    //&&(hrRating<=maxPoints)
    
   else if(parseInt(leadRating,10)>parseInt(maxPoints,10)){
        document.getElementById("leadRating").value = maxPoints;
        document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Lead rating must be less than or equal max points.</font>";
    
         }   
       else  if(parseInt(hrRating,10)>parseInt(maxPoints,10)){
             
                document.getElementById("hrRating").value = maxPoints;
        document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Hr rating must be less than or equal max points.</font>";
      
       
             
         }
   else {
        overlayReviewName = escape(overlayReviewName);
       overlayDescription = escape(overlayDescription);
       tlComments = escape(tlComments);
       hrComments = escape(hrComments);
       onsiteMgrComments = escape(onsiteMgrComments);
     setTimeout(disableFunction,1);
    document.getElementById("load").style.display = 'block';  
    
    var file =document.getElementById('file');
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
       var url='addReview.action?overlayReviewType='+overlayReviewType+'&overlayReviewName='+overlayReviewName+'&overlayReviewDate='+overlayReviewDate+'&overlayDescription='+overlayDescription+'&reviewStatusOverlay='+reviewStatusOverlay+'&reviewOnsiteMgrStatusOverlay='+reviewOnsiteMgrStatusOverlay+'&reviewHrStatusOverlay='+reviewHrStatusOverlay+"&leadRating="+leadRating+"&hrRating="+hrRating+"&onsiteMgrRating="+onsiteMgrRating+"&maxPoints="+maxPoints+"&userId="+userId+"&addType="+addType+"&tlComments="+tlComments+"&hrComments="+hrComments+"&onsiteMgrComments="+onsiteMgrComments+"&overlayReviewLogAdded="+overlayReviewLogAdded+"&overlayReviewBilling="+overlayReviewBilling;//
       // var url="timsheetAttachmentUpload.action?empId="+empId+"&timeSheetID="+timeSheetID;
        //'timsheetAttachmentUpload.action?empId='+empId+"&timeSheetID="+timeSheetID,
        xmlHttpRequest.open("POST", url, true);
	    
        xmlHttpRequest.send(data);
  
   }
   
    return false;

}

*/

function toggleReviewEditOverlay(reviewId,reviewType,reviewName,dateOfReview,Comments,attachmentName){
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');
    hideRow('uploadTr');
    showRow('downloadTr');
    document.getElementById("headerLabel").innerHTML="Edit Review";
    //   document.getElementById("uploadTr").style.display = 'none';
    //  document.getElementById("downloadTr").style.display = 'table-row';
    document.getElementById("reviewId").value = reviewId;
    document.getElementById("overlayReviewType").value = reviewType;
    document.getElementById("overlayReviewName").value = reviewName;
    document.getElementById("overlayReviewDate").value = dateOfReview;
    document.getElementById("overlayDescription").value = Comments;
    // document.getElementById("addButton").style.display = 'none';
    //  document.getElementById("updateButton").style.display = 'block';
      
         document.getElementById("downloadFile").innerHTML = attachmentName; 
    
    
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}

//javascript:toggleTeamReviewEditOverlay
/*
function toggleTeamReviewEditOverlay(reviewId,reviewType,reviewName,dateOfReview,Comments,attachmentName,createdDate,reviewStatusOverlay,tlComments,tlRating,hrRating,maxPoints,hrComments,userId,reviewTypeName){
  
document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Edit Review";
var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');
    hideRow('uploadTr');
    showRow('downloadTr');
        
    var roleName = document.getElementById("roleName").value;
    // alert(roleName);
    if(roleName=='Employee'){
        //showRow('tlcommentsTr');
     //   hideRow('hrcommentsTr');
        document.getElementById("leadRating").readOnly = false;
        document.getElementById("hrRating").readOnly = true;
        document.getElementById("hrComments").readOnly = true;
        document.getElementById("tlComments").readOnly = false;
    }
    if(roleName=='Operations'){
     //   showRow('hrcommentsTr');
     //   hideRow('tlcommentsTr');
        document.getElementById("hrRating").readOnly = false;
        document.getElementById("leadRating").readOnly = true;
        document.getElementById("tlComments").readOnly = true;
        document.getElementById("hrComments").readOnly = false;
    }
    //   document.getElementById("uploadTr").style.display = 'none';
    //  document.getElementById("downloadTr").style.display = 'table-row';
    document.getElementById("reviewId").value = reviewId;
    document.getElementById("overlayReviewType").value = reviewType;
    document.getElementById("overlayReviewName").value = reviewName;
  document.getElementById("reviewStatusOverlay").value = reviewStatusOverlay;
   document.getElementById("maxPoints").value = maxPoints;
  document.getElementById("leadRating").value = tlRating;
    document.getElementById("hrRating").value = hrRating;
    document.getElementById("hrpointsInfo").innerHTML = "<font color='green' size='2px'>(Max&nbsp;Points:"+maxPoints+")";
    document.getElementById("leadpointsInfo").innerHTML = "<font color='green' size='2px'>(Max&nbsp;Points:"+maxPoints+")";
    document.getElementById("userId").value = userId;
  //reviewName
  document.getElementById("overlayReviewType").style.display='none';
  //alert(reviewName);
  document.getElementById("reviewTypeValue").innerHTML = "<font color='green' size='2px'>"+reviewTypeName+"</font>"
  
  
      var reviewedDate = dateOfReview.split(" ")[0];
    var reviewDate = reviewedDate.split("-");
    var reviewedDateConverted = reviewDate[1]+"/"+reviewDate[2]+"/"+reviewDate[0];
     var rvcreatedDate = createdDate.split(" ")[0];
    var rvCrDate = rvcreatedDate.split("-");
    var reviewedCreatedDateConverted = rvCrDate[1]+"/"+rvCrDate[2]+"/"+rvCrDate[0];
      //document.getElementById("overlayReviewCreatedDate").value = reviewedCreatedDateConverted;
      document.getElementById("overlayReviewDate").value = reviewedDateConverted;
    document.getElementById("overlayDescription").value = Comments;
    // document.getElementById("addButton").style.display = 'none';
    //  document.getElementById("updateButton").style.display = 'block';
    
  document.getElementById("tlComments").value=tlComments;
  document.getElementById("hrComments").value=hrComments;
        
   // document.getElementById("downloadFile").innerHTML = attachmentName;
    
       if(attachmentName.trim().length>0)  {
         // alert("hiii");
          document.getElementById("downloadLink").style.display = 'block';
          
         document.getElementById("downloadFile").innerHTML = attachmentName; 
      }else {
          //alert("none");
          document.getElementById("downloadLink").style.display = 'none';
          //document.getElementById("downloadFile").innerHTML ='';
      }
    
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}*/
function downloadReviewFile(){
    window.location = "download.action?reviewId="+document.getElementById("reviewId").value;
}

//upadtereview
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
function upadtereview(type){
    //alert("id-->"+id+"----------- empname"+empName);
    var reviewId=  document.getElementById("reviewId").value;
   // var overlayReviewType=     document.getElementById("overlayReviewType").value ;
    var overlayReviewName=     document.getElementById("overlayReviewName").value ;
    var overlayReviewDate=     document.getElementById("overlayReviewDate").value ;
    var overlayDescription=     document.getElementById("overlayDescription").value ;
  //  var overlayReviewCreatedDate=     document.getElementById("overlayReviewCreatedDate").value ;
    var reviewStatusOverlay=   document.getElementById("reviewStatusOverlay").value ;
    var reviewOnsiteMgrStatusOverlay=   document.getElementById("reviewOnsiteMgrStatusOverlay").value ;
    var reviewHrStatusOverlay=   document.getElementById("reviewHrStatusOverlay").value ;
    
    var leadRating = document.getElementById("leadRating").value ;
    var hrRating = document.getElementById("hrRating").value ;
    var onsiteMgrRating = document.getElementById("onsiteMgrRating").value ;
    
     var maxPoints = document.getElementById("maxPoints").value ;
     var overlayReviewLogAdded =0;
   var overlayReviewBilling =0;
  //var overlayReviewType=document.getElementById('overlayReviewType').value;
  //alert(overlayReviewType);
var tempoverlayReviewType=document.getElementById("tempoverlayReviewType").value;
  //alert("overlayReviewType-->"+tempoverlayReviewType);
   if(tempoverlayReviewType=='55'){
       overlayReviewLogAdded = document.getElementById('overlayReviewLogAdded').value;
       overlayReviewBilling = document.getElementById('overlayReviewBilling').value;
       if(overlayReviewBilling.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please Enter Billing amount.</font>";
       return false
        }
       if(parseInt(overlayReviewBilling)<=0){
           document.getElementById('resultMessage').innerHTML = "<font color=red>Billing amount must be greater than zero..</font>";
           return false;
       }
       
   }

  //  alert(maxPoints);
    var tlComments = "";
    var hrComments = "";
    var onsiteMgrComments = "";
  //  alert(type)
    var isManager=0;
   var livingCountry='';
    var roleName =document.getElementById('roleName').value;
    if(type=='1'){
        
        tlComments =document.getElementById("tlComments").value;
        hrComments = document.getElementById("hrComments").value;
        onsiteMgrComments = document.getElementById("onsiteMgrComments").value;
        isManager = document.getElementById("isManager").value;
         livingCountry = document.getElementById("livingCountry").value;
        
    }
    //=======
     if(type=='1' && roleName=="Employee" && reviewStatusOverlay=='Opened'){
         
        document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Lead state must be Approved or Denied.</font>";
        return false;
    }
    if(type=='1' && roleName=="Employee" && reviewOnsiteMgrStatusOverlay=='Opened' && isManager=='1' && livingCountry=='USA'){
          document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Onsite Manager state must be Approved or Denied.</font>";
            return false;
     }
     if(type=='1' && roleName=="Operations" && reviewHrStatusOverlay=='Opened' ){
        
               document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Hr state must be Approved or Denied.</font>";
              return false;
           
       }
    if(type=='1' && roleName=="Operations" && reviewHrStatusOverlay=='Approved' && parseInt(hrRating,10)==0){
        
               document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Hr rating should not be Zero.</font>";
              return false;
           
       }
         if(type=='1' && roleName=="Employee" && reviewStatusOverlay=='Approved' && parseInt(leadRating,10)==0){
        
               document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Lead rating should not be Zero.</font>";
                return false;
           
       }
        if(type=='1' && roleName=="Employee" && reviewOnsiteMgrStatusOverlay=='Approved' && isManager=='1' && livingCountry=='USA' && parseInt(onsiteMgrRating,10)==0){
          document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Onsite Manager rating should not be Zero.</font>";
            return false;
     }
   // ====
    if(type=='1' && isManager=='1' && livingCountry=='USA' && roleName=="Employee" && reviewOnsiteMgrStatusOverlay=='Approved' && parseInt(onsiteMgrRating,10)>parseInt(maxPoints,10)){
          document.getElementById("onsiteMgrRating").value = maxPoints;
        document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Onsite Manager rating must be less than or equal max points.</font>";
        return false;
     }
     //alert(parseInt(hrRating,10));
    //&&(hrRating<=maxPoints)
    if(type=='1' && roleName=="Employee" && reviewStatusOverlay=='Approved' &&  parseInt(leadRating,10)>parseInt(maxPoints,10)){
         document.getElementById("leadRating").value = maxPoints;
        document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Lead rating must be less than or equal max points.</font>";
        return false;
    }
         if(type=='1' && roleName=="Operations" && reviewHrStatusOverlay=='Approved' && parseInt(hrRating,10)>parseInt(maxPoints,10)){
              document.getElementById("hrRating").value = maxPoints;
        document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Hr rating must be less than or equal max points.</font>";
         return false;
         }
         
         overlayReviewName = escape(overlayReviewName);
       overlayDescription = escape(overlayDescription);
       tlComments = escape(tlComments);
       hrComments = escape(hrComments);
       onsiteMgrComments = escape(onsiteMgrComments);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,ppulateReviewUpadteResult); 

    var url=CONTENXT_PATH+"/updateReview.action?reviewId="+reviewId+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay+"&tlComments="+tlComments+"&leadRating="+leadRating+"&hrRating="+hrRating+"&hrComments="+hrComments+"&reviewOnsiteMgrStatusOverlay="+reviewOnsiteMgrStatusOverlay+"&reviewHrStatusOverlay="+reviewHrStatusOverlay+"&onsiteMgrRating="+onsiteMgrRating+"&onsiteMgrComments="+onsiteMgrComments+"&overlayReviewLogAdded="+overlayReviewLogAdded+"&overlayReviewBilling="+overlayReviewBilling;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
         
        

}


function ppulateReviewUpadteResult(resText){
     document.getElementById('resultMessage').innerHTML = "<font color=green size=2px>Updated Successfully</font>";
  //  alert("Updated Successfully");
    toggleOverlay();
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
    
    /*
function toggleEditOverlay(reviewId,reviewType,reviewName,dateOfReview,Comments,attachmentName,createdDate,reviewStatusOverlay,tlRating,hrRating,maxPoints,hrComments,tlComments,ApprovedBy1,Approver1Date,Approver2Date,ApprovedBy2,reviewTypeName){
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');
    hideRow('uploadTr');
    showRow('downloadTr');
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Edit Review";
    var roleName = document.getElementById("roleName").value;
    document.getElementById("reviewId").value = reviewId;
    document.getElementById("overlayReviewType").value = reviewType;
    document.getElementById("overlayReviewName").value = reviewName;
    if(tlComments.trim().length>0){
        document.getElementById("tlComments").value = tlComments;
        
    }else {
        hideRow('tlcommentsTr');
    }if(hrComments.trim().length>0){
        document.getElementById("hrComments").value = hrComments;
        
    }else {
        hideRow('hrcommentsTr');
    }
    document.getElementById("leadRating").value = tlRating;
    document.getElementById("hrRating").value = hrRating;
    //reviewName
  document.getElementById("overlayReviewType").style.display='none';
  //alert(reviewName);
  document.getElementById("reviewTypeValue").innerHTML = "<font color='green' size='2px'>"+reviewTypeName+"</font>"
  
    
    
   // alert(ApprovedBy1.trim().length);
    if(ApprovedBy1.trim().length>0){
        document.getElementById("tlId").innerHTML=ApprovedBy1;
        document.getElementById("ApprovedBy1Date").innerHTML=Approver1Date.split(" ")[0];
        //document.getElementById("tlApprovedDetails").style.display = 'block';
        //showRow('tlApprovedDetails');
    }
     if(ApprovedBy2.trim().length>0){
        document.getElementById("hrId").innerHTML=ApprovedBy2;
        document.getElementById("ApprovedBy2Date").innerHTML=Approver2Date.split(" ")[0];
        //document.getElementById("hrApprovedDetails").style.display = 'block';
       // showRow('hrApprovedDetails');
    }
    
    
    document.getElementById("hrpointsInfo").innerHTML = "<font color='green' size='2px'>(Max&nbsp;Points:"+maxPoints+")";
    document.getElementById("leadpointsInfo").innerHTML = "<font color='green' size='2px'>(Max&nbsp;Points:"+maxPoints+")";
    
    if(reviewStatusOverlay=='Opened'){
         document.getElementById("reviewStatusOverlay").style.display = 'block';
    }else {
         document.getElementById("reviewStatusOverlay").style.display = 'none';
        document.getElementById("stateLabel").innerHTML = "<font color='green' size='2px'>"+reviewStatusOverlay+"</font>";
    }
        
    
    var reviewedDate = dateOfReview.split(" ")[0];
    var reviewDate = reviewedDate.split("-");
    var reviewedDateConverted = reviewDate[1]+"/"+reviewDate[2]+"/"+reviewDate[0];
    var rvcreatedDate = createdDate.split(" ")[0];
    var rvCrDate = rvcreatedDate.split("-");
    var reviewedCreatedDateConverted = rvCrDate[1]+"/"+rvCrDate[2]+"/"+rvCrDate[0];
    document.getElementById("overlayReviewDate").value = reviewedDateConverted;
    document.getElementById("overlayDescription").value = Comments;
   // document.getElementById("overlayReviewCreatedDate").value = reviewedCreatedDateConverted;
    document.getElementById("reviewStatusOverlay").value = reviewStatusOverlay;


    if(reviewStatusOverlay=="Approved" || reviewStatusOverlay=="Denied"){
        document.getElementById("update").style.display="none";
         document.getElementById("delete").style.display="none";
      
   }
   else{
        document.getElementById("update").style.display="block";
         document.getElementById("delete").style.display="block";
        // hideRow("approvedByComments2");
        //    hideRow("approvedByComments1");
   }
    if(attachmentName.trim().length>0)  {
         // alert("hiii");
          document.getElementById("downloadLink").style.display = 'block';
          
         document.getElementById("downloadFile").innerHTML = attachmentName; 
      }else {
          //alert("none");
          document.getElementById("downloadLink").style.display = 'none';
          //document.getElementById("downloadFile").innerHTML ='';
      }
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}
*/


function deleteReview(){
    var Id=document.getElementById("reviewId").value;
    window.location = "deleteReview.action?reviewId="+Id;
}

/*
function ppulateApprovedByResult(resTxt){
   var result= resTxt.split("|");

       document.getElementById("ApprovedBy1").innerHTML=result[0].split("@")[0];
       document.getElementById("ApprovedByComments1").value=result[0].split("@")[1];
       document.getElementById("ApprovedBy2").innerHTML=result[1].split("@")[0];
       document.getElementById("ApprovedByComments2").value=result[1].split("@")[1];
   
}

*/
 function getBasePoints(pointObj) {
    document.getElementById("leadRating").value="";
    document.getElementById("hrRating").value="";
    document.getElementById("maxPoints").value="";
    
    document.getElementById("leadpointsInfo").innerHTML="";
    document.getElementById("hrpointsInfo").innerHTML="";
    document.getElementById("onsiteMgrpointsInfo").innerHTML="";
    if(pointObj.value.length>0){
     var req = newXMLHttpRequest();
     req.onreadystatechange = readyStateHandler(req,populateBasePoints); 

    var url=CONTENXT_PATH+"/getBasePoints.action?reviewTypeId="+pointObj.value;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    }
}


function populateBasePoints(resText){
    document.getElementById("leadRating").value=0;
    document.getElementById("hrRating").value=0;
    document.getElementById("maxPoints").value=resText;
    
    document.getElementById("leadpointsInfo").innerHTML="<font color='green' size='2px'>(Max&nbsp;Points:"+resText+")";
    document.getElementById("hrpointsInfo").innerHTML="<font color='green' size='2px'>(Max&nbsp;Points:"+resText+")";
    document.getElementById("onsiteMgrpointsInfo").innerHTML="<font color='green' size='2px'>(Max&nbsp;Points:"+resText+")";
    document.getElementById("onsiteMgrRating").value=0;
    if(parseInt(resText)<0){
        document.getElementById("leadRating").value=resText;
    document.getElementById("hrRating").value=resText;
     document.getElementById("onsiteMgrRating").value=resText;
    }
}


//Update My review changes


function upadteMyreview(){
    //alert("id-->"+id+"----------- empname"+empName);
    var reviewId=  document.getElementById("reviewId").value;
  //  var overlayReviewType=     document.getElementById("overlayReviewType").value ;
    var overlayReviewName=     document.getElementById("overlayReviewName").value ;
    var overlayReviewDate=     document.getElementById("overlayReviewDate").value ;
    var overlayDescription=     document.getElementById("overlayDescription").value ;
  //  var overlayReviewCreatedDate=     document.getElementById("overlayReviewCreatedDate").value ;
    var reviewStatusOverlay=   document.getElementById("reviewStatusOverlay").value ;
    
  document.getElementById('resultMessage').style.display="block";
  
      var overlayReviewType=document.getElementById("overlayReviewType").value;
      var overlayReviewLogAdded =0;
   var overlayReviewBilling =0;
  if(overlayReviewType=='55'){
       overlayReviewLogAdded = document.getElementById('overlayReviewLogAdded').value;
       overlayReviewBilling = document.getElementById('overlayReviewBilling').value;
       //alert(overlayReviewBilling);
        if(overlayReviewBilling.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please Enter Billing amount.</font>";
       return false
        }
       if(parseInt(overlayReviewBilling)<=0){
           document.getElementById('resultMessage').innerHTML = "<font color=red>Billing amount must be greater than zero..</font>";
           return false;
       }
   }

   
     // alert(overlayReviewType);
   // var leadRating = document.getElementById("leadRating").value ;
 //   var hrRating = document.getElementById("hrRating").value ;
    
 //   var tlComments = "";
 //   var hrComments = "";
  //  alert(type)
 //   if(type=='1'){
        
  //      tlComments =document.getElementById("tlComments").value;
  //      hrComments = document.getElementById("hrComments").value;
  //  }
   // alert(tlComments);
   // alert(tlComments)
    overlayReviewName = escape(overlayReviewName);
       overlayDescription = escape(overlayDescription);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,ppulateMyReviewUpadteResult); 

    //var url=CONTENXT_PATH+"/updateMyReview.action?reviewId="+reviewId+"&overlayReviewType="+overlayReviewType+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay;
    var url=CONTENXT_PATH+"/updateMyReview.action?reviewId="+reviewId+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay+"&overlayReviewLogAdded="+overlayReviewLogAdded+"&overlayReviewBilling="+overlayReviewBilling;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
   

}

function ppulateMyReviewUpadteResult(resText){
     document.getElementById('resultMessage').innerHTML = "<font color=green size=2px>Updated Successfully</font>";
     document.getElementById('resultMessage').style.display="block";
  //  alert("Updated Successfully");
    toggleOverlay();
}


 function disableFunction() {
    //alert("indisable");
    document.getElementById("addButton").disabled = 'true';
}


function isNumberKeyExam(evt) {
 //alert(evt);
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode!=45 && charCode!=46 && charCode > 31 && (charCode < 48 || charCode > 57))
    {
     // alert("Please enter numeric value");
      document.getElementById('resultMessage').innerHTML = "<font color=red size=2px>Please enter numeric value.</font>";
      return false;
    }else {
        return true;
        }
    }

    
    function getCuurentDate(){
    var today = new Date();
var dd = today.getDate();
var mm = today.getMonth()+1; //January is 0!
var yyyy = today.getFullYear();

if(dd<10) {
    dd='0'+dd
} 

if(mm<10) {
    mm='0'+mm
} 

today = mm+'/'+dd+'/'+yyyy;

return today;
}





function reviewFieldLengthValidator(element) {
    var i=0;
    var fieldName='';
 //alert("In Field Length validator ESCV");
    if(element.value!=null&&(element.value!="")) {
        if(element.id=="overlayReviewName") { 
            fieldName = 'Review Name';
            i=60;
        }
        if(element.id=="overlayDescription") { 
            fieldName = 'Description';
            i=1500;
        }
         if(element.id=="tlComments") { 
            fieldName = 'Lead Comments';
            i=1500;
        } if(element.id=="hrComments") { 
            fieldName = 'Hr Comments';
            i=1500;
        } if(element.id=="onsiteMgrComments") { 
            fieldName = 'OnsiteMgr Comments';
            i=1500;
        }
       //  if(element.id == 'billingManager'|| element.id == 'bankName') { 
      
        if(element.value.replace(/^\s+|\s+$/g,"").length>i) {
            str=new String(element.value);
            element.value=str.substring(0,i);
            alert("The "+fieldName+" length must be less than "+i+" characters");
            element.focus();
            return false;
        }
        return true;
        
        
    }
}






/*Modified method for Employee review details
 * Author : santosh Kola
 * Date : 03/18/2015
 */


//function toggleEditOverlay(reviewId,reviewType,reviewName,dateOfReview,Comments,attachmentName,createdDate,reviewStatusOverlay,tlRating,hrRating,maxPoints,hrComments,tlComments,ApprovedBy1,Approver1Date,Approver2Date,ApprovedBy2,reviewTypeName){
function toggleEditOverlay(reviewId){
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');
    hideRow('uploadTr');
    showRow('downloadTr');
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Edit Review";
    document.getElementById("update").style.display="none";
         document.getElementById("delete").style.display="none";
    var roleName = document.getElementById("roleName").value;
     document.getElementById("load").style.display = 'block';
     $.ajax({
            url:'getReviewDetails.action?reviewId='+reviewId,//
            context: document.body,
            success: function(responseText) {
                                var json = $.parseJSON(responseText);
                
                var EmployeeName = json["EmployeeName"];
                var ReviewType = json["ReviewType"];
                var EmpId = json["EmpId"];
                  var ReviewTypeId = json["ReviewTypeId"];
                var Status = json["Status"];
                var EmpComments = json["EmpComments"];
                  var CreatdBy = json["CreatdBy"];
                var CreatedDate = json["CreatedDate"];
                var AttachmentName = json["AttachmentName"];
                  var AttachmentLocation = json["AttachmentLocation"];
                var ReviewName = json["ReviewName"];
                var ReviewDate = json["ReviewDate"];
                var UserId = json["UserId"];
                var HrComments = json["HrComments"];
                var TLComments = json["TLComments"];
                var TLRating = json["TLRating"];
                var HRRating = json["HRRating"];
                var MaxPoints = json["MaxPoints"];
                var ApprovedBy1 = json["ApprovedBy1"];
                var Approver1Date = json["Approver1Date"];
                var Approver2Date = json["Approver2Date"];
                var ApprovedBy2 = json["ApprovedBy2"];
                 var Id = json["Id"];
                 var BillingAmount = json["BillingAmount"];
                 var LogosCount = json["LogosCount"];
                 
               //alert(ReviewTypeId+" BillingAmount "+BillingAmount);
                
                 document.getElementById("reviewId").value = Id;
    document.getElementById("overlayReviewType").value = ReviewTypeId;
    document.getElementById("overlayReviewName").value = ReviewName;
    document.getElementById("overlayDescription").value = EmpComments;
    if(ReviewTypeId=='55'){
        document.getElementById('overlayReviewLogAdded').value=LogosCount;
       document.getElementById('overlayReviewBilling').value=BillingAmount;
          showRow('RequirementClosedTR');
    }
    else{
         hideRow('RequirementClosedTR');
    }
    
    
    if(TLComments !=null && TLComments !='' && TLComments.trim().length>0){
        document.getElementById("tlComments").value = TLComments;
        
    }else {
        hideRow('tlcommentsTr');
    }if(HrComments !=null && HrComments !='' && HrComments.trim().length>0){
        document.getElementById("hrComments").value = HrComments;
        
    }else {
        hideRow('hrcommentsTr');
    }
    document.getElementById("leadRating").value = TLRating;
    document.getElementById("hrRating").value = HRRating;
    //reviewName
  document.getElementById("overlayReviewType").style.display='none';
  document.getElementById("reviewTypeValue").style.display='block';
  //alert(reviewName);
  document.getElementById("reviewTypeValue").innerHTML = "<font color='green' size='2px'>"+ReviewType+"</font>"
  
    
    
   // alert(ApprovedBy1.trim().length);
    if(ApprovedBy1 !=null && ApprovedBy1 !='' && ApprovedBy1.trim().length>0){
        document.getElementById("tlId").innerHTML=ApprovedBy1;
        document.getElementById("ApprovedBy1Date").innerHTML=Approver1Date.split(" ")[0];
        //document.getElementById("tlApprovedDetails").style.display = 'block';
        //showRow('tlApprovedDetails');
    }
     if(ApprovedBy2 !=null && ApprovedBy2 !='' &&ApprovedBy2.trim().length>0){
        document.getElementById("hrId").innerHTML=ApprovedBy2;
        document.getElementById("ApprovedBy2Date").innerHTML=Approver2Date.split(" ")[0];
        //document.getElementById("hrApprovedDetails").style.display = 'block';
       // showRow('hrApprovedDetails');
    }
    
    
    document.getElementById("hrpointsInfo").innerHTML = "<font color='green' size='2px'>(Max&nbsp;Points:"+MaxPoints+")";
    document.getElementById("leadpointsInfo").innerHTML = "<font color='green' size='2px'>(Max&nbsp;Points:"+MaxPoints+")";
    
    if(Status=='Opened'){
         document.getElementById("reviewStatusOverlay").style.display = 'block';
    }else {
         document.getElementById("reviewStatusOverlay").style.display = 'none';
         document.getElementById("stateLabel").style.display = 'block';
         
        document.getElementById("stateLabel").innerHTML = "<font color='green' size='2px'>"+Status+"</font>";
    }
        
    
    var reviewedDate = ReviewDate.split(" ")[0];
    var reviewDate = reviewedDate.split("-");
    var reviewedDateConverted = reviewDate[1]+"/"+reviewDate[2]+"/"+reviewDate[0];
    var rvcreatedDate = CreatedDate.split(" ")[0];
    var rvCrDate = rvcreatedDate.split("-");
    var reviewedCreatedDateConverted = rvCrDate[1]+"/"+rvCrDate[2]+"/"+rvCrDate[0];
    document.getElementById("overlayReviewDate").value = reviewedDateConverted;
    
   // document.getElementById("overlayReviewCreatedDate").value = reviewedCreatedDateConverted;
    document.getElementById("reviewStatusOverlay").value = Status;


    if(Status=="Approved" || Status=="Denied"){
        document.getElementById("update").style.display="none";
         document.getElementById("delete").style.display="none";
      
   }
   else{
        document.getElementById("update").style.display="block";
         document.getElementById("delete").style.display="block";
        // hideRow("approvedByComments2");
        //    hideRow("approvedByComments1");
   }
    if(AttachmentName!= null && AttachmentName!=''&& AttachmentName.trim().length>0)  {
         // alert("hiii");
          document.getElementById("downloadLink").style.display = 'block';
          
         document.getElementById("downloadFile").innerHTML = AttachmentName; 
      }else {
          //alert("none");
          document.getElementById("downloadLink").style.display = 'none';
          //document.getElementById("downloadFile").innerHTML ='';
      }
                
                
               document.getElementById("load").style.display = 'none';  
               document.getElementById("resultMessage").style.display = 'none';  
            
                
            }, error: function(e){
                document.getElementById("load").style.display = 'none';
                alert("error-->"+e);
            }
        });
    
    
    
    
    
    
    
    
   
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}




function toggleTeamReviewEditOverlay(reviewId){
    // alert("reviewId-->"+reviewId);
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Edit Review";
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');
    hideRow('uploadTr');
    showRow('downloadTr');
    hideRow('tlApprovedDetails');
    hideRow('hrApprovedDetails');
    hideRow('onsiteMgrApprovedDetails');
    var roleName = document.getElementById("roleName").value;
    //  alert(roleName);
    document.getElementById("overlayDescription").readOnly = true;
    if(roleName=='Employee' || roleName=='employee'){
        //showRow('tlcommentsTr');
        //   hideRow('hrcommentsTr');
        hideRow('hrcommentsTr');
        hideRow('hrratingsTr');
        document.getElementById("leadRating").readOnly = false;
        document.getElementById("hrRating").readOnly = true;
        document.getElementById("hrComments").readOnly = true;
        document.getElementById("tlComments").readOnly = false;
        
        var isManager = document.getElementById("isManager").value;
        var livingCountry = document.getElementById("livingCountry").value;
        if(isManager==1 && livingCountry=='USA'){
            showRow('onsiteMgrcommentsTr');
            showRow('onsiteMgrratingsTr');
            document.getElementById("onsiteMgrComments").readOnly = false;
            document.getElementById("onsiteMgrRating").readOnly = false;
        //  showRow('onsiteMgrratingsTr');
        //onsiteMgrApprovedDetails
        }else {
            hideRow('onsiteMgrcommentsTr');
            hideRow('onsiteMgrratingsTr');
            document.getElementById("onsiteMgrComments").readOnly = true;
            document.getElementById("onsiteMgrRating").readOnly = true;
        }
        
        
        
    }
    if(roleName=='Operations'){
        //   showRow('hrcommentsTr');
        //   hideRow('tlcommentsTr');
        document.getElementById("hrRating").readOnly = false;
        document.getElementById("leadRating").readOnly = true;
        document.getElementById("tlComments").readOnly = true;
        document.getElementById("hrComments").readOnly = false;
        
        
        showRow('onsiteMgrcommentsTr');
        showRow('onsiteMgrratingsTr');
        document.getElementById("onsiteMgrComments").readOnly = true;
        document.getElementById("onsiteMgrRating").readOnly = true;
        document.getElementById("reviewOnsiteMgrStatusOverlay").disabled = true;
        document.getElementById("reviewStatusOverlay").disabled = true;
    }
   
    document.getElementById("load").style.display = 'block';  
    $.ajax({
        url:'getReviewDetails.action?reviewId='+reviewId,//
        context: document.body,
        success: function(responseText) {
            var json = $.parseJSON(responseText);
            var EmployeeName = json["EmployeeName"];
            var ReviewType = json["ReviewType"];
            var EmpId = json["EmpId"];
            var ReviewTypeId = json["ReviewTypeId"];
            var Status = json["Status"];
            var EmpComments = json["EmpComments"];
            var CreatdBy = json["CreatdBy"];
            var CreatedDate = json["CreatedDate"];
            var AttachmentName = json["AttachmentName"];
            var AttachmentLocation = json["AttachmentLocation"];
            var ReviewName = json["ReviewName"];
            var ReviewDate = json["ReviewDate"];
            var UserId = json["UserId"];
            var HrComments = json["HrComments"];
            var TLComments = json["TLComments"];
            var TLRating = json["TLRating"];
            var HRRating = json["HRRating"];
            var MaxPoints = json["MaxPoints"];
            var ApprovedBy1 = json["ApprovedBy1"];
            var Approver1Date = json["Approver1Date"];
            var Approver2Date = json["Approver2Date"];
            var ApprovedBy2 = json["ApprovedBy2"];
            var Approver3Date = json["Approver3Date"];
            var ApprovedBy3 = json["ApprovedBy3"];
            var OnSiteMgrRating = json["OnSiteMgrRating"];
            var OnSiteMgrComments = json["OnSiteMgrComments"];
            var OnSiteMgrStatus = json["OnSiteMgrStatus"];
            var Id = json["Id"];
            var HRStatus = json["HRStatus"];
                
            var BillingAmount = json["BillingAmount"];
            var LogosCount = json["LogosCount"];
				 
            if(ReviewTypeId=='55'){
                document.getElementById('overlayReviewLogAdded').value=LogosCount;
                document.getElementById('overlayReviewBilling').value=BillingAmount;
                showRow('RequirementClosedTR');
            }
            else{
                hideRow('RequirementClosedTR');
                document.getElementById('overlayReviewLogAdded').value=0;
                document.getElementById('overlayReviewBilling').value=0;
            }
            //   document.getElementById("uploadTr").style.display = 'none';
            //  document.getElementById("downloadTr").style.display = 'table-row';
            document.getElementById("reviewId").value = Id;
            //alert(ReviewTypeId);
            document.getElementById("overlayReviewType").value = ReviewTypeId;
            document.getElementById("tempoverlayReviewType").value = parseInt(ReviewTypeId,10);
            document.getElementById("overlayReviewName").value = ReviewName;
            document.getElementById("reviewStatusOverlay").value = Status;
            document.getElementById("maxPoints").value = MaxPoints;
            document.getElementById("leadRating").value = TLRating;
            document.getElementById("hrRating").value = HRRating;
            document.getElementById("hrpointsInfo").innerHTML = "<font color='green' size='2px'>(Max&nbsp;Points:"+MaxPoints+")";
            document.getElementById("leadpointsInfo").innerHTML = "<font color='green' size='2px'>(Max&nbsp;Points:"+MaxPoints+")";
            document.getElementById("onsiteMgrpointsInfo").innerHTML = "<font color='green' size='2px'>(Max&nbsp;Points:"+MaxPoints+")";
            document.getElementById("userId").value = UserId;
            document.getElementById("userId").disabled=true;
            document.getElementById("reviewOnsiteMgrStatusOverlay").value = OnSiteMgrStatus;
            document.getElementById("reviewHrStatusOverlay").value = HRStatus;
            document.getElementById("onsiteMgrRating").value = OnSiteMgrRating;
            document.getElementById("onsiteMgrComments").value = OnSiteMgrComments;
            //reviewName
            document.getElementById("overlayReviewType").style.display='none';
            //alert(reviewName);
            document.getElementById("reviewTypeValue").innerHTML = "<font color='green' size='2px'>"+ReviewType+"</font>"
  
  
            var reviewedDate = ReviewDate.split(" ")[0];
            var reviewDate = reviewedDate.split("-");
            var reviewedDateConverted = reviewDate[1]+"/"+reviewDate[2]+"/"+reviewDate[0];
            var rvcreatedDate = CreatedDate.split(" ")[0];
            var rvCrDate = rvcreatedDate.split("-");
            var reviewedCreatedDateConverted = rvCrDate[1]+"/"+rvCrDate[2]+"/"+rvCrDate[0];
            //document.getElementById("overlayReviewCreatedDate").value = reviewedCreatedDateConverted;
            document.getElementById("overlayReviewDate").value = reviewedDateConverted;
            document.getElementById("overlayDescription").value = EmpComments;
            // document.getElementById("addButton").style.display = 'none';
            //  document.getElementById("updateButton").style.display = 'block';
    
            document.getElementById("tlComments").value=TLComments;
            document.getElementById("hrComments").value=HrComments;
            if(ApprovedBy3 !=null && ApprovedBy3 !='' && ApprovedBy3.trim().length>0){
      
                document.getElementById("onsiteMgrId").innerHTML = ApprovedBy3;       
                document.getElementById("ApprovedBy3Date").innerHTML = Approver3Date.split(" ")[0]; 
                showRow('onsiteMgrApprovedDetails');
            }
            if(ApprovedBy1 !=null && ApprovedBy1 !='' && ApprovedBy1.trim().length>0){
      
                document.getElementById("tlId").innerHTML = ApprovedBy1;       
                document.getElementById("ApprovedBy1Date").innerHTML = Approver1Date.split(" ")[0]; 
                showRow('tlApprovedDetails');
               // alert(isManager+" "+roleName) ; 
                if(roleName=='Employee' || roleName=='employee'){
                    hideRow('hrApprovedDetails');  
                    if(isManager!=1 || livingCountry!='USA'){
                        hideRow('onsiteMgrApprovedDetails');
        
                    }
                }
            }
  
            if(ApprovedBy2 !=null && ApprovedBy2 !='' &&ApprovedBy2.trim().length>0){
       
                document.getElementById("hrId").innerHTML = ApprovedBy2;       
                document.getElementById("ApprovedBy2Date").innerHTML = Approver2Date.split(" ")[0]; 
          
                if(roleName=='Employee' || roleName=='employee'){
                    hideRow('hrApprovedDetails');  
                }else {
                    showRow('hrApprovedDetails');  
                }
            }
   
  
    
            // document.getElementById("downloadFile").innerHTML = attachmentName;
    
            if(AttachmentName!= null && AttachmentName!=''&& AttachmentName.trim().length>0)  {
                // alert("hiii");
                document.getElementById("downloadLink").style.display = 'block';
          
                document.getElementById("downloadFile").innerHTML = AttachmentName; 
            }else {
                //alert("none");
                document.getElementById("downloadLink").style.display = 'none';
            //document.getElementById("downloadFile").innerHTML ='';
            }
    
            document.getElementById("load").style.display = 'none';
        }, 
        error: function(e){
            document.getElementById("load").style.display = 'none';
            alert("error-->"+e);
        }
    });
    
    
    
    
    
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}
 



/*
* 
Newly moved from jsp to js
* */

   //function toggleOverlay(){
        function mytoggleOverlay(){
            // hideRow('downloadTr');
            // showRow('uploadTr');
            document.getElementById("downloadTr").style.display = 'none';
            document.getElementById("uploadTr").style.display = '';
            document.getElementById("reviewId").value = '';
            document.getElementById("overlayReviewType").value = '';
            document.getElementById("overlayReviewName").value = '';
            document.getElementById("resultMessage").value = '';
           // document.getElementById("overlayReviewDate").value = '';
            document.getElementById("overlayDescription").value = '';
            document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Add Review";
            var overlay = document.getElementById('overlay');
            var specialBox = document.getElementById('specialBox');
           //  hideRow("approvedBy");
           hideRow("tlcommentsTr");
            hideRow("hrcommentsTr");
            overlay.style.opacity = .8;
            if(overlay.style.display == "block"){
                overlay.style.display = "none";
                specialBox.style.display = "none";
                var startDate = document.getElementById("startDate").value;
                var endDate = document.getElementById("endDate").value;
                var reviewType = document.getElementById("reviewType").value;
                //var reviewStatus = document.getElementById("reviewStatus").value;
                var roleName = document.getElementById("roleName").value;
                
                
                var empnameById="-1";
                if(roleName=="Hr")
                    {
                        empnameById = document.getElementById("empnameById").value;
                    }
                //document.frmEmpSearch.submit();
                document.frmDBGrid.submit();
                //window.location = 'reviewSearch.action?startDate='+startDate+'&endDate='+endDate+'&reviewType='+reviewType+'&reviewStatus='+reviewStatus+'&empnameById='+empnameById;
            } else {
                
                 hideRow('reviewTypeValue');
                 showRow('overlayReviewType');
                 showRow('reviewStatusOverlay');
                 hideRow('stateLabel');
                 
                overlay.style.display = "block";
                specialBox.style.display = "block";
            }
        }







  function teamtoggleOverlay(){
    document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Add Review";
    hideRow('downloadTr');
     showRow('uploadTr');
        // document.getElementById("downloadTr").style.display = 'none';
       //  document.getElementById("uploadTr").style.display = '';
         document.getElementById("reviewId").value = '';
        document.getElementById("overlayReviewType").value = '';
        document.getElementById("overlayReviewName").value = '';
        document.getElementById("overlayReviewDate").value = getCuurentDate();
        document.getElementById("overlayDescription").value = '';
         hideRow('RequirementClosedTR');
        var roleName = document.getElementById("roleName").value;
        var isManager = document.getElementById("isManager").value;
        var livingCountry = document.getElementById("livingCountry").value;
        //alert(roleName);
        
         if(roleName=='Employee' || roleName=='employee'){
        //showRow('tlcommentsTr');
     //   hideRow('hrcommentsTr');
         hideRow('hrcommentsTr');
        hideRow('hrratingsTr');
        document.getElementById("reviewStatusOverlay").value='Approved';
        document.getElementById("reviewOnsiteMgrStatusOverlay").value='Opened';
        document.getElementById("reviewHrStatusOverlay").value='Opened';
        document.getElementById("leadRating").readOnly = false;
        document.getElementById("hrRating").readOnly = true;
        document.getElementById("hrComments").readOnly = true;
        document.getElementById("tlComments").readOnly = false;
       // alert(isManager+" "+livingCountry);
        
        if(isManager=="1" && livingCountry=="USA"){
            document.getElementById("reviewStatusOverlay").value='Approved';
          document.getElementById("reviewOnsiteMgrStatusOverlay").value='Approved';
          document.getElementById("reviewHrStatusOverlay").value='Opened';
         showRow('onsiteMgrcommentsTr');
        showRow('onsiteMgrratingsTr');
        }
        else{
            hideRow('onsiteMgrcommentsTr');
        hideRow('onsiteMgrratingsTr');
        
        }
    }
    if(roleName=='Operations'){
     //   showRow('hrcommentsTr');
     //   hideRow('tlcommentsTr');
        document.getElementById("hrRating").readOnly = false;
        document.getElementById("leadRating").readOnly = true;
        document.getElementById("tlComments").readOnly = true;
        document.getElementById("hrComments").readOnly = false;
        document.getElementById("reviewStatusOverlay").value='Opened';
          document.getElementById("reviewOnsiteMgrStatusOverlay").value='Opened';
          document.getElementById("reviewHrStatusOverlay").value='Approved';
    }
        
        
        
	var overlay = document.getElementById('overlay');
	var specialBox = document.getElementById('specialBox');
	overlay.style.opacity = .8;
	if(overlay.style.display == "block"){
		overlay.style.display = "none";
		specialBox.style.display = "none";
                  var startDate = document.getElementById("startDate").value;
                var endDate = document.getElementById("endDate").value;
                var reviewType = document.getElementById("reviewType").value;
                //var reviewStatus = document.getElementById("reviewStatus").value;
             //   var roleName = document.getElementById("roleName").value;
                var empnameById=document.getElementById("empnameById").value;
                    
               // document.frmEmpSearch.submit();
               document.frmDBGrid.submit();
               // window.location = 'teamReviewSearch.action?startDate='+startDate+'&endDate='+endDate+'&reviewType='+reviewType+'&empnameById='+empnameById;
	} else {
		overlay.style.display = "block";
		specialBox.style.display = "block";
	}
}




 function getRequirementClosed(){
    var reviewType=empnameById=document.getElementById("overlayReviewType").value;
    if(reviewType=='55'){
        showRow('RequirementClosedTR');
    }
    else{
         hideRow('RequirementClosedTR');
    }
}


/*Account add excel upload
 *Author : Kalpana
 * DAte : 03/14/2016
 * 
 */

function ajaxExcelUpload()
{    
   var attachmentFileName = document.getElementById('file').value;    
   if(attachmentFileName.length==0){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please upload File.</font>";
   }
   else {        
    $.ajaxFileUpload({        
        url:'ajaxExcelUpload.action',      
        secureuri:false,//false
        fileElementId:'file',//id  <input type="file" id="file" name="file" />
        dataType: 'json',// json
        success: function(data,status){            
            var displaymessage = "<font color=red>Please try again later</font>";    
           // alert(data);
            if(data.indexOf("uploaded")>0){   
            //if(data=="uploaded"){               
                displaymessage = "<font color=green>Uploaded Successfully.</font>";
            } 
             if(data.indexOf("Error")>0){ 
            //if(data=="Error"){               
                displaymessage = "<font color=red>Internal Error!, Please try again later.</font>"
            } //if(data=="Exceded"){               
            if(data.indexOf("Exceded")>0){ 
                displaymessage = "<font color=red>Max records exceded.Please upload less than 1500 .</font>"
            } 
            if(data.indexOf("InvalidFormat")>0){ 
            //if(data=="InvalidFormat"){               
                displaymessage = "<font color=red>Please upoload excelsheet with specified header fileds.</font>"
            }                    
            document.getElementById('resultMessage').innerHTML = displaymessage;        
        },
        error: function(e){                     
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";       
        }
    });    
   }
   //}	
    return false;
}


function accountExcelFileValidation() {
    var imagePath =document.getElementById('file').value;   
        if(imagePath != null && (imagePath !="")) {                
            var extension = imagePath.substring(imagePath.lastIndexOf('.')+1);            
            if(extension=="xls"){                  
                    return (true);
                }            
                else {
                document.getElementById('file').value = "";
                document.getElementById('resultMessage').innerHTML = "<font color=red>Invalid file extension!Please select xlsx file.</font>"
                
                return (false);
            }
        }
    return (true);
};



