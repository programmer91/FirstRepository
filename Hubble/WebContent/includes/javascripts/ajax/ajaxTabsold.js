/*******************START ***************************/

var xmlHttp

function GetXmlHttpObject()
{
var objXMLHttp=null
if (window.XMLHttpRequest)
{
objXMLHttp=new XMLHttpRequest()
}
else if (window.ActiveXObject)
{
objXMLHttp=new ActiveXObject("Microsoft.XMLHTTP")
}
return objXMLHttp
} 

/*********************** AccountActivity ******************************/

function dotest(accid)
{
//alert("hi");
var url =CONTENXT_PATH+"/crm/accounts/AccountActivities.jsp?currentAccountId="+accid;
//alert("url:"+url);
xmlHttp=GetXmlHttpObject()
if (xmlHttp==null)
{
alert ("Browser does not support HTTP Request")
return
}
xmlHttp.onreadystatechange=getData;
xmlHttp.open("GET",url,true)
xmlHttp.send(null)
return
}

function getData()
{
if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
{
//alert("inner1");
document.getElementById("AccountActivityList").innerHTML=xmlHttp.responseText
}
}



/*************  opp **********************************/
function dotestOpp(accid)
{
//alert("hi1");
var url =CONTENXT_PATH+"/crm/accounts/AccountopportunitiesList.jsp?currentAccountId="+accid;
//alert("url:"+url);
xmlHttp=GetXmlHttpObject()
if (xmlHttp==null)
{
alert ("Browser does not support HTTP Request")
return
}
xmlHttp.onreadystatechange=getDataopp;
xmlHttp.open("GET",url,true)
xmlHttp.send(null)
return
}

function getDataopp()
{
if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
{
document.getElementById("opportunitiesList").innerHTML=xmlHttp.responseText
}
}
/********************* req***************************/
function dotestreq(accid)
{
//alert("hi2");
var url =CONTENXT_PATH+"/crm/accounts/AccountrequirementsList.jsp?currentAccountId="+accid;
//alert("url:"+url);
xmlHttp=GetXmlHttpObject()
if (xmlHttp==null)
{
alert ("Browser does not support HTTP Request")
return
}
xmlHttp.onreadystatechange=getDatareq;
xmlHttp.open("GET",url,true)
xmlHttp.send(null)
return
}

function getDatareq()
{
if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
{
document.getElementById("requirementsList").innerHTML=xmlHttp.responseText
}
}

/*************************projects ****************************/

function dotestproj(accid)
{
//alert("hi2");
var url =CONTENXT_PATH+"/crm/accounts/AccountprojectsList.jsp?currentAccountId="+accid;
//alert("url:"+url);
xmlHttp=GetXmlHttpObject()
if (xmlHttp==null)
{
alert ("Browser does not support HTTP Request")
return
}
xmlHttp.onreadystatechange=getDataproj;
xmlHttp.open("GET",url,true)
xmlHttp.send(null)
return
}

function getDataproj()
{
if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
{
document.getElementById("projectsList").innerHTML=xmlHttp.responseText
}
}

/*****************All Activities*******************/
function dotestallactivity(accid)
{
//alert("hi2");
var url =CONTENXT_PATH+"/crm/accounts/AccountAllAccountActivitiesList.jsp?currentAccountId="+accid;
//alert("url:"+url);
xmlHttp=GetXmlHttpObject()
if (xmlHttp==null)
{
alert ("Browser does not support HTTP Request")
return
}
xmlHttp.onreadystatechange=getDataallact;
xmlHttp.open("GET",url,true)
xmlHttp.send(null)
return
}

function getDataallact()
{
if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
{
document.getElementById("AllAccountActivitiesList").innerHTML=xmlHttp.responseText
}
}

/***************** Attachments************************/
function dotestattach(accid)
{
//alert("hi2");
var url =CONTENXT_PATH+"/crm/accounts/AccountattachmentsList.jsp?currentAccountId="+accid;
//alert("url:"+url);
xmlHttp=GetXmlHttpObject()
if (xmlHttp==null)
{
alert ("Browser does not support HTTP Request")
return
}
xmlHttp.onreadystatechange=getDataattch;
xmlHttp.open("GET",url,true)
xmlHttp.send(null)
return
}

function getDataattch()
{
if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
{
document.getElementById("attachmentsList").innerHTML=xmlHttp.responseText
}
}

/************************** green Sheet list*************************/

function dotestgren(accid)
{
//alert("hi2");
var url =CONTENXT_PATH+"/crm/accounts/AccountgreensheetsList.jsp?currentAccountId="+accid;
//alert("url:"+url);
xmlHttp=GetXmlHttpObject()
if (xmlHttp==null)
{
alert ("Browser does not support HTTP Request")
return
}
xmlHttp.onreadystatechange=getDatagreen;
xmlHttp.open("GET",url,true)
xmlHttp.send(null)
return
}

function getDatagreen()
{
if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
{
document.getElementById("greensheetsList").innerHTML=xmlHttp.responseText
}
}

/*****************END ******************************/









