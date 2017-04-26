//Start :Script for DBGrid on OnSiteTimeSheet Details
function getNextOnsite(pstrWhere, pintTot) {
    var strTmp;
    
    strTmp = document.frmDBGridOnsite.txtOnSiteCurr.value;
    
    intPg = parseInt(strTmp);
    
    
    
    if (isNaN(intPg)) intPg = 1;
    if(pintTot == 0) pintTot=1;
    if ((pstrWhere == 'F' || pstrWhere == 'P') && intPg == 1) {
        alert("You are already viewing first page!");
        return;
    }
    else if ((pstrWhere == 'N' || pstrWhere == 'L') && intPg == pintTot) {
        alert("You are already viewing last page!");
        return;
    }
    
    document.frmDBGridOnsite.txtOnSiteCurr.value = calculate(pstrWhere, pintTot,intPg);
    
    document.frmDBGridOnsite.txtOffShoreCurr.value = document.frmDBGridOffShore.txtOffShoreCurr.value;
    
    
    document.frmDBGridOnsite.submit();
}

//Start :Script for DBGrid on OffShoreTimeSheet Details
function getNextOffShore(pstrWhere, pintTot) {
    var strTmp;
    
    strTmp = document.frmDBGridOffShore.txtOffShoreCurr.value;
    
    intPg = parseInt(strTmp);
    
    
    
    if (isNaN(intPg)) intPg = 1;
    if(pintTot == 0) pintTot=1;
    if ((pstrWhere == 'F' || pstrWhere == 'P') && intPg == 1) {
        alert("You are already viewing first page!");
        return;
    }
    else if ((pstrWhere == 'N' || pstrWhere == 'L') && intPg == pintTot) {
        alert("You are already viewing last page!");
        return;
    }
    
    document.frmDBGridOffShore.txtOffShoreCurr.value = calculate(pstrWhere, pintTot,intPg);
    
    document.frmDBGridOffShore.txtOnSiteCurr.value = document.frmDBGridOnsite.txtOnSiteCurr.value;
    
    
    document.frmDBGridOffShore.submit();
}

function calculate(pstrWhere, pintTot,intPg){
    
    if (pstrWhere == 'F')
        intPg = 1;
    else if (pstrWhere == 'P')
        intPg = intPg - 1;
    else if (pstrWhere == 'N')
        intPg = intPg + 1;
    else if (pstrWhere == 'L')
        intPg = pintTot;
    if (intPg < 1) intPg = 1;
    if (intPg > pintTot) intPg = pintTot;
    
    return intPg;
}

/*START: Search Capability for TimeSheet Grid*/
function contactSearch(){
    var searchBy = document.frmDBContactGrid.searchBy.value;
    var accountId = document.frmAccForm.id.value;
    
    if(searchBy=='Name'){
        document.frmDBContactGrid.searchBy.value="";
    }else{
        document.location = "";
        document.location = CONTENXT_PATH+"/crm/contacts/contactSearchSubmit.action?name="+searchBy+"&accountId=" +accountId;
    }
    
}
/*END: Search Capability for Contact Grid*/