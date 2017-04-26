
 function validatenumber(xxxxx) {
   
   	var maintainplus = '';
 	var numval = xxxxx.value
 	if ( numval.charAt(0)=='+' ){ var maintainplus = '+';}
 	curnumbervar = numval.replace(/[\\A-Za-z!"�$%^&*+_={};:'@#~,.�\/<>?|`�\]\[]/g,'');
 	xxxxx.value = maintainplus + curnumbervar;
 	var maintainplus = '';
        // alert("enter integers only");
 	xxxxx.focus;
}

        
 function prjNameValidate(){
      var prjName= document.frmProjectAdd.prjName;
    
    
    if (prjName.value != null && (prjName.value != "")) {
        if(prjName.value.replace(/^\s+|\s+$/g,"").length>50){
            
              str = new String(document.frmProjectAdd.prjName.value);
            document.frmProjectAdd.prjName.value=str.substring(0,50);
            
            alert("The projectName must be less than 50 characters");
            
          
           
              }
       document.frmProjectAdd.prjName.focus();
        return (false);
    }
  
    return (true);
};

function totalResourcesValidate(){
      var totalResources= document.frmProjectAdd.totalResources;
    
    
    if (totalResources.value != null && (totalResources.value != "")) {
        if(totalResources.value.replace(/^\s+|\s+$/g,"").length>10){
            
              str = new String(document.frmProjectAdd.totalResources.value);
            document.frmProjectAdd.totalResources.value=str.substring(0,10);
            
            alert("The totalResources must be less than 10 characters");
            
            
           
              }
       document.frmProjectAdd.totalResources.focus();
        return (false);
    }
  
    return (true);
};

function descriptionValidate(){
      var description= document.frmProjectAdd.description;
    
    
    if (description.value != null && (description.value != "")) {
        if(description.value.replace(/^\s+|\s+$/g,"").length>512){
            
               str = new String(document.frmProjectAdd.description.value);
            document.frmProjectAdd.description.value=str.substring(0,512);  
            
            alert("The description must be less than 512 characters");
            
          
           
              }
       document.frmProjectAdd.description.focus();
        return (false);
    }
  
    return (true);
};
function fieldLengthValidatorforProject(element){
    var i = 0;
   if(element.id == 'comments') i=1500;
        
            else if(element.id == 'description' || element.id == 'software') i=500;
    
    else if(element.id == 'projectName') i=50;
     else if(element.id == 'taskTitle') i=100;
   else if(element.id == 'comments' || element.id == 'resolution') i=1500;
    
     if(element.value.replace(/^\s+|\s+$/g,"").length>i){
            //subStringValue(i,element,"The "+element.id+" must be less than "+i+" characters");
            str = new String(element.value);
            element.value=str.substring(0,i);
            
            alert("The "+element.id+" must be less than "+i+" characters");
            element.focus();
            return false;
        }
}
function isNumberKey(evt) {
 
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode!=46 && charCode > 31 && (charCode < 48 || charCode > 57))
    {
        alert("Please enter numeric value");
        return false;
    }else {
        return true;
    }
}
function projectValidation(){
    var projectName=document.getElementById("projectName").value;
     var practice=document.getElementById("practice").value;
    var pmo=document.getElementById("pmo").value;
   
    if(projectName==''){
        alert("Please enter project name");
        return false;
        }
     if(practice=='' || practice==-1){
      alert("Please select practice");
        return false;   
     }  
      if(pmo=='' || pmo==-1){
      alert("Please select pmo");
        return false;   
     } 
    if(checkDate()==false){
        return false;
    }
    return true;
}

   function checkDate() {
                var sdate = document.getElementById('startDatePlan').value;
                var edate = document.getElementById('endDatePlan').value;
                var mm = sdate.substring(0,2);
                var dd = sdate.substring(3,5);
                var yyyy = sdate.substring(6,10);
                var mm1 = edate.substring(0,2);
                var dd1 = edate.substring(3,5);
                var yyyy1 = edate.substring(6,10);
                 var startdate = document.getElementById('startDateActual').value;
               
                 var enddate=document.getElementById("endDateActual").value;
    var status=document.getElementById("projectStatus").value;
  
    if(startdate == "" || startdate == null){
        alert("Actual Start date should not be empty");
        return false;
    }
    
    if(status == "Completed" && (enddate == "" || enddate == null) ){
        alert("Actual End date should not be empty");
        return false;
    }
    
    
      if((edate != "" && edate != null ) ){
          //  alert(edate);
                if(yyyy1 < yyyy) {
                    alert('End Date is older than Start Date');
                    return false;
                }
                else if((yyyy1 == yyyy) && (mm1 < mm)) {
                    alert('End Date is older than Start Date');
                    return false;
                }
                else if((yyyy1 == yyyy) && (mm1 == mm) && (dd1 < dd)) {
                    alert('End Date is older than Start Date');
                    return false;
                }
                
                
           }
       if( (enddate != "" && enddate != null )){
            
                 mm = startdate.substring(0,2);
                 dd = startdate.substring(3,5);
                 yyyy = startdate.substring(6,10);
                 mm1 = enddate.substring(0,2);
                 dd1 = enddate.substring(3,5);
                 yyyy1 = enddate.substring(6,10);
                if(yyyy1 < yyyy) {
                    alert('End Date is older than Start Date');
                    return false;
                }
                else if((yyyy1 == yyyy) && (mm1 < mm)) {
                    alert('End Date is older than Start Date');
                    return false;
                }
                else if((yyyy1 == yyyy) && (mm1 == mm) && (dd1 < dd)) {
                    alert('End Date is older than Start Date');
                    return false;
                }
       }
                return true;
            }
            function fieldLengthValidatorforProjectRisk(element){
    var i = 0;
    if(element.id == 'description') i=1500;
    
    else if(element.id == 'resolution') i=2500;
    
     if(element.value.replace(/^\s+|\s+$/g,"").length>i){
            //subStringValue(i,element,"The "+element.id+" must be less than "+i+" characters");
            str = new String(element.value);
            element.value=str.substring(0,i);
            
            alert("The "+element.id+" must be less than "+i+" characters");
            element.focus();
            return false;
        }
}
function validateProjectRisk(){
    var description=document.getElementById('description').value;
    var assignedTo=document.getElementById('assignedTo').value;
    var impact=document.getElementById('impact').value;
    var closedDate=document.getElementById('closedDate').value;
    var resolution=document.getElementById('resolution').value;
    
    if(description==''){
        alert("Please enter description");
        return false;
    }
     if(assignedTo=='-1'){
        alert("Please select Assigned to");
        return false;
    }
     if(impact=='-1'){
        alert("Please select impact");
        return false;
    }
    
}

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
function readyStateHandlerText(req,responseTextHandler){
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {               
                responseTextHandler(req.responseText);
            } else {
                alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }
    }
}   
function getRiskDescription(Id) {
    //alert("hi-->"+Id);
    var riskId = Id;
   
   var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populateDescriptionComments);    
   
    var url=CONTENXT_PATH+"/popupTasksRiskDescWindow.action?id="+riskId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}getRiskResolution

function getRiskResolution(Id) {
    //alert("hi-->"+Id);
    var riskId = Id;
  
   var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populateResolutionComments);    
   
    var url=CONTENXT_PATH+"/popupTasksRiskResolutionWindow.action?id="+riskId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateDescriptionComments(text) {
    var background = "#3E93D4";
    var title = "Description";
    var text1 = text; 
    var size = text1.length;
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+text1+"<br />\
    </body></html>";
    
    if(size < 50){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 100){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 260){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    } else {
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}

function populateResolutionComments(text) {
    var background = "#3E93D4";
    var title = "Resolution";
    var text1 = text; 
    var size = text1.length;
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+text1+"<br />\
    </body></html>";
    
    if(size < 50){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 100){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 260){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    } else {
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}


function mytoggleOverlayForTaskAdd(){
    hideRow("severityTR");
    hideRow("typeId");
    hideRow("updatingTr");
    hideRow("commentsTR");
    hideRow("resolutionTR");
    
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Add Task";
    var overlay = document.getElementById('overlayForProjectAdd');
    var specialBox = document.getElementById('specialBoxForProjectAdd');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
        //        document.frmDBGrid.submit();
        var projectId=document.getElementById("editProject_id").value;
        var accountId=document.getElementById("editProject_customerId").value;
        var roleName=document.getElementById("roleName").value;
       var accountName=document.getElementById("accountName").value;
       var backFlag=document.getElementById("backFlag").value;
       
        if(roleName=="Employee"){
               window.location = "getCustomerProjectDetails.action?accountId="+accountId+"&projectId="+projectId+"&accountName="+accountName+"&backFlag="+backFlag;}
        else{
        window.location = '../projects/getProject.action?id='+projectId+'&accountId='+accountId;}

        
        
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}
function addProjectTaskDetails(){
    //  alert("hii");
    document.getElementById('resultMessage').innerHTML="";
    var projectId=document.getElementById("editProject_id").value;
    var accountId=document.getElementById("editProject_customerId").value;
    var taskTitle = document.getElementById('taskTitle').value;
    var assignedTo = document.getElementById('assignedTo').value;
    var startDate = document.getElementById('startDate').value;
    var dateClosed = document.getElementById('dateClosed').value;
    var durationTotheTask = document.getElementById('durationTotheTask').value;
  
    if(taskTitle.length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please Enter Title.</font>";
        return false;
    }
    else if(assignedTo=='-1'){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please select assigned To.</font>";
        return false;
                
    }
    else if(durationTotheTask.length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please Enter Hours.</font>";
        return false;
    }else if(startDate.length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please Enter Start Date.</font>";
        return false;
    }else if(dateClosed.length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please Enter Due Date.</font>";
        return false;
    }
    else {
        // document.getElementById("load").style.display = 'block';
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerTxt(req,getTaskDetailsResponse); 
        var url=CONTENXT_PATH+'/addProjectTaskDetails.action?accountId='+accountId+'&projectId='+projectId+'&title='+taskTitle+'&assignedTo='+assignedTo+'&startDate='+startDate+'&hours='+durationTotheTask+'&expiryDate='+dateClosed;
        //alert(url);
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }

}
function getTaskDetailsResponse(resText){
    //alert(resText);
    document.getElementById('resultMessage').innerHTML = resText;
     document.getElementById('resultMessage').value="";
}

function toggleEditOverlayForProjectTask(id){
    hideRow("addingTr");
    
    document.getElementById("taskId").value=id;
    var overlay = document.getElementById('overlayForProjectAdd');
    var specialBox = document.getElementById('specialBoxForProjectAdd');
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Edit Task Details";
    document.getElementById("load").style.display = 'block';
    $.ajax({
        url:'getProjectTaskDetails.action?taskId='+id,
        context: document.body,
        success: function(responseText) {
              
            var json = $.parseJSON(responseText);
            
            var Title = json["Title"];
            var STATUS = json["STATUS"];
            var Startdate = json["Startdate"];
            var PriAssignTO = json["PriAssignTO"];
            var IssueType = json["IssueType"];
            var Duration = json["Duration"];
            var Severity = json["Severity"];
            var Description = json["Description"];
            var Resolution = json["Resolution"];
            var DueDate = json["DueDate"];
            
        
            document.getElementById("taskTitle").value = Title;
            document.getElementById("assignedTo").value = PriAssignTO;
            document.getElementById("startDate").value = Startdate;
            document.getElementById("durationTotheTask").value = Duration;
            document.getElementById("priority").value = Severity;
            document.getElementById("issueType").value = IssueType;
            document.getElementById("statusId").value = STATUS;
            document.getElementById("comments").value = Description;
            document.getElementById("resolution").value = Resolution;
            document.getElementById("dateClosed").value = DueDate;
            document.getElementById("load").style.display = 'none';  
            document.getElementById("resultMessage").style.display = 'none';  
            
            
                
        }, 
        error: function(e){
            document.getElementById("load").style.display = 'none';
            alert("error-->"+e);
        }
    });
    
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}
function hideRow(id) {
    //alert(id);
    var row = document.getElementById(id);
    row.style.display = 'none';
}
function showRow(id) {
    //  alert(id);
    var row = document.getElementById(id);
    row.style.display = '';
} 
function upadteProjectTaskDetails(){
    // alert("hai");
   var taskId= document.getElementById("taskId").value;
    var Title=document.getElementById("taskTitle").value ;
    var PriAssignTO= document.getElementById("assignedTo").value;
    var Startdate= document.getElementById("startDate").value;
    var Duration=document.getElementById("durationTotheTask").value;
    var Severity=document.getElementById("priority").value;
    var IssueTypeId=document.getElementById("issueType").value;
    var STATUS=document.getElementById("statusId").value;
    var resolution=document.getElementById("resolution").value;
    var comments=document.getElementById("comments").value;
    var dateClosed=document.getElementById("dateClosed").value;
    

    document.getElementById('resultMessage').style.display="block";
   
    // alert(overlayBridgeNumber);
    //document.getElementById('resultMessage').innerHTML=overlayBridgeNumber;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerTxt(req,ppulateProjectTaskUpadteResult); 
    var url=CONTENXT_PATH+'/updateProjectTaskDetails.action?status='+STATUS+'&severity='+Severity+'&hours='+Duration+'&title='+Title+'&assignedTo='+PriAssignTO+'&issueTypeId='+IssueTypeId+'&startDate='+Startdate+'&taskId='+taskId+'&comments='+comments+'&resolution='+resolution+'&endDate='+dateClosed;
    //alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
} 

function ppulateProjectTaskUpadteResult(resText){
    document.getElementById('resultMessage').innerHTML = resText;
    document.getElementById('resultMessage').style.display="block";
    //  alert("Updated Successfully");
    toggleOverlay();
}

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
function readyStateHandlerTxt(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
               
                responseTextHandler(req.responseText);
            }
            else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
          
        }
    }
}
function isExistedProjectName(){
    $("#correctImg").hide();
    $("#wrongImg").hide();
    var accountId = document.getElementById("accountId").value;
    var projName = document.getElementById("projectName").value;
   // var projectId = document.getElementById("projectId").value;
  
    
    projName = projName.replace("\\s","");
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,isExistedProjectNameResponse); 

    //var url=CONTENXT_PATH+"/updateMyReview.action?reviewId="+reviewId+"&overlayReviewType="+overlayReviewType+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay;
    // var url=CONTENXT_PATH+'/upadtePayrollTaxExemption.action?overLayExemptionType='+overLayexemptionType+'&overlayApprovedAmount='+overlayApprovedAmount+'&overLayStatus='+overLaystatus+'&comments='+comments+'&exemptionId='+exemptionId+"&paymentDateEmp="+paymentDateEmp;
    var url=CONTENXT_PATH+'/isExistedProjectName.action?accountId='+accountId+'&projectName='+projName;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function isExistedProjectNameResponse(resText){
    if(resText=='Yes'){
        $("#correctImg").show();
       
    }else{
        $("#wrongImg").show();
        document.getElementById("projectName").value='';
    }
}
