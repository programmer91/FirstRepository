//NUMBER FORMAT VALIDATION SCRIPT: START

function formatPhone(num) { 
    
    str = new String(document.frmAccForm.phone.value);
    document.frmAccForm.phone.value=str.replace(/[A-Za-z\(\)\.\-\x\s,]/g, "");
    num=document.frmAccForm.phone.value;
    
    if(num.length == 10) { 
        _return="(";
        var ini = num.substring(0,3);
        _return+=ini+")";
        var st = num.substring(3,6);
        _return+="-"+st+"-";
        var end = num.substring(6,10);
        _return+=end;
        
        document.frmAccForm.phone.value ="";
        document.frmAccForm.phone.value =_return;
        
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
        
        alert('Phone Number should be 10 characters');
        document.frmAccForm.phone.value =_return;
        document.frmAccForm.phone.value ="";
        document.frmAccForm.phone.focus();
        return false;
    }else if(num.length < 10) {
        alert('Please give atleast  10 charcters in PhoneNumber');
       // document.frmAccForm.phone.value ="";
    }
    
    return _return;
}  

//NUMBER FORMAT VALIDATION SCRIPT: END






function validatenumber(xxxxx) {
    
    var maintainplus = '';
    var numval = xxxxx.value
    if ( numval.charAt(0)=='+' ){ var maintainplus = '+';}
    curnumbervar = numval.replace(/[\\A-Za-z!"�$%^&*+_={};:'@#~,�\/<>?|`�\]\[]/g,'');
    xxxxx.value = maintainplus + curnumbervar;
    var maintainplus = '';
    // alert("enter integers only");
    xxxxx.focus;
}


function accountNameValidate(){
    var accountName= document.frmAccForm.accountName;
    
    
    if (accountName.value != null && (accountName.value != "")) {
        if(accountName.value.replace(/^\s+|\s+$/g,"").length>60){
            
            str = new String(document.frmAccForm.accountName.value);
            document.frmAccForm.accountName.value=str.substring(0,60);
            
            alert("The accountName must be less than 60 characters");
            
            
            
        }
        document.frmAccForm.accountName.focus();
        return (false);
    }
    
    return (true);
};

function urlValidate(){
    var url= document.frmAccForm.url;
    
    
    if (url.value != null && (url.value != "")) {
        if(url.value.replace(/^\s+|\s+$/g,"").length>50){
            
            str = new String(document.frmAccForm.url.value);
            document.frmAccForm.url.value=str.substring(0,50);
            
            alert("The url must be less than 50 characters");
            
            
            
        }
        document.frmAccForm.url.focus();
        return (false);
    }
    
    return (true);
};

function synonymsValidate(){
    var synonyms= document.frmAccForm.synonyms;
    
    
    if (synonyms.value != null && (synonyms.value != "")) {
        if(synonyms.value.replace(/^\s+|\s+$/g,"").length>60){
            
            str = new String(document.frmAccForm.synonyms.value);
            document.frmAccForm.synonyms.value=str.substring(0,60);
            
            alert("The synonyms must be less than 60 characters");
            
            
            
        }
        document.frmAccForm.synonyms.focus();
        return (false);
    }
    
    return (true);
};

function phoneValidate(){
    var phone= document.frmAccForm.phone;
    
    
    if (phone.value != null && (phone.value != "")) {
        if(phone.value.replace(/^\s+|\s+$/g,"").length>16){
            
            str = new String(document.frmAccForm.phone.value);
            document.frmAccForm.phone.value=str.substring(0,16);
            
            alert("The phone must be less than 16 characters");
            
            
            
        }
        document.frmAccForm.phone.focus();
        return (false);
    }
    
    return (true);
};

function faxValidate(){
    var fax= document.frmAccForm.fax;
    
    if (fax.value != null && (fax.value != "")) {
        if(fax.value.replace(/A-Za-z^\s+|\s+$/g,"").length>16){
            
            str = new String(document.frmAccForm.fax.value);
            document.frmAccForm.fax.value=str.substring(0,16);  
            
            alert("The fax must be less than 16 characters");
            
            
        }
        document.frmAccForm.fax.focus();
        fax.value.replace(/A-Za-z^\s+|\s+$/g,"");
        return (false);
    }
    
    return (true);
};

function leadSourceValidate(){
    var leadSource= document.frmAccForm.leadSource;
    
    
    if (leadSource.value != null && (leadSource.value != "")) {
        if(leadSource.value.replace(/^\s+|\s+$/g,"").length>50){
            
            str = new String(document.frmAccForm.leadSource.value);
            document.frmAccForm.leadSource.value=str.substring(0,50);
            
            alert("The leadSource must be less than 50 characters");
            
            
            
        }
        document.frmAccForm.leadSource.focus();
        return (false);
    }
    
    return (true);
};

function accountTeamValidate(){
    var accountTeam= document.frmAccForm.accountTeam;
    
    
    if (accountTeam.value != null && (accountTeam.value != "")) {
        if(accountTeam.value.replace(/^\s+|\s+$/g,"").length>20){
            
            str = new String(document.frmAccForm.accountTeam.value);
            document.frmAccForm.accountTeam.value=str.substring(0,20);
            
            alert("The accountTeam must be less than 20 characters");
            
            
            
        }
        document.frmAccForm.accountTeam.focus();
        return (false);
    }
    
    return (true);
};

function noOfEmployeesValidate(){
    var noOfEmployees= document.frmAccForm.noOfEmployees;
    
    
    if (noOfEmployees.value != null && (noOfEmployees.value != "")) {
        if(noOfEmployees.value.replace(/^\s+|\s+$/g,"").length>10){
            
            str = new String(document.frmAccForm.noOfEmployees.value);
            document.frmAccForm.noOfEmployees.value=str.substring(0,10);
            
            alert("The noOfEmployees must be less than 10 characters");
            
            
            
        }
        document.frmAccForm.noOfEmployees.focus();
        return (false);
    }
    
    return (true);
};

function stockSymbol1Validate(){
    var stockSymbol1= document.frmAccForm.stockSymbol1;
    
    
    if (stockSymbol1.value != null && (stockSymbol1.value != "")) {
        if(stockSymbol1.value.replace(/^\s+|\s+$/g,"").length>12){
            
            str = new String(document.frmAccForm.stockSymbol1.value);
            document.frmAccForm.stockSymbol1.value=str.substring(0,12);
            
            alert("The stockSymbol1 must be less than 12 characters");
            
            
            
        }
        document.frmAccForm.stockSymbol1.focus();
        return (false);
    }
    
    return (true);
};

function taxIdValidate(){
    var taxId= document.frmAccForm.taxId;
    
    
    if (taxId.value != null && (taxId.value != "")) {
        if(taxId.value.replace(/^\s+|\s+$/g,"").length>50){
            
            str = new String(document.frmAccForm.taxId.value);
            document.frmAccForm.taxId.value=str.substring(0,50);  
            
            alert("The taxId must be less than 50 characters");
            
        }
        document.frmAccForm.taxId.focus();
        return (false);
    }
    
    return (true);
};

function stockSymbol2Validate(){
    var stockSymbol2= document.frmAccForm.stockSymbol2;
    
    
    if (stockSymbol2.value != null && (stockSymbol2.value != "")) {
        if(stockSymbol2.value.replace(/^\s+|\s+$/g,"").length>12){
            
            str = new String(document.frmAccForm.stockSymbol2.value);
            document.frmAccForm.stockSymbol2.value=str.substring(0,12);
            
            alert("The stockSymbol2 must be less than 12 characters");
            
            
            
        }
        document.frmAccForm.stockSymbol2.focus();
        return (false);
    }
    
    return (true);
};

function descriptionValidate(){
    var description= document.frmAccForm.description;
    
    
    if (description.value != null && (description.value != "")) {
        if(description.value.replace(/^\s+|\s+$/g,"").length>255){
            
            str = new String(document.frmAccForm.description.value);
            document.frmAccForm.description.value=str.substring(0,255);  
            
            alert("The description must be less than 255 characters");
            
            
            
        }
        document.frmAccForm.description.focus();
        return (false);
    }
    
    return (true);
};

function addressLine1Validate(){
    var addressLine1= document.frmAccForm.addressLine1;
    
    
    if (addressLine1.value != null && (addressLine1.value != "")) {
        if(addressLine1.value.replace(/^\s+|\s+$/g,"").length>200){
            
            str = new String( document.frmAccForm.addressLine1.value);
            document.frmAccForm.addressLine1.value=str.substring(0,200); 
            
            alert("The addressLine1 must be less than 200 characters");
            
            
            
        }
        document.frmAccForm.addressLine1.focus();
        return (false);
    }
    
    return (true);
};

function addressLine2Validate(){
    var addressLine2= document.frmAccForm.addressLine2;
    
    
    if (addressLine2.value != null && (addressLine2.value != "")) {
        if(addressLine2.value.replace(/^\s+|\s+$/g,"").length>50){
            
            str = new String(document.frmAccForm.addressLine2.value);
            document.frmAccForm.addressLine2.value=str.substring(0,50);
            
            alert("The addressLine2 must be less than 50 characters");
            
            
            
        }
        document.frmAccForm.addressLine2.focus();
        return (false);
    }
    
    return (true);
};

function cityValidate(){
alert('hi');
    var city= document.frmAccForm.city;
    
    
    if (city.value != null && (city.value != "")) {
        if(city.value.replace(/^\s+|\s+$/g,"").length>25){
            
            str = new String( document.frmAccForm.city.value);
            document.frmAccForm.city.value=str.substring(0,25);
            
            alert("The city must be less than 25 characters");
            
            
            
        }
        document.frmAccForm.city.focus();
        return (false);
    }
    
    return (true);
};

function mailStopValidate(){
    var mailStop= document.frmAccForm.mailStop;
    
    
    if (mailStop.value != null && (mailStop.value != "")) {
        if(mailStop.value.replace(/^\s+|\s+$/g,"").length>10){
            
            str = new String(document.frmAccForm.mailStop.value);
            document.frmAccForm.mailStop.value=str.substring(0,10);
            
            
            alert("The mailStop must be less than 10 characters");
            
            
        }
        document.frmAccForm.mailStop.focus();
        return (false);
    }
    
    return (true);
};

function zipValidate(){
    var zip= document.frmAccForm.zip;
    zip.value=zip.value.replace(/[A-Za-z\(\)\.\-\x\s,]/g, "");
    if (zip.value != null && (zip.value != "")) {
        if(zip.value.replace(/^\s+|\s+$/g,"").length>15){
            
            str = new String(document.frmAccForm.zip.value);
            document.frmAccForm.zip.value=str.substring(0,15);
            
            alert("The zip must be less than 15 characters");
            
        }
        document.frmAccForm.zip.focus();
        return (false);
    }
    
    return (true);
};
