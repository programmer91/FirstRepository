function homeAddLine1Validate(){
    
     var homeAddLine1 = document.employeeAddForm.homeAddLine1;

 
    if (homeAddLine1.value != null && (homeAddLine1.value != "")) {
        if(homeAddLine1.value.replace(/^\s+|\s+$/g,"").length>50){
            
             str = new String(document.employeeAddForm.homeAddLine1.value);
           document.employeeAddForm.homeAddLine1.value=str.substring(0,50);
           
            alert("The homeAddLine1 must be less than 50 characters");
            
              
           
              }
       document.employeeAddForm.homeAddLine1.focus();
        return (false);
    }
  
    return (true);
};

function homeAddLine2Validate(){
    var homeAddLine2 = document.employeeAddForm.homeAddLine2;

  
    if (homeAddLine2.value != null && (homeAddLine2.value != "")) {
        if(homeAddLine2.value.replace(/^\s+|\s+$/g,"").length>50){
            
             str = new String(document.employeeAddForm.homeAddLine2.value);
           document.employeeAddForm.homeAddLine2.value=str.substring(0,50);
           
            alert("The homeAddLine2 must be less than 50 characters");
            
          
           
              }
       document.employeeAddForm.homeAddLine2.focus();
        return (false);
    }
  
    return (true);
};

function homeCityValidate(){
    var homeCity = document.employeeAddForm.homeCity;
  
    if (homeCity.value != null && (homeCity.value != "")) {
        if(homeCity.value.replace(/^\s+|\s+$/g,"").length>25){
            
             str = new String(document.employeeAddForm.homeCity.value);
           document.employeeAddForm.homeCity.value=str.substring(0,25);
           
            alert("The homeCity must be less than 25 characters");
            
          
           
              }
       document.employeeAddForm.homeCity.focus();
        return (false);
    }
  
    return (true);
};

function homeZipValidate(){
    var homeZip = document.employeeAddForm.homeZip;

    if (homeZip.value != null && (homeZip.value != "")) {
        if(homeZip.value.replace(/^\s+|\s+$/g,"").length>15){
            
             str = new String(document.employeeAddForm.homeZip.value);
           document.employeeAddForm.homeZip.value=str.substring(0,15);
           
            alert("The homeZip must be less than 15 characters");
            
         
           
              }
       document.employeeAddForm.homeZip.focus();
        return (false);
    }
  
    return (true);
};

function payrollAddLine1Validate(){
    var payrollAddLine1= document.employeeAddForm.payrollAddLine1;
  
    if (payrollAddLine1.value != null && (payrollAddLine1.value != "")) {
        if(payrollAddLine1.value.replace(/^\s+|\s+$/g,"").length>50){
            
              str = new String(document.employeeAddForm.payrollAddLine1.value);
           document.employeeAddForm.payrollAddLine1.value=str.substring(0,50);
           
            alert("The payrollAddLine1 must be less than 50 characters");
            
          
           
              }
       document.employeeAddForm.payrollAddLine1.focus();
        return (false);
    }
  
    return (true);
};

function payrollAddLine2Validate(){
     var payrollAddLine2= document.employeeAddForm.payrollAddLine2;
  
    if (payrollAddLine2.value != null && (payrollAddLine2.value != "")) {
        if(payrollAddLine2.value.replace(/^\s+|\s+$/g,"").length>50){
            
            str = new String(document.employeeAddForm.payrollAddLine2.value);
           document.employeeAddForm.payrollAddLine2.value=str.substring(0,50);
           
            alert("The payrollAddLine2 must be less than 50 characters");
            
            
           
              }
       document.employeeAddForm.payrollAddLine2.focus();
        return (false);
    }
  
    return (true);
};

function payrollCityValidate(){
     var payrollCity= document.employeeAddForm.payrollCity;
   
    if (payrollCity.value != null && (payrollCity.value != "")) {
        if(payrollCity.value.replace(/^\s+|\s+$/g,"").length>25){
            
            str = new String(document.employeeAddForm.payrollCity.value);
           document.employeeAddForm.payrollCity.value=str.substring(0,25);
           
            alert("The payrollCity must be less than 25 characters");
            
        
           
              }
       document.employeeAddForm.payrollCity.focus();
        return (false);
    }
  
    return (true);
};

function payrollZipValidate(){
     var payrollZip= document.employeeAddForm.payrollZip;
  
    if (payrollZip.value != null && (payrollZip.value != "")) {
        if(payrollZip.value.replace(/^\s+|\s+$/g,"").length>15){
            
             str = new String(document.employeeAddForm.payrollZip.value);
           document.employeeAddForm.payrollZip.value=str.substring(0,15);    
            
            alert("The payrollZip must be less than 15 characters");
            
          
           
              }
       document.employeeAddForm.payrollZip.focus();
        return (false);
    }
  
    return (true);
};
 
 function cprojectAddLine1Validate(){
      var cprojectAddLine1= document.employeeAddForm.cprojectAddLine1;
  
    if (cprojectAddLine1.value != null && (cprojectAddLine1.value != "")) {
        if(cprojectAddLine1.value.replace(/^\s+|\s+$/g,"").length>50){
            
              str = new String(document.employeeAddForm.cprojectAddLine1.value);
           document.employeeAddForm.cprojectAddLine1.value=str.substring(0,50);
            
            alert("The cprojectAddLine1 must be less than 50 characters");
            
            
           
              }
       document.employeeAddForm.cprojectAddLine1.focus();
        return (false);
    }
  
    return (true);
};

function cprojectAddLine2Validate(){
     var cprojectAddLine2= document.employeeAddForm.cprojectAddLine2;
  
    if (cprojectAddLine2.value != null && (cprojectAddLine2.value != "")) {
        if(cprojectAddLine2.value.replace(/^\s+|\s+$/g,"").length>50){
            
              str = new String(document.employeeAddForm.cprojectAddLine2.value);
           document.employeeAddForm.cprojectAddLine2.value=str.substring(0,50);
            
            alert("The cprojectAddLine2 must be less than 50 characters");
            
            
           
              }
       document.employeeAddForm.cprojectAddLine2.focus();
        return (false);
    }
  
    return (true);
};

function cprojectCityValidate(){
     var cprojectCity= document.employeeAddForm.cprojectCity;
   
    if (cprojectCity.value != null && (cprojectCity.value != "")) {
        if(cprojectCity.value.replace(/^\s+|\s+$/g,"").length>25){
            
           str = new String(document.employeeAddForm.cprojectCity.value);
           document.employeeAddForm.cprojectCity.value=str.substring(0,25);
            
            alert("The cprojectCity must be less than 25 characters");
            
              
           
              }
       document.employeeAddForm.cprojectCity.focus();
        return (false);
    }
  
    return (true);
};

function cprojectZipValidate(){
     var cprojectZip= document.employeeAddForm.cprojectZip;
 
    if (cprojectZip.value != null && (cprojectZip.value != "")) {
        if(cprojectZip.value.replace(/^\s+|\s+$/g,"").length>15){
            
      str = new String(document.employeeAddForm.cprojectZip.value);
      document.employeeAddForm.cprojectZip.value=str.substring(0,15);          
      
            alert("The cprojectZip must be less than 15 characters");
            
         
           
              }
       document.employeeAddForm.cprojectZip.focus();
        return (false);
    }
  
    return (true);
};

function hotelAddLine1Validate(){
     var hotelAddLine1= document.employeeAddForm.hotelAddLine1;
   
    if (hotelAddLine1.value != null && (hotelAddLine1.value != "")) {
        if(hotelAddLine1.value.replace(/^\s+|\s+$/g,"").length>50){
            
             str = new String(document.employeeAddForm.hotelAddLine1.value);
           document.employeeAddForm.hotelAddLine1.value=str.substring(0,50);
            
            alert("The hotelAddLine1 must be less than 50 characters");
            
            
           
              }
       document.employeeAddForm.hotelAddLine1.focus();
        return (false);
    }
  
    return (true);
};

function hotelAddLine2Validate(){
     var hotelAddLine2= document.employeeAddForm.hotelAddLine2;
  
    if (hotelAddLine2.value != null && (hotelAddLine2.value != "")) {
        if(hotelAddLine2.value.replace(/^\s+|\s+$/g,"").length>50){
            
            str = new String(document.employeeAddForm.hotelAddLine2.value);
           document.employeeAddForm.hotelAddLine2.value=str.substring(0,50);
           
            alert("The hotelAddLine2 must be less than 50 characters");
            
            
           
              }
       document.employeeAddForm.hotelAddLine2.focus();
        return (false);
    }
  
    return (true);
};

function hotelCityValidate(){
     var hotelCity= document.employeeAddForm.hotelCity;
   
    if (hotelCity.value != null && (hotelCity.value != "")) {
        if(hotelCity.value.replace(/^\s+|\s+$/g,"").length>25){
            
           str = new String(document.employeeAddForm.hotelCity.value);
           document.employeeAddForm.hotelCity.value=str.substring(0,25);
            
            alert("The hotelCity must be less than 25 characters");
            
         
           
              }
       document.employeeAddForm.hotelCity.focus();
        return (false);
    }
  
    return (true);
};

function hotelZipValidate(){
     var hotelZip= document.employeeAddForm.hotelZip;
   
    if (hotelZip.value != null && (hotelZip.value != "")) {
        if(hotelZip.value.replace(/^\s+|\s+$/g,"").length>15){
            
           str = new String(document.employeeAddForm.hotelZip.value);
           document.employeeAddForm.hotelZip.value=str.substring(0,15);
            
            alert("The hotelZip must be less than 15 characters");
            
         
           
              }
       document.employeeAddForm.hotelZip.focus();
        return (false);
    }
  
    return (true);
};

function offShoreAddLine1Validate(){
     var offShoreAddLine1= document.employeeAddForm.offShoreAddLine1;
 
    if (offShoreAddLine1.value != null && (offShoreAddLine1.value != "")) {
        if(offShoreAddLine1.value.replace(/^\s+|\s+$/g,"").length>50){
            
           str = new String(document.employeeAddForm.offShoreAddLine1.value);
           document.employeeAddForm.offShoreAddLine1.value=str.substring(0,50);
            
            alert("The offShoreAddLine1 must be less than 50 characters");
            
            
              }
       document.employeeAddForm.offShoreAddLine1.focus();
        return (false);
    }
  
    return (true);
};

function offShoreAddLine2Validate(){
      var offShoreAddLine2= document.employeeAddForm.offShoreAddLine2;
   
    if (offShoreAddLine2.value != null && (offShoreAddLine2.value != "")) {
        if(offShoreAddLine2.value.replace(/^\s+|\s+$/g,"").length>50){
            
              str = new String(document.employeeAddForm.offShoreAddLine2.value);
           document.employeeAddForm.offShoreAddLine2.value=str.substring(0,50);
            
            alert("The offShoreAddLine2 must be less than 50 characters");
            
        
           
              }
       document.employeeAddForm.offShoreAddLine2.focus();
        return (false);
    }
  
    return (true);
};

function offShoreCityValidate(){
      var offShoreCity= document.employeeAddForm.offShoreCity;
  
    if (offShoreCity.value != null && (offShoreCity.value != "")) {
        if(offShoreCity.value.replace(/^\s+|\s+$/g,"").length>25){
            
            str = new String(document.employeeAddForm.offShoreCity.value);
           document.employeeAddForm.offShoreCity.value=str.substring(0,25);
            
            alert("The offShoreCityy must be less than 25 characters");
            
              
           
              }
       document.employeeAddForm.offShoreCity.focus();
        return (false);
    }
  
    return (true);
};

function offShoreZipValidate(){
      var offShoreZip= document.employeeAddForm.offShoreZip;
  
    if (offShoreZip.value != null && (offShoreZip.value != "")) {
        if(offShoreZip.value.replace(/^\s+|\s+$/g,"").length>15){
            
           str = new String(document.employeeAddForm.offShoreZip.value);
           document.employeeAddForm.offShoreZip.value=str.substring(0,15);    
            
            alert("The offShoreZip must be less than 15 characters");
            
          
           
              }
       document.employeeAddForm.offShoreZip.focus();
        return (false);
    }
  
    return (true);
};

function otherAddLine1Validate(){
      var otherAddLine1= document.employeeAddForm.otherAddLine1;
    
    if (otherAddLine1.value != null && (otherAddLine1.value != "")) {
        if(otherAddLine1.value.replace(/^\s+|\s+$/g,"").length>50){
            
              str = new String(document.employeeAddForm.otherAddLine1.value);
           document.employeeAddForm.otherAddLine1.value=str.substring(0,50);
            
            alert("The otherAddLine1 must be less than 50 characters");
            
          
           
              }
       document.employeeAddForm.otherAddLine1.focus();
        return (false);
    }
  
    return (true);
};

function otherAddLine2Validate(){
     var otherAddLine2= document.employeeAddForm.otherAddLine2;
   
    if (otherAddLine2.value != null && (otherAddLine2.value != "")) {
        if(otherAddLine2.value.replace(/^\s+|\s+$/g,"").length>50){
            
               str = new String(document.employeeAddForm.otherAddLine2.value);
           document.employeeAddForm.otherAddLine2.value=str.substring(0,50);
            
            alert("The otherAddLine2 must be less than 50 characters");
            
             
           
              }
       document.employeeAddForm.otherAddLine2.focus();
        return (false);
    }
  
    return (true);
};

function otherCityValidate(){
     var otherCity= document.employeeAddForm.otherCity;
   
 
    if (otherCity.value != null && (otherCity.value != "")) {
        if(otherCity.value.replace(/^\s+|\s+$/g,"").length>25){
            
           str = new String(document.employeeAddForm.otherCity.value);
           document.employeeAddForm.otherCity.value=str.substring(0,25);
            
            alert("The otherCity must be less than 25 characters");
            
             
           
              }
       document.employeeAddForm.otherCity.focus();
        return (false);
    }
  
    return (true);
};

function otherZipValidate(){
     var otherZip= document.employeeAddForm.otherZip;
   
  
    if (otherZip.value != null && (otherZip.value != "")) {
        if(otherZip.value.replace(/^\s+|\s+$/g,"").length>15){
            
           str = new String(document.employeeAddForm.otherZip.value);
           document.employeeAddForm.otherZip.value=str.substring(0,15);      
            
            alert("The otherZip must be less than 15 characters");
            
          
           
              }
       document.employeeAddForm.otherZip.focus();
        return (false);
    }
  
    return (true);
};




         
      
        