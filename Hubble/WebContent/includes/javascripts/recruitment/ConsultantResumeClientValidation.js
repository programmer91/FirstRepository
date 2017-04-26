function attachmentNameValidate(){
    
   var attachmentName= document.frmAttach.attachmentName;
   
       
    if (attachmentName.value != null && (attachmentName.value != "")) {
        if(attachmentName.value.replace(/^\s+|\s+$/g,"").length>50){
                       
                 str = new String(document.frmAttach.attachmentName.value);
             document.frmAttach.attachmentName.value=str.substring(0,50);
             
               alert("The AttachResumeName must be less than 50 characters");
           
              }
       document.frmAttach.attachmentName.focus();
        return (false);
    }
  
    return (true);
};


function attachmentFileNameValidate(){
    //alert("attachmentFileNameValidate");
   var attachmentFileName= document.frmAttach.attachmentFileName;
   
       
    if (attachmentFileName.value != null && (attachmentFileName.value != "")) {
        if(attachmentFileName.value.length>40){
                       
                 
               document.getElementById('attachmentFileName').value = "";
               alert("File name must be less than 40 characters.Please rename !");
           
              }
       document.frmAttach.attachmentFileName.focus();
        return (false);
    }
  
    return (true);
};

function checkManadatory() {
    
 var attachmentFileName= document.getElementById('attachmentFileName').value;
 var attachmentName= document.frmAttach.attachmentName;
   if(attachmentFileName.length<=0) {
  alert("Please browse file!");
    document.frmAttach.attachmentFileName.focus();
    return (false);
    }else if(attachmentName.value == null || attachmentName.value == "") {
        alert("Please Enter Attachment Name !");
        document.frmAttach.attachmentName.focus();
        return (false);
    }
  else {
 
    return (true);
    }

}