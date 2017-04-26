function load(form) {	
    document.getElementById("greensheetSearchButton").disabled=true;
    document.getElementById("noteLableForGreenSheet").style.display='none';
    var titleType=document.getElementById("titleType1").value;
    var poType = escape(document.greensheetDashBoard.poType.value);        
    var poStatus = escape(document.greensheetDashBoard.poStatus.value);
    var createdBy = escape(document.greensheetDashBoard.createdBy.value);
    var country = escape(document.greensheetDashBoard.country.value);
    
    var startDate = document.greensheetDashBoard.greensheetStartDate.value;  
    var endDate = document.greensheetDashBoard.greensheetEndDate.value; 

    var checkResult = compareDates(startDate,endDate);
    if(!checkResult) {
    return false;
    }
    
    if(startDate == '') {
        startDate="00/00/0000";
    } else {
        startDate = convert(startDate);
    }
         
    if(endDate == '') {
        endDate = "00/00/0000";
    } else {
        endDate = convert(endDate);
    }
    
    document.getElementById('greensheetDashBoardList').style.display = 'inline'; 
   // loadXMLDoc(CONTENXT_PATH+'/ajaxHandle/greensheetListSearch.action?poType='+poType+'&startDate='+startDate+'&endDate='+endDate+'&poStatus='+poStatus+'&createdBy='+createdBy+'&country='+country+'&dummy='+new Date().getTime()
         //   ,CBFunc_GreensheetListSearch);
          loadXMLDoc(CONTENXT_PATH+'/ajaxHandle/greensheetListSearch.action?poType='+poType+'&startDate='+startDate+'&endDate='+endDate+'&poStatus='+poStatus+'&titleType='+titleType+'&createdBy='+createdBy+'&country='+country+'&dummy='+new Date().getTime(),CBFunc_GreensheetListSearch);
}

function convert(str) {
    
    var mn = str.substring(0,2);
    
    var dt = str.substring(3,5);
    
    var year = str.substring(6,10);
    
    var sqlDate = year+"-"+mn+"-"+dt;
    return sqlDate;
}

//  Define the CallBack Function for the Employee Search URL Response

function CBFunc_GreensheetListSearch() {
    var responseText;

    var myHTMLTable = document.getElementById("tblUpdate");
    // alert('value is11234 -- '+myHTMLTable.id)

    var  spnFast = document.getElementById("spnFast");
    
    // var  myHeadTable = document.getElementById("tableHead");
    
    /*Cleaning Rows*/
    ClrTable(myHTMLTable);
    
    if (req.readyState == 4) {
        if (req.status == 200) {
            document.getElementById("loadGreenMessage").style.display = 'none';
           // var headerFields = new Array("SNo", "Account Name","Consultant Name","POStartDate","POEndDate","CreatedBy","RatePerHour");			
           var headerFields = new Array("SNo", "Account&nbsp;Name","Consultant&nbsp;Name","PO&nbsp;Type","PO&nbsp;Status","POStartDate","POEndDate","CreatedBy","RatePerHour");
            
            var getResponseData;
            getResponseData = req.responseText;
            var temp = new Array();
            temp = getResponseData.split('addto');            
            //var storeSum = formattedNumber(temp[2]);                  

            var country = document.getElementById("country").value;            
            if(country == 'India') { 
                    symbol = "INR ";
                }else{
                       symbol = "$ ";
                }
            var roundOff = formatNumber(temp[2]);
            var storeSum = formattedNumber(roundOff,symbol);                        


            if(req.responseText!=''){
                /*
                document.greensheetDashBoard.inputRowData.style.display="block";
                document.greensheetDashBoard.inputRowData.value= "Records Found "+temp[1];
                document.greensheetDashBoard.inputTotal.style.display="block";
                document.greensheetDashBoard.inputTotal.value= "Total Value "+storeSum;
                 */
                document.getElementById("totalGreenRec").innerHTML = temp[1];
                //document.getElementById("totalGreenSum").innerHTML = storeSum;
               document.getElementById("totalGreenSum").innerHTML = "$ "+moneyFormat(temp[2]);
                ParseAndGenerateHTML(myHTMLTable,temp[0], headerFields);
              //  document.getElementById(("footer"+myHTMLTable.id)).innerHTML = "Total Value is:  "+storeSum;
              document.getElementById(("footer"+myHTMLTable.id)).innerHTML = "Total Value is($):  "+moneyFormat(temp[2]);
                 document.getElementById("noteLableForGreenSheet").style.display='block';
                // myHeadTable.innerHTML = "Total Rows"+temp[1];
            }else{
                alert('No Result For This Search...');
                spnFast.innerHTML="No Result For This Search...";                
            }
        } 
        
    }else {
            document.getElementById("loadGreenMessage").style.display = 'block';
            //alert("HTTP error ---"+req.status+" : "+req.statusText);
            //alert("Please enter a valid zip code:\n" + req.statusText);
        }  
        document.getElementById("greensheetSearchButton").disabled=false;
    document.getElementById("noteLableForGreenSheet").style.display='block';
}


/*function ClrTable(myHTMLTable) { 
    var tbl =  myHTMLTable;
    var lastRow = tbl.rows.length; 
    //document.getElementById('addlabel1').style.display = 'none'; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}*/
var temp ="0";

function defaultDates(){
    
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
    
    document.getElementById('greensheetStartDate').value = firstDayOfMonth;       
    document.getElementById('greensheetEndDate').value = lastDate;  
    
   
    
    //document.getElementById('dashBoardStartDateRep').value = firstDayOfMonth;       
    //document.getElementById('dashBoardEndDateRep').value = lastDate;
    
    /*For getting start date and 30 days prior date from current date
     * To set these dates into Account Summary by rep dates
     * 
     */
    
    var currentDate = new Date();
    var day = currentDate.getDate();
    var month = currentDate.getMonth() + 1;
    var year = currentDate.getFullYear();

    var endDate =  month+"/"+day+"/"+year;


    var today = new Date();
    var priorDate = new Date(endDate);
    priorDate.setDate(priorDate.getDate()-30);
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
    
    document.getElementById('dashBoardStartDateRep').value = startDate;       
    document.getElementById('dashBoardEndDateRep').value = endDate;
    
    document.getElementById('startDateSummaryGraph').value = startDate;       
    document.getElementById('endDateSummaryGraph').value = endDate;
    
    
    /*End the code of prior 30 days from current date.
     * 
     */

    }



