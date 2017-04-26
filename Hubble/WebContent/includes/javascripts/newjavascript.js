/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function fillCustomerInIssue() {
    var test=document.getElementById("customerName").value; 

//alert("test--->"+test);       
    if (test == "") {
        //document.issuesForm.customerId.value="";
        document.getElementById("customerId").value = "";
        clearTable();
        hideScrollBar();
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "";
        

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
        isCustomerExist = true;
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
        isCustomerExist = false;
  document.getElementById("customerName").value = "";
        document.getElementById("customerId").value = "";
        validationMessage.innerHTML = " Name  is InValid ";
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

function hideScrollBar() {
    autorow = document.getElementById("menu-popup");
    autorow.style.display = 'none';
}
var isIE;
function init2() {
  //  alert("rk--->"+cl);
    autorow = document.getElementById("menu-popup");
    autorow.style.display = "none";
    autorow1 = document.getElementById("menu-popup");
    autorow1.style.display ="none";
    var height = document.documentElement.clientHeight - 120;
    autorow1.style.height = Math.max(height, 150);
    autorow1.style.overflowY = "scroll";
    autorow.style.height = Math.max(height, 150);
    autorow.style.overflowY = "scroll";
    
    completeTables = document.getElementById("completeTable");
    completeTables.setAttribute("bordercolor", "white");
       
   
}
