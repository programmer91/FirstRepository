// This is a javascript file
function validateDates() {    
    
    var startDate = document.getElementById('startDate').value;             
    var endDate = document.getElementById('endDate').value;
    
    if(startDate.length==0 || endDate.length==0){
        alert("Start-date and End-date are required.");
        return false;
    }else{
        return true;
    }
    
};

function validateDates2() {    
    var startDate = document.getElementById('startDate').value;             
    var endDate = document.getElementById('endDate').value;
    var radios= document.forms['bDate'].elements['listby'];
    for (var i=0; i <radios.length; i++) {
        if (radios[i].checked) {
            if((startDate.length==0 || endDate.length==0) && document.bDate.listby[i].value==4 ){
                alert("Start-date and End-date are required.");
                return false;
            }else{
                return true;
            }
        }
       
    }
   
  
};


function checkEnable(){
    var checkbox_all=document.getElementById('all_users').checked;               
    
    if(checkbox_all==true){
        document.getElementById('user_login').disabled=true;
    }
    if(checkbox_all==false){
        document.getElementById('user_login').disabled=false;
    }
};

function compareDates(start,end){         
    var startDate = start;             
    var endDate =end;
    var sDate=new Date(startDate);            
    var eDate=new Date(endDate);
    if(sDate>eDate){
        alert("StartDate must lessthan EndDate");
        return false;}
    else
        return true;
};

function checkOption(){
    var name=document.getElementById('username').value;
    
    if(name.length!=0 & document.getElementById('all_users').checked){
        alert("Invalid selection. Please select only one option");
        document.getElementById('all_users').checked = false;
        return false;
    }
    else
        return true;
    
};

function checkOption1(){
    var name=document.getElementById('username').value;
    
    if((name.length!=0 & document.getElementById('all_users').checked) || (name.length!=0 & document.getElementById('all_emp').checked) || (document.getElementById('all_users').checked & document.getElementById('all_emp').checked)){
        alert("Invalid selection. Please select only one option");
        return false;
    }
    else
        return true;
    
};