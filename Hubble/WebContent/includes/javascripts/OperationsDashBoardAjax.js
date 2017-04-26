/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


 /*Don't Alter these Methods*/
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

/*Methods for getting Practices by Department*/

function getPracticeDataV1() {
    
    var departmentName = document.getElementById("departmentId").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populatePractices);
    var url = CONTENXT_PATH+"/getEmpDepartment.action?departmentName="+departmentName;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populatePractices(resXML) {    
    
    var practiceId = document.getElementById("practiceId");
    var department = resXML.getElementsByTagName("DEPARTMENT")[0];
    var practices = department.getElementsByTagName("PRACTICE");
    practiceId.innerHTML=" ";
    
    for(var i=0;i<practices.length;i++) {
        var practiceName = practices[i];
        
        var name = practiceName.firstChild.nodeValue;
        var opt = document.createElement("option");
        if(i==0){
            opt.setAttribute("value","All");
        }else{
            opt.setAttribute("value",name);
        }
        opt.appendChild(document.createTextNode(name));
        practiceId.appendChild(opt);
    }
}

 /*********************************************
 * Utilization Report  start
 ********************************************/



function getUtilizationReport()
{
    
    var oTable = document.getElementById("tblutilityReport");
    clearTable(oTable);
  
    document.getElementById("totalState").innerHTML = "";
    document.getElementById("totalAvailable").innerHTML ="";
    document.getElementById("totalOnBench").innerHTML = "";
    document.getElementById("totalOnProject").innerHTML = "";
    document.getElementById("totalRP").innerHTML = "";
    document.getElementById("totalTraining").innerHTML = "";
            
    var opsContactId=document.getElementById("opsContactId").value;
    var country=document.getElementById("country").value;
    var departmentId=document.getElementById("departmentId").value;
    var practiceId=document.getElementById("practiceId").value;
   // alert("****** opsContactId ****"+opsContactId);
    //    alert("****** country ****"+country);
    //    alert("****** departmentId ****"+departmentId);
    //    alert("****** practiceId ****"+practiceId);
    
     
    if(opsContactId=="-1")
    {
        alert("please select operational contact");
        return false;
    }
   
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("utilityReport").style.display = 'none';
                displayUtilizationReport(req.responseText);                        
            } 
        }else {
            document.getElementById("utilityReport").style.display = 'block';
        // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }; 
    var url = CONTENXT_PATH+"/getUtilizationReport.action?opsContactId="+opsContactId+"&country="+country+"&departmentId="+departmentId+"&practiceId="+practiceId;
    
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function displayUtilizationReport(response) {
    // alert(response);
    
   
    var oTable = document.getElementById("tblutilityReport");
    clearTable(oTable);
    
    var dataArray = response;
    
    if(dataArray == "no data")
    {
        alert("No Records Found for this Search");   
    }
    else {
        
        // tbody = document.createElement("TBODY");
        var headerFields = new Array("S.No","Reports To","Employee name","State","HireDate(DOJ)","Project","SkillSet");
        //generateHeader(headerArray,tableId);
       
        var temp = new Array;
        temp = dataArray.split('addTo');            
            
        if(response!=''){
                 
            document.getElementById("totalState").innerHTML = temp[1];
            document.getElementById("totalAvailable").innerHTML = temp[2];
            document.getElementById("totalOnBench").innerHTML = temp[3];
            document.getElementById("totalOnProject").innerHTML = temp[4];
            document.getElementById("totalRP").innerHTML = temp[5];
            document.getElementById("totalTraining").innerHTML = temp[6];
            ParseAndGenerateHTML(oTable,temp[0], headerFields);
        //document.getElementById(("footer"+myHTMLTable.id)).innerHTML = "Total Value is:  "+storeSum;
                
        //        // myHeadTable.innerHTML = "Total Rows"+temp[1];
        //        }else{
        //            alert('No Result For This Search...');
        //            spnFast.innerHTML="No Result For This Search...";                
        //        }
        } 
        
   
  
    }
}
		
		
		
function generateUtilizationReport(tableBody,record,fieldDelimiter){
    // alert("generateAccountsListByPriority");
    // var empLoginId=""; 
    //var empLoginId = document.getElementById("teamMemberId1").value;
    var row;
    var cell;
    var fieldLength;
    // alert("delimetr-->"+fieldDelimiter);
    var fields = record.split(fieldDelimiter);
    //  alert("fileds-->"+fields)
    fieldLength = fields.length ;
    //   alert("fileds-->"+fieldLength);
    var length = fieldLength;
    
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    // alert(fields);
    for (var i=2;i<length;i++) {            
        cell = document.createElement( "TD" );
        cell.className="gridColumn";       
        cell.innerHTML = fields[i];  
        if(parseInt(i,10)==4){
          //  alert(fields[0]);
            cell.innerHTML = "<a href='javascript:getEmployeeDetails("+fields[0]+")'>"+fields[i]+"</a>";
        }
        if(parseInt(i,10)==8){
          //  alert(fields[0]);
            cell.innerHTML = "<a href='javascript:getSkillSet("+fields[0]+")'>"+fields[i]+"</a>";
        }
       
        if(fields[i]!=''){
            if(i==1)
            {
                cell.setAttribute("align","left");
            }
            else
            {
                cell.setAttribute("align","left");     
            }
            row.appendChild( cell );
        }
    }
    empLoginId = "";     
}

function ParseAndGenerateHTML(oTable,responseString,headerFields) {
    
   //   alert("ParseAndGenerateHTML");
    var start = new Date();
    var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";   
    //alert('responseString%%%% ******'+responseString);
    //alert('rowCount%%%% ******'+rowCount);
      
    
    var records = responseString.split(recordDelimiter); 
    // alert("records---->"+records);
    generateTable(oTable,headerFields,records,fieldDelimiter);
}


function generateTable(oTable, headerFields,records,fieldDelimiter) {	
  //  alert("In Generate Table "+fieldDelimiter);
  //alert(records);
    var tbody = oTable.childNodes[0];    
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    generateTableHeader(tbody,headerFields);
    var rowlength;
    if(oTable.id == "tblutilityReport" ) {
        rowlength = records.length;
    }
    else
        rowlength = records.length-1;
    // alert("rowlength--->"+rowlength);
    if(rowlength >0 && records!=""){
        //alert("rowlength-->^"+records);
        for(var i=0;i<rowlength;i++) {
            // alert("i-->"+i);
            if(oTable.id == "tblutilityReport") {
                generateUtilizationReport(tbody,records[i],fieldDelimiter);  
            }else if(oTable.id == "tblleaveReport"){
                //  alert("in if condition");
                generateLeavesReport(tbody,records[i],fieldDelimiter); 
            }else if(oTable.id == "tblAccomadationReport"){
                //  alert("in if condition");
                generateAccomadationReport(tbody,records[i],fieldDelimiter); 
            }
            else if(oTable.id == "tblLeaves"){
                //  alert("in if condition");
                generateLeaveOverLay(tbody,records[i],fieldDelimiter); 
            }else if(oTable.id == "tblsalesReport"){
                //alert("in if condition");
                generateSalesKpiReport(tbody,records[i],fieldDelimiter); 
            }else if(oTable.id == "tblAvailableReport"){
                generateAvailableReport(tbody,records[i],fieldDelimiter); 
            }else if(oTable.id == "tblrecruitmentReport"){
                generateRecruitmentReport(tbody,records[i],fieldDelimiter); 
            }else if(oTable.id == "tblPFPortalReport"){
                generatePFPortalReport(tbody,records[i],fieldDelimiter); 
            }else if(oTable.id == "tblEmpBasedOnCustomerReport"){
                generateEmpBasedOnCustomerReport(tbody,records[i],fieldDelimiter); 
            } else if(oTable.id == "tblOnboardData"){
                generateOnboardReport(tbody,records[i],fieldDelimiter); 
            }else if(oTable.id == "tblMissingData"){
                generateMissingDataReport(tbody,records[i],fieldDelimiter); 
            }
            else{
                generateRow(oTable,tbody,records[i],fieldDelimiter);  
            }
        }
       
    }    else {
        generateNoRecords(tbody,oTable);
    }
    generateFooter(tbody,oTable);
}

function generateRow(oTable,tableBody,record,delimiter) {
  //  alert("In generate Row function")
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(delimiter);
   // alert(fields);
    fieldLength = fields.length ;
    var length;
    if(oTable.id == "utilityReport"  ){
        length = fieldLength;
    }else if(oTable.id == "tblleaveReport" ){
        length = fieldLength;
    }else if(oTable.id == "tblAccomadationReport" ){
        length = fieldLength;
    } else if(oTable.id == "tblsalesReport" ){
        length = fieldLength;
    } else if(oTable.id == "tblAvailableReport" ){
        length = fieldLength;
    } else if(oTable.id=="tblPFPortalReport"){
        length = fieldLength;

    }else if(oTable.id=="tblLeaves"){
        length = fieldLength;
        
//        alert("in grid formation");
//        for (var i=0;i<length;i++) {
//            cell = document.createElement( "TD" );
//            cell.className="gridColumn";
////            if(parseInt(i,10)==15){
////                var jobTitle = fields[i].substring(0,10);
////                cell.innerHTML = jobTitle
////        
////            }
////            else{
//                cell.innerHTML = fields[i];
////            }
//            row.appendChild( cell );
//        }
    }else if(oTable.id == "tblrecruitmentReport" ){
        length = fieldLength;
    }else if(oTable.id == "tblEmpBasedOnCustomerReport" ){
        length = fieldLength;
    } else if(oTable.id == "tblOnboardData" ){
        length = fieldLength;
    }
    else {
        length = fieldLength-1;
    }
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    
    for (var i=0;i<length;i++) {
        // cell = document.createElement( "TD" );
        //  cell.className="gridColumn";
        //alert(fields[i]+"fields[i]");
        //  cell.innerHTML = fields[i];
        
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        //alert(fields[i]+"fields[i]");
        cell.innerHTML = fields[i];
        
       
        //if(fields[i]!=''){
        if(fields[i]!=''){
            row.appendChild( cell );
        }
    }
}

function generateNoRecords(tbody,oTable) {
    var noRecords =document.createElement("TR");
    noRecords.className="gridRowEven";
    tbody.appendChild(noRecords);
    cell = document.createElement("TD");
    cell.className="gridColumn";
    
    if(oTable.id == "tblutilityReport"){
        cell.colSpan = "7";
    }else if(oTable.id == "tblleaveReport"){
        cell.colSpan = "8";
    }else if(oTable.id == "tblAccomadationReport"){
        cell.colSpan = "10";
    }else if(oTable.id == "tblLeaves"){
        cell.colSpan = "3";
    }else if(oTable.id == "tblsalesReport"){
        cell.colSpan = "7";
    }else if(oTable.id == "tblrecruitmentReport"){
        cell.colSpan = "8";
    } else if(oTable.id == "tblAvailableReport"){
        cell.colSpan = "11";
    }else if(oTable.id == "tblPFPortalReport"){
        cell.colSpan = "12";
    }else if(oTable.id == "tblEmpBasedOnCustomerReport"){
        cell.colSpan = "9";
    }else if(oTable.id == "tblOnboardData"){
        cell.colSpan = "10";
    }else if(oTable.id == "tblMissingData"){
        cell.colSpan = "4";
    }
   
    cell.innerHTML = "No Records Found for this Search";
    noRecords.appendChild(cell);
}


function generateFooter(tbody,oTable) {
    //alert(oTable.id);
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.id="footer"+oTable.id;
    //cell.colSpan = "5";
    if(oTable.id == "tblutilityReport"){
        cell.colSpan = "7";
    }else if(oTable.id == "tblleaveReport"){
        cell.colSpan = "8";
    }else if(oTable.id == "tblAccomadationReport"){
        cell.colSpan = "10";
    }else if(oTable.id == "tblLeaves"){
        cell.colSpan = "3";
    } else if(oTable.id == "tblsalesReport"){
        cell.colSpan = "7";
    }else if(oTable.id == "tblrecruitmentReport"){
        cell.colSpan = "8";
    }else if(oTable.id == "tblAvailableReport"){
        cell.colSpan = "13";
    }else if(oTable.id == "tblPFPortalReport"){
        cell.colSpan = "12";
    }else if(oTable.id == "tblEmpBasedOnCustomerReport"){
        cell.colSpan = "9";
    }else if(oTable.id == "tblOnboardData"){
        cell.colSpan = "11";
    }else if(oTable.id == "tblMissingData"){
        cell.colSpan = "4";
    }
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

function getEmployeeDetails(empId)
{
 
    // window.location='../employee/getEmployee.action?empId='+empId+'','target_blank';
     window.location='../employee/getEmployee.action?empId='+empId;
    
}



function getSkillSet(empId){
  
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, populateSkillset);
    var url = CONTENXT_PATH+"/popupSkillSetWindow.action?empId="+empId;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateSkillset(text) {
    
    var background = "#3E93D4";
    var title = "Emp Skill Set";
    var text1 = text; 
    var size = text1.length;
    

     var res = text1.split("|");

        var res1="";
        for(var i=0;i<res.length;i++){
            if((res[i].trim()== "null") || (res[i].trim()== "null") || (res[i].trim()== "" ) || (res[i].trim() == " " )){

            }else{
                if(i==res.length-1){res1=res1+res[i].trim().toUpperCase();}
                else{
                res1=res1+res[i].trim().toUpperCase()+",";}
            }
        }
        
        var res2=res1.split(",");
        var test2="";
        var count=0;
        for(var i=0;i<res2.length;i++){
            for(var j=i;j<res2.length;j++){
                if(res2[i].trim().toUpperCase()==res2[j].trim().toUpperCase()){
                    count=count+1;
                }
                else{
                    count=count;
                }
                
            }
            
            if(count==1){
                if(res2[i].trim() != ""){
                test2=test2+res2[i].trim().toUpperCase()+",";}
                count=0;
            }
        else{
            count=0;
        }    
        }
      //  alert(test2);
        var finalStr1 = test2.slice(0, test2.lastIndexOf(","));
     //   alert(finalStr1);
        var finalStr="";
        if(finalStr1.trim()== "" ){
                    finalStr="No Data";}

        
        else{
         finalStr=finalStr1+".".big();
     }
      ///  alert(test2);

    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'>"+finalStr+"<br />\
    </body></html>";
    //alert(content);
//    //alert("text1"+text1);
//    // alert("size "+content.length);
//    var indexof=(content.indexOf("|")+1);
//    var lastindexof=(content.lastIndexOf("|"));
    
    popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
  
 
      popup.document.write("<b>SkillS :</b>"+content.substr(0,content.length));
    //Write content into it.  
    //Write content into it.    
    
    
    
}

/*********************************************
 * Utilization Report  end
 ********************************************/



/*********************************************
 * Accomadation Report start
 ********************************************/
function getAccomadationReport()
{
    var opsContactIdForAcc=document.getElementById("opsContactIdForAcc").value;
    var oTable = document.getElementById("tblAccomadationReport");
    clearTable(oTable);
     if(opsContactIdForAcc=="-1")
    {
        alert("please select operational contact");
        return false;
    }
   
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("accomadationReport").style.display = 'none';
                displayAccomadationReport(req.responseText);                        
            } 
        }else {
            document.getElementById("accomadationReport").style.display = 'block';
        // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }; 
    var url = CONTENXT_PATH+"/getAccomadationReport.action?opsContactIdForAcc="+opsContactIdForAcc;
    
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}
function displayAccomadationReport(response) {
    //alert("response"+response);
    
   
    var oTable = document.getElementById("tblAccomadationReport");
    clearTable(oTable);
    
    var dataArray = response;
    
    if(dataArray == "no data")
    {
        alert("No Records Found for this Search");   
    }
    else {
        
        // tbody = document.createElement("TBODY");
        var headerFields = new Array("S.No","Employee name","Employee Id","Department","Accomadation","Room Number","Cafeteria","Occupancy Type","Occupancy Date","Contact Number");
        
        ParseAndGenerateHTML(oTable,response, headerFields);
    //document.getElementById(("footer"+myHTMLTable.id)).innerHTML = "Total Value is:  "+storeSum;
                
    // myHeadTable.innerHTML = "Total Rows"+temp[1];
       
    } 
        
   
  
}
		
		
		
function generateAccomadationReport(tableBody,record,fieldDelimiter){
    // alert("generateAccountsListByPriority");
    // var empLoginId=""; 
    //var empLoginId = document.getElementById("teamMemberId1").value;
    var row;
    var cell;
    var fieldLength;
    // alert("delimetr-->"+fieldDelimiter);
    var fields = record.split(fieldDelimiter);
    //  alert("fileds-->"+fields)
    fieldLength = fields.length ;
    //   alert("fileds-->"+fieldLength);
    var length = fieldLength;
    
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    // alert(fields);
    for (var i=0;i<length;i++) {            
        cell = document.createElement( "TD" );
        cell.className="gridColumn";       
        cell.innerHTML = fields[i];  
        //        if(parseInt(i,10)==3){
        //            cell.innerHTML = "<a href='javascript:getEmployeeDetails("+fields[0]+")'>"+fields[i]+"</a>";
        //        }
        //       
       
        if(fields[i]!=''){
            if(i==1)
            {
                cell.setAttribute("align","left");
            }
            else
            {
                cell.setAttribute("align","left");     
            }
            row.appendChild( cell );
        }
    }
    empLoginId = "";     
}

/*********************************************
 * Accomadation Report  end
 ********************************************/


/*********************************************
 * Sales KPI Report  Start
 ********************************************/
function getSalesKPIReport()
{
     var oTable = document.getElementById("tblsalesReport");
    clearTable(oTable);
    var salesLeadId=document.getElementById("salesLeadId").value;
    var startDate=document.getElementById("salesStartDate").value;
    var endDate=document.getElementById("salesEndDate").value;
    if((salesLeadId=="-1")||(startDate=="")||(endDate==""))
    {
        alert("Please select mandatory fields.");
        return false;
    }
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("salesKPIReport").style.display = 'none';
                displaySalesKpiReport(req.responseText);                        
            } 
        }else {
            document.getElementById("salesKPIReport").style.display = 'block';
        }
    }; 
    var url = CONTENXT_PATH+"/getsalesKPIReport.action?salesLeadId="+salesLeadId+"&salesStartDate="+startDate+"&salesEndDate="+endDate;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function displaySalesKpiReport(response) {
   var oTable = document.getElementById("tblsalesReport");
    clearTable(oTable);
    var dataArray = response;
     if(dataArray == "no data")
    {
        alert("No Records Found for this Search");   
    }
    else {
         var headerFields = new Array("S.No","Employee name","Reports To","Country","Logos Count","No, of Placements","No, of ConferenceCall");
         ParseAndGenerateHTML(oTable,dataArray, headerFields);
    }     
  
}


function generateSalesKpiReport(tableBody,record,fieldDelimiter){
      var row;
    var cell;
    var fieldLength;
    var fields = record.split(fieldDelimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    for (var i=0;i<length;i++) {            
        cell = document.createElement( "TD" );
        cell.className="gridColumn";       
        cell.innerHTML = fields[i];  
          if(fields[i]!=''){
            if(i==1)
            {
                cell.setAttribute("align","left");
            }
            else
            {
                cell.setAttribute("align","left");     
            }
            row.appendChild( cell );
        }
    }
   
}



var temp ="0";
function defaultDates(){
  //  alert("default date");
    var currentYear = new Date().getFullYear();    
    var currentMonth = new Date().getMonth() +1;    
    var currentDay = "01";
    /*
    var currentDay = new Date().getDate();
    if(currentDay <10 ){
        currentDay = temp+ currentDay;
    }*/
    if(currentMonth <10 ){
        currentMonth = temp+ currentMonth;
    }
    // month-date-year
    var firstDayOfMonth = currentMonth + '/' + currentDay + '/' + currentYear;
    //alert('firstDayOfMonth'+firstDayOfMonth)
    
    var intCurrentYear = parseInt(currentYear);
    
    var now = new Date();// Add 30 days   
    now.setDate(1);
    now.setDate(now.getDate() + 29);
    
    var currentYear = now.getFullYear();    
    var currentMonth = now.getMonth() +1;    
    var currentDay = now.getDate();
    if(currentDay <10 ){
        currentDay = temp+ currentDay;
    }
    if(currentMonth <10 ){
        currentMonth = temp+ currentMonth;
    }
    var lastDate;    
    var lastDate = currentMonth + '/' + currentDay + '/' + currentYear;    
    //alert('lastDate'+lastDate)
    
    
    var currentDate = new Date();
    var day = currentDate.getDate();
    var month = currentDate.getMonth() + 1;
    var year = currentDate.getFullYear();

    var endDate =  month+"/"+day+"/"+year;


    var today = new Date();
    var priorDate = new Date(endDate);
    priorDate.setDate(priorDate.getDate()-60);
    var priorDay = priorDate.getDate();
    var priorMonth = priorDate.getMonth() + 1;
    var priorYear = priorDate.getFullYear();

    //var startDate = priorMonth+"/"+priorDay+"/"+priorYear;
    var startDate="";
    if(parseInt(priorMonth)<10){
     startDate = "0"+priorMonth+"/"+"01"+"/"+priorYear;
    }else{
        startDate = priorMonth+"/"+"01"+"/"+priorYear;
    }
    
    /*Start date before last month first date and end date as current date */
    
    document.getElementById('salesStartDate').value = startDate;       
    document.getElementById('salesEndDate').value = endDate;
    document.getElementById('startDateKPI').value = startDate;       
    document.getElementById('endDateKPI').value = endDate;
    
    }



/*********************************************
 * Sales KPI Report  end
 ********************************************/

/*********************************************
 * Recruitment KPI Report  start
   author:Indira Soujanya Boni
   Date  :12/13/2015
   
 ********************************************/

function getRecruitmentKpiReport()
{
       var oTable = document.getElementById("tblrecruitmentReport");
    clearTable(oTable);
    
    var recManagerId=document.getElementById("recManagerId").value;
    var startDate=document.getElementById("startDateKPI").value;
    var endDate=document.getElementById("endDateKPI").value;
    if((recManagerId=="-1"))
    {
        alert("Please select mandatory fields.");
        return false;
    }
         var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("recruitmentReport").style.display = 'none';
                displayRecruitmentReport(req.responseText);                        
            } 
        }else {
            document.getElementById("recruitmentReport").style.display = 'block';
        }
    }; 
    var url = CONTENXT_PATH+"/getRecruitmentKpiReport.action?recManagerId="+recManagerId+"&startDate="+startDate+"&endDate="+endDate;
    
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function displayRecruitmentReport(response) {
     var oTable = document.getElementById("tblrecruitmentReport");
    clearTable(oTable);
    
    var dataArray = response;
    
    if(dataArray == "no data")
    {
        alert("No Records Found for this Search");   
    }
    else {
        
        var headerFields = new Array("S.No","Name","Reports To","Country","Requirements","Resumes Submitted","Interviews","Closures");
          ParseAndGenerateHTML(oTable,dataArray, headerFields);
    }     
  
}


function generateRecruitmentReport(tableBody,record,fieldDelimiter){
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(fieldDelimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
    
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    for (var i=0;i<length;i++) {            
        cell = document.createElement( "TD" );
        cell.className="gridColumn";       
        cell.innerHTML = fields[i];  
         if(fields[i]!=''){
            if(i==1)
            {
                cell.setAttribute("align","left");
            }
            else
            {
                cell.setAttribute("align","left");     
            }
            row.appendChild( cell );
        }
    }
    
}


/*********************************************
 * Recruitment KPI Report  end
 ********************************************/

/*********************************************
*Available Employees  List Report start
********************************************/

function getPracticeDataV2() {
  
    var departmentName=document.getElementById("departmentId1").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populatePractice);
    var url = CONTENXT_PATH+"/getEmpDepartment.action?departmentName="+departmentName;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populatePractice(resXML) {    
    
    var practiceId = document.getElementById("practiceId1");
    var department = resXML.getElementsByTagName("DEPARTMENT")[0];
    var practices = department.getElementsByTagName("PRACTICE");
    practiceId.innerHTML=" ";
    
    for(var i=0;i<practices.length;i++) {
        var practiceName = practices[i];
        
        var name = practiceName.firstChild.nodeValue;
        var opt = document.createElement("option");
        if(i==1){
            opt.setAttribute("value","All");
        }else{
            opt.setAttribute("value",name);
        }
        opt.appendChild(document.createTextNode(name));
        practiceId.appendChild(opt);
    }
}

function getAvailableList()
{
    var oTable = document.getElementById("tblAvailableReport");
    clearTable1(oTable);
    var state=document.getElementById("state").value;
    var country=document.getElementById("country1").value;
    var departmentId=document.getElementById("departmentId1").value;
    var practiceId=document.getElementById("practiceId1").value;
    var subPracticeId=document.getElementById("subPractice1").value;
   var sortedBy=document.getElementById("sortedBy").value;
   
var resourceType=document.getElementById("resourceType").value;
 

    if(state=="-1")
    {
        alert("please select state");
        return false;
    }
    if(practiceId=="--Please Select--")
    {
        practiceId="-1";
    }
      if(subPracticeId=="All")
    {
        subPracticeId="-1";
    }
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("availableReport").style.display = 'none';
                displayAvailableReport(req.responseText);                        
            } 
        }else {
            document.getElementById("availableReport").style.display = 'block';
        // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }; 
   // var url = CONTENXT_PATH+"/getAvailableEmpReport.action?state="+escape(state)+"&country="+country+"&departmentId="+departmentId+"&practiceId="+practiceId;
    var url = CONTENXT_PATH+"/getAvailableEmpReport.action?state="+escape(state)+"&country="+country+"&departmentId="+departmentId+"&practiceId="+practiceId+"&subPracticeId="+subPracticeId+"&sortedBy="+sortedBy+"&resourceType="+resourceType;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}



function displayAvailableReport(response) {
  //  alert(response);
    var oTable = document.getElementById("tblAvailableReport");
    clearTable1(oTable);
    var state=document.getElementById("state").value;
    var dataArray = response;
    
    if(dataArray == "no data")
    {
        alert("No Records Found for this Search");   
    }
    else {
        
        // tbody = document.createElement("TBODY");
       // var headerFields = new Array("S.No","Reports To","Employee name","practice","Email","Phone Number","SkillSet");
         if(state=="Available")
        {
            // tbody = document.createElement("TBODY");
       //     var headerFields = new Array("S.No","Reports&nbsp;To","Employee&nbsp;name","Exp","Practice","Available Utilization","Available&nbsp;From","Email","Phone&nbsp;Number","SkillSet");
        var headerFields = new Array("S.No","Employee&nbsp;name","Total&nbsp;Exp","Practice","Available Utilization","Available&nbsp;From","Contact&nbsp;Details","SkillSet");

        //generateHeader(headerArray,tableId);
        }
       else if(state=="OnProject"){
            // var headerFields = new Array("S.No","Reports&nbsp;To","Employee&nbsp;name","Exp","Practice","Project&nbsp;Name","Res&nbsp;Type","Available Utilization","Email","Phone&nbsp;Number","SkillSet");
           // var headerFields = new Array("S.No","Employee&nbsp;name","Total&nbsp;Exp","Practice","Project&nbsp;Name","ProjectExp","Res&nbsp;Type","Avail.Util","Contact&nbsp;Details","SkillSet");
      var headerFields = new Array("S.No","Employee&nbsp;name","Total&nbsp;Exp","ProjectExp","Practice","Project&nbsp;Name","Res&nbsp;Type","Avail.Util","Contact&nbsp;Details","SkillSet");
    
    }
        else
        {
   //         var headerFields = new Array("S.No","Reports&nbsp;To","Employee&nbsp;name","Exp","Practice","Project&nbsp;Name","Email","Phone&nbsp;Number","SkillSet");
    var headerFields = new Array("S.No","Employee&nbsp;name","Total&nbsp;Exp","Practice","Project&nbsp;Name","Contact&nbsp;Details","SkillSet");
   
        }

        //generateHeader(headerArray,tableId);
       
        var temp = new Array;
        temp = dataArray.split('addTo');            
       
        if(response!=''){
           
            document.getElementById("totalState1").innerHTML = temp[1];
        
            ParseAndGenerateHTML(oTable,temp[0], headerFields);
        }else{
            alert('No Result For This Search...');
          //  spnFast.innerHTML="No Result For This Search...";                
        }
    } 
        
   
  
}
function generateAvailableReport(tableBody,record,fieldDelimiter){
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(fieldDelimiter);
    fieldLength = fields.length ;
    var state=document.getElementById("state").value;
    var length = fieldLength;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    for (var i=1;i<length-2;i++) {            
        cell = document.createElement( "TD" );
        cell.className="gridColumn";       
      //
        if(parseInt(i,10)==2){
              cell.innerHTML = fields[i];  
            cell.innerHTML = "<a href='javascript:getEmployeeDetails("+fields[0]+")'>"+fields[i]+"</a>";
        }
      if(state=="Available")
        {
         //   alert("available");
           /* if(parseInt(i,10)==10){
                cell.innerHTML = "<a href='javascript:getSkillSet("+fields[0]+")'>"+fields[i]+"</a>";
            }*/
            if(parseInt(i,10)==8){
                cell.innerHTML = "<a href='javascript:getSkillSet("+fields[0]+")'>"+fields[10]+"</a>";
            }
            if(parseInt(i,10)==7){
                
            
            cell.innerHTML = "<a href='javascript:getEmployeeContactDetails("+fields[0]+")'>"+fields[9]+"</a>";
            }
            if(parseInt(i,10)==3){
                
           // alert("in exp");
            cell.innerHTML = "<a href='javascript:getEmployeeExpDetails(\""+fields[3]+"\",\""+fields[7]+"\")'>"+fields[8]+"</a>";
            }
             if (i==4){
            cell.innerHTML = fields[4];
         }
//          if (i==3){
//            cell.innerHTML = fields[3];
//         }
         if (i==5){
            cell.innerHTML = fields[5];
         }
         if (i==6){
            cell.innerHTML = fields[6];
         }
         

           if (i==1){
            cell.innerHTML = fields[1];
         } 
        } 
else if(state=="OnProject")
        {
             if(parseInt(i,10)==10){
            cell.innerHTML = "<a href='javascript:getSkillSet("+fields[0]+")'>"+fields[11]+"</a>";
             }
             if(parseInt(i,10)==9){
                  cell.innerHTML = "<a href='javascript:getEmployeeContactDetails("+fields[0]+")'>"+fields[10]+"</a>";
                
            }
             if(parseInt(i,10)==8){
                 
             cell.innerHTML = fields[7];
           
        }
        if(parseInt(i,10)==3){
                
           // alert("in exp");
            cell.innerHTML = "<a href='javascript:getEmployeeExpDetails(\""+fields[3]+"\",\""+fields[8]+"\")'>"+fields[9]+"</a>";
            }
         
         if (i==4){
            cell.innerHTML = fields[12];
         }
        
         
         if (i==5){
            cell.innerHTML = fields[4];
         }
         if (i==6){
            cell.innerHTML = fields[5];
         }
         if (i==7){
            cell.innerHTML = fields[6];
         }
         if (i==1){
            cell.innerHTML = fields[1];
         }
         
        }  
        else
        {
           /* if(parseInt(i,10)==9){
                cell.innerHTML = "<a href='javascript:getSkillSet("+fields[0]+")'>"+fields[i]+"</a>";
            } */
             if(parseInt(i,10)==7){
                cell.innerHTML = "<a href='javascript:getSkillSet("+fields[0]+")'>"+fields[9]+"</a>";
            } 
            
             if(parseInt(i,10)==6){
            cell.innerHTML = "<a href='javascript:getEmployeeContactDetails("+fields[0]+")'>"+fields[8]+"</a>";
        }
         if(parseInt(i,10)==3){
                
           // alert("in exp");
            cell.innerHTML = "<a href='javascript:getEmployeeExpDetails(\""+fields[3]+"\",\""+fields[6]+"\")'>"+fields[7]+"</a>";
            }
         if (i==4){
            cell.innerHTML = fields[4];
         }
         if (i==5){
            cell.innerHTML = fields[5];
         }
         
           if (i==1){
            cell.innerHTML = fields[1];
         } 

        }

        if(fields[i]!=''){
            if(i==1)
            {
                cell.setAttribute("align","left");
            }
            else
            {
                cell.setAttribute("align","left");     
            }
            row.appendChild( cell );
        }
    }
   
}


function getSkillSet(empId){
  
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, populateSkillset);
    var url = CONTENXT_PATH+"/popupSkillSetWindow.action?empId="+empId;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateSkillset(text) {
    
    var background = "#3E93D4";
    var title = "Emp Skill Set";
    var text1 = text; 
    var size = text1.length;
    
    var res = text1.split("|");

    var res1="";
    for(var i=0;i<res.length;i++){
        if((res[i].trim()== "null") || (res[i].trim()== "null") || (res[i].trim()== "" ) || (res[i].trim() == " " )){

        }else{
            if(i==res.length-1){
                res1=res1+res[i].trim().toUpperCase();
            }
            else{
                res1=res1+res[i].trim().toUpperCase()+",";
            }
        }
    }
        
    var res2=res1.split(",");
    var test2="";
    var count=0;
    for(var i=0;i<res2.length;i++){
        for(var j=i;j<res2.length;j++){
            if(res2[i].trim().toUpperCase()==res2[j].trim().toUpperCase()){
                count=count+1;
            }
            else{
                count=count;
            }
                
        }
            
        if(count==1){
            if(res2[i].trim() != ""){
                test2=test2+res2[i].trim().toUpperCase()+",";
            }
            count=0;
        }
        else{
            count=0;
        }    
    }
    //  alert(test2);
    var finalStr1 = test2.slice(0, test2.lastIndexOf(","));
    //   alert(finalStr1);
    var finalStr="";
    if(finalStr1.trim()== "" ){
        finalStr="No Data";
    }

        
    else{
        finalStr=finalStr1+".".big();
    }
    ///  alert(test2);

    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'>"+finalStr+"<br />\
    </body></html>";
    //alert(content);
    //    //alert("text1"+text1);
    //    // alert("size "+content.length);
    //    var indexof=(content.indexOf("|")+1);
    //    var lastindexof=(content.lastIndexOf("|"));
    
    popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
  
 
    popup.document.write("<b>SkillS :</b>"+content.substr(0,content.length));
//Write content into it.  
//Write content into it.    
    
    
    
}
/*********************************************
* Available Employees  List Report End
********************************************/
		
		
		
 function getProjectSheets()
{
    var startDate = document.getElementById('startDateProj').value;
    var endDate = document.getElementById('endDateProj').value;
    var country = document.getElementById('country2').value;
    var flag = document.getElementById('flog').value; 
     var departmentId=document.getElementById('departmentId2').value;
 
var ActiveProjectFlag=document.getElementById('ActiveProjects').value;
    //alert(country);
     var checkResult = compareDates(startDate,endDate);
    if(!checkResult) {
        return false;
		}
      if((flag=="-1"))
    {
        alert("Please select mandatory fields.");
        return false;
    }
   if((endDate==""))
    {
        alert("Please select mandatory fields.");
        return false;
    }
    if((startDate==""))
    {
        alert("Please select mandatory fields.");
        return false;
    }
   

    window.location = "../reports/generateProjectXls.action?startDate="+startDate+"&endDate="+endDate+"&country="+country+"&flag="+flag+"&departmentId="+departmentId+"&activeProjectFlag="+ActiveProjectFlag;
}


function getdatesforProject() {
//alert("Hai");
var now = new Date();
var firstDayPrevMonth = new Date(now.getFullYear(), now.getMonth() - 1, 1);
//var firstday = firstDayPrevMonth.toString("MM/dd/yyyy");
var sessionUserId=document.getElementById("userId").value;
var sessionIsAdminFlag=document.getElementById("isAdminFlag").value;
 var currentYear = firstDayPrevMonth.getFullYear();    
    var currentMonth = firstDayPrevMonth.getMonth() +1;    
    var currentDay = firstDayPrevMonth.getDate();
//alert(currentMonth+"/"+currentDay+"/"+currentYear);

var startDate = currentMonth+"/"+currentDay+"/"+currentYear;

var LastDayPrevMonth = new Date(now.getFullYear(), now.getMonth(), 0);
var lastYear = LastDayPrevMonth.getFullYear();    
    var lastMonth = LastDayPrevMonth.getMonth() +1;    
    var lastDay = LastDayPrevMonth.getDate();

var endDate=lastMonth+"/"+lastDay+"/"+lastYear;
if(sessionUserId=="rijju" || sessionIsAdminFlag=="YES"){
    document.getElementById('startDateProj').value = startDate;       
    document.getElementById('endDateProj').value = endDate;
}
 
  //  alert("startDateProj "+startDate+"  endDateProj "+endDate);
   // startDate=now.getMonth()+"/1/"+now.getFullYear();
  //  endDate=now.getMonth()+"/30/"+now.getFullYear();
     document.getElementById('startDateOnboard').value = startDate;       
    document.getElementById('endDateOnboard').value = endDate;
   //  alert("startDateOnboard "+startDate+"  endDateOnboard "+endDate);
}


/*********************************************
*PF Portal Report Start
********************************************/

function getPFPortalReport()
{
    
     var opsContactId= document.getElementById("opsContactId1").value;
     var oTable = document.getElementById("tblPFPortalReport");
 clearTable(oTable);
  
    var docType=document.getElementById("docTypeId").value;
    if(docType=='SSN')
        docType='PAN';
   
   
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("PFPortal").style.display = 'none';
                PFPortalReport(req.responseText);                        
            } 
        }else {
            document.getElementById("PFPortal").style.display = 'block';
        // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }; 
    var url = CONTENXT_PATH+"/getPFPortalReport.action?opsContactIdForPF="+opsContactId+"&docType="+docType;
 // alert(url);
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

  function PFPortalReport(response) {
  
        var oTable = document.getElementById("tblPFPortalReport");
    clearTable(oTable);
    var dataArray = response;
    if(dataArray == "no data")
    {
        alert("No Records Found for this Search");   
    }
    else {
        
       var headerFields = new Array("S.No","EmpNo","UANNo","PFNo","NAME","docNumber","IfscCode","PhyChallenged","PhyCategory","Gender","MaritalStatus","Int'lWorker");
          ParseAndGenerateHTML(oTable,dataArray, headerFields);
    }     
  
    } 
        
   function generatePFPortalReport(tableBody,record,fieldDelimiter){
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(fieldDelimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
    
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    for (var i=0;i<length;i++) {            
        cell = document.createElement( "TD" );
        cell.className="gridColumn";       
        cell.innerHTML = fields[i];  
         if(fields[i]!=''){
            if(i==1)
            {
                cell.setAttribute("align","left");
            }
            else
            {
                cell.setAttribute("align","left");     
            }
            row.appendChild( cell );
        }
    }
    
}

/*********************************************
* PF Portal End
********************************************/

/*********************************************
* PF Portal XL Start
********************************************/

function getPFPortalXLReport()
{
   var opsContactId= document.getElementById("opsContactId1").value;
 var docType=document.getElementById("docTypeId").value;
   
    window.location = "generatePFPortalReport.action?opsContactId="+opsContactId+"&docType="+docType;

}
/*********************************************
* PF Portal XL End
********************************************/
var Billable=0;
var Shadows=0;
 var Main=0;
var Training=0;
var OverHead=0;

function getProjectDetailsByCustomer()
{
Billable=0;
Shadows=0;
 Main=0;
 Training=0;
 OverHead=0;  
 
     var oTable = document.getElementById("tblEmpBasedOnCustomerReport");
      clearTable1(oTable);
    var customerName=document.getElementById("accountName").value;
    var accountId=document.getElementById("accountId").value;
    var projectId=document.getElementById("projectId").value;
    var currStatus=document.getElementById("currStatus").value;
    var resourceCountry=document.getElementById("resourceCountry").value;
    
    if(accountId=="-1")
        {
            alert("please enter customer name");
            return false;
        }
        if(projectId=="-1")
            {
               alert("please select project");
               return false;
            }
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("employeeReport").style.display = 'none';
                displayEmpBasedOnCustomerReport(req.responseText);                        
            } 
        }else {
            document.getElementById("employeeReport").style.display = 'block';
        // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }; 
    var url = CONTENXT_PATH+"/getEmployeesBasedOnCustomer.action?accountId="+accountId+"&projectId="+projectId+"&status="+currStatus+"&country="+resourceCountry;
    
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}



function displayEmpBasedOnCustomerReport(response) {
  //  alert(response);
    var oTable = document.getElementById("tblEmpBasedOnCustomerReport");
    clearTable1(oTable);
  //  var state=document.getElementById("state").value;
    var dataArray = response;
    
    if(dataArray == "no data")
    {
        alert("No Records Found for this Search");   
    }
    else {
        
            var headerFields = new Array("S.No","Resource&nbsp;Name","Status","Role","Country","Resource&nbsp;Start&nbsp;date","Resource&nbsp;End&nbsp;date","Billable","Utilization");
        //generateHeader(headerArray,tableId);
       
        var temp = new Array;
        temp = dataArray.split('addTo');            
       
        if(response!=''){
           
            
        
            ParseAndGenerateHTML(oTable,temp[0], headerFields);
           // document.getElementById("totalCount").innerHTML = temp[1]+"(Billable:"+Billable+"+ Shadows:"+Shadows+")";
            document.getElementById("totalCount").innerHTML = temp[1]+"( Main(Billable):"+Billable+"+ Main:"+(Main-Billable)+"+ Shadows:"+ Shadows+"<br/> Training:"+ Training+"+ OverHead:"+ OverHead+")";
        }else{
            alert('No Result For This Search...');
          //  spnFast.innerHTML="No Result For This Search...";                
        }
    } 
        
   
  
}	
		

function generateEmpBasedOnCustomerReport(tableBody,record,fieldDelimiter){
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(fieldDelimiter);
    fieldLength = fields.length ;
    var state=document.getElementById("state").value;
    var length = fieldLength;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    for (var i=0;i<length;i++) {
        
        if(i!=8){
        cell = document.createElement( "TD" );
        cell.className="gridColumn";       
        cell.innerHTML = fields[i]; 
        }
        if(i==7){
            if(fields[i]=='YES')
                Billable = parseInt(Billable,10)+1;
         }
        
        if(i==8){
            if(fields[i]=='Main')
                Main = parseInt(Main,10)+1;
            else   if(fields[i]=='Shadow')
                Shadows = parseInt(Shadows,10)+1;
             else   if(fields[i]=='Training')
                Training = parseInt(Training,10)+1;
             else   if(fields[i]=='OverHead')
                OverHead = parseInt(OverHead,10)+1;
        }
            
        
        

        if(fields[i]!=''){
            if(i==1)
            {
                cell.setAttribute("align","left");
            }
            else
            {
                cell.setAttribute("align","left");     
            }
            row.appendChild( cell );
        }
    }
   
}	



function clearTable1(tableId) {
    var tbl =  tableId;
    var lastRow = tbl.rows.length; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}
//onboard report dashboard start
function getOnBoardReport()
{
    
    var oTable = document.getElementById("tblOnboardData");
    clearTable(oTable);
  //  alert("in getOnBoardReport");
    var startDate = document.getElementById('startDateOnboard').value;
    var endDate = document.getElementById('endDateOnboard').value;
    var country = document.getElementById('country3').value;
    var flag = document.getElementById('flog1').value; 
     var departmentId3 = document.getElementById('departmentId3').value; 
     var checkResult = compareDates(startDate,endDate);
    if(!checkResult) {
        return false;
		}

    if((flag=="-1"))
    {
        alert("Please select mandatory fields.");
        return false;
    }
   if((endDate==""))
    {
        alert("Please select mandatory fields.");
        return false;
    }
    if((startDate==""))
    {
        alert("Please select mandatory fields.");
        return false;
    } 
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("onBoardReport").style.display = 'none';
                displayOnboardReport(req.responseText);                        
            } 
        }else {
            document.getElementById("onBoardReport").style.display = 'block';
        }
    }; 
    var url = CONTENXT_PATH+"/getOnboardReport.action?country="+country+"&startDate="+startDate+"&endDate="+endDate+"&flag="+flag+"&departmentName="+departmentId3;
    
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function displayOnboardReport(response) {
  //  alert(response);
    var oTable = document.getElementById("tblOnboardData");
    clearTable1(oTable);
    var flag = document.getElementById('flog1').value; 
    var dataArray = response;
    
    if(dataArray == "no data")
    {
        alert("No Records Found for this Search");   
    }
    else {
        if(flag=="1")
            {
        var headerFields = new Array("S.No","MSS/CNE","EmpId","LName","FName","DoJ","DoE","Practice","SubPractice","Other");
            }
             if(flag=="2")
            {
        var headerFields = new Array("S.No","MSS/CNE","EmpId","LName","FName","DoJ","DoE","Practice","SubPractice","other","Roll-Off Date");
            }
        ParseAndGenerateHTML(oTable,dataArray, headerFields);
    }     
  
}


function generateOnboardReport(tableBody,record,fieldDelimiter){
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(fieldDelimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
    
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    for (var i=0;i<length;i++) {            
        cell = document.createElement( "TD" );
        cell.className="gridColumn";       
        cell.innerHTML = fields[i];  
        if(fields[i]!=''){
            if(parseInt(i,10)==9){
                cell.innerHTML = "<a href='javascript:getOtherDetails(\""+fields[9]+"\")'>View</a>";
            }
            if(i==1)
            {
                cell.setAttribute("align","left");
            }
            else
            {
                cell.setAttribute("align","left");     
            }
            row.appendChild( cell );
        }
    }
    
}

//onboard report dashboard end



 function disbaleDates()
{
//alert("test")
  var flag = document.getElementById('flog').value;
 document.getElementById('startDateProj').readOnly = false; 
// $("#test").prop("onclick", null);
 if(flag == 1 || flag == 2)
     {
         document.getElementById('ActiveProjects').disabled = false; 
     }
     else
         {
            document.getElementById('ActiveProjects').disabled = true;  
         }
		 


  if(flag == 1 || flag == 3||flag == 6)
      {
          var endDate=document.getElementById('endDateProj').value;
         // alert(endDate);
          var d = new Date(endDate);
        // alert(d);
          var month=d.getMonth()+1;
          var year=d.getFullYear();
         // alert(month+"  "+year);
          var statDate=month+'/01/'+year;
         // alert(statDate);
           document.getElementById('startDateProj').value = statDate; 
           document.getElementById('startDateProj').readOnly = true; 
           document.getElementById('startDateProj').disabled=true;
           $("#startDateTag"). removeAttr("href");
      }
      if(flag == 2 || flag == 4 || flag == 5)
      {
       //  $("#test").prop("onclick", null);
           document.getElementById('startDateProj').readOnly = false;
           document.getElementById('startDateProj').disabled=false;
            $("#startDateTag"). attr("href","javascript:cal7.popup();");
      }
      
}

function changeStartDateByEnddate()
{
   // alert("test1")
    var flag = document.getElementById('flog').value;
   // alert(flag)
    if(flag == 1 || flag == 3||flag == 6)
          {
              
              var endDate=document.getElementById('endDateProj').value;
         // alert(endDate);
          var d = new Date(endDate);
        // alert(d);
          var month=d.getMonth()+1;
          var year=d.getFullYear();
         // alert(month+"  "+year);
          var statDate=month+'/01/'+year;
         // alert(statDate);
           document.getElementById('startDateProj').value = statDate; 
             // alert("please select")
          }
}

function getOtherDetails(data)
{
   // alert(data);
     var background = "#3E93D4";
      var title = "Emp Other Details";
      var data1=data.split("|");
      var finalString1="Reporting Manager : "+data1[0];
      var finalString2="Current Location : "+data1[1];  
       var  finalString3="HR Contact : "+data1[2];
      var content = "<html><head><title>"+title+"<br /></title></head>\
    <body bgcolor='"+background +"' style='color:white;'><br />"+finalString1+"<br />"+finalString2+"<br />"+finalString3+"<br />\
    </body></html>";
    //alert(content);
    //    //alert("text1"+text1);
    //    // alert("size "+content.length);
    //    var indexof=(content.indexOf("|")+1);
    //    var lastindexof=(content.lastIndexOf("|"));
    
    popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
  
 
    popup.document.write("<b>Emp Other Dtails :</b>"+content.substr(0,content.length));
}
 //missing fields dashboard start
function getMissingFileldsReport()
{
    
    var oTable = document.getElementById("tblMissingData");
    clearTable(oTable);
  //  alert("in getOnBoardReport");
   
    //var country = document.getElementById('country3').value;
    var missingField = document.getElementById('missingField').value; 
    var location = document.getElementById('location').value; 
    var country = document.getElementById('countryForMissingData').value;
     var opsContactId = document.getElementById('opsContactIdRMD').value; 
    document.getElementById("recordDisplay").style.display = 'none';
   // document.getElementByClassName("pagination").style.display = 'none';
   $(".pagination").css("display","none");
    if(missingField=='-1'){
       alert("please select the Based on field!");
       return false;
   }
   
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("missingFileldReport").style.display = 'none';
                displayMissingDataReport(req.responseText);                        
            } 
        }else {
            document.getElementById("missingFileldReport").style.display = 'block';
        }
    }; 
  //  var url = CONTENXT_PATH+"/getOnboardReport.action?country="+country+"&startDate="+startDate+"&endDate="+endDate+"&flag="+flag;
    var url = CONTENXT_PATH+"/getMissingDataReport.action?missedField="+missingField+"&country="+country+"&location="+location+"&opsContactId="+opsContactId;
   
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function displayMissingDataReport(response) {
  //  alert(response);
    var oTable = document.getElementById("tblMissingData");
    clearTable1(oTable);
    //var flag = document.getElementById('flog1').value; 
    var dataArray = response;
    
    if(dataArray == "no data")
    {
        alert("No Records Found for this Search");   
    }
    else {
        
        var headerFields = new Array("S.No","Employee Name","E-Mail","Work-Phone");
           
        ParseAndGenerateHTML(oTable,dataArray, headerFields);
        document.getElementById("recordDisplay").style.display = 'block';
      //   document.getElementByClassName("pagination").style.display = 'block';
      $(".pagination").css("display","block");
    }     
   $('#tblMissingData').tablePaginate({navigateType:'navigator'},recordPage);
}


function generateMissingDataReport(tableBody,record,fieldDelimiter){
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(fieldDelimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
    
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    for (var i=0;i<length;i++) {            
        cell = document.createElement( "TD" );
        cell.className="gridColumn";       
        cell.innerHTML = fields[i];  
        if(fields[i]!=''){
          
                cell.setAttribute("align","left");     
            
            row.appendChild( cell );
        }
    }
    
}

//missing fields dashboard end


 function getLocationsByCountry(obj){
     
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateEmployeeLocations);
    var url = CONTENXT_PATH+"/getEmployeeLocations.action?country="+obj.value;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}



function populateEmployeeLocations(resXML) {    
    var locationObj = document.getElementById("location");
    var country = resXML.getElementsByTagName("COUNTRY")[0];
    //var teamMember = team.getElementsByTagName("TEAMMEMBER");    
    locationObj.innerHTML=" ";
    for(var i=0;i<country.childNodes.length;i++) {
        var location = country.childNodes[i];
        var id =location.getElementsByTagName("LOCATION-ID")[0];        
        var name = location.getElementsByTagName("LOCATION-NAME")[0];
       // alert(id.childNodes[0].nodeValue);
        var opt = document.createElement("option");
        opt.setAttribute("value",id.childNodes[0].nodeValue);
        opt.appendChild(document.createTextNode(name.childNodes[0].nodeValue));
        //alert(name.childNodes[0].nodeValue);
        locationObj.appendChild(opt);
    }
}
 function getLocationsByCountryOnload(){
     var country=document.getElementById("countryForMissingData").value;
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateEmployeeLocations);
    var url = CONTENXT_PATH+"/getEmployeeLocations.action?country="+country;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}



 function getEmployeeContactDetails(empId){
  
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, populateContactDetails);
    var url = CONTENXT_PATH+"/popupContactsWindow.action?empId="+empId;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateContactDetails(text) {
    var background = "#3E93D4";
    var title = "Contact Details:";
    var text1 = text; 
    var size = text1.length;
    
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
        popup.document.write("<b>Reports To : </b>"+content.substr((content.lastIndexOf("^")+1),content.length));
        popup.document.write("<br><br>");
        popup.document.write("<b>Email Address :</b>"+content.substr(0,content.indexOf("^")));
        popup.document.write("<br><br>");
        popup.document.write("<b>Work Phone No :</b>"+content.substr((content.indexOf("^")+1),(lastindexof-indexof)));
        popup.document.write("<br><br>");
    }
    
}


function getEmployeeExpDetails(mss,prev) {
  
    var background = "#3E93D4";
    var title = "Experience Details:";
   
//    var size = text1.length;
    
    //Now create the HTML code that is required to make the popup
   var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><br />\
    </body></html>";
    
    //alert("text1"+text1);
    // alert("size "+content.length);
  
    
    popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
    if(content.indexOf("^")){
        //alert(content.substr(0,content.indexOf("//")));
        popup.document.write("<b>Mss EXP : </b>"+mss);
        popup.document.write("<br><br>");
        popup.document.write("<b>Prev Exp :</b>"+prev);
       popup.document.write(content);
      
    }
    
}



function getSubPracticeData2(){
    
var practiceName = document.getElementById("practiceId1").value;

    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateSubPractices);
    var url = CONTENXT_PATH+"/getEmpPractice.action?practiceName="+practiceName;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}



function populateSubPractices(resXML) {
   // alert(resXML);
    var subPractice = document.getElementById("subPractice1");
    
    var practice = resXML.getElementsByTagName("PRACTICE")[0];
    
    var subPractices = practice.getElementsByTagName("SUBPRACTICE");
    subPractice.innerHTML=" ";
    
    for(var i=0;i<subPractices.length;i++) {
        var subPracticeName = subPractices[i];
        
        var name = subPracticeName.firstChild.nodeValue;
        var opt = document.createElement("option");
        if(i==0){
            opt.setAttribute("value","All");
        }else{
            opt.setAttribute("value",name);
        }
        opt.appendChild(document.createTextNode(name));
        subPractice.appendChild(opt);
    }
}



 function getProjectStatus(){
    
      
document.getElementById("projectStatusSpan").innerHTML='';
   
    var projectId=document.getElementById("projectId").value;
   //alert(projectId);
        if(projectId=="-1")
            {
               alert("please select project");
               return false;
            }
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
             
                displayProjectStatus(req.responseText);                        
            } 
        }else {
         
       
        }
    }; 
    var url = CONTENXT_PATH+"/getProjectStatusById.action?projectId="+projectId;
    
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function displayProjectStatus(resTest){
    //alert(resTest);
//    (document.getElementById("projStatus")).style.display = "none";
var res=resTest.split("#^$"); 

 //document.getElementById("projectStatusSpan").innerHTML='<font color="green">'+res[0]+','+res[1]+','+res[2]+'</font>';
    document.getElementById("projectStatusSpan").innerHTML='<font color="green" size="2px">Project Status:<b>'+res[0]+'</b><br>&nbsp;&nbsp;&nbsp;Start Date:<b>'+res[1]+'</b><br>&nbsp;&nbsp;&nbsp;End Date<b>'+res[2]+'</b></font>';
}
