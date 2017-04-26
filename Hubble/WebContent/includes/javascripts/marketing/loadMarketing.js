


function hideLoad() {
    document.getElementById('addlabel1').style.display = 'none';         
    document.getElementById('addlabel2').style.display = 'none'; 
}

function divAction() {
    document.getElementById('menu-popup').style.display = 'none'; 
}

function getActivityForm() {         
    var accountId = document.frmDBGrid.accountActivId.value;         
    var contactId = document.getElementById('contactActivId').value;         
    var activityType = document.getElementById('activityTypeName').value;         
    document.location= "gridAction.action?accId="+accountId+"&contId="+contactId+"&actType="+activityType;         
}

function getActivityListAll() {    
    var accountId = document.frmDBGrid.accountActivId.value;                  
    var contactId = document.getElementById('contactActivId').value;         
    var activityType = document.getElementById('activityTypeName').value;         
    document.location= "gridActivityAll.action?accId="+accountId+"&contId="+contactId+"&actType="+activityType;         
}