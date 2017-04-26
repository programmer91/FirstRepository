/*Don't Alter these Methods*/
// This Script is used to get the text(String) from servlet not xml.


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
                alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }
    }
}

function getContactDetails() {
    //alert('hello');
    
    var accountId = document.getElementById("getAccountId").value;
    //alert(accountId);
    var contactId = document.getElementById("contactsId").value;
    if(contactId == 0 || contactId < 0 || contactId == ""){
        contactId = 0;
    }
    //alert(contactId);    
    
    var fname = document.getElementById("firstNames").value;
    if(fname == 'undefined') {
        fname = " ";
    }
    var lname = document.getElementById("lastNames").value;
    if(lname == 'undefined') {
        lname = " ";
    }
    var mname = document.getElementById("middleName").value;
    if(mname == 'undefined') {
        mname = " ";
    }
    var email = document.getElementById("emails").value;    
    if(email == 'undefined') {
        email = " ";
    }
    var phone = document.getElementById("homePhoneNo").value;
    if(phone == 'undefined') {
        phone = " ";
    }
    var source = document.getElementById("source").value;
    if(source == 'undefined') {
        source = " ";
    }
    
    var req = newXMLHttpRequest();    
    req.onreadystatechange = readyStateHandler(req,contactDetails);    
    
    var url = CONTENXT_PATH+"/saveContact.action?accId="+accountId+"&contId="+contactId+"&firstName="+fname+"&lastName="+lname+"&middleName="+mname+"&email="+email+"&phone="+phone+"&source="+source;
    //var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=allContactDetails&accId="+accountId+"&contId="+contactId+"&fname="+fname+"&lname="+lname+"&mname="+mname+"&email="+email+"&phone="+phone+"&source="+source;
    //alert(url);
    
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function contactDetails(suggText){
    var out = suggText;
    
    // here u have to check compulsory that the out is integer or not to 
    // display and to now whether the contact is added or not correctly..
    // so here u have to validate whether the out is integer or not ?? 
    // check the url ....  http://snippets.dzone.com/posts/show/703 
    
    // parse the input as an integer
    var intValue = parseInt(out);
    
    // if this is not an integer
    if (isNaN(intValue)) {        
        // clear text box
        alert('your contact details are not saved !');
        document.getElementById("contactsId").value = 0;
        document.getElementById("contactActivId").value = 0;         
    }
    else{
        if(intValue > 0){
            alert('your contact details are saved !');
            document.getElementById("contactsId").value = out;
            document.getElementById("contactActivId").value = out;  
        }
        else{
            alert('your contact details not saved !');
        }
    }
}




