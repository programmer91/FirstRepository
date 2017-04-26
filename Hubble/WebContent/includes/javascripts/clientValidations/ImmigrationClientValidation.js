      
   function passportNoValidate() {
         var passportNo= document.immigrationForm.passportNo;
  
    if (passportNo.value != null && (passportNo.value != "")) {
        if(passportNo.value.replace(/^\s+|\s+$/g,"").length>20){
            
             str = new String(document.immigrationForm.passportNo.value);
           document.immigrationForm.passportNo.value=str.substring(0,20);
            
             alert("The passportNo must be less than 20 characters");
          
                
        }
        document.immigrationForm.passportNo.focus();
        return (false);
    }
     return (true);
};

function passportIssuedAtValidate() {
       var passportIssuedAt= document.immigrationForm.passportIssuedAt;
    
    
    if (passportIssuedAt.value != null && (passportIssuedAt.value != "")) {
        if(passportIssuedAt.value.replace(/^\s+|\s+$/g,"").length>50){
            
            str = new String(document.immigrationForm.passportIssuedAt.value);
           document.immigrationForm.passportIssuedAt.value=str.substring(0,50); 
            
             alert("The passportIssuedAt must be less than 50 characters");
          
       
             
        }
        document.immigrationForm.passportIssuedAt.focus();
        return (false);
    }
   
    return (true);
};

function portOfEntryValidate() {
      var portOfEntry= document.immigrationForm.portOfEntry;
    
  
    if (portOfEntry.value != null && (portOfEntry.value != "")) {
        if(portOfEntry.value.replace(/^\s+|\s+$/g,"").length>30){
            
       str = new String(document.immigrationForm.portOfEntry.value);
           document.immigrationForm.portOfEntry.value=str.substring(0,30);
            
             alert("The portOfEntry must be less than 30 characters");
           
             
        }
        document.immigrationForm.portOfEntry.focus();
        return (false);
    }
       return (true);
};

function i94NoValidate() {
     var i94No= document.immigrationForm.i94No;
    
   
    if (i94No.value != null && (i94No.value != "")) {
        if(i94No.value.replace(/^\s+|\s+$/g,"").length>30){
            
              str = new String(document.immigrationForm.i94No.value);
           document.immigrationForm.i94No.value=str.substring(0,30);  
            
             alert("The i94No must be less than 30 characters");
           
           }
        document.immigrationForm.i94No.focus();
        return (false);
    }
     return (true);
};

function ptUniversityValidate() {
      var ptUniversity= document.immigrationForm.ptUniversity;
    
    if (ptUniversity.value != null && (ptUniversity.value != "")) {
        if(ptUniversity.value.replace(/^\s+|\s+$/g,"").length>40){
            
               str = new String(document.immigrationForm.ptUniversity.value);
           document.immigrationForm.ptUniversity.value=str.substring(0,40);
            
             alert("The ptUniversity must be less than 40 characters");
            
           }
        document.immigrationForm.ptUniversity.focus();
        return (false);
    }
     return (true);
};

function h1LinValidate() {
      var h1Lin= document.immigrationForm.h1Lin;
    
  
    if (h1Lin.value != null && (h1Lin.value != "")) {
        if(h1Lin.value.replace(/^\s+|\s+$/g,"").length>50){
            
               str = new String(document.immigrationForm.h1Lin.value);
           document.immigrationForm.h1Lin.value=str.substring(0,50);  
            
             alert("The h1Lin must be less than 50 characters");
            
           }
        document.immigrationForm.h1Lin.focus();
        return (false);
    }
     return (true);
};

function h1LcaEtaNoValidate() {
        var h1LcaEtaNo= document.immigrationForm.h1LcaEtaNo;
    
 
    if (h1LcaEtaNo.value != null && (h1LcaEtaNo.value != "")) {
        if(h1LcaEtaNo.value.replace(/^\s+|\s+$/g,"").length>15){
            
             str = new String(document.immigrationForm.h1LcaEtaNo.value);
           document.immigrationForm.h1LcaEtaNo.value=str.substring(0,15);
            
             alert("The h1LcaEtaNo must be less than 15 characters");
          
           }
        document.immigrationForm.h1LcaEtaNo.focus();
        return (false);
    }
     return (true);
};

function h1TitleValidate() {
      var h1Title= document.immigrationForm.h1Title;
    
  
    if (h1Title.value != null && (h1Title.value != "")) {
        if(h1Title.value.replace(/^\s+|\s+$/g,"").length>50){
            
               str = new String(document.immigrationForm.h1Title.value);
           document.immigrationForm.h1Title.value=str.substring(0,50);   
            
             alert("The h1Title must be less than 50 characters");
             
           }
        document.immigrationForm.h1Title.focus();
        return (false);
    }
     return (true);
};

function queryCommentsValidate() {
    var queryComments= document.immigrationForm.queryComments;
    
  
    if (queryComments.value != null && (queryComments.value != "")) {
        if(queryComments.value.replace(/^\s+|\s+$/g,"").length>255){
            
            str = new String(document.immigrationForm.queryComments.value);
           document.immigrationForm.queryComments.value=str.substring(0,255);
            
             alert("The queryComments must be less than 255 characters");
             
           }
        document.immigrationForm.queryComments.focus();
        return (false);
    }
     return (true);
};

function gcRefValidate() {
     var gcRef= document.immigrationForm.gcRef;

    if (gcRef.value != null && (gcRef.value != "")) {
        if(gcRef.value.replace(/^\s+|\s+$/g,"").length>50){
                     
             str = new String(document.immigrationForm.gcRef.value);
           document.immigrationForm.gcRef.value=str.substring(0,50);  
           
             alert("The gcRef must be less than 50 characters");
            
           }
        document.immigrationForm.gcRef.focus();
        return (false);
    }
     return (true);
};

function gcTitleValidate() {
        var gcTitle= document.immigrationForm.gcTitle;

    
    
    if (gcTitle.value != null && (gcTitle.value != "")) {
        if(gcTitle.value.replace(/^\s+|\s+$/g,"").length>50){
            
             str = new String(document.immigrationForm.gcTitle.value);
           document.immigrationForm.gcTitle.value=str.substring(0,50);
            
             alert("The gcTitle must be less than 50 characters");
             
           }
        document.immigrationForm.gcTitle.focus();
        return (false);
    }
     return (true);
};

function commentsValidate() {
     var comments= document.immigrationForm.comments;
    
  
    if (comments.value != null && (comments.value != "")) {
        if(comments.value.replace(/^\s+|\s+$/g,"").length>255){
            
           str = new String(document.immigrationForm.comments.value);
           document.immigrationForm.comments.value=str.substring(0,255);      
            
             alert("The comments must be less than 255 characters");
             
           }
        document.immigrationForm.comments.focus();
        return (false);
    }
     return (true);
}; 