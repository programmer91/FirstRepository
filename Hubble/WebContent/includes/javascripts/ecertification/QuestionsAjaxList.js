
function readyStateHandlerreq(req,responseTextHandler) {

        //alert("ready");
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadingMessage12")).style.display = "none";
                responseTextHandler(req.responseText);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
          
            (document.getElementById("loadingMessage12")).style.display = "block";
        }
    }
}

var tempSubtopicId = "";
var spnFast;
var end ;
var start;
function getQuestionsList(subTopicId) {

  start = new Date();
spnFast=document.getElementById("spnFast");
    tempSubtopicId = subTopicId;
    AjaxRoleId = document.getElementById("roleTypeId").value;
   // alert("role Id in getREqList:"+AjaxRoleId);
    var req = newXMLHttpRequest();
     req.onreadystatechange = readyStateHandlerreq(req, displayQuestionListResult); 
    
      //alert("path ==="+CONTENXT_PATH);

    var url = CONTENXT_PATH+"/questionsAjaxList.action?subTopicId="+subTopicId;
 
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}




function displayQuestionListResult(resText) {
end = new Date();
spnFast.innerHTML = "This Search took " + (end.getTime()-start.getTime()) + " milliseconds.";

    if(resText.length !=0 && resText!="addto0"){
        var oTable = document.getElementById("tblUpdate1");
        
        clearTable(oTable);
       
            
                var headerFields = new Array("SNo","Edit","Question","Option1","Option2","Option3","Option4","Answer");	
       
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
       
        var resTextSplit1 = resText.split("^");

         generateTableHeader(tbody,headerFields);
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("|");
           
                generateRow(tbody,resTextSplit2,index);
          
        }
        generateFooter(tbody);
    }else {
        alert("No Records Found");
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

        cell.setAttribute("width","10000px");
        cell.innerHTML = headerFields[i];
    }
}

function generateFooter(tbody) {
   // alert(tbody);
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
//    cell.id="footer"+oTable.id;

      
        cell.colSpan = "12";
     

    footer.appendChild(cell);
}

function clearTable(tableId) {
    var tbl =  tableId;
    var lastRow = tbl.rows.length; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}


function generateRow(tableBody,rowFeildsSplit,index) {
    var row;
    var cell;
    row = document.createElement("TR");
    row.className="gridRowEven";
    cell = document.createElement("TD");
    cell.className="gridRowEven";
    cell.innerHTML = index+1;
    cell.setAttribute('align','center');
    row.appendChild(cell);
    tableBody.appendChild(row);

    for (var i=1; i<rowFeildsSplit.length; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild(cell);

        if(i==1) {
            cell.innerHTML = " ";
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:getQuestionData('"+rowFeildsSplit[1]+"','"+tempSubtopicId+"')");
            j.appendChild(document.createTextNode("Edit"));
            cell.appendChild(j);
            cell.align="center";
         }
         else if(i==2){ 
            // job details
             var question = rowFeildsSplit[2].substring(0,10);
            var j = document.createElement("a");
            j.setAttribute("href", "#");
            j.setAttribute("onmouseover","javascript:tooltip.show('"+rowFeildsSplit[2]+"')");
            j.setAttribute("onmouseout","javascript:tooltip.hide();");
            j.appendChild(document.createTextNode(question+"..."));      
            cell.appendChild(j);

           
        }
          else if(i==3){ 
            // job details
             var option1 = rowFeildsSplit[3].substring(0,10);
            var j = document.createElement("a");
            j.setAttribute("href", "#");
            j.setAttribute("onmouseover","javascript:tooltip.show('"+rowFeildsSplit[3]+"')");
            j.setAttribute("onmouseout","javascript:tooltip.hide();");
            j.appendChild(document.createTextNode(option1+"..."));      
            cell.appendChild(j);

           
        }
          else if(i==4){ 
            // job details
             var option2 = rowFeildsSplit[4].substring(0,10);
            var j = document.createElement("a");
            j.setAttribute("href", "#");
            j.setAttribute("onmouseover","javascript:tooltip.show('"+rowFeildsSplit[4]+"')");
            j.setAttribute("onmouseout","javascript:tooltip.hide();");
            j.appendChild(document.createTextNode(option2+"..."));      
            cell.appendChild(j);
           
        }
          else if(i==5){ 
            // job details
             var option3 = rowFeildsSplit[5].substring(0,10);
            var j = document.createElement("a");
            j.setAttribute("href", "#");
            j.setAttribute("onmouseover","javascript:tooltip.show('"+rowFeildsSplit[5]+"')");
            j.setAttribute("onmouseout","javascript:tooltip.hide();");
            j.appendChild(document.createTextNode(option3+"..."));      
            cell.appendChild(j);
           
        }
else if(i==6){ 
            // job details
             var option4 = rowFeildsSplit[6].substring(0,10);
            var j = document.createElement("a");
            j.setAttribute("href", "#");
            j.setAttribute("onmouseover","javascript:tooltip.show('"+rowFeildsSplit[6]+"')");
            j.setAttribute("onmouseout","javascript:tooltip.hide();");
            j.appendChild(document.createTextNode(option4+"..."));      
            cell.appendChild(j);
           
        }else if(i==7){
            
            cell.setAttribute('align','center');
            cell.innerHTML = rowFeildsSplit[7];
            
        }
          
        cell.width = 120;
    }
}


 function getQuestionData(questionId,subTopicId) {
    //alert('hi');
    document.location="/Hubble/ecertification/populateQuestionDetails.action?questionId="+questionId+"&subTopicId="+subTopicId;
}


//tooltip for job title in requirement list

var tooltip=function(){
	var id = 'tt';
	var top = 3;
	var left = 3;
	var maxw = 300;
	var speed = 10;
	var timer = 20;
	var endalpha = 95;
	var alpha = 0;
	var tt,t,c,b,h;
	var ie = document.all ? true : false;
	return{
		show:function(v,w){
			if(tt == null){
				tt = document.createElement('div');
				tt.setAttribute('id',id);
				t = document.createElement('div');
				t.setAttribute('id',id + 'top');
				c = document.createElement('div');
				c.setAttribute('id',id + 'cont');
				b = document.createElement('div');
				b.setAttribute('id',id + 'bot');
				tt.appendChild(t);
				tt.appendChild(c);
				tt.appendChild(b);
				document.body.appendChild(tt);
				tt.style.opacity = 0;
				tt.style.filter = 'alpha(opacity=0)';
				document.onmousemove = this.pos;
			}
			tt.style.display = 'block';
			c.innerHTML = v;
			tt.style.width = w ? w + 'px' : 'auto';
			if(!w && ie){
				t.style.display = 'none';
				b.style.display = 'none';
				tt.style.width = tt.offsetWidth;
				t.style.display = 'block';
				b.style.display = 'block';
			}
			if(tt.offsetWidth > maxw){tt.style.width = maxw + 'px'}
			h = parseInt(tt.offsetHeight) + top;
			clearInterval(tt.timer);
			tt.timer = setInterval(function(){tooltip.fade(1)},timer);
		},
		pos:function(e){
			var u = ie ? event.clientY + document.documentElement.scrollTop : e.pageY;
			var l = ie ? event.clientX + document.documentElement.scrollLeft : e.pageX;
			tt.style.top = (u - h) + 'px';
			tt.style.left = (l + left) + 'px';
		},
		fade:function(d){
			var a = alpha;
			if((a != endalpha && d == 1) || (a != 0 && d == -1)){
				var i = speed;
				if(endalpha - a < speed && d == 1){
					i = endalpha - a;
				}else if(alpha < speed && d == -1){
					i = a;
				}
				alpha = a + (i * d);
				tt.style.opacity = alpha * .01;
				tt.style.filter = 'alpha(opacity=' + alpha + ')';
			}else{
				clearInterval(tt.timer);
				if(d == -1){tt.style.display = 'none'}
			}
		},
		hide:function(){
			clearInterval(tt.timer);
			tt.timer = setInterval(function(){tooltip.fade(-1)},timer);
		}
	};
}();







