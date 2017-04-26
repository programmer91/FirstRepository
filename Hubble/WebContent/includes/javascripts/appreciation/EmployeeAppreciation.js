/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function getValue()
{
   
    var x=document.getElementById("empnameById");
    var count=0;
    var res;

    if(x.toString().length>0){
        for (var i = 0; i < x.options.length; i++) {
    
            if(x.options[i].selected ==true ){
                count+=1;
                if(count<=3){
                    res=true;
                }
                else
                {
                    x.options[i].selected =false;
                    alert("only three selections are possible");
                    res=false;
                }
        
            }
        }
    }
    return res;
   
}





function fieldLenghtValidator(element){

    var i = 0;
    if (element.value != null && (element.value != "")) {
        
        if(element.id == 'appreciationTitle') i = 150;
        else if(element.id == 'subAppreciationTitle') i = 150;
        
        else if(element.id == 'appreciationSubject') i = 100;
        else if(element.id == "appreciationBodyContent") i=3000;
      
        if(element.value.replace(/^\s+|\s+$/g,"").length>i){
            str = new String(element.value);
            element.value=str.substring(0,i);
            
            alert("The "+element.id+" must be less than "+i+" characters");
            element.focus();
            return false;
        }
       
       
    }



}

function sendDetails(){
    document.getElementById("sendOrSave").value="Send";

    poplatingvalues();
    if( checkAppreciationForm()){
        document.getElementById("frmAppreciation").action="doSendAppreciation.action";
        document.getElementById("frmAppreciation").submit();
    }
};
            
function saveStaus(){
    document.getElementById("sendOrSave").value="Created";
    poplatingvalues();
    if( checkAppreciationForm()){
        document.getElementById("frmAppreciation").action="doSendAppreciation.action";
        document.getElementById("frmAppreciation").submit();
    }
    
		
                
};
            
function poplatingvalues()
{
    var skillCatArry = [];    
    $("#appreciationCC :selected").each(function(){
        skillCatArry.push($(this).val()); 

    });

    document.getElementById("CCHidden").value=skillCatArry;

    var skillCatArry1 = [];    
    $("#appreciationBCC :selected").each(function(){
        skillCatArry1.push($(this).val()); 
    });
    document.getElementById("BCCHidden").value=skillCatArry1;


    var skillCatArry2 = [];    
    $("#empnameById :selected").each(function(){
        skillCatArry2.push($(this).val()); 
    });
    document.getElementById("ToHidden").value=skillCatArry2;
                

}
            
function checkAppreciationForm()
{
                
                
    var appreciationTitle = document.getElementById("appreciationTitle").value;
    var subAppreciationTitle = document.getElementById("subAppreciationTitle").value;
    var appreciationSubject = document.getElementById("appreciationSubject").value;
    var appreciationBodyContent =  document.getElementById("appreciationBodyContent").value;
    var empnameById =  document.getElementById("empnameById").value;
    var res;
                
    if((subAppreciationTitle == "" ) || (appreciationSubject=="") || (appreciationBodyContent=="") || (appreciationTitle=="" )  || (empnameById.length=='0')){
        alert("Enter All Mandatory Fields");
        res=false;
    }
    else
    {
                    
        res=true;
    }
                
    return res;
}


function init(){
    $(document).ready(function(){
        $('#appreciationCC').selectivity('setOptions', {
            readOnly: true
        });
        $('#appreciationBCC').selectivity('setOptions', {
            readOnly: true
        });
    });
    document.getElementById("appreciationTitle").readOnly = true;
    document.getElementById("subAppreciationTitle").readOnly = true;
    document.getElementById("appreciationSubject").readOnly = true;
    document.getElementById("appreciationBodyContent").readOnly = true;
    document.getElementById("empnameById").disabled = true;
        
        
        
}


												
