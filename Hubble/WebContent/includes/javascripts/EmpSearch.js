//=========================================================================
//  This Function is responsible for generating the URL
//  based on all the various form variables and make sure that
//  you escape those Variable before appending to URL.
//  and then invoking the URL and passing the callback function
//=========================================================================


function load(form) {	
 // alert("URL Is being Invoked");
    var fname = escape(document.searchForm.firstName.value);
    var tex = document.getElementById("tex");
    tex.style.display = 'none';
    //document.getElementById("fullName").style.display = 'none';
    var lname = escape(document.searchForm.lastName.value);
    var skils=escape(document.searchForm.skils.value);
    loadXMLDoc(CONTENXT_PATH+'/ajaxHandle/employeeSearch.action?fname='+fname+'&lname='+lname+'&skils='+skils,CBFunc_EmployeeSearch);
    //alert(/MirageV2/ajaxHandle/employeeSearch.action?fname='+fname+'&lname='+lname+'&skils='+skils);
    //loadXMLDoc(CONTENXT_PATH+'/ajaxHandle/employeeSearch.action?fname='+fname+'&lname='+lname+'skils='+skils,CBFunc_EmployeeSearch);
    //===========================================
    //  Local Server Configuration
    //=========================================== 
    //loadXMLDoc('http://172.17.4.167/MirageV2/AjaxHandlerServlet?from=employeeSearch&fname='+fname+'&lname='+lname,CBFunc_EmployeeSearch);
    
    //===========================================
    //  US Server Configuration
    //=========================================== 
    //loadXMLDoc('http://w3.miraclesoft.com/MirageV2/AjaxHandlerServlet?from=employeeSearch&fname='+fname+'&lname='+lname,CBFunc_EmployeeSearch);
}

//=========================================================================
//  Define the CallBack Function for the Employee Search URL Response
//=========================================================================

function CBFunc_EmployeeSearch() {
    var responseText;
    var  myHTMLTable = document.getElementById("tblUpdate");
    
    /*Cleaning Rows*/
    ClrTable(myHTMLTable);
    
    if (req.readyState == 4) {
        if (req.status == 200) {
            
            var headerFields = new Array("Employee Name","Email Address","Phone No","Home Phone No","Cell Phone","Skil Set");			
            
            if(req.responseText!=''){
                ParseAndGenerateHTML(myHTMLTable,req.responseText, headerFields);
            }else{
                //spnFast=document.getElementById("spnFast");
                //spnFast.innerText="No Result For This Search...";
                alert('No Result For This Search...');
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
    var imageRow = document.getElementById("imageTR1");
    var lastImageCell = imageRow.cells.length;
    while(lastImageCell > 0) {
        imageRow.deleteCell(lastImageCell - 1);  
        lastImageCell = imageRow.cells.length; 
    }
    
    var imageRow = document.getElementById("imageTR2");
    var lastImageCell = imageRow.cells.length;
    while(lastImageCell > 0) {
        imageRow.deleteCell(lastImageCell - 1);  
        lastImageCell = imageRow.cells.length; 
    }
    
    var nameRow = document.getElementById("nameTR1");
    var lastNameCell = nameRow.cells.length;
    while(lastNameCell > 0) {
        nameRow.deleteCell(lastNameCell - 1);  
        lastNameCell = nameRow.cells.length; 
    }
    
    var nameRow = document.getElementById("nameTR2");
    var lastNameCell = nameRow.cells.length;
    while(lastNameCell > 0) {
        nameRow.deleteCell(lastNameCell - 1);  
        lastNameCell = nameRow.cells.length; 
    }
    
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}



