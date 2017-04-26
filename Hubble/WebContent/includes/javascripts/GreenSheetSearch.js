function queryDetailsToggle() {
    if(document.getElementById('queryDetails').style.display == 'none') { 
        document.getElementById('queryDetails').style.display = 'inline'; 
    }
    else {    
        document.getElementById('queryDetails').style.display = 'none'; 
    }
}

var poType;
function checkPoType() {
    
    if(document.getElementById('queryDetails').style.display == 'none') { 
        document.getElementById('queryDetails').style.display = 'inline'; 
    }
    
    if(document.getElementById('greensheetList').style.display == 'inline') { 
        document.getElementById('greensheetList').style.display = 'none'; 
    }
    
    document.getElementById('poStatus').value = "Open";
    
    poType = document.getElementById('poType').value;
    //alert('type is '+poType);
    
    document.getElementById('customerName').value = '';
    document.getElementById('empFname').value = '';
    document.getElementById('empLname').value = '';
    
    document.getElementById('poStartDateFrom').value = '';    
    document.getElementById('poStartDateTo').value = '';  
    document.getElementById('poEndDateFrom').value='';       
    document.getElementById('poEndDateTo').value=''; 
    
    document.getElementById('empStartDateFrom').value = '';
    document.getElementById('empStartDateTo').value = '';
    document.getElementById('empEndDateFrom').value = '';
    document.getElementById('empEndDateTo').value = '';    
    
    defaultDates();
    
    
    //==============================================================
    //  Query for New PO's in the System
    //  Set PO Status: Opne, Enable Date Range (Start and End)
    //==============================================================
    
    if(poType == 'New PO') {
        //alert('hello');
        document.getElementById('customerName').disabled = false;
        document.getElementById('empFname').disabled = true;
        document.getElementById('empLname').disabled = true;
        
        document.getElementById('poStartDateFrom').disabled = false;    
        document.getElementById('poStartDateTo').disabled = false;      
        document.getElementById('poEndDateFrom').disabled = false;    
        document.getElementById('poEndDateTo').disabled = false; 
        
        document.getElementById('empStartDateFrom').disabled = true;
        document.getElementById('empStartDateTo').disabled = true;
        document.getElementById('empEndDateFrom').disabled = true;
        document.getElementById('empEndDateTo').disabled = true;
    }
    
    //==============================================================
    //  Query for Expiring PO's in the System
    //  Set PO Status: Open, Enable Only End Date = Dafault (Current Date + 30)
    //  Get all PO's that are Open and have an PO End Date <= Date Set in END DATE
    //==============================================================
    
    if(poType == 'Expiring PO') {        
        defaultDates();
        //document.frmAddGreenSheet.poStartDateFrom.;
        document.getElementById('customerName').disabled = false;
        document.getElementById('empFname').disabled = true;
        document.getElementById('empLname').disabled = true;
        
        document.getElementById('poStartDateFrom').disabled = true;    
        document.getElementById('poStartDateTo').disabled = true; 
        document.getElementById('poEndDateFrom').disabled = false;    
        document.getElementById('poEndDateTo').disabled = false;
        
        document.getElementById('empStartDateFrom').disabled = true;
        document.getElementById('empStartDateTo').disabled = true;
        document.getElementById('empEndDateFrom').disabled = true;
        document.getElementById('empEndDateTo').disabled = true;
    }
    
    //==============================================================
    //  Query for PO's related to an Account in the System
    //  Set PO Status: Open Enable  PO Start Date Range, PO End Date Range
    //  Enable Account name Data Entry
    //  Get all PO's that are Open and have an PO End Date with in the Range
    //  and PO Start Date with in the Range and Account Name = Name Provided
    //==============================================================
    if(poType == 'PO BY Account') {
        document.getElementById('customerName').disabled = false;
        document.getElementById('empFname').disabled = true;
        document.getElementById('empLname').disabled = true;
        
        document.getElementById('poStartDateFrom').disabled = false;    
        document.getElementById('poStartDateTo').disabled = false; 
        document.getElementById('poEndDateFrom').disabled = false;    
        document.getElementById('poEndDateTo').disabled = false; 
        
        document.getElementById('empStartDateFrom').disabled = true;
        document.getElementById('empStartDateTo').disabled = true;
        document.getElementById('empEndDateFrom').disabled = true;
        document.getElementById('empEndDateTo').disabled = true;        
    }
    
    //==============================================================
    //  Query for Rolled Off Consultants
    //  Enable COnsultant End Date Range = Dafault (Current Date) and (Current Date + 30)
    //  Get all PO's that  have a Employee End date with in the Range Specified
    //==============================================================
    
    if(poType == 'Rolled Off') {
        document.getElementById('customerName').disabled = true;
        document.getElementById('empFname').disabled = true;
        document.getElementById('empLname').disabled = true;
        
        document.getElementById('poStartDateFrom').disabled = true;    
        document.getElementById('poStartDateTo').disabled = true; 
        document.getElementById('poEndDateFrom').disabled = true;    
        document.getElementById('poEndDateTo').disabled = true; 
        
        document.getElementById('empStartDateFrom').disabled = true;
        document.getElementById('empStartDateTo').disabled = true;
        document.getElementById('empEndDateFrom').disabled = false;
        document.getElementById('empEndDateTo').disabled = false;        
    }
    
    
    //==============================================================
    //  Query for Active PO's in the System
    //  Set PO Status: Open, Enable PO End Date Range
    //  Get all PO's that are Open and have an PO End Date <= Date Range Specified
    //==============================================================
    
    if(poType == 'Active PO') {
        document.getElementById('customerName').disabled = false;
        document.getElementById('empFname').disabled = true;
        document.getElementById('empLname').disabled = true;
        
        document.getElementById('poStartDateFrom').disabled = false;    
        document.getElementById('poStartDateTo').disabled = false;      
        document.getElementById('poEndDateFrom').disabled = false;    
        document.getElementById('poEndDateTo').disabled = false; 
        
        document.getElementById('empStartDateFrom').disabled = true;
        document.getElementById('empStartDateTo').disabled = true;
        document.getElementById('empEndDateFrom').disabled = true;
        document.getElementById('empEndDateTo').disabled = true;
    }
    
}

var temp ="0";

function defaultDates(){
    
    poType = document.getElementById('poType').value;
    if(poType == 'Active PO') {
        document.getElementById('customerName').disabled = false;
        document.getElementById('empFname').disabled = true;
        document.getElementById('empLname').disabled = true;
        
        document.getElementById('poStartDateFrom').disabled = false;    
        document.getElementById('poStartDateTo').disabled = false;      
        document.getElementById('poEndDateFrom').disabled = false;    
        document.getElementById('poEndDateTo').disabled = false; 
        
        document.getElementById('empStartDateFrom').disabled = true;
        document.getElementById('empStartDateTo').disabled = true;
        document.getElementById('empEndDateFrom').disabled = true;
        document.getElementById('empEndDateTo').disabled = true;
    }
    
    var currentYear = new Date().getFullYear();    
    var currentMonth = new Date().getMonth() +1;    
    var currentDay = new Date().getDate();
    if(currentDay <10 ){
        currentDay = temp+ currentDay;
    }
    if(currentMonth <10 ){
        currentMonth = temp+ currentMonth;
    }
    // month-date-year
    var firstDayOfMonth = currentMonth + '/' + currentDay + '/' + currentYear;    
    var firstDayOfMonth1 = '01/01/2008';
    
    //alert('satrtup >> '+document.getElementById('poType').value);
    
    if( document.getElementById('poType').value == 'Expiring PO'){
        document.getElementById('poStartDateFrom').value = '';        
        document.getElementById('poEndDateFrom').value=firstDayOfMonth1; 
        document.getElementById('empEndDateFrom').value=''; 
        document.getElementById('empStartDateFrom').value='';
    }else if( document.getElementById('poType').value == 'New PO' || document.getElementById('poType').value == 'PO BY Account'){
        document.getElementById('poStartDateFrom').value=firstDayOfMonth1;    
        document.getElementById('poEndDateFrom').value=firstDayOfMonth1;   
        document.getElementById('empEndDateFrom').value=''; 
        document.getElementById('empStartDateFrom').value='';
    }else if(document.getElementById('poType').value == 'Rolled Off'){
        //document.getElementById('empStartDateFrom').value=firstDayOfMonth1; 
        document.getElementById('empEndDateFrom').value=firstDayOfMonth1; 
        document.getElementById('poStartDateFrom').value='';    
        document.getElementById('poEndDateFrom').value=''; 
    }else if(document.getElementById('poType').value == 'Active PO'){
        document.getElementById('poStartDateFrom').value = firstDayOfMonth1;        
        document.getElementById('poEndDateFrom').value=firstDayOfMonth1; 
        document.getElementById('empEndDateFrom').value=''; 
        document.getElementById('empStartDateFrom').value='';
    }
    
    
    
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
    
    if( document.getElementById('poType').value == 'Expiring PO'){
        document.getElementById('poStartDateTo').value = '';        
        document.getElementById('poEndDateTo').value=lastDate; 
        document.getElementById('empStartDateTo').value=''; 
        document.getElementById('empEndDateTo').value=''; 
    }else if( document.getElementById('poType').value == 'New PO' || document.getElementById('poType').value == 'PO BY Account'){
        document.getElementById('poStartDateTo').value=lastDate;    
        document.getElementById('poEndDateTo').value=lastDate;    
        document.getElementById('empStartDateTo').value=''; 
        document.getElementById('empEndDateTo').value=''; 
    }else if(document.getElementById('poType').value == 'Rolled Off'){
        //document.getElementById('empStartDateTo').value=lastDate; 
        document.getElementById('empEndDateTo').value=lastDate; 
        document.getElementById('poStartDateTo').value='';    
        document.getElementById('poEndDateTo').value='';         
    }else if(document.getElementById('poType').value == 'Active PO'){
        document.getElementById('poStartDateTo').value = lastDate;        
        document.getElementById('poEndDateTo').value= lastDate; 
    }
}




function load(form){    
    
    if(document.getElementById('queryDetails').style.display == 'none') { 
        document.getElementById('queryDetails').style.display = 'inline'; 
    }
    else {    
        document.getElementById('queryDetails').style.display = 'none'; 
    }
    
    if(document.getElementById('greensheetList').style.display == 'none') { 
        document.getElementById('greensheetList').style.display = 'inline'; 
    }
    
    var country = escape(document.frmAddGreenSheet.country.value);
    if(country == 'undefined'){        
        country = "nodata";
    }
    if(country == -1){        
        country = "nodata";        
    }
    
    var poStatus = escape(document.frmAddGreenSheet.poStatus.value);
    if(poStatus == 'undefined'){        
        poStatus = "nodata";
    }
    if(poStatus == -1){        
        poStatus = "nodata";        
    }
    //alert('poStatus******'+poStatus);    
    
    var poType = escape(document.frmAddGreenSheet.poType.value);
    /*
    if(poType == 'undefined'){        
        poType = "nodata";
    }
    if(poType == -1){        
        poType = "nodata";
        //alert('poType******'+poType);
    }*/
    
    var customerName = escape(document.frmAddGreenSheet.customerName.value);
    if(customerName == ''){
        customerName="nodata";
        //alert('customerName******'+customerName);
    }
    
    /*var accountId = escape(document.frmAddGreenSheet.accountId.value);
    if(accountId == ''){
        accountId=0;
        //alert('accountId******'+accountId);
    }*/
    
    var empFname = escape(document.frmAddGreenSheet.empFname.value);
    if(empFname == ''){
        empFname="nodata";
        
    }
    //alert('empFname******'+empFname);
    
    var empLname = escape(document.frmAddGreenSheet.empLname.value);
    if(empLname == ''){
        empLname="nodata";
        
    }
    //alert('empLname******'+empLname);
    
    var poStartDateFrom = document.frmAddGreenSheet.poStartDateFrom.value;        
    //alert('poStartDateFrom******'+poStartDateFrom);
    if(poStartDateFrom == '') {
        poStartDateFrom="00/00/0000";
    } else {
        poStartDateFrom = convert(poStartDateFrom);
    }
    //alert('poStartDateFrom22******'+poStartDateFrom);
    
    
    var poStartDateTo = document.frmAddGreenSheet.poStartDateTo.value;   
    //alert('poStartDateTo******'+poStartDateTo);
    if(poStartDateTo == '') {
        poStartDateTo="00/00/0000";
    } else {
        poStartDateTo = convert(poStartDateTo);
    }
    //alert('poStartDateTo2******'+poStartDateTo);
    
    
    
    var poEndDateFrom = document.frmAddGreenSheet.poEndDateFrom.value;    
    //alert('poEndDateFrom******'+poEndDateFrom);
    if(poEndDateFrom == '') {
        poEndDateFrom="00/00/0000";
    } else {
        poEndDateFrom = convert(poEndDateFrom);
    }
    //alert('poEndDateFrom2******'+poEndDateFrom);
    
    
    
    var poEndDateTo = document.frmAddGreenSheet.poEndDateTo.value;    
    //alert('poEndDateTo******'+poEndDateTo);
    if(poEndDateTo == '') {
        poEndDateTo="00/00/0000";
    } else {
        poEndDateTo = convert(poEndDateTo);
    }
    //alert('poEndDateTo2******'+poEndDateTo);
    
    var empStartDateFrom = document.frmAddGreenSheet.empStartDateFrom.value;    
    if(empStartDateFrom == '') {
        empStartDateFrom="00/00/0000";
    } else {
        empStartDateFrom = convert(empStartDateFrom);
    }
    
    var empStartDateTo = document.frmAddGreenSheet.empStartDateTo.value;    
    if(empStartDateTo == '') {
        empStartDateTo="00/00/0000";
    } else {
        empStartDateTo = convert(empStartDateTo);
    }
    
    var empEndDateFrom = document.frmAddGreenSheet.empEndDateFrom.value;    
    if(empEndDateFrom == '') {
        empEndDateFrom="00/00/0000";
    } else {
        empEndDateFrom = convert(empEndDateFrom);
    }
    
    var empEndDateTo = document.frmAddGreenSheet.empEndDateTo.value;    
    if(empEndDateTo == '') {
        empEndDateTo="00/00/0000";
    } else {
        empEndDateTo = convert(empEndDateTo);
    }
    
    loadXMLDoc(CONTENXT_PATH+'/ajaxHandle/greensheetSearch.action?poStatus='+poStatus+'&poType='+poType+'&accountName='+customerName+
    '&poEndDateFrom='+poEndDateFrom+'&poEndDateTo='+poEndDateTo+'&empStartDateFrom='+empStartDateFrom+'&empStartDateTo='+empStartDateTo+
    '&empFname='+empFname+'&empLname='+empLname+'&poStartDateFrom='+poStartDateFrom+'&poStartDateTo='+poStartDateTo+    
    '&empEndDateFrom='+empEndDateFrom+'&empEndDateTo='+empEndDateTo+'&queryType='+poType+'&country='+country+'&dummy='+new Date().getTime(),CBFunc_GreenSheetSearch);           

}

function convert(str) {
    
    var mn = str.substring(0,2);
    
    var dt = str.substring(3,5);
    
    var year = str.substring(6,10);
    
    var sqlDate = year+"-"+mn+"-"+dt;
    return sqlDate;
}

//=========================================================================
//  Define the CallBack Function for the Employee Search URL Response
//=========================================================================

function CBFunc_GreenSheetSearch() {
    var responseText;
    var  myHTMLTable = document.getElementById("tblUpdate");
    var  spnFast = document.getElementById("spnFast");
    
    // var  myHeadTable = document.getElementById("tableHead");
    
    /*Cleaning Rows*/
    ClrTable(myHTMLTable);
    
    if (req.readyState == 4) {
        if (req.status == 200) {
            
            var headerFields = new Array("SNo", "AccountName","FName","LName","POStartDate","POEndDate","PONum","POLineNo","POStatus");			
            
            var getResponseData;
            getResponseData = req.responseText;
            //alert('getResponseData******'+getResponseData);
            var temp = new Array();
            temp = getResponseData.split('addto');
            
            // alert('temp[1]>>> '+temp[1]);
            
            if(req.responseText!=''){
                document.frmAddGreenSheet.inputRowData.style.display="block";
                document.frmAddGreenSheet.inputRowData.value= "Records Found "+temp[1];
                ParseAndGenerateHTML(myHTMLTable,temp[0], headerFields,temp[1]);
                
                // myHeadTable.innerHTML = "Total Rows"+temp[1];
            }else{
                alert('No Result For This Search...');
                //spnFast.innerHTML="No Result For This Search...";                
            }
        } 
        else {
            alert("Please enter a valid zip code:\n" + req.statusText);
        }
    }
    
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
