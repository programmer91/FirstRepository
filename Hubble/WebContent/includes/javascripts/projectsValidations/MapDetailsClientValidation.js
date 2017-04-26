function mapNameValidate(){
      var mapName= document.frmMapEdit.mapName;
    
    
    if (mapName.value != null && (mapName.value != "")) {
        if(mapName.value.replace(/^\s+|\s+$/g,"").length>50){
            
              str = new String(document.frmMapEdit.mapName.value);
            document.frmMapEdit.mapName.value=str.substring(0,50);
            
            alert("The mapName must be less than 50 characters");
            
          
           
              }
       document.frmMapEdit.mapName.focus();
        return (false);
    }
  
    return (true);
};

function currentStateValidate(){
      var currentState= document.frmMapEdit.currentState;
    
    
    if (currentState.value != null && (currentState.value != "")) {
        if(currentState.value.replace(/^\s+|\s+$/g,"").length>10){
            
              str = new String(document.frmMapEdit.currentState.value);
            document.frmMapEdit.currentState.value=str.substring(0,10);
            
            alert("The currentState must be less than 10 characters");
            
          
           
              }
       document.frmMapEdit.currentState.focus();
        return (false);
    }
  
    return (true);
};

function bussinessDomainValidate(){
      var bussinessDomain= document.frmMapEdit.bussinessDomain;
    
    
    if (bussinessDomain.value != null && (bussinessDomain.value != "")) {
        if(bussinessDomain.value.replace(/^\s+|\s+$/g,"").length>20){
            
              str = new String(document.frmMapEdit.bussinessDomain.value);
            document.frmMapEdit.bussinessDomain.value=str.substring(0,20);
            
            alert("The bussinessDomain must be less than 20 characters");
            
          
           
              }
       document.frmMapEdit.bussinessDomain.focus();
        return (false);
    }
  
    return (true);
};

function descriptionValidate(){
      var description= document.frmMapEdit.description;
    
    
    if (description.value != null && (description.value != "")) {
        if(description.value.replace(/^\s+|\s+$/g,"").length>100){
            
              str = new String(document.frmMapEdit.description.value);
            document.frmMapEdit.description.value=str.substring(0,100);
            
            alert("The description must be less than 100 characters");
            
          
           
              }
       document.frmMapEdit.description.focus();
        return (false);
    }
  
    return (true);
};


