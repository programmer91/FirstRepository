//====================================================================================
//	This Function splits the numbers with commas (example -- $82,000.00)
//====================================================================================

function formattedNumber(amount,symbol) {
    var symbol2 = symbol;
    
    var Num = amount;
    var newNum = "";
    var newNum2 = "";
    var count = 0;
    
    //check for decimal number
    if (Num.indexOf('.') != -1){  //number ends with a decimal point
        if (Num.indexOf('.') == Num.length-1){
            Num += "00";
        }
        if (Num.indexOf('.') == Num.length-2){ //number ends with a single digit
            Num += "0";
        }
        
        var a = Num.split("."); 
        Num = a[0];   //the part we will commify
        var end = a[1] //the decimal place we will ignore and add back later
    }
    else {var end = "00";}  
 
    //this loop actually adds the commas   
    for (var k = Num.length-1; k >= 0; k--){
      var oneChar = Num.charAt(k);
      if (count == 3){
        newNum += ",";
        newNum += oneChar;
        count = 1;
        continue;
      }
      else {
        newNum += oneChar;
        count ++;
      }
   }  //but now the string is reversed!
   
  //re-reverse the string
  for (var k = newNum.length-1; k >= 0; k--){
      var oneChar = newNum.charAt(k);
      newNum2 += oneChar;
  }
   
   // add dollar sign and decimal ending from above
   newNum2 = symbol2 + newNum2 + "." + end;
  //alert('formatted is '+newNum2)
   return newNum2;
}

//================================================================================================
//	This Function round off numbers to TWO Decimal Places. (example -- 23244.4325 to 23244.43)
//================================================================================================

var numOfDec;

function formatNumber(myNum)
{
numOfDec = 2;
var decimal = 1
for(i=1; i<=numOfDec;i++)
decimal = decimal *10

var myFormattedNum = (Math.round(myNum * decimal)/decimal).toFixed(numOfDec)
return(myFormattedNum);
} 

