/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function addAppreciation() {
   
    var searchflag = document.getElementById('searchflag').value;
 //   alert("searchflag"+searchflag);
    window.location = "getAppreciationadd.action?searchflag="+searchflag;

}

  function SubTitleOverlay(element){
    var overlay = document.getElementById('AppOverlay');
    var specialBox1 = document.getElementById('specialBoxApp');
    
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="SubTitle";
   
   
     
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox1.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox1.style.display = "block";
         document.getElementById("Title").innerHTML=element;


    }
}


function win_open2(url){
        
                   
                      
                        newWindow=window.open(url,'height=540,width=540');
                    }
  
  function AppFormFieldsValidator(element) {
    var i=0;
    var fieldName='';
 //alert("In Field Length validator ESCV");
    if(element.value!=null&&(element.value!="")) {
        if(element.id=="appreciationTitle") { 
            fieldName = 'Title';
            i=150;
        }
        if(element.id=="surveyDescription") { 
            fieldName = 'Description';
            i=5000;
        } if(element.id=="questionTitle") { 
            fieldName = 'Question';
            i=200;
        }
        
         if(element.value.replace(/^\s+|\s+$/g,"").length>i) {
            str=new String(element.value);
            element.value=str.substring(0,i);
            alert("The "+fieldName+" length must be less than "+i+" characters");
            element.focus();
            return false;
        }
        return true;
        
        
    }
}
 function compareDates(){         
    var stdate =document.getElementById('startDate').value;
    var endate =document.getElementById('endDate').value;
    var date1 = new Date(stdate.toString());
    var date2 = new Date(endate.toString()); 
    if(date1>date2) {
        alert("EndDate must be greater than the StartDate");
        document.getElementById('endDate').value = '';
    }
}