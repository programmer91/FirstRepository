 function readyStateHandler(req,responseHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("loadActMessage").style.display = 'none';
                responseHandler(req.responseText);
            }
        }
        else {
            document.getElementById("loadActMessage").style.display = 'block';
        //alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }
}

function showWeight_div(type,timeFlag) {
    //  alert("in showwiehht method");
    var memberId= document.getElementById("memberId").value;
    document.getElementById("reqMap").value=timeFlag;
    //alert(memberId)
  //  var req = new XMLHttpRequest();
   // req.onreadystatechange = readyStateHandler(req,populateWeightMap);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/showWeightMap.action?memberId="+memberId+"&timeFlag="+timeFlag;
     var req = initRequest(url);
    req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
populateWeightMap(req.responseText,type);
                    } 
                }
            };
    //  alert(url)
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateWeightMap(response,type){
    //respone;
   // alert(type);
   
    if(response!="" || response != null){
        document.getElementById("loadActMessage").style.display="none";
        weightChart(response,type);
    }else{
        document.getElementById("loadActMessage").style.display="none";
        document.getElementById("weight_div").innerHTML="no records to display";
    }
}

function showBloodPressure_div(type,timeFlag) {
    //alert("in showwiehht method");
    var memberId= document.getElementById("memberId").value;
     document.getElementById("reqMap").value=timeFlag;
    //var req = new XMLHttpRequest();
   // req.onreadystatechange = readyStateHandler(req,populateBpMap);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/showBpMap.action?memberId="+memberId+"&timeFlag="+timeFlag;
     var req = initRequest(url);
    req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
populateBpMap(req.responseText,type);
                    } 
                }
            };
    //  alert(url)
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateBpMap(response,type){
    //respone;
  //   alert("bp--------->"+type);
   
     if(response!="" || response != null)
    {
        document.getElementById("loadActMessageBP").style.display="none";
        bpChart(response,type);
    }
    else
    {
        document.getElementById("loadActMessageBP").style.display="none";
        document.getElementById("bp_div").innerHTML="no records to display";
    }
}

function showGlucoseLevelMap_div(type,timeFlag) {
    //alert("in showwiehht method");
      var memberId= document.getElementById("memberId").value;
       document.getElementById("reqMap").value=timeFlag;
   // var req = new XMLHttpRequest();
    //req.onreadystatechange = readyStateHandler(req,populateGlucoseLevelMap);    
    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/showGlucoseLevelMap.action?memberId="+memberId+"&timeFlag="+timeFlag;
    var req = initRequest(url);
    req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
populateGlucoseLevelMap(req.responseText,type);
                    } 
                }
            };
    //  alert(url)
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateGlucoseLevelMap(response,type){
    //respone;
   //alert("glu---->"+type)
 
    if(response!="" || response != null)
    {
        document.getElementById("loadActMessageGL").style.display="none";
        glucoseChart(response,type);
    }
    else
    {
        document.getElementById("loadActMessageGL").style.display="none";
        document.getElementById("glucose_div").innerHTML="no records to display";
    }
}
function showLipidsOrCholestrolMap_div(type,timeFlag) {
    //alert("in showwiehht method");
     var memberId= document.getElementById("memberId").value;
      document.getElementById("reqMap").value=timeFlag;
    //var req = new XMLHttpRequest();
    //req.onreadystatechange = readyStateHandler(req,populateLipidsorCholestrolMap);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/showLipidsOrCholestrolMap.action?memberId="+memberId+"&timeFlag="+timeFlag;
     var req = initRequest(url);
    req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
populateLipidsorCholestrolMap(req.responseText,type);
                    } 
                }
            };
    //  alert(url)
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateLipidsorCholestrolMap(response,type){
    //respone;
   // alert("lipids---->"+type)
 
    if(response!="" || response != null)
    {
        document.getElementById("loadActMessageLC").style.display="none";
        lcChart(response,type);
    }
    else
    {
        document.getElementById("loadActMessageLC").style.display="none";
        document.getElementById("lc_div").innerHTML="no records to display";
    }
}

function weightChart(response,type) {
    var vmonths = new Object();
    vmonths=vmonthsMap();
    var result = response.split("^");
   var arraydata = [['Month', 'Weight','Target_Weight']];
    for(var i=0; i<result.length-1; i++){
        var res = result[i].split("|");
         var res1=result[i].split("~");
        var dArray = [vmonths[res[0]],parseInt(res[1].split(" ")[0]),parseInt(res1[1])];
        arraydata.push(dArray);
    }
   /* var arraydata = [];
    arraydata = dataArray;*/
    //alert(arraydata);
    var data = google.visualization.arrayToDataTable(arraydata);
    var chart = new google.visualization.LineChart(document.getElementById('weight_div'));
    if(type=="0"){
    var options = {
        'legend': {
            'position': 'top' 
        },
     'chartArea': {'width': '90%', 'height': '77%','left':'20%'},
          hAxis: { maxValue: 7 },
           vAxis: { viewWindowMode:'explicit',min:170,max: 220 },ticks:[170,180,190,200,210,220]   ,
        pointSize: 5,
        pointShape: 'circle',
         colors:['#8abaca','#97d381'] 
    };}
else
    {
         var options = {
        'legend': {
            'position': 'top' 
        },
     'chartArea': {'width': '90%', 'height': '77%','left':'20%'},
          hAxis: { maxValue: 7 },
          vAxis: { viewWindowMode:'explicit',viewWindow:{min:170,max: 220},ticks:[170,180,190,200,210,220]    },
        pointSize: 5,
        pointShape: 'circle',
         colors:['#8abaca','#97d381'] 
    };
    }
    chart.draw(data,options);
    if(type!="0"){
         var memberId= document.getElementById("memberId").value;
   // alert("memberid to get details -->"+memberId);
    var req = new XMLHttpRequest();
   req.onreadystatechange = readyStateHandler(req,populateWeightDeialsMap);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/getMapDetails.action?memberId="+memberId+"&recordType="+type;
    
    //  alert(url)
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    }
}

function populateWeightDeialsMap(response){
    //alert(response);
   var records=response.split("^");
    var weightDetails='<table><tr><td style="text-align: left;"> <font style="vertical-align: middle;font-family:myHeaderFonts;color: #367074;font-size:small;font-weight: 900;">'
                                                      +'  Latest&nbsp;Entries</font></td></tr>';
   for(var i=0;i<records.length-1;i++){
       if(i%2==0)
       weightDetails = weightDetails +'<tr style=""><td style="height:19px;width:300px;text-align:left;"><font style="vertical-align: middle;font-family:myHeaderFonts;color: #367074;font-size:small;font-weight:700;">'+records[i].split("|")[0]+'&nbsp;-&nbsp;'+records[i].split("|")[1]+'</font></td></tr>';
   else
        weightDetails = weightDetails +'<tr style="background:#ebebeb;"><td  style="height:19px;width:300px;text-align:left;"><font style="vertical-align: middle;font-family:myHeaderFonts;color: #367074;font-size:small;font-weight:700;">'+records[i].split("|")[0]+'&nbsp;-&nbsp;'+records[i].split("|")[1]+'</font></td></tr>';
   }
  weightDetails=weightDetails+'</table>';
   document.getElementById("rightContentBp").innerHTML="";
   document.getElementById("rightContentGl").innerHTML="";
   document.getElementById("rightContentLipids").innerHTML="";
   document.getElementById("rightContentWeight").innerHTML=weightDetails;
}
 
 
function bpChart(response,type) {
   // alert(response)
      var vmonths = new Object();
    vmonths=vmonthsMap();
    var result = response.split("^");
    var arraydata = [['Month', 'SYSTOLIC_BLOOD_PRESSURE','MAX_SYSTOLIC_BLOOD_PRESSURE','DIASTOLIC_BLOOD_PRESSURE','MAX_DIASTOLIC_BLOOD_PRESSURE']];
  // var arraydata = [['Month', 'SYSTOLIC_BLOOD_PRESSURE']];
    for(var i=0; i<result.length-1; i++){
        var res = result[i].split("|");
      var dArray = [vmonths[res[0]],parseInt(res[1].split(" ")[0]),parseInt(res[2].split(" ")[0]),parseInt(res[3].split(" ")[0]),parseInt(res[4].split(" ")[0])];
   //     var dArray = [vmonths[res[0]],parseInt(res[1].split(" ")[0])];
        arraydata.push(dArray);
    }
console.log(arraydata);
    var chart = new google.visualization.LineChart(document.getElementById('bp_div'));
  
    var data = google.visualization.arrayToDataTable(arraydata);
  if(type=="0"){
    var options = {
        'legend': {
            'position': 'top' 
        },
     'chartArea': {'width': '90%', 'height': '77%','left':'20%'},
          hAxis: { maxValue: 7 },
          vAxis: {  viewWindowMode:'explicit',
                      viewWindow:{                
                      min:75,max: 155 },ticks:[75,91,107,123,139,155] },

        pointSize: 5,
        pointShape: 'circle',
        colors:['#8bbebe','#97d381','#c68368','#97d381']
    };}
else
    {
         var options = {
        'legend': {
            'position': 'top' 
        },
     'chartArea': {'width': '90%', 'height': '77%','left':'20%'},
          hAxis: { maxValue: 7 },
        vAxis: {  viewWindowMode:'explicit',
                      viewWindow:{                
                      min:75,max: 155 },ticks:[75,91,107,123,139,155] },

        pointSize: 5,
        pointShape: 'circle',
        colors:['#8bbebe','#97d381','#c68368','#97d381']
    };
    }
    chart.draw(data,options);
    if(type!="0"){
         var memberId= document.getElementById("memberId").value;
    //alert("memberid to get details -->"+memberId);
    var req = new XMLHttpRequest();
   req.onreadystatechange = readyStateHandler(req,populateBpDeialsMap);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/getMapDetails.action?memberId="+memberId+"&recordType="+type;
    
    //  alert(url)
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    }
}
      
    function populateBpDeialsMap(response){
   // alert("bp========"+response);
   var records=response.split("^");
       var bpDetails='<table><tr><td style="text-align: left;"> <font style="vertical-align: middle;font-family:myHeaderFonts;color: #367074;font-size:small;font-weight: 900;">'
                                                      +'  Latest&nbsp;Entries&nbsp;-&nbsp;Systolic/Diastolic</font></td></tr>';
   for(var i=0;i<records.length-1;i++){
       if(i%2==0)
       bpDetails = bpDetails +'<tr style=""><td style="height:19px;width:300px;text-align:left;"><font style="vertical-align: middle;font-family:myHeaderFonts;color: #367074;font-size:small;font-weight:700;">'+records[i].split("|")[0]+'&nbsp;-&nbsp;'+records[i].split("|")[1]+'</font></td></tr>';
   else
        bpDetails = bpDetails +'<tr style="background:#ebebeb;"><td  style="height:19px;width:300px;text-align:left;"><font style="vertical-align: middle;font-family:myHeaderFonts;color: #367074;font-size:small;font-weight:700;">'+records[i].split("|")[0]+'&nbsp;-&nbsp;'+records[i].split("|")[1]+'</font></td></tr>';
   }
   bpDetails=bpDetails+' </table>';
 
   document.getElementById("rightContentGl").innerHTML="";
   document.getElementById("rightContentLipids").innerHTML="";
   document.getElementById("rightContentWeight").innerHTML="";
   document.getElementById("rightContentBp").innerHTML=bpDetails;
}  
/** glucose **/
      
      
      
function glucoseChart(response,type) {
     var vmonths = new Object();
    vmonths=vmonthsMap();
    var result = response.split("^");
    var arraydata = [['Month', 'GLUCOSE','Target_Glucose']];
    for(var i=0; i<result.length-1; i++){
        var res = result[i].split("|");
        var res1=result[i].split("~");
        var dArray = [vmonths[res[0]],parseInt(res[1].split(" ")[0]),parseInt(res1[1])];
   
        arraydata.push(dArray);
    }
   /* var arraydata = [];
    arraydata = dataArray;*/
    //alert(arraydata);
    var data = google.visualization.arrayToDataTable(arraydata);
    //var chart = new google.visualization.ImageLineChart(document.getElementById('glucose_div'));
    var chart = new google.visualization.LineChart(document.getElementById('glucose_div'));
   if(type=="0"){
    var options = {
        'legend': {
            'position': 'top' 
        },
     'chartArea': {'width': '90%', 'height': '77%','left':'20%'},
          hAxis: { maxValue: 7 },
          vAxis: { viewWindowMode:'explicit',
                      viewWindow:{                
                      min:90,max: 140}},ticks:[90,100,110,120,130,140]  
 ,
        pointSize: 5,
        pointShape: 'circle',
           colors:['#8abaca','#97d381']  
    };}
else
    {
         var options = {
        'legend': {
            'position': 'top' 
        },
     'chartArea': {'width': '90%', 'height': '77%','left':'20%'},
          hAxis: { maxValue: 7 },
          vAxis: {viewWindowMode:'explicit',
                      viewWindow:{                
                      min:90,max: 140}},ticks:[90,100,110,120,130,140] 
 ,
        pointSize: 5,
        pointShape: 'circle',
           colors:['#8abaca','#97d381'] 
    };
    }
    chart.draw(data,options);
     if(type!="0"){
         var memberId= document.getElementById("memberId").value;
    //alert("memberid to get details -->"+memberId);
    var req = new XMLHttpRequest();
   req.onreadystatechange = readyStateHandler(req,populateGlDeialsMap);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/getMapDetails.action?memberId="+memberId+"&recordType="+type;
    
    //  alert(url)
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    }
}
      
    function populateGlDeialsMap(response){
   // alert("Gl========"+response);
   var records=response.split("^");
     var glDetails='<table><tr><td style="text-align: left;"> <font style="vertical-align: middle;font-family:myHeaderFonts;color: #367074;font-size:small;font-weight: 900;">'
                                                      +'  Latest&nbsp;Entries</font></td></tr>';
   for(var i=0;i<records.length-1;i++){
       if(i%2==0)
       glDetails = glDetails +'<tr style=""><td style="height:19px;width:300px;text-align:left;"><font style="vertical-align: middle;font-family:myHeaderFonts;color: #367074;font-size:small;font-weight:700;">'+records[i].split("|")[0]+'&nbsp;-&nbsp;'+records[i].split("|")[1]+'</font></td></tr>';
   else
        glDetails = glDetails +'<tr style="background:#ebebeb;"><td  style="height:19px;width:300px;text-align:left;"><font style="vertical-align: middle;font-family:myHeaderFonts;color: #367074;font-size:small;font-weight:700;">'+records[i].split("|")[0]+'&nbsp;-&nbsp;'+records[i].split("|")[1]+'</font></td></tr>';
   }
     glDetails=glDetails+' </table>';
   document.getElementById("rightContentGl").innerHTML=glDetails;
   document.getElementById("rightContentLipids").innerHTML="";
   document.getElementById("rightContentWeight").innerHTML="";
   document.getElementById("rightContentBp").innerHTML="";

}   
      
/** lipids/cholesterol**/
      
      
      
function lcChart(response,type) {
   // CREATED_DATE,LDL,HDL,TRIGLYCERIDES lc_div
       var vmonths = new Object();
    vmonths=vmonthsMap();
    var result = response.split("^");
    var arraydata = [['Month', 'LDL','HDL','TRIGLYCERIDES']];
    for(var i=0; i<result.length-1; i++){
        var res = result[i].split("|");
        var dArray = [vmonths[res[0]],parseInt(res[1].split(" ")[0]),parseInt(res[2].split(" ")[0]),parseInt(res[3].split(" ")[0])];
        arraydata.push(dArray);
    }
   /* var arraydata = [];
    arraydata = dataArray;*/
    //alert(arraydata);
    var data = google.visualization.arrayToDataTable(arraydata);
    var chart = new google.visualization.LineChart(document.getElementById('lc_div'));
   //var options = {'legend': 'none','width': '300','height': '200','hAxis': '{ minValue: 0, maxValue: 9 }','pointSize': '5','pointShape': 'circle','colors':'violet'};
     if(type=="0"){
    var options = {
        'legend': {
            'position': 'top' 
        },
     'chartArea': {'width': '90%', 'height': '77%','left':'20%'},
          hAxis: { maxValue: 7 },
          vAxis: { viewWindowMode:'explicit',
                      viewWindow:{                
                      min:40,max: 270}, ticks:[40,86,132,178,224,270]  
 },
        pointSize: 5,
        pointShape: 'circle',
         colors: ['#8bb8cb','#e6d792','#cf7267']
    };}
else
    {
         var options = {
        'legend': {
            'position': 'top' 
        },
     'chartArea': {'width': '90%', 'height': '77%','left':'20%'},
          hAxis: { maxValue: 7 },
          vAxis: {viewWindowMode:'explicit',
                      viewWindow:{                
                      min:40,max: 270}, ticks:[40,86,132,178,224,270]  
},
        pointSize: 5,
        pointShape: 'circle',
          colors: ['#8bb8cb','#e6d792','#cf7267'] 
    };
    }
    chart.draw(data,options);
  if(type!="0"){
         var memberId= document.getElementById("memberId").value;
   // alert("memberid to get details -->"+memberId);
    var req = new XMLHttpRequest();
   req.onreadystatechange = readyStateHandler(req,populatelipidsDeialsMap);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/getMapDetails.action?memberId="+memberId+"&recordType="+type;
    
    //  alert(url)
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    }
}
      
    function populatelipidsDeialsMap(response){
    //alert("Gl========"+response);
   var records=response.split("^");
   var lipidsDetails='<table><tr><td style="text-align: left;"> <font style="vertical-align: middle;font-family:myHeaderFonts;color: #367074;font-size:x-small;font-weight: 900;">'
                                                      +'  Latest&nbsp;Entries&nbsp;-&nbsp;LDL/HDL/TRIGLYCERIDES</font></td></tr>';
   for(var i=0;i<records.length-1;i++){
       if(i%2==0)
       lipidsDetails = lipidsDetails +'<tr style=""><td style="height:19px;width:300px;text-align:left;"><font style="vertical-align: middle;font-family:myHeaderFonts;color: #367074;font-size:small;font-weight:700;">'+records[i].split("|")[0]+'&nbsp;-&nbsp;'+records[i].split("|")[1]+'</font></td></tr>';
   else
        lipidsDetails = lipidsDetails +'<tr style="background:#ebebeb;"><td  style="height:19px;width:300px;text-align:left;"><font style="vertical-align: middle;font-family:myHeaderFonts;color: #367074;font-size:small;font-weight:700;">'+records[i].split("|")[0]+'&nbsp;-&nbsp;'+records[i].split("|")[1]+'</font></td></tr>';
   }
   lipidsDetails=lipidsDetails+'</table>';
   document.getElementById("rightContentGl").innerHTML="";
   document.getElementById("rightContentLipids").innerHTML=lipidsDetails;
   document.getElementById("rightContentWeight").innerHTML="";
   document.getElementById("rightContentBp").innerHTML="";

}   

function vmonthsMap(){
    var map = new Object();
    map["1"] = "Jan";
    map["2"] = "Feb";
    map["3"] = "Mar";
    map["4"] = "Apr";
    map["5"] = "May";
    map["6"] = "Jun";
    map["7"] = "Jul";
    map["8"] = "Aug";
    map["9"] = "Sep";
    map["10"] = "Oct";
    map["11"] = "Nov";
    map["12"] = "Dec";
    return map; 
}

function populateInsuranceInfo (response)
{
   //alert("showInsuranceInfo-->"+response);    
   
   document.getElementById("insuranceInfo").innerHTML = response.split("|")[0]+"<br>"+response.split("|")[1];
}

//--------------------------------------------------------
function showInsuranceCompInfo() {
   //alert("in showwiehht method");
    //var memberId= document.getElementById("memberId").value;
   var req = new XMLHttpRequest();
   req.onreadystatechange = readyStateHandler(req,populateInsuranceInfo);    
   // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
   var url=CONTENXT_PATH+"/showInsuranceCompInfo.action";
   //  alert(url)
   req.open("GET",url,"true");
   req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
   req.send(null);
}


function populateEmployerInfo (response)
{
   //alert("showEmployerInfo-->"+response);    
   
   document.getElementById("employerInfo").innerHTML = response.split("|")[0]+"<br>"+response.split("|")[1]+"<br>"+response.split("|")[2]+", "+response.split("|")[3]+" "+response.split("|")[4]+"<br>"+response.split("|")[5];
}

//--------------------------------------------------------
function showEmployerInfo() {
   //alert("in showwiehht method");
    //var memberId= document.getElementById("memberId").value;
   var req = new XMLHttpRequest();
   req.onreadystatechange = readyStateHandler(req,populateEmployerInfo);    
   // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
   var url=CONTENXT_PATH+"/showEmployerInfo.action";
   //  alert(url)
   req.open("GET",url,"true");
   req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
   req.send(null);
}



function populateEmergencyContact(response){
 //alert(" Concentrate here  showEmergencyContactInfo-->"+response);    
   
   document.getElementById("emergencyContact").innerHTML = response.split("|")[0]+"  "+response.split("|")[1]+" ( "+response.split("|")[2]+" ) "+" <br>"+response.split("|")[3];
}

//--------------------------------------------------------
function showEmergencyContact() {
  // alert("in emergency contact method");
    //var memberId= document.getElementById("memberId").value;
   var req = new XMLHttpRequest();
   req.onreadystatechange = readyStateHandler(req,populateEmergencyContact);    
   // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
   var url=CONTENXT_PATH+"/showEmergencyContact.action";
  // alert(url)
   req.open("GET",url,"true");
   req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
   req.send(null);
}

function populateContactInfo (response){
  // alert("showContactInfo-->"+response);  
   document.getElementById("contactInfo").innerHTML = response.split("|")[0]+"<br>"+response.split("|")[1]+","+response.split("|")[2]+" "+response.split("|")[3]+" <br>"+response.split("|")[4];
}

//--------------------------------------------------------
function showContactInfo() {
 //  alert("in contact method");
    //var memberId= document.getElementById("memberId").value;
   var req = new XMLHttpRequest();
   req.onreadystatechange = readyStateHandler(req,populateContactInfo);    
   // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
   var url=CONTENXT_PATH+"/showContactInfo.action";
   //  alert(url)
   req.open("GET",url,"true");
   req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
   req.send(null);
}





/*Method for displaying medical records
 * Date : 26/01/2015
 */
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


function getRecords() {
    showAllMedicalRecords("MedicalRecords");
    showAllMedicalRecords("Labs");
    showAllMedicalRecords("MedicalScans");
    showAllMedicalRecords("ImmunizationRecords");
}

function showAllMedicalRecords(recordType) {
     //alert("first div called");
   var memberId= document.getElementById("memberId").value;
   // alert("Member Id"+memberId);
   // recordType="MedicalRecords";
   var req = new XMLHttpRequest();
  // req.onreadystatechange = readyStateHandler(req,populateAllMedRecords);    
   var url=CONTENXT_PATH+"/showAllMedicalRecords.action?typeOfReport="+recordType+"&memberId="+memberId;
    var req = initRequest(url);
    req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
populateAllMedRecords(req.responseText,recordType);
                    } 
                }
            };
   //  alert("Medical Records"+url)
   req.open("GET",url,"true");
   req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
   req.send(null);
 
}


function populateAllMedRecords(response,type){
    
    var result = response.split("^");
    var RecordsData =' <table border=\"0\" cellspacing=\"5\" ><tr>';
    //alert("length-->"+result.length-1);
    for(var i=0;i<result.length-1;i++){
           //alert(result[i]);
        var Values=result[i].split("|");
        
     var j = i + 0;
        if(Values[3]=="pdf" || Values[3]=="PDF")
        {
           // alert(Values[0])
        /*   RecordsData = RecordsData+'<td colspan=\"0\" width=\"10%\" style=\"text-align: left;vertical-align: top;\"><table border=\"0\" width=\"15%\" height=\"50%\" bgcolor=\"#ededed\" style=\"margin-top: 20%;\"><tr width=\"10%\" height=\"5%\"><td style=\"width: auto;text-align: right;vertical-align: middle;\"> <tr width="\10%\" height="5%">'+

                                           '<td style="width: auto;text-align: right;vertical-align: middle;"><embed  src="file:///C:'+ Values[0]+'" width="200px" height="220px" type="application/pdf"></td></tr>'
                                        

                                            
            +'<tr width=\"10%\" height=\"5%\"><td colspan=\"3\" style=\"text-align:left;\"><span class=\"HeaderLabels\">'+Values[1]+'&nbsp;#'+j+'</span></td>'
            +'<td style=\"width: auto;text-align: right;vertical-align: middle;\"><a href=\"\"><span class=\"headerLabelsEdit\">'+Values[3].toUpperCase()+'</span></a></td></tr>'
            +  '<tr><td colspan=\"4\" style=\"vertical-align: middle;text-align: left;\"><p class=\"fieldValues\" style=\"text-align: left;vertical-align: middle;font-size: smaller;font-family: mySelectedValueFonts;\">Uploaded:'+Values[4]+'</p></td></tr>'
            +  '<tr><td colspan=\"4\" style=\"vertical-align: middle;text-align: left;\"><p class=\"fieldValues\" style=\"text-align: left;vertical-align: middle;font-size: smaller;\">Sports induced or when allergens are present.Sports induced or when allergens are present.Sports induced or when allergens are present.Sports induced or when allergens are present.</p></td></tr>'
            +' </table></td>' ;
*/
  RecordsData = RecordsData+'<td colspan=\"0\" width=\"10%\" style=\"text-align: left;vertical-align: top;\"><table border=\"0\" width=\"15%\" height=\"40%\" bgcolor=\"#ededed\" style=\"margin-top: 20%;\"><tr width=\"10%\" height=\"5%\"><td style=\"width: auto;text-align: right;vertical-align: middle;\"><a href=\"download.action?path='+
            Values[0]+'\"  ><img src="../includes/images/include/pdfimage.jpg" width=\"200px\" height=\"220px\"></a></td></tr>'
            +'<tr width=\"10%\" height=\"5%\"><td colspan=\"3\" style=\"text-align:left;\"><span class=\"HeaderLabels\">'+Values[1]+'&nbsp;#'+j+'</span></td>'
            +'<td style=\"width: auto;text-align: right;vertical-align: middle;\"><a href=\"\"><span class=\"headerLabelsEdit\">'+Values[3].toUpperCase()+'</span></a></td></tr>'
            +  '<tr><td colspan=\"4\" style=\"vertical-align: middle;text-align: left;\"><p class=\"fieldValues\" style=\"text-align: left;vertical-align: middle;font-size: smaller;font-family: mySelectedValueFonts;\">Uploaded:'+Values[4]+'</p></td></tr>'
            +  '<tr><td colspan=\"4\" style=\"vertical-align: middle;text-align: left;\"><p class=\"fieldValues\" style=\"text-align: left;vertical-align: middle;font-size: smaller;\">Sports induced or when allergens are present.Sports induced or when allergens are present.Sports induced or when allergens are present.Sports induced or when allergens are present.</p></td></tr>'
            +' </table></td>' ;
        }
        else if(Values[3]=="jpg" || Values[3]=="jpeg" || Values[3]=="JPG" || Values[3]=="JPEG" )
        {
            RecordsData = RecordsData+'<td colspan=\"0\" width=\"10%\" style=\"text-align: left;vertical-align: top;\"><table border=\"0\" width=\"15%\" height=\"40%\" bgcolor=\"#ededed\" style=\"margin-top: 20%;\"><tr width=\"10%\" height=\"5%\"><td style=\"width: auto;text-align: right;vertical-align: middle;\"><a href=\"download.action?path='+
            Values[0]+'\"  ><img src="../includes/images/include/jpgimage.png" width=\"200px\" height=\"220px\"></a></td></tr>'
            +'<tr width=\"10%\" height=\"5%\"><td colspan=\"3\" style=\"text-align:left;\"><span class=\"HeaderLabels\">'+Values[1]+'&nbsp;#'+j+'</span></td>'
            +'<td style=\"width: auto;text-align: right;vertical-align: middle;\"><a href=\"\"><span class=\"headerLabelsEdit\">'+Values[3].toUpperCase()+'</span></a></td></tr>'
            +  '<tr><td colspan=\"4\" style=\"vertical-align: middle;text-align: left;\"><p class=\"fieldValues\" style=\"text-align: left;vertical-align: middle;font-size: smaller;font-family: mySelectedValueFonts;\">Uploaded:'+Values[4]+'</p></td></tr>'
            +  '<tr><td colspan=\"4\" style=\"vertical-align: middle;text-align: left;\"><p class=\"fieldValues\" style=\"text-align: left;vertical-align: middle;font-size: smaller;\">Sports induced or when allergens are present.Sports induced or when allergens are present.Sports induced or when allergens are present.Sports induced or when allergens are present.</p></td></tr>'
            +' </table></td>' ;
        }
        else if(Values[3]=="png" || Values[3]=="PNG")
        {
            RecordsData = RecordsData+'<td colspan=\"0\" width=\"10%\" style=\"text-align: right;vertical-align: middle;\"><table border=\"0\" width=\"15%\" height=\"50%\" bgcolor=\"#ededed\" style=\"margin-top: 20%;\"><tr width=\"10%\" height=\"5%\"><td style=\"width: auto;text-align: right;vertical-align: middle;\"><a href=\"download.action?path='+
            Values[0]+'\"  ><img src="../includes/images/include/pngimage.png" width=\"200px\" height=\"220px\"></a></td></tr>'
            +'<tr width=\"10%\" height=\"5%\"><td colspan=\"3\" style=\"text-align:left;\"><span class=\"HeaderLabels\">'+Values[1]+'&nbsp;#'+j+'</span></td>'
            +'<td style=\"width: auto;text-align: right;vertical-align: middle;\"><a href=\"\"><span class=\"headerLabelsEdit\">'+Values[3].toUpperCase()+'</span></a></td></tr>'
            +  '<tr><td colspan=\"4\" style=\"vertical-align: middle;text-align: left;\"><p class=\"fieldValues\" style=\"text-align: left;vertical-align: middle;font-size: smaller;font-style: italic;font-family: mySelectedValueFonts;\">Uploaded:'+Values[4]+'</p></td></tr>'
            +  '<tr><td colspan=\"4\" style=\"vertical-align: middle;text-align: left;\"><p class=\"fieldValues\" style=\"text-align: left;vertical-align: middle;font-size: smaller;\">Sports induced or when allergens are present.Sports induced or when allergens are present.Sports induced or when allergens are present.Sports induced or when allergens are present.</p></td></tr>'
            +' </table></td>' ;
        }
          
    }
   
  if(result.length>1){
    RecordsData =RecordsData +'<td style=\"text-align: left;vertical-align: bottom;\"><a href=\"#\"><img src=\"../includes/images/include/addfile.jpg\" width=\"230px\" height=\"355px\"/></a></td></tr></table>';
    }else{
       RecordsData =RecordsData +'<td style=\"width: auto;vertical-align: bottom;\"><br><a href=\"#\"><img src=\"../includes/images/include/addfile.jpg\" width=\"230px\" height=\"360px\"/></a></td></tr></table>'; 
    }

 if(response.length>0)
   {
       if(type== 'MedicalRecords') {
            document.getElementById("medicalRecordsData").innerHTML=RecordsData;
          // MedRecordDetails(response);
       }else if(type== 'Labs') {
             document.getElementById("labRecordsData").innerHTML=RecordsData;
         //  LabRecordDetails(response);
       }else if(type== 'MedicalScans') {
           document.getElementById("scanRecordsData").innerHTML=RecordsData;
           //ScanRecordDetails(response);
       }else if(type== 'ImmunizationRecords') {
           document.getElementById("irRecordsData").innerHTML=RecordsData;
           document.getElementById("loadActMessage").style.display="none";
          // IrRecordDetails(response);
       }
   }
 RecordsData = "";
 
}



//Charts Display MyDashBoardDetails..
function redirectToMyMedicalRec(dashboardType){
    
    //alert(dashboardType);
    var timeFlag=document.getElementById("reqMap").value;
    window.location=CONTENXT_PATH+"/profile/myDashboardDetails.action?dashboardType="+dashboardType+"&timeFlag="+timeFlag;
}

function showWelghtMap(type,timeFlag){
 //   alert("in showweightmap------------->"+type);
    var str='<font style="font-size:small;font-family: myHeaderFonts;color: #367074;font-weight:900;"> <div id="weightMenu">Weight&nbsp;<img src="../includes/images/img.jpg" height="12px" width="12px"/></div></font>';
    document.getElementById("glMenu").innerHTML="Glucose&nbsp;Level";
    document.getElementById("bpMenu").innerHTML="Blood&nbsp;Pressure";
     document.getElementById("lcMenu").innerHTML="Lipids&nbsp;Cholesterol";
    document.getElementById("weightMenu").innerHTML=str.bold();
    document.getElementById("weightDiv").style.display="block";
     document.getElementById("check").value=timeFlag;
                    document.getElementById("bpDiv").style.display="none";
                    document.getElementById("glDiv").style.display="none";
                     document.getElementById("lcDiv").style.display="none";
    showWeight_div(type,timeFlag);
}
function showBPMap(type,timeFlag){
   // var str="Blood&nbsp;Pressure>>";
    var str='<font style="font-size:small;font-family: myHeaderFonts;color: #367074;font-weight:900;"> <div id="weightMenu">Blood&nbsp;Pressure&nbsp;<img src="../includes/images/img.jpg" height="12px" width="12px"/></div></font>';
    document.getElementById("glMenu").innerHTML="Glucose&nbsp;Level";
    document.getElementById("bpMenu").innerHTML=str.bold();
     document.getElementById("lcMenu").innerHTML="Lipids&nbsp;Cholesterol";
    document.getElementById("weightMenu").innerHTML="Weight";
    document.getElementById("weightDiv").style.display="none";
     document.getElementById("check1").value=timeFlag;
                    document.getElementById("bpDiv").style.display="block";
                    document.getElementById("glDiv").style.display="none";
                     document.getElementById("lcDiv").style.display="none";
    showBloodPressure_div(type,timeFlag);
}
function showGlMap(type,timeFlag){
    //var str="Glucose&nbsp;Level>>";
     var str='<font style="font-size:small;font-family: myHeaderFonts;color: #367074;font-weight:900;"> <div id="weightMenu">Glucose&nbsp;Level&nbsp;<img src="../includes/images/img.jpg" height="12px" width="12px"/></div></font>';
   document.getElementById("weightMenu").innerHTML="Weight";
    document.getElementById("bpMenu").innerHTML="Blood&nbsp;Pressure";
    document.getElementById("glMenu").innerHTML=str.bold();
     document.getElementById("lcMenu").innerHTML="Lipids&nbsp;Cholesterol";
    document.getElementById("weightDiv").style.display="none";
     document.getElementById("check2").value=timeFlag;
                    document.getElementById("bpDiv").style.display="none";
                    document.getElementById("glDiv").style.display="block";
                     document.getElementById("lcDiv").style.display="none";
    showGlucoseLevelMap_div(type,timeFlag);
}
function showLcMap(type,timeFlag){
   // var str="Lipids&nbsp;Cholesterol>>";
    var str='<font style="font-size:small;font-family: myHeaderFonts;color: #367074;font-weight:900;"> <div id="weightMenu">Lipids&nbsp;Cholesterol&nbsp;<img src="../includes/images/img.jpg" height="12px" width="12px"/></div></font>';
    document.getElementById("weightMenu").innerHTML="Weight";
    document.getElementById("glMenu").innerHTML="Glucose&nbsp;Level";
    document.getElementById("bpMenu").innerHTML="Blood&nbsp;Pressure";
    document.getElementById("lcMenu").innerHTML=str.bold();
    document.getElementById("weightDiv").style.display="none";
     document.getElementById("check3").value=timeFlag;
                    document.getElementById("bpDiv").style.display="none";
                    document.getElementById("glDiv").style.display="none";
                     document.getElementById("lcDiv").style.display="block";
    showLipidsOrCholestrolMap_div(type,timeFlag);
}




function setMapAnnualSemiAnnual(mapType,selectValue){
    //alert(document.getElementById('reqMap').value);
    document.getElementById('reqMap').value=selectValue;
    //alert(document.getElementById('reqMap').value);
    return false;
}