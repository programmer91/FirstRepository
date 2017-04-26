// This is a javascript file

function reasonValidate(){
    
   var reason= document.leaveForm.reason;
   
       
    if (reason.value != null && (reason.value != "")) {
        if(reason.value.replace(/^\s+|\s+$/g,"").length>500){
            
             str = new String(document.leaveForm.reason.value);
            document.leaveForm.reason.value=str.substring(0,500); 
            
            alert("The reason must be less than 500 characters");
            
        
           
              }
       document.leaveForm.reason.focus();
        return (false);
    }
  
    return (true);
};


