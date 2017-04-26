
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
function readyStateHandlerTxt(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
               
                responseTextHandler(req.responseText);
            }
            else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
          
    }
    }
}

function addBridgeOverlay(){
    // alert("add");
   
    document.getElementById("resultMessage").style.display="none";
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Event Registration";
 
    var overlay = document.getElementById('bridgeOverlay');
    var specialBox = document.getElementById('bridgeSpecialBox');
           
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
        window.location="bmsEvent.action";
    } else {
       
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}
function issueBridgeOverlay(){
    // alert("add");
  //  document.getElementById('resultMessageNoDue').innerHTML = "";
     
    //    document.getElementById("headerLabel").style.color="white";
    //    document.getElementById("headerLabel").innerHTML="Add Event";
 
    var overlay = document.getElementById('bridgeIssueOverlay');
    var specialBox = document.getElementById('bridgeIssueSpecialBox');
           
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
       
    } else {
       
        overlay.style.display = "block";
        specialBox.style.display = "block";
       
    }
}
function addBridgeEvent(){
    //alert(externalAttendeesArray);
   //  var internalAttendees=document.getElementById("internalAttendees");
    document.getElementById("load").style.display="block";
    document.getElementById("resultMessage").style.display="none";
    
    var bridgeDate = document.getElementById('bridgeDate').value;
    var startTime = document.getElementById('startTime').value;
    var midDayFrom = document.getElementById('midDayFrom').value;
   // var endTime = document.getElementById('endTime').value;
   // var midDayTo = document.getElementById('midDayTo').value;
    var timeZone = document.getElementById('timeZone').value;
    var bridgeCode = document.getElementById('bridgeCode').value;
    var comments = document.getElementById('description').value;
     var duration = document.getElementById('duration').value;
    var durationType = document.getElementById('durationType').value;
    var externalAttendees = document.getElementById('externalAttendees').value;
    var bridgeNumber = document.getElementById('bridgeNumber').value;
     var passCode = document.getElementById('passCode').value;
    var emailFlag=0;
    if(document.getElementById('mailFlag').checked){
        emailFlag=1;
    }
 
     var internalAttendees = []; 
    $("#internalAttendees :selected").each(function(){
                        internalAttendees.push($(this).text());
                       
                    });
 // alert(internalAttendees);
    //    return false;   
    if(bridgeDate.length==0){
        document.getElementById("load").style.display="none";
        document.getElementById("resultMessage").style.display="block";
        document.getElementById("resultMessage").innerHTML="<font size='2' color='red'>please select bridge date.</font>";
        
        return false;
    }
    if(startTime==''){
         document.getElementById("load").style.display="none";
        document.getElementById("resultMessage").style.display="block";
        document.getElementById("resultMessage").innerHTML="<font size='2' color='red'>please enter start time.</font>";
        
        return false;
    }
//    else{
//        startTime=startTime+":00";
//    }
//    if(endTime==''){
//         document.getElementById("load").style.display="none";
//        document.getElementById("resultMessage").style.display="block";
//        document.getElementById("resultMessage").innerHTML="<font size='2' color='red'>please enter end time.</font>";
//        
//        return false;
//    }
//    else{
//        endTime=endTime+":00";
//    }
//    if(midDayFrom==midDayTo){
//        var startHour=startTime.substring(0,2);
//        var endHour=endTime.substring(0,2);
//   
//        if(parseInt(startHour)<=parseInt(endHour)){
//            var startMin=startTime.substring(3,5);
//            var endMin=endTime.substring(3,5);
//            if(parseInt(startHour)==parseInt(endHour)){
//            if(parseInt(startMin)>=parseInt(endMin)){
//                document.getElementById("load").style.display="none";
//                document.getElementById("resultMessage").style.display="block";
//                document.getElementById("resultMessage").innerHTML="<font size='2' color='red'>End time must be greater than start time.</font>";
//        
//                return false;
//            }
//            }
//        }
//        else{
//            document.getElementById("load").style.display="none";
//                document.getElementById("avlResultMessage").style.display="block";
//                document.getElementById("avlResultMessage").innerHTML="<font size='2' color='red'>End time must be greater than start time.</font>";
//        
//                return false;
//        }
//    
//      
//    }
//    else{
//        if(midDayFrom=="PM" && midDayTo=="AM"){
//            document.getElementById("load").style.display="none";
//            document.getElementById("resultMessage").style.display="block";
//            document.getElementById("resultMessage").innerHTML="<font size='2' color='red'>End time must be greater than start time.</font>";
//        
//            return false;
//        }
//        
//    }
//   
   
    if(bridgeCode.length==0){
        document.getElementById("load").style.display="none";
        document.getElementById("resultMessage").style.display="block";
        document.getElementById("resultMessage").innerHTML="<font size='2' color='red'>please enter bridge code.</font>";
        
        return false;
    }
    if(internalAttendees.length==0){
        document.getElementById("load").style.display="none";
        document.getElementById("resultMessage").style.display="block";
        document.getElementById("resultMessage").innerHTML="<font size='2' color='red'>please enter Internal Attendees.</font>";
        
        return false;
    }
   
    if(comments.length==0){
        document.getElementById("load").style.display="none";
        document.getElementById("resultMessage").style.display="block";
        document.getElementById("resultMessage").innerHTML="<font size='2' color='red'>please enter Description.</font>";
        
        return false;
    }
   comments = comments.replace(/\r?\n/g, '<br/>');
  // alert(comments);
    if(window.confirm("Do you want to add event bridge?")){
        var req = newXMLHttpRequest();
        
         document.getElementById("addButton").disabled=true;
        req.onreadystatechange = readyStateHandlerTxt(req,addBridgeResponse); 

        //var url=CONTENXT_PATH+"/updateMyReview.action?reviewId="+reviewId+"&overlayReviewType="+overlayReviewType+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay;
        // var url=CONTENXT_PATH+'/upadtePayrollTaxExemption.action?overLayExemptionType='+overLayexemptionType+'&overlayApprovedAmount='+overlayApprovedAmount+'&overLayStatus='+overLaystatus+'&comments='+comments+'&exemptionId='+exemptionId+"&paymentDateEmp="+paymentDateEmp;
        var url=CONTENXT_PATH+'/addBridgeEvent.action?bridgeDate='+bridgeDate+'&startTime='+startTime+'&midDayFrom='+midDayFrom+"&timeZone="+timeZone+"&comments="+escape(comments)+"&bridgeCode="+bridgeCode+"&duration="+duration+"&durationType="+durationType+'&internalAttendees='+internalAttendees+'&externalAttendees='+externalAttendees+'&bridgeNumber='+bridgeNumber+'&emailFlag='+emailFlag+'&passCode='+passCode;
         //alert(url);
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
    else{
        document.getElementById("load").style.display="none";
    }
   
}

function addBridgeResponse(resText){
    document.getElementById("load").style.display="none";
    document.getElementById('resultMessage').innerHTML = resText;
    document.getElementById("resultMessage").style.display="block";
    document.getElementById("addButton").disabled=false;
  
}
var tempbridgeDate='';
function bridgeList(bridgeDate){
    //  alert("method "+bridgeDate);
     document.getElementById("tblload").style.display="block";
      document.getElementById("tblEventList").style.display="none";
    tempbridgeDate=bridgeDate;
   // document.getElementById("hiddenbridgeDate").value=bridgeDate;
  //  $('#loadimg').show();
   // document.getElementById("bridgeList").innerHTML='';
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerTxt(req,bridgeListResponse); 

    //var url=CONTENXT_PATH+"/updateMyReview.action?reviewId="+reviewId+"&overlayReviewType="+overlayReviewType+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay;
    // var url=CONTENXT_PATH+'/upadtePayrollTaxExemption.action?overLayExemptionType='+overLayexemptionType+'&overlayApprovedAmount='+overlayApprovedAmount+'&overLayStatus='+overLaystatus+'&comments='+comments+'&exemptionId='+exemptionId+"&paymentDateEmp="+paymentDateEmp;
    var url=CONTENXT_PATH+'/getBridgeList.action?bridgeDate='+bridgeDate;
    // alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function bridgeListResponse(resText){
    //   alert(resText);
    var mainData=resText.split('AddTO');
    //$("#bridgeListTitle").html('<font color="#00008B">Events :</font> '+tempbridgeDate+'&nbsp;&nbsp;<font color="#00008B">Total Events :</font> '+mainData[1]+'&nbsp;&nbsp;<img src="/Hubble/includes/images/info.jpg" onmouseover="$(\'#infoDiv\').toggle();" onmouseout="$(\'#infoDiv\').toggle();" height="18" width="20">');
    $("#bridgeListTitle").html('<font color="#00008B">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Events&nbsp;:</font>&nbsp;'+tempbridgeDate+'&nbsp;&nbsp;<font color="#00008B">Total&nbsp;Events&nbsp;:</font>&nbsp;'+mainData[1]+'&nbsp;');
    var result='';
     var oTable = document.getElementById("tblEventList");
     clearTable(document.getElementById("tblEventList"));
         var headerFields = new Array("S.No","BExtension","BNumber","Pass&nbsp;Code","Time","Created&nbsp;By","Description","Attendees","Status","Cancel")
         tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
        var count=0;
       // resTextSplit2[0]="";
        generateTableHeader(tbody,headerFields);
      
    if(mainData[0]!='nodata'){
        var dataAraay=mainData[0].split('*@!');
        // alert(dataAraay.length)
//        for(var i=0;i<dataAraay.length-1;i++){
//            var row=dataAraay[i].split('#^$');
//            //    1#^$1#^$B1911#^$01:00:00 AM#^$01:10:00 AM#^$IST#^$Created*@!
//            var image="";
//            if(row[10]=="Created"){
//                image="yellow2.png";
//            }
//            else if(row[10]=="Canceled"){
//                image="red.png";
//            }
//            else if(row[10]=="Completed"){
//                image="green.png";
//            }
//            else if(row[10]=="Process"){
//                image="blue1.png";
//            }
//            result+="<div id='"+row[0]+"'>"+row[0]+'. '+row[2]+'<img  alt="Checked" src="/Hubble/includes/images/ecertification/'+image+'"  width="20px" height="20px" border="0" ><br>&nbsp;&nbsp;&nbsp;&nbsp;Time : '+row[3]+' - '+row[4]+' '+row[5]+'<br>&nbsp;&nbsp;&nbsp;&nbsp;Created by :'+row[6]+'<br>&nbsp;&nbsp;&nbsp;&nbsp;Comments : <a href="javascript:getBridgeComments(\''+row[7]+'\');">Click to view</a>';
//    
//            if(row[10]=="Created" && row[11]=="allow"){
//                result+='&nbsp;&nbsp;<input type="button" onclick="cancelBridge(\''+row[1]+'\')"; class="cancel" value="Cancel"/>';
//            }
//            else if(row[10]=="Canceled"){
//                result+='<br>&nbsp;&nbsp;&nbsp;&nbsp;Canceled by :'+row[8]+'<br>&nbsp;&nbsp;&nbsp;&nbsp;Reason : <a href="javascript:getBridgeReason(\''+row[9]+'\');">Click to view</a>';
//            }
//            result+='<hr></div>'
//        }
        
    
  //  $('#loadimg').hide();
    //document.getElementById("bridgeList").innerHTML=result;
    
   
         var resTextSplit1 = mainData[0].split("*@!");
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#^$");
            generateRow(oTable,tbody,resTextSplit2,index);
        }
        
        }else{
            generateNoRecords(tbody, oTable)
        }
        generateFooter(tbody,oTable);
         document.getElementById("tblload").style.display="none";
      document.getElementById("tblEventList").style.display="block";
}
function isActiveBridge(){
    $("#correctImg").hide();
    $("#wrongImg").hide();
    var bridgeCode=document.getElementById("bridgeCodeIssue").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerTxt(req,isActiveBridgeResponse); 

    //var url=CONTENXT_PATH+"/updateMyReview.action?reviewId="+reviewId+"&overlayReviewType="+overlayReviewType+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay;
    // var url=CONTENXT_PATH+'/upadtePayrollTaxExemption.action?overLayExemptionType='+overLayexemptionType+'&overlayApprovedAmount='+overlayApprovedAmount+'&overLayStatus='+overLaystatus+'&comments='+comments+'&exemptionId='+exemptionId+"&paymentDateEmp="+paymentDateEmp;
    var url=CONTENXT_PATH+'/isActiveBridge.action?bridgeCode='+bridgeCode;
    // alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function isActiveBridgeResponse(resText){
    //alert(resText);
    
    if(resText=='Yes'){
        // document.getElementById("correctImg").style.display="block";
        $("#correctImg").show();
        document.getElementById("bookButton").disabled =false;
    }else{
        //document.getElementById("wrongImg").style.display="block";
        $("#wrongImg").show();
        document.getElementById("bookButton").disabled =true;
    }
    
    
}

//function getBridgeDates(month,year){
//    
//    var req = newXMLHttpRequest();
//    req.onreadystatechange = readyStateHandlerTxt(req,getBridgeDatesResponse); 
//
//    var url=CONTENXT_PATH+'/getBridgeDates.action?month='+month+'&year='+year;
//    // alert(url);
//    req.open("GET",url,"true");
//    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
//    req.send(null);
//}
//function getBridgeDatesResponse(resText){
//    alert(resText);
//
//}
function getBridgeComments(comments){
    var background = "#3E93D4";
    var title = "Description";
    // var text1 = text; 
    // var size = text1.length;
    
    
  
    
    var size = comments.length;
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;word-wrap: break-word;'><h4>"+title+"</h4>"+comments+"<br />\
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
        popup = window.open("","window","channelmode=0,width=600,height=300,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    } else {
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=600,height=400,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}
function getAttendees(attendees){
    var background = "#3E93D4";
    var title = "Attendees";
    // var text1 = text; 
    // var size = text1.length;
    
    
  
    
    var size = attendees.length;
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;word-wrap: break-word;'><h4>"+title+"</h4>"+attendees+"<br />\
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
        popup = window.open("","window","channelmode=0,width=600,height=300,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    } else {
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=600,height=400,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}
function getBridgeReason(comments){
    var background = "#3E93D4";
    var title = "Reason";
    // var text1 = text; 
    // var size = text1.length;
    
    
  
    
    var size = comments.length;
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;word-wrap: break-word;'><h4>"+title+"</h4>"+comments+"<br />\
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
        popup = window.open("","window","channelmode=0,width=600,height=300,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    } else {
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=600,height=400,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}
function cancelBridge(id){
  
    // alert(id);
    
    document.getElementById("resultMessageCancel").innerHTML="";
    document.getElementById('cancelId').value = id;
     
    //    document.getElementById("headerLabel").style.color="white";
    //    document.getElementById("headerLabel").innerHTML="Add Event";
 
    var overlay = document.getElementById('bridgeCancelOverlay');
    var specialBox = document.getElementById('bridgeCancelSpecialBox');
           
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
       
    } else {
       
        overlay.style.display = "block";
        specialBox.style.display = "block";
        
    }

}
function doCancelBridge(){
    var id=document.getElementById('cancelId').value;
    var reason=document.getElementById('reason').value;
    document.getElementById("resultMessageCancel").style.display="none";
    document.getElementById("resultMessageCancel").innerHTML="";
    document.getElementById("loadCancel").style.display="block";
    if(reason==""){
        //alert("Please Enter comments");
        document.getElementById("loadCancel").style.display="none";
        document.getElementById("resultMessageCancel").style.display="block";
        document.getElementById("resultMessageCancel").innerHTML="<font size='2' color='red'>please enter reason.</font>";
        return false;
    }
    if(window.confirm("Do you want to cancel the event?")){
        var req = newXMLHttpRequest();
     
        req.onreadystatechange = readyStateHandlerTxt(req,doCancelBridgeResponse); 

        var url=CONTENXT_PATH+'/doCancelBridgeEvent.action?id='+id+'&reason='+reason;
       // alert(url);
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
}
function doCancelBridgeResponse(resText){
  //  alert(resText);
    document.getElementById("loadCancel").style.display="none";
    document.getElementById("resultMessageCancel").style.display="block";
    document.getElementById("resultMessageCancel").innerHTML=resText;
}
function clearTable(tableId) {
    var tbl =  tableId;
    var lastRow = tbl.rows.length; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}
function generateTableHeader(tableBody,headerFields) {
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridHeader";
    tableBody.appendChild( row );
    for (var i=0; i<headerFields.length; i++) {
        cell = document.createElement( "TH" );
        cell.className="gridHeader";
        row.appendChild( cell );
        cell.innerHTML = headerFields[i];
    //cell.width = 120;
    }
}
function generateTableHeaderForAvailableBridges(tableBody,headerFields) {
    var row;
    var cell;
    row = document.createElement( "TR" );
    //row.className="gridHeader";
    tableBody.appendChild( row );
    for (var i=0; i<headerFields.length; i++) {
        cell = document.createElement( "TH" );
      //  cell.className="gridHeader";
        row.appendChild( cell );
        cell.innerHTML = headerFields[i];
    //cell.width = 120;
    }
}
function generateRowAvailableBridges(oTable,tableBody,rowFeildsSplit,index) {
    
    //alert("rowFeildsSplit"+rowFeildsSplit[rowFeildsSplit.length-5]);
    var row;
    var cell;
    row = document.createElement("TR");
    // row.className="gridRowEven";
    // cell = document.createElement("TD");
    // cell.className="gridRowEven";
    // cell.innerHTML = index+1;
    //row.appendChild(cell);
    tableBody.appendChild(row);
    var totalLeaves;
   
    for (var i=0; i<=rowFeildsSplit.length-1; i++) {
      
      
        cell = document.createElement( "TD" );
        cell.className="boxdiv";
        row.appendChild(cell);
        cell.innerHTML = rowFeildsSplit[i];
        cell.width = 150;
     
        
    }
}
function generateRow(oTable,tableBody,rowFeildsSplit,index) {
    
    //alert("rowFeildsSplit"+rowFeildsSplit[rowFeildsSplit.length-5]);
    var row;
    var cell;
    row = document.createElement("TR");
    // row.className="gridRowEven";
    // cell = document.createElement("TD");
    // cell.className="gridRowEven";
    // cell.innerHTML = index+1;
    //row.appendChild(cell);
    tableBody.appendChild(row);
    var totalLeaves;
   if(oTable.id=="tblEventList"){
       
       cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild(cell);
        cell.innerHTML = rowFeildsSplit[0];
        cell.width = 120;
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild(cell);
        cell.innerHTML = rowFeildsSplit[2];
        cell.width = 120;
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild(cell);
        cell.innerHTML = rowFeildsSplit[12];
        cell.width = 120;
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild(cell);
        cell.innerHTML = rowFeildsSplit[14];
        cell.width = 120;
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild(cell);
        cell.innerHTML = rowFeildsSplit[3]+" - "+rowFeildsSplit[4]+" "+rowFeildsSplit[5];
        cell.width = 120;
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild(cell);
        cell.innerHTML = rowFeildsSplit[6];
        cell.width = 120;
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild(cell);
        cell.innerHTML = "<a href='javascript:getBridgeComments(\""+rowFeildsSplit[7]+"\")'>Click to view</a>";
        cell.width = 120;
          cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild(cell);
        cell.innerHTML = "<a href='javascript:getAttendees(\""+rowFeildsSplit[13]+"\")'>Click to view</a>";
        cell.width = 120;
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild(cell);
        var image="";
        if(rowFeildsSplit[10]=="Created"){
                image="yellow2.png";
            }
            else if(rowFeildsSplit[10]=="Canceled"){
                image="red.png";
            }
            else if(rowFeildsSplit[10]=="Completed"){
                image="green.png";
            }
            else if(rowFeildsSplit[10]=="Process"){
                image="blue1.png";
            }
          //  result+="<div id='"+row[0]+"'>"+row[0]+'. '+row[2]+'<img  alt="Checked" src="/Hubble/includes/images/ecertification/'+image+'"  width="20px" height="20px" border="0" ><br>&nbsp;&nbsp;&nbsp;&nbsp;Time : '+row[3]+' - '+row[4]+' '+row[5]+'<br>&nbsp;&nbsp;&nbsp;&nbsp;Created by :'+row[6]+'<br>&nbsp;&nbsp;&nbsp;&nbsp;Comments : <a href="javascript:getBridgeComments(\''+row[7]+'\');">Click to view</a>';
    
        cell.innerHTML = '<img  alt="Checked" src="/Hubble/includes/images/ecertification/'+image+'" title="'+rowFeildsSplit[9]+'" width="20px" height="20px" border="0" >';
        cell.width = 120;
        
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild(cell);
        if(rowFeildsSplit[10]=="Created" && rowFeildsSplit[11]=="allow"){
//                result+='&nbsp;&nbsp;<input type="button" onclick="cancelBridge(\''+row[1]+'\')"; class="cancel" value="Cancel"/>';
           
          cell.innerHTML = '<img  alt="Checked" src="/Hubble/includes/images/closeButton.png" onclick="cancelBridge(\''+rowFeildsSplit[1]+'\')"; width="20px" height="20px" border="0" >';;
         }
        cell.width = 120;
   }else{
    for (var i=0; i<=rowFeildsSplit.length-1; i++) {
      
      
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild(cell);
        cell.innerHTML = rowFeildsSplit[i];
        cell.width = 120;
      if(oTable.id=="tblEventList"){
          if(i==6){
               cell.style="text-align:right;width:auto;";
            cell.innerHTML="$&nbsp;"+moneyFormat(rowFeildsSplit[i]);
          }
          
      }
        
    }
   }
}
function generateFooter(tbody,oTable) {
    var row;
    var cell;
    var totalVal =0;
    row = document.createElement("TR");
    row.className="gridPager";
    tbody.appendChild(row);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.colSpan = "10";
    cell.align="right";
     cell.id="footer"+oTable.id;
    row.appendChild(cell);
}
function generateNoRecords(tbody,oTable) {
    //alert("In generateNoRecords");
    var noRecords =document.createElement("TR");
    noRecords.className="gridRowEven";
    tbody.appendChild(noRecords);
    cell = document.createElement("TD");
    cell.className="gridColumn";
    
    if(oTable.id == "tblEventList"){
        cell.colSpan = "10";
    }

    cell.innerHTML = "No Records Found for this Search";
    noRecords.appendChild(cell);
}
function getAvailableBridges(){
    
   // document.getElementById("loadAvalable").style.display="block";
      $("#addTR").hide();
    document.getElementById("mainResultMessage").value="";
     $("#searchTR").hide();
    var bridgeDate = document.getElementById('bridgeDate').value;
    var startTime = document.getElementById('startTime').value;
    var midDayFrom = document.getElementById('midDayFrom').value;
    var timeZone = document.getElementById('timeZone').value;
    var duration = document.getElementById('duration').value;
    var durationType = document.getElementById('durationType').value;
    if(bridgeDate==''){
       // alert("please enter date.");
        document.getElementById("mainResultMessage").innerHTML="<font size='2' color='red'>please enter date!</font>";
        return false;
    }
     if(startTime==''){
       // alert("please enter start time.");
         document.getElementById("mainResultMessage").innerHTML="<font size='2' color='red'>please enter start time!</font>";
        return false;
    }
     if(duration==''){
       // alert("please enter duration.");
        document.getElementById("mainResultMessage").innerHTML="<font size='2' color='red'>please enter duration!</font>";
        return false;
    }
    else{
        if(durationType=='Min'){
            if(parseInt(duration)<15){
               document.getElementById("mainResultMessage").innerHTML="<font size='2' color='red'>Minimum duration is 15 minutes!</font>";
        return false;  
            }
        }else{
            if(parseInt(duration)>8){
               document.getElementById("mainResultMessage").innerHTML="<font size='2' color='red'>Maximum duration is 8 hrs!</font>";
        return false;  
            }
        }
    }
    document.getElementById("mainResultMessage").innerHTML="";
     $("#availableTr").show();
    var req = newXMLHttpRequest();
      clearTable(document.getElementById("tblbridges"));
    req.onreadystatechange = readyStateHandlerTxt(req,getAvailableBridgesResponse); 

    var url=CONTENXT_PATH+'/getAvailableBridges.action?bridgeDate='+bridgeDate+'&startTime='+startTime+'&midDayFrom='+midDayFrom+'&timeZone='+timeZone+'&duration='+duration+'&durationType='+durationType;
    //alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function getAvailableBridgesResponse(resText){
   // document.getElementById("loadAvalable").style.display="none";
   if(resText=='lessThanCurrentDate'){
         $("#availableTr").hide();
       //alert("<font size='2' color='red'>Given date and time must be greater than current date and time!</font>");
       document.getElementById("mainResultMessage").innerHTML="<font size='2' color='red'>Given date and time must be greater than current date and time!</font>";
       return false;
   }
   if(resText=='nodata'){
         $("#availableTr").hide();
       document.getElementById("mainResultMessage").innerHTML="<font size='2' color='red'>Bridges are not available for given date and time!</font>";
       return false;
   }
   
    var result='';
     var resTextSplit2=new Array();
     var oTable = document.getElementById("tblbridges");
         var headerFields = new Array("")
         tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
        var count=0;
        resTextSplit2[0]="";
        generateTableHeaderForAvailableBridges(tbody,headerFields);
          
       
       // generateRow(oTable,tbody,resTextSplit2,0);
    if(resText!='nodata'){
        var datarows=resText.split('*@!');
        for(var i=0;i<datarows.length-1;i++){
            var data=datarows[i].split('#^$');
            var image="";
            if(data[4]=="Active"){
                image="green.png";
            }
            else if(data[4]=="InActive") {
                image="red.png";
            }
            else if(data[4]=="Issue") {
                image="yellow2.png";
            }
            
             //resTextSplit2[count]="<div class='boxdiv'>"+data[0]+". <b>"+data[2]+"</b><img  src='/Hubble/includes/images/ecertification/"+image+"'  width='20px' height='20px' border='0' ><br>&nbsp;&nbsp;&nbsp;&nbsp;<font size='2'>"+data[3]+"</font></div>";
              resTextSplit2[count]=""+data[0]+". <b><a href='javascript:getBcodeAndNumber(\""+data[2]+"\",\""+data[3]+"\",\""+data[5]+"\");' >"+data[2]+"</a></b><img title='"+data[4]+"' src='/Hubble/includes/images/ecertification/"+image+"'  width='20px' height='20px' border='0' ><br>&nbsp;&nbsp;&nbsp;&nbsp;<font size='2'>"+data[3].replace(/\ /g,"&nbsp;");+"</font>";
            //result+="<div class='boxdiv'>"+data[0]+". <b>"+data[2]+"</b><img  src='/Hubble/includes/images/ecertification/"+image+"'  width='20px' height='20px' border='0' ><br>&nbsp;&nbsp;&nbsp;&nbsp;<font size='2'>"+data[3]+"</font></div>"
        if((i+1)%4==0){
           // alert("test")
             generateRowAvailableBridges(oTable,tbody,resTextSplit2,0);
             resTextSplit2=new Array();
             count=-1;
        }
        count++;
    }
    //alert(resTextSplit2.length)
    if(resTextSplit2.length>0)
        generateRowAvailableBridges(oTable,tbody,resTextSplit2,0);
      //  generateTableHeader(tbody,headerFields);
       // var resTextSplit1 = resText.split("*@!");
     //   for(var index=0;index<resTextSplit1.length-1;index++) {
            //resTextSplit2 = resTextSplit1[index].split("#^$");
          //  generateRow(oTable,tbody,resTextSplit2,index);
      //  }
      //  generateFooter(tbody,oTable);
    }
    document.getElementById("resultData").innerHTML=result;
       $('.pagination-btn').click();
}
function mytoggleOverlayForBmsBridge(){
    showRow('addingTr');
    hideRow('updatingTr');
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Add Bridge";
    var overlay = document.getElementById('overlayForBridge');
    var specialBox = document.getElementById('specialBoxForBridge');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
        document.frmDBGrid.submit();
    //window.location = 'reviewSearch.action?startDate='+startDate+'&endDate='+endDate+'&reviewType='+reviewType+'&reviewStatus='+reviewStatus+'&empnameById='+empnameById;
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}
        
function addBridgeDetails(){
    //  alert("hii");
    var overlayBridgeCode = document.getElementById('overlayBridgeCode').value;
    var res = overlayBridgeCode.charAt(0);
    var overlayBridgeNumber = document.getElementById('overlayBridgeNumber').value;
    var overlayBridgeName = document.getElementById('overlayBridgeName').value;
    var overlayBridgeStatus = document.getElementById('overlayBridgeStatus').value;
    var passCode=document.getElementById('overlayPassCode').value;
      document.getElementById('resultMessage').style.display="block";
      document.getElementById('resultMessage').innerHTML="";
  overlayBridgeNumber=overlayBridgeNumber.replace(/\+/g,"%2B");
 
//    if(overlayBridgeCode.length==0){
//        document.getElementById('resultMessage').innerHTML = "<font color=red>Please Enter Bridge Code.</font>";
//        return false;
//    }
    if(overlayBridgeNumber.length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please Enter Bridge Number.</font>";
        return false;
    }else if(passCode.length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please Enter Pass code.</font>";
        return false;
  
    }else if(overlayBridgeName.length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please Enter Bridge Name.</font>";
        return false;
    } else {
        if(overlayBridgeNumber.length<11){
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please give atleast 10 digits for Bridge Number.</font>";
        return false;
        }
        if(passCode.length<6){
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please give atleast 6 digits for Pass code.</font>";
        return false;
        }
         document.getElementById("load").style.display = 'block';
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerTxt(req,getBridgeDetailsResponse); 
        var url=CONTENXT_PATH+'/addBMSBridgeDetails.action?bridgeCode='+overlayBridgeCode+'&bridgeNumber='+overlayBridgeNumber+'&bridgeName='+overlayBridgeName+'&bridgeStatus='+overlayBridgeStatus+'&passCode='+passCode;
        //alert(url);
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }

}
function getBridgeDetailsResponse(resText){
    //alert(resText);
    document.getElementById('resultMessage').innerHTML = resText;
     document.getElementById("load").style.display = 'none';
}
function toggleEditOverlayForBmsBridge(bcode){
    showRow('updatingTr');
    hideRow('addingTr');
    //showRow('bridgeCodeLable');
  //  showRow('bridgeCodeField');
  //  alert("test")
    document.getElementById('overlayBridgeStatus').contentEditable=false;
    var overlay = document.getElementById('overlayForBridge');
    var specialBox = document.getElementById('specialBoxForBridge');
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Edit Bridge Details";
    document.getElementById("update").style.display="block";
    document.getElementById("adding").style.display="none";
    document.getElementById("load").style.display = 'block';
    $.ajax({
        url:'getBmsBridgeDetails.action?bridgeCode='+bcode,
        context: document.body,
        success: function(responseText) {
            //  alert(responseText);
            var json = $.parseJSON(responseText);
                
            var BCode = json["BCode"];
            var BNumber = json["BNumber"];
            var BName = json["BName"];
            var STATUS = json["STATUS"];
            var PassCode = json["PassCode"];
        
            document.getElementById("overlayBridgeCode").value = BCode;
            document.getElementById("overlayBridgeNumber").value = BNumber;
            document.getElementById("overlayBridgeName").value = BName;
            document.getElementById("overlayBridgeStatus").value = STATUS;
            document.getElementById("overlayPassCode").value = PassCode;
            document.getElementById("load").style.display = 'none';  
            document.getElementById("resultMessage").style.display = 'none';  
            document.getElementById("overlayBridgeCode").readOnly=true;
            
                
        }, 
        error: function(e){
            document.getElementById("load").style.display = 'none';
            alert("error-->"+e);
        }
    });
    
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}
function hideRow(id) {
    //alert(id);
    var row = document.getElementById(id);
    row.style.display = 'none';
}
function showRow(id) {
    //  alert(id);
    var row = document.getElementById(id);
    row.style.display = '';
} 
function upadteBMSBridgeDetails(){
    // alert("hai");
    var bridgeCode=  document.getElementById("overlayBridgeCode").value;
    
    var overlayBridgeNumber = document.getElementById('overlayBridgeNumber').value;
    var overlayBridgeName = document.getElementById('overlayBridgeName').value;
    var overlayBridgeStatus = document.getElementById('overlayBridgeStatus').value;
     var passCode=document.getElementById('overlayPassCode').value;
    document.getElementById('resultMessage').style.display="block";
      document.getElementById('resultMessage').innerHTML ='';
     overlayBridgeNumber=overlayBridgeNumber.replace(/\+/g,"%2B");
      if(overlayBridgeNumber.length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please Enter Bridge Number.</font>";
        return false;
    }else if(passCode.length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please Enter Pass code.</font>";
        return false;
  
    }else if(overlayBridgeName.length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please Enter Bridge Name.</font>";
        return false;
    }
    // alert(overlayBridgeNumber);
     //document.getElementById('resultMessage').innerHTML=overlayBridgeNumber;
      document.getElementById('load').style.display="block";
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerTxt(req,ppulateBMSBridgeUpadteResult); 
    var url=CONTENXT_PATH+'/updateBMSBridgeDetails.action?bridgeCode='+bridgeCode+'&bridgeNumber='+overlayBridgeNumber+'&bridgeName='+overlayBridgeName+'&bridgeStatus='+overlayBridgeStatus+'&passCode='+passCode;
    //alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
   

}   
function ppulateBMSBridgeUpadteResult(resText){
    document.getElementById('resultMessage').innerHTML = resText;
    document.getElementById('resultMessage').style.display="block";
    document.getElementById('load').style.display="none";
    //  alert("Updated Successfully");
    toggleOverlay();
}
function isBridgeExist(){
    $("#correctImg").hide();
    $("#wrongImg").hide();
    var bridgeCode=document.getElementById("overlayBridgeCode").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerTxt(req,isBridgeExistResponse); 

    //var url=CONTENXT_PATH+"/updateMyReview.action?reviewId="+reviewId+"&overlayReviewType="+overlayReviewType+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay;
    // var url=CONTENXT_PATH+'/upadtePayrollTaxExemption.action?overLayExemptionType='+overLayexemptionType+'&overlayApprovedAmount='+overlayApprovedAmount+'&overLayStatus='+overLaystatus+'&comments='+comments+'&exemptionId='+exemptionId+"&paymentDateEmp="+paymentDateEmp;
    var url=CONTENXT_PATH+'/isActiveBridge.action?bridgeCode='+bridgeCode;
    // alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function isBridgeExistResponse(resText){
    //alert(resText);
    
    if(resText=='Yes'){
        // document.getElementById("correctImg").style.display="block";
        
        $("#wrongImg").show();
        document.getElementById("bookButton").disabled =true;
    }else{
        //document.getElementById("wrongImg").style.display="block";
        $("#correctImg").show();
        document.getElementById("bookButton").disabled =false;
    }
    
    
}
function calendarDetails(event){
    
    if(event.id=="downImage"){
        $("#calendarfrm").show(500);
        $("#downImage").hide();
        $("#upImage").show();
    }else{
         $("#calendarfrm").hide(500);
          $("#downImage").show();
        $("#upImage").hide();
    }
}
       
  
   
//$("#downImage").click(function(){
//        $("#calendarfrm").show();
//    });

function doAvailableBridgeCheck(){
   // alert("test")
  var bridgeDate = document.getElementById('hiddenbridgeDate').value;
    var startTime = document.getElementById('avlstartTime').value;
    var midDayFrom = document.getElementById('avlmidDayFrom').value;
    var endTime = document.getElementById('avlendTime').value;
    var midDayTo = document.getElementById('avlmidDayTo').value;
    var timeZone = document.getElementById('avltimeZone').value;
    var bridgeCode = document.getElementById('avlbridgeCode').value;
    document.getElementById("loadimg").style.display="block";
    if(bridgeDate.length==0){
        document.getElementById("loadimg").style.display="none";
        document.getElementById("avlResultMessage").style.display="block";
        document.getElementById("avlResultMessage").innerHTML="<font size='2' color='red'>please select bridge date.</font>";
        
        return false;
    }
     if(bridgeCode.length==0){
        document.getElementById("loadimg").style.display="none";
        document.getElementById("avlResultMessage").style.display="block";
        document.getElementById("avlResultMessage").innerHTML="<font size='2' color='red'>please enter bridge code.</font>";
        
        return false;
    }
    if(startTime==''){
         document.getElementById("loadimg").style.display="none";
        document.getElementById("avlResultMessage").style.display="block";
        document.getElementById("avlResultMessage").innerHTML="<font size='2' color='red'>please enter start time.</font>";
        
        return false;
    }
    else{
        startTime=startTime+":00";
    }
    if(endTime==''){
         document.getElementById("loadimg").style.display="none";
        document.getElementById("avlResultMessage").style.display="block";
        document.getElementById("avlResultMessage").innerHTML="<font size='2' color='red'>please enter end time.</font>";
        
        return false;
    }
    else{
        endTime=endTime+":00";
    }
    if(midDayFrom==midDayTo){
        var startHour=startTime.substring(0,2);
        var endHour=endTime.substring(0,2);
   //alert(parseInt(startHour)+" "+parseInt(endHour));
        if(parseInt(startHour)<=parseInt(endHour)){
            var startMin=startTime.substring(3,5);
            var endMin=endTime.substring(3,5);
            if(parseInt(startHour)==parseInt(endHour)){
            if(parseInt(startMin)>=parseInt(endMin)){
                document.getElementById("loadimg").style.display="none";
                document.getElementById("avlResultMessage").style.display="block";
                document.getElementById("avlResultMessage").innerHTML="<font size='2' color='red'>End time must be greater than start time.</font>";
        
                return false;
            }
            }
        }else{
            document.getElementById("loadimg").style.display="none";
                document.getElementById("avlResultMessage").style.display="block";
                document.getElementById("avlResultMessage").innerHTML="<font size='2' color='red'>End time must be greater than start time.</font>";
        
                return false;
        }
    
      
    }
    else{
        if(midDayFrom=="PM" && midDayTo=="AM"){
            document.getElementById("loadimg").style.display="none";
            document.getElementById("avlResultMessage").style.display="block";
            document.getElementById("avlResultMessage").innerHTML="<font size='2' color='red'>End time must be greater than start time.</font>";
        
            return false;
        }
        
    }
   
   
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerTxt(req,doAvailableBridgeCheckResponse); 

        //var url=CONTENXT_PATH+"/updateMyReview.action?reviewId="+reviewId+"&overlayReviewType="+overlayReviewType+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay;
        // var url=CONTENXT_PATH+'/upadtePayrollTaxExemption.action?overLayExemptionType='+overLayexemptionType+'&overlayApprovedAmount='+overlayApprovedAmount+'&overLayStatus='+overLaystatus+'&comments='+comments+'&exemptionId='+exemptionId+"&paymentDateEmp="+paymentDateEmp;
        var url=CONTENXT_PATH+'/doAvailableBridgeCheck.action?bridgeDate='+bridgeDate+'&startTime='+startTime+'&midDayFrom='+midDayFrom+'&endTime='+endTime+'&midDayTo='+midDayTo+"&timeZone="+timeZone+"&bridgeCode="+bridgeCode;
        // alert(url);
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    
    
   
}
function doAvailableBridgeCheckResponse(resText){
    document.getElementById("avlResultMessage").style.display="block";
                document.getElementById("avlResultMessage").innerHTML=resText;
                 document.getElementById("loadimg").style.display="none";
}
 function getBridgeDetailsSearch(){
     
     $("#addTR").hide();
     $("#availableTr").hide();
    document.getElementById("mainResultMessage").innerHTML="";
      
   var startTime = document.getElementById('startTime').value;
    var midDayFrom = document.getElementById('midDayFrom').value;
    var timeZone = document.getElementById('timeZone').value;
    //var bridgeCode = document.getElementById('bridgeCodeSearch').value;
    var bridgeDate = document.getElementById('bridgeDate').value;
    document.getElementById("tblEventList").style.display="none";
if(bridgeDate==''){
       // alert("please enter date.");
        document.getElementById("mainResultMessage").innerHTML="<font size='2' color='red'>please enter date!</font>";
        document.getElementById("tblload").style.display="none";
        $("#searchTR").hide();
        return false;
    }
    document.getElementById("tblload").style.display="block";
     $("#searchTR").show();
//    else{
//        startTime=startTime+":00";
//    }
    tempbridgeDate=bridgeDate;
      
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerTxt(req,bridgeListResponse); 
    var url=CONTENXT_PATH+'/getBridgeEventSearchDetails.action?bridgeDate='+bridgeDate+'&startTime='+startTime+'&midDayFrom='+midDayFrom+"&timeZone="+timeZone;
    // alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
 function formatPhone(num) { 
    
   // str = new String(document.frmAccForm.phone.value);
    str = document.getElementById("overlayBridgeNumber").value;
   // alert(str);
   document.getElementById("overlayBridgeNumber").value=str.replace(/[A-Za-z\(\)\.\-\x\s,]/g, "");
    num=document.getElementById("overlayBridgeNumber").value;
    if(!num.contains("+1")){
      //  alert(num);
        num="+1"+num;
    }
    if(num.length == 12) { 
        _return="(";
        var ini = num.substring(0,2);
        _return+=ini+")";
        var st = num.substring(2,5);
        _return+="-"+st;
        var st1 = num.substring(5,8);
        _return+="-"+st1+"-";
        var end = num.substring(8,12);
        _return+=end;
        
       document.getElementById("overlayBridgeNumber").value="";
       document.getElementById("overlayBridgeNumber").value =_return;
        
    }else if(num.length > 12) {
        var num = num.substring(0, 12);
     _return="(";
        var ini = num.substring(0,2);
        _return+=ini+")";
        var st = num.substring(2,5);
        _return+="-"+st;
        var st1 = num.substring(5,8);
        _return+="-"+st1+"-";
        var end = num.substring(8,12);
        _return+=end;
        alert('Bridge Number should be 12 digits');
        document.getElementById("overlayBridgeNumber").value="";
        document.getElementById("overlayBridgeNumber").value =_return;
        document.getElementById("overlayBridgeNumber").focus();
       
    }else if(num.length < 12) {
        alert('Please give atleast 12 digits for Bridge Number.');
         return false;
       // document.frmAccForm.phone.value ="";
    }
    
    return _return;
}
  function enterdTime(element) {
    var x = element;

var f=x.value;

if(f.length==1 && f>1){
//alert("0"+f);
f="0"+f;
}
if(f.length==2){
var s=f.substring(1,2);
//alert(s);
if(s==":" || s==" "){
x.value ="0"+f.substring(0,1)+":";
}
else{
x.value = f+":";
}
   }
else {
x.value=f;
}

}


function timeValidator(element) {
    var x = element;
var f=x.value;

if(f.length==1){

    f = "0"+f+":";

   }
   
var s=f.split(':');



if((s[0]>=0) && (s[0]<13) && ((s[1]>=0) && (s[1]<60)) ){


if(s[1]==""){

x.value =s[0]+':'+"00";
}
else if(s[1].length==1){
    x.value =s[0]+':'+"0"+s[1];
   // s[1]="0"+s[1];
}
else
{
x.value =s[0]+':'+s[1];
}
}
else{
    x.value ="";
    alert('Enter time in 12 hours based')
}
}
 function isNumberKey(evt) {
 
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode!=46 && charCode > 31 && (charCode < 48 || charCode > 57))
    {
        alert("Please enter numeric value");
        return false;
    }else {
        return true;
    }
}
function getBcodeAndNumber(bCode,bNumber,passCode){
    
     document.getElementById("bridgeCode").value =bCode;
     document.getElementById("bridgeNumber").value =bNumber;
     document.getElementById("description").value ="";
      document.getElementById("passCode").value =passCode;
     document.getElementById("resultMessage").innerHTML ="";
     
     document.getElementById("externalAttendees").value ="";
       $('#internalAttendees').selectivity('clear');
     $("#addTR").show();
     $("#availableTr").hide();
     $("#searchTR").hide();
     
}
function createBridgeIssue(){
    
  document.getElementById("IssueResultMessage").innerHTML="";
     $("#addTR").hide();
     $("#availableTr").hide();
     $("#searchTR").hide();
     
    document.getElementById("mainResultMessage").innerHTML="";
      document.getElementById("loadAvalable").style.display="block";
   var bridgeCode = document.getElementById('bridgeCodeIssue').value;
    var priority = document.getElementById('priority').value;
    var assignTo = document.getElementById('assignTo').value;
    //var bridgeCode = document.getElementById('bridgeCodeSearch').value;
    var comments = document.getElementById('comments1').value;
    document.getElementById("tblEventList").style.display="none";
      if(bridgeCode==''){
          
            document.getElementById("IssueResultMessage").innerHTML="<font size='2' color='red'>please enter bridge extension!</font>";
        document.getElementById("loadAvalable").style.display="none";
          return false;
      }
      if(comments==''){
          
            document.getElementById("IssueResultMessage").innerHTML="<font size='2' color='red'>please enter bridge comments!</font>";
        document.getElementById("loadAvalable").style.display="none";
          return false;
      }
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerTxt(req,createBridgeIssueResponse); 
    var url=CONTENXT_PATH+'/createBridgeIssue.action?bridgeCode='+bridgeCode+'&priority='+priority+'&assignTo='+assignTo+"&comments="+comments;
    // alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function createBridgeIssueResponse(resTxt){
    //alert(resTxt);
    
    document.getElementById("IssueResultMessage").innerHTML =resTxt;
     document.getElementById("loadAvalable").style.display="none";
}
function maiilValidate(element)
{
document.getElementById("resultMessage").innerHTML ="";
var regexEmail = /\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
var email = element.value;
var lastval=email.substring(email.length-1,email.length);
//alert(lastval);
if(lastval==','){
email=email.substring(0,email.length-1).trim();
}
element.value=email;
var x = new Array();
var i;
x = email .split(",");
for(i=0;i<=x.length-1;i++)
{

if (regexEmail.test(x[i])) {
   //document.getElementById("result").innerHTML = "the email is valid";
}
else {
    document.getElementById("resultMessage").innerHTML = "<font color='red' size=2> <b>"+x[i]+ "</b> not valid email.</font>";
    return false;
}
}
}


 function minHrs(){
    
    var duration=document.getElementById("duration").value;
    var durationType=document.getElementById("durationType").value;
    
    if(durationType=='Min'){
        if(parseInt(duration)>60){
            alert("You have entered wrong Minutes");
            return false;
        }
    }else{
        if(parseInt(duration)>24){
            alert("You have entered wrong Hours");
            return false;
        }
    }
    
    
}