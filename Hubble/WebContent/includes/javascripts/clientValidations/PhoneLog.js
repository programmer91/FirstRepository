
// validation for PhoneLog.jsp
function validation() {
    //alert(document.getElementById('callType')[0].checked);
    var startTime=document.getElementById('startTime').value; 
    var endTime=document.getElementById('endTime').value;
    var toPhoneNo=document.getElementById('toPhoneNo').value;
    var description=document.getElementById('description').value;
    
    if(startTime.length==0 || endTime.length==0 ) {
        alert("Start time or EndTime Required");
        return (false);
    }
    else if(toPhoneNo.length==0 || toPhoneNo==null) {
        alert("Please Enter To Phone Number");
        return (false);
    }
    /*if((form.callType[0].checked == false ) && (form.callType[1].checked == false )) {
        alert("Please Select Call Type");
        return (false);
    }*/
    else if(description.length==0 || description==null) {
        alert('Please Enter Description');
        return (false);
    }
    
    else {            
        return (true);
    }
};


function getCallDuration()  {
    var startTime= document.getElementById('startTime').value;
    var endTime=document.getElementById('endTime').value;
    var startTimeHours=startTime.substring(0,2);
    var endTimeHours=endTime.substring(0,2);
    
    if(startTime.substring(5,8).trim==endTime.substring(5,8).trim) {
        
        if(startTime.length==8 && endTime.length==8) {
            
            var startTimeHours=startTime.substring(0,2);
            startTimeHours=parseInt(startTimeHours);
            var endTimeHours=endTime.substring(0,2);
            endTimeHours=parseInt(endTimeHours);
            var startTimeMinutes=startTime.substring(3,5);
            startTimeMinutes=parseInt(startTimeMinutes);
            var endTimeMinutes=endTime.substring(3,5);
            endTimeMinutes=parseInt(endTimeMinutes);
            
            
            
            if( (endTimeMinutes < startTimeMinutes) && (endTimeHours ==  startTimeHours )) {
                
                alert('enter valid time');
                document.getElementById('endTime').value="";
                document.getElementById('callDuration').value="";
            }
            if( (endTimeMinutes < startTimeMinutes)) {
                
                var minutesDifference=(60-startTimeMinutes)+parseInt(endTimeMinutes);
            }
            if(endTimeMinutes >= startTimeMinutes)  {
                minutesDifference=endTimeMinutes-startTimeMinutes;
            }	
            
            if((endTimeHours > startTimeHours) && (endTimeMinutes < startTimeMinutes) ) {
                hourDifference=endTimeHours-startTimeHours-1
            }
            if((endTimeHours < startTimeHours) && (endTimeMinutes <= startTimeMinutes) ) {
                hourDifference=((12-startTimeHours)+parseInt(endTimeHours))-1;
                
            }
            else if(endTimeHours > startTimeHours){
                hourDifference=endTimeHours-startTimeHours;
            }
            else {
                hourDifference=0;
            }                 
        }
        
        if(startTime.length==7 && endTime.length==7) {
            var startTimeHours=startTime.substring(0,1);
            startTimeHours=parseInt(startTimeHours);
            var endTimeHours=endTime.substring(0,1);
            endTimeHours=parseInt(endTimeHours);
            var startTimeMinutes=startTime.substring(2,5);
            startTimeMinutes=parseInt(startTimeMinutes);
            var endTimeMinutes=endTime.substring(2,5);
            endTimeMinutes=parseInt(endTimeMinutes);
            if( (endTimeMinutes < startTimeMinutes)) {
                
                var minutesDifference=(60-startTimeMinutes)+parseInt(endTimeMinutes);
                
            }
            else {
                minutesDifference=endTimeMinutes-startTimeMinutes;
            }	
            
            if((endTimeHours > startTimeHours) && (endTimeMinutes < startTimeMinutes) ) {
                hourDifference=endTimeHours-startTimeHours-1
            }
            else if(endTimeHours > startTimeHours){
                hourDifference=endTimeHours-startTimeHours;
            }
            else {
                hourDifference=0;
            }
            
            
        }
        
        if(startTime.length==7 && endTime.length==8) {
            
            var startTimeHours=startTime.substring(0,1);
            startTimeHours=parseInt(startTimeHours);
            var startTimeMinutes=startTime.substring(2,5)
            startTimeMinutes=parseInt(startTimeMinutes);
            var endTimeHours=endTime.substring(0,2);
            endTimeHours=parseInt(endTimeHours);
            var endTimeMinutes=endTime.substring(3,5);
            endTimeMinutes=parseInt(endTimeMinutes);
            if( (endTimeMinutes < startTimeMinutes)) {
                
                var minutesDifference=(60-startTimeMinutes)+parseInt(endTimeMinutes);
            }
            if(endTimeMinutes > startTimeMinutes)  {
                minutesDifference=endTimeMinutes-startTimeMinutes;
                
            } 
            if(endTimeMinutes == startTimeMinutes) {
                minutesDifference=0;
            }
            
            if((endTimeHours > startTimeHours) && (endTimeMinutes < startTimeMinutes) ) {
                hourDifference=endTimeHours-startTimeHours-1
            }
            
            else if(endTimeHours > startTimeHours) {
                hourDifference=endTimeHours-startTimeHours;
            }
        }
        
        if(startTime.length==8 && endTime.length==7) {
            
            var startTimeHours=startTime.substring(0,2);
            startTimeHours = parseInt(startTimeHours);
            var startTimeMinutes=startTime.substring(3,5);
            startTimeMinutes=parseInt(startTimeMinutes);
            var endTimeHours=endTime.substring(0,1);
            endTimeHours = parseInt(endTimeHours);
            var endTimeMinutes=endTime.substring(2,5);
            endTimeMinutes=parseInt(endTimeMinutes);
            if((endTimeHours < startTimeHours) && (endTimeMinutes >= startTimeMinutes)  ) {
                hourDifference=(12-startTimeHours)+parseInt(endTimeHours);
            }
            
            if((endTimeHours < startTimeHours) && (endTimeMinutes < startTimeMinutes) ) {
                hourDifference=((12-startTimeHours)+parseInt(endTimeHours))-1;
                
            }
            if(endTimeMinutes < startTimeMinutes) {
                minutesDifference=(60-startTimeMinutes)+parseInt(endTimeMinutes);
            }
            else if(endTimeMinutes > startTimeMinutes) {
                minutesDifference=endTimeMinutes-startTimeMinutes;
            }
            else 	{
                minutesDifference=0;
            }
        }		
        
    }
    
    
    if(hourDifference < 9) {
        hourDifference= "0" + hourDifference;
    }
    if(minutesDifference < 9) {
        minutesDifference= "0" + minutesDifference;
    }
    
    
    
    
    
    
    
    var result=hourDifference+":"+ minutesDifference;
    document.getElementById('callDuration').value=result;
    
}

/*function validation1() {
            var startDate=document.getElementById('startDate').value;
            var endDate=document.getElementById('endDate').value;
            if(startDate.length==0 || endDate.length==0) {
             alert("Start Date or end Date is required"); 
                  <!-- alert("end Date is required"); -->
                 return (false);
 
             }
 
            else {            
                return (true);
            }
   }
 
 
function toPhoneNoFormat(num) { 
 
    str = new String(document.phoneLogForm.toPhoneNo.value);
    document.phoneLogForm.toPhoneNo.value=str.replace(/[\(\)\.\-\s,]/g, "");
    num=document.phoneLogForm.toPhoneNo.value;
 
 
    var numericExpression = /^[0-9]+$/;
    if(document.phoneLogForm.toPhoneNo.value.match(numericExpression)){
 
    var _return;
    if(num.length == 10) {
 
        _return="(";
        var ini = num.substring(0,3);
        _return+=ini+")";
        var st = num.substring(3,6);
        _return+="-"+st+"-";
        var end = num.substring(6,10);
        _return+=end;
 
        document.phoneLogForm.toPhoneNo.value ="";
        document.phoneLogForm.toPhoneNo.value =_return;
 
    }else if(num.length > 10) {
        _return="(";
        var ini = num.substring(0,3);
        _return+=ini+")";
        var st = num.substring(3,6);
        _return+="-"+st+"-";
        var end = num.substring(6,10);
        _return+=end+"x";
        var ext = num.substring(10,num.length);
        _return+=ext;
 
        document.phoneLogForm.toPhoneNo.value ="";
        document.phoneLogForm.toPhoneNo.value =_return;
        return false;
    }else if(num.length < 10) {
        alert('Please give atleast  10 charcters in PhoneNumber');
        document.phoneLogForm.toPhoneNo.value ="";
    }
 
    return _return;
} 
        else{
                alert('enter proper number');
                 document.phoneLogForm.toPhoneNo.value ="";
                return false;
        }
}  
//PHONE NUMBER FORMAT SCRIPT :END        
function validatenumber(xxxxx) {
    var maintainplus = '';
    var numval = xxxxx.value
    if ( numval.charAt(0)=='+' ){ var maintainplus = '+';}
    curnumbervar = numval.replace(/[\\A-Za-z!"£$%^&*+_={};:'@#~,.¦\/<>?|`¬\]\[]/g,'');
    xxxxx.value = maintainplus + curnumbervar;
    var maintainplus = '';
    // alert("enter integers only");
    xxxxx.focus;
}*/
