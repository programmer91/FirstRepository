var AjaxRoleId = "";

function getRequirementSkills(RequirementId) {
    var aId = RequirementId;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populateRequirementSkills);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/popupReqSkillsWindow.action?requirementId="+aId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

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


/*function populateRequirementSkills(text) {
    var background = "#3E93D4";
    var title = "Skillset / Description";
    var text1 = text; 
    var size = text1.length;
    
    
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

//Modified:

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


// Recruiter details Like name emil_Id phone number.

function getRecruiterDetails(Recruiter) {
    
    var test = Recruiter;
    //alert(test.substr(0,1));
    //alert("test --"+test);
    //alert(indexOf("."));
    // alert(test.substr(test.indexOf(".")+1,test.length));
    //var loginId=(test.substr(0,1)+test.substr(test.indexOf(".")+1,test.length));
      var loginId=Recruiter;
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


function populateReqPersonSkills(text) {
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
    // alert("size "+content.length);
    var indexof=(content.indexOf("^")+1);
    var lastindexof=(content.lastIndexOf("^"));
    
    popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
    if(content.indexOf("^")){
        //alert(content.substr(0,content.indexOf("//")));
        popup.document.write("<b>Employee Name : </b>"+content.substr(0,content.indexOf("^")));
        popup.document.write("<br><br>");
        popup.document.write("<b>Work Phone No :</b>"+content.substr((content.indexOf("^")+1),(lastindexof-indexof)));
        popup.document.write("<br><br>");
        popup.document.write("<b>Email Address :</b>"+content.substr((content.lastIndexOf("^")+1),content.length));
    }//Write content into it.  
    //Write content into it.    
    
    
    
}

//new  Requirement List ----------------------------------------------------------------------------------------


function getRequirementsList1() {
    
    AjaxRoleId = document.getElementById("roleTypeId").value;
   // alert("role Id in getREqList:"+AjaxRoleId);
    var req = newXMLHttpRequest();
     req.onreadystatechange = readyStateHandlerreq(req, displayReqListResult); 
    
      //alert("path ==="+CONTENXT_PATH);

    var url = CONTENXT_PATH+"/requirementAjaxList.action";
 
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

// search  ReqList

function getSearchReqList(){
       // alert("hi")
       AjaxRoleId = document.getElementById("roleTypeId").value;
   
   status=document.getElementById("status").value;
   createdBy=document.getElementById("createdBy").value;
   assignedTo=document.getElementById("assignedTo").value;
   title=document.getElementById("title").value;
   postedDate1=document.getElementById("postedDate1").value;
   postedDate2=document.getElementById("postedDate2").value;
assignedBy=document.getElementById("assignedBy").value;
   country=document.getElementById("country").value;
   practiceid=document.getElementById("practiceId").value;
 requirementId=document.getElementById("requirementId").value;
  state=document.getElementById("state").value;
  preSalesPerson=document.getElementById("preSalesPerson").value;
  clientId=document.getElementById("clientId").value;
  
   //alert("status1-->"+status);

//createdBy,assignedTo,title,postedDate1,postedDate2
//alert("Created BY-----------"+createdBy.value+"-------assign TO---"+assignedTo.value+"-----title----"+title.value+"----postdate1---"+postedDate1.value+"----postedDate2----"+postedDate2.value+"----roleId------"+roleTypeId.value)
         var req = newXMLHttpRequest();
     req.onreadystatechange = readyStateHandlerreq(req, displayReqListResult); 
    
      //alert("path ==="+CONTENXT_PATH);

     //var url = CONTENXT_PATH+"/searchRequirementAjaxList.action?createdBy="+createdBy.value+"&assignedTo="+assignedTo.value+"&title="+title.value+"&postedDate1="+postedDate1.value+"&postedDate2="+postedDate2.value+"&status="+status;
    //var url = CONTENXT_PATH+"/searchRequirementAjaxList.action?createdBy="+createdBy+"&assignedTo="+assignedTo+"&title="+title+"&postedDate1="+postedDate1+"&postedDate2="+postedDate2+"&status="+status;
  //var url = CONTENXT_PATH+"/searchRequirementAjaxList.action?createdBy="+createdBy+"&assignedTo="+assignedTo+"&title="+title+"&postedDate1="+postedDate1+"&postedDate2="+postedDate2+"&status="+status +"&assignedBy="+assignedBy+"&country="+country+"&practiceid="+practiceid+"&requirementId="+requirementId+"&state="+state+"&preSalesPerson="+preSalesPerson;
  var url = CONTENXT_PATH+"/searchRequirementAjaxList.action?createdBy="+createdBy+"&assignedTo="+assignedTo+"&title="+title+"&postedDate1="+postedDate1+"&postedDate2="+postedDate2+"&status="+status +"&assignedBy="+assignedBy+"&country="+country+"&practiceid="+practiceid+"&requirementId="+requirementId+"&state="+state+"&preSalesPerson="+preSalesPerson+"&clientId="+clientId;
  //alert("url-----"+url)
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);

}


function readyStateHandlerreq(req,responseTextHandler) {

        //alert("ready");
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadingMessage12")).style.display = "none";
                responseTextHandler(req.responseText);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
          
            (document.getElementById("loadingMessage12")).style.display = "block";
        }
    }
}

function displayReqListResult(resText) {
    var oTable = document.getElementById("tblUpdate1");
     clearTable(oTable);
    if(resText.length !=0 && resText!="addto0"){
        
        
       
       //"Secondary Recruiter",
            if(AjaxRoleId == 6)
                var headerFields = new Array("SNo","Req&nbsp;Id","RequirementJobTitle","Status","ConsultantName","Location","AssignedDate","SubmittedDate","No.of Pos","Resumes Submitted","Recruiter","Pre-Sales");	
            else
                var headerFields = new Array("SNo","Req&nbsp;Id","RequirementJobTitle","Status","Location","AssignedDate","SubmittedDate","No.of Pos","Resumes Submitted","Recruiter","Pre-Sales");	
               

        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
       
        var resTextSplit1 = resText.split("^");

         generateTableHeader(tbody,headerFields);
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("|");
            if(AjaxRoleId == 6)
                generateRow(tbody,resTextSplit2,index);
            else
               generateRowforsales(tbody,resTextSplit2,index);
        }
        generateFooter(tbody);
    }else {
        alert("No Records Found");
    }
}
function generateTableHeader(tableBody,headerFields) {
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridHeader";
    tableBody.appendChild( row );
    for (var i=0; i<headerFields.length; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridHeader";
        row.appendChild( cell );

        cell.setAttribute("width","10000px");
        cell.innerHTML = headerFields[i];
    }
}

function generateRow(tableBody,rowFeildsSplit,index) {
    var row;
    var cell;
    row = document.createElement("TR");
    row.className="gridRowEven";
    cell = document.createElement("TD");
    cell.className="gridRowEven";
    cell.innerHTML = index+1;
    cell.setAttribute('align','center');
    row.appendChild(cell);
    tableBody.appendChild(row);

    for (var i=1; i<=rowFeildsSplit.length-5; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild(cell);

        if(i==1) {
            cell.innerHTML = " ";
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:getRequirementData('"+rowFeildsSplit[2]+"')");
            j.appendChild(document.createTextNode(rowFeildsSplit[2]));
            cell.appendChild(j);
            cell.align="center";
         }
         else if(i==2){ 
            // job details
             var jobTitle = rowFeildsSplit[4].substring(0,25);
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:getRequirementSkills('"+rowFeildsSplit[2]+"')");
           // j.setAttribute("onmouseover","javascript:tooltip.show('"+rowFeildsSplit[4]+"')");
           // j.setAttribute("onmouseout","javascript:tooltip.hide();");
           
           j.setAttribute("onmouseover","Tip('"+rowFeildsSplit[4]+"')");
           j.setAttribute("onmouseout","javascript:UnTip();");
           j.appendChild(document.createTextNode(jobTitle+"..."));      
            cell.appendChild(j);

           
        }
          else if(i==4){
             // consultant 
           if(rowFeildsSplit[5]=="null"){
            rowFeildsSplit[5] = "-";
            cell.innerHTML = "-";
            }else{
            // cell.innerHTML = rowFeildsSplit[5];
             var j = document.createElement("a");
            j.setAttribute("href", "javascript:getConsultantData('"+rowFeildsSplit[3]+"','"+rowFeildsSplit[2]+"')");
            j.appendChild(document.createTextNode(rowFeildsSplit[5]));
            cell.appendChild(j);
            }
        }else if(i==5){
            cell.setAttribute('align','center');
            cell.innerHTML = rowFeildsSplit[6];
        }else if(i==3){
               cell.setAttribute('align','center');
               cell.innerHTML = rowFeildsSplit[7];
        }else if(i==7){
                var ass_Date = rowFeildsSplit[8].split(" ");

                if(ass_Date[0] == "null"){
                    cell.setAttribute('align','center');
                    cell.innerHTML = " ";
                }else{
                cell.setAttribute('align','center');
                cell.innerHTML = ass_Date[0];
                }
        }else if(i==6){
                var sub_date=rowFeildsSplit[9].split(" ");

                if(sub_date[0] == "null"){
                    cell.setAttribute('align','center');
                    cell.innerHTML = " ";
                }else{
                    cell.setAttribute('align','center');
                   cell.innerHTML = sub_date[0];
                   }
        }else if(i==8){
            if(rowFeildsSplit[10] == "0"){
            cell.setAttribute('align','center');
            cell.innerHTML = "1";
            }else{
            cell.setAttribute('align','center');
            cell.innerHTML = rowFeildsSplit[10];
            }
        }else if(i==9){
            if(rowFeildsSplit[5]=="-"){
                cell.setAttribute('align','center');
                cell.innerHTML = "0";
            }
            else{
                cell.setAttribute('align','center');
                cell.innerHTML = rowFeildsSplit[1];
            }
        }else if(i==10){

                // recruiter

               if(rowFeildsSplit[11]=="null" || rowFeildsSplit[11]=="" || rowFeildsSplit[11]==" ")
                    cell.innerHTML = "-";
              else{
                    //getRecruiterDetails
                    var req_Name = rowFeildsSplit[11];
                    var req_namesplit = req_Name.split(".");
                   // alert(req_namesplit[0]);
                    var REQ_NAME = "";
                   // alert(req_namesplit[0].indexOf(" "))
                    if(req_namesplit[0].indexOf(" ")>0){
                    var req_fName = req_namesplit[0].split(" ");
                    REQ_NAME = req_fName[0]+"."+req_namesplit[1];
                    }else{
                     REQ_NAME = req_namesplit[0]   
                    }
                    
                    var j = document.createElement("a");
                    j.setAttribute("href", "javascript:getRecruiterDetails('"+rowFeildsSplit[11]+"')");
                    j.appendChild(document.createTextNode(REQ_NAME));
                    cell.appendChild(j);
                    // cell.innerHTML = rowFeildsSplit[11];
              }
        }else if(i==11){
                // pre- sales
                
                if(rowFeildsSplit[13]=="null" || rowFeildsSplit[13]=="" || rowFeildsSplit[13]==" ")
                    cell.innerHTML = "-";
                else{ 
                    var presales_Name = rowFeildsSplit[13];
                    var presales_namesplit = presales_Name.split(".");
                    var PRESALES_NAME = "";
                    if(presales_namesplit[0].indexOf(" ")>0){
                    var presales_fName = presales_namesplit[0].split(" ");
                    PRESALES_NAME = presales_fName[0]+"."+presales_namesplit[1];
                    }else{
                     PRESALES_NAME = req_namesplit[0]   
                    }
                    //cell.innerHTML = rowFeildsSplit[13];
                    var j = document.createElement("a");
                    j.setAttribute("href", "javascript:getRecruiterDetails('"+rowFeildsSplit[13]+"')");
                    j.appendChild(document.createTextNode(PRESALES_NAME));
                    cell.appendChild(j);
                        
                }
        }
        cell.width = 120;
    }
}

//  genrate records for sales 

function generateRowforsales(tableBody,rowFeildsSplit,index){

       // alert("role Id in genarate row:"+AjaxRoleId);
    
   // alert("rowFeildsSplit---------------------"+rowFeildsSplit[rowFeildsSplit.length-5]);
    var row;
    var cell;
    row = document.createElement("TR");
    row.className="gridRowEven";
    cell = document.createElement("TD");
    cell.className="gridRowEven";
    cell.innerHTML = index+1;
    cell.setAttribute('align','center');
    row.appendChild(cell);
    tableBody.appendChild(row);

    for (var i=1; i<=rowFeildsSplit.length-6; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild(cell);

        if(i==1) {

             cell.innerHTML = " ";

             var j = document.createElement("a");
            j.setAttribute("href", "javascript:getRequirementData('"+rowFeildsSplit[2]+"')");
            j.appendChild(document.createTextNode(rowFeildsSplit[2]));
            cell.appendChild(j);
             
            cell.align = "center";
      
        }else if(i==2){ 

            // job details

             var jobTitle = rowFeildsSplit[4].substring(0,25);
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:getRequirementSkills('"+rowFeildsSplit[2]+"')");
            j.setAttribute("onmouseover","javascript:tooltip.show('"+rowFeildsSplit[4]+"')");
            j.setAttribute("onmouseout","javascript:tooltip.hide();");
            j.appendChild(document.createTextNode(jobTitle+"..."));      
            cell.appendChild(j);


        }else if(i==4){
            cell.setAttribute('align','center');
            cell.innerHTML = rowFeildsSplit[6];
        }else if(i==3){
                cell.setAttribute('align','center');
               cell.innerHTML = rowFeildsSplit[7];
        }else if(i==6){
                var ass_Date = rowFeildsSplit[8].split(" ");

                if(ass_Date[0] == "null"){
                    cell.innerHTML = " ";
                    cell.setAttribute('align','center');
                }else{ 
                cell.innerHTML = ass_Date[0];
                cell.setAttribute('align','center');
                }
        }else if(i==5){
                var sub_date=rowFeildsSplit[9].split(" ");

                if(sub_date[0] == "null"){
                    cell.setAttribute('align','center');
                    cell.innerHTML = " ";
                }    
                else{
                    cell.setAttribute('align','center');
                   cell.innerHTML = sub_date[0];
                }
        }else if(i==7){
             if(rowFeildsSplit[10] == "0"){
             cell.setAttribute('align','center');
              cell.innerHTML = "1";
            }else{
            cell.setAttribute('align','center');
            cell.innerHTML = rowFeildsSplit[10];
            }
        }else if(i==8){
           
             if(rowFeildsSplit[5]=="-" || rowFeildsSplit[5]=="null"){
             cell.setAttribute('align','center');
                cell.innerHTML = "0";
            }
            else{
            cell.setAttribute('align','center');
            cell.innerHTML = rowFeildsSplit[1];
            }
        }else if(i==9){

               // recruiter

               if(rowFeildsSplit[11]=="null" || rowFeildsSplit[11]=="" || rowFeildsSplit[11]==" ")
                    cell.innerHTML = "-";
              else{
                    //getRecruiterDetails
                    var req_Name = rowFeildsSplit[11];
                    var req_namesplit = req_Name.split(".");
                   // alert(req_namesplit[0]);
                    var REQ_NAME = "";
                   // alert(req_namesplit[0].indexOf(" "))
                    if(req_namesplit[0].indexOf(" ")>0){
                    var req_fName = req_namesplit[0].split(" ");
                    REQ_NAME = req_fName[0]+"."+req_namesplit[1];
                    }else{
                     REQ_NAME = req_namesplit[0]   
                    }
                    
                    var j = document.createElement("a");
                    j.setAttribute("href", "javascript:getRecruiterDetails('"+rowFeildsSplit[11]+"')");
                    j.appendChild(document.createTextNode(REQ_NAME));
                    cell.appendChild(j);
                    // cell.innerHTML = rowFeildsSplit[11];
              }
        }else if(i==10){
              // pre- sales
                
                if(rowFeildsSplit[13]=="null" || rowFeildsSplit[13]=="" || rowFeildsSplit[13]==" ")
                    cell.innerHTML = "-";
                else{ 
                    var presales_Name = rowFeildsSplit[13];
                    var presales_namesplit = presales_Name.split(".");
                    var PRESALES_NAME = "";
                    if(presales_namesplit[0].indexOf(" ")>0){
                    var presales_fName = presales_namesplit[0].split(" ");
                    PRESALES_NAME = presales_fName[0]+"."+presales_namesplit[1];
                    }else{
                     PRESALES_NAME = req_namesplit[0]   
                    }
                    //cell.innerHTML = rowFeildsSplit[13];
                    var j = document.createElement("a");
                    j.setAttribute("href", "javascript:getRecruiterDetails('"+rowFeildsSplit[13]+"')");
                    j.appendChild(document.createTextNode(PRESALES_NAME));
                    cell.appendChild(j);
                    
                        
                }
        }
        cell.width = 120;
    }
}



function generateFooter(tbody) {
   // alert(tbody);
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
//    cell.id="footer"+oTable.id;

     if(AjaxRoleId == 6)   
        cell.colSpan = "12";
     else
        cell.colSpan = "11";

    footer.appendChild(cell);
}

function clearTable(tableId) {
    var tbl =  tableId;
    var lastRow = tbl.rows.length; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}

//

 function getRequirementData(requirementId) {
    //alert('hi');
    var requirementAdminFlag = document.getElementById("requirementAdminFlag").value;
    
    
    document.location="/Hubble/crm/requirement/getRequirement.action?objectId="+requirementId+"&requirementAdminFlag="+requirementAdminFlag;
}

function getConsultantData(empid,reqId){
 
 // document.location="/Hubble/recruitment/consultant/getConsultant.action?empId="+empid+"&requirement"+reqId;

   document.location="/Hubble/recruitment/consultant/getConsultant.action?empId="+empid+"&requirement="+reqId;
}

//tooltip for job title in requirement list

var tooltip=function(){
	var id = 'tt';
	var top = 3;
	var left = 3;
	var maxw = 300;
	var speed = 10;
	var timer = 20;
	var endalpha = 95;
	var alpha = 0;
	var tt,t,c,b,h;
	var ie = document.all ? true : false;
	return{
		show:function(v,w){
			if(tt == null){
				tt = document.createElement('div');
				tt.setAttribute('id',id);
				t = document.createElement('div');
				t.setAttribute('id',id + 'top');
				c = document.createElement('div');
				c.setAttribute('id',id + 'cont');
				b = document.createElement('div');
				b.setAttribute('id',id + 'bot');
				tt.appendChild(t);
				tt.appendChild(c);
				tt.appendChild(b);
				document.body.appendChild(tt);
				tt.style.opacity = 0;
				tt.style.filter = 'alpha(opacity=0)';
				document.onmousemove = this.pos;
			}
			tt.style.display = 'block';
			c.innerHTML = v;
			tt.style.width = w ? w + 'px' : 'auto';
			if(!w && ie){
				t.style.display = 'none';
				b.style.display = 'none';
				tt.style.width = tt.offsetWidth;
				t.style.display = 'block';
				b.style.display = 'block';
			}
			if(tt.offsetWidth > maxw){tt.style.width = maxw + 'px'}
			h = parseInt(tt.offsetHeight) + top;
			clearInterval(tt.timer);
			tt.timer = setInterval(function(){tooltip.fade(1)},timer);
		},
		pos:function(e){
			var u = ie ? event.clientY + document.documentElement.scrollTop : e.pageY;
			var l = ie ? event.clientX + document.documentElement.scrollLeft : e.pageX;
			tt.style.top = (u - h) + 'px';
			tt.style.left = (l + left) + 'px';
		},
		fade:function(d){
			var a = alpha;
			if((a != endalpha && d == 1) || (a != 0 && d == -1)){
				var i = speed;
				if(endalpha - a < speed && d == 1){
					i = endalpha - a;
				}else if(alpha < speed && d == -1){
					i = a;
				}
				alpha = a + (i * d);
				tt.style.opacity = alpha * .01;
				tt.style.filter = 'alpha(opacity=' + alpha + ')';
			}else{
				clearInterval(tt.timer);
				if(d == -1){tt.style.display = 'none'}
			}
		},
		hide:function(){
			clearInterval(tt.timer);
			tt.timer = setInterval(function(){tooltip.fade(-1)},timer);
		}
	};
}();





function getRequirementsAdminList1() {
    
    AjaxRoleId = document.getElementById("roleTypeId").value;
   // alert("role Id in getREqList:"+AjaxRoleId);
    var req = newXMLHttpRequest();
     req.onreadystatechange = readyStateHandlerreq(req, displayReqListResult); 
    
      //alert("path ==="+CONTENXT_PATH);

    var url = CONTENXT_PATH+"/requirementAdminAjaxList.action";
 
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function getAdminSearchReqList(){
       // alert("hi")
       AjaxRoleId = document.getElementById("roleTypeId").value;
   
   status=document.getElementById("status").value;
   createdBy=document.getElementById("createdBy").value;
   assignedTo=document.getElementById("assignedTo").value;
   title=document.getElementById("title").value;
   postedDate1=document.getElementById("postedDate1").value;
   postedDate2=document.getElementById("postedDate2").value;
assignedBy = document.getElementById("assignedBy").value;
country=document.getElementById("country").value;
   practiceid=document.getElementById("practiceId").value;
   requirementId=document.getElementById("requirementId").value;
   state=document.getElementById("state").value;
   preSalesPerson=document.getElementById("preSalesPerson").value;
clientId=document.getElementById("clientId").value;
   //alert("status1-->"+status);

//createdBy,assignedTo,title,postedDate1,postedDate2
//alert("Created BY-----------"+createdBy.value+"-------assign TO---"+assignedTo.value+"-----title----"+title.value+"----postdate1---"+postedDate1.value+"----postedDate2----"+postedDate2.value+"----roleId------"+roleTypeId.value)
         var req = newXMLHttpRequest();
     req.onreadystatechange = readyStateHandlerreq(req, displayReqListResult); 
    
      //alert("path ==="+CONTENXT_PATH);

     //var url = CONTENXT_PATH+"/searchRequirementAjaxList.action?createdBy="+createdBy.value+"&assignedTo="+assignedTo.value+"&title="+title.value+"&postedDate1="+postedDate1.value+"&postedDate2="+postedDate2.value+"&status="+status;
    //var url = CONTENXT_PATH+"/searchAdminRequirementAjaxList.action?createdBy="+createdBy+"&assignedTo="+assignedTo+"&title="+title+"&postedDate1="+postedDate1+"&postedDate2="+postedDate2+"&status="+status;
  //var url= CONTENXT_PATH+"/searchAdminRequirementAjaxList.action?createdBy="+createdBy+"&assignedTo="+assignedTo+"&title="+title+"&postedDate1="+postedDate1+"&postedDate2="+postedDate2+"&status="+status+"&assignedBy="+assignedBy+"&country="+country+"&practiceid="+practiceid+"&requirementId="+requirementId+"&state="+state+"&preSalesPerson="+preSalesPerson;
  var url= CONTENXT_PATH+"/searchAdminRequirementAjaxList.action?createdBy="+createdBy+"&assignedTo="+assignedTo+"&title="+title+"&postedDate1="+postedDate1+"&postedDate2="+postedDate2+"&status="+status+"&assignedBy="+assignedBy+"&country="+country+"&practiceid="+practiceid+"&requirementId="+requirementId+"&state="+state+"&preSalesPerson="+preSalesPerson+"&clientId="+clientId;

  //alert("url-----"+url)
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);

}



