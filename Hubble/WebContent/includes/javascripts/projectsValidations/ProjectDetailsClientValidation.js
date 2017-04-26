  function validatenumber(xxxxx) {
   
   	var maintainplus = '';
 	var numval = xxxxx.value
 	if ( numval.charAt(0)=='+' ){ var maintainplus = '+';}
 	curnumbervar = numval.replace(/[\\A-Za-z!"�$%^&*+_={};:'@#~,.�\/<>?|`�\]\[]/g,'');
 	xxxxx.value = maintainplus + curnumbervar;
 	var maintainplus = '';
       //  alert("enter integers only");
 	xxxxx.focus;
}

        
 function prjNameValidate(){
      var prjName= document.frmProjectEdit.prjName;
    
    
    if (prjName.value != null && (prjName.value != "")) {
        if(prjName.value.replace(/^\s+|\s+$/g,"").length>25){
            
              str = new String(document.frmProjectEdit.prjName.value);
            document.frmProjectEdit.prjName.value=str.substring(0,25);
            
            alert("The projectName must be less than 25 characters");
            
          
           
              }
       document.frmProjectEdit.prjName.focus();
        return (false);
    }
  
    return (true);
};

function totalResourcesValidate(){
      var totalResources= document.frmProjectEdit.totalResources;
    
    
    if (totalResources.value != null && (totalResources.value != "")) {
        if(totalResources.value.replace(/^\s+|\s+$/g,"").length>10){
            
              str = new String(document.frmProjectEdit.totalResources.value);
            document.frmProjectEdit.totalResources.value=str.substring(0,10);
            
            alert("The totalResources must be less than 10 characters");
            
            
           
              }
       document.frmProjectEdit.totalResources.focus();
        return (false);
    }
  
    return (true);
};

function descriptionValidate(){
      var description= document.frmProjectEdit.description;
    
    
    if (description.value != null && (description.value != "")) {
        if(description.value.replace(/^\s+|\s+$/g,"").length>512){
            
               str = new String(document.frmProjectEdit.description.value);
            document.frmProjectEdit.description.value=str.substring(0,512);  
            
            alert("The description must be less than 512 characters");
            
          
           
              }
       document.frmProjectEdit.description.focus();
        return (false);
    }
  
    return (true);
};
