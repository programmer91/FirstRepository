function readyStateHandlerText(req,responseTextHandler){
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {               
                responseTextHandler(req.responseText);
            } else {
                alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }
    }
}  

/**
 * To get the leave details in pop
 */

function getLeaveDetails(id,empName){
   //alert("id-->"+id+"----------- empname"+empName);
   var req = newXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateLeaveDetails); 

    var url=CONTENXT_PATH+"/popupLeaveDetailsWindow.action?leaveId="+id+"&empName="+empName;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
   

}

function getRecruiterDetails(Recruiter) {
    
    var test = Recruiter;
//alert("REC--->"+Recruiter);
    //alert(test.substr(0,1));
    //alert(indexOf("."));
    // alert(test.substr(test.indexOf(".")+1,test.length));
    var loginId=(test.substr(0,1)+test.substr(test.indexOf(".")+1,test.length));
    //alert("loginId"+loginId);
    var aId = Recruiter;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populateReqPersonSkills);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/popupReqRecruiterWindow.action?recruiterId="+loginId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function populateLeaveDetails(text) {
    var background = "#3E93D4";
    var title = "Employee Details";
    var text1 = text; 
    var size = text1.length;
    
    // alert("text "+text1);
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'>"+text1+"<br />\
    </body></html>";
    
    //alert("text1"+text1);
       
       var str= text1;
       var strArray=str.split("^");

    // alert("size "+content.length);
    var indexof=(content.indexOf("^")+1);
    var lastindexof=(content.lastIndexOf("^"));
    //alert("indexof"+indexof+"-------"+"lastindexof"+lastindexof);
    
    popup = window.open("","window","channelmode=0,width=400,height=250,top=200,left=350,scrollbars=yes,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
    if(content.indexOf("^")){
        popup.document.write("<b>Employee Name : </b>"+content.substr(0,content.indexOf("^")));
        popup.document.write("<br><br>");
        popup.document.write("<b>Reports To : </b>"+strArray[3]);
        popup.document.write("<br><br>");
        popup.document.write("<b>Start Date : </b>"+strArray[1]);
        popup.document.write("<br><br>");
        popup.document.write("<b>End Date : </b>"+strArray[2]);
        popup.document.write("<br><br>");
        popup.document.write("<b>Leave Type : </b>"+strArray[4]);
        popup.document.write("<br><br>");
        popup.document.write("<b>Reason : </b>"+strArray[5]);
        popup.document.write("<br><br>");
        popup.document.write("<b>Leave Status :</b>"+content.substr((content.lastIndexOf("^")+1),content.length));
        
    }//Write content into it.  
    //Write content into it.    
    
    
    
}

function getReason(text) {
//alert(text);
    var background = "#3E93D4";
    var title = "Leave Details";
    var text1 = "^"+text+"^"; 
//alert(text1);
    var size = text1.length;
    
   
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'>"+text1+"<br />\
    </body></html>";
   var str= text1;
       var strArray=str.split("^");

var indexof=(content.indexOf("^")+1);
    var lastindexof=(content.lastIndexOf("^"));

   // popup = window.open("","window","channelmode=0,width=400,height=100,top=200,left=350,scrollbars=yes,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
   popup = window.open("","window","channelmode=0,width=400,height=150,top=200,left=350,scrollbars=yes,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
         if(content.indexOf("^")){
        popup.document.write("<b>Reason : </b>"+content.substr(0,content.indexOf("^"))+text);
        popup.document.write("<br><br>"+content.substr((content.lastIndexOf("^")+1),content.length));
        
  }
    
    
    
}




