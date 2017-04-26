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

//START--This part for EmpAddress page

function readyStateHandler(req,responseXmlHandler,typeAdd) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                if(typeAdd=='hotel'){
                    responseXmlHandler(req.responseXML);
                }
                if(typeAdd =='curProj'){
                    responseXmlHandler(req.responseXML);
                }
                if(typeAdd =='offShor'){
                    responseXmlHandler(req.responseXML);
                }
                if(typeAdd =='payRoll'){
                    responseXmlHandler(req.responseXML);
                }
                if(typeAdd =='home'){
                    responseXmlHandler(req.responseXML);
                }
                if(typeAdd =='other'){
                    responseXmlHandler(req.responseXML);
                }
            } else {
                alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }
    }
}

//THIS IS FOR OFFSHORE ADDRESS

function getOffShoreStates(ds) {
    //var country = document.getElementById("offShoreCountry").value;     
    var req = newXMLHttpRequest();
    var typeAdd="offShor";
    req.onreadystatechange = readyStateHandler(req, populateOffShore,typeAdd);
    var url = CONTENXT_PATH+"/getStateData.action?country="+ds.value;
    req.open("GET",url,"true");
    req.send(null);
}

function populateOffShore(resXML) {

    var stateId = document.getElementById("offShoreState"); 
    var country = resXML.getElementsByTagName("COUNTRY")[0];
    var states = country.getElementsByTagName("STATE");
    stateId.innerHTML="";
    for (var i=0; i<states.length; i++) {
        var stateName = states[i]; 
        var name = stateName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",name);
        opt.appendChild(document.createTextNode(name));
        stateId.appendChild(opt);
    }
}

//THIS IS FOR PAYROLL ADDRESS

function getPayrollStates(ds) {
    //var country = document.getElementById("offShoreCountry").value;     
    var req = newXMLHttpRequest();
    var typeAdd="payRoll";
    req.onreadystatechange = readyStateHandler(req, populatePayroll,typeAdd);
    var url = CONTENXT_PATH+"/getStateData.action?country="+ds.value;
    req.open("GET",url,"true");
    req.send(null);
}

function populatePayroll(resXML) {
    var stateId = document.getElementById("payrollState");   
    var country = resXML.getElementsByTagName("COUNTRY")[0];
    var states = country.getElementsByTagName("STATE");
    stateId.innerHTML="";
    for (var i=0; i<states.length; i++) {
        var stateName = states[i]; 
        var name = stateName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",name);
        opt.appendChild(document.createTextNode(name));
        stateId.appendChild(opt);
    }
}

//THIS IS FOR CURRENT PROJECT ADDRESS

function getCprojectStates(ds) {
    
    //var country = document.getElementById("offShoreCountry").value;     
    var req = newXMLHttpRequest();
    var typeAdd="curProj";
    req.onreadystatechange = readyStateHandler(req, populateCurPorj,typeAdd);
    var url = CONTENXT_PATH+"/getStateData.action?country="+ds.value;
    req.open("GET",url,"true");
    req.send(null);
}

function populateCurPorj(resXML) {
    var stateId = document.getElementById("cprojectState");   
    var country = resXML.getElementsByTagName("COUNTRY")[0];
    var states = country.getElementsByTagName("STATE");
    stateId.innerHTML="";
    for (var i=0; i<states.length; i++) {
        var stateName = states[i]; 
        var name = stateName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",name);
        opt.appendChild(document.createTextNode(name));
        stateId.appendChild(opt);
    }
}

//THIS IS FOR HOME ADDRESS

function getHomeStates(ds) {
    //var country = document.getElementById("offShoreCountry").value;     
    var req = newXMLHttpRequest();
    var typeAdd="home";
    req.onreadystatechange = readyStateHandler(req, populateHome,typeAdd);
    var url = CONTENXT_PATH+"/getStateData.action?country="+ds.value;
    req.open("GET",url,"true");
    req.send(null);
}

function populateHome(resXML) {
    var stateId = document.getElementById("homeState");   
    var country = resXML.getElementsByTagName("COUNTRY")[0];
    var states = country.getElementsByTagName("STATE");
    stateId.innerHTML="";
    for (var i=0; i<states.length; i++) {
        var stateName = states[i]; 
        var name = stateName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",name);
        opt.appendChild(document.createTextNode(name));
        stateId.appendChild(opt);
    }
}

//THIS IS FOR HOTEL ADDRESS

function getHotelStates(ds) {
    //var country = document.getElementById("offShoreCountry").value;     
    var req = newXMLHttpRequest();
    var typeAdd="hotel";
    req.onreadystatechange = readyStateHandler(req, populateHotel,typeAdd);
    var url = CONTENXT_PATH+"/getStateData.action?country="+ds.value;
    req.open("GET",url,"true");
    req.send(null);
}

function populateHotel(resXML) {
    var stateId = document.getElementById("hotelState");   
    var country = resXML.getElementsByTagName("COUNTRY")[0];
    var states = country.getElementsByTagName("STATE");
    stateId.innerHTML="";
    for (var i=0; i<states.length; i++) {
        var stateName = states[i]; 
        var name = stateName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",name);
        opt.appendChild(document.createTextNode(name));
        stateId.appendChild(opt);
    }
}

//THIS IS FOR OTHER ADDRESS

function getOtherStates(ds) {
    //var country = document.getElementById("offShoreCountry").value;     
    var req = newXMLHttpRequest();
    var typeAdd="other";
    req.onreadystatechange = readyStateHandler(req, populateOther,typeAdd);
    var url = CONTENXT_PATH+"/getStateData.action?country="+ds.value;
    req.open("GET",url,"true");
    req.send(null);
}

function populateOther(resXML) {
    var stateId = document.getElementById("otherState");   
    var country = resXML.getElementsByTagName("COUNTRY")[0];
    var states = country.getElementsByTagName("STATE");
    stateId.innerHTML="";
    for (var i=0; i<states.length; i++) {
        var stateName = states[i]; 
        var name = stateName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",name);
        opt.appendChild(document.createTextNode(name));
        stateId.appendChild(opt);
    }
}

function readyStateHandlerAS(req,responseHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("loadActMessageAS").style.display = 'none';
                responseHandler(req.responseText);
            }
        } else {
            document.getElementById("loadActMessageAS").style.display = 'block';
        //alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }
}
//END--This part for EmpAddress page
function checkEmailExistsOrNot()
{
    //alert("")
    var email = document.getElementById("email").value;
    var emailCheck =new Array();
    emailCheck = email.split("@");
   // alert(emailCheck[1])
    if(emailCheck[1]!="miraclesoft.com")
        {
            alert("please provide miraclesoft mail id");
            return false;
        }
       
        
    //alert(email);
      var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerAS(req, displayEmailMessage);
    var url = CONTENXT_PATH+"/checkEmail.action?email="+email;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function displayEmailMessage(resText) {
    //alert(resText);
    if(resText=="Existed")
    document.getElementById("resultMessage").innerHTML="Email already exists";
else
    document.getElementById("resultMessage").innerHTML="Email doesn't exist";
}
//END--This part for EmpAddress page
function checkEmail(element){if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(element.value)){return(true);
 }element.value="";
 alert("Invalid E-mail Address! Please re-enter.");
 return(false);
 }
  function isCafeteria(){
     
     var cafeteria = $("input[name='cafeteria']:checked").val();
     if(cafeteria=='No'){
          document.getElementById('cafeteriaFee').value="";
          $('#cafeteriaFee').attr("disabled", true);	
          
     }else{
           $('#cafeteriaFee').attr("disabled", false);	
     }
    
 }
 function isTransportation(){
     
     var transportation = $("input[name='transportation']:checked").val();
     if(transportation=='No'){
         document.getElementById('transportLocation').value="";
          document.getElementById('transportFee').value="";
          $('#transportLocation').attr("disabled", true);	
          $('#transportFee').attr("disabled", true);	
     }else{
         
           $('#transportLocation').attr("disabled", false);	
           $('#transportFee').attr("disabled", false);	
     }
    
 }

 
 
 function expensesValues()
 {
     var id=document.getElementById("id").value;
     window.location="./expensesCheck.action?id="+id+"&flag=copy";
 }
 
  function expensesValidatator()
 {
     var accomdation=document.getElementById('homeState').value;
     var dateOfOccupancy=document.getElementById('dateOfOccupancy').value;
     var roomNo=document.getElementById('roomNo').value;
     var cafeteriaFee=document.getElementById('cafeteriaFee').value;
     var transportFee=document.getElementById('transportFee').value;
     var transportLocation=document.getElementById('transportLocation').value;
      var roomFee=document.getElementById('roomFee').value;    
      var electricalCharges=document.getElementById('electricalCharges').value;
      var occupancyType=document.getElementById('occupancyType').value;
     var cafeteria = $("input[name='cafeteria']:checked").val();
     var transportation = $("input[name='transportation']:checked").val();                  
         if(accomdation=="" && cafeteria=='No' && transportation=='No')
             {
                  document.getElementById("expensesResultMsg").innerHTML="<font color='red' size='2'>Please enter atleast Accomdation or Cafeteria or Transportation details.</font>";                
                 return false;
             }
         
         if(accomdation!=''){
             if(dateOfOccupancy==""){
                  document.getElementById("expensesResultMsg").innerHTML="<font color='red' size='2'>Please enter Occupancy date.</font>";                  
                   return false;
               }
               if(occupancyType==""){
                    document.getElementById("expensesResultMsg").innerHTML="<font color='red' size='2'>Please Select Occupancy Type.</font>";                   
                   return false;
               }
               
             if(roomNo==""){
                  document.getElementById("expensesResultMsg").innerHTML="<font color='red' size='2'>Please Enter Room No.</font>";
                   
                   return false;
               }
                if(roomFee=="" || roomFee=="0.0"){
                    document.getElementById("expensesResultMsg").innerHTML="<font color='red' size='2'>Please Enter Room Fee.</font>"; 
                 
                   return false;
               }
               if(cafeteria=='No'){
                if(electricalCharges=="" || electricalCharges=="0.0"){
                     document.getElementById("expensesResultMsg").innerHTML="<font color='red' size='2'>Please Enter Electrical Charges.</font>";
                  
                   return false;
               }
               }
               
         }
           var cafeteria = $("input[name='cafeteria']:checked").val();
           if(cafeteria=='Yes'){
               if(cafeteriaFee=="" || cafeteriaFee=="0.0"){
                    document.getElementById("expensesResultMsg").innerHTML="<font color='red' size='2'>Please Enter CafeteriaFee.</font>";
                 
                   return false;
               }
               
           }
           
            var transportation = $("input[name='transportation']:checked").val();
            if(transportation=='Yes'){
                 if(transportLocation==""){
                 
                  document.getElementById("expensesResultMsg").innerHTML="<font color='red' size='2'>Please Select Transport Location.</font>";
                   return false;
               }
                if(transportFee=="" || transportFee=="0.0"){
                     document.getElementById("expensesResultMsg").innerHTML="<font color='red' size='2'>Please Enter TransportFee.</font>";
                 
                   return false;
               }
            }
           
 }
 
  function selectedAccomdation(){      
    var accomdation=document.getElementById('homeState').value;  
    if(accomdation=='')
    {          
        $('#dateOfOccupancy').attr("disabled", true);
        $('#roomNo').attr("disabled", true);
        $('#roomFee').attr("disabled", true);
        $('#electricalCharges').attr("disabled", true);       
         $('#occupancyType').attr("disabled", true);
         document.getElementById("dateOfOccupancy").value="";
        document.getElementById('roomNo').value="";       
        document.getElementById('roomFee').value="0.0";       
        document.getElementById('electricalCharges').value="0.0";
        document.getElementById('occupancyType').value="";
     }
     else{
         
          $('#dateOfOccupancy').attr("disabled", false);
        $('#roomNo').attr("disabled", false);
        $('#roomFee').attr("disabled", false);
        $('#electricalCharges').attr("disabled", false);           
        $('#occupancyType').attr("disabled", false);
        
     }

}
