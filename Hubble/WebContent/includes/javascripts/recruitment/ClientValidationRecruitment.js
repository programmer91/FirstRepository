//=====================================
// For Recriutment Add Consultant Form
//====================================


function checkConsultantAddForm() {
    
    var fiName = document.getElementById("fiName").value;
    var laName = document.getElementById("laName").value;
    var gender = document.getElementById("gender").value;
    var email1 = document.getElementById("email1").value;
    var email2 = document.getElementById("email2").value;
    var email3 = document.getElementById("email3").value;
    
    var cellPhoneNo = document.getElementById("cellPhoneNo").value;
    var hotelPhoneNo = document.getElementById("hotelPhoneNo").value;
    var indiaPhoneNo = document.getElementById("indiaPhoneNo").value;
    var faxPhoneNo = document.getElementById("faxPhoneNo").value;
    var alterPhoneNo = document.getElementById("alterPhoneNo").value;
    var homePhoneNo = document.getElementById("homePhoneNo").value;
    var workPhoneNo = document.getElementById("workPhoneNo").value;
    
    if (fiName == "") {
        hideAllRecruitErrors();
        document.getElementById("fiNameError").style.display = "inline";
        document.getElementById("fiName").select();
        //document.getElementById("fiName").focus();
        return false;
    } else if (laName == "") {
        hideAllRecruitErrors();
        document.getElementById("laNameError").style.display = "inline";
        //document.getElementById("laName").select();
        //document.getElementById("fiName").focus();
        return false;
    } else if (gender == "1") {
        hideAllRecruitErrors();
        document.getElementById("genderError").style.display = "inline";
        //document.getElementById("gender").select();
        //document.getElementById("genderName").focus();
        return false;
    } else if (email1 == "" && email2 == "" && email3 == "") {
        hideAllRecruitErrors();
        document.getElementById("emailError").style.display = "inline";
        //document.getElementById("email1").select();
        //document.getElementById("email1").focus();
        return false;
    } if (cellPhoneNo == "" && hotelPhoneNo == "" && indiaPhoneNo == "" && faxPhoneNo == "" && alterPhoneNo == "" && homePhoneNo == "" && workPhoneNo== "") {
        hideAllRecruitErrors();
        document.getElementById("phoneNoError").style.display = "inline";
        //document.getElementById("cellPhoneNO").select();
        //document.getElementById("cellPhoneNo").focus();
        return false;
    }
    return true;
};

function hideAllRecruitErrors() {
    document.getElementById("fiNameError").style.display = "none";
    document.getElementById("laNameError").style.display = "none";
    document.getElementById("genderError").style.display = "none";
    document.getElementById("emailError").style.display = "none";
    document.getElementById("phoneNoError").style.display = "none";
    document.getElementById("commentsError").style.display = "inline";
};

function ConsultantAddFormvalidation(){
 // alert("hiiiii");
    var activitytype = document.getElementById("activityType").value;
    var assignedTo = document.getElementById("assignedTo").value;
    if(activitytype == "-1"){
      alert("Please select Activity Type");
       return false;
    }
     if(assignedTo == "-1"){
       alert("Please select AssignTo");
       return false;
     }
}