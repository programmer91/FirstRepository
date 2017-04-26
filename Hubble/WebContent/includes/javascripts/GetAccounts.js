
/*function readyStateHandler(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadingMessage")).style.display = "none";
                responseTextHandler(req.responseText);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
            (document.getElementById("loadingMessage")).style.display = "block";
        }
    }
}
*/



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

           /** checkbox event **/
           
function addEvent(obj, evType, fn){ 
	if (obj.addEventListener){ 
   		obj.addEventListener(evType, fn, false); 
   		return true; 
 	} else if (obj.attachEvent){ 
   		var r = obj.attachEvent("on"+evType, fn); 
   		return r; 
 	} else { 
   		return false; 
 	} 
}
 
addEvent(window, 'load', initCheckboxes);
 
function initCheckboxes() {
	addEvent(document.getElementById('text'), 'click', setCheckboxes);
}
 
function setCheckboxes() {
           var cb = document.searchForm.check_List;
	for (var i = 0; i < cb.length; i++) {
		cb[i].checked = document.getElementById('text').checked;
	}
}

function setUnCheckboxes() {
           var cb = document.searchForm.check_List;
	for (var i = 0; i < cb.length; i++) {
		cb[i].checked = false;
	}
}


/***  to get the accounts  List ***/
function getAccounts(){
            //alert("hi");
var territory = document.getElementById('territory').value;
var state1 = document.getElementById('state1').value;
var state2 = document.getElementById('state2').value;
var state3 = document.getElementById('state3').value;
var state4 = document.getElementById('state4').value;
var state5 = document.getElementById('state5').value;
var region = document.getElementById('region').value;
var accountName = document.getElementById('accountName').value;
if(accountName == " "|| accountName == ""){
  document.getElementById('accountList').style.display = 'none';
  document.getElementById('assignButton').style.display = 'none';
  alert("Please Enter Account Name!");
//return false;
}/*else if(accountName.length < 2){
 alert("Please Enter More then 3 characters in Account Name!");
}*/
else{
//getAccountsList();
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandler(req, populateRecords);
        var url = CONTENXT_PATH+"/getAccounts.action?accountName="+accountName+"&state1="+state1+"&state2="+state2 +"&state3="+state3+"&state4="+state4+"&state5="+state5+"&territory="+territory+"&region="+region;
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
        document.getElementById('addlabel4').style.display = 'block'; 

}
}

function populateRecords(totalData){
    var str = totalData;
    var n=0;
    var n1 =0;
    var n2 =0;
   //alert("@ simble location ---"+str.indexOf("@"));
    if(str.indexOf("@")>=1){
       document.getElementById('accountList').style.display = 'block'; 
       document.getElementById('assignButton').style.display = 'block'; 
          for(var r=1;r<=30;r++){
                document.getElementById('gridRow_'+r).style.display = 'block'; 
          }
        var caps = new Array();
       caps = str.split("@");
   //-----------------------------------------------------------------  populating records  */
        var ids = new Array();
        var accountNames = new Array();
        ids = caps[0].split("!");
        accountNames = caps[1].split("!");
        var n1=0;
    for(var j=0; j<ids.length;j++) {
        n1 = parseInt(j)+1;
        document.getElementById('text'+n1).value= ids[j];
        document.getElementById('texta'+n1).value= accountNames[j];
    }


/*==================================
 * Empty Remaining Fields
 *==================================
 */
    var count=n1;
    var s = 0;
    var res=0;
    for(k=0;k<=100-n1;k++){
        s= parseInt(count)+parseInt(k);
        if(document.getElementById('text'+s)!=null){
            document.getElementById('text'+s).value="";
           // alert(s);
            document.getElementById('gridRow_'+s).style.display = 'none'; 
        }else{
            break;
        }
        if(document.getElementById('texta'+s)!=null){
            document.getElementById('texta'+s).value="";
        }else{
            break;
        } 
    } 
    }else{
      alert("No records to display");
      document.getElementById('accountList').style.display = 'none'; 
      document.getElementById('assignButton').style.display = 'none'; 
    }   
}


function getData(chk){
var resultString = "";
//var accountNames = "";
//alert("hi"+chk.length);
for(i=0;i<30;i++){
// alert("check-->"+i+"---value--->"+document.searchForm.check_List[i].checked);
var res = document.searchForm.check_List[i].checked;
//alert("res--->"+res);
if(res == true){
//alert("inif--->"+i);
 var k = i + 1;
 var resultString1 = document.getElementById('text'+k).value;
//var accName = document.getElementById('texta'+k).value;
 //alert("resultString"+resultString);
 resultString = resultString.concat(resultString1,'!');
//accountNames =  accountNames.concat(accName,'@');
}
}
if(resultString.indexOf("!") > 1){         
getAccountDetails(resultString);
//getAccountDetails(resultString, accountNames);
}else{
alert("Please select Accounts which you want assign to you!");
}
} 


function getAccountDetails(vals){  
//function getAccountDetails(vals, accountNamesString) {
      var input_box = confirm("Please Confirm The Data Entered is Correct"); 
     if(input_box==true) {
          //alert("vals-->"+vals);
         // var aciD = parseInt(vals)-parseInt(2);
         // var olId = parseInt(vals)-parseInt(1);
         //BdmAssignment(document.getElementById('text'+aciD).value);
            BdmAssignment(vals);  
           // BdmAssignment(vals, accountNamesString); 
     }
     else{
       alert("You clicked cancel");
     }
}

function BdmAssignment(accountId){
//function BdmAssignment(accountId, accNames){
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, getUpdateResult);
   var url = CONTENXT_PATH+"/accountUpdateForBdm.action?accId="+accountId;
    //var url = CONTENXT_PATH+"/accountUpdateForBdm.action?accId="+accountId+"&accountName="+accNames;
 // alert("url--->"+url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null); 
}

function getUpdateResult(result) {
    var resultText=result;
    alert(result);
    setUnCheckboxes();
    getAccounts();
}