/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * By harsha 
 * For Constant contact
 */
function clearTable(tableId) {
    var tbl =  tableId;
   
    var lastRow = tbl.rows.length; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}
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


function getContactListSearch() {
    // var name=document.getElementById("activityName").value;resMesg
    document.getElementById("btnContactList").disabled = true;
    (document.getElementById("loadingMessage1")).style.display = "block";
    document.getElementById('resMesg').innerHTML ="";
    var oTable = document.getElementById("ContactListSearchTbl");
    clearTable(oTable);
    var constantContactId = document.getElementById("constantContactId").value;
    var  startDate=document.getElementById("startDate").value;
    var  endDate=document.getElementById("endDate").value;                                                            
 
    
    //alert("Haii-->"+campaignId);
    //  alert(campaignId);
    //alert("name::"+name);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerTextt(req,displayConstantContactListResult); 
    
    var url = CONTENXT_PATH+"/getConstantContactListSearch.action?constantContactId="+constantContactId+"&startDate="+startDate+"&endDate="+endDate;
    //var url = CONTENXT_PATH+"/allActivitySearch.action?&actAccId="+accId;
    //alert(url);
 
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}
function readyStateHandlerTextt(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadingMessage1")).style.display = "none";
                responseTextHandler(req.responseText);
                alert(req.responseText);
            }
            else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
                 (document.getElementById("loadingMessage1")).style.display = "none";
                document.getElementById("btnContactList").disabled = false;
                document.getElementById('resMesg').innerHTML = "<font color='red'>Issue with constant contact service...</font>";
            }
        }else {
            (document.getElementById("loadingMessage1")).style.display = "block";
        }
    }
}

function readyStateHandlerTxt(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadingMessage")).style.display = "none";
                responseTextHandler(req.responseText);
          
            }
            else {
                (document.getElementById("loadingMessage")).style.display = "none";
                document.getElementById('resultMessage').innerHTML = "<font color=red>Issue due Constant Contact</font>";
                alert("HTTP error ---"+req.status+" : "+req.statusText);
                document.getElementById("btnSynchronize").disabled = false;
            }
        }else {
            (document.getElementById("loadingMessage")).style.display = "block";
        }
    }
}

function displayConstantContactListResult(resText) 
{
    document.getElementById("btnContactList").disabled = false;
     
    if(resText.length !=0)
    {
        if(resText=="NoData"){
      alert("No Records Found");
           document.getElementById('resMesg').innerHTML = "<font color='red'>No Records Found...</font>";
        }
        else if(resText=="Issue"){
         document.getElementById('resMesg').innerHTML = "<font color='red'>Issue with constant contact service...</font>";
    }
        else{
        var oTable = document.getElementById("ContactListSearchTbl");
       
        clearTable(oTable);
      
            
        var headerFields = new Array("SNo","Contact List Name","Contact List Size","Created Date","Stauts");	
               
   
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
       
        var resTextSplit1 = resText.split("*@!");

        generateTableHeader(tbody,headerFields);
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#^$");
            
            generateRow1(tbody,resTextSplit2,index);
            
        }  
        generateFooter(tbody);
    }}
    else {
        alert("No Records Found");
    }
    
   
 
}
function generateFooter(tbody,oTable) {
  
    var cell;
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
   
    if(oTable.id=="campSearchTbl"){
    
        cell.colSpan = "10";
    }
    else{
        cell.colSpan = "7";
    }
   

    footer.appendChild(cell);
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

        cell.setAttribute("width","5%");
        cell.innerHTML = headerFields[i];
    }
}

function generateRow1(tableBody,rowFeildsSplit,index){
    var row;
    var cell;
    row = document.createElement("TR");
    row.className="gridRowEven";
    cell = document.createElement("TD");
    cell.className="gridRowEven";
    cell.innerHTML = index+1;
    cell.setAttribute("width","5%");
    cell.setAttribute('align','left');
    row.appendChild(cell);

    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    cell.appendChild(document.createTextNode(rowFeildsSplit[1]));
    row.appendChild(cell);
    cell.setAttribute("width","20%");
    cell.setAttribute('align','left');
        
    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    cell.appendChild(document.createTextNode(rowFeildsSplit[4]));
    row.appendChild(cell);
    cell.setAttribute("width","20%");
    cell.setAttribute('align','left');
        
    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    cell.appendChild(document.createTextNode(rowFeildsSplit[2].substring(0, 10)));
    row.appendChild(cell);
    cell.setAttribute("width","20%");
    cell.setAttribute('align','left');


    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    cell.appendChild(document.createTextNode(rowFeildsSplit[3]));
    row.appendChild(cell);
    cell.setAttribute("width","20%");
    cell.setAttribute('align','left');

    tableBody.appendChild(row);
}




function getCampaignListSearch() {
    // var name=document.getElementById("activityName").value;resMessage
    document.getElementById('resMessage').innerHTML ="";
    document.getElementById("btnCampaignList").disabled = true;
    (document.getElementById("loadingMessage2")).style.display = "block";
    var oTable = document.getElementById("campSearchTbl");
  
    clearTable(oTable);

    var  startDate=document.getElementById("campaignStartDate").value;
    var  endDate=document.getElementById("campaignEndDate").value;                                                            
 
    (document.getElementById("pag1")).style.display = "none";
    $('span.pagination').empty().remove();
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerTextt2(req,displayCampaignListRes); 
    
    var url = CONTENXT_PATH+"/getCampaignContactListSearch.action?startDate="+startDate+"&endDate="+endDate;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}
function generateTableHeader1(tableBody,headerFields) {
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridHeader";
    tableBody.appendChild( row );
    for (var i=0; i<headerFields.length; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridHeader";
        row.appendChild( cell );

        cell.setAttribute("width","5%");
        cell.setAttribute('align','center');
        cell.innerHTML = headerFields[i];
    }
}
function displayCampaignListRes(resText) 
{
 
    if(resText.length !=0)
    {
        if(resText=="NoData"){
      alert("No Records Found");
           document.getElementById('resMessage').innerHTML = "<font color='red'>No Records Found...</font>";
        }
        else if(resText=="Issue"){
         document.getElementById('resMessage').innerHTML = "<font color='red'>Issue with constant contact service...</font>";
    }
        else{
        var oTable = document.getElementById("campSearchTbl");
       
        clearTable(oTable);
      
            
        var headerFields = new Array("SNo","Campaign Name","Modified Date","Stauts","Tracking Service");	
               
    
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
       
        var resTextSplit1 = resText.split("*@!");

        generateTableHeader1(tbody,headerFields);
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#^$");
            
            generateRow2(tbody,resTextSplit2,index);
            
        }
        generateFooter(tbody,oTable);
        (document.getElementById("pag1")).style.display = "block";
    }}
    else {
        alert("No Records Found");
    }
    
    document.getElementById("btnCampaignList").disabled = false;
    pagerOption1();
}


function generateRow2(tableBody,rowFeildsSplit,index){
    
    var row;
    var cell;
    row = document.createElement("TR");
    row.className="gridRowEven";
    
    cell = document.createElement("TD");
    cell.className="gridRowEven";
    cell.innerHTML = index+1;
    cell.setAttribute("width","10%");
    cell.setAttribute('align','center');
    row.appendChild(cell);
   
 
        
        
    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    cell.appendChild(document.createTextNode(rowFeildsSplit[1]));
    row.appendChild(cell);
    cell.setAttribute("width","30%");
    cell.setAttribute('align','center');



    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    cell.appendChild(document.createTextNode(rowFeildsSplit[2].substring(0, 10)));
    row.appendChild(cell);
    cell.setAttribute("width","20%");
    cell.setAttribute('align','center');
        
   
    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    cell.appendChild(document.createTextNode(rowFeildsSplit[3]));
    row.appendChild(cell);
    cell.setAttribute("width","15%");
    cell.setAttribute('align','center');

    //       
    //cell = document.createElement( "TD" );
    //        cell.className="gridRowEven";
    //       
    //          var j = document.createElement("a");
    //            j.setAttribute("href", "javascript:doGetContactsEmails('"+rowFeildsSplit[0]+"','send')");
    //            j.appendChild(document.createTextNode(rowFeildsSplit[5]));
    //            cell.appendChild(j);
    //               row.appendChild(cell);
    //    cell.setAttribute("width","10%");
    //        cell.setAttribute('align','left');
    //       
    //cell = document.createElement( "TD" );
    //        cell.className="gridRowEven";
    //       
    //          var j = document.createElement("a");
    //            j.setAttribute("href", "javascript:doGetContactsEmails('"+rowFeildsSplit[0]+"','open')");
    //            j.appendChild(document.createTextNode(rowFeildsSplit[6]));
    //            cell.appendChild(j);
    //               row.appendChild(cell);
    //    cell.setAttribute("width","10%");
    //        cell.setAttribute('align','left');
    //       
    //cell = document.createElement( "TD" );
    //        cell.className="gridRowEven";
    //       
    //          var j = document.createElement("a");
    //            j.setAttribute("href", "javascript:doGetContactsEmails('"+rowFeildsSplit[0]+"','bounce')");
    //            j.appendChild(document.createTextNode(rowFeildsSplit[7]));
    //            cell.appendChild(j);
    //               row.appendChild(cell);
    //    cell.setAttribute("width","10%");
    //        cell.setAttribute('align','left');
    //        
    //       
    //cell = document.createElement( "TD" );
    //        cell.className="gridRowEven";
    //       
    //          var j = document.createElement("a");
    //              j.setAttribute("href", "javascript:doGetContactsEmails('"+rowFeildsSplit[0]+"','unsubscribe')");
    //            j.appendChild(document.createTextNode(rowFeildsSplit[8]));
    //            cell.appendChild(j);
    //               row.appendChild(cell);
    //    cell.setAttribute("width","10%");
    //        cell.setAttribute('align','left');
    //        
    //cell = document.createElement( "TD" );
    //        cell.className="gridRowEven";
    //       
    //          var j = document.createElement("a");
    //           j.setAttribute("href", "javascript:doGetContactsEmails('"+rowFeildsSplit[0]+"','forward')");
    //            j.appendChild(document.createTextNode(rowFeildsSplit[9]));
    //            cell.appendChild(j);
    //               row.appendChild(cell);
    //    cell.setAttribute("width","10%");
    //        cell.setAttribute('align','left');

    ////////////////////////////////////
    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    var j = document.createElement("a");
    j.setAttribute("href", "javascript:trackingEmailCampaign('"+rowFeildsSplit[0]+"')");
    j.innerHTML = "<img SRC='../includes/images/go_21x21.gif' WIDTH=18 HEIGHT=15 BORDER=0 ALTER='Click to Add'>";
         
    document.create
    cell.appendChild(j);
    //            j.appendChild(document.createTextNode("<img SRC='../includes/images/go_21x21.gif' WIDTH=18 HEIGHT=15 BORDER=0 ALTER='Click to Add'>"));
    //            cell.appendChild(j);
    row.appendChild(cell);
    cell.setAttribute("width","15%");
    cell.setAttribute('align','center');
    ///////////////////////////////////

    tableBody.appendChild(row);
}

function doGetContactsEmails(element,requirement){
    
    var campaignId = element;
    var requirement = requirement;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerTextCam(req,campaignPopulate);    
    var url = CONTENXT_PATH+"/doGetCampaignEmailsList.action?campaignId="+campaignId+"&requirement="+requirement;
  
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function campaignPopulate(text) {
    //  alert(text);
    var background = "#3E93D4";
    var text1 = text;
    var text2 = text1.split("^");
    var title =text2[0];
    //  alert(text2[1]);
    var s=text2[1].split("|");
    var size = text1.length;
    //   alert("title"+title)
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>";
    content=content+"<body bgcolor='"+background +"' style='color:white;'><font size='5px' >"+title+"</font><br/><br/>";
    var txt="";
    for(var i=0;i<s.length;i++){
        txt=txt+s[i]+"<br/>";
    }
    //alert(txt);
    content=content+txt+"</body></html>";
    
    if(size < 50){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 1000){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size > 1000){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=600,height=400,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}
function readyStateHandlerTextCam(req,responseTextHandler){
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                
                responseTextHandler(req.responseText);
            //   alert(req.responseText);
            } else {
                alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }
    }
} 

function synchronize(){
    
          
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Consultant List";
    var overlay = document.getElementById('overlaySynchronize');
    var specialBox = document.getElementById('specialBoxSynchronize');
          
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
               
    //window.location = 'reviewSearch.action?startDate='+startDate+'&endDate='+endDate+'&reviewType='+reviewType+'&reviewStatus='+reviewStatus+'&empnameById='+empnameById;
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}

function synchronizeToHubble(){
    document.getElementById("btnSynchronize").disabled=true;
    var constantContactId = document.getElementById("constantContId").value;
   
    alert(constantContactId);
    document.getElementById('resultMessage').innerHTML ="";
    (document.getElementById("loadingMessage")).style.display = "block";
    
    if(constantContactId=="-1"){
        
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please select any list which you want's to synchronize with Hubble</font>"; 
        document.getElementById("btnSynchronize").disabled = false;
        (document.getElementById("loadingMessage")).style.display = "none";
        return false;  

    }else{
    
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerTxt(req,displaySynchronizeResponse); 
    
        var url = CONTENXT_PATH+"/doSynchronizeToHubble.action?constantContactId="+constantContactId;
  
 
        req.open("GET",url,"true");    
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
    
    
}
function displaySynchronizeResponse(text){
    if(text=="success"){
        document.getElementById('resultMessage').innerHTML = "<font color=green>Synchronization Successfull...</font>";
    }
    else{
        document.getElementById('resultMessage').innerHTML = text;
    }
    document.getElementById("btnSynchronize").disabled = false;
}

function readyStateHandlerTextt2(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadingMessage2")).style.display = "none";
                responseTextHandler(req.responseText);
               
            }
            else {
                document.getElementById("btnCampaignList").disabled = false;
                 (document.getElementById("loadingMessage2")).style.display = "none";
                 document.getElementById('resMessage').innerHTML = "<font color='red'>Issue with constant contact service...</font>";
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
            (document.getElementById("loadingMessage2")).style.display = "block";
        }
    }
}

function constantContactEmailList(){
    document.getElementById("btnEmailList").disabled = true;
 
    var constantContactId = document.getElementById("constantContactId").value;
    var contactEmailID = document.getElementById("contactEmailID").value;
    
    var oTable = document.getElementById("ContactEmailListTbl");
       
    clearTable(oTable);
    document.getElementById('resultMessage1').innerHTML ="";
    (document.getElementById("loadingMessageCon")).style.display = "block";
    (document.getElementById("pag")).style.display = "none";
    $('span.pagination').empty().remove();
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerListTxt(req,displayConstantContactEmailList); 
    
    var url = CONTENXT_PATH+"/doConstantContactEmailList.action?constantContactId="+constantContactId+"&contactEmailID="+contactEmailID;
    ;
  
 
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    
    
}

function readyStateHandlerListTxt(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadingMessageCon")).style.display = "none";
                 
                  
                responseTextHandler(req.responseText);
              
            }
            else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
                (document.getElementById("loadingMessageCon")).style.display = "none";
                document.getElementById("btnEmailList").disabled = false;
                document.getElementById('resultMessage1').innerHTML = "<font color='red'>Issue with constant contact service...</font>";
            }
        }else {
            (document.getElementById("loadingMessageCon")).style.display = "block";
        //    document.getElementById("btnEmailList").disabled = false;
        }
    }
}


function displayConstantContactEmailList(resText) 
{
 
    if(resText.length !=0)
    {
        if(resText=="NoData"){
            alert("No Records Found");
            document.getElementById('resultMessage1').innerHTML = "<font color='red'>No Records Found...</font>";
        }
        else if(resText=="Issue"){
            document.getElementById('resultMessage1').innerHTML = "<font color='red'>Issue with constant contact service...</font>";
        }
        else{
            var oTable = document.getElementById("ContactEmailListTbl");
       
            clearTable(oTable);
      
            
            var headerFields = new Array("SNo","Contact First Name","Contact Last Name","Created Date","Email","Status");	
               
    
            tbody = document.createElement("TBODY");
            oTable.appendChild(tbody);
       
            var resTextSplit1 = resText.split("*@!");

            generateTableHeader(tbody,headerFields);
            for(var index=0;index<resTextSplit1.length-1;index++) {
                resTextSplit2 = resTextSplit1[index].split("#^$");
            
                generateRow3(tbody,resTextSplit2,index);
            
            }
            (document.getElementById("pag")).style.display = "block";
            generateFooter(tbody,oTable);
        }
    
    }
    else {
        alert("No Records Found");
    }
    document.getElementById("btnEmailList").disabled = false;
    pagerOption();
    
}

function generateRow3(tableBody,rowFeildsSplit,index){
    
    var row;
    var cell;
    row = document.createElement("TR");
    row.className="gridRowEven";
    cell = document.createElement("TD");
    cell.className="gridRowEven";
    cell.innerHTML = index+1;
    cell.setAttribute("width","5%");
    cell.setAttribute('align','left');
    row.appendChild(cell);
   
 
        
        
    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    cell.appendChild(document.createTextNode(rowFeildsSplit[0]));
    row.appendChild(cell);
    cell.setAttribute("width","20%");
    cell.setAttribute('align','left');



    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    cell.appendChild(document.createTextNode(rowFeildsSplit[1]));
    row.appendChild(cell);
    cell.setAttribute("width","20%");
    cell.setAttribute('align','left');
        
    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    cell.appendChild(document.createTextNode(rowFeildsSplit[4].substring(0, 10)));
    row.appendChild(cell);
    cell.setAttribute("width","15%");
    cell.setAttribute('align','left');
        
    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    cell.appendChild(document.createTextNode(rowFeildsSplit[3]));
    row.appendChild(cell);
    cell.setAttribute("width","15%");
    cell.setAttribute('align','left');

       
    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    cell.appendChild(document.createTextNode(rowFeildsSplit[2]));
    row.appendChild(cell);
    cell.setAttribute("width","15%");
    cell.setAttribute('align','left');
    tableBody.appendChild(row);
}



function getSyncListSearch() {
   
    document.getElementById("btnContactSync").disabled = true;
    (document.getElementById("loadingMessage3")).style.display = "block";
    document.getElementById('resultMessage2').innerHTML ="";
    var oTable = document.getElementById("SynchronizeListTbl");
    clearTable(oTable);
    var loactionName = document.getElementById("loactionName").value;
                                                            
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerSync(req,displaySyncListResult); 
    
    var url = CONTENXT_PATH+"/doGetSyncListSearch.action?loactionName="+loactionName;
     
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function readyStateHandlerSync(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadingMessage3")).style.display = "none";
                responseTextHandler(req.responseText);
                alert(req.responseText);
            }
            else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
                document.getElementById("btnContactSync").disabled = false;
            }
        }else {
            (document.getElementById("loadingMessage3")).style.display = "block";
        }
    }
}


function displaySyncListResult(resText) 
{
    document.getElementById("btnContactSync").disabled = false;
    if(resText.length !=0)
    {
        var oTable = document.getElementById("SynchronizeListTbl");
       
        clearTable(oTable);
      
            
        var headerFields = new Array("SNo"," Location Name in Hubble","No.Of Hubble Contacts ","No.Of Constant Contacts","Syncing");	
               
   
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
       
        var resTextSplit1 = resText.split("*@!");

        generateTableHeader(tbody,headerFields);
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#^$");
            
            generateRow4(tbody,resTextSplit2,index);
            
        }  
        generateFooter(tbody);
    }
    else {
        alert("No Records Found");
    }
}


function generateRow4(tableBody,rowFeildsSplit,index){
    var row;
    var cell;
    row = document.createElement("TR");
    row.className="gridRowEven";
    cell = document.createElement("TD");
    cell.className="gridRowEven";
    cell.innerHTML = index+1;
    cell.setAttribute("width","5%");
    cell.setAttribute('align','left');
    row.appendChild(cell);

    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    cell.appendChild(document.createTextNode(rowFeildsSplit[0]));
    row.appendChild(cell);
    cell.setAttribute("width","25%");
    cell.setAttribute('align','left');
        
    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    cell.appendChild(document.createTextNode(rowFeildsSplit[1]));
    row.appendChild(cell);
    cell.setAttribute("width","20%");
    cell.setAttribute('align','left');
    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    cell.appendChild(document.createTextNode(rowFeildsSplit[2]));
    row.appendChild(cell);
    cell.setAttribute("width","20%");
    cell.setAttribute('align','left');
        

    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
    var j = document.createElement("a");
      j.setAttribute("href", "javascript:doConfirmSync('"+rowFeildsSplit[0]+"','"+rowFeildsSplit[1]+"')");

        
    j.innerHTML = "<center><img SRC='../includes/images/sync.jpg' WIDTH=25 HEIGHT=25  ALTER='Click to Sync'></center>";
         
    document.create
    cell.appendChild(j);
    cell.setAttribute('align','left');
    row.appendChild(cell);
           






    tableBody.appendChild(row);
}
function doConfirmSync(locationId,memberCount){
     document.getElementById('resultMessage2').innerHTML ="";
    var r = confirm("Do you really want to Sync ?");
    if (r == true) {

   // alert(locationId);
   if(memberCount.trim()=="Not Assigned"){
       alert("No Employees are assigned to "+locationId+" location");
     // document.getElementById('resultMessage2').innerHTML = "<font color=red>No Employees are assigned to "+locationId+" location</font>";
   }
   else{
    document.getElementById('resultMessage2').innerHTML ="";
    transperent();

    (document.getElementById("loadingMessage3")).style.display = "block";
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerSyncing(req,displaySynchronizeRes); 
    
    var url = CONTENXT_PATH+"/doSynchronizeToHubble.action?constantContactId="+locationId;
  
 
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
    }
}


function displaySynchronizeRes(text){
    transperent();
    if(text=="success"){
        document.getElementById('resultMessage2').innerHTML = "<font color=green>Synchronization Successfull...</font>";
    }
    else{
        document.getElementById('resultMessage2').innerHTML = text;
    }
   
}

function readyStateHandlerSyncing(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadingMessage3")).style.display = "none";
               
                responseTextHandler(req.responseText);
               
            }
            else {
                
                (document.getElementById("loadingMessage3")).style.display = "none";
                document.getElementById('resultMessage2').innerHTML = "<font color=red>Issue due Constant Contact</font>";
                alert("HTTP error ---"+req.status+" : "+req.statusText);
                transperent();
                document.getElementById("btnContactSync").disabled = false;
            }
        }else {
            (document.getElementById("loadingMessage3")).style.display = "block";
        }
    }
}



function transperent(){
    
          

    var overlay = document.getElementById('displyID');
    var specialBox = document.getElementById('blockDiv');
          
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}



function trackingEmailCampaign(element){
    
          
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Email Campaign Tracking Details";
    var overlay = document.getElementById('overlayEmailCampaign');
    var specialBox = document.getElementById('specialBoxEmailCampaign');
          
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
               
    //window.location = 'reviewSearch.action?startDate='+startDate+'&endDate='+endDate+'&reviewType='+reviewType+'&reviewStatus='+reviewStatus+'&empnameById='+empnameById;
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
        //        alert(element);
        
        var oTable = document.getElementById("TrackingTbl");
       
        clearTable(oTable);
         document.getElementById('restMessage').innerHTML ="";
        (document.getElementById("loadingMessageCam")).style.display = "block";
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerTracking(req,displayEmailCampaign); 
                    
        var url = CONTENXT_PATH+"/dotrackingEmailCampaign.action?campaignId="+element;
 
        req.open("GET",url,"true");    
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
        
        
    }
}

function readyStateHandlerTracking(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadingMessageCam")).style.display = "none";
               
                responseTextHandler(req.responseText);
               
            }
            else {
                
                (document.getElementById("loadingMessageCam")).style.display = "none";
              document.getElementById('restMessage').innerHTML = "<font color='red'>Issue with constant contact service...</font>";
                alert("HTTP error ---"+req.status+" : "+req.statusText);
                 
            }
        }else {
            (document.getElementById("loadingMessageCam")).style.display = "block";
        }
    }
}



function displayEmailCampaign(resText) 
{
 
    if(resText.length !=0)
    {
        
 
   if(resText=="NoData"){
      alert("No Records Found");
           document.getElementById('restMessage').innerHTML = "<font color='red'>No Records Found...</font>";
        }
        else if(resText=="Issue"){
         document.getElementById('restMessage').innerHTML = "<font color='red'>Issue with constant contact service...</font>";
    }
        else{
        var oTable = document.getElementById("TrackingTbl");
       
        clearTable(oTable);
      
            
        var headerFields = new Array("SNo","Campaign Last Run Date","Sends","Opens","Bounces","Unsubscribes","Forwards");	
               
    
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
       
        var resTextSplit1 = resText.split("*@!");

        generateTableHeader(tbody,headerFields);
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#^$");
            
            generateRowCam(tbody,resTextSplit2,index);
            
        }
        generateFooter(tbody,oTable);
    
    }}
    else {
        alert("No Records Found");
    }
    
    
  
}


function generateRowCam(tableBody,rowFeildsSplit,index){
    
    var row;
    var cell;
    row = document.createElement("TR");
    row.className="gridRowEven";
    
    cell = document.createElement("TD");
    cell.className="gridRowEven";
    cell.innerHTML = index+1;
    cell.setAttribute("width","5%");
    cell.setAttribute('align','left');
    row.appendChild(cell);
   
 
        
        
    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    cell.appendChild(document.createTextNode(rowFeildsSplit[1].substring(0, 10)));
    row.appendChild(cell);
    cell.setAttribute("width","20%");
    cell.setAttribute('align','left');


       
    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    var j = document.createElement("a");
    j.setAttribute("href", "javascript:doGetContactsEmails('"+rowFeildsSplit[0]+"','send')");
    j.appendChild(document.createTextNode(rowFeildsSplit[2]));
    cell.appendChild(j);
    row.appendChild(cell);
    cell.setAttribute("width","15%");
    cell.setAttribute('align','left');
       
    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    var j = document.createElement("a");
    j.setAttribute("href", "javascript:doGetContactsEmails('"+rowFeildsSplit[0]+"','open')");
    j.appendChild(document.createTextNode(rowFeildsSplit[3]));
    cell.appendChild(j);
    row.appendChild(cell);
    cell.setAttribute("width","15%");
    cell.setAttribute('align','left');
       
    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    var j = document.createElement("a");
    j.setAttribute("href", "javascript:doGetContactsEmails('"+rowFeildsSplit[0]+"','bounce')");
    j.appendChild(document.createTextNode(rowFeildsSplit[4]));
    cell.appendChild(j);
    row.appendChild(cell);
    cell.setAttribute("width","15%");
    cell.setAttribute('align','left');
        
       
    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    var j = document.createElement("a");
    j.setAttribute("href", "javascript:doGetContactsEmails('"+rowFeildsSplit[0]+"','unsubscribe')");
    j.appendChild(document.createTextNode(rowFeildsSplit[5]));
    cell.appendChild(j);
    row.appendChild(cell);
    cell.setAttribute("width","15%");
    cell.setAttribute('align','left');
        
    cell = document.createElement( "TD" );
    cell.className="gridRowEven";
       
    var j = document.createElement("a");
    j.setAttribute("href", "javascript:doGetContactsEmails('"+rowFeildsSplit[0]+"','forward')");
    j.appendChild(document.createTextNode(rowFeildsSplit[6]));
    cell.appendChild(j);
    row.appendChild(cell);
    cell.setAttribute("width","15%");
    cell.setAttribute('align','left');



    tableBody.appendChild(row);
}
