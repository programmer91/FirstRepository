
function hideVendorDetails(){    
    
    document.getElementById('vendorTab1').style.display = 'none';
    document.getElementById('vendorTab2').style.display = 'none';
    document.getElementById('vendorTab3').style.display = 'none';
    document.getElementById('vendorTab4').style.display = 'none';
    //new
    document.getElementById('vendorTab5').style.display = 'none';
    document.getElementById('vendorTab6').style.display = 'none';
    document.getElementById('vendorTab7').style.display = 'none';
}
function showVendorDetails(){
    
    document.getElementById('vendorTab1').style.display = '';
    document.getElementById('vendorTab2').style.display = '';
    document.getElementById('vendorTab3').style.display = '';
    document.getElementById('vendorTab4').style.display = ''; 
    //new
    document.getElementById('vendorTab5').style.display = '';
    document.getElementById('vendorTab6').style.display = ''; 
    document.getElementById('vendorTab7').style.display = ''; 
}

function test(){
    //  alert("hello show");
    if(document.getElementById('consultantTypeId').value=='5') {
        document.frmAddGreenSheet.consultantType[1].checked=true;
        //showVendorDetails('hideVendorDetails');
        
        document.getElementById('vendorTab1').style.display = '';
        document.getElementById('vendorTab2').style.display = '';
        document.getElementById('vendorTab3').style.display = '';
        document.getElementById('vendorTab4').style.display = ''; 
        //new
        document.getElementById('vendorTab5').style.display = '';
        document.getElementById('vendorTab6').style.display = ''; 
        document.getElementById('vendorTab7').style.display = ''; 
    }
    else {
        document.frmAddGreenSheet.consultantType[0].checked=true;
        
        document.getElementById('vendorTab1').style.display = 'none';
        document.getElementById('vendorTab2').style.display = 'none';
        document.getElementById('vendorTab3').style.display = 'none';
        document.getElementById('vendorTab4').style.display = 'none';
        //new
        document.getElementById('vendorTab5').style.display = 'none';
        document.getElementById('vendorTab6').style.display = 'none';
        document.getElementById('vendorTab7').style.display = 'none';
    }
}

function getOtherDivs() {    
    
    var potype = document.getElementById('poType').value;

    if( potype == 'Services') {                        
        
        document.getElementById('poStartDateLabel').innerHTML='PO Start Date: ';
        document.getElementById('poEndDateTDLabel').style.display = '';        
        document.getElementById('poEndDateTDText').style.display = '';
        document.getElementById('poEndDateLabel').innerHTML='PO End Date: ';
        
        document.getElementById('reportManagerLabel').innerHTML='Reporting Manager: ';
        document.getElementById('reportPhoneLabel').innerHTML='Reporting Phone: ';
        document.getElementById('reportEmailLabel').innerHTML='Reporting Manager Email: ';

        document.getElementById('consultantTab1').style.display = '';
        document.getElementById('consultantTab2').style.display = '';        
        document.getElementById('consultantTab3').style.display = '';        
        document.getElementById('consultantTab4').style.display = '';
        document.getElementById('consultantTab5').style.display = '';
        document.getElementById('consultantTab6').style.display = '';
        document.getElementById('consultantTab7').style.display = '';        
        document.getElementById('consultantTab8').style.display = '';
        document.getElementById('consultantTab9').style.display = '';
        document.getElementById('consultantTab10').style.display = '';        
        document.getElementById('consultantTab11').style.display = '';
        document.getElementById('consultantTab12').style.display = '';
        document.getElementById('consultantTab13').style.display = '';
        document.getElementById('consultantTab14').style.display = 'none';
        
        document.getElementById('softwareTab1').style.display = 'none'; 
        document.getElementById('softwareTab2').style.display = 'none';  
        document.getElementById('softwareTab3').style.display = 'none'; 
        document.getElementById('softwareTab4').style.display = 'none'; 
        document.getElementById('softwareTab5').style.display = 'none'; 
        document.getElementById('softwareTab6').style.display = 'none'; 
        document.getElementById('softwareTab7').style.display = 'none'; 
        //new
        document.getElementById('softwareTab8').style.display = 'none';  
        //document.getElementById('softwareTab9').style.display = 'none';
        document.getElementById('softwareTab10').style.display = 'none';
    }
    else if (potype == 'Expenses') {        
        
        document.getElementById('poStartDateLabel').innerHTML='PO Start Date: ';
        document.getElementById('poEndDateTDLabel').style.display = 'none';
        document.getElementById('poEndDateTDText').style.display = 'none';  

        document.getElementById('reportManagerLabel').innerHTML='Reporting Manager: ';
        document.getElementById('reportPhoneLabel').innerHTML='Reporting Phone: ';
        document.getElementById('reportEmailLabel').innerHTML='Reporting Manager Email: ';

        document.getElementById('consultantTab1').style.display = '';
        document.getElementById('consultantTab2').style.display = '';        
        document.getElementById('consultantTab3').style.display = '';        
        document.getElementById('consultantTab4').style.display = '';
        document.getElementById('consultantTab5').style.display = '';
        document.getElementById('consultantTab6').style.display = '';
        document.getElementById('consultantTab7').style.display = '';        
        document.getElementById('consultantTab8').style.display = '';
        document.getElementById('consultantTab9').style.display = '';
        document.getElementById('consultantTab10').style.display = '';        
        document.getElementById('consultantTab11').style.display = '';
        document.getElementById('consultantTab12').style.display = '';
        document.getElementById('consultantTab13').style.display = '';
        document.getElementById('consultantTab14').style.display = 'none';
        
        document.getElementById('softwareTab1').style.display = 'none'; 
        document.getElementById('softwareTab2').style.display = 'none';  
        document.getElementById('softwareTab3').style.display = 'none'; 
        document.getElementById('softwareTab4').style.display = 'none'; 
        document.getElementById('softwareTab5').style.display = 'none'; 
        document.getElementById('softwareTab6').style.display = 'none'; 
        document.getElementById('softwareTab7').style.display = 'none'; 
        //new 
        document.getElementById('softwareTab8').style.display = 'none';  
       // document.getElementById('softwareTab9').style.display = 'none';
        document.getElementById('softwareTab10').style.display = 'none';
    }
    else if (potype == 'FixedBid') {                
            
        document.getElementById('poStartDateLabel').innerHTML='Proj. Start Date: ';
        document.getElementById('poEndDateTDLabel').style.display = '';        
        document.getElementById('poEndDateTDText').style.display = '';
        document.getElementById('poEndDateLabel').innerHTML='Proj. End Date: ';
        
        document.getElementById('poDetailsLabel').innerHTML='Milestone Details: ';        
        document.getElementById('softwareHeaderLabel').innerHTML='Fixed Bid Project Details';    
        document.getElementById('custPriceLabel').innerHTML='Project Price: ' ;        
        document.getElementById('ourPriceLabel').innerHTML='Miracle Cost: ';

        document.getElementById('reportManagerLabel').innerHTML='Project Owner: ';
        document.getElementById('reportPhoneLabel').innerHTML='Phone: ';
        document.getElementById('reportEmailLabel').innerHTML='Email: ';
        
        document.getElementById('consultantTab1').style.display = 'none';
        document.getElementById('consultantTab2').style.display = 'none';
        document.getElementById('consultantTab3').style.display = 'none';
        document.getElementById('consultantTab4').style.display = 'none';
        document.getElementById('consultantTab5').style.display = 'none';
        document.getElementById('consultantTab6').style.display = 'none';
        document.getElementById('consultantTab7').style.display = 'none';
        document.getElementById('consultantTab8').style.display = 'none';
        document.getElementById('consultantTab9').style.display = 'none';
        document.getElementById('consultantTab10').style.display = 'none';        
        document.getElementById('consultantTab11').style.display = 'none';
        document.getElementById('consultantTab12').style.display = 'none';
        document.getElementById('consultantTab13').style.display = 'none';
        document.getElementById('consultantTab14').style.display = 'none';
        
        document.getElementById('softwareTab1').style.display = '';
        document.getElementById('softwareTab2').style.display = ''; 
        document.getElementById('softwareTab3').style.display = ''; 
        document.getElementById('softwareTab4').style.display = ''; 
        document.getElementById('softwareTab5').style.display = '';
        document.getElementById('softwareTab6').style.display = 'none'; 
        document.getElementById('softwareTab7').style.display = 'none'; 
        //new
        document.getElementById('softwareTab8').style.display = 'none'; 
       // document.getElementById('softwareTab9').style.display = 'none';
        document.getElementById('softwareTab10').style.display = 'none';
        // change Reporting Manager / Phone / email as Project Owner / Phone / Email
    }
    else if (potype == 'Software') {        
        
        document.getElementById('poStartDateLabel').innerHTML='PO Issued Date: ';
        document.getElementById('poEndDateTDLabel').style.display = 'none';
        document.getElementById('poEndDateTDText').style.display = 'none';        
        
        document.getElementById('poDetailsLabel').innerHTML='Software Details: ';  
        //new
         
        
        //document.getElementById('partNumber').innerHTML='Part Number:';     
        document.getElementById('softwareHeaderLabel').innerHTML='Software Resale Details';
        document.getElementById('custPriceLabel').innerHTML='Customer Price: ';
        document.getElementById('ourPriceLabel').innerHTML='AVNET Price: ';  
      
        document.getElementById('reportManagerLabel').innerHTML='Reporting Manager: ';
        document.getElementById('reportPhoneLabel').innerHTML='Reporting Phone: ';
        document.getElementById('reportEmailLabel').innerHTML='Reporting Manager Email: ';
        
        document.getElementById('consultantTab1').style.display = 'none';
        document.getElementById('consultantTab2').style.display = '';
        document.getElementById('consultantTab3').style.display = '';
        document.getElementById('consultantTab4').style.display = 'none';
        document.getElementById('consultantTab5').style.display = 'none';
        document.getElementById('consultantTab6').style.display = 'none';
        document.getElementById('consultantTab7').style.display = 'none';
        document.getElementById('consultantTab8').style.display = 'none';
        document.getElementById('consultantTab9').style.display = 'none';
        document.getElementById('consultantTab10').style.display = 'none';        
        document.getElementById('consultantTab11').style.display = 'none';
        document.getElementById('consultantTab12').style.display = 'none';
        document.getElementById('consultantTab13').style.display = 'none';
         document.getElementById('consultantTab14').style.display = '';
        
        document.getElementById('softwareTab1').style.display = ''; 
        document.getElementById('softwareTab2').style.display = ''; 
        document.getElementById('softwareTab3').style.display = ''; 
        document.getElementById('softwareTab4').style.display = ''; 
        document.getElementById('softwareTab5').style.display = '';         
        document.getElementById('softwareTab6').style.display = '';         
        document.getElementById('softwareTab7').style.display = ''; 
        //new
        document.getElementById('softwareTab8').style.display = ''; 
        //document.getElementById('softwareTab9').style.display = '';
        document.getElementById('softwareTab10').style.display ='';
    }
    else if (potype == 'Support') { 
        document.getElementById('poStartDateLabel').innerHTML='PO Issued Date: ';
        document.getElementById('poEndDateTDLabel').style.display = 'none';
        document.getElementById('poEndDateTDText').style.display = 'none';

        document.getElementById('reportManagerLabel').innerHTML='Reporting Manager: ';
        document.getElementById('reportPhoneLabel').innerHTML='Reporting Phone: ';
        document.getElementById('reportEmailLabel').innerHTML='Reporting Manager Email: ';

        document.getElementById('consultantTab1').style.display = 'none';
        document.getElementById('consultantTab2').style.display = 'none';
        document.getElementById('consultantTab3').style.display = 'none';
        document.getElementById('consultantTab4').style.display = 'none';
        document.getElementById('consultantTab5').style.display = 'none';
        document.getElementById('consultantTab6').style.display = 'none';
        document.getElementById('consultantTab7').style.display = 'none';
        document.getElementById('consultantTab8').style.display = 'none';
        document.getElementById('consultantTab9').style.display = 'none';
        document.getElementById('consultantTab10').style.display = 'none';        
        document.getElementById('consultantTab11').style.display = 'none';
        document.getElementById('consultantTab12').style.display = 'none';
        document.getElementById('consultantTab13').style.display = 'none';
        document.getElementById('consultantTab14').style.display = 'none';
        
        document.getElementById('softwareTab1').style.display = 'none'; 
        document.getElementById('softwareTab2').style.display = 'none';  
        document.getElementById('softwareTab3').style.display = 'none'; 
        document.getElementById('softwareTab4').style.display = 'none'; 
        document.getElementById('softwareTab5').style.display = 'none'; 
        document.getElementById('softwareTab6').style.display = 'none'; 
        document.getElementById('softwareTab7').style.display = 'none'; 
       //new
        document.getElementById('softwareTab8').style.display = 'none';  
       // document.getElementById('softwareTab9').style.display = 'none';
        document.getElementById('softwareTab10').style.display = 'none';
    }
    else if (potype == 'Others') {
        document.getElementById('poStartDateLabel').innerHTML='PO Issued Date: ';
        document.getElementById('poEndDateTDLabel').style.display = 'none';
        document.getElementById('poEndDateTDText').style.display = 'none';

        document.getElementById('reportManagerLabel').innerHTML='Reporting Manager: ';
        document.getElementById('reportPhoneLabel').innerHTML='Reporting Phone: ';
        document.getElementById('reportEmailLabel').innerHTML='Reporting Manager Email: ';

        document.getElementById('consultantTab1').style.display = 'none';
        document.getElementById('consultantTab2').style.display = 'none';
        document.getElementById('consultantTab3').style.display = 'none';
        document.getElementById('consultantTab4').style.display = 'none';
        document.getElementById('consultantTab5').style.display = 'none';
        document.getElementById('consultantTab6').style.display = 'none';
        document.getElementById('consultantTab7').style.display = 'none';
        document.getElementById('consultantTab8').style.display = 'none';
        document.getElementById('consultantTab9').style.display = 'none';
        document.getElementById('consultantTab10').style.display = 'none';        
        document.getElementById('consultantTab11').style.display = 'none';
        document.getElementById('consultantTab12').style.display = 'none';
        document.getElementById('consultantTab13').style.display = 'none';
        document.getElementById('consultantTab14').style.display = 'none';
        
        document.getElementById('softwareTab1').style.display = 'none'; 
        document.getElementById('softwareTab2').style.display = 'none';  
        document.getElementById('softwareTab3').style.display = 'none'; 
        document.getElementById('softwareTab4').style.display = 'none'; 
        document.getElementById('softwareTab5').style.display = 'none'; 
        document.getElementById('softwareTab6').style.display = 'none'; 
        document.getElementById('softwareTab7').style.display = 'none';  
        //new
        document.getElementById('softwareTab8').style.display = 'none';
        //document.getElementById('softwareTab9').style.display = 'none';
        document.getElementById('softwareTab10').style.display = 'none';
    }
}

// Generating Pop Up Window

function popitup(url) {
    newwindow=window.open(url,'name','height=650,width=750');
    if (window.focus) {newwindow.focus()}
    return false;
}
function currencyPopup(url) {
    newwindow=window.open(url,'name','height=400,width=600');
    if (window.focus) {newwindow.focus()}
    return false;
}
// for duration calculation
function countDuration() {
    var stdate =document.getElementById('startDate').value;
    var endate =document.getElementById('endDate').value;
    var date1 = new Date(stdate.toString());
    var date2 = new Date(endate.toString()); 
    var date1_ms = date1.getTime();
    var date2_ms = date2.getTime();
    if(date1_ms<date2_ms) {
        // Calculate the difference in milliseconds
        var difference_ms = Math.abs(date1_ms - date2_ms);
        
        // The number of milliseconds in one day
        var ONE_DAY = 1000 * 60 * 60 * 24;
        // Convert back to days and return
        var totalDays= Math.round(difference_ms/ONE_DAY);
        var totMon= Math.round(totalDays/30);
        //alert("The months"+totMon);
        
        document.frmAddGreenSheet.duration.value=totMon;
    }
    else {
        //alert("EndDate must be graterthen the StartDate");
        stdate=0;
    }
}

// for vendor name
var isIE;
var vendorName;

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

function doCompletion() {
    clearfields();
    vendorName=document.getElementById("agencyName");
    if (vendorName.value != " ") {
        
        var url = CONTENXT_PATH+"/greenSheet.action?vendorName="+vendorName.value;
        
        var req = initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4) {
                if (req.status == 200) {
                    parseVenMessages(req.responseXML);
                }
                else 
                    if (req.status == 204) {
                        clearfields();
                    }
            }
        };
        req.open("GET", url, true);
        req.send(null);
    }
    
}  

function parseVenMessages(responseXML) {
    clearfields();
    var vendList = responseXML.getElementsByTagName("VENLIST")[0];
    //alert(vendList);
    var vendour = vendList.childNodes[0];
    var chk=vendour.getElementsByTagName("VALID")[0];
    if(chk.childNodes[0].nodeValue =="true"){
        
        if (vendList.childNodes.length > 0) {
            var venDetails = vendList.childNodes[0];
            var venPhone = venDetails.getElementsByTagName("PHONE")[0];
            var venMail = venDetails.getElementsByTagName("MAIL")[0];
            document.frmAddGreenSheet.vendorPhone.value=venPhone.childNodes[0].nodeValue;
            document.frmAddGreenSheet.vendorEmail.value=venMail.childNodes[0].nodeValue;
        } 
        else {
            clearfields();
        }
    } else {
        
    }
    
}
function clearfields() {
    document.frmAddGreenSheet.vendorEmail.value=" ";
    document.frmAddGreenSheet.vendorPhone.value=" ";
}

// for consultant name

var completeTable;
var completeField;
var autorow;
var autorow1;
var req;

function getElementY(element){
    var targetTop = 0;
    if (element.offsetParent) {
        while (element.offsetParent) {
            targetTop += element.offsetTop;
            element = element.offsetParent;
        }
    } else if (element.y) {
        targetTop += element.y;
    }
    return targetTop;
}

function init() {
    
    getOtherDivs();
    
    //var menu = document.getElementById("auto-row");
    //var menu1 = document.getElementById("auto-row1");
    autorow = document.getElementById("menu-popup");
    autorow.style.display ="none";
    autorow1 = document.getElementById("menu-popup");
    autorow1.style.display ="none";
    //autorow.style.top = getElementY(menu) + "px";
    //autorow1.style.top = getElementY(menu1) + "px";
    var height = document.documentElement.clientHeight - 120;
    autorow1.style.height = Math.max(height, 150);
    autorow1.style.overflowY = "scroll";
    autorow.style.height = Math.max(height, 150);
    autorow.style.overflowY = "scroll";
    
    completeTable = document.getElementById("completeTable");    
    completeTable.setAttribute("bordercolor", "white");
}

function init1() {
    //alert("rk")
    //getOtherDivs();
    
    //var menu = document.getElementById("auto-row");
    //var menu1 = document.getElementById("auto-row1");
    autorow = document.getElementById("menu-popup");
    autorow.style.display ="none";
    autorow1 = document.getElementById("menu-popup");
    autorow1.style.display ="none";
    //autorow.style.top = getElementY(menu) + "px";
    //autorow1.style.top = getElementY(menu1) + "px";
    var height = document.documentElement.clientHeight - 120;
    autorow1.style.height = Math.max(height, 150);
    autorow1.style.overflowY = "scroll";
    autorow.style.height = Math.max(height, 150);
    autorow.style.overflowY = "scroll";
    
    completeTable = document.getElementById("completeTable");
    completeTable.setAttribute("bordercolor", "white");
}

function fillConsultant() {
    completeField = document.getElementById("firstName");
    if (completeField.value == "") {
        clearTable();
    } else {
        var test=document.getElementById("firstName").value;
        
        if (test.length >2) {
            //var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=GSConsultant&ConsultantName="+ escape(test);
            var url = CONTENXT_PATH+"/gsConsultant.action?ConsultantName="+ escape(test);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        parseMessages(req.responseXML);
                    } else if (req.status == 204){
                        clearTable();
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }
}

function parseMessages(responseXML) {
    clearTable();
    var employees = responseXML.getElementsByTagName("EMPLOYEES")[0];
    if (employees.childNodes.length > 0) {
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");
        
    } else {
        clearTable();
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
    if(chk.childNodes[0].nodeValue =="true"){
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "";
        
        appendEmployeeAtFirst();
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < employees.childNodes.length; loop++) {
            var employee = employees.childNodes[loop];
            var firstName = employee.getElementsByTagName("FIRSTNAME")[0];
            var lastName = employee.getElementsByTagName("LASTNAME")[0];
            var middleName = employee.getElementsByTagName("MIDDLENAME")[0];
            var cellno = employee.getElementsByTagName("CELLNO")[0];
            appendEmployee(firstName.childNodes[0].nodeValue,lastName.childNodes[0].nodeValue,middleName.childNodes[0].nodeValue,cellno.childNodes[0].nodeValue);
        }
        var position = findPosition(document.getElementById("firstName"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = " First Name  is inValid ";
    }
}

function clearTable() {
    if (completeTable) {
        completeTable.setAttribute("bordercolor", "white");
        completeTable.setAttribute("border", "0");
        completeTable.style.visible = false;
        for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
}


function appendEmployee(firstName,lastName,middleName,cellNo) {
    var firstNameCell;
    var lastNameCell;
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
    linkElement.setAttribute("href", "javascript:anchor_test('"+firstName+"','"+lastName+"','"+middleName+"','"+cellNo+"')");
    linkElement.appendChild(document.createTextNode(firstName +" "+lastName+" "+middleName+" "+cellNo));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}


function hideScrollBar() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
}

function appendEmployeeAtFirst() {
    var firstNameCell;
    var lastNameCell;
    var row;
    var nameCell;
    if (isIE) {
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
    //nameCell.style="font:12px;";
    
    var linkElement = document.createTextNode("FName :: LName :: MName :: CellNo");
    nameCell.appendChild(linkElement);
}
function anchor_test(first,last,midd,cno){
    clearTable();
    document.frmAddGreenSheet.lastName.value =last;
    if(last == "Not Avail"){
        last = "";
    } 
    if(midd == "Not Avail"){
        midd = "";
    } 
    if(cno == "Not Avail"){
        cno = "";
    } 
    document.frmAddGreenSheet.middleName.value =midd;
    document.frmAddGreenSheet.phone.value =cno;
    document.frmAddGreenSheet.fName.value =first;
}

//for customer

function fillCustomer() {
    var test=document.getElementById("customerName").value; 

//alert("test--->"+test);       
    if (test == "") {
        clearTable();
    } else {
        if (test.length >2) {
            var url = CONTENXT_PATH+"/getCustomerDetails.action?customerName="+ escape(test);   

            //alert("URL--->"+url);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {                        
                        parseCustMessages(req.responseXML);                        
                    } else if (req.status == 204){
                        clearTable();
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }
}

function parseCustMessages(responseXML) {
    clearTable();
    var employees = responseXML.getElementsByTagName("EMPLOYEES")[0];
    if (employees.childNodes.length > 0) {        
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");        
    } else {
        clearTable();
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
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < employees.childNodes.length; loop++) {
            
            var employee = employees.childNodes[loop];
            var customerName = employee.getElementsByTagName("NAME")[0];
            var empid = employee.getElementsByTagName("EMPID")[0];
            appendCustomer(empid.childNodes[0].nodeValue,customerName.childNodes[0].nodeValue);
        }
        var position = findPosition(document.getElementById("customerName"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = " Name  is InValid ";
    }
}
function appendCustomer(empId,empName) {
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
    linkElement.setAttribute("href", "javascript:set_cust('"+empName.replace(/'/g, "\\'") +"','"+ empId +"')");
    
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}
function set_cust(eName,eID){
    clearTable();
    getProjectNamesList(eID);
    document.frmAddGreenSheet.customerName.value =eName;
    document.frmAddGreenSheet.consultantId.value =eID;
    //alert("hii");
}


// Start for issue..
function fillCustomerInIssue() {
    var test=document.getElementById("customerName").value; 

//alert("test--->"+test);       
    if (test == "") {
        clearTable();
    } else {
        if (test.length >2) {
            var url = CONTENXT_PATH+"/getCustomerDetails.action?customerName="+ escape(test);   

            //alert("URL--->"+url);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {                        
                        parseCustMessagesForissue(req.responseXML);                        
                    } else if (req.status == 204){
                        clearTable();
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }
}

function parseCustMessagesForissue(responseXML) {
    clearTable();
    var employees = responseXML.getElementsByTagName("EMPLOYEES")[0];
    if (employees.childNodes.length > 0) {        
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");        
    } else {
        clearTable();
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
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < employees.childNodes.length; loop++) {
            
            var employee = employees.childNodes[loop];
            var customerName = employee.getElementsByTagName("NAME")[0];
            var empid = employee.getElementsByTagName("EMPID")[0];
            appendCustomerforissue(empid.childNodes[0].nodeValue,customerName.childNodes[0].nodeValue);
        }
        var position = findPosition(document.getElementById("customerName"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = " Name  is InValid ";
    }
}
function appendCustomerforissue(empId,empName) {
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

    linkElement.setAttribute("href", "javascript:set_cust_issue('"+empName +"','"+ empId +"');getProjectNamesList('"+empId+"');");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}












function set_cust_issue(eName,eID){
    clearTable();
    document.issuesForm.customerName.value =eName;
    document.issuesForm.consultantId.value =eID;

}

// end issue..

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

function countPoEndDate() {
    var stdate =document.getElementById('startDate').value;
    var endate =document.getElementById('endDate').value;
    var duration =document.getElementById('duration').value;
    
    var curdate = new Date(stdate); // get system date
    
    // convert system date to milliseconds from 1-1-1970 using  UTC function of Date object
    curdate_utc = Date.UTC( curdate.getFullYear(), curdate.getMonth(), curdate.getDate(),0,0,0,0);
    
   // alert(duration+" duration");
    
    var monthsinmill=(duration*2628000000);
    var milliseconds = (curdate_utc+monthsinmill);
    var myDate = new Date(milliseconds);
  //  alert("(myDate.getMonth()+1)"+(myDate.getMonth()+1));
  //  alert("myDate after  "+(myDate.getMonth()+1)+"/"+myDate.getDate()+"/"+myDate.getFullYear());
    
    document.getElementById('endDate').value=(myDate.getMonth()+1)+"/"+myDate.getDate()+"/"+myDate.getFullYear();
    
    
}


function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode!=46 && charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    
    return true;
}



//new methods for getting projectNames List

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


/*START: Methods for getting Projects Names  data*/

function getProjectNamesList(accId){

   // var deptName = document.employeeForm.departmentId.value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateProjectNamesList);
    var url = CONTENXT_PATH+"/getProjectList.action?accId="+accId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}

function populateProjectNamesList(resXML) {
    var projectName1 = document.issuesForm.projectName;
    var customer = resXML.getElementsByTagName("CUSTOMER")[0];
    var projectNameList = customer.getElementsByTagName("PROJECT");
    projectName1.innerHTML=" ";

    for(var i=0;i<projectNameList.length;i++) {
        var projects = projectNameList[i];
        
        var att = projects.getAttribute("projectId");
        var name = projects.firstChild.nodeValue;
        
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        projectName1.appendChild(opt);
    }


}



/*END: Methods for getting reports to person data*/


/*For fill Employee Details*/
//new
function fillEmployee(assignedToType) {
var test='';
    if(assignedToType=='pre') {
    var test=document.getElementById("assignedToUID").value;  
    }else {
    test=document.getElementById("postAssignedToUID").value;  
    }      
    if (test == "") {
    
        clearTable();
        hideScrollBar();
    } else {
        if (test.length >2) {
            var url = CONTENXT_PATH+"/getEmployeeDetails.action?customerName="+ escape(test);         
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {                        
                        parseEmpMessages(req.responseXML,assignedToType);                        
                    } else if (req.status == 204){
                        clearTable();
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }
}



//new
function parseEmpMessages(responseXML,assignedToType) {
    clearTable();
    var employees = responseXML.getElementsByTagName("EMPLOYEES")[0];
    if (employees.childNodes.length > 0) {        
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");        
    } else {
        clearTable();
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
        if(assignedToType == 'pre') {
        validationMessage=document.getElementById("assignEmpValidationMessage");
        }else {
        validationMessage=document.getElementById("postAssignEmpValidationMessage");
        }
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < employees.childNodes.length; loop++) {
            
            var employee = employees.childNodes[loop];
            var customerName = employee.getElementsByTagName("NAME")[0];
            var empid = employee.getElementsByTagName("EMPID")[0];
            appendEmployee(empid.childNodes[0].nodeValue,customerName.childNodes[0].nodeValue,assignedToType);
        }
        //var position = findPosition(document.getElementById("customerName"));
        var position;
        
        if(assignedToType == 'pre') {
        position = findPosition(document.getElementById("assignedToUID"));
        }else {
        position = findPosition(document.getElementById("postAssignedToUID"));
        }
        //var position = findPosition(document.getElementById("assignedToUID"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
    var validationMessage = '';
     if(assignedToType == 'pre') {
     validationMessage=document.getElementById("assignEmpValidationMessage");
    }else {
    validationMessage=document.getElementById("postAssignEmpValidationMessage");
    }
     //validationMessage=document.getElementById("assignEmpValidationMessage");
        //var validationMessage=document.getElementById("assignEmpValidationMessage");
        validationMessage.innerHTML = " Name  is InValid ";
    }
}


//new
function appendEmployee(empId,empName,assignedToType) {
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
    if(assignedToType=='pre'){
    linkElement.setAttribute("href", "javascript:set_emp('"+empName +"','"+ empId +"')");
    }else {
    linkElement.setAttribute("href", "javascript:set_postEmp('"+empName +"','"+ empId +"')");
    }
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}


//new
function set_emp(eName,eID){
    clearTable();
    document.issuesForm.assignedToUID.value =eName;
    document.issuesForm.preAssignEmpId.value =eID;
}
function set_postEmp(eName,eID){
    clearTable();
    document.issuesForm.postAssignedToUID.value =eName;
    document.issuesForm.postAssignEmpId.value =eID;
}



//for getting Subcatgoery

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


/*Methods for getting Practices by Department*/

function getPracticeDataV1() {
    
    var departmentName = document.getElementById("categoryId").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populatePractices);
    var url = CONTENXT_PATH+"/getEmpDepartment.action?departmentName="+departmentName;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populatePractices(resXML) {    
    
    var practiceId = document.getElementById("subCategoryId");
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

/*Methods closing Practices by Department*/
