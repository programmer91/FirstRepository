/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 function campaignAdd()
{   
    window.location = "redirectCampaign.action";   
}

 function EditCampaignDetails(campaignId)
{   
    window.location = "selectCampaignDetails.action?campaignId="+campaignId;       
}

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
  function readyStateHandlerTxt(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                 (document.getElementById("loadingMessage")).style.display = "none";
                responseTextHandler(req.responseText);
            }
            else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
              (document.getElementById("loadingMessage")).style.display = "block";
    }
    }
}



function getCampaignSearch() {
   // var name=document.getElementById("activityName").value;
    if(checkDate()){
    var oTable = document.getElementById("CampaignSearchTbl");
  clearTable(oTable);
    var campaignTitle = document.getElementById("campaignTitle").value;
     var campaignStatus=document.getElementById("campaignStatus").value;
 var  campaignStartDate=document.getElementById("campaignStartDate").value;
 var  campaignEndDate=document.getElementById("campaignEndDate").value;                                                            
 
    
    //alert("Haii-->"+campaignId);
  //  alert(campaignId);
    //alert("name::"+name);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerTxt(req,displayCampaignResult); 
    
      var url = CONTENXT_PATH+"/getCampaignSearch.action?campaignTitle="+campaignTitle+"&campaignStatus="+campaignStatus+"&campaignStartDate="+campaignStartDate+"&campaignEndDate="+campaignEndDate;
     //var url = CONTENXT_PATH+"/allActivitySearch.action?&actAccId="+accId;
   //alert(url);
 
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    }
}


function displayCampaignResult(resText) 
{
  
    if(resText.length !=0)
    {
        var oTable = document.getElementById("CampaignSearchTbl");
       
         clearTable(oTable);
      
            
       var headerFields = new Array("Campaign&nbsp;Title","Created&nbsp;Date","Created&nbsp;By","Completion&nbsp;Date","Count","Status");	
               
    
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
       
        //var resTextSplit1 = resText.split("^");

       ParseAndGenerateHTML(oTable,resText, headerFields);
       
          //  resTextSplit2 = resTextSplit1[index].split("|");
            
             //   ParseAndGenerateHTML(tbody,resTextSplit2,index);
  
        generateFooter(tbody);
       
    }
    else {
        alert("No Records Found");
    }
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
function ParseAndGenerateHTML(oTable,responseString,headerFields) {
    
    //alert("In ParseAndGenerateHTML");
    var start = new Date();
    var fieldDelimiter = "|";
    var recordDelimiter = "^";   
    //alert('responseString%%%% ******'+responseString);
    //alert('rowCount%%%% ******'+rowCount);      
    if(oTable.id=="CampaignSearchTbl" ){
    
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
          generateRow(oTable,tbody,records[i],fieldDelimiter);  
            
        }    
    }    
    else {
        generateNoRecords(tbody,oTable);
    }
    
    generateFooter(tbody,oTable);
    //alert("End Of generateTable");
}

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
     if(oTable.id=="CampaignSearchTbl"){
   //  alert(length);
          for (var i=0;i<length-1;i++) {
            //  alert(i+" "+fields[i]);
                cell = document.createElement( "TD" );
        cell.className="gridColumn";
          cell.innerHTML = fields[i];
      if(parseInt(i,10)==0){
      
        
        
             cell.innerHTML = "<a href='javascript:EditCampaignDetails("+fields[6]+")'>"+fields[i]+"</a>";
        
        
      }
       if(fields[i]!=''){
            row.appendChild( cell );
        }
    }
     } 
     
     else {
         
         
          for (var i=0;i<length-1;i++) {
       
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
    
     if(oTable.id == "CampaignSearchTbl"){
        cell.colSpan = "6";
    }
    
    cell.innerHTML = "No Records Found for this Search";
    noRecords.appendChild(cell);
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
    
    
     if(oTable.id == "CampaignSearchTbl"){
        cell.colSpan = "6";
    }
    else{
        cell.colSpan = "25";   
    }
    footer.appendChild(cell);
}



function getCampaignContactsList() {
   // var name=document.getElementById("activityName").value;
    
    var campaignId = document.getElementById("campaignId").value;
    var contactStartDate = document.getElementById("contactStartDate").value;
       var contactEndDate = document.getElementById("contactEndDate").value;
    //alert("Haii-->"+campaignId);
    
    //alert("name::"+name);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerCampaignContactDetails(req); 
    
      var url = CONTENXT_PATH+"/getCampaignContactsList.action?campaignId="+campaignId+"&contactStartDate="+contactStartDate+"&contactEndDate="+contactEndDate;
     //var url = CONTENXT_PATH+"/allActivitySearch.action?&actAccId="+accId;
   //  alert(url);
 
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

//for all activities
function readyStateHandlerCampaignContactDetails(req,responseHandler) {
    return function() {
          document.getElementById("load").style.display = 'block';
   
    var campaignId = document.getElementById("campaignId").value;
         var  myHTMLTable2 = document.getElementById("tblUpdate111");
           var roleName = document.getElementById("roleName").value;
        ClrTable2(myHTMLTable2);
        if (req.readyState == 4) {
            if (req.status == 200) {
                //document.getElementById("loadMessage").style.display = 'none';
                 //(document.getElementById("loadingMessage")).style.display = "none";
                // var headerFields2 = new Array("SNo","Sal","FName","LName","AliasName","Title","Office Phone","Email","Create Login");	
                
                //totalStream = totalStream + Id + "#^$" + Title + "#^$" + CreatedBy + "#^$" + CurrStatus + "*@!";
                document.getElementById("load").style.display = 'none';
               	var headerFields2 = new Array("#","ContactName","Company","Email");
              
                var getResponseData;
                getResponseData = req.responseText;
              //  alert('getResponseData******'+getResponseData);
                var temp = new Array();
                temp = getResponseData.split('addto');
               if(getResponseData!="" && getResponseData!="addto0"){
                     // document.forMycop.inputRowData.style.display="block";
                   //document.forMycop.inputRowData.value= "Records Found "+temp[1];
                 //alert(temp[0]);
                 if(roleName!='Sales')
                  document.getElementById("GenerateExcel").style.display = 'block';
              else
                  document.getElementById("GenerateExcel").style.display = 'none';
              
                    ParseAndGenerateCampaignConatcts(myHTMLTable2,temp[0], headerFields2,temp[1]);   //this function implementation in VenusReportAjax.js
                 
                   // myHeadTable.innerHTML = "Total Rows"+temp[1];
                }else{
                   document.getElementById("GenerateExcel").style.display = 'none';
               alert('No Result For This Search...');
                     
                }
                //document.getElementById("loadMessage2").style.display = 'none';
                
            } else {
                //alert("HTTP error ---"+req.status+" : "+req.statusText);
                alert("please wait");
                
                 //document.getElementById("loadMessage").style.display = 'block';
                //document.getElementById("loadMessage2").style.display = 'block';
            }
        }
        else { //document.getElementById("loadMessage").style.display = 'block';
	 }
    }
}
function ParseAndGenerateCampaignConatcts(oTable,responseString,headerFields2,rowCount) {
    var start = new Date(); 
    var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";
    
   //  alert('responseString*******'+responseString);
    var records = responseString.split(recordDelimiter);
   // alert(records);
    //	Generate the HTML Code for the Table Creation using HeaderFields & Records
    generateCampaignContactTable(oTable,headerFields2,records,fieldDelimiter);
}


function generateCampaignContactRow(tableBody,record,delimiter) {

    var campaignId = document.getElementById("campaignId").value;
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
   //
  // alert(record);
    var fields = record.split(delimiter);   
    for (var i=0;i<fields.length-1;i++) {
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        row.appendChild( cell );
   //     alert(fields[1]+'----'+fields[2]+'--'+fields[3]+'--'+fields[4]);
        
       if(i==1){            
            
            var j = document.createElement("a");
            //j.setAttribute("href", "javascript:getLeadData('"+fields[1]+"','"+accId+"')");
              j.setAttribute("href", "javascript:getContactData('"+fields[1]+"','"+campaignId+"')");
            j.appendChild(document.createTextNode(fields[2]));
            cell.appendChild(j);
        }
       
        else  if(i==0){  
            cell.innerHTML = fields[0];        
        } else  if(i==2){  
            cell.innerHTML = fields[4];        
        }
        else  if(i==3){  
                cell.innerHTML = fields[3]; 
        }
       
    }
    
}
function generateCampaignContactTable(oTable, headerFields2,records,fieldDelimiter) {	    
     var cell;
    var tbody = oTable.childNodes[0];
    // oTable.outerHTML = str;
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    if(records.length >=1 && records!=""){
        generateTableCampaignContactHeader(tbody,headerFields2);
    }
    
   // alert('records.length>> '+records.length);
   // alert('records>> '+records);
  
        
    for(var i=0;i<records.length-1;i++) {
       // alert('records>> '+records[i]);
        generateCampaignContactRow(tbody,records[i],fieldDelimiter);               
    }
    //oTable.row[0].column[0].onclick=getData();
    
   var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.colSpan = "9";
    footer.appendChild(cell); 
  // generateFooter(tbody); 
    
} 



function getCampaignContactsExcel(){
    
       var campaignId  = document.getElementById('campaignId').value;
          var contactStartDate = document.getElementById("contactStartDate").value;
       var contactEndDate = document.getElementById("contactEndDate").value;
            
            window.location="getCampaignContactsExcel.action?campaignId="+campaignId+"&contactStartDate="+contactStartDate+"&contactEndDate="+contactEndDate;    
                      
            return true;    
                
}


function ClrTable2(myHTMLTable2) { 
    var tbl =  myHTMLTable2;
    var lastRow = tbl.rows.length; 
    //document.getElementById('addlabel1').style.display = 'none'; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}


function generateTableCampaignContactHeader(tableBody,headerFields2) {
    var row;
    var cell;
    
    row = document.createElement( "TR" );
    row.className="gridHeader";
    tableBody.appendChild( row );
    
    for (var i=0; i<headerFields2.length; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridHeader";
        row.appendChild( cell );
        cell.innerHTML = headerFields2[i];
        cell.width = 100;
    }
    //generateFooter(tableBody);
}



 function checkDate(){
    var startDate=document.getElementById('campaignStartDate').value;
    var endDate=document.getElementById('campaignEndDate').value;
    if((startDate=="")&&(endDate=="")){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please give Start Date and End Date!!</font>";    
      // alert("Please give Start Date and End Date");
        return false;
    }
else if((startDate!="")&&(endDate=="")){
    document.getElementById('resultMessage').innerHTML = "<font color=red>please give End Date!!</font>";    
    //alert("please give End Date");
    return false;
}
else if((startDate=="")&&(endDate!="")){
    document.getElementById('resultMessage').innerHTML = "<font color=red>please give Start Dates!!</font>";    
    //alert("please give Start Date");
    return false;
}
return true;
}

function getContactData(contactId,designationName) {
    //alert('hi------>'+designationName);
    var designation = "";
    if(designationName=="TL")
        {
            
            designation = "TeamLead";
        }
   else if(designationName=="CU")
    {
        designation = "Customer";
    }
   else if(designationName=="CN")
        {
             designation = "Consultant";
        }
    else if(designationName=="MN")
        {
             designation = "Manager";
        }
     else if(designationName=="DR")
        {
             designation = "Director";
        }
         else if(designationName=="OR")
        {
             designation = "Operations";
        }
       // alert("designation-->"+designation);
    document.location="/Hubble/crm/contacts/getContact.action?Id="+contactId+"&designationName="+designation;
    }