/*function opportunityValidate(){
    var opportunity= document.frmOpportunity.opportunity;
 
 
    if (opportunity.value != null && (opportunity.value != "")) {
        if(opportunity.value.replace(/^\s+|\s+$/g,"").length>50){
 
            str = new String(document.frmOpportunity.opportunity.value);
            document.frmOpportunity.opportunity.value=str.substring(0,50);
 
            alert("The opportunity must be less than 50 characters");
 
 
 
        }
        document.frmOpportunity.opportunity.focus();
        return (false);
    }
 
    return (true);
};
 
function descriptionValidate(){
    var description= document.frmOpportunity.description;
 
 
    if (description.value != null && (description.value != "")) {
        if(description.value.replace(/^\s+|\s+$/g,"").length>255){
 
            str = new String(document.frmOpportunity.description.value);
            document.frmOpportunity.description.value=str.substring(0,255); 
 
            alert("The description must be less than 255 characters");
 
 
 
        }
        document.frmOpportunity.description.focus();
        return (false);
    }
 
    return (true);
};*/
function OpportunityValidation(){
    if(document.getElementById("title").value==null || document.getElementById("title").value=='' ||
    document.getElementById("architectId").value=='-1' || document.getElementById("regionalMgrId").value=='-1' || 
    document.getElementById("insideSalesId").value=='' ||
    document.getElementById("bdmId").value=='-1'  || document.getElementById("dueDate").value=='' ||
    document.getElementById("offshorePMId").value=='-1' ||  document.getElementById("type").value=='-1' ||
    document.getElementById("vpId").value=='-1' ||
    document.getElementById("stage").value=='-1' ||
    document.getElementById("practiceMgrId").value=='-1' || 
    document.getElementById("value").value=='' ||
    document.getElementById("description").value==''
    ){
        alert('enter mandatory fields');
        return false;
    } 
    
    else true;
   
}
function fieldLengthValidator(element){
 //alert("In Field Length validator OCV");
    var i = 0;
    if (element.value != null && (element.value != "")) {
        if(element.id=='title') i=50;
        else if(element.id='description') i=100;
    }
    if(element.value.replace(/^\s+|\s+$/g,"").length>i){
        alert('venki');
        subStringValue(i,element,"The "+element.id+" must be less than "+i+" characters");
    }
    return (true);
    
}

function subStringValue(i,element,message) {
    str = new String(element.value);
    element.value=str.substring(0,i);
    
    alert(message);
    element.focus();
    return (false);
}
