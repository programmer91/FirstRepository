var isIE;
var completeTable;
var autorow;
var autorow1;
var autorow2;
var autorow3;
var completeField;
var completeTable1;

function init() {
    //var menu = document.getElementById("auto-row");
    //var menu1 = document.getElementById("auto-row1");
    autorow = document.getElementById("menu-popup");
    autorow.style.display ="none";
    autorow1 = document.getElementById("menu-popup");
    autorow1.style.display ="none";
    
    //autorow.style.top = getElementY(menu) + "px";
    //autorow1.style.top = getElementY(menu1) + "px";
    var height = document.documentElement.clientHeight - 120;
    // autorow1.style.height = Math.max(height, 150);
    //    autorow1.style.height = "auto";
    //    autorow1.style.overflowY = "scroll";
    //    autorow.style.height = "auto";
    //    autorow.style.overflowY = "scroll";
    
    completeTable = document.getElementById("completeTable");
    completeTable.setAttribute("bordercolor", "white");
   
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
function clearTableSuggestion() {
    if (completeTable) {
        //alert("In Clear Table");
        completeTable.setAttribute("bordercolor", "white");
        completeTable.setAttribute("border", "0");
        completeTable.style.visible = false;
        
        //document.consultantRequirementForm.email2.value ="";
        
        
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = " ";
        
      
        
       
        
        for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
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
   
    for (var i=0; i<=rowFeildsSplit.length-1; i++) {
      
      
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild(cell);
        cell.innerHTML = rowFeildsSplit[i];
        cell.width = 120;
      if(oTable.id=="tblgreenSheet"){
          if(i==6){
               cell.style="text-align:right;width:auto;";
            cell.innerHTML="$&nbsp;"+moneyFormat(rowFeildsSplit[i]);
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
    cell.colSpan = "8";
    cell.align="right";
     cell.id="footer"+oTable.id;
    row.appendChild(cell);
}
function getAccountNames() {
    completeField = document.getElementById("accountName");
    //alert(test);
    if (completeField.value== "") {
        clearTableSuggestion();
    } else {
        var test = document.getElementById("accountName").value;
        if (test.length >2) {
            //  var url = CONTENXT_PATH+"/getConsultantList.action?email="+ escape(test);
            var url = CONTENXT_PATH+"/getAccountNamesList.action?accountName="+ escape(test);
            //alert(url);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        parseCustMessages(req.responseXML);
                    } else if (req.status == 204){
                        clearTableSuggestion();
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }
}
function parseCustMessages(responseXML) {
    //alert("beforeClear");
    clearTableSuggestion();
    //  alert(responseXML.getElementsByTagName("ACCOUNTNAMES")[0]);
  
    var accountNames = responseXML.getElementsByTagName("ACCOUNTNAMES")[0];
    // alert("test");
    if (accountNames.childNodes.length > 0) {
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");
    } else {
        clearTableSuggestion();
    }
    if(accountNames.childNodes.length<10) {
        autorow1.style.overflowY = "hidden";
        autorow.style.overflowY = "hidden";
    //alert("in If");
    }
    else {
        autorow1.style.overflowY = "scroll";
        autorow.style.overflowY = "scroll";
    //alert("in Else");
    }
    var accountName = accountNames.childNodes[0];
    var chk=accountName.getElementsByTagName("VALID")[0];
    //alert("Before If");
    if(chk.childNodes[0].nodeValue =="true") {
        //alert("Again In If");
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < accountNames.childNodes.length; loop++) {
            //alert("in For");
            var accountName = accountNames.childNodes[loop];
            var id = accountName.getElementsByTagName("ID")[0];
            var accName = accountName.getElementsByTagName("ACCNAME")[0];
            //  alert(id.childNodes[0].nodeValue+" "+accName.childNodes[0].nodeValue);
            // alert(targetRate.childNodes[0].nodeValue);
            // appendCustomer(email.childNodes[0].nodeValue,id.childNodes[0].nodeValue,phone.childNodes[0].nodeValue,availableFrom.childNodes[0].nodeValue,isReject.childNodes[0].nodeValue,targetRate.childNodes[0].nodeValue,workauthorization.childNodes[0].nodeValue);
            appendAccountNames(accName.childNodes[0].nodeValue,id.childNodes[0].nodeValue)
            //appendCustomer(email.childNodes[0].nodeValue,id.childNodes[0].nodeValue);
            vmessage = 1;
        }
        
    } //if
    //alert("After IF");
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "  Account Name is INVALID ";
        vmessage = 2;
       
    }
}
function appendAccountNames(accountName,Id){
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
    linkElement.setAttribute("href","javascript:set_AccountId('"+accountName+"','"+Id+"')");
    linkElement.appendChild(document.createTextNode(accountName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}
function set_AccountId(accountName,id){
    clearTableSuggestion();
    
    document.getElementById("accountId").value=id;
    document.getElementById("accountName").value=accountName;
    getMyProjectsByCustomer(id);
// var test=document.getElementById("accountId").value;
//alert("test"+test);
    
   
    
}
function hideScrollBar() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
}
function getBDMpiecharts() {
   
    document.getElementById("resultMessage").style.display="none";
    document.getElementById("staatusGraph").style.display="none";
    document.getElementById("oppertunitiesGraph").style.display="none";
    document.getElementById("requirementGraph").style.display="none";
    document.getElementById("greensheetGraph").style.display="none";
   document.getElementById("tableHeading1").style.display='none';
  document.getElementById("noteLable1").style.display='none';
   clearTable1(document.getElementById("tblrequirements1"));
    var accountId = document.getElementById("accountId").value;
    var fromMonth=    document.getElementById("fromMonth").value;
    var toMonth=    document.getElementById("toMonth").value;
    var fromYear=    document.getElementById("fromYear").value;
    var toYear=    document.getElementById("toYear").value;
    var accountName= document.getElementById("accountName").value;
    
    if(fromMonth==""){
        alert("Please select from month");
        return false;
    }
    if(toMonth==""){
        alert("Please select to month");
        return false;
    }
    if(fromYear=="" || isNaN(fromYear)){
        alert("Enter valid year");
        document.getElementById("fromYear").value="";
        return false;
        
    }
    if(toYear=="" || isNaN(toYear)){
        alert("Enter valid year");
        document.getElementById("toYear").value="";
        return false;
        
    } 
    if(accountName==""){
        accountId=0;
    }
    
    //    var checkResult = compareDates(startDate,endDate);
    //    if(!checkResult) {
    //      return false;
    //    }    
    
   
           
    var req = newXMLHttpRequest();
    req.onreadystatechange = loadActMessageASh(req, displaysalesActivitiesAsGraph);
    var url = CONTENXT_PATH+"/bdmDashboardInfo.action?fromMonth="+fromMonth+"&fromYear="+fromYear+"&toMonth="+toMonth+"&toYear="+toYear+"&accountId="+accountId;
    // alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
       

}
function displaysalesActivitiesAsGraph(response) {
    //var tableId = document.getElementById("tblRecDashBoardSummGraph");
   
    if(response=="addto0"){
        //alert("injui");
        document.getElementById("resultMessage").style.display="block";
        document.getElementById("resultMessage").innerHTML="No data available";
      
    }else{
        var result1 = response.split("Addedto");
        
       
        // generate status pie chart
        var statusresult =result1[0].split("*@!");
       // statusChart(statusresult);
        document.getElementById("staatusGraph").style.display="block";
        generatePieChart(statusresult, "Account Status", document.getElementById("statuspiechart"));
        
       
        // generate requirenent pie chart
        var requirementresult =result1[2].split("*@!");
         requirementChart(requirementresult);
        document.getElementById("requirementGraph").style.display="block";
       // generatePieChart(requirementresult, "Requirement Status", document.getElementById("requirementpiechart"));
        
         // generate Opportunity piechart
        var oppertunitiesresult =result1[1].split("*@!");
       // document.getElementById("oppertunitiesGraph").style.display="block";
       // generatePieChart(oppertunitiesresult, "Opportunities", document.getElementById("opertunitypiechart"));
      
        
        //generate green sheet pie chart
        var greensheetresult =result1[3].split("*@!");
        document.getElementById("greensheetGraph").style.display="block";
        //generatePieChart(greensheetresult, "Green Sheets", document.getElementById("greensheetpiechart"));
    }
}  
function statusChart(result){
    document.getElementById("staatusGraph").style.display="block";
      
    var arraydata = [['Status', 'Count']];
    for(var i=0; i<result.length-1; i++){
        
        var res = result[i].split("#^$");
        var dArray = [res[0],parseInt(res[1])];
        // alert(dArray);
        arraydata.push(dArray);
    }
    //alert(arraydata);
    var data = google.visualization.arrayToDataTable(arraydata);

    var options = {
        title: 'Account Status' ,
        legend: 'left',
        chartArea:{
            width:"100%"
        },
        is3D: true,
        sliceVisibilityThreshold: 0
         
    };

    var chart = new google.visualization.PieChart(document.getElementById("statuspiechart"));

    chart.draw(data, options,dArray);
}
function oppertunitiesChart(result){
    document.getElementById("oppertunitiesGraph").style.display="block";
      
    var arraydata = [['Type', 'Count']];
    for(var i=0; i<result.length-1; i++){
        
        var res = result[i].split("#^$");
        var dArray = [res[0],parseInt(res[1])];
        // alert(dArray);
        arraydata.push(dArray);
    }

    var data = google.visualization.arrayToDataTable(arraydata);

    var options = {
        title: 'Oppertunities' ,
        legend: 'left',
        chartArea:{
            width:"100%"
        },
        is3D: true
         
    };

    var chart = new google.visualization.PieChart(document.getElementById("opertunitypiechart"));

    chart.draw(data, options,dArray);
}

function requirementChart(result){
    document.getElementById("requirementGraph").style.display="block";
      
    var arraydata = [['Type', 'Count']];
    for(var i=0; i<result.length-1; i++){
        
        var res = result[i].split("#^$");
        var dArray = [res[0],parseInt(res[1])];
        // alert(dArray);
        arraydata.push(dArray);
    }

    var data = google.visualization.arrayToDataTable(arraydata);

    var options = {
        title: 'Requirement Status' ,
        legend: 'left',
        chartArea:{
            width:"100%"
        },
        is3D: true,
        pieSliceText: 'value',
         sliceVisibilityThreshold: 0
    };

    var chart = new google.visualization.PieChart(document.getElementById("requirementpiechart"));

    function selectHandler() {
        var selectedItem = chart.getSelection()[0];
        if (selectedItem) {
            var status = data.getValue(selectedItem.row, 0);
             //alert('The user selected ' + status);
            requirementDetailsByStatus(status);
        //chatOverlay(topping);
        }
    }
    google.visualization.events.addListener(chart, 'select', selectHandler);    
    chart.draw(data, options);
}
function greenSheetChart(result){
    document.getElementById("greensheetGraph").style.display="block";
      
    var arraydata = [['Type', 'Count']];
    for(var i=0; i<result.length-1; i++){
        
        var res = result[i].split("#^$");
        var dArray = [res[0],parseInt(res[1])];
        // alert(dArray);
        arraydata.push(dArray);
    }

    var data = google.visualization.arrayToDataTable(arraydata);

    var options = {
        title: 'Green Sheet' ,
        legend: 'left',
        chartArea:{
            width:"100%"
        },
        is3D: true
         
    };

    var chart = new google.visualization.PieChart(document.getElementById("greensheetpiechart"));

    chart.draw(data, options,dArray);
}
function BDMOverlay(){
    var overlay = document.getElementById('overlay');
    var specialBox1 = document.getElementById('specialBox');
    
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="";
    var req = newXMLHttpRequest();
    var fromMonth=    document.getElementById("fromMonth").value;
    var toMonth=    document.getElementById("toMonth").value;
    var fromYear=    document.getElementById("fromYear").value;
    var toYear=    document.getElementById("toYear").value;
    var accountName= document.getElementById("accountName").value;
    var accountId = document.getElementById("accountId").value;
   document.getElementById("resultMessage").style.display="none";
        document.getElementById("resultMessage").innerHTML="";
    if(fromMonth==""){
        alert("Please select from month");
        return false;
    }
    if(toMonth==""){
        alert("Please select to month");
        return false;
    }
    if(fromYear=="" || isNaN(fromYear)){
        alert("Enter valid year");
        document.getElementById("fromYear").value="";
        return false;
        
    }
    if(toYear=="" || isNaN(toYear)){
        alert("Enter valid year");
        document.getElementById("toYear").value="";
        return false;
        
    } 
    if(accountName==""){
        accountId=0;
    }
     
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox1.style.display = "none";
    } else {
       
       //document.getElementById("barchart_materialGraph").style.display="none";
        req.onreadystatechange = loadActMessageASh(req, displayBDMRevenueAsGraph);
        var url = CONTENXT_PATH+"/bdmDashboardRevenueInfo.action?fromMonth="+fromMonth+"&fromYear="+fromYear+"&toMonth="+toMonth+"&toYear="+toYear+"&accountId="+accountId;
        // alert(url);
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
}

function displayBDMRevenueAsGraph(response) {
    //var tableId = document.getElementById("tblRecDashBoardSummGraph");
   var overlay = document.getElementById('overlay');
    var specialBox1 = document.getElementById('specialBox');
    if(response=="addto0"){
        //alert("injui");
        document.getElementById("resultMessage").style.display="block";
        
        document.getElementById("resultMessage").innerHTML="No data available";
       overlay.style.display = "none";
        specialBox1.style.display = "none";
    }else{
         overlay.style.display = "block";
        specialBox1.style.display = "block";
        var result1 = response.split("*@!");
       // document.getElementById("barchart_materialGraph").style.display="bolck";
        bdmRevenueChart(result1);
        
    }
}
function bdmRevenueChart(result){
    //document.getElementById("greensheetGraph").style.display="block";
    // google.load('visualization', '1.1', {packages: ['corechart']});
    //alert("hai");
      
    var arraydata = [['Revenue', 'service','fixedBrid','software']];
    for(var i=0; i<result.length-1; i++){
        
        var res = result[i].split("#^$");
        var dArray = [res[3],parseInt(res[0]),parseInt(res[1]),parseInt(res[2])];
        // alert(dArray);
        arraydata.push(dArray);
    }

    var data = google.visualization.arrayToDataTable(arraydata);
    var view=new google.visualization.DataView(data);
    view.setColumns([
        0,1,{
            calc:"stringify",
            sourceColumn:1,
            type: "string",
            role:"annotation"
        }
        ]);

    var options = {
        title:'Revenue Generation Chart',
 titleTextStyle: {color: 'blue',fontName: 'Verdana', fontSize: 20,bold: true,italic: true
  },
        vAxes:[{title:'Revenue($)',titleTextStyle:
                    {color:'blue',fontName:'Verdana',fontSize:12,bold:true,italic:true}}],
        hAxis:{title:'Month',titleTextStyle:
                {color:'blue',fontName:'Verdana',fontSize:12,bold:true,italic:true}},
          height: 300,
          width: 900,
          legend: 'none',
         
          pointShape: 'diamond',
		 
          trendlines: { 0: {
                  type:'linear',
                  visibleInLegend:true
          } },
		   bars :'vertical',
                   legend: {position: 'bottom', maxLines: 3}
  
  
    
		  
    };

    var chart = new google.visualization.ColumnChart(document.getElementById("barchart_material"));

    chart.draw(data, options);
}
function BDMOppertunitiesOverlay(){
    var overlay = document.getElementById('overlay');
    var specialBox1 = document.getElementById('specialBox');
    
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="";
    var req = newXMLHttpRequest();
    var fromMonth=    document.getElementById("fromMonth").value;
    var toMonth=    document.getElementById("toMonth").value;
    var fromYear=    document.getElementById("fromYear").value;
    var toYear=    document.getElementById("toYear").value;
    var accountName= document.getElementById("accountName").value;
    var accountId = document.getElementById("accountId").value;
    document.getElementById("resultMessage").style.display="none";
        document.getElementById("resultMessage").innerHTML="";
    if(fromMonth==""){
        alert("Please select from month");
        return false;
    }
    if(toMonth==""){
        alert("Please select to month");
        return false;
    }
    if(fromYear=="" || isNaN(fromYear)){
        alert("Enter valid year");
        document.getElementById("fromYear").value="";
        return false;
        
    }
    if(toYear=="" || isNaN(toYear)){
        alert("Enter valid year");
        document.getElementById("toYear").value="";
        return false;
        
    } 
    if(accountName==""){
        accountId=0;
    }
     
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox1.style.display = "none";
    } else {
       
       
        req.onreadystatechange = loadActMessageASh(req, displayBDMOppertunitiesAsGraph);
        var url = CONTENXT_PATH+"/bdmDashboardOppertunitiesInfo.action?fromMonth="+fromMonth+"&fromYear="+fromYear+"&toMonth="+toMonth+"&toYear="+toYear+"&accountId="+accountId;
        // alert(url);
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
}
function displayBDMOppertunitiesAsGraph(response) {
     var overlay = document.getElementById('overlay');
    var specialBox1 = document.getElementById('specialBox');
    if(response=="addto0"){
        document.getElementById("resultMessage").style.display="block";
        document.getElementById("resultMessage").innerHTML="No data available";
      overlay.style.display = "none";
        specialBox1.style.display = "none";
    }else{
         overlay.style.display = "block";
        specialBox1.style.display = "block";
        
        var result1 = response.split("*@!");
        bdmOppertunityChart(result1);
    }
}
function bdmOppertunityChart(result){
    google.load('visualization', '1.1', {
        packages: ['corechart']
    });

      
    var arraydata = [['Opportunity($)', 'Count']];
    for(var i=0; i<result.length-1; i++){
        
        var res = result[i].split("#^$");
        var dArray = [res[1],parseInt(res[0])];
        // alert(dArray);
        arraydata.push(dArray);
    }

    var data = google.visualization.arrayToDataTable(arraydata);
    var view=new google.visualization.DataView(data);
    view.setColumns([
        0,1,{
            calc:"stringify",
            sourceColumn:1,
            type: "string",
            role:"annotation"
        }
        ]);
    var options = {
        title:'Opportunities by month',
 titleTextStyle: {color: 'blue',fontName: 'Verdana', fontSize: 20,bold: true,italic: true
     
  },
         vAxes:[{title:'Opportunities($)',titleTextStyle:
                    {color:'blue',fontName:'Verdana',fontSize:12,bold:true,italic:true}}],
        hAxis:{title:'Month',titleTextStyle:
                {color:'blue',fontName:'Verdana',fontSize:12,bold:true,italic:true}},
         
          height: 300,
          width:800,
          legend: 'none',
          colors: ['DarkOrchid', '#33ac71'],
          pointShape: 'diamond',
		 
          trendlines: { 0: {} },
		   bars :'vertical'
  
    
		  
        };


    var chart = new google.visualization. ColumnChart(document.getElementById("barchart_material"));

    chart.draw(view, options);
}
function opportunitiesPieChartDetails(){
    var fromMonth=    document.getElementById("fromMonth1").value;
    var toMonth=    document.getElementById("toMonth1").value;
    var fromYear=    document.getElementById("fromYear1").value;
    var toYear=    document.getElementById("toYear1").value;
//    var practiceName= document.getElementById("practiceName").value;
    document.getElementById("noteLable").style.display='none';
    document.getElementById("loadMessage").style.display="block";
    document.getElementById("resultMessage1").style.display="none";
    document.getElementById("opportunitiesGraph").style.display="none";
    document.getElementById("requirementByPracticeGraph").style.display="none";
    document.getElementById("greensheetByPracticeGraph").style.display="none";
    clearTable1(document.getElementById("tblrequirements"));
    clearTable1(document.getElementById("tblopportunities"));
    clearTable1(document.getElementById("tblgreenSheet"));
     document.getElementById("tableHeading").style.display='none';
    if(fromMonth==""){
        alert("Please select from month");
        return false;
    }
    if(toMonth==""){
        alert("Please select to month");
        return false;
    }
    if(fromYear=="" || isNaN(fromYear)){
        alert("Enter valid year");
        document.getElementById("fromYear1").value="";
        return false;
        
    }
    if(toYear=="" || isNaN(toYear)){
        alert("Enter valid year");
        document.getElementById("toYear1").value="";
        return false;
        
    } 
    var req = newXMLHttpRequest();
    req.onreadystatechange = loadActMessageASh(req, opportunitiesPieChartDetailsResponse);
    var url = CONTENXT_PATH+"/opportunitesByPractice.action?fromMonth="+fromMonth+"&fromYear="+fromYear+"&toMonth="+toMonth+"&toYear="+toYear;
    // alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function opportunitiesPieChartDetailsResponse(response) {
    // alert(response);
    document.getElementById("loadMessage").style.display="none";
    document.getElementById("opportunitiesGraph").style.display="none";
    if(response=="addto0"){
//        document.getElementById("resultMessage1").style.display="block";
//        document.getElementById("resultMessage1").innerHTML="No data available";
      
    }else{
        var result1 = response.split("*@!");
        opportunitiesChart(result1);
    }
    requirementPieChartDetails();
}
function opportunitiesChart(result){
    document.getElementById("opportunitiesGraph").style.display="block";
      
    var arraydata = [['Type', 'Count']];
    for(var i=0; i<result.length-1; i++){
        
        var res = result[i].split("#^$");
        var dArray = [res[0],parseInt(res[1])];
        // alert(dArray);
        arraydata.push(dArray);
    }

    var data = google.visualization.arrayToDataTable(arraydata);

    var options = {
        title: 'Opportunities' ,
        legend: 'left',
        chartArea:{
            width:"100%"
        },
        is3D: true,
        pieSliceText: 'value',
         sliceVisibilityThreshold: 0
         
    };

    var chart = new google.visualization.PieChart(document.getElementById("opportunitieschart"));
    function selectHandler() {
        var selectedItem = chart.getSelection()[0];
        if (selectedItem) {
            var practice = data.getValue(selectedItem.row, 0);
            // alert('The user selected ' + topping);
            opportunitiesDetailsByPractice(practice);
        //chatOverlay(topping);
        }
    }
    google.visualization.events.addListener(chart, 'select', selectHandler);    
    chart.draw(data, options);
        
}
function requirementPieChartDetails(){
    var fromMonth=    document.getElementById("fromMonth1").value;
    var toMonth=    document.getElementById("toMonth1").value;
    var fromYear=    document.getElementById("fromYear1").value;
    var toYear=    document.getElementById("toYear1").value;
//    var practiceName= document.getElementById("practiceName").value;
    
    document.getElementById("loadMessage").style.display="block";
    if(fromMonth==""){
        alert("Please select from month");
        return false;
    }
    if(toMonth==""){
        alert("Please select to month");
        return false;
    }
    if(fromYear=="" || isNaN(fromYear)){
        alert("Enter valid year");
        document.getElementById("fromYear1").value="";
        return false;
        
    }
    if(toYear=="" || isNaN(toYear)){
        alert("Enter valid year");
        document.getElementById("toYear1").value="";
        return false;
        
    } 
    var req = newXMLHttpRequest();
    req.onreadystatechange = loadActMessageASh(req, requirementPieChartDetailsResponse);
    var url = CONTENXT_PATH+"/requirementsByPractice.action?fromMonth="+fromMonth+"&fromYear="+fromYear+"&toMonth="+toMonth+"&toYear="+toYear;
    // alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function requirementPieChartDetailsResponse(response) {
    //alert(response);
    document.getElementById("loadMessage").style.display="none";
    document.getElementById("requirementByPracticeGraph").style.display="none";
    if(response=="addto0"){
//        document.getElementById("resultMessage1").style.display="block";
//        document.getElementById("resultMessage1").innerHTML="No data available";
      
    }else{
        var result1 = response.split("*@!");
        document.getElementById("requirementByPracticeGraph").style.display="block";
       // generatePieChart(result1,"Requirements",document.getElementById("requirementByPracticePiechart"));
        requirementByPracticeChart(result1);
    }
    greensheetPieChartDetails();
}
function requirementByPracticeChart(result){
      
    var arraydata = [['Type', 'Count']];
    for(var i=0; i<result.length-1; i++){
        
        var res = result[i].split("#^$");
        var dArray = [res[0],parseInt(res[1])];
        // alert(dArray);
        arraydata.push(dArray);
    }

    var data = google.visualization.arrayToDataTable(arraydata);

    var options = {
        title: 'Requirements' ,
        legend: 'left',
        chartArea:{
            width:"100%"
        },
        is3D: true,
        pieSliceText: 'value',
         sliceVisibilityThreshold: 0
         
    };

    var chart = new google.visualization.PieChart(document.getElementById("requirementByPracticePiechart"));
    function selectHandler() {
        var selectedItem = chart.getSelection()[0];
        if (selectedItem) {
            var practice = data.getValue(selectedItem.row, 0);
            // alert('The user selected ' + topping);
            requirementDetailsByPractice(practice);
        //chatOverlay(topping);
        }
    }
    google.visualization.events.addListener(chart, 'select', selectHandler);    
    chart.draw(data, options);
        
}
function generatePieChart(result,titleText,Id){
    
      
    var arraydata = [['X', 'Y']];
    for(var i=0; i<result.length-1; i++){
        
        var res = result[i].split("#^$");
        var dArray = [res[0],parseInt(res[1])];
        // alert(dArray);
        arraydata.push(dArray);
    }

    var data = google.visualization.arrayToDataTable(arraydata);

    var options = {
        title: titleText ,
        legend: 'left',
        chartArea:{
            width:"100%"
        },
        is3D: true,
        pieSliceText: 'value',
        sliceVisibilityThreshold: 0
         
    };

    var chart = new google.visualization.PieChart(Id);

    chart.draw(data, options,dArray);
}
function greensheetPieChartDetails(){
    var fromMonth=    document.getElementById("fromMonth1").value;
    var toMonth=    document.getElementById("toMonth1").value;
    var fromYear=    document.getElementById("fromYear1").value;
    var toYear=    document.getElementById("toYear1").value;
//    var practiceName= document.getElementById("practiceName").value;
    
    document.getElementById("loadMessage").style.display="block";
    if(fromMonth==""){
        alert("Please select from month");
        return false;
    }
    if(toMonth==""){
        alert("Please select to month");
        return false;
    }
    if(fromYear=="" || isNaN(fromYear)){
        alert("Enter valid year");
        document.getElementById("fromYear1").value="";
        return false;
        
    }
    if(toYear=="" || isNaN(toYear)){
        alert("Enter valid year");
        document.getElementById("toYear1").value="";
        return false;
        
    } 
    var req = newXMLHttpRequest();
    req.onreadystatechange = loadActMessageASh(req, greensheetPieChartDetailsResponse);
    var url = CONTENXT_PATH+"/greenSheetsDetailsByPractice.action?fromMonth="+fromMonth+"&fromYear="+fromYear+"&toMonth="+toMonth+"&toYear="+toYear;
    // alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function greensheetPieChartDetailsResponse(response) {
    //alert(response);
    document.getElementById("loadMessage").style.display="none";
    document.getElementById("greensheetByPracticeGraph").style.display="none";
    if(response=="addto0"){
//        document.getElementById("resultMessage1").style.display="block";
//        document.getElementById("resultMessage1").innerHTML="No data available";
      
    }else{
        var result1 = response.split("*@!");
        // greensheetByPracticeChart(result1);
        document.getElementById("greensheetByPracticeGraph").style.display="block";
        //generatePieChart(result1,"Green Sheet",document.getElementById("greensheetByPracticeChart"));
        greensheetByPracticeChart(result1);
    }
}
function greensheetByPracticeChart(result){
      
    var arraydata = [['Type', 'Count']];
    for(var i=0; i<result.length-1; i++){
        
        var res = result[i].split("#^$");
        var dArray = [res[0],parseInt(res[1])];
        // alert(dArray);
        arraydata.push(dArray);
    }

    var data = google.visualization.arrayToDataTable(arraydata);

    var options = {
        title: 'Green Sheet' ,
        legend: 'left',
        chartArea:{
            width:"100%"
        },
        is3D: true,
        pieSliceText: 'value',
        sliceVisibilityThreshold: 0
         
    };

    var chart = new google.visualization.PieChart(document.getElementById("greensheetByPracticeChart"));
    function selectHandler() {
        var selectedItem = chart.getSelection()[0];
        if (selectedItem) {
            var poType = data.getValue(selectedItem.row, 0);
            // alert('The user selected ' + topping);
            greenSheetDetailsByPractice(poType);
        //chatOverlay(topping);
        }
    }
    google.visualization.events.addListener(chart, 'select', selectHandler);    
    chart.draw(data, options);
        
}
function opportunitiesDetailsByPractice(practiceName){    
    var fromMonth=    document.getElementById("fromMonth1").value;
    var toMonth=    document.getElementById("toMonth1").value;
    var fromYear=    document.getElementById("fromYear1").value;
    var toYear=    document.getElementById("toYear1").value;
    // var practiceName= document.getElementById("practiceName").value;
    
    //document.getElementById("loadMessage").style.display="block";
    if(fromMonth==""){
        alert("Please select from month");
        return false;
    }
    if(toMonth==""){
        alert("Please select to month");
        return false;
    }
    if(fromYear=="" || isNaN(fromYear)){
        alert("Enter valid year");
        document.getElementById("fromYear1").value="";
        return false;
        
    }
    if(toYear=="" || isNaN(toYear)){
        alert("Enter valid year");
        document.getElementById("toYear1").value="";
        return false;
        
    }
    
 document.getElementById("tableHeading").style.display='none';
 document.getElementById("tblloadMessage").style.display='block';
    
   clearTable1(document.getElementById("tblrequirements"));
    clearTable1(document.getElementById("tblopportunities"));
     clearTable1(document.getElementById("tblgreenSheet"));
    //  document.getElementById("pastloadingMessage").style.display='block';
    var req = newXMLHttpRequest();
    req.onreadystatechange = loadActMessageASh(req, opportunitiesDetailsByPracticeResponse);
   
    // alert("year-->"+departmentId);
    // alert("month-->"+empnameById);
    //loadXMLDoc( CONTENXT_PATH+'/getEmployeesForPerformers.action?departmentId='+departmentId+'&empnameById='+empnameById+'&empNo='+empNo,wagesHistoryResponse);           
    var url = CONTENXT_PATH+"/opportunitiesDetailsByPractice.action?fromMonth="+fromMonth+"&fromYear="+fromYear+"&toMonth="+toMonth+"&toYear="+toYear+"&practiceName="+practiceName;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);  

}
function opportunitiesDetailsByPracticeResponse(resText) {
    if(resText.length !=0 && resText!="addto0"){
        
        document.getElementById("tableHeading").innerHTML="Opportunities Details";
       document.getElementById("tableHeading").style.display='block';
       document.getElementById("noteLable").style.display='block';
    //   document.getElementById("noteLable").innerHTML='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Note :The details are order by CreatedBy,State,Account Name and Traget Date.';
    document.getElementById("noteLable").innerHTML='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Note :The details are order by CreatedBy ascending,State ascending,Account Name ascending and Traget Date descending.';
        var oTable = document.getElementById("tblopportunities");
        // var headerFields = new Array("S.No","Employee Name","Reports To","Start Date(YY-MM-DD)","End Date(YY-MM-DD)","Leaves")
        //var headerFields = new Array("S.No","Employee Name","Reports To","Start Date(YY-MM-DD)","End Date(YY-MM-DD)","Status","Leaves")
        //var headerFields = new Array("S.No","ReviewType","ReviewTitle","Status","EmpComments","Tl&nbsp;Rating","Tl&nbsp;Comments","Hr&nbsp;Rating","Hr&nbsp;Comments","ReviewDate")
        var headerFields = new Array("S.No","CreatedBy","Account&nbsp;Name","Title","Practice","State","CreatedDate","TargetDate")
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
        generateTableHeader(tbody,headerFields);
        var resTextSplit1 = resText.split("*@!");
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#^$");
            generateRow(oTable,tbody,resTextSplit2,index);
        }
         generateFooter(tbody,oTable);
    }else {
        alert("No Records Found");
        
    }
    document.getElementById("tblloadMessage").style.display='none';
    
}
function requirementDetailsByPractice(practiceName){    
    var fromMonth=    document.getElementById("fromMonth1").value;
    var toMonth=    document.getElementById("toMonth1").value;
    var fromYear=    document.getElementById("fromYear1").value;
    var toYear=    document.getElementById("toYear1").value;
    // var practiceName= document.getElementById("practiceName").value;
    
    //document.getElementById("loadMessage").style.display="block";
    if(fromMonth==""){
        alert("Please select from month");
        return false;
    }
    if(toMonth==""){
        alert("Please select to month");
        return false;
    }
    if(fromYear=="" || isNaN(fromYear)){
        alert("Enter valid year");
        document.getElementById("fromYear1").value="";
        return false;
        
    }
    if(toYear=="" || isNaN(toYear)){
        alert("Enter valid year");
        document.getElementById("toYear1").value="";
        return false;
        
    }
    
 document.getElementById("tableHeading").style.display='none';
 document.getElementById("tblloadMessage").style.display='block';
    
    clearTable1(document.getElementById("tblrequirements"));
    clearTable1(document.getElementById("tblopportunities"));
     clearTable1(document.getElementById("tblgreenSheet"));
    //  document.getElementById("pastloadingMessage").style.display='block';
    var req = newXMLHttpRequest();
    req.onreadystatechange = loadActMessageASh(req, requirementDetailsByPracticeResponse);
   
    // alert("year-->"+departmentId);
    // alert("month-->"+empnameById);
    //loadXMLDoc( CONTENXT_PATH+'/getEmployeesForPerformers.action?departmentId='+departmentId+'&empnameById='+empnameById+'&empNo='+empNo,wagesHistoryResponse);           
    var url = CONTENXT_PATH+"/requirementDetailsByPractice.action?fromMonth="+fromMonth+"&fromYear="+fromYear+"&toMonth="+toMonth+"&toYear="+toYear+"&practiceName="+practiceName;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);  

}
function requirementDetailsByPracticeResponse(resText) {
    if(resText.length !=0 && resText!="addto0"){
        
        document.getElementById("tableHeading").innerHTML="Requirement Details";
       document.getElementById("tableHeading").style.display='block';
       document.getElementById("noteLable").style.display='block';
        //document.getElementById("noteLable").innerHTML='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Note :The details are order by CreatedBy,Account Name and Created Date.';
        document.getElementById("noteLable").innerHTML='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Note :The details are order by CreatedBy ascending,Account Name ascending and Created Date descending.';
        var oTable = document.getElementById("tblrequirements");
        // var headerFields = new Array("S.No","Employee Name","Reports To","Start Date(YY-MM-DD)","End Date(YY-MM-DD)","Leaves")
        //var headerFields = new Array("S.No","Employee Name","Reports To","Start Date(YY-MM-DD)","End Date(YY-MM-DD)","Status","Leaves")
        //var headerFields = new Array("S.No","ReviewType","ReviewTitle","Status","EmpComments","Tl&nbsp;Rating","Tl&nbsp;Comments","Hr&nbsp;Rating","Hr&nbsp;Comments","ReviewDate")
        var headerFields = new Array("S.No","CreatedBy","Account&nbsp;Name","Title","Status","CreatedDate","ClosedDate")
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
        generateTableHeader(tbody,headerFields);
        var resTextSplit1 = resText.split("*@!");
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#^$");
             generateRow(oTable,tbody,resTextSplit2,index);
        }
        generateFooter(tbody,oTable);
    }else {
        alert("No Records Found");
        
    }
    document.getElementById("tblloadMessage").style.display='none';
    
}
function greenSheetDetailsByPractice(poType){    
    var fromMonth=    document.getElementById("fromMonth1").value;
    var toMonth=    document.getElementById("toMonth1").value;
    var fromYear=    document.getElementById("fromYear1").value;
    var toYear=    document.getElementById("toYear1").value;
    // var practiceName= document.getElementById("practiceName").value;
    
    //document.getElementById("loadMessage").style.display="block";
    if(fromMonth==""){
        alert("Please select from month");
        return false;
    }
    if(toMonth==""){
        alert("Please select to month");
        return false;
    }
    if(fromYear=="" || isNaN(fromYear)){
        alert("Enter valid year");
        document.getElementById("fromYear1").value="";
        return false;
        
    }
    if(toYear=="" || isNaN(toYear)){
        alert("Enter valid year");
        document.getElementById("toYear1").value="";
        return false;
        
    }
    
 document.getElementById("tableHeading").style.display='none';
 document.getElementById("tblloadMessage").style.display='block';
    
    clearTable1(document.getElementById("tblrequirements"));
    clearTable1(document.getElementById("tblopportunities"));
    clearTable1(document.getElementById("tblgreenSheet"));
    //  document.getElementById("pastloadingMessage").style.display='block';
    var req = newXMLHttpRequest();
    req.onreadystatechange = loadActMessageASh(req, greenSheetDetailsByPracticeResponse);
   
    // alert("year-->"+departmentId);
    // alert("month-->"+empnameById);
    //loadXMLDoc( CONTENXT_PATH+'/getEmployeesForPerformers.action?departmentId='+departmentId+'&empnameById='+empnameById+'&empNo='+empNo,wagesHistoryResponse);           
    var url = CONTENXT_PATH+"/greenSheetDetailsByPractice.action?fromMonth="+fromMonth+"&fromYear="+fromYear+"&toMonth="+toMonth+"&toYear="+toYear+"&poType="+poType;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);  

}
function greenSheetDetailsByPracticeResponse(resText) {
    if(resText.length !=0 && resText!="addto0"){
        
        document.getElementById("tableHeading").innerHTML="GreenSheet Details";
       document.getElementById("tableHeading").style.display='block';
       document.getElementById("noteLable").style.display='block';
       // document.getElementById("noteLable").innerHTML='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Note :The details are order by CreatedBy,Account Name and Created Date.';
       document.getElementById("noteLable").innerHTML='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Note :The details are order by CreatedBy ascending,Account Name ascending and Created Date ascending.';
        var oTable = document.getElementById("tblgreenSheet");
        // var headerFields = new Array("S.No","Employee Name","Reports To","Start Date(YY-MM-DD)","End Date(YY-MM-DD)","Leaves")
        //var headerFields = new Array("S.No","Employee Name","Reports To","Start Date(YY-MM-DD)","End Date(YY-MM-DD)","Status","Leaves")
        //var headerFields = new Array("S.No","ReviewType","ReviewTitle","Status","EmpComments","Tl&nbsp;Rating","Tl&nbsp;Comments","Hr&nbsp;Rating","Hr&nbsp;Comments","ReviewDate")
        //var headerFields = new Array("S.No","CreatedBy","Status","Account&nbsp;Name","Consultant&nbsp;Name","Rate($)","CreatedDate")
        var headerFields = new Array("S.No","CreatedBy","Status","PO&nbsp;Type","Account&nbsp;Name","Consultant&nbsp;Name","Rate($)","CreatedDate");
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
        generateTableHeader(tbody,headerFields);
        var response=resText.split("addto0")
        var resTextSplit1 = response[0].split("*@!");
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#^$");
            generateRow(oTable,tbody,resTextSplit2,index);
        }
        generateFooter(tbody,oTable);
         document.getElementById(("footer"+oTable.id)).innerHTML = "Total Rate: $ "+moneyFormat(response[1]); 
    }else {
        alert("No Records Found");
        
    }
    document.getElementById("tblloadMessage").style.display='none';
    
}
function requirementDetailsByStatus(status){    
    var accountId = document.getElementById("accountId").value;
    var fromMonth=    document.getElementById("fromMonth").value;
    var toMonth=    document.getElementById("toMonth").value;
    var fromYear=    document.getElementById("fromYear").value;
    var toYear=    document.getElementById("toYear").value;
    var accountName= document.getElementById("accountName").value;
    
    if(fromMonth==""){
        alert("Please select from month");
        return false;
    }
    if(toMonth==""){
        alert("Please select to month");
        return false;
    }
    if(fromYear=="" || isNaN(fromYear)){
        alert("Enter valid year");
        document.getElementById("fromYear").value="";
        return false;
        
    }
    if(toYear=="" || isNaN(toYear)){
        alert("Enter valid year");
        document.getElementById("toYear").value="";
        return false;
        
    } 
    if(accountName==""){
        accountId=0;
    }
    
 document.getElementById("tableHeading1").style.display='none';
 document.getElementById("tblloadMessage1").style.display='block';
    
    clearTable1(document.getElementById("tblrequirements1"));
    
    //  document.getElementById("pastloadingMessage").style.display='block';
    var req = newXMLHttpRequest();
    req.onreadystatechange = loadActMessageASh(req, requirementDetailsByStatusResponse);
   
    // alert("year-->"+departmentId);
    // alert("month-->"+empnameById);
    //loadXMLDoc( CONTENXT_PATH+'/getEmployeesForPerformers.action?departmentId='+departmentId+'&empnameById='+empnameById+'&empNo='+empNo,wagesHistoryResponse);           
    var url = CONTENXT_PATH+"/requirementDetailsByStatus.action?fromMonth="+fromMonth+"&fromYear="+fromYear+"&toMonth="+toMonth+"&toYear="+toYear+"&status="+status+"&accountId="+accountId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);  

}
function requirementDetailsByStatusResponse(resText) {
    if(resText.length !=0 && resText!="addto0"){
        
        document.getElementById("tableHeading1").innerHTML="Requirement Details";
       document.getElementById("tableHeading1").style.display='block';
       document.getElementById("noteLable1").style.display='block';
        document.getElementById("noteLable1").innerHTML='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Note :The details are order by CreatedBy ascending,Account Name ascending and Created Date descending.';
        var oTable = document.getElementById("tblrequirements1");
        // var headerFields = new Array("S.No","Employee Name","Reports To","Start Date(YY-MM-DD)","End Date(YY-MM-DD)","Leaves")
        //var headerFields = new Array("S.No","Employee Name","Reports To","Start Date(YY-MM-DD)","End Date(YY-MM-DD)","Status","Leaves")
        //var headerFields = new Array("S.No","ReviewType","ReviewTitle","Status","EmpComments","Tl&nbsp;Rating","Tl&nbsp;Comments","Hr&nbsp;Rating","Hr&nbsp;Comments","ReviewDate")
        var headerFields = new Array("S.No","CreatedBy","Account&nbsp;Name","Title","Practice","CreatedDate","ClosedDate")
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
        generateTableHeader(tbody,headerFields);
        var resTextSplit1 = resText.split("*@!");
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#^$");
            generateRow(oTable,tbody,resTextSplit2,index);
        }
        generateFooter(tbody,oTable);
    }else {
        alert("No Records Found");
        
    }
    document.getElementById("tblloadMessage1").style.display='none';
    
}



function getMyProjectsByCustomer(accountId){
 
   // var accountId = document.getElementById("accountId").value;
 //alert("accountId "+accountId);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateMyProjects);
    var url = CONTENXT_PATH+"/getAllProjectsByAccountId.action?accountId="+accountId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}


function populateMyProjects(resXML) {
  
    //var projects = document.getElementById("projectName");
    var projects = document.getElementById("projectId");
    
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

