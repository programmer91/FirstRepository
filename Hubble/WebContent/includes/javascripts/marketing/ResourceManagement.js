/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function addNewResource() {
   // showRow("addTr");
   // hideRow("editTr");
    //document.getElementById("eventType").style.display='block';
           // document.getElementById("headerLabel").style.display='none';
   // clearData();
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
            document.getElementById("headerLabel").innerHTML="Add Resource";
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
            document.getElementById('resultMessage').innerHTML ='';
       //     hideRow('createdTr');
           /* hideRow('editTr');
            hideRow("createdTr");
*/
//clearData();

          //  document.getElementById("headerLabel").style.color="white";
          //  document.getElementById("headerLabel").innerHTML="Add EventPosting";
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

         
         //  document.getElementById("frmDBGrid").submit();
        }
        
        
function addLibrary() {


window.location="addLibrary.action";
}

function checkDoubleQuotes(obj){
   // if(obj.value.indexOf('"') > -1){
   
   obj.value =  obj.value.replace(/"/g , "'");
       
      //  document.getElementById('resultMessage').innerHTML = "<font color=red>Please replace with single quotes by double quotes..</font>";
        //document.getElementById("eventDescription").value = '';
   // }
    
    
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
var count=0;
function pageTitleCheck(){
    var resourceTitle = document.getElementById("resourceTitle").value;
    document.getElementById("load").innerHTML="";
    
    
    //alert(resourceTitle);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, pageTitleCheckResponse);
    // var url = CONTENXT_PATH+"/getEmpForReportsTo.action?deptName="+deptName;
    var url = CONTENXT_PATH+"/doLibraryTitleCheck.action?resourceTitle="+resourceTitle;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}
function pageTitleCheckResponse(resText) {
    //alert("resText "+resText);
    
    if(resText.length !=0){
        document.getElementById("load").innerHTML= resText;
        document.getElementById("load").style.display='block';
        
        document.getElementById("resourceTitle").value="";
        return false;
    }
    
    document.getElementById("load").style.display='hide';
}


 function libraryFieldsValidate(){
    //alert("test");
    var resourceTitle = document.getElementById("resourceTitle").value;
    var dateOfPublish = document.getElementById("dateOfPublish").value;
    var resourceType = document.getElementById("resourceType").value;
    var resourceIndustry = document.getElementById("resourceIndustry").value;
    var resourcePrimaryTrack = document.getElementById("resourcePrimaryTrack").value;
    var resourceDescription = document.getElementById("resourceDescription").value;
    var breadCrumbHeading = document.getElementById("breadCrumbHeading").value;
    var webPageCreationdetails = document.getElementById("webPageCreationdetails").value;
   // var primaryAuthor = document.getElementById("primaryAuthor").value;
   // var author2 = document.getElementById("author2").value;
 //   var author3 = document.getElementById("author3").value;
  if(document.getElementById('curentActionId').value=="doAddLibrary"){
    var libraryFile = document.getElementById("libraryFile").value;
    var isResourceDownloadable = document.getElementById("isResourceDownloadable").checked;
  }
      var resourceSecondaryTrack = document.getElementById("resourceSecondaryTrack").value;
       var gatedContent = document.getElementById("gatedContent").checked;
    //alert(resourceTitle );
    if(resourceTitle==""){
        alert("Please enter Page Title");
        return false;
    }
    
    if(dateOfPublish==""){
        alert("Please enter Date Of Publish");
        return false;
    }
    if(resourceType==""){
        alert("Please select Type");
        return false;
    }
    if(resourceIndustry==""){
        alert("Please select Industry");
        return false;
    }
    if(resourcePrimaryTrack==""){
        alert("Please select PrimaryTrack");
        return false;
    }
    else{
        if(resourceSecondaryTrack!="" && resourceSecondaryTrack==resourcePrimaryTrack){
             alert("Primary Track And Secondary Track should not be same");
             document.getElementById("resourceSecondaryTrack").value="";
                return false;
        }
    }
    if(resourceDescription=="" && gatedContent){
        alert("Please enter Resource Depot Description");
        return false;
    }
     var customerName = document.getElementById("customerName").value;
    if(document.getElementById('resourceType').value=="Case Study"){
        if(customerName==""){
            alert("Please enter Customer Name");
            return false;
        }else if(customerName.length>200){
            alert("Please enter less than 200 Characters");
            return false;
        }
    }
    if("Presentation"!=resourceType){
        if(breadCrumbHeading==""){
            alert("Please enter Breadcrumb Name");
            return false;
      
        }
        if(webPageCreationdetails==""){
            alert("Please enter Page Content");
            return false;
        }
    }
    if(document.getElementById('curentActionId').value=="doAddLibrary"){
   if(isResourceDownloadable==true){
        if(libraryFile==""){
            alert("Please upload File");
            return false;
        }
    }
    }

 if("Presentation"==resourceType){
       if(gatedContent==false){
            alert("gated content is mandatory if ResouceType is Presentation");
            return false;
       }
        if(document.getElementById('curentActionId').value=="doAddLibrary"){
        if(libraryFile==false){
            alert("Upload file is mandatory if ResouceType is Presentation");
            return false;
        }
        
        }
    }
   if( window.confirm("Do you want to save?")){
    return true;
   }else{
   return false;
   }
}


 function hideResourceDepotContent(){
    // alert("hai");
    if(document.getElementById('gatedContent').checked==true){
        
        //document.getElementById('gatedContent').value='1';
        
        showRow("resourceDescriptionTr");
    
    }else{
       //document.getElementById('gatedContent').value='0';
        document.getElementById("resourceDescription").value=""; 
        //document.getElementById("resourceDescription").value="";
        //document.getElementById('resourceDescriptionTr').style.display='inline';
       
         hideRow("resourceDescriptionTr");
    }
}

function hideBodyContent(){
    //alert("hai");
    if(document.getElementById('resourceType').value=="Presentation"){
        // document.getElementById('resourceDescriptionTr').style.display='none';
        //document.getElementById("resourceType").value="";
       // document.getElementById("bodyContent").value="";
        document.getElementById("webPageCreationdetails").value="";
        document.getElementById("breadCrumbHeading").value="";
        document.getElementById("phpFileName").value="";
     //   hideRow("resourceBodyDescriptionTr");
        hideRow("resourcePageDescriptionTr");
        hideRow("resourceBreadcrumbNameTr");
        hideRow("fileNameTr");
        //fileNameTr
    }else{
        //document.getElementById('resourceDescriptionTr').style.display='inline';
      //  showRow("resourceBodyDescriptionTr");
        showRow("resourcePageDescriptionTr");
        showRow("resourceBreadcrumbNameTr");
         showRow("fileNameTr");
    }
}
function hideUploadFile(){
   // alert("hai");
    if(document.getElementById('isResourceDownloadable').checked==true){
       
        // document.getElementById('resourceDescriptionTr').style.display='none';
        showRow("uploadTr");
        
    
    }else{
         
         document.getElementById("libraryFile").value="";
        //document.getElementById('resourceDescriptionTr').style.display='inline';
        hideRow("uploadTr");
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


 function validateFileName(){
 
    
                var txt = document.getElementById("phpFileName").value;
               // alert(txt);
var re = /^[A-Za-z0-9/-]*$/
if (re.test(txt)) {
//alert('Valid Text')
var first=txt.substring(txt.length-2,txt.length-1);
                    var second=txt.substring(txt.length-1,txt.length);
                    if(first=="-" && second=="-"){
                       // if(type=='1')
                        //document.getElementById("phpFileName").value=txt.substring(0,txt.length-1);
                       // else
                           document.getElementById("phpFileName").value="";  
                    }
}
else {
alert('Special character are not allowed except -(hyphen)');
//if(type=='1')
//document.getElementById("phpFileName").value=txt.substring(0,txt.length-1);
//else
   document.getElementById("phpFileName").value=""; 
return false;
}
 }
 
 


function getAuthors(libId,objectType) {
    window.location = "../getEventSpeakers.action?eventId="+libId+"&objectType="+objectType;
    
}

function downloadDoc(){
   
    window.location = "downloadLibraryDoc.action?libraryId="+document.getElementById("libraryId").value;
}


function readyStateHandler(req,responseXmlHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                responseXmlHandler(req.responseText);
            } else {
                alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }
    }
}




function showCustomerTag(){
    
    
    if(document.getElementById('resourceType').value=="Case Study"){
        showRow("customerNameTr");
    }else{
        document.getElementById('customerName').value='';
        hideRow("customerNameTr");
    }
}



 function libraryFieldLengthValidator(element){
   //  alert("hi"+element);
  
    if(element.id=="customerName"){
        i=200;
    }
    if(element.value.replace(/^\s+|\s+$/g,"").length>i) {
            str=new String(element.value);
            element.value=str.substring(0,i);
          //  alert("The "+element.id+" length must be less than "+i+" characters");
             alert("The " +element.id+ " length must be less than "+i+" characters");
            element.focus();
            return false;
        }
        return true;
}
