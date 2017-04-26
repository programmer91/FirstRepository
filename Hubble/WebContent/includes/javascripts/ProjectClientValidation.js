
     function validatenumber(xxxxx) {
   
    	var maintainplus = '';
 	var numval = xxxxx.value
 	if ( numval.charAt(0)=='+' ){ var maintainplus = '+';}
 	curnumbervar = numval.replace(/[\\A-Za-z!"£$%^&*+_={};:'@#~,.¦\/<>?|`¬\]\[]/g,'');
 	xxxxx.value = maintainplus + curnumbervar;
 	var maintainplus = '';
        // alert("enter integers only");
 	xxxxx.focus;
}

        
 function projectNameValidate(){
      var projectName= document.frmProjectAdd.projectName;
    
    
    if (projectName.value != null && (projectName.value != "")) {
        if(projectName.value.replace(/^\s+|\s+$/g,"").length>25){
            
             str = new String(document.frmProjectAdd.projectName.value);
            document.frmProjectAdd.projectName.value=str.substring(0,25);
            
            alert("The projectName must be less than 25 characters");
            
          
           
              }
       document.frmProjectAdd.projectName.focus();
        return (false);
    }
  
    return (true);
};

function totalResourcesValidate(){
      var totalResources= document.frmProjectAdd.totalResources;
    
    
    if (totalResources.value != null && (totalResources.value != "")) {
        if(totalResources.value.replace(/^\s+|\s+$/g,"").length>10){
            
              str = new String(document.frmProjectAdd.totalResources.value);
            document.frmProjectAdd.totalResources.value=str.substring(0,10);
            
            alert("The totalResources must be less than 10 characters");
            
            
           
              }
       document.frmProjectAdd.totalResources.focus();
        return (false);
    }
  
    return (true);
};

function descriptionValidate(){
      var description= document.frmProjectAdd.description;
    
    
    if (description.value != null && (description.value != "")) {
        if(description.value.replace(/^\s+|\s+$/g,"").length>80){
            
              str = new String(document.frmProjectAdd.description.value);
            document.frmProjectAdd.description.value=str.substring(0,80);  
            
            alert("The description must be less than 80 characters");
            
           
           
              }
       document.frmProjectAdd.description.focus();
        return (false);
    }
  
    return (true);
};
