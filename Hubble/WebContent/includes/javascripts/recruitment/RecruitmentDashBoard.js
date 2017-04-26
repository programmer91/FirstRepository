/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


var foo = "bar";


function newXMLHttpRequest() {
    var xmlreq = false;
    if(window.XMLHttpRequest) {
        xmlreq = new XMLHttpRequest();
    } else if(window.ActiveXObject) {
        try {
            xmlreq = new ActiveXObject("MSxm12.XMLHTTP");
        } catch(e1) {
            try {
                xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
            } catch(e2) {
                xmlreq = false;
            }
        }
    }
    return xmlreq;
}
function loadActMessageASh(req,responseHandler) {
   // alert("yes");
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("loadActMessageASh").style.display = 'none';
                responseHandler(req.responseText);
            }
        } else {
            document.getElementById("loadActMessageASh").style.display = 'block';
        //alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }
}

function readyStateHandlerAS(req,responseHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("loadActMessageAS").style.display = 'none';
                responseHandler(req.responseText);
            }
        } else {
            document.getElementById("loadActMessageAS").style.display = 'block';
        //alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }
}

function readyStateHandlerSAPC(req,responseHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("loadActMessageSAPC").style.display = 'none';
                responseHandler(req.responseText);
            }
        } else {
            document.getElementById("loadActMessageSAPC").style.display = 'block';
        //alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }
}

function readyStateHandlerNPSR(req,responseHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("loadActMessageNPSR").style.display = 'none';
                responseHandler(req.responseText);
            }
        } else {
            document.getElementById("loadActMessageNPSR").style.display = 'block';
        //alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }
}

function readyStateHandlerNRS(req,responseHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("loadActMessageNRS").style.display = 'none';
                responseHandler(req.responseText);
            }
        } else {
            document.getElementById("loadActMessageNRS").style.display = 'block';
        //alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }
}

function readyStateHandlerNRC(req,responseHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("loadActMessageNRC").style.display = 'none';
                responseHandler(req.responseText);
            }
        } else {
            document.getElementById("loadActMessageNRC").style.display = 'block';
        //alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }
}

//new method for rec dashboard

function getRecActivitiesByRep() {
 var startdate = document.getElementById("startDateSummary").value;
    var enddate = document.getElementById("endDateSummary").value;
    if(startdate==""||startdate==null||enddate==""||enddate==null)
        {
    validateDates();
    compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value);
    return false;
        }
    var tableId = document.getElementById("tblRecDashBoardSummRep");
    ClrTable(tableId);
    var checkResult = compareDates(startdate,enddate);
    if(!checkResult) {
        return false;
    }
     var createdBy = document.getElementById("createdBy").value;
     var activityType = document.getElementById("activityType").value;
    if(createdBy == '-1') {
        createdBy = '--Please Select--';
    }
    //dueStartDate = replaceDateSplits(dueStartDate);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerAS(req, displayRecActivitiesByRep);
    var url = CONTENXT_PATH+"/consultantActivitiesByRep.action?dashBoardStartDateRep="+startdate+"&dashBoardEndDateRep="+enddate+"&createdBy="+createdBy+"&activityType="+activityType;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);

}


function displayRecActivitiesByRep(response) {
    var tableId = document.getElementById("tblRecDashBoardSummRep");
    var headerFields = new Array("S.No","Consultant Name","Activity Type","Activity By","Activity Date","Comments");
    var dataArray = response;
    //alert("in displayRecActivitiesByRep..."+dataArray);
    ParseAndGenerateHTML(tableId,dataArray, headerFields);
// document.getElementById(("footer"+tableId.id)).innerHTML = "Total Value is($):  "+dataArray[2];
}


function getTotalProfiles() {   
    //alert("In getTotalProfiles"); 
    var tableId = document.getElementById("tblToatlProfiles");
    ClrTable(tableId);
    var dashBoardStartDate = document.getElementById("dashBoardStartDate").value;
    var dashBoardEndDate = document.getElementById("dashBoardEndDate").value;
    
     if(dashBoardStartDate==""||dashBoardStartDate==null||dashBoardEndDate==""||dashBoardEndDate==null)
        {
    validateDates();
    compareDates(dashBoardStartDate,dashBoardEndDate);
    return false;
        }
    var tableId = document.getElementById("tblToatlProfiles");
    ClrTable(tableId);
    //dueStartDate = replaceDateSplits(dueStartDate);
    var req = newXMLHttpRequest();
   // req.onreadystatechange = readyStateHandler(req, displayTotalProfilesByPractice);
   
    req.onreadystatechange = readyStateHandlerSAPC(req, displayTotalProfilesByPractice);
    //var url = CONTENXT_PATH+"/accountsByRep.action?dashBoardStartDateRep="+dashBoardStartDateRep+"&dashBoardEndDateRep="+dashBoardEndDateRep;
    var url = CONTENXT_PATH+"/getTotalProfilesByPractice.action?dashBoardStartDate="+dashBoardStartDate+"&dashBoardEndDate="+dashBoardEndDate;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}



function displayTotalProfilesByPractice(response) {
//alert("response-->"+response);
    var tableId = document.getElementById("tblToatlProfiles");
    
   // var headerFields = new Array("Sourcing Person","B2B","BPM","SAP");
   var headerFields = new Array("Sourcing Person","B2B","BPM","SAP","J2EE","Others");
    var dataArray = response;    
   
    ParseAndGenerateHTML(tableId,dataArray, headerFields);   

    
}


function ParseAndGenerateHTML(oTable,responseString,headerFields) {
    
    //alert("In ParseAndGenerateHTML");
    var start = new Date();
    var fieldDelimiter = "|";
    var recordDelimiter = "^";   
    //alert('responseString%%%% ******'+responseString);
    //alert('rowCount%%%% ******'+rowCount);      
    if(oTable.id=="tblSubmitReport" || oTable.id=="tblAddedConsultant"){
    
         fieldDelimiter = "#^$";
     recordDelimiter = "*@!"; 
    }
    var records = responseString.split(recordDelimiter); 
    //alert(records);
    generateTable(oTable,headerFields,records,fieldDelimiter);
    //alert("End Of ParseAndGenerateHTML");
}

function generateTable(oTable, headerFields,records,fieldDelimiter) {	
    //alert("oTable.id-->"+oTable.id);
    //alert("IN generateTable");
    var tbody = oTable.childNodes[0];    
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    generateTableHeader(tbody,headerFields);
     var rowlength;
    rowlength = records.length-1;
    if(rowlength >=1 && records!=""){
        //alert("rowlength-->^"+records);
        for(var i=0;i<rowlength;i++) {
           // alert("i-->"+i);
           if(oTable.id == "tblRecDashBoardSummRep")
                {
                  //alert("2");
                  generateRecDashBoardActivities(oTable,tbody,records[i],fieldDelimiter)
                }
            else if(oTable.id == "tblToatlProfiles" || oTable.id ==  "tblProfilesSubmitted" || oTable.id ==  "tblRequirementsClosed" || oTable.id ==  "tblRequirementsStatus" || oTable.id ==  "tblSubmitReport"|| oTable.id ==  "tblAddedConsultant"){
                generateRow(oTable,tbody,records[i],fieldDelimiter);  
            }
            
        }    
    }    
    else {
        generateNoRecords(tbody,oTable);
    }
    
    generateFooter(tbody,oTable);
    //alert("End Of generateTable");
}

function generateRecDashBoardActivities(oTable,tableBody,record,delimiter){
   // alert("1");
    var activityType = "";
    var consultantName = "";
    var activityBy = "";
    var activityDate = 0;
    var comments = 0;
    //var noOfActivities = 0;
    var row;
    var cell;
    var fieldLength;
    //alert(record);
    var fields = record.split(delimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
    /*if(oTable.id == "tblAccountSummRep"){
        length = fieldLength;
    }
    else {
        length = fieldLength-1;
    }*/
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    //Assigning values start
    activityType = fields[1];
    consultantName = fields[2];
    activityBy = fields[3];
    activityDate = fields[4];
    comments = fields[5];
    //alert("-->"+comments+"<--");
    //empLoginId = fields[7];
    //Assigning values End
    //alert(length);
    for (var i=0;i<length-1;i++) {
       // cell = document.createElement( "TD" );
       // cell.className="gridColumn";
       if(i==0)
           {
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        cell.innerHTML = fields[i];
        cell.setAttribute('align','center');
           }
           
        if(i>0 && i!=5) { 
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        cell.innerHTML = fields[i];
         //cell.setAttribute('align','center');
        }
        //else if(i==7){
        else if(i==5){
            //empLoginId = fields[i];
            cell = document.createElement( "TD" );
            cell.className="gridColumn";
             cell.setAttribute('align','center');
         
            //row.appendChild( cell );
            var j = document.createElement("a");
            
            j.setAttribute("href", "javascript:getRecDashBoardComments('"+comments+"')");
            j.appendChild(document.createTextNode("view"));
            cell.appendChild(j);
        }
       // if(fields[i]!=''){
        row.appendChild( cell );
       // }
    }              
        //Feed back Mail End
}

//new method rec dashboard

function readyStateHandler(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadActMessageAS")).style.display = "none";
                responseTextHandler(req.responseText);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
            (document.getElementById("loadActMessageAS")).style.display = "block";
        }
    }
}
function getRecDashBoardComments(comments) {
    var comments = comments;
    var req = new XMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,populateRecDashboardComments);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/popupRecDashBoardComments.action?comments="+comments;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateRecDashboardComments(text) {
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


//function generateRow(oTable,tableBody,record,delimiter) {
//    //alert("In generateRow");
//    var row;
//    var cell;
//    var fieldLength;
//    var fields = record.split(delimiter);
//    fieldLength = fields.length ;
//    var length;
//    //if(oTable.id == "tblAccountSummRep" || oTable.id == "tblUpdateForAccountsListByPriority"){
//        length = fieldLength;
//  //  }
//    
//   // else {
//   //     length = fieldLength-1;
//   // }
//  
//    row = document.createElement( "TR" );
//    row.className="gridRowEven";
//    tableBody.appendChild( row );
//     
//    for (var i=0;i<length;i++) {
//       
//        cell = document.createElement( "TD" );
//        cell.className="gridColumn";
//        //alert(fields[i]+"fields[i]");
//        cell.innerHTML = fields[i];
//        if(fields[i]!=''){
//            row.appendChild( cell );
//        }
//    }
//}

function generateRow(oTable,tableBody,record,delimiter) {
    //alert("In generateRow");
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(delimiter);
    fieldLength = fields.length ;
    var length;
    //if(oTable.id == "tblAccountSummRep" || oTable.id == "tblUpdateForAccountsListByPriority"){
        length = fieldLength;
  //  }
    
   // else {
   //     length = fieldLength-1;
   // }
  
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
     if(oTable.id=="tblSubmitReport"){
   //  alert(length);
          for (var i=1;i<length;i++) {
            //  alert(i+" "+fields[i]);
                cell = document.createElement( "TD" );
        cell.className="gridColumn";
          
      if(parseInt(i,10)!=1){
      
        //alert(fields[i]+"fields[i]");
        cell.innerHTML = fields[i];
        
        
        if(parseInt(i,10)==23){
           // alert(fields[0]);
            // cell.innerHTML = "<a href='#' onMouseOver='Tip(\"" + fields[i] + "\");' onmouseout='UnTip();'>"+fields[i]+"</a>";
             cell.innerHTML = "<a href='javascript:getConsultantDetails("+fields[0]+")'>"+fields[i]+"</a>";
        }
        
        
      }else {
         
           var jobTitle = fields[i].substring(0,10);
           //cell.innerHTML = part1+ "<a href='#' onMouseOver='getPart2(\"" + fields[i] + "\");' onmouseout='closepopUpWindow();'> ..</a>";
           cell.innerHTML = jobTitle+ "<a href='#' onMouseOver='Tip(\"" + fields[i] + "\");' onmouseout='UnTip();'> ..</a>";
        
          // cell.innerHTML = jobTitle;
      /*   var j = document.createElement("a");
        //var j = document.createElement("font");
        j.setAttribute("href", "#");
		//	j.setAttribute("onmouseover","javascript:tooltip.show('"+fields[i]+"')");
           //j.setAttribute("onmouseout","javascript:tooltip.hide();");
           j.setAttribute("onmouseover","Tip('"+fields[i]+"')");
           j.setAttribute("onmouseout","javascript:UnTip();");
          // j.appendChild(document.createTextNode(jobTitle+"..."));      
           j.appendChild(document.createTextNode(":::"));      
          // j.appendChild(document.createTextNode(fields[i]));     */ 
           // cell.appendChild(j);
           
           
       }
       if(fields[i]!=''){
            row.appendChild( cell );
        }
    }
     } else if(oTable.id=="tblAddedConsultant"){
    for (var i=0;i<length;i++) {
      // alert(i+" "+fields[i]);
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        //alert(fields[i]+"fields[i]");
        //if(i!=length-1)
       
       // document.elementById("overlayRecruitment").style.height=length+"px";
        if(parseInt(i,10)==14){
           // alert(fields[0]);
            // cell.innerHTML = "<a href='#' onMouseOver='Tip(\"" + fields[i] + "\");' onmouseout='UnTip();'>"+fields[i]+"</a>";
           //  cell.innerHTML = "<a href='javascript:getConsultantRemarks("+fields[i]+")'>Click to view</a>";
               var jobTitle = fields[i].substring(0,10);
           //cell.innerHTML = part1+ "<a href='#' onMouseOver='getPart2(\"" + fields[i] + "\");' onmouseout='closepopUpWindow();'> ..</a>";
           cell.innerHTML = jobTitle+ "<a href='#' onMouseOver='Tipp(\"" + fields[i] + "\");' onmouseout='UnTipp();'> ..</a>";
        
        }
        else{
             cell.innerHTML = fields[i];
        }
         //cell.innerHTML = fields[i];
        //else{
         //   cell.innerHTML = "<a href='javascript:getConsultantDetails("+fields[i]+")'>Click To View</a>";
        //}
        
       // alert(fields[i]);
       //if(fields[i]!=''){
            row.appendChild( cell );
        //}
    }
     }else if(oTable.id=="tblOpertunities"){
           for (var i=0;i<length;i++) {
       
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        //alert(fields[i]+"fields[i]");
        cell.innerHTML = fields[i];
        if(fields[i]!=''){
            row.appendChild( cell );
        }
    }
         
     }
     
     else {
         
         
          for (var i=0;i<length;i++) {
       
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        //alert(fields[i]+"fields[i]");
        cell.innerHTML = fields[i];
        if(fields[i]!=''){
            row.appendChild( cell );
        }
    }
         
         
       
     }
}


function generateNoRecords(tbody,oTable) {
    //alert("In generateNoRecords");
    var noRecords =document.createElement("TR");
    noRecords.className="gridRowEven";
    tbody.appendChild(noRecords);
    cell = document.createElement("TD");
    cell.className="gridColumn";
    
     if(oTable.id == "tblRecDashBoardSummRep"){
        cell.colSpan = "6";
    }else if(oTable.id == "tblActUpdate"){
        cell.colSpan = "5";
    }else if(oTable.id == "tblOppUpdate"){
        cell.colSpan = "5";
    }else if(oTable.id == "tblRequirementInfo"){
        cell.colSpan = "2";
    }
    else if(oTable.id == "tblToatlProfiles"){
       
            cell.colSpan = "6";  
    }
    else if(oTable.id == "tblInactiveProfiles"||oTable.id == "tblProfilesSubmitted" || oTable.id ==  "tblRequirementsClosed"){
        cell.colSpan = "3";
    }
    else if(oTable.id ==  "tblRequirementsStatus"){
        cell.colSpan = "3";
    }
    
    cell.innerHTML = "No Records Found for this Search";
    noRecords.appendChild(cell);
}


function generateTableHeader(tableBody,headerFields) {
     //alert("In generateTableHeader");
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridHeader";
    tableBody.appendChild( row );
    
    for (var i=0; i<headerFields.length; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridHeader";
        row.appendChild( cell );
        cell.innerHTML = headerFields[i];
        cell.width = 120;
    }
}


function generateFooter(tbody,oTable) {
     //alert("In generateFooter");
    //alert(oTable.id);
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.id="footer"+oTable.id;
    
    
     if(oTable.id == "tblRecDashBoardSummRep"){
        cell.colSpan = "6";
    }
    //cell.colSpan = "5";
    else if(oTable.id == "tblActUpdate"){
        cell.colSpan = "5";
    }else if(oTable.id == "tblOppUpdate"){
        cell.colSpan = "5";
    }else if(oTable.id == "tblRequirementInfo"){
        cell.colSpan = "3";
    }
    else if(oTable.id == "tblToatlProfiles"){
       
            cell.colSpan = "6"; 
    }
    else if(oTable.id == "tblProfilesSubmitted" || oTable.id ==  "tblRequirementsClosed" || oTable.id ==  "tblRequirementsStatus" || oTable.id == "tblInactiveProfiles" || oTable.id == "tblActiveProfiles" )
    {
        cell.colSpan = "3";   
    }else{
        cell.colSpan = "25";   
    }
    footer.appendChild(cell);
}

function ClrTable(myHTMLTable) { 
     //alert("In ClrTable");
    var tbl =  myHTMLTable;
    var lastRow = tbl.rows.length; 
    //document.getElementById('addlabel1').style.display = 'none'; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}


/*Methods for Profile Activities
 * 
 * 
 */
function getInactiveProfileByPractice() {
   // alert("hiii");
  
    var req = newXMLHttpRequest();
  
   
    req.onreadystatechange = readyStateHandler(req, displayInProfilesByPractice);
    
    var url = CONTENXT_PATH+"/getInActiveProfilesByPractice.action";
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    
    
}


function displayInProfilesByPractice(response) {
//alert("response-->"+response);
    var tableId = document.getElementById("tblInactiveProfiles");
    
    var headerFields = new Array("B2B","BPM","SAP");
   
    var dataArray = response;
    
 //  generateTableHeader(tableId,headerFields)
    ParseAndGenerateHTML(tableId,dataArray, headerFields);

    
}



function getActiveProfileByPractice() {
   // alert("hiii");
  
    var req = newXMLHttpRequest();
  
   
    req.onreadystatechange = readyStateHandler(req, displayActiveProfilesByPractice);
    
    var url = CONTENXT_PATH+"/getActiveProfilesByPractice.action";
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    
    
}


function displayActiveProfilesByPractice(response) {
//alert("response-->"+response);
    var tableId = document.getElementById("tblActiveProfiles");
    
    var headerFields = new Array("B2B","BPM","SAP");
   
    var dataArray = response;
    
 //  generateTableHeader(tableId,headerFields)
    ParseAndGenerateHTML(tableId,dataArray, headerFields);

    
}


function getRequirementInfo() {
   // alert("hiii");
  
    var req = newXMLHttpRequest();
  
   
    req.onreadystatechange = readyStateHandler(req, displayRequirementInfo);
    
    var url = CONTENXT_PATH+"/getRequirementInfo.action";
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    
    
}

function displayRequirementInfo(response) {
//alert("response-->"+response);
    var tableId = document.getElementById("tblRequirementInfo");
    
    var headerFields = new Array("No. of Requirements","Practice");
    
   var responseArray = response.split("@");
   
  // alert("responseArray[1]-->"+responseArray[1]);
  document.getElementById("totalRequirementsFound").innerHTML = responseArray[0];
    var dataArray = responseArray[1];
    
 //  generateTableHeader(tableId,headerFields)
    ParseAndGenerateHTML(tableId,dataArray, headerFields);

    
}



function getReqProfileSubmittedInfo() {
   // alert("hiii");
  
  var practiceName = document.getElementById("practiceId").value;
  var startdate = document.getElementById("startDateSub").value;
   var enddate = document.getElementById("endDateSub").value;
   // alert(startdate);
   if(startdate==""||startdate==null||enddate==""||enddate==null)
        {
    validateDates();
    compareDates(startdate,enddate);
    return false;
        }
  
 
   var tableId = document.getElementById("tblProfilesSubmitted");
    ClrTable(tableId);
    var checkResult = compareDates(startdate,enddate);
  
    if(!checkResult) {
        return false;
    }
   if(practiceName!="-1") {
  
    var req = newXMLHttpRequest();  
   
    req.onreadystatechange = readyStateHandlerNPSR(req, displayRequirementProfileInfo);
    
    var url = CONTENXT_PATH+"/getReqProfileSubmittedInfo.action?practiceName="+practiceName+"&dashBoardStartDateRep="+startdate+"&dashBoardEndDateRep="+enddate;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
   }else {
       alert("Please select practice!");
   }
    
}

function displayRequirementProfileInfo(response) {
//alert("response-->"+response);

    var tableId = document.getElementById("tblProfilesSubmitted");

    var headerFields = new Array("JobTitle","DatePosted","ProfilesSubmitted");  
   
  // alert("responseArray[1]-->"+responseArray[1]);
  //document.getElementById("totalRequirementsFound").innerHTML = responseArray[0];
    var dataArray = response;
    
 //  generateTableHeader(tableId,headerFields)
    ParseAndGenerateHTML(tableId,dataArray, headerFields);

    
}



function getReqClosedInfo() {
    
//    var year = document.getElementById("startDateClose").value;
//    var month = document.getElementById("endDateClose").value;
   var startdate = document.getElementById("startDateClose").value;
   var enddate = document.getElementById("endDateClose").value;
   // alert(startdate);
   if(startdate==""||startdate==null||enddate==""||enddate==null)
        {
    validateDates();
    compareDates(startdate,enddate);
    return false;
        }
    
  //alert("year-->"+year+"---month-->"+month);
    
    var tableId = document.getElementById("tblRequirementsClosed");
    ClrTable(tableId);
//   if(year=="-1") {
//       alert("Please select year!");
//   }else if(month == "-1") {
//        alert("Please select month!");
//   }else {
//           var req = newXMLHttpRequest();
//          } 
  var checkResult = compareDates(startdate,enddate);
  
    if(!checkResult) {
        return false;
    }
    var req = newXMLHttpRequest();
   
    req.onreadystatechange = readyStateHandlerNRC(req, displayRequirementClosedInfo);
    
    var url = CONTENXT_PATH+"/getReqClosedInfo.action?dashBoardStartDateRep="+startdate+"&dashBoardEndDateRep="+enddate;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
   
}



function displayRequirementClosedInfo(response) {

    var tableId = document.getElementById("tblRequirementsClosed");

    var headerFields = new Array("JobTitle","DatePosted","ProfilesSubmitted");
   
  // alert("responseArray[1]-->"+responseArray[1]);
  //document.getElementById("totalRequirementsFound").innerHTML = responseArray[0];
    var dataArray = response;
    
 //  generateTableHeader(tableId,headerFields)
    ParseAndGenerateHTML(tableId,dataArray, headerFields);

    
}



function getReqStatusInfo() {
    //alert("In getReqStatusInfo ");
    var startdate = document.getElementById("startDate").value;
    var enddate = document.getElementById("endDate").value;
    if(startdate==""||startdate==null||enddate==""||enddate==null)
        {
    validateDates();
    compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value);
    return false;
        }
        
     var tableId = document.getElementById("tblRequirementsStatus");
    ClrTable(tableId);
   var checkResult = compareDates(startdate,enddate);
    if(!checkResult) {
        return false;
    } 
    var req = newXMLHttpRequest(); 
   
    req.onreadystatechange = readyStateHandlerNRS(req, displayRequirementStatusInfo);
    
    var url = CONTENXT_PATH+"/getReqStatusInfo.action?dashBoardStartDateRep="+startdate+"&dashBoardEndDateRep="+enddate;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
   
}



function displayRequirementStatusInfo(response) {
    //alert("In displayRequirementStatusInfo ");
   // alert("response-->"+response);

    var tableId = document.getElementById("tblRequirementsStatus");

    var headerFields = new Array("S.No","STATUS","COUNT");
    
  // alert("responseArray[1]-->"+responseArray[1]);
  //document.getElementById("totalRequirementsFound").innerHTML = responseArray[0];
    var dataArray = response;
    
 //  generateTableHeader(tableId,headerFields)
    ParseAndGenerateHTML(tableId,dataArray, headerFields);
     //alert("End Of displayRequirementStatusInfo");
    
}


//new methods recuritment activity graph
 function getRecActivitiesAsGraph() {
   
 document.getElementById("resultMessage").style.display="none";
  document.getElementById("recDashBoardActivitygraph").style.display="none";
     var createdBy = document.getElementById("createdByGraph").value;
     var activityType = document.getElementById("activityTypeGraph").value;
     var startDate=    document.getElementById("startDateSummaryGraph").value;
     var endDate=    document.getElementById("endDateSummaryGraph").value;

  
    if(startDate==""||startDate==null||endDate==""||endDate==null)
        {
    validateDates();
    
    compareDates(document.getElementById('startDateSummaryGraph').value,document.getElementById('endDateSummaryGraph').value);
    return false;
        } 
       
    
    var checkResult = compareDates(startDate,endDate);
    if(!checkResult) {
      return false;
    }
    
   
  
   if(createdBy!="-1"){
      
           var req = newXMLHttpRequest();
    req.onreadystatechange = loadActMessageASh(req, displayRecActivitiesAsGraphInd);
    var url = CONTENXT_PATH+"/consultantActivitiesAsGraphInd.action?startDateSummaryGraph="+startDate+"&endDateSummaryGraph="+endDate+"&createdBy="+createdBy+"&activityType="+activityType;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
       
       }else {
          
    var req = newXMLHttpRequest();
    req.onreadystatechange = loadActMessageASh(req, displayRecActivitiesAsGraph);
    var url = CONTENXT_PATH+"/consultantActivitiesAsGraph.action?startDateSummaryGraph="+startDate+"&endDateSummaryGraph="+endDate+"&createdBy="+createdBy+"&activityType="+activityType;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
       }

}

function displayRecActivitiesAsGraphInd(response) {
    //var tableId = document.getElementById("tblRecDashBoardSummGraph");

    if(response=="addto0"){ //alert("injuhjkhbhjvi");
        document.getElementById("resultMessage").style.display="block";
        document.getElementById("resultMessage").innerHTML="No data available"; 
       
      
    }else{
 document.getElementById("recDashBoardActivitygraph").style.display="block";
       //   alert("injuhjkhbhjvi");
        var result = response.split("^");
    var arraydata = [['Activity Name', 'Activity Count']];
    for(var i=0; i<result.length-1; i++){
        
        var res = result[i].split("|");
        var dArray = [res[1],parseInt(res[2])];
        //alert(dArray);
        arraydata.push(dArray);
    }

        var data = google.visualization.arrayToDataTable(arraydata);

        var options = {
          title: 'Activity Summary Graph' ,legend: 'left',chartArea:{width:"100%"},is3D: true
         
        };
        
         
        
        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
        
          
        chart.draw(data, options,dArray);
    }
    
    
    
   
}

function displayRecActivitiesAsGraph(response) {
    //var tableId = document.getElementById("tblRecDashBoardSummGraph");
   
    if(response=="addto0"){
        //alert("injui");
        document.getElementById("resultMessage").style.display="block";
        document.getElementById("resultMessage").innerHTML="No data available";
      
    }else{
        
         document.getElementById("recDashBoardActivitygraph").style.display="block";
        var result = response.split("^");
    var arraydata = [['Emp Name', 'Activity Count']];
    for(var i=0; i<result.length-1; i++){
        
        var res = result[i].split("|");
        var dArray = [res[1],parseInt(res[2])];
        //alert(dArray);
        arraydata.push(dArray);
    }

        var data = google.visualization.arrayToDataTable(arraydata);

        var options = {
          title: 'Activity Summary Graph' ,legend: 'left',chartArea:{width:"100%"},is3D: true
         
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options,dArray);
    }
}    

 




function readyStateHandler(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadActMessageAS")).style.display = "none";
                responseTextHandler(req.responseText);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
            (document.getElementById("loadActMessageAS")).style.display = "block";
        }
    }
}

function readyStateHandlerReport(req,responseHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("loadActMessageSubmitReport").style.display = 'none';
                responseHandler(req.responseText);
            }
        } else {
            document.getElementById("loadActMessageSubmitReport").style.display = 'block';
        //alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }
}

