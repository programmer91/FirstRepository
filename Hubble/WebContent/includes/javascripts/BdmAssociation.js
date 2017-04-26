/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


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
 //   alert('responseString%%%% ******'+responseString);
    //alert('rowCount%%%% ******'+rowCount);
      
    
    var records = responseString.split(recordDelimiter); 
    //  alert("records---->"+records);
    generateTable(oTable,headerFields,records,fieldDelimiter);
}


function generateTable(oTable, headerFields,records,fieldDelimiter) {	
    //   alert("In Generate Table "+fieldDelimiter);
    //alert(records);
    var tbody = oTable.childNodes[0];    
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
     if(oTable.id == "tblBdmReport"){
          generateTableHeaderBdm(tbody,headerFields);
                
            }
            else{
             generateTableHeader(tbody,headerFields);    
            }
   
    
    var rowlength;
    rowlength = records.length-1;
    // alert("rowlength--->"+rowlength);
    if(rowlength >0 && records!=""){
        //alert("rowlength-->^"+records);
        for(var i=0;i<rowlength;i++) {
           

           if(oTable.id == "tblBdmReport"){
                generateBdmListDetails(tbody,records[i],fieldDelimiter); 
            }
            else{
                generateRow(oTable,tbody,records[i],fieldDelimiter);  
            }
        }
       
    } else {
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
    if(oTable.id == "tblBdmReport"){
        cell.colSpan = "11";
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
    if(oTable.id == "tblBdmReport"){
        cell.colSpan = "11";
        cell.innerHTML ="Total&nbsp;Records&nbsp;:"+totalRec;
     cell.setAttribute('align','right');
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

function getBdmList()
{
    var oTable = document.getElementById("tblBdmReport");
    clearTable2(oTable);
    
    
   
    var BdmName=document.getElementById("bdmId").value;
  //  alert(BdmName);
  //  var endDate=document.getElementById("logEndDate").value;
// document.getElementById("recordDisplay").style.display = 'none';
//     $(".pagination").css("display","none");
     
      $('span.pagination').empty().remove();
    document.getElementById("totalState1").innerHTML = "";
     
    var req = newXMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {      
                document.getElementById("bdmAvailableReport").style.display = 'none';
                displayBdmAvailableReport(req.responseText);                        
            } 
        }else {
            document.getElementById("bdmAvailableReport").style.display = 'block';
        // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }; 
    //   var url = CONTENXT_PATH+"/getAvailableEmpReport.action?state="+escape(state)+"&country="+country+"&departmentId="+departmentId+"&practiceId="+practiceId;
  //  var url = CONTENXT_PATH+"/getAvailableEmpReport.action?state="+escape(state)+"&country="+country+"&departmentId="+departmentId+"&practiceId="+practiceId+"&subPracticeId="+subPracticeId;
//  var url = CONTENXT_PATH+"/getBdmReport.action?startDate="+startDate+"&endDate="+endDate;
  var url = CONTENXT_PATH+"/getBdmReport.action?bdmId="+BdmName;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
    req.send(null);
}


 function clearTable2(tableId) {
    var tbl =  tableId;
    var lastRow = tbl.rows.length; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}

function displayBdmAvailableReport(response) {
  //alert(response);
    var oTable = document.getElementById("tblBdmReport");
    clearTable(oTable);
    

    var dataArray = response;
    
    if(dataArray == "no data")
    {
        alert("No Records Found for this Search");   
    }
    else {
        
        // tbody = document.createElement("TBODY");
     //   var headerFields = new Array("S.No","Reports To","Employee name","practice","Email","Phone Number","SkillSet");
       
       
            // var headerFields = new Array("S.No","Reports&nbsp;To","Employee&nbsp;name","Exp","Practice","Project&nbsp;Name","Res&nbsp;Type","Available Utilization","Email","Phone&nbsp;Number","SkillSet");
             
             var headerFields = new Array("S.No","BDM Name","EmailId","PhoneNo","ReportsTo","Team");
//        document.getElementById("recordDisplay").style.display = 'block';
//         $(".pagination").css("display","block");
       //  var dataArray = response;
              
               var temp = new Array;
        temp = dataArray.split('addto');   
              
            //  alert(temp)
               if(response!=''){
        //   alert("temp[1] is----------->"+temp[0]);
            document.getElementById("totalState1").innerHTML = temp[0];
           totalRec= temp[1];
            ParseAndGenerateHTML1(oTable,temp[0], headerFields);
               pagerOption();
        }else{
            alert('No Result For This Search...');
            spnFast.innerHTML="No Result For This Search...";                
        }
              
       
//        if(response!=''){
//           
//         //   document.getElementById("totalState1").innerHTML = temp[1];
//        
//            ParseAndGenerateHTML1(oTable,response, headerFields);
//        }else{
//            alert('No Result For This Search...');
//            spnFast.innerHTML="No Result For This Search...";                
//        }
    } 
    
     $('#tblBdmReport').tablePaginate({navigateType:'navigator'},recordPage);
}



function generateBdmListDetails(tableBody,record,fieldDelimiter){
    var row;
    var cell;
    var fieldLength;
     
    var fields = record.split(fieldDelimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
  //  alert("length is---->"+length);
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    for (var i=0;i<length;i++) {            
       cell = document.createElement( "TD" );
       cell.className="gridColumn";       
      
    //   alert(fields[i]);
      
              cell.innerHTML = fields[i];  
           cell.setAttribute("align","left");   
            if(parseInt(i,10)==0){
                cell.innerHTML =fields[0];
            }
//            if(parseInt(i,10)==0){
//                cell.innerHTML =fields[1];
//            }
              if(parseInt(i,10)==1){
                cell.innerHTML =fields[2];
            }
              if(parseInt(i,10)==2){
                cell.innerHTML =fields[3];
            }
              if(parseInt(i,10)==3){
                cell.innerHTML =fields[4];
            }
             if(parseInt(i,10)==4){
                cell.innerHTML =fields[5];
            }
         if(parseInt(i,10)==5){
          //   alert("in if");
                cell = document.createElement( "TD" );
                cell.className="gridColumn";   
                 cell.setAttribute("align","center");
//                  cell.width = 60;
                cell.innerHTML = "<a href='javascript:addBdmTeam("+fields[1]+")'><img SRC='../includes/images/go_21x21.gif' WIDTH=18 HEIGHT=15 BORDER=0 ALTER='Click to Add'></a>";
             
        } 
           row.appendChild( cell );
        
       
               
        }
   }
   
   
     function addBdmTeam(bdmId){
       //  alert("bdmId is---->"+bdmId)
         var bdmId=bdmId;
       window.location="addBdmTeam.action?bdmId="+bdmId;
   }
   
   
   
   
   function mytoggleOverlayBdmAssociates(){
            // hideRow('downloadTr');
            // showRow('uploadTr');
               hideRow('editSalesTeam');
             showRow('addSalesTeam');
       document.getElementById("resultMessage").value="";
            document.getElementById("headerLabel").style.color="white";
            document.getElementById("headerLabel").innerHTML="Bdm Team Association";
            var overlay = document.getElementById('overlayBdmAssociates');
            var specialBox = document.getElementById('specialBoxBdmAssociates');
        
            overlay.style.opacity = .8;
            if(overlay.style.display == "block"){
                overlay.style.display = "none";
                specialBox.style.display = "none";
              //  var startDate = document.getElementById("startDate").value;
                //document.frmEmpSearch.submit();
                document.frmDBGrid.submit();
                //window.location = 'reviewSearch.action?startDate='+startDate+'&endDate='+endDate+'&reviewType='+reviewType+'&reviewStatus='+reviewStatus+'&empnameById='+empnameById;
            } else {
                overlay.style.display = "block";
                specialBox.style.display = "block";
            }
        }
        
        
        
        function SalesTeam() {
    var test=document.getElementById("assignedToBDM").value;
   
    if (test == "") {

        clearTable1();
        hideScrollBar();
        var validationMessage=document.getElementById("authorBdmValidationMessage");
        validationMessage.innerHTML = "";
        document.frmSearch.preAssignSalesId.value="";
    //frmSearch issuesForm
    } else {
        if (test.length >2) {
            //alert("CONTENXT_PATH-- >"+CONTENXT_PATH)
            var url = CONTENXT_PATH+"/getSalesTeamforBDMAssociate.action?salesName="+escape(test);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                //    alert("req-->"+req);
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        // alert(req.responseXML);
                    
                        parseSalesMessagesForBdm(req.responseXML);
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


function parseSalesMessagesForBdm(responseXML) {
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
        
        validationMessage=document.getElementById("authorBdmValidationMessage");
        isPriEmpExist = true;
         
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < employees.childNodes.length; loop++) {
            
            var employee = employees.childNodes[loop];
            var customerName = employee.getElementsByTagName("NAME")[0];
            var empid = employee.getElementsByTagName("EMPID")[0];
            appendSalesForBdm(empid.childNodes[0].nodeValue,customerName.childNodes[0].nodeValue);
        }
        var position;
        
        
        position = findPosition(document.getElementById("assignedToBDM"));
        
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage = '';
      
        isPriEmpExist = false;
        validationMessage=document.getElementById("authorBdmValidationMessage");
    
 
        validationMessage.innerHTML = " Invalid ! Select from Suggesstion List. ";
        validationMessage.style.color = "green";
        validationMessage.style.fontSize = "12px";
       
        document.getElementById("assignedToBDM").value = "";
        document.getElementById("preAssignSalesId").value = "";
            
        
    }
}


function appendSalesForBdm(empId,empName) {
    
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
    document.addBdmTeamAssociates.assignedToBDM.value =eName;
    document.addBdmTeamAssociates.preAssignSalesId.value =eID;
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


function checkResourceNameValidation(){
 //   alert("in checkResourceNameVaalidation()");
  var  assignedToBDM =  document.getElementById("assignedToBDM").value;
//  alert(assignedToBDM);
  if(assignedToBDM == ""|| assignedToBDM == null){
      alert("please suggest an sales associative");
      return false;
  }
  return true;
}

function generateTableHeaderBdm(tableBody,headerFields) {
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
//        cell.width = 20;
    }
    
     
}



function toggleEditBDMAssociateOverlay(teamMemberId,bdmId){
    //alert("teamMemberId is----->"+teamMemberId);
  var overlay = document.getElementById('overlayBdmAssociates');
            var specialBox = document.getElementById('specialBoxBdmAssociates');
             document.getElementById("teamMemberId").value=teamMemberId;
    hideRow('addSalesTeam');
   // showRow('downloadTr');
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Edit Sales Team";
     document.getElementById("load").style.display = 'block';
     $.ajax({
            url:'getSalesTeamDetails.action?teamMemberId='+teamMemberId+'&bdmId='+bdmId,//
            context: document.body,
            success: function(responseText) {
                                var json = $.parseJSON(responseText);
                
                var EmployeeName = json["EmployeeName"];
                var Status = json["Status"];
              var uniqueId= json["Id"];
                 
               //alert(ReviewTypeId+" BillingAmount "+BillingAmount);
                
                 document.getElementById("salesTeamMember").value = EmployeeName;
    document.getElementById("teamMemberStatus").value = Status;
document.getElementById("uniqueId").value = uniqueId;
               document.getElementById("load").style.display = 'none';  
               document.getElementById("resultMessage").style.display = 'none';  
            
                
            }, error: function(e){
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
      var row = document.getElementById(id);
      row.style.display = 'none';
    }
  function showRow(id) {
      var row = document.getElementById(id);
      row.style.display = '';
    }  
    
      function addSalesTeamToBdm()
{
  //alert("in addSalesTeamToBdm")
   var bdmName = document.getElementById('bdmName').value;
   var bdmId = document.getElementById('bdmId').value;
//alert("bdmName----->"+bdmName);
    var  assignedToBDM =  document.getElementById("assignedToBDM").value;
    var  preAssignSalesId =  document.getElementById("preAssignSalesId").value;

   if(assignedToBDM == ""|| assignedToBDM == null){
       document.getElementById('resultMessage').innerHTML = "<font color=red>Please suggest a sales associative.</font>";
   }
   else {
        assignedToBDM = escape(assignedToBDM);
       preAssignSalesId = escape(preAssignSalesId);
     //  alert("asgdig---->"+document.getElementById("resultMessage").value)
        document.getElementById("resultMessage").value='';
    document.getElementById("load").style.display = 'block';
      
      $.ajax({
        url:'addSalesToBdm.action?bdmId='+bdmId+'&preAssignSalesId='+preAssignSalesId+'&bdmName='+bdmName,//
        
        secureuri:false,//false
        fileElementId:'file',//id  <input type="file" id="file" name="file" />
      context: document.body,
            success: function(responseText){
        //  alert(responseText);
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
        
        },
        error: function(e){
            
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
       
        }
    });
    
   }
    return false;

}


function updateSalesTeam(){
//alert("in updateSalesTeam()")
    var bdmName = document.getElementById('bdmName').value;
   var bdmId = document.getElementById('bdmId').value;
    var  salesTeamMember =  document.getElementById("salesTeamMember").value;
    var  teamMemberId =  document.getElementById("teamMemberId").value;
    var  teamMemberStatus =  document.getElementById("teamMemberStatus").value;
    var  uniqueId =  document.getElementById("uniqueId").value;
  document.getElementById('resultMessage').style.display="block";
//alert("bdmName"+bdmName);
//alert("bdmId"+bdmId);
//alert("salesTeamMember"+salesTeamMember);
//alert("teamMemberId"+teamMemberId);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req,ppulateBdmTeamUpadteResult); 

    //var url=CONTENXT_PATH+"/updateMyReview.action?reviewId="+reviewId+"&overlayReviewType="+overlayReviewType+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay;
    var url=CONTENXT_PATH+"/updateBdmTeam.action?teamMemberId="+teamMemberId+"&teamMemberStatus="+teamMemberStatus+"&bdmId="+bdmId+"&id="+uniqueId;
 //   alert("url");
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function ppulateBdmTeamUpadteResult(resText){
     document.getElementById('resultMessage').innerHTML = "<font color=green size=2px>Updated Successfully</font>";
     document.getElementById('resultMessage').style.display="block";
  //  alert("Updated Successfully");
   // toggleOverlay();
}


function SuggestSalesTeam() {
    var test=document.getElementById("salesTeamMember").value;
   
    if (test == "") {

        clearTable1();
        hideScrollBar();
        var validationMessage=document.getElementById("authorBdmUpdateValidationMessage");
        validationMessage.innerHTML = "";
        document.frmSearch.teamMemberId.value="";
    //frmSearch issuesForm
    } else {
        if (test.length >2) {
            //alert("CONTENXT_PATH-- >"+CONTENXT_PATH)
            var url = CONTENXT_PATH+"/getSalesTeamforBDMAssociate.action?salesName="+escape(test);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                //    alert("req-->"+req);
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        // alert(req.responseXML);
                    
                        parseSalesMessagesUpdateForBdm(req.responseXML);
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

function parseSalesMessagesUpdateForBdm(responseXML) {
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
        
        validationMessage=document.getElementById("authorBdmUpdateValidationMessage");
        isPriEmpExist = true;
         
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < employees.childNodes.length; loop++) {
            
            var employee = employees.childNodes[loop];
            var customerName = employee.getElementsByTagName("NAME")[0];
            var empid = employee.getElementsByTagName("EMPID")[0];
            appendUpdateSalesForBdm(empid.childNodes[0].nodeValue,customerName.childNodes[0].nodeValue);
        }
        var position;
        
        
        position = findPosition(document.getElementById("salesTeamMember"));
        
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage = '';
      
        isPriEmpExist = false;
        validationMessage=document.getElementById("authorBdmUpdateValidationMessage");
    
 
        validationMessage.innerHTML = " Invalid ! Select from Suggesstion List. ";
        validationMessage.style.color = "green";
        validationMessage.style.fontSize = "12px";
       
        document.getElementById("salesTeamMember").value = "";
        document.getElementById("teamMemberId").value = "";
            
        
    }
}

function appendUpdateSalesForBdm(empId,empName) {
    
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
    linkElement.setAttribute("href", "javascript:set_Bdm('"+empName +"','"+ empId +"')");

    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
//fillWorkPhone(empId);
}
function set_Bdm(eName,eID){
    clearTable1();
    document.addBdmTeamAssociates.salesTeamMember.value =eName;
    document.addBdmTeamAssociates.teamMemberId.value =eID;
}