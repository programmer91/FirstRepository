// This is a javascript file
/*Don't Alter these Methods*/

var popup;

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


function doGetAll(Id) {
    
    var aId = Id;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populate);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/popupWindow.action?activityId="+aId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function doGetComments(Id) {
    var aId = Id;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populateComments);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/popupCommentsWindow.action?activityId="+aId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function getSkills(Id) {
    var aId = Id;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populateSkills);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/popupSkillsWindow.action?consultantId="+aId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateSkills(text) {
    var background = "#3E93D4";
    var title = "Consultant Skillset";
    var text1 = text; 
    var size = text1.length;
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+text1+"<br />\
    </body></html>";
    
    if(size < 50){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 100){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 260){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}

function populate(text) {
    var background = "#3E93D4";
    var title = "ActivityDescription";
    var text1 = text;        
    var size = text1.length;
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+text1+"<br />\
    </body></html>";
    
    if(size < 50){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 100){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 260){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}



function closepopUpWindow(){
    popup.close();
}  


function getComments(Id) {
    var aId = Id;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populate1);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/popupWindowForComments.action?activityId="+aId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}



function populate1(text) {
    var background = "#3E93D4";
    var title = "Comments";
    var text1 = text;        
    var size = text1.length;
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+text1+"<br />\
    </body></html>";
    
    if(size < 50){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 100){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 260){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}

function populateComments(text) {
    var background = "#3E93D4";
    var title = "Activity Comments";
    var text1 = text; 
    var size = text1.length;
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+text1+"<br />\
    </body></html>";
    
    if(size < 50){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 100){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 260){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}




function getRequirementSkills(RequirementId) {
    //  alert("hi");
    var aId = RequirementId;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populateRequirementSkills);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/popupReqSkillsWindow.action?requirementId="+aId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

/*function populateRequirementSkills(text) {
    var background = "#3E93D4";
    var title = "Skillset / Description";
    var text1 = text; 
    var size = text1.length;
    
    // alert("text "+text1);
    //alert("size "+size);
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'>"+text1+"<br />\
    </body></html>";
    
    
    if(size < 50){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        if(content.indexOf("^")){
            //alert(content.substr(0,content.indexOf("//")));
            popup.document.write("<b>Skills     : </b>"+content.substr(0,content.indexOf("^")));
            popup.document.write("<br><br>");
            popup.document.write("<b>Decription :</b>"+content.substr((content.indexOf("^")+1),size));
        }//Write content into it.  
        //Write content into it.    
    }
    
    else if(size < 100){
        
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        if(content.indexOf("^")){
            // alert("I am in IF #");
            //alert(content.substr(0,content.indexOf("//")));
            popup.document.write("<b>Skills     : </b>"+content.substr(0,content.indexOf("^")));
            popup.document.write("<br><br>");
            popup.document.write("<b>Decription :</b>"+content.substr((content.indexOf("^")+1),size));
        }//Write content into it.     
    }
    
    else if(size < 260){
        //alert("length");
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        if(content.indexOf("^")){
            //alert(content.substr(0,content.indexOf("//")));
            popup.document.write("<b>Skills     : </b>"+content.substr(0,content.indexOf("^")));
            popup.document.write("<br><br>");
            popup.document.write("<b>Decription :</b>"+content.substr((content.indexOf("^")+1),size));
        }//Write content into it.    
    }
    
    else{
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=600,height=400,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        if(content.indexOf("^")){
            //alert(content.substr(0,content.indexOf("//")));
            popup.document.write("<b>Skills     : </b>"+content.substr(0,content.indexOf("^")));
            popup.document.write("<br><br>");
            popup.document.write("<b>Decription :</b>"+content.substr((content.indexOf("^")+1),size));
        }//Write content into it.  
    }
}*/

//modifeid
 function populateRequirementSkills(text) {

     //alert("skills1234-----"+text);
    var background = "#3E93D4";
    var title = "Skillset / Description";
    var text1 = text; 
    var size = text1.length;

    var text2 = text.split("^");
    //alert("created by"+text2[2]);
    
    var n=text2[1];

    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'>"+text1+"<br />\
    </body></html>";

    if(size < 50){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    

      if(content.indexOf("^")){
            //alert(content.substr(0,content.indexOf("/)));
            popup.document.write("<b>Skills     : </b>"+content.substr(0,content.indexOf("^")));

            if(n!="null"){
            popup.document.write("<br><br>");
            popup.document.write("<b>Decription :</b>"+n);
            }

            popup.document.write("<br><br>");
            popup.document.write("<b>Created By  :</b>"+content.substr((content.lastIndexOf("^")+1),size));

        }//Write content into it. 



    }
    
    else if(size < 100){
        
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
       if(content.indexOf("^")){
            // alert("I am in IF #");
            //alert(content.substr(0,content.indexOf("/)));


             popup.document.write("<b>Skills     : </b>"+content.substr(0,content.indexOf("^")));
           
             if(n!="null"){
            popup.document.write("<br><br>");
            popup.document.write("<b>Decription :</b>"+n);
            }

            popup.document.write("<br><br>");
            popup.document.write("<b>Created By  :</b>"+content.substr((content.lastIndexOf("^")+1),size));


       

        }//Write content into it.  
        //New 

       
    }
    
    else if(size < 260){
        //alert("length");
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
     
         if(content.indexOf("^")){
            // alert("I am in IF #");
            //alert(content.substr(0,content.indexOf("/)));


             popup.document.write("<b>Skills     : </b>"+content.substr(0,content.indexOf("^")));
            
             if(n!="null"){
            popup.document.write("<br><br>");
            popup.document.write("<b>Decription :</b>"+n);
            }

            popup.document.write("<br><br>");
            popup.document.write("<b>Created By n :</b>"+content.substr((content.lastIndexOf("^")+1),size));

            }

    }
    
    else{
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=600,height=400,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
       
         if(content.indexOf("^")){
            // alert("I am in IF #");
            //alert(content.substr(0,content.indexOf("/)));


             popup.document.write("<b>Skills     : </b>"+content.substr(0,content.indexOf("^")));
            
             if(n!="null"){
            popup.document.write("<br><br>");
            popup.document.write("<b>Decription :</b>"+n);
            }

            popup.document.write("<br><br>");
            popup.document.write("<b>Created By  :</b>"+content.substr((content.lastIndexOf("^")+1),size));

            }

    }
}

