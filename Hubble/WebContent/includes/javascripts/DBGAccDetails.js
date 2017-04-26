//Start :Script for DBGrid on Account Details
function getNextContacts(pstrWhere, pintTot) {
    var strTmp;    
    strTmp = document.frmDBContactGrid.txtContactCurr.value;
    
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
    
    document.frmDBContactGrid.txtContactCurr.value = calculate(pstrWhere, pintTot,intPg);
    
    
    document.frmDBContactGrid.txtAccActCurr.value = document.frmDBAccActGrid.txtAccActCurr.value;
    
    if(document.frmDBContactGrid.role.value == 'Vendor') {
        document.frmDBContactGrid.txtVendorAttachCurr.value = document.frmVendorAttachGrid.txtVendorAttachCurr.value;
    }
    else if(document.frmDBContactGrid.role.value == 'Admin' || document.frmDBContactGrid.role.value == 'Sales') {
        document.frmDBContactGrid.txtAllAccActCurr.value = document.frmDBAllAccActGrid.txtAllAccActCurr.value;    
        document.frmDBContactGrid.txtProjectCurr.value = document.frmDBProjectGrid.txtProjectCurr.value;    
    }
    
    /*document.frmDBContactGrid.id.value = document.frmDBContactGrid.id.value;*/
    /*
    document.frmDBContactGrid.txtGreenSheetCurr.value = document.frmDBGreenSheetGrid.txtGreenSheetCurr.value;
    document.frmDBContactGrid.txtNotesCurr.value = document.frmDBNotesGrid.txtNotesCurr.value;
    document.frmDBContactGrid.txtAttachCurr.value = document.frmDBAttachGrid.txtAttachCurr.value;*/
    document.frmDBContactGrid.txtOppCurr.value = document.frmDBOppGrid.txtOppCurr.value;
    
    
    document.frmDBContactGrid.submit();
}

function getVendorAttachment(pstrWhere, pintTot) {
    var strTmp;
    
    //strTmp = document.frmDBContactGrid.txtContactCurr.value;
    strTmp = document.frmVendorAttachGrid.txtVendorAttachCurr.value;
    //alert('strTmp::::'+strTmp);
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
    
//    document.frmVendorAttachGrid.txtVendorAttachCurr.value = calculate(pstrWhere, pintTot,intPg);
//    //alert('document.frmVendorAttachGrid.txtVendorAttachCurr.value::::'+document.frmVendorAttachGrid.txtVendorAttachCurr.value);
//    document.frmVendorAttachGrid.txtContactCurr.value = document.frmDBContactGrid.txtContactCurr.value;
//    //alert('document.frmVendorAttachGrid.txtContactCurr.value::::'+document.frmVendorAttachGrid.txtContactCurr.value);
//    document.frmVendorAttachGrid.txtAccActCurr.value = document.frmDBAccActGrid.txtAccActCurr.value;
//    //alert('document.frmVendorAttachGrid.txtAccActCurr.value::::'+document.frmVendorAttachGrid.txtAccActCurr.value);
//    //document.frmDBContactGrid.txtAllAccActCurr.value = document.frmDBAllAccActGrid.txtAllAccActCurr.value;
//    /*document.frmDBContactGrid.id.value = document.frmDBContactGrid.id.value;*/
//    //document.frmDBContactGrid.txtProjectCurr.value = document.frmDBProjectGrid.txtProjectCurr.value;
//    /*
//    document.frmDBContactGrid.txtGreenSheetCurr.value = document.frmDBGreenSheetGrid.txtGreenSheetCurr.value;
//    document.frmDBContactGrid.txtNotesCurr.value = document.frmDBNotesGrid.txtNotesCurr.value;
//    document.frmDBContactGrid.txtAttachCurr.value = document.frmDBAttachGrid.txtAttachCurr.value;*/
//    document.frmDBContactGrid.txtOppCurr.value = document.frmDBOppGrid.txtOppCurr.value;
//    
//    
//    document.frmVendorAttachGrid.submit();

document.frmVendorAttachGrid.txtVendorAttachCurr.value = calculate(pstrWhere, pintTot,intPg);
 document.frmVendorAttachGrid.txtVendorAttCur.value = document.frmVendorAttachGrid.txtVendorAttCur.value;
 document.frmVendorAttachGrid.txtVendorAttachSortCol.value = document.frmVendorAttachGrid.txtVendorAttachSortCol.value;
   
  document.frmVendorAttachGrid.txtVendorAttachSortAsc.value = document.frmVendorAttachGrid.txtVendorAttachSortAsc.value;
    
    
    document.frmVendorAttachGrid.submit();


}


function getNextGreenSheets(pstrWhere, pintTot) {
    var strTmp;
 
    strTmp = document.frmDBGreenSheetGrid.txtGreenSheetCurr.value;
 
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
    document.frmDBGreenSheetGrid.txtGreenSheetCurr.value = calculate(pstrWhere, pintTot,intPg);
// 
//    document.frmDBGreenSheetGrid.txtProjectCurr.value = document.frmDBProjectGrid.txtProjectCurr.value;
//    //document.frmDBGreenSheetGrid.txtAllAccActCurr.value = document.frmDBAllAccActGrid.txtAllAccActCurr.value;
//    document.frmDBGreenSheetGrid.txtContactCurr.value = document.frmDBContactGrid.txtContactCurr.value;
//   // document.frmDBGreenSheetGrid.txtNotesCurr.value = document.frmDBNotesGrid.txtNotesCurr.value;
//    //document.frmDBGreenSheetGrid.txtAttachCurr.value = document.frmDBAttachGrid.txtAttachCurr.value;
//    document.frmDBGreenSheetGrid.txtOppCurr.value = document.frmDBOppGrid.txtOppCurr.value;
//   // document.frmDBGreenSheetGrid.txtAccActCurr.value = document.frmDBAccActGrid.txtAccActCurr.value;
 document.frmDBGreenSheetGrid.txtGreenSheetProjectCurr.value = document.frmDBGreenSheetGrid.txtGreenSheetProjectCurr.value;
   
   document.frmDBGreenSheetGrid.txtGreenSheetContactCurr.value = document.frmDBGreenSheetGrid.txtGreenSheetContactCurr.value;
 
 document.frmDBGreenSheetGrid.txtGreenSheetOppCurr.value = document.frmDBGreenSheetGrid.txtGreenSheetOppCurr.value;
  
    document.frmDBGreenSheetGrid.submit();
}
 /*
function getNextNotes(pstrWhere, pintTot) {
    var strTmp;
 
 
    strTmp = document.frmDBNotesGrid.txtNotesCurr.value;
 
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
    document.frmDBGrid.txtNotesCurr.value = calculate(pstrWhere, pintTot,intPg);
 
    document.frmDBNotesGrid.txtProjectCurr.value = document.frmDBProjectGrid.txtProjectCurr.value;
    document.frmDBNotesGrid.txtAllAccActCurr.value = document.frmDBAllAccActGrid.txtAllAccActCurr.value;
    document.frmDBNotesGrid.txtContactCurr.value = document.frmDBContactGrid.txtContactCurr.value;
    document.frmDBNotesGrid.txtGreenSheetCurr.value = document.frmDBGreenSheetGrid.txtGreenSheetCurr.value;
    document.frmDBNotesGrid.txtAttachCurr.value = document.frmDBAttachGrid.txtAttachCurr.value;
    document.frmDBNotesGrid.txtOppCurr.value = document.frmDBOppGrid.txtOppCurr.value;
    document.frmDBNotesGrid.txtAccActCurr.value = document.frmDBAccActGrid.txtAccActCurr.value;
 
    document.frmDBNotesGrid.submit();
}
 
function getNextAttachments(pstrWhere, pintTot) {
    var strTmp;
 
 
 
    strTmp = document.frmDBAttachGrid.txtAttachCurr.value;
 
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
    document.frmDBAttachGrid.txtAttachCurr.value = calculate(pstrWhere, pintTot,intPg);
 
    document.frmDBAttachGrid.txtProjectCurr.value = document.frmDBProjectGrid.txtProjectCurr.value;
    document.frmDBAttachGrid.txtAllAccActCurr.value = document.frmDBAllAccActGrid.txtAllAccActCurr.value;
    document.frmDBAttachGrid.txtContactCurr.value = document.frmDBContactGrid.txtContactCurr.value;
    document.frmDBAttachGrid.txtGreenSheetCurr.value = document.frmDBGreenSheetGrid.txtGreenSheetCurr.value;
    document.frmDBAttachGrid.txtNotesCurr.value = document.frmDBNotesGrid.txtNotesCurr.value;
    document.frmDBAttachGrid.txtOppCurr.value = document.frmDBOppGrid.txtOppCurr.value;
    document.frmDBAttachGrid.txtAccActCurr.value = document.frmDBAccActGrid.txtAccActCurr.value;
 
    document.frmDBAttachGrid.submit();
}
 */
function getNextOpportunities(pstrWhere, pintTot) {
    var strTmp;
    
    strTmp = document.frmDBOppGrid.txtOppCurr.value;
    
    intPg = parseInt(strTmp);
    
    
    
    if (isNaN(intPg)) intPg = 1;    
    if(pintTot == 0) pintTot=1;
    //f=first,p=previous,L=last,N=next
    if ((pstrWhere == 'F' || pstrWhere == 'P') && intPg == 1) {
        alert("You are already viewing first page!");
        return;
    }
    else if ((pstrWhere == 'N' || pstrWhere == 'L') && intPg == pintTot) {
        alert("You are already viewing last page!");
        return;
    }
    document.frmDBOppGrid.txtOppCurr.value = calculate(pstrWhere, pintTot,intPg);
    
    document.frmDBOppGrid.txtProjectCurr.value = document.frmDBProjectGrid.txtProjectCurr.value;
    document.frmDBOppGrid.txtAllAccActCurr.value = document.frmDBAllAccActGrid.txtAllAccActCurr.value;
    document.frmDBOppGrid.txtContactCurr.value = document.frmDBContactGrid.txtContactCurr.value;
    //document.frmDBOppGrid.txtGreenSheetCurr.value = document.frmDBGreenSheetGrid.txtGreenSheetCurr.value;
    //document.frmDBOppGrid.txtNotesCurr.value = document.frmDBNotesGrid.txtNotesCurr.value;
    //document.frmDBOppGrid.txtAttachCurr.value = document.frmDBAttachGrid.txtAttachCurr.value;
    document.frmDBOppGrid.txtAccActCurr.value = document.frmDBAccActGrid.txtAccActCurr.value;
    
    document.frmDBOppGrid.submit();
}

function getNextAccountActivities(pstrWhere, pintTot) {
    var strTmp;
    strTmp = document.frmDBAccActGrid.txtAccActCurr.value;
    
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
    document.frmDBAccActGrid.txtAccActCurr.value = calculate(pstrWhere, pintTot,intPg);
    
    document.frmDBAccActGrid.txtAllAccActCurr.value = document.frmDBAllAccActGrid.txtAllAccActCurr.value;
    document.frmDBAccActGrid.txtContactCurr.value = document.frmDBContactGrid.txtContactCurr.value;
    
    
    document.frmDBAccActGrid.txtProjectCurr.value = document.frmDBProjectGrid.txtProjectCurr.value;
    if(document.frmDBContactGrid.role.value == 'Vendor') {
        document.frmDBContactGrid.txtVendorAttachCurr.value = document.frmVendorAttachGrid.txtVendorAttachCurr.value;
        document.frmDBAccActGrid.txtOppCurr.value = document.frmDBOppGrid.txtOppCurr.value;
    }
    /*
    document.frmDBAccActGrid.txtGreenSheetCurr.value = document.frmDBGreenSheetGrid.txtGreenSheetCurr.value;
    document.frmDBAccActGrid.txtNotesCurr.value = document.frmDBNotesGrid.txtNotesCurr.value;
    document.frmDBAccActGrid.txtAttachCurr.value = document.frmDBAttachGrid.txtAttachCurr.value;*/
    
    
    document.frmDBAccActGrid.submit();
}


function getNextAllAccountActivities(pstrWhere, pintTot) {
    var strTmp;
    strTmp = document.frmDBAllAccActGrid.txtAllAccActCurr.value;
    
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
    document.frmDBAllAccActGrid.txtAllAccActCurr.value = calculate(pstrWhere, pintTot,intPg);
    
    document.frmDBAllAccActGrid.txtAccActCurr.value = document.frmDBAccActGrid.txtAccActCurr.value;
    document.frmDBAllAccActGrid.txtContactCurr.value = document.frmDBContactGrid.txtContactCurr.value;
    
    
    document.frmDBAllAccActGrid.txtProjectCurr.value = document.frmDBProjectGrid.txtProjectCurr.value;
    if(document.frmDBContactGrid.role.value == 'Vendor') {
        document.frmDBContactGrid.txtVendorAttachCurr.value = document.frmVendorAttachGrid.txtVendorAttachCurr.value;
    }
        /*
    document.frmDBAllAccActGrid.txtGreenSheetCurr.value = document.frmDBGreenSheetGrid.txtGreenSheetCurr.value;
    document.frmDBAllAccActGrid.txtNotesCurr.value = document.frmDBNotesGrid.txtNotesCurr.value;
    document.frmDBAllAccActGrid.txtAttachCurr.value = document.frmDBAttachGrid.txtAttachCurr.value;*/
    document.frmDBAllAccActGrid.txtOppCurr.value = document.frmDBOppGrid.txtOppCurr.value;
    document.frmDBAllAccActGrid.submit();
}


function getNextProjects(pstrWhere, pintTot) {
    var strTmp;
    strTmp = document.frmDBProjectGrid.txtProjectCurr.value;
    
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
    
    document.frmDBProjectGrid.txtProjectCurr.value = calculate(pstrWhere, pintTot,intPg);
    /*document.frmDBProjectGrid.txtAllAccActCurr.value = document.frmDBAllAccActGrid.txtAllAccActCurr.value;
    document.frmDBProjectGrid.txtAccActCurr.value = document.frmDBAccActGrid.txtAccActCurr.value; 
    document.frmDBProjectGrid.txtContactCurr.value = document.frmDBContactGrid.txtContactCurr.value;
    document.frmDBProjectGrid.txtOppCurr.value = document.frmDBOppGrid.txtOppCurr.value;*/
    
    document.frmDBProjectGrid.submit();
}



//End :Scripts for DBGrid on Account Details





// Script for DB Grid Support
function doNavigate(pstrWhere, pintTot) {
    var strTmp;
    strTmp = document.frmDBGrid.txtCurr.value;
    
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
    
    document.frmDBGrid.txtCurr.value = calculate(pstrWhere, pintTot,intPg);
    
    document.frmDBGrid.submit();
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



function doSort(pstrFld, pstrOrd) {
    document.frmDBGrid.txtSortCol.value = pstrFld;
    document.frmDBGrid.txtSortAsc.value = pstrOrd;
    document.frmDBGrid.submit();
}


/*START: Search Capability for Contact Grid*/
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





function getAttachments(pstrWhere, pintTot) {
    var strTmp;
    strTmp = document.frmAttachGrid.txtAttachCurr.value;
    
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
    
    document.frmAttachGrid.txtAttachCurr.value = calculate(pstrWhere, pintTot,intPg);

    document.frmAttachGrid.submit();
}
