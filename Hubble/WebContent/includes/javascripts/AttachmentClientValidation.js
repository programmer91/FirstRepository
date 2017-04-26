
function attachmentNameValidate(){
      var attachmentName= document.attachmentAdd.attachmentName;
    
    
    if (attachmentName.value != null && (attachmentName.value != "")) {
        if(attachmentName.value.replace(/^\s+|\s+$/g,"").length>50){
            
           str = new String(document.attachmentAdd.attachmentName.value);
           document.attachmentAdd.attachmentName.value=str.substring(0,50);
            
            alert("The attachmentName must be less than 50 characters");
           
              }
       document.attachmentAdd.attachmentName.focus();
        return (false);
    }
  
    return (true);
};

