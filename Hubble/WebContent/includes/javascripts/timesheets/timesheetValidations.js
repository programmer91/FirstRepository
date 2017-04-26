//Author : Hari Krishna Kondala
//Email :  hkondala@miraclesoft.com


function checkDates() {
    
    var w1 = document.getElementById("weekDate1").value;
    var w2 = document.getElementById("weekDate2").value;
    var w3 = document.getElementById("weekDate3").value;
    var w4 = document.getElementById("weekDate4").value;
    var w5 = document.getElementById("weekDate5").value;
    var w6 = document.getElementById("weekDate6").value;
    var w7 = document.getElementById("weekDate7").value;
    
    day2 = w2.substr(3,2);
    day3 = w3.substr(3,2);
    day4 = w4.substr(3,2);
    day5 = w5.substr(3,2);
    day6 = w6.substr(3,2);
    day7 = w7.substr(3,2);
    
    month2 = w2.substr(0,2);
    month3 = w3.substr(0,2);
    month4 = w4.substr(0,2);
    month5 = w5.substr(0,2);
    month6 = w6.substr(0,2);
    month7 = w7.substr(0,2);
    
    year2 = w2.substr(6,10);
    year3 = w3.substr(6,10);
    year4 = w4.substr(6,10);
    year5 = w5.substr(6,10);
    year6 = w6.substr(6,10);
    year7 = w7.substr(6,10);
    
    var prsYear = new Date().getYear();
    if (prsYear < 2000) prsYear += 1900;
    var prsMonth = new Date().getMonth()+1;
    if (prsMonth < 10) {
        prsMonth = '0'+prsMonth;
    }
    var prsDate = new Date().getDate();
    if(year2 > prsYear) {
        disableAllMonday();
    } else if(year2 == prsYear) {
        if(parseInt(month2,10) > parseInt(prsMonth,10)) {
            disableAllMonday();
        }else if(month2 == prsMonth) {
            if(day2 > prsDate) {
                disableAllMonday();
            }
        }
    }
    
    if(year3 > prsYear) {
        disableAllTuesday();
    }else if(year3 == prsYear) {
        if(month3 > prsMonth) {
            disableAllTuesday();
        }else if(month3 == prsMonth) {
            if(day3 > prsDate) {
                disableAllTuesday();
            }
        }
    }
    
    if(year4 > prsYear) {
        disableAllWednesday();
    }else if(year4 == prsYear) {
        if(month4 > prsMonth) {
            disableAllWednesday();
        }else if(month4 == prsMonth) {
            if(day4 > prsDate) {
                disableAllWednesday();
            }
        }
    }
    
    if(year5 > prsYear) {
        disableAllThursday();
    }else if(year5 == prsYear) {
        if(month5 > prsMonth) {
            disableAllThursday()
        }else if(month5 == prsMonth) {
            if(day5 > prsDate) {
                disableAllThursday();
            }
        }
    }
    
    if(year6 > prsYear) {
        disableAllFriday();
    }else if(year6 == prsYear) {
        if(parseInt(month6,10) > parseInt(prsMonth,10)) {
            disableAllFriday();
        }else if(month6 == prsMonth) {
            if(day6 > prsDate) {
                disableAllFriday();
            }
        }
    }
    
    if(year7 > prsYear) {
        disableAllSaturday();
    }else if(year7 == prsYear) {
        if(parseInt(month7,10)> parseInt(prsMonth,10)) {
            disableAllSaturday(); 
        }else if(parseInt(month7,10) == parseInt(prsMonth,10)) {
            if(day7 > prsDate) {
                disableAllSaturday();
            }
        }
    }
}

function disableAllMonday() {
    document.getElementById("proj1Mon").disabled = true;
    document.getElementById("proj2Mon").disabled = true;
    document.getElementById("internalMon").disabled = true;
    document.getElementById("vacationMon").disabled = true;
    document.getElementById("holiMon").disabled = true;
}

function disableAllTuesday() {
    document.getElementById("proj1Tus").disabled = true;
    document.getElementById("proj2Tus").disabled = true;
    document.getElementById("internalTus").disabled = true;
    document.getElementById("vacationTus").disabled = true;
    document.getElementById("holiTus").disabled = true;
}

function disableAllWednesday() {
    document.getElementById("proj1Wed").disabled = true;
    document.getElementById("proj2Wed").disabled = true;
    document.getElementById("internalWed").disabled = true;
    document.getElementById("vacationWed").disabled = true;
    document.getElementById("holiWed").disabled = true;
    
}
function disableAllThursday() {
    document.getElementById("proj1Thur").disabled = true;
    document.getElementById("proj2Thur").disabled = true;
    document.getElementById("internalThur").disabled = true;
    document.getElementById("vacationThur").disabled = true;
    document.getElementById("holiThur").disabled = true;
}
function disableAllFriday() {
    document.getElementById("proj1Fri").disabled = true;
    document.getElementById("proj2Fri").disabled = true;
    document.getElementById("internalFri").disabled = true;
    document.getElementById("vacationFri").disabled = true;
    document.getElementById("holiFri").disabled = true;
}

function disableAllSaturday() {
    document.getElementById("proj1Sat").disabled = true;
    document.getElementById("proj2Sat").disabled = true;
    document.getElementById("internalSat").disabled = true;
    document.getElementById("vacationSat").disabled = true;
    document.getElementById("holiSat").disabled = true;
}