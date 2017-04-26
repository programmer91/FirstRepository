//=========================================================================
//  This Function is responsible for generating the URL
//  based on all the various form variables and make sure that
//  you escape those Variable before appending to URL.
//  and then invoking the URL and passing the callback function
//=========================================================================

function accountSearch(form) {
    //spnFast=document.getElementById("spnFast");  
    //spnFast.innerHTML = " ";

    var accountName = escape(document.searchForm.accountName.value);
    loadXMLDoc(CONTENXT_PATH+"/getAccount.action?accountName="+accountName,CBFunc_AccountSearch);
}

//=========================================================================
//  Define the CallBack Function for the Employee Search URL Response
//=========================================================================

function CBFunc_AccountSearch() {
    var responseText;
    var  myHTMLTable = document.getElementById("tblUpdate");
    var spnFast=document.getElementById("spnFast");

    /*Cleaning Rows*/
    ClrTable(myHTMLTable); 
    
    spnFast.innerHTML = '';
    if (req.readyState == 4) {
        if (req.status == 200) {
            
            var headerFields = new Array("Account Name","State ","LastModified Date");			            
            if(req.responseText!=''){                
                ParseAndGenerateHTML(myHTMLTable,req.responseText, headerFields);
            }else{

                spnFast.innerHTML="No Result For This Search...";                            
                //spnFast.innerText="No Result For This Search...";
                alert("No Records Found..");
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
    
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}


function enableEnter(e) {
    var keynum;
    var keychar;
    var numcheck;
    
    if(window.event) {
        keynum = e.keyCode;
    }
    else if(e.which) // Netscape/Firefox/Opera
    {
        keynum = e.which;
    }
    try{
        if(keynum==13){
            accountSearch();
            return false;
        }
    }catch(e){
        alert("Error"+e);
    }
};