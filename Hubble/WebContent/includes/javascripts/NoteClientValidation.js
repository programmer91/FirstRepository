

function noteValidate(){
      var note= document.noteForm.note;
    
    
    if (note.value != null && (note.value != "")) {
        if(note.value.replace(/^\s+|\s+$/g,"").length>1000){
            
                str = new String(document.noteForm.note.value);
            document.noteForm.note.value=str.substring(0,1000);
            
            alert("The note must be less than 1000 characters");
            
       
           
              }
       document.noteForm.note.focus();
        return (false);
    }
  
    return (true);
};
