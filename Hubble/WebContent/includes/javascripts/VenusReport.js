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
function readyStateHandler(req,responseHandler) {
    return function() {
        var  myHTMLTable = document.getElementById("tblUpdate");
        ClrTable(myHTMLTable);
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("loadMessage").style.display = 'none';
                var headerFields = new Array("SNo","Name","LoginTime","LogoutTime","TotalBreakTime","TotalMeetingTime","TotalWorkedTime");	
                var getResponseData;
                getResponseData = req.responseText;
                // alert('getResponseData******'+getResponseData);
                var temp = new Array();
                temp = getResponseData.split('addto');
                if(req.responseText!=''){
                    // document.forMycop.inputRowData.style.display="block";
                    //document.forMycop.inputRowData.value= "Records Found "+temp[1];
                    
                    ParseAndGenerateHTML(myHTMLTable,temp[0], headerFields,temp[1]);   //this function implementation in VenusReportAjax.js
                    
                    // myHeadTable.innerHTML = "Total Rows"+temp[1];
                }else{
                    
                    alert('No Result For This Search...');
                    spnFast.innerHTML="No Result For This Search...";                
                }
                //document.getElementById("loadMessage2").style.display = 'none';
                
            } else {
                //alert("HTTP error ---"+req.status+" : "+req.statusText);
                //alert("please wait");
                document.getElementById("loadMessage").style.display = 'block';
                //document.getElementById("loadMessage2").style.display = 'block';
            }
        }
    }
}

function getVenusReportList() {                                   //This is for getting VenusReportList
    var venEmpName=document.getElementById("venusEmpName").value;
    var venDeptId=document.getElementById("departmentId").value;
    var venStartDate=document.getElementById("startDate").value;
    var venEndDate=document.getElementById("endDate").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req);  
    var url = CONTENXT_PATH+"/getVenusReport.action?venusEmpName="+venEmpName+"&venusDeptId="+venDeptId+"&venusStaDate="+venStartDate+"&venusEndDate="+venEndDate+"&dummy="+new Date().getTime();
    
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
/** Following functions are for getting Absentees List **/
function  getAbsentees(){
    var absEmpName=document.getElementById("absEmpName").value;
    var absDeptId=document.getElementById("departmentId1").value;
    var absStartdate=document.getElementById("absStartDate").value;
    var absEndDate=document.getElementById("absEndDate").value;
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler1(req);
    var url = CONTENXT_PATH+"/getAbsReport.action?venusEmpName="+absEmpName+"&venusDeptId="+absDeptId+"&venusStaDate="+absStartdate+"&venusEndDate="+absEndDate+"&dummy="+new Date().getTime();
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function readyStateHandler1(req,responseHandler) {
    return function() {
        var  myHTMLTable = document.getElementById("tblAbsantee");
        ClrTable(myHTMLTable);
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("loadMessage").style.display = 'none';
                var headerFields = new Array("SNo","Name","Email","WorkPhoneNo","CellPhoneNo","ReportsTo");	
                var getResponseData;
                getResponseData = req.responseText;
                // alert('getResponseData******'+getResponseData);
                var temp = new Array();
                temp = getResponseData.split('addto');
                if(req.responseText!=''){
                     // document.forMycop.inputRowData.style.display="block";
                   //document.forMycop.inputRowData.value= "Records Found "+temp[1];
                    
                    GenerateHTML(myHTMLTable,temp[0], headerFields,temp[1]);   //this function implementation in VenusReportAjax.js
                    
                    // myHeadTable.innerHTML = "Total Rows"+temp[1];
                }else{
                    alert('No Result For This Search...');
                    spnFast.innerHTML="No Result For This Search...";                
                }
                //document.getElementById("loadMessage2").style.display = 'none';
                
            } else {
                //alert("HTTP error ---"+req.status+" : "+req.statusText);
                alert("please wait");
                
                 //document.getElementById("loadMessage").style.display = 'block';
                //document.getElementById("loadMessage2").style.display = 'block';
            }
        }
        else { document.getElementById("loadMessage").style.display = 'block';}
    }
}




function getTimeSheetReportList() {

    var DeptId = document.getElementById("departmentId").value;
    var WeekStartDate = document.getElementById("timeSheetWeekStartDate").value;
    var WeekEndDate = document.getElementById("timeSheetWeekEndDate").value;    
     if(WeekStartDate == "" || WeekEndDate == "") {
        alert("Please enter mandatory fileds");
        return false;
     }else {
           var check = compareDates(WeekStartDate,WeekEndDate);
           if(check == false) {
                return false
           }
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandler2(req);
        var url = CONTENXT_PATH+"/getTimeSheetsReport.action?departmentId="+DeptId+"&timeSheetWeekStartDate="+WeekStartDate+"&timeSheetWeekEndDate="+WeekEndDate;
        req.open("GET",url,"true");    
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }

}


function readyStateHandler2(req,responseHandler) {
    return function() {
        var  myHTMLTable = document.getElementById("tblTimeSheets");
        ClrTable(myHTMLTable);
        //alert(req.readyState);
        if (req.readyState ==4) {
            //alert(req.status);
            if (req.status == 200) {
                document.getElementById("loadMessage2").style.display = 'none';
                var headerFields = new Array("SNo1","EmployeeName","Department","ReportsTo");	
                var getResponseData;
                getResponseData = req.responseText;
                //alert('getResponseData******'+getResponseData);
                var temp = new Array();
                temp = getResponseData.split('addto');        
                //alert("req.responseText"+req.responseText)
                if(req.responseText!='' && req.responseText!='addto0' ){
                   // alert(" response string not empty");
                    GenerateTimeSheetHTMLPage(myHTMLTable,temp[0], headerFields,temp[1]);   //this function implementation in VenusReportAjax.js
                                       
                }else{
                  //  alert(" response string is empty");
                    alert('No Result For This Search...');
                    spnFast.innerHTML="No Result For This Search...";                
                }
                document.getElementById("loadMessage2").style.display = 'none';
                
            } else {
                //alert("HTTP error ---"+req.status+" : "+req.statusText);

                   alert("please wait");
                                
                //document.getElementById("loadMessage2").style.display = 'block';
            }
        }
        else { document.getElementById("loadMessage2").style.display = 'block';                
                }
    }
}


function getNotApprovedReportList() {

    var DeptId = document.getElementById("notApprovedDepartmentId").value;
    var WeekStartDate = document.getElementById("notApprovedStartDate").value;
    var WeekEndDate = document.getElementById("notApprovedEndDate").value;

     if(WeekStartDate == "" || WeekEndDate == "") {
        alert("Please enter mandatory fileds");
        return false;
     }else {        
            var check = compareDates(WeekStartDate,WeekEndDate);
            if(check == false) {
                return false
           }
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandler3(req);
        var url = CONTENXT_PATH+"/getNotApprovedReport.action?notApprovedDepartmentId="+DeptId+"&notApprovedStartDate="+WeekStartDate+"&notApprovedEndDate="+WeekEndDate;
        req.open("GET",url,"true");    
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }

}


function readyStateHandler3(req,responseHandler) {
    return function() {
        var  myHTMLTable = document.getElementById("tblNotApprovedTimeSheets");
        ClrTable(myHTMLTable);
        //alert(req.readyState);
        if (req.readyState ==4) {
            //alert(req.status);
            if (req.status == 200) {
                document.getElementById("loadMessage3").style.display = 'none';
                var headerFields = new Array("SNo","EmployeeName","Department","ReportsTo");	
                var getResponseData;
                getResponseData = req.responseText;
                // alert('getResponseData******'+getResponseData);
                var temp = new Array();
                temp = getResponseData.split('addto');                
                if(req.responseText!='' && req.responseText!='addto0'){
                    
                    GenerateNotApprovedTimeSheetHTMLPage(myHTMLTable,temp[0], headerFields,temp[1]);   //this function implementation in VenusReportAjax.js
                                       
                }else{
                    alert('No Result For This Search...');
                    spnFast.innerHTML="No Result For This Search...";                
                }
                document.getElementById("loadMessage3").style.display = 'none';
                
            } else {
                //alert("HTTP error ---"+req.status+" : "+req.statusText);

                   alert("please wait");
                                                
            }
        }
        else { document.getElementById("loadMessage3").style.display = 'block';                
                }
    }
}





