//=========================================================================
//  This Function is responsible for generating the URL
//  based on all the various form variables and make sure that
//  you escape those Variable before appending to URL.
//  and then invoking the URL and passing the callback function
//=========================================================================


function load(form) {	    
    var fname = escape(document.consultantsearchForm.fullName.value);
    if(fname == ''){
        fname="nodata";        
    }
    var skillset = escape(document.consultantsearchForm.skillSet.value);
    if(skillset == ''){
        skillset="nodata";            
    }    
    var email1 = escape(document.consultantsearchForm.email1.value);
    if(email1 == ''){
        email1="nodata";         
    }
    
    var workAuthorization = escape(document.getElementById('workAuthorization').value);    
    if(workAuthorization == 'undefined'){        
        workAuthorization = "nodata";
    }
    if(workAuthorization == -1){        
        workAuthorization = "nodata";    
    }
    
    var createdBy = escape(document.getElementById('assignedTo').value);    
    if(createdBy == 'undefined'){        
        createdBy = "nodata";
    }
    if(createdBy == -1){        
        createdBy = "nodata";        
    }
    
    var source = escape(document.getElementById('source').value);    
    if(source == '') {
        source="nodata";
    }
    
    var location = escape(document.getElementById('location').value);    
    if(location == 'undefined'){        
        location = "nodata";
    }
    if(location == -1){        
        location = "nodata";        
    }
    
    var comments = escape(document.consultantsearchForm.comments.value);
    if(comments == ''){
        comments="nodata";            
    } 
    
    //document.getElementById('tblResume').style.display = 'none'; 
    document.getElementById('consultantList').style.display = 'inline'; 
    document.getElementById('resumeList').style.display = 'none'; 
    
    //loadXMLDoc(CONTENXT_PATH+'/ajaxHandle/consultantSearch.action?fname='+fname+'&skillset='+skillset+'&email='+email1+'&practiceid='+practiceid+'&workAuthor='+workAuthorization+'&createdBy='+createdBy,CBFunc_ConsultantSearch);      
    loadXMLDoc(CONTENXT_PATH+'/ajaxHandle/consultantSearch.action?fname='+fname+'&skillset='+skillset+'&email='+email1+'&source='+source+'&workAuthor='+workAuthorization+'&createdBy='+createdBy+'&location='+location+'&comments='+comments,CBFunc_ConsultantSearch);
}

//=========================================================================
//  Define the CallBack Function for the Employee Search URL Response
//=========================================================================

function CBFunc_ConsultantSearch() {
    var responseText;
    var  myHTMLTable = document.getElementById("tblUpdate");
    var  spnFast = document.getElementById("spnFast");
    
    // var  myHeadTable = document.getElementById("tableHead");
    
    /*Cleaning Rows*/
    ClrTable(myHTMLTable);
    
    if (req.readyState == 4) {
        if (req.status == 200) {
            
            var headerFields = new Array("SNo", "Name","Email Address","Mobile No","Skill Set","Resume");			
            
            var getResponseData;
            getResponseData = req.responseText;

            //alert('getResponseData******'+getResponseData);

            var temp = new Array();
            temp = getResponseData.split('addto');
            
            //alert('temp[1]>>> '+temp[1]);
            
            if(req.responseText!=''){
                document.consultantsearchForm.inputRowData.style.display="block";
                document.consultantsearchForm.inputRowData.value= "Records Found "+temp[1];
                ParseAndGenerateHTML(myHTMLTable,temp[0], headerFields,temp[1]);
                
                // myHeadTable.innerHTML = "Total Rows"+temp[1];
            }else{
                alert('No Result For This Search...');
                spnFast.innerHTML="No Result For This Search...";                
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


