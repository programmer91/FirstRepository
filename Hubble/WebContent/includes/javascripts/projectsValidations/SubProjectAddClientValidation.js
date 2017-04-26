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


function subPrjNameValidate(){
      var subPrjName= document.frmSubProjectAdd.subPrjName;
    
    
    if (subPrjName.value != null && (subPrjName.value != "")) {
        if(subPrjName.value.replace(/^\s+|\s+$/g,"").length>30){
            
              str = new String(document.frmSubProjectAdd.subPrjName.value);
            document.frmSubProjectAdd.subPrjName.value=str.substring(0,30);
            
            alert("The subProjectName must be less than 30 characters");
            
          
           
              }
       document.frmSubProjectAdd.subPrjName.focus();
        return (false);
    }
  
    return (true);
};

function totalResourcesValidate(){
      var totalResources= document.frmSubProjectAdd.totalResources;
    
    
    if (totalResources.value != null && (totalResources.value != "")) {
        if(totalResources.value.replace(/^\s+|\s+$/g,"").length>10){
            
              str = new String(document.frmSubProjectAdd.totalResources.value);
            document.frmSubProjectAdd.totalResources.value=str.substring(0,10);
            
            alert("The teamSize must be less than 10 characters");
            
          
           
              }
       document.frmSubProjectAdd.totalResources.focus();
        return (false);
    }
  
    return (true);
};

function descriptionValidate(){
      var description= document.frmSubProjectAdd.description;
    
    
    if (description.value != null && (description.value != "")) {
        if(description.value.replace(/^\s+|\s+$/g,"").length>80){
            
              str = new String(document.frmSubProjectAdd.description.value);
            document.frmSubProjectAdd.description.value=str.substring(0,80);
            
            alert("The description must be less than 80 characters");
            
          
           
              }
       document.frmSubProjectAdd.description.focus();
        return (false);
    }
  
    return (true);
};