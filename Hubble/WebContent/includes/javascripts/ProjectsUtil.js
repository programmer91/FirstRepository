// This is a javascript file
function getClearData() {
    var getClearData=confirm("Do you really want to clear  this page");
    if(getClearData==true) {
        alert('clear');
        document.getElementById("prjName").value="";
        document.getElementById("prjManagerUID").value="";
        document.getElementById("startDate").value=""; 
        document.getElementById("endDate").value="";
        document.getElementById("projectType").value="";
        document.getElementById("accName").value="";
    }
    else {
        return false;
    }
    
}


function goBack() {
    var goBack= confirm("Do you really want to cancel  this page");
    if (goBack== true) {
        document.location="projectHome.action";
    }
    else {
        
    }
}


function check() {
    var sdate = document.frmProjectEdit.startDate.value;
    var edate = document.frmProjectEdit.endDate.value;
    if(edate == null || edate == '') {
        checkDate();
    }else
        checkDuration();
}


function getMonth(month) {
    
    if(month == 'Jan') {
        month = 00;
    }
    if(month=='Feb') {
        month = 01;
    }
    if(month=='Mar') {
        month = 02;
    }
    if(month=='Apr') {
        month = 03;
    }
    if(month=='May') {
        month = 04;
    }
    if(month=='Jun') {
        month = 05;
    }
    if(month=='Jul') {
        month = 06;
    }
    if(month=='Aug') {
        month = 07;
    }
    if(month=='Sep') {
        month = 08;
    }
    if(month=='Oct') {
        month = 09;
    }
    if(month=='Nov') {
        month = 10;
    }
    if(month=='Dec') {
        month = 11;
    }
    return month;
}

function checkDuration() {
    
    var startDate = document.frmProjectEdit.startDate.value;                       
    var endDate = document.frmProjectEdit.endDate.value;                
    var duration;
    var sdate = startDate.split('-');            
    var edate = endDate.split('-');            
    var month1 = sdate[1];       
    
    var startMonth = getMonth(month1);                      
    var month2 = edate[1];            
    var endMonth = getMonth(month2);                
    var oDate_1 = new Date( parseInt(sdate[2]), startMonth, parseInt(sdate[0]) )
    var oDate_2 = new Date( parseInt(edate[2]), endMonth, parseInt(edate[0]) )
    
    iTime_1 = oDate_1.getTime();
    iTime_2 = oDate_2.getTime();
    
    iDiff = Math.abs( iTime_1 - iTime_2 );           
    //alert( Math.ceil(iDiff / 86400000)+1 ) // 1000ms * 60secs * 60mins * 24hours = 1 day)
    duration = Math.ceil(iDiff / 86400000)+1;
    
    document.frmProjectEdit.projectDuration.value = duration;
    
}                                          

function checkStrMonth(month) {
    
    if(month == '00') {
        month = 'Jan';            
    }
    if(month== '01') {
        month = 'Feb';
    }
    if(month== '02') {
        month = 'Mar';
    }
    if(month== '03') {
        month = 'Apr';
    }
    if(month== '04') {
        month = 'May';
    }
    if(month== '05') {
        month ='Jun';
    }
    if(month== '06') {
        month ='Jul';
    }
    if(month== '07') {
        month ='Aug';
    }
    if(month== '08') {
        month = 'Sep';
    }
    if(month== '09') {
        month = 'Oct';
    }
    if(month== '10') {
        month ='Nov';
    }
    if(month== '11') {
        month ='Dec';
    }
    return month;
}

function checkDate() {
    
    var startDate = document.frmProjectEdit.startDate.value;
    var days = document.frmProjectEdit.projectDuration.value;            
    var sdate = startDate.split('-'); 
    var month1 = sdate[1];                            
    var startMonth = getMonth(month1);                 
    var oDate_1 = new Date( parseInt(sdate[2]), startMonth, parseInt(sdate[0]) )     
    var now = new Date(oDate_1.getTime() + days*24*60*60*1000);                
    var month = checkStrMonth(now.getMonth());
    var date = (now.getDate()-1);
    if(date < 10) {
        date = '0'+date;
    }
    var dateString =  date + "-" + month + "-" + now.getFullYear();                                   
    document.frmProjectEdit.endDate.value = dateString;
}

/*function checkDate1() {
    var sdate = document.frmProjectEdit.startDate1.value;
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1;//January is 0!
    var yyyy = today.getFullYear();
    if(dd<10){dd='0'+dd}
    if(mm<10){mm='0'+mm}
    var date=mm+'/'+dd+'/'+yyyy;
    var dd1 = sdate.substring(0,2);
    var mm1 = sdate.substring(3,6);
    var yyyy1 = sdate.substring(7,11);
    var mm2 = getMonth(mm1)+1;
    if(yyyy1 < yyyy) {
        alert('Start Date cannot be Changed');
        document.frmProjectEdit.startDate.value = document.frmProjectEdit.startDate1.value;
        return false;
    }
    else if((yyyy1 == yyyy) && (mm2 < mm)) {
        alert('Start Date cannot be Changed');
        document.frmProjectEdit.startDate.value = document.frmProjectEdit.startDate1.value;
        return false;
    }
    else if((yyyy1 == yyyy) && (mm2 == mm) && (dd1 < dd)) {
        alert('Start Date cannot be Changed');
        document.frmProjectEdit.startDate.value = document.frmProjectEdit.startDate1.value;
        return false;
    }
}*/

