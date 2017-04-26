// This is a javascript file
var testArray=new Array();
dojo.event.topic.subscribe('/accessCalUserList', function(event, tab, tabContainer) {
    var checkUpdateTab = document.getElementById("accessCalUserCheck");
    if(checkUpdateTab.value == "" && tab.widgetId == 'calendarUsers') {
        checkUpdateTab.value = "notified";
        getCalUserList();
    } 
});

function getCalUserList() {
    //alert("I had call");
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, displayCalUsers);
    var url = CONTENXT_PATH+"/getCalUserList.action?dummy="+new Date().getTime();
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function displayCalUsers(responseXML) {
    //alert("resonsse--"+responseXML)
    var testArray=new Array();
    var i=0;
    var accessUserList = responseXML.getElementsByTagName("CAL-ACCESSLIST")[0];
    var chk = accessUserList.getElementsByTagName("VALID")[0];
    if(chk.childNodes[0].nodeValue == "false" ) {
        alert(" No Records Found for Access User List ");
    } else {
        var headerFields = new Array("S.No","Calendar User Name","Access Type","Remove User","Change Access Type");
        var gridTableId  = document.getElementById("calendarAccess");
        generateTableHeader(gridTableId,headerFields)
        for(loop = 0; loop < accessUserList.childNodes.length; loop++) {
            var userList = accessUserList.childNodes[loop];
            var listId = userList.getElementsByTagName("CAL-ACCESSLISTID")[0];
            var accessUserId = userList.getElementsByTagName("CAL-ACCESSLIST-OTHERID")[0];
            var accessTypeId = userList.getElementsByTagName("CAL-ACCESSLIST-ACCESSTYPE")[0]; 
            var userName = userList.getElementsByTagName("CAL-ACCESSLIST-USERNAME")[0]; 
            appendAccessList(loop+1,listId.childNodes[0].nodeValue,accessUserId.childNodes[0].nodeValue,accessTypeId.childNodes[0].nodeValue,userName.childNodes[0].nodeValue);
            testArray[i]=accessUserId.childNodes[0].nodeValue;
            i++;
            
        }
        //alert(accessUserId.childNodes[0].nodeValue);
    }
    var test="";
    for(var i=0;i < testArray.length; i++){
        test=test+'|'+testArray[i];
        // alert('........'+testArray[i]);
    }
    
    document.getElementById("testId").value=test;
    // getData(testArray);  document.getElementById("testId").value=tes
}


/*
 if(loop == accessUserList.childNodes.length-1) {
                var footer =document.createElement("TR");
                footer.className="gridPager";
                gridTableId.appendChild(footer);
                for(index=0;index<5;index++) {
                    cell = document.createElement("TD");
                    cell.className="gridFooter";
                    cell.colspan=5;
                    cell.height = 8;
                    footer.appendChild(cell);
                }
            }
 
 */

function appendAccessList(loop,listId,accessuserId,accessTypeId,userName) {
    var gridTableId  = document.getElementById("calendarAccess");
    row = document.createElement("TR");
    row.className="gridRowEven";
    row.id= listId;
    gridTableId.appendChild(row);
    var userListData = new Array(loop,userName,accessTypeId,listId)
    for(var index=0;index<userListData.length-1;index++) {
        cell = document.createElement("TD");
        cell.className="gridRowEven";
        row.appendChild(cell);
        if(index==2) {
            if(accessTypeId=='R') {
                cell.innerHTML = 'READ';
            } else if(accessTypeId=='RW') {
                cell.innerHTML = 'READ/WRITE';
            }
        }else {
            cell.innerHTML = userListData[index];
        }
    }
    cell = document.createElement("TD");
    row.appendChild(cell);
    img = document.createElement("img");
    img.src = CONTENXT_PATH+'/includes/images/DBGrid/Delete.png';
    img.style.cursor = "pointer";
    img.onclick = new Function("deleteUser("+listId+",'"+userName+"')");
    cell.appendChild(img);
    
    cell = document.createElement("TD");
    row.appendChild(cell);
    img = document.createElement("img");
    img.src = CONTENXT_PATH+'/includes/images/DBGrid/Delete.png';
    img.style.cursor = "pointer";
    img.onclick = new Function("updateUser("+listId+",'"+userName+"','"+accessTypeId+"','"+accessuserId+"')");
    cell.appendChild(img);
}


function deleteUser(listId,userName) {
    //    alert("delete UserListId---"+listId+"--User--"+userName);
    var checkDelete = confirm("Are you sure..?\nYou want do delete "+userName);
    if(checkDelete == true) {
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandler(req, showDeleteResult);
        var url = CONTENXT_PATH+"/deleteCalAccessUser.action?accessId="+listId+"&dummy="+new Date().getTime();
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    } else {
        alert("Delete Operation aborted By User")
        
    }
    
}

function showDeleteResult(responseXML) {
    var accUserList = responseXML.getElementsByTagName("ACCESS-USERLIST-DELETE")[0];
    var userUpdate = accUserList.getElementsByTagName("DELETE")[0];
    alert(userUpdate.childNodes[0].nodeValue);
    clearAccessList();
    getCalUserList();
}

function updateUser(listId,userName,accessTypeId,accessuserId) {
    //    alert("UpdateUserListId---"+listId+"---User--"+userName);
    var popupAdd = document.getElementById("popupAddUser");
    popupAdd.style.top = '50px';
    popupAdd.style.left = '150px';
    document.getElementById("accessUserFName").value=userName;
    document.getElementById("accessUserId").value=accessuserId;
    if(accessTypeId == 'R') {
        //        alert("in if--R")
        document.calUserDetails.accessType[0].checked="true";
        //document.calUserDetails.accessType[1].checked="false";
    }else if(accessTypeId == 'RW') {
        //        alert("in else if--RW")
        //document.calUserDetails.accessType[0].checked="false";
        document.calUserDetails.accessType[1].checked="true";
    }
    //document.getElementById("accessUserId").value="";
    document.getElementById("accessId").value=listId;
    (document.getElementById("addUserBtn")).disabled ="false";
    (document.getElementById("addUserBtn")).removeAttribute("disabled");
    popupAdd.style.display = 'block';
    
}


function addUserToList() {
    //myPopupRelocate();
    clearAddData();
    var popupAdd = document.getElementById("popupAddUser");
    popupAdd.style.top = '50px';
    popupAdd.style.left = '150px';
    popupAdd.style.display = 'block';
}

function fillUser() {
    var accUserFname  = document.getElementById("accessUserFName").value;
    //var accUserLname  = document.getElementById("accessUserLName").value;
    var userName= "";
    if(accUserFname != null && accUserFname!= "" && accUserFname != " " ) {
        userNameArr = accUserFname.split(".");
        //alert("length--"+userNameArr.length);
        userName = accUserFname;
    }
    var addUserbtn = document.getElementById("addUserBtn");
    if((userName == "" || userName ==" ")|| userNameArr.length>1 ) {
        var validationMessage=document.getElementById("validationMessageAcc");
        validationMessage.innerHTML = "";
        if(userNameArr.length<=1) {
            if(!addUserbtn.disabled) {
                addUserbtn.disabled = "true";
            }
            //            alert("ok--")
            addUserbtn.disabled;
        }
        hideScrollBarAccess();
    } else if(userName.length>3 ) {
        if(addUserbtn.disabled) {
            addUserbtn.disabled = "false";
        }
        addUserbtn.removeAttribute("disabled");
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandler(req, popUpUser);
        var url = CONTENXT_PATH+"/userPopUpList.action?userName="+userName+"&dummy="+new Date().getTime();
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
}

function popUpUser(responseXML) {
    clearAccessUserTable();
    //alert(responseXML);
    var autorow;
    autorow = document.getElementById("userListpopUp");
    autorow.style.display = 'block';
    var accUserList = responseXML.getElementsByTagName("ACCESS-USERLIST")[0];
    var completeTable = document.getElementById("completeTable3");
    if(accUserList.childNodes.length > 0) {
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");
    } else {
        clearTable();
    }
    if(accUserList.childNodes.length<10) {         
        autorow.style.overflowY = "hidden";
    }
    else {
        autorow.style.overflowY = "scroll";
    }
    var userlist = accUserList.childNodes[0];
    var chk=userlist.getElementsByTagName("VALID")[0];    
    if(chk.childNodes[0].nodeValue =="true") {
        var validationMessage=document.getElementById("validationMessageAcc");
        validationMessage.innerHTML = "";
        for (loop = 0; loop < accUserList.childNodes.length; loop++) {
            var userlist = accUserList.childNodes[loop];
            var userId = userlist.getElementsByTagName("USERID")[0]; 
            var userName = userlist.getElementsByTagName("USERNAME")[0];   
            appendUserList(userId.childNodes[0].nodeValue,userName.childNodes[0].nodeValue);
        }
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessageAcc");
        validationMessage.innerHTML = "  Name  is inValid ";
        addUserbtn = document.getElementById("addUserBtn");
        if(!addUserbtn.disabled) {
            addUserbtn.disabled = "true"
        }
        //addUserbtn.removeAttribute("disabled");
        addUserbtn.disabled;
        //alert("No Records Found");
    }
    
}

function appendUserList(userId,userName) {
    //alert(userId);
    var row;
    var nameCell;
    var completeTable = document.getElementById("completeTable3");
    if (!isIE) {
        row = completeTable.insertRow(completeTable.rows.length);
        nameCell = row.insertCell(0);
    } else {
        row = document.createElement("tr");
        row.id=userId;
        nameCell = document.createElement("td");
        //nameCell.onfocus=""
        row.appendChild(nameCell);
        completeTable.appendChild(row);
    }
    row.className = "popupRow";
    
    nameCell.setAttribute("bgcolor", "#3E93D4");
    
    var linkElement = document.createElement("a");
    linkElement.className = "popupItem";
    linkElement.setAttribute("href", "javascript:set_userToList('"+userId +"','"+ userName +"')");
    linkElement.appendChild(document.createTextNode(userName));
    //linkElement["onclick"] = new Function("getTeam("+accId+")");
    nameCell.appendChild(linkElement);
}

function set_userToList(userId,userName) {
    hideScrollBarAccess();
    document.getElementById("accessUserFName").value=userName;
    document.getElementById("accessUserId").value=userId;
    //alert("usrId--"+document.getElementById("accessUserId").value)
}

function hideScrollBarAccess() {
    autorow = document.getElementById("userListpopUp");
    autorow.style.display = 'none';
}

function clearAccessUserTable() {
    completeTable = document.getElementById("completeTable3");
    if (completeTable) {
        completeTable.setAttribute("bordercolor", "white");
        completeTable.setAttribute("border", "0");
        completeTable.style.visible = false;
        for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
}

function saveCalAccessUser() {
    var name = document.getElementById("accessUserFName").value;
    var userId = document.getElementById("accessUserId").value;
    var test=document.getElementById("testId").value;
    var test1=test.split("|");
    //alert(test1.length);
    /*for(var i=0; i < test1.length; i++){
        if(test1[i]==userId){
            alert('User already exists');
            return false;
        }
     
    }*/
    
    for(var i=0; i < testArray.length ; i++) {
        if(testArrat[i]==userId) {
            alert('user id already exists');
        }
    }
    
    var accessId = document.getElementById("accessId").value;
    //    alert("id--"+accessId)
    var accessType = document.getElementById("accessType");
    var accessTypeVal ="";
    if(accessType.checked) {
        accessTypeVal="R"
    }else {
        accessTypeVal ="RW";
    }
    
    if(accessId != "" && accessId !=" ") {
        saveType="upd";
    }else {
        for(var i=0; i < test1.length; i++){
            if(test1[i]==userId){
                alert('User already exists');
                return false;
            }
        }
        saveType="add";
    }
    //    alert("Save Type---"+saveType)
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, showResult);
    var url = CONTENXT_PATH+"/saveCalAccessUser.action?accessUserId="+userId+"&accessType="+accessTypeVal+"&saveType="+saveType+"&accessId="+accessId+"&dummy="+new Date().getTime();
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function showResult(responseXML) {
    var accUserList = responseXML.getElementsByTagName("ACCESS-USERLIST-UPDATE")[0];
    var userUpdate = accUserList.getElementsByTagName("UPDATE")[0];
    alert(userUpdate.childNodes[0].nodeValue);
    userUpdateId = accUserList.getElementsByTagName("UPDATE-ID")[0];
    //alert("table length---"+(document.getElementById("calendarAccess")).rows.length);
    var accessType = document.getElementById("accessType");
    var testIdList = document.getElementById("testId").value 
    document.getElementById("testId").value = testIdList+"|"+document.getElementById("accessUserId").value;
    //  alert("users list ----"+document.getElementById("testId").value)
    var accessTypeVal ="";
    if(accessType.checked) {
        accessTypeVal="R";
    }else {
        accessTypeVal ="RW";
    }
    if(userUpdateId.childNodes[0].nodeValue !=0) {
        userUpdateIdVal= userUpdateId.childNodes[0].nodeValue;
        //        alert("last id--"+userUpdateIdVal)
        appendAccessList((document.getElementById("calendarAccess")).rows.length,userUpdateIdVal,document.getElementById("accessUserId").value,accessTypeVal,document.getElementById("accessUserFName").value);
    } else if(document.getElementById("accessId").value !="" && userUpdateId.childNodes[0].nodeValue ==0){
        userUpdateIdVal = document.getElementById("accessId").value;
        //        alert("last esle if id--"+userUpdateIdVal);
        var rowId = document.getElementById(document.getElementById("accessId").value);
        //        alert("row id --"+rowId)
        var tableId = document.getElementById("calendarAccess");
        //        alert("row cells --"+rowId.cells[0])
        rowId.cells[1].innerHTML = document.getElementById("accessUserFName").value;
        if(accessTypeVal=="R") {
            rowId.cells[2].innerHTML = "READ";
        } else if(accessTypeVal=="RW"){
            rowId.cells[2].innerHTML = "READ/WRITE";
        }
        (document.getElementById("popupAddUser")).style.display="none";
    }
    clearAddData();
}



function clearAddData() {
    document.getElementById("accessUserFName").value="";
    document.getElementById("accessUserId").value="";
    document.getElementById("accessId").value="";
    document.calUserDetails.accessType[0].checked="true";
    (document.getElementById("addUserBtn")).disabled =true;
}


function clearAccessList() {
    tableId = document.getElementById("calendarAccess");
    var lastRow = tableId.rows.length;
    while (lastRow > 0) { 
        tableId.deleteRow(lastRow - 1);
        lastRow = tableId.rows.length; 
    }
}

