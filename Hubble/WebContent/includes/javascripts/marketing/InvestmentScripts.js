/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function investmentToggleOverlay(){
     $('#addButton').attr("disabled", false);
  //  alert('investmentToggleOverlay');
    document.getElementById('resultMessage').innerHTML ='';
    document.getElementById('investmentName').value="";  
    document.getElementById('startDateInvestment').value="";
    document.getElementById('endDateInvestment').value="";
    document.getElementById('countryInvestment').value="";
    document.getElementById('totalExpenseAmount').value="";
    document.getElementById('locationInvestment').value="";
    document.getElementById('investmentDesc').value="";
      document.getElementById('conferenceId').value="";
      document.getElementById('createdBy').innerHTML='';
      document.getElementById('createdDate').innerHTML='';
      
    document.getElementById('file').value="";
    //document.getElementById('usd').value="USD";
  //  document.getElementById('usd').value="";
     hideRow('downloadTr');
     showRow('uploadTr');
      hideRow('createdTr');
     document.getElementById("fileTag").innerHTML="Upload File:"
      document.getElementById("resetDiv").style.display='block';
    // $("#resetDiv").show();
    //document.getElementById("downloadTr").style.display = 'none';
    //document.getElementById("uploadTr").style.display = '';
         
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Investment Details";
    
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBoxInvestment');
    //  hideRow("approvedBy");
    //hideRow("tlcommentsTr");
    // hideRow("hrcommentsTr");
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
         window.location = "../marketing/leadGen.action";     
        
                
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
            
  
}
        
        
        function toggleEditInvestmentOverlay(Id){
   
    var totalConferences = document.getElementById("totalConferenceId");
   
    var i;
    var conference = document.getElementById("conferenceId");
    while (conference.options.length) {
        conference.remove(0);
    }
    for (i = 0; i < totalConferences.length; i++) {
        var ConfId = "";
        var ConfValue = "";
        ConfValue = ConfValue + totalConferences.options[i].text;
        ConfId = ConfId + totalConferences.options[i].value;
        var totalConf = new Option(ConfValue,ConfId);
        conference.options.add(totalConf);
    }
    //  alert('edit');
    $('#updateButtonInvs').attr("disabled", false);
 
 
    document.getElementById("updateButtonInvs").style.display="block";
    document.getElementById("addButton").style.display="none";
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBoxInvestment');
    document.getElementById('investmentId').value=Id;
   
    showRow('uploadTr');
    showRow('downloadTr');  
     showRow('createdTr');
    //  $("#resetDiv").hide();
    document.getElementById("resetDiv").style.display='none';
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Investment Details";
   
      
    //   var roleName = document.getElementById("roleName").value;
    document.getElementById("load").style.display = 'block';
    $.ajax({
        url:'getInvestmentDetails.action?id='+Id,//
        context: document.body,
        success: function(responseText) {
            var json = $.parseJSON(responseText);
            //   Inv_Id   StartDate EndDate TotalExpenses Currency Location Description AttachmentFileName AttachmentLocation CreatedBy CreatedDate ModifiedBy ModifiedDate
           
       
            var InvestmentName = json["Inv_Name"];
            var Country = json["Country"];
            var StartDate = json["StartDate"];
            var EndDate = json["EndDate"];
            var TotalExpenses = json["TotalExpenses"];
            var Currency = json["Currency"];
            var Location = json["Location"];
            var Description = json["Description"];
            var AttachmentFileName = json["AttachmentFileName"];
           
            // alert(AttachmentFileName);
            var InvestmentType = json["InvestmentType"];
            var ConferenceId = json["ConferenceId"];
            
             var createdBy=json["CreatedBy"];
            var createdDate=json["CreatedDate"];
            
            //alert(AttachmentFileName);
            //   var AttachmentLocation = json["AttachmentLocation"];                             
            //alert(ReviewTypeId+" BillingAmount "+BillingAmount);               
            document.getElementById("investmentName").value = InvestmentName;
            document.getElementById('countryInvestment').value=Country;
            document.getElementById("startDateInvestment").value = StartDate;
            document.getElementById("endDateInvestment").value = EndDate;       
            document.getElementById('totalExpenseAmount').value=TotalExpenses;
            document.getElementById('locationInvestment').value=Location;
            document.getElementById('usd').value=Currency;
            document.getElementById('investmentDesc').value=Description;
            document.getElementById('investmentType').value=InvestmentType;
            document.getElementById('invType').value=InvestmentType;
            document.getElementById('conferenceId').value=ConferenceId;
           document.getElementById('createdBy').innerHTML=createdBy;
           document.getElementById('createdDate').innerHTML=createdDate;
            document.getElementById('investmentType').disabled=true;
            //  document.getElementById('file').value=AttachmentFileName;
            if(AttachmentFileName!= null && AttachmentFileName!=''&& AttachmentFileName.trim().length>0)  {
                // alert("hiii");
                document.getElementById("downloadLink").style.display = 'block';
         
                document.getElementById("downloadFile").innerHTML = AttachmentFileName;
                document.getElementById("fileTag").innerHTML="Replace File:"
            }else {
                //alert("none");
                document.getElementById("downloadLink").style.display = 'none';
                document.getElementById("fileTag").innerHTML="Upload File:"
            //document.getElementById("downloadFile").innerHTML ='';
            }
            document.getElementById("load").style.display = 'none'; 
        //document.getElementById("resultMessage").style.display = 'none';
        }      
    })       
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
        window.location = "../marketing/leadGen.action";    
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}


function addInvestmentdetails()
{   
    $('#addButton').attr("disabled", false);    
    var investmentName=escape(document.getElementById('investmentName').value);    
    var startDateInvestment=document.getElementById('startDateInvestment').value;   
    var endDateInvestment=document.getElementById('endDateInvestment').value;
    var countryInvestment=document.getElementById('countryInvestment').value;   
    var totalExpenseAmount=document.getElementById('totalExpenseAmount').value;   
    var locationInvestment=escape(document.getElementById('locationInvestment').value);
    var investmentDesc=escape(document.getElementById('investmentDesc').value);
    var investmentType=escape(document.getElementById('investmentType').value);
    var investmentConference=document.getElementById('conferenceId').value;
    if(investmentConference.length==0){
        investmentConference=0;
    }
    
    var attachInvestment='';
    if(document.getElementById('file').value!=null&&document.getElementById('file').value!='')
    attachInvestment=document.getElementById('file').files[0].name;   
  //  alert(attachInvestment);
    //document.getElementById('usd').value="USD";
    var usd=document.getElementById('usd').value;   
    if(startDateInvestment!="" && endDateInvestment!="")
    {
        var sDate=new Date(startDateInvestment);            
        var eDate=new Date(endDateInvestment);
        if(sDate>eDate){           
            document.getElementById('resultMessage').innerHTML = "<font color=red>StartDate must lessthan EndDate</font>";
            return false;
        }
           
    }
   
    if(attachInvestment!="")
    {
        var ext = attachInvestment.split('.').pop();
        if(ext=="pdf" || ext=="docx" || ext=="doc" || ext=="zip" || ext=="txt" || ext=="png" || ext=="jpeg" || ext=="xls" || ext=="xlsx"){        
        } else{         
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please Enter Valid Format</font>";
            return false;
        }
               
    }
    
    if(investmentName.length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter Title .</font>";
    }else if(startDateInvestment.length==0){       
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please select start date.</font>";
    }else if(endDateInvestment.length==0){       
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please select end date.</font>";
    }else if(countryInvestment.length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please select country.</font>";
    }else if(totalExpenseAmount.length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter total expenses.</font>";       
    }else if(locationInvestment.length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter location.</font>";
    }else if(investmentType.length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please select investment type.</font>";
    }
    else{
        //alert("attachInvestment-->"+attachInvestment);
        $.ajaxFileUpload({           
            //url:'addInvestmentdetails.action?investmentName='+investmentName+'&startDateInvestment='+startDateInvestment+'&endDateInvestment='+endDateInvestment+'&countryInvestment='+countryInvestment+'&totalExpenseAmount='+totalExpenseAmount+'&locationInvestment='+locationInvestment+'&investmentDesc='+investmentDesc+'&usd='+usd+'&investmentType='+investmentType+'&attachInvestment='+attachInvestment,//
            url:'addInvestmentdetails.action?investmentName='+investmentName+'&startDateInvestment='+startDateInvestment+'&endDateInvestment='+endDateInvestment+'&countryInvestment='+countryInvestment+'&totalExpenseAmount='+totalExpenseAmount+'&locationInvestment='+locationInvestment+'&investmentDesc='+investmentDesc+'&usd='+usd+'&investmentType='+investmentType+'&attachInvestment='+attachInvestment+'&investmentConferenceId='+investmentConference,//
            secureuri:false,//false       
            fileElementId:'file',
            dataType: 'json',// json
            success: function(data,status){                
               /* var displaymessage = "<font color=red>Please try again later</font>";                
                if(data.indexOf("uploaded")>0){               
                    displaymessage = "<font color=green>Added Successfully.</font>";
                    $('#addButton').attr("disabled", true);                                
                }
           
                if(data.indexOf("Error")>0){
               
                    displaymessage = "<font color=red>Internal Error!, Please try again later.</font>"
                }*/
                if(data.indexOf("green")>0){  
                    $('#addButton').attr("disabled", true);   
                }
           
                document.getElementById("load").style.display = 'none';
                document.getElementById('resultMessage').innerHTML = data;//"<font color=green>File uploaded successfully</font>";
        
            },
            error: function(e){
            
                document.getElementById("load").style.display = 'none';
                document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
       
            }
        });
    }   
   
    return false;

}

function updateInvestmentdetails(id){
//alert("updateInvestmentdetails");
     $('#updateButtonInvs').attr("disabled", false); 
   var id= document.getElementById("investmentId").value;
    var investmentName=escape(document.getElementById('investmentName').value);  
   var startDateInvestment=document.getElementById('startDateInvestment').value;
   var endDateInvestment=document.getElementById('endDateInvestment').value;
   var countryInvestment=document.getElementById('countryInvestment').value; 
   var totalExpenseAmount=document.getElementById('totalExpenseAmount').value;
   var locationInvestment=escape(document.getElementById('locationInvestment').value);
   var investmentDesc=escape(document.getElementById('investmentDesc').value);
   var investmentType=escape(document.getElementById('invType').value);
    var investmentConference=document.getElementById('conferenceId').value;
    if(investmentConference.length==0){
        investmentConference=0;
    }
   
   
     var attachInvestment='';
    if(document.getElementById('file').value!=null&&document.getElementById('file').value!='')
    attachInvestment=document.getElementById('file').files[0].name;   
 //  alert(attachInvestment);
  var usd= document.getElementById('usd').value;
 // alert('usd-->'+usd);
 //  var usd=document.getElementById('usd').value;   
    if(startDateInvestment!="" && endDateInvestment!="")
        {
            var sDate=new Date(startDateInvestment);            
            var eDate=new Date(endDateInvestment);
            if(sDate>eDate){               
                document.getElementById('resultMessage').innerHTML = "<font color=red>StartDate must lessthan EndDate</font>";
                 return false;
            }
           
      }
       if(attachInvestment!="")
    {
        var ext = attachInvestment.split('.').pop();
        if(ext=="pdf" || ext=="docx" || ext=="doc" || ext=="zip" || ext=="txt" || ext=="png" || ext=="jpeg" || ext=="xls" || ext=="xlsx"){        
        } else{         
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please Enter Valid Format</font>";
            return false;
        }
               
    }
      
    if(investmentName.length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter Title .</font>";
        return false;
    }else if(startDateInvestment.length==0){       
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please select start date.</font>";
        return false;
    }else if(endDateInvestment.length==0){       
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please select end date.</font>";
    }else if(countryInvestment.length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please select country.</font>";
    }else if(totalExpenseAmount.length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter total expenses.</font>";
        return false;
    }else if(locationInvestment.length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter location.</font>";
        return false;
    }
    else{
    document.getElementById("load").style.display = 'block';
    
 $.ajaxFileUpload({
             
          // var url=CONTENXT_PATH+"/updateInvestmentdetails.action?investmentName="+investmentName+"&startDateInvestment="+startDateInvestment+"&endDateInvestment="+endDateInvestment+"&countryInvestment="+countryInvestment+"&totalExpenseAmount="+totalExpenseAmount+"&locationInvestment="+locationInvestment+"&investmentDesc="+investmentDesc+"&attachInvestment="+attachInvestment+"&usd="+usd+"&id="+id;
         //  url:'updateInvestmentdetails.action?investmentName='+investmentName+"&startDateInvestment="+startDateInvestment+"&endDateInvestment="+endDateInvestment+"&countryInvestment="+countryInvestment+"&totalExpenseAmount="+totalExpenseAmount+"&locationInvestment="+locationInvestment+"&investmentDesc="+investmentDesc+"&attachInvestment="+attachInvestment+"&usd="+usd+"&id="+id+"&investmentType="+investmentType,
         url:'updateInvestmentdetails.action?investmentName='+investmentName+"&startDateInvestment="+startDateInvestment+"&endDateInvestment="+endDateInvestment+"&countryInvestment="+countryInvestment+"&totalExpenseAmount="+totalExpenseAmount+"&locationInvestment="+locationInvestment+"&investmentDesc="+investmentDesc+"&attachInvestment="+attachInvestment+"&usd="+usd+"&id="+id+"&investmentType="+investmentType+"&investmentConferenceId="+investmentConference,
            
             secureuri:false,//false       
            fileElementId:'file',
            dataType: 'json',// json
            success: function(responseText) {
               // displaymessage = responseText;
       
           
            document.getElementById("load").style.display = 'none';
          /*  if(responseText=="updated")
                {
                 document.getElementById('resultMessage').innerHTML = "<font color=green>Updated Successfully</font>";
      
//"<font color=green>File uploaded successfully</font>";
            }*/
              document.getElementById('resultMessage').innerHTML = responseText;
            //  alert(attachInvestment);
           if(attachInvestment!=null && attachInvestment!=''){
             //  alert(attachInvestment);
               document.getElementById("downloadLink").style.display = 'block';
               document.getElementById("downloadFile").innerHTML = attachInvestment;
              }
        // document.getElementById('fileType').value = "";
        },
        error: function(e){
         //  showRow("addTr");
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
     
                
        }
    });
    }
}






function populateInvestmentUpadteResult(resText){
    document.getElementById("load").style.display = 'none';
    var displaymessage = "<font color=red>Please try again later</font>";  
    if(resText=="updated"){                      
          document.getElementById('resultMessage').innerHTML = "<font color=green size=2px>Updated Successfully</font>";
        $('#updateButtonInvs').attr("disabled", true);
        }
   
    if(resText=="Error"){
           document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";       
    }
}


function downloadInvestmentAttachment()
{
    window.location = "downloadInvestmentAttachment.action?investmentId="+document.getElementById("investmentId").value;   
}


function deleteInvestment()
{
    window.location = "deleteInvestment.action?id="+document.getElementById("investmentId").value;
}


 function investmentFieldLengthValidator(element)
{
    var i=0;
    var fieldName='';   
    if(element.value!=null&&(element.value!="")) {
        if(element.id=="investmentName") { 
            fieldName = 'Investment Name';
            i=100;
        }
        if(element.id=="locationInvestment") { 
            fieldName = 'location';
            i=80;
        }
        if(element.id=="investmentDesc") { 
            fieldName = 'investment Description';
            i=1500;
        }      
        if(element.value.replace(/^\s+|\s+$/g,"").length>i) {
            str=new String(element.value);
            element.value=str.substring(0,i);          
            document.getElementById('resultMessage').innerHTML = "<font color=red>The "+fieldName+" length must be less than "+i+" characters</font>";
            element.focus();
            return false;
        }
        return true;
        
        
    }
}


function generateInvestmentXls()
{
    
    var isInvestmentRecordsExist = document.getElementById('isInvestmentRecordsExist').value ; 
    if(parseInt(isInvestmentRecordsExist,10)>0)
    window.location = "generateInvestmentXls.action";
else
    alert("No records found");
}


function compareDates()
{  
    var startDate = document.getElementById('startDate').value ;  
    var endDate =document.getElementById('endDate').value ;  
    var sDate=new Date(startDate);            
    var eDate=new Date(endDate);
    if(sDate>eDate){        
        document.getElementById('resultMessage123').innerHTML = "<font color=red>StartDate must lessthan EndDate</font>";
        return false;
    }
    else
        return true;
}


 function isNumeric(element){
    var val=element.value;
    if (isNaN(val)) {
         document.getElementById('resultMessage').innerHTML = "<font color=red>Please Enter numeric values</font>";      
        element.value=val.substring(0, val.length-1);      
        return false;
    }
    else
        return true;
}


function isNumberKey(evt) { 
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57) || charCode.keyCode == 46)
    {
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please Enter numeric values</font>";
        return false;
    }else {
        return true;
    }
}

function decimalsValidations(res)
{  
     if(res!="")
    {
        var  totalExpenseAmountsplit=res.split(".");        
        if(totalExpenseAmountsplit[0].length>8)
        {           
            document.getElementById('totalExpenseAmount').value=totalExpenseAmountsplit[0].substring(0, 7);
            totalExpenseAmountsplit[0].substring(0, 7);        
            document.getElementById('resultMessage').innerHTML = "<font color=red>Only 7 Integer values are allowed</font>";            
            return false;
        }
       
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
function readyStateHandler(req,responseHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("load").style.display = 'none';
                responseHandler(req.responseText);
            }
        }
        else {
            document.getElementById("load").style.display = 'block';
        //alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }
}


function currencyPopup(url) {
    newwindow=window.open(url,'name','height=400,width=600');
    if (window.focus) {newwindow.focus()}
    return false;
}




/*
 * Dashboard Changes
 * 
 * 
 */
var totalValue="";
function getOppurtunityList() 
{
    var investmentId=document.getElementById("investmentId").value;
    var oppurtunityAccountName = document.getElementById("oppurtunityAccountName").value;
     var oppurtunitiesPersonId = document.getElementById("oppurtunitiesPersonId").value;
     totalValue="";
  //alert(investmentId)
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler1(req); 
   // var url = CONTENXT_PATH+"/getLeadSearch.action";
   
    var url = CONTENXT_PATH+"/getOppurtunitySearch.action?investmentId="+investmentId+"&accountName="+oppurtunityAccountName+"&createdBy="+oppurtunitiesPersonId;
    //alert(url);
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}


function readyStateHandler1(req,responseHandler) {
    return function() {
        var  myHTMLTable = document.getElementById("tblOppurtunity1");
        ClrTable(myHTMLTable);
        if (req.readyState == 4) {
            if (req.status == 200) {
               // document.getElementById("loadMessage").style.display = 'none';
                var headerFields0 = new Array("s.No","Account Name","Opportunity&nbsp;Title","Status","Created Date","Created By","Value");	
                var getResponseData;
                getResponseData = req.responseText;
                //alert("getResponseData******"+getResponseData);
                var temp = new Array();
                temp = getResponseData.split('addto');
                if(req.responseText!=''){
                     // document.forMycop.inputRowData.style.display="block";
                   //document.forMycop.inputRowData.value= "Records Found "+temp[1];
                   // alert(temp[0]);
                    ParseAndGenerateHTML(myHTMLTable,temp[0], headerFields0,temp[1]);   //this function implementation in VenusReportAjax.js
                    
                    // myHeadTable.innerHTML = "Total Rows"+temp[1];
                }else{
                    alert('No Result For This Search...');
                    spnFast.innerHTML="No Result For This Search...";                
                }
                //document.getElementById("loadMessage2").style.display = 'none';
                
            } else {
                //alert("HTTP error ---"+req.status+" : "+req.statusText);
               // alert("please wait");
                
                 document.getElementById("loadingMessage12").style.display = 'block';
                //document.getElementById("loadMessage2").style.display = 'block';
            }
        }
        else { //document.getElementById("loadMessage").style.display = 'block';
	 }
    }


}


function ParseAndGenerateHTML(oTable,responseString,headerFields0,rowCount) {
    var start = new Date();
//    var fieldDelimiter = "|";
//    var recordDelimiter = "^";
 var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";
    
    // alert('responseString*******'+responseString);
    var records = responseString.split(recordDelimiter);
    //	Generate the HTML Code for the Table Creation using HeaderFields & Records
    generateTable(oTable,headerFields0,records,fieldDelimiter);
}


function ClrTable(myHTMLTable) { 
    var tbl =  myHTMLTable;
    var lastRow = tbl.rows.length; 
    //document.getElementById('addlabel1').style.display = 'none'; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}


function generateTableHeader(tableBody,headerFields0) {
    var row;
    var cell;
    
    row = document.createElement( "TR" );
    row.className="gridHeader";
    tableBody.appendChild( row );
    
    for (var i=0; i<headerFields0.length; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridHeader";
        row.appendChild( cell );
        cell.innerHTML = headerFields0[i];
        cell.width = 100;
    }
    //generateFooter(tableBody);
}






function generateTable(oTable, headerFields,records,fieldDelimiter) {	    
    
    var tbody = oTable.childNodes[0];
    // oTable.outerHTML = str;
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    if(records.length >=1 && records!=""){
        generateTableHeader(tbody,headerFields);
    }
    
    //alert('records.length>> '+records.length);
    if(oTable.id=="tblOppurtunity1")
        {
            for(var i=0;i<records.length-1;i++) {
        generateRow(tbody,records[i],fieldDelimiter);               
    }
    
        }
        else
            {
                
            
    for(var i=0;i<records.length;i++) {
        generateRow(tbody,records[i],fieldDelimiter);               
    }
            }
    //oTable.row[0].column[0].onclick=getData();
//generateFooter(tableBody,document.getElementById("tblOppurtunity1"));
 generateFooter(tbody);
    
}  

function generateFooter(tbody) {
   
    var row;
    var cell;
    var totalVal =0;
    row = document.createElement("TR");
   // row.className="gridPager";
    tbody.appendChild(row);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.colSpan = "7";
    //cell.width = 100;
    cell.align="right";
    var totalValue1="";
    totalValue1 = totalValue.split(",");
    for(i=0;i<totalValue1.length-1;i++) {
        totalVal = totalVal+parseInt(totalValue1[i]);
    }
    cell.innerHTML = "Total value : "+totalVal+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    row.appendChild(cell);

}

function generateRow(tableBody,record,delimiter) {
    
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    
    var fields = record.split(delimiter);   
    for (var i=0;i<fields.length-2;i++) {
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        row.appendChild( cell );
        
    
        
        if(i==1){
             var j = document.createElement("a");
             j.setAttribute("href", "javascript:getAccountDetailsPopup('"+fields[7]+"')");
              j.appendChild(document.createTextNode(fields[i]));
            cell.appendChild(j);
             
        } else if(i==2){
             var j = document.createElement("a");
             j.setAttribute("href", "javascript:getOppurtunityDetailsPopup('"+fields[8]+"')");
              j.appendChild(document.createTextNode(fields[i]));
            cell.appendChild(j);
             
        }else if(i==6)
            {
                 cell.innerHTML = fields[i]; 
          totalValue=totalValue+fields[i]+",";
            }
        else
            {
          cell.innerHTML = fields[i];       
            }
        }
        
}
