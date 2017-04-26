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
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populate);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/popupWindow.action?activityId="+aId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function doGetComments(Id) {
    var aId = Id;
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populateComments);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/popupCommentsWindow.action?activityId="+aId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function getSkills(Id) {
    var aId = Id;
    var req = new XMLHttpRequest();
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
    var req = new XMLHttpRequest();
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
    }else{
         //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}




function getRequirementSkills(RequirementId) {
    //  alert("hi");
    var aId = RequirementId;
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populateRequirementSkills);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/popupReqSkillsWindow.action?requirementId="+aId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

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
            //alert(content.substr(0,content.indexOf("//")));
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
            //alert(content.substr(0,content.indexOf("//")));


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
            //alert(content.substr(0,content.indexOf("//")));


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
            //alert(content.substr(0,content.indexOf("//")));


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

// New Function for Comments 

function getIssueComments(Id) {
//alert("hi-->"+Id);
    var aId = Id;
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populateIssueComments);    
   
    var url=CONTENXT_PATH+"/popupIssueCommentsWindow.action?issueId="+aId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateIssueComments(text) {
    var background = "#3E93D4";
    var title = "Issue Comments";
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


//New function for geeting task Description


function getIssueDescription(Id) {
    //alert("hi-->"+Id);
    var aId = Id;
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populateDescriptionComments);    
   
    var url=CONTENXT_PATH+"/popupTasksDescWindow.action?issueId="+aId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateDescriptionComments(text) {
    var background = "#3E93D4";
    var title = "Task Description";
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
    } else {
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}


//new function to populate Exam Name:

function getExamName(TopicId) {
   
    var eId = TopicId;
     //alert("Exam Name---->"+eId);
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populateExamName);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/popupWindowForExamName.action?activityId="+eId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function populateExamName(text) {
    //alert("hiii---->"+text);
    //alert("text--->"+text);
    var background = "#3E93D4";
    var title = "Exam Name";
    var text1 = text; 
    var size = text1.length;
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+text1+"<br />\
    </body></html>";
    
    
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=200,height=100,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
         
    
}

function populateNotesComments(text) {
    var background = "#3E93D4";
    var title = "Notes Comments";
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
    }else{
         //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}


/*Opportunity Comments
 * 
 * 
 */

function getOpportunityDescription(Id) {
    var aId = Id;
    //alert(Id);
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populateOpportunityDescription);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/popupOpportunityDescription.action?opportunityId="+Id;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function populateOpportunityDescription(text) {
    var background = "#3E93D4";
    var title = "Opportunity Description";
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
    }else{
         //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}



/*
 * Account Details
 * Date : 04/20/2016
 * Author : Santosh Kola
 */




function getAccountDetailsPopup(accountId) {
    
    //alert(Id);
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populateAccountDescription);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/getAccountDetailsPopup.action?accountId="+accountId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function populateAccountDescription(text) {
    //alert(text)
    var background = "#3E93D4";
    var title = "Account Details";
    var text1 = text; 
    
    var strArray=text1.split("^");
    var strAccArray=strArray[0].split("!");
    var strAccTeamArray=strArray[1].split("!");
    var accountName = strAccArray[0];
    var city = strAccArray[1];
    var state = strAccArray[2];
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head><body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>";
     content=content+"<b>Account Name:</b>"+accountName+"<br>";
     content=content+"<b>City:</b>"+city+"<br>";
     content=content+"<b>State:</b>"+state+"<br>";
    
    content=content+"<h4>AccountTeam</h4>";
    var acctable="<table border=1><tr><th>EmployeeName</th><th>Email</th><th>Workphone</th></tr>";
    
    for (i = 0; i < strAccTeamArray.length-1; i++) {
        var empdata=strAccTeamArray[i].split(",");
        acctable =acctable+"<tr><td>"+empdata[0]+"</td><td>"+empdata[1]+"</td><td>"+empdata[2]+"</td></tr>";
    }
    acctable=acctable+"</table>";
    content=content+acctable ;

   content=content+" </body></html>";
    var size = content.length;
   // alert(size)
    if(size < 50){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 100){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=500,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 260){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=500,height=300,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }else{
         //Create the popup       
        popup = window.open("","window","channelmode=0,width=500,height=500,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}


 function getOppurtunityDetailsPopup(oppurtunityAccountId) {
    
   // alert(oppurtunityAccountId);
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populateOppurtunityAccountDescription);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/getOppurtunityDetailsPopup.action?opportunityId="+oppurtunityAccountId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}



function populateOppurtunityAccountDescription(text) {
    var background = "#3E93D4";
    var title = "Oppurtunity Details";
    var text1 = text; 
   // alert(text);
    var strArray=text1.split("*@!");
    var strAccArray=strArray[0].split("#^$");
    var strAccTeamArray=strArray[1].split("#^$");
    var oppurtunityTitle = strAccArray[0];
    var state = strAccArray[1];
    var practice = strAccArray[2];
    var type = strAccArray[3];
    var stage = strAccArray[4];
    var insidesales = strAccArray[5];
    var duedate = strAccArray[6];
    var value = strAccArray[7];
    var description = strAccArray[8];
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head><body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>";
     content=content+"<b>oppurtunity Title:nbps&nbsp</b>"+oppurtunityTitle+"<br>";
     content=content+"<b>State:&nbsp</b>"+state+"<br>";
     content=content+"<b>Practice:&nbsp</b>"+practice+"<br>";
     content=content+"<b>Type:&nbsp</b>"+type+"<br>";
     content=content+"<b>Stage:&nbsp</b>"+stage+"<br>";
     content=content+"<b>Inside Sales:&nbsp</b>"+insidesales+"<br>";
     content=content+"<b>Due Date:&nbsp</b>"+duedate+"<br>";
     content=content+"<b>Value Of Oppurtunity:&nbsp</b>"+value+"<br>";
     content=content+"<b>Description:&nbsp</b>"+description+"<br>";
   content=content+" </body></html>";
    var size = content.length;
   // alert(size)
    if(size < 50){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    
    else if(size < 100){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=500,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 260){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=500,height=300,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }else{
         //Create the popup       
        popup = window.open("","window","channelmode=0,width=500,height=300,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}

	
function populateDescription(text) {
    var background = "#3E93D4";
    var title = "Description";
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
    }else{
         //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}
