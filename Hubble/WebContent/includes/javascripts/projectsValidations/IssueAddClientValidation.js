function issueNamesValidate(){
      var issueNames= document.frmAccountAdd.issueNames;
    
    
    if (issueNames.value != null && (issueNames.value != "")) {
        if(issueNames.value.replace(/^\s+|\s+$/g,"").length>30){
            
              str = new String(document.frmAccountAdd.issueNames.value);
            document.frmAccountAdd.issueNames.value=str.substring(0,30);
            
            alert("The issueName must be less than 30 characters");
            
          
           
              }
       document.frmAccountAdd.issueNames.focus();
        return (false);
    }
  
    return (true);
};

function issueStatesValidate(){
      var issueStates= document.frmAccountAdd.issueStates;
    
    
    if (issueStates.value != null && (issueStates.value != "")) {
        if(issueStates.value.replace(/^\s+|\s+$/g,"").length>30){
            
              str = new String(document.frmAccountAdd.issueStates.value);
            document.frmAccountAdd.issueStates.value=str.substring(0,30);
            
            alert("The issueStatus must be less than 30 characters");
            
          
           
              }
       document.frmAccountAdd.issueStates.focus();
        return (false);
    }
  
    return (true);
};

function descriptionsValidate(){
      var descriptions= document.frmAccountAdd.descriptions;
    
    
    if (descriptions.value != null && (descriptions.value != "")) {
        if(descriptions.value.replace(/^\s+|\s+$/g,"").length>50){
            
              str = new String(document.frmAccountAdd.descriptions.value);
            document.frmAccountAdd.descriptions.value=str.substring(0,50);
            
            alert("The description must be less than 50 characters");
            
          
           
              }
       document.frmAccountAdd.descriptions.focus();
        return (false);
    }
  
    return (true);
};

