/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




function load(){    
    var year = document.getElementById("year").value;
    var month = document.getElementById("month").value;
   var difference = document.getElementById("difference").value;
   var orgId = document.getElementById("organizationId").value;
   
     (document.getElementById("colorDiff")).style.display="none";
    (document.getElementById("pagCheck")).style.display = "none";
    $('span.pagination').empty().remove();
  if(year == "" || month == ""){
    //  alert("Select Year and Month");
      x0p( '','Select Year and Month','info');
  }
   else{
  
    // alert("year-->"+year);
    // alert("month-->"+month);
    loadXMLDoc(CONTENXT_PATH+'/ajaxHandle/getPayrollCheck.action?year='+year+'&month='+month+'&difference='+difference+'&organizationId='+orgId,CBFunc_payrollCheckList);           
   
   }
}

function CBFunc_payrollCheckList() {
    var responseText;
    var  myHTMLTable = document.getElementById("tblUpdate");
    var  spnFast = document.getElementById("spnFast");
    
    // var  myHeadTable = document.getElementById("tableHead");
    
    /*Cleaning Rows*/
    ClrTable(myHTMLTable);
    document.getElementById("resultmsg").style.display="none";
   
    
    if (req.readyState == 4) {
        if (req.status == 200) {
            (document.getElementById("loadingMessage")).style.display = "none";
           
            var headerFields = new Array("SNo","EmpNo","EmpName","PayRollDate","NetPaid","LastPayRollDate","LastNetPaid","Difference");			
            
            var getResponseData;
            getResponseData = req.responseText;
           // alert('getResponseData******'+getResponseData);
            var temp = new Array();
            temp = getResponseData.split('addto');
            
            // alert('temp[1]>>> '+temp[1]);
            
            if(req.responseText!=''){
                // document.frmAddGreenSheet.inputRowData.style.display="block";
                // document.frmAddGreenSheet.inputRowData.value= "Records Found "+temp[1];
                 (document.getElementById("pagCheck")).style.display = "block"; 
                  
                ParseAndGenerateHTML(myHTMLTable,temp[0], headerFields,temp[1]);
                 var tbl =  myHTMLTable;
                 var lastRow = tbl.rows.length;
              //   alert(lastRow)
               document.getElementById("total").innerHTML = lastRow;
                 (document.getElementById("colorDiff")).style.display="block";
                   
               
              
               
            // myHeadTable.innerHTML = "Total Rows"+temp[1];
            }else{
              //  alert('No Result For This Search...');
                x0p( '','No Result For This Search...','info');
            //spnFast.innerHTML="No Result For This Search...";                
            }
        } 
        else {
           // alert("Please enter a valid zip code:\n" + req.statusText);
            x0p( '','Please enter a valid zip code:\n' + req.statusText,'info');
        }
    }else {
        (document.getElementById("loadingMessage")).style.display = "block";
    }
    pagerOptionCheck();
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


function ParseAndGenerateHTML(oTable,responseString,headerFields,rowCount) {
    var start = new Date();
    var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";   
    
    //alert('responseString%%%% ******'+responseString);
    //alert('rowCount%%%% ******'+rowCount);
    var records = responseString.split(recordDelimiter); 
    generateTable(oTable,headerFields,records,fieldDelimiter);
    
}


function loadXMLDoc(url,callback) {
    //alert("URL: " + url);
    // branch for native XMLHttpRequest object
    if (window.XMLHttpRequest) {
        req = new XMLHttpRequest();
        req.onreadystatechange = callback;
        req.open("GET", url, true);
        req.send(null);
    } 
    // branch for IE/Windows ActiveX version
    else if (window.ActiveXObject) {
        isIE = true;
        req = new ActiveXObject("Microsoft.XMLHTTP");
        if (req) {
            req.onreadystatechange = callback;
            req.open("GET", url, true);
            req.send(null);
        }
    }
}

function convertGridDate(str) {
    // yyyy-mm-dd 
    // mm-dd-yyyy      
    var yyyy = str.substring(0,4);    
    var mn = str.substring(5,7);    
    var dt = str.substring(8,10);        
    var sqlDate = mn+"-"+dt+"-"+yyyy;       
    return sqlDate;   
}

//====================================================================================
//	This Function generates the HTML <TR> and <TD> for a Set of Fields in a Record
//====================================================================================
var itemNum;
var fields = '';

var name;
var convertDate;



function generateRow(tableBody,record,delimiter) {
    var row;
    var cell;
    var difference = document.getElementById("difference").value;
   // alert(difference)
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    
    var fields = record.split(delimiter);
    for (var i=0;i<fields.length;i++) {
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        row.appendChild( cell );
   
   //alert(fields[7])
        if(parseFloat(fields[8])<10 ){
          
        }
        // row.className="gridRowEvenRed";
        if(parseFloat(fields[8])>=20) {
            row.className="gridRowEvenRed";
        }
        if((parseFloat(fields[8])>=10 && parseFloat(fields[8])<=19))
            {
                  row.className="gridRowEvenBlue";
            }
           if(parseFloat(fields[8]) < 0 ){
              
                 row.className="gridRowEvenGreen";
            }
            if(i!=8){
        cell.innerHTML = fields[i]; 
          }
      
    }
  
   //  document.getElementById("total").value=fields[0];
    
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
        cell.width = 120;
    }
}

function generateTable(oTable, headerFields,records,fieldDelimiter) {	    
    
    var tbody = oTable.childNodes[0];    
    // oTable.outerHTML = str;
    
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    if(records.length >=1 && records!=""){
        generateTableHeader(tbody,headerFields);
    } else {
        document.getElementById("resultmsg").style.display="block";
    }   
    
    
    for(var i=0;i<records.length-1;i++) {
        generateRow(tbody,records[i],fieldDelimiter);               
    }    
    if(records.length >=1 && records!=""){
        var footer =document.createElement("TR");
        footer.className="gridPager";
        tbody.appendChild(footer);
        cell = document.createElement("TD");
        cell.colSpan = "8";
        cell.className="gridFooter";
        footer.appendChild(cell);
    }
    
}


var empnameById="";
var wages=0;
function getPayRollHistory(){    
    
    var empnameById = document.getElementById("empNameByIdTef").value;
    var empNo = document.getElementById("payRollHistoryempNo").value;
    (document.getElementById("pagHistory")).style.display = "none";
    $('span.pagination').empty().remove();
   
    if(empNo==""){
            
       
        if(empnameById==""){
           // alert("Select EmployeeName or Enter Employee Number");
            x0p( '','Select EmployeeName or Enter Employee Number','info');
            return false;
        }
    }
    wages=0;
    // alert("year-->"+year);
    // alert("month-->"+month);
    loadXMLDoc(CONTENXT_PATH+'/ajaxHandle/getPayrollHistory.action?empnameById='+empnameById+'&empNo='+empNo,payRollHistoryResponse);           
   

}
function payRollHistoryResponse(){
    var responseText;
    var  myHTMLTable = document.getElementById("tblpayrollHistoryUpdate");
    var  spnFast = document.getElementById("spnFast");
    
    // var  myHeadTable = document.getElementById("tableHead");
    
    /*Cleaning Rows*/
    ClrTable(myHTMLTable);
    document.getElementById("payrollHistoryresultmsg").style.display="none";
    
    if (req.readyState == 4) {
        if (req.status == 200) {
            (document.getElementById("payrollHistoryloadingMessage")).style.display = "none";
            
            var headerFields = new Array("EmpName","Modified By","Modified Date ","Details");			
            
            var getResponseData;
            getResponseData = req.responseText;
            // alert(getResponseData);
          
            //alert('getResponseData******'+getResponseData);
            
            // alert('temp[1]>>> '+temp[1]);
            
            if(req.responseText!=''){
                // document.frmAddGreenSheet.inputRowData.style.display="block";
                // document.frmAddGreenSheet.inputRowData.value= "Records Found "+temp[1];
                ParseAndGenerateHTM(myHTMLTable,getResponseData, headerFields);
                 (document.getElementById("pagHistory")).style.display = "block";
            // myHeadTable.innerHTML = "Total Rows"+temp[1];
            }else{
            //    alert('No Result For This Search...');
                x0p( '','No Result For This Search...','info');
            //spnFast.innerHTML="No Result For This Search...";                
            }
        
        } 
        else{
          //  alert("Please enter a valid zip code:\n" + req.statusText);
            x0p( '','Please enter a valid zip code:\n' + req.statusText,'info');
        }
    }else {
        (document.getElementById("payrollHistoryloadingMessage")).style.display = "block";
    }
    pagerOptionHistory();
}

function getWagesHistory(){    
  
    var empnameById = document.getElementById("empNameById").value;
    var empNo = document.getElementById("wagesHistoryempNo").value;
      (document.getElementById("pagWages")).style.display = "none";
    $('span.pagination').empty().remove();
    if(empNo==""){
          
        if(empnameById==""){
         //   alert("Select EmployeeName or Enter Employee Number");
            x0p( '','Select EmployeeName or Enter Employee Number','info');
            return false;
        }
       
    }
    wages=1;
    // alert("year-->"+departmentId);
    // alert("month-->"+empnameById);
    loadXMLDoc(CONTENXT_PATH+'/ajaxHandle/getWagesHistory.action?empnameById='+empnameById+'&empNo='+empNo,wagesHistoryResponse);           
   

}
function wagesHistoryResponse(){
    var responseText;
    var  myHTMLTable = document.getElementById("tblWagesHistoryUpdate");
    var  spnFast = document.getElementById("spnFast");
    
    // var  myHeadTable = document.getElementById("tableHead");
    
    /*Cleaning Rows*/
    ClrTable(myHTMLTable);
    document.getElementById("wagesHistoryresultmsg").style.display="none";
    
    if (req.readyState == 4) {
        if (req.status == 200) {
            (document.getElementById("wagesHistoryloadingMessage")).style.display = "none";
            
            var headerFields = new Array("EmpName","Modified By","Modified Date ","Details");
            
            var getResponseData;
            getResponseData = req.responseText;
            // alert(getResponseData);
          
            //alert('getResponseData******'+getResponseData);
            var temp = new Array();
            temp = getResponseData.split('addto');
            
            // alert('temp[1]>>> '+temp[1]);
            
            if(req.responseText!=''){
                // document.frmAddGreenSheet.inputRowData.style.display="block";
                // document.frmAddGreenSheet.inputRowData.value= "Records Found "+temp[1];
                ParseAndGenerateHTM(myHTMLTable,getResponseData, headerFields);
                 (document.getElementById("pagWages")).style.display = "block";
            // myHeadTable.innerHTML = "Total Rows"+temp[1];
            }else{
               // alert('No Result For This Search...');
                x0p( '','No Result For This Search...','info');
            //spnFast.innerHTML="No Result For This Search...";                
            }
        
        } 
        else {
          //  alert("Please enter a valid zip code:\n" + req.statusText);
              x0p( '','Please enter a valid zip code:\n'+ req.statusText,'info'); 
        }
    }else {
        (document.getElementById("wagesHistoryloadingMessage")).style.display = "block";
    }
    pagerOptionWages();
}


function getEmployeeByDept(departmentId,p_empnameById){
    //   var deptName = document.employeeForm.departmentId.value;
    empnameById=p_empnameById;
    var deptName = document.getElementById(departmentId).value;
    var req = newXMLHttpRequest();
    //alert("haiii="+deptName);
    req.onreadystatechange = readyStateHandlerXml(req, populateEmployeeByDept);
    // var url = CONTENXT_PATH+"/getEmpForReportsTo.action?deptName="+deptName;
    var url = CONTENXT_PATH+"/getEmployeesByDept.action?departmentId="+deptName;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}

function populateEmployeeByDept(resXML) {
    //   var reportsTo = document.employeeForm.reportsTo;
    //alert("hai");
    var empId = document.getElementById(empnameById);
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
function ParseAndGenerateHTM(oTable,responseString,headerFields) {
    var start = new Date();
    var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";   
    //alert('responseString%%%% ******'+responseString);
    //alert('rowCount%%%% ******'+rowCount);
    var records = responseString.split(recordDelimiter); 
    generateMyTable(oTable,headerFields,records,fieldDelimiter);
    
}
function generateMyTable(oTable, headerFields,records,fieldDelimiter) {	    
    
    var tbody = oTable.childNodes[0];    
    // oTable.outerHTML = str;
    
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    if(records.length >1 && records!=""){
       
        generateTableHeader(tbody,headerFields);
    } else {
        document.getElementById("resultmsg").style.display="block";
    }   
    
    
    for(var i=0;i<records.length-1;i++) {
         if(oTable.id=="tblUpdateRepayment"){
        generateMyRowRepayment(tbody,records[i],fieldDelimiter);
    }else{
        generateMyRow(tbody,records[i],fieldDelimiter);   
    }
    }    
    if(records.length >=1 && records!=""){
        var footer =document.createElement("TR");
        footer.className="gridPager";
        tbody.appendChild(footer);
        cell = document.createElement("TD");
        //alert("test"+records.length);
        cell.colSpan = records.length+1;
        if(oTable.id=="tblUpdateRepayment"){
             cell.colSpan = "7";
        }
        cell.className="gridFooter";
        footer.appendChild(cell);
    }
    
}
function generateMyRow(tableBody,record,delimiter) {
    var row;
    var cell;
    
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    
    var fields = record.split(delimiter);
   // alert(fields[1]+"fields"+fields[0]);
    // alert(fields.length);
    
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    row.appendChild( cell );
    cell.innerHTML = fields[0];        
        
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    row.appendChild( cell );
    cell.innerHTML = fields[1];  
        
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    row.appendChild( cell );
    cell.innerHTML = fields[2]; 
        
    cell = document.createElement( "TD" );
    var j = document.createElement("a");
    if(wages==0){      
    
        j.setAttribute("href", "javascript:togglePayRollHistoryRequirement('"+fields[3]+"','"+fields[2]+"')");
    }else{
        j.setAttribute("href", "javascript:toggleWagesHistoryRequirement('"+fields[3]+"','"+fields[2]+"')");
    }
    j.appendChild(document.createTextNode("Details"));
    cell.appendChild(j);
    cell.className="gridColumn";
    row.appendChild( cell );
    
}
function generateMyRowRepayment(tableBody,record,delimiter) {
    var row;
    var cell;
    
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    
    var fields = record.split(delimiter);
    //alert("fields"+fields);
    // alert(fields.length);
    
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    cell.setAttribute("width","20%");
    cell.setAttribute("align", "center");
    row.appendChild( cell );
    cell.innerHTML = fields[0];        
        
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    cell.setAttribute("width","20%");
    row.appendChild( cell );
    cell.innerHTML = fields[1];  
        
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    cell.setAttribute("width","20%");
    row.appendChild( cell );
    cell.innerHTML = fields[2]; 
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    cell.setAttribute("width","20%");
    row.appendChild( cell );
    cell.innerHTML = fields[3];
    cell.setAttribute("width","20%");
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    row.appendChild( cell );
    cell.innerHTML = fields[4]; 
    cell.setAttribute("width","20%");
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    row.appendChild( cell );
    cell.innerHTML = fields[5]; 
        
    cell = document.createElement( "TD" );
    var j = document.createElement("a");
       j.setAttribute("href", "javascript:getRepaymentReason('"+fields[6]+"')");

    j.appendChild(document.createTextNode("Click To View"));
    cell.appendChild(j);
    cell.className="gridColumn";
    row.appendChild( cell );
    
}

function togglePayRollHistoryRequirement(empId,modifiedDate){
    // alert("empId"+empId);
    var overlay = document.getElementById('payRollHistoryGridOverlay');
    var specialBox = document.getElementById('payRollHistoryGridOverlayMain');
    // alert(empId)   
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        //alert("in block");
        overlay.style.display = "none";
        specialBox.style.display = "none";
        
     
    } else {
        fillPayRollHistory(empId,modifiedDate);
    // alert("else block");
       
    //         overlay.style.display = "block";
    //        specialBox.style.display = "block";
    //      
    }   
    
}
function fillPayRollHistory(empId,modifiedDate){
   // alert("empId"+empId);
    loadXMLDoc(CONTENXT_PATH+'/ajaxHandle/getPayRollHistoryEmployeeDetails.action?empId='+empId+'&modifiedDate='+modifiedDate,fillPayRollHistoryResponse);           
   
}
function fillPayRollHistoryResponse(){
    var getResponseData;
    getResponseData = req.responseText;
    // alert("response"+getResponseData);
    if(getResponseData!=""){
        var data=getResponseData.split("#^$");
        
        //alert("length="+data.length);
        document.getElementById("employeeName").innerHTML=data[0];
        document.getElementById("orgId").value=data[1];
        document.getElementById("departmentId").value=data[2];
        document.getElementById("titleId").value=data[3];
        document.getElementById("shiftId").value=data[4];
        document.getElementById("classificationId").value=data[5];
        document.getElementById("locationId").value=data[6];
        document.getElementById("gender").value=data[8];
        document.getElementById("currStatus").value=data[9];
           
        if(data[10]=="1"){
            document.getElementById("isPfFlag").checked=true;
        }
        else{
            document.getElementById("isPfFlag").checked=false;
        }
            
            
        document.getElementById("employerId").value=data[11];
        document.getElementById("ssn").value=data[12];
        document.getElementById("passportNo").value=data[13];
        document.getElementById("pfAccount").value=data[14];
        document.getElementById("trainingPeriod").value=data[15];
        document.getElementById("contractPeriod").value=data[16];
        document.getElementById("dateOfJoining").value=data[17];
        document.getElementById("UANNo").value=data[18];
        document.getElementById("adharNo").value=data[19];
        document.getElementById("resonsForLeaving").value=data[20];
            
        //contact details
        document.getElementById("address").value=data[21];
        document.getElementById("corporateEmail").value=data[22];
        document.getElementById("personalEmail").value=data[23];
        document.getElementById("fathername").value=data[24];
        document.getElementById("fatherTitle").value=data[25];
        document.getElementById("city").value=data[26];
        document.getElementById("state").value=data[27];
        document.getElementById("zip").value=data[28];
        document.getElementById("fatherPhone").value=data[29];
        document.getElementById("homePhone").value=data[30];
        document.getElementById("mobilePhone").value=data[31];
        document.getElementById("workPhone").value=data[32];

        //payroll details
        document.getElementById("basic").value=data[33];
        document.getElementById("paymentType").value=data[34];
        document.getElementById("da").value=data[35];
        document.getElementById("bankName").value=data[36];
        document.getElementById("hra").value=data[37];
        document.getElementById("bankAccountNo").value=data[38];
        document.getElementById("ta").value=data[39];
        document.getElementById("employerPf").value=data[40];
        document.getElementById("ra").value=data[41];
        document.getElementById("employeePf").value=data[42];
        document.getElementById("entertainment").value=data[43];
        document.getElementById("employeresi").value=data[44];
        document.getElementById("kidsEducation").value=data[45];
        document.getElementById("employeeesi").value=data[46];
        document.getElementById("vehicleAllowance").value=data[47];
        document.getElementById("cca").value=data[48];
        document.getElementById("life").value=data[49];
        document.getElementById("miscPay").value=data[50];
        document.getElementById("health").value=data[51];
        document.getElementById("splAllowance").value=data[52];
        document.getElementById("professionalTax").value=data[53];
        document.getElementById("longTermBonus").value=data[54];
        document.getElementById("otherDeductions").value=data[55];
        document.getElementById("projectPay").value=data[56];
        document.getElementById("grossPay").value=data[57];
        document.getElementById("attendanceAllow").value=data[58];
        document.getElementById("variablePay").value=data[59];
        if(data[60]=="1"){
            document.getElementById("onProjectInd").checked=true;
        }
        else{
            document.getElementById("onProjectInd").checked=false;
        }
        

        document.getElementById("onProjectIndValue1").value=data[61];
        document.getElementById("onProjectIndValue2").value=data[62];

        document.getElementById("totalCost").value=data[63];

        if(data[64]=="1"){
            document.getElementById("onsiteInd").checked=true;
        }
        else{
            document.getElementById("onsiteInd").checked=false;
        }
       
        document.getElementById("onsiteIndV").value=data[65];

        document.getElementById("datePayRevised").value=data[66];

        //insurence details

        // document.getElementById("prevYtdSalary").value=data[67];
        document.getElementById("empSaving1").value=data[68];
        document.getElementById("empSaving2").value=data[69];
        //  document.getElementById("tutionfees").value=data[70];
        //        document.getElementById("hbLoanInterst").value=data[71];
        //        document.getElementById("ppf").value=data[72];
        //        document.getElementById("medicalIns").value=data[73];
        //        document.getElementById("lifeIns").value=data[74];
        //        document.getElementById("hraLifeInsuranceSavings").value=data[75];
        //        document.getElementById("premium").value=data[76];
        //        document.getElementById("eduInterest").value=data[77];
        //        document.getElementById("fd").value=data[78];
        //        document.getElementById("hbLoanPrinciple").value=data[79];
        //        document.getElementById("mutualFunds").value=data[80];
        //        document.getElementById("nsc").value=data[81];
        //        
        //        //other details
        //        document.getElementById("wagecomments").value=data[82];
        //        document.getElementById("wagecomments1").value=data[83];
        //        document.getElementById("generalcomments").value=data[84];
        //        document.getElementById("referencecomments").value=data[85];
        //

        //TDS
        document.getElementById("expectedYearlyCost").value=data[86];
        document.getElementById("lifeInsurancePremium").value=data[87];
        document.getElementById("housingLoanRepay").value=data[88];
        document.getElementById("nscTds").value=data[89];
        document.getElementById("ppfContribution").value=data[90];
        document.getElementById("tutionFee").value=data[91];
        document.getElementById("elss").value=data[92];
        document.getElementById("postOfficeTerm").value=data[93];
        document.getElementById("bankDepositTax").value=data[94];
        document.getElementById("licFromSal").value=data[95];
        document.getElementById("othersTDS").value=data[96];
        document.getElementById("contributionToPf").value=data[97];
        document.getElementById("npsEmployeeContr").value=data[98];
        document.getElementById("totalTds").value=data[99];
        document.getElementById("totalTdsDeductable").value=data[100];
        document.getElementById("interstOnBorrowed").value=data[101];
        document.getElementById("interstOnBorrowedDeductable").value=data[102];
        document.getElementById("insuranceForParents").value=data[103];
        document.getElementById("insuranceForParentsDeduc").value=data[104];
        document.getElementById("insuranceOthers").value=data[105];
        document.getElementById("insuranceOthersDeduc").value=data[106];
        document.getElementById("interstOnEdu").value=data[107];
        document.getElementById("interstOnHrAssumptionsInv").value=data[108];
        document.getElementById("interstOnHrAssumptions").value=data[109];
        document.getElementById("expectedYearlyCost").value=data[110];
        document.getElementById("empSaving3").value=data[111];
        document.getElementById("empSaving4").value=data[112];
        document.getElementById("empSaving5").value=data[113];
         document.getElementById("practiceId").value=data[114];
        document.getElementById("payrollHistoryloadingMessage").style.display = "none";
        document.getElementById('payRollHistoryGridOverlay').style.display = "block";
        document.getElementById('payRollHistoryGridOverlayMain').style.display = "block";
     
    }
    else{
        document.getElementById("payrollHistoryloadingMessage").style.display = "block";
    }
}
function toggleWagesHistoryRequirement(empId,modifiedDate){
    // alert("empId"+empId);
    var overlay = document.getElementById('wagesHistoryGridOverlay');
    var specialBox = document.getElementById('wagesHistoryGridOverlayMain');
        
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        //alert("in block");
        overlay.style.display = "none";
        specialBox.style.display = "none";
        
     
    } else {
        fillWagesHistory(empId,modifiedDate);
    // alert("else block");
       
    //overlay.style.display = "block";
    //specialBox.style.display = "block";
    //      
    }   
    
}
function fillWagesHistory(empId,modifiedDate){
    // alert("empId"+empId);
    loadXMLDoc(CONTENXT_PATH+'/ajaxHandle/getWagesHistoryEmployeeDetails.action?empId='+empId+'&modifiedDate='+modifiedDate,fillWagesHistoryResponse);           
   
}
function fillWagesHistoryResponse(){
    var getResponseData;
    getResponseData = req.responseText;
   
    if(getResponseData!=""){
        
        //alert("response"+getResponseData);
        var data=getResponseData.split("#^$");
        
        //alert("length="+data.length);
        document.getElementById("whemployeeName").value=data[0];
        document.getElementById("whtitleId").value=data[1];
        document.getElementById("whclassificationId").value=data[2];
        document.getElementById("whpaymentType").value=data[3];
        document.getElementById("whpayPeriodStartDate").value=data[4];

        document.getElementById("whbankName").value=data[5];

        document.getElementById("whdaysInMonth").value=data[6];

        document.getElementById("whbankAccountNo").value=data[7];

        document.getElementById("whdaysWorked").value=data[8];
        document.getElementById("whpayrollDate").value=data[9];
        document.getElementById("whpayRunId").value=data[10];
        document.getElementById("whnetPaid").value=data[11];
        document.getElementById("whemployerId").value=data[12];
        document.getElementById("whgrossPay").value=data[13];
        document.getElementById("whdedEmpPf").value=data[14];
        document.getElementById("whtds").value=data[15];
        document.getElementById("whdedProfessionalTax").value=data[16];
        document.getElementById("whdaysProject").value=data[17];
        document.getElementById("whdedIncomeTax").value=data[18];
        document.getElementById("whdaysVacation").value=data[19];
        document.getElementById("whdedCorporateLoan").value=data[20];
        document.getElementById("whvactionsAvailable").value=data[21];
        document.getElementById("whdedLife").value=data[22];
        document.getElementById("whdaysHolidays").value=data[23];
        document.getElementById("whdedHealth").value=data[24];
        document.getElementById("whdaysWeekends").value=data[25];
        document.getElementById("whdedOthers").value=data[26];
        if(data[27]=="1"){
            document.getElementById("whfreezePayroll").checked=true;
        }
        else{
            document.getElementById("whfreezePayroll").checked=false;
        }
        if(data[28]=="1"){
            document.getElementById("whdoRepaymentFlag").checked=true;
        }
        else{
            document.getElementById("whdoRepaymentFlag").checked=false;
        }
        
        
        //payroll details
        document.getElementById("whbasic").value=data[29];
        document.getElementById("whmaidServices").value=data[30];
        document.getElementById("whda").value=data[31];
        document.getElementById("whentertainment").value=data[32];
        document.getElementById("whhra").value=data[33];
        document.getElementById("whkidsEducation").value=data[34];
        document.getElementById("whta").value=data[35];
        document.getElementById("whvehicleAllowance").value=data[36];
        document.getElementById("whra").value=data[37];
        document.getElementById("whlongTermBonus").value=data[38];
        document.getElementById("whlife").value=data[39];
        document.getElementById("whemployerPf").value=data[40];
        document.getElementById("whhealth").value=data[41];
        document.getElementById("whhealth1").value=data[42];
        document.getElementById("whsplAllowance").value=data[43];
        
        document.getElementById("whcca").value=data[44];
        document.getElementById("whattendanceAllow").value=data[45];
        document.getElementById("whmiscPay").value=data[46];
        document.getElementById("whprojectPay").value=data[47];
        document.getElementById("whemployeePfPayRollDetails").value=data[48];
        document.getElementById("whclassificationId1").value=data[49];
        document.getElementById("whgrossPayPayRollDetails").value=data[50];
        document.getElementById("whvariablePay").value=data[51];
        
        document.getElementById("whearnedBasic").value=data[52];
        document.getElementById("whearnedFood").value=data[53];
        document.getElementById("whearnedDa").value=data[54];
        document.getElementById("whearnedLaundary").value=data[55];
        document.getElementById("whearnedHra").value=data[56];
        document.getElementById("whearnedMaidServices").value=data[57];
        document.getElementById("whearnedTa").value=data[58];
        document.getElementById("whearnedEntertainment").value=data[59];
        document.getElementById("whearnedRa").value=data[60];
        document.getElementById("whearnedKidsEducation").value=data[61];
        document.getElementById("whearnedLife").value=data[62];
        document.getElementById("whearnedVehicleAllowance").value=data[63];
        document.getElementById("whearnedHealth").value=data[64];
        document.getElementById("whearnedLongTermBonus").value=data[65];
        document.getElementById("whearnedCCa").value=data[66];
        document.getElementById("whearnedMiscPay").value=data[67];
        document.getElementById("whearnedProjectPay").value=data[68];
        document.getElementById("whearnedEmployerPf").value=data[69];
        document.getElementById("whearnedattallowance").value=data[70];
        document.getElementById("whearnedsplallowance").value=data[71];
        document.getElementById("whtdsDeduction").value=data[72];
        document.getElementById("whemployeePfActualDetails").value=data[73];
        document.getElementById("whgrossPayActualDetails").value=data[74];
        document.getElementById("whbonusCommission").value=data[75];
        document.getElementById("whnetPaidActualDetails").value=data[76];
        document.getElementById("whotherDeductions").value=data[77];
        document.getElementById("whtaxableIncome").value=data[78];
        document.getElementById("whotherAdditions").value=data[79];
        if(data[80]=="1"){
            document.getElementById("whisBlock").checked=true;
        }
        else{
            document.getElementById("whisBlock").checked=false;
        }
        
        document.getElementById("whprojectEndDate").value=data[81];
        
        //ytd
        
        document.getElementById("whytdGross").value=data[82];
        document.getElementById("whytdTaxableSalary").value=data[83];
        document.getElementById("whytdLongterm").value=data[84];
        document.getElementById("whytdTaxableCommission").value=data[85];
        document.getElementById("whytdPf").value=data[86];
        document.getElementById("whytdTDSonSalary").value=data[87];
        document.getElementById("whytdProffTax").value=data[88];
        document.getElementById("whytdTDSOnCommm").value=data[89];
        document.getElementById("whytdLifeInsurance").value=data[90];
        document.getElementById("whytdTDSCollected").value=data[91];
        document.getElementById("whytdTa").value=data[92];
        document.getElementById("whytdTDSLiabilitiesSalary").value=data[93];
        document.getElementById("whytdRa").value=data[94];
        document.getElementById("whytdTDSLiabilitiesBonus").value=data[95];
        document.getElementById("whytdOthersMisc").value=data[96];
        document.getElementById("whdiffTDSLiabilitiesSalary").value=data[97];
        document.getElementById("whytdExpTaxFree").value=data[98];
        document.getElementById("whdiffTDSLiabilitiesBonus").value=data[99];
        document.getElementById("whytdProjectPay").value=data[100];
        document.getElementById("whytdSavings1Reported").value=data[101];
        document.getElementById("whytdSavings2Reported").value=data[102];
        
        
        //TDS
        
        document.getElementById("whlifeInsurancePremium").value=data[103];
        document.getElementById("whhousingLoanRepay").value=data[104];
        document.getElementById("whnscTds").value=data[105];
        document.getElementById("whppfContribution").value=data[106];
        document.getElementById("whtutionFee").value=data[107];
        document.getElementById("whelss").value=data[108];
        document.getElementById("whpostOfficeTerm").value=data[109];
        document.getElementById("whbankDepositTax").value=data[110];
        document.getElementById("whlicFromSal").value=data[111];
        document.getElementById("whothersTDS").value=data[112];
        document.getElementById("whcontributionToPf").value=data[113];
        document.getElementById("whnpsEmployeeContr").value=data[114];
        document.getElementById("whtotalTds").value=data[115];
        document.getElementById("whtotalTdsDeductable").value=data[116];
        document.getElementById("whinterstOnBorrowed").value=data[117];
        document.getElementById("whinterstOnBorrowedDeductable").value=data[118];
        document.getElementById("whinsuranceForParents").value=data[119];
        document.getElementById("whinsuranceForParentsDeduc").value=data[120];
        document.getElementById("whinsuranceOthers").value=data[121];
        document.getElementById("whinsuranceOthersDeduc").value=data[122];
        document.getElementById("whinterstOnEdu").value=data[123];
        document.getElementById("whinterstOnHrAssumptionsInv").value=data[124];
        document.getElementById("whinterstOnHrAssumptions").value=data[125];
        document.getElementById("whexpectedYearlyCost").value=data[126];
        document.getElementById("whhemployeresi").value=data[127];
        document.getElementById("whhemployeeesi").value=data[128];
        document.getElementById("whearnedEmployeresi").value=data[129];
        document.getElementById("whearnedEmployeeesi").value=data[130];
         
        document.getElementById("whleavesApplied").value=data[131];
        document.getElementById("wagesHistoryloadingMessage").style.display = "none";
        document.getElementById('wagesHistoryGridOverlay').style.display = "block";
        document.getElementById('wagesHistoryGridOverlayMain').style.display = "block";
    }
    else{
        document.getElementById("wagesHistoryloadingMessage").style.display = "block";
        
    }
}
function repaymentDetails(){    
    var year = document.getElementById("yearRepayment").value;
    var month = document.getElementById("monthRepayment").value;
    // alert("year-->"+year);
    // alert("month-->"+month);
    (document.getElementById("pagRepay")).style.display = "none";
    $('span.pagination').empty().remove();
    if(year.trim()==''){
     //   alert("Please enter year!");
        x0p( '','Please enter year!','info');
        return false;
    }
     if(month.trim()==''){
     //   alert("Please select month!");
        x0p( '','Please select month!','info');
        return false;
    }
    document.getElementById("loadingMessageRepament").style.display = "block";
    loadXMLDoc(CONTENXT_PATH+'/ajaxHandle/getRepaymentDetails.action?year='+year+'&month='+month,repaymentDetailsResponse);           
   

}
function repaymentDetailsResponse() {
    
    var  myHTMLTable = document.getElementById("tblUpdateRepayment");
    
    
    /*Cleaning Rows*/
    ClrTable(myHTMLTable);
    document.getElementById("resultmsgRepayment").style.display="none";
    
    if (req.readyState == 4) {
        if (req.status == 200) {
            (document.getElementById("loadingMessageRepament")).style.display = "none";
            
            var headerFields = new Array("SNo", "EmpName","PayRollDate","VariablePay","GrossPay","NetPaid","Reason");			
            
            var getResponseData;
            getResponseData = req.responseText;
            //alert('getResponseData******'+getResponseData);
            var temp = new Array();
            temp = getResponseData.split('addto');
            
            // alert('temp[1]>>> '+temp[1]);
            
            if(req.responseText!='noData'){
                
                ParseAndGenerateHTM(myHTMLTable,getResponseData, headerFields);
            (document.getElementById("pagRepay")).style.display = "block";
            var tbl =  myHTMLTable;
                 var lastRow = tbl.rows.length;
                // alert(lastRow)
               document.getElementById("totalRepay").innerHTML = lastRow;
                 (document.getElementById("totalDivRepay")).style.display = "block";
            }else{
               // alert('No Result For This Search...');
                x0p( '','No Result For This Search...','info');
            //spnFast.innerHTML="No Result For This Search...";                
            }
           
        } 
       
    }else {
        (document.getElementById("loadingMessage")).style.display = "block";
    }
    
}
function getRepaymentReason(wageId){
      loadXMLDoc(CONTENXT_PATH+'/ajaxHandle/getRepaymentReason.action?wageId='+wageId,getRepaymentReasonResponse); 
}
function getRepaymentReasonResponse() {
     var text;
          
     if (req.readyState == 4) {
        if (req.status == 200) {
              text = req.responseText;
        }
     }
     
    var background = "#3E93D4";
    var title = "Repayment Reason";
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
function TdsCalculationReport(){    
    var year = document.getElementById("yearTdsCalculations").value;
    var month = document.getElementById("monthTdsCalculations").value;
    // alert("year-->"+year);
    // alert("month-->"+month);
    if(year.trim()==''){
     //   alert("Please enter year!");
        x0p( '','Please enter year!','info');
        return false;
    }
     if(month.trim()==''){
     //   alert("Please select month!");
        x0p( '','Please select month!','info');
        return false;
    }
    document.getElementById("loadingMessageRepament").style.display = "block";
    loadXMLDoc(CONTENXT_PATH+'/ajaxHandle/getRepaymentDetails.action?year='+year+'&month='+month,repaymentDetailsResponse);           
   

}

function blockedSalaryDetails(){  
    
    var year = document.getElementById("yearBlockedSal").value;
    var month = document.getElementById("monthBlockedSal").value;
    if(year.trim()==''){
    //    alert("Please enter year!");
         x0p( '','Please enter year!','info');
        return false;
    }
     if(month.trim()==''){
     //   alert("Please select month!");
           x0p( '','Please select month!','info');
        return false;
    }
  loadXMLDoc(CONTENXT_PATH+'/ajaxHandle/getBlockedSalDetails.action?year='+year+'&month='+month,BlockedSalDetailsResponse);

}
function BlockedSalDetailsResponse() {
    
    var  myHTMLTable = document.getElementById("tblUpdateBlockedSal");
    
    
    /*Cleaning Rows*/
    ClrTable(myHTMLTable);
    document.getElementById("resultmsgBlockedSal").style.display="none";
    
    if (req.readyState == 4) {
        if (req.status == 200) {
         (document.getElementById("loadingMessageBlockedSal")).style.display = "none";
            
            var headerFields = new Array("SNo", "EmpNo","EmpName","Salary");			
            
            var getResponseData;
            getResponseData = req.responseText;
            //alert('getResponseData******'+getResponseData);
            var temp = new Array();
            temp = getResponseData.split('addto');
            
            // alert('temp[1]>>> '+temp[1]);
            if(getResponseData == "No Records Found" ){
                (document.getElementById("MessageBlockedSal")).style.display = "block";
            }
            else{
            if(req.responseText!='noData'){
                
                ParseAndGenerateHTMLBlock(myHTMLTable,getResponseData, headerFields);
                 (document.getElementById("pagBlock")).style.display = "block";
                 (document.getElementById("MessageBlockedSal")).style.display = "none";
           var tbl =  myHTMLTable;
                 var lastRow = tbl.rows.length;
               //  alert(lastRow)
               document.getElementById("totalBlock").innerHTML = lastRow;
                 (document.getElementById("totalDivBlock")).style.display = "block";
            }else{
              //  alert('No Result For This Search...');
                x0p( '','No Result For This Search...','info');
            //spnFast.innerHTML="No Result For This Search...";                
            }
            
        } 
       
    }else {
        (document.getElementById("loadingMessageBlockedSal")).style.display = "block";
    }
    
}
pagerOptionBlock();
}

function ParseAndGenerateHTMLBlock(oTable,responseString,headerFields) {
    var start = new Date();
    var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";   
    //alert('responseString%%%% ******'+responseString);
    //alert('rowCount%%%% ******'+rowCount);
    var records = responseString.split(recordDelimiter); 
    generateMyTableBlock(oTable,headerFields,records,fieldDelimiter);
 }
function generateMyTableBlock(oTable, headerFields,records,fieldDelimiter) {	    
    
    var tbody = oTable.childNodes[0];    
    // oTable.outerHTML = str;
    //  alert(tbody)
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    if(records.length >1 && records!=""){
       
        generateTableHeader(tbody,headerFields);
    } else {
        document.getElementById("resultmsg").style.display="block";
    }   
    
  
    for(var i=0;i<records.length-1;i++) {
         if(oTable.id=="tblUpdateBlockedSal"){
        generateMyRowBlock(tbody,records[i],fieldDelimiter);
    }
    }    
    if(records.length >=1 && records!=""){
        var footer =document.createElement("TR");
        footer.className="gridPager";
        tbody.appendChild(footer);
        cell = document.createElement("TD");
        //alert("test"+records.length);
        cell.colSpan = records.length+1;
        if(oTable.id=="tblUpdateBlockedSal"){
             cell.colSpan = "7";
        }
        cell.className="gridFooter";
        footer.appendChild(cell);
    }
    
}
function generateMyRowBlock(tableBody,record,delimiter) {
    var row;
    var cell;
    
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    
    var fields = record.split(delimiter);
    //alert("fields"+fields);
    // alert(fields.length);
    
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    cell.setAttribute("align", "center");
    row.appendChild( cell );
    cell.innerHTML = fields[0];        
        
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    row.appendChild( cell );
    cell.innerHTML = fields[1];  
        
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    row.appendChild( cell );
    cell.innerHTML = fields[2]; 
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    row.appendChild( cell );
    cell.innerHTML = fields[3]; 
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
   
   
    row.appendChild( cell );
    
}

function revisedSalaryDetails(){    
   var year = document.getElementById("revisedYear").value;
    var month = document.getElementById("revisedMonth").value;
     if(year.trim()==''){
       // alert("Please enter year!");
        x0p( '','Please enter year!','info');
        return false;
    }
     if(month.trim()==''){
     //   alert("Please select month!");
        x0p( '','Please select month!','info');
        return false;
    }
     (document.getElementById("pag")).style.display = "none";
    $('span.pagination').empty().remove();
     
  loadXMLDoc(CONTENXT_PATH+'/ajaxHandle/getRevisedSalDetails.action?revisedYear='+year+'&revisedMonth='+month,RevisedSalDetailsResponse);
}
function RevisedSalDetailsResponse() {
    
    var  myHTMLTable = document.getElementById("tblUpdateRevisedSal");
    
    
    /*Cleaning Rows*/
    ClrTable(myHTMLTable);
  document.getElementById("resultmsgRevisedSal").style.display="none";
    
    if (req.readyState == 4) {
        if (req.status == 200) {
          (document.getElementById("loadingMessageRevisedSal")).style.display = "none";
            
            var headerFields = new Array("SNo", "EmpNo","EmpName","CurTotalCost","PrevTotalCost","Differance");			
            
            var getResponseData;
            getResponseData = req.responseText;
           // alert('getResponseData******'+getResponseData);
            var temp = new Array();
            temp = getResponseData.split('addto');
            
            // alert('temp[1]>>> '+temp[1]);
            
            if(req.responseText!='noData'){
                 
                ParseAndGenerateHTMLRevised(myHTMLTable,getResponseData, headerFields);
                (document.getElementById("pag")).style.display = "block";
                  var tbl =  myHTMLTable;
                 var lastRow = tbl.rows.length;
                
               document.getElementById("totalRev").innerHTML = lastRow;
                 (document.getElementById("totalDivRev")).style.display = "block";
           
            }else{
             //   alert('No Result For This Search...');
                x0p( '','No Result For This Search...','info');
            //spnFast.innerHTML="No Result For This Search...";                
            }
            
        } 
       
    }else {
        (document.getElementById("loadingMessageRevisedSal")).style.display = "block";
    }
    pagerOptionRev();
    
}

function ParseAndGenerateHTMLRevised(oTable,responseString,headerFields) {
    var start = new Date();
    var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";   
    //alert('responseString%%%% ******'+responseString);
    //alert('rowCount%%%% ******'+rowCount);
    var records = responseString.split(recordDelimiter); 
    generateMyTableRevised(oTable,headerFields,records,fieldDelimiter);
 }
function generateMyTableRevised(oTable, headerFields,records,fieldDelimiter) {	    
    
    var tbody = oTable.childNodes[0];    
    // oTable.outerHTML = str;
    //  alert(tbody)
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    if(records.length >1 && records!=""){
       
        generateTableHeader(tbody,headerFields);
    } else {
        document.getElementById("resultmsg").style.display="block";
    }   
    
  
    for(var i=0;i<records.length-1;i++) {
         if(oTable.id=="tblUpdateRevisedSal"){
        generateMyRowRevised(tbody,records[i],fieldDelimiter);
    }
    }    
    if(records.length >=1 && records!=""){
        var footer =document.createElement("TR");
        footer.className="gridPager";
        tbody.appendChild(footer);
        cell = document.createElement("TD");
        //alert("test"+records.length);
        cell.colSpan = records.length+1;
        if(oTable.id=="tblUpdateRevisedSal"){
             cell.colSpan = "7";
        }
        cell.className="gridFooter";
        footer.appendChild(cell);
    }
    
}


function generateMyRowRevised(tableBody,record,delimiter) {
    var row;
    var cell;
    
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    
    var fields = record.split(delimiter);
    //alert("fields"+fields);
    // alert(fields.length);
    
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    cell.setAttribute("align", "center");
    row.appendChild( cell );
    cell.innerHTML = fields[0];        
        
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    row.appendChild( cell );
    cell.innerHTML = fields[1];  
        
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    row.appendChild( cell );
    cell.innerHTML = fields[2]; 
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    row.appendChild( cell );
    cell.innerHTML = fields[3]; 
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    row.appendChild( cell );
    cell.innerHTML = fields[4]; 
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    row.appendChild( cell );
    cell.innerHTML = fields[5]; 
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
   
   
    row.appendChild( cell );
    
}

function getTdsCalculation(){    
   var year = document.getElementById("yearTdsCalculations").value;
    var month = document.getElementById("monthTdsCalculations").value;
     if(year.trim()==''){
       // alert("Please enter year!");
        x0p( '','Please enter year!','info');
        return false;
    }
     if(month.trim()==''){
       // alert("Please select month!");
        x0p( '','Please select month!','info');
        return false;
    }
     (document.getElementById("pagTdsCalculation")).style.display = "none";
    $('span.pagination').empty().remove();
     
  loadXMLDoc(CONTENXT_PATH+'/ajaxHandle/getTdsCalculation.action?year='+year+'&month='+month,TdsCalculationDetailsResponse);
}
function TdsCalculationDetailsResponse() {
    
    var  myHTMLTable = document.getElementById("tblUpdateTdsCalculation");
    
    
    /*Cleaning Rows*/
    ClrTable(myHTMLTable);
  document.getElementById("resultmsgTdsCalculation").style.display="none";
    
    if (req.readyState == 4) {
        if (req.status == 200) {
          (document.getElementById("loadingMessageTdsCalculation")).style.display = "none";
            
            var headerFields = new Array("SNo", "EmpNo","EmpName","TaxableSalary","TDS");			
            
            var getResponseData;
            getResponseData = req.responseText;
           // alert('getResponseData******'+getResponseData);
            var temp = new Array();
            temp = getResponseData.split('addto');
            
            // alert('temp[1]>>> '+temp[1]);
            
            if(req.responseText!='noData'){
                 
                ParseAndGenerateHTMLTdsCalculation(myHTMLTable,getResponseData, headerFields);
                (document.getElementById("pagTdsCalculation")).style.display = "block";
                
                
             var tbl =  myHTMLTable;
                 var lastRow = tbl.rows.length;
               //  alert(lastRow)
               document.getElementById("totalTDS").innerHTML = lastRow;
               (document.getElementById("totalDiv")).style.display = "block";
            }else{
             //   alert('No Result For This Search...');
                x0p( '','No Result For This Search...','info');
            //spnFast.innerHTML="No Result For This Search...";                
            }
            
        } 
       
    }else {
        (document.getElementById("loadingMessageTdsCalculation")).style.display = "block";
    }
    pagerOptionTdsCalculation();
    
}

function ParseAndGenerateHTMLTdsCalculation(oTable,responseString,headerFields) {
    var start = new Date();
    var fieldDelimiter = "#^$";
    var recordDelimiter = "*@!";   
    //alert('responseString%%%% ******'+responseString);
    //alert('rowCount%%%% ******'+rowCount);
    var records = responseString.split(recordDelimiter); 
    generateMyTableTdsCalculation(oTable,headerFields,records,fieldDelimiter);
 }
function generateMyTableTdsCalculation(oTable, headerFields,records,fieldDelimiter) {	    
    
    var tbody = oTable.childNodes[0];    
    // oTable.outerHTML = str;
    //  alert(tbody)
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    if(records.length >1 && records!=""){
       
        generateTableHeader(tbody,headerFields);
    } else {
        document.getElementById("resultmsg").style.display="block";
    }   
    
  
    for(var i=0;i<records.length-1;i++) {
      
        generateMyRowTdsCalculation(tbody,records[i],fieldDelimiter);
  
    }    
    if(records.length >=1 && records!=""){
        var footer =document.createElement("TR");
        footer.className="gridPager";
        tbody.appendChild(footer);
        cell = document.createElement("TD");
        //alert("test"+records.length);
        cell.colSpan = records.length+1;
        if(oTable.id=="tblUpdateTdsCalculation"){
             cell.colSpan = "7";
        }
        cell.className="gridFooter";
        footer.appendChild(cell);
    }
    
}

function generateMyRowTdsCalculation(tableBody,record,delimiter) {
    var row;
    var cell;
    
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    
    var fields = record.split(delimiter);
   //alert("fields"+fields);
    // alert(fields.length);
    
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    cell.setAttribute("align", "center");
    row.appendChild( cell );
    cell.innerHTML = fields[0];        
        
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    row.appendChild( cell );
    cell.innerHTML = fields[1];  
        
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    row.appendChild( cell );
    cell.innerHTML = fields[2]; 
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    row.appendChild( cell );
    cell.innerHTML = fields[3]; 
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    row.appendChild( cell );
    cell.innerHTML = fields[4]; 
    cell = document.createElement( "TD" );
    cell.className="gridColumn";
    row.appendChild( cell );
    
   
    
}
function getCosilidatedTaxSavings(){    
   var year = document.getElementById("yearCosilidatedTax").value;
    var month = document.getElementById("monthCosilidatedTax").value;
    var orgId = document.getElementById("orgIdForCosilidatedTax").value;
     if(orgId.trim()==''){
       // alert("Please enter year!");
        x0p( '','Please select Organization!','info');
        return false;
    }
     if(year.trim()==''){
       // alert("Please enter year!");
        x0p( '','Please enter year!','info');
        return false;
    }
     if(month.trim()==''){
       // alert("Please select month!");
        x0p( '','Please select month!','info');
        return false;
    }
   
     
  //loadXMLDoc(CONTENXT_PATH+'/ajaxHandle/getTdsCalculation.action?year='+year+'&month='+month,TdsCalculationDetailsResponse);
   window.location = "generateTaxSaving.action?orgId="+orgId+"&year="+year+"&month="+month;
}
