/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


var totalTlRating = 0;
var totalHrRating = 0;
//var totalHr="";
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


function readyStateHandler(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadingMessage")).style.display = "none";
                responseTextHandler(req.responseText);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
            (document.getElementById("loadingMessage")).style.display = "block";
        }
    }
}

function readyStateHandlerText(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                //(document.getElementById("loadingMessage")).style.display = "none";
                responseTextHandler(req.responseText);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
           // (document.getElementById("loadingMessage")).style.display = "block";
        }
    }
}



function clearTable(tableId) {
    var tbl =  tableId;
    var lastRow = tbl.rows.length; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}

function getTotalReviews() {
     totalTlRating = 0;
 totalHrRating = 0;
    clearTable(document.getElementById("tblUpdate"));
    
     var userId = document.getElementById("userId").value;
     var reviewType = document.getElementById("reviewType").value;
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, displayReviewsResult);
   
   // var url = CONTENXT_PATH+"/getEmpLeavesOpp.action?month="+month+"&year="+year+"&dummy="+new Date().getTime();
    var url = CONTENXT_PATH+"/getEmpTotalReviews.action?userId="+userId+"&reviewTypeId="+reviewType;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


//-------------------------
function displayReviewsResult(resText) {
    if(resText.length !=0 && resText!="no data"){
        var oTable = document.getElementById("tblUpdate");
       // var headerFields = new Array("S.No","Employee Name","Reports To","Start Date(YY-MM-DD)","End Date(YY-MM-DD)","Leaves")
 //var headerFields = new Array("S.No","Employee Name","Reports To","Start Date(YY-MM-DD)","End Date(YY-MM-DD)","Status","Leaves")
  //var headerFields = new Array("S.No","ReviewType","ReviewTitle","Status","EmpComments","Tl&nbsp;Rating","Tl&nbsp;Comments","Hr&nbsp;Rating","Hr&nbsp;Comments","ReviewDate")
 // var headerFields = new Array("S.No","ReviewType","ReviewTitle","Status","Description","Tl&nbsp;Rating","Tl&nbsp;Comments","Hr&nbsp;Rating","Hr&nbsp;Comments","ReviewDate")
  var headerFields = new Array("S.No","ReviewType","ReviewTitle","Description","TL&nbsp;Status","Tl&nbsp;Rating","Tl&nbsp;Comments","HR&nbsp;Status","Hr&nbsp;Rating","Hr&nbsp;Comments","ReviewDate")
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
        generateTableHeader(tbody,headerFields);
        var resTextSplit1 = resText.split("*@!");
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#^$");
            generateRow(tbody,resTextSplit2,index);
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
        cell.innerHTML = headerFields[i];
        //cell.width = 120;
    }
}

 function generateRow(tableBody,rowFeildsSplit,index) {
    
    //alert("rowFeildsSplit"+rowFeildsSplit[rowFeildsSplit.length-5]);
    var row;
    var cell;
    row = document.createElement("TR");
    row.className="gridRowEven";
    cell = document.createElement("TD");
    cell.className="gridRowEven";
    cell.innerHTML = index+1;
    row.appendChild(cell);
    tableBody.appendChild(row);
    var totalLeaves;
   
   for (var i=0; i<=rowFeildsSplit.length-1; i++) {
       //alert(rowFeildsSplit[2]);
      // if(rowFeildsSplit[2]!='Denied'){
      if(rowFeildsSplit[3]=='Approved'){
       if(i==4){
           totalTlRating = parseInt(totalTlRating,10)+parseInt(rowFeildsSplit[4],10);
       }
      }
     // alert(rowFeildsSplit[6]);
       if(rowFeildsSplit[6]=='Approved'){
       if(i==7){
           totalHrRating = parseInt(totalHrRating,10)+parseInt(rowFeildsSplit[7],10);
       }
       }
       
       
       if(i!=rowFeildsSplit.length-1){
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild(cell);
       }
       if(i==2){
            //cell.innerHTML = rowFeildsSplit[i];
            var anc = document.createElement("a");
          
          anc.setAttribute("href", "javascript:getEmpReviewComments('"+rowFeildsSplit[rowFeildsSplit.length-1]+"' )");
           anc.appendChild(document.createTextNode("Click Here"));
            cell.appendChild(anc);
        }else if(i==4 || i==6){
            if(rowFeildsSplit[2]=='Opened'){
              cell.innerHTML ="-"  ;
            }else {
                 cell.innerHTML = rowFeildsSplit[i];
            }
           
        }
       else if(i==5){
            //cell.innerHTML = rowFeildsSplit[i];
            var anc = document.createElement("a");
          
          anc.setAttribute("href", "javascript:getTlReviewComments('"+rowFeildsSplit[rowFeildsSplit.length-1]+"' )");
           anc.appendChild(document.createTextNode("Click Here"));
            cell.appendChild(anc);
        }else if(i==8){
            var anc = document.createElement("a");
            anc.setAttribute("href", "javascript:getHrReviewComments('"+rowFeildsSplit[rowFeildsSplit.length-1]+"')");
            anc.appendChild(document.createTextNode("Click Here"));
            cell.appendChild(anc);
        }else if(i!=rowFeildsSplit.length-1){
            cell.innerHTML = rowFeildsSplit[i];
        }
        cell.width = 120;
    }
}





function generateFooter(tbody) {
    var row;
    var cell;
    var totalVal =0;
    row = document.createElement("TR");
    row.className="gridPager";
    tbody.appendChild(row);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.colSpan = "11";
    //cell.width = 100;
    cell.align="right";
  //  totalLeavesApp = totalLeavesAppled.split(",");
   // for(i=0;i<totalLeavesApp.length-1;i++) {
   //     totalVal = totalVal+parseInt(totalLeavesApp[i]);
   // }
 cell.innerHTML = "Total Tl Rating : "+totalTlRating+"&nbsp; Hr Rating : "+totalHrRating;
    row.appendChild(cell);
}







function generateFooter(tbody) {
    var row;
    var cell;
    var totalVal =0;
    row = document.createElement("TR");
    row.className="gridPager";
    tbody.appendChild(row);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.colSpan = "11";
    //cell.width = 100;
    cell.align="right";
  //  totalLeavesApp = totalLeavesAppled.split(",");
   // for(i=0;i<totalLeavesApp.length-1;i++) {
   //     totalVal = totalVal+parseInt(totalLeavesApp[i]);
   // }
 cell.innerHTML = "Total Tl Rating : "+totalTlRating+"&nbsp; Hr Rating : "+totalHrRating;
    row.appendChild(cell);
}



function getEmpReviewComments(reviewId) {
   //  var userId = document.getElementById("userId").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, displayEmpReviewsComments);
   
   // var url = CONTENXT_PATH+"/getEmpLeavesOpp.action?month="+month+"&year="+year+"&dummy="+new Date().getTime();
    var url = CONTENXT_PATH+"/getEmpReviewComments.action?reviewId="+reviewId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}



function displayEmpReviewsComments(resText) {
              var background = "#3E93D4";
              //var background = "blue";
    var title = "Employee Comments";
   // var text1 = text; 
   // var size = text1.length;
    
    
  
    var size = resText.length;
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head><body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+resText+"<br/></body></html>";
    
    
   var popup ='';
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



function getTlReviewComments(reviewId) {
   //  var userId = document.getElementById("userId").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, displayTlReviewsComments);
   
   // var url = CONTENXT_PATH+"/getEmpLeavesOpp.action?month="+month+"&year="+year+"&dummy="+new Date().getTime();
    var url = CONTENXT_PATH+"/getTlReviewComments.action?reviewId="+reviewId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}



function displayTlReviewsComments(resText) {
              var background = "#3E93D4";
              //var background = "blue";
    var title = "Tl Comments";
   // var text1 = text; 
   // var size = text1.length;
    
    
  
    var size = resText.length;
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head><body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+resText+"<br/></body></html>";
    
    
   var popup ='';
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



function getHrReviewComments(reviewId) {
   //  var userId = document.getElementById("userId").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, displayHrReviewsComments);
   
   // var url = CONTENXT_PATH+"/getEmpLeavesOpp.action?month="+month+"&year="+year+"&dummy="+new Date().getTime();
    var url = CONTENXT_PATH+"/getHrReviewComments.action?reviewId="+reviewId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}



function displayHrReviewsComments(resText) {
    
              var background = "#3E93D4";
              //var background = "blue";
    var title = "Hr Comments";
   // var text1 = text; 
   // var size = text1.length;
    
    
  
    var size = resText.length;
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head><body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+resText+"<br/></body></html>";
    
    
   var popup ='';
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
        
        
        //-------------------------------------------------------------
        

function readyStateHandlerXml(req,responseXmlHandler) {
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
        
        
        
function getEmployeesByDept(){
 //   var deptName = document.employeeForm.departmentId.value;
    var deptName = document.getElementById("departmentId").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerXml(req, populateEmployeesByDept);
   // var url = CONTENXT_PATH+"/getEmpForReportsTo.action?deptName="+deptName;
    var url = CONTENXT_PATH+"/getEmployeesByDept.action?deptName="+deptName;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}

function populateEmployeesByDept(resXML) {
 //   var reportsTo = document.employeeForm.reportsTo;
      var empId = document.getElementById("empnameById");
    var team = resXML.getElementsByTagName("TEAM")[0];
    var users = team.getElementsByTagName("USER");
    empId.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("userId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        empId.appendChild(opt);
    }
}

	
// Performance Review dashboard chnages

function getTopPerformers() {
     clearTable(document.getElementById("tblTopPerfReviews"));
     var startDate = document.getElementById("startDate").value;
    var endDate = document.getElementById("endDate").value;
    var isManagerInclude = document.getElementById("isManagerInclude").checked;
    var departmentId=document.getElementById("departmentId2").value;

    
    
    //alert(isManagerInclude);
      var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateTopPerformers);
   // var url = CONTENXT_PATH+"/getEmpForReportsTo.action?deptName="+deptName;
    var url = CONTENXT_PATH+"/getTopPerformers.action?startDate="+startDate+"&endDate="+endDate+"&isManagerInclude="+isManagerInclude +"&departmentId="+departmentId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
   
}



//-------------------------
function populateTopPerformers(resText) {
    if(resText.length !=0 && resText!="no data"){
        var oTable = document.getElementById("tblTopPerfReviews");
       // var headerFields = new Array("S.No","Employee Name","Reports To","Start Date(YY-MM-DD)","End Date(YY-MM-DD)","Leaves")
 //var headerFields = new Array("S.No","Employee Name","Reports To","Start Date(YY-MM-DD)","End Date(YY-MM-DD)","Status","Leaves")
  //var headerFields = new Array("S.No","ReviewType","ReviewTitle","Status","EmpComments","Tl&nbsp;Rating","Tl&nbsp;Comments","Hr&nbsp;Rating","Hr&nbsp;Comments","ReviewDate")
//  var headerFields = new Array("Employee Name","Designation","Department ","Rank","Score")

var dept=document.getElementById("departmentId2").value;
   var headerFields;
  if(dept=='Sales'){
      headerFields= new Array("Employee Name","Designation","Practice","Region","Department ","Rank","Score")
  }
  else{
      headerFields= new Array("Employee Name","Designation","Department ","Rank","Score")
  }
  
  
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
        generateTableHeader(tbody,headerFields);
        var resTextSplit1 = resText.split("*@!");
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#^$");
            generateDashboardRow(tbody,resTextSplit2,index);
        }
        generateDashboardFooter(tbody);
    }else {
        alert("No Records Found");
    }
}
function generateDashboardRow(tableBody,rowFeildsSplit,index) {
    
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
        cell.className="gridRowEven";
        row.appendChild(cell);
        cell.innerHTML = rowFeildsSplit[i];
     cell.width = 120;
      
     
    }
}
function generateDashboardFooter(tbody) {
    var row;
    var cell;
    var totalVal =0;
    row = document.createElement("TR");
    row.className="gridPager";
    tbody.appendChild(row);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.colSpan = "12";
    cell.align="right";
    row.appendChild(cell);
}
function getEmployees() {
    clearTable(document.getElementById("tblPersonalRecord"));
    var startDate = document.getElementById("personalRecordStartDate").value;
    var endDate = document.getElementById("personalRecordEndDate").value;
    var isManagerInclude = document.getElementById("isManagerinclude").checked;
    var departmentId=document.getElementById("departmentId3").value;
    document.getElementById("prloadingMessage").style.display='block';
    
    //alert(isManagerInclude);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, getEmployeesResponse);
    // var url = CONTENXT_PATH+"/getEmpForReportsTo.action?deptName="+deptName;
    var url = CONTENXT_PATH+"/getEmployeesForPerformers.action?startDate="+startDate+"&endDate="+endDate+"&isManagerInclude="+isManagerInclude+"&departmentId="+departmentId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
   
}



//-------------------------
function getEmployeesResponse(resText) {
    if(resText.length !=0 && resText!="no data"){
        var oTable = document.getElementById("tblPersonalRecord");
        // var headerFields = new Array("S.No","Employee Name","Reports To","Start Date(YY-MM-DD)","End Date(YY-MM-DD)","Leaves")
        //var headerFields = new Array("S.No","Employee Name","Reports To","Start Date(YY-MM-DD)","End Date(YY-MM-DD)","Status","Leaves")
        //var headerFields = new Array("S.No","ReviewType","ReviewTitle","Status","EmpComments","Tl&nbsp;Rating","Tl&nbsp;Comments","Hr&nbsp;Rating","Hr&nbsp;Comments","ReviewDate")
       // var headerFields = new Array("Employee Name","Designation","Department ","Rank","Score","Reporting Manager","Reviews Submitted","Reviews Approved","Review Approval Percentage")
       
        var dept=document.getElementById("departmentId3").value;
   var headerFields;
  if(dept=='Sales'){
      headerFields = new Array("Employee Name","Designation","Practice","Region","Department ","Rank","Score","Reporting Manager","Reviews Submitted","Reviews Approved","Review Approval Percentage")
  }
  else{
      headerFields = new Array("Employee Name","Designation","Department ","Rank","Score","Reporting Manager","Reviews Submitted","Reviews Approved","Review Approval Percentage")
  }
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
        generateTableHeader(tbody,headerFields);
        var resTextSplit1 = resText.split("*@!");
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#^$");
            generateDashboardRow(tbody,resTextSplit2,index);
        }
        generateDashboardFooter(tbody);
    }else {
        alert("No Records Found");
        
    }
    document.getElementById("prloadingMessage").style.display='none';
}
function getEmployeeByDept(){
    //   var deptName = document.employeeForm.departmentId.value;
    
    
    
    var deptName = document.getElementById("departmentIdd").value;
    var req = newXMLHttpRequest();
    //alert("ha1="+deptName);
    req.onreadystatechange = readyStateHandlerXml(req, populateEmployeeByDept);
    // var url = CONTENXT_PATH+"/getEmpForReportsTo.action?deptName="+deptName;
    var url = CONTENXT_PATH+"/getEmployeesByDept.action?deptName="+deptName;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}


function populateEmployeeByDept(resXML) {
    //   var reportsTo = document.employeeForm.reportsTo;
    //alert("hai");
    var empId = document.getElementById("empnameById");
    var team = resXML.getElementsByTagName("TEAM")[0];
    var users = team.getElementsByTagName("USER");
    empId.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("userId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        empId.appendChild(opt);
    }
}
function getPastReviewData(){    
    var departmentId = document.getElementById("departmentIdd").value;
    var empnameById = document.getElementById("empnameById").value;
  //  var empNo = document.getElementById("empNo").value;
//  alert(empnameById);
        if(departmentId==""){
            alert("Select Department");
            return false;
        }
        else {
             if(empnameById=="-1"){
            alert("select Employee Name");
            return false;
        }
        }
        
    

    
    clearTable(document.getElementById("tblPastReviewRecord"));
    document.getElementById("pastloadingMessage").style.display='block';
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, getPastReviewDataResponse);
   
    // alert("year-->"+departmentId);
    // alert("month-->"+empnameById);
    //loadXMLDoc( CONTENXT_PATH+'/getEmployeesForPerformers.action?departmentId='+departmentId+'&empnameById='+empnameById+'&empNo='+empNo,wagesHistoryResponse);           
    var url = CONTENXT_PATH+'/getPastReviewData.action?departmentId='+departmentId+'&empnameById='+empnameById;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);  

}
function getPastReviewDataResponse(resText) {
    if(resText.length !=0 && resText!="no data"){
        var oTable = document.getElementById("tblPastReviewRecord");
        // var headerFields = new Array("S.No","Employee Name","Reports To","Start Date(YY-MM-DD)","End Date(YY-MM-DD)","Leaves")
        //var headerFields = new Array("S.No","Employee Name","Reports To","Start Date(YY-MM-DD)","End Date(YY-MM-DD)","Status","Leaves")
        //var headerFields = new Array("S.No","ReviewType","ReviewTitle","Status","EmpComments","Tl&nbsp;Rating","Tl&nbsp;Comments","Hr&nbsp;Rating","Hr&nbsp;Comments","ReviewDate")
        var headerFields = new Array("S.No","Review Date","Review Type","Review Description","Approval Status","Points Approved","Approval Date","Approval Manager")
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
        generateTableHeader(tbody,headerFields);
        var resTextSplit1 = resText.split("*@!");
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#^$");
            generatePastReviewRow(tbody,resTextSplit2,index);
        }
        generateDashboardFooter(tbody);
    }else {
        alert("No Records Found");
        
    }
    document.getElementById("pastloadingMessage").style.display='none';
}

function generatePastReviewRow(tableBody,rowFeildsSplit,index) {
    
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
      
        if(i==3){
            cell = document.createElement( "TD" );
            var j = document.createElement("a");
    
            j.setAttribute("href", "javascript:getPastReviewDescription('"+rowFeildsSplit[i]+"')");
    
            j.appendChild(document.createTextNode("Click To View"));
            cell.appendChild(j);
            cell.className="gridRowEven";
            row.appendChild( cell );
        }
        else{
            cell = document.createElement( "TD" );
            cell.className="gridRowEven";
            row.appendChild(cell);
            cell.innerHTML = rowFeildsSplit[i];
            cell.width = 120;
      
        }
    }
}
function getPastReviewDescription(text) {
    var background = "#3E93D4";
    var title = "Review Description";
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
function getEmployeeReviewDetails() {
    
    var startDate = document.getElementById("personalRecordStartDate").value;
    var endDate = document.getElementById("personalRecordEndDate").value;
    var isManagerInclude = document.getElementById("isManagerinclude").checked;
    
    document.getElementById("prloadingMessage").style.display='block';
    document.getElementById("prloadingMessage").innerHTML="<b>Loading Please Wait.....</b>";
    //alert(isManagerInclude);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, getEmployeeReviewDetailsResponse);
    // var url = CONTENXT_PATH+"/getEmpForReportsTo.action?deptName="+deptName;
    //var url = CONTENXT_PATH+"/getEmployeesForPerformers.action?startDate="+startDate+"&endDate="+endDate+"&isManagerInclude="+isManagerInclude;
    var url = CONTENXT_PATH+"/getEmployeeReviewDetails.action?startDate="+startDate+"&endDate="+endDate+"&isManagerInclude="+isManagerInclude;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
   
}
function getEmployeeReviewDetailsResponse(resText) {
    if(resText.length !=0 && resText!="no data"){
        document.getElementById("prloadingMessage").style.display='none';
        document.getElementById("rank").innerHTML=resText.split("#^$")[0];
        document.getElementById("score").innerHTML=resText.split("#^$")[1];
        document.getElementById("reportingManager").innerHTML=resText.split("#^$")[2];
        document.getElementById("reviewsSubmitted").innerHTML=resText.split("#^$")[3];
        document.getElementById("reviewsApproved").innerHTML=resText.split("#^$")[4];
        document.getElementById("reviewApprovalPercentage").innerHTML=resText.split("#^$")[5];
        document.getElementById("reviewsRejected").innerHTML=resText.split("#^$")[6];
        document.getElementById("reviewsPending").innerHTML=resText.split("#^$")[7];
      document.getElementById("leadApproved").innerHTML=resText.split("#^$")[8];
        document.getElementById("hrApproved").innerHTML=resText.split("#^$")[9];
        document.getElementById("leadRejected").innerHTML=resText.split("#^$")[10];
        document.getElementById("hrRejected").innerHTML=resText.split("#^$")[11];
    }else {
        document.getElementById("prloadingMessage").style.display='block';
        document.getElementById("prloadingMessage").innerHTML="<b>No Records Found</b>";
       // alert("No Records Found");
        
    }
    //document.getElementById("prloadingMessage").style.display='none';
}
 

function getEmployeePastReviewData(){    
    //var departmentId = document.getElementById("departmentIdd").value;
   // var empnameById = document.getElementById("empnameById").value;
  //  var empNo = document.getElementById("empNo").value;
//  alert(empnameById);
       var startDate = document.getElementById("pastReviewStartDate").value;
    var endDate = document.getElementById("pastReviewEndDate").value;
    

    
    clearTable(document.getElementById("tblPastReviewRecord"));
    document.getElementById("pastloadingMessage").style.display='block';
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, getPastReviewDataResponse);
   
    var url = CONTENXT_PATH+"/getEmployeePastReviewData.action?startDate="+startDate+"&endDate="+endDate;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);  

}
function getPastReviewDataResponse(resText) {
    if(resText.length !=0 && resText!="no data"){
        var oTable = document.getElementById("tblPastReviewRecord");
       // var headerFields = new Array("S.No","Review Date","Review Type","Review Description","Approval Status","Points Approved","Approval Date","Approval Manager")
       var headerFields = new Array("S.No","Review Date","Review Type","Review Description","Lead Status","Hr Status","Points Approved","Approval Date","Approval Manager")

        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
        generateTableHeader(tbody,headerFields);
        var resTextSplit1 = resText.split("*@!");
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#^$");
            generatePastReviewRow(tbody,resTextSplit2,index);
        }
        generateDashboardFooter(tbody);
    }else {
        alert("No Records Found");
        
    }
    document.getElementById("pastloadingMessage").style.display='none';
}

        
        
