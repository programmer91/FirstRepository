function workPhoneNoFormat(num) { 
    var _return;
    str = new String(document.consultantdemographics.workPhoneNo.value);
    document.consultantdemographics.workPhoneNo.value=str.replace(/[\(\)\.\-\s,]/g, "");
    num=document.consultantdemographics.workPhoneNo.value;
    
    if(num.length == 10) { 
        _return="(";
        var ini = num.substring(0,3);
        _return+=ini+")";
        var st = num.substring(3,6);
        _return+="-"+st+"-";
        var end = num.substring(6,10);
        _return+=end;
        
        document.consultantdemographics.workPhoneNo.value ="";
        document.consultantdemographics.workPhoneNo.value =_return;
        
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
        
        document.consultantdemographics.workPhoneNo.value ="";
        document.consultantdemographics.workPhoneNo.value =_return;
        return false;
    }else if(num.length < 10) {
        alert('Please give atleast  10 charcters in PhoneNumber');
        document.consultantdemographics.workPhoneNo.value ="";
    }
    
    return _return;
}   

// This function for AddConsultant.jsp
function workPhoneNoFormat1(num) { 
    var _return;
    str = new String(document.addConsultantForm.workPhoneNo.value);
    document.addConsultantForm.workPhoneNo.value=str.replace(/[\(\)\.\-\s,]/g, "");
    num=document.addConsultantForm.workPhoneNo.value;
    
    if(num.length == 10) { 
        _return="(";
        var ini = num.substring(0,3);
        _return+=ini+")";
        var st = num.substring(3,6);
        _return+="-"+st+"-";
        var end = num.substring(6,10);
        _return+=end;
        
        document.addConsultantForm.workPhoneNo.value ="";
        document.addConsultantForm.workPhoneNo.value =_return;
        
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
        
        document.addConsultantForm.workPhoneNo.value ="";
        document.addConsultantForm.workPhoneNo.value =_return;
        return false;
    }else if(num.length < 10) {
        alert('Please give atleast  10 charcters in PhoneNumber');
        document.addConsultantForm.workPhoneNo.value ="";
        document.addConsultantForm.workPhoneNo.focus();
    }
    
    return _return;
}

function homePhoneNoFormat(num) { 
    var _return;
    str = new String(document.consultantdemographics.homePhoneNo.value);
    document.consultantdemographics.homePhoneNo.value=str.replace(/[\(\)\.\-\x\s,]/g, "");
    num=document.consultantdemographics.homePhoneNo.value;
    
    if(num.length == 10) { 
        _return="(";
        var ini = num.substring(0,3);
        _return+=ini+")";
        var st = num.substring(3,6);
        _return+="-"+st+"-";
        var end = num.substring(6,10);
        _return+=end;
        
        document.consultantdemographics.homePhoneNo.value ="";
        document.consultantdemographics.homePhoneNo.value =_return;
        
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
        
        document.consultantdemographics.homePhoneNo.value ="";
        document.consultantdemographics.homePhoneNo.value =_return;
        return false;
    }else if(num.length < 10) {
        alert('Please give atleast  10 charcters in PhoneNumber');
        document.consultantdemographics.homePhoneNo.value ="";
    }
    
    return _return;
}  

// This function for AddConsultant.jsp
function homePhoneNoFormat1(num) { 
    var _return;
    str = new String(document.addConsultantForm.homePhoneNo.value);
    document.addConsultantForm.homePhoneNo.value=str.replace(/[\(\)\.\-\s,]/g, "");
    num=document.addConsultantForm.homePhoneNo.value;
    
    if(num.length == 10) { 
        _return="(";
        var ini = num.substring(0,3);
        _return+=ini+")";
        var st = num.substring(3,6);
        _return+="-"+st+"-";
        var end = num.substring(6,10);
        _return+=end;
        
        document.addConsultantForm.homePhoneNo.value =num;
        document.addConsultantForm.homePhoneNo.value =_return;
        
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
        
        document.addConsultantForm.homePhoneNo.value ="";
        document.addConsultantForm.homePhoneNo.value =_return;
        return false;
    }else if(num.length < 10) {
        alert('Please give atleast  10 charcters in PhoneNumber');
        document.addConsultantForm.homePhoneNo.value ="";
        document.addConsultantForm.homePhoneNo.focus();
    }
    
    return _return;
}

function validatenumber(xxxxx) {
    
    var maintainplus = '';
    var temp = xxxxx; 
    var numval = xxxxx.value;
    if ( numval.charAt(0)=='+' ){
        var maintainplus = '+';
    }
    curnumbervar = numval.replace(/[\\A-Za-z!"�$%^&*+_={};:'@#~,.�\/<>?|`�\]\[]/g,'');
    if(maintainplus == '+') {
        if(numval.length>(curnumbervar.length+1)) {
            xxxxx.value = maintainplus + curnumbervar;
            alert("enter integers only");
        }
    } else if(numval.length>curnumbervar.length) {
        xxxxx.value = maintainplus + curnumbervar;
        alert("enter integers only");
    }
    formatPhone(temp);
    var maintainplus = '';
    xxxxx.focus;
}

function formatPhone(element){
    str=new String(element.value);
    //alert(str+"value is");
    if(str == null || str == ''){
        //alert("in if");
        return true;
    }else{
        element.value=str.replace(/[A-Za-z\(\)\.\-\x\s,]/g,"");
        num=element.value;
        var _return;
        if(num.length==10){
            _return="(";
            var ini=num.substring(0,3);
            _return+=ini+")";
            var st=num.substring(3,6);
            _return+="-"+st+"-";
            var end=num.substring(6,10);
            _return+=end;
            element.value="";
            element.value=_return;
        }
        else {
            if(num.length==16){
                _return="(";
                var ini=num.substring(0,3);
                _return+=ini+")";
                var st=num.substring(3,6);
                _return+="-"+st+"-";
                var last=num.substring(6,10);
                _return+=last+" X";
                var end=num.substring(10,16);
                _return+=end;
                element.value="";
                element.value=_return;
            } 
            else{
                if(num.length>16){
                    alert("Phone Number should be 16 characters");
                    element.value=_return;
                    element.value=num.substring(0,16);
                    element.value=num.substring(0,16);
                    _return="(";
                    var ini=num.substring(0,3);
                    _return+=ini+")";
                    var st=num.substring(3,6);
                    _return+="-"+st+"-";
                    var last=num.substring(6,10);
                    _return+=last+" X";
                    var end=num.substring(10,16);
                    _return+=end;
                    element.value="";
                    element.value=_return;
                    element.focus();
                    return false;
                } else{
                    if(num.length<10){
                        alert("Please give atleast  10 charcters in PhoneNumber");
                        return false;
                    } 
                    else{
                        if(num.length>10&&num.length<16){
                            alert("Please give atleast  16 charcters in PhoneNumber");
                            return false;
                        }
                    }
                }
            }
        }
        return _return;
    }
}

function checkEmail1(myForm) {
    
    if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(myForm)){
        return (true)
    } else if(document.consultantdemographics.email1.value == ""){
        return (true)
    } else {
        document.consultantdemographics.email1.value="";
        alert("Invalid E-mail Address! Please re-enter.")
        return false;
    }
}
function checkEmail2(myForm) {
    if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(myForm)){
        return (true)
    } else if(document.consultantdemographics.email2.value == ""){
        return (true)
    } else {
        document.consultantdemographics.email2.value="";
        alert("Invalid E-mail Address! Please re-enter.")
        return false;
    }
}

function newCheckEmail2(myForm) {
    if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(myForm)){
        return (true)
    } 
    else if(document.addConsultantForm.email2.value == ""){
        return (true)
    } else {
        document.addConsultantForm.email2.value="";
        //alert("Invalid E-mail Address! Please re-enter.")
        return false;
    }
}

function checkEmail3(myForm) {
    if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(myForm)){
        return (true)
    } else if(document.consultantdemographics.email3.value == ""){
        return (true)
    } else {
        document.consultantdemographics.email3.value="";
        alert("Invalid E-mail Address! Please re-enter.")
        return false;
    }
}      

function fiNameValidate(){
    
    var fiName= document.consultantdemographics.fiName;
    
    
    if (fiName.value != null && (fiName.value != "")) {
        if(fiName.value.replace(/^\s+|\s+$/g,"").length>30){
            
            str = new String(document.consultantdemographics.fiName.value);
            document.consultantdemographics.fiName.value=str.substring(0,30);
            
            alert("The firstName must be less than 30 characters");
            
        }
        document.consultantdemographics.fiName.focus();
        return (false);
    }
    
    return (true);
};

function laNameValidate(){
    
    var laName= document.consultantdemographics.laName;
    
    
    if (laName.value != null && (laName.value != "")) {
        if(laName.value.replace(/^\s+|\s+$/g,"").length>30){
            
            
            str = new String(document.consultantdemographics.laName.value);
            document.consultantdemographics.laName.value=str.substring(0,30);
            
            alert("The lastName must be less than 30 characters");
            
        }
        document.consultantdemographics.laName.focus();
        return (false);
    }
    
    return (true);
};


function fiNameValidate1(){
    
    var fiName= document.addConsultantForm.fiName;
    
    if (fiName.value != null && (fiName.value != "")) {
        if(fiName.value.replace(/^\s+|\s+$/g,"").length>30){
            
            str = new String(document.addConsultantForm.fiName.value);
            document.addConsultantForm.fiName.value=str.substring(0,30);
            
            alert("The firstName must be less than 30 characters");
            
        }
        document.addConsultantForm.fiName.focus();
        return (false);
    }
    
    return (true);
};

function laNameValidate1(){
    
    var laName= document.addConsultantForm.laName;
    
    if (laName.value != null && (laName.value != "")) {
        if(laName.value.replace(/^\s+|\s+$/g,"").length>30){
            
            str = new String(document.addConsultantForm.laName.value);
            document.addConsultantForm.laName.value=str.substring(0,30);
            
            alert("The lastName must be less than 30 characters");
            
        }
        document.addConsultantForm.laName.focus();
        return (false);
    }
    
    return (true);
};


function miNameValidate(){
    
    var miName= document.consultantdemographics.miName;
    
    
    if (miName.value != null && (miName.value != "")) {
        if(miName.value.replace(/^\s+|\s+$/g,"").length>20){
            
            
            str = new String(document.consultantdemographics.miName.value);
            document.consultantdemographics.miName.value=str.substring(0,20);
            
            alert("The middleName must be less than 20 characters");
            
        }
        document.consultantdemographics.miName.focus();
        return (false);
    }
    
    return (true);
};

function aliasNameValidate(){
    
    var aliasName= document.consultantdemographics.aliasName;
    
    
    if (aliasName.value != null && (aliasName.value != "")) {
        if(aliasName.value.replace(/^\s+|\s+$/g,"").length>15){
            
            
            str = new String(document.consultantdemographics.aliasName.value);
            document.consultantdemographics.aliasName.value=str.substring(0,15);
            
            alert("The aliasName must be less than 15 characters");
            
        }
        document.consultantdemographics.aliasName.focus();
        return (false);
    }
    
    return (true);
};

function ssnValidate(){
    
    var ssn= document.consultantdemographics.ssn;
    
    
    if (ssn.value != null && (ssn.value != "")) {
        if(ssn.value.replace(/^\s+|\s+$/g,"").length>12){
            
            
            str = new String(document.consultantdemographics.ssn.value);
            document.consultantdemographics.ssn.value=str.substring(0,12);
            
            alert("The ssn must be less than 12 characters");
            
        }
        document.consultantdemographics.ssn.focus();
        return (false);
    }
    
    return (true);
};

function email1Validate(){
    
    var email1= document.consultantdemographics.email1;
    
    
    if (email1.value != null && (email1.value != "")) {
        if(email1.value.replace(/^\s+|\s+$/g,"").length>60){
            
            
            str = new String(document.consultantdemographics.email1.value);
            document.consultantdemographics.email1.value=str.substring(0,60);
            
            alert("The officeEmail must be less than 60 characters");
            
        }
        document.consultantdemographics.email1.focus();
        return (false);
    }
    
    return (true);
};

function email2Validate(){
    
    var email2= document.consultantdemographics.email2;
    
    
    if (email2.value != null && (email2.value != "")) {
        if(email2.value.replace(/^\s+|\s+$/g,"").length>60){
            
            
            str = new String(document.consultantdemographics.email2.value);
            document.consultantdemographics.email2.value=str.substring(0,60);
            
            alert("The personalEmail must be less than 60 characters");
            
        }
        document.consultantdemographics.email2.focus();
        return (false);
    }
    
    return (true);
};

function email3Validate(){
    
    var email3= document.consultantdemographics.email3;
    
    
    if (email3.value != null && (email3.value != "")) {
        if(email3.value.replace(/^\s+|\s+$/g,"").length>60){
            
            
            str = new String(document.consultantdemographics.email3.value);
            document.consultantdemographics.email3.value=str.substring(0,60);
            
            alert("The otherEmail must be less than 60 characters");
            
        }
        document.consultantdemographics.email3.focus();
        return (false);
    }
    
    return (true);
};

function cellPhoneNoValidate(){
    
    var cellPhoneNo= document.consultantdemographics.cellPhoneNo;
    
    
    if (cellPhoneNo.value != null && (cellPhoneNo.value != "")) {
        if(cellPhoneNo.value.replace(/^\s+|\s+$/g,"").length>20){
            
            
            str = new String(document.consultantdemographics.cellPhoneNo.value);
            document.consultantdemographics.cellPhoneNo.value=str.substring(0,20);
            
            alert("The cellPhoneNo must be less than 20 characters");
            
        }
        document.consultantdemographics.cellPhoneNo.focus();
        return (false);
    }
    
    return (true);
};

// This function for AddConsultant.jsp
function cellPhoneNoValidate1(num) { 
    var _return;
    str = new String(document.addConsultantForm.cellPhoneNo.value);
    document.addConsultantForm.cellPhoneNo.value=str.replace(/[\(\)\.\-\s,]/g, "");
    num=document.addConsultantForm.cellPhoneNo.value;
    
    if(num.length == 10) { 
        _return="(";
        var ini = num.substring(0,3);
        _return+=ini+")";
        var st = num.substring(3,6);
        _return+="-"+st+"-";
        var end = num.substring(6,10);
        _return+=end;
        
        document.addConsultantForm.cellPhoneNo.value ="";
        document.addConsultantForm.cellPhoneNo.value =_return;
        
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
        
        document.addConsultantForm.cellPhoneNo.value ="";
        document.addConsultantForm.cellPhoneNo.value =_return;
        return false;
    }else if(num.length < 10) {
        alert('Please give atleast  10 charcters in PhoneNumber');
        document.addConsultantForm.cellPhoneNo.value ="";
        document.addConsultantForm.cellPhoneNo.focus();
    }
    
    return _return;
}

function hotelPhoneNoValidate(){
    
    var hotelPhoneNo= document.consultantdemographics.hotelPhoneNo;
    
    
    if (hotelPhoneNo.value != null && (hotelPhoneNo.value != "")) {
        if(hotelPhoneNo.value.replace(/^\s+|\s+$/g,"").length>20){
            
            
            str = new String(document.consultantdemographics.hotelPhoneNo.value);
            document.consultantdemographics.hotelPhoneNo.value=str.substring(0,20);
            
            alert("The hotelPhoneNo must be less than 20 characters");
            
        }
        document.consultantdemographics.hotelPhoneNo.focus();
        return (false);
    }
    
    return (true);
};

function indiaPhoneNoValidate(){
    
    var indiaPhoneNo= document.consultantdemographics.indiaPhoneNo;
    
    
    if (indiaPhoneNo.value != null && (indiaPhoneNo.value != "")) {
        if(indiaPhoneNo.value.replace(/^\s+|\s+$/g,"").length>20){
            
            
            str = new String(document.consultantdemographics.indiaPhoneNo.value);
            document.consultantdemographics.indiaPhoneNo.value=str.substring(0,20); 
            
            alert("The indiaPhoneNo must be less than 20 characters");
            
        }
        document.consultantdemographics.indiaPhoneNo.focus();
        return (false);
    }
    
    return (true);
};

function faxPhoneNoValidate(){
    
    var faxPhoneNo= document.consultantdemographics.faxPhoneNo;
    
    
    if (faxPhoneNo.value != null && (faxPhoneNo.value != "")) {
        if(faxPhoneNo.value.replace(/^\s+|\s+$/g,"").length>20){
            
            
            str = new String(document.consultantdemographics.faxPhoneNo.value);
            document.consultantdemographics.faxPhoneNo.value=str.substring(0,20); 
            
            alert("The faxPhoneNo must be less than 20 characters");
            
        }
        document.consultantdemographics.faxPhoneNo.focus();
        return (false);
    }
    
    return (true);
};

function alterPhoneNoValidate(){
    
    var alterPhoneNo= document.consultantdemographics.alterPhoneNo;
    
    
    if (alterPhoneNo.value != null && (alterPhoneNo.value != "")) {
        if(alterPhoneNo.value.replace(/^\s+|\s+$/g,"").length>20){
            
            
            str = new String(document.consultantdemographics.alterPhoneNo.value);
            document.consultantdemographics.alterPhoneNo.value=str.substring(0,20); 
            
            alert("The alterPhoneNo must be less than 20 characters");
            
        }
        document.consultantdemographics.alterPhoneNo.focus();
        return (false);
    }
    
    return (true);
};

function refferedByValidate(){
    
    var refferedBy= document.consultantdemographics.refferedBy;
    
    
    if (refferedBy.value != null && (refferedBy.value != "")) {
        if(refferedBy.value.replace(/^\s+|\s+$/g,"").length>50){
            
            
            str = new String(document.consultantdemographics.refferedBy.value);
            document.consultantdemographics.refferedBy.value=str.substring(0,50);
            
            alert("The refferedBy must be less than 50 characters");
            
        }
        document.consultantdemographics.refferedBy.focus();
        return (false);
    }
    
    return (true);
};

function refferalBonusValidate(){
    
    var refferalBonus= document.consultantdemographics.refferalBonus;
    
    
    if (refferalBonus.value != null && (refferalBonus.value != "")) {
        if(refferalBonus.value.replace(/^\s+|\s+$/g,"").length>10){
            
            
            str = new String(document.consultantdemographics.refferalBonus.value);
            document.consultantdemographics.refferalBonus.value=str.substring(0,10);
            
            alert("The refferalBonus must be less than 10 characters");
            
        }
        document.consultantdemographics.refferalBonus.focus();
        return (false);
    }
    
    return (true);
};

function ratePerHourValidate(){
    
    var ratePerHour= document.consultantdemographics.ratePerHour;
    
    
    if (ratePerHour.value != null && (ratePerHour.value != "")) {
        if(ratePerHour.value.replace(/^\s+|\s+$/g,"").length>250){
            
            
            str = new String(document.consultantdemographics.ratePerHour.value);
            document.consultantdemographics.ratePerHour.value=str.substring(0,250);
            
            alert("The ratePerHour must be less than 250 characters");
            
        }
        document.consultantdemographics.ratePerHour.focus();
        return (false);
    }
    
    return (true);
};

function rateNegotiatedValidate(){
    
    var rateNegotiated= document.consultantdemographics.rateNegotiated;
    
    
    if (rateNegotiated.value != null && (rateNegotiated.value != "")) {
        if(rateNegotiated.value.replace(/^\s+|\s+$/g,"").length>250){
            
            
            str = new String(document.consultantdemographics.rateNegotiated.value);
            document.consultantdemographics.rateNegotiated.value=str.substring(0,250);
            
            alert("The rateNegotiated must be less than 250 characters");
            
        }
        document.consultantdemographics.rateNegotiated.focus();
        return (false);
    }
    
    return (true);
};

function currentEmployerValidate(){
    
    var currentEmployer= document.consultantdemographics.currentEmployer;
    
    
    if (currentEmployer.value != null && (currentEmployer.value != "")) {
        if(currentEmployer.value.replace(/^\s+|\s+$/g,"").length>100){
            
            str = new String(document.consultantdemographics.currentEmployer.value);
            document.consultantdemographics.currentEmployer.value=str.substring(0,100);
            
            alert("The currentEmployer must be less than 100 characters");
            
            
        }
        document.consultantdemographics.currentEmployer.focus();
        return (false);
    }
    
    return (true);
};

function preferedAnswerValidate(){
    
    var preferedAnswer= document.consultantdemographics.preferedAnswer;
    
    
    if (preferedAnswer.value != null && (preferedAnswer.value != "")) {
        if(preferedAnswer.value.replace(/^\s+|\s+$/g,"").length>16){
            
            
            str = new String(document.consultantdemographics.preferedAnswer.value);
            document.consultantdemographics.preferedAnswer.value=str.substring(0,16);
            
            alert("The preferedAnswer must be less than 16 characters");
            
        }
        document.consultantdemographics.preferedAnswer.focus();
        return (false);
    }
    
    return (true);
};

function AddressLine1Validate(){
    
    var AddressLine1= document.consultantdemographics.AddressLine1;
    
    
    if (AddressLine1.value != null && (AddressLine1.value != "")) {
        if(AddressLine1.value.replace(/^\s+|\s+$/g,"").length>50){
            
            
            str = new String(document.consultantdemographics.AddressLine1.value);
            document.consultantdemographics.AddressLine1.value=str.substring(0,50);
            
            alert("The AddressLine1 must be less than 50 characters");
            
        }
        document.consultantdemographics.AddressLine1.focus();
        return (false);
    }
    
    return (true);
};

function AddressLine2Validate(){
    
    var AddressLine2= document.consultantdemographics.AddressLine2;
    
    
    if (AddressLine2.value != null && (AddressLine2.value != "")) {
        if(AddressLine2.value.replace(/^\s+|\s+$/g,"").length>50){
            
            
            str = new String(document.consultantdemographics.AddressLine2.value);
            document.consultantdemographics.AddressLine2.value=str.substring(0,50);
            
            alert("The AddressLine2 must be less than 50 characters");
            
        }
        document.consultantdemographics.AddressLine2.focus();
        return (false);
    }
    
    return (true);
};

function CityValidate(){
    
    var City= document.consultantdemographics.City;
    
    
    if (City.value != null && (City.value != "")) {
        if(City.value.replace(/^\s+|\s+$/g,"").length>25){
            
            
            str = new String(document.consultantdemographics.City.value);
            document.consultantdemographics.City.value=str.substring(0,25);
            
            alert("The City must be less than 25 characters");
            
        }
        document.consultantdemographics.City.focus();
        return (false);
    }
    
    return (true);
};

function MailStopValidate(){
    
    var MailStop= document.consultantdemographics.MailStop;
    
    
    if (MailStop.value != null && (MailStop.value != "")) {
        if(MailStop.value.replace(/^\s+|\s+$/g,"").length>10){
            
            
            str = new String(document.consultantdemographics.MailStop.value);
            document.consultantdemographics.MailStop.value=str.substring(0,10);
            
            alert("The MailStop must be less than 10 characters");
            
        }
        document.consultantdemographics.MailStop.focus();
        return (false);
    }
    
    return (true);
};

function ZipValidate(){
    
    var Zip= document.consultantdemographics.Zip;
    
    
    if (Zip.value != null && (Zip.value != "")) {
        if(Zip.value.replace(/^\s+|\s+$/g,"").length>15){
            
            
            str = new String(document.consultantdemographics.Zip.value);
            document.consultantdemographics.Zip.value=str.substring(0,15);
            
            alert("The Zip must be less than 15 characters");
            
        }
        document.consultantdemographics.Zip.focus();
        return (false);
    }
    
    return (true);
};

function curAddressLine1Validate(){
    
    var curAddressLine1= document.consultantdemographics.curAddressLine1;
    
    
    if (curAddressLine1.value != null && (curAddressLine1.value != "")) {
        if(curAddressLine1.value.replace(/^\s+|\s+$/g,"").length>50){
            
            
            str = new String(document.consultantdemographics.curAddressLine1.value);
            document.consultantdemographics.curAddressLine1.value=str.substring(0,50);
            
            alert("The curAddressLine1 must be less than 50 characters");
            
        }
        document.consultantdemographics.curAddressLine1.focus();
        return (false);
    }
    
    return (true);
};

function curAddressLine2Validate(){
    
    var curAddressLine2= document.consultantdemographics.curAddressLine2;
    
    
    if (curAddressLine2.value != null && (curAddressLine2.value != "")) {
        if(curAddressLine2.value.replace(/^\s+|\s+$/g,"").length>50){
            
            
            str = new String(document.consultantdemographics.curAddressLine2.value);
            document.consultantdemographics.curAddressLine2.value=str.substring(0,50);
            
            alert("The curAddressLine2 must be less than 50 characters");
            
        }
        document.consultantdemographics.curAddressLine2.focus();
        return (false);
    }
    
    return (true);
};

function curCityValidate(){
    
    var curCity= document.consultantdemographics.curCity;
    
    
    if (curCity.value != null && (curCity.value != "")) {
        if(curCity.value.replace(/^\s+|\s+$/g,"").length>25){
            
            
            str = new String(document.consultantdemographics.curCity.value);
            document.consultantdemographics.curCity.value=str.substring(0,25);
            
            alert("The curCity must be less than 25 characters");
            
        }
        document.consultantdemographics.curCity.focus();
        return (false);
    }
    
    return (true);
};

function curMailStopValidate(){
    
    var curMailStop= document.consultantdemographics.curMailStop;
    
    
    if (curMailStop.value != null && (curMailStop.value != "")) {
        if(curMailStop.value.replace(/^\s+|\s+$/g,"").length>10){
            
            
            str = new String(document.consultantdemographics.curMailStop.value);
            document.consultantdemographics.curMailStop.value=str.substring(0,10);
            
            alert("The curMailStop must be less than 10 characters");
            
        }
        document.consultantdemographics.curMailStop.focus();
        return (false);
    }
    
    return (true);
};

function curZipValidate(){
    
    var curZip= document.consultantdemographics.curZip;
    
    
    if (curZip.value != null && (curZip.value != "")) {
        if(curZip.value.replace(/^\s+|\s+$/g,"").length>15){
            
            
            str = new String(document.consultantdemographics.curZip.value);
            document.consultantdemographics.curZip.value=str.substring(0,15);
            
            alert("The curZip must be less than 15 characters");
            
        }
        document.consultantdemographics.curZip.focus();
        return (false);
    }
    
    return (true);
};


function offAddressLine1Validate(){
    
    var offAddressLine1= document.consultantdemographics.offAddressLine1;
    
    
    if (offAddressLine1.value != null && (offAddressLine1.value != "")) {
        if(offAddressLine1.value.replace(/^\s+|\s+$/g,"").length>50){
            
            
            str = new String(document.consultantdemographics.offAddressLine1.value);
            document.consultantdemographics.offAddressLine1.value=str.substring(0,50);
            
            alert("The offAddressLine1 must be less than 50 characters");
            
        }
        document.consultantdemographics.offAddressLine1.focus();
        return (false);
    }
    
    return (true);
};

function offAddressLine2Validate(){
    
    var offAddressLine2= document.consultantdemographics.offAddressLine2;
    
    
    if (offAddressLine2.value != null && (offAddressLine2.value != "")) {
        if(offAddressLine2.value.replace(/^\s+|\s+$/g,"").length>50){
            
            
            str = new String(document.consultantdemographics.offAddressLine2.value);
            document.consultantdemographics.offAddressLine2.value=str.substring(0,50);
            
            alert("The offAddressLine2 must be less than 50 characters");
            
        }
        document.consultantdemographics.offAddressLine2.focus();
        return (false);
    }
    
    return (true);
};

function offCityValidate(){
    
    var offCity= document.consultantdemographics.offCity;
    
    
    if (offCity.value != null && (offCity.value != "")) {
        if(offCity.value.replace(/^\s+|\s+$/g,"").length>25){
            
            
            str = new String(document.consultantdemographics.offCity.value);
            document.consultantdemographics.offCity.value=str.substring(0,25);
            
            alert("The offCity must be less than 25 characters");
            
        }
        document.consultantdemographics.offCity.focus();
        return (false);
    }
    
    return (true);
};

function offMailStopValidate(){
    
    var offMailStop= document.consultantdemographics.offMailStop;
    
    
    if (offMailStop.value != null && (offMailStop.value != "")) {
        if(offMailStop.value.replace(/^\s+|\s+$/g,"").length>10){
            
            
            str = new String(document.consultantdemographics.offMailStop.value);
            document.consultantdemographics.offMailStop.value=str.substring(0,10);
            
            alert("The offMailStop must be less than 10 characters");
            
        }
        document.consultantdemographics.offMailStop.focus();
        return (false);
    }
    
    return (true);
};

function offZipValidate(){
    
    var offZip= document.consultantdemographics.offZip;
    
    
    if (offZip.value != null && (offZip.value != "")) {
        if(offZip.value.replace(/^\s+|\s+$/g,"").length>15){
            
            
            str = new String(document.consultantdemographics.offZip.value);
            document.consultantdemographics.offZip.value=str.substring(0,15);
            
            alert("The offZip must be less than 15 characters");
            
        }
        document.consultantdemographics.offZip.focus();
        return (false);
    }
    
    return (true);
};

function othAddressLine1Validate(){
    
    var othAddressLine1= document.consultantdemographics.othAddressLine1;
    
    
    if (othAddressLine1.value != null && (othAddressLine1.value != "")) {
        if(othAddressLine1.value.replace(/^\s+|\s+$/g,"").length>50){
            
            
            str = new String(document.consultantdemographics.othAddressLine1.value);
            document.consultantdemographics.othAddressLine1.value=str.substring(0,50);
            
            alert("The othAddressLine1 must be less than 50 characters");
            
        }
        document.consultantdemographics.othAddressLine1.focus();
        return (false);
    }
    
    return (true);
};

function othAddressLine2Validate(){
    
    var othAddressLine2= document.consultantdemographics.othAddressLine2;
    
    
    if (othAddressLine2.value != null && (othAddressLine2.value != "")) {
        if(othAddressLine2.value.replace(/^\s+|\s+$/g,"").length>50){
            
            str = new String(document.consultantdemographics.othAddressLine2.value);
            document.consultantdemographics.othAddressLine2.value=str.substring(0,50);
            
            alert("The othAddressLine2 must be less than 50 characters");
            
            
        }
        document.consultantdemographics.othAddressLine2.focus();
        return (false);
    }
    
    return (true);
};

function othCityValidate(){
    
    var othCity= document.consultantdemographics.othCity;
    
    
    if (othCity.value != null && (othCity.value != "")) {
        if(othCity.value.replace(/^\s+|\s+$/g,"").length>25){
            
            
            str = new String(document.consultantdemographics.othCity.value);
            document.consultantdemographics.othCity.value=str.substring(0,25);
            
            alert("The othCity must be less than 25 characters");
            
        }
        document.consultantdemographics.othCity.focus();
        return (false);
    }
    
    return (true);
};

function othMailStopValidate(){
    
    var othMailStop= document.consultantdemographics.othMailStop;
    
    
    if (othMailStop.value != null && (othMailStop.value != "")) {
        if(othMailStop.value.replace(/^\s+|\s+$/g,"").length>10){
            
            
            str = new String(document.consultantdemographics.othMailStop.value);
            document.consultantdemographics.othMailStop.value=str.substring(0,10);
            
            alert("The othMailStop must be less than 10 characters");
            
        }
        document.consultantdemographics.othMailStop.focus();
        return (false);
    }
    
    return (true);
};


function othZipValidate(){
    
    var othZip= document.consultantdemographics.othZip;
    
    
    if (othZip.value != null && (othZip.value != "")) {
        if(othZip.value.replace(/^\s+|\s+$/g,"").length>15){
            
            
            str = new String(document.consultantdemographics.othZip.value);
            document.consultantdemographics.othZip.value=str.substring(0,15);
            
            alert("The othZip must be less than 15 characters");
            
        }
        document.consultantdemographics.othZip.focus();
        return (false);
    }
    
    return (true);
};

function commentsValidate(){
    
    var comments= document.consultantdemographics.comments;
    
    
    if (comments.value != null && (comments.value != "")) {
        if(comments.value.replace(/^\s+|\s+$/g,"").length>1000){
            
            
            str = new String(document.consultantdemographics.comments.value);
            document.consultantdemographics.comments.value=str.substring(0,1000);
            
            alert("The comments must be less than 1000 characters");
            
        }
        document.consultantdemographics.comments.focus();
        return (false);
    }
    
    return (true);
};

function commentsValidate1(){
    
    var comments= document.addConsultantForm.comments;
    
    
    if (comments.value != null && (comments.value != "")) {
        if(comments.value.replace(/^\s+|\s+$/g,"").length>1000){
            
            
            str = new String(document.addConsultantForm.comments.value);
            document.addConsultantForm.comments.value=str.substring(0,1000);
            
            alert("The comments must be less than 1000 characters");
            
        }
        document.addConsultantForm.comments.focus();
        return (false);
    }
    
    return (true);
};

function skillsValidate() {
    var skills= document.addConsultantForm.skills;
    
    
    if (skills.value != null && (skills.value != "")) {
        if(skills.value.replace(/^\s+|\s+$/g,"").length>250){
            
            
            str = new String(document.addConsultantForm.skills.value);
            document.addConsultantForm.skills.value=str.substring(0,250);
            
            alert("The skills must be less than 250 characters");
            
        }
        document.addConsultantForm.skills.focus();
        return (false);
    }
    
    return (true);
};

function searchNameValidate(){
    
    var searchName= document.search.searchName;
    
    
    if (searchName.value != null && (searchName.value != "")) {
        if(searchName.value.replace(/^\s+|\s+$/g,"").length>30){
            
            
            str = new String(document.search.searchName.value);
            document.search.searchName.value=str.substring(0,30);
            
            alert("The Consultant Name must be less than 30 characters");
            
        }
        document.search.searchName.focus();
        return (false);
    }
    
    return (true);
};

function searchPhoneValidate(){
    
    var searchPhone= document.search.searchPhone;
    
    
    if (searchPhone.value != null && (searchPhone.value != "")) {
        if(searchPhone.value.replace(/^\s+|\s+$/g,"").length>20){
            
            
            str = new String(document.search.searchPhone.value);
            document.search.searchPhone.value=str.substring(0,20);
            
            alert("The CellPhoneNo must be less than 20 characters");
            
        }
        document.search.searchPhone.focus();
        return (false);
    }
    
    return (true);
};



/*    if (fiName == "") {
        if (laName == "") {
            if (gender == "") {
                if (email1 == "" && email2 == "" && email3 == "") {
                    if (cellPhoneNo == "" && hotelPhoneNo == "" && indiaPhoneNo == "" && faxPhoneNo == "" && alterPhoneNo == "" && homePhoneNo == "" && workPhoneNo== "") {
                        document.getElementById("fiNameError").style.display = "inline";
                        document.getElementById("laNameError").style.display = "inline";
                        document.getElementById("genderError").style.display = "inline";
                        document.getElementById("emailError").style.display = "inline";
                        document.getElementById("phoneNoError").style.display = "inline";
                        document.getElementById("fiName").select();
                        document.getElementById("fiName").focus();
                        return false;
                    }
                } else {
                    hideAllErrors();
                    document.getElementById("fiNameError").style.display = "inline";
                    document.getElementById("laNameError").style.display = "inline";
                    document.getElementById("genderError").style.display = "inline";
                    document.getElementById("emailError").style.display = "inline";
                    document.getElementById("fiName").select();
                    document.getElementById("fiName").focus();
                    return false;
                } 
            } else {
                hideAllErrors();
                document.getElementById("fiNameError").style.display = "inline";
                document.getElementById("laNameError").style.display = "inline";
                document.getElementById("genderError").style.display = "inline";
                document.getElementById("fiName").select();
                document.getElementById("fiName").focus();
                return false;
            }
        } else {
            hideAllErrors();
            document.getElementById("fiNameError").style.display = "inline";
            document.getElementById("laNameError").style.display = "inline";
            document.getElementById("fiName").select();
            document.getElementById("fiName").select();
            return false;
        }
    } else {
        hideAllErrors();
        document.getElementById("fiNameError").style.display = "inline";
        document.getElementById("fiName").select();
        document.getElementById("fiName").focus();
        return false;
    }
    return true;
};*/

function checkConsultantAddForm() {
    alert("hi");
    var fname = document.addConsultantForm.fiName.value;
    var lname = document.addConsultantForm.laName.value;
    var email = document.addConsultantForm.email2.value;
    var offPhone = document.addConsultantForm.workPhoneNo.value;
    var cellPhone = document.addConsultantForm.cellPhoneNo.value;
    var homPhone = document.addConsultantForm.homePhoneNo.value;
    var gender = document.addConsultantForm.gender.value;
        
    if(gender == '1') {
        alert("Enter Mandatory Fields");
        return false;
    }else if(fname=="" || lname=="" || email=="" && gender == '1') {
        alert("Enter Mandatory Fields");
        return false;
    }else if(fname=="" || lname=="" || email==""  || (offPhone=="" && homPhone=="" && cellPhone=="")) {
        alert("Enter atleast One Phone No.");
        return false;
    }else {
        return true;
    }
/*if(offPhone < 10 || cellPhone < 10 || homPhone < 10) {
        alert("Hi");
        alert('Please give atleast  10 charcters in PhoneNumber');
        return false;
    }else {
        return true;
    }*/
    
}

function skillValidate() {
    document.addConsultantForm.skills.value=filterNum(document.addConsultantForm.skills.value)

    function filterNum(str) {
        re = /\$|@|~|`|\%|\^|\+|\=|\[|\_|\]|\[|\}|\{|\;|\:|\'|\"|\<|\>|\?|\||\\|\!|\$/g;
        // remove special characters like "$" and "," etc...
        return str.replace(re, ",");
    //\(|\)|           |\.
    }
}

function skillLengthValidate() {
    var skills= document.addConsultantForm.skills;
    
    
    if (skills.value != null && (skills.value != "")) {
        if(skills.value.replace(/^\s+|\s+$/g,"").length>250){
            
            
            str = new String(document.addConsultantForm.skills.value);
            document.addConsultantForm.skills.value=str.substring(0,250);
            
            alert("The Skills must be less than 250 characters");
            
        }
        document.addConsultantForm.skills.focus();
        return (false);
    }
    
    return (true);
}
function doConsultantForward() {
    var requirementId = document.getElementById("requirementId").value;
    var consultantId = document.getElementById("consultantId").value;
    var consultId = document.getElementById("consultId").value;
    var status=document.getElementById("status").value;
    var assignedTo2="";
    var assignedTo3="";
    //var requirementAdminFlag = document.getElementById("requirementAdminFlag").value;
    var requirementAdminFlag = "YES";
    window.location = "../../recruitment/consultant/reviews.action?consultantId="+consultantId+"&recConsultantId="+consultId+"&requirementId="+requirementId+"&requirementAdminFlag="+requirementAdminFlag+"&assignedTo2="+assignedTo2+"&assignedTo3="+assignedTo3+"&status="+status;

}
function checkStatus() {
    var status = document.getElementById("status").value;
    var roleName = document.getElementById("roleName").value;
     var prestatus= document.getElementById("preStatus").value;
     //alert("test"+prestatus)
     if(getStatusValue(status)<getStatusValue(prestatus)){
         alert('You can not update to previous status.');
         document.getElementById("status").value=prestatus;
         return false;
     }
    if(roleName=="Sales"){
        if( status=="Sales Accepted" || status=="Sales Reject" || status=="Client Submission" || 
            status=="Client Reject"  || status=="Client Interview" || status=="Client Interview Reject" ||
            status=="Offered" || status=="Not Joined" || status=="Joined" || status=="Dropped/Backout" || 
            status=="Hold"){
                    
                    
        }
        else{
            alert("Your not Authorized to update this Status");
           
            document.getElementById("status").value=prestatus;
            return false;
        }
    
    }
   // else if(roleName=="Recruitment"){
        //                     if( status=="Sales Accepted" || status=="Sales Reject" || status=="Client Submission" || 
        //                       status=="Client Reject"  || status=="Client Interview" || status=="Client Interview Reject" ||
        //                       status=="Offered" || status=="Not Joined" || status=="Joined" || status=="Dropped/Backout" || 
        //                       status=="Hold" ||status=="Tech Screen shotlisted" || status=="Tech Screen Reject" ){
        //                     alert("you can not change this status");
        //                        document.getElementById("status").value="-1";
        //                        return false;
        //                    
        //                    }
        if((status=='Tech Screen  - Phone'||status=='Tech Screen  - Skype/F2F' ||status=='Face to Face')){
            if(roleName=="Recruitment"){
                 document.getElementById("forwardLink").style.display="block";
            }
           else{
                document.getElementById("forwardLink").style.display="none";
            document.getElementById("status").value=prestatus;
            alert("Only recruiter can forward to the technical review");
           }
        }else {
            document.getElementById("forwardLink").style.display="none";
            
        // alert("hii");
        // document.getElementById("updateButton").disabled=false;
        }
   // }
   
   
   
                
              
}

function consultantValidate(){
   
     var consultantName= document.getElementById("consultantName").value;
    var  email2 =document.getElementById("email2").value;
    var  AvailableFrom =document.getElementById("AvailableFrom").value;
    var  cellPhoneNo =document.getElementById("cellPhoneNo").value;
    var  workAuthorization =document.getElementById("workAuthorization").value;
    var  status =document.getElementById("status").value;
    var  targetRate =document.getElementById("targetRate").value;
    var prestatus= document.getElementById("preStatus").value;
    var resumes = document.getElementById("resumes").value;
  // alert(" i am in ")
     
     
     if(consultantName == ''){
          alert("Consultant Name should not be empty.")
        return false;
     }
    if(email2==''){
        alert("Consultant Email should not be empty.")
        return false;
    }
    if(resumes==''){
        alert("Consultant resume should not be empty.")
        return false;
    }
    if(targetRate==''){
        alert("Rate should not be empty.")
        return false;
    }
    //alert("test"+AvailableFrom+"asd");
    if(AvailableFrom==''){
        alert("AvailableFrom should not be empty.")
        return false;
    }
    if(cellPhoneNo==''){
        alert("CellPhoneNo should not be empty.")
        return false;
    }
    if(workAuthorization=='1'){
        alert("Please select Work Authorization.")
        return false;
    }
    if(status=='-1'){
        alert("Please select status.")
        return false;
    }
    if(status=='Tech Screen  - Phone'||status=='Tech Screen  - Skype/F2F' ||status=='Face to Face'){
        //  alert("OnLoad image"+"<img SRC='../../includes/images/DBGrid/forward.png' WIDTH=20 HEIGHT=20 BORDER=0 ALTER='Forward'></a>" );
        document.getElementById("updatealertId").style.display="block";
        return false;
    }
    if(status=='Tech Screen shotlisted'||status=='Tech Screen Reject'){
        alert("Sorry! this status is allowed to Pre-sales person.")
        return false;
    }
    if(getStatusValue(status)<getStatusValue(prestatus)){
         
         document.getElementById("status").value=prestatus;
         alert('You can not update to previous status.');
         return false;
     }
     
     if(document.getElementById("currentEmployer").value == '' || document.getElementById("currentEmployer").value == null){
         alert("currentEmployer should not be empty ")
         return false;
     }
   //  alert(document.getElementById("currentLocation").value.length == 0);
    
     if(document.getElementById("currentLocation").value == '' || document.getElementById("currentLocation").value == null){
         alert("currentLocation should not be empty ")
         return false;
     }
//     if(document.getElementById("workAuthorizationCopy").value == '' || document.getElementById("workAuthorizationCopy").value == null){
//         alert("workAuthorizationCopy should not be empty,Please Upload a file ")
//         return false;
//     }
//     if(document.getElementById("onProject").value == '-1' || document.getElementById("onProject").value == null){
//         alert("onProject should not be empty ")
//         return false;
//     }
//     if(document.getElementById("dlCopyAttached").value == '' || document.getElementById("dlCopyAttached").value == null){
//         alert("dlCopyAttached should not be empty,Please Upload a file ")
//         return false;
//     }
//     if(document.getElementById("projectEnd").value == '' || document.getElementById("projectEnd").value == null){
//         alert("projectEnd should not be empty ")
//         return false;
//     }
     if(document.getElementById("relocation").value == '-1' || document.getElementById("relocation").value == null){
         alert("relocation should not be empty ")
         return false;
     }
//     if(document.getElementById("changeReason").value == '' || document.getElementById("changeReason").value == null){
//         alert("changeReason should not be empty ")
//         return false;
//     }
//     if(document.getElementById("yearOfCompletion").value == '' || document.getElementById("yearOfCompletion").value == null){
//         alert("yearOfCompletion should not be empty ")
//         return false;
//     }
    // alert("----")
      if(isNaN(document.getElementById("yearOfCompletion").value)) 
    {
        alert("Please enter numeric values for yearOfCompletion");
        return false;
    }
//     if(document.getElementById("availability").value == '-1' || document.getElementById("availability").value == null){
//         alert("availability should not be empty ")
//         return false;
//     }
//     if(document.getElementById("startDatetoUs").value == '' || document.getElementById("startDatetoUs").value == null){
//         alert("startDatetoUs should not be empty ")
//         return false;
//     }
//     if(document.getElementById("educationDetails").value == '' || document.getElementById("educationDetails").value == null){
//         alert("educationDetails should not be empty ")
//         return false;
//     }
//     if(document.getElementById("reference").value == '' || document.getElementById("reference").value == null){
//         alert("reference should not be empty ")
//         return false;
//     }
     
    return true;
}

function getStatusValue(status){
    var val;
    switch (status) {
        case 'Assigned':
            val = 1;
            break;
        case 'Evaluation Reject':
            val = 2;
            break;
        case 'Tech Screen  - Phone':
            val = 3;
            break;
        case 'Tech Screen  - Skype/F2F':
            val = 4;
            break;
        case 'Face to Face':
            val = 5;
            break;
        case 'Tech Screen shotlisted':
            val = 6;
            break;
        case 'Tech Screen Reject':
            val = 7;
            break;
        case 'Submitted to Sales':
            val = 8;
            break;
        case 'Sales Accepted':
            val = 9;
            break;
        case 'Sales Reject':
            val = 10;
            break;
        case 'Client Submission':
            val = 11;
            break;
        case 'Client Reject':
            val = 12;
            break;
        case 'Client Interview':
            val = 13;
            break;
        case 'Client Interview Reject':
            val = 14;
            break;
        case 'Offered':
            val = 15;
            break;
        case 'Not Joined':
            val = 16;
            break;
        case 'Joined':
            val = 17;
            break;
        case 'Dropped/Backout':
            val = 18;
            break;
        case 'Hold':
            val = 19;
            break;
		
        default:
            val = 0;
    }
    return parseInt(val);
}

