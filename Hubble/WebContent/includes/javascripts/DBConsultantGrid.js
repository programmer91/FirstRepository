function getConsultSkill(pstrWhere,pintTot){var strTmp;
strTmp=document.frmDBSkillGrid.txtSkillCurr.value;
intPg=parseInt(strTmp);
if(isNaN(intPg)){intPg=1;
}if(pintTot==0){pintTot=1;
}if((pstrWhere=="F"||pstrWhere=="P")&&intPg==1){alert("You are already viewing first page!");
return;
}else{if((pstrWhere=="N"||pstrWhere=="L")&&intPg==pintTot){alert("You are already viewing last page!");
return;
}}document.frmDBSkillGrid.txtSkillCurr.value=calculate(pstrWhere,pintTot,intPg);
document.frmDBSkillGrid.txtAttachCurr.value=document.frmDBAttachGrid.txtAttachCurr.value;
document.frmDBSkillGrid.txtSubmittCurr.value=document.frmDBSubmittGrid.txtSubmittCurr.value;
document.frmDBSkillGrid.txtTechCurr.value=document.frmDBTechGrid.txtTechCurr.value;
document.frmDBSkillGrid.submit();
}function getResumeAttach(pstrWhere,pintTot){
//alert("in getresume attach");
var strTmp;
strTmp=document.frmDBAttachGrid.txtAttachCurr.value;
intPg=parseInt(strTmp);
if(isNaN(intPg)){intPg=1;
}if(pintTot==0){pintTot=1;
}if((pstrWhere=="F"||pstrWhere=="P")&&intPg==1){alert("You are already viewing first page!");
return;
}else{if((pstrWhere=="N"||pstrWhere=="L")&&intPg==pintTot){alert("You are already viewing last page!");
return;
}}document.frmDBAttachGrid.txtAttachCurr.value=calculate(pstrWhere,pintTot,intPg);
document.frmDBAttachGrid.txtSkillCurr.value=document.frmDBSkillGrid.txtSkillCurr.value;
document.frmDBAttachGrid.txtSubmittCurr.value=document.frmDBSubmittGrid.txtSubmittCurr.value;
document.frmDBAttachGrid.txtTechCurr.value=document.frmDBTechGrid.txtTechCurr.value;
document.frmDBAttachGrid.submit();
}function getResumeSubmitt(pstrWhere,pintTot){var strTmp;
strTmp=document.frmDBSubmittGrid.txtSubmittCurr.value;
intPg=parseInt(strTmp);
if(isNaN(intPg)){intPg=1;
}if(pintTot==0){pintTot=1;
}if((pstrWhere=="F"||pstrWhere=="P")&&intPg==1){alert("You are already viewing first page!");
return;
}else{if((pstrWhere=="N"||pstrWhere=="L")&&intPg==pintTot){alert("You are already viewing last page!");
return;
}}document.frmDBSubmittGrid.txtSubmittCurr.value=calculate(pstrWhere,pintTot,intPg);
document.frmDBSubmittGrid.txtSkillCurr.value=document.frmDBSkillGrid.txtSkillCurr.value;
document.frmDBSubmittGrid.txtAttachCurr.value=document.frmDBAttachGrid.txtAttachCurr.value;
document.frmDBSubmittGrid.txtTechCurr.value=document.frmDBTechGrid.txtTechCurr.value;
document.frmDBSubmittGrid.submit();
}function getTechReview(pstrWhere,pintTot){var strTmp;
strTmp=document.frmDBTechGrid.txtTechCurr.value;
intPg=parseInt(strTmp);
if(isNaN(intPg)){intPg=1;
}if(pintTot==0){pintTot=1;
}if((pstrWhere=="F"||pstrWhere=="P")&&intPg==1){alert("You are already viewing first page!");
return;
}else{if((pstrWhere=="N"||pstrWhere=="L")&&intPg==pintTot){alert("You are already viewing last page!");
return;
}}document.frmDBTechGrid.txtTechCurr.value=calculate(pstrWhere,pintTot,intPg);
document.frmDBTechGrid.txtSkillCurr.value=document.frmDBSkillGrid.txtSkillCurr.value;
document.frmDBTechGrid.txtAttachCurr.value=document.frmDBAttachGrid.txtAttachCurr.value;
document.frmDBTechGrid.txtSubmittCurr.value=document.frmDBSubmittGrid.txtSubmittCurr.value;
document.frmDBTechGrid.submit();
}function doNavigate(pstrWhere,pintTot){var strTmp;
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
}function doSort(pstrFld,pstrOrd){
//alert("dosort");
document.frmDBGrid.txtSortCol.value=pstrFld;
document.frmDBGrid.txtSortAsc.value=pstrOrd;
document.frmDBGrid.submit();
}

//For Consultant activity grid navigation
//Created by Anand Patnaik.
//Date : 06/18/2015

 function doActivityNavigate(pstrWhere,pintTot){
var strTmp;
strTmp=document.frmActivityDBGrid.txtCurr.value;
intPg=parseInt(strTmp);
if(isNaN(intPg)){intPg=1;
}if(pintTot==0){pintTot=1;
}if((pstrWhere=="F"||pstrWhere=="P")&&intPg==1){alert("You are already viewing first page!");
return;
}else{if((pstrWhere=="N"||pstrWhere=="L")&&intPg==pintTot){alert("You are already viewing last page!");
return;
}}document.frmActivityDBGrid.txtCurr.value=calculate(pstrWhere,pintTot,intPg);
document.frmActivityDBGrid.submit();
}
