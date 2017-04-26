var foo = "bar";

function newXMLHttpRequest() {
    var xmlreq = false;
    if(window.XMLHttpRequest) {
        xmlreq = new XMLHttpRequest();
    } else if(window.ActiveXObject) {
        try {
            xmlreq = new ActiveXObject("MSxm12.XMLHTTP");
        } catch(e1) {
            try {
                xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
            } catch(e2) {
                xmlreq = false;
            }
        }
    }
    return xmlreq;
}
function readyStateHandler(req,responseHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("loadActMessage").style.display = 'none';
                responseHandler(req.responseText);
            }
        } else {
            document.getElementById("loadActMessage").style.display = 'block';
        //alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }
}

function readyStateHandlerPriority(req,responseHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("loadActMessagePriority").style.display = 'none';
                
                responseHandler(req.responseText);
            }
        } else {
            document.getElementById("loadActMessagePriority").style.display = 'block';
       // alert("HTTP error ---"+req.status+" : "+req.statusText);
        }
    }
}

function getProjectTeam()
{
    //alert("getProjectTeam");
    var projectId = document.getElementById("projectId").value;
    var tableId = document.getElementById("tblUpdateForAccountsListByPriority");
    ClrTable(tableId);
     if(projectId == "-1")
    {
        alert("Please select project name");
        projectId = "-1";
        return fasle;
        
    }
 var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerPriority(req, populateTeamMembers);
    var url = CONTENXT_PATH+"/getProjectTeam.action?projectId="+projectId;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateTeamMembers(response) {
    var tableId = document.getElementById("tblUpdateForAccountsListByPriority");
   // var headerFields = new Array("S.No","ResourceName","Email","EmpType","Title","Type","Work PhoneNo","Mobile Phone");
  //  var headerFields = new Array("S.No","ResourceName","Country","Email","EmpType","Title","Type","Work PhoneNo","Mobile Phone");
 // var headerFields = new Array("S.No","ResourceName","Country","Email","StartDate","Title","Type","Work PhoneNo","Mobile Phone");
 var headerFields = new Array("S.No","ResourceName","Country","Email","StartDate","Title","Type","Billable","Work PhoneNo","Mobile Phone");
    var dataArray = response;
    //generateHeader(headerArray,tableId);
    ParseAndGenerateHTMLTeam(tableId,dataArray, headerFields);
    }

function ParseAndGenerateHTMLTeam(oTable,responseString,headerFields) {
    // alert("ParseAndGenerateHTML");
    var start = new Date();
    var fieldDelimiter = "|";
    var recordDelimiter = "^";   
    //alert('responseString%%%% ******'+responseString);
    //alert('rowCount%%%% ******'+rowCount);
    var records = responseString.split(recordDelimiter); 
    generateTableTeam(oTable,headerFields,records,fieldDelimiter);
}


function generateTableTeam(oTable, headerFields,records,fieldDelimiter) {	
    //alert("oTable.id-->"+oTable.id);
   // alert("generateTable");
    var tbody = oTable.childNodes[0];    
    tbody = document.createElement("TBODY");
    oTable.appendChild(tbody);
    generateTableHeader(tbody,headerFields);
    var rowlength;
        rowlength = records.length-1;

    if(rowlength >=1 && records!=""){
        //alert("rowlength-->^"+records);
        for(var i=0;i<rowlength;i++) {
          
            if(oTable.id == "tblUpdateForAccountsListByPriority")
            {
                generateProjectTeamMembers(oTable,tbody,records[i],fieldDelimiter); 

            }
            else{
                generateRow(oTable,tbody,records[i],fieldDelimiter);  
            }
        }    
    } else {
        generateNoRecords(tbody,oTable);
    }
    generateFooter(tbody,oTable);
}
function generateProjectTeamMembers(oTable,tableBody,record,delimiter){
  // alert(record);
    var row;
    var cell;
    var fieldLength;
    var fields = record.split(delimiter);
    fieldLength = fields.length ;
    var length = fieldLength;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    //Assigning values start
    count =fields[0];
    objectId=fields[1];
    EmpType=fields[2];
    ResourceName = fields[3];
    country = fields[4];
  //  alert('country-->'+country);
    email = fields[5];
    ResTitle = fields[6];
    Type = fields[7];
    Billable = fields[8];
    for (var i=0;i<length;i++) {
       
        if(i<=9) { 
        cell = document.createElement( "TD" );
    //    cell.className="gridColumn";
        if(i==0)
        cell.innerHTML = fields[0];
         if(i==1) {
        cell.innerHTML = fields[3];
        }
       else if(i==2)
           {
            cell.innerHTML = fields[4];   
           }
           
         else if(i==3)
           {
            cell.innerHTML = fields[5];   
           } 
           
        else if(i==4)
           {
             cell.innerHTML = fields[2];  
           }    
        else if(i==5)
           {
          //cell.innerHTML = fields[5];   
          if(fields[6]=="1")
              {
               cell.innerHTML = "Team Member";   
              }
          else if(fields[6]=="2")
              {
              cell.innerHTML = "Team Lead";    
              }
          else if(fields[6]=="3")
              {
               cell.innerHTML = "PM Offshore";   
              }
          else if(fields[6]=="4")
              {
               cell.innerHTML = "PM Onsite";   
              }
          else if(fields[6]=="5")
              {
               cell.innerHTML = "PM Customer";   
              }
          else if(fields[6]=="6")
              {
               cell.innerHTML = "Sponsor";   
              }
          else if(fields[6]=="7")
              {
              cell.innerHTML = "Delivery Manager";    
              }    
           }
            else if(i==6)
           {
             
               if(fields[10] == "" || fields[10]==null || fields[10] == 'null'){
                    
               cell.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-";   
                 
               }else{
               
            cell.innerHTML = fields[10];   
               }
           }
           
            else if(i==7)
           {
                if(fields[7] == '1')
                {
                cell.innerHTML = "Yes";       
                }
                else
                {
                cell.innerHTML = "No";      
                } 
            //cell.innerHTML = fields[6];   
           }
    
           else if(i==8)
           {
               if(fields[8] == "" || fields[8]==null || fields[8] == 'null'){
                   cell.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-";   
               }else{
            cell.innerHTML = fields[8];   
               }
           }
          else if(i==9)
           {
             
               if(fields[9] == "" || fields[9]==null || fields[9] == 'null'){
                    
               cell.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-";   
                 
               }else{
               
            cell.innerHTML = fields[9];   
               }
           }
          
        }
     row.appendChild( cell );
       // }
    }
    
               
                    
        //Feed back Mail End
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

function generateFooter(tbody,oTable) {
    //alert(oTable.id);
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    cell.id="footer"+oTable.id;
    //cell.colSpan = "5";
    
    if(oTable.id == "tblUpdateForAccountsListByPriority")
    {
        cell.colSpan = "9";   
    }
    footer.appendChild(cell);
}
