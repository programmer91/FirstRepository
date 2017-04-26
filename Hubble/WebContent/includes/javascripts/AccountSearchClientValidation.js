function accountNameValidate(){
      var accountName= document.frmSearch.accountName;
    
    
    if (accountName.value != null && (accountName.value != "")) {
        if(accountName.value.replace(/^\s+|\s+$/g,"").length>60){
            alert("The accountName must be less than 60 characters");
            
              str = new String(document.frmSearch.accountName.value);
             document.frmSearch.accountName.value=str.substring(0,60);
           
              }
       document.frmSearch.accountName.focus();
        return (false);
    }
  
    return (true);
};


function descriptionValidate(){
      var description= document.frmSearch.description;
    
    
    if (description.value != null && (description.value != "")) {
        if(description.value.replace(/^\s+|\s+$/g,"").length>255){
            alert("The description must be less than 255 characters");
            
              str = new String(document.frmSearch.description.value);
             document.frmSearch.description.value=str.substring(0,255);
           
              }
       document.frmSearch.description.focus();
        return (false);
    }
  
    return (true);
};