/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



function isNumberKeyPayrollAmt(evt) {
 
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode!=46 && charCode > 31 && (charCode < 48 || charCode > 57))
    {
     // alert("Please enter numeric value");
      x0p( '','Please enter numeric value','info');
      return false;
    }else {
        return true;
        }
    }
    
    
    
    
 /* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function checkEmail(myForm) {
    
    var email = myForm.value;
   email = email.replace(/^\s+|\s+$/g,'');
    
    if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(myForm.value)){
        return (true)
    }

    document.getElementById("empEmailId").value="";
  //  alert("Invalid E-mail Address! Please re-enter.");
    x0p( '','Invalid E-mail Address! Please re-enter.','info');
    
    return (false)
}

function payRollFieldLengthValidator(element) {
    var i=0;
 
    //if(element.id == 'employerId')
    if(element.id=="pfAccount"|| element.id =="UANNo"||element.id == "pfno"||element.id=="bankAccountNo"||element.id == "employerId"||element.id == "lifeInsureancePolicy"||element.id =="bankAccNo")
    { 
        i=50;
    }
            
    if(element.id=="fathername"||element.id=="fatherTitle")
    { 
        i=30;
    }
            
    if(element.id=="contractPeriod")
    { 
        i=10;
    }
    if(element.id=="itgBatch")
    { 
        i=8;
    }
            
    if(element.id=="corporateEmail"||element.id=="personalEmail")
    { 
        i=60;
    }
            
    if(element.id=="ssn")
    { 
        element.id="PAN No";
        i=12;
    }
    if(element.id=="zip")
    { 
        i=15;
    }
    if(element.id=="state" || element.id=="policyNumber")
    { 
        i=20;
    }
      
            
    if(element.id=="basic"||element.id=="da"||element.id=="hra"||element.id=="ta"||element.id=="ra"||element.id=="employeePf"||element.id=="employerPf"||element.id=="entertainment"||element.id=="life"||element.id=="kidsEducation"||element.id=="health"||element.id=="vehicleAllowance" ||element.id=="professionalTax"||element.id=="cca"||element.id=="otherDeductions"||element.id=="miscPay"||element.id=="splAllowance" ||element.id=="longTermBonus"||element.id=="grossPay"||element.id=="projectPay"||element.id=="variablePay"||element.id=="attendanceAllow" ||element.id=="totalCost"||element.id=="datePayRevised"||element.id=="prevYtdSalary"||element.id=="empSaving2"||element.id=="empSaving1"
        ||element.id=="healthInsuranceAnnual"||element.id=="healthInsuranceAmt" || element.id=="onProjectIndValue1" || element.id=="onProjectIndValue2" || element.id=="onsiteIndV" || element.id=="hbLoanInterst"|| element.id=="ppf" || element.id=="medicalIns"|| element.id=="lifeIns" || element.id=="hraLifeInsuranceSavings"|| element.id=="premium"|| element.id=="eduInterest"|| element.id=="fd"|| element.id=="hbLoanPrinciple"  || element.id=="mutualFunds"|| element.id=="nsc"|| element.id=="tutionfees"|| element.id=="netPaid"|| element.id=="dedEmpPf"  || element.id=="tds"|| element.id=="dedIncomeTax"|| element.id=="dedCorporateLoan"|| element.id=="dedLife"|| element.id=="dedHealth"
        || element.id=="dedOthers"|| element.id=="health1"|| element.id=="employeePfPayRollDetails"|| element.id=="grossPayPayRollDetails" || element.id=="earnedBasic"|| element.id=="earnedFood"|| element.id=="earnedDa"|| element.id=="earnedLaundary"|| element.id=="earnedHra" || element.id=="earnedMaidServices"|| element.id=="earnedTa"|| element.id=="earnedEntertainment"|| element.id=="earnedRa"|| element.id=="earnedKidsEducation"  || element.id=="earnedLife"|| element.id=="earnedVehicleAllowance"|| element.id=="earnedHealth"|| element.id=="earnedLongTermBonus"  || element.id=="earnedCCa"|| element.id=="earnedMiscPay"|| element.id=="earnedProjectPay"|| element.id=="earnedEmployeePf"|| element.id=="earnedattallowance"
        || element.id=="earnedsplallowance"|| element.id=="tdsDeduction"|| element.id=="employeePfActualDetails"|| element.id=="grossPayActualDetails"|| element.id=="bonusCommission"|| element.id=="netPaidActualDetails"|| element.id=="otherDeductions"|| element.id=="taxableIncome"|| element.id=="otherAdditions"|| element.id=="ytdGross"|| element.id=="ytdTaxableSalary"|| element.id=="ytdLongterm"|| element.id=="ytdTaxableCommission"|| element.id=="ytdPf"|| element.id=="ytdTDSonSalary" || element.id=="ytdProffTax"|| element.id=="ytdTDSOnCommm"|| element.id=="ytdLifeInsurance"|| element.id=="ytdTDSCollected"|| element.id=="ytdTa"|| element.id=="ytdTDSLiabilitiesSalary"
        || element.id=="ytdRa"|| element.id=="ytdTDSLiabilitiesBonus"|| element.id=="ytdOthersMisc"|| element.id=="diffTDSLiabilitiesSalary"|| element.id=="ytdExpTaxFree" || element.id=="diffTDSLiabilitiesBonus"|| element.id=="ytdProjectPay"|| element.id=="ytdSavings1Reported"|| element.id=="ytdSavings2Reported"  
        || element.id=="lifeInsurancePremium"|| element.id=="housingLoanRepay"|| element.id=="nscTds" || element.id=="ppfContribution" || element.id=="tutionFee"   || element.id=="elss" || element.id=="postOfficeTerm" || element.id=="bankDepositTax" || element.id=="othersTDS"   || element.id=="contributionToPf"  || element.id=="npsEmployeeContr" || element.id=="totalTds" || element.id=="totalTdsDeductable" || element.id=="interstOnBorrowed"  || element.id=="insuranceForParents"  || element.id=="insuranceForParentsDeduc"  || element.id=="insuranceOthers" || element.id=="insuranceOthersDeduc"   || element.id=="interstOnEdu" || element.id=="interstOnHrAssumptions" || element.id == "interstOnHrAssumptionsInv" || element.id =="employeeesi" || element.id =="employeresi" || element.id == "licFromSal" || element.id == "totalCostCalc" || element.id == "basicCalc" || element.id == "prjPayCalc" || element.id == "attAllowCalc" || element.id == "longBonusCalc" || element.id=="totalCostCalc"|| element.id=="longBonusCalc"|| element.id=="employerPfCalc"|| element.id=="attAllowCalc"|| element.id=="healthCalc"|| element.id=="pfTaxCalc"||element.id=="diffPF")
        { 
        if(element.value.length==0){
            element.value="0.0";
        }
        var val=parseFloat(element.value);
        val=val.toFixed(2);
        element.value=val;
        i=10;
    }
             
    if(element.id=="homePhone"||element.id=="city"||element.id=="mobilePhone"||element.id=="fatherPhone"||element.id == "bankName"||element.id =="workPhone")
    { 
        i=25;
    }
            
    if(element.id=="lifeInsureanceTerm"||element.id=="trainingPeriod"||element.id=="contractPeriod"||element.id=="lifeInsureanceAmt"||element.id=="lifeInsureanceAnnual"||element.id=="daysInMonth"||element.id=="daysWorked"||element.id=="daysProject"||element.id=="daysVacation"||element.id=="daysHolidays"||element.id=="daysWeekends")
    { 
        i=11;
    }
            
    if(element.id=="wagecomments"||element.id=="wagecomments1"||element.id=="referencecomments"||element.id=="generalcomments" || element.id=="repaymentComments"  || element.id=="houseAddress")
    { 
        i=250;
    }
            
    if(element.id=="passportNo")
    { 
        i=20;
    }
    if(element.id=="adharNo")
    { 
        i=15;
    }
    if(element.id=="address")
    {
        i=160;
    }
    if(element.id=="payRollComments")
    {
        i=255;
    }
    if(element.id=="resonsForLeaving")
    {
        // alert("in reason")
        i=120;
    }
   var temp=0;
    if(element.id=="overlaySavingAmount" || element.id=="overlaymonthlyAmount" || element.id=="taxApprovedAmount"   ){
        temp=1;
        i=10;
        if(element.value.replace(/^\s+|\s+$/g,"").length>i) {
            str=new String(element.value);
            element.value=str.substring(0,i);
            //   x0p( '','Please enter OwnerName!','info');
            x0p( '','Amount length must be less than '+i,'info');
            element.focus();
            return false;
        }
    }
    if(element.id=="taxcomments" ){
       
        temp=1;
        i=500;
        if(element.value.replace(/^\s+|\s+$/g,"").length>i) {
            str=new String(element.value);
            element.value=str.substring(0,i);
            //   x0p( '','Please enter OwnerName!','info');
            x0p( '','Comments length must be less than '+i,'info');
            element.focus();
            return false;
        }
    }
    if(element.id=="ownerName" ){
        temp=1;
        i=60;
        if(element.value.replace(/^\s+|\s+$/g,"").length>i) {
            str=new String(element.value);
            element.value=str.substring(0,i);
            //   x0p( '','Please enter OwnerName!','info');
            x0p( '','Owner Name length must be less than '+i,'info');
            element.focus();
            return false;
        }
    }
    if(element.id=="PANNumber" ){
        temp=1;
        i=15;
        if(element.value.replace(/^\s+|\s+$/g,"").length>i) {
            str=new String(element.value);
            element.value=str.substring(0,i);
            //   x0p( '','Please enter OwnerName!','info');
            x0p( '','PAN Number length must be less than '+i+' characters','info');
            element.focus();
            return false;
        }
    }
     
    if(temp==0 && element.value.replace(/^\s+|\s+$/g,"").length>i) {
        str=new String(element.value);
        element.value=str.substring(0,i);
        x0p( '','The '+element.id+' length must be less than '+i+' characters','info');
        element.focus();
        return false;
    }
    return true;
        
        
}




function yearValidation(year,ev) {

    var text = /^[0-9]+$/;
    if(ev.type=="blur" || year.length==4 && ev.keyCode!=8 && ev.keyCode!=46) {
        if (year != 0) {
            if ((year != "") && (!text.test(year))) {
                document.getElementById("year").value="";
             //   alert("Please Enter Numeric Values Only");
                x0p( '','Please Enter Numeric Values Only','info');
                return false;
            }

            if (year.length != 4) {
                document.getElementById("year").value="";
             //   alert("Year is not proper. Please check");
                x0p( '','Year is not proper. Please check','info');
                return false;
            }
      
            return true;
        }
    }
}

function resetPayrollValues(){

    document.getElementById("basic").value = "0.0";
    document.getElementById("da").value = "0.0";
    document.getElementById("hra").value = "0.0";
    document.getElementById("ta").value = "0.0";
    document.getElementById("ra").value = "0.0";
    document.getElementById("entertainment").value = "0.0";
    document.getElementById("kidsEducation").value = "0.0";
    document.getElementById("vehicleAllowance").value = "0.0";
    document.getElementById("cca").value = "0.0";
    document.getElementById("miscPay").value = "0.0";
    document.getElementById("splAllowance").value = "0.0";
    document.getElementById("longTermBonus").value = "0.0";
    document.getElementById("projectPay").value = "0.0";
    document.getElementById("attendanceAllow").value = "0.0";
    document.getElementById("onProjectInd").checked = false;
    document.getElementById("onProjectIndValue1").value = "0.0";
    document.getElementById("onProjectIndValue2").value = "0.0";
    document.getElementById("onsiteInd").checked = false;
    
    document.getElementById("employerPf").value = "0.0";
    document.getElementById("employeePf").value = "0.0";
    document.getElementById("life").value = "0.0";
    document.getElementById("health").value = "0.0";
    
    document.getElementById("professionalTax").value = "0.0";
    document.getElementById("otherDeductions").value = "0.0";
     
    document.getElementById("grossPay").value = "0.0";
     
    document.getElementById("variablePay").value = "0.0";
     
    document.getElementById("totalCost").value = "0.0";
    document.getElementById("datePayRevised").value = "0.0";
    
}

function isNumberKey(element)
{
    var val=element.value;
    if (isNaN(val)) {
       // alert("enter numeric values");
             x0p( '','Enter Decimal Number!','info'); 
        element.value="";
        return false;
    }
    else{
        return true;
    }
}

      
function checkForSpace(eve){
    //   alert(eve);
    
    var enteredVal = eve.value;
    //alert("============="+enteredVal)
    // alert("============="+eve.id)
    if(enteredVal.length ==0){
    //    alert("Please enter "+eve.id+" value");
         x0p( '','Please enter '+eve.id+' value','info');
        return false;
    }
    
}      


function checkValidationsForLeavesReports(){
    var departmentId = document.getElementById("departmentId").value;
    var empnameById = document.getElementById("empnameById").value;
    var year = document.getElementById("year").value;
    var month = document.getElementById("month").value;
    var country = document.getElementById("country").value;
    if(departmentId.length == 0){
    //    alert("Please select department");
        x0p( '','Please select department','info');
        return false;
    }
   
    else if(year.length == 0){
    //    alert("Please enter year");
        x0p( '','Please enter year','info');
        return false;
    }
    else if(month.length == 0){
    //    alert("Please select month");
        x0p( '','Please select month','info');
        return false;
    }
    else if(country.length == 0){
     //   alert("Please select country");
        x0p( '','Please select country','info');
        return false;
    }
    else
    {
        document.getElementById("leaveReports").submit();
    }
}

function enableOnProjectBasedScripts(){
    
    if(!document.getElementById("onProjectInd").checked){
        document.getElementById("longTermBonus").value = "0.0";
        document.getElementById("projectPay").value = "0.0";
        document.getElementById("attendanceAllow").value = "0.0";
        document.getElementById("longTermBonus").readOnly = true;
        document.getElementById("projectPay").readOnly = true;
        document.getElementById("attendanceAllow").readOnly = true;
    }
    
    
}

/*
 * Payroll Check Validations
 * Date : 04/29/2015
 * 
 */

function checkValidationsForPayrollReports(){
    
    var year = document.getElementById("year").value;
    var month = document.getElementById("month").value;
    
  
   
    if(year.length == 0){
     //   alert("Please enter year");
        x0p( '','Please enter year','info');
        return false;
    }
    else if(month.length == 0){
      //  alert("Please select month");
        x0p( '','Please select month','info');
        return false;
    }
   
    else
    {
        document.getElementById("leaveReports").submit();
    }
}


//----------------------------

function changeDorepayment() {
    if(!document.getElementById("freezePayroll").checked){
        document.getElementById("doRepaymentFlag").disabled = false;
         document.getElementById("dedIncomeTax").disabled = false;
         document.getElementById("dedCorporateLoan").disabled = false;
         document.getElementById("dedLife").disabled = false;
         document.getElementById("dedHealth").disabled = false;
         document.getElementById("dedOthers").disabled = false;
         document.getElementById("projectEndDate").disabled = false;
         document.getElementById("isBlock").disabled = false;
    }else{
        document.getElementById("doRepaymentFlag").checked = false;
        document.getElementById("doRepaymentFlag").disabled = true;
          document.getElementById("dedIncomeTax").disabled = true;
         document.getElementById("dedCorporateLoan").disabled = true;
         document.getElementById("dedLife").disabled = true;
         document.getElementById("dedHealth").disabled = true;
         document.getElementById("dedOthers").disabled = true;
         document.getElementById("projectEndDate").disabled = true;
         document.getElementById("isBlock").disabled = true;
        document.getElementById("repaymentComments").value='';
        document.getElementById("repaymentRow").style.display  =  'none';
    }
     
}

function changeRepayComments() {
    if(document.getElementById("doRepaymentFlag").checked){
        document.getElementById("repaymentRow").style.display  =  'table-row';
        document.getElementById("repaymentGrossAndNetRow").style.display  =  'table-row';
        document.getElementById("repaymentVariableRow").style.display  =  'table-row';
    }else {
      //  document.getElementById("repaymentComments").value='';
        document.getElementById("repaymentRow").style.display  =  'none';
       // document.getElementById("repaymentGrossAndNetRow").value="";
       // document.getElementById("repaymentVariableRow").value="";
        document.getElementById("repaymentGrossAndNetRow").style.display  =  'none';
        document.getElementById("repaymentVariableRow").style.display  =  'none';
      
    }
}

function showHouseRentDetails(){
     var v_HrExemptions = document.getElementById("interstOnHrAssumptionsInv").value;

    if(v_HrExemptions>100000){
        //alert("in if");
        document.getElementById("houseOwnerDetails1").style.display  =  'table-row';
        document.getElementById("houseOwnerDetails2").style.display  =  'table-row';
        document.getElementById("houseOwnerMobileNumber").style.display  =  'table-row';
    }
    else
        {
             document.getElementById("houseOwnerDetails1").style.display  =  'none';
        document.getElementById("houseOwnerDetails2").style.display  =  'none';
        document.getElementById("houseOwnerMobileNumber").style.display  =  'none';
        }
}   

function checkDates(element)
{     
    var birthDate = element.value;
    
    if(birthDate.length > 10){
          x0p( '','Invalid Date Format','info');
       // alert("Invalid Date Format");
        element.value = "";
        element.focus();
        return;
    }
    var split = birthDate.split(/[^\d]+/);
    var year = parseFloat(split[2]);
    var month = parseFloat(split[0]);
    var day = parseFloat(split[1]);
    if(birthDate != null){
        if (!/\d{2}\/\d{2}\/\d{4}/.test(birthDate)) {
                x0p( '','Invalid Date Format','info');
          //  alert("Invalid Date Format");
            element.value = "";
            element.focus();
            return;
        }
        if(month > 13 || day > 32){
                 x0p( '','Invalid Date Format','info');
          //  alert("Invalid Date Format");
            element.value = "";
            element.focus();     
            return;
        }
    }
}

var _validFileExtensions = [".rar" , ".TAR", ".zip" , ".doc", ".docx" , ".XLS" , ".XLSX" , ".pdf" , ".txt" , ".rtf" , ".jpg" , ".jpeg" , ".jpe" , ".tif" , ".png" , ".gif"];    
function ValidateSingleInput(oInput) {
    if (oInput.type == "file") {
        var sFileName = oInput.value;
       // alert(sFileName)
       //  var size = document.getElementById('file').files[0].size;
       var size =oInput.files[0].size;
       // var fileSize = sFileName.size;
    
       if(sFileName.length>50){
                   oInput.value = "";
                   x0p( '','File name length must be less than 50 characters!','info');
                 return false;
             }else {
            if(parseInt(size)>2097152) {
              oInput.value = "";
                  x0p( '','File size must be less than 2 MB!','info');
                 return (false);
            }
             }
        
         if (sFileName.length > 0) {
            var blnValid = false;
            for (var j = 0; j < _validFileExtensions.length; j++) {
                var sCurExtension = _validFileExtensions[j];
                if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) {
                    blnValid = true;
                    break;
                }
            }
             
            if (!blnValid) {
                x0p( '',sFileName + ' is invalid, allowed extensions are: ' + _validFileExtensions.join(", "),'info');
               // alert("Sorry, " + sFileName + " is invalid, allowed extensions are: " + _validFileExtensions.join(", "));
                oInput.value = "";
                return false;
            }
        }
    
    return true;
}

}
function isValidDate(event){
    var date = event.value;
  // var regExp = /^([1-9]|[1][012])\/|-([1-9]|[1][0-9]|[2][0-9]|[3][01])\/|-([1][6-9][0-9][0-9]|[2][0][01][0-9])$/;

  if (date.match(/^(?:(0[1-9]|1[012])[\- \/.](0[1-9]|[12][0-9]|3[01])[\- \/.](19|20)[0-9]{2})$/)){
 // if(regExp.test(event.value)) {   
    return true;
  }else{
      x0p('','  Invalid Date','info');
       event.value = "";
        
    return false;
  }
}
 function isNumberKeyWage(element)
{
    var val=element.value;
    if (isNaN(val)) {
       // alert("enter numeric values");
             x0p( '','Enter Decimal Number!','info'); 
        element.value="0.0";
        return false;
    }
    else{
        return true;
    }
}