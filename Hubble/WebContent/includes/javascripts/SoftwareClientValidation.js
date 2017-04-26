
function applicationsValidate(){
      var applications= document.softwareForm.applications;
    
    
    if (applications.value != null && (applications.value != "")) {
        if(applications.value.replace(/^\s+|\s+$/g,"").length>150){
            
            str = new String(document.softwareForm.applications.value);
             document.softwareForm.applications.value=str.substring(0,150);
            
            alert("The applications must be less than 150 characters");
       
              }
       document.softwareForm.applications.focus();
        return (false);
    }
  
    return (true);
};


function hardwareValidate(){
      var hardware= document.softwareForm.hardware;
    
    
    if (hardware.value != null && (hardware.value != "")) {
        if(hardware.value.replace(/^\s+|\s+$/g,"").length>150){
            
            str = new String(document.softwareForm.hardware.value);
            document.softwareForm.hardware.value=str.substring(0,150);
            
            alert("The hardware must be less than 150 characters");
               
              }
       document.softwareForm.hardware.focus();
        return (false);
    }
  
    return (true);
};

function softwaresValidate(){
      var softwares= document.softwareForm.softwares;
    
    
    if (softwares.value != null && (softwares.value != "")) {
        if(softwares.value.replace(/^\s+|\s+$/g,"").length>150){
            
              str = new String(document.softwareForm.softwares.value);
             document.softwareForm.softwares.value=str.substring(0,150);
            
            alert("The softwares must be less than 150 characters");
            
                }
       document.softwareForm.softwares.focus();
        return (false);
    }
  
    return (true);
};


function databasesValidate(){
      var databases= document.softwareForm.databases;
    
    
    if (databases.value != null && (databases.value != "")) {
        if(databases.value.replace(/^\s+|\s+$/g,"").length>150){
            
              str = new String(document.softwareForm.databases.value);
             document.softwareForm.databases.value=str.substring(0,150);    
            
            alert("The databases must be less than 150 characters");
           
              }
       document.softwareForm.databases.focus();
        return (false);
    }
  
    return (true);
};