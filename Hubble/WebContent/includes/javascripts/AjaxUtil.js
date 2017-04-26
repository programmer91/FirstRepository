var emailId;
var myVals = new Array();
var myName = new Array();
var f=0;
var k=0;
var account=0;

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
//====================================================================================
//	This Function generates the HTML <TR> and <TD> for a Set of Fields in a Record
//====================================================================================

function generateRow(tableBody,record,delimiter,headerFields) {
var lang =headerFields.length;
    var row;
    var cell;
    var hyperCell;
    var textCell;
    row = document.createElement( "TR" );
    row.className="gridRowEven";
    tableBody.appendChild( row );
    var j;
    var cellname="lo";
    var cellnames="so";
    var fields = record.split(delimiter);	
    for (var i=0;i<fields.length;i++) {
        
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild( cell );
        if(i==2)
        {
        cell.innerHTML = fields[2];
        }
        
                
        if (i==0){
         cell.innerHTML = fields[0];
        }

        if (i==1){

      if(lang > 3) {  
             generateEmployeeImage(fields[1],fields[0]);
             cell.innerHTML = fields[1];
    /*     k=k+1;
         f=f+1;
         myVals[k]=fields[0];
         myName[f]=fields[1];
         cellname =k;
         cellname =f;
         
         if(fields[1] == "")
           {
            fields[1] ="none";
           }
        hyperCell  = document.createElement("a");
        hyperCell.setAttribute("href","#");
          
        hyperCell.setAttribute("name",cellname);   
        hyperCell.setAttribute("name1",cellname);
        hyperCell.appendChild(document.createTextNode(fields[1]));
       
       // if ((document.all)&&(document.getElementById)){
       hyperCell["onclick"]= new Function("getEmail(this.name,this.name1)");          
        cell.appendChild(hyperCell);  
       //}*/ 
        
     } 
     if(lang <= 3){
    if(fields[1]=="-1"){
            cell.innerHTML = " ";
        }else
         {
            cell.innerHTML = fields[1];
         }
     }
}
        if (i==3)
         {
        cell.innerHTML = fields[3];
        }

        if (i==4)
         {
            
        cell.innerHTML = fields[4];
        }

        if(i==5){
          //  cell.innerHTML = fields[5];
             var x = fields[i];
             var y;
              if(x.length>20){
                var part1 = x.substring(0,20);
                var part2 = x.substring(20);   
                 cell.innerHTML = part1+ "<a href='#' onMouseOver='getPart2(\"" + fields[i] + "\");' onmouseout='closepopUpWindow();'> ..</a>";
                //cell.innerHTML = part1+ "<a href="#" onMouseOver="getPart2(\"" + x + "\")" onMouseOut="closepopUpWindow()"> ..</a>";
                //cell.innerHTML = part1+ "<A href="javascript:void(0)" onMouseover="getPart2(\"" + x + "\")" onMouseout="closepopUpWindow()">";
                // <a HREF='' onmouseover='"+this.mstrFunction+"("+resolveFields(this.mstrPrimaryId)+");' onmouseout='closepopUpWindow();'>
                // <A href="javascript:void(0)" onMouseover="lightup('pic1')" onMouseout="turnoff('pic1')" onMouseDown="clickdown('pic1')" onMouseUp="lightup('pic1')"> 
                // cell.innerHTML = part1+ "<a href='javascript: getPart2(\"" + x + "\");'> ..</a>";
            }else
                cell.innerHTML = x;
            
            }

      

    }
        
    
}
var popup;
function getPart2(data){
  // alert('data is ---='+data);
    
    var background = "#3E93D4";
    var title = "ActivityDescription";
    var text1 = data.substring(0,40);  
    var text2 = data.substring(40,80);  
    var text3 = data.substring(80,120);  
    var text4 = data.substring(120,160);  
    var text5 = data.substring(160,data.length);  
    
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+text1+"<br />"+text2+"<br />"+text3+"<br />"+text4+"<br />"+text5+"<br />\
    </body></html>";
    // "+text4+"<br /
    // alert('content is ---='+content);
    
    //Create the popup       
        popup = window.open("","window","channelmode=0,width=350,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    
    //window.open('jex5.htm','jav','width=300,height=200,resizable=yes'); 
    //document.write("a HREF='' onmouseover='"+populate+"("+resolveFields(data)+");' onmouseout='closepopUpWindow();'");
    
}

function closepopUpWindow(){
    popup.close();
} 

function generateEmployeeImage(empId,name) {
    //alert('entered');
    
    document.getElementById("tex").style.display = 'block';
    var imageTable = document.getElementById("imageTable");
    //alert('after able');
    var imageRow = document.getElementById("imageTR1");
    var fullNameRow = document.getElementById("nameTR1");
    //alert(imageRow.cells.length);
    if(imageRow.cells.length > 4) {
      //  alert('in if');
        var imageRow = document.getElementById("imageTR2");
        var fullNameRow = document.getElementById("nameTR2");
    }
    var imageColumn = document.createElement("td");
    //alert('After column Create'+imageColumn);
    imageRow.appendChild(imageColumn);
    //alert('After colu append');
    
    var hyperCell = document.createElement("a");
    hyperCell.setAttribute("href","#");
    
    var empImage = document.createElement("img");
    //alert('After image --'+empImage);
    empImage.src = CONTENXT_PATH+"/getImage.action?image="+empId;
    empImage.width = 80;
    empImage.height = 65;
//    empImage.id = "image"+numOfEmp;
    //alert('stop here');
    //imageColumn.appendChild(empImage);
    //alert("image append");

    hyperCell.appendChild(empImage);
    imageColumn.appendChild(hyperCell);
    /*var fullName = document.createTextNode(name);
    imageColumn.appendChild(document.createElement("br"));
    imageColumn.appendChild(fullName);*/

    var fullNameColumn = document.createElement("td");
    //fullNameColumn.width = 110;
    //alert('After name column create');
    fullNameRow.appendChild(fullNameColumn);
    //alert('After column append');
    var pos = empId.indexOf("@");
    empId = empId.substr(0,pos);
    var fullName = document.createTextNode(empId);
    //alert('after text node');
    //imageColumn.appendChild(empImage);
    fullNameColumn.appendChild(fullName);
    //alert('done');
}

function getEmail(url,names)
{
var len = url.length;
//var nam = names.length;
var value = myVals[url];

//alert("Login ID:"+value);

//alert("User Name:"+ myName[names]);

var tex = document.getElementById("tex");
tex.style.display = 'block';

var mdiv = document.getElementById("img");
var src1 = CONTENXT_PATH+"/ajaxHandle/getImage?image="+value;
mdiv.src =src1;
//document.getElementById("fullName").style.display = 'block';
//document.getElementById("fullName").innerHTML=myName[url];
}




// getEmail function over

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
        generateRow(tbody,records[i],fieldDelimiter,headerFields);
    }
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    footer.appendChild(cell);
    
    
}

function ParseAndGenerateHTML(oTable,responseString,headerFields) {
    var start = new Date();
    var fieldDelimiter = "|";
    var recordDelimiter = "^";
    
    var records = responseString.split(recordDelimiter);
    //	Generate the HTML Code for the Table Creation using HeaderFields & Records
    generateTable(oTable,headerFields,records,fieldDelimiter);
    
    //document.getElementById("table").innerHTML = strTableHTML;
    var end = new Date();
   spnFast=document.getElementById("spnFast");
  
    spnFast.innerHTML = "This Search took " + (end.getTime()-start.getTime()) + " milliseconds.";
}


//new methods account merge

function getConsultants(url,names)
{
    var original = document.getElementById('original').value;
   // alert(original);
    var duplicate = document.getElementById('duplicate').value;
   // alert(duplicate);
    var req = newXMLHttpRequest();
        req.onreadystatechange = loadActMessageASm(req, populateConsultant);
        var url = CONTENXT_PATH+"/getMergeConsultants.action?original="+original+"&duplicate="+duplicate;
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
        
}

   function populateConsultant(finalData){
    
    alert(finalData);
    window.location = "getConsultantMerge.action";
    
    
   }
   
   ///////////////////////////////////////////////////////////////////////


function getConsultantsAnalyze(url,names)
{
    
    var original = document.getElementById('original').value;
    
    var duplicate = document.getElementById('duplicate').value;
    if(original==""||original=="null"||original==null)
        {
            alert("please valid original email id");
            return false;
        }
       else if(duplicate==""||duplicate=="null"||duplicate==null)
{
    alert("please valid duplicate email id");
            return false;
}
    
    var req = newXMLHttpRequest();
        req.onreadystatechange = loadActMessageASk(req, populateAnalysisConsultant);
       
        var url = CONTENXT_PATH+"/ConsultantAnalysis.action?original="+original+"&duplicate="+duplicate;
     
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
        
}

function populateAnalysisConsultant(totalData){
    
   // alert(totalData);
    document.getElementById("mergeButton").style.display="block";
    
    
    var orgDetails = totalData.split("*")[0];
    //alert("org details-->"+orgDetails);
    if(orgDetails==null || orgDetails=='')
        {
            document.getElementById("mergeButton").style.display="none";
         alert("Orginal emailId not exists in the database");
         
         return false;
        }
        
    var org = orgDetails.split("|");
  
    
     var originalDetails = '<table border=0 class="gridTable" cellpadding="1" cellspacing="1" style="max-width: 230px;"><tr class="gridHeader"><td colspan=2>Original Consulatnt Details</td></tr>';
    
             
             
             
                                originalDetails=  originalDetails+'<tr CLASS="gridRowEven"><td class="title">EmailId:</td><td class="title">'+org[0]+'</td></tr>';
                                  originalDetails = originalDetails+'<tr CLASS="gridRowEven"><td class="title">Name:</td><td class="title">'+org[1]+'</td></tr>';
                                  originalDetails = originalDetails+'<tr CLASS="gridRowEven"><td class="title">Cell:</td><td class="title">'+org[2]+'</td></tr>';
                                 originalDetails = originalDetails+'<tr CLASS="gridRowEven"><td class="title">Skill Set:</td><td class="title">'+org[4]+'</td></tr>';
                                 originalDetails = originalDetails+'<tr CLASS="gridRowEven"><td class="title">Resume:</td><td class="title"><a href="../getAttachment.action?id='+org[5]+'" ><img src="../../includes/images/download_11x10.jpg" )/></a></td></tr>';
                               
                                 originalDetails = originalDetails+'</table>'; 
             
       
         
               document.getElementById("originalDetails").innerHTML=originalDetails;
       
         
var dupDetails = totalData.split("*")[1].split("|");

           if(dupDetails==null || dupDetails=='')
        {
         document.getElementById("mergeButton").style.display="none";   
         alert("Duplicate emailId not exists in the database");
         return false;
        }

        
          var duplicateDetails = '<table border=0 class="gridTable"  cellpadding="1" cellspacing="1" style="max-width: 230px;" ><tr class="gridHeader"><td colspan=2>Duplicate Consulatnt Details</td></tr>';
    
             
             
             
                                duplicateDetails=  duplicateDetails+'<tr CLASS="gridRowEven"><td class="title">EmailId:</td><td class="title" >'+dupDetails[0]+'</td></tr>';
                                  duplicateDetails = duplicateDetails+'<tr CLASS="gridRowEven"><td class="title">Name:</td><td class="title">'+dupDetails[1]+'</td></tr>';
                                  duplicateDetails = duplicateDetails+'<tr CLASS="gridRowEven"><td class="title">Cell:</td><td class="title">'+dupDetails[2]+'</td></tr>';
                                 duplicateDetails = duplicateDetails+'<tr CLASS="gridRowEven"><td class="title">Skill Set:</td><td class="title">'+dupDetails[4]+'</td></tr>';
                                 duplicateDetails = duplicateDetails+'<tr CLASS="gridRowEven"><td class="title">Resume:</td><td class="title"><a href="../getAttachment.action?id='+dupDetails[5]+'" ><img src="../../includes/images/download_11x10.jpg"/></a></td></tr>';
                               
                                 duplicateDetails = duplicateDetails+'</table>'; 
             
        
               document.getElementById("duplicateDetails").innerHTML=duplicateDetails;
  
   }
    

function doDownload(attachmentId){
    window.location = "../getAttachment.action?id="+attachmentId;
}

function loadActMessageASk(req,responseXmlHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
               
                 document.getElementById("loadMsg").style.display = 'none';
                responseXmlHandler(req.responseText);
            } else {
                
                
              alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }else {
            (document.getElementById("loadMsg")).style.display = "block";
        }
    }
}

function loadActMessageASm(req,responseXmlHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
               
                 document.getElementById("loadMessages").style.display = 'none';
                responseXmlHandler(req.responseText);
            } else {
                
                
              alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }else {
            (document.getElementById("loadMessages")).style.display = "block";
        }
    }
}

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
