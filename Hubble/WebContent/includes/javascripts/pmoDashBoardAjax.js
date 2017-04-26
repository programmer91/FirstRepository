/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*Don't Alter these Methods*/
var totalRec=0;
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

function ParseAndGenerateHTML1(oTable,responseString,headerFields) {
    
    //   alert("ParseAndGenerateHTML");
    var start = new Date();
    var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";   
    //alert('responseString%%%% ******'+responseString);
    //alert('rowCount%%%% ******'+rowCount);
      
    
    var records = responseString.split(recordDelimiter); 
    //  alert("records---->"+records);
    generateTable(oTable,headerFields,records,fieldDelimiter);
}

function generateTable(oTable, headerFields,records,fieldDelimiter) {	
    //   alert("In Generate Table "+fieldDelimiter);
    //alert(records);
  var tbody
     if(oTable.id == "tblPmoProjectList") {
        generateTableDynamicHeader(oTable,headerFields);
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
    }else{
    
     tbody = oTable.childNodes[0];    
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    generateTableHeader(tbody,headerFields);
    }
    var rowlength;
    rowlength = records.length-1;
    // alert("rowlength--->"+rowlength);
    if(rowlength >0 && records!=""){
        //alert("rowlength-->^"+records);
        for(var i=0;i<rowlength;i++) {
            if(oTable.id == "tblPmoReport"){
                generatePMOAvailableReport(tbody,records[i],fieldDelimiter); 
            }else if(oTable.id == "tblPmoProjectList"){
                  totalRec=rowlength;
                generateProjectDetailsReport(tbody,records[i],fieldDelimiter);
            }else if(oTable.id == "tblProjectEmployeeDetails"){
                generateProjectEmpDetailsReport(tbody,records[i],fieldDelimiter); 
            }else if(oTable.id == "tblPmoCutomerProjectList"){
                  totalRec=rowlength;
                generateCutomerProjectDetailsReport(tbody,records[i],fieldDelimiter); 
            }
else if(oTable.id == "tblResourceTypeDetails"){
                generatetblResourceTypeDetails(tbody,records[i],fieldDelimiter); 
            }else if(oTable.id == "tblProjectListDetails"){
                generateProjectListDetails(tbody,records[i],fieldDelimiter); 
            }else if(oTable.id == "tblMultipleProjectEmployeeList"){
                generateMultipleProjectsEmployeeList(i,tbody,records[i],fieldDelimiter); 
            }else if(oTable.id == "tblMultipleProjectListDetails"){
                generateMultipleProjectsEmployeeListDetails(i,tbody,records[i],fieldDelimiter); 
            }
            else{
                generateRow(oTable,tbody,records[i],fieldDelimiter);  
            }
        }
       
    } else {
        generateNoRecords(tbody,oTable);
    }
   // generateFooter(tbody,oTable);
   if(oTable.id != "tblMultipleProjectListDetails"){
    generateFooter(tbody,oTable);
    }

}
function generateRow(oTable,tableBody,record,delimiter) {
    //  alert("In generate Row function")
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(delimiter);
    fieldLength = fields.length ;
    var length;
    if(oTable.id == "tblPmoReport" ){
        length = fieldLength;
    }
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    
    for (var i=0;i<length;i++) {
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        cell.innerHTML = fields[i];
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
    if(oTable.id == "tblPmoReport"){
        cell.colSpan = "11";
    }else if(oTable.id == "tblPmoProjectList"){
        cell.colSpan = "10";
    }else if(oTable.id == "tblProjectEmployeeDetails"){
        cell.colSpan = "5";
    }else if(oTable.id == "tblPmoCutomerProjectList"){
        cell.colSpan = "4";
    }
else if(oTable.id == "tblResourceTypeDetails"){
        cell.colSpan = "5";
    }else if(oTable.id == "tblProjectListDetails"){
        cell.colSpan = "4";
    }else if(oTable.id == "tblMultipleProjectEmployeeList"){
        cell.colSpan = "6";
    }else if(oTable.id == "tblMultipleProjectListDetails"){
        cell.colSpan = "8";
    }
   
    cell.innerHTML = "No Records Found for this Search";
    noRecords.appendChild(cell);
}

function generateFooter(tbody,oTable) {
    //alert(oTable.id);
    var footer =document.createElement("TR");
    //footer.className="gridPager";
     if(oTable.id != "tblPmoProjectList"){
    footer.className="gridPager";
     }
     else{
      $("#tblPmoProjectList").css("background","#3e93d4");   
     }
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.id="footer"+oTable.id;
    //cell.colSpan = "5";
    if(oTable.id == "tblPmoReport"){
        cell.colSpan = "11";
        cell.innerHTML ="Total&nbsp;Records&nbsp;:"+totalRec;
     cell.setAttribute('align','right');
    }
    else if(oTable.id == "tblPmoProjectList"){
        cell.colSpan = "7";
        cell.innerHTML ="Total&nbsp;Records&nbsp;:"+totalRec;
     cell.setAttribute('align','right');
    }else if(oTable.id == "tblProjectEmployeeDetails"){
        cell.colSpan = "5";
    }else if(oTable.id == "tblPmoCutomerProjectList"){
        cell.colSpan = "4";
        cell.innerHTML ="Total&nbsp;Records&nbsp;:"+totalRec;
     cell.setAttribute('align','right');
    }
else if(oTable.id == "tblResourceTypeDetails"){
        cell.colSpan = "5";
    }else if(oTable.id == "tblProjectListDetails"){
        cell.colSpan = "4";
    }else if(oTable.id == "tblMultipleProjectEmployeeList"){
        cell.colSpan = "6";
    }else if(oTable.id == "tblMultipleProjectListDetails"){
        cell.colSpan = "8";
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


/*********************************************
*Available Employees  List Report start
********************************************/

function readyStateHandler80(req,responseXmlHandler) {
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

function getPracticeDataV2() {
  
    var departmentName=document.getElementById("departmentId").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler80(req, populatePractice);
    var url = CONTENXT_PATH+"/getEmpDepartment.action?departmentName="+departmentName;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populatePractice(resXML) {    
    
    var practiceId = document.getElementById("practiceId");
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

 function getPMOAvailableList()
{
    var oTable = document.getElementById("tblPmoReport");
    clearTable2(oTable);
        $('span.pagination').empty().remove();
    document.getElementById("totalState1").innerHTML = "";
    var state=document.getElementById("state").value;
    var country=document.getElementById("country").value;
    var departmentId=document.getElementById("departmentId").value;
    var practiceId=document.getElementById("practiceId").value;
    var subPracticeId=document.getElementById("subPractice2").value;
    var sortedBy=document.getElementById("sortedBy").value;
  var resourceType=document.getElementById("resourceType").value;
    if(state=="-1")
    {
        alert("please select status");
        return false;
    }
    if(departmentId.trim()==""){
        departmentId="-1";
    }
    if(practiceId=="-1" || practiceId.trim()=="" || practiceId.trim()=="--Please Select--")
    {
        practiceId="-1";
    }
    if(subPracticeId=="-1" || subPracticeId=="All")
    {
        subPracticeId="-1";
    }
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("pmoAvailableReport").style.display = 'none';
                displayPMOAvailableReport(req.responseText);       
             
            } 
        }else {
            document.getElementById("pmoAvailableReport").style.display = 'block';
        // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }; 
    //   var url = CONTENXT_PATH+"/getAvailableEmpReport.action?state="+escape(state)+"&country="+country+"&departmentId="+departmentId+"&practiceId="+practiceId;
  //  var url = CONTENXT_PATH+"/getAvailableEmpReport.action?state="+escape(state)+"&country="+country+"&departmentId="+departmentId+"&practiceId="+practiceId+"&subPracticeId="+subPracticeId;
  var url = CONTENXT_PATH+"/getAvailableEmpReport.action?state="+escape(state)+"&country="+country+"&departmentId="+departmentId+"&practiceId="+practiceId+"&subPracticeId="+subPracticeId+"&sortedBy="+sortedBy +"&resourceType="+resourceType;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}



function displayPMOAvailableReport(response) {
 // alert(response);
    var oTable = document.getElementById("tblPmoReport");
    clearTable(oTable);
     var state=document.getElementById("state").value;

    var dataArray = response;
    
    if(dataArray == "no data")
    {
        alert("No Records Found for this Search");   
    }
    else {
        
        // tbody = document.createElement("TBODY");
     //   var headerFields = new Array("S.No","Reports To","Employee name","practice","Email","Phone Number","SkillSet");
       if(state=="Available")
        {
            // tbody = document.createElement("TBODY");
       //     var headerFields = new Array("S.No","Reports&nbsp;To","Employee&nbsp;name","Exp","Practice","Available Utilization","Available&nbsp;From","Email","Phone&nbsp;Number","SkillSet");
            var headerFields = new Array("S.No","Employee&nbsp;name","Total&nbsp;Exp","Practice","Available Utilization","Available&nbsp;From","Contact&nbsp;Details","SkillSet");
        //generateHeader(headerArray,tableId);
        }
        else if(state=="OnProject"){
            // var headerFields = new Array("S.No","Reports&nbsp;To","Employee&nbsp;name","Exp","Practice","Project&nbsp;Name","Res&nbsp;Type","Available Utilization","Email","Phone&nbsp;Number","SkillSet");
             
         //    var headerFields = new Array("S.No","Employee&nbsp;name","Total&nbsp;Exp","Practice","Project&nbsp;Name","ProjectExp","Res&nbsp;Type","Avail.Util","Contact&nbsp;Details","SkillSet");
        var headerFields = new Array("S.No","Employee&nbsp;name","Total&nbsp;Exp","ProjectExp","Practice","Project&nbsp;Name","Res&nbsp;Type","Avail.Util","Contact&nbsp;Details","SkillSet");
       
   }
        else
        {
           // var headerFields = new Array("S.No","Reports&nbsp;To","Employee&nbsp;name","Exp","Practice","Project&nbsp;Name","Email","Phone&nbsp;Number","SkillSet");
   
   var headerFields = new Array("S.No","Employee&nbsp;name","Total&nbsp;Exp","Practice","Project&nbsp;Name","Contact&nbsp;Details","SkillSet");
        }
        //generateHeader(headerArray,tableId);
       
        var temp = new Array;
        temp = dataArray.split('addTo');            
       
        if(response!=''){
           
            document.getElementById("totalState1").innerHTML = temp[1];
           totalRec= temp[1];
            ParseAndGenerateHTML1(oTable,temp[0], headerFields);
               pagerOption();
        }else{
            alert('No Result For This Search...');
            spnFast.innerHTML="No Result For This Search...";                
        }
    } 
}
	
function generatePMOAvailableReport(tableBody,record,fieldDelimiter){
    var row;
    var cell;
    var fieldLength;
     var state=document.getElementById("state").value;
    var fields = record.split(fieldDelimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    for (var i=1;i<length-2;i++) {            
        cell = document.createElement( "TD" );
        cell.className="gridColumn";       
       // cell.innerHTML = fields[i];
      /*  if(parseInt(i,10)==2){
              cell.innerHTML = fields[i];  
            cell.innerHTML = "<a href='javascript:getEmployeeDetails("+fields[0]+")'>"+fields[i]+"</a>";
        }*/
        if(parseInt(i,10)==2){
            cell.innerHTML = fields[2];
         }
       if(state=="Available" )
        {
//            if(parseInt(i,10)==10){
//                cell.innerHTML = "<a href='javascript:getSkillSet("+fields[0]+")'>"+fields[i]+"</a>";
//            }


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
else if( state=="OnProject")
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
//            if(parseInt(i,10)==9){
//                cell.innerHTML = "<a href='javascript:getSkillSet("+fields[0]+")'>"+fields[i]+"</a>";
//            } 


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
            if((i==5 || i==6) && state=="Available")
            {
                cell.setAttribute("align","right");
            }
             else if((i==7) && state=="OnProject")
            {
                cell.setAttribute("align","right");
            }else if(i==3){
                 cell.setAttribute("align","right");
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
		
		





		
/***********************************************************
 *PMO project dashboard suggestion list start
 * ********************************************************/
function EmployeeForProjectDetails() {
    var test=document.getElementById("assignedToUID").value;
   
    if (test == "") {

        clearTable1();
        hideScrollBar();
        var validationMessage=document.getElementById("authorEmpValidationMessage");
        validationMessage.innerHTML = "";
        document.frmSearch.preAssignEmpId.value="";
    //frmSearch issuesForm
    } else {
        if (test.length >2) {
            //alert("CONTENXT_PATH-- >"+CONTENXT_PATH)
            var url = CONTENXT_PATH+"/getEmployeeDetailforPMOActivity.action?customerName="+escape(test);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                //    alert("req-->"+req);
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        // alert(req.responseXML);
                    
                        parseEmpMessagesForProjectDeatils(req.responseXML);
                    } else if (req.status == 204){
                        clearTable1();
                    }
                }
            };
            req.open("GET", url, true);

            req.send(null);
        }
    }
}

function parseEmpMessagesForProjectDeatils(responseXML) {
    //alert("-->"+responseXML);
    autorow1 = document.getElementById("menu-popup");
    autorow1.style.display ="none";
    autorow = document.getElementById("menu-popup");
    autorow.style.display ="none";
    clearTable1();
    var employees = responseXML.getElementsByTagName("EMPLOYEES")[0];
    if (employees.childNodes.length > 0) {        
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");        
    } else {
        clearTable1();
    }
    if(employees.childNodes.length<10) {
        autorow1.style.overflowY = "hidden";
        autorow.style.overflowY = "hidden";        
    }
    else {    
        autorow1.style.overflowY = "scroll";
        autorow.style.overflowY = "scroll";
    }
    
    var employee = employees.childNodes[0];
    var chk=employee.getElementsByTagName("VALID")[0];
    if(chk.childNodes[0].nodeValue =="true") {
        //var validationMessage=document.getElementById("validationMessage");
        var validationMessage;
        
        validationMessage=document.getElementById("authorEmpValidationMessage");
        isPriEmpExist = true;
         
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < employees.childNodes.length; loop++) {
            
            var employee = employees.childNodes[loop];
            var customerName = employee.getElementsByTagName("NAME")[0];
            var empid = employee.getElementsByTagName("EMPID")[0];
            appendEmployeeForProjectDetails(empid.childNodes[0].nodeValue,customerName.childNodes[0].nodeValue);
        }
        var position;
        
        
        position = findPosition(document.getElementById("assignedToUID"));
        
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage = '';
      
        isPriEmpExist = false;
        validationMessage=document.getElementById("authorEmpValidationMessage");
    
 
        validationMessage.innerHTML = " Invalid ! Select from Suggesstion List. ";
        validationMessage.style.color = "green";
        validationMessage.style.fontSize = "12px";
       
        document.getElementById("assignedToUID").value = "";
        document.getElementById("preAssignEmpId").value = "";
            
        
    }
}
function appendEmployeeForProjectDetails(empId,empName) {
    
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
    // if(assignedToType=='pre'){
    linkElement.setAttribute("href", "javascript:set_emp('"+empName +"','"+ empId +"')");

    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
//fillWorkPhone(empId);
}
function set_emp(eName,eID){
    clearTable1();
    document.frmSearch.assignedToUID.value =eName;
    document.frmSearch.preAssignEmpId.value =eID;
}
var isIE;
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
function clearTable1() {
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
/***********************************************************
 *PMO  project dashboard suggestion list end
 * ********************************************************/
		

function geProjectListDetails()
{
    var oTable = document.getElementById("tblPmoProjectList");
       
      ClrDashBordTable(oTable);
    
     $('span.pagination').empty().remove();
    var custName=document.getElementById("customerName").value;
    var  ProjectName=document.getElementById("projectName").value;
    var  status=document.getElementById("status").value;                                                            
    var ProjectStartDate=document.getElementById("projectStartDate").value;
    var pmoLoginId = document.getElementById("pmoLoginId").value;
    var preAssignEmpId = document.getElementById("preAssignEmpId").value;
    var practiceId = document.getElementById("practiceId1").value;
    var empStatus=document.getElementById("empStatus").value;
     var assignedToUID=document.getElementById("assignedToUID").value;
    if(assignedToUID.trim()==""){
       preAssignEmpId=""; 
    }
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            //  alert("1");
            if (req.status == 200) {    
                //      alert("2");
                document.getElementById("pmoProjectDetailsList").style.display = 'none';
                displayProjectDetailsResult(req.responseText);
                
            } 
        }else {
            document.getElementById("pmoProjectDetailsList").style.display = 'block';
        // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }; 
    var url = CONTENXT_PATH+"/projectDetailsDashboard.action?customerName="+custName+"&projectName="+ProjectName+"&status="+status+"&projectStartDate="+ProjectStartDate+"&pmoLoginId="+pmoLoginId+"&preAssignEmpId="+preAssignEmpId+"&practiceId="+practiceId+"&empStatus="+empStatus;

    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);


}


function displayProjectDetailsResult(resText) 
{
    // alert(resText);
    if(resText.length !=0)
    {
        var oTable = document.getElementById("tblPmoProjectList");
       
      ClrDashBordTable(oTable);
      
            
        // var headerFields = new Array("SNo","NAME","ProjectName","ProjectStartDate","Resources","Status","Activity");	
        // var headerFields = new Array("SNo","ProjectName","Account&nbsp;Name","ProjectStartDate","Resources","Status","Pmo","Activity");	
        var headerFields = new Array("SNo","Customer&nbsp;Name","Project&nbsp;Name","Total&nbsp;Resources","OffShore&nbsp;Main(Billable)","OffShore&nbsp;Main","OffShore&nbsp;Shadow","OffShore&nbsp;Training","OffShore&nbsp;Overhead","Onsite&nbsp;Main(Billable)","Onsite&nbsp;Main","Onsite&nbsp;Shadow","Onsite&nbsp;Training","Onsite&nbsp;Overhead");
               
    
        if(resText!=''){
           
            //   document.getElementById("totalState1").innerHTML = temp[1];
        
            ParseAndGenerateHTML1(oTable,resText, headerFields);
            
               $("#tblPmoProjectList").tableHeadFixer({
        'left' : 4, 
        'foot' : false, 
        'head' : true
    });
     pagerOption1();
        }else{
            alert('No Result For This Search...');
            spnFast.innerHTML="No Result For This Search...";                
        }
        
       
       
    }
    else {
        alert("No Records Found");
    }
}


function generateProjectDetailsReport(tableBody,record,fieldDelimiter){
    var row;
    var cell;
    var fieldLength;
    var state=document.getElementById("state").value;
    var fields = record.split(fieldDelimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    for (var i=2;i<length;i++) {            
        cell = document.createElement( "TD" );
        cell.className="gridColumn";       
        cell.innerHTML = fields[i];
        if(fields[i]!=0)
        {
            if(parseInt(i,10)==5){
           
                cell.innerHTML = "<a href='javascript:getProjectEmployeeDetails("+fields[0]+",1)'>"+fields[i]+"</a>";
                
            }
            if(parseInt(i,10)==6){
                cell.innerHTML = "<a href='javascript:getProjectEmployeeDetails("+fields[0]+",2)'>"+fields[i]+"</a>";
            }
            if(parseInt(i,10)==7){
                cell.innerHTML = "<a href='javascript:getProjectEmployeeDetails("+fields[0]+",3)'>"+fields[i]+"</a>";
            }
            if(parseInt(i,10)==8){
                cell.innerHTML = "<a href='javascript:getProjectEmployeeDetails("+fields[0]+",4)'>"+fields[i]+"</a>";
            }
            if(parseInt(i,10)==9){
                cell.innerHTML = "<a href='javascript:getProjectEmployeeDetails("+fields[0]+",5)'>"+fields[i]+"</a>";
            }
             if(parseInt(i,10)==10){
                cell.innerHTML = "<a href='javascript:getProjectEmployeeDetails("+fields[0]+",6)'>"+fields[i]+"</a>";
            }
             if(parseInt(i,10)==11){
                cell.innerHTML = "<a href='javascript:getProjectEmployeeDetails("+fields[0]+",7)'>"+fields[i]+"</a>";
            }
             if(parseInt(i,10)==12){
                cell.innerHTML = "<a href='javascript:getProjectEmployeeDetails("+fields[0]+",8)'>"+fields[i]+"</a>";
            }
             if(parseInt(i,10)==13){
                cell.innerHTML = "<a href='javascript:getProjectEmployeeDetails("+fields[0]+",9)'>"+fields[i]+"</a>";
            }
             if(parseInt(i,10)==14){
                cell.innerHTML = "<a href='javascript:getProjectEmployeeDetails("+fields[0]+",10)'>"+fields[i]+"</a>";
            }
             if(parseInt(i,10)==15){
                cell.innerHTML = "<a href='javascript:getProjectEmployeeDetails("+fields[0]+",11)'>"+fields[i]+"</a>";
            }
        }
        else
        {
            cell.innerHTML = fields[i];
        }
        if(fields[i]!=''){
            if(i>=5)
            {
                cell.setAttribute("align","right");
            }
            else
            {
                cell.setAttribute("align","left");     
            }
            row.appendChild( cell );
        }
    }
   
}


function ClrTable(myHTMLTable) { 
    var tbl =  myHTMLTable;
    var lastRow = tbl.rows.length; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}

	
function getProjectEmployeeDetails(proejctId,flag){
    var req = newXMLHttpRequest();
    var empStatus=document.getElementById("empStatus").value;
    req.onreadystatechange =function() {
        if (req.readyState == 4) {
            //  alert("1");
            if (req.status == 200) {    
                //      alert("2");
                document.getElementById("load").style.display = 'none';
                displayProjectEmpDetailsResult(req.responseText);                        
            } 
        }else {
            document.getElementById("load").style.display = 'block';
        // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }; 
    var url = CONTENXT_PATH+"/getProjectEmployeeDetails.action?projectId="+proejctId+"&empStatus="+empStatus+"&flag="+flag;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function displayProjectEmpDetailsResult(response) {
  
    var tableId = document.getElementById("tblProjectEmployeeDetails");
    ClrTable(tableId);
    var headerFields = new Array("Employee Name","Email","Phone Number","Utilization");
   
    // alert("responseArray[1]-->"+responseArray[1]);
    //document.getElementById("totalRequirementsFound").innerHTML = responseArray[0];
    var dataArray = response;
    
    //  generateTableHeader(tableId,headerFields)
    ParseAndGenerateHTML1(tableId,dataArray, headerFields);
 
 
    document.getElementById("headerLabel1").style.color="white";
    document.getElementById("headerLabel1").innerHTML="Employee Details";
    //document.getElementById("total").innerHTML=total;
    var overlay = document.getElementById('overlayPmoDashBoard');
    var specialBox = document.getElementById('specialBoxPmoDashBoard');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }

    
}

function generateProjectEmpDetailsReport(tableBody,record,fieldDelimiter){
    var row;
    var cell;
    var fieldLength;
    var state=document.getElementById("state").value;
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


function toggleCloseUploadOverlay1() {
    var overlay = document.getElementById('overlayPmoDashBoard');
    var specialBox = document.getElementById('specialBoxPmoDashBoard');

    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
//window.location="empSearchAll.action";
}


function getSubPracticeData3(){

    

    var practiceName = document.getElementById("practiceId").value;



    var req = newXMLHttpRequest();

    req.onreadystatechange = readyStateHandler80(req, populateSubPractices);

    var url = CONTENXT_PATH+"/getEmpPractice.action?practiceName="+practiceName;

    req.open("GET",url,"true");    

    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

    req.send(null);

    

}







function populateSubPractices(resXML) {
    var subPractice = document.getElementById("subPractice2");
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


 function getEmployeeExpDetails(mss,prev) {
   // alert(mss);
   // alert(prev);
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


 function clearTable2(tableId) {
    var tbl =  tableId;
    var lastRow = tbl.rows.length; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}



/*Customer Project details dashboard scipts start
 * Date : 02/09/2017
 * Author : Teja Kadamanti
 */


function geCustomerProjectListDetails()
{
    var oTable = document.getElementById("tblPmoCutomerProjectList");
       
    ClrTable(oTable);
    
     $('span.pagination').empty().remove();
    var custName=document.getElementById("customerName1").value;
    var  ProjectName=document.getElementById("projectName1").value;
    var  status=document.getElementById("status1").value;                                                            
    var ProjectStartDate=document.getElementById("projectStartDate1").value;
    var pmoLoginId = document.getElementById("pmoLoginId1").value;
    var preAssignEmpId = document.getElementById("preAssignEmpId1").value;
    var practiceId = document.getElementById("practiceId2").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            //  alert("1");
            if (req.status == 200) {    
                //      alert("2");
                document.getElementById("pmoCutomerProjectDetailsList").style.display = 'none';
                displayCustomerProjectDetailsResult(req.responseText);     
                
                
            } 
        }else {
            document.getElementById("pmoCutomerProjectDetailsList").style.display = 'block';
        // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }; 
    var url = CONTENXT_PATH+"/customerProjectDetailsDashboard.action?customerName="+custName+"&projectName="+ProjectName+"&status="+status+"&projectStartDate="+ProjectStartDate+"&pmoLoginId="+pmoLoginId+"&preAssignEmpId="+preAssignEmpId+"&practiceId="+practiceId;

    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);


}



function displayCustomerProjectDetailsResult(resText) 
{
    
    if(resText.length !=0)
    {
        var oTable = document.getElementById("tblPmoCutomerProjectList");
       
        var headerFields = new Array("SNo","Customer&nbsp;Name","Active&nbsp;Resources","Projects&nbsp;List");
               
      //  alert("test1");
        if(resText!=''){
         //   alert("test2");
            //   document.getElementById("totalState1").innerHTML = temp[1];
        
            ParseAndGenerateHTML1(oTable,resText, headerFields);
               pagerOption2();
        }else{
            alert('No Result For This Search...');
        // spnFast.innerHTML="No Result For This Search...";                
        }
       
    }
    else {
        alert("No Records Found");
    }
}
function generateCutomerProjectDetailsReport(tableBody,record,fieldDelimiter){
    
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
       if(fields[i]!=0)
        {
            if(parseInt(i,10)==2){
                cell = document.createElement( "TD" );
                cell.className="gridColumn";    
                cell.innerHTML = "<a href='javascript:getResourceTypeDetailsByCustomer("+fields[3]+")'>"+fields[2]+"</a>";
            }
            
          if(parseInt(i,10)==3){
                cell = document.createElement( "TD" );
                cell.className="gridColumn";    
                cell.innerHTML = "<a href='javascript:getProjectListByCustomer("+fields[3]+")'><img SRC='../includes/images/go_21x21.gif' WIDTH=18 HEIGHT=15 BORDER=0 ALTER='Click to Add'></a>";
            
        }  
        } else
         {
            cell.innerHTML = fields[i];
        }
    
        if(fields[i]!=''){
            if(i==3)
            {
                cell.setAttribute("align","center");
            }else if(i==2){
                 cell.setAttribute("align","right");
            }
            else
            {
                cell.setAttribute("align","left");     
            }
            row.appendChild( cell );
        }
    }
    
    
   
}



function getResourceTypeDetailsByCustomer(accId){
//   
      var req = newXMLHttpRequest();
    var  projectId=document.getElementById("projectName1").value;
    req.onreadystatechange = readyStateHandlerText(req, displayResourceTypeDetailsByCustomer);
    var url = CONTENXT_PATH+"/getResourceTypeDetailsByCustomer.action?accId="+accId+'&projectId='+projectId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function displayResourceTypeDetailsByCustomer(response) {
    var oTable = document.getElementById("tblResourceTypeDetails");
    ClrTable(oTable);
    var headerFields = new Array("Main","Shadow","Training","Overhead");
    var dataArray = response;
     // ParseAndGenerateHTML1(oTable,dataArray, headerFields);
      var records=dataArray.split("*@!");
      if(records.length>0){
      var data=records[0].split("#^$");
      
      var tbody = oTable.childNodes[0];    
  var tbody = oTable.childNodes[0];    
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridHeader";
    tbody.appendChild( row );
     for (var i=0; i<headerFields.length; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridHeader";
         cell.setAttribute("colspan","2");
           cell.setAttribute("align","center");
        row.appendChild( cell );
        cell.innerHTML = headerFields[i];
        cell.width = 120;
    }
     row = document.createElement( "TR" );
    row.className="gridRowEven";
	tbody.appendChild( row );
	  cell = document.createElement( "TD" );
        cell.className="gridColumn";    
         cell.setAttribute("colspan","2");
		cell.innerHTML = data[0];
		  cell.setAttribute("align","center");
                  
	 row.appendChild( cell );
           cell = document.createElement( "TD" );
        cell.className="gridColumn";    
         cell.setAttribute("colspan","2");
		cell.innerHTML = data[1];
		  cell.setAttribute("align","center");
	 row.appendChild( cell );
          cell = document.createElement( "TD" );
        cell.className="gridColumn";    
         cell.setAttribute("colspan","2");
		cell.innerHTML = data[2];
		  cell.setAttribute("align","center");
                   row.appendChild( cell );
                   cell = document.createElement( "TD" );
        cell.className="gridColumn";    
         cell.setAttribute("colspan","2");
		cell.innerHTML = data[3];
		  cell.setAttribute("align","center");
                  
	 row.appendChild( cell );
         headerFields = new Array("OnSite","OffShore","OnSite","OffShore","OnSite","OffShore","OnSite","OffShore");
         row = document.createElement( "TR" );
    row.className="gridHeader";
      cell.setAttribute("align","center");
    tbody.appendChild( row );
     for (var i=0; i<headerFields.length; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridHeader";
        
        row.appendChild( cell );
        cell.innerHTML = headerFields[i];
        cell.width = 120;
    }
      row = document.createElement( "TR" );
    row.className="gridRowEven";
	tbody.appendChild( row );
	  cell = document.createElement( "TD" );
        cell.className="gridColumn";    
		cell.innerHTML = data[4];
		  cell.setAttribute("align","right");
	 row.appendChild( cell );
           cell = document.createElement( "TD" );
        cell.className="gridColumn";    
		cell.innerHTML = data[5];
		  cell.setAttribute("align","right");
	 row.appendChild( cell );
          cell = document.createElement( "TD" );
        cell.className="gridColumn";    
		cell.innerHTML = data[6];
		  cell.setAttribute("align","right");
	 row.appendChild( cell );
          cell = document.createElement( "TD" );
        cell.className="gridColumn";    
		cell.innerHTML = data[7];
		  cell.setAttribute("align","right");
	 row.appendChild( cell );
         
           cell = document.createElement( "TD" );
        cell.className="gridColumn";    
		cell.innerHTML = data[8];
		  cell.setAttribute("align","right");
	 row.appendChild( cell );
         
           cell = document.createElement( "TD" );
        cell.className="gridColumn";    
		cell.innerHTML = data[9];
		  cell.setAttribute("align","right");
	 row.appendChild( cell );
         
           cell = document.createElement( "TD" );
        cell.className="gridColumn";    
		cell.innerHTML = data[10];
		  cell.setAttribute("align","right");
	 row.appendChild( cell );
         
           cell = document.createElement( "TD" );
        cell.className="gridColumn";    
		cell.innerHTML = data[11];
		  cell.setAttribute("align","right");
	 row.appendChild( cell );
         
           var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
     cell.setAttribute("align","right");
      cell.innerHTML ="Total&nbsp;Resource&nbsp;:"+ data[12];
      cell.colSpan = "8";
      footer.appendChild(cell);
      }
    document.getElementById("headerLabel3").style.color="white";
    document.getElementById("headerLabel3").innerHTML="Resource Details";
            
    var overlay = document.getElementById('overlayResourceType');
    var specialBox = document.getElementById('specialBoxResourceType');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }

    
}


function toggleCloseUploadOverlay2() {
    var overlay = document.getElementById('overlayResourceType');
    var specialBox = document.getElementById('specialBoxResourceType');

    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
//window.location="empSearchAll.action";
}





function generatetblResourceTypeDetails(tableBody,record,fieldDelimiter){
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

function getProjectListByCustomer(accId){
    
      var  status=document.getElementById("status1").value;                                                            
    var ProjectStartDate=document.getElementById("projectStartDate1").value;
    var pmoLoginId = document.getElementById("pmoLoginId1").value;
    var practiceId = document.getElementById("practiceId2").value;
    var  ProjectName=document.getElementById("projectName1").value;
    var preAssignEmpId = document.getElementById("preAssignEmpId1").value;
//      
      var req = newXMLHttpRequest();
   
    req.onreadystatechange = readyStateHandlerText(req, displayProjectListByCustomer);
    var url = CONTENXT_PATH+"/getProjectListByCustomer.action?accId="+accId+"&projectName="+ProjectName+"&status="+status+"&projectStartDate="+ProjectStartDate+"&pmoLoginId="+pmoLoginId+"&practiceId="+practiceId+"&preAssignEmpId="+preAssignEmpId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function displayProjectListByCustomer(response) {
    var tableId = document.getElementById("tblProjectListDetails");
    ClrTable(tableId);
    var headerFields = new Array("Name","StartDate","Status","Practice");
    var dataArray = response;
    ParseAndGenerateHTML1(tableId,dataArray, headerFields);
 
 
    document.getElementById("headerLabel2").style.color="white";
    document.getElementById("headerLabel2").innerHTML="Project Details";
            
    var overlay = document.getElementById('overlayProjectList');
    var specialBox = document.getElementById('specialBoxProjectList');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }

    
}



function toggleCloseUploadOverlay3() {
    var overlay = document.getElementById('overlayProjectList');
    var specialBox = document.getElementById('specialBoxProjectList');

    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
//window.location="empSearchAll.action";
}





function generateProjectListDetails(tableBody,record,fieldDelimiter){
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



function EmployeeForProjectDetails1() {
    var test=document.getElementById("assignedToUID1").value;
   
    if (test == "") {

        clearTable1();
        hideScrollBar();
        var validationMessage=document.getElementById("authorEmpValidationMessage");
        validationMessage.innerHTML = "";
        document.frmSearch.preAssignEmpId.value="";
    //frmSearch issuesForm
    } else {
        if (test.length >2) {
            //alert("CONTENXT_PATH-- >"+CONTENXT_PATH)
            var url = CONTENXT_PATH+"/getEmployeeDetailforPMOActivity.action?customerName="+escape(test);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                //    alert("req-->"+req);
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        // alert(req.responseXML);
                    
                        parseEmpMessagesForProjectDeatils1(req.responseXML);
                    } else if (req.status == 204){
                        clearTable1();
                    }
                }
            };
            req.open("GET", url, true);

            req.send(null);
        }
    }
}

function parseEmpMessagesForProjectDeatils1(responseXML) {
    //alert("-->"+responseXML);
    autorow1 = document.getElementById("menu-popup");
    autorow1.style.display ="none";
    autorow = document.getElementById("menu-popup");
    autorow.style.display ="none";
    clearTable1();
    var employees = responseXML.getElementsByTagName("EMPLOYEES")[0];
    if (employees.childNodes.length > 0) {        
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");        
    } else {
        clearTable1();
    }
    if(employees.childNodes.length<10) {
        autorow1.style.overflowY = "hidden";
        autorow.style.overflowY = "hidden";        
    }
    else {    
        autorow1.style.overflowY = "scroll";
        autorow.style.overflowY = "scroll";
    }
    
    var employee = employees.childNodes[0];
    var chk=employee.getElementsByTagName("VALID")[0];
    if(chk.childNodes[0].nodeValue =="true") {
        //var validationMessage=document.getElementById("validationMessage");
        var validationMessage;
        
        validationMessage=document.getElementById("authorEmpValidationMessage1");
        isPriEmpExist = true;
         
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < employees.childNodes.length; loop++) {
            
            var employee = employees.childNodes[loop];
            var customerName = employee.getElementsByTagName("NAME")[0];
            var empid = employee.getElementsByTagName("EMPID")[0];
            appendEmployeeForProjectDetails1(empid.childNodes[0].nodeValue,customerName.childNodes[0].nodeValue);
        }
        var position;
        
        
        position = findPosition(document.getElementById("assignedToUID1"));
        
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage = '';
      
        isPriEmpExist = false;
        validationMessage=document.getElementById("authorEmpValidationMessage");
    
 
        validationMessage.innerHTML = " Invalid ! Select from Suggesstion List. ";
        validationMessage.style.color = "green";
        validationMessage.style.fontSize = "12px";
       
        document.getElementById("assignedToUID1").value = "";
        document.getElementById("preAssignEmpId1").value = "";
            
        
    }
}
function appendEmployeeForProjectDetails1(empId,empName) {
    
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
    // if(assignedToType=='pre'){
    linkElement.setAttribute("href", "javascript:set_emp1('"+empName +"','"+ empId +"')");

    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
//fillWorkPhone(empId);
}
function set_emp1(eName,eID){
    clearTable1();
    document.frmSearch.assignedToUID1.value =eName;
    document.frmSearch.preAssignEmpId1.value =eID;
}
function getProjectsByAccountId(){
 
    var accountId = document.getElementById("customerName").value;
 if(accountId!=""){
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler80(req, populateProjectsList);
    var url = CONTENXT_PATH+"/getProjectsByAccountId.action?accountId="+accountId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}else{
     var projects = document.getElementById("projectName");
      projects.innerHTML=" ";
      
        var opt = document.createElement("option");
        opt.setAttribute("value","");
        opt.appendChild(document.createTextNode("All"));
         projects.appendChild(opt);
}
}
function populateProjectsList(resXML) {
  
    //var projects = document.getElementById("projectName");
    var projects = document.getElementById("projectName");
 //  alert("test")
    
    var projectsList = resXML.getElementsByTagName("PROJECTS")[0];
   
    var users = projectsList.getElementsByTagName("USER");
    projects.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("projectId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        projects.appendChild(opt);
    }
}

function getProjectsByAccountIdForResourceCount(){
 
    var accountId = document.getElementById("customerName1").value;
 if(accountId!=""){
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler80(req, populateProjectsListForResourceCount);
    var url = CONTENXT_PATH+"/getProjectsByAccountId.action?accountId="+accountId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}else{
     var projects = document.getElementById("projectName1");
      projects.innerHTML=" ";
      
        var opt = document.createElement("option");
        opt.setAttribute("value","");
        opt.appendChild(document.createTextNode("All"));
         projects.appendChild(opt);
}
}
function populateProjectsListForResourceCount(resXML) {
  
    //var projects = document.getElementById("projectName");
    var projects = document.getElementById("projectName1");
 //  alert("test")
    
    var projectsList = resXML.getElementsByTagName("PROJECTS")[0];
   
    var users = projectsList.getElementsByTagName("USER");
    projects.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("projectId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        projects.appendChild(opt);
    }
}




function generateTableDynamicHeader(tableBody,headerFields) {
    var row;
    var cell;
    
    var thead=document.createElement("thead");
    tableBody.appendChild(thead);
    row = document.createElement( "TR" );
    row.className="dashBoardgridHeader";
    thead.appendChild(row);
    
    for (var i=0; i<headerFields.length; i++) {
        cell = document.createElement( "TH" );
        cell.className="dashBoardgridHeader";
        row.appendChild( cell );
        cell.innerHTML = headerFields[i];
        cell.width = 120;
    }
}


function ClrDashBordTable(myHTMLTable) { 
    var tbl =  myHTMLTable;
    
tbl.deleteTHead();
    var lastRow = tbl.rows.length; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}



function getMultipleProjectsEmployeeList(){
   var preAssignEmpId = document.getElementById("preAssignEmpId2").value;
   var departmentId = document.getElementById("departmentIdEmpPrj").value;
   var practiceId = document.getElementById("practiceIdEmpPrj").value;
   var subPracticeId = document.getElementById("subPracticeEmpPrj").value;
   if(departmentId.trim()==""){
        departmentId="-1";
    }
    if(practiceId=="-1" || practiceId.trim()=="" || practiceId.trim()=="--Please Select--")
    {
        practiceId="-1";
    }
    if(subPracticeId=="-1" || subPracticeId=="All")
    {
        subPracticeId="-1";
    }
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            //  alert("1");
            if (req.status == 200) {    
                //      alert("2");
                document.getElementById("multipleProjectEmployeeDetailsLoad").style.display = 'none';
                displayMultipleProjectsEmpList(req.responseText);
                
            } 
        }else {
            document.getElementById("multipleProjectEmployeeDetailsLoad").style.display = 'block';
        // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }; 
  //  var url = CONTENXT_PATH+"/multipleProjectsEmployeeList.action?preAssignEmpId="+preAssignEmpId;
 var url = CONTENXT_PATH+"/multipleProjectsEmployeeList.action?preAssignEmpId="+preAssignEmpId+"&departmentId="+departmentId+"&practiceId="+practiceId+"&subPracticeId="+subPracticeId;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);

}





function displayMultipleProjectsEmpList(resText) 
{
    
    if(resText.length !=0)
    {
        var oTable = document.getElementById("tblMultipleProjectEmployeeList");
        ClrTable(oTable);
        // var headerFields = new Array("SNo","Employee&nbsp;Name","Department","Practice","Projects");
           var headerFields = new Array("SNo","Employee&nbsp;Name","Department","Practice","SubPractice","Projects");
      
      //  alert("test1");
        if(resText!=''){
         //   alert("test2");
            //   document.getElementById("totalState1").innerHTML = temp[1];
        
            ParseAndGenerateHTML1(oTable,resText, headerFields);
               pagerOption3();
        }else{
            alert('No Result For This Search...');
        // spnFast.innerHTML="No Result For This Search...";                
        }
       
    }
    else {
        alert("No Records Found");
    }
}
function generateMultipleProjectsEmployeeList(index,tableBody,record,fieldDelimiter){
    
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(fieldDelimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    
          cell = document.createElement( "TD" );
          cell.className="gridColumn";   
          cell.innerHTML = index+1;
          row.appendChild(cell);
           
    for (var i=1;i<length-1;i++) { 
           
        cell = document.createElement( "TD" );
        cell.className="gridColumn";   
        cell.innerHTML = fields[i];
        row.appendChild(cell);
     
    }
    
     cell = document.createElement( "TD" );
        cell.className="gridColumn";   
    //   cell.innerHTML = "<a href='javascript:getMultipleProjectsList("+fields[0]+")'>"+fields[4]+"</a>";
           cell.innerHTML = "<a href='javascript:getMultipleProjectsList("+fields[0]+")'>"+fields[5]+"</a>";
     

        row.appendChild(cell);
    
   
}


function getMultipleProjectsList(preAssignEmpId){
   
     var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            //  alert("1");
            if (req.status == 200) {    
                //      alert("2");
                setMultipleProjectsList(req.responseText);
                
            } 
        }else {
          // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }; 
    var url = CONTENXT_PATH+"/multipleProjectsEmployeeListDetails.action?preAssignEmpId="+preAssignEmpId;

    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function setMultipleProjectsList(response) {
   var tableId = document.getElementById("tblMultipleProjectListDetails");
    ClrTable(tableId);
    var headerFields = new Array("S.No","Customer&nbsp;Name","Project&nbsp;Name","StartDate","EndDate","EmpProjStatus","Billable","Utilization");
    var dataArray ;
    
     dataArray = response.split("addTo");
             
     ParseAndGenerateHTML1(tableId,dataArray[0], headerFields);
 
 
    document.getElementById("headerLabel4").style.color="white";
    document.getElementById("headerLabel4").innerHTML="Employee Project Details";
            
    var overlay = document.getElementById('overlayMultipleProjectList');
    var specialBox = document.getElementById('specialBoxMultipleProjectList');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
    
    
  var  tbody = tableId.childNodes[0];    
    tbody = document.createElement("TBODY");
    tableId.appendChild(tbody);
    var cell;
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
     cell.setAttribute("align","right");
      cell.innerHTML ="Total&nbsp;Utilization&nbsp;:"+ dataArray[1];
      cell.colSpan = "8";
      footer.appendChild(cell);

    
}

function toggleCloseUploadOverlayMultipleProjects() {
    var overlay = document.getElementById('overlayMultipleProjectList');
    var specialBox = document.getElementById('specialBoxMultipleProjectList');

    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
//window.location="empSearchAll.action";
}



function generateMultipleProjectsEmployeeListDetails(index,tableBody,record,fieldDelimiter){
    
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(fieldDelimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    
          cell = document.createElement( "TD" );
          cell.className="gridColumn";   
          cell.innerHTML = index+1;
          row.appendChild(cell);
           
    for (var i=0;i<length;i++) { 
           
        cell = document.createElement( "TD" );
        cell.className="gridColumn";   
        cell.innerHTML = fields[i];
        row.appendChild(cell);
     
    }
     
    
   
}





function EmployeeMultipleProjectDetails() {
    var test=document.getElementById("assignedToUID2").value;
   
    if (test == "") {

        clearTable1();
        hideScrollBar();
        var validationMessage=document.getElementById("authorEmpValidationMessage2");
        validationMessage.innerHTML = "";
        document.frmSearch.preAssignEmpId.value="";
    //frmSearch issuesForm
    } else {
        if (test.length >2) {
            //alert("CONTENXT_PATH-- >"+CONTENXT_PATH)
            var url = CONTENXT_PATH+"/getEmployeeDetailforPMOActivity.action?customerName="+escape(test);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                //    alert("req-->"+req);
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        // alert(req.responseXML);
                    
                        parseEmpMessagesForMultipleProjectDeatils(req.responseXML);
                    } else if (req.status == 204){
                        clearTable1();
                    }
                }
            };
            req.open("GET", url, true);

            req.send(null);
        }
    }
}

function parseEmpMessagesForMultipleProjectDeatils(responseXML) {
    //alert("-->"+responseXML);
    autorow1 = document.getElementById("menu-popup");
    autorow1.style.display ="none";
    autorow = document.getElementById("menu-popup");
    autorow.style.display ="none";
    clearTable1();
    var employees = responseXML.getElementsByTagName("EMPLOYEES")[0];
    if (employees.childNodes.length > 0) {        
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");        
    } else {
        clearTable1();
    }
    if(employees.childNodes.length<10) {
        autorow1.style.overflowY = "hidden";
        autorow.style.overflowY = "hidden";        
    }
    else {    
        autorow1.style.overflowY = "scroll";
        autorow.style.overflowY = "scroll";
    }
    
    var employee = employees.childNodes[0];
    var chk=employee.getElementsByTagName("VALID")[0];
    if(chk.childNodes[0].nodeValue =="true") {
        //var validationMessage=document.getElementById("validationMessage");
        var validationMessage;
        
        validationMessage=document.getElementById("authorEmpValidationMessage2");
        isPriEmpExist = true;
         
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < employees.childNodes.length; loop++) {
            
            var employee = employees.childNodes[loop];
            var customerName = employee.getElementsByTagName("NAME")[0];
            var empid = employee.getElementsByTagName("EMPID")[0];
            appendEmployeeForMultipleProjectDetails(empid.childNodes[0].nodeValue,customerName.childNodes[0].nodeValue);
        }
        var position;
        
        
        position = findPosition(document.getElementById("assignedToUID2"));
        
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage = '';
      
        isPriEmpExist = false;
        validationMessage=document.getElementById("authorEmpValidationMessage2");
    
 
        validationMessage.innerHTML = " Invalid ! Select from Suggesstion List. ";
        validationMessage.style.color = "green";
        validationMessage.style.fontSize = "12px";
       
        document.getElementById("assignedToUID2").value = "";
        document.getElementById("preAssignEmpId2").value = "";
            
        
    }
}
function appendEmployeeForMultipleProjectDetails(empId,empName) {
    
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
    // if(assignedToType=='pre'){
    linkElement.setAttribute("href", "javascript:set_empMultipleProjects('"+empName +"','"+ empId +"')");

    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
//fillWorkPhone(empId);
}


function set_empMultipleProjects(eName,eID){
    clearTable1();
    document.empSearch.assignedToUID2.value =eName;
    document.empSearch.preAssignEmpId2.value =eID;
}


/*Author : Tirumala Teja kadamanti
 *
 *Date :04/24/2017
 * Populating Practice and Subpractice
 * 
 */


function getPracticeDataForMultipleProjects() {
  
    var departmentName=document.getElementById("departmentIdEmpPrj").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler80(req, populatePracticeForResource);
    var url = CONTENXT_PATH+"/getEmpDepartment.action?departmentName="+departmentName;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populatePracticeForResource(resXML) {    
   
    var practiceId = document.getElementById("practiceIdEmpPrj");
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



function getSubPracticeDataForMultipleProjects(){

    

    var practiceName = document.getElementById("practiceIdEmpPrj").value;



    var req = newXMLHttpRequest();

    req.onreadystatechange = readyStateHandler80(req, populateSubPracticesResources);

    var url = CONTENXT_PATH+"/getEmpPractice.action?practiceName="+practiceName;

    req.open("GET",url,"true");    

    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

    req.send(null);

    

}



function populateSubPracticesResources(resXML) {
    var subPractice = document.getElementById("subPracticeEmpPrj");
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