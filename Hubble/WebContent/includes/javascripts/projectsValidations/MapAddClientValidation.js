 function mapNameValidate(){
      var mapName= document.frmMapAdd.mapName;
    
    
    if (mapName.value != null && (mapName.value != "")) {
        if(mapName.value.replace(/^\s+|\s+$/g,"").length>50){
            
              str = new String(document.frmMapAdd.mapName.value);
            document.frmMapAdd.mapName.value=str.substring(0,50);
            
            alert("The mapName must be less than 50 characters");
            
          
           
              }
       document.frmMapAdd.mapName.focus();
        return (false);
    }
  
    return (true);
};

 function currentStateValidate(){
      var currentState= document.frmMapAdd.currentState;
    
    
    if (currentState.value != null && (currentState.value != "")) {
        if(currentState.value.replace(/^\s+|\s+$/g,"").length>10){
            
              str = new String(document.frmMapAdd.currentState.value);
            document.frmMapAdd.currentState.value=str.substring(0,10);
            
            alert("The currentState must be less than 10 characters");
            
          
           
              }
       document.frmMapAdd.currentState.focus();
        return (false);
    }
  
    return (true);
};

 function bussinessDomainValidate(){
      var bussinessDomain= document.frmMapAdd.bussinessDomain;
    
    
    if (bussinessDomain.value != null && (bussinessDomain.value != "")) {
        if(bussinessDomain.value.replace(/^\s+|\s+$/g,"").length>20){
            
              str = new String(document.frmMapAdd.bussinessDomain.value);
            document.frmMapAdd.bussinessDomain.value=str.substring(0,20);
            
            alert("The bussinessDomain must be less than 20 characters");
            
          
           
              }
       document.frmMapAdd.bussinessDomain.focus();
        return (false);
    }
  
    return (true);
};

function descriptionValidate(){
      var description= document.frmMapAdd.description;
    
    
    if (description.value != null && (description.value != "")) {
        if(description.value.replace(/^\s+|\s+$/g,"").length>100){
            
              str = new String(document.frmMapAdd.description.value);
            document.frmMapAdd.description.value=str.substring(0,100);
            
            alert("The description must be less than 100 characters");
            
          
           
              }
       document.frmMapAdd.description.focus();
        return (false);
    }
  
    return (true);
};


