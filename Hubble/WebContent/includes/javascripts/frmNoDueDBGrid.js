function goToPage(element) {
//alert("in go to page");
    document.frmNoDueDBGrid.txtCurr.value = element.options[element.selectedIndex].value;
    document.frmNoDueDBGrid.submit();
}

function doNavigate(pstrWhere,pintTot){var strTmp;
strTmp=document.frmNoDueDBGrid.txtCurr.value;
intPg=parseInt(strTmp);
if(isNaN(intPg)){intPg=1;
}if(pintTot==0){pintTot=1;
}if((pstrWhere=="F"||pstrWhere=="P")&&intPg==1){alert("You are already viewing first page!");
return;
}else{if((pstrWhere=="N"||pstrWhere=="L")&&intPg==pintTot){alert("You are already viewing last page!");
return;
}}document.frmNoDueDBGrid.txtCurr.value=calculate(pstrWhere,pintTot,intPg);
document.frmNoDueDBGrid.submit();
}function calculate(pstrWhere,pintTot,intPg){if(pstrWhere=="F"){intPg=1;
}else{if(pstrWhere=="P"){intPg=intPg-1;
}else{if(pstrWhere=="N"){intPg=intPg+1;
}else{if(pstrWhere=="L"){intPg=pintTot;
}}}}if(intPg<1){intPg=1;
}if(intPg>pintTot){intPg=pintTot;
}return intPg;
}function doSort(pstrFld,pstrOrd){
//alert("pstrOrd");
//alert(pstrFld);
//alert(pstrOrd);
//alert("second"+pstrOrd);
document.frmNoDueDBGrid.txtSortCol.value=pstrFld;
document.frmNoDueDBGrid.txtSortAsc.value=pstrOrd;
document.frmNoDueDBGrid.submit();

}

