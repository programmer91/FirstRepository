// This is a javascript file

var CONTENXT_PATH="/Hubble";

function moneyFormat(number){
   // alert(number);
    var value=parseFloat(number);
    var money=value.toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
    
    
    
    return money;
}