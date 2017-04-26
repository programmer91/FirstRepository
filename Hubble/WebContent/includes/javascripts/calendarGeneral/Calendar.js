/********************************************
 Author : Hari Krishna Kondala
 Email  : hkondala@miraclesoft.com
*********************************************/


var months = new Array("January","February","March","April","May","June","July","August","September","October","November","December");
var weekDay = new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday");
//var monthEnd = new Array(31,28,31,30,31,30,31,31,30,31,30,31);

function getDateInfo() {
    var y = document.eventForm.year.value;
    var m = document.eventForm.month.options[document.eventForm.month.options.selectedIndex].value;
    var d = 1;//document.eventForm.day.options[document.eventForm.day.options.selectedIndex].value;
    //var hlpr = monthEnd[m];
    //if (d < monthEnd[m] + 1) {
    //if (m == 1 && y % 4 == 0) { 
    //hlpr++;
    //}
    var month = months[m];
    var i =1;
    var text;
    while(i<=42) {
        text = "text"+i;
        //alert(text);
        document.eventForm[text].style.backgroundColor="white";
        i++;
    }
    if(y <= 99999) {
        var c = new Date(y,m,d);
        var dayOfWeek = c.getDay();
        var myday = weekDay[dayOfWeek];
        
        if(myday == 'Sunday') {
            for(i=1;i<=28;i++) {
                text = "text"+i;
                document.eventForm[text].value=i;
            }
            if(month != 'February') {
                document.eventForm.text29.value='29';
                document.eventForm.text30.value='30';
            }else {
                if(y % 4 == 0) {
                    document.eventForm.text29.value='29';
                }else {
                    document.eventForm.text29.value='';
                }
                document.eventForm.text30.value='';
            }
            if(month == 'January' || month == 'March' || month == 'May' || month == 'July' || month == 'August' || month == 'October' || month == 'December') {
                document.eventForm.text31.value='31';
            }else {
                document.eventForm.text31.value='';
            }
            document.eventForm.text32.value='';
            document.eventForm.text33.value='';
            document.eventForm.text34.value='';
            document.eventForm.text35.value='';
            document.eventForm.text36.value='';
            document.eventForm.text37.value='';
            
            tempId = document.getElementById("td1");
            tempId  = tempId.firstChild;
            //alert("In sunday--TempId--"+tempId)
            getData(tempId);
            
        }
        
        if(myday == 'Monday') {
            document.eventForm.text1.value='';
            for(i=2;i<=29;i++) {
                text = "text"+i;
                j = i-1;
                document.eventForm[text].value=j;
            }
            if(month != 'February') {
                document.eventForm.text30.value='29';
                document.eventForm.text31.value='30';
            }else {
                if(y % 4 == 0) {
                    document.eventForm.text30.value='29';
                }else {
                    document.eventForm.text30.value='';
                }
                document.eventForm.text31.value='';
            }
            if(month == 'January' || month == 'March' || month == 'May' || month == 'July' || month == 'August' || month == 'October' || month == 'December') {
                document.eventForm.text32.value='31';
            }else {
                document.eventForm.text32.value='';
            }
            document.eventForm.text33.value='';
            document.eventForm.text34.value='';
            document.eventForm.text35.value='';
            document.eventForm.text36.value='';
            document.eventForm.text37.value='';
            
            tempId = document.getElementById("td2");
            tempId  = tempId.firstChild;
            getData(tempId);
        }
        
        if(myday == 'Tuesday') {
            document.eventForm.text1.value='';
            document.eventForm.text2.value='';
            for(i=3;i<=30;i++) {
                text = "text"+i;
                j = i-2;
                document.eventForm[text].value=j;
            }
            if(month != 'February') {
                document.eventForm.text31.value='29';
                document.eventForm.text32.value='30';
            }else {
                if(y % 4 == 0) {
                    document.eventForm.text31.value='29';
                }else {
                    document.eventForm.text31.value='';
                }
                document.eventForm.text32.value='';
            }
            if(month == 'January' || month == 'March' || month == 'May' || month == 'July' || month == 'August' || month == 'October' || month == 'December') {
                document.eventForm.text33.value='31';
            }else {
                document.eventForm.text33.value='';
            }
            document.eventForm.text34.value='';
            document.eventForm.text35.value='';
            document.eventForm.text36.value='';
            document.eventForm.text37.value='';
            
            tempId = document.getElementById("td3");
            tempId  = tempId.firstChild;
            getData(tempId);
            
        }
        
        if(myday == 'Wednesday') {
            document.eventForm.text1.value='';
            document.eventForm.text2.value='';
            document.eventForm.text3.value='';
            for(i=4;i<=31;i++) {
                text = "text"+i;
                j = i-3;
                document.eventForm[text].value=j;
            }
            if(month != 'February') {
                document.eventForm.text32.value='29';
                document.eventForm.text33.value='30';
            }else {
                if(y % 4 == 0) {
                    document.eventForm.text32.value='29';
                }else {
                    document.eventForm.text32.value='';
                }
                document.eventForm.text33.value='';
            }
            if(month == 'January' || month == 'March' || month == 'May' || month == 'July' || month == 'August' || month == 'October' || month == 'December') {
                document.eventForm.text34.value='31';
            }else {
                document.eventForm.text34.value='';
            }
            document.eventForm.text35.value='';
            document.eventForm.text36.value='';
            document.eventForm.text37.value='';
            
            tempId = document.getElementById("td4");
            tempId  = tempId.firstChild;
            getData(tempId);
        }
        
        if(myday == 'Thursday') {
            document.eventForm.text1.value='';
            document.eventForm.text2.value='';
            document.eventForm.text3.value='';
            document.eventForm.text4.value='';
            for(i=5;i<=32;i++) {
                text = "text"+i;
                j = i-4;
                document.eventForm[text].value=j;
            }
            if(month != 'February') {
                document.eventForm.text33.value='29';
                document.eventForm.text34.value='30';
            }else {
                if(y % 4 == 0) {
                    document.eventForm.text33.value='29';
                }else {
                    document.eventForm.text33.value='';
                }
                document.eventForm.text34.value='';
            }
            if(month == 'January' || month == 'March' || month == 'May' || month == 'July' || month == 'August' || month == 'October' || month == 'December') {
                document.eventForm.text35.value='31';
            }else {
                document.eventForm.text35.value='';
            }
            document.eventForm.text36.value='';
            document.eventForm.text37.value='';
            
            tempId = document.getElementById("td5");
            tempId  = tempId.firstChild;
            getData(tempId);
            
        }
        
        if(myday == 'Friday') {
            document.eventForm.text1.value='';
            document.eventForm.text2.value='';
            document.eventForm.text3.value='';
            document.eventForm.text4.value='';
            document.eventForm.text5.value='';
            for(i=6;i<=33;i++) {
                text = "text"+i;
                j = i-5;
                document.eventForm[text].value=j;
            }
            if(month != 'February') {
                document.eventForm.text34.value='29';
                document.eventForm.text35.value='30';
            }else {
                if(y % 4 == 0) {
                    document.eventForm.text34.value='29';
                }else {
                    document.eventForm.text34.value='';
                }
                document.eventForm.text35.value='';
            }
            if(month == 'January' || month == 'March' || month == 'May' || month == 'July' || month == 'August' || month == 'October' || month == 'December') {
                document.eventForm.text36.value='31';
            }else {
                document.eventForm.text36.value='';
            }
            document.eventForm.text37.value='';
            
            tempId = document.getElementById("td6");
            tempId  = tempId.firstChild;
            getData(tempId);
            
        }
        
        if(myday == 'Saturday') {
            document.eventForm.text1.value='';
            document.eventForm.text2.value='';
            document.eventForm.text3.value='';
            document.eventForm.text4.value='';
            document.eventForm.text5.value='';
            document.eventForm.text6.value='';
            for(i=7;i<=34;i++) {
                text = "text"+i;
                j = i-6;
                document.eventForm[text].value=j;
            }
            if(month != 'February') {
                document.eventForm.text35.value='29';
                document.eventForm.text36.value='30';
            }else {
                if(y % 4 == 0) {
                    document.eventForm.text35.value='29';
                }else {
                    document.eventForm.text35.value='';
                }
                document.eventForm.text36.value='';
            }
            if(month == 'January' || month == 'March' || month == 'May' || month == 'July' || month == 'August' || month == 'October' || month == 'December') {
                document.eventForm.text37.value='31';
            }else {
                document.eventForm.text37.value='';
            }
            
            tempId = document.getElementById("td7");
            tempId  = tempId.firstChild;
            getData(tempId);
            
        }
    }
    else {
        alert("Year is invalid.\nCheck it again.");
    }
}


function setMonthYear() {
    var y = new Date().getYear();
    if (y < 2000) y += 1900;
    //alert("year is ---"+y);
    document.eventForm.year.value = y;
    //alert("year value is ---"+document.eventForm.year.value);
    var m = new Date().getMonth();
    document.eventForm.month.value = m;
    //alert("month is ---"+document.eventForm.month.value)
}





