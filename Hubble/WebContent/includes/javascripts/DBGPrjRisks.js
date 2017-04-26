/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function goToPage(element) {

    document.frmDBPrjRiskGrid.txtRiskCurr.value = element.options[element.selectedIndex].value;
    document.frmDBPrjRiskGrid.submit();
}

function doSort(pstrFld,pstrOrd){
//alert("pstrOrd");
//alert(pstrFld);
//alert(pstrOrd);
//alert("second"+pstrOrd);
document.frmDBPrjRiskGrid.txtRiskSortCol.value=pstrFld;
document.frmDBPrjRiskGrid.txtRiskSortAsc.value=pstrOrd;
document.frmDBPrjRiskGrid.submit();

}





function getNextRisk(pstrWhere,pintTot){
   var strTmp;

strTmp=document.frmDBPrjRiskGrid.txtRiskCurr.value;
intPg=parseInt(strTmp);
if(isNaN(intPg)){intPg=1;
}if(pintTot==0){pintTot=1;
}if((pstrWhere=="F"||pstrWhere=="P")&&intPg==1){alert("You are already viewing first page!");
return;
}else{if((pstrWhere=="N"||pstrWhere=="L")&&intPg==pintTot){alert("You are already viewing last page!");
return;
}}document.frmDBPrjRiskGrid.txtRiskCurr.value=calculate(pstrWhere,pintTot,intPg);
document.frmDBPrjRiskGrid.txtRiskCurr.value=document.frmDBPrjRiskGrid.txtRiskCurr.value;
document.frmDBPrjRiskGrid.submit();
}


function calculate(pstrWhere,pintTot,intPg){if(pstrWhere=="F"){intPg=1;
}else{if(pstrWhere=="P"){intPg=intPg-1;
}else{if(pstrWhere=="N"){intPg=intPg+1;
}else{if(pstrWhere=="L"){intPg=pintTot;
}}}}if(intPg<1){intPg=1;
}if(intPg>pintTot){intPg=pintTot;
}return intPg;
}


