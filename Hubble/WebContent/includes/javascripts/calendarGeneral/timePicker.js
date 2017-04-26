var popUp = '';
var popUpId1='';
var tempTimeInputId='';
function openTimePicker(event,popUPId) {
    //var positions = findPosition(event.target)
    //posi = positions.split(",");
    if(event.target == undefined) {
        timeInputId = window.event.srcElement.id
        positions = findPosition(window.event.srcElement)
        posi = positions.split(",");
    }else {
        timeInputId =event.target.id;
        positions = findPosition(event.target)
        posi = positions.split(",");
    }
    var div = document.createElement("div");
    div.id = "childDiv";
    popUPId1 = popUPId;
    popUp = document.getElementById(popUPId);
    popUp.style.left = posi[0]+"px";
    popUp.style.top = (parseInt(posi[1])+20)+"px";
    var hourSelect = document.createElement("select");
    var minSelect = document.createElement("select");
    if(tempTimeInputId != "" && tempTimeInputId == timeInputId) {
        //alert("in first if--"+tempTimeInputId +"---new Input id--"+timeInputId)
        //        popUp.style.display ="block";
    } else if(tempTimeInputId != "" && tempTimeInputId != timeInputId) {
        //alert("in Second if--"+tempTimeInputId +"---new Input id--"+timeInputId)
        //        popUp.style.display ="block";
        document.getElementById("hour").onchange = new Function("appendValue('"+timeInputId+"')");
        //alert(document.getElementById("hour").onchange);
        document.getElementById("minute").onchange = new Function("appendValue('"+timeInputId+"')");
        tempTimeInputId=timeInputId;
    }else {
        //alert("in else--"+tempTimeInputId +"---new Input id--"+timeInputId)
        //popUp.style.left = posi[0]+"px";
        //popUp.style.top = (parseInt(posi[1])+20)+"px";
        hourSelect.id = "hour";
        minSelect.id = "minute";
        hourSelect.onchange = new Function("appendValue('"+timeInputId+"')");
        minSelect.onchange = new Function("appendValue('"+timeInputId+"')");
        for(var index=0;index<24;index++) {
            if(parseInt(index)<10) {
                option = document.createElement("option");
                option.value = "0"+index;
                option.appendChild(document.createTextNode("0"+index));
                hourSelect.appendChild(option);
            }else {
                option = document.createElement("option");
                option.value = index;
                //option.onclick = appendValue(timeText);
                option.appendChild(document.createTextNode(index));
                hourSelect.appendChild(option);
            }
        }
        for(var index=0;index<60;index++) {
            if(parseInt(index)<10) {
                option = document.createElement("option");
                //option.onclick = appendValue(timeText);
                option.value = "0"+index;
                option.appendChild(document.createTextNode("0"+index));
                minSelect.appendChild(option);
            }else {
                option = document.createElement("option");
                option.value = index;
                option.appendChild(document.createTextNode(index));
                minSelect.appendChild(option);
            }
        }
        div.align = "left";
        div.width="80"
        var closeAnch = document.createElement("a");
        closeAnch.onclick = new Function("closeWindow()");
        closeAnch.style.cursor = "pointer";
        closeAnch.appendChild(document.createTextNode("[x]"));
        div.appendChild(hourSelect);
        div.appendChild(minSelect);
        div.appendChild(closeAnch);
/*        table = document.createElement("table");
        alert(table)
        table.width="80";
        table.bgcolor="white";
        tr = document.createElement("tr");
        td = document.createElement("td");
        td.appendChild(hourSelect);
        tr.appendChild(td);
        td = document.createElement("td");
        td.appendChild(minSelect);
        tr.appendChild(td);
        td = document.createElement("td");
        td.appendChild(closeAnch);
        tr.appendChild(td);
        table.appendChild(tr);
        div.appendChild(table);*/
        
        popUp.appendChild(div);
        popUp.style.display = "block";
        tempTimeInputId=timeInputId;
    }
    checkCurrentValue(timeInputId);
    popUp.style.display ="block";
}

function closeWindow() {
    popUp.style.display = "none";
    //clearPopup();
}

function appendValue(timeInputId) {
    //alert("append---"+timeInputId)
    timeText = document.getElementById(timeInputId);
    var hourValue = document.getElementById("hour").value;
    var minValue = document.getElementById("minute").value;
    timeText.value = hourValue+":"+minValue+":"+"00";
}

function clearPopup() {
    //alert("parent--"+popUp.id)
    //alert("Child is---"+popUp.firstChild.id)
    var child = document.getElementById(("childDiv"));
    if(child != undefined)
        popUp.removeChild(child);
}


function findPosition( oElement ) {
    if( typeof( oElement.offsetParent ) != undefined ) {
        for( var posX = 0, posY = 0; oElement; oElement = oElement.offsetParent ) {
            posX += oElement.offsetLeft;
            posY += oElement.offsetTop;
        }
        return posX+","+posY;
    } else {
        return oElement.x+","+oElement.y;
    }
}


function checkCurrentValue(timeInputId) {
    var timeInputValue = document.getElementById(timeInputId).value;
    if(timeInputValue != "" || timeInputValue != " "  ) {
        if(timeInputValue.search(":") == 2) {
            var timeInputValueArr = timeInputValue.split(":");
            document.getElementById("hour").value = timeInputValueArr[0];
            document.getElementById("minute").value= timeInputValueArr[1];
        }
    }
}