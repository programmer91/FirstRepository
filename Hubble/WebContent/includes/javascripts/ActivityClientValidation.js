

function descriptionValidate(){
      var description= document.activityForm.description;
    
    
    if (description.value != null && (description.value != "")) {
        if(description.value.replace(/^\s+|\s+$/g,"").length>255){
            
               str = new String( document.activityForm.description.value);
             document.activityForm.description.value=str.substring(0,255);
            
            alert("The description must be less than 255 characters");
            
              }
       document.activityForm.description.focus();
        return (false);
    }
  
    return (true);
};

function commentsValidate(){
      var comments= document.activityForm.comments;
    
    
    if (comments.value != null && (comments.value != "")) {
        if(comments.value.replace(/^\s+|\s+$/g,"").length>1000){
            
              str = new String( document.activityForm.comments.value);
            document.activityForm.comments.value=str.substring(0,1000);
            
            alert("The comments must be less than 1000 characters");
            
             }
       document.activityForm.comments.focus();
        return (false);
    }
  
    return (true);
};

