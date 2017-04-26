function getNextSubProjects(pstrWhere,pintTot){var strTmp;
strTmp=document.frmDBSubProjGrid.txtSubProjCurr.value;
intPg=parseInt(strTmp);
if(isNaN(intPg)){intPg=1;
}if(pintTot==0){pintTot=1;
}if((pstrWhere=="F"||pstrWhere=="P")&&intPg==1){alert("You are already viewing first page!");
return;
}else{if((pstrWhere=="N"||pstrWhere=="L")&&intPg==pintTot){alert("You are already viewing last page!");
return;
}}document.frmDBSubProjGrid.txtSubProjCurr.value=calculate(pstrWhere,pintTot,intPg);
document.frmDBSubProjGrid.txtAttachCurr.value=document.frmDBAttachGrid.txtAttachCurr.value;
document.frmDBSubProjGrid.submit();
}function getNextMapProjects(pstrWhere,pintTot){var strTmp;
strTmp=document.frmDBMapGrid.txtMapCurr.value;
intPg=parseInt(strTmp);
if(isNaN(intPg)){intPg=1;
}if(pintTot==0){pintTot=1;
}if((pstrWhere=="F"||pstrWhere=="P")&&intPg==1){alert("You are already viewing first page!");
return;
}else{if((pstrWhere=="N"||pstrWhere=="L")&&intPg==pintTot){alert("You are already viewing last page!");
return;
}}document.frmDBMapGrid.txtMapCurr.value=calculate(pstrWhere,pintTot,intPg);
document.frmDBMapGrid.txtAttachCurr.value=document.frmDBAttachGrid.txtAttachCurr.value;
document.frmDBMapGrid.submit();
}function getProjAttachment(pstrWhere,pintTot){var strTmp;
strTmp=document.frmDBAttachGrid.txtAttachCurr.value;
intPg=parseInt(strTmp);
if(isNaN(intPg)){intPg=1;
}if(pintTot==0){pintTot=1;
}if((pstrWhere=="F"||pstrWhere=="P")&&intPg==1){alert("You are already viewing first page!");
return;
}else{if((pstrWhere=="N"||pstrWhere=="L")&&intPg==pintTot){alert("You are already viewing last page!");
return;
}}document.frmDBAttachGrid.txtAttachCurr.value=calculate(pstrWhere,pintTot,intPg);
document.frmDBAttachGrid.txtSubProjCurr.value=document.frmDBSubProjGrid.txtSubProjCurr.value;
document.frmDBAttachGrid.submit();
}
function doNavigate(pstrWhere,pintTot){var strTmp;
strTmp=document.frmDBGrid.txtCurr.value;
intPg=parseInt(strTmp);
if(isNaN(intPg)){intPg=1;
}if(pintTot==0){pintTot=1;
}if((pstrWhere=="F"||pstrWhere=="P")&&intPg==1){alert("You are already viewing first page!");
return;
}else{if((pstrWhere=="N"||pstrWhere=="L")&&intPg==pintTot){alert("You are already viewing last page!");
return;
}}document.frmDBGrid.txtCurr.value=calculate(pstrWhere,pintTot,intPg);
document.frmDBGrid.submit();
}function calculate(pstrWhere,pintTot,intPg){if(pstrWhere=="F"){intPg=1;
}else{if(pstrWhere=="P"){intPg=intPg-1;
}else{if(pstrWhere=="N"){intPg=intPg+1;
}else{if(pstrWhere=="L"){intPg=pintTot;
}}}}if(intPg<1){intPg=1;
}if(intPg>pintTot){intPg=pintTot;
}return intPg;
}

/*function getProjHierarchy(pstrWhere,pintTot){var strTmp;
strTmp=document.frmDBHierarchyGrid.txtAttachCurr.value;
intPg=parseInt(strTmp);
if(isNaN(intPg)){intPg=1;
}if(pintTot==0){pintTot=1;
}if((pstrWhere=="F"||pstrWhere=="P")&&intPg==1){alert("You are already viewing first page!");
return;
}else{if((pstrWhere=="N"||pstrWhere=="L")&&intPg==pintTot){alert("You are already viewing last page!");
return;
}}document.frmDBHierarchyGrid.txtAttachCurr.value=calculate(pstrWhere,pintTot,intPg);
document.frmDBHierarchyGrid.txtSubProjCurr.value=document.frmDBHierarchyGrid.txtSubProjCurr.value;
document.frmDBHierarchyGrid.submit();
}
*/
function getNextProjectsHierarchy(pstrWhere,pintTot){var strTmp;
strTmp=document.frmDBHierarchyGrid.txtProjHierarchyCurr.value;
intPg=parseInt(strTmp);
if(isNaN(intPg)){intPg=1;
}if(pintTot==0){pintTot=1;
}if((pstrWhere=="F"||pstrWhere=="P")&&intPg==1){alert("You are already viewing first page!");
return;
}else{if((pstrWhere=="N"||pstrWhere=="L")&&intPg==pintTot){alert("You are already viewing last page!");
return;
}}document.frmDBHierarchyGrid.txtProjHierarchyCurr.value=calculate(pstrWhere,pintTot,intPg);
document.frmDBHierarchyGrid.txtProjHierarchyCurr.value=document.frmDBHierarchyGrid.txtProjHierarchyCurr.value;
document.frmDBHierarchyGrid.submit();
}

function goToPage(element) {
//alert("in go to page");
    document.frmDBHierarchyGrid.txtProjHierarchyCurr.value = element.options[element.selectedIndex].value;
    document.frmDBHierarchyGrid.submit();
}

function doSort(pstrFld,pstrOrd){
//alert("pstrOrd");
//alert(pstrFld);
//alert(pstrOrd);
//alert("second"+pstrOrd);
document.frmDBHierarchyGrid.txtProjSortCol.value=pstrFld;
document.frmDBHierarchyGrid.txtProjSortAsc.value=pstrOrd;
document.frmDBHierarchyGrid.submit();

}




