 function yearsOfExperienceValidate(){
    
   var yearsOfExperience= document.skillsForm.yearsOfExperience;
   
       
    if (yearsOfExperience.value != null && (yearsOfExperience.value != "")) {
        if(yearsOfExperience.value.replace(/^\s+|\s+$/g,"").length>9){
          
            
                 str = new String(document.skillsForm.yearsOfExperience.value);
             document.skillsForm.yearsOfExperience.value=str.substring(0,9);
             
               alert("The yearsOfExperience must be less than 9 characters");
           
              }
       document.skillsForm.yearsOfExperience.focus();
        return (false);
    }
  
    return (true);
};

function skillSetValidate(){
    
   var skillSet= document.skillsForm.skillSet;
   
       
    if (skillSet.value != null && (skillSet.value != "")) {
        if(skillSet.value.replace(/^\s+|\s+$/g,"").length>255){
          
            
                 str = new String(document.skillsForm.skillSet.value);
             document.skillsForm.skillSet.value=str.substring(0,255);
             
               alert("The skillSet must be less than 255 characters");
           
              }
       document.skillsForm.skillSet.focus();
        return (false);
    }
  
    return (true);
};

function skillSetUtilizedValidate(){
    
   var skillSetUtilized= document.skillsForm.skillSetUtilized;
   
       
    if (skillSetUtilized.value != null && (skillSetUtilized.value != "")) {
        if(skillSetUtilized.value.replace(/^\s+|\s+$/g,"").length>255){
          
            
                 str = new String(document.skillsForm.skillSetUtilized.value);
             document.skillsForm.skillSetUtilized.value=str.substring(0,255);
             
               alert("The skillSetUtilized must be less than 255 characters");
           
              }
       document.skillsForm.skillSetUtilized.focus();
        return (false);
    }
  
    return (true);
};

function projectInfoValidate(){
    
   var projectInfo= document.skillsForm.projectInfo;
   
       
    if (projectInfo.value != null && (projectInfo.value != "")) {
        if(projectInfo.value.replace(/^\s+|\s+$/g,"").length>255){
          
            
                 str = new String(document.skillsForm.projectInfo.value);
             document.skillsForm.projectInfo.value=str.substring(0,255);
             
               alert("The projectInfo must be less than 255 characters");
           
              }
       document.skillsForm.projectInfo.focus();
        return (false);
    }
  
    return (true);
};