/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function goToPage(element) {

    document.frmDBProjectTeamGrid1.txtProjTeamCurr.value = element.options[element.selectedIndex].value;
    document.frmDBProjectTeamGrid1.submit();
}

function doSort(pstrFld,pstrOrd){
//alert("pstrOrd");
//alert(pstrFld);
//alert(pstrOrd);
//alert("second"+pstrOrd);
document.frmDBProjectTeamGrid1.txtProjSortCol.value=pstrFld;
document.frmDBProjectTeamGrid1.txtProjSortAsc.value=pstrOrd;
document.frmDBProjectTeamGrid1.submit();

}





function getNextProjectsPMOTeam(pstrWhere,pintTot){
   var strTmp;

strTmp=document.frmDBProjectTeamGrid1.txtProjTeamCurr.value;
intPg=parseInt(strTmp);
if(isNaN(intPg)){intPg=1;
}if(pintTot==0){pintTot=1;
}if((pstrWhere=="F"||pstrWhere=="P")&&intPg==1){alert("You are already viewing first page!");
return;
}else{if((pstrWhere=="N"||pstrWhere=="L")&&intPg==pintTot){alert("You are already viewing last page!");
return;
}}document.frmDBProjectTeamGrid1.txtProjTeamCurr.value=calculate(pstrWhere,pintTot,intPg);
document.frmDBProjectTeamGrid1.txtAttachCurr1.value=document.frmDBProjectTeamGrid1.txtAttachCurr1.value;
document.frmDBProjectTeamGrid1.submit();
}
