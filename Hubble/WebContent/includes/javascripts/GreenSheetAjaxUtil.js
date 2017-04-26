function loadXMLDoc(url,callback) {
    //alert("URL: " + url);
    // branch for native XMLHttpRequest object
    if (window.XMLHttpRequest) {
        req = new XMLHttpRequest();
        req.onreadystatechange = callback;
        req.open("GET", url, true);
        req.send(null);
    } 
    // branch for IE/Windows ActiveX version
    else if (window.ActiveXObject) {
        isIE = true;
        req = new ActiveXObject("Microsoft.XMLHTTP");
        if (req) {
            req.onreadystatechange = callback;
            req.open("GET", url, true);
            req.send(null);
        }
    }
}

function convertGridDate(str) {
    // yyyy-mm-dd 
    // mm-dd-yyyy      
    var yyyy = str.substring(0,4);    
    var mn = str.substring(5,7);    
    var dt = str.substring(8,10);        
    var sqlDate = mn+"-"+dt+"-"+yyyy;       
    return sqlDate;   
}

//====================================================================================
//	This Function generates the HTML <TR> and <TD> for a Set of Fields in a Record
//====================================================================================
var itemNum;
var fields = '';

var name;
var convertDate;

function generateRow(tableBody,record,delimiter) {
    var row;
    var cell;
    
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    
    var fields = record.split(delimiter);
    for (var i=0;i<fields.length-1;i++) {
        cell = document.createElement( "TD" );
        cell.className="gridColumn";
        row.appendChild( cell );
        
        if(i == 4) {                        
            convertDate = convertGridDate(fields[i]);            
            cell.innerHTML = convertDate;
            }
        else if(i==5){            
            convertDate = convertGridDate(fields[i]);            
            cell.innerHTML = convertDate;
            
            currentYear = new Date().getFullYear();
            //alert('currentYear::: '+currentYear);
            currentMonth = new Date().getMonth() +1;
            //alert('currentMonth::: '+currentMonth);
            currentDay = new Date().getDate();
            //alert('currentDay::: '+currentDay);
            dateSplit = (fields[i]).split("-");
            //alert("year--"+dateSplit[0]+"--month--"+dateSplit[1]+"---day--"+dateSplit[2]+"current Dtae--"+currentDay)
            if(parseInt(dateSplit[0]) < parseInt(currentYear)) {
                //row.style.backgroundColor="#fb636d";
                row.className="gridRowEvenRed";
                //row.classNam
                //alert("less"+currentMonth)
                //alert("actual Date"+parseInt(dateSplit[1]))
                /*if(parseInt(dateSplit[1]) <= parseInt(currentMonth) ) {
                    if(parseInt(dateSplit[2]) <= parseInt(currentDay) ) {
                        alert("less"+currentMonth)
                        alert("actual Date"+parseInt(dateSplit[1]))
                 
                    }
                }*/
            } else if(parseInt(dateSplit[0]) == parseInt(currentYear)){
                if(parseInt(dateSplit[1]) < parseInt(currentMonth) ) {
                    //row.style.backgroundColor="#fb636d";
                    row.className="gridRowEvenRed";
                }else if(parseInt(dateSplit[2]) <= parseInt(currentDay)) {
                    //row.style.backgroundColor="#fb636d";
                    row.className="gridRowEvenRed";
                } 
            } 
        }
        else if(i == 1) {
            div = document.createElement("td");
            div.oncontextmenu = new Function("getGreenSheetDetails("+fields[fields.length-1]+")");
            div.innerHTML = fields[i];
            div.style.cursor = 'pointer';
            cell.appendChild(div);
        }
        else{
            
            cell.innerHTML = fields[i];        
        }
        
    }
}

function generateTableHeader(tableBody,headerFields) {
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridHeader";
    tableBody.appendChild( row );
    
    for (var i=0; i<headerFields.length; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridHeader";
        row.appendChild( cell );
        cell.innerHTML = headerFields[i];
        cell.width = 120;
    }
}

function generateTable(oTable, headerFields,records,fieldDelimiter) {	    
    
    var tbody = oTable.childNodes[0];    
    // oTable.outerHTML = str;
    
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    if(records.length >=1 && records!=""){
        generateTableHeader(tbody,headerFields);
    }    
    
    
    for(var i=0;i<records.length;i++) {
        generateRow(tbody,records[i],fieldDelimiter);               
    }    
    
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    footer.appendChild(cell);
    
    
}

function ParseAndGenerateHTML(oTable,responseString,headerFields,rowCount) {
    var start = new Date();
    var fieldDelimiter = "|";
    var recordDelimiter = "^";   
    //alert('responseString%%%% ******'+responseString);
    //alert('rowCount%%%% ******'+rowCount);
    var records = responseString.split(recordDelimiter); 
    generateTable(oTable,headerFields,records,fieldDelimiter);
    
}


/*var ua=navigator.userAgent.toLowerCase();
var MAC=ua.indexOf("mac")!=-1;
var OP=ua.indexOf("opera")!=-1;
var KO=ua.indexOf("konqueror")!=-1;
var IE=ua.indexOf("msie")!=-1&&!OP&&ua.indexOf("webtv")==-1;
var NN=ua.indexOf("gecko")!=-1;
var FF = ua.indexOf("firefox")!=-1;
var e = window.event||window.Event;*/

var mozilla=document.getElementById && !document.all
var ie=document.all


function iebody(){
    return (document.compatMode && document.compatMode!="BackCompat")? document.documentElement : document.body
}

function getGreenSheetDetails(data) {
    //init();
    document.onmousemove = showMousePos;
    /*if (ie) { // grab the x-y pos.s if browser is IE
        /*tempX = event.clientX + document.body.scrollLeft
        tempY = event.clientY + document.body.scrollTop*/
        /*alert('In IE');
        document.getElementById("menuDiv").style.left=iebody().scrollLeft+event.clientX-290
        document.getElementById("menuDiv").style.top=iebody().scrollTop+event.clientY-80
        document.getElementById("menuDiv").style.display="block"
        document.getElementById("menuDiv").style.visibility="visible"
        //return false
    }*/
    /*else if(mozilla){  // grab the x-y pos.s if browser is NS
        alert('In FF')
        /*tempX = e.pageX;
        tempY = e.pageY;
        //alert("tempX1---"+tempX);
        //alert("tempY1---"+tempY);*/
        /*alert(pageXOffset+document.getElementById("menuDiv").clientX);
        document.getElementById("menuDiv").style.left=pageXOffset+document.getElementById("menuDiv").clientX+"px"
        document.getElementById("menuDiv").style.top=pageYOffset+document.getElementById("menuDiv").clientY+"px"
        document.getElementById("menuDiv").style.display="block"
        document.getElementById("menuDiv").style.visibility="visible"
        //return false
    }  
    // catch possible negative values in NS4
    /*if (tempX < 0){tempX = 0}
    if (tempY < 0){tempY = 0}  */
    document.getElementById("menuDiv").style.top = mp.y-80 + "px";
    document.getElementById("menuDiv").style.left = mp.x-290 + "px";
    document.getElementById("menuDiv").style.display = "block";
    
    link = document.getElementById("detailsLink");
    link.onclick = new Function("gotoLink("+data+")");
    cancel = document.getElementById("cancelLink");
    cancel.onclick = new Function("hide()");
    link.style.cursor = 'pointer';
    cancel.style.cursor = 'pointer';
    //document.location = "getGreenSheetByID.action?greenSheetId="+data;
}

function gotoLink(data) {
    document.getElementById("menuDiv").style.display = 'none';
    document.location = "getGreenSheetByID.action?greenSheetId="+data;
}

function hide() {
    document.getElementById("menuDiv").style.display = 'none';
}


var mp;
function getMousePosition(e) {
    return e.pageX ? {'x':e.pageX, 'y':e.pageY} : 
    {'x':e.clientX + document.documentElement.scrollLeft + document.body.scrollLeft, 
     'y':e.clientY + document.documentElement.scrollTop + document.body.scrollTop};
}; 
function showMousePos(e) { 
    if (!e) e = event; 
    // make sure we have a reference to the event
    var input = document.getElementById('mousepos');
    mp = getMousePosition(e); 
    //input.value = 'x : ' + mp.x + ', y : ' + mp.y;
    //alert('x---'+mp.x+' y----'+mp.y);
};

