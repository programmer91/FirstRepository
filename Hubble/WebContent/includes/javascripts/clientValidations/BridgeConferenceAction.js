 
// validation for PhoneLog.jsp 
function validation() {
            var contactNo=document.getElementById('contactNo').value;
            var title = document.getElementById('title').value;
            var engagedBy = document.getElementById('engagedBy').value;
            var date = document.getElementById('date').value;
            var startTime=document.getElementById('startTime').value; 
            var endTime=document.getElementById('endTime').value
            var listOfEmailAdd =document.getElementById('listOfEmailAdd').value;
            var senderMail= document.getElementById('senderMail').value;
          if(contactNo==null || title.length==0 ) {
                 alert("contactnumber or title  required");
                 return (false);
            }
            else if(engagedBy.length==0 || date==null) {
                alert("required engagedBy or date");
                return (false);
            }
           else if(startTime.length==0 || endTime.length==0) {
                alert('required StartTime or endTime');
                return (false);
            }
            else if(listOfEmailAdd.length==0 || senderMail.lenght==0) {
                alert('required ListOfEmail add or sender');
                return (false);
            }
            
            else {            
                return (true);
            }
 };

            
        