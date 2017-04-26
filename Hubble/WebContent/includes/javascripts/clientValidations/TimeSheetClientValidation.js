// This is a javascript file
function txtNotesValidate(){
      var txtNotes= document.frmAddTimeSheet.txtNotes;
    
    
    if (txtNotes.value != null && (txtNotes.value != "")) {
        if(txtNotes.value.replace(/^\s+|\s+$/g,"").length>255){
            
             str = new String(document.frmAddTimeSheet.txtNotes.value);
             document.frmAddTimeSheet.txtNotes.value=str.substring(0,255);
             
            alert("The Notes must be less than 255 characters");
            
                 }
       document.frmAddTimeSheet.txtNotes.focus();
        return (false);
    }
  
    return (true);
};