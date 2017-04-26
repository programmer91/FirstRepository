// Script for DB Grid Support
function doNavigate(pstrWhere, pintTot) {
    var strTmp;
    var intPg;
    
    strTmp = document.formGrid.txtCurr.value;
    
    intPg = parseInt(strTmp);
    if (isNaN(intPg)) intPg = 1;
    if ((pstrWhere == 'F' || pstrWhere == 'P') && intPg == 1) {
        alert("You are already viewing first page!");
        return;
    }
    else if ((pstrWhere == 'N' || pstrWhere == 'L') && intPg == pintTot) {
        alert("You are already viewing last page!");
        return;
    }
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
    
    document.formGrid.txtCurr.value = intPg;
    document.formGrid.submit();
    
}

function doSort(pstrFld, pstrOrd) {
   //alert("dosort");
    document.formGrid.txtSortCol.value = pstrFld;
    document.formGrid.txtSortAsc.value = pstrOrd;
    document.formGrid.submit();
}

//------------------------------------------------------------------
var oldRow;
function SelectCurrentRow(DBId, TRId) {
    //alert('SelectCurrentRowfunc--1.1');
   
    var oTable;
    var oTBody;
    var oRow;
    var row;
    
    var srcElem;
    var selRow = -1;
    
    document.formGrid.txtDBId.value = DBId;
    //alert('SelectCurrentRowfunc--1.1 :: '+document.frmDBGrid.txtDBId.value);
    srcElem = window.event.srcElement;
    if (!srcElem) srcElem = event.target;
    
    //crawl up the tree to find the table row
    while (srcElem.tagName != "TR" && srcElem.tagName != "TABLE")
        srcElem = srcElem.parentElement;
    
    //alert(srcElem.tagName.toUpperCase());
    if(srcElem.tagName.toUpperCase() != "TR") return;
    if(srcElem.rowIndex == 0 ) return;
    
    oRow = srcElem;
    oTBody = srcElem.parentElement;
    oTable = oTBody.parentElement;
    if(oldRow!=null) {
        oldRow.runtimeStyle.backgroundColor = '';
    }
    oRow.runtimeStyle.backgroundColor = 'Red';
    oldRow=oRow;
    
    //alert("srcElem RowIndex " + oRow.rowIndex);
    //alert("txtDBID =" + document.all("txtDBId").value);
    //alert("txtTRID =" + document.frmDBGrid.txtTRId.value);
    
    document.formGrid.txtDBId.value = DBId;
    document.formGrid.txtTRId.value = oRow.rowIndex;
    
    return true;
}

/*
var oldRow1 ;
function SelectCurrentRow1(DBId,DBId1,DBId2,DBId3,TRId)
{
var oTable;
var oTBody;
var oRow;
var row;
var srcElem;
var selRow = -1;
srcElem = window.event.srcElement;
document.frmDBGrid.txtDBId.value = DBId;
document.frmDBGrid.txtDBId1.value = DBId1;
document.frmDBGrid.txtDBId2.value = DBId2;
document.frmDBGrid.txtDBId3.value = DBId3;
// document.frmDBGrid.txtTRId.value = oRow.rowIndex;
 
//crawl up the tree to find the table row
while (srcElem.tagName != "TR" && srcElem.tagName != "TABLE")
srcElem = srcElem.parentElement;
if(srcElem.tagName!= "TR") return;
if(srcElem.rowIndex == 0 ) return;
oRow = srcElem;
oTBody = srcElem.parentElement;
oTable = oTBody.parentElement;
if(oldRow1!=null)
{
oldRow1.currentStyle.backgroundColor = '';
}
oRow.runtimeStyle.backgroundColor = 'Blue';
oldRow1=oRow;
document.frmDBGrid.txtDBId.value = DBId;
document.frmDBGrid.txtDBId1.value = DBId1;
document.frmDBGrid.txtDBId2.value = DBId2;
document.frmDBGrid.txtDBId3.value = DBId3;
document.frmDBGrid.txtTRId.value = oRow.rowIndex;
 
return true;
}
 
 */


//------------------------------------------------------------------
var oldRow2;
function SelectCurrentRow2(DBId, EXID, TRId) {
    //alert('SelectCurrentRowfunc--2.1 hai');
    
    var oTable;
    var oTBody;
    var oRow;
    var row;
    
    var srcElem;
    var selRow = -1;
    //alert('hai2');
    document.formGrid.txtDBId.value = DBId;
    document.formGrid.txtExId.value = EXID;
    //alert('SelectCurrentRowfunc--1.1 :: '+document.frmDBGrid.txtDBId.value);
    //alert('hai3');
    //alert('SelectCurrentRowfunc--1.2 :: '+document.frmDBGrid.txtExId.value);
    srcElem = window.event.srcElement;
    if (!srcElem) srcElem = event.target;
    
    //crawl up the tree to find the table row
    while (srcElem.tagName != "TR" && srcElem.tagName != "TABLE")
        srcElem = srcElem.parentElement;
    
    //alert(srcElem.tagName.toUpperCase());
    if(srcElem.tagName.toUpperCase() != "TR") return;
    if(srcElem.rowIndex == 0 ) return;
    
    oRow = srcElem;
    oTBody = srcElem.parentElement;
    oTable = oTBody.parentElement;
    if(oldRow2!=null) {
        oldRow2.runtimeStyle.backgroundColor = '';
    }
    oRow.runtimeStyle.backgroundColor = 'Red';
    oldRow2=oRow;
    
    //alert("srcElem RowIndex " + oRow.rowIndex);
    //alert("txtDBID =" + document.all("txtDBId").value);
    //alert("txtTRID =" + document.frmDBGrid.txtTRId.value);
    
    document.formGrid.txtDBId.value = DBId;
    document.formGrid.txtTRId.value = oRow.rowIndex;
    
    return true;
}
// --------------------------------------------------------------------------


var oldRow1 ;
function SelectCurrentRow1(DBId,DBId1,DBId2,DBId3,DBId4,TRId) {
    document.formGrid.txtDBId.value = DBId;
    document.formGrid.txtDBId1.value = DBId1;
    document.formGrid.txtDBId2.value = DBId2;
    document.formGrid.txtDBId3.value = DBId3;
    document.formGrid.txtDBId4.value = DBId4;
    var oTable;
    var oTBody;
    var oRow;
    var row;
    var srcElem;
    var selRow = -1;
    srcElem = window.event.srcElement;
    
    // document.frmDBGrid.txtTRId.value = oRow.rowIndex;
    
    //crawl up the tree to find the table row
    while (srcElem.tagName != "TR" && srcElem.tagName != "TABLE")
        srcElem = srcElem.parentElement;
    if(srcElem.tagName!= "TR") return;
    if(srcElem.rowIndex == 0 ) return;
    oRow = srcElem;
    oTBody = srcElem.parentElement;
    oTable = oTBody.parentElement;
    if(oldRow1!=null) {
        oldRow1.currentStyle.backgroundColor = '';
    }
    oRow.runtimeStyle.backgroundColor = 'Blue';
    oldRow1=oRow;
    document.formGrid.txtDBId.value = DBId;
    document.formGrid.txtDBId1.value = DBId1;
    document.formGrid.txtDBId2.value = DBId2;
    document.formGrid.txtDBId3.value = DBId3;
    document.formGrid.txtTRId.value = oRow.rowIndex;
    
    return true;
}

// --------------------------------------
function highlite(newRow,currRow,selRow) {
    if (currRow != -1 && currRow!=selRow) {
        currRow.runtimeStyle.backgroundColor = '';
    }
    
    if (newRow != -1 && newRow!=selRow) {
        newRow.runtimeStyle.backgroundColor = 'Red';
    }
    
    currRow = newRow;
}
