
/*
 *For Date tool tip in recruitment consultant search list
 *Ajay Tummapala
 */
function closepopUpWindow(){
   // popup.close();
   UnTip();
} 
function getCreatedDate(Id) {
    //alert("hello");
    var aId = Id;
    
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populateDate);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/popupWindowForCreatedDate.action?activityId="+aId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}



function populateDate(text) {
    //alert("hello");
    var background = "#3E93D4";
    var title = "Created Date";
    var text1 = text;        
    var size = text1.length;
    
    Tip("Date:"+text1);
    
}


function getModifiedDate(Id) {
    //alert("hello");
    var aId = Id;
    
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populateModifiedDate);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/popupWindowForModifiedDate.action?activityId="+aId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}



function populateModifiedDate(text) {
    //alert("hello");
   // alert(text);
    var background = "#3E93D4";
    var title = "Modified Date";
    var text1 = text;        
    var size = text1.length;
    Tip("Date:"+text1);
}