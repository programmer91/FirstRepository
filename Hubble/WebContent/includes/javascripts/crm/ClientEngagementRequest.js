/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


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


function initRequest(url) {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    }
    else
        if (window.ActiveXObject) {
            isIE = true;
            return new ActiveXObject("Microsoft.XMLHTTP");
        }
    
}
var isIE;
var vendorName;
function init1() {
   // $("#contactSpan").html("<select  name='accContacts'  id='accContacts' class='inputTextBlueLargeAccount' style='width:430px; height: 100px' multiple='true'/>"); 
    
    //alert("rk")
    //getOtherDivs();
    
    //var menu = document.getElementById("auto-row");
    //var menu1 = document.getElementById("auto-row1");
    autorow = document.getElementById("menu-popup");
    autorow.style.display ="none";
    autorow1 = document.getElementById("menu-popup");
    autorow1.style.display ="none";
    //autorow.style.top = getElementY(menu) + "px";
    //autorow1.style.top = getElementY(menu1) + "px";
    var height = document.documentElement.clientHeight - 120;
    //autorow1.style.height = Math.max(height, 150);
   // autorow1.style.overflowY = "scroll";
   // autorow.style.height = Math.max(height, 150);
   // autorow.style.overflowY = "scroll";
    
    completeTable = document.getElementById("completeTable");
    completeTable.setAttribute("bordercolor", "white");
    
     var preRequestStage=document.getElementById("preRequestStage").value;
    var requestStage=document.getElementById("requestStage").value;
    var clientType=document.getElementById("clientType").value;
   // alert(document.getElementById("clientType").value);
   // alert(document.getElementById("requestStage").value);
  if((preRequestStage=='Creation' || requestStage=='Creation') && clientType=="E" ){
           document.getElementById("revenues").readOnly = false;
    document.getElementById("noOfEmployees").readOnly = false;
  } 
}
function fillCustomer() {
    
    if($("#clientType").val()=='I'){
    
    
     document.getElementById("consultantId").value =0;
     removeOptionsFromDropDown(document.getElementById("opportunityId"));
    var test=document.getElementById("customerName").value; 
  //  $("#accContacts").remove();
//$("#contactSpan").html("<select  name='accContacts'  id='accContacts' class='inputTextBlueLargeAccount' style='width:430px; height: 100px' multiple='true'/>"); 
//alert("test--->"+test);       
    if (test == "") {
        document.getElementById("consultantId").value =0;
        removeOptionsFromDropDown(document.getElementById("opportunityId"));
        clearCustomerTable();
      //  $('#accContacts').remove();
       //  $('#accContacts').selectivity('close');
    } else {
        if (test.length >2) {
            var url = CONTENXT_PATH+"/getCustomerDetails.action?customerName="+ escape(test);   

            //alert("URL--->"+url);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {                        
                        parseCustMessages(req.responseXML);                        
                    } else if (req.status == 204){
                        clearCustomerTable();
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }
    }
}

function parseCustMessages(responseXML) {
    clearCustomerTable();
    var employees = responseXML.getElementsByTagName("EMPLOYEES")[0];
    if (employees.childNodes.length > 0) {        
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");        
    } else {
        clearCustomerTable();
    }
//    if(employees.childNodes.length<10) {
//        autorow1.style.overflowY = "hidden";
//        autorow.style.overflowY = "hidden";        
//    }
//    else {    
//        autorow1.style.overflowY = "scroll";
//        autorow.style.overflowY = "scroll";
//    }
    
    var employee = employees.childNodes[0];
    var chk=employee.getElementsByTagName("VALID")[0];
    if(chk.childNodes[0].nodeValue =="true") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < employees.childNodes.length; loop++) {
            
            var employee = employees.childNodes[loop];
            var customerName = employee.getElementsByTagName("NAME")[0];
            var empid = employee.getElementsByTagName("EMPID")[0];
            appendCustomer(empid.childNodes[0].nodeValue,customerName.childNodes[0].nodeValue);
        }
        var position = findPosition(document.getElementById("customerName"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
        document.getElementById("consultantId").value ='';
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = " Name  is InValid ";
    }
}
function appendCustomer(empId,empName) {
    var row;
    var nameCell;
    if (!isIE) {
        row = completeTable.insertRow(completeTable.rows.length);
        nameCell = row.insertCell(0);
    } else {
        row = document.createElement("tr");
        nameCell = document.createElement("td");
        row.appendChild(nameCell);
        completeTable.appendChild(row);
    }
    row.className = "popupRow";
    nameCell.setAttribute("bgcolor", "#3E93D4");
    var linkElement = document.createElement("a");
    linkElement.className = "popupItem";
    linkElement.setAttribute("href", "javascript:set_cust('"+empName.replace(/'/g, "\\'") +"','"+ empId +"')");
    
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}
function set_cust(eName,eID){
    clearCustomerTable();
    // $("#accContacts").remove();
//$("#contactSpan").html("<select  name='accContacts'  id='accContacts' class='inputTextBlueLargeAccount' style='width:430px; height: 100px' multiple='true'/>"); 
    
   // getProjectNamesList(eID);
   document.getElementById("customerName").value =eName;
    
    document.getElementById("consultantId").value =eID;
   // getContactsNamesList(eID);
  getOpportunityNamesList(eID);
   getPopulateAccountDetails(eID);
    //alert("hii");
}


function clearCustomerTable() {
    if (completeTable) {
        completeTable.setAttribute("bordercolor", "white");
        completeTable.setAttribute("border", "0");
        completeTable.style.visible = false;
        for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
}

function hideScrollBar() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
}

function findPosition( oElement ) {
    if( typeof( oElement.offsetParent ) != undefined ) {
        for( var posX = 0, posY = 0; oElement; oElement = oElement.offsetParent ) {
            posX += oElement.offsetLeft;
            posY += oElement.offsetTop;
        }
        return posX+","+posY;
    } else {
        return oElement.x+","+oElement.y;
    }
}



function getOpportunityNamesList(accId){

   // var deptName = document.employeeForm.departmentId.value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateOpportunityNamesList);
    var url = CONTENXT_PATH+"/getOpportunityNamesList.action?accId="+accId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}

function populateOpportunityNamesList(resXML) {
    //var projectName1 = document.addLeads.accContacts;
      var projectName1 = document.getElementById("opportunityId");
    var customer = resXML.getElementsByTagName("OPPORTUNITIES")[0];
    var projectNameList = customer.getElementsByTagName("OPPORTUNITY");
    projectName1.innerHTML="";

    for(var i=0;i<projectNameList.length;i++) {
        var projects = projectNameList[i];
        
        var att = projects.getAttribute("oppId");
        var name = projects.firstChild.nodeValue;
        //alert(name);
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        projectName1.appendChild(opt);
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

function readyStateHandler(req,responseXmlHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                responseXmlHandler(req.responseXML);
            } else {
                alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }
    }
}


function removeOptionsFromDropDown(dropdownId){
    $(dropdownId).find('option:gt(0)').remove();


}


function getAddressRow(obj){
    $("#state").val("");
    $("#city").val("");
    if(obj.value=='Client F2F Meeting'){
        showRow("addressRow");
    }else {
        hideRow("addressRow");
    }
}
function loadAddressRow(){
    var obj = document.getElementById("meetingType");
 
    if(obj.value=='Client F2F Meeting'){
        showRow("addressRow");
    }else {
        hideRow("addressRow");
    }
    if( $("#clientType").val()=='E'){
         $("#opportunityId").hide();
          $("#opportunityName").show();
    }else {
         $("#opportunityId").show();
          $("#opportunityName").hide();
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


function engagementFieldsValidate() {
    var roleName= $("#roleName").val();
     var preRequestStage= $("#preRequestStage").val();
     var requestStage= $("#requestStage").val();
     if( (roleName!='Sales') && ((preRequestStage=="Reviewed" && requestStage=="Reviewed" )||(requestStage=="Approved" ))){
    poplatingResourceValues();
     }
  //  alert("here engagementFieldsValidate");
    var requestorId= $("#requestorId").val();
    var rvpName= $("#rvpName").val();
    var customerName= $("#customerName").val();
    var consultantId= $("#consultantId").val();
    var opportunityId= $("#opportunityId").val();
    //var meetingStatus= $("#meetingStatus").val();
    var meetingType= $("#meetingType").val();
    var state= $("#state").val();
    var city= $("#city").val();
    var levelEngagement= $("#levelEngagement").val();
    var engagementDetails= $("#engagementDetails").val();
    var insightDetails= $("#insightDetails").val();
    var meetingDate= $("#meetingDate").val();
    var startTime= $("#startTime").val();
    var midDayFrom= $("#midDayFrom").val();
    var timeZone= $("#timeZone").val();
    var otherMeetingDate= $("#otherMeetingDate").val();
    
    var otherStartTime= $("#otherStartTime").val();
    var otherMidDayFrom= $("#otherMidDayFrom").val();
    var otherTimeZone= $("#otherTimeZone").val();
    var additionalComments= $("#additionalComments").val();
    var opportunityName= $("#opportunityName").val();
      var bdmId= $("#bdmId").val();
      var title= $("#title").val();
      
      var resourcesHidden= $("#resourcesHidden").val();
     var regionalMgrId= $("#regionalMgrId").val();
      
      var requestType= $("#requestType").val();
      
//    alert(requestType);
//    alert(meetingType+"......");
//    alert(city+"......");
//    alert(state+"......");
//    alert(levelEngagement+"......");
//    alert(meetingDate+"......");
//    alert(startTime+"......");
    if(requestorId.trim().length==0){
        alert("Please select requestor name!");
        return false;
    }else if(rvpName.trim().length==0){
        alert("Please enter RVP Region/ RVP Name!");
        return false;
    }else if(customerName.trim().length==0){
        alert("Please select client name from suggestion list!");
        return false;
    }else if(consultantId==0&& $("#clientType").val()=='I'){
       alert("Please select client name from suggestion list!");
        return false;
    } else if(regionalMgrId=="-1"){
        alert("Please select Practice Sales from dropdown!");
        return false;
    }
//    else if(opportunityId=='-1' && $("#clientType").val()=='I'){
//        alert("Please select opportunity name!");
//        return false;
//    }else if(opportunityName.trim().length==0 && $("#clientType").val()=='E'){
//        alert("Please enter opportunity name!");
//        return false;
//    }
    else if(meetingType.trim().length==0 && requestType=='PSCER'){
        alert("Please select meeting type!");
        return false;
    }else if(state.trim().length==0&&meetingType=='Client F2F Meeting' && requestType=='PSCER'){
        alert("Please select State name!");
        return false;
    }else if(city.trim().length==0&&meetingType=='Client F2F Meeting' && requestType=='PSCER'){
        alert("Please enter city name!");
        return false;
    }else if(levelEngagement.trim().length==0 && requestType=='PSCER'){
        alert("Please select Level of Engagement!");
        return false;
    }else if(insightDetails.trim().length==0){
        alert("Please enter Specific Technology/Tool insights!");
        return false;
    }else if(meetingDate.trim().length==0 && requestType=='PSCER'){
        alert("Please select meeting date!");
        return false;
    }else if(startTime.trim().length==0 && requestType=='PSCER'){
        alert("Please enter meeting time!");
        return false;
    }
    
    else if(((preRequestStage=="Reviewed" && requestStage=="Reviewed" )||requestStage=="Approved" ) && (resourcesHidden.trim().length==0) && (roleName!='Sales')){
           alert("Please select Resources!");
        return false;
        
    }
  
   /* if(title.trim()=="BDE"){
        if(bdmId.trim()=='0'){
        alert("Please select BDM!");
        return false;}
    }else {
       return true;   
    }*/
    
  //  alert("here");
    return true;
  
}
/*
 * Comments Popup
 * 
 */


function getRequestComments(Id){
   
   
    $.ajax({
        url:'getCerCommnets.action?id='+Id,//
        context: document.body,
        success: function(responseText) {
             populateCERDescription(responseText);
      
        // getSlider(4,7);
            
        }, 
        error: function(e){
            document.getElementById("load").style.display = 'none';
            alert("error-->"+e);
        }
    });
    
    
    
  
}



function populateCERDescription(text) {
    
    var recordSplit=text.split("*@!");
   //  alert("recordSplit[0].."+recordSplit[0]);
    var length=recordSplit.length-1;
   // alert("length..."+length);
   var content ="";
    for(var i=0;i<length;i++){
    var fieldSplit=recordSplit[i].split("#^$");
    
      var background = "#3E93D4";
    var title = fieldSplit[0]+" Comments :";
    var text1 = fieldSplit[1]; 
   // var size = text1.length;
    
    //Now create the HTML code that is required to make the popup
     content = content+"<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+text1+"<br />\
    </body></html>";
    } 
     var size = content.length;
           if(size < 50){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=300,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 100){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=300,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 260){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=400,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }else{
         //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=500,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
      
   
 
    
}



function cerFieldLengthValidator(element) {
     var i=0;
     var title='';
 //alert("In Field Length validator ESCV");
    if(element.value!=null&&(element.value!="")) {
        
        
        if(element.id=="city") { 
            i=25;
            title='City';
        }else if(element.id=="engagementDetails") { 
            i=1000;
            title='Engagement Details/Summary';
        }else if(element.id=="insightDetails") { 
            i=1000;
            title='Specific Technology/Tool insights';
        }else if(element.id=="additionalComments") { 
            i=1500;
            title='Additional Comment(s)';
        }
        
           if(element.value.replace(/^\s+|\s+$/g,"").length>i) {
            str=new String(element.value);
            element.value=str.substring(0,i);
            alert("The "+element.id+" length must be less than "+i+" characters");
            element.focus();
            return false;
        }
        return true;
        
        
    }
}

function poplatingResourceValues()
{
   // alert("a");
    var skillCatArry = [];    
    $("#requestResources :selected").each(function(){
        skillCatArry.push($(this).val()); 

    });
    document.getElementById("resourcesHidden").value=skillCatArry;
}



function doRequestSubmit(status){
     if(engagementFieldsValidate()){
    if (confirm("Please confirm to "+status+"!") == true) {
    $("#requestStage").val(status);
   
            document.frmClientRequestForm.submit();
    }
    }
}
 function checkClientType(obj){
    $("#consultantId").val(0);
    $("#customerName").val("");
    $("#opportunityName").val("");
    
     removeOptionsFromDropDown(document.getElementById("opportunityId"));
     
     if(obj.value=='E'){
         $("#opportunityId").hide();
          $("#opportunityName").show();
           document.getElementById("revenues").value ='0.0';
   
    document.getElementById("noOfEmployees").value ='0';
     document.getElementById("revenues").readOnly = false;
    document.getElementById("noOfEmployees").readOnly = false;
     }else {
          $("#opportunityId").show();
          $("#opportunityName").hide();
            document.getElementById("revenues").value ='0.0';
   
    document.getElementById("noOfEmployees").value ='0';
     }
    
}


//------------


function getPopulateAccountDetails(accId){
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, populateAccountDetails);
    var url = CONTENXT_PATH+"/doPopulateAccountDetails.action?accId="+accId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}

function readyStateHandlerText(req,responseTextHandler) {
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

function populateAccountDetails(resp){
    
    
    var data=resp.split("@#");
     document.getElementById("revenues").value =data[0];
   
    document.getElementById("noOfEmployees").value =data[1];
   
    document.getElementById("revenues").readOnly = true;
    document.getElementById("noOfEmployees").readOnly = true;
}

function validatenumber(xxxxx) {
    
    var maintainplus = '';
    var numval = xxxxx.value
    if ( numval.charAt(0)=='+' ){ var maintainplus = '+';}
    curnumbervar = numval.replace(/[\\A-Za-z!"?$%^&*+_={};:'@#~,?\/<>?|`?\]\[]/g,'');
    xxxxx.value = maintainplus + curnumbervar;
    var maintainplus = '';
    // alert("enter integers only");
    xxxxx.focus;
}
 
 
 
function requestSelect(){
   
//  alert("hai");
    if( $("#requestType").val()=='RFP'){
        
//    (document.getElementById("PSCER")).style.display = "none";
//    (document.getElementById("PSCER1")).style.display = "none";
//    (document.getElementById("PSCER2")).style.display = "none";

           hideRow("meetingTypeTr");
 hideRow("meetingDateTr");
 hideRow("otherMeetingDateTr");
    }else {
//        (document.getElementById("PSCER")).style.display = "";
//        (document.getElementById("PSCER1")).style.display = "";
//        (document.getElementById("PSCER2")).style.display = "";
 showRow("meetingTypeTr");
 showRow("meetingDateTr");
 showRow("otherMeetingDateTr");
    
    }
    
}



/*PSCER Ajax Grid changes Start
 * Author : Teja Kadamanti
 * Date : 11/28/2016
 */

function clearTable(tableId) {
    var tbl =  tableId;
    var lastRow = tbl.rows.length; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}

function readyStateHandlerreq(req,responseTextHandler,element,flag) {

       // alert("ready");
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadingMessage")).style.display = "none";
               
                responseTextHandler(req.responseText,element,flag);
                pagerOption();
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
          
            (document.getElementById("loadingMessage")).style.display = "block";
        }
    }
}


function getClientrequestDetailsForPresales(element,flag){
      var oTable = document.getElementById("tblPSCERDetailsPresales");
  clearTable(oTable);
  document.getElementById("button_pageNation").innerHTML="";
   var startDate=document.getElementById("startDate").value;
    var endDate=document.getElementById("endDate").value;
     var requestType=document.getElementById("requestType").value;
     
      var pgNo=document.getElementById("pgNo").value;
      var pGflag=element;
      
      if(flag=='search'){
       if(pGflag=='1'){
            pgNo='1';  
        }
      }
       var req = newXMLHttpRequest();
        if(flag=='search'){
         req.onreadystatechange = readyStateHandlerreq(req, displayPSCERDetailsPresalesResult,element,"search"); 
        }else{
             req.onreadystatechange = readyStateHandlerreq(req, displayPSCERDetailsPresalesResult,element,"onload"); 
       
        }
        var url = CONTENXT_PATH+"/searchPSCERDetailsForPresales.action?startDate="+startDate+"&endDate="+endDate+"&requestType="+requestType+"&pgFlag="+pGflag+"&pgNo="+pgNo;
        
 req.open("GET",url,"true");    
 req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
 req.send(null); 
         
}




function displayPSCERDetailsPresalesResult(resText,element,flag) 
{
  
    if(resText.length !=0)
    {
        var oTable = document.getElementById("tblPSCERDetailsPresales");
         clearTable(oTable);
         
          var dataArray = resText.split("###");

       
      
     if(element=='1' && flag=='search'){
      
        document.getElementById("pgNo").value=1;
        
    }
    
      if(element=='1'){
      
      
        document.getElementById("totalRecords").value=dataArray[1];
    }
         
         
         var requestType=document.getElementById("requestType").value;
         var headerFields =new Array("SNo","RequestId","CustomerName","Requestor","Presales","MeetingDate","CreatedDate","MeetingType","Tools&nbsp;Tech");
            if (requestType=='RFP')
        headerFields =  new Array("SNo","RequestId","CustomerName","Requestor","Presales","CreatedDate","Tools&nbsp;Tech");
         /* else
         var headerFields = new Array("RequestId","CustomerName","Requestor","Presales","MeetingDate","CreatedDate","MeetingType","Tools&nbsp;Tech");
         */ 
    
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
       
        var resTextSplit1 = dataArray[0].split("*@!");
          generateTableHeader(tbody,headerFields);
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#^$");
            
                generateRow1(tbody,resTextSplit2,index);
            
        }
        generateFooter(tbody);
       
    }
    else {
        alert("No Records Found");
    }
}



function generateRow1(tableBody,rowFeildsSplit,index){
    
       var requestType=document.getElementById("requestType").value;
        var totalRecords=document.getElementById("totalRecords").value;
     
     var row;
    var cell;
    row = document.createElement("TR");
    row.className="gridRowEven";
  
   cell = document.createElement( "TD" );
        cell.className="gridRowEven";
       
         cell.appendChild(document.createTextNode(rowFeildsSplit[11]));
        row.appendChild(cell);
   
        cell.setAttribute('align','center');
  
    cell = document.createElement( "TD" );
        cell.className="gridRowEven";
            var j = document.createElement("a");

           j.setAttribute("href", "getClientEngagementDetails.action?requestId="+rowFeildsSplit[1]);
         
          
            j.innerHTML = rowFeildsSplit[1];
         
           document.create
            cell.appendChild(j);
              cell.setAttribute('align','center');
              row.appendChild(cell);
     
        
         cell = document.createElement( "TD" );
        cell.className="gridRowEven";
       
         cell.appendChild(document.createTextNode(rowFeildsSplit[2]));
        row.appendChild(cell);
   
        cell.setAttribute('align','center');
        cell.setAttribute('width','38%');
        
        
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
       
         cell.appendChild(document.createTextNode(rowFeildsSplit[8]));
        row.appendChild(cell);
    cell.setAttribute('width','38%');
        cell.setAttribute('align','left');

cell = document.createElement( "TD" );
        cell.className="gridRowEven";
      
         cell.appendChild(document.createTextNode(rowFeildsSplit[10]));
        row.appendChild(cell);
     cell.setAttribute('width','38%');
        cell.setAttribute('align','left');

  if (requestType!='RFP'){

cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        
         cell.appendChild(document.createTextNode(rowFeildsSplit[5]));
        row.appendChild(cell);
     
        cell.setAttribute('align','left');
        
        

  }
  
  
cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        
         cell.appendChild(document.createTextNode(rowFeildsSplit[7]));
        row.appendChild(cell);
     
        cell.setAttribute('align','left');
  
   if (requestType!='RFP'){
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        
         cell.appendChild(document.createTextNode(rowFeildsSplit[4]));
        row.appendChild(cell);
     
        cell.setAttribute('align','left');
   }
       cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        cell.innerHTML = "<a href='javascript:getRequestInSightDetails(\""+rowFeildsSplit[1]+"\")'>Click To View</a>";
         //cell.appendChild(document.createTextNode(rowFeildsSplit[5]));
        row.appendChild(cell);
    
        cell.setAttribute('align','left');	       
 
     tableBody.appendChild(row);
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


function generateFooter(tbody) {
    var requestType=document.getElementById("requestType").value;
    
   var cell;
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
 if (requestType=='RFP'){
        cell.colSpan = "9";
 }
    else{
        cell.colSpan = "11";
    }
       
   

    footer.appendChild(cell);
}


function getRequestInSightDetails(Id){
   
   
    $.ajax({
        url:'getRequestInSightDetails.action?leadId='+Id,//
        context: document.body,
        success: function(responseText) {
             populateInSightDetails(responseText);
      
        // getSlider(4,7);
            
        }, 
        error: function(e){
            document.getElementById("load").style.display = 'none';
            alert("error-->"+e);
        }
    });
    
    
    
  
}



function populateInSightDetails(text) {
    
   // var recordSplit=text.split("*@!");
   //  alert("recordSplit[0].."+recordSplit[0]);
    var length=text.length-1;
   // alert("length..."+length);
   var content ="";
  //  for(var i=0;i<length;i++){
  //  var fieldSplit=recordSplit[i].split("#^$");
    
      var background = "#3E93D4";
    //var title = "Tools Tech : ";
    var title = "  Technology/Tool insights: :";
    var text1 = text;
   // var size = text1.length;
    
    //Now create the HTML code that is required to make the popup
     content = content+"<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+text1+"<br />\
    </body></html>";
   // } 
     var size = content.length;
           if(size < 50){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=300,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 100){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=300,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 260){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=400,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }else{
         //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=500,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
      
   
 
    
}



/*PSCER Dynamic Ajax grid 
 * Author : Teja Kadamanti
 * Date : 12052016
 */


function getClientrequestDetailsForEmployee(element,flag){
      var oTable = document.getElementById("tblPSCERDetailsEmployee");
  clearTable(oTable);
  document.getElementById("button_pageNation").innerHTML="";
   var startDate=document.getElementById("startDate").value;
    var endDate=document.getElementById("endDate").value;
     var requestType=document.getElementById("requestType").value;
       var state=document.getElementById("state").value;
    
      var pgNo=document.getElementById("pgNo").value;
      var pGflag=element;
      
      if(flag=='search'){
       if(pGflag=='1'){
            pgNo='1';  
        }
      }
       var req = newXMLHttpRequest();
        if(flag=='search'){
         req.onreadystatechange = readyStateHandlerreq(req, displayPSCERDetailsEmployeeResult,element,"search"); 
        }else{
             req.onreadystatechange = readyStateHandlerreq(req, displayPSCERDetailsEmployeeResult,element,"onload"); 
       
        }
        var url = CONTENXT_PATH+"/searchPSCERDetailsForEmployee.action?startDate="+startDate+"&endDate="+endDate+"&requestType="+requestType+"&pgFlag="+pGflag+"&pgNo="+pgNo+"&state="+state;
        
 req.open("GET",url,"true");    
 req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
 req.send(null); 
         
}




function displayPSCERDetailsEmployeeResult(resText,element,flag) 
{
  
    if(resText.length !=0)
    {
        var oTable = document.getElementById("tblPSCERDetailsEmployee");
         clearTable(oTable);
         
          var dataArray = resText.split("###");

       
      
     if(element=='1' && flag=='search'){
      
        document.getElementById("pgNo").value=1;
        
    }
    
      if(element=='1'){
      
      
        document.getElementById("totalRecords").value=dataArray[1];
    }
         
         
         var requestType=document.getElementById("requestType").value;
            if (requestType=='RFP')
       var headerFields = new Array("SNo","RequestId","CustomerName","State","Requestor","Presales","CreatedDate","Comments","Tools&nbsp;Tech");
          else
         var headerFields = new Array("SNo","RequestId","CustomerName","State","Requestor","Presales","MeetingDate","CreatedDate","MeetingType","Comments","Tools&nbsp;Tech");
          
    
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
       
        var resTextSplit1 = dataArray[0].split("*@!");
          generateTableHeader(tbody,headerFields);
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#^$");
            
                generateRowForEmployee(tbody,resTextSplit2,index);
            
        }
        generateFooter(tbody);
       
    }
    else {
         document.getElementById('button_pageNation').style.display='none';
        alert("No Records Found");
       
    }
}




function generateRowForEmployee(tableBody,rowFeildsSplit,index){
    
       var requestType=document.getElementById("requestType").value;
        var totalRecords=document.getElementById("totalRecords").value;
     
     var row;
    var cell;
    row = document.createElement("TR");
    row.className="gridRowEven";
  
  
   cell = document.createElement( "TD" );
        cell.className="gridRowEven";
       
         cell.appendChild(document.createTextNode(rowFeildsSplit[11]));
        row.appendChild(cell);
    
        cell.setAttribute('align','center');
   
  
    cell = document.createElement( "TD" );
        cell.className="gridRowEven";
            var j = document.createElement("a");

           j.setAttribute("href", "getClientEngagementApprovalDetails.action?requestId="+rowFeildsSplit[1]);
         
          
            j.innerHTML = rowFeildsSplit[1];
         
           document.create
            cell.appendChild(j);
              cell.setAttribute('align','center');
              row.appendChild(cell);
     
        
         cell = document.createElement( "TD" );
        cell.className="gridRowEven";
       
         cell.appendChild(document.createTextNode(rowFeildsSplit[2]));
        row.appendChild(cell);
   
        cell.setAttribute('align','center');
        cell.setAttribute('width','38%');
        
        
          cell = document.createElement( "TD" );
        cell.className="gridRowEven";
       
         cell.appendChild(document.createTextNode(rowFeildsSplit[3]));
        row.appendChild(cell);
   
        cell.setAttribute('align','left');
        cell.setAttribute('width','38%');
        
        
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
       
         cell.appendChild(document.createTextNode(rowFeildsSplit[8]));
        row.appendChild(cell);
    
        cell.setAttribute('align','left');

cell = document.createElement("TD");
        cell.className="gridRowEven gridRowEvenPSCER";
      
         cell.appendChild(document.createTextNode(rowFeildsSplit[10]));
        row.appendChild(cell);
        cell.setAttribute('align','left');

  if (requestType!='RFP'){

cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        
         cell.appendChild(document.createTextNode(rowFeildsSplit[5]));
        row.appendChild(cell);
     
        cell.setAttribute('align','left');
        
        

  }
  
  
cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        
         cell.appendChild(document.createTextNode(rowFeildsSplit[7]));
        row.appendChild(cell);
     
        cell.setAttribute('align','left');
  
   if (requestType!='RFP'){
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        
         cell.appendChild(document.createTextNode(rowFeildsSplit[4]));
        row.appendChild(cell);
     
        cell.setAttribute('align','left');
   }
   
    cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        cell.innerHTML = "<a href='javascript:getRequestComments(\""+rowFeildsSplit[1]+"\")'>Click To View</a>";
         //cell.appendChild(document.createTextNode(rowFeildsSplit[5]));
        row.appendChild(cell);
    
        cell.setAttribute('align','left');
   
   
       cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        cell.innerHTML = "<a href='javascript:getRequestInSightDetails(\""+rowFeildsSplit[1]+"\")'>Click To View</a>";
         //cell.appendChild(document.createTextNode(rowFeildsSplit[5]));
        row.appendChild(cell);
    
        cell.setAttribute('align','left');	       
 
     tableBody.appendChild(row);
}



function getClientrequestDetailsForSales(element,flag){
      var oTable = document.getElementById("tblPSCERDetailsSales");
  clearTable(oTable);
  document.getElementById("button_pageNation").innerHTML="";
   var startDate=document.getElementById("startDate").value;
    var endDate=document.getElementById("endDate").value;
     var requestType=document.getElementById("requestType").value;
       var state=document.getElementById("state").value;
    
      var pgNo=document.getElementById("pgNo").value;
      var pGflag=element;
      
      if(flag=='search'){
       if(pGflag=='1'){
            pgNo='1';  
        }
      }
       var req = newXMLHttpRequest();
        if(flag=='search'){
         req.onreadystatechange = readyStateHandlerreq(req, displayPSCERDetailsSalesResult,element,"search"); 
        }else{
             req.onreadystatechange = readyStateHandlerreq(req, displayPSCERDetailsSalesResult,element,"onload"); 
       
        }
        var url = CONTENXT_PATH+"/searchPSCERDetailsForSales.action?startDate="+startDate+"&endDate="+endDate+"&requestType="+requestType+"&pgFlag="+pGflag+"&pgNo="+pgNo+"&state="+state;
        
 req.open("GET",url,"true");    
 req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
 req.send(null); 
         
}




function displayPSCERDetailsSalesResult(resText,element,flag) 
{
  
    if(resText.length !=0)
    {
        var oTable = document.getElementById("tblPSCERDetailsSales");
         clearTable(oTable);
         
          var dataArray = resText.split("###");

       
      
     if(element=='1' && flag=='search'){
      
        document.getElementById("pgNo").value=1;
        
    }
    
      if(element=='1'){
      
      
        document.getElementById("totalRecords").value=dataArray[1];
    }
         
         
         var requestType=document.getElementById("requestType").value;
            if (requestType=='RFP')
       var headerFields = new Array("SNo","RequestId","CustomerName","State","RequestType","Presales","CreatedDate","Comments","Tools&nbsp;Tech");
          else
         var headerFields = new Array("SNo","RequestId","CustomerName","State","RequestType","Presales","MeetingDate","CreatedDate","MeetingType","Comments","Tools&nbsp;Tech");
          
    
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
       
        var resTextSplit1 = dataArray[0].split("*@!");
          generateTableHeader(tbody,headerFields);
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#^$");
            
                generateRowForSales(tbody,resTextSplit2,index);
            
        }
        generateFooter(tbody);
       
    }
    else {
         document.getElementById('button_pageNation').style.display='none';
        alert("No Records Found");
       
    }
}




function generateRowForSales(tableBody,rowFeildsSplit,index){
    
       var requestType=document.getElementById("requestType").value;
        var totalRecords=document.getElementById("totalRecords").value;
     
     var row;
    var cell;
    row = document.createElement("TR");
    row.className="gridRowEven";
  
  
   cell = document.createElement( "TD" );
        cell.className="gridRowEven";
       
         cell.appendChild(document.createTextNode(rowFeildsSplit[11]));
        row.appendChild(cell);
    
        cell.setAttribute('align','center');
   
  
    cell = document.createElement( "TD" );
        cell.className="gridRowEven";
            var j = document.createElement("a");

           j.setAttribute("href", "getClientEngagementDetails.action?requestId="+rowFeildsSplit[1]);
         
          
            j.innerHTML = rowFeildsSplit[1];
         
           document.create
            cell.appendChild(j);
              cell.setAttribute('align','center');
              row.appendChild(cell);
     
        
         cell = document.createElement( "TD" );
        cell.className="gridRowEven";
       
         cell.appendChild(document.createTextNode(rowFeildsSplit[2]));
        row.appendChild(cell);
   
        cell.setAttribute('align','center');
        cell.setAttribute('width','38%');
        
        
          cell = document.createElement( "TD" );
        cell.className="gridRowEven";
       
         cell.appendChild(document.createTextNode(rowFeildsSplit[3]));
        row.appendChild(cell);
   
        cell.setAttribute('align','left');
        cell.setAttribute('width','38%');
        
        
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
       
         cell.appendChild(document.createTextNode(rowFeildsSplit[9]));
        row.appendChild(cell);
    
        cell.setAttribute('align','Center');

cell = document.createElement("TD");
        cell.className="gridRowEven gridRowEvenPSCER";
      if(rowFeildsSplit[10]==''){
        cell.innerHTML="-";  
      }else{
         cell.appendChild(document.createTextNode(rowFeildsSplit[10]));
      }
        row.appendChild(cell);
        cell.setAttribute('align','left');

  if (requestType!='RFP'){

cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        
         cell.appendChild(document.createTextNode(rowFeildsSplit[5]));
        row.appendChild(cell);
     
        cell.setAttribute('align','left');
        
        

  }
  
  
cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        
         cell.appendChild(document.createTextNode(rowFeildsSplit[7]));
        row.appendChild(cell);
     
        cell.setAttribute('align','left');
  
   if (requestType!='RFP'){
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        
         cell.appendChild(document.createTextNode(rowFeildsSplit[4]));
        row.appendChild(cell);
     
        cell.setAttribute('align','left');
   }
   
    cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        cell.innerHTML = "<a href='javascript:getRequestComments(\""+rowFeildsSplit[1]+"\")'>Click To View</a>";
         //cell.appendChild(document.createTextNode(rowFeildsSplit[5]));
        row.appendChild(cell);
    
        cell.setAttribute('align','left');
   
   
       cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        cell.innerHTML = "<a href='javascript:getRequestInSightDetails(\""+rowFeildsSplit[1]+"\")'>Click To View</a>";
         //cell.appendChild(document.createTextNode(rowFeildsSplit[5]));
        row.appendChild(cell);
    
        cell.setAttribute('align','left');	       
 
     tableBody.appendChild(row);
}
