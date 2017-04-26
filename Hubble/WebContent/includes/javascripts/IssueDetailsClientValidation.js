function descriptionValidate(){
      var description= document.issuesForm.description;
    
    
    if (description.value != null && (description.value != "")) {
        if(description.value.replace(/^\s+|\s+$/g,"").length>150){
            
                 str = new String(document.issuesForm.description.value);
             document.issuesForm.description.value=str.substring(0,150);
            
            alert("The description must be less than 150 characters");
            
         
           
              }
       document.issuesForm.description.focus();
        return (false);
    }
  
    return (true);
};

function commentsValidate(){
      var comments= document.issuesForm.comments;
    
    
    if (comments.value != null && (comments.value != "")) {
        if(comments.value.replace(/^\s+|\s+$/g,"").length>1500){
            
             str = new String(document.issuesForm.comments.value);
             document.issuesForm.comments.value=str.substring(0,1500);
            
            alert("The comments must be less than 1500 characters");
            
          
           
              }
       document.issuesForm.comments.focus();
        return (false);
    }
  
    return (true);
};

