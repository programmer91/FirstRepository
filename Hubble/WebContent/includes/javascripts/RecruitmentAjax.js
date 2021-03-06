function newXMLHttpRequest(){var xmlreq=false;
if(window.XMLHttpRequest){xmlreq=new XMLHttpRequest();
}else{if(window.ActiveXObject){try{xmlreq=new ActiveXObject("MSxm12.XMLHTTP");
}catch(e1){try{xmlreq=new ActiveXObject("Microsoft.XMLHTTP");
}catch(e2){xmlreq=false;
}}}}return xmlreq;
}function readyStateHandler(req,responseXmlHandler,typeAdd){return function(){if(req.readyState==4){if(req.status==200){if(typeAdd=="home"){responseXmlHandler(req.responseXML);
}if(typeAdd=="curProj"){responseXmlHandler(req.responseXML);
}if(typeAdd=="offShor"){responseXmlHandler(req.responseXML);
}if(typeAdd=="other"){responseXmlHandler(req.responseXML);
}}else{alert("HTTP error"+req.status+" : "+req.statusText);
}}};
}function getStateData(ds){var req=newXMLHttpRequest();
var typeAdd="home";
req.onreadystatechange=readyStateHandler(req,populateStates,typeAdd);
var url=CONTENXT_PATH+"/getStateData.action?country="+ds.value;
req.open("GET",url,"true");
req.send(null);
}function getCurrentProjStates(ds){var req=newXMLHttpRequest();
var typeAdd="curProj";
req.onreadystatechange=readyStateHandler(req,populateCurrStates,typeAdd);
var url=CONTENXT_PATH+"/getStateData.action?country="+ds.value;
req.open("GET",url,"true");
req.send(null);
}function populateCurrStates(resXML){var stateId=document.getElementById("curState");
var country=resXML.getElementsByTagName("COUNTRY")[0];
var states=country.getElementsByTagName("STATE");
stateId.innerHTML="";
for(var i=0;
i<states.length;
i++){var stateName=states[i];
var name=stateName.firstChild.nodeValue;
var opt=document.createElement("option");
opt.setAttribute("value",name);
opt.appendChild(document.createTextNode(name));
stateId.appendChild(opt);
}}function getOffShoreStates(ds){var req=newXMLHttpRequest();
var typeAdd="offShor";
req.onreadystatechange=readyStateHandler(req,populateOffShore,typeAdd);
var url=CONTENXT_PATH+"/getStateData.action?country="+ds.value;
req.open("GET",url,"true");
req.send(null);
}function populateOffShore(resXML){var stateId=document.getElementById("offState");
var country=resXML.getElementsByTagName("COUNTRY")[0];
var states=country.getElementsByTagName("STATE");
stateId.innerHTML="";
for(var i=0;
i<states.length;
i++){var stateName=states[i];
var name=stateName.firstChild.nodeValue;
var opt=document.createElement("option");
opt.setAttribute("value",name);
opt.appendChild(document.createTextNode(name));
stateId.appendChild(opt);
}}function getOthAddStates(ds){var req=newXMLHttpRequest();
var typeAdd="other";
req.onreadystatechange=readyStateHandler(req,populateOtherAdd,typeAdd);
var url=CONTENXT_PATH+"/getStateData.action?country="+ds.value;
req.open("GET",url,"true");
req.send(null);
}function populateOtherAdd(resXML){var stateId=document.getElementById("othState");
var country=resXML.getElementsByTagName("COUNTRY")[0];
var states=country.getElementsByTagName("STATE");
stateId.innerHTML="";
for(var i=0;
i<states.length;
i++){var stateName=states[i];
var name=stateName.firstChild.nodeValue;
var opt=document.createElement("option");
opt.setAttribute("value",name);
opt.appendChild(document.createTextNode(name));
stateId.appendChild(opt);
}}function populateStates(resXML){var stateId=document.getElementById("state");
var country=resXML.getElementsByTagName("COUNTRY")[0];
var states=country.getElementsByTagName("STATE");
stateId.innerHTML="";
for(var i=0;
i<states.length;
i++){var stateName=states[i];
var name=stateName.firstChild.nodeValue;
var opt=document.createElement("option");
opt.setAttribute("value",name);
opt.appendChild(document.createTextNode(name));
stateId.appendChild(opt);
}}function getResStateData(){var resCountry=document.getElementById("resCountry").value;
var req=newXMLHttpRequest();
req.onreadystatechange=readyStateHandler(req,populateResStates);
var url=CONTENXT_PATH+"/AjaxHandlerServlet?from=address&country="+resCountry;
req.open("GET",url,"true");
req.send(null);
}function populateResStates(resXML){var stateId=document.getElementById("resState");
var country=resXML.getElementsByTagName("COUNTRY")[0];
var states=country.getElementsByTagName("STATE");
stateId.innerHTML="";
for(var i=0;
i<states.length;
i++){var stateName=states[i];
var name=stateName.firstChild.nodeValue;
var opt=document.createElement("option");
opt.setAttribute("value",name);
opt.appendChild(document.createTextNode(name));
stateId.appendChild(opt);
}}