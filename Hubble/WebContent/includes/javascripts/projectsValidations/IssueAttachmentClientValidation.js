 function attachmentNameValidate(){
      var attachmentName= document.attachmentForm.attachmentName;
    
    
    if (attachmentName.value != null && (attachmentName.value != "")) {
        if(attachmentName.value.replace(/^\s+|\s+$/g,"").length>150){
            
              str = new String(document.attachmentForm.attachmentName.value);
            document.attachmentForm.attachmentName.value=str.substring(0,150);
            
            alert("The attachmentName must be less than 150 characters");
            
          
           
              }
       document.attachmentForm.attachmentName.focus();
        return (false);
    }
  
    return (true);
};

 function versionValidate(){
      var version= document.attachmentForm.version;
    
    
    if (version.value != null && (version.value != "")) {
        if(version.value.replace(/^\s+|\s+$/g,"").length>10){
            
              str = new String(document.attachmentForm.version.value);
            document.attachmentForm.version.value=str.substring(0,10);
            
            alert("The version must be less than 10 characters");
            
          
           
              }
       document.attachmentForm.version.focus();
        return (false);
    }
  
    return (true);
};

 function descriptionValidate(){
      var description= document.attachmentForm.description;
    
    
    if (description.value != null && (description.value != "")) {
        if(description.value.replace(/^\s+|\s+$/g,"").length>50){
            
              str = new String(document.attachmentForm.description.value);
            document.attachmentForm.description.value=str.substring(0,50);
            
            alert("The description must be less than 50 characters");
            
          
           
              }
       document.attachmentForm.description.focus();
        return (false);
    }
  
    return (true);
};

function attachmentFileNameValidate(){
    
   // alert("attachmentFileNameValidate");
   var attachmentFileName= document.attachmentForm.upload;
   
       
    if (attachmentFileName.value != null && (attachmentFileName.value != "")) {
        if(attachmentFileName.value.length>40){
                       
                 
               document.getElementById('attachmentFileName').value = "";
               alert("File name must be less than 40 characters.Please rename ! ");
           
              }
       document.attachmentForm.upload.focus();
        return (false);
    }
  
    return (true);
};


 function addAttachmentValidation(){
   // alert("hiii");
    var attachmentName=document.attachmentForm.attachmentName;
    var attachmentFileName= document.attachmentForm.upload; 
    var attachType=document.attachmentForm.attachType;
    var version=document.attachmentForm.version;
    var description=document.attachmentForm.description;
 // alert("attachmentName..."+attachmentName.value+"attachmentFileName..."+attachmentFileName.value+"attachType...."+attachType.value+"version..."+version.value+"description..."+description.value);
if(attachmentName.value==''){
  alert("Please enter attachment name")  ;
  return false;
}
if(attachmentFileName.value==''){
  alert("Please upload attachment")  ;
  return false;
}
if(attachType.value==''){
  alert("please select attachment type")  ;
  return false;
}
if(version.value==''){
  alert("please enter version")  ;
  return false;
}
if(description.value==''){
  alert("please enter description")  ;
  return false;
}

return true;
   
}
