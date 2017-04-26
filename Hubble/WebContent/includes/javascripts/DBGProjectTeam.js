/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function goToPage(element) {

    document.frmDBProjectTeamGrid.txtProjTeamCurr.value = element.options[element.selectedIndex].value;
    document.frmDBProjectTeamGrid.submit();
}

function doSort(pstrFld,pstrOrd){
//alert("pstrOrd");
//alert(pstrFld);
//alert(pstrOrd);
//alert("second"+pstrOrd);
document.frmDBProjectTeamGrid.txtProjSortCol.value=pstrFld;
document.frmDBProjectTeamGrid.txtProjSortAsc.value=pstrOrd;
document.frmDBProjectTeamGrid.submit();

}





function getNextProjectsTeam(pstrWhere,pintTot){
   var strTmp;

strTmp=document.frmDBProjectTeamGrid.txtProjTeamCurr.value;
intPg=parseInt(strTmp);
if(isNaN(intPg)){intPg=1;
}if(pintTot==0){pintTot=1;
}if((pstrWhere=="F"||pstrWhere=="P")&&intPg==1){alert("You are already viewing first page!");
return;
}else{if((pstrWhere=="N"||pstrWhere=="L")&&intPg==pintTot){alert("You are already viewing last page!");
return;
}}document.frmDBProjectTeamGrid.txtProjTeamCurr.value=calculate(pstrWhere,pintTot,intPg);
document.frmDBProjectTeamGrid.txtAttachCurr1.value=document.frmDBProjectTeamGrid.txtAttachCurr1.value;
document.frmDBProjectTeamGrid.submit();
}


function calculate(pstrWhere,pintTot,intPg){if(pstrWhere=="F"){intPg=1;
}else{if(pstrWhere=="P"){intPg=intPg-1;
}else{if(pstrWhere=="N"){intPg=intPg+1;
}else{if(pstrWhere=="L"){intPg=pintTot;
}}}}if(intPg<1){intPg=1;
}if(intPg>pintTot){intPg=pintTot;
}return intPg;
}

