/*Method To Display the Error Messages*/

       onerror = handleErr;
           
           function handleErr(msg,url,l) {
              txt = "Error :" +msg;
              txt+="\n Line :"+l;
              alert(txt);
           }
               var req;


     /* Method to get the bridge number */

            function getnumber() {
                
                var Starttime = document.getElementById("starttime").value;
                var Endtime = document.getElementById("endtime").value;
                
                var url =CONTENXT_PATH+"/AjaxHandlerServlet?from=bridgenumber&start="+Starttime+"&end="+Endtime;
                
                if(window.XMLHttpRequest) {
                    req=new XMLHttpRequest();
                }
                else if (window.ActiveXObject){
                     req = new ActiveXObject("Microsoft.XMLHTTP");
                }
               
                req.onreadystatechange=callback;
                
                req.open("GET",url,true);
                req.send(null);
             }

           
            function callback() {
               if (req.readyState == 4) {
                    if (req.status == 200) { 
                         message = req.responseXML;
                        
                         populateBridgeNumbers(message);
                      }
                 }
             }
        

        /* Method to get the elements from the xml response */

        function populateBridgeNumbers(resXML) {
              var BridgeNumberslist = document.getElementById("bridgeNumber");
               
              var BridgeNumbers = resXML.getElementsByTagName("BRIDGENUMBERS")[0];
               
              var Bridgenumber = BridgeNumbers.getElementsByTagName("BRIDGENUMBER");
              
              BridgeNumberslist.innerHTML="";
              for (var i=0; i<Bridgenumber.length; i++) {
                   var bridgeNumber = Bridgenumber[i]; 
                   var number = bridgeNumber.firstChild.nodeValue;
                   var opt = document.createElement("option");
                   opt.setAttribute("value",number);
                   opt.appendChild(document.createTextNode(number));
                   BridgeNumberslist.appendChild(opt);
      
              }
         }

