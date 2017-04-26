/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



 function checkDateForTimesheet()
        {
            var radios = document.getElementsByName('checkDate');
             var timesheetDate = document.getElementById("previousWeek").value;
            for (var i = 0, length = radios.length; i < length; i++) 
            {
                if (radios[i].checked) {
                      if(radios[i].value=="PreviousWeek"){
                              if(timesheetDate==null || timesheetDate=="")
                                 {
                                     alert("Please select Date from calender popup");
                                     return false;
                                 }
                                     }
                                        }
            }
                return true;
        }
        
      function getDescriptions(priStatus,secStatus) {
              var background = "#3E93D4";
              //var background = "blue";
    var title = "Timesheet Status";
   // var text1 = text; 
   // var size = text1.length;
    
    
    var statusConetent = "Status:&nbsp;&nbsp;"+priStatus;
    if(secStatus=='Approved'||secStatus=='Disapproved'||secStatus=='Submitted'){
        statusConetent = statusConetent+"<br>"+"Secondary Status:&nbsp;&nbsp;"+secStatus;
    }
    var size = statusConetent.length;
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head><body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+statusConetent+"<br/></body></html>";
    
    
   var popup ='';
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
		