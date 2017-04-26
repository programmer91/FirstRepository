
//PHONE NUMBER FORMAT SCRIPT :START   
function formatPhone(num)
{ 

str = new String(document.frmAccountAdd.phone.value);
document.frmAccountAdd.phone.value=str.replace(/[A-Za-z\(\)\.\-\x\s,]/g, "");
num=document.frmAccountAdd.phone.value;
    var _return;
  if(num.length == 10) 
  { 
  _return="(";
	var ini = num.substring(0,3);
	_return+=ini+")";
	var st = num.substring(3,6);
	_return+="-"+st+"-";
	var end = num.substring(6,10);
	_return+=end;
  
 document.frmAccountAdd.phone.value ="";
   document.frmAccountAdd.phone.value =_return;
         
  }else if(num.length > 10)
  {
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
        document.frmAccountAdd.phone.value =_return;
        document.frmAccountAdd.phone.value ="";
        document.frmAccountAdd.phone.focus();
            return false;
 }else if(num.length < 10)
 {
   alert('Please give atleast  10 charcters in PhoneNumber');
    document.frmAccountAdd.phone.value ="";
 }

return _return;
}  

//PHONE NUMBER FORMAT SCRIPT :END
function validatenumber(xxxxx) {
   
    	var maintainplus = '';
 	var numval = xxxxx.value
 	if ( numval.charAt(0)=='+' ){ var maintainplus = '+';}
 	curnumbervar = numval.replace(/[\\A-Za-z!"£$%^&*+_={};:'@#~,¦\/<>?|`¬\]\[]/g,'');
 	xxxxx.value = maintainplus + curnumbervar;
 	var maintainplus = '';
        // alert("enter integers only");
 	xxxxx.focus;
}


function accountNameValidate(){
      var accountName= document.frmAccountAdd.accountName;
    
    
    if (accountName.value != null && (accountName.value != "")) {
        if(accountName.value.replace(/^\s+|\s+$/g,"").length>60){
            
             str = new String(document.frmAccountAdd.accountName.value);
             document.frmAccountAdd.accountName.value=str.substring(0,60);
            
            alert("The accountName must be less than 60 characters");
        
              }
       document.frmAccountAdd.accountName.focus();
        return (false);
    }
  
    return (true);
};

function urlValidate(){
      var url= document.frmAccountAdd.url;
    
    
    if (url.value != null && (url.value != "")) {
        if(url.value.replace(/^\s+|\s+$/g,"").length>50){
            
                str = new String(document.frmAccountAdd.url.value);
             document.frmAccountAdd.url.value=str.substring(0,50);
            
            alert("The url must be less than 50 characters");
            
       
           
              }
       document.frmAccountAdd.url.focus();
        return (false);
    }
  
    return (true);
};

function synonymsValidate(){
      var synonyms= document.frmAccountAdd.synonyms;
    
    
    if (synonyms.value != null && (synonyms.value != "")) {
        if(synonyms.value.replace(/^\s+|\s+$/g,"").length>60){
            
             str = new String(document.frmAccountAdd.synonyms.value);
             document.frmAccountAdd.synonyms.value=str.substring(0,60);
            
            alert("The synonyms must be less than 60 characters");
            
            
           
              }
       document.frmAccountAdd.synonyms.focus();
        return (false);
    }
  
    return (true);
};

function phoneValidate(){
      var phone= document.frmAccountAdd.phone;
    
    
    if (phone.value != null && (phone.value != "")) {
        if(phone.value.replace(/^\s+|\s+$/g,"").length>16){
            
               str = new String(document.frmAccountAdd.phone.value);
             document.frmAccountAdd.phone.value=str.substring(0,16);
            
            alert("The phone must be less than 16 characters");
            
         
           
              }
       document.frmAccountAdd.phone.focus();
        return (false);
    }
  
    return (true);
};

function faxValidate(){
      var fax= document.frmAccountAdd.fax;
    
    
    if (fax.value != null && (fax.value != "")) {
        if(fax.value.replace(/^\s+|\s+$/g,"").length>16){
            
               str = new String(document.frmAccountAdd.fax.value);
             document.frmAccountAdd.fax.value=str.substring(0,16); 
            
            alert("The fax must be less than 16 characters");
            
         
           
              }
       document.frmAccountAdd.fax.focus();
        return (false);
    }
  
    return (true);
};

function leadSourceValidate(){
      var leadSource= document.frmAccountAdd.leadSource;
    
    
    if (leadSource.value != null && (leadSource.value != "")) {
        if(leadSource.value.replace(/^\s+|\s+$/g,"").length>50){
            
            str = new String(document.frmAccountAdd.leadSource.value);
             document.frmAccountAdd.leadSource.value=str.substring(0,50);
            
            alert("The leadSource must be less than 50 characters");
            
             
           
              }
       document.frmAccountAdd.leadSource.focus();
        return (false);
    }
  
    return (true);
};

function accountTeamValidate(){
      var accountTeam= document.frmAccountAdd.accountTeam;
    
    
    if (accountTeam.value != null && (accountTeam.value != "")) {
        if(accountTeam.value.replace(/^\s+|\s+$/g,"").length>20){
            
               str = new String(document.frmAccountAdd.accountTeam.value);
             document.frmAccountAdd.accountTeam.value=str.substring(0,20);
            
            alert("The accountTeam must be less than 20 characters");
            
           
           
              }
       document.frmAccountAdd.accountTeam.focus();
        return (false);
    }
  
    return (true);
};

function noOfEmployeesValidate(){
      var noOfEmployees= document.frmAccountAdd.noOfEmployees;
    
    
    if (noOfEmployees.value != null && (noOfEmployees.value != "")) {
        if(noOfEmployees.value.replace(/^\s+|\s+$/g,"").length>10){
            
               str = new String(document.frmAccountAdd.noOfEmployees.value);
             document.frmAccountAdd.noOfEmployees.value=str.substring(0,10);
            
            alert("The noOfEmployees must be less than 10 characters");
            
          
           
              }
       document.frmAccountAdd.noOfEmployees.focus();
        return (false);
    }
  
    return (true);
};

function stockSymbol1Validate(){
      var stockSymbol1= document.frmAccountAdd.stockSymbol1;
    
    
    if (stockSymbol1.value != null && (stockSymbol1.value != "")) {
        if(stockSymbol1.value.replace(/^\s+|\s+$/g,"").length>12){
            
              str = new String(document.frmAccountAdd.stockSymbol1.value);
             document.frmAccountAdd.stockSymbol1.value=str.substring(0,12);
            
            alert("The stockSymbol1 must be less than 12 characters");
            
            
           
              }
       document.frmAccountAdd.stockSymbol1.focus();
        return (false);
    }
  
    return (true);
};

function taxIdValidate(){
      var taxId= document.frmAccountAdd.taxId;
    
    
    if (taxId.value != null && (taxId.value != "")) {
        if(taxId.value.replace(/^\s+|\s+$/g,"").length>50){
            
               str = new String(document.frmAccountAdd.taxId.value);
             document.frmAccountAdd.taxId.value=str.substring(0,50);    
            
            alert("The taxId must be less than 50 characters");
            
           
           
              }
       document.frmAccountAdd.taxId.focus();
        return (false);
    }
  
    return (true);
};

function stockSymbol2Validate(){
      var stockSymbol2= document.frmAccountAdd.stockSymbol2;
    
    
    if (stockSymbol2.value != null && (stockSymbol2.value != "")) {
        if(stockSymbol2.value.replace(/^\s+|\s+$/g,"").length>12){
            
             str = new String(document.frmAccountAdd.stockSymbol2.value);
             document.frmAccountAdd.stockSymbol2.value=str.substring(0,12);
            
            alert("The stockSymbol2 must be less than 12 characters");
            
             
           
              }
       document.frmAccountAdd.stockSymbol2.focus();
        return (false);
    }
  
    return (true);
};

function descriptionValidate(){
      var description= document.frmAccountAdd.description;
    
    
    if (description.value != null && (description.value != "")) {
        if(description.value.replace(/^\s+|\s+$/g,"").length>255){
            
             str = new String(document.frmAccountAdd.description.value);
             document.frmAccountAdd.description.value=str.substring(0,255);  
            
            alert("The description must be less than 255 characters");
            
             
           
              }
       document.frmAccountAdd.description.focus();
        return (false);
    }
  
    return (true);
};



