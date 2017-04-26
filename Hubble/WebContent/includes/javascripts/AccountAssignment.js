/*Don't Alter these Methods*/
var opType="";

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


function readyStateHandler(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                responseTextHandler(req.responseText);
            } else {
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }
    }
}





function  populateMessage(totalData) {
    alert(totalData);
    
    document.getElementById('addlabel5').style.display = 'none'; 
    document.getElementById('addlabel2').style.display = 'block'; 
    document.searchForm.newloginId.value="";
    document.searchForm.acctype.value="3";
    document.searchForm.accountName.value="";
    document.searchForm.state.value="";
    
}

function populateRecords(totalData) {
    var str = totalData;
    var n=0;
    var n1 =0;
    var n2 =0;
   // alert("@ simble location ---"+str.indexOf("@"));
    if(str.indexOf("@")>=1){
    document.getElementById('addlabel1').style.display = 'block'; 
    var caps = new Array();
    caps = str.split("@");
    
    var records = new Array();
    
    for(var i=0;i<caps.length;i++) {
        records = caps[i].split("!");

       // alert("records.length+1"+(records.length+1));

        for(var j=0; j<records.length+1;j++) {
            n =  parseInt(n)+1;
            n1 = n+5;
            while(n2 <= n1) {
                document.getElementById('text'+n1).value= "";
                document.getElementById('text'+n1).value=records[j];
                //document.getElementById('text'+n1).value=n1;
             
                n2 = n2+1;
            }//while
        }// 2nd for  
        document.getElementById('text'+n1).value="Click To Assign";
          n2=n+2;
       
    } // End for
    
/*==================================
 * Empty Remaining Fields
 *==================================
 */
    var count=n1;
    for(k=0;k<=200-n1;k++){
        ++count;
        if(document.getElementById('text'+count)!=null){
            document.getElementById('text'+count).value="";
            n2 = n2+1;
        }else{
            break;
        }
    } 
    
    document.searchForm.newloginId.value="";
    document.searchForm.secondState.value="";
    }else{
      alert("No records to display");
      document.getElementById('addlabel1').style.display = 'none'; 
    }
    
}

function displayTable() {
    opType = document.getElementById('acctype').value;
    
    
    //if(document.getElementById('addlabel1').style.display == 'none')
    if(opType == '1') { 
        document.getElementById('addlabel3').style.display = 'block';
        document.getElementById('addlabel2').style.display = 'none'; 
        document.getElementById('addlabel6').style.display = 'none';
        document.getElementById('addlabel7').style.display = 'none';
    }
    
    if(opType == '2') { 
        document.getElementById('addlabel6').style.display = 'none';
        document.getElementById('addlabel3').style.display = 'none';
        document.getElementById('addlabel2').style.display = 'none'; 
        document.getElementById('addlabel7').style.display = 'none';
        document.getElementById('addlabel5').style.display = 'block'; 
    }
    if(opType == '3') { 
        document.getElementById('addlabel3').style.display = 'none';
        document.getElementById('addlabel2').style.display = 'none'; 
        document.getElementById('addlabel5').style.display = 'none'; 
        document.getElementById('addlabel7').style.display = 'none';
        document.getElementById('addlabel6').style.display = 'block';
        
    }
     if(opType == '5') { 
        document.getElementById('addlabel3').style.display = 'none';
        document.getElementById('addlabel2').style.display = 'none'; 
        document.getElementById('addlabel5').style.display = 'none'; 
        document.getElementById('addlabel6').style.display = 'none';
        document.getElementById('addlabel7').style.display = 'block';
        
    }
    document.getElementById('secondState').value = '';
    
}

function searchStart() {
        
    document.getElementById('accountName').value = '';
    document.getElementById('state').value = '';
    document.getElementById('addlabel3').style.display = 'none'; 
    document.searchForm.acctype.value="3"; 
    document.getElementById('addlabel4').style.display = 'none'; 
    document.getElementById('addlabel1').style.display = 'none'; 
    document.getElementById('addlabel2').style.display = 'block'; 
}




function RetriveType() {
    //alert("i am in RetriveType");
    var accountName = document.getElementById("accountName").value;
    var state =  document.getElementById("state").value;  
    if(accountName.length == 0 || state.length == 0){
      if(accountName.length == 0 )
       {
       alert("Enter Account Name");
       } 
        if(state.length == 0 )
       {
       alert("Enter state");
       }      
        //alert("Enter Required Fields");
        return false;
    }else{ 
        var secondState = "secondState";
        var newLoginId = "newloginId";
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandler(req, populateRecords);
        var url = CONTENXT_PATH+"/accountAssignment.action?accountName="+accountName+"&state="+state +"&loginId="+newLoginId +"&secondState="+secondState;
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
        document.getElementById('addlabel4').style.display = 'block'; 
        
    }
}



/*function RetriveSecondType() {
    
    var accountName = document.getElementById("accountName").value;
    var state =  document.getElementById("state").value;   
    var secondState = document.searchForm.secondState.value;
    var newLoginId = document.searchForm.newloginId.value;

    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateMessage);
    var url = CONTENXT_PATH+"/accountAssignment.action?accountName="+accountName+"&state="+state +"&loginId="+newLoginId +"&secondState="+secondState;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");   
    req.send(null);
    

    if((newLoginId != 'newloginId' && newLoginId != '') && (secondState != 'state' && secondState != '')) {
            var req = newXMLHttpRequest();
            req.onreadystatechange = readyStateHandler(req, populateMessage);
            var url = CONTENXT_PATH+"/accountAssignment.action?accountName="+accountName+"&state="+state +"&loginId="+newLoginId +"&secondState="+secondState;
            req.open("GET",url,"true");
            req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            req.send(null);
    }
    else alert('Enter LoginId & State');

}*/
 function RetriveSecondType() {
    
    var accountName = document.getElementById("accountName").value;
    var state =  document.getElementById("state").value;   
    var secondState = document.searchForm.secondState.value;
    var newLoginId = document.searchForm.newloginId.value;

    /*var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateMessage);
    var url = CONTENXT_PATH+"/accountAssignment.action?accountName="+accountName+"&state="+state +"&loginId="+newLoginId +"&secondState="+secondState;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");   
    req.send(null);*/
    

    if((newLoginId != 'newloginId' && newLoginId != '' ) && (secondState != 'state' && secondState != '')  ) {
            var req = newXMLHttpRequest();
            req.onreadystatechange = readyStateHandler(req, populateMessage);
            var url = CONTENXT_PATH+"/accountAssignment.action?accountName="+accountName+"&state="+state +"&loginId="+newLoginId +"&secondState="+secondState;
            req.open("GET",url,"true");
            req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            req.send(null);
    }
    else alert('Select New Teammember Name & State');

}




function getDetails(vals) {
    
    var input_box = confirm("Please Confirm The Data Entered is Correct");
    
    if(input_box==true) {
        
        var aciD = parseInt(vals)-parseInt(4);
        var olId = parseInt(vals)-parseInt(2);
        
    //alert("accountId ---- "+ aciD+"---------------olId  ----   "+olId);

        TeamAssignment(document.getElementById('text'+aciD).value, document.getElementById('text'+olId).value,document.getElementById('text'+(vals-1)).value);
    }
    else{
        alert("You clicked cancel");
    }
    
} // End function


function TeamAssignment(accId,oldTeamMember,newTeamMember) {
    //alert('optype'+opType);
    
    
    
    
    var type = opType;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, getUpdateResult);
    var url = CONTENXT_PATH+"/accountSearchUpdate.action?accId="+accId +"&oldTeamMember="+oldTeamMember +"&newMember="+newTeamMember +"&optType="+type;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function getUpdateResult(result) {
    var resultText=result;
    alert(result);
}

/*
 * Delete Accounts changes
 * Date : 12/03/2013
 */
function deleteEmpAccount(result) {
    var accHolderName = document.getElementById("teamMemberId").value;
    var stateAccName = document.getElementById("delStateAcc").value;
    //alert(stateAccName);
    //alert(accHolderName);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateDelete);
    var url = CONTENXT_PATH+"/stateAccountDelete.action?teamMemberId="+accHolderName +"&delStateAcc="+stateAccName;
    //alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    
}

function populateDelete(message) {
    
    //var str=message.value;
   alert(message);
        
        
}



function readyStateHandlerLoad(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("loadTransferMessage").style.display = 'none';
                responseTextHandler(req.responseText);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
            document.getElementById("loadTransferMessage").style.display = 'block';
        }
    }
}




function transferAccounts() {
    
 var fromId = document.getElementById("frmLoginId").value;
 var toId = document.getElementById("toLoginId").value;
 if(fromId.length<1 || toId.length<1){
     alert("Please select mandatory fileds!");
 }else {
    var r=confirm("Please do confirm to transfer accounts ?");
    if (r==true) {
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerLoad(req, getTransferResult);
    var url = CONTENXT_PATH+"/transferAccounts.action?frmLoginId="+fromId +"&toLoginId="+toId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    }
 }
}

function getTransferResult(resText) {
    alert(resText);
}



function RetriveSecondMarketingType() {
  //  alert("hii")
    
   // var accountName = document.getElementById("accountName").value;
    var state =  document.getElementById("secondStateMarketing").value;  
  //  alert(state)
   // var secondState = document.searchForm.secondState.value;
    var newLoginId = document.getElementById("newloginIdMarketing").value;
//alert(newLoginId)
    if((newLoginId != 'newloginId' && newLoginId != '' ) && (state != 'state' && state != '')  ) {
            var req = newXMLHttpRequest();
            req.onreadystatechange = readyStateHandler(req, populateMessage1);
            var url = CONTENXT_PATH+"/marketingAccountAssignment.action?state="+state +"&loginId="+newLoginId;
            req.open("GET",url,"true");
            req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            req.send(null);
    }
    else alert('Please insert LoginId & State');

}
function  populateMessage1(totalData) {
    //alert(totalData);
    if(totalData!="Exists")
        {
            alert(totalData);
        
   
}
else
    alert("Loginid doesn't exists");
}
