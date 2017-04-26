// This function will get the datetime value with +2 days
function doTime() { 
var d = new Date(); 
var y = d.getFullYear(); 
var h = d.getHours(); 
var m = d.getMinutes(); 
var s = d.getSeconds(); 
var mo = d.getMonth() + 1; 
 if(mo <10) {
 mo = "0" +mo;  
 }
var da = d.getDate()+2; 
if(da <10) {
 da = "0" +da; 
 }

if(h <10) {
 h = "0" +h;  
 }
 if(m <10) {
 m = "0" +m;  
 }
 if(s <10) {
 s = "0" +s;  
 }
var f = mo + '/' + da + '/' + y; 
var time =  f +  " " + h + ':' + m +  ':' + s ;

if(document.leaveForm.action.value == "addLeave")
{
document.leaveForm.leaveRequiredFrom.value = time; 
document.leaveForm.leaveRequiredTo.value = time; 
}
} 