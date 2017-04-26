function enableEnter(e) {
    var keynum;
    var keychar;
    var numcheck;
    
    if(window.event) {
        keynum = e.keyCode;
    }
    else if(e.which) // Netscape/Firefox/Opera
    {
        keynum = e.which;
    }
    try{
        if(keynum==13){
            load();
            return false;
        }
    }catch(e){
        alert("Error"+e);
    }
};




