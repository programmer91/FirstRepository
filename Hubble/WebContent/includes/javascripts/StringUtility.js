//Script for smallcaps

function changeCase(frmObj) {
    var index;
    var tmpStr;
    var tmpChar;
    var preString;
    var postString;
    var strlen;
    tmpStr = frmObj.value.toLowerCase();
    strLen = tmpStr.length;
    if (strLen > 0)  {
        for (index = 0; index < strLen; index++)  {
            if (index == 0)  {
                tmpChar = tmpStr.substring(0,1).toUpperCase();
                postString = tmpStr.substring(1,strLen);
                tmpStr = tmpChar + postString;
            }
            else {
                tmpChar = tmpStr.substring(index, index+1);
                if (tmpChar == " " && index < (strLen-1))  {
                    tmpChar = tmpStr.substring(index+1, index+2).toUpperCase();
                    preString = tmpStr.substring(0, index+1);
                    postString = tmpStr.substring(index+2,strLen);
                    tmpStr = preString + tmpChar + postString;
                    
                }
            }
        }
    }
    frmObj.value = tmpStr;
    /*alert(tmpStr);*/
    return tmpStr;
}

function change(frmObj) {
    changeCase(frmObj);
}


//script for allSmalls

function allSmalls( name ) {
    
    
    var lower = name.value.toLowerCase(); 
    name.value=lower;
    /*alert(name.value=lower);*/
    
    return lower;
    
}

function change( name ) {
    allSmalls(name);
}


//script for allCaps

function allCaps( name ) {
    
    
    var lower = name.value.toUpperCase(); 
    name.value=lower;
    /*alert(name.value=lower);*/
    
    return lower;
    
}


function handleRequest() {
    
    if(xmlHttp.readyState == 4){
        
        /* Retrieving Fields data from DBserver */
        var FirstName=xmlHttp.responseXML.getElementsByTagName("value1")[0].firstChild.data;
        
        
        /* Popup Fields with Data */
        document.contactForm.firstName.value=firstName; 
    }
}

