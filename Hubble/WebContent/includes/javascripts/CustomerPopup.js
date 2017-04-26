// This is a javascript file
/*function doEdit(var1,var2) {
    testwindow = window.open('getContactLogin.action?id='+var1+'&accountId='+var2, 'testwindow', "width=350,height=250,status=yes");
    testwindow.defaultStatus="Creating Login..."
    testwindow.moveTo(350,370);
} // function Over
*/
function doEdit(var1,var2,var3,var4) {
  if(var3==""||var3==null)
      {
          alert("Please update the contact with mailId first");
          
      }
      else if(var4==1){
          alert("Already created login credentials!");
      }
      else{
    testwindow = window.open('getContactLogin.action?id='+var1+'&accountId='+var2, 'testwindow', "width=350,height=250,status=yes");
    testwindow.defaultStatus="Creating Login..."
    testwindow.moveTo(350,370);
}} 
/*function validate() {
    var loginId = document.ContactLoginForm.loginId.value;
    var passWord = document.ContactLoginForm.password.value;
    var emailId = document.ContactLoginForm.emailId.value;
    var contactId = document.ContactLoginForm.Id.value;
    var accountId =  document.ContactLoginForm.accId.value;
    //alert(emailId);
    if(emailId=="None" ||  passWord=="" || loginId=="") {
        alert('Please fill Fileds');
    }
    else{
        document.location = "createContactLogin.action?Id="+contactId+"&loginId="+loginId+"&passWord="+passWord+"&accId="+accountId+"&emailId="+emailId;
    }           
    
    
}
*/
function validate() {
    //var loginId = document.ContactLoginForm.loginId.value;
    //var passWord = document.ContactLoginForm.password.value;
    var emailId = document.ContactLoginForm.emailId.value;
    var contactId = document.ContactLoginForm.Id.value;
    var accountId =  document.ContactLoginForm.accId.value;
   
    var loginId = document.ContactLoginForm.loginId.value;
     //   alert(document.getElementById("designationListId"));
     var isTrue = false;   

    //alert(projectsArray);

    
     if(loginId=="")
            {
                alert("Please provide loginId");
            return false;
                
            }

       
    else{
        document.location = "createContactLogin.action?Id="+contactId+"&accId="+accountId+"&emailId="+emailId+"&custLoginId="+loginId;
               
    }
    
}
function sendMail(var1,var2,var3){
   // alert('hi');
   //document.location="../contacts/getEmail.action?officeEmail="+var1+"&id="+var2+"&AccountId="+var3;
   //alert(document.location);
   testwindow1=window.open('../contacts/getEmail.action?officeEmail='+var1+'&id='+var2+'&AccountId='+var3,'testwindow', "width=650,height=450,status=yes")
   //testwindow1.defaultStatus="Creating Login..."
    testwindow1.moveTo(300,370);
}
function getRecruiterDetails(Recruiter) {
    
    var test = Recruiter;
    //alert(test.substr(0,1));
    //alert("test --"+test);
    //alert(indexOf("."));
    // alert(test.substr(test.indexOf(".")+1,test.length));
    //var loginId=(test.substr(0,1)+test.substr(test.indexOf(".")+1,test.length));
      var loginId=Recruiter;
    //alert("loginId"+loginId);
    var aId = Recruiter;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req,populateReqPersonSkills);    
    // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
    var url=CONTENXT_PATH+"/popupReqRecruiterWindow.action?recruiterId="+loginId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function populateReqPersonSkills(text) {
    var background = "#3E93D4";
    var title = "Employee Details";
    var text1 = text; 
    var size = text1.length;
    
    // alert("text "+text1);
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'>"+text1+"<br />\
    </body></html>";
    
    //alert("text1"+text1);
    // alert("size "+content.length);
    var indexof=(content.indexOf("^")+1);
    var lastindexof=(content.lastIndexOf("^"));
    
    popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
    if(content.indexOf("^")){
        //alert(content.substr(0,content.indexOf("//")));
        popup.document.write("<b>Employee Name : </b>"+content.substr(0,content.indexOf("^")));
        popup.document.write("<br><br>");
        popup.document.write("<b>Work Phone No :</b>"+content.substr((content.indexOf("^")+1),(lastindexof-indexof)));
        popup.document.write("<br><br>");
        popup.document.write("<b>Email Address :</b>"+content.substr((content.lastIndexOf("^")+1),content.length));
    }//Write content into it.  
    //Write content into it.    
    
    
    
}