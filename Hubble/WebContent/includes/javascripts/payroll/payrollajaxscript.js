/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function newXMLHttpRequest() {
    var xmlreq = false;
    if(window.XMLHttpRequest){
        xmlreq = new XMLHttpRequest();
    }else if(window.ActiveXObject) {
        try {
            xmlreq = new ActiveXObject("MSxm12.XMLHTTP");
        } catch(e1) {
            try {
                xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
            } catch(e2) {
                xmlreq = false;
            }
        }
    }
    return xmlreq;
}

function checkEmpExitsForPayroll(){
    document.getElementById('resultMessage').innerHTML = " ";
    var email = document.getElementById('empEmailId').value;
  // email = email.replace(/^\s+|\s+$/g,'');
    if(email.trim()==""){
        
       // alert("Please enter Employee Id");
           x0p( '','Please enter valid id!!!','info');
        return false;
    }
    else
    {
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerText(req,populateEmpExitsOrNot);    
        // var url = CONTENXT_PATH+"/AjaxHandlerServlet?from=gridAjax&activityId="+aId;
        var url=CONTENXT_PATH+"/checkEmpExitsForPayroll.action?email="+email;
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
}


function populateEmpExitsOrNot(res){
    
    if(res.length>0){
        var empId =res.split("|")[0];
        var empNum =res.split("|")[1];
        var empStatus = res.split("|")[2];
        var payRollId=res.split("|")[3];
       // alert(empNum+"======"+empStatus);
        var email = document.getElementById('empEmailId').value;
        if(empStatus=="Active")
        {
            
            if(payRollId=="" ||payRollId=="null" ||payRollId==null|| payRollId ==0){
              //  window.location="addPayrollRecord.action?email="+email+"&empId="+empId;
              window.location="addPayrollRecord.action?empId="+empId+"&payRollId="+empNum;
            }
            else
            {
                document.getElementById('resultMessage').innerHTML="<font style='color:red;font-size:15px;'>This record is alread exists!</font>"
            }

        }
        else{
            document.getElementById('resultMessage').innerHTML = "<font style='color:blue;font-size:15px'>Please check the employee profile,current status for the record is <font style='color:red;font-size:15px;'>"+empStatus+"</font></font>"; 
        }
    }
    else{
    
    
        document.getElementById('resultMessage').innerHTML = "<font style='color:red;font-size:15px'>Given Id doesn't exists..</font>";  
    }
}


/*function readyStateHandlerText(req,responseTextHandler){
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {               
                document.getElementById("loadingMessage").style.display = "block";
                responseTextHandler(req.responseText);
            } else {
                alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }
    }
}    
*/


function readyStateHandlerText(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadingMessage")).style.display = "none";
                (document.getElementById("loadingMessageForFreeze")).style.display = "none";
                
                responseTextHandler(req.responseText);
            } else {
                
           //     alert("HTTP error ---"+req.status+" : "+req.statusText);
            x0p( '','HTTP error ---'+req.status+' : '+req.statusText,'info');
            }
        }else {
            (document.getElementById("loadingMessage")).style.display = "block";
            (document.getElementById("loadingMessageForFreeze")).style.display = "block";
        }
    }
}
function readyStateHandler(req,responseXmlHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                responseXmlHandler(req.responseXML);
            } else {
              //  alert("HTTP error"+req.status+" : "+req.statusText);
               x0p( '','HTTP error ---'+req.status+' : '+req.statusText,'info');   
            }
        }
    }
}

function readyStateHandlerXml(req,responseXmlHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                responseXmlHandler(req.responseXML);
            } else {
               // alert("HTTP error"+req.status+" : "+req.statusText);
                  x0p( '','HTTP error ---'+req.status+' : '+req.statusText,'info');
            }
        }
    }
}

function toggleOverlayForProfile(){
    document.getElementById("resultMessage").innerHTML="";
    var overlay = document.getElementById('payrollOverlay');
    var specialBox = document.getElementById('hubblePayrollOverlay');
        
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        // alert("in block");
        overlay.style.display = "none";
        specialBox.style.display = "none";
                   
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }   
}
   
function confirmForNewPayrollAdd(){

    // var result = confirm("Are you sure on adding new persons payroll ?");
     var result = false;
  //  var result = confirm("Are you sure you want to un freeze the records for "+val+","+year+" ?");
      x0p('re you sure on adding new persons payroll ?', '', 'warning',
     function(button) {
       
        if(button == 'warning') {
            result = true;
            //alert(button)
        }
        if(button == 'cancel') { 
             result = false;
        }
        
    if(result)
    {
        toggleOverlayForProfile();
        return true;
    }else
    {
        return false;
    }
});
}

function getEmpTitleData() {
    
    var departmentName = document.getElementById("departmentId").value;
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateEmpTitles);
    var url = CONTENXT_PATH+"/getEmpForTitles.action?departmentName="+departmentName;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populateEmpTitles(resXML) {
    // alert(resXML);
    
    var titleId = document.getElementById("titleId");
    
    var department = resXML.getElementsByTagName("DEPARTMENT")[0];
    var titles = department.getElementsByTagName("TITLE");
    titleId.innerHTML=" ";
    
    for(var i=0;i<titles.length;i++) {
        var titleName = titles[i];
        
        var name = titleName.firstChild.nodeValue;
        var opt = document.createElement("option");
        if(i==0){
            opt.setAttribute("value","");
        }else{
            opt.setAttribute("value",name);
        }
        opt.appendChild(document.createTextNode(name));
        titleId.appendChild(opt);
    }
}



/*function computeGrossPay(){
    
    var basic =  document.getElementById("basic").value;
    var da =   document.getElementById("da").value;
    var hra =    document.getElementById("hra").value;
    var ta =   document.getElementById("ta").value;
    var ra= document.getElementById("ra").value;
    var entertainment=document.getElementById("entertainment").value;
    var kidsEdu=document.getElementById("kidsEducation").value;
    var vehicle = document.getElementById("vehicleAllowance").value;
    var cca =document.getElementById("cca").value;
    var miscpay=document.getElementById("miscPay").value;
    var splAllowance = document.getElementById("splAllowance").value;
    var health = document.getElementById("health").value;
    var life = document.getElementById("life").value;
    document.getElementById("grossPay").value = parseFloat(basic) + parseFloat(da) + parseFloat(hra) + parseFloat(ta) + parseFloat(ra) + parseFloat(entertainment) + parseFloat(kidsEdu) + parseFloat(vehicle) + parseFloat(cca) + parseFloat(miscpay) + parseFloat(splAllowance) + parseFloat(health) + parseFloat(life);
 
}*/

function addPayrollDetails(updateFlag){
   
   
    document.getElementById("payrollResultMessage").innerHTML = "";
    var firstName =     document.getElementById("firstName").value;
    if(firstName==null||firstName==""||firstName.length==0){
        firstName = "nun";
    }
    var lastName = document.getElementById("lastName").value;
    if(lastName==null||lastName==""||lastName.length==0){
        lastName = "nun";
    }
    var middleName = document.getElementById("middleName").value;
    if(middleName==null||middleName==""||middleName.length==0){
        middleName = "nun";
    }
    var departmentId = document.getElementById("departmentId").value;
    if(departmentId==null||departmentId==""||departmentId.length==0){
     
        departmentId = "nun";
    }
    var titleId = document.getElementById("titleId").value;
    if(titleId==null||titleId==""||titleId.length==0){
     
        titleId = "nun";
    }
    var shiftId = document.getElementById("shiftId").value;
    var classificationId = document.getElementById("classificationId").value;
    var locationId = document.getElementById("locationId").value;
    if(locationId==null||locationId==""||locationId.length==0){
     
        locationId = "nun";
    }

  //  var regionId = document.getElementById("regionId").value;
    var gender = document.getElementById("gender").value;
    var currStatus = document.getElementById("currStatus").value;
 
 
    var isPfFlag = "";
    if(document.getElementById("isPfFlag").checked){
        isPfFlag="1";
    }
    else
    {
        isPfFlag="0";
    }
    var employerId = document.getElementById("employerId").value;
    // alert(employerId.length)
    if(employerId==null||employerId==""||employerId.length==0){
        //  alert(employerId)
        employerId = "nun";
    }
    //alert(employerId)
    var ssn = document.getElementById("ssn").value;
    if(ssn==null||ssn==""||ssn.length==0){
        ssn = "nun";
    }
    var passportNo = document.getElementById("passportNo").value;
    if(passportNo==null||passportNo==""||passportNo.length==0){
        passportNo = "nun";
    }
    var pfAccount = document.getElementById("pfAccount").value;
    if(pfAccount==null||pfAccount==""||pfAccount.length==0){
        pfAccount = "nun";
    }

    var trainingPeriod = document.getElementById("trainingPeriod").value;
    if(trainingPeriod==null||trainingPeriod==""||trainingPeriod.length==0){
        trainingPeriod = "nun";
    }
    var contractPeriod = document.getElementById("contractPeriod").value;
    if(contractPeriod==null||contractPeriod==""||contractPeriod.length==0){
        contractPeriod ="nun";
    }
    var dateOfJoining = document.getElementById("dateOfJoining").value;
    if(dateOfJoining==null||dateOfJoining==""||dateOfJoining.length==0){
        dateOfJoining = "nun";
    }
 
    var UANNo = document.getElementById("UANNo").value;
    if(UANNo==null||UANNo==""||UANNo.length==0){
        UANNo = "nun";
    }
    var adharNo = document.getElementById("adharNo").value;
    if(adharNo==null||adharNo==""||adharNo.length==0){
        adharNo = "nun";
    }

    var resonsForLeaving = document.getElementById("resonsForLeaving").value;
    if(resonsForLeaving==null||resonsForLeaving==""||resonsForLeaving.length==0){
        resonsForLeaving = "nun";
    }
    var address = escape(document.getElementById("address").value);
    if(address==null||address==""||address.length==0){
        address ="nun";
    }
    if(address.indexOf(',') > -1) {
        
        address = address.replace(',', ' ');
    }
    var corporateEmail = document.getElementById("corporateEmail").value;
    if(corporateEmail==null||corporateEmail==""||corporateEmail.length==0){
        corporateEmail = "nun";
    }
    var personalEmail = document.getElementById("personalEmail").value;
    if(personalEmail==null||personalEmail==""||personalEmail.length==0){
        personalEmail = "nun";
    }
    var fathername = document.getElementById("fathername").value;
    if(fathername==null||fathername==""||fathername.length==0){
        fathername = "nun";
    }
    var fatherTitle = document.getElementById("fatherTitle").value;
    if(fatherTitle==null||fatherTitle==""||fatherTitle.length==0){
        fatherTitle ="nun";
    }
    var city = document.getElementById("city").value;
    if(city==null||city==""||city.length==0){
        city = "nun";
    }
    var state = document.getElementById("state").value;
    if(state==null||state==""||state.length==0){
        state = "nun";
    }
    var zip = document.getElementById("zip").value;
    if(zip==null||zip==""||zip.length==0){
        zip = "nun";
    }
    var fatherPhone = document.getElementById("fatherPhone").value;
    if(fatherPhone==null||fatherPhone==""||fatherPhone.length==0){
        fatherPhone ="nun";
    }
    var homePhone = document.getElementById("homePhone").value;
    if(homePhone==null||homePhone==""||homePhone.length==0){
        homePhone = "nun";
    }
    var mobilePhone = document.getElementById("mobilePhone").value;
    if(mobilePhone==null||mobilePhone==""||mobilePhone.length==0){
        mobilePhone = "nun";
    }
    var basic = document.getElementById("basic").value;
    var paymentType = document.getElementById("paymentType").value;
    var da = document.getElementById("da").value;
    var bankAccountNo = document.getElementById("bankAccountNo").value;
    if(bankAccountNo==null||bankAccountNo==""){
        bankAccountNo = "nun";
    }
    var hra = document.getElementById("hra").value;
    var bankName = document.getElementById("bankName").value;
    var ta = document.getElementById("ta").value;
    var employerPf = document.getElementById("employerPf").value;
    var ra = document.getElementById("ra").value;
    var employeePf = document.getElementById("employeePf").value;
    var entertainment = document.getElementById("entertainment").value;
    var life = document.getElementById("life").value;
    var kidsEducation = document.getElementById("kidsEducation").value;
    var health = document.getElementById("health").value;
    var vehicleAllowance = document.getElementById("vehicleAllowance").value;
    var professionalTax = document.getElementById("professionalTax").value;
    var cca = document.getElementById("cca").value;
    var otherDeductions = document.getElementById("otherDeductions").value;
    var miscPay = document.getElementById("miscPay").value;
    var splAllowance = document.getElementById("splAllowance").value;
    var longTermBonus = document.getElementById("longTermBonus").value;
    var grossPay = document.getElementById("grossPay").value;
    var projectPay = document.getElementById("projectPay").value;
    var variablePay = document.getElementById("variablePay").value;
    var attendanceAllow = document.getElementById("attendanceAllow").value;
    var totalCost = document.getElementById("totalCost").value;
    var onProjectInd = "";
    if(document.getElementById("onProjectInd").checked)
    {
        onProjectInd = "1";
    }
    else{
        onProjectInd = "0";
    }
    var isSixMonthsLock="";
    if(document.getElementById("isSixMonthsLock").checked)
    {
        isSixMonthsLock = "1";
    }
    else{
        isSixMonthsLock = "0";
    }
    var onProjectIndValue1 = document.getElementById("onProjectIndValue1").value;
    var onProjectIndValue2 = document.getElementById("onProjectIndValue2").value;
    var datePayRevised = document.getElementById("datePayRevised").value;
    if(datePayRevised==null||datePayRevised==""||datePayRevised.length==0){
        datePayRevised = "nun";
    }
    var lockAmtStartDate = document.getElementById("lockAmtStartDate").value;
    if(lockAmtStartDate==null||lockAmtStartDate==""||lockAmtStartDate.length==0){
        lockAmtStartDate = "nun";
    }
    
    var onsiteInd = "";
    if(document.getElementById("onsiteInd").checked){
        onsiteInd = "1";
    }
    else{
        onsiteInd = "0";
    }
    var onsiteIndV = document.getElementById("onsiteIndV").value;
    //    var prevYtdSalary = document.getElementById("prevYtdSalary").value;
    var empSaving1 = document.getElementById("empSaving1").value;
    var empSaving2 = document.getElementById("empSaving2").value;
    var empSaving3 = document.getElementById("empSaving3").value;
    var empSaving4 = document.getElementById("empSaving4").value;
    var empSaving5 = document.getElementById("empSaving5").value;
    var empSaving6 = document.getElementById("empSaving6").value;

    /*   var tutionfees = document.getElementById("tutionfees").value;
    if(tutionfees==null||tutionfees==""||tutionfees.length==0){
        tutionfees = "nun";
    }
    var hbLoanInterst = document.getElementById("hbLoanInterst").value;
    if(hbLoanInterst==null||hbLoanInterst==""||hbLoanInterst.length==0){
        hbLoanInterst = "nun";
    }
    var ppf = document.getElementById("ppf").value;
    if(ppf==null||ppf==""||ppf.length ==0){
        ppf ="nun";
    }
    var medicalIns =  document.getElementById("medicalIns").value;
    if(medicalIns==null||medicalIns==""||medicalIns.length ==0){
        medicalIns = "nun";
    }
    var lifeIns = document.getElementById("lifeIns").value;
    if(lifeIns==null||lifeIns==""||lifeIns.length==0){
        lifeIns = "nun";
    }
    var hraLifeInsuranceSavings = document.getElementById("hraLifeInsuranceSavings").value;
    if(hraLifeInsuranceSavings==null||hraLifeInsuranceSavings==""||hraLifeInsuranceSavings.length==0){
        hraLifeInsuranceSavings = "nun";
    }
    var premium = document.getElementById("premium").value;
    if(premium==null||premium==""||premium.length==0){
        premium = "nun";
    }
    var eduInterest = document.getElementById("eduInterest").value;
    if(eduInterest==null||eduInterest==""||eduInterest.length==0){
        eduInterest = "nun";
    }
    var fd = document.getElementById("fd").value;
    if(fd==null||fd==""||fd.length==0){
        fd ="nun";
    }
    var hbLoanPrinciple =  document.getElementById("hbLoanPrinciple").value;
    if(hbLoanPrinciple==null||hbLoanPrinciple==""||hbLoanPrinciple.length==0){
        hbLoanPrinciple = "nun";
    }
    var mutualFunds = document.getElementById("mutualFunds").value;
    if(mutualFunds==null||mutualFunds==""||mutualFunds.length==0){
        mutualFunds = "nun";
    }
    var nsc = document.getElementById("hraLifeInsuranceSavings").value;
    if(nsc==null||nsc==""||nsc.length==0){
        nsc = "nun";
    }*/
         
    /*   var lifeInsureanceAmt = document.getElementById("lifeInsureanceAmt").value;
    if(lifeInsureanceAmt==null||lifeInsureanceAmt==""){
        lifeInsureanceAmt = "nun";
    }
    var lifeInsureanceTerm = document.getElementById("lifeInsureanceTerm").value;
    if(lifeInsureanceTerm==null||lifeInsureanceTerm==""){
        lifeInsureanceTerm = "nun";
    }
    var lifeInsureanceAnnual = document.getElementById("lifeInsureanceAnnual").value;
    if(lifeInsureanceAnnual==null||lifeInsureanceAnnual==""){
        lifeInsureanceAnnual ="nun";
    }
    var lifeInsureancePolicy =  document.getElementById("lifeInsureancePolicy").value;
    if(lifeInsureancePolicy==null||lifeInsureancePolicy==""){
        lifeInsureancePolicy = "nun";
    }
    var healthInsuranceAnnual = document.getElementById("healthInsuranceAnnual").value;
    if(healthInsuranceAnnual==null||healthInsuranceAnnual==""){
        healthInsuranceAnnual = "nun";
    }
    var healthInsuranceAmt = document.getElementById("healthInsuranceAmt").value;
    if(healthInsuranceAmt==null||healthInsuranceAmt==""){
        healthInsuranceAmt = "nun";
    }*/
    /* var wagecomments = document.getElementById("wagecomments").value;
    if(wagecomments==null||wagecomments==""||wagecomments.length == 0){
        wagecomments = "nun";
    }*/
    // alert("wagecomments-->"+wagecomments);
    // alert("wagecomments.indexOf(',')-->"+wagecomments.indexOf(','));
    /*   if(wagecomments.indexOf(',') > -1) {
        // value contains a comma
        // alert("in if");
        wagecomments = wagecomments.replace(',',' ');
    }
    var wagecomments1 = document.getElementById("wagecomments1").value;
    if(wagecomments1==null||wagecomments1==""||wagecomments1.length == 0){
        wagecomments1 = "nun";
    }
    if(wagecomments1.indexOf(',') > -1) {
        // value contains a comma
        // alert("in if");
        wagecomments1 = wagecomments1.replace(',',' ');
    }
    var generalcomments = document.getElementById("generalcomments").value;
    //alert(generalcomments);
    if(generalcomments==null||generalcomments==" "||generalcomments.length == 0){
        //alert("in");
        generalcomments = "nun";
    }
    if(generalcomments.indexOf(',') > -1) {
        // value contains a comma
        // alert("in if");
        generalcomments = generalcomments.replace(',',' ');
    }
    //alert(generalcomments);
    var referencecomments = document.getElementById("referencecomments").value;
    if(referencecomments==null||referencecomments==""||referencecomments.length == 0){
        referencecomments = "nun";
    }
    if(referencecomments.indexOf(',') > -1) {
        // value contains a comma
        // alert("in if");
        referencecomments = referencecomments.replace(',',' ');
    }*/
    var empId = document.getElementById("empId").value;
    var payRollId = document.getElementById("payRollId").value;
    if(payRollId.length ==0){
        payRollId ='0';
    }
    /*var tdsId = document.getElementById("tdsId").value;
    if(tdsId.length ==0){
        tdsId ='0';
    }*/
     
    // alert("payrollId -->"+payRollId);
    var homeAddressId = document.getElementById("homeAddressId").value;
    if(homeAddressId==null||homeAddressId==""||homeAddressId=="0"||homeAddressId==0){
        homeAddressId = "nun";
    }
    var workPhone = document.getElementById("workPhone").value;
    if(workPhone==null||workPhone==""||workPhone.length==0){
        workPhone = "nun";
    }

    var orgId =     document.getElementById("orgId").value;
    
    var itgBatch =     document.getElementById("itgBatch").value;
    if(itgBatch==null||itgBatch==""||itgBatch.length==0){
        itgBatch = "nun";
    }
    //  var projectEndDate = document.getElementById("projectEndDate").value;
    /*  if(projectEndDate==null||projectEndDate==""||projectEndDate.length==0){
        projectEndDate = "nun";
    }*/
    
    /*  var lifeInsurancePremium  =  document.getElementById("lifeInsurancePremium").value;
    var housingLoanRepay =  document.getElementById("housingLoanRepay").value;
    var nscTds =  document.getElementById("nscTds").value;
    var ppfContribution =  document.getElementById("ppfContribution").value;
    var tutionFee =  document.getElementById("tutionFee").value;
    var elss =  document.getElementById("elss").value;
    var postOfficeTerm =  document.getElementById("postOfficeTerm").value;
    var bankDepositTax =  document.getElementById("bankDepositTax").value;
    var othersTDS =  document.getElementById("othersTDS").value;
    var contributionToPf =  document.getElementById("contributionToPf").value;
    var npsEmployeeContr =  document.getElementById("npsEmployeeContr").value;
    var totalTds =  document.getElementById("totalTds").value;
    var totalTdsDeductable =  document.getElementById("totalTdsDeductable").value;
    var interstOnBorrowed =  document.getElementById("interstOnBorrowed").value;
    var interstOnBorrowedDeductable =  document.getElementById("interstOnBorrowedDeductable").value;
    var insuranceForParents =  document.getElementById("insuranceForParents").value;
    var insuranceForParentsDeduc =  document.getElementById("insuranceForParentsDeduc").value;
    var insuranceOthers =  document.getElementById("insuranceOthers").value;
    var insuranceOthersDeduc =  document.getElementById("insuranceOthersDeduc").value;
    var interstOnEdu =  document.getElementById("interstOnEdu").value;
  
    var interstOnHrAssumptionsInv =  document.getElementById("interstOnHrAssumptionsInv").value;
    var interstOnHrAssumptions =  document.getElementById("interstOnHrAssumptions").value;*/
    var expectedYearlyCost =  document.getElementById("expectedYearlyCost").value;
    var netPaidPayroll =  document.getElementById("netPaidPayroll").value;
    var employeeEsi = document.getElementById("employeeesi").value;
    var employerEsi = document.getElementById("employeresi").value;
    //   var licFromSal = document.getElementById("licFromSal").value;
    // var totalCostMatchedValue= document.getElementById("totalCostMatchedValue").value;
    // alert("orgId-->"+orgId);
    /*  var resultString="firstName:"+firstName+",lastName:"+lastName+",middleName:"+middleName+",departmentId:"+departmentId+",titleId:"+titleId+",shiftId:"+shiftId+","+
     
    "classificationId:"+classificationId+",locationId:"+locationId+",regionId:"+regionId+",gender:"+gender+",currStatus:"+currStatus+",isPfFlag:"+isPfFlag+","+
     
    "employerId:"+employerId+",ssn:"+ssn+",passportNo:"+passportNo+",pfAccount:"+pfAccount+",trainingPeriod:"+trainingPeriod+","+
 
    "contractPeriod:"+contractPeriod+",dateOfJoining:"+dateOfJoining+","+
    "UANNo:"+UANNo+",adharNo:"+adharNo+",resonsForLeaving:"+resonsForLeaving+",address:"+address+","+
    "corporateEmail:"+corporateEmail+",personalEmail:"+personalEmail+",fathername:"+fathername+",fatherTitle:"+fatherTitle+",city:"+city+",state:"+state+","+
    "zip:"+zip+",fatherPhone:"+fatherPhone+",homePhone:"+homePhone+",mobilePhone:"+mobilePhone+",basic:"+basic+",paymentType:"+paymentType+","+
    "da:"+da+",bankAccountNo:"+bankAccountNo+",hra:"+hra+",bankName:"+bankName+",ta:"+ta+",employerPf:"+employerPf+","+
    "ra:"+ra+",employeePf:"+employeePf+",entertainment:"+entertainment+",life:"+life+",kidsEducation:"+kidsEducation+",health:"+health+","+
    "vehicleAllowance:"+vehicleAllowance+",professionalTax:"+professionalTax+",cca:"+cca+",otherDeductions:"+otherDeductions+",miscPay:"+miscPay+",splAllowance:"+splAllowance+","+
    "longTermBonus:"+longTermBonus+",grossPay:"+grossPay+",projectPay:"+projectPay+",variablePay:"+variablePay+",attendanceAllow:"+attendanceAllow+",totalCost:"+totalCost+","+
    "onProjectInd:"+onProjectInd+",onProjectIndValue1:"+onProjectIndValue1+",onProjectIndValue2:"+onProjectIndValue2+",datePayRevised:"+datePayRevised+",onsiteInd:"+onsiteInd+",onsiteIndV:"+onsiteIndV+","+
    "prevYtdSalary:"+prevYtdSalary+",empSaving1:"+empSaving1+",empSaving2:"+empSaving2+
   
    ",tutionfees:"+tutionfees+",hbLoanInterst:"+hbLoanInterst+",ppf:"+ppf+","+
    "medicalIns:"+medicalIns+",lifeIns:"+lifeIns+",hraLifeInsuranceSavings:"+hraLifeInsuranceSavings+
    ",premium:"+premium+",eduInterest:"+eduInterest+",fd:"+fd+","+
    "hbLoanPrinciple:"+hbLoanPrinciple+",mutualFunds:"+mutualFunds+",nsc:"+nsc+
    ",wagecomments:"+wagecomments+",wagecomments1:"+wagecomments1+",generalcomments:"+generalcomments+",referencecomments:"+referencecomments+",homeAddressId:"+homeAddressId+",workPhone:"+workPhone+
    ",lifeInsurancePremium:"+lifeInsurancePremium+",housingLoanRepay:"+housingLoanRepay+",nscTds:"+nscTds+",ppfContribution:"+ppfContribution+",tutionFee:"+tutionFee+",elss:"+elss+",postOfficeTerm:"+postOfficeTerm+
    ",bankDepositTax:"+bankDepositTax+",othersTDS:"+othersTDS+",contributionToPf:"+contributionToPf+",npsEmployeeContr:"+npsEmployeeContr+",totalTds:"+totalTds+",totalTdsDeductable:"+totalTdsDeductable+",interstOnBorrowed:"+interstOnBorrowed+
    ",interstOnBorrowedDeductable:"+interstOnBorrowedDeductable+",insuranceForParents:"+insuranceForParents+",insuranceForParentsDeduc:"+insuranceForParentsDeduc+",insuranceOthers:"+insuranceOthers+",insuranceOthersDeduc:"+insuranceOthersDeduc+",interstOnEdu:"+interstOnEdu+",interstOnHrAssumptions:"+interstOnHrAssumptions+",interstOnHrAssumptionsInv:"+interstOnHrAssumptionsInv+",expectedYearlyCost:"+expectedYearlyCost+",licFromSal:"+licFromSal+",netPaidPayroll:"+netPaidPayroll+",employeresi:"+employerEsi+",employeeesi:"+employeeEsi+",orgId:"+orgId+",payRollId:"+payRollId+",tdsId:"+tdsId+",empId:"+empId;
*/ 


    var payRollComments =     document.getElementById("payRollComments").value;
    if(payRollComments==null||payRollComments==""||payRollComments.length==0){
        payRollComments = "nun";
    }
    var dateOfEmployement = document.getElementById("dateOfEmployement").value;
    if(dateOfEmployement==null||dateOfEmployement==""||dateOfEmployement.length==0){
        dateOfEmployement = "nun";
    }
    var dateOfTermination = document.getElementById("dateOfTermination").value;
    if(dateOfTermination==null||dateOfTermination==""||dateOfTermination.length==0){
        dateOfTermination = "nun";
    }
    var diffPF = document.getElementById("diffPF").value;
    if(diffPF==null||diffPF==""||diffPF.length==0){
        diffPF = "nun";
    }
    
    var esiFlag = "";
    if(document.getElementById("esiFlag").checked){
        esiFlag="1";
    }
    else
    {
        esiFlag="0";
    }
    var isFixedSalary="";
    if(document.getElementById("isFixedSalary").checked){
        isFixedSalary="1";
    }
    else
    {
        isFixedSalary="0";
    }
    var noSalary = "";
    if(document.getElementById("noSalary").checked){
       
        noSalary="1";
    //  alert(noSalary)
    }
    else
    {
        noSalary="0";
    }
     var practiceId =  document.getElementById("practiceId").value;
       var newGrossPay =  document.getElementById("newGrossPay").value;
    var submyObj = {};
    submyObj["firstName"] = firstName;
    submyObj["lastName"] = lastName;
    submyObj["middleName"] = middleName;
    submyObj["departmentId"] = departmentId;
    submyObj["titleId"] = titleId;
    submyObj["shiftId"] = shiftId;
    submyObj["classificationId"] = classificationId;
    submyObj["locationId"] = locationId;
  //  submyObj["regionId"] = regionId;
    submyObj["gender"] = gender;
    submyObj["currStatus"] = currStatus;
    submyObj["isPfFlag"] = isPfFlag;
    submyObj["employerId"] = employerId;
    submyObj["ssn"] = ssn;
    submyObj["passportNo"] = passportNo;
    submyObj["pfAccount"] = pfAccount;
    submyObj["trainingPeriod"] = trainingPeriod;
    submyObj["contractPeriod"] = contractPeriod;
    submyObj["dateOfJoining"] = dateOfJoining;
    submyObj["UANNo"] = UANNo;
    submyObj["adharNo"] = adharNo;
    submyObj["resonsForLeaving"] = resonsForLeaving;
    submyObj["address"] = address;
    submyObj["corporateEmail"] = corporateEmail;
    submyObj["personalEmail"] = personalEmail;
    submyObj["fathername"] = fathername;
    submyObj["fatherTitle"] = fatherTitle;
    submyObj["city"] = city;
    submyObj["state"] = state;
    submyObj["zip"] = zip;
    submyObj["fatherPhone"] = fatherPhone;
    submyObj["homePhone"] = homePhone;
    submyObj["mobilePhone"] = mobilePhone;
    submyObj["basic"] = basic;
    submyObj["paymentType"] = paymentType;
    submyObj["da"] = da;
    submyObj["bankAccountNo"] = bankAccountNo;
    submyObj["hra"] = hra;
    submyObj["bankName"] = bankName;
    submyObj["ta"] = ta;
    submyObj["employerPf"] = employerPf;
    submyObj["ra"] = ra;
    submyObj["employeePf"] = employeePf;
    submyObj["entertainment"] = entertainment;
    submyObj["life"] = life;
    submyObj["kidsEducation"] = kidsEducation;
    submyObj["health"] = health;
    submyObj["vehicleAllowance"] = vehicleAllowance;
    submyObj["professionalTax"] = professionalTax;
    submyObj["cca"] = cca;
    submyObj["otherDeductions"] = otherDeductions;
    submyObj["miscPay"] = miscPay;
    submyObj["splAllowance"] = splAllowance;
    submyObj["longTermBonus"] = longTermBonus;
    submyObj["grossPay"] = grossPay;
    submyObj["projectPay"] = projectPay;
    submyObj["variablePay"] = variablePay;
    submyObj["attendanceAllow"] = attendanceAllow;
    submyObj["totalCost"] = totalCost;
    submyObj["onProjectInd"] = onProjectInd;
    submyObj["onProjectIndValue1"] = onProjectIndValue1;
    submyObj["onProjectIndValue2"] = onProjectIndValue2;
    submyObj["datePayRevised"] = datePayRevised;
    submyObj["onsiteInd"] = onsiteInd;
    submyObj["onsiteIndV"] = onsiteIndV;
    submyObj["empSaving1"] = empSaving1;
    submyObj["empSaving2"] = empSaving2;
    submyObj["homeAddressId"] = homeAddressId;
    submyObj["workPhone"] = workPhone;
    submyObj["netPaidPayroll"] = netPaidPayroll;
    submyObj["employerEsi"] = employerEsi;
    submyObj["employeeEsi"] = employeeEsi;
    submyObj["itgBatch"] = itgBatch;
    submyObj["esiFlag"] = esiFlag;
    submyObj["diffPF"] = diffPF;
    submyObj["dateOfTermination"] = dateOfTermination;
    submyObj["dateOfEmployement"] = dateOfEmployement;
    submyObj["payRollComments"] = payRollComments;
    submyObj["empSaving3"] = empSaving3;
    submyObj["empSaving4"] = empSaving4;
    submyObj["empSaving5"] = empSaving5;
    submyObj["empSaving6"] = empSaving6;
     
    submyObj["expectedYearlyCost"] = expectedYearlyCost;
    submyObj["lockAmtStartDate"] = lockAmtStartDate;
    submyObj["isSixMonthsLock"] = isSixMonthsLock;
    submyObj["orgId"] = orgId;
    submyObj["payRollId"] = payRollId;
    submyObj["empId"] = empId;
    submyObj["isFixedSalary"] = isFixedSalary;
    submyObj["noSalary"] = noSalary;
    submyObj["practiceId"] = practiceId;
    submyObj["newGrossPay"] = newGrossPay;
    submyObj["updateFlag"]=updateFlag;
//    alert("getNewGrossPay"+newGrossPay);
//    alert(submyObj.toString())
    var json = JSON.stringify(submyObj);
 // alert(json);

    
    
    //    var resultString="firstName:"+firstName+",lastName:"+lastName+",middleName:"+middleName+",departmentId:"+departmentId+",titleId:"+titleId+",shiftId:"+shiftId+","+
    //     
    //    "classificationId:"+classificationId+",locationId:"+locationId+",regionId:"+regionId+",gender:"+gender+",currStatus:"+currStatus+",isPfFlag:"+isPfFlag+","+
    //     
    //    "employerId:"+employerId+",ssn:"+ssn+",passportNo:"+passportNo+",pfAccount:"+pfAccount+",trainingPeriod:"+trainingPeriod+","+
    // 
    //    "contractPeriod:"+contractPeriod+",dateOfJoining:"+dateOfJoining+","+
    //    "UANNo:"+UANNo+",adharNo:"+adharNo+",resonsForLeaving:"+resonsForLeaving+",address:"+address+","+
    //    "corporateEmail:"+corporateEmail+",personalEmail:"+personalEmail+",fathername:"+fathername+",fatherTitle:"+fatherTitle+",city:"+city+",state:"+state+","+
    //    "zip:"+zip+",fatherPhone:"+fatherPhone+",homePhone:"+homePhone+",mobilePhone:"+mobilePhone+",basic:"+basic+",paymentType:"+paymentType+","+
    //    "da:"+da+",bankAccountNo:"+bankAccountNo+",hra:"+hra+",bankName:"+bankName+",ta:"+ta+",employerPf:"+employerPf+","+
    //    "ra:"+ra+",employeePf:"+employeePf+",entertainment:"+entertainment+",life:"+life+",kidsEducation:"+kidsEducation+",health:"+health+","+
    //    "vehicleAllowance:"+vehicleAllowance+",professionalTax:"+professionalTax+",cca:"+cca+",otherDeductions:"+otherDeductions+",miscPay:"+miscPay+",splAllowance:"+splAllowance+","+
    //    "longTermBonus:"+longTermBonus+",grossPay:"+grossPay+",projectPay:"+projectPay+",variablePay:"+variablePay+",attendanceAllow:"+attendanceAllow+",totalCost:"+totalCost+","+
    //    "onProjectInd:"+onProjectInd+",onProjectIndValue1:"+onProjectIndValue1+",onProjectIndValue2:"+onProjectIndValue2+",datePayRevised:"+datePayRevised+",onsiteInd:"+onsiteInd+",onsiteIndV:"+onsiteIndV+","+
    //    // "prevYtdSalary:"+prevYtdSalary+
    //    "empSaving1:"+empSaving1+",empSaving2:"+empSaving2+
    //    /*   ",tutionfees:"+tutionfees+",hbLoanInterst:"+hbLoanInterst+",ppf:"+ppf+","+
    //    "medicalIns:"+medicalIns+",lifeIns:"+lifeIns+",hraLifeInsuranceSavings:"+hraLifeInsuranceSavings+
    //    ",premium:"+premium+",eduInterest:"+eduInterest+",fd:"+fd+","+
    //    "hbLoanPrinciple:"+hbLoanPrinciple+",mutualFunds:"+mutualFunds+",nsc:"+nsc+*/
    //    ",homeAddressId:"+homeAddressId+",workPhone:"+workPhone+
    //    ",netPaidPayroll:"+netPaidPayroll+",employeresi:"+employerEsi+",employeeesi:"+employeeEsi+",itgBatch:"+itgBatch+",esiFlagVal:"+esiFlag+",diffPF:"+diffPF+",dateOfTermination:"+dateOfTermination+",dateOfEmployement:"+dateOfEmployement+",payRollComments:"+payRollComments+
    //    ",empSaving3:"+empSaving3+",empSaving4:"+empSaving4+",empSaving5:"+empSaving5+",expectedYearlyCost:"+expectedYearlyCost+",lockAmtStartDate:"+lockAmtStartDate+",isSixMonthsLock:"+isSixMonthsLock+",orgId:"+orgId+",payRollId:"+payRollId+",empId:"+empId+",isFixedSalary:"+isFixedSalary+",noSalary:"+noSalary;

    // alert("total match value-->"+totalCostMatchedValue);
    //alert("totalCost value-->"+totalCost);  
 
if(updateFlag=="payrollDetails" || updateFlag==''){
    if(bankName == 'All')
    {
      //  alert("please select bank");
          x0p( '','please select bank','info');
        return false;
    }
    else
    if(paymentType == 'All')
    {
      //  alert("please select payment type");
           x0p( '','please select payment type','info');
        return false;
    }
    if(basic == "" || basic == 0.0 || basic == null ){
      //  alert("please fill the basic field");
          x0p( '','please fill the basic field','info');
        return false;
    }  
    if(da == "" || da == 0.0 || da == null ){
    //    alert("please fill the da field");
          x0p( '','please fill the da field','info');
        return false;
    }
    if(hra == "" || hra == 0.0 || hra == null ){
    //    alert("please fill the hra field");
          x0p( '','please fill the hra field','info');
        return false;
    }
}

//    if(ta == "" || ta == 0 || ta == null ){
//        alert("please fill the ta field");
//        return false;
//    }
    
    
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerText(req, populatePayrollAddDetails);
        var url = CONTENXT_PATH+"/payrollAddDetails.action?resultString="+json;

        req.open("GET",url,"true");    
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    
}


function populatePayrollAddDetails(res)
{
    //alert(res);
    // alert(res.split("|")[1]);
    if(res.split("|")[1] == "insert"){
        document.getElementById("payrollResultMessage").innerHTML = "PayRoll added successfully"   ;
     
        document.getElementById("employerId").value=res.split("|")[2];
        document.getElementById("payRollId").value=res.split("|")[2];
        document.getElementById("tdsId").value=res.split("|")[3];
        
        setSaveOrUpdateButton();
    }
    if(res.split("|")[1] == "update"){
        document.getElementById("payrollResultMessage").innerHTML = "PayRoll updated successfully"   ;
     
        document.getElementById("employerId").value=res.split("|")[2];
        document.getElementById("payRollId").value=res.split("|")[2];
        setSaveOrUpdateButton();
    }
    
    
}

function updateEmployeeDetailsForPayroll(){
    var firstName =     document.getElementById("firstName").value;
    if(firstName==null||firstName==""){
        firstName == "nun";
    }
    var lastName = document.getElementById("lastName").value;
    if(lastName==null||lastName==""){
        lastName == "nun";
    }
    var middleName = document.getElementById("middleName").value;
    if(middleName==null||middleName==""){
        middleName == "nun";
    }
    var departmentId = document.getElementById("departmentId").value;
 
    var titleId = document.getElementById("titleId").value;
    var shiftId = document.getElementById("shiftId").value;
    var classificationId = document.getElementById("classificationId").value;
    var locationId = document.getElementById("locationId").value;
   // var regionId = document.getElementById("regionId").value;
    var gender = document.getElementById("gender").value;
    var currStatus = document.getElementById("currStatus").value;
    var isPfFlag = document.getElementById("isPfFlag").value;
    var employerId = document.getElementById("employerId").value;
    // alert(employerId.length)
    if(employerId==null||employerId==""||employerId.length==0){
        //  alert(employerId)
        employerId = "nun";
    }
    //alert(employerId)
    var ssn = document.getElementById("ssn").value;
    if(ssn==null||ssn==""){
        ssn = "nun";
    }
    var passportNo = document.getElementById("passportNo").value;
    if(passportNo==null||passportNo==""){
        passportNo = "nun";
    }
    var pfAccount = document.getElementById("pfAccount").value;
    if(pfAccount==null||pfAccount==""){
        pfAccount = "nun";
    }
    var birthDate = document.getElementById("birthDate").value;
    if(birthDate==null||birthDate==""){
        birthDate = "nun";
    }
    var trainingPeriod = document.getElementById("trainingPeriod").value;
    if(trainingPeriod==null||trainingPeriod==""){
        trainingPeriod = "nun";
    }
    var contractPeriod = document.getElementById("contractPeriod").value;
    if(contractPeriod==null||contractPeriod==""){
        contractPeriod ="nun";
    }
    var dateOfJoining = document.getElementById("dateOfJoining").value;
    if(dateOfJoining==null||dateOfJoining==""){
        dateOfJoining = "nun";
    }
    var dateOfEmployeement = document.getElementById("dateOfEmployeement").value;
    if(dateOfEmployeement==null||dateOfEmployeement==""){
        dateOfEmployeement = "nun";
    }
    var trueBirthday = document.getElementById("trueBirthday").value;
    if(trueBirthday==null||trueBirthday==""){
        trueBirthday = "nun";
    }
    var dateOfterminating = document.getElementById("dateOfterminating").value;
    if(dateOfterminating==null||dateOfterminating==""){
        dateOfterminating = "nun";
    }
    var weddingDay = document.getElementById("weddingDay").value;
    if(weddingDay==null||weddingDay==""){
        weddingDay = "nun";
    }
    var UANNo = document.getElementById("UANNo").value;
    if(UANNo==null||UANNo==""){
        UANNo = "nun";
    }
    var adharNo = document.getElementById("adharNo").value;
    if(adharNo==null||adharNo==""){
        adharNo = "nun";
    }
    var itgBatch = document.getElementById("itgBatch").value;
    if(itgBatch==null||itgBatch==""){
        itgBatch = "nun";
    }
    var resonsForLeaving = document.getElementById("resonsForLeaving").value;
    if(resonsForLeaving==null||resonsForLeaving==""){
        resonsForLeaving = "nun";
    }
    var empId = document.getElementById("empId").value;
    var homeAddressId = document.getElementById("homeAddressId").value;
    if(homeAddressId==null||homeAddressId==""){
        homeAddressId = "nun";
    }
//    var resultString="firstName:"+firstName+",lastName:"+lastName+",middleName:"+middleName+",departmentId:"+departmentId+",titleId:"+titleId+",shiftId:"+shiftId+","+
//     
//    "classificationId:"+classificationId+",locationId:"+locationId+",regionId:"+regionId+",gender:"+gender+",currStatus:"+currStatus+",isPfFlag:"+isPfFlag+","+
//     
//    "employerId:"+employerId+",ssn:"+ssn+",passportNo:"+passportNo+",pfAccount:"+pfAccount+",birthDate:"+birthDate+",trainingPeriod:"+trainingPeriod+","+
// 
//    "contractPeriod:"+contractPeriod+",dateOfJoining:"+dateOfJoining+",dateOfEmployeement:"+dateOfEmployeement+",trueBirthday:"+trueBirthday+",dateOfterminating:"+dateOfterminating+",weddingDay:"+weddingDay+","+
//    "UANNo:"+UANNo+",adharNo:"+adharNo+",itgBatch:"+itgBatch+",resonsForLeaving:"+resonsForLeaving+",empId:"+empId;

    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, populateUpdatePayrollEmployeeDetails);
    var url = CONTENXT_PATH+"/updatePayrollEmployeeDetails.action?resultString="+resultString;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}



function populateUpdatePayrollEmployeeDetails(res)
{
   // alert("success");
    x0p( '','success','info');
    
    
}

function updatePayrollDetailsForPayroll(){
    
    var basic = document.getElementById("basic").value;
    var paymentType = document.getElementById("paymentType").value;
    var da = document.getElementById("da").value;
    var bankAccountNo = document.getElementById("bankAccountNo").value;
    if(bankAccountNo==null||bankAccountNo==""){
        bankAccountNo = "nun";
    }
    var hra = document.getElementById("hra").value;
    var bankName = document.getElementById("bankName").value;
    var ta = document.getElementById("ta").value;
    var employerPf = document.getElementById("employerPf").value;
    var ra = document.getElementById("ra").value;
    var employeePf = document.getElementById("employeePf").value;
    var entertainment = document.getElementById("entertainment").value;
    var life = document.getElementById("life").value;
    var kidsEducation = document.getElementById("kidsEducation").value;
    var health = document.getElementById("health").value;
    var vehicleAllowance = document.getElementById("vehicleAllowance").value;
    var professionalTax = document.getElementById("professionalTax").value;
    var cca = document.getElementById("cca").value;
    var otherDeductions = document.getElementById("otherDeductions").value;
    var miscPay = document.getElementById("miscPay").value;
    var splAllowance = document.getElementById("splAllowance").value;
    var longTermBonus = document.getElementById("longTermBonus").value;
    var grossPay = document.getElementById("grossPay").value;
    var projectPay = document.getElementById("projectPay").value;
    var variablePay = document.getElementById("variablePay").value;
    var attendanceAllow = document.getElementById("attendanceAllow").value;
    var totalCost = document.getElementById("totalCost").value;
    var onProjectInd = document.getElementById("onProjectInd").value;
    var onProjectIndValue1 = document.getElementById("onProjectIndValue1").value;
    var onProjectIndValue2 = document.getElementById("onProjectIndValue2").value;
    var datePayRevised = document.getElementById("datePayRevised").value;
    if(datePayRevised==null||datePayRevised==""){
        datePayRevised = "nun";
    }
    var onsiteInd = document.getElementById("onsiteInd").value;
    var onsiteIndV = document.getElementById("onsiteIndV").value;
    var empId = document.getElementById("empId").value;
    var homeAddressId = document.getElementById("homeAddressId").value;
    if(homeAddressId==null||homeAddressId==""){
        homeAddressId = "nun";
    } 
    
    var resultString ="basic:"+basic+",paymentType:"+paymentType+","+
    "da:"+da+",bankAccountNo:"+bankAccountNo+",hra:"+hra+",bankName:"+bankName+",ta:"+ta+",employerPf:"+employerPf+","+
    "ra:"+ra+",employeePf:"+employeePf+",entertainment:"+entertainment+",life:"+life+",kidsEducation:"+kidsEducation+",health:"+health+","+
    "vehicleAllowance:"+vehicleAllowance+",professionalTax:"+professionalTax+",cca:"+cca+",otherDeductions:"+otherDeductions+",miscPay:"+miscPay+",splAllowance:"+splAllowance+","+
    "longTermBonus:"+longTermBonus+",grossPay:"+grossPay+",projectPay:"+projectPay+",variablePay:"+variablePay+",attendanceAllow:"+attendanceAllow+",totalCost:"+totalCost+","+
    "onProjectInd:"+onProjectInd+",onProjectIndValue1:"+onProjectIndValue1+",onProjectIndValue2:"+onProjectIndValue2+",datePayRevised:"+datePayRevised+",onsiteInd:"+onsiteInd+",onsiteIndV:"+onsiteIndV+",empId:"+empId    ;
    
   
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, populateUpdatePayrollDetailsForPayroll);
    var url = CONTENXT_PATH+"/updatePayrollDetailsForPayroll.action?resultString="+resultString;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
   
}

function populateUpdatePayrollDetailsForPayroll(res){
    
   // alert("success");
     x0p( '','success','info');
}

function updateInsuranceSavingsTab(){
    
    var prevYtdSalary = document.getElementById("prevYtdSalary").value;
    var empSaving1 = document.getElementById("empSaving1").value;
    var empSaving2 = document.getElementById("empSaving2").value;
    var lifeInsureanceAmt = document.getElementById("lifeInsureanceAmt").value;
    if(lifeInsureanceAmt==null||lifeInsureanceAmt==""){
        lifeInsureanceAmt = "nun";
    }
    var lifeInsureanceTerm = document.getElementById("lifeInsureanceTerm").value;
    if(lifeInsureanceTerm==null||lifeInsureanceTerm==""){
        lifeInsureanceTerm = "nun";
    }
    var lifeInsureanceAnnual = document.getElementById("lifeInsureanceAnnual").value;
    if(lifeInsureanceAnnual==null||lifeInsureanceAnnual==""){
        lifeInsureanceAnnual ="nun";
    }
    var lifeInsureancePolicy =  document.getElementById("lifeInsureancePolicy").value;
    if(lifeInsureancePolicy==null||lifeInsureancePolicy==""){
        lifeInsureancePolicy = "nun";
    }
    var healthInsuranceAnnual = document.getElementById("healthInsuranceAnnual").value;
    if(healthInsuranceAnnual==null||healthInsuranceAnnual==""){
        healthInsuranceAnnual = "nun";
    }
    var healthInsuranceAmt = document.getElementById("healthInsuranceAmt").value;
    if(healthInsuranceAmt==null||healthInsuranceAmt==""){
        healthInsuranceAmt = "nun";
    }
    var empId = document.getElementById("empId").value;
    var homeAddressId = document.getElementById("homeAddressId").value;
    if(homeAddressId==null||homeAddressId==""){
        homeAddressId = "nun";
    } 
    var resultString = "prevYtdSalary:"+prevYtdSalary+",empSaving1:"+empSaving1+",empSaving2:"+empSaving2+",lifeInsureanceAmt:"+lifeInsureanceAmt+",lifeInsureanceTerm:"+lifeInsureanceTerm+",lifeInsureanceAnnual:"+lifeInsureanceAnnual+","+
    "lifeInsureancePolicy:"+lifeInsureancePolicy+",healthInsuranceAnnual:"+healthInsuranceAnnual+",healthInsuranceAmt:"+healthInsuranceAmt+",empId:"+empId;

    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, populateUpdateInsuranceSavingsForPayroll);
    var url = CONTENXT_PATH+"/updateInsuranceSavingsForPayroll.action?resultString="+resultString;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);

}

function populateUpdateInsuranceSavingsForPayroll(res){
   // alert("success")
     x0p( '','success','info');
}


function updateOtherDetailsForPayroll(){
    
    var wagecomments = document.getElementById("wagecomments").value;
    if(wagecomments==null||wagecomments==""){
        wagecomments = "nun";
    }
    var wagecomments1 = document.getElementById("wagecomments1").value;
    if(wagecomments1==null||wagecomments1==""){
        wagecomments1 = "nun";
    }
    var generalcomments = document.getElementById("generalcomments").value;
    if(generalcomments==null||generalcomments==""){
        generalcomments = "nun";
    }
    var referencecomments = document.getElementById("referencecomments").value;
    if(referencecomments==null||referencecomments==""){
        referencecomments = "nun";
    }
    var empId = document.getElementById("empId").value;
    var resultString = "wagecomments:"+wagecomments+",wagecomments1:"+wagecomments1+",generalcomments:"+generalcomments+",referencecomments:"+referencecomments+",empId:"+empId;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, populateOtherDetailsForPayroll);
    var url = CONTENXT_PATH+"/updateOtherDetailsForPayroll.action?resultString="+resultString;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);

}

function populateOtherDetailsForPayroll(res){
  //  alert("success")
   x0p( '','success','info');
}


function updateContactDetailsForPayroll(){
    
    var address = document.getElementById("address").value;
    if(address==null||address==""){
        address ="nun";
    }
    var corporateEmail = document.getElementById("corporateEmail").value;
    if(corporateEmail==null||corporateEmail==""){
        corporateEmail = "nun";
    }
    var personalEmail = document.getElementById("personalEmail").value;
    if(personalEmail==null||personalEmail==""){
        personalEmail = "nun";
    }
    var fathername = document.getElementById("fathername").value;
    if(fathername==null||fathername==""){
        fathername = "nun";
    }
    var fatherTitle = document.getElementById("fatherTitle").value;
    if(fatherTitle==null||fatherTitle==""){
        fatherTitle ="nun";
    }
    var city = document.getElementById("city").value;
    if(city==null||city==""){
        city = "nun";
    }
    var state = document.getElementById("state").value;
    if(state==null||state==""){
        state = "nun";
    }
    var zip = document.getElementById("zip").value;
    if(zip==null||zip==""){
        zip = "nun";
    }
    var fatherPhone = document.getElementById("fatherPhone").value;
    if(fatherPhone==null||fatherPhone==""){
        fatherPhone ="nun";
    }
    var homePhone = document.getElementById("homePhone").value;
    if(homePhone==null||homePhone==""){
        homePhone = "nun";
    }
    var mobilePhone = document.getElementById("mobilePhone").value;
    if(mobilePhone==null||mobilePhone==""){
        mobilePhone = "nun";
    }
    var homeAddressId = document.getElementById("homeAddressId").value;
    if(homeAddressId==null||homeAddressId==""){
        homeAddressId = "nun";
    } 
    var empId = document.getElementById("empId").value;
    var resultString = "address:"+address+","+
    "corporateEmail:"+corporateEmail+",personalEmail:"+personalEmail+",fathername:"+fathername+",fatherTitle:"+fatherTitle+",city:"+city+",state:"+state+","+
    "zip:"+zip+",fatherPhone:"+fatherPhone+",homePhone:"+homePhone+",mobilePhone:"+mobilePhone+",homeAddressId:"+homeAddressId+",empId:"+empId;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, populateContactDetailsForPayroll);
    var url = CONTENXT_PATH+"/updateContactDetailsForPayroll.action?resultString="+resultString;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);

}

function populateContactDetailsForPayroll(res){
    
   // alert(res);
     x0p( '',res,'info');
}



function setSaveOrUpdateButton(){
    var empNum = document.getElementById("payrollAddOrUpdate").value;
    if(empNum=="" ||empNum=="null" ||empNum==null|| empNum ==0){
        document.getElementById("update00").style.display="none";
        document.getElementById("save00").style.display="block";
        document.getElementById("new00").style.display="block";
        document.getElementById("update11").style.display="none";
        document.getElementById("save11").style.display="block";
        document.getElementById("new11").style.display="block";
        document.getElementById("update22").style.display="none";
        document.getElementById("save22").style.display="block";
        document.getElementById("new22").style.display="block";
        document.getElementById("update33").style.display="none";
        document.getElementById("save33").style.display="block";
        document.getElementById("new33").style.display="block";
    /*   document.getElementById("update4").style.display="none";
        document.getElementById("save4").style.display="block";
        document.getElementById("new4").style.display="block";
         document.getElementById("update5").style.display="none";
        document.getElementById("save5").style.display="block";
        document.getElementById("new5").style.display="block";*/
    //      document.getElementById("calculatorButton").style.display="block";
    //     document.getElementById("calculatorDiv").style.display="block";
    //  document.getElementById("reset").style.display="block";
        
    }
    else
    {
        document.getElementById("update00").style.display="block";
        document.getElementById("save00").style.display="none";
        document.getElementById("new00").style.display="none";
        document.getElementById("update11").style.display="block";
        document.getElementById("save11").style.display="none";
        document.getElementById("new11").style.display="none";
        document.getElementById("update22").style.display="block";
        document.getElementById("save22").style.display="none";
        document.getElementById("new22").style.display="none";
        document.getElementById("update33").style.display="block";
        document.getElementById("save33").style.display="none";
        document.getElementById("new33").style.display="none";
        /*  document.getElementById("update4").style.display="block";
        document.getElementById("save4").style.display="none";
        document.getElementById("new4").style.display="none";
         document.getElementById("update5").style.display="block";
        document.getElementById("save5").style.display="none";
        document.getElementById("new5").style.display="none";*/
        //  document.getElementById("reset").style.display="none";
       // document.getElementById("orgId").disabled = true;
    //   document.getElementById("calculatorButton").style.display="none";
    //    document.getElementById("calculatorDiv").style.display="none";
    }
    var currStatus=document.getElementById("currStatus").value;
    if(currStatus=="Active"){
        
         document.getElementById("dateOfTerminationTD1").style.display="none";
         document.getElementById("dateOfTerminationTD2").style.display="none";
         document.getElementById("resonsForLeavingTr").style.display="none";
    }
}

function getEmployeesByDept(){
    //   var deptName = document.employeeForm.departmentId.value;
    var deptName = document.getElementById("departmentId").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerXml(req, populateEmployeesByDept);
    // var url = CONTENXT_PATH+"/getEmpForReportsTo.action?deptName="+deptName;
    var url = CONTENXT_PATH+"/getEmployeesByDept.action?departmentId="+deptName;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}

function populateEmployeesByDept(resXML) {
    //   var reportsTo = document.employeeForm.reportsTo;
    var empId = document.getElementById("empnameById");
    var team = resXML.getElementsByTagName("TEAM")[0];
    var users = team.getElementsByTagName("USER");
    empId.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("userId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        empId.appendChild(opt);
    }
}

function clearPayrollSearch(){
    document.getElementById("year").value="";
    document.getElementById("empNo").value="";
    document.getElementById("isPfFlag").checked=false;
    document.getElementById("onProjectInd").checked=false;
}

function toggleRunPayrollReportOverlay(){
    document.getElementById("resultMessageForFreeze").innerHTML = "";
   // document.getElementById("resultMessage").innerHTML = "";
    
    var overlay = document.getElementById('payrollReportOverlay');
    var specialBox = document.getElementById('hubblePayrollReportOverlay');
        
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        window.location="getPayRollReports.action";
        overlay.style.display = "none";
        specialBox.style.display = "none";
                   
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }   
}

function doRunWagesScript(){
    (document.getElementById("loadingMessageForFreeze")).style.display = "none";
    document.getElementById("resultMessage").innerHTML ="";
    var year=document.getElementById("yearOverlay").value;
 
    var month=document.getElementById("monthOverlay").value;   
    
    var noOfDays=document.getElementById("noOfDays").value;
    var noOfWeekedDays=document.getElementById("noOfWeekendDays").value;
    //var noOfHolidays=document.getElementById("noOfHolidays").value;
    var noOfWorkingDays=document.getElementById("noOfWorkingDays").value;
    var paymentDateEmp=document.getElementById('paymentDateEmp').value;
    var orgId = document.getElementById('orgId').value;
    
     if (isNaN(year) || year.length!=4){
               document.getElementById("resultMessage").innerHTML="<font color='red' size='2'>Please Enter valid Year!!</font>"; 
                return false; 
          }
        if(year == null || year == "" || year == 0){
            document.getElementById("resultMessage").innerHTML="<font color='red' size='2'>Please Enter Year!!!</font>"; 
                return false; 
        }
        if(month == null || month == "" || month == 0){
            document.getElementById("resultMessage").innerHTML="<font color='red' size='2'>Please select Month!!!</font>"; 
                return false; 
        }
     if(orgId.trim()==""){
            document.getElementById("resultMessage").innerHTML="<font color='red' size='2'>Please select an organization!</font>";
            return false;
      }
        if(paymentDateEmp == ""){
          document.getElementById("resultMessage").innerHTML="<font color='red' size='2'>Please Enter PaymentDate!</font>";
            return false;
      }
       if(noOfDays == ""){
          document.getElementById("resultMessage").innerHTML="<font color='red' size='2'>Please Enter NoOfDays!!</font>";
            return false;
      }
       if(noOfWeekedDays == ""){
          document.getElementById("resultMessage").innerHTML="<font color='red' size='2'>Please Enter NoOfWeekedDays!!</font>";
            return false;
      }
       if(noOfWorkingDays == ""){
          document.getElementById("resultMessage").innerHTML="<font color='red' size='2'>Please Enter NoOfWorkingDays!!</font>";
            return false;
      }
     
       var paymentDate = new Date(paymentDateEmp);
      var payMonth = paymentDate.getMonth();
      var payYear = paymentDate.getFullYear();
      
      
     if(month == 12){
         var newYear =  parseInt(year)+1;
         if(newYear != payYear || parseInt(payMonth)+1 != 1){
             document.getElementById("resultMessage").innerHTML="<font color='red' size='2'>PaymentDate's year must be a year greaterthan Wagerun year!</font>"; 
     return false;    
    }
	}
     else{
   
    
      if((payMonth != (month))){
           document.getElementById("resultMessage").innerHTML="<font color='red' size='2'>PaymentDate's month must be a month greaterthan Wagerun month!</font>";
            return false;
      }
     }

    transperent();
      window.location="doRunWages.action?year="+year+"&month="+month+"&noOfDays="+noOfDays+"&noOfWeekendDays="+noOfWeekedDays+"&noOfWorkingDays="+noOfWorkingDays+"&paymentDate="+paymentDateEmp+"&orgId="+orgId;
//         $.ajax({
//        url:CONTENXT_PATH+"/doRunWages.action?year="+year+"&month="+month+"&noOfDays="+noOfDays+"&noOfWeekedDays="+noOfWeekedDays+"&workingDays="+noOfWorkingDays+"&paymentDateEmp="+paymentDateEmp+"&orgId="+orgId,//
//        context: document.body,
//        timeout: 150000,
//          async: false,
//        success: function(responseText) {
//            
// 
//    document.getElementById("resultMessage").innerHTML = responseText;
//    transperent(); 
//                
//        }, 
//        error: function(e){
//            document.getElementById("resultMessage").innerHTML = "<font color='red' size='2'>Please try again later</font>";
//          //  document.getElementById("loadTAxAdd").style.display = 'none';
//         // alert(e.text);
//         
//          
//        }
//    });	
    
//    var req = newXMLHttpRequest();
//    req.onreadystatechange = readyStateHandlerText(req, populateRunWagesAck);
//    var url = CONTENXT_PATH+"/doRunWages.action?year="+year+"&month="+month+"&noOfDays="+noOfDays+"&noOfWeekedDays="+noOfWeekedDays+"&workingDays="+noOfWorkingDays+"&paymentDateEmp="+paymentDateEmp+"&orgId="+orgId;
//    req.open("GET",url,"true");    
//    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
//    req.send(null);
//document.getElementById("wagesOverlay").submit();  doRunWages
     
}



function populateRunWagesAck(res){
    // document.getElementById("resultMessage").style.dispay="block";
    document.getElementById("resultMessage").innerHTML = res;
    transperent();
    
}

function getPaySlip(){
    (document.getElementById("payslipResultMessage")).style.display = "none";
   
    var empnameById=document.getElementById("userId").value;
    var empNo=document.getElementById("empNo").value;
    var year=document.getElementById("year").value; 
    var month=document.getElementById("month").value;
   
    if (isNaN(year) || year.length!=4){
        x0p( '','Please enter valid Year!!!','info');
             // alert("Please Enter valid Year!!"); 
                return false; 
          }
      if (isNaN(empNo)){
               x0p('','Please enter valid EmpNo!!','info'); 
                return false; 
          } 
    
    else if(empnameById.length==0 && empNo.length==0 ) {
 
        x0p('Please select Empname or empNo!','','info');
        return false;
    
    }
//    if(departmentId.length==0) {
//  
//        alert("Please select Department!");
//        return false;
//    
//    }
     
   else if(month=="0") {
  
       x0p('','Please select month','info');
        return false;
    
    }
  
    else{
        var passwordForPdf = "";
        x0p('', 'Please give the password encryption for your payslip if you want?', 'input',
    function(button, passwordForPdf) {
        if(button == 'info') {
            // alert(password)
            x0p('Your password is ' + passwordForPdf, 
                '', 
                'ok', false);
           
        }
        if(button == 'cancel') {
            x0p('No Password', 
                '',
                'error', false); 
             passwordForPdf = ""; 
        }
        
           window.location="runPayslip.action?empnameById="+empnameById+"&year="+year+"&month="+month+"&passwordForPdf="+passwordForPdf+"&empNo="+empNo;
    });
   
//       var password = prompt('Please give the password encryption for your payslip if you want?','');
//        var passwordForPdf="";
//        if(password!=null)
//        {
//            passwordForPdf = password;
//        }else
//        {
//            passwordForPdf = " "; 
//        }
//   
  
     
   
 }
    
}


function getPayrollReport() {
    (document.getElementById("loadingMessageForFreeze")).style.display = "none";
    
    var empnameById = document.getElementById('empLoginId').value;
    var empNo = document.getElementById('payrollEmpNo').value;
    var year = document.getElementById('payrollYear').value;
    var month = document.getElementById('payrollMonth').value;
   
    if(month=="0") {
         x0p( '','Please select month','info');
      //  alert("Please select month");
        return false;
   }
    else if (isNaN(year) || year.length!=4){
         x0p( '','Please enter valid Year!!!','info');
     //   alert("Please Enter valid Year!!"); 
        return false; 
   }
    else if(year.length==0) {
         x0p( '','Please select Year!!!','info');
       // alert("Please select year");
        return false;
   }
   else if(empNo.length ==0 && empnameById.length==0){
        x0p( '','Please enter EmpNo or select EmployeeName!!','info');
       //  alert("Please enter EmpNo or select EmployeeName!");
        return false;
   }
   else{
       window.location = "generatePayrollReport.action?empnameById="+empnameById+"&year="+year+"&month="+month+'&empNo='+empNo;
   }
}
            
            
function setFreezeForWages(){
    (document.getElementById("freezeloadingMessage")).style.display = "none";
   
       
        var empnameById = "nun";
        var empNo = "nun";
        var orgId = 0;
      var year=document.getElementById("freezeyearOverlay").value;
        var month=document.getElementById("freezemonthOverlay").value;
        
          if (isNaN(year) || year.length != 4 ){
             // alert(year.length)
               document.getElementById("freezeresultMessage").innerHTML="<font color='red' size='2'>Please Enter valid Year!!</font>"; 
                return false; 
          }
           
        if(year == 0){
            document.getElementById("freezeresultMessage").innerHTML="<font color='red' size='2'>Please Enter Year!!!</font>"; 
                return false; 
        }
        if(month == null || month == "" || month == 0){
            document.getElementById("freezeresultMessage").innerHTML="<font color='red' size='2'>Please select Month!!!</font>"; 
                return false; 
        }
      
       
        if(document.getElementById("empNameByIdFreeze").value != null && document.getElementById("empNameByIdFreeze").value != "" ){
            empnameById=document.getElementById("empNameByIdFreeze").value;
         //   alert(empnameById)       
        }
      
        if(document.getElementById("freezeEmpNoOverlay").value != null && document.getElementById("freezeEmpNoOverlay").value != "" ){
            empNo=document.getElementById("freezeEmpNoOverlay").value;
           if (isNaN(empNo)){
                document.getElementById("freezeresultMessage").innerHTML="<font color='red' size='2'>Please enter valid EmpNo!!</font>"; 
                return false; 
          }
         //   alert(empNo)    
        }
        if(document.getElementById("freezeorgId").value != null && document.getElementById("freezeorgId").value != "" ){
            orgId=document.getElementById("freezeorgId").value;
          //  alert(orgId)    
        }
     
       
        
        var dt = new Date();
       // alert(dt)
        
         if(year > dt.getFullYear()){
           //  alert(year)
             document.getElementById("freezeresultMessage").innerHTML="<font color='red' size='2'>Year must be less than the current Year!</font>";
             return false;
    }
         if(month > (dt.getMonth())){
            document.getElementById("freezeresultMessage").innerHTML="<font color='red' size='2'>Month must be less than the current Month!</font>";
            return false;
        }
   
      //  document.getElementById("resultMessageForFreeze").innerHTML = "";
         //var result = confirm("Are you sure you want to freeze the records for the month selected?");
        
          var result = false;
  //  var result = confirm("Are you sure you want to un freeze the records for "+val+","+year+" ?");
      x0p('Are you sure you want to freeze the records for '+month+','+year+' ?', '', 'warning',
     function(button) {
       
        if(button == 'warning') {
            result = true;
            //alert(button)
        }
        if(button == 'cancel') { 
             result = false;
        }
   
   
        
    if(result)
    {
        
       
       
     
        (document.getElementById("freezeloadingMessage")).style.display = "block";
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerText(req, populateFreezeWagesAck);
        var url = CONTENXT_PATH+"/doFreezeWages.action?year="+year+"&month="+month+"&empNo="+empNo+"&empnameById="+empnameById+"&orgId="+orgId;
        req.open("GET",url,"true");    
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
        return true;
    }
    else{
        return false;
    }
     });
}

function populateFreezeWagesAck(res){
    (document.getElementById("freezeloadingMessage")).style.display = "none";
    document.getElementById("freezeresultMessage").innerHTML = res;
    
}


function ifFreezedShowUpdate(){
    if(document.getElementById("freezePayroll").checked){
        document.getElementById("updateAllForEditEmpWages1").style.display="none";
        document.getElementById("updateAllForEditEmpWages2").style.display="none";
        document.getElementById("updateAllForEditEmpWages3").style.display="none";
        document.getElementById("updateAllForEditEmpWages4").style.display="none";
        document.getElementById("updateAllForEditEmpWages5").style.display="none";
        document.getElementById("updateForyearAndDateTab").style.display="block";
    }
    else
    {
        document.getElementById("updateAllForEditEmpWages1").style.display="block";
        document.getElementById("updateAllForEditEmpWages2").style.display="block";
        document.getElementById("updateAllForEditEmpWages3").style.display="block";
        document.getElementById("updateAllForEditEmpWages4").style.display="block";
        document.getElementById("updateAllForEditEmpWages5").style.display="block";
        document.getElementById("updateForyearAndDateTab").style.display="none";
    //alert("unchecked");
    }
    
}

function updateYearAndDate(){
    var wageId =  document.getElementById("wageId").value;
    var payRollId =  document.getElementById("payRollId").value;
    var ytdGross =  document.getElementById("ytdGross").value;
    var ytdTaxableSalary =  document.getElementById("ytdTaxableSalary").value;
    var ytdLongterm =  document.getElementById("ytdLongterm").value;
    var ytdTaxableCommission =  document.getElementById("ytdTaxableCommission").value;
    var ytdPf =  document.getElementById("ytdPf").value;
    var ytdTDSonSalary =  document.getElementById("ytdTDSonSalary").value;
    var ytdProffTax =  document.getElementById("ytdProffTax").value;
    var ytdTDSOnCommm =  document.getElementById("ytdTDSOnCommm").value;
    var ytdLifeInsurance =  document.getElementById("ytdLifeInsurance").value;
    var ytdTDSCollected =  document.getElementById("ytdTDSCollected").value;
    var ytdTa =  document.getElementById("ytdTa").value;
    var ytdTDSLiabilitiesSalary =  document.getElementById("ytdTDSLiabilitiesSalary").value;
    var ytdRa =  document.getElementById("ytdRa").value;
    var ytdTDSLiabilitiesBonus =  document.getElementById("ytdTDSLiabilitiesBonus").value;
    var ytdOthersMisc =  document.getElementById("ytdOthersMisc").value;
    var diffTDSLiabilitiesSalary =  document.getElementById("diffTDSLiabilitiesSalary").value;
    var ytdExpTaxFree =  document.getElementById("ytdExpTaxFree").value;
    var diffTDSLiabilitiesBonus =  document.getElementById("diffTDSLiabilitiesBonus").value;
    var ytdProjectPay =  document.getElementById("ytdProjectPay").value;
    var ytdSavings1Reported =  document.getElementById("ytdSavings1Reported").value;
    var ytdSavings2Reported =  document.getElementById("ytdSavings2Reported").value;
           
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, populateUpdateStatusForYearAndDate);
    var url = CONTENXT_PATH+"/updateYearAndDate.action?wageId="+wageId+"&payRollId="+payRollId+"&ytdGross="+ytdGross
    +"&ytdTaxableSalary="+ytdTaxableSalary+"&ytdLongterm="+ytdLongterm+"&ytdTaxableCommission="+ytdTaxableCommission+"&ytdPf="+ytdPf
    +"&ytdTDSonSalary="+ytdTDSonSalary+"&ytdProffTax="+ytdProffTax+"&ytdTDSOnCommm="+ytdTDSOnCommm+"&ytdLifeInsurance="+ytdLifeInsurance+"&ytdTDSCollected="+ytdTDSCollected
    +"&ytdTa="+ytdTa+"&ytdTDSLiabilitiesSalary="+ytdTDSLiabilitiesSalary+"&ytdRa="+ytdRa+"&ytdTDSLiabilitiesBonus="+ytdTDSLiabilitiesBonus
    +"&ytdOthersMisc="+ytdOthersMisc+"&diffTDSLiabilitiesSalary="+diffTDSLiabilitiesSalary+"&ytdExpTaxFree="+ytdExpTaxFree+"&diffTDSLiabilitiesBonus="+diffTDSLiabilitiesBonus
    +"&ytdProjectPay="+ytdProjectPay+"&ytdSavings1Reported="+ytdSavings1Reported+"&ytdSavings2Reported="+ytdSavings2Reported;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    
    
}

function populateUpdateStatusForYearAndDate(res){
    
    document.getElementById("wagesResultMessage").innerHTML = res;

}


function freezePayroll(wageId,payrollId,freezePayrollVal){
    
    var Wag_Id=wageId;
    var PayRollId=payrollId;
    var Freeze_Payroll=freezePayrollVal;
   
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, populatefreezeWageAck);
    var url = CONTENXT_PATH+"/freezeEmpWageDetails.action?wageId="+Wag_Id+"&payRollId="+PayRollId+"&freezePayroll="+Freeze_Payroll;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populatefreezeWageAck(res){
    //  alert(res);
    var departmentId=document.getElementById("departmentId").value;
    var empnameById=document.getElementById("empnameById").value;
    var currStatus=document.getElementById("currStatus").value;
    var empNo=document.getElementById("empNo").value;
    var year=document.getElementById("year").value;
    var month=document.getElementById("month").value;
    var isPfFlag=document.getElementById("isPfFlag").checked;
    var onProjectInd=document.getElementById("onProjectInd").checked;
    window.location="searchWages.action?departmentId="+departmentId+"&empnameById="+empnameById+"&currStatus="+currStatus+"&empNo="+empNo+"&year="+year+"&month="+month+"&isPfFlag="+isPfFlag+"&onProjectInd="+onProjectInd+"&resultMessage="+res+"&freezeFlag=1";

}


function unfreezePayroll(wageId,payrollId,freezePayrollVal){
    var Wag_Id=wageId;
    var PayRollId=payrollId;
    var Freeze_Payroll=freezePayrollVal;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, populateUnfreezeWageAck);
    var url = CONTENXT_PATH+"/unfreezeEmpWageDetails.action?wageId="+Wag_Id+"&payRollId="+PayRollId+"&freezePayroll="+Freeze_Payroll;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    

}
function populateUnfreezeWageAck(res){
    // alert(res);
    var departmentId=document.getElementById("departmentId").value;
    var empnameById=document.getElementById("empnameById").value;
    var currStatus=document.getElementById("currStatus").value;
    var empNo=document.getElementById("empNo").value;
    var year=document.getElementById("year").value;
    var month=document.getElementById("month").value;
    var isPfFlag=document.getElementById("isPfFlag").checked;
    var onProjectInd=document.getElementById("onProjectInd").checked;
    window.location="searchWages.action?departmentId="+departmentId+"&empnameById="+empnameById+"&currStatus="+currStatus+"&empNo="+empNo+"&year="+year+"&month="+month+"&isPfFlag="+isPfFlag+"&onProjectInd="+onProjectInd+"&resultMessage="+res+"&freezeFlag=1";

}


function toggleOverlayForWagesRequirement(){
    document.getElementById("resultMessage").innerHTML = "";
    var overlay = document.getElementById('wagesGridOverlay');
    var specialBox = document.getElementById('wagesGridMainOverlay');
        
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        // alert("in block");
        overlay.style.display = "none";
        specialBox.style.display = "none";
        var departmentId=document.getElementById("departmentId").value;
        var empnameById=document.getElementById("empnameById").value;
        var currStatus=document.getElementById("currStatus").value;
        var empNo=document.getElementById("empNo").value;
        var year=document.getElementById("year").value;
        var month=document.getElementById("month").value;
        var isPfFlag=document.getElementById("isPfFlag").checked;
        var onProjectInd=document.getElementById("onProjectInd").checked;
    
    
        window.location="searchWages.action?departmentId="+departmentId+"&empnameById="+empnameById+"&currStatus="+currStatus+"&empNo="+empNo+"&year="+year+"&month="+month+"&isPfFlag="+isPfFlag+"&onProjectInd="+onProjectInd;
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }   
}

function setDetailsForWagesOverlay(Wag_Id,PayRollId,Freeze_Payroll){
    document.getElementById("resultMessage").innerHTML = "";
    if(Freeze_Payroll=="1"){
        document.getElementById("buttonsForUnfreeze").style.display="block";
        document.getElementById("buttonsForfreeze").style.display="none";
        
    }
    else if(Freeze_Payroll=="0"){
        document.getElementById("buttonsForUnfreeze").style.display="none";
        document.getElementById("buttonsForfreeze").style.display="block";
    }
    document.getElementById("wageIdforOverlay").value=Wag_Id;
    document.getElementById("payrollIdforOverlay").value=PayRollId;
    document.getElementById("freezePayrollforOverlay").value=Freeze_Payroll;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, populateDaysCountDetails);
    var url = CONTENXT_PATH+"/getDaysCountDetails.action?wageId="+Wag_Id+"&payRollId="+PayRollId;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
  
}

function populateDaysCountDetails(res){
    document.getElementById("yearOverlay").value = res.split("|")[6];
    document.getElementById("monthOverlay").value =res.split("|")[5] ;
    // document.getElementById("noOfDays").value =res.split("|")[2] ;
    // document.getElementById("noOfWeekendDays").value =res.split("|")[4] ;
    // document.getElementById("noOfHolidays").value =res.split("|")[3] ;
    // document.getElementById("daysVactaionFromHubble").value =res.split("|")[8] ;
    // document.getElementById("daysVactaionFromBiometric").value =res.split("|")[9] ;
    toggleOverlayForWagesRequirement();
    
}

function updateDaysCount(){
    document.getElementById("resultMessage").innerHTML = "";
    var Wag_Id =  document.getElementById("wageIdforOverlay").value;
    var PayRollId =   document.getElementById("payrollIdforOverlay").value;
    var noOfDays =  document.getElementById("noOfDays").value ;
    var noOfWeekedDays = document.getElementById("noOfWeekendDays").value  ;
    var noOfHolidays = document.getElementById("noOfHolidays").value  ;
    var leavesCount =  document.getElementById("daysVactaionFromHubble").value  ;
    var daysVactaionFromBiometric =  document.getElementById("daysVactaionFromBiometric").value  ;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, populateDaysCountUpdateAck);
    var url = CONTENXT_PATH+"/updateDaysCount.action?wageId="+Wag_Id+"&payRollId="+PayRollId+"&noOfDays="+noOfDays+"&noOfWeekedDays="+noOfWeekedDays+"&noOfHolidays="+noOfHolidays+"&leavesCount="+leavesCount+"&daysVactaionFromBiometric="+daysVactaionFromBiometric;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateDaysCountUpdateAck(res){

    document.getElementById("resultMessage").innerHTML = res;
    
}


function updateAllEmpWageDetails(){
    // document.getElementById("resultMessage").innerHTML = " " ;
    document.getElementById("wagesResultMessage").innerHTML = " ";
    var   paymentType=  document.getElementById("paymentType").value;
    var payPeriodStartDate=  document.getElementById("payPeriodStartDate").value;
    var bankName=  document.getElementById("bankName").value;
    var daysInMonth=  document.getElementById("daysInMonth").value;
    var bankAccountNo=  document.getElementById("bankAccountNo").value;
    var daysWorked=  document.getElementById("daysWorked").value;
    var freezePayroll=  document.getElementById("freezePayroll").checked;
    var freezePayrollVal=0;
    if(freezePayroll){
        freezePayrollVal=1;
        
    }
    var isBlock=  document.getElementById("isBlock").checked;
    var isBlockVal=0;
    if(isBlock){
        isBlockVal=1;
        
    }
    var doRepaymentFlag=  document.getElementById("doRepaymentFlag").checked;
    var doRepaymentFlagValue=0;
    if(doRepaymentFlag){
        doRepaymentFlagValue=1;
    }
    var payrollDate=  document.getElementById("payrollDate").value;
    var payRunId=  document.getElementById("payRunId").value;
    var netPaid=  document.getElementById("netPaid").value;
    var employerId=  document.getElementById("employerId").value;
    var grossPay=  document.getElementById("grossPay").value;
    var dedEmpPf=  document.getElementById("dedEmpPf").value;
    var tds=  document.getElementById("tds").value;
    var dedProfessionalTax=  document.getElementById("dedProfessionalTax").value;
    var daysProject=  document.getElementById("daysProject").value;
    var dedIncomeTax=  document.getElementById("dedIncomeTax").value;
    var daysVacation=  document.getElementById("daysVacation").value;
    var dedCorporateLoan=  document.getElementById("dedCorporateLoan").value;
    var vactionsAvailable=  document.getElementById("vactionsAvailable").value;
    var dedLife=  document.getElementById("dedLife").value;
    var daysHolidays=  document.getElementById("daysHolidays").value;
    var dedHealth=  document.getElementById("dedHealth").value;
    var daysWeekends=  document.getElementById("daysWeekends").value;
    var dedOthers=  document.getElementById("dedOthers").value;
    //alert("dedOthers"+dedOthers);
    var basic  =  document.getElementById("basic").value;
    var maidServices =  document.getElementById("maidServices").value;
    var da =  document.getElementById("da").value;
    var entertainment =  document.getElementById("entertainment").value;
    var hra =  document.getElementById("hra").value;
    var kidsEducation =  document.getElementById("kidsEducation").value;
    var ta =  document.getElementById("ta").value;
    var vehicleAllowance =  document.getElementById("vehicleAllowance").value;
    var ra =  document.getElementById("ra").value;
    var longTermBonus =  document.getElementById("longTermBonus").value;
    var life =  document.getElementById("life").value;
    var employerPf =  document.getElementById("employerPf").value;
    var health =  document.getElementById("health").value;
    var splAllowance =  document.getElementById("splAllowance").value;
    var cca =  document.getElementById("cca").value;
    var attendanceAllow =  document.getElementById("attendanceAllow").value;
    var miscPay =  document.getElementById("miscPay").value;
    var projectPay =  document.getElementById("projectPay").value;
    var employeePfPayRollDetails =  document.getElementById("employeePfPayRollDetails").value;
    var classificationId =  document.getElementById("classificationId").value;
    var grossPayPayRollDetails =  document.getElementById("grossPayPayRollDetails").value;
    var variablePay =  document.getElementById("variablePay").value;
    var earnedBasic =  document.getElementById("earnedBasic").value;
    var earnedFood =  document.getElementById("earnedFood").value;
    var earnedDa =  document.getElementById("earnedDa").value;
    var earnedLaundary =  document.getElementById("earnedLaundary").value;
    var earnedHra =  document.getElementById("earnedHra").value;
    var earnedMaidServices =  document.getElementById("earnedMaidServices").value;
    var earnedTa =  document.getElementById("earnedTa").value;
    var earnedEntertainment =  document.getElementById("earnedEntertainment").value;
    var earnedRa =  document.getElementById("earnedRa").value;
    var earnedKidsEducation =  document.getElementById("earnedKidsEducation").value;
    var earnedLife =  document.getElementById("earnedLife").value;
    var earnedVehicleAllowance =  document.getElementById("earnedVehicleAllowance").value;
    var earnedHealth =  document.getElementById("earnedHealth").value;
    var earnedLongTermBonus =  document.getElementById("earnedLongTermBonus").value;
    var earnedCCa =  document.getElementById("earnedCCa").value;
    var earnedMiscPay =  document.getElementById("earnedMiscPay").value;
    var earnedProjectPay =  document.getElementById("earnedProjectPay").value;
    var earnedEmployerPf =  document.getElementById("earnedEmployerPf").value;
    var earnedattallowance =  document.getElementById("earnedattallowance").value;
    var earnedsplallowance =  document.getElementById("earnedsplallowance").value;
    var tdsDeduction =  document.getElementById("tdsDeduction").value;
    var employeePfActualDetails =  document.getElementById("employeePfActualDetails").value;
    var grossPayActualDetails =  document.getElementById("grossPayActualDetails").value;
    var bonusCommission =  document.getElementById("bonusCommission").value;
    var netPaidActualDetails =  document.getElementById("netPaidActualDetails").value;
    var otherDeductions =  document.getElementById("otherDeductions").value;
    var taxableIncome =  document.getElementById("taxableIncome").value;
    var otherAdditions =  document.getElementById("otherAdditions").value;

    
    var wageId =  document.getElementById("wageId").value;
    var payRollId =  document.getElementById("payRollId").value;
    var ytdGross =  document.getElementById("ytdGross").value;
    var ytdTaxableSalary =  document.getElementById("ytdTaxableSalary").value;
    var ytdLongterm =  document.getElementById("ytdLongterm").value;
    var ytdTaxableCommission =  document.getElementById("ytdTaxableCommission").value;
    var ytdPf =  document.getElementById("ytdPf").value;
    var ytdTDSonSalary =  document.getElementById("ytdTDSonSalary").value;
    var ytdProffTax =  document.getElementById("ytdProffTax").value;
    var ytdTDSOnCommm =  document.getElementById("ytdTDSOnCommm").value;
    var ytdLifeInsurance =  document.getElementById("ytdLifeInsurance").value;
    var ytdTDSCollected =  document.getElementById("ytdTDSCollected").value;
    var ytdTa =  document.getElementById("ytdTa").value;
    var ytdTDSLiabilitiesSalary =  document.getElementById("ytdTDSLiabilitiesSalary").value;
    var ytdRa =  document.getElementById("ytdRa").value;
    var ytdTDSLiabilitiesBonus =  document.getElementById("ytdTDSLiabilitiesBonus").value;
    var ytdOthersMisc =  document.getElementById("ytdOthersMisc").value;
    var diffTDSLiabilitiesSalary =  document.getElementById("diffTDSLiabilitiesSalary").value;
    var ytdExpTaxFree =  document.getElementById("ytdExpTaxFree").value;
    var diffTDSLiabilitiesBonus =  document.getElementById("diffTDSLiabilitiesBonus").value;
    var ytdProjectPay =  document.getElementById("ytdProjectPay").value;
    var ytdSavings1Reported =  document.getElementById("ytdSavings1Reported").value;
    var ytdSavings2Reported =  document.getElementById("ytdSavings2Reported").value;
    var projectEndDate = document.getElementById("projectEndDate").value;
    if(projectEndDate.length == 0){
        projectEndDate = '01/01/1951';
    }

    var repaymentComments =  document.getElementById("repaymentComments").value;
    if(repaymentComments.length==0){
        
        repaymentComments = "nun";
    }
    var repaymentGross =  document.getElementById("repaymentGross").value;
    var repaymentNet =  document.getElementById("repaymentNet").value;
    var repaymentVariablePay =  document.getElementById("repaymentVariablePay").value;
    var tdsId = document.getElementById("tdsId").value;
    
    var lifeInsurancePremium  =  document.getElementById("lifeInsurancePremium").value;
    var housingLoanRepay =  document.getElementById("housingLoanRepay").value;
    var nscTds =  document.getElementById("nscTds").value;
    var ppfContribution =  document.getElementById("ppfContribution").value;
    var tutionFee =  document.getElementById("tutionFee").value;
    var elss =  document.getElementById("elss").value;
    var postOfficeTerm =  document.getElementById("postOfficeTerm").value;
    var bankDepositTax =  document.getElementById("bankDepositTax").value;
    var othersTDS =  document.getElementById("othersTDS").value;
    var contributionToPf =  document.getElementById("contributionToPf").value;
    var npsEmployeeContr =  document.getElementById("npsEmployeeContr").value;
    var totalTds =  document.getElementById("totalTds").value;
    var totalTdsDeductable =  document.getElementById("totalTdsDeductable").value;
    var interstOnBorrowed =  document.getElementById("interstOnBorrowed").value;
    var interstOnBorrowedDeductable =  document.getElementById("interstOnBorrowedDeductable").value;
    var insuranceForParents =  document.getElementById("insuranceForParents").value;
    var insuranceForParentsDeduc =  document.getElementById("insuranceForParentsDeduc").value;
    var insuranceOthers =  document.getElementById("insuranceOthers").value;
    var insuranceOthersDeduc =  document.getElementById("insuranceOthersDeduc").value;
    var interstOnEdu =  document.getElementById("interstOnEdu").value;
    var expectedYearlyCost =  document.getElementById("expectedYearlyCost").value;
    var interstOnHrAssumptionsInv =  document.getElementById("interstOnHrAssumptionsInv").value;
    var interstOnHrAssumptions =  document.getElementById("interstOnHrAssumptions").value;
    var licFromSal = document.getElementById("licFromSal").value;
    var leavesApplied = document.getElementById("leavesApplied").value;
   var releasedDate = document.getElementById("releasedDate").value;
   alert(releasedDate);
    var empId =document.getElementById("empId").value;
    var newGrossPay = document.getElementById("newgrossPayActualDetails").value;
    
    var submyObj = {};
	   
	    submyObj["paymentType"] = paymentType;
	    submyObj["payPeriodStartDate"] = payPeriodStartDate;
	    submyObj["bankName"] = bankName;
	    submyObj["daysInMonth"] = daysInMonth;
	    submyObj["bankAccountNo"] = bankAccountNo;
	    submyObj["daysWorked"] = daysWorked;
	    submyObj["freezePayroll"] = freezePayrollVal+"";
	    submyObj["payrollDate"] = payrollDate;
	    submyObj["payRunId"] = payRunId;
	    submyObj["netPaid"] = netPaid;
	    submyObj["employerId"] = employerId;
	    submyObj["grossPay"] = grossPay;
	    submyObj["dedEmpPf"] = dedEmpPf;
	    submyObj["tds"] = tds;
	    submyObj["dedProfessionalTax"] = dedProfessionalTax;
	    submyObj["daysProject"] = daysProject;
	    submyObj["dedIncomeTax"] = dedIncomeTax;
	    submyObj["daysVacation"] = daysVacation;
	    submyObj["dedCorporateLoan"] = dedCorporateLoan;
	    submyObj["vactionsAvailable"] = vactionsAvailable;
	    submyObj["dedLife"] = dedLife;
	    submyObj["daysHolidays"] = daysHolidays;
	    submyObj["dedHealth"] = dedHealth;
	    submyObj["daysWeekends"] = daysWeekends;
	    submyObj["dedOthers"] = dedOthers;
	    submyObj["basic"] = basic;
	    submyObj["maidServices"] = maidServices;
	    submyObj["da"] = da;
	    submyObj["entertainment"] = entertainment;
	    submyObj["hra"] = hra;
	    submyObj["kidsEducation"] = kidsEducation;
	    submyObj["ta"] = ta;
	    submyObj["vehicleAllowance"] = vehicleAllowance;
	    submyObj["ra"] = ra;
	    submyObj["longTermBonus"] = longTermBonus;
	    submyObj["life"] = life;
	    submyObj["employerPf"] = employerPf;
	    submyObj["health"] = health;
	    submyObj["splAllowance"] = splAllowance;
	    submyObj["cca"] = cca;
	    submyObj["attendanceAllow"] = attendanceAllow;
	    submyObj["miscPay"] = miscPay;
	    submyObj["projectPay"] = projectPay;
	    submyObj["employeePfPayRollDetails"] = employeePfPayRollDetails;
	    submyObj["classificationId"] = classificationId;
	    submyObj["grossPayPayRollDetails"] = grossPayPayRollDetails;
	    submyObj["variablePay"] = variablePay;
	    submyObj["earnedBasic"] = earnedBasic;
	    submyObj["earnedFood"] = earnedFood;
	    submyObj["earnedDa"] = earnedDa;
	    submyObj["earnedLaundary"] = earnedLaundary;
	    submyObj["earnedHra"] = earnedHra;
	    submyObj["earnedMaidServices"] = earnedMaidServices;
	    submyObj["earnedTa"] = earnedTa;
	    submyObj["earnedEntertainment"] = earnedEntertainment;
	    submyObj["earnedRa"] = earnedRa;
	    submyObj["earnedKidsEducation"] = earnedKidsEducation;
	    submyObj["earnedLife"] = earnedLife;
	    submyObj["earnedVehicleAllowance"] = earnedVehicleAllowance;
	    submyObj["earnedHealth"] = earnedHealth;
	    submyObj["earnedLongTermBonus"] = earnedLongTermBonus;
	    submyObj["earnedCCa"] = earnedCCa;
	    submyObj["earnedMiscPay"] = earnedMiscPay;
	    submyObj["earnedProjectPay"] = earnedProjectPay;
	    submyObj["earnedEmployerPf"] = earnedEmployerPf;
	    submyObj["earnedattallowance"] = earnedattallowance;
	    submyObj["earnedsplallowance"] = earnedsplallowance;
	    submyObj["tdsDeduction"] = tdsDeduction;
	    submyObj["employeePfActualDetails"] = employeePfActualDetails;
	    submyObj["grossPayActualDetails"] = grossPayActualDetails;
	    submyObj["bonusCommission"] = bonusCommission;
	    submyObj["netPaidActualDetails"] = netPaidActualDetails;
	    submyObj["otherDeductions"] = otherDeductions;
	    submyObj["taxableIncome"] = taxableIncome;
	    submyObj["otherAdditions"] = otherAdditions;
	    submyObj["ytdGross"] = ytdGross;
	    submyObj["ytdTaxableSalary"] = ytdTaxableSalary;
	    submyObj["ytdLongterm"] = ytdLongterm;
	    submyObj["ytdTaxableCommission"] = ytdTaxableCommission;
	    submyObj["ytdPf"] = ytdPf;
	    submyObj["ytdTDSonSalary"] = ytdTDSonSalary;
	    submyObj["ytdProffTax"] = ytdProffTax;
	    submyObj["ytdTDSOnCommm"] = ytdTDSOnCommm;
	    submyObj["ytdLifeInsurance"] = ytdLifeInsurance;
	    submyObj["ytdTDSCollected"] = ytdTDSCollected;
	    submyObj["ytdTa"] = ytdTa;
	    submyObj["ytdTDSLiabilitiesSalary"] = ytdTDSLiabilitiesSalary;
	    submyObj["ytdRa"] = ytdRa;
	    submyObj["ytdTDSLiabilitiesBonus"] = ytdTDSLiabilitiesBonus;
	    submyObj["ytdOthersMisc"] = ytdOthersMisc;
	    submyObj["diffTDSLiabilitiesSalary"] = diffTDSLiabilitiesSalary;
	    submyObj["ytdExpTaxFree"] = ytdExpTaxFree;
	    submyObj["diffTDSLiabilitiesBonus"] = diffTDSLiabilitiesBonus;
	    submyObj["ytdProjectPay"] = ytdProjectPay;
	    submyObj["ytdSavings1Reported"] = ytdSavings1Reported;
	    submyObj["ytdSavings2Reported"] = ytdSavings2Reported;
	    submyObj["isBlockVal"] = isBlockVal+"";
	    submyObj["projectEndDate"] = projectEndDate;
	    submyObj["repaymentComments"] = repaymentComments;
	    submyObj["repaymentGross"] = repaymentGross;
	    submyObj["repaymentNet"] = repaymentNet;
	    submyObj["repaymentVariablePay"] = repaymentVariablePay;
	    submyObj["doRepaymentFlagValue"] = doRepaymentFlagValue+"";
	    submyObj["leavesApplied"] = leavesApplied;
	    submyObj["wageId"] = wageId;
	    submyObj["payRollId"] = payRollId;
	    submyObj["tdsId"] = tdsId;
	    submyObj["empId"] = empId;
	    submyObj["newGrossPay"] = newGrossPay;
	    submyObj["releasedDate"] = releasedDate;
             var json = JSON.stringify(submyObj);
             alert(json);
             console.log(json);
//    var resultString="paymentType:"+paymentType+",payPeriodStartDate:"+payPeriodStartDate+",bankName:"+bankName+",daysInMonth:"+daysInMonth+",bankAccountNo:"+bankAccountNo+",daysWorked:"+daysWorked+","+
//     
//    "freezePayroll:"+freezePayrollVal+",payrollDate:"+payrollDate+",payRunId:"+payRunId+",netPaid:"+netPaid+",employerId:"+employerId+",grossPay:"+grossPay+","+
//     
//    "dedEmpPf:"+dedEmpPf+",tds:"+tds+",dedProfessionalTax:"+dedProfessionalTax+",daysProject:"+daysProject+","+
//    "dedIncomeTax:"+dedIncomeTax+","+
//    "daysVacation:"+daysVacation+",dedCorporateLoan:"+dedCorporateLoan+","+
//    "vactionsAvailable:"+vactionsAvailable+",dedLife:"+dedLife+",daysHolidays:"+daysHolidays+",dedHealth:"+dedHealth+","+
//    "daysWeekends:"+daysWeekends+",dedOthers:"+dedOthers+",basic:"+basic+",maidServices:"+maidServices+",da:"+da+",entertainment:"+entertainment+","+
//    "hra:"+hra+",kidsEducation:"+kidsEducation+",ta:"+ta+",vehicleAllowance:"+vehicleAllowance+",ra:"+ra+",longTermBonus:"+longTermBonus+","+
//    "life:"+life+",employerPf:"+employerPf+",health:"+health+",splAllowance:"+splAllowance+",cca:"+cca+",attendanceAllow:"+attendanceAllow+","+
//    "miscPay:"+miscPay+",projectPay:"+projectPay+",employeePfPayRollDetails:"+employeePfPayRollDetails+",classificationId:"+classificationId+",grossPayPayRollDetails:"+grossPayPayRollDetails+",variablePay:"+variablePay+","+
//    "earnedBasic:"+earnedBasic+",earnedFood:"+earnedFood+",earnedDa:"+earnedDa+",earnedLaundary:"+earnedLaundary+",earnedHra:"+earnedHra+",earnedMaidServices:"+earnedMaidServices+","+
//    "earnedTa:"+earnedTa+",earnedEntertainment:"+earnedEntertainment+",earnedRa:"+earnedRa+",earnedKidsEducation:"+earnedKidsEducation+",earnedLife:"+earnedLife+",earnedVehicleAllowance:"+earnedVehicleAllowance+","+
//    "earnedHealth:"+earnedHealth+",earnedLongTermBonus:"+earnedLongTermBonus+",earnedCCa:"+earnedCCa+",earnedMiscPay:"+earnedMiscPay+",earnedProjectPay:"+earnedProjectPay+",earnedEmployerPf:"+earnedEmployerPf+","+
//    "earnedattallowance:"+earnedattallowance+",earnedsplallowance:"+earnedsplallowance+",tdsDeduction:"+tdsDeduction+",employeePfActualDetails:"+employeePfActualDetails+",grossPayActualDetails:"+grossPayActualDetails+",bonusCommission:"+bonusCommission+","+
//    "netPaidActualDetails:"+netPaidActualDetails+",otherDeductions:"+otherDeductions+",taxableIncome:"+taxableIncome+",otherAdditions:"+otherAdditions+
//    ",ytdGross:"+ytdGross+",ytdTaxableSalary:"+ytdTaxableSalary+",ytdLongterm:"+ytdLongterm+",ytdTaxableCommission:"+ytdTaxableCommission+",ytdPf:"+ytdPf+
//    ",ytdTDSonSalary:"+ytdTDSonSalary+",ytdProffTax:"+ytdProffTax+",ytdTDSOnCommm:"+ytdTDSOnCommm+",ytdLifeInsurance:"+ytdLifeInsurance+",ytdTDSCollected:"+ytdTDSCollected+
//    ",ytdTa:"+ytdTa+",ytdTDSLiabilitiesSalary:"+ytdTDSLiabilitiesSalary+",ytdRa:"+ytdRa+",ytdTDSLiabilitiesBonus:"+ytdTDSLiabilitiesBonus+",ytdOthersMisc:"+ytdOthersMisc+
//    ",diffTDSLiabilitiesSalary:"+diffTDSLiabilitiesSalary+",ytdExpTaxFree:"+ytdExpTaxFree+",diffTDSLiabilitiesBonus:"+diffTDSLiabilitiesBonus+",ytdProjectPay:"+ytdProjectPay
//    +",ytdSavings1Reported:"+ytdSavings1Reported+",ytdSavings2Reported:"+ytdSavings2Reported+",isBlock:"+isBlockVal+
//    ",projectEndDate:"+projectEndDate+",repaymentComments:"+repaymentComments+",repaymentGross:"+repaymentGross+
//    ",repaymentNet:"+repaymentNet+",repaymentVariablePay:"+repaymentVariablePay+",doRepaymentFlagValue:"+doRepaymentFlagValue+
//    /*",lifeInsurancePremium:"+lifeInsurancePremium+",housingLoanRepay:"+housingLoanRepay+",nscTds:"+nscTds+",ppfContribution:"+ppfContribution+",tutionFee:"+tutionFee+",elss:"+elss+",postOfficeTerm:"+postOfficeTerm+
//    ",bankDepositTax:"+bankDepositTax+",othersTDS:"+othersTDS+",contributionToPf:"+contributionToPf+",npsEmployeeContr:"+npsEmployeeContr+",totalTds:"+totalTds+",totalTdsDeductable:"+totalTdsDeductable+",interstOnBorrowed:"+interstOnBorrowed+
//    ",interstOnBorrowedDeductable:"+interstOnBorrowedDeductable+",insuranceForParents:"+insuranceForParents+",insuranceForParentsDeduc:"+insuranceForParentsDeduc+",insuranceOthers:"+insuranceOthers+",insuranceOthersDeduc:"+insuranceOthersDeduc+",interstOnEdu:"+interstOnEdu+",interstOnHrAssumptions:"+interstOnHrAssumptions+",interstOnHrAssumptionsInv:"+interstOnHrAssumptionsInv+",expectedYearlyCost:"+expectedYearlyCost+",licFromSal:"+licFromSal+*/
//    ",leavesApplied:"+leavesApplied+
//    ",wageId:"+wageId+",payRollId:"+payRollId+",tdsId:"+tdsId+",empId:"+empId+",newGrossPay:"+newGrossPay+",releasedDate:"+releasedDate;


    var today=new Date();
    var payrollDateCheck=new Date(payrollDate.split("/")[2],payrollDate.split("/")[0] , payrollDate.split("/")[1]) //Month is 0-11 in JavaScript
    var todayDate=new Date(today.getFullYear(),parseInt(today.getMonth())+1 , today.getDate()) //Month is 0-11 in JavaScript

    //Set 1 day in milliseconds
    var one_day=1000*60*60*24
 
    //Calculate difference btw the two dates, and convert to days
    var diffDays = Math.abs((payrollDateCheck.getTime()-todayDate.getTime())/(one_day));

 if(document.getElementById('isBlock').checked){
         var releasedDate = document.getElementById("releasedDate").value;
         if(releasedDate == "" || releasedDate == null){
             x0p('','ReleasedDate is Mandatory!!','info');
             return false;
         }
          
     }
    


    if(bankName == 'All')
    {
       // alert("please select bank");
        x0p( '','please select bank','info');
        return false;
    }
    else if(payPeriodStartDate.length==0){
      //  alert("please enter payPeriodStartDate");
        x0p( '','please enter payPeriodStartDate','info');
        return false;
    }
    else if(paymentType=='All'){
      //  alert("please select paymentType");
        x0p( '','please select paymentType','info');
        return false;
    }
   
    else if(payrollDate.length==0){
      //  alert("please enter payrollDate");
        x0p( '','please enter payrollDate','info');
        return false;
    }
    else if(payRunId.length==0){
     //   alert("please enter payRunId");
        x0p( '','please enter payRunId','info');
        return false;
    }
    else if(bankAccountNo.length==0){
     //   alert("please enter bankAccountNo");
        x0p( '','please enter bankAccountNo','info');
        return false;
    }else if(doRepaymentFlag&&repaymentComments.trim().length==0){
     //   alert("please enter repayment comments");
        x0p( '','please enter repayment comments','info');
        return false;
    }
    
//    else  if(!freezePayroll && diffDays>=30){
//     //   alert("Limit to unfreeze the record exceeded 30 days");
//        x0p( '','Limit to unfreeze the record exceeded 30 days','info');
//        return false;
//    }  
    
    else{
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerText(req, populateUpdateAllEmpWagesAck);
      //  var url = CONTENXT_PATH+"/updateAllEmpWageDetails.action?resultString="+resultString;
        var url = CONTENXT_PATH+"/updateAllEmpWageDetails.action?resultString="+json;
        req.open("GET",url,"true");    
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);

    }
}

function populateUpdateAllEmpWagesAck(res){

    document.getElementById("wagesResultMessage").innerHTML=res;
   
}

function confirmForRunWagesHolidaysUpdation(){

 var result = false;
  //  var result = confirm("Are you sure you want to un freeze the records for "+val+","+year+" ?");
      x0p('Are you sure for the number of holidays entered for the month ?', '', 'warning',
     function(button) {
       
        if(button == 'warning') {
            result = true;
            //alert(button)
        }
        if(button == 'cancel') { 
             result = false;
        }
        
   


   // var result = confirm("Are you sure for the number of holidays entered for the month ?");
        
    if(result)
    {
        var year=document.getElementById("yearOverlay").value;
 
        var month=document.getElementById("monthOverlay").value;   
        var dt = new Date();

        if(month == dt.getMonth()+1){
    
           // var result1 = confirm("Current month is not yet done are you sure to continue ?");
    var result1 = false;
     x0p('Current month is not yet done are you sure to continue ?', '', 'warning',
     function(button) {
       
        if(button == 'warning') {
            result1 = true;
            //alert(button)
        }
        if(button == 'cancel') { 
             result1 = false;
        }
            if(result1){
                doRunWagesScript();
                return true;
        
            }else
            {
                return false;
            }
     });
    
        }else{
        
            doRunWagesScript();
            return true;
        }
   
    }else
    {
        return false;
    }
     });
}


function getDaysForTheSelectedMonth(){
    
    var year=document.getElementById("yearOverlay").value;
    var month=document.getElementById("monthOverlay").value;

    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, populateDaysForTheSelectedMonth);
    var url = CONTENXT_PATH+"/doGetDaysFortheSelectedMonthRunWages.action?year="+year+"&month="+month;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populateDaysForTheSelectedMonth(res){

    document.getElementById("noOfDays").value=res.split("^")[0];
    document.getElementById("noOfWeekendDays").value=res.split("^")[1];
    document.getElementById("noOfWorkingDays").value=parseInt(res.split("^")[0])-parseInt(res.split("^")[1]);
}

function generateSampleReport() {
    var departmentId = document.getElementById('departmentId').value;
    var empnameById = document.getElementById('empnameById').value;
    var year = document.getElementById('year').value;
    var month = document.getElementById('month').value;
   
    window.location = "generateBiometricReport.action?departmentId="+departmentId+"&empnameById="+empnameById+"&year="+year+"&month="+month;
       
}
function setBankReportForWages(){

    document.getElementById("wagesGridMainOverlay").style.display="block";
    document.getElementById('wagesGridOverlay').style.display="block";

}     

function toggleBankReportForWagesRequirement(){
    var overlay = document.getElementById('bankReportsGridOverlay');
    var specialBox = document.getElementById('bankReportsGridOverlayMain');
        
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
         window.location="getPayRollReports.action";
        overlay.style.display = "none";
        specialBox.style.display = "none";
        
     
    } else {
        // alert("else block");
        overlay.style.display = "block";
        specialBox.style.display = "block";
      
    }   
    
}

function getBankReport(){
    var year = document.getElementById('yearOverlayForBiometricReportGeneration').value;
    var month = document.getElementById('monthForBiometricReportGeneration').value;
    var orgName=document.getElementById('orgIdForBank').value;
    var doRepayment=document.getElementById('doRepaymentFlag').checked;
    var doRepaymentFlag=0;
    var bankName=document.getElementById('bankName').value;
    
    if (isNaN(year) || year.length!=4) {
        x0p( '','Please enter valid Year!!!','info');
         // alert("please enter valid year");
        return false;
    }
    
   else if(year.length == 0)
    {
        x0p( '','Please enter year!!!','info');
    //    alert("please enter year");
        return false;
    }
    else if(month.length == 0)
    {
         x0p( '','Please select month!!!','info');
      //  alert("please select month");
        return false;
    }
    else if(orgName.length == 0){
         x0p( '','Please select organisation!!!','info');
      //  alert("please select organisation");
        return false;
    }
    else{
        if(doRepayment==true){
            doRepaymentFlag=1;
        }
        window.location = "generateBankReport.action?bankName="+bankName+"&orgName="+orgName+"&year="+year+"&month="+month+"&doRepaymentFlag="+doRepaymentFlag;
    }
    
}

function getTimeSheetDetails(){
  
    var year = document.getElementById('yearForTimeSheetReport').value;
    if(year.length==''){
     //   alert("Please Enter year");
         x0p( '','Please Enter year','info');
        return false;
    }
   
    var month = document.getElementById('monthForTimeSheetReport').value;
    if(month=='0'){
       // alert("Please Select Month");
         x0p( '','Please Select Month','info');
        return false;
    }
    var departmentId=document.getElementById('departmenttId').value;
    if(departmentId.length==''){
       // alert("Please Select Department");
         x0p( '','Please Select Department','info');
        return false;
    }
    var empnameById=document.getElementById('empnameByIdd').value;
  
    var country=document.getElementById('countryy').value;
 
    window.location ="generateTimeSheetReport.action?departmentId="+departmentId+"&empnameById="+empnameById+"&year="+year+"&month="+month+"&country="+country;  
    return true;
}

function getEmployeeByDept(){
    //   var deptName = document.employeeForm.departmentId.value;
   
    var deptName = document.getElementById("departmenttId").value;
    var req = newXMLHttpRequest();
    //  alert("haiii="+deptName);
    req.onreadystatechange = readyStateHandlerXml(req, populateEmployeeByDept);
    // var url = CONTENXT_PATH+"/getEmpForReportsTo.action?deptName="+deptName;
    var url = CONTENXT_PATH+"/getEmployeesByDept.action?departmentId="+deptName;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}

function populateEmployeeByDept(resXML) {
    //   var reportsTo = document.employeeForm.reportsTo;
    var empId = document.getElementById("empnameByIdd");
    var team = resXML.getElementsByTagName("TEAM")[0];
    var users = team.getElementsByTagName("USER");
    empId.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("userId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        empId.appendChild(opt);
    }
}

function fillNoOfDaysWorked(){
    //alert("in function");
    var noOfWorkingDays;
    var noOfHolidays = document.getElementById("noOfHolidays").value;
    var noOfDaysInMonth = document.getElementById("noOfDays").value;
    var noOfWeekendDays = document.getElementById("noOfWeekendDays").value;
    noOfWorkingDays = parseFloat(noOfDaysInMonth)-parseFloat(noOfHolidays)-parseFloat(noOfWeekendDays);
    document.getElementById("noOfWorkingDays").value= noOfWorkingDays;
}


function backupEmpPayRoll(){
    
    document.getElementById("payrollResultMessage").innerHTML = "";
    var firstName =     document.getElementById("firstName").value;
    if(firstName==null||firstName==""){
        firstName = "nun";
    }
    var lastName = document.getElementById("lastName").value;
    if(lastName==null||lastName==""){
        lastName = "nun";
    }
    var middleName = document.getElementById("middleName").value;
    if(middleName==null||middleName==""){
        middleName = "nun";
    }
    var departmentId = document.getElementById("departmentId").value;
 
    var titleId = document.getElementById("titleId").value;
    if(titleId==null||titleId==""){
     
        titleId = "nun";
    }
    var shiftId = document.getElementById("shiftId").value;
    var classificationId = document.getElementById("classificationId").value;
    var locationId = document.getElementById("locationId").value;
    if(locationId==null||locationId==""){
     
        locationId = "nun";
    }

    var regionId = document.getElementById("regionId").value;
    var gender = document.getElementById("gender").value;
    var currStatus = document.getElementById("currStatus").value;
 
 
    var isPfFlag = "";
    if(document.getElementById("isPfFlag").checked){
        isPfFlag="1";
    }
    else
    {
        isPfFlag="0";
    }
    var employerId = document.getElementById("employerId").value;
    // alert(employerId.length)
    if(employerId==null||employerId==""||employerId.length==0){
        //  alert(employerId)
        employerId = "nun";
    }
    //alert(employerId)
    var ssn = document.getElementById("ssn").value;
    if(ssn==null||ssn==""){
        ssn = "nun";
    }
    var passportNo = document.getElementById("passportNo").value;
    if(passportNo==null||passportNo==""){
        passportNo = "nun";
    }
    var pfAccount = document.getElementById("pfAccount").value;
    if(pfAccount==null||pfAccount==""){
        pfAccount = "nun";
    }

    var trainingPeriod = document.getElementById("trainingPeriod").value;
    if(trainingPeriod==null||trainingPeriod==""){
        trainingPeriod = "nun";
    }
    var contractPeriod = document.getElementById("contractPeriod").value;
    if(contractPeriod==null||contractPeriod==""){
        contractPeriod ="nun";
    }
    var dateOfJoining = document.getElementById("dateOfJoining").value;
    if(dateOfJoining==null||dateOfJoining==""){
        dateOfJoining = "nun";
    }
 
    var UANNo = document.getElementById("UANNo").value;
    if(UANNo==null||UANNo==""){
        UANNo = "nun";
    }
    var adharNo = document.getElementById("adharNo").value;
    if(adharNo==null||adharNo==""){
        adharNo = "nun";
    }

    var resonsForLeaving = document.getElementById("resonsForLeaving").value;
    if(resonsForLeaving==null||resonsForLeaving==""){
        resonsForLeaving = "nun";
    }
    var address = document.getElementById("address").value;
    if(address==null||address==""){
        address ="nun";
    }
    var corporateEmail = document.getElementById("corporateEmail").value;
    if(corporateEmail==null||corporateEmail==""){
        corporateEmail = "nun";
    }
    var personalEmail = document.getElementById("personalEmail").value;
    if(personalEmail==null||personalEmail==""){
        personalEmail = "nun";
    }
    var fathername = document.getElementById("fathername").value;
    if(fathername==null||fathername==""){
        fathername = "nun";
    }
    var fatherTitle = document.getElementById("fatherTitle").value;
    if(fatherTitle==null||fatherTitle==""){
        fatherTitle ="nun";
    }
    var city = document.getElementById("city").value;
    if(city==null||city==""){
        city = "nun";
    }
    var state = document.getElementById("state").value;
    if(state==null||state==""){
        state = "nun";
    }
    var zip = document.getElementById("zip").value;
    if(zip==null||zip==""){
        zip = "nun";
    }
    var fatherPhone = document.getElementById("fatherPhone").value;
    if(fatherPhone==null||fatherPhone==""){
        fatherPhone ="nun";
    }
    var homePhone = document.getElementById("homePhone").value;
    if(homePhone==null||homePhone==""){
        homePhone = "nun";
    }
    var mobilePhone = document.getElementById("mobilePhone").value;
    if(mobilePhone==null||mobilePhone==""){
        mobilePhone = "nun";
    }
    var basic = document.getElementById("basic").value;
    var paymentType = document.getElementById("paymentType").value;
    var da = document.getElementById("da").value;
    var bankAccountNo = document.getElementById("bankAccountNo").value;
    if(bankAccountNo==null||bankAccountNo.trim()==""){
       x0p('','Bank Account Number must not be Empty!!','info');
       return false;
    }
    var hra = document.getElementById("hra").value;
    var bankName = document.getElementById("bankName").value;
    var ta = document.getElementById("ta").value;
    var employerPf = document.getElementById("employerPf").value;
    var ra = document.getElementById("ra").value;
    var employeePf = document.getElementById("employeePf").value;
    var entertainment = document.getElementById("entertainment").value;
    var life = document.getElementById("life").value;
    var kidsEducation = document.getElementById("kidsEducation").value;
    var health = document.getElementById("health").value;
    var vehicleAllowance = document.getElementById("vehicleAllowance").value;
    var professionalTax = document.getElementById("professionalTax").value;
    var cca = document.getElementById("cca").value;
    var otherDeductions = document.getElementById("otherDeductions").value;
    var miscPay = document.getElementById("miscPay").value;
    var splAllowance = document.getElementById("splAllowance").value;
    var longTermBonus = document.getElementById("longTermBonus").value;
    var grossPay = document.getElementById("grossPay").value;
    var projectPay = document.getElementById("projectPay").value;
    var variablePay = document.getElementById("variablePay").value;
    var attendanceAllow = document.getElementById("attendanceAllow").value;
    var totalCost = document.getElementById("totalCost").value;
    var onProjectInd = "";
    if(document.getElementById("onProjectInd").checked)
    {
        onProjectInd = "1";
    }
    else{
        onProjectInd = "0";
    }
    var onProjectIndValue1 = document.getElementById("onProjectIndValue1").value;
    var onProjectIndValue2 = document.getElementById("onProjectIndValue2").value;
    var datePayRevised = document.getElementById("datePayRevised").value;
    if(datePayRevised==null||datePayRevised==""){
        datePayRevised = "nun";
    }
    var onsiteInd = "";
    if(document.getElementById("onsiteInd").checked){
        onsiteInd = "1";
    }
    else{
        onsiteInd = "0";
    }
    var onsiteIndV = document.getElementById("onsiteIndV").value;
    var prevYtdSalary = document.getElementById("prevYtdSalary").value;
    var empSaving1 = document.getElementById("empSaving1").value;
    var empSaving2 = document.getElementById("empSaving2").value;
    var tutionfees = document.getElementById("tutionfees").value;
    if(tutionfees==null||tutionfees==""){
        tutionfees = "nun";
    }
    var hbLoanInterst = document.getElementById("hbLoanInterst").value;
    if(hbLoanInterst==null||hbLoanInterst==""){
        hbLoanInterst = "nun";
    }
    var ppf = document.getElementById("ppf").value;
    if(ppf==null||ppf==""){
        ppf ="nun";
    }
    var medicalIns =  document.getElementById("medicalIns").value;
    if(medicalIns==null||medicalIns==""){
        medicalIns = "nun";
    }
    var lifeIns = document.getElementById("lifeIns").value;
    if(lifeIns==null||lifeIns==""){
        lifeIns = "nun";
    }
    var hraLifeInsuranceSavings = document.getElementById("hraLifeInsuranceSavings").value;
    if(hraLifeInsuranceSavings==null||hraLifeInsuranceSavings==""){
        hraLifeInsuranceSavings = "nun";
    }
    var premium = document.getElementById("premium").value;
    if(premium==null||premium==""){
        premium = "nun";
    }
    var eduInterest = document.getElementById("eduInterest").value;
    if(eduInterest==null||eduInterest==""){
        eduInterest = "nun";
    }
    var fd = document.getElementById("fd").value;
    if(fd==null||fd==""){
        fd ="nun";
    }
    var hbLoanPrinciple =  document.getElementById("hbLoanPrinciple").value;
    if(hbLoanPrinciple==null||hbLoanPrinciple==""){
        hbLoanPrinciple = "nun";
    }
    var mutualFunds = document.getElementById("mutualFunds").value;
    if(mutualFunds==null||mutualFunds==""){
        mutualFunds = "nun";
    }
    var nsc = document.getElementById("hraLifeInsuranceSavings").value;
    if(nsc==null||nsc==""){
        nsc = "nun";
    }
         
    /*   var lifeInsureanceAmt = document.getElementById("lifeInsureanceAmt").value;
    if(lifeInsureanceAmt==null||lifeInsureanceAmt==""){
        lifeInsureanceAmt = "nun";
    }
    var lifeInsureanceTerm = document.getElementById("lifeInsureanceTerm").value;
    if(lifeInsureanceTerm==null||lifeInsureanceTerm==""){
        lifeInsureanceTerm = "nun";
    }
    var lifeInsureanceAnnual = document.getElementById("lifeInsureanceAnnual").value;
    if(lifeInsureanceAnnual==null||lifeInsureanceAnnual==""){
        lifeInsureanceAnnual ="nun";
    }
    var lifeInsureancePolicy =  document.getElementById("lifeInsureancePolicy").value;
    if(lifeInsureancePolicy==null||lifeInsureancePolicy==""){
        lifeInsureancePolicy = "nun";
    }
    var healthInsuranceAnnual = document.getElementById("healthInsuranceAnnual").value;
    if(healthInsuranceAnnual==null||healthInsuranceAnnual==""){
        healthInsuranceAnnual = "nun";
    }
    var healthInsuranceAmt = document.getElementById("healthInsuranceAmt").value;
    if(healthInsuranceAmt==null||healthInsuranceAmt==""){
        healthInsuranceAmt = "nun";
    }*/
    var wagecomments = document.getElementById("wagecomments").value;
    if(wagecomments==null||wagecomments==""){
        wagecomments = "nun";
    }
    var wagecomments1 = document.getElementById("wagecomments1").value;
    if(wagecomments1==null||wagecomments1==""){
        wagecomments1 = "nun";
    }
    var generalcomments = document.getElementById("generalcomments").value;
    if(generalcomments==null||generalcomments==""){
        generalcomments = "nun";
    }
    var referencecomments = document.getElementById("referencecomments").value;
    if(referencecomments==null||referencecomments==""){
        referencecomments = "nun";
    }
    var empId = document.getElementById("empId").value;
    var homeAddressId = document.getElementById("homeAddressId").value;
    if(homeAddressId==null||homeAddressId==""||homeAddressId=="0"||homeAddressId==0){
        homeAddressId = "nun";
    }
    var workPhone = document.getElementById("workPhone").value;
    if(workPhone==null||workPhone==""){
        workPhone = "nun";
    }
    var orgId =     document.getElementById("orgId").value;
    var projectEndDate = document.getElementById("projectEndDate").value;
    if(projectEndDate==null||projectEndDate==""){
        projectEndDate = "nun";
    }
    
    var lifeInsurancePremium  =  document.getElementById("lifeInsurancePremium").value;
    var housingLoanRepay =  document.getElementById("housingLoanRepay").value;
    var nscTds =  document.getElementById("nscTds").value;
    var ppfContribution =  document.getElementById("ppfContribution").value;
    var tutionFee =  document.getElementById("tutionFee").value;
    var elss =  document.getElementById("elss").value;
    var postOfficeTerm =  document.getElementById("postOfficeTerm").value;
    var bankDepositTax =  document.getElementById("bankDepositTax").value;
    var othersTDS =  document.getElementById("othersTDS").value;
    var contributionToPf =  document.getElementById("contributionToPf").value;
    var npsEmployeeContr =  document.getElementById("npsEmployeeContr").value;
    var totalTds =  document.getElementById("totalTds").value;
    var totalTdsDeductable =  document.getElementById("totalTdsDeductable").value;
    var interstOnBorrowed =  document.getElementById("interstOnBorrowed").value;
    var interstOnBorrowedDeductable =  document.getElementById("interstOnBorrowedDeductable").value;
    var insuranceForParents =  document.getElementById("insuranceForParents").value;
    var insuranceForParentsDeduc =  document.getElementById("insuranceForParentsDeduc").value;
    var insuranceOthers =  document.getElementById("insuranceOthers").value;
    var insuranceOthersDeduc =  document.getElementById("insuranceOthersDeduc").value;
    var interstOnEdu =  document.getElementById("interstOnEdu").value;
    var interstOnHrAssumptions =  document.getElementById("interstOnHrAssumptions").value;
    var payRollId =  document.getElementById("payRollId").value;
    var netPaidPayroll =  document.getElementById("netPaidPayroll").value;
    

    // alert("orgId-->"+orgId);
    var resultString="firstName:"+firstName+",lastName:"+lastName+",middleName:"+middleName+",departmentId:"+departmentId+",titleId:"+titleId+",shiftId:"+shiftId+","+
     
    "classificationId:"+classificationId+",locationId:"+locationId+",regionId:"+regionId+",gender:"+gender+",currStatus:"+currStatus+",isPfFlag:"+isPfFlag+","+
     
    "employerId:"+employerId+",ssn:"+ssn+",passportNo:"+passportNo+",pfAccount:"+pfAccount+",trainingPeriod:"+trainingPeriod+","+
 
    "contractPeriod:"+contractPeriod+",dateOfJoining:"+dateOfJoining+","+
    "UANNo:"+UANNo+",adharNo:"+adharNo+",resonsForLeaving:"+resonsForLeaving+",address:"+address+","+
    "corporateEmail:"+corporateEmail+",personalEmail:"+personalEmail+",fathername:"+fathername+",fatherTitle:"+fatherTitle+",city:"+city+",state:"+state+","+
    "zip:"+zip+",fatherPhone:"+fatherPhone+",homePhone:"+homePhone+",mobilePhone:"+mobilePhone+",basic:"+basic+",paymentType:"+paymentType+","+
    "da:"+da+",bankAccountNo:"+bankAccountNo+",hra:"+hra+",bankName:"+bankName+",ta:"+ta+",employerPf:"+employerPf+","+
    "ra:"+ra+",employeePf:"+employeePf+",entertainment:"+entertainment+",life:"+life+",kidsEducation:"+kidsEducation+",health:"+health+","+
    "vehicleAllowance:"+vehicleAllowance+",professionalTax:"+professionalTax+",cca:"+cca+",otherDeductions:"+otherDeductions+",miscPay:"+miscPay+",splAllowance:"+splAllowance+","+
    "longTermBonus:"+longTermBonus+",grossPay:"+grossPay+",projectPay:"+projectPay+",variablePay:"+variablePay+",attendanceAllow:"+attendanceAllow+",totalCost:"+totalCost+","+
    "onProjectInd:"+onProjectInd+",onProjectIndValue1:"+onProjectIndValue1+",onProjectIndValue2:"+onProjectIndValue2+",datePayRevised:"+datePayRevised+",onsiteInd:"+onsiteInd+",onsiteIndV:"+onsiteIndV+","+
    "prevYtdSalary:"+prevYtdSalary+",empSaving1:"+empSaving1+",empSaving2:"+empSaving2+
    /*",lifeInsureanceAmt:"+lifeInsureanceAmt+",lifeInsureanceTerm:"+lifeInsureanceTerm+",lifeInsureanceAnnual:"+lifeInsureanceAnnual+","+
    "lifeInsureancePolicy:"+lifeInsureancePolicy+",healthInsuranceAnnual:"+healthInsuranceAnnual+",healthInsuranceAmt:"+healthInsuranceAmt+*/
    ",tutionfees:"+tutionfees+",hbLoanInterst:"+hbLoanInterst+",ppf:"+ppf+","+
    "medicalIns:"+medicalIns+",lifeIns:"+lifeIns+",hraLifeInsuranceSavings:"+hraLifeInsuranceSavings+
    ",premium:"+premium+",eduInterest:"+eduInterest+",fd:"+fd+","+
    "hbLoanPrinciple:"+hbLoanPrinciple+",mutualFunds:"+mutualFunds+",nsc:"+nsc+
    ",wagecomments:"+wagecomments+",wagecomments1:"+wagecomments1+",generalcomments:"+generalcomments+",referencecomments:"+referencecomments+",homeAddressId:"+homeAddressId+",workPhone:"+workPhone+",projectEndDate:"+projectEndDate+
    ",lifeInsurancePremium:"+lifeInsurancePremium+",housingLoanRepay:"+housingLoanRepay+",nscTds:"+nscTds+",ppfContribution:"+ppfContribution+",tutionFee:"+tutionFee+",elss:"+elss+",postOfficeTerm:"+postOfficeTerm+
    ",bankDepositTax:"+bankDepositTax+",othersTDS:"+othersTDS+",contributionToPf:"+contributionToPf+",npsEmployeeContr:"+npsEmployeeContr+",totalTds:"+totalTds+",totalTdsDeductable:"+totalTdsDeductable+",interstOnBorrowed:"+interstOnBorrowed+
    ",interstOnBorrowedDeductable:"+interstOnBorrowedDeductable+",insuranceForParents:"+insuranceForParents+",insuranceForParentsDeduc:"+insuranceForParentsDeduc+",insuranceOthers:"+insuranceOthers+",insuranceOthersDeduc:"+insuranceOthersDeduc+",interstOnEdu:"+interstOnEdu+",interstOnHrAssumptions:"+interstOnHrAssumptions+",netPaidPayroll:"+netPaidPayroll+",orgId:"+orgId+",empId:"+empId;

    if(bankName == 'All')
    {
      //  alert("please select bank");
        x0p( '','please select bank','info');
        return false;
    }
    else
    if(paymentType == 'All')
    {
      //  alert("please select payment type");
        x0p( '','please select payment type','info');
        return false;
    }
    
    else{
        
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerText(req, populatePayrollBackUpDetails);
        var url = CONTENXT_PATH+"/payrollBackUpDetails.action?resultString="+resultString+"&payRollId="+payRollId;
        req.open("GET",url,"true");    
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }  
}
function populatePayrollBackUpDetails(res)
{
    //alert(res.split("|")[0]);
    // alert(res.split("|")[1]);
    if(res == "success"){
        document.getElementById("payrollResultMessage").innerHTML = "PayRoll Backup successfully"   ;
     
        // document.getElementById("employerId").value=res.split("|")[0];
        setSaveOrUpdateButton();
    }
    else{
        document.getElementById("payrollResultMessage").innerHTML = "PayRoll Backup Failed"   ;
    }
    
    
    
}

function cleanPayroll(){
    
    var year = document.getElementById("cleanYear").value;
    var month = document.getElementById("cleanMonth").value;
     var orgId =document.getElementById("cleanorgId").value;
    if (isNaN(year) || year.length!=4){
         x0p( '','Please enter valid Year!!!','info');
             // alert("Please Enter valid Year!!"); 
                return false; 
          }
        if(year == null || year == "" || year == 0){
             x0p( '','Please enter Year!!!','info');
          // alert("Please Enter Year!!!"); 
                return false; 
        }
        if(month == null || month == "" || month == 0){
             x0p( '','Please select Month!','info');
         //  alert("Please select Month!!!"); 
                return false; 
        }
       if(orgId == null || orgId == "" || orgId == 0){
            x0p( '','Please select Organization!!','info');
         // alert("Please select Organization!!!"); 
                return false; 
        }
    
    var d = new Date();
    var currentMonth = d.getMonth()+1;
    var currentYear = d.getFullYear();
    
    if(month >= currentMonth){
         x0p( '','Please select before months for the current year!!','info');
      //  alert("Please select before months for the current year");
        return false;
    }
    else if(year > currentYear){
         x0p( '','Please select current year or before year!','info');
      //  alert("Please select current year or before year ");
        return false;
    }
    else
    {
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerText(req, populateCleanPayrollStatus);
        var url = CONTENXT_PATH+"/cleanPayroll.action?year="+year+"&month="+month+"&orgId="+orgId;
        req.open("GET",url,"true");    
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
}
function populateCleanPayrollStatus(res){
    
    if(res == "Success"){
        document.getElementById("cleanPayrollresultMessage").innerHTML = "<font style='color:green'>Records are cleaned successfully for the month and year selected..</font>";
    }
    else
    {
        document.getElementById("cleanPayrollresultMessage").innerHTML = "<font style='color:red'>Reocrds are in freezed state for the month and year selected</font>";
    }
    
}


function setUnFreezeForWages(){
    (document.getElementById("unfreezeloadingMessage")).style.display = "none";
    var year=document.getElementById("unfreezeyearOverlay").value;
    var month=document.getElementById("unfreezemonthOverlay").value;
   
  
     if (isNaN(year) || year.length!=4){
               document.getElementById("unfreezeresultMessage").innerHTML="<font color='red' size='2'>Please Enter valid Year!!</font>"; 
                return false; 
          }
        if(year == null || year == "" || year == 0){
            document.getElementById("unfreezeresultMessage").innerHTML="<font color='red' size='2'>Please Enter Year!!!</font>"; 
                return false; 
        }
        if(month == null || month == "" || month == 0){
            document.getElementById("unfreezeresultMessage").innerHTML="<font color='red' size='2'>Please select Month!!!</font>"; 
                return false; 
        }
    var dt = new Date();

    if(year > dt.getFullYear()){
        document.getElementById("unfreezeresultMessage").innerHTML="<font color='red' size='2'>Year must be less than the current year!</font>"; 
        return false;    
    }
   
    
    if(month > (dt.getMonth() + 1)){
        document.getElementById("unfreezeresultMessage").innerHTML="<font color='red' size='2'>Month must be less than the current Month!</font>";
        return false;
    }
    
    
    document.getElementById("unfreezeresultMessage").innerHTML = "";
    
        var empnameById="nun";
        var empNo="nun";
        var orgId =0;
      
        if(document.getElementById("empNameByIdUnFreeze").value != null && document.getElementById("empNameByIdUnFreeze").value != "" ){
            empnameById=document.getElementById("empNameByIdUnFreeze").value;
        }
        
        if(document.getElementById("unfreezeEmpNoOverlay").value != null && document.getElementById("unfreezeEmpNoOverlay").value != "" ){
            empNo=document.getElementById("unfreezeEmpNoOverlay").value;
             if (isNaN(empNo)){
                document.getElementById("unfreezeEmpNoOverlay").innerHTML="<font color='red' size='2'>Please enter valid EmpNo!!</font>"; 
                return false; 
          }
        }
        if(document.getElementById("unfreezeorgId").value != null && document.getElementById("unfreezeorgId").value != "" ){
            orgId=document.getElementById("unfreezeorgId").value;
        }
      
    
      var sel = document.getElementById("unfreezemonthOverlay");
    var val = sel.options[sel.selectedIndex].text;
    var result = false;
  //  var result = confirm("Are you sure you want to un freeze the records for "+val+","+year+" ?");
      x0p('Are you sure you want to un freeze the records for '+val+','+year+' ?', '', 'warning',
     function(button) {
       
        if(button == 'warning') {
            result = true;
            //alert(button)
        }
        if(button == 'cancel') { 
             result = false;
        }
   
     // alert(result)
    if(result)
    {
        
       
     //  alert(result)
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerText(req, populateUnFreezeWagesAck);
        var url = CONTENXT_PATH+"/doUnFreezeWages.action?year="+year+"&month="+month+"&empNo="+empNo+"&empnameById="+empnameById+"&orgId="+orgId;
        req.open("GET",url,"true");    
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
        return true;
    }
    else{
        return false;
    }

 });
}

function populateUnFreezeWagesAck(res){
    // alert(res);
    if(res=="LimitExceeded")
    {
        document.getElementById("unfreezeresultMessage").innerHTML = "<font style='color:red;size:15px;'>Limited days to unfreeze the records has been exceeded 30 days!!</font>"; 
    }
    else

    {
        document.getElementById("unfreezeresultMessage").innerHTML = res;
               
    }
}
function tooglereRunWages(){
    document.getElementById("rerunresultMessage").innerHTML = "";
    var overlay = document.getElementById('rerunWagesOverlay');
    var specialBox = document.getElementById('rerunReportOverlay');
    
    // document.getElementById("rerunempnameById").value="";
    // document.getElementById("rerunyearOverlay").value="";
    //  document.getElementById("rerunnoOfDays").value="";
    //   document.getElementById("rerunnoOfWeekendDays").value="";
    //   document.getElementById("rerunnoOfHolidays").value="";
    //   document.getElementById("rerunnoOfWorkingDays").value="";
         
    
        
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        // alert("in block");
        overlay.style.display = "none";
        specialBox.style.display = "none";
                   
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }   
}

function confirmForReRunWagesHolidaysUpdation(){
    var year=document.getElementById("rerunyearOverlay").value;
 
    var month=document.getElementById("rerunmonthOverlay").value;   
    var rerunempnameById=document.getElementById("empNameById").value;   
    var rerunnoOfDays=document.getElementById("rerunnoOfDays").value;   
    var rerunnoOfWeekendDays=document.getElementById("rerunnoOfWeekendDays").value;   
    var rerunnoOfHolidays=document.getElementById("rerunnoOfHolidays").value;   
    var rerunEmpNoOverlay = document.getElementById("rerunEmpNoOverlay").value;
    var rerunnoOfWorkingDays=document.getElementById("rerunnoOfWorkingDays").value;  
    
    var isAllGiven = false;
    var resultMsg = "";
    if(year.trim().length==0){
        isAllGiven = false;
        resultMsg = "<font color=red size=2px>Please enter year!</font>";
    // document.getElementById("rerunresultMessage").innerHTML =
    }
    else if(month.trim().length==0){
        isAllGiven = false;
        resultMsg = "<font color=red size=2px>Please enter month!</font>";
    // document.getElementById("rerunresultMessage").innerHTML =
    }else if(rerunempnameById.trim().length==0 && rerunEmpNoOverlay.length==0){
      
        isAllGiven = false;
        resultMsg = "<font color=red size=2px>Please select Employee or Employee No!</font>";
    // document.getElementById("rerunresultMessage").innerHTML =
    }else if(rerunnoOfDays.trim().length==0){
        isAllGiven = false;
        resultMsg = "<font color=red size=2px>Please enter No. of days!</font>";
    // document.getElementById("rerunresultMessage").innerHTML =
    }else if(rerunnoOfHolidays.trim().length==0){
        isAllGiven = false;
        resultMsg = "<font color=red size=2px>Please enter no. of holidays!</font>";
    // document.getElementById("rerunresultMessage").innerHTML =
    }else if(rerunnoOfWorkingDays.trim().length==0){
        isAllGiven = false;
        resultMsg = "<font color=red size=2px>Please enter no. of working days!</font>";
    // document.getElementById("rerunresultMessage").innerHTML =
    }else {
        isAllGiven = true;
    }
    
    
    if(isAllGiven){
         var result = false;
  //  var result = confirm("Are you sure you want to un freeze the records for "+val+","+year+" ?");
      x0p('Are you sure for the number of holidays entered for the month ?', '', 'warning',
     function(button) {
       
        if(button == 'warning') {
            result = true;
            //alert(button)
        }
        if(button == 'cancel') { 
             result = false;
        }
        
      //  var result = confirm("Are you sure for the number of holidays entered for the month ?");
        
        if(result)
        {
      
            var dt = new Date();

            if(month == dt.getMonth()+1){
    
    var result1 = false;
  //  var result = confirm("Are you sure you want to un freeze the records for "+val+","+year+" ?");
      x0p('Current month is not yet done are you sure to continue ?', '', 'warning',
     function(button) {
       
        if(button == 'warning') {
            result1 = true;
            //alert(button)
        }
        if(button == 'cancel') { 
             result1 = false;
        }
    
                //var result1 = confirm("Current month is not yet done are you sure to continue ?");
    
                if(result1){
                    doReRunWagesScript();
                    return true;
        
                }else
                {
                    return false;
                }
     });  
    
            }else{
        
                doReRunWagesScript();
                return true;
            }
     
        }else
        {
            return false;
        }
     });  
  }else {
        document.getElementById("rerunresultMessage").innerHTML =resultMsg;
        return false;
    }
    
}


function readyStateHandlerReRunWagesText(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("rerunloadingMessage")).style.display = "none";
               
                
                responseTextHandler(req.responseText);
            } else {
                
              //  alert("HTTP error ---"+req.status+" : "+req.statusText);
               x0p( '','HTTP error ---'+req.status+' : '+req.statusText,'info');
            }
        }
        else {
            (document.getElementById("rerunloadingMessage")).style.display = "block";
           
        }
    }
}



function populateReRunWagesAck(res){
    // document.getElementById("resultMessage").style.dispay="block";
    document.getElementById("rerunresultMessage").innerHTML = res;
    
}

function doReRunWagesScript(){
    (document.getElementById("rerunloadingMessage")).style.display = "none";
    document.getElementById("rerunresultMessage").innerHTML ="";
    var year=document.getElementById("rerunyearOverlay").value;
 
    var month=document.getElementById("rerunmonthOverlay").value;   
    
    var noOfDays=document.getElementById("rerunnoOfDays").value;
    var noOfWeekedDays=document.getElementById("rerunnoOfWeekendDays").value;
    var noOfHolidays=document.getElementById("rerunnoOfHolidays").value;
    var noOfWorkingDays=document.getElementById("rerunnoOfWorkingDays").value;
    var rerunempnameById=document.getElementById("empNameById").value;
    var rerunEmpNoOverlay =  document.getElementById("rerunEmpNoOverlay").value;
      
    // alert("EmpId-->"+rerunempnameById);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerReRunWagesText(req, populateReRunWagesAck);
    var url = CONTENXT_PATH+"/doReRunWages.action?year="+year+"&month="+month+"&noOfDays="+noOfDays+"&noOfWeekedDays="+noOfWeekedDays+"&noOfHolidays="+noOfHolidays+"&workingDays="+noOfWorkingDays+"&empnameById="+rerunempnameById+"&empId="+rerunEmpNoOverlay;
    //  alert(url);
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
//document.getElementById("wagesOverlay").submit();  doRunWages
     
}



function fillNoOfDaysWorkedForReRunWages(){
    //alert("in function");
    var noOfWorkingDays;
    var noOfHolidays = document.getElementById("rerunnoOfHolidays").value;
    var noOfDaysInMonth = document.getElementById("rerunnoOfDays").value;
    var noOfWeekendDays = document.getElementById("rerunnoOfWeekendDays").value;
    noOfWorkingDays = parseFloat(noOfDaysInMonth)-parseFloat(noOfHolidays)-parseFloat(noOfWeekendDays);
    document.getElementById("rerunnoOfWorkingDays").value= noOfWorkingDays;
}

function getDaysForTheSelectedMonthReRunWages(){
    
    var year=document.getElementById("rerunyearOverlay").value;
    var month=document.getElementById("rerunmonthOverlay").value;

    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerReRunWagesText(req, populateDaysForTheSelectedMonthReRunWages);
    var url = CONTENXT_PATH+"/doGetDaysFortheSelectedMonthRunWages.action?year="+year+"&month="+month;
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populateDaysForTheSelectedMonthReRunWages(res){

    document.getElementById("rerunnoOfDays").value=res.split("^")[0];
    document.getElementById("rerunnoOfWeekendDays").value=res.split("^")[1];
}

// Upload leaves script

function toggleUploadLeavesOverlay(){
   // document.getElementById("leavesUploadResultMessage").innerHTML = "";
    document.getElementById('file').value = "";
    var overlay = document.getElementById('leavesUplaodSpecailbox');
    var specialBox = document.getElementById('leavesUploadOverlay');
        
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        window.location="getPayRollReports.action";
        overlay.style.display = "none";
        specialBox.style.display = "none";
                   
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }   
}

function doUploadLeavesExcel()
{
  
    var year = document.getElementById("leaveYear").value;
    var month = document.getElementById("leaveMonth").value;
    var fullPath = document.getElementById('file').value;
    var orgId = document.getElementById('orgIdForLeavesUpload').value;
    if (isNaN(year) || year.length!=4 ) {
         document.getElementById('leavesUploadResultMessage').innerHTML = "<font color=red>Please enter Valid Year!!!</font>";
        return false;
    }
    if(orgId==""){
        document.getElementById('leavesUploadResultMessage').innerHTML = "<font color=red>Please select organization</font>";
        return false;
    }
    if(year.trim().length==0){
        document.getElementById('leavesUploadResultMessage').innerHTML = "<font color=red>Please enter year</font>";
    }
    else if(month.trim().length==0){
        document.getElementById('leavesUploadResultMessage').innerHTML = "<font color=red>Please select month</font>";
    }else if(fullPath.trim().length==0){
        document.getElementById('leavesUploadResultMessage').innerHTML = "<font color=red>Please browse file</font>";
    }else {
        document.getElementById("leavesUploadLoadingMessage").style.display = 'block';
        $.ajaxFileUpload({
            url:'uploadLeavesExcel.action?year='+year+'&month='+month+'&orgId='+orgId,//
            secureuri:false,//false
            fileElementId:'file',//id  <input type="file" id="file" name="file" />
            dataType: 'json',// json
            success: function(data,status){
            
                var displaymessage = "<font color=red>Please try again later</font>";
                //  alert("success-->11111-->"+data);
                // var json = $.parseJSON(data);
                if(data.indexOf("uploaded")>0){
                    var rowsInserted = data.split("^")
                    // alert("uploaded");
                    displaymessage = "<font color=green>"+rowsInserted[1]+" Rows inserted ,File uploaded Successfully!</font>";
                }
                else if(data.indexOf("Notvalid")>0){
                    // alert("not valid");
                    displaymessage = "<font color=red>Not a valid file!,Please check the file and try again.</font>";
                }
                else if(data.indexOf("Error")>0){
                    // alert("Erro");
                    displaymessage = "<font color=red>Internal Error!, Please try again later.</font>"
                }
                else {
                    displaymessage="<font color=red>"+data+"</font>"; 
                }
           
                //alert(data.indexOf("uploaded"));
           
                document.getElementById("leavesUploadLoadingMessage").style.display = 'none';
                document.getElementById('leavesUploadResultMessage').innerHTML = displaymessage;//"<font color=green>File uploaded successfully</font>";
                
            },
            error: function(e){
                // alert('Error:111 ' + e);
                document.getElementById("leavesUploadLoadingMessage").style.display = 'none';
                document.getElementById('leavesUploadResultMessage').innerHTML = "<font color=red>Please try again later</font>";
                
                
            }
        });
    
    }
		
    return false;

}


function putFileName()
{
    document.getElementById('leavesUploadResultMessage').innerHTML = '';
    var fullPath = document.getElementById('file').value;
    var extension= fullPath.substr(fullPath.lastIndexOf('.')+1);
    // alert(fullPath);
    //alert(extension);
    if(extension=='xls' || extension=='xlsx'){
        // alert(fullPath);
            
        var size = document.getElementById('file').files[0].size;
                 
        if(fullPath.length>50){
            document.getElementById('file').value = '';
            document.getElementById('leavesUploadResultMessage').innerHTML = "<font color=red>File name length must be less than 50 characters!</font>"
            // showAlertModal("File size must be less than 2 MB");
            return (false);
        }else {
            if(parseInt(size)<2097152) {
                
                  
            }
            else {
                document.getElementById('file').value = '';
                document.getElementById('leavesUploadResultMessage').innerHTML = "<font color=red>File size must be less than 2 MB.</font>"
                // showAlertModal("File size must be less than 2 MB");
                return (false);
            }
        }
    }
    else {
        document.getElementById('file').value = '';
        document.getElementById('leavesUploadResultMessage').innerHTML = "<font color=red>File must be xls extension.</font>"
        // showAlertModal("File size must be less than 2 MB");
        return (false);
    }
}


//method for bank reports
function getBankReportAsPDF(){
    var year = document.getElementById('yearOverlayForBiometricReportGeneration').value;
    var month = document.getElementById('monthForBiometricReportGeneration').value;
    var orgName=document.getElementById('orgIdForBank').value;
    var doRepayment=document.getElementById('doRepaymentFlag').checked;
    var doRepaymentFlag=0;
    var bankName=document.getElementById('bankName').value;
    
   if (isNaN(year) || year.length!=4) {
       x0p( '','Please enter valid Year!!!','info');
      //  alert("please enter valid year");
        return false;
   }
       
    else if(year.length == 0)
    {
        x0p( '','Please enter year!!!','info');
       // alert("please enter year");
        return false;
    }
    else if(month.length == 0)
    {
          x0p( '','Please select month!!!','info');
        //alert("please select month");
        return false;
    }
    else if(orgName.length == 0){
        x0p( '','Please select organisation!!!','info');
       // alert("Please select organisation");
        return false;
    }
    else{
        if(doRepayment==true){
            doRepaymentFlag=1;
        }
        
        var url = "generateBankReportAsPDF.action?bankName="+bankName+"&orgName="+orgName+"&year="+year+"&month="+month+"&doRepaymentFlag="+doRepaymentFlag;
        window.open(url, '_blank');
    }
    
}


function freezeOrUnfreeze(wageId,payrollId,freezePayrollVal,month,year){
    if(freezePayrollVal == "0"){
     
        freezePayroll(wageId,payrollId,freezePayrollVal);
    }
    else if(freezePayrollVal == "1"){
        unfreezePayroll(wageId,payrollId,freezePayrollVal);
    }
    
}

function goToWageRegistery(){
    window.location = "getEmpWageDetails.action"
}

function checkWithDaysInMonth(eve){
    /*
 *Days project> Days in month 
Then Days project=Days in month ;


Days Project < Days in month 

then conformbox("") --If click on ok as it is .If Cancel then Focus on days project .
 *
 *
 **/
    var daysVactaion = document.getElementById('daysVacation').value;
    var daysInMonth = document.getElementById('daysInMonth').value;
    var daysWorked = document.getElementById('daysWorked').value;
    var daysHolidays = document.getElementById('daysHolidays').value;
    var daysWeekends = document.getElementById('daysWeekends').value;
    var earnedactualProjectPay = document.getElementById('earnedProjectPay').value;
    var originalEarnedProjectpay = document.getElementById('earnedProjectPayHidden').value;
    var temp_count = parseInt(daysWorked) + parseInt(daysHolidays) + parseInt(daysWeekends) + parseInt(daysVactaion) ;
    var daysInProject =eve.value;
    var daysInProjectHidden =document.getElementById('daysProjectHiddenValue').value;
    /* if(daysInProject>temp_count){
        alert(" Days project should be smaller or equal to the total days of the month");
        document.getElementById('daysProject').value = document.getElementById('daysProjectHiddenValue').value;
        setTimeout(function(){
            document.employeeWageDetails.daysProject.focus()
            }, 10);
        return false;
    }
    else*/ 
    var result = false;
    
   
    if(daysInProject>daysInProjectHidden){
       // result  = confirm("Days Project entered is greater than actual value.. days do you want to continue?");
         var resultMes = "Days Project entered is greater than actual value.. days do you want to continue?";
    }
    else if(daysInProject==daysInProjectHidden){
       // result  = confirm("Days Project entered is equal to actual value.. days do you want to continue?");
         var resultMes ="Days Project entered is equal to actual value.. days do you want to continue?";
    }
    else
    {
     //   result  = confirm("Days Project entered is smaller than actual value.. days do you want to continue?");   
        var resultMes ="Days Project entered is smaller than actual value.. days do you want to continue?";
    }
    x0p(resultMes, '', 'warning',
     function(button) {
       
        if(button == 'warning') {
            result = true;
            //alert(button)
        }
        if(button == 'cancel') { 
             result = false;
        }
    if(result)
    {
        //SET v_Earned_ProjectPay = v_ProjectPay-ROUND((v_ProjectPay/frmNoOfDays)*v_ExtraLeaves);
        //   var v_Earned_ProjectPay =  Math.round((parseFloat(earnedactualProjectPay)/parseInt(daysInMonth))*parseInt(temp_count));
        var v_Earned_ProjectPay =  Math.round((parseFloat(originalEarnedProjectpay)/parseInt(daysInMonth))*parseInt(daysInProject));
        document.getElementById('daysProject').value = daysInProject;
        //    Math.round((parseFloat(earnedactualProjectPay)/parseInt(daysInMonth))*parseInt(temp_count))
        document.getElementById('earnedProjectPay').value = parseFloat(v_Earned_ProjectPay).toFixed(2);
        return true;
    }else
    {
        
        document.getElementById('daysProject').value = document.getElementById('daysProjectHiddenValue').value;
        setTimeout(function(){
            document.employeeWageDetails.daysProject.focus()
        }, 10);
        return false;
    }  
    
/*alert("in second if daysInMonth-->"+daysInMonth);
  alert("in second  daysInProject -->"+daysInProject);
if(daysInProject > daysInMonth){
    alert("in second if");
    alert("in second if temp_count-->"+temp_count);
    
       document.getElementById('daysProject').value = temp_count;
}*/
     });
}


function changeDaysVacationValues(eve){
    var daysVactaion = document.getElementById('daysVacation').value;
    var vactionsAvailable = document.getElementById('vactionsAvailable').value;
    var leavesApplied = eve.value;
    var leavesAppliedHidden = document.getElementById('leavesAppliedHidden').value;
  
    // var temp
    var  temp_daysvac=0,temp_leaves_avl=0,temp_leavesapl=0,temp_var=0;
    if(leavesApplied<=1){
        document.getElementById("earnedattallowance").value="1000.00";
    }else{
        document.getElementById("earnedattallowance").value="0.00";
    }
    
    if(leavesApplied < leavesAppliedHidden){
        if(leavesApplied==0){
            temp_var = leavesAppliedHidden - daysVactaion;
            if(temp_var>0){
                document.getElementById('daysVacation').value=0;
                document.getElementById('vactionsAvailable').value=temp_var;
            }else{
                document.getElementById('daysVacation').value=temp_var;
                document.getElementById('vactionsAvailable').value=0;
            }
        }else{
            
        }
    }else if(leavesApplied > leavesAppliedHidden){
        
    }else{
        
    }
  
}
function readyStateHandlerreq(req,responseTextHandler) {

    //alert("ready");
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadingMessage12")).style.display = "none";
                responseTextHandler(req.responseText);
            } else {
                
            //    alert("HTTP error ---"+req.status+" : "+req.statusText);
                 x0p( '','HTTP error ---'+req.status+' : '+req.statusText,'info');
            }
        }else {
          
            (document.getElementById("loadingMessage12")).style.display = "block";
        }
    }
}
function getTEFDetails() {
    
    var empId = document.getElementById("empId").value;
    // alert("role Id in getREqList:"+AjaxRoleId);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerreq(req, displayTEFListResult); 
    
    //alert("path ==="+CONTENXT_PATH);

    var url = CONTENXT_PATH+"/getTEFDetails.action?empId="+empId;
 
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function displayTEFListResult(resText) {
    if(resText.length !=0 && resText!="addto0"){
        var oTable = document.getElementById("tblTefList");
        
        clearTable(oTable);

      //  var headerFields = new Array("Exemption Type","Edit","Status","AppliedBy","PaymentDate","Sav Amt","ApprovedBy","Appr Amt","Approver Comments","Attachments");	
        
var headerFields = new Array("Exemption Type","Status","AppliedBy","PaymentDate","Sav Amt","ApprovedBy","Appr Amt","Approver Comments","Attachments");	
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
       
        var resTextSplit1 = resText.split("^");
        var resTextSplit2="";
        generateTableHeader(tbody,headerFields);
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("|");
            //    alert(resTextSplit2);
            generateRow(tbody,resTextSplit2,index);
         
        }
        generateFooter(tbody);
    }else {
     //   alert("No Records Found");
         x0p( '','No Records Found','info');
    }
}
function generateTableHeader(tableBody,headerFields) {
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridHeader";
    tableBody.appendChild( row );
    for (var i=0; i<headerFields.length; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridHeader";
        row.appendChild( cell );
        cell.setAttribute('align','center');
        cell.setAttribute("width","10000px");
        cell.innerHTML = headerFields[i];
    }
}

function generateRow(tableBody,rowFeildsSplit,index) {
    var row;
    var cell;
    row = document.createElement("TR");
    row.className="gridRowEven";
    /*cell = document.createElement("TD");
    cell.className="gridRowEven";
    cell.innerHTML = index+1;
    cell.setAttribute('align','center');
    row.appendChild(cell);*/
    tableBody.appendChild(row);

    for (var i=0; i<=rowFeildsSplit.length-5; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild(cell);
        if(i==0) {
            cell.setAttribute('align','center');
            cell.innerHTML = rowFeildsSplit[13];
        }
//        if(i==1) {
//            cell.innerHTML = " ";
//            var j = document.createElement("a");
//            j.setAttribute("href", "javascript:getTefEmpDetails('"+rowFeildsSplit[7]+"')");
//            j.appendChild(document.createTextNode("Edit"));
//            cell.appendChild(j);
//            cell.align="center";
//        }
        
       // else if(i==2){
          if(i==1) {
              cell.setAttribute('align','center');
            cell.innerHTML = rowFeildsSplit[2];
        }else if(i==2){
            cell.setAttribute('align','center');
            cell.innerHTML = rowFeildsSplit[3];
        }
        else if(i==3){
            cell.setAttribute('align','center');
            cell.innerHTML = rowFeildsSplit[8];
                
        }
        else if(i==4){
            cell.setAttribute('align','center');
            cell.innerHTML = rowFeildsSplit[4];
                
        }
        else if(i==5){
            cell.setAttribute('align','center');
            cell.innerHTML = rowFeildsSplit[9];
                
        }
        else if(i==6){
            cell.setAttribute('align','center');
            cell.innerHTML = rowFeildsSplit[10];
                
        }
        else if(i==7){
            var j = document.createElement("a");
            j.setAttribute("href", "javascript:showApproverCommentsPopUp('"+rowFeildsSplit[11]+"')");
            j.appendChild(document.createTextNode("Click To View"));
            cell.appendChild(j);
            cell.align="center";
        }
        else if(i==8){
            if(rowFeildsSplit[5] == "--"){
                cell.setAttribute('align','center');
                cell.innerHTML = rowFeildsSplit[5];
            }
            else
            {
                var j = document.createElement("a");
                j.setAttribute("href", "javascript:downloadTefAttachement('"+rowFeildsSplit[7]+"','"+rowFeildsSplit[1]+"')");
                j.appendChild(document.createTextNode("click"));
                cell.setAttribute('text-decoration','none');
                cell.appendChild(j);
                cell.align="center";
            }
                
        }
        cell.width = 120;
    }
}





function generateFooter(tbody) {
    // alert(tbody);
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    //    cell.id="footer"+oTable.id;
 
    cell.colSpan = "11";
     
    footer.appendChild(cell);
}

function clearTable(tableId) {
    var tbl =  tableId;
    var lastRow = tbl.rows.length; 
    while (lastRow > 0) { 
        tbl.deleteRow(lastRow - 1);  
        lastRow = tbl.rows.length; 
    } 
}


function downloadTefAttachement(tefId,empId){
    window.location="downloadTefAttachment.action?tefId="+tefId+"&empId="+empId+"&fromTef="+document.getElementById("fromTef").value;
    
}

function showApproverCommentsPopUp(text) {
    var background = "#3E93D4";
    var title = "Approver Comments";
    var text1 = ""; 
    if(text == "--"){
        text1 = "No Comments Given";
    }
    else{
        text1 = text; 
    }
    var size = text1.length;
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+text1+"<br />\
    </body></html>";
    
    if(size < 50){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 100){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 260){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
}
          
 function updatePayrollTaxExemptionForPayollList(){
  
    var overLayexemptionType = document.getElementById('overLayexemptionType').value;
    var overLaystatus = document.getElementById('overLaystatus').value;
    var overlayApprovedAmount = document.getElementById('overlayApprovedAmount').value;
    var overlaySavingAmount = document.getElementById('overlaySavingAmount').value;
    var exemptionId= document.getElementById("tefId").value; 
    //var yearOverlay= document.getElementById("yearOverlay").value; 
    //var monthOverlay= document.getElementById("monthOverlay").value; 
    var comments= document.getElementById("comments").value; 
    var empId =  document.getElementById("empId").value;
    var paymentDateEmp = document.getElementById("paymentDateEmp").value;
    var category =  document.getElementById("categoryId").value;
    // alert(overLaystatus);
  
    //alert(overlayApprovedAmount);
    if(parseInt(overlaySavingAmount)<parseInt(overlayApprovedAmount)){
        document.getElementById("overlayApprovedAmount").style.background='transperant';
        document.getElementById("overlayApprovedAmount").focus();
        document.getElementById('overlayApprovedAmount').value="0.00";
        document.getElementById('resultMessageTefUpdate').innerHTML = "<font color=red>Approved Amount should be less than or equal to Applied Amount.</font>";
        return false;
    }
    else if(paymentDateEmp.length == 0){
    //    alert("Please enter payment date");
         x0p( '','Please enter payment date','info');
        return false;
    }
    else
    {
      //  alert("in final elase");
        
        if(overLaystatus == "Approved"){
               
         
            if(category == '1'){
                var savings1= parseFloat(document.getElementById("empSaving1").value)+ parseFloat(overlayApprovedAmount); 
                if(savings1 > 150000){
                   // alert("EmpSavings1 should not exceed Rs150000/-");
                     x0p( '','EmpSavings1 should not exceed Rs150000/-','info');
                    var savingDifference=parseFloat(savings1)-150000;
                    var allowedAmmount=parseFloat(overlayApprovedAmount)-parseFloat(savingDifference); 
                    document.getElementById("overlayApprovedAmount").value = allowedAmmount.toFixed(2);
                    setTimeout(function(){
                        document.overlayPayrollListTef.overlayApprovedAmount.focus()
                    }, 10);
                    return false;
             
                }
                else{
                    document.getElementById("empSaving1").value = savings1.toFixed(2);
                  //  return true;
                }
            }
            if(category == '2'){
   
                var savings2=  parseFloat(document.getElementById("empSaving2").value)+ parseFloat(overlayApprovedAmount); 
                if(savings2 > 200000){
                  //  alert("EmpSavings2 should not exceed Rs200000/-");
                     x0p( '','EmpSavings2 should not exceed Rs200000/-','info');
                    var savingDifference=parseFloat(savings2)-200000;
                    var allowedAmmount=parseFloat(overlayApprovedAmount)-parseFloat(savingDifference); 
                    document.getElementById("overlayApprovedAmount").value = allowedAmmount.toFixed(2);
                    setTimeout(function(){
                        document.overlayPayrollListTef.overlayApprovedAmount.focus()
                    }, 10);
                    return false;
                }else{
                    document.getElementById("empSaving2").value = savings2.toFixed(2);
                   // return true;
                }
            }
            //if(category == '3' && overLayexemptionType == '15'){
      if(category == '3'){
                if(overLayexemptionType == '15'){
            //if(category == '3'){
             
                if(parseFloat(overlayApprovedAmount) > 30000){
                    
               //     alert("EmpSavings3(80D Parents Insurance) for selected exception category  should not exceed Rs30000/-");
                    x0p( '','EmpSavings3(80D Parents Insurance) for selected exception category  should not exceed Rs30000/-','info');
                     var savingDifference=30000;
                    var allowedAmmount=parseFloat(overlayApprovedAmount)-parseFloat(savingDifference); 
                    document.getElementById("overlayApprovedAmount").value = allowedAmmount.toFixed(2);
                    setTimeout(function(){
                        document.overlayPayrollListTef.overlayApprovedAmount.focus()
                    }, 10);
                    return false;
                }
               
            }
            if(overLayexemptionType == '16'){
               var health= document.getElementById("health").value;
               health=parseFloat(health)*12;
                if((parseFloat(overlayApprovedAmount)+health) > 25000){
                  //  alert("EmpSavings3(80D Self Insurance etc.) for selected exception category should not exceed Rs25000/-");
                      x0p( '','EmpSavings3(80D Self Insurance etc.) for selected exception category should not exceed Rs25000/-','info');
                     var savingDifference=25000;
                    var allowedAmmount=parseFloat(overlayApprovedAmount)-health-parseFloat(savingDifference); 
                    allowedAmmount=overlayApprovedAmount-allowedAmmount;
                    document.getElementById("overlayApprovedAmount").value = allowedAmmount.toFixed(2);
                    setTimeout(function(){
                        document.overlayPayrollListTef.overlayApprovedAmount.focus()
                    }, 10);
                    return false;
                } 
               
            }
             var health= document.getElementById("health").value;
               health=parseFloat(health)*12;
            overlayApprovedAmount=parseFloat(overlayApprovedAmount)+health;
                var savings3=  parseFloat(document.getElementById("empSaving3").value)+ parseFloat(overlayApprovedAmount); 
                if(savings3 > 55000){
                  //  alert("EmpSavings3 for selected exception category should not exceed Rs55000/-");
                     x0p( '','EmpSavings3 for selected exception category should not exceed Rs55000/-','info');
                    var savingDifference=parseFloat(savings3)-55000;
                    var allowedAmmount=parseFloat(overlayApprovedAmount)-parseFloat(savingDifference)-health; 
                     document.getElementById("overlayApprovedAmount").value = allowedAmmount.toFixed(2);
                    setTimeout(function(){
                        document.overlayPayrollListTef.overlayApprovedAmount.focus()
                    }, 10);
                    return false;
                }else{
                    savings3=savings3-health;
                    overlayApprovedAmount=parseFloat(overlayApprovedAmount)-health;

                    document.getElementById("empSaving3").value = savings3.toFixed(2);
                   // return true;
                }
            }

//            if(category == '3' && overLayexemptionType == '16'){
//                var savings3=  parseFloat(document.getElementById("empSaving3").value)+ parseFloat(overlayApprovedAmount); 
//                if(savings3 > 15000){
//                    alert("EmpSavings3 for selected exception category should not exceed Rs15000/-");
//                     var savingDifference=parseFloat(savings2)-150000;
//                    var allowedAmmount=parseFloat(overlayApprovedAmount)-parseFloat(savingDifference); allowedAmmount.toFixed(2);
//                    document.getElementById("overlayApprovedAmount").value = "0.00";
//                    setTimeout(function(){
//                        document.overlayPayrollListTef.overlayApprovedAmount.focus()
//                    }, 10);
//                    return false;
//                }
//                else{
//                    document.getElementById("empSaving3").value = savings3.toFixed(2);
//                  //  return true;
//                }
//            }
   
            if(category == '4'){
                var savings4=  parseFloat(document.getElementById("empSaving4").value)+ parseFloat(overlayApprovedAmount); 
                document.getElementById("empSaving4").value = savings4.toFixed(2);
               // return true;
            }
            if(category == '5'){
                var savings5=  parseFloat(document.getElementById("empSaving5").value)+ parseFloat(overlayApprovedAmount); 
                document.getElementById("empSaving5").value = savings5.toFixed(2);
              //  return true;
            }
              if(category == '6'){
                   
                var savings6= parseFloat(document.getElementById("empSaving6").value)+ parseFloat(overlayApprovedAmount); 
               // alert("--->sav"+savings6)
               if(savings6 > 50000){
                  //  alert("EmpSavings should not exceed Rs50000/-");
                     x0p( '','EmpSavings should not exceed Rs50000/-','info');
                    var savingDifference=parseFloat(savings6)-50000;
                    var allowedAmmount=parseFloat(overlayApprovedAmount)-parseFloat(savingDifference); 
                    document.getElementById("overlayApprovedAmount").value = allowedAmmount.toFixed(2);
                    setTimeout(function(){
                        document.overlayPayrollListTef.overlayApprovedAmount.focus()
                    }, 10);
                    return false;
             
                }
                else{
                    document.getElementById("empSaving6").value = savings6.toFixed(2);
                  //  return true;
                }
            }
        }
        else  {
            if(category == '1'){
                var savings1= parseFloat(document.getElementById("empSaving1").value)- parseFloat(overlayApprovedAmount); 
                if(parseFloat(savings1)>0){
                document.getElementById("empSaving1").value = savings1.toFixed(2);
            }else{
                document.getElementById("empSaving1").value = "0.0";
            }
        }
            else  if(category == '2'){
                var savings2=  parseFloat(document.getElementById("empSaving2").value)- parseFloat(overlayApprovedAmount); 
                 if(parseFloat(savings1)>0){
                document.getElementById("empSaving2").value = savings2.toFixed(2);
            }else{
                  document.getElementById("empSaving2").value = "0.0";
            }}
            else if(category == '3'){
                var savings3=  parseFloat(document.getElementById("empSaving3").value)- parseFloat(overlayApprovedAmount); 
                 if(parseFloat(savings1)>0){
                document.getElementById("empSaving3").value = savings3.toFixed(2);
            }else{
                  document.getElementById("empSaving3").value = "0.0";
            }}
            else if(category == '4'){
                var savings4=  parseFloat(document.getElementById("empSaving4").value)- parseFloat(overlayApprovedAmount); 
                 if(parseFloat(savings1)>0){
                document.getElementById("empSaving4").value = savings4.toFixed(2);
            }else{
                  document.getElementById("empSaving4").value = "0.0";
            }
        }
            else if(category == '5'){
                var savings5=  parseFloat(document.getElementById("empSaving5").value)- parseFloat(overlayApprovedAmount); 
                 if(parseFloat(savings1)>0){
                document.getElementById("empSaving5").value = savings5.toFixed(2);
            }else{
                 document.getElementById("empSaving5").value = "0.0"; 
            }}
         else if(category == '6'){
                var savings6=  parseFloat(document.getElementById("empSaving6").value)- parseFloat(overlayApprovedAmount); 
                 if(parseFloat(savings6)>0){
                document.getElementById("empSaving6").value = savings6.toFixed(2);
            }else{
                 document.getElementById("empSaving6").value = "0.0"; 
            }}
        }
        
           var empSaving1 =  document.getElementById("empSaving1").value;
            var empSaving2 =  document.getElementById("empSaving2").value;
            var empSaving3 =  document.getElementById("empSaving3").value;
            var empSaving4 =  document.getElementById("empSaving4").value;
            var empSaving5 =  document.getElementById("empSaving5").value;
             var empSaving6 =  document.getElementById("empSaving6").value;
           // alert("in final");
            var overlayApprovedAmountHidden =  document.getElementById("overlayApprovedAmountHidden").value;
            
            var req = newXMLHttpRequest();
            req.onreadystatechange = readyStateHandlerreq(req,ppulateMyReviewUpadteResultPayRollList); 
        
            //var url=CONTENXT_PATH+"/updateMyReview.action?reviewId="+reviewId+"&overlayReviewType="+overlayReviewType+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay;
            var url=CONTENXT_PATH+'/upadtePayrollTaxExemptionPayRollList.action?overLayExemptionType='+overLayexemptionType+'&overlayApprovedAmount='+overlayApprovedAmount+'&overLayStatus='+overLaystatus+'&comments='+comments+'&exemptionId='+exemptionId+"&empId="+empId+"&paymentDateEmp="+paymentDateEmp+"&empSaving1="+empSaving1+"&empSaving2="+empSaving2+"&empSaving3="+empSaving3+"&empSaving4="+empSaving4+"&empSaving5="+empSaving5+"&empSaving6="+empSaving6;
            // alert(url);
            req.open("GET",url,"true");
            req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            req.send(null);
        
        
    }

}


function ppulateMyReviewUpadteResultPayRollList(resText){
    //  alert(resText);
    if(resText.split("$")[0] == "updated"){
        document.getElementById('resultMessageTefUpdate').innerHTML = "<font color=green size=2px>Updated Successfully</font>";
        document.getElementById('empSaving1').value=resText.split("$")[1].split("#")[0];
        document.getElementById('empSaving2').value=resText.split("$")[1].split("#")[1];
        document.getElementById('empSaving3').value=resText.split("$")[1].split("#")[2];
        document.getElementById('empSaving4').value=resText.split("$")[1].split("#")[3];
        document.getElementById('empSaving5').value=resText.split("$")[1].split("#")[4];
           
        getTEFDetails();
    }
    else
    {
        document.getElementById('resultMessageTefUpdate').innerHTML = "<font color=red size=2px>Please try again later</font>";
    }
//  alert("Updated Successfully");
//  toggleOverlay();
}


function taxExemptionUpdatetoggleOverlay(){

    var overlay = document.getElementById('overlayPayrollListEditTef');
    var specialBox = document.getElementById('specialBoxPayrollListEditTef');
           
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
               
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}
        

function payrollTaxExemptionEdittoggleOverlayPayRollList(id,ExemptionId,SavingsAmount,STATUS,ApprovedAmount,ApproverComments,attachmentName,empId,paymentDate,category){
    //alert("reviewId-->"+ExemptionType);
    document.getElementById('resultMessageTefUpdate').innerHTML =" ";
    // document.getElementById("downloadTr").style.display = 'block';
    //   document.getElementById("uploadTr").style.display = 'none';
    var overlay = document.getElementById('overlayPayrollListEditTef');
    var specialBox = document.getElementById('specialBoxPayrollListEditTef');
    //     hideRow("uploadTr");
    //    showRow("downloadTr");
    //alert("test");
    // var roleName = document.getElementById("roleName").value;
                
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Edit Tax Exemptions";
    var overlay = document.getElementById('overlayPayrollListEditTef');
   
       
   
   
    document.getElementById("load").style.display = 'block';  

    document.getElementById('overlaySavingAmount').value=SavingsAmount;
    //alert(ApprovedAmount);
    if(ApprovedAmount>0){
        document.getElementById('overlayApprovedAmount').value=ApprovedAmount;
         document.getElementById('overlayApprovedAmountHidden').value=ApprovedAmount;
    }
    else{
        document.getElementById('overlayApprovedAmount').value=SavingsAmount;
        document.getElementById('overlayApprovedAmountHidden').value=SavingsAmount;
    }
    // document.getElementById("overLayexemptionType").style.display = 'none';
         
        
    // alert(STATUS);
    document.getElementById("overLayexemptionType").style.display = 'block';
     
     
   document.getElementById("overLaystatus").value= STATUS;
    //showRow("overLaystatusTr");
    document.getElementById("overLayexemptionType").style.display = 'block';
     
    document.getElementById("overLayexemptionType").value =ExemptionId;
    document.getElementById("tefId").value =id;
    document.getElementById("tefEmpId").value =empId;
    document.getElementById("categoryId").value =category;
    document.getElementById("comments").value =ApproverComments;
   // var reqFormatForPayDate = paymentDate.split("-")[1]+"/"+paymentDate.split("-")[2]+"/"+paymentDate.split("-")[0]
    document.getElementById('paymentDateEmp').value=paymentDate;
    
    //downloadFile
    document.getElementById("downloadFile").innerHTML ="";
    //  alert(attachmentName);
    if(attachmentName != "--")
    {
        //  alert("in if");
        
        document.getElementById("downloadFile").innerHTML =attachmentName;
    }
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        
        overlay.style.display = "none";
        specialBox.style.display = "none";
        
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
        document.getElementById("load").style.display = 'none'; 
    }
    

}
function downloadTefAttachment(){
    var tefId =  document.getElementById("tefId").value;
    var empId =document.getElementById("tefEmpId").value;
    downloadTefAttachement(tefId,empId);
    
    
}

function getTefEmpDetails(id){
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerreq(req, ppulateEmpTefDetails); 

    //var url=CONTENXT_PATH+"/updateMyReview.action?reviewId="+reviewId+"&overlayReviewType="+overlayReviewType+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay;
    var url=CONTENXT_PATH+'/getEmpTefDetailsForOverlay.action?tefId='+id+"&q="+Math.random();
    //alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function ppulateEmpTefDetails(resText){
   // alert(resText);
    //id,ExemptionId,SavingsAmount,STATUS,ApprovedAmount,ApproverComments,attachmentName,empId
    var id,ExemptionId,ExemptionType,SavingsAmount,STATUS,ApprovedAmount,paymentDate,ApproverComments,AttachmentName,empId,category,comments,tefType,panNumber,ownerType,validityDate,FinancialYear,RentStartDate,RentEndDate,MonthlyAmount,PolicyNumber,licPremium;
    
    
           
            
            var  resultJson = JSON.parse(resText);
 
 
  
    id = resultJson["Id"];
    ExemptionId = resultJson["ExemptionId"];
    ExemptionType = resultJson["ExemptionType"];
    SavingsAmount = resultJson["SavingsAmount"];
    STATUS = resultJson["STATUS"];
    ApprovedAmount = resultJson["ApprovedAmount"];
    paymentDate = resultJson["PaymentDate"];
    ApproverComments = resultJson["ApproverComments"];
    AttachmentName = resultJson["attachment"];
    empId = resultJson["EmpId"];
    category = resultJson["Category"];
     comments = resultJson["Comments"];
    tefType = resultJson["SavingsType"];
    panNumber = resultJson["PAN_No"];
    ownerType = resultJson["Owner_Name"];
    validityDate = resultJson["ValidityDate"];
    FinancialYear = resultJson["FinancialYear"];
    RentStartDate = resultJson["RentStartDate"];
    RentEndDate = resultJson["RentEndDate"];
    MonthlyAmount = resultJson["MonthlyAmount"];
    PolicyNumber = resultJson["PolicyNumber"];
     licPremium = resultJson["licPremium"];
     var houseAddress = resultJson["HouseAddress"];
    
     Form12BBAttachmentName = resultJson["Form12BBAttachmentName"];
  // alert(AttachmentName)
   
    if(document.getElementById("flagForOverlay").value=="0"){
      //  alert("--->")
      taxExemptionEdittoggleOverlay(id,ExemptionId,ExemptionType,SavingsAmount,STATUS,ApprovedAmount,paymentDate,comments,tefType,panNumber,ownerType,validityDate,AttachmentName,empId,FinancialYear,RentStartDate,RentEndDate,MonthlyAmount,ApproverComments,PolicyNumber,licPremium,Form12BBAttachmentName,houseAddress);
    }
    /*else  if(document.getElementById("flagForOverlay").value=="1"){
       // alert("-------->11")
        payrollTaxExemptionEdittoggleOverlay(id,ExemptionId,SavingsAmount,STATUS,ApprovedAmount,ApproverComments,paymentDate);
    }
    else{
       // alert("------------->111")
        payrollTaxExemptionEdittoggleOverlayPayRollList(id,ExemptionId,SavingsAmount,STATUS,ApprovedAmount,ApproverComments,AttachmentName,empId,paymentDate,category)
    } */
}


function getEmployeePaySlip(){
    (document.getElementById("loadingMessageForFreeze")).style.display = "none";
    //var departmentId=document.getElementById("departmentId").value;
    var empnameById=document.getElementById("empnameById").value;
    var year=document.getElementById("year").value;
    var month=document.getElementById("month").value;
   
    if(empnameById.length==0) {
  
      //  alert("Please select Empname!");
         x0p( '','Please select Empname!','info');
        return false;
    
    }
    if(month=="0") {
  
      //  alert("Please select month");
         x0p( '','Please select month','info');
        return false;
    
    }
    if(year.length==0) {
  
       // alert("Please select year");
         x0p( '','Please select year','info');
        return false;
    
    }
    else{
        var password = prompt("Please give the password encryption for your payslip if you want","");
        var passwordForPdf="";
        if(password!=null)
        {
            passwordForPdf = password;
        }else
        {
            passwordForPdf = " "; 
            
        }
    
        window.location="runEmployeePayslip.action?empnameById="+empnameById+"&year="+year+"&month="+month+"&passwordForPdf="+passwordForPdf;
    }
    
}


function showNoDuesCommentsPopUp(text){
    
    var background = "#3E93D4";
    var title = "Comments";
    var text1 = ""; 
    if(text == "--"){
        text1 = "No Comments Given";
    }
    else{
        text1 = text; 
    }
    var size = text1.length;
    
    //Now create the HTML code that is required to make the popup
    var content = "<html><head><title>"+title+"</title></head>\
    <body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>"+text1+"<br />\
    </body></html>";
    
    if(size < 50){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 100){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 260){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }

}





//fileupoad.js content

function taxExemptiontoggleOverlay(flag){
    document.getElementById('resultMessage').innerHTML = "";
    document.getElementById("downloadTr").style.display = 'none';
    document.getElementById("uploadTr").style.display = '';
    document.getElementById("overLayexemptionType").style.display = 'block';
    document.getElementById("exemptionTypeValue").style.display = 'none';
    document.getElementById("overLaystatusTrForApproved").style.display = 'none';
    document.getElementById("overLaystatusTr").style.display = 'block';
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Add Tax Exemptions";
    document.getElementById("overlaySavingAmount").value="";
    document.getElementById("overLayexemptionType").value="";
      $("#validityId").hide();
        $('#validityDateSpan').hide();
        $("#approvedCommentsTr").hide();
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');
           hideRow("downloadTrForm12BB");
           document.getElementById("Attachment").innerHTML = "Attachment :";
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
        /*var startDate = document.getElementById("startDate").value;
                var endDate = document.getElementById("endDate").value;
                var reviewType = document.getElementById("exemptionType").value;
                var reviewStatus = document.getElementById("status").value;
                */
        // document.frmTaxAssumptionSearch.submit();
        if(flag=='1'){
           // window.location="getEmployeePayRollDashBoard.action";
            document.getElementById("frmTaxAssumptionSearch").submit();
        }
        else
        {
            window.location="getPayRollDashBoard.action";       
        }
        var empnameById="-1";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}

 
   

function ajaxFileUploadTaxAssumption()
{
   
    var overLayexemptionType = document.getElementById('overLayexemptionType').value;
    var overlaySavingAmount = document.getElementById('overlaySavingAmount').value;
    var paymentDateEmp = document.getElementById('paymentDateEmp').value;
    var status = document.getElementById('overLaystatus').value;
   
    var userId= "";
    var tlComments ="";
    var hrComments ="";

    // var validityDate = $('#validityDate').val();
    var tefType = $('#tefType').val();
    var taxcomments = $('#taxcomments').val();
    var ownerName = document.getElementById('ownerName').value;
    var panNumber = document.getElementById('PANNumber').value;
    var fullPath = document.getElementById('file').value;
    var rentStartDate =$("#overLayrentStartDate").val();
    var rentEndDate =$("#overLayrentEndDate").val();
    var monthlyAmount =$("#overlaymonthlyAmount").val();
    var policyNumber =$("#policyNumber").val();
    var licPremium =$("#licPremium").val();
    var houseAddress =$("#houseAddress").val();
    var d = new Date();
    var month = d.getMonth();

    var year = d.getFullYear();
    var day=d.getDate();

    //alert("day==="+day);
    if(month==3 && day<=15){
        year=year-1;
    }

    var sdate = "";
    var edate = "";
    if (month >= 3) {
        sdate = "04/01/" + year;
        edate = "03/31/" + (year + 1);

    } else {
        sdate = "04/01/" + (year - 1);
        edate = "03/31/" + year;

    }
    var startDate=new Date(sdate);
    var endDate=new Date(edate);
    
  
    if(overLayexemptionType==""){
        //document.getElementById('resultMessage').innerHTML = "<font color=red>Please select Exemption type.</font>";
        x0p( '','Please select Exemption type!','info'); 
        return false;
    }
    else if(tefType.length==0){
        // document.getElementById('resultMessage').innerHTML = "<font color=red>Please select type.</font>";
        x0p( '','Please select type!','info'); 
        return false;
    }
    else if(overLayexemptionType=="1" && policyNumber.length==0){
        //  document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter PaymentDate.</font>";
        x0p( '','Please enter policy Number!','info'); 
        return false;
    }
    else if(overLayexemptionType=="1" && licPremium=="0"){
        //  document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter PaymentDate.</font>";
        x0p( '','Please select  Premium Type!','info'); 
        return false;
    }
    else if(overLayexemptionType!="18" && paymentDateEmp.length==0){
        //  document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter PaymentDate.</font>";
        x0p( '','Please enter PaymentDate!','info'); 
        return false;
    }
    
    //else if(validityDate.length==0){
    //       // document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter validity date.</font>";
    //         x0p( '','Please enter validity date!','info'); 
    //        return false;
    //    }
    else if(overlaySavingAmount.length==0){
        // document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter Amount.</font>";
        x0p( '','Please enter Amount!','info'); 
        return false;
    }
    
  //NSC(3),ppf(4),Tuition Fees(5),ELSS(6),POTD(7),Fixed Deposits(8),NPS(12)
    if(overLayexemptionType=="3" || overLayexemptionType=="4" || overLayexemptionType=="5" || overLayexemptionType=="6" || overLayexemptionType=="7" || overLayexemptionType=="8" || overLayexemptionType=="12"){
        if(tefType == 'Declarations'){
            
            x0p( '','Please change type as Actuals !','info'); 
            return false;
            
        }
    }
    if(overLayexemptionType!="18"){
        if(tefType == 'Declarations'){
            var paymentDate=new Date(paymentDateEmp);
            if(paymentDate>startDate){
                x0p( '','You selected type as Declarations. So,PaymentDate should be lees than current financial year !','info'); 
                return false;
            }
        }
        else if(tefType == 'Actuals'){
            var paymentDate=new Date(paymentDateEmp);
            //     alert(startDate);
            //    alert(endDate);
            //     alert((paymentDate<startDate) +" test "+(paymentDate<=endDate));
          
            if(paymentDate<startDate || paymentDate>endDate){
                x0p( '','You selected type as Actuals. So,PaymentDate should be in current financial year !','info'); 
                return false;
            }
        }
    }

    if(overLayexemptionType=="18"){
        if(tefType == 'Declarations'){
    
     
            x0p( '','Please change type as Actuals for House Rent Paid-80GG !','info'); 
            return false;
        }
        var renstartDate=new Date(rentStartDate);
        var renendDate=new Date(rentEndDate);

        if(renstartDate>renendDate){
            x0p( '','End date should be greater than start date!','info'); 
            return false;
        }
        if(monthlyAmount.trim()==""){
            x0p( '','Please enter monthly amount!','info'); 
            return false;
        }
         if(ownerName.length == 0 || ownerName == " "){
            //   document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter OwnerName.</font>";
            x0p( '','Please enter OwnerName!','info');
            return false;
        }
        if(panNumber.length == 0 || panNumber == " "){
            // document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter panNumber.</font>";
            x0p( '','Please enter panNumber!','info');
            return false;
        }
        if(houseAddress.trim() == ''){
    
     
            x0p( '','Please enter House Address !','info'); 
            return false;
        }
    }
    if(tefType == 'Declarations' && fullPath.length == 0){
        x0p( '','Please attach previous financial year receipts for Declarations!','info'); 
        // document.getElementById('resultMessage').innerHTML = "<font color=red>Please attach a file for Actuals.</font>";
        
        return false
    }
    else if(tefType == 'Actuals' && fullPath.length == 0){
        // document.getElementById('resultMessage').innerHTML = "<font color=red>Please attach a file for Actuals.</font>";
        x0p( '','Please attach a file for Actuals!','info'); 
        return false
    }
    if(overLayexemptionType=="18"){
        if(document.getElementById("uploadTrForm12BB").style.visibility == "visible"){
            var file1 = document.getElementById('file1').value;  
            // alert("===="+file1.value+"====")
            // alert(file1.length)
            document.getElementById("load").style.display = 'none';
            if(file1.length == 0){
        
         
                x0p( '','Please attach a file for Form12BB!','info');
               
                return false;
            }
        }
    }
     document.getElementById("addButton").disabled=false;
    var financialYear =$("#financialYear").val();
    // alert('addTaxAssumption.action?overLayExemptionType='+overLayexemptionType+'&overlaySavingAmount='+overlaySavingAmount+'&paymentDateEmp='+paymentDateEmp+'&tefType='+tefType+'&comments='+taxcomments+'&status='+status +'&ownerName='+ownerName+'&panNumber='+panNumber+ '&rentStartDate='+rentStartDate+'&rentEndDate='+rentEndDate+'&monthlyAmount='+monthlyAmount+'&financialYear='+financialYear);
    setTimeout(disableFunction,1);
    document.getElementById("load").style.display = 'block';
        
        
    if(window.XMLHttpRequest){
        xmlHttpRequest=new XMLHttpRequest();
    }
    else if(window.ActiveXObject){
        xmlHttpRequest=new ActiveXObject("Microsoft.XMLHttp");
          
    }
    else{
        alert("browser does not support ajax");
        return false;
    }
    xmlHttpRequest.onreadystatechange=function(){
            
        if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200) {
            //document.getElementById("msg").innerHTML =data;
            var data=xmlHttpRequest.responseText;
            //  alert(data +"  "+data.indexOf("uploaded"));
            var displaymessage = "<font color=red>Please try again later</font>";
          
            if(data=="uploaded"){
               
                displaymessage = "<font color=green>Tax exemption added successfully.</font>";
            }
            if(data=="Notvalid"){
                
                displaymessage = "<font color=red>Not a valid file!,Please check the file and try again.</font>";
            }
            if(data=="Error"){
               
                displaymessage = "<font color=red>Internal Error!, Please try again later.</font>";
                document.getElementById("addButton").disabled=false;
            }
            if(data=="existed"){
                displaymessage = "<font color=red>You have already added for the given dates.For queries,Contact payroll team</font>";
            }
           
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = displaymessage;//"<font color=green>File uploaded successfully</font>";
        //    alert(data);
       
        }
    };
     
       

     
    var data = new FormData();
    var file = document.getElementById('file')
    data.append('file', file.files[0]);
    if(document.getElementById("uploadTrForm12BB").style.visibility == "visible"){
        var file1 = document.getElementById('file1');  
        // alert("===="+file1.value+"====")
        if(file1.value.length != 0){
        
        
            data.append('file1', file1.files[0]);
        }
    }
    var url='addTaxAssumption.action?overLayExemptionType='+overLayexemptionType+'&overlaySavingAmount='+overlaySavingAmount+'&paymentDateEmp='+paymentDateEmp+'&tefType='+tefType+'&comments='+taxcomments+'&status='+status +'&ownerName='+ownerName+'&panNumber='+panNumber+ '&rentStartDate='+rentStartDate+'&rentEndDate='+rentEndDate+'&monthlyAmount='+monthlyAmount+'&financialYear='+financialYear+'&policyNumber='+policyNumber+"&licPremium="+licPremium+'&houseAddress='+escape(houseAddress)+'&q='+new Date();
       
    // alert(url);
    //'timsheetAttachmentUpload.action?empId='+empId+"&timeSheetID="+timeSheetID,
    xmlHttpRequest.open("POST", url, true);
	    
    xmlHttpRequest.send(data);
   
    return false;

}


		

function upadteTaxExemption(){
    var id = document.getElementById('id').value;
    document.getElementById("overLayexemptionType").disabled=false;
    //  alert(isExists)
    var overLayexemptionType = document.getElementById('overLayexemptionType').value;
    var overLaystatus = document.getElementById('overLaystatus').value;
    var previousOverlayAmount = document.getElementById('previousOverlayAmount').value;
    var previousoverLaystatus = document.getElementById('previousoverLaystatus').value;
    // alert(overLaystatus+"....."+previousoverLaystatus)
    var overlaySavingAmount = document.getElementById('overlaySavingAmount').value;
    var exemptionId= document.getElementById("exemptionId").value; 
    var paymentDateEmp = document.getElementById("paymentDateEmp").value;
    //var validityDate = $('#validityDate').val();
    var tefType = $('#tefType').val();
    var fullPath = document.getElementById('file').value;
    // alert(fullPath)
    var taxcomments = $('#taxcomments').val();
    var ownerName = document.getElementById('ownerName').value;
    var panNumber = document.getElementById('PANNumber').value;
    var rentStartDate =$("#overLayrentStartDate").val();
    var rentEndDate =$("#overLayrentEndDate").val();
    var monthlyAmount =$("#overlaymonthlyAmount").val();
    var policyNumber =$("#policyNumber").val();
    var licPremium =$("#licPremium").val();
    var Form12BBAttachmentName =$("#Form12BBAttachmentName").val();
    var AttachmentName =$("#AttachmentName").val();
    var houseAddress =$("#houseAddress").val();
    document.getElementById('resultMessage').innerHTML='';
    var data = new FormData();
    var d = new Date();
    var month = d.getMonth();

    var year = d.getFullYear();
    var day=d.getDate();

    //alert("day==="+day);
    if(month==3 && day<=15){
        year=year-1;
    }
    var sdate = "";
    var edate = "";
    if (month >= 3) {
        sdate = "04/01/" + year;
        edate = "03/31/" + (year + 1);

    } else {
        sdate = "04/01/" + (year - 1);
        edate = "03/31/" + year;

    }
    var startDate=new Date(sdate);
    var endDate=new Date(edate);
    // if(document.getElementById("panDetailsTr").style.visibility == "visible"){
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerTxt(req,ppulateMyReviewUpadteResult12); 
    if(overLayexemptionType==""){
        // document.getElementById('resultMessage').innerHTML = "<font color=red>Please select Exemption type.</font>";
        x0p( '','Please select Exemption type!','info');
        return false;
    }

    else if(overlaySavingAmount.length==0){
        //  document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter Amount.</font>";
        x0p( '','Please enter Amount!','info');
        return false;
    }
    else if(overLayexemptionType=="1" && policyNumber.length==0){
        //  document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter PaymentDate.</font>";
        x0p( '','Please enter policy number!','info');
        return false;
    }
    else if(overLayexemptionType=="1" && licPremium==0){
        //  document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter PaymentDate.</font>";
        x0p( '','Please select Policy Premium!','info');
        return false;
    }
    else if(overLayexemptionType!="18" && paymentDateEmp.length==0){
        //  document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter PaymentDate.</font>";
        x0p( '','Please enter PaymentDate!','info');
        return false;
    }
    //     else if(validityDate.length==0){
    //       // document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter validity date.</font>";
    //        x0p( '','Please enter validity date!','info');
    //        return false;
    //    }
    else if(tefType.length==0){
        //    document.getElementById('resultMessage').innerHTML = "<font color=red>Please select type.</font>";
        x0p( '','Please select type!','info');
        return false;
    }
    //alert("previous"+previousOverlayAmount)
     
      
          
    if(overLaystatus == 'Applied'){
        if(document.getElementById("uploadTrForm12BB").style.visibility == "visible"){
            var file1 = document.getElementById('file1').value;  
            //  alert("//"+Form12BBAttachmentName+"//")
            if((file1.length == 0 && previousOverlayAmount < 100000)|| (file1.length == 0 && Form12BBAttachmentName.trim() == '--' )){
                //  alert(data)
                document.getElementById("load").style.display = 'none';
                x0p( '','Please attach a file for Form12BB!','info');
                document.getElementById("addButton").disabled=false;
                return false;
            }
        } 
        var file = document.getElementById('file').value; 
        if(file.length == 0 && AttachmentName.trim() == '--'){
            x0p( '','Please attach a file for '+tefType,'info');
            return false;
        }
    }
    
      
   //NSC(3),ppf(4),Tuition Fees(5),ELSS(6),POTD(7),Fixed Deposits(8),NPS(12)
    if(overLayexemptionType=="3" || overLayexemptionType=="4" || overLayexemptionType=="5" || overLayexemptionType=="6" || overLayexemptionType=="7" || overLayexemptionType=="8" || overLayexemptionType=="12"){
        if(tefType == 'Declarations'){
            
            x0p( '','Please change type as Actuals !','info'); 
            return false;
            
        }
    }

	
    
    if(overLayexemptionType!="18"){
        if(tefType == 'Declarations'){
            var paymentDate=new Date(paymentDateEmp);
            if(paymentDate>startDate){
                x0p( '','You selected type as Declarations. So,PaymentDate should be less than current financial year!','info'); 
                return false;
            }
        }
        else if(tefType == 'Actuals'){
            var paymentDate=new Date(paymentDateEmp);
            //     alert(startDate);
            //    alert(endDate);
            //     alert((paymentDate<startDate) +" test "+(paymentDate<=endDate));
          
            if(paymentDate<startDate || paymentDate>endDate){
                x0p( '','You selected type as Actuals. So,PaymentDate should be in current financial year !','info'); 
                return false;
            }
        }
    }
    if(overLayexemptionType=="18"){
        var renstartDate=new Date(rentStartDate);
        var renendDate=new Date(rentEndDate);

        if(renstartDate>renendDate){
            x0p( '','End date should be greater than start date!','info'); 
            return false;
        }
        if(monthlyAmount.trim()==""){
            x0p( '','Please enter monthly amount!','info'); 
            return false;
        }
       
        if(ownerName.length == 0 || ownerName == " "){
            //   document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter OwnerName.</font>";
            x0p( '','Please enter OwnerName!','info');
            return false;
        }
        if(panNumber.length == 0 || panNumber == " "){
            // document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter panNumber.</font>";
            x0p( '','Please enter panNumber!','info');
            return false;
        }
         
   
        if(houseAddress.trim()==""){
            x0p( '','Please enter House Address!','info'); 
            return false;
        }
        
    }
    if(overLayexemptionType=="18" && tefType == 'Declarations'){
    
     
        x0p( '','Please change type as Actuals for House Rent Paid-80GG !','info'); 
        return false;
          
    }
    if((previousoverLaystatus == 'Approved' || previousoverLaystatus == 'Rejected') && overLaystatus=="Applied" ){
        if(tefType == 'Declarations' && fullPath.length == 0 && AttachmentName.trim() == '--'){
            x0p( '','Please attach previous financial year receipts for Declarations!','info'); 
            // document.getElementById('resultMessage').innerHTML = "<font color=red>Please attach a file for Actuals.</font>";
        
            return false
        }
        else if(tefType == 'Actuals' && fullPath.length == 0 && previousoverLaystatus != 'Rejected' && AttachmentName.trim() == '--'){
            // document.getElementById('resultMessage').innerHTML = "<font color=red>Please attach a file for Actuals.</font>";
            x0p( '','Please attach a file for Actuals!','info'); 
            return false
        }
    }
    var data = new FormData();
    var file = document.getElementById('file')
    //  alert("==//=="+file.value+"==//==")
    if(file.value.length != 0){
        //  alert(file.value.length)
     
        data.append('file', file.files[0]);
    }
    if(document.getElementById("uploadTrForm12BB").style.visibility == "visible"){
        var file1 = document.getElementById('file1');  
        // alert("===="+file1.value+"====")
        if(file1.value.length != 0){
            data.append('file1', file1.files[0]);
        }
    }
    var financialYear =$("#financialYear").val();
    if(window.XMLHttpRequest){
        xmlHttpRequest=new XMLHttpRequest();
    }
    else if(window.ActiveXObject){
        xmlHttpRequest=new ActiveXObject("Microsoft.XMLHttp");
          
    }
    else{
        alert("browser does not support ajax");
        return false;
    }
    xmlHttpRequest.onreadystatechange=function(){
            
        if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200) {
            //    document.getElementById("msg").innerHTML =data;
            var data=xmlHttpRequest.responseText;
             document.getElementById("overLayexemptionType").disabled=true;
            //  alert(data +"  "+data.indexOf("uploaded"));
            var displaymessage = "<font color=red>Please try again later</font>";
          
            if(data=="updated"){
               
                displaymessage = "<font color=green>Tax exemption updated Successfully.</font>";
            }
            if(data=="Notvalid"){
                
                displaymessage = "<font color=red>Not a valid file!,Please check the file and try again.</font>";
            }
            if(data=="Error"){
               
                displaymessage = "<font color=red>Internal Error!, Please try again later.</font>";
                document.getElementById("addButton").disabled=false;
            }
            if(data=="existed"){
                displaymessage = "<font color=red>Sorry! already you submited the record for given dates. Please contact to payroll team</font>";
            }
           
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = displaymessage;//"<font color=green>File uploaded successfully</font>";
        //    alert(data);
       
        }
    };
     
       

     
    document.getElementById("load").style.display = 'block';
    
        
    var url='upadteTaxExemption.action?overLayExemptionType='+overLayexemptionType+'&overlaySavingAmount='+overlaySavingAmount+'&overLayStatus='+overLaystatus+'&exemptionId='+exemptionId+"&paymentDateEmp="+paymentDateEmp+"&tefType="+tefType+"&comments="+taxcomments+"&ownerName="+ownerName+"&panNumber="+panNumber+'&rentStartDate='+rentStartDate+'&rentEndDate='+rentEndDate+'&monthlyAmount='+monthlyAmount+'&financialYear='+financialYear+"&policyNumber="+policyNumber+"&licPremium="+licPremium+"&q="+Math.random()+"&id="+id+"&houseAddress="+escape(houseAddress)+'&q='+new Date();
       
    //alert(url);
    //'timsheetAttachmentUpload.action?empId='+empId+"&timeSheetID="+timeSheetID,
    xmlHttpRequest.open("POST", url, true);
	    
    xmlHttpRequest.send(data);
    
/*$.ajaxFileUpload({
            url:'upadteTaxExemption.action?overLayExemptionType='+overLayexemptionType+'&overlaySavingAmount='+overlaySavingAmount+'&overLayStatus='+overLaystatus+'&exemptionId='+exemptionId+"&paymentDateEmp="+paymentDateEmp+"&tefType="+tefType+"&comments="+taxcomments+"&ownerName="+ownerName+"&panNumber="+panNumber+'&rentStartDate='+rentStartDate+'&rentEndDate='+rentEndDate+'&monthlyAmount='+monthlyAmount+'&financialYear='+financialYear,
            secureuri:false,//false
            fileElementId:'file',//id  <input type="file" id="file" name="file" />
            dataType: 'json',// json
            success: function(data,status){
            
                var displaymessage = "<font color=red>Please try again later</font>";
          
                if(data.indexOf("updated")>0){
               
                    displaymessage = "<font color=green>TEF updated Successfully.</font>";
                }
                if(data.indexOf("Notvalid")>0){
                
                    displaymessage = "<font color=red>Not a valid file!,Please check the file and try again.</font>";
                }
                 if(data.indexOf("Error")>0){
               
                    displaymessage = "<font color=red>Internal Error!, Please try again later.</font>";
                    document.getElementById("addButton").disabled=false;
                }
                if(data.indexOf("existed")>0){
                      displaymessage = "<font color=red>Sorry! already you submited the record for given dates. Please contact to payroll team</font>";
                }
           
                document.getElementById("load").style.display = 'none';
                document.getElementById('resultMessage').innerHTML = displaymessage;//"<font color=green>File uploaded successfully</font>";
        
            },
            error: function(e){
            
                document.getElementById("load").style.display = 'none';
                document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
       
            }
        }); */
    
    
//  var url=CONTENXT_PATH+'/upadteTaxExemption.action?overLayExemptionType='+overLayexemptionType+'&overlaySavingAmount='+overlaySavingAmount+'&overLayStatus='+overLaystatus+'&exemptionId='+exemptionId+"&paymentDateEmp="+paymentDateEmp+"&tefType="+tefType+"&comments="+taxcomments+"&ownerName="+ownerName+"&panNumber="+panNumber+'&rentStartDate='+rentStartDate+'&rentEndDate='+rentEndDate+'&monthlyAmount='+monthlyAmount+'&financialYear='+financialYear;
//alert(url);
//   req.open("GET",url,"true");
//   req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
//   req.send(null);
    
}



function ppulateMyReviewUpadteResult12(resText){
    document.getElementById('resultMessage').innerHTML = "<font color=green size=2px>Updated Successfully</font>";
  
}


//function getToDate(){
//    var fromDate = document.getElementById('fromDate').value;
//    
//
//    var req = newXMLHttpRequest();
//    req.onreadystatechange = readyStateHandlerTxt(req,getToDateResponse); 
//
//    //var url=CONTENXT_PATH+"/updateMyReview.action?reviewId="+reviewId+"&overlayReviewType="+overlayReviewType+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay;
//    var url=CONTENXT_PATH+'/getEndDate.action?startDate='+fromDate;
//    //alert(url);
//    req.open("GET",url,"true");
//    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
//    req.send(null);
//}
//function getToDateResponse(resText){
//    //alert(resText);
//    document.getElementById('toDate').value = resText;
//  
//}

function getAmount(){
    if(document.getElementById('settled').checked==true)
        document.getElementById('AmountTd').style.display='inline';
    
    else{
        
        document.getElementById("dueAmount").value="0.00";
        document.getElementById('AmountTd').style.display='none';
    }
}

function readyStateHandlerTxt(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
               
                responseTextHandler(req.responseText);
            }
            else {
                
              //  alert("HTTP error ---"+req.status+" : "+req.statusText);
                 x0p( '','HTTP error ---'+req.status+' : '+req.statusText,'info');
            }
        }else {
          
        }
    }
}
function hideRow(id) {
    //alert(id);
    var row = document.getElementById(id);
    row.style.display = 'none';
}
function showRow(id) {
    //  alert(id);
    var row = document.getElementById(id);
    row.style.display = '';
} 

 
function taxExemptionEdittoggleOverlay(id,ExemptionId,ExemptionType,SavingsAmount,STATUS,ApprovedAmount,paymentDate,comments,tefType,panNumber,ownerType,validityDate,AttachmentName,empId,FinancialYear,RentStartDate,RentEndDate,MonthlyAmount,ApproverComments,PolicyNumber,licPremium,Form12BBAttachmentName,houseAddress){
 
    document.getElementById("id").value=id;
    //    document.getElementById("isExists").value = isExists;
    document.getElementById('resultMessage').innerHTML = "";
    hideRow("downloadTrForm12BB");
    document.getElementById("uploadTrForm12BB").style.visibility = 'hidden';
    //    if(document.getElementById("uploadTrForm12BB").style.visibility == "visible"){
    //       alert(1)
    //      hideRow("downloadTrForm12BB");
    //       if(STATUS == 'Rejected'){
    //          alert(2)
    //           showRow("downloadTrForm12BB");
    //      }
    //   }
  
    if((STATUS=='Approved' || STATUS == 'ValidityExpired') && tefType == 'Declarations'){
        var tefTypeChange = document.getElementById("tefType");
        tefTypeChange.onchange = function(){
            // alert('test');
            var selectedString = tefTypeChange.options[tefTypeChange.selectedIndex].value;
            //  alert(selectedString);
  
            if(selectedString == 'Actuals'){
                document.getElementById("stateLabel").innerHTML = "<font color='green' size='2px'>Applied</font>";
                document.getElementById("update").style.display="block";
                showRow("uploadTr");
                showRow("downloadTr");
                document.getElementById("Attachment").innerHTML = "Update Attachment :";
                $("#buttonsTd").hide();
            //   $("#saveButtons").hide();
            // document.getElementById("saveButtons").style.display = 'none';
            //         var resetButton = document.getElementsByName("resetButton");
            //       saveButton.style.display = "none";
            //       resetButton.style.display = "none";
         
     
            }
            else{
                document.getElementById("stateLabel").innerHTML = "<font color='green' size='2px'>"+STATUS+"</font>";
                document.getElementById("update").style.display="none";
                hideRow("uploadTr");
                showRow("downloadTr");
            }
        }
    }
 
    document.getElementById("previousOverlayAmount").value= SavingsAmount;
    document.getElementById("downloadTr").style.display = 'none';
    document.getElementById("uploadTr").style.display = '';
          
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');
    hideRow("uploadTr");
    showRow("downloadTr");
           
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Edit Tax Exemptions";
    var overlay = document.getElementById('overlay');
    //  alert("Form12BBAttachmentName"+Form12BBAttachmentName)
    //        if(Form12BBAttachmentName != "" && Form12BBAttachmentName != null) {
    //        alert("Form12BBAttachmentName.length"+Form12BBAttachmentName.length)
    //           document.getElementById("uploadTrForm12BB").style.visibility = 'hidden';
    //       }else{
    //        document.getElementById("uploadTrForm12BB").style.visibility = 'visible';
    //       }
   
    document.getElementById("load").style.display = 'block';  
    

    document.getElementById('overlaySavingAmount').value=SavingsAmount;
    document.getElementById('tefType').value=tefType;
    document.getElementById('empIdForOverlay').value=empId;
    document.getElementById('ownerName').value=ownerType;
    document.getElementById('PANNumber').value=panNumber;
    document.getElementById('taxcomments').value=comments;
    
    //   var reqFormatForPayDate = paymentDate.split("-")[1]+"/"+paymentDate.split("-")[2]+"/"+paymentDate.split("-")[0]
    document.getElementById('paymentDateEmp').value=paymentDate;
    document.getElementById('financialYear').value=FinancialYear;
    document.getElementById('overLayrentStartDate').value=RentStartDate;
    document.getElementById('overLayrentEndDate').value=RentEndDate;
    document.getElementById('overlaymonthlyAmount').value=MonthlyAmount;
    document.getElementById('approvedComments').value=ApproverComments;
    document.getElementById('previousoverLaystatus').value=STATUS;
    document.getElementById('policyNumber').value=PolicyNumber;
    document.getElementById('licPremium').value=licPremium;
    document.getElementById('houseAddress').value=houseAddress;
      
    if(STATUS == 'Applied'){
        document.getElementById("Attachment").innerHTML = "Update Attachment :";
        showRow("uploadTr");
        hideRow("buttonsTd");
    }
    if(STATUS=='Approved' || STATUS == 'Rejected') {
    
        $("#approvedCommentsTr").show();
    }else{
        $("#approvedCommentsTr").hide();
    }
    
    $('#validityDateSpan').html("<font color=green size=2px>"+validityDate+"</font>");
    document.getElementById("validDate").value = validityDate;
    if(ExemptionId=="18"){
        $("#paymentDateTr").hide();
        $("#rentAmmount").show();
        $("#rentDatesId").show();
        $("#validityId").hide();
        $('#validityDateSpan').hide();
        $('#houseTr').show();
        $('#panDetailsTr').show();
        if(SavingsAmount > 100000 && STATUS == 'Applied'){
            showRow("downloadTrForm12BB");
            document.getElementById("uploadTrForm12BB").style.visibility = 'visible';
            document.getElementById("Form12BB").innerHTML = "Update Form12BB :";
        }
        if(SavingsAmount > 100000 && (STATUS == 'Rejected' || STATUS == 'Approved')){
            showRow("downloadTrForm12BB");
        }
        
        document.getElementById("savingAmountLable").innerHTML="Total&nbsp;Rent&nbsp;Projected&nbsp;:";
        document.getElementById("overlaySavingAmount").readOnly=true;
        setRentSratDateValues(document.getElementById('overLayrentStartDate'),RentStartDate);
        setRentEndDateValues(document.getElementById('overLayrentEndDate'),RentEndDate);
    }else{
        $("#paymentDateTr").show();
        $("#rentAmmount").hide();
        $("#rentDatesId").hide();
        $('#houseTr').hide();
        $('#panDetailsTr').hide();
        document.getElementById("savingAmountLable").innerHTML="AppliedAmount&nbsp;:"; 
        document.getElementById("overlaySavingAmount").readOnly=false;
    }
 
    //  var validityDateFormat = validityDate.split("-")[1]+"/"+validityDate.split("-")[2]+"/"+validityDate.split("-")[0]
    // document.getElementById('validityDate').value=validityDate;
      
    /*    if(ownerType.trim()!=""){
             document.getElementById("panDetailsTr").style.visibility = 'visible';
        }else{
             document.getElementById("panDetailsTr").style.visibility = 'hidden';
        }*/
        
    if(AttachmentName.trim()!="--"){
        document.getElementById("downloadSpan").innerHTML="<a href=javascript:taxExemptiondownloadFile("+id+");>"+AttachmentName+"</a>";
    }else{
        document.getElementById("downloadSpan").innerHTML="-";
    }
    //   alert(Form12BBAttachmentName)
    if(Form12BBAttachmentName.trim()!="--"){
        
        document.getElementById("downloadSpanForm12BB").innerHTML="<a href=javascript:taxExemptiondownloadFileForm12BB("+id+");>"+Form12BBAttachmentName+"</a>";
    }else{
        document.getElementById("downloadSpanForm12BB").innerHTML="-";
    }
    document.getElementById('Form12BBAttachmentName').value=Form12BBAttachmentName;
    document.getElementById('AttachmentName').value=AttachmentName;
    if(ExemptionId=="1"){
        $("#policyTdLable").show(); 
        $("#policyTdValue").show(); 
        $("#licPremiumTR").show(); 
    }else{
        $("#policyTdLable").hide();
        $("#policyTdValue").hide();
        $("#licPremiumTR").hide(); 
    }
   //  alert(STATUS);
   document.getElementById("overLayexemptionType").disabled=true;
    if(STATUS=='Applied'){
        // document.getElementById("overLaystatus").style.display = 'block';

       
        document.getElementById("overLaystatusTr").style.display = 'block';
        document.getElementById("overLaystatusTrForApproved").style.display = 'none';
        document.getElementById("overLayexemptionType").style.display = 'block';
     
        document.getElementById("overLayexemptionType").value =ExemptionId;
        document.getElementById("exemptionId").value =id;
        document.getElementById("update").style.display="block";
       
    }
    else {
        
        document.getElementById("overLaystatusTr").style.display = 'none';
        document.getElementById("overLayexemptionType").value =ExemptionId;
        document.getElementById("exemptionId").value =id;
        document.getElementById("overLaystatusTrForApproved").style.display = 'block';
        document.getElementById("stateLabel").innerHTML = "<font color='green' size='2px'>"+STATUS+"</font>";
        document.getElementById("exemptionTypeValue").style.display = 'block';
        //  document.getElementById("exemptionTypeValue").innerHTML = "<font color='green' size='2px'>"+ExemptionType+"</font>";
        document.getElementById("update").style.display="none";
        if(STATUS=='Rejected'){
            document.getElementById("overLaystatusTr").style.display = 'block';
            document.getElementById("overLaystatusTrForApproved").style.display = 'none';
            // document.getElementById("overLaystatus").contenteditable = false;
            document.getElementById("overLaystatus").disabled=false;
            document.getElementById("overLaystatus").value=STATUS;
            var overLaystatusChange = document.getElementById("overLaystatus");
            overLaystatusChange.onchange = function(){
                //alert('test1');
                var selectedString = overLaystatusChange.options[overLaystatusChange.selectedIndex].value;
    
                //alert(STATUS);
  
                if(selectedString == 'Applied'){
                    if(ExemptionId=="18" && SavingsAmount > 100000){
                        document.getElementById("uploadTrForm12BB").style.visibility = 'visible';
                        document.getElementById("Form12BB").innerHTML = "Update Form12BB :";
                    }
                    document.getElementById("stateLabel").innerHTML = "<font color='green' size='2px'>Applied</font>";
                    document.getElementById("update").style.display="block";
                    document.getElementById("Attachment").innerHTML = "Update Attachment :";
                    showRow("uploadTr");
                    showRow("downloadTr");
                    $("#buttonsTd").hide();
                //   $("#saveButtons").hide();
                // document.getElementById("saveButtons").style.display = 'none';
                //         var resetButton = document.getElementsByName("resetButton");
                //       saveButton.style.display = "none";
                //       resetButton.style.display = "none";
         
     
                }
                else{
                    document.getElementById("stateLabel").innerHTML = "<font color='green' size='2px'>"+STATUS+"</font>";
                    document.getElementById("update").style.display="none";
                    hideRow("uploadTr");
                    showRow("downloadTr");
                }
            }
        }
    }
    
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        
        overlay.style.display = "none";
        specialBox.style.display = "none";
        
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
        document.getElementById("load").style.display = 'none'; 
    }


}


function taxExemptiondownloadFile(id){
    window.location = "taxExemptionDownload.action?exemptionId="+id;
}

/*function readyStateHandlerXml(req,responseXmlHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                responseXmlHandler(req.responseXML);
            } else {
                alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }
    }
}*/
function getNamesByDesignation(flag){
    var url;
    var isTeamLead=0;
    var isManager=0;
 //   var deptName = document.getElementById("departmentId").value;
    document.getElementById("empUserId").value = flag;
    if(document.getElementById("isManager").checked==true && document.getElementById("isTeamLead").checked==true  ){
    
        isManager=1;
        isTeamLead=1;
    
    }
    else {
        if(document.getElementById("isManager").checked){
            isManager=1;
        }
        if(document.getElementById("isTeamLead").checked){
            isTeamLead=1;
        }
    }
    url = CONTENXT_PATH+"/getNamesByDesignation.action?isManager="+isManager+"&isTeamLead="+isTeamLead;
    // alert(url);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerXml(req, populateEmployeesByDept12);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null); 
   
        
      
}

function populateEmployeesByDept12(resXML) {
    //   var reportsTo = document.employeeForm.reportsTo;
    // alert(resXML);
    var empId = document.getElementById("userId");
    var team = resXML.getElementsByTagName("TEAM")[0];
    var users = team.getElementsByTagName("USER");
    empId.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("userId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        empId.appendChild(opt);
    }
    //alert(document.getElementById("empUserId").value);
    if(document.getElementById("empUserId").value =='0'){
        setEmpNameSelectBoxValue(); 
    }
}

function setEmpNameSelectBoxValue(){
    document.getElementById("userId").value= document.getElementById("empUserIdValue").value;
    
}

function upadtePayrollTaxExemption(){
    var overLayexemptionType = document.getElementById('overLayexemptionType').value;
    var overLaystatus = document.getElementById('overLaystatus').value;
    var overlayApprovedAmount = document.getElementById('overlayApprovedAmount').value;
    var overlaySavingAmount= document.getElementById("overlaySavingAmount").value;
    var exemptionId= document.getElementById("exemptionId").value;
   
    //   var yearOverlay= document.getElementById("yearOverlay").value; 
    //  var monthOverlay= document.getElementById("monthOverlay").value; 
    var comments= document.getElementById("comments").value;
    var paymentDateEmp = document.getElementById("paymentDateEmp").value;
    if(overLayexemptionType==""){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please select Exemption type.</font>";
        return false;
    }

    else if(overlayApprovedAmount.length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter Approved Amount.</font>";
        return false;
    }
    else if(overlaySavingAmount.length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please  enter Savings Amount</font>";
        return false;
    }
    else if(paymentDateEmp.length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please  enter payment date</font>";
        return false;
       
    }
    else if(overlaySavingAmount<overlayApprovedAmount){
        document.getElementById("overlayApprovedAmount").style.background='transperant';
        document.getElementById("overlayApprovedAmount").focus();
        document.getElementById('overlayApprovedAmount').value="0.00";
        document.getElementById('resultMessage').innerHTML = "<font color=red>Approved Amount should be less than or equal to Applied Amount.</font>";
        return false;
    }
    else
    {
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerTxt(req,ppulateMyReviewUpadteResult12); 

        //var url=CONTENXT_PATH+"/updateMyReview.action?reviewId="+reviewId+"&overlayReviewType="+overlayReviewType+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay;
        var url=CONTENXT_PATH+'/upadtePayrollTaxExemption.action?overLayExemptionType='+overLayexemptionType+'&overlayApprovedAmount='+overlayApprovedAmount+'&overLayStatus='+overLaystatus+'&comments='+comments+'&exemptionId='+exemptionId+"&paymentDateEmp="+paymentDateEmp;
        // alert(url);
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
   
    }
}


function taxExemptiondownloadFileFortheRolePayRoll(id){
    window.location = "taxExemptionDownloadForRolePayroll.action?exemptionId="+id;
}
function payrollTaxExemptionEdittoggleOverlay(id,ExemptionId,SavingsAmount,STATUS,ApprovedAmount,ApproverComments,paymentDate){
    //alert("reviewId-->"+ExemptionType);
 
    document.getElementById("downloadTr").style.display = 'block';
    document.getElementById("uploadTr").style.display = 'none';
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');
    hideRow("uploadTr");
    showRow("downloadTr");
 // alert("test");
    // var roleName = document.getElementById("roleName").value;
                
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Edit Tax Exemptions";
    var overlay = document.getElementById('overlay');
   
       
   
   
    document.getElementById("load").style.display = 'block';  

    document.getElementById('overlaySavingAmount').value=SavingsAmount;
    //alert(ApprovedAmount);
    if(ApprovedAmount>0){
        document.getElementById('overlayApprovedAmount').value=ApprovedAmount;
    }
    else{
        document.getElementById('overlayApprovedAmount').value=SavingsAmount;
    }
    // document.getElementById("overLayexemptionType").style.display = 'none';
         
        
    // alert(STATUS);
    document.getElementById("overLayexemptionType").style.display = 'block';
     
     
    // document.getElementById("overLaystatus").style.display = 'block';
    //showRow("overLaystatusTr");
    document.getElementById("overLayexemptionType").style.display = 'block';
     
    document.getElementById("overLayexemptionType").value =ExemptionId;
    document.getElementById("exemptionId").value =id;
    document.getElementById("comments").value =ApproverComments;
 //   var reqFormatForPayDate = paymentDate.split("-")[1]+"/"+paymentDate.split("-")[2]+"/"+paymentDate.split("-")[0]
    document.getElementById('paymentDateEmp').value=paymentDate;
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        
        overlay.style.display = "none";
        specialBox.style.display = "none";
        
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
        document.getElementById("load").style.display = 'none'; 
    }


}
function checkCategoryWiseSavingsAmt(eve){
    var approvedAmt = eve.value;
    var tefId =  document.getElementById("tefId").value ;
    var empId = document.getElementById("tefEmpId").value;
    var overLayexemptionType = document.getElementById("overLayexemptionType").value;
    var payRollId = document.getElementById('payRollId').value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerreq(req,ppulateAckCheck); 

    //var url=CONTENXT_PATH+"/updateMyReview.action?reviewId="+reviewId+"&overlayReviewType="+overlayReviewType+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay;
    var url=CONTENXT_PATH+'/checkCategoryWiseSavingsAmt.action?overlayApprovedAmount='+approvedAmt+'&tefId='+tefId+'&empId='+empId+"&overLayExemptionType="+overLayexemptionType+"&payRollId="+payRollId;
    // alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    
}

function ppulateAckCheck(res){
    if(res.split("#")[0] == "Fail"){
        if(res.split("#")[1] == "1"){
          //  alert("Emp Savings1 should not exceed 150000");
             x0p( '','Emp Savings1 should not exceed 150000','info');
        }
        else if(res.split("#")[1] == "2"){
           // alert("Emp Savings2 should not exceed 200000");
             x0p( '','Emp Savings2 should not exceed 200000','info');
        }
        if(res.split("#")[1] == "3" && document.getElementById("overLayexemptionType").value == "15"){
           // alert("Emp Savings3 for selected exemtion should not exceed 20000");
             x0p( '','Emp Savings3 for selected exemtion should not exceed 20000','info');
        }
        else if(res.split("#")[1] == "3" && document.getElementById("overLayexemptionType").value == "16"){
         //   alert("Emp Savings3 for selected exemtion should not exceed 15000");
             x0p( '','Emp Savings3 for selected exemtion should not exceed 15000','info');
        }
    
        document.getElementById("overlayApprovedAmount").value = "0.00";
        setTimeout(function(){
            document.overlayPayrollListTef.overlayApprovedAmount.focus()
        }, 10);
        return false;
    }
    else
    {
        return true;
    }
}



function addNoDuesSettlement(flag){
    //alert("js");
    var fromDate = document.getElementById('overlayFromDate').value;
    if(fromDate.length==0){
      //  alert("please select from DATE");
         x0p( '','please select from DATE','info');
        return false;
    }
    getToDate('overlayFromDate','overlayToDate');
    var toDate = document.getElementById('overlayToDate').value;
    var release = document.getElementById('release').value;
    var commissions = document.getElementById('commissions').value;
    var settled = document.getElementById('settled').value;
    var dueAmount = document.getElementById('dueAmount').value;
    var comments = document.getElementById('comments').value;
    var noDueTableId = document.getElementById('noDueTableId').value;
    var noDueId = document.getElementById('noDueId').value;

    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerTxt(req,ppulateMyReviewUpadteResult123); 

    //var url=CONTENXT_PATH+"/updateMyReview.action?reviewId="+reviewId+"&overlayReviewType="+overlayReviewType+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay;
    // var url=CONTENXT_PATH+'/upadtePayrollTaxExemption.action?overLayExemptionType='+overLayexemptionType+'&overlayApprovedAmount='+overlayApprovedAmount+'&overLayStatus='+overLaystatus+'&comments='+comments+'&exemptionId='+exemptionId+"&paymentDateEmp="+paymentDateEmp;
  //  var url=CONTENXT_PATH+'/addNoDuesSettlement.action?fromDate='+fromDate+'&toDate='+toDate+'&release='+release+'&commissions='+commissions+'&settled='+settled+"&dueAmount="+dueAmount+"&comments="+comments;
    var url=CONTENXT_PATH+'/addNoDuesSettlement.action?fromDate='+fromDate+'&toDate='+toDate+'&release='+release+'&commissions='+commissions+'&settled='+settled+"&dueAmount="+dueAmount+"&comments="+comments+"&noDueTableID="+noDueTableId+"&noDueId="+noDueId+"&tempVar="+flag;

    //alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
   
}
function ppulateMyReviewUpadteResult123(resText){
    document.getElementById('resultMessageNoDue').innerHTML = resText;
  
}

function NoDuesSettlementOverlay(flag){
    // alert("add");
    document.getElementById('resultMessageNoDue').innerHTML = "";
   
    document.getElementById("noDueheaderLabel").style.color="white";
    document.getElementById("noDueheaderLabel").innerHTML="Add No-Dues";
 
    var overlay = document.getElementById('noDuesOverlay');
    var specialBox = document.getElementById('noDuesSpecialBox');
           
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
        if(flag=='1'){
            //window.location="getEmployeePayRollDashBoard.action";
         //  window.location="getEmployeePayRollDashBoard.action";
document.getElementById("frmTaxAssumptionSearch").submit();
        }
        else
        {
            window.location="getPayRollDashBoard.action";       
        }
        var empnameById="-1";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}

var dateidd;
function getToDate(fromDateId,toDateId){
    dateidd=toDateId;
    var fromDate = document.getElementById(fromDateId).value;
    

    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerTxt(req,getToDateResponse); 

    //var url=CONTENXT_PATH+"/updateMyReview.action?reviewId="+reviewId+"&overlayReviewType="+overlayReviewType+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay;
    var url=CONTENXT_PATH+'/getEndDate.action?startDate='+fromDate;
    //alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function getToDateResponse(resText){
    //alert(resText);
    document.getElementById(dateidd).value = resText;
  
}


function serachForLockAmt(){
    var yearlockAmt = document.getElementById("yearlockAmt").value;
    var monthlockAmt=document.getElementById("monthlockAmt").value;
   
    var lockAmtempnameById=document.getElementById("userId").value;
    var lockAmtPeriod=document.getElementById("lockAmtPeriod").value;
   
    if(yearlockAmt.length==0){
         x0p( '','Please give year','info');
      //  alert("Please give year");
        return false;
    }
    else if(monthlockAmt.length==0){
         x0p( '','Please select month','info');
       // alert("Please select month");
        return false;
    }
    else if(lockAmtempnameById.length ==0){
         x0p( '','Please select Employee Name','info');
       // alert("Please select Employee Name");
        return false;
    }
   
    else{
        document.getElementById("loadingMessagelockAmt").style.display = "block";
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerTxt(req,lockAmtListForEmp); 
        var url=CONTENXT_PATH+'/lockAmtListForAllEmps.action?year='+yearlockAmt+"&month="+monthlockAmt+"&empnameById="+lockAmtempnameById+"&lockAmtPeriod="+lockAmtPeriod;
        //var url=CONTENXT_PATH+'/lockAmtListForAllEmps.action?year='+yearlockAmt+"&month="+monthlockAmt;
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
}
function lockAmtListForEmp(res){
    //alert(res);   
    if(res.length == 0){
        document.getElementById("loadingMessagelockAmt").style.display = "none";
     //   alert("No Records Found");
         x0p( '','No Records Found','info');
        document.getElementById("lockAmtAllSingleEmpList").style.display="none";
        document.getElementById("lockAmtAllEmpsList").style.display="none";
    }
    else if(res.split("@")[0]=="Single"){
        document.getElementById("loadingMessagelockAmt").style.display = "none";
        document.getElementById("lockAmtAllEmpsList").style.display="none";
        document.getElementById("lockAmtAllSingleEmpList").style.display="block";
        var oTable = document.getElementById("tbllockAmtSingleEmpList");
        
        clearTable(oTable);

        var headerFields = new Array("Month","  Year","LongTerm Bonus");	       
        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
        document.getElementById("empNameForLockAmtSearch").innerHTML = res.split("@")[1].split("$")[0];
        var resText = res.split("@")[1].split("$")[1];
        var resTextSplit1 = resText.split("&");
        var resTextSplit2="";
        generateTableHeaderForLockAmtDetails(tbody,headerFields);
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#");
            //    alert(resTextSplit2);
            generateRowForLockAmtDetails(tbody,resTextSplit2,index);
        }
        generateFooterForLockAmtDetails(tbody);
    }
    else{
        document.getElementById("loadingMessagelockAmt").style.display = "none";
        document.getElementById("lockAmtAllSingleEmpList").style.display="none";
        document.getElementById("lockAmtAllEmpsList").style.display="block";
        var oTable = document.getElementById("tbllockAmtAllEmpsList");
        
        clearTable(oTable);

        var headerFields = new Array("EmpId","  EmpName ","Total Long Term Bonus","Total Months");	
        

        tbody = document.createElement("TBODY");
        oTable.appendChild(tbody);
        var resText = res.split("@")[1];
        var resTextSplit1 = resText.split("&");
        var resTextSplit2="";
        generateTableHeaderForLockAmtAllEMPDetails(tbody,headerFields);
        for(var index=0;index<resTextSplit1.length-1;index++) {
            resTextSplit2 = resTextSplit1[index].split("#");
            //    alert(resTextSplit2);
            generateRowForLockAmtAllEMPDetails(tbody,resTextSplit2,index);
         
        }
        generateFooterForLockAmtAllEMPDetails(tbody);
    }
}


function generateRowForLockAmtDetails(tableBody,rowFeildsSplit,index) {
    var row;
    var cell;
    row = document.createElement("TR");
    row.className="gridRowEven";
    /*cell = document.createElement("TD");
    cell.className="gridRowEven";
    cell.innerHTML = index+1;
    cell.setAttribute('align','center');
    row.appendChild(cell);*/
    tableBody.appendChild(row);
    //alert(rowFeildsSplit.length);
    for (var i=0; i<=rowFeildsSplit.length-1; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild(cell);
        if(i==0) {
            // alert(rowFeildsSplit[1]);
            cell.setAttribute('align','center');
            cell.innerHTML = rowFeildsSplit[1];
        }
        else if(i==1) {
            //  alert(rowFeildsSplit[2]);
            cell.setAttribute('align','center');
            cell.innerHTML = rowFeildsSplit[2];
        }
        
        else if(i==2){
            // alert(rowFeildsSplit[0]);
            cell.setAttribute('align','center');
            cell.innerHTML = rowFeildsSplit[0];
        }
            
    }
    cell.width = 80;
    
}






function generateFooterForLockAmtDetails(tbody) {
    // alert(tbody);
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    //    cell.id="footer"+oTable.id;
 
    cell.colSpan = "3";
     
    footer.appendChild(cell);
}

function generateTableHeaderForLockAmtDetails(tableBody,headerFields) {
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridHeader";
    tableBody.appendChild( row );
    for (var i=0; i<headerFields.length; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridHeader";
        row.appendChild( cell );
        cell.setAttribute('align','center');
        cell.setAttribute("width","10000px");
        cell.innerHTML = headerFields[i];
    }
}


function generateRowForLockAmtAllEMPDetails(tableBody,rowFeildsSplit,index) {
    var row;
    var cell;
    row = document.createElement("TR");
    row.className="gridRowEven";
    /*cell = document.createElement("TD");
    cell.className="gridRowEven";
    cell.innerHTML = index+1;
    cell.setAttribute('align','center');
    row.appendChild(cell);*/
    tableBody.appendChild(row);
    //alert(rowFeildsSplit.length);
    for (var i=0; i<=rowFeildsSplit.length-1; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridRowEven";
        row.appendChild(cell);
        if(i==0) {
            // alert(rowFeildsSplit[1]);
            cell.setAttribute('align','center');
            cell.innerHTML = rowFeildsSplit[0];
        }
        else if(i==1) {
            //  alert(rowFeildsSplit[2]);
            cell.setAttribute('align','center');
            cell.innerHTML = rowFeildsSplit[1];
        }
        
        else if(i==2){
            // alert(rowFeildsSplit[0]);
            cell.setAttribute('align','center');
            cell.innerHTML = rowFeildsSplit[2];
        }
        else if(i==3){
            // alert(rowFeildsSplit[0]);
            cell.setAttribute('align','center');
            cell.innerHTML = rowFeildsSplit[3];
        }        
    }
    cell.width = 80;
}






function generateFooterForLockAmtAllEMPDetails(tbody) {
    // alert(tbody);
    var footer =document.createElement("TR");
    footer.className="gridPager";
    tbody.appendChild(footer);
    cell = document.createElement("TD");
    cell.className="gridFooter";
    //    cell.id="footer"+oTable.id;
 
    cell.colSpan = "4";
     
    footer.appendChild(cell);
}

function generateTableHeaderForLockAmtAllEMPDetails(tableBody,headerFields) {
    var row;
    var cell;
    row = document.createElement( "TR" );
    row.className="gridHeader";
    tableBody.appendChild( row );
    for (var i=0; i<headerFields.length; i++) {
        cell = document.createElement( "TD" );
        cell.className="gridHeader";
        row.appendChild( cell );
        cell.setAttribute('align','center');
        cell.setAttribute("width","10000px");
        cell.innerHTML = headerFields[i];
    }
}




function generatePfOrPTExcel(){
    var year = document.getElementById("yearPForPTAmt").value;
    var month=document.getElementById("monthPForPTAmt").value;
    var reportFor=document.getElementById("reportFor").value;
    if(year.length==0){
      //  alert("Please give year");
         x0p( '','Please give year','info');
        return false;
    }
    else if(month.length==0){
      //  alert("Please select month");
         x0p( '','Please select month','info');
        return false;
    }
    else if(reportFor.length==0 ){
      //  alert("Please select report type");
         x0p( '','Please select report type','info');
        return false;
    }
   
    else{
        
        window.location = "generatePfOrPTExcel.action?year="+year+"&month="+month+"&reportFor="+reportFor;
     
        
    }
    
}

function generateLockAmtExcel(){
    var yearlockAmt = document.getElementById("yearlockAmt").value;
    var monthlockAmt=document.getElementById("monthlockAmt").value;
   
    var lockAmtempnameById=document.getElementById("userId").value;
    if(yearlockAmt.length==0){
      //  alert("Please give year");
         x0p( '','Please give year','info');
        return false;
    }
    else if(monthlockAmt.length==0){
      //  alert("Please select month");
         x0p( '','Please select month','info');
        return false;
    }
    else if(lockAmtdepartmentId.length==0 && lockAmtempnameById.length !=0){
       // alert("Please select department");
         x0p( '','Please select department','info');
        return false;
    }
   
    else{
        
        window.location = "generateExcelReportForLockAmt.action?year="+yearlockAmt+"&month="+monthlockAmt+"&empnameById="+lockAmtempnameById;
    // window.location = "generateExcelReportForLockAmt.action?year="+yearlockAmt+"&month="+monthlockAmt;
        
    }
    
}
function toogleTef(){
   // document.getElementById("tefresultMessage").innerHTML = "";
    var overlay = document.getElementById('tefOverlay');
    var specialBox = document.getElementById('rerunTefOverlay');
    
         
    
        
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
        window.location="getPayRollReports.action";
                   
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
      
    }   
}
function getTefEmployeesByDept(){
    var deptName = document.getElementById("tefdepartmentId").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerXml(req, populateTefEmployeesByDept);
    // var url = CONTENXT_PATH+"/getEmpForReportsTo.action?deptName="+deptName;
    var url = CONTENXT_PATH+"/getEmployeesByDept.action?departmentId="+deptName;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}

function populateTefEmployeesByDept(resXML) {
    //   var reportsTo = document.employeeForm.reportsTo;
    var empId = document.getElementById("tefempnameById");
    var team = resXML.getElementsByTagName("TEAM")[0];
    var users = team.getElementsByTagName("USER");
    empId.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("userId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        empId.appendChild(opt);
    }
}

function getTefReport(){
    
   
    var empnameById=document.getElementById("empNameByIdTef").value;
    var year=document.getElementById("tefyearOverlay").value;
    var month=document.getElementById("tefmonthOverlay").value;
    var empNo = document.getElementById("tefEmpNoOverlay").value;
    //alert(empNo)
    if (isNaN(empNo)){
        x0p( '','Please enter valid EmpNo!!!!!','info');
       
              // alert("Please enter valid EmpNo!!"); 
                return false; 
          }
    if (isNaN(year) || year.length!=4) {
         x0p( '','Please enter Valid Year!','info');
       // alert("Please enter Valid Year!");
        return false;
    }
    if(empnameById.length==0 && empNo.length == 0) {
   x0p( '','Please select Empname on EmpNo!','info');
      //  alert("Please select Empname on EmpNo!");
        return false;
       
    
    }
    if(year==""){
         x0p( '','Please enter Year!!!!!','info');
        //alert("Year not Empty!");
        return false;
    }
    if(month==0){
         x0p( '','Please select Month!','info');
     //   alert("Please select Month!");
        return false;
    }
    
    
    
    else{
        // var password = prompt("Please give the password encryption for your payslip if you want","");
        var passwordForPdf="";
       
    
        window.location="generateTefReport.action?empnameById="+empnameById+"&passwordForPdf="+passwordForPdf+"&year="+year+"&month="+month+"&empNo="+empNo;
    }
    
}



//function NoDuesSettlementOverlay(){
//    // alert("add");
//    document.getElementById('resultMessageNoDue').innerHTML = "";
//     
//    document.getElementById("noDueheaderLabel").style.color="white";
//    document.getElementById("noDueheaderLabel").innerHTML="Add No-Dues";
// 
//    var overlay = document.getElementById('noDuesOverlay');
//    var specialBox = document.getElementById('noDuesSpecialBox');
//           
//    overlay.style.opacity = .8;
//    if(overlay.style.display == "block"){
//        overlay.style.display = "none";
//        specialBox.style.display = "none";
//        window.location="getEmployeeNoDues.action";
//    } else {
//        document.getElementById('overlayFromDate').focus();
//        overlay.style.display = "block";
//        specialBox.style.display = "block";
//    }
//}
function NoDuesSettlementOverlayOperations(){
    document.getElementById('resultMessageNoDue').innerHTML = "";
    document.getElementById("noDueheaderLabel").style.color="white";
    document.getElementById("noDueheaderLabel").innerHTML="Add No-Dues";
    var overlay = document.getElementById('noDuesOverlay');
    var specialBox = document.getElementById('noDuesSpecialBox');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
        document.getElementById('frmDBGrid').submit();
    } else {
        document.getElementById('overlayFromDate').focus();
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}
function NoDuesSettlementOverlayOperationsRemainder(){
    document.getElementById("noDueheaderLabelRemainder").style.color="white";
    document.getElementById("noDueheaderLabelRemainder").innerHTML="Add No-Dues";
    var overlay = document.getElementById('noDuesOverlayRemainder');
    var specialBox = document.getElementById('noDuesSpecialBoxRemainder');
           
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
        document.getElementById('frmNoDuesSettlementOperationsRemainder').submit();
    } else {
        document.getElementById('overlayFromDateRemainder').focus();
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}
var dateidd;
function getToDate(fromDateId,toDateId){
    dateidd=toDateId;
    var fromDate = document.getElementById(fromDateId).value;
    

    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerTxt(req,getToDateResponse); 

    //var url=CONTENXT_PATH+"/updateMyReview.action?reviewId="+reviewId+"&overlayReviewType="+overlayReviewType+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay;
    var url=CONTENXT_PATH+'/getEndDate.action?startDate='+fromDate;
    //alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function showEmpListRemainder(empList,deptId){
    if(empList=="All"){
      
        var background = "#3E93D4";
        var title = "Employee Names List";
        var text1 = ""; 
        if(empList == " "){
            text1 = "No Employees Selected";
        }
        else{
            text1 = empList; 
        }
        var size = text1.length;
   
        var content = "<html><head><title>"+title+"</title></head>\<body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>";
        if(deptId=="All"){
            content =content  +" All employees selected for all departments<br />";
       
        }
        else{
            content =content  +" All employees selected for department "+deptId+"<br />";
        }
    
        content =content  + " </body></html>";
    
        if(size < 50){
            //Create the popup       
            popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
            popup.document.write(content); //Write content into it.    
        }
    
        else if(size < 100){
            //Create the popup       
            popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
            popup.document.write(content); //Write content into it.    
        }
    
        else if(size < 260){
            //Create the popup       
            popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
            popup.document.write(content); //Write content into it.    
        }
  
    }
    else{    
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerTxt(req,shpwPopupEmpNamesNoDues); 
        var url=CONTENXT_PATH+'/showEmpListNoDuesRemainder.action?selectedEmployess='+empList;
        //alert(url);
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }


}

function shpwPopupEmpNamesNoDues(res){
    // alert(res);
    
    var background = "#3E93D4";
    var title = "Employee Names List";
    var text1 = ""; 
    if(res == " "){
        text1 = "No Employees Selected";
    }
    else{
        text1 = res; 
    }
    var size = text1.length;
    var empNames = text1.split("$$");
    //alert(empNames.length);
    var content = "<html><head><title>"+title+"</title></head>\<body bgcolor='"+background +"' style='color:white;'><h4>"+title+"</h4>";
    content = content + "<table border='1' style='background-color:white;color:black;border: 4px solid black;'><tr><td colspan='2'>Employee Name</td><td colspan='2'>Department</td></tr>";   
    for(var i=0;i<empNames.length-1;i++){
        content =content  +"<tr><td colspan='2'>"+empNames[i].split("#$#")[0].trim()+"</td><td colspan='2'>"+empNames[i].split("#$#")[1]+"</td></tr>";
    }
    content =content  + "</table></body></html>";
    
    if(size < 150){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 200){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }
    
    else if(size < 360){
        //Create the popup       
        popup = window.open("","window","channelmode=0,width=400,height=200,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no");    
        popup.document.write(content); //Write content into it.    
    }

}

//function addNoDuesSettlement(flag){
//    //alert("js");
//    document.getElementById("load").style.display="block";
//    document.getElementById("FlagForButton").value=flag;
//    var fromDate = document.getElementById('overlayFromDate').value;
//    if(fromDate.length==0){
//        document.getElementById("load").style.display="none";
//        alert("please select from date");
//        return false;
//    }
//   
//    var toDate = document.getElementById('overlayToDate').value;
//    if(toDate.length==0){
//        document.getElementById("load").style.display="none";
//        alert("please select to date");
//        return false;
//    }
//    var release="";
//    if(document.getElementById('release').checked)
//    {
//        release = "1";
//    }
//    else{
//         document.getElementById("load").style.display="none";
//        alert("please check isRelease check box");
//        return false;
//    }
// 
//    var commissions ="";
//    if(document.getElementById('commissions').checked){
//        commissions=  "1";
//    }
//    else{
//        commissions = "0";
//         document.getElementById("load").style.display="none";
//        alert("please check commisions check box");
//        return false;
//    }
// //   alert(commissions);
//    var settled = "";
//    if(document.getElementById('settled').checked){
//        settled = "1";
//    }
//    else{
//        settled = "0";
//    }
//  //  alert(settled);
//    var dueAmount = document.getElementById('dueAmount').value;
//    var comments = document.getElementById('comments').value;
//    var noDueId=document.getElementById("noDueId").value;
//    var noDueTableID = document.getElementById("noDueTableId").value;
//    //   alert(document.getElementById("noDueId").value);
//    var noDueEmpId=document.getElementById("noDueEmpId").value;
//    var req = newXMLHttpRequest();
//    req.onreadystatechange = readyStateHandlerTxt(req,addNoduesSettlementResponse); 
//
//    //var url=CONTENXT_PATH+"/updateMyReview.action?reviewId="+reviewId+"&overlayReviewType="+overlayReviewType+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay;
//    // var url=CONTENXT_PATH+'/upadtePayrollTaxExemption.action?overLayExemptionType='+overLayexemptionType+'&overlayApprovedAmount='+overlayApprovedAmount+'&overLayStatus='+overLaystatus+'&comments='+comments+'&exemptionId='+exemptionId+"&paymentDateEmp="+paymentDateEmp;
//    var url=CONTENXT_PATH+'/addNoDuesSettlement.action?fromDate='+fromDate+'&toDate='+toDate+'&release='+release+'&commissions='+commissions+'&settled='+settled+"&dueAmount="+dueAmount+"&comments="+comments+"&tempVar="+flag+"&noDueTableID="+noDueTableID+"&noDueId="+noDueId+"&noDueEmpId="+noDueEmpId;
//    //alert(url);
//    req.open("GET",url,"true");
//    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
//    req.send(null);
//   
//}

function addNoduesSettlementResponse(resText){
    document.getElementById("load").style.display="none";
    document.getElementById('resultMessageNoDue').innerHTML = resText;
 
    document.getElementById("addButton").disabled="true";
    document.getElementById("updateButton").disabled="true";
    document.getElementById("submitButton").disabled="true";
    
  
}

function NoDuesSettlementEditOverlay(id){
     
    var noDueId=id;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerTxt(req,getNoduesSettlementResponse); 

    //var url=CONTENXT_PATH+"/updateMyReview.action?reviewId="+reviewId+"&overlayReviewType="+overlayReviewType+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay;
    // var url=CONTENXT_PATH+'/upadtePayrollTaxExemption.action?overLayExemptionType='+overLayexemptionType+'&overlayApprovedAmount='+overlayApprovedAmount+'&overLayStatus='+overLaystatus+'&comments='+comments+'&exemptionId='+exemptionId+"&paymentDateEmp="+paymentDateEmp;
    var url=CONTENXT_PATH+'/getNoDueDetails.action?noDueId='+noDueId;
    //alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function getNoduesSettlementResponse(res){
    //alert(res);
    var resText = res.split("|@#");
    var id= res.split("|@#")[0];
    var approverComments = res.split("|@#")[1];
    var fromDate=res.split("|@#")[2];
    var isReleased=res.split("|@#")[3];
    var isSettled=res.split("|@#")[4];
    var isCommision=res.split("|@#")[5];
    var comments=res.split("|@#")[6];
    var dueAmt = res.split("|@#")[7];
    var status = res.split("|@#")[8];
    var toDate=res.split("|@#")[9];
    var empId=res.split("|@#")[10];
    var noDueTableId = res.split("|@#")[11];
    var noDueDate = fromDate.split("-");
    var noDueDateConverted = noDueDate[1]+"/"+noDueDate[2]+"/"+noDueDate[0];
    var noDueToDate = toDate.split("-");
    var noDueToDateConverted = noDueToDate[1]+"/"+noDueToDate[2]+"/"+noDueToDate[0];
    document.getElementById("noDueId").value=id;
    //   alert(document.getElementById("noDueId").value);
    document.getElementById("noDueEmpId").value=empId;
    document.getElementById("noDueTableId").value=noDueTableId;
    document.getElementById("overlayFromDate").value=noDueDateConverted;
    document.getElementById("overlayToDate").value=noDueToDateConverted;
    if(approverComments==null||approverComments=="null"){
        approverComments = "";
    }
    document.getElementById("approverComments").value=approverComments;
    
    if(isReleased=='1'){
        document.getElementById("release").checked=true;
    }
    else{
        document.getElementById("release").checked=false;
    }
    if(isCommision=='1'){
        document.getElementById("commissions").checked=true;
    }
    else{
        document.getElementById("commissions").checked=false;
    }
    if(isSettled=='1'){
        document.getElementById("settled").checked=true;
        document.getElementById("dueAmount").value=dueAmt;
        getAmount('AmountTd');
    }
    else{
        document.getElementById("settled").checked=false;
    }
    document.getElementById("comments").value=comments;
    document.getElementById("overlayFromDate").readOnly="true";
    
    if(status=="Entered"){
        document.getElementById("addButtonForNoDues").style.display='none';
        document.getElementById("updateButton").style.display='inline';
        document.getElementById("submitButton").style.display='inline';
    }
    else  if(status=="Approved" ){
        //document.getElementById("approverComments").style.display='block';
        showRow('approverCommentsTr');
        document.getElementById("addButtonForNoDues").style.display='none';
        document.getElementById("updateButton").style.display='none';
        document.getElementById("submitButton").style.display='none';
        document.getElementById("statusMessageNoDues").style.display='block';
        document.getElementById("statusMessageNoDues").innerHTML="<font color='green'>Already "+status+"</font>";
    }
    else if(status == "Rejected"){
        showRow('approverCommentsTr');
        document.getElementById("statusMessageNoDues").style.display='block';
        document.getElementById("statusMessageNoDues").innerHTML="<font color='red'>This No Due form has been "+status+" Please re enter</font>";
    }
    else{
        document.getElementById("addButtonForNoDues").style.display='none';
        document.getElementById("updateButton").style.display='none';
        document.getElementById("submitButton").style.display='none';
        document.getElementById("statusMessageNoDues").style.display='block';
        document.getElementById("statusMessageNoDues").innerHTML="<font color='green'>Already "+status+"</font>";
    }
    
    NoDuesSettlementOverlay();
}

function getAmount1(id){
    if(document.getElementById('settled').checked==true)
        showRow(id);
    
    else{
        
        document.getElementById("dueAmount").value="0.00";
        hideRow(id);
    }
}


//function getEmployeesByDept(){
//    //   var deptName = document.employeeForm.departmentId.value;
//   
//    var deptName = document.getElementById("departmenttId").value;
//    var status = document.getElementById("status").value;
//    var req = newXMLHttpRequest();
//    //  alert("haiii="+deptName);
//    req.onreadystatechange = readyStateHandlerXml(req, populateEmployeeByDept);
//    // var url = CONTENXT_PATH+"/getEmpForReportsTo.action?deptName="+deptName;
//    var url = CONTENXT_PATH+"/getEmployeesByDeptPayroll.action?departmentId="+deptName+"&status="+status;
//    req.open("GET",url,"true");
//    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
//    req.send(null);   
//}
//
//function populateEmployeeByDept(resXML) {
//    //   var reportsTo = document.employeeForm.reportsTo;
//    var empId = document.getElementById("empnameByIdd");
//    var team = resXML.getElementsByTagName("TEAM")[0];
//    var users = team.getElementsByTagName("USER");
//    empId.innerHTML=" ";
//    for(var i=0;i<users.length;i++) {
//        var userName = users[i];
//        var att = userName.getAttribute("userId");
//        var name = userName.firstChild.nodeValue;
//        var opt = document.createElement("option");
//        opt.setAttribute("value",att);
//        opt.appendChild(document.createTextNode(name));
//        empId.appendChild(opt);
//    }
//}
function getEmployeesByDeptRem(){
    //   var deptName = document.employeeForm.departmentId.value;
   
    var deptName = document.getElementById("departmentId").value;
    var status = document.getElementById("statusRem").value;
    var country = document.getElementById("country").value;
    var req = newXMLHttpRequest();
    //  alert("haiii="+deptName);
    req.onreadystatechange = readyStateHandlerXml(req, populateEmployeeByDeptRem);
    // var url = CONTENXT_PATH+"/getEmpForReportsTo.action?deptName="+deptName;
    var url = CONTENXT_PATH+"/getEmployeesByDeptPayroll.action?departmentId="+deptName+"&status="+status+"&country="+country;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
}

function populateEmployeeByDeptRem(resXML) {
    //   var reportsTo = document.employeeForm.reportsTo;
    var empId = document.getElementById("empnameById");
    var team = resXML.getElementsByTagName("TEAM")[0];
    var users = team.getElementsByTagName("USER");
    empId.innerHTML=" ";
    for(var i=0;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("userId");
        var name = userName.firstChild.nodeValue;
        var opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        empId.appendChild(opt);
    }
}
function NoDuesSettlementEditOverlayOperations(id){
    
    var noDueId=id;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerTxt(req,getNoduesSettlementResponseOperations); 

    //var url=CONTENXT_PATH+"/updateMyReview.action?reviewId="+reviewId+"&overlayReviewType="+overlayReviewType+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay;
    // var url=CONTENXT_PATH+'/upadtePayrollTaxExemption.action?overLayExemptionType='+overLayexemptionType+'&overlayApprovedAmount='+overlayApprovedAmount+'&overLayStatus='+overLaystatus+'&comments='+comments+'&exemptionId='+exemptionId+"&paymentDateEmp="+paymentDateEmp;
    var url=CONTENXT_PATH+'/getNoDueDetails.action?noDueId='+noDueId;
    //alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}
function getNoduesSettlementResponseOperations(res){
    //alert(res);
    var resText = res.split("|@#");
    var id= res.split("|@#")[0];
    var approverComments = res.split("|@#")[1];
    var fromDate=res.split("|@#")[2];
    var isReleased=res.split("|@#")[3];
    var isSettled=res.split("|@#")[4];
    var isCommision=res.split("|@#")[5];
    var comments=res.split("|@#")[6];
    var dueAmt = res.split("|@#")[7];
    var status = res.split("|@#")[8];
    var toDate=res.split("|@#")[9];
    var empId=res.split("|@#")[10];
    var noDueDate = fromDate.split("-");
    var noDueDateConverted = noDueDate[1]+"/"+noDueDate[2]+"/"+noDueDate[0];
    var noDueToDate = toDate.split("-");
    var noDueToDateConverted = noDueToDate[1]+"/"+noDueToDate[2]+"/"+noDueToDate[0];
    document.getElementById("noDueId").value=id;
    //   alert(document.getElementById("noDueId").value);
    document.getElementById("noDueEmpId").value=empId;
    document.getElementById("overlayFromDate").value=noDueDateConverted;
    document.getElementById("overlayToDate").value=noDueToDateConverted;
    if(isReleased=='1'){
        document.getElementById("release").checked=true;
        document.getElementById("release").disabled=true;
    }
    else{
        document.getElementById("release").checked=false;
        document.getElementById("release").disabled=true;
    }
    if(isCommision=='1'){
        document.getElementById("commissions").checked=true;
        document.getElementById("commissions").disabled=true;
    }
    else{
        document.getElementById("commissions").checked=false;
        document.getElementById("commissions").disabled=true;
    }
    if(isSettled=='1'){
        document.getElementById("settled").checked=true;
        document.getElementById("settled").disabled=true;
        document.getElementById("dueAmount").value=dueAmt;
        getAmount();
    }
    else{
        document.getElementById("settled").checked=false;
        document.getElementById("settled").disabled=true;
    }
    document.getElementById("comments").value=comments;
    document.getElementById("comments").readOnly="true";
    document.getElementById("overlayFromDate").readOnly="true";
    if(approverComments==null||approverComments=="null"){
        approverComments = "";
    }
    document.getElementById("approverComments").value=approverComments;
    
    if(status=="Submitted"){
        document.getElementById("approveButton").style.display='block';
        document.getElementById("rejectButton").style.display='block';
  
    }
    else{
        // showRow('approverCommentsTr');
        document.getElementById("statusMessageNoDues").style.display='block';
        document.getElementById("statusMessageNoDues").innerHTML="<font color='green'>This record is "+status+" already</font>";
    }
    
    NoDuesSettlementOverlayOperations();
}
function approveOrRejectNoDue(flag){
    
    document.getElementById("load").style.display="block";
    var fromDate = document.getElementById('overlayFromDate').value;
    if(fromDate.length==0){
        document.getElementById("load").style.display="none";
      //  alert("please select from date");
         x0p( '','please select from date','info');
        return false;
    }
   
    var toDate = document.getElementById('overlayToDate').value;
    if(toDate.length==0){
        document.getElementById("load").style.display="none";
     //   alert("please select to date");
         x0p( '','please select to date','info');
        return false;
    }
    var release="";
    if(document.getElementById('release').checked)
    {
        release = "1";
    }
    else{
        release = "0";
    }
 
    var commissions ="";
    if(document.getElementById('commissions').checked){
        commissions=  "1";
    }
    else{
        commissions = "0";
    }
    // alert(commissions);
    var settled = "";
    if(document.getElementById('settled').checked){
        settled = "1";
    }
    else{
        settled = "0";
    }
    // alert(settled);
    var dueAmount = document.getElementById('dueAmount').value;
    var comments = document.getElementById('comments').value;
    var apprComments = document.getElementById('approverComments').value;
    var noDueId=document.getElementById("noDueId").value;
    var noDueEmpId=document.getElementById("noDueEmpId").value;
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerTxt(req,updateNoduesSettlementResponse); 
    var url=CONTENXT_PATH+'/approveOrRejectNoDues.action?fromDate='+fromDate+'&toDate='+toDate+'&release='+release+'&commissions='+commissions+'&settled='+settled+"&dueAmount="+dueAmount+"&comments="+escape(comments)+"&tempVar="+flag+"&noDueId="+noDueId+"&noDueEmpId="+noDueEmpId+"&apprComments="+escape(apprComments);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
   
}

function updateNoduesSettlementResponse(resText){
    document.getElementById("load").style.display="none";
    document.getElementById('resultMessageNoDue').innerHTML = resText;
    document.getElementById('approveButton').disabled = true;
    document.getElementById('rejectButton').disabled = true;
    
}
function addRemainderValuesNoDues(){
    document.getElementById("loadForRemainder").style.display="block";
    document.getElementById('resultMessageNoDueRemainder').innerHTML = " ";
   
    //  alert(selectedEmployess);
    var fromDate = document.getElementById('overlayFromDateRemainder').value;
    var toDate = document.getElementById('overlayToDateRemainder').value;
    var departmentId = document.getElementById('departmentId').value;
    var status = document.getElementById('statusRem').value;
    var commentsRem = document.getElementById('commentsRem').value;
    var country = document.getElementById('country').value;
    var empIds= document.getElementById("empnameById");
    var count=0;
    // alert(empIds.options.length);
    var selectedArray = new Array;
    var selectedEmployess="";
    // alert(empIds.options.length);
    for (var i=0; i<empIds.options.length; i++) {
        if (empIds.options[i].selected) {
            //  alert(empIds.options[i].value);
            //  alert(empIds.options[i].value);
            selectedArray[count] = empIds.options[i].value;
            selectedEmployess=selectedEmployess+empIds.options[i].value+"$$";
            count++;
        }
    }
    if(fromDate.length==0){
        document.getElementById("loadForRemainder").style.display="none";
       // alert("Please enter from date");
         x0p( '','Please enter from date','info');
        return false;
    }
    else if(toDate.length==0){
        document.getElementById("loadForRemainder").style.display="none";
     //   alert("Please enter to date");
         x0p( '','Please enter to date','info');
        return false;
    }
        
    else if(selectedEmployess.length==0){
        document.getElementById("loadForRemainder").style.display="none";
     //   alert("Please select employees");
         x0p( '','Please select employees','info');
        return false;
    }
    
    // var fromDate = document.getElementById('overlayFromDateRemainder').value;
    else{
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerTxt(req,addRemainderResponse); 
        var url=CONTENXT_PATH+'/addRemainderValuesNoDues.action?fromDate='+fromDate+'&toDate='+toDate+'&departmentId='+departmentId+'&status='+status+"&comments="+commentsRem+"&selectedEmployess="+selectedEmployess+"&country="+country;
        //alert(url);
  
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
   
        
    }
}

function addRemainderResponse(res){
    // alert(res);
    document.getElementById("loadForRemainder").style.display="none";
    document.getElementById('resultMessageNoDueRemainder').innerHTML = res;
//   document.getElementById("remianderAddButton").disabled=true;
    
}

function checkForEmpNoDueRecordExistsOrNot(){
    
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerTxt(req,checkForEMpNoDueExistsResponse); 
    var url=CONTENXT_PATH+'/checkForEmpNoDueRecordExistsOrNot.action';
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function checkForEMpNoDueExistsResponse(res){
    
    //   alert(res);
    if(res=="fail"){
        hideRow('buttonToAddEmpNoDues');
    }
    else{
        showRow('buttonToAddEmpNoDues');
        var noDueDate = res.split('$$')[0].split("-");
        var noDueDateConverted = noDueDate[1]+"/"+noDueDate[2]+"/"+noDueDate[0];
        var noDueToDate = res.split('$$')[1].split("-");
        var noDueToDateConverted = noDueToDate[1]+"/"+noDueToDate[2]+"/"+noDueToDate[0];
        document.getElementById('overlayFromDate').value = noDueDateConverted;
        document.getElementById('overlayToDate').value = noDueToDateConverted;
        document.getElementById('noDueTableId').value=res.split('$$')[2];
    }
}

function noDuesRemainderClose(id,fromDate,toDate,selectedEmployess,flag,deptId){
    if(flag=='0'){
        document.getElementById("loadForRemainderCloseInSearch").style.display="block";
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerTxt(req,noDuesRemainderCloseResponse); 
        var url=CONTENXT_PATH+'/noDuesRemainderClose.action?fromDate='+fromDate+'&toDate='+toDate+"&selectedEmployess="+selectedEmployess+"&noDueId="+id+"&departmentId="+deptId;
        //alert(url);
  
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
   
        
    }
    else{
       // alert("Remainder has already closed");
         x0p( '','Remainder has already closed','info');
    //return false;
    }
}

function noDuesRemainderCloseResponse(res){
    // alert(res);
    document.getElementById("loadForRemainderCloseInSearch").style.display="none";
    document.getElementById('resultMessageNoDueRemainderCloseInSearch').innerHTML = res;
    document.getElementById("frmNoDuesSettlementOperationsGrid").submit();
    
}

function payrollAuthenticationRegister(){
    

    var payrollPassword=document.getElementById("payrollPassword").value;
    var payrollConformPassword=document.getElementById("payrollConformPassword").value;
    
    if(payrollPassword==""){
        document.getElementById("payRollAuthenticationMsg").innerHTML="<font style='color:red;font-size:15px;'>password should not be empty!</font>";
        return false;
    }
    if(payrollConformPassword==""){
        document.getElementById("payRollAuthenticationMsg").innerHTML="<font style='color:red;font-size:15px;'>Conform password should not be empty!</font>";
        return false;
    }
    if(payrollPassword!=payrollConformPassword){
        document.getElementById("payRollAuthenticationMsg").innerHTML="<font style='color:red;font-size:15px;'>Conform password is not matching!</font>";
        return false;
    }
    
    
   
    // var password = prompt("Please give the password encryption for your payslip if you want","");
    var passwordForPdf="";
       
    
    window.location="payrollAuthenticationRegister.action?payrollPassword="+payrollPassword;
    
    
}

function payrollAuthenticationLogin(){
    

    var payrollPassword=document.getElementById("payrollPassword").value;
    
    
    if(payrollPassword==""){
        document.getElementById("payRollAuthenticationMsg").innerHTML="<font style='color:red;font-size:15px;'>password should not be empty!</font>";
        return false;
    }
     
    
    window.location="payrollAuthenticationLogin.action?payrollPassword="+payrollPassword;
    
    
}

function payrollAuthenticationCanel(){
    

    
    
    window.location="payrollAuthenticationCanel.action";
    
    
}
function forgetPayRollPassword(){
   
    //   document.getElementById('EmailTr').style.display='inline';
    $('#EmailTr').show();
    $('#buttonsTrForget').show();
    $('#buttonsTr').hide();
    $('#authPasswordTr').hide();
// alert("test");
}
function generatePayRollPassword(){
  
    var emailId=document.getElementById("authEmailId").value;
    document.getElementById("loadingMessageAuth").style.display="block";
    document.getElementById("payRollAuthenticationMsg").innerHTML='';
    if(emailId.trim()==''){
        document.getElementById("payRollAuthenticationMsg").innerHTML="<font color='red' size='2'>Please enter EmailId!</font>";
        document.getElementById("authEmailId").focus();
        return false;
    }
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerTxt(req,generatePayRollPasswordResponse); 
    var url=CONTENXT_PATH+'/generatePayRollPassword.action?email='+emailId;
    //alert(url);
  
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
   
        
}
    

function generatePayRollPasswordResponse(res){
    //alert(res);
    document.getElementById("loadingMessageAuth").style.display="none";
    document.getElementById('payRollAuthenticationMsg').innerHTML = res;
   
    $('#EmailTr').hide();
    $('#buttonsTrForget').hide();
    $('#buttonsTr').show();
    $('#authPasswordTr').show();
}
function passwordValidation(){
    // var payrollPassword= document.resetForm.payrollPassword;
    var password=document.getElementById("payrollPassword");
    //   var pwdregex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}";
    //     document.getElementById("payRollAuthenticationMsg").innerHTML='';
    //     if(payrollPassword.trim()==''){
    //          document.getElementById("payRollAuthenticationMsg").innerHTML="<font color='red' size='2'>Password should not be empty!</font>";
    //          document.getElementById("payrollPassword").focus();
    //        return false;
    //    }
    //    alert(payrollPassword.value.match(pwdregex))
    //    if(payrollPassword.value.match(pwdregex)){
    //        return true;
    //    }else{
    //      document.getElementById("payRollAuthenticationMsg").innerHTML="<font color='red' size='2'>Password must contain Minimum 8 characters at least 1 Uppercase Alphabet, 1 Lowercase Alphabet, 1 Number and 1 Special Character!</font>";
    //       return false;    
    //    }
    //    
    // var payrollPassword= document.resetForm.payrollPassword;
  
 
    var decimal=  /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{4,15}$/;  
    if(password.value.match(decimal))   
    {   

        document.getElementById("payRollAuthenticationMsg").innerHTML=" ";
        return true;  
    }  
    else  
    {   

        document.getElementById("payRollAuthenticationMsg").innerHTML="<font color='red' size='2'>New Password must contain Minimum 4 characters,Maximum 15 characters, at least 1 Uppercase Alphabet, 1 Lowercase Alphabet, 1 Number and 1 Special Character!</font>";
    
        return false;  
    }
  
  
    
    //    alert(payrollPassword)
    //    var passwordregex =  "/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$/";
    //  
    //  
    //  alert(payrollPassword.test(passwordregex))
    //    if(payrollPassword.match(passwordregex)){
    //        alert("hiiiii")
    //         document.getElementById("payRollAuthenticationMsg").innerHTML=' ';
    //        return true;
    //    }else{
    //      document.getElementById("payRollAuthenticationMsg").innerHTML="<font color='red' size='2'>Password must contain Minimum 4 characters at least 1 Uppercase Alphabet, 1 Lowercase Alphabet, 1 Number and 1 Special Character!</font>";
    //       return false;    
    //    }
    //    
    return true;
}

function tdsCalculationOverlay(){
    // alert("add");
    document.getElementById('resultMessageNoDue').innerHTML = "";
   
    document.getElementById("noDueheaderLabel").style.color="white";
    
 
    var overlay = document.getElementById('noDuesOverlay');
    var specialBox = document.getElementById('noDuesSpecialBox');
           
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
         
       
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}
function tdsCalculation(){
    
    var year = document.getElementById("year").value;
    var month = document.getElementById("month").value;
    var empNo = document.getElementById("empNo").value;
    var empName = document.getElementById("empName").value;
    var tutionfees = document.getElementById("tutionfees").value;
    var nsc = document.getElementById("nsc").value;
    var lifeIns = document.getElementById("lifeIns").value;
    var fd = document.getElementById("fd").value;
    var hbLoanPrinciple = document.getElementById("hbLoanPrinciple").value;
    var hbLoanInterst = document.getElementById("hbLoanInterst").value;
    var medicalIns = document.getElementById("medicalIns").value;
    var mutualFunds = document.getElementById("mutualFunds").value;
    var eduInterest = document.getElementById("eduInterest").value;
    var ppf = document.getElementById("ppf").value;
    var hra = document.getElementById("hra").value;
   
    
    
    

    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerTxt(req,tdsCalculationResponse); 

    //var url=CONTENXT_PATH+"/updateMyReview.action?reviewId="+reviewId+"&overlayReviewType="+overlayReviewType+"&overlayReviewName="+overlayReviewName+"&overlayReviewDate="+overlayReviewDate+"&overlayDescription="+overlayDescription+"&reviewStatusOverlay="+reviewStatusOverlay;
    var url=CONTENXT_PATH+'/tdsCalculation.action?year='+year+
    "&month="+month+
    "&empNo="+empNo+
    "&empName="+empName+
    "&tutionfees="+tutionfees+
    "&nsc="+nsc+
    "&lifeIns="+lifeIns+
    "&fd="+fd+
    "&hbLoanPrinciple="+hbLoanPrinciple+
    "&hbLoanInterst="+hbLoanInterst+
    "&medicalIns="+medicalIns+
    "&mutualFunds="+mutualFunds+
    "&eduInterest="+eduInterest+
    "&ppf="+ppf+
    "&hra="+hra
        
        
    
    //alert(url);
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function tdsCalculationResponse(resText){
    document.getElementById("tds").value=resText;
}
/*
 * Payroll Tax Exemption add functionality from Payroll
 * Date : 05/03/2016
 * Author : Santosh Kola
 * 
 */

function taxExemptiontoggleOverlayFromPayroll(){
   
    $('#taxStatus').val("Approved");
    // document.getElementById("downloadTr").style.display = 'none';
    // document.getElementById("uploadTr").style.display = '';
    // document.getElementById("overLayexemptionType").style.display = 'block';
    // document.getElementById("exemptionTypeValue").style.display = 'none';
    //document.getElementById("overLaystatusTrForApproved").style.display = 'none';
    // document.getElementById("overLaystatusTr").style.display = 'block';
    document.getElementById("taxHeaderLabel").style.color="white";
    document.getElementById("taxHeaderLabel").innerHTML="Add Tax Exemptions";
    document.getElementById('resultMessageTaxAdd').innerHTML = "";
    document.getElementById("Attachment").innerHTML = "Attachment :";
 hideRow("downloadAttachmentTr");
 hideRow("downloadTrForm12BB");
    var overlay = document.getElementById('overlayTaxAdd');
    var specialBox = document.getElementById('specialBoxTaxAdd');
           
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
      
       
       // window.location="getPayRollDashBoard.action";       
       document.getElementById("PayrollTaxAssumptionSearch").submit();
        
       
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}





function getEmployeeNamesByDepartment(obj){
    // alert(obj.value);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandler(req, populateEmployees);
    var url = CONTENXT_PATH+"/getEmployeesByDept.action?departmentId="+obj.value;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);  
}
function populateEmployees(resXML) {
    var reportsTo = document.getElementById("empLoginId");
    var team = resXML.getElementsByTagName("TEAM")[0];
    var users = team.getElementsByTagName("USER");
    reportsTo.innerHTML="";
   
    var opt = document.createElement("option");
    opt.setAttribute("value","-1");
    opt.appendChild(document.createTextNode("--Please Select--"));
    reportsTo.appendChild(opt);
    //  alert(users.length);
    for(var i=1;i<users.length;i++) {
        var userName = users[i];
        var att = userName.getAttribute("userId");
        var name = userName.firstChild.nodeValue;
        opt = document.createElement("option");
        opt.setAttribute("value",att);
        opt.appendChild(document.createTextNode(name));
        reportsTo.appendChild(opt);
    }
}



function getEmployeeNumberByLoginId(obj){
    // alert(obj.value);
    if(obj.value=='-1'){
        document.getElementById("empNoSpan").innerHTML='';
    }else {
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerTextWotLoad(req, populateEmployeeName);
        var url = CONTENXT_PATH+"/getEmployeeNumberByLoginId.action?loginId="+obj.value;
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);  
    }
}
function populateEmployeeName(resText){
    // alert(resText);
   
    document.getElementById("empNoSpan").innerHTML='<font color="green" size="2px">EmpNo:'+resText.split("$")[0]+'</font>';
    var savingsAmt = resText.split("$")[1]
    $('#empSaving1').val(savingsAmt.split("#")[0]);
    $('#empSaving2').val(savingsAmt.split("#")[1]);
    $('#empSaving3_parents').val(savingsAmt.split("#")[2]);
    $('#empSaving4').val(savingsAmt.split("#")[3]);
    $('#empSaving5').val(savingsAmt.split("#")[4]);
    $('#empSaving3_self').val(savingsAmt.split("#")[5]);
    $('#empSavingsHealthAmt').val(savingsAmt.split("#")[6]);
         $('#empSaving6').val(savingsAmt.split("#")[7]);
    $('#empId').val(resText.split("$")[2]);

    
//  alert(savingsAmt.split("#")[6]);
}


function readyStateHandlerTextWotLoad(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
               
                responseTextHandler(req.responseText);
            } else {
                
               //  alert("HTTP error ---"+req.status+" : "+req.statusText);
               
                x0p( '','HTTP error ---'+req.status+' : '+req.statusText,'info');
                
            }
        }
    }
}

function ajaxFileUploadTaxAssumptionFromPayroll()
{
    document.getElementById('resultMessageTaxAdd').innerHTML ='';
    
    var empLoginId = $('#empLoginId').val();
    var exemptionTypeId = $('#exemptionTypeId').val();
    var paymentDateTax = $('#paymentDateTax').val();
    var taxAmount = $('#overlaySavingAmount').val();
    var taxStatus = $('#taxStatus').val();
    var taxcomments = $('#taxcomments').val();
    var taxApprovedAmount = $('#taxApprovedAmount').val();
    var empSaving1 = $('#empSaving1').val();
    var empSaving2 = $('#empSaving2').val();
    var empSaving3_parents = $('#empSaving3_parents').val();
    var empSaving4 = $('#empSaving4').val();
    var empSaving5 = $('#empSaving5').val();
    var categoryId = $('#categoryId').val();
    var empSaving3_self = $('#empSaving3_self').val();
    var empSavingsHealthAmt = $('#empSavingsHealthAmt').val();
    var validityDate = $('#validityDate').val();

    var taxStartDate = new Date($('#taxStartDate').val());
    var taxEndDate = new Date($('#taxEndDate').val());
    var givenDate = new Date($('#paymentDateTax').val());
    var tefType = $('#tefType').val();
  
    var ownerName = $('#ownerName').val();
    var PANNumber = $('#PANNumber').val();
    var fullPath = document.getElementById('file').value;
  //  alert(tefType)
   // alert(fullPath.length)
   var rentStartDate =$("#overLayrentStartDate").val();
      var rentEndDate =$("#overLayrentEndDate").val();
      var monthlyAmount =$("#overlaymonthlyAmount").val();
         var policyNumber =$("#policyNumber").val();
      var licPremium =$("#licPremium").val();
      var houseAddress =$("#houseAddress").val();
       var d = new Date();
    var month = d.getMonth();

 var year = d.getFullYear();
 var day=d.getDate();

//alert("day==="+day);
if(month==3 && day<=15){
    year=year-1;
}
//alert("hi222")
 var sdate = "";
                var edate = "";
                if (month >= 3) {
                    sdate = "04/01/" + year;
                    edate = "03/31/" + (year + 1);

                } else {
                    sdate = "04/01/" + (year - 1);
                    edate = "03/31/" + year;

                }
                var startDate=new Date(sdate);
                var endDate=new Date(edate);
   if(exemptionTypeId=="18"){
         
        if(ownerName.length == 0 ){
           // document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please enter OwnerName.</font>";
              x0p( '','Please enter OwnerName!','info'); 
            return false;
        }
        if(PANNumber.length == 0 ){
          //  document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please enter panNumber.</font>";
              x0p( '','Please enter panNumber!','info'); 
            return false;
        }
        if(houseAddress.length == 0 ){
          //  document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please enter panNumber.</font>";
              x0p( '','Please enter House Address!','info'); 
            return false;
        }
         
    }else{
        document.getElementById('ownerName').value = " ";
        document.getElementById('PANNumber').value = " ";
       
    }
  
    if(empLoginId=="-1" || empLoginId==""){
       // document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please select employee.</font>";
          x0p( '','Please select employee!','info'); 
        return false;
    }

    else if(exemptionTypeId.length==0){
       // document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please select exception type.</font>";
          x0p( '','Please select exception type!','info'); 
        return false;
    } else if(tefType.length==0){
        //document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please select type.</font>";
          x0p( '','Please select type!','info'); 
        return false;
    }
    else if(exemptionTypeId=="1" && policyNumber.trim().length==0){
      //  document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please PaymentDate.</font>";
          x0p( '','Please enter Policy Number!!','info'); 
        return false;
    } 
     else if(exemptionTypeId=="1" && licPremium==0){
      //  document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please PaymentDate.</font>";
          x0p( '','Please select Premium Type!!','info'); 
        return false;
    } 
    else if(exemptionTypeId!="18" && paymentDateTax.length==0){
      //  document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please PaymentDate.</font>";
          x0p( '','Please enter PaymentDate!!','info'); 
        return false;
    } 
     if(exemptionTypeId!="18" &&  validityDate.length==0){
   //     document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please enter validity date.</font>";
          x0p( '','Please enter validity date!','info'); 
        return false;
            }
     if(exemptionTypeId=="18" && tefType == 'Declarations'){
     
               x0p( '','Please change type as Actuals for House Rent Paid-80GG !','info'); 
        return false;
          
      }
    if(exemptionTypeId!="18"){
     if(tefType == 'Declarations'){
          var paymentDate=new Date(paymentDateTax);
          if(paymentDate>startDate){
               x0p( '','You selected type as Declarations. So,PaymentDate should be last financial year !','info'); 
        return false;
          }
      }
      else if(tefType == 'Actuals'){
          var paymentDate=new Date(paymentDateTax);
     //     alert(startDate);
      //    alert(endDate);
     //     alert((paymentDate<startDate) +" test "+(paymentDate<=endDate));
          
          if(paymentDate<startDate || paymentDate>endDate){
               x0p( '','You selected type as Actuals. So,PaymentDate should be in current financial year !','info'); 
        return false;
          }
      }
}
    //else if(taxStartDate.getTime() > givenDate.getTime()|| taxEndDate.getTime() < givenDate.getTime()){
//        document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>PaymentDate should be between "+$('#taxStartDate').val()+" and "+$('#taxEndDate').val()+" </font>";
//          x0p( '','PaymentDate should be between '+$("#taxStartDate").val()+' and '+$("#taxEndDate").val()+'!','info'); 
//        return false;
//    
//    } 
//    else if(validityDate.length==0){
//      //  document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please enter validity date.</font>";
//          x0p( '','Please enter validity date!','info'); 
//        return false;
//    }

		var renstartDate=new Date(rentStartDate);
 var renendDate=new Date(rentEndDate);

 if(renstartDate>renendDate){
        x0p( '','End date should be greater than start date!','info'); 
        return false;
    }
	
 if(exemptionTypeId=="18"){
        if(monthlyAmount.trim()==""){
             x0p( '','Please enter monthly amount!','info'); 
             return false;
        }
        
    }
  //  alert(taxApprovedAmount+"taxApprovedAmount")
     if(taxAmount.length==0){
        //document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please enter Applied amount.</font>";
          x0p( '','Please enter Applied amount!','info'); 
        return false;
    } else if(taxApprovedAmount.length==0){
        //document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please enter approved amount.</font>";
          x0p( '','Please enter approved amount!','info'); 
        return false;
    } else if(taxAmount<taxApprovedAmount){
        document.getElementById("taxApprovedAmount").style.background='transperant';
        document.getElementById("taxApprovedAmount").focus();
        document.getElementById('taxApprovedAmount').value="0.00";
       // document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Approved Amount should be less than or equal to Applied Amount.</font>";
          x0p( '','Approved Amount should be less than or equal to Applied Amount!','info'); 
        return false;
    }
   
     if(taxStatus == '' && taxStatus.length == 0){
       // document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please attach a file for Actuals.</font>";
          x0p( '','Please select the status','info'); 
          return false;
    }
    
    
 var financialYear =$("#financialYear").val();
 
  
     if(tefType == 'Actuals' && fullPath.length == 0){
       // document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please attach a file for Actuals.</font>";
          x0p( '','Please attach a file for Actuals!','info'); 
          return false;
    }
     else if(tefType == 'Declarations' && fullPath.length == 0){
        // document.getElementById('resultMessage').innerHTML = "<font color=red>Please attach a file for Actuals.</font>";
          x0p( '','Please attach previous financial year receipts for Declarations!','info');
          return false
    }
    if(document.getElementById("uploadTrForm12BB").style.visibility == "visible"){
             var file1 = document.getElementById('file1').value;  
         
    if(file1.length == 0){
      //  alert(data)
         // document.getElementById("load").style.display = 'none';
         x0p( '','Please attach a file for Form12BB!','info');
         // document.getElementById("addButton").disabled=false;
         return false;
     }
       }
    
    
        if(taxStatus == "Approved"){
               
            if(categoryId == '1' || categoryId == '2' || (categoryId == '3' && exemptionTypeId == '15') || (categoryId == '3' && exemptionTypeId == '16') || categoryId == '4' || categoryId == '5' || categoryId == '6'){
   var tefId = "";
            var trueFlag = getEmpSavingsValidate(tefId,taxApprovedAmount,categoryId,exemptionTypeId);
   //  alert("trueFlag"+trueFlag)
  if(trueFlag == "false"){
      return false;
  }
            }
//            if(categoryId == '1'){
//                var savings1= parseFloat(document.getElementById("empSaving1").value)+ parseFloat(taxApprovedAmount); 
//                if(savings1 > 150000){
//                   // alert("EmpSavings1(80 C) should not exceed Rs150000/-");
//                    x0p( '','EmpSavings1(80 C) should not exceed Rs150000/-','info'); 
//                    var savingDifference=parseFloat(savings1)-150000;
//                    var allowedAmmount=parseFloat(taxApprovedAmount)-parseFloat(savingDifference); 
//                    document.getElementById("taxApprovedAmount").value = allowedAmmount.toFixed(2);
//                    setTimeout(function(){
//                        document.addTaxForm.taxApprovedAmount.focus()
//                    }, 10);
//                    return false;
//             
//                }
//                else{
//                    document.getElementById("empSaving1").value = savings1.toFixed(2);
//                //  return true;
//                }
//            }
//            if(categoryId == '2'){
//   
//                var savings2=  parseFloat(document.getElementById("empSaving2").value)+ parseFloat(taxApprovedAmount); 
//                if(savings2 > 200000){
//                   // alert("EmpSavings2(24B) should not exceed Rs200000/-");
//                    x0p( '','EmpSavings2(24B) should not exceed Rs200000/-','info'); 
//                    var savingDifference=parseFloat(savings2)-200000;
//                    var allowedAmmount=parseFloat(taxApprovedAmount)-parseFloat(savingDifference); 
//                    document.getElementById("taxApprovedAmount").value = allowedAmmount.toFixed(2);
//                    setTimeout(function(){
//                        document.addTaxForm.taxApprovedAmount.focus()
//                    }, 10);
//                    return false;
//                }else{
//                    document.getElementById("empSaving2").value = savings2.toFixed(2);
//                // return true;
//                }
//            }
//            if(categoryId == '3' && exemptionTypeId == '15'){
//                // if(categoryId == '3'){
//                var empSaving3_parents=  parseFloat(document.getElementById("empSaving3_parents").value)+ parseFloat(taxApprovedAmount); 
//                if(empSaving3_parents > 30000){
//                   // alert("EmpSavings3(80D Parents Insurance) for selected exception category  should not exceed Rs30000/-");
//                    x0p( '','EmpSavings3(80D Parents Insurance) for selected exception category  should not exceed Rs30000/-','info'); 
//                    var savingDifference=parseFloat(empSaving3_parents)-30000;
//                    var allowedAmmount=parseFloat(taxApprovedAmount)-parseFloat(savingDifference); 
//                    document.getElementById("taxApprovedAmount").value = allowedAmmount.toFixed(2);
//                    setTimeout(function(){
//                        document.addTaxForm.taxApprovedAmount.focus()
//                    }, 10);
//                    return false;
//                }
//                else{
//                    document.getElementById("empSaving3_parents").value = empSaving3_parents.toFixed(2);
//                // return true;
//                }
//            }
//            if(categoryId == '3' && exemptionTypeId == '16'){
//                var empSaving3_self=  parseFloat(document.getElementById("empSaving3_self").value)+ parseFloat(taxApprovedAmount)+parseFloat(document.getElementById("empSavingsHealthAmt").value); 
//                if(empSaving3_self > 25000){
//                   // alert("EmpSavings3(80D Self Insurance etc.) for selected exception category should not exceed Rs25000/-");
//                    x0p( '','EmpSavings3(80D Self Insurance etc.) for selected exception category should not exceed Rs25000/-','info'); 
//                    var savingDifference=parseFloat(empSaving3_self)-25000;
//                    var allowedAmmount=parseFloat(taxApprovedAmount)-parseFloat(savingDifference);
//                    allowedAmmount.toFixed(2);
//                    document.getElementById("taxApprovedAmount").value = allowedAmmount.toFixed(2);
//                    setTimeout(function(){
//                        document.addTaxForm.taxApprovedAmount.focus()
//                    }, 10);
//                    return false;
//                }
//                else{
//                    document.getElementById("empSaving3_self").value = empSaving3_self.toFixed(2);
//                //  return true;
//                }
//            }
//   
//            if(categoryId == '4'){
//                var savings4=  parseFloat(document.getElementById("empSaving4").value)+ parseFloat(taxApprovedAmount); 
//                document.getElementById("empSaving4").value = savings4.toFixed(2);
//            // return true;
//            }
//            if(categoryId == '5'){
//                var savings5=  parseFloat(document.getElementById("empSaving5").value)+ parseFloat(taxApprovedAmount); 
//                document.getElementById("empSaving5").value = savings5.toFixed(2);
//            //  return true;
//            }
//            if(categoryId == '6'){
//   
//                var savings6=  parseFloat(document.getElementById("empSaving6").value)+ parseFloat(taxApprovedAmount); 
//                if(savings6 > 50000){
//                    //alert("EmpSavings2(24B) should not exceed Rs 200000/-");
//                    x0p( '','National pension scheme(NPS)-80CCD should not exceed Rs 50000/-','info'); 
//                    var savingDifference=parseFloat(savings6)-50000;
//                    var allowedAmmount=parseFloat(taxApprovedAmount)-parseFloat(savingDifference); 
//                    document.getElementById("taxApprovedAmount").value = allowedAmmount.toFixed(2);
//                    setTimeout(function(){
//                        document.addTaxForm.taxApprovedAmount.focus()
//                    }, 10);
//                    return false;
//                }else{
//                    document.getElementById("empSaving6").value = savings6.toFixed(2);
//                // return true;
//                }
//            }

         
        }
        
   
       
        
        
        
        setTimeout(disableFunction,1);
        document.getElementById("loadTAxAdd").style.display = 'block';
        
         if(window.XMLHttpRequest){
            xmlHttpRequest=new XMLHttpRequest();
        }
        else if(window.ActiveXObject){
            xmlHttpRequest=new ActiveXObject("Microsoft.XMLHttp");
          
        }
        else{
            alert("browser does not support ajax");
            return false;
        }
        xmlHttpRequest.onreadystatechange=function(){
            
            if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200) {
        //document.getElementById("msg").innerHTML =data;
        var data=xmlHttpRequest.responseText;
      //  alert(data +"  "+data.indexOf("uploaded"));
        var displaymessage = "<font color=red>Please try again later</font>";
                // alert("Add-->"+data.indexOf("uploaded"));
                if(data=="uploaded"){
               
                    displaymessage = "<font color=green>Tax exemption added successfully.</font>";
                }
                if(data=="Notvalid"){
                
                    displaymessage = "<font color=red>Not a valid file!,Please check the file and try again.</font>";
                }
                 if(data=="Error"){
               
                    displaymessage = "<font color=red>Internal Error!, Please try again later.</font>";
                    document.getElementById("addButton").disabled=false;
                }
                if(data=="existed"){
                        displaymessage = "<font color=red>You have already added Excemption for the given dates.</font>";
                }

               
           
                document.getElementById("loadTAxAdd").style.display = 'none';
                document.getElementById('resultMessageTaxAdd').innerHTML = displaymessage;//"<font color=green>File uploaded successfully</font>";
        
           //    alert(data);
       
    }
        };
     
       

     
        var data = new FormData();
        var file = document.getElementById('file')
        data.append('file', file.files[0]);
        if(document.getElementById("uploadTrForm12BB").style.visibility == "visible"){
             var file1 = document.getElementById('file1');  
        // alert("===="+file1.value+"====")
     if(file1.value.length != 0){
        
        
          data.append('file1', file1.files[0]);
     }
       }
        var url='addTaxAssumptionFromPayroll.action?exemptionId='+exemptionTypeId+'&overlayApprovedAmount='+taxApprovedAmount+'&overlaySavingAmount='+taxAmount+'&paymentDateEmp='+paymentDateTax+'&comments='+escape(taxcomments)+'&overLayStatus='+taxStatus+'&loginId='+empLoginId+'&tefType='+tefType+'&ownerName='+ownerName+'&panNumber='+PANNumber+'&rentStartDate='+rentStartDate+'&rentEndDate='+rentEndDate+'&monthlyAmount='+monthlyAmount+'&financialYear='+financialYear+'&validityDate='+validityDate+'&policyNumber='+policyNumber+'&licPremium='+licPremium+'&houseAddress='+escape(houseAddress)+'&q='+new Date();
       
      // alert(url);
       //'timsheetAttachmentUpload.action?empId='+empId+"&timeSheetID="+timeSheetID,
        xmlHttpRequest.open("POST", url, true);
        
        xmlHttpRequest.send(data);
//        $.ajaxFileUpload({
//             
//            url:'addTaxAssumptionFromPayroll.action?exemptionId='+exemptionTypeId+'&overlayApprovedAmount='+taxApprovedAmount+'&overlaySavingAmount='+taxAmount+'&paymentDateEmp='+paymentDateTax+'&comments='+escape(taxcomments)+'&overLayStatus='+taxStatus+'&loginId='+empLoginId+'&tefType='+tefType+'&ownerName='+ownerName+'&panNumber='+PANNumber+'&rentStartDate='+rentStartDate+'&rentEndDate='+rentEndDate+'&monthlyAmount='+monthlyAmount+'&financialYear='+financialYear+'&validityDate='+validityDate+'&policyNumber='+policyNumber+'&licPremium='+licPremium,//
//            secureuri:false,//false
//            fileElementId:'file',//id  <input type="file" id="file" name="file" />
//            dataType: 'json',// json
//            success: function(data,status){
//          //  alert(data);
//                var displaymessage = "<font color=red>Please try again later</font>";
//                // alert("Add-->"+data.indexOf("uploaded"));
//                if(data.indexOf("uploaded")>0){
//               
//                    displaymessage = "<font color=green>Tax exemption added Successfully.</font>";
//                }
//                if(data.indexOf("Notvalid")>0){
//                
//                    displaymessage = "<font color=red>Not a valid file!,Please check the file and try again.</font>";
//                }
//                if(data.indexOf("Error")>0){
//               
//                    displaymessage = "<font color=red>Internal Error!, Please try again later.</font>"
//                }
//               
//                if(data.indexOf("existed")>0){
//               
//                    displaymessage = "<font color=red>Record already existed for given dates.</font>"
//                }
//               
//           
//                document.getElementById("loadTAxAdd").style.display = 'none';
//                document.getElementById('resultMessageTaxAdd').innerHTML = displaymessage;//"<font color=green>File uploaded successfully</font>";
//        
//            },
//            error: function(e){
//            
//                document.getElementById("loadTAxAdd").style.display = 'none';
//                document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please try again later</font>";
//       
//            }
//        });
//    
//    

    //}	
    return false;

}





function getCategoryByExemptionTypeId(obj){
    // alert(obj.value);
    if(obj.value!=''){
   
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerTextWotLoad(req, populateCategory);
        var url = CONTENXT_PATH+"/getCategoryByExemptionTypeId.action?exemptionId="+obj.value;
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);  
    }else {
        $('#categoryId').val(0);
    }
}

function populateCategory(resText){
    //  alert(resText)x
    $('#categoryId').val(resText);
}


function getTaxAssumptionFromPayroll(taxId){
    
    var overlay = document.getElementById('overlayTaxAdd');
    var specialBox = document.getElementById('specialBoxTaxAdd');
           
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
      
       
    //  window.location="getPayRollDashBoard.action";       
        
       
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
    
    
    document.getElementById('resultMessageTaxAdd').innerHTML ='';
    hideRow("downloadAttachmentTr");
    hideRow("addFieldsTr");
    hideRow("attachmentTr");
  
    document.getElementById("loadTAxAdd").style.display = 'block';
    $.ajax({
        url:'getTaxAssumptionFromPayroll.action?tefId='+taxId,//
        context: document.body,
        success: function(responseText) {
            //  alert(responseText);
            var json = $.parseJSON(responseText);
                
            var TefId = json["TefId"];
            var ExemptionId = json["ExemptionId"];
            var Category = json["Category"];
            var ExemptionType = json["ExemptionType"];
            var SavingsAmount = json["SavingsAmount"];
           var STATUS = json["STATUS"];
         //  alert(STATUS)
            var ApprovedAmount = json["ApprovedAmount"];
            var PaymentDate = json["PaymentDate"];
            var ApproverComments = json["ApproverComments"];
            var AttachmentName = json["AttachmentName"];
            var EmpId = json["EmpId"];
            var EmpName = json["EmpName"];
            var tefType = json["tefType"];
            var loginId = json["LoginId"];
            //  alert(EmpName)
           
            // alert(tefType)
            //  alert(loginId)
            var validityDate = json["ValidityDate"];
           
            var PANNumber=json["PANNumber"];
        var    FinancialYear = json["FinancialYear"];
   var RentStartDate = json["RentStartDate"];
  var  RentEndDate = json["RentEndDate"];
  var  MonthlyAmount = json["MonthlyAmount"];
  var  Comments = json["Comments"];
  var  PolicyNumber = json["PolicyNumber"];
  var  licPremium = json["licPremium"];
  var Form12BBAttachmentName = json["Form12BBAttachmentName"];
  var houseAddress = json["HouseAddress"];
 // alert(PolicyNumber+"PolicyNumber"+licPremium)
   $('#policyNumber').val(PolicyNumber);
            $('#licPremium').val(licPremium); 
            $('#houseAddress').val(houseAddress); 
  
//  alert("AttachmentName"+AttachmentName)
  if(Form12BBAttachmentName == "" || Form12BBAttachmentName == '-' || Form12BBAttachmentName == null ){
      Form12BBAttachmentName = "-";
  }
   if(AttachmentName == "" || AttachmentName == '-' || AttachmentName == null ){
      AttachmentName = "-";
  }
 
  // alert("Form12BBAttachmentName"+Form12BBAttachmentName)
  
  document.getElementById("Form12BBAttachmentName").value = Form12BBAttachmentName;
  document.getElementById("AttachmentName").value = AttachmentName;
 
  document.getElementById("uploadTrForm12BB").style.visibility = "hidden";
   hideRow("downloadTrForm12BB");
 document.getElementById("previousOverlayAmount").value = SavingsAmount;
 // alert(STATUS+"Form12BBAttachmentName"+Form12BBAttachmentName)
  if(STATUS == 'Applied' || STATUS == 'Approved'){
      document.getElementById("Attachment").innerHTML = "Update Attachment :";
      showRow("attachmentTr");
      showRow("downloadAttachmentTr");
      hideRow("uploadTr");
       }
//       if(STATUS == 'Approved'){
//           hideRow("downloadTrForm12BB");
//       }
  
 
  document.getElementById('employeeComents').value=Comments;
  document.getElementById('financialYear').value=FinancialYear;
    document.getElementById('overLayrentStartDate').value=RentStartDate;
    document.getElementById('overLayrentEndDate').value=RentEndDate;
    document.getElementById('overlaymonthlyAmount').value=MonthlyAmount;
    
    if(ExemptionId=="18"){
        $("#paymentDateTr").hide();
        $("#rentAmmount").show();
        $("#rentDatesId").show();
        $("#houseTr").show();
        $("#panDetailsTr").show();
        document.getElementById("savingAmountLable").innerHTML="Total&nbsp;Rent&nbsp;Projected&nbsp;:";
         $('#downloadSpanForm12BB').html("<a href='#' onclick=downloadTefAttachementForm12BB("+TefId+","+EmpId+")><font color=green size=2px>"+Form12BBAttachmentName+"</font></a>");


 //        if(SavingsAmount > 100000 ){
//             showRow("downloadTrForm12BB");
//        }else{
//            hideRow("downloadTrForm12BB");
//        }
if(ApprovedAmount >100000 && (STATUS == 'Applied' || STATUS == 'Approved')){
  document.getElementById("Form12BB").innerHTML = "Update Form12BB :";
   document.getElementById("uploadTrForm12BB").style.visibility = "visible";
    
          
    
}
      if(ApprovedAmount > 100000 || SavingsAmount > 100000 ){
            
            showRow("downloadTrForm12BB");
           
        }
        document.getElementById("overlaySavingAmount").readOnly=true;
         setRentSratDateValues(document.getElementById('overLayrentStartDate'),RentStartDate);
        setRentEndDateValues(document.getElementById('overLayrentEndDate'),RentEndDate);
    }else{
        $("#paymentDateTr").show();
        $("#rentAmmount").hide();
        $("#rentDatesId").hide();
          hideRow("downloadTrForm12BB");
          $("#houseTr").hide();
           $("#panDetailsTr").hide();
          document.getElementById("savingAmountLable").innerHTML="AppliedAmount&nbsp;:";
    
        document.getElementById("overlaySavingAmount").readOnly=false;
    }
            //alert(json["ownerName"]);
          
            var OwnerName=json["ownerName"];
              $('#ownerName').val(OwnerName);
              $('#PANNumber').val(PANNumber);
          /*  if(OwnerName.trim()!=""){
             document.getElementById("panDetailsTr").style.visibility = 'visible';
             
              $('#ownerName').val(OwnerName);
              $('#PANNumber').val(PANNumber);
              
        }else{
             document.getElementById("panDetailsTr").style.visibility = 'hidden';
            
        }*/
            $('#validityDate').val(validityDate);
            $('#taxExemptionId').val(TefId);
            $('#empLoginId').val();
            $('#exemptionTypeId').val(ExemptionId);
            $('#paymentDateTax').val(PaymentDate);
            $('#overlaySavingAmount').val(SavingsAmount);
            if(STATUS == 'Applied'){
           $('#taxStatus').val("Approved");
            }
else{
     $('#taxStatus').val(STATUS);
}            $('#taxcomments').val(ApproverComments);
            $('#taxApprovedAmount').val(ApprovedAmount);
            $('#empSaving1').val(json["empSaving1"]);
            $('#empSaving2').val(json["empSaving2"]);
            $('#empSaving3_parents').val(json["empSaving3_parens"]);
            $('#empSaving4').val(json["empSaving4"]);
            $('#empSaving5').val(json["empSaving5"]);
            $('#empSaving3_self').val(json["empSaving3_self"]);
            $('#empSavingsHealthAmt').val(json["healthAmt"]);
            $('#empSaving6').val(json["empSaving6"]);
            $('#categoryId').val(Category);
            $('#empId').val(json["EmpId"]);
            $('#tefType').val(json["tefType"]);
            $('#PANNumber').val(json["PANNumber"]);
            $('#ownerName').val(json["ownerName"]);
            $('#validityDate').val(json["ValidityDate"]);
            $('#empLoginId').val(json["LoginId"]);
                 
            showRow("employeeTr");
                  
                  
        //    if(STATUS=='Approved')
         //       $('#updateButton').hide();
                  
            $('#exemptionTypeId').hide();
            $('#exemptionTypeValue').html("<font color=green size=2px>"+ExemptionType+"</font>");
                   document.getElementById("exemptionName").value = ExemptionType; 
                  
            $('#empNameSpan').html("<font color=green size=2px>"+EmpName+"</font>");
           if(ExemptionId=='1'){
              
                  $('#licPremiumTR').show();
                  
            
          
            }else{
                 $('#licPremiumTR').hide();
               
            }
            showRow("downloadAttachmentTr");
            
            if(AttachmentName != '-')
                $('#downloadSpan').html("<a href='#' onclick=downloadTefAttachement("+TefId+","+EmpId+")><font color=green size=2px>"+AttachmentName+"</font></a>");
            else
                $('#downloadSpan').html("<font color=green size=2px>"+AttachmentName+"</font>");
                 
                      
                  
                  
            //alert(ApproverComments);
            document.getElementById("loadTAxAdd").style.display = 'none';
            document.getElementById("taxHeaderLabel").style.color="white";
            document.getElementById("taxHeaderLabel").innerHTML="Edit Tax Exemptions";
            document.getElementById('resultMessageTaxAdd').innerHTML = "";


                    
                
        }, 
        error: function(e){
            document.getElementById("loadTAxAdd").style.display = 'none';
           // alert("error-->"+e);
             x0p( '','error ---'+e,'info');
        }
    }); 
}




function hideRow(id) {
    //alert(id);
    var row = document.getElementById(id);
    row.style.display = 'none';
}
function showRow(id) {
    //  alert(id);
    var row = document.getElementById(id);
    row.style.display = '';
} 

function updateTaxExemptionFromPayroll(){
    document.getElementById('resultMessageTaxAdd').innerHTML ='';
    //   var empLoginId = $('#empLoginId').val();
    var validityDate = $('#validityDate').val();
    var exemptionTypeId = $('#exemptionTypeId').val();
    var tefId = $('#taxExemptionId').val();
    var paymentDateTax = $('#paymentDateTax').val();
    var taxAmount = $('#overlaySavingAmount').val();
  //  alert("taxAmount..."+taxAmount)
    var taxStatus = $('#taxStatus').val();
    var taxcomments = $('#taxcomments').val();
    var taxApprovedAmount = $('#taxApprovedAmount').val();
 //  alert("taxApprovedAmount----"+taxApprovedAmount);
    var empSaving1 = $('#empSaving1').val();
    var empSaving2 = $('#empSaving2').val();
    var empSaving3_parents = $('#empSaving3_parents').val();
    var empSaving4 = $('#empSaving4').val();
    var empSaving5 = $('#empSaving5').val();
    var categoryId = $('#categoryId').val();
    var empSaving3_self = $('#empSaving3_self').val();
    var empSavingsHealthAmt = $('#empSavingsHealthAmt').val();

    var taxStartDate = new Date($('#taxStartDate').val());
    var taxEndDate = new Date($('#taxEndDate').val());
    var givenDate = new Date($('#paymentDateTax').val());
    var tefType = $('#tefType').val();
    var ownerName = $('#ownerName').val();
    var PANNumber = $('#PANNumber').val();
     var rentStartDate =$("#overLayrentStartDate").val();
      var rentEndDate =$("#overLayrentEndDate").val();
      var monthlyAmount =$("#overlaymonthlyAmount").val();
     var policyNumber =$("#policyNumber").val();
      var licPremium =$("#licPremium").val();
      
       var Form12BBAttachmentName =$("#Form12BBAttachmentName").val();
      var AttachmentName =$("#AttachmentName").val();
      var houseAddress =$("#houseAddress").val();
      if(rentStartDate == null){
          rentStartDate = "";
      }
       if(rentEndDate == null){
          rentEndDate = "";
      }
       var d = new Date();
    var month = d.getMonth();

 var year = d.getFullYear();
 var day=d.getDate();

//alert("day==="+day);
if(month==3 && day<=15){
    year=year-1;
}
var previousOverlayAmount = $("#previousOverlayAmount").val();
// alert("previous"+previousOverlayAmount)
 var sdate = "";
                var edate = "";
                if (month >= 3) {
                    sdate = "04/01/" + year;
                    edate = "03/31/" + (year + 1);

                } else {
                    sdate = "04/01/" + (year - 1);
                    edate = "03/31/" + year;

                }
                var startDate=new Date(sdate);
                var endDate=new Date(edate);
                
    var fullPath = document.getElementById('file').value;
      var empId = $('#empId').val();
     var exemptionName = $('#exemptionName').val();
      if(taxStatus!="Rejected"){
          
	  if(exemptionTypeId=="18"){
       //  alert("hiiii"+ownerName.length)
         if(ownerName.length == 0 || ownerName.trim() == "" ){
               // document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please enter OwnerName.</font>";
                x0p( '','Please enter OwnerName!','info'); 
                return false;
         }
          if(PANNumber.length == 0 || PANNumber == " " ){
              //  document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please enter panNumber.</font>";
                x0p( '','Please enter panNumber!','info'); 
                return false;
         }
         
   }else{
       document.getElementById('ownerName').value = " ";
       document.getElementById('PANNumber').value = " ";
       
   }
   
  
    if(taxAmount.length==0 || taxAmount == 0.0){
       // document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please enter Applied amount.</font>";
        x0p( '','Please enter Applied amount!','info'); 
        return false;
    } else if(tefType.length==0){
       // document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please select type.</font>";
        x0p( '','Please select type!','info'); 
        return false;
    }
      else if(exemptionTypeId=="1" && policyNumber.trim().length==0){
       // document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please PaymentDate.</font>";
            x0p( '','Please enter Policy Number','info'); 
        return false;
    }
    else if(exemptionTypeId=="1" && licPremium==0){
       // document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please PaymentDate.</font>";
        x0p( '','Please Select Premium type','info'); 
        return false;
    }
    if(exemptionTypeId=="18" && (rentStartDate==null || rentStartDate=='null' || rentStartDate=='')){
         x0p( '','Please select rentStartDate','info'); 
        return false;
    }
    if(exemptionTypeId=="18" && (rentEndDate==null || rentEndDate=='null' || rentEndDate=='')){
         x0p( '','Please select rentEndDate','info'); 
        return false;
    }
// alert(taxApprovedAmount)
    if(taxApprovedAmount.length==0 || taxApprovedAmount == 0.0){
      //  document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please enter approved amount.</font>";
        x0p( '','Please enter approved amount!','info'); 
        return false;
    } 
    else  if(taxStatus.length==0){
      //  document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please enter approved amount.</font>";
        x0p( '','Please enter status!','info'); 
        return false;
    } 

    else if(exemptionTypeId!="18" && paymentDateTax.length==0){
       // document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please PaymentDate.</font>";
        x0p( '','Please enter PaymentDate','info'); 
        return false;
    }
    if(exemptionTypeId!="18" &&  validityDate.length==0){
   //     document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please enter validity date.</font>";
          x0p( '','Please enter validity date!','info'); 
        return false;
            }
//    else if(taxStartDate.getTime() > givenDate.getTime()|| taxEndDate.getTime() < givenDate.getTime()){
//        document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>PaymentDate should be between "+$('#taxStartDate').val()+" and "+$('#taxEndDate').val()+" </font>";
//        return false;
//    
//    }else 
        if(parseFloat(taxAmount)<parseFloat(taxApprovedAmount)){
        document.getElementById("taxApprovedAmount").style.background='transperant';
        document.getElementById("taxApprovedAmount").focus();
        document.getElementById('taxApprovedAmount').value="0.00";
       // document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Approved Amount should be less than or equal to Applied Amount.</font>";
        x0p( '','Approved Amount should be less than or equal to Applied Amount.','info'); 
        return false;
    } 
    
    if(exemptionTypeId!="18"){
     if(tefType == 'Declarations'){
          var paymentDate=new Date(paymentDateTax);
          if(paymentDate>startDate){
               x0p( '','You selected type as Declarations. So,PaymentDate should be last financial year !','info'); 
        return false;
          }
      }
      else if(tefType == 'Actuals'){
          var paymentDate=new Date(paymentDateTax);
     //     alert(startDate);
      //    alert(endDate);
     //     alert((paymentDate<startDate) +" test "+(paymentDate<=endDate));
          
          if(paymentDate<startDate || paymentDate>endDate){
               x0p( '','You selected type as Actuals. So,PaymentDate should be current financial year !','info'); 
        return false;
          }
      }
      
}

if(document.getElementById("uploadTrForm12BB").style.visibility == "visible"){
             var file1 = document.getElementById('file1').value;  
       //  alert("===="+Form12BBAttachmentName+"====")
    if((previousOverlayAmount < 100000 && previousOverlayAmount != 0 && file1.length == 0) || ( Form12BBAttachmentName == '-' && file1.length == 0) ){
      //  alert(data)
         // document.getElementById("load").style.display = 'none';
         x0p( '','Please attach a file for Form12BB!','info');
         // document.getElementById("addButton").disabled=false;
         return false;
       }
       }
      
       var file = document.getElementById('file').value;  
        if(AttachmentName == '-' && file.length == 0){
             x0p( '','Please attach a file for '+tefType,'info');
         // document.getElementById("addButton").disabled=false;
         return false;
        }
    
		var renstartDate=new Date(rentStartDate);
 var renendDate=new Date(rentEndDate);

 if(renstartDate>renendDate){
        x0p( '','End date should be greater than start date!','info'); 
        return false;
    }
	
if(exemptionTypeId=="18"){
        if(monthlyAmount.trim()==""){
             x0p( '','Please enter monthly amount!','info'); 
             return false;
        }
        
    }
    if(exemptionTypeId=="18"){
        if(houseAddress.trim()==""){
             x0p( '','Please enter House Address!','info'); 
             return false;
        }
        
    }
      }
      
       if(taxStatus == "Approved"){
             
         
            if(categoryId == '1' || categoryId == '2' || (categoryId == '3' && exemptionTypeId == '15') || (categoryId == '3' && exemptionTypeId == '16') || categoryId == '4' || categoryId == '5' || categoryId == '6'){
   var trueFlag = getEmpSavingsValidate(tefId,taxApprovedAmount,categoryId,exemptionTypeId);
  //   alert("trueFlag"+trueFlag)
  if(trueFlag == "false"){
      return false;
  }
                    

            }
        }
      
   
          
        setTimeout(disableFunction,1);
        document.getElementById("loadTAxAdd").style.display = 'block';
        //        $.ajaxFileUpload({
        //             
        //            url:'updateTaxAssumptionFromPayroll.action?tefId='+tefId+'&overlayApprovedAmount='+taxApprovedAmount+'&overlaySavingAmount='+taxAmount+'&paymentDateEmp='+paymentDateTax+'&comments='+escape(taxcomments)+'&overLayStatus='+taxStatus,//
        //            secureuri:false,//false
        //            fileElementId:'file',//id  <input type="file" id="file" name="file" />
        //            dataType: 'json',// json
        //            success: function(data,status){
            var financialYear =$("#financialYear").val();
              if(window.XMLHttpRequest){
            xmlHttpRequest=new XMLHttpRequest();
        }
        else if(window.ActiveXObject){
            xmlHttpRequest=new ActiveXObject("Microsoft.XMLHttp");
          
        }
        else{
            alert("browser does not support ajax");
            return false;
        }
        
      xmlHttpRequest.onreadystatechange=function(){
            
            if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200) {
    //    document.getElementById("msg").innerHTML =data;
        var data=xmlHttpRequest.responseText;
      //  alert(data +"  "+data.indexOf("uploaded"));
    //  alert(data)
         var displaymessage = "<font color=red>Please try again later</font>";
          
                if(data=="uploaded"){
               
                    displaymessage = "<font color=green>Tax exemption updated Successfully.</font>";
                }
                if(data=="Notvalid"){
                
                    displaymessage = "<font color=red>Not a valid file!,Please check the file and try again.</font>";
                }
                 if(data=="Error"){
               
                    displaymessage = "<font color=red>Internal Error!, Please try again later.</font>";
                  //  document.getElementById("addButton").disabled=false;
                }
                if(data=="existed"){
                      displaymessage = "<font color=red>Sorry! already you submited the record for given dates. Please contact payroll team</font>";
                }
           
                document.getElementById("loadTAxAdd").style.display = 'none';
                document.getElementById('resultMessageTaxAdd').innerHTML = displaymessage;//"<font color=green>File uploaded successfully</font>";
    //    alert(data);
       
    }
        };
     
        

     var data = new FormData();
        var file = document.getElementById('file')
      //  alert("==//=="+file.value+"==//==")
        if(file.value.length != 0){
          //  alert(file.value.length)
     
        data.append('file', file.files[0]);
        }
        if(document.getElementById("uploadTrForm12BB").style.visibility == "visible"){
             var file1 = document.getElementById('file1');  
   //     alert("===="+file1.value+"====")
     if(file1.value.length != 0){
        
        
          data.append('file1', file1.files[0]);
     }
       }
       
     
       
          var url= 'updateTaxAssumptionFromPayroll.action?tefId='+tefId+'&overlayApprovedAmount='+taxApprovedAmount+'&overlaySavingAmount='+taxAmount+'&paymentDateEmp='+paymentDateTax+'&comments='+escape(taxcomments)+'&overLayStatus='+taxStatus+'&empId='+empId+'&tefType='+tefType+'&panNumber='+PANNumber+'&ownerName='+ownerName+'&exemptionId='+exemptionTypeId +'&rentStartDate='+rentStartDate+'&rentEndDate='+rentEndDate+'&monthlyAmount='+monthlyAmount+'&financialYear='+financialYear+'&exemptionName='+exemptionName+'&validityDate='+validityDate+'&policyNumber='+policyNumber+'&licPremium='+licPremium+'&houseAddress='+houseAddress+'&q='+new Date();//
       
    //   alert(url);
       //'timsheetAttachmentUpload.action?empId='+empId+"&timeSheetID="+timeSheetID,
        xmlHttpRequest.open("POST", url, true);
	    
        xmlHttpRequest.send(data);
     
//        $.ajax({
//           // url:'updateTaxAssumptionFromPayroll.action?tefId='+tefId+'&overlayApprovedAmount='+taxApprovedAmount+'&overlaySavingAmount='+taxAmount+'&paymentDateEmp='+paymentDateTax+'&comments='+escape(taxcomments)+'&overLayStatus='+taxStatus+'&validityDate='+validityDate+'&empId='+empId+'&tefType='+tefType+'&panNumber='+PANNumber+'&ownerName='+ownerName,//
//           url:'updateTaxAssumptionFromPayroll.action?tefId='+tefId+'&overlayApprovedAmount='+taxApprovedAmount+'&overlaySavingAmount='+taxAmount+'&paymentDateEmp='+paymentDateTax+'&comments='+escape(taxcomments)+'&overLayStatus='+taxStatus+'&empId='+empId+'&tefType='+tefType+'&panNumber='+PANNumber+'&ownerName='+ownerName+'&exemptionId='+exemptionTypeId +'&rentStartDate='+rentStartDate+'&rentEndDate='+rentEndDate+'&monthlyAmount='+monthlyAmount+'&financialYear='+financialYear+'&exemptionName='+exemptionName+'&validityDate='+validityDate+'&policyNumber='+policyNumber+'&licPremium='+licPremium,//
// 
//            context: document.body,
//            success: function(responseText) {
//                // alert(responseText);
//                //var json = $.parseJSON(responseText);
//                
//                //  var message = json["message"];
//            
//                //alert(responseText.indexOf("uploaded"));
//                var displaymessage = "<font color=red>Please try again later</font>";
//          
//                if(responseText=="uploaded"){
//               
//                    displaymessage = "<font color=green>Tax exemption updated successfully.</font>";
//                }
//                if(responseText=="Notvalid"){
//                
//                    displaymessage = "<font color=red>Not a valid file!,Please check the file and try again.</font>";
//                }
//                if(responseText=="Error"){
//               
//                    displaymessage = "<font color=red>Internal Error!, Please try again later.</font>"
//                }
//           
//                document.getElementById("loadTAxAdd").style.display = 'none';
//                document.getElementById('resultMessageTaxAdd').innerHTML = displaymessage;//"<font color=green>File uploaded successfully</font>";
//        
//            },
//            error: function(e){
//            
//                document.getElementById("loadTAxAdd").style.display = 'none';
//                document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please try again later</font>";
//       
//            }
//        });
    
    

    //}	
    return false;

}


 

function goToReleasePayslip(){

    window.location = "getReleasePayslip.action"

}

function toggleReleasesOverlay(Id,date,status){
     
    document.getElementById("releasePayslipResultMessage").innerHTML="";

    document.getElementById("headerLabel").style.color="white";
    if(Id != 0){
        document.getElementById('edit').style.display= "block";
        document.getElementById('add').style.display= "none";
        document.getElementById('Id').value=Id;
        document.getElementById('status').value=status;
        
        var dateobj= new Date(date) ;
        var month = dateobj.getMonth() + 1;
        var year = dateobj.getFullYear();
        document.getElementById('month').value=month;
        document.getElementById('year').value=year;

        document.getElementById("headerLabel").innerHTML="Edit ReleasePayslip";
    }
    else{
        // alert("else")
        document.getElementById('status').disabled= "Release";
        document.getElementById('add').style.display= "block";
        document.getElementById('edit').style.display= "none";
        document.getElementById("headerLabel").innerHTML="Add ReleasePayslip";
    }
    var overlay = document.getElementById('releasePayslipOverlay');
    var specialBox = document.getElementById('releasePayslipSpecailbox');
    //    ReleasePayslipEditOverlay({Id})
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        //    alert("in block");
        overlay.style.display = "none";
        specialBox.style.display = "none";
        window.location="getReleasePayslip.action";
                   
    } else {
        //  alert("in else");
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }   
}



function doAddPayslipReleases(){
    var month = document.getElementById("month").value;
    var year = document.getElementById("year").value;
    var status = document.getElementById("status").value;
    
    //  alert("--------"+year)

    $.ajax({
        url:'doAddPayslipReleases.action?month='+month+'&year='+year+'&status='+status,
        context: document.body,
        success: function(responseText) {
           // alert(responseText == "success")
            var displaymessage = "<font color=red>Please try again later</font>";
            if(responseText == "success"){
               
                displaymessage = "<font color=green>ReleasePayroll Successfully.</font>";
            }
            if(responseText == "Nowage"){
                displaymessage = "<font color=red>No wage records exists for the given Month and Year !!</font>";
            }
            if(responseText == "exists"){
                displaymessage = "<font color=red>This month Payslip is already Released</font>";
            }
            if(responseText == "error"){
               
                displaymessage = "<font color=red>Internal Error!, Please try again later.</font>"
            }
            document.getElementById("releasePayslipResultMessage").innerHTML = displaymessage;
           
        },
        error: function(e){
            document.getElementById("releasePayslipResultMessage").innerHTML = 'none';
           // alert("Please try again");
            x0p( '','Please try again','info');
        }
    });
    
}
function doEditPayslipReleases(){
    var month = document.getElementById("month").value;
    var year = document.getElementById("year").value;
    var status = document.getElementById("status").value;
  
    var Id = document.getElementById("Id").value;

    //alert("--------"+Id)
    $.ajax({
        url:'doEditPayslipReleases.action?month='+month+'&year='+year+'&status='+status+'&Id='+Id,
        context: document.body,
        success: function(responseText) {
                     //alert(responseText)
            var displaymessage = "<font color=red>Please try again later</font>";
            if(responseText == "success"){
               
                displaymessage = "<font color=green>ReleasedPayroll edited Successfully.</font>";
            }
            if(responseText == "exists"){
                displaymessage = "<font color=red>This month Payslip is already Released/Revoked</font>";
            }
            if(responseText == "Nowage"){
                displaymessage = "<font color=red>No wage records exists for the given Month and Year !!</font>";
            }
            if(responseText == "error"){
               
                displaymessage = "<font color=red>Internal Error!, Please try again later.</font>"
            }
            document.getElementById("releasePayslipResultMessage").innerHTML = displaymessage;
           
        },
        error: function(e){
            document.getElementById("releasePayslipResultMessage").innerHTML = 'none';
          //  alert("Please try again");
              x0p( '','Please try again','info');
        }
    });
}

function enableHRExemptionField(amount){
    
  //  alert(amount)
      var type = document.getElementById('overLayexemptionType').value; 
      var paymentDate = document.getElementById('overLayrentStartDate').value;
      var empIdForOverlay = document.getElementById('empIdForOverlay').value;
      var financialYear = document.getElementById('financialYear').value;
    if(type == 18){
      if(paymentDate.length == 0){
               document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter paymentDate.</font>";
               document.getElementById('overlaySavingAmount').value = "0";
               return false;
        }
        
     
   
 
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerTextWotLoad(req, populateCategoryHRExemption);
        var url = CONTENXT_PATH+"/getSumOfHRA.action?overlaySavingAmount="+amount.value+"&paymentDateEmp="+paymentDate+"&empId="+empIdForOverlay+"&financialYear="+financialYear;
        //alert(url)
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);  
    }
}

function enableHRExemptionFieldPayroll(amount){
    
  
      var type = document.getElementById('exemptionTypeId').value; 
   //   alert(type)
      var empId = document.getElementById('empId').value; 
      var paymentDate = document.getElementById('overLayrentStartDate').value;
          var financialYear = document.getElementById('financialYear').value;
         if(type == 18){
        if(paymentDate.length == 0){
               document.getElementById('resultMessageTaxAdd').innerHTML = "<font color=red>Please enter paymentDate.</font>";
                 document.getElementById('taxApprovedAmount').value = "0";
               return false;
        }
     
  
 
   
        var req = newXMLHttpRequest();
        req.onreadystatechange = readyStateHandlerTextWotLoad(req, populateCategoryHRExemption);
       var url = CONTENXT_PATH+"/getSumOfHRA.action?overlaySavingAmount="+amount.value+"&paymentDateEmp="+paymentDate+"&empId="+empId+"&financialYear="+financialYear;
        // alert(url)
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);  
    }
}

function populateCategoryHRExemption(resText){
   // alert(resText)
       document.getElementById("panDetailsTr").style.visibility = 'visible';
     
       
  /*    if(resText > 150000 ){
             
             document.getElementById("panDetailsTr").style.visibility = 'visible';
             
  //                      document.getElementById('ownerName').style.display='block';
//     document.getElementById('PANNumber').style.display='block';
//      document.getElementById('PANNumberlabel').style.display='block';
//     document.getElementById('ownerNamelabel').style.display='block';
                }
               else{
                    document.getElementById("panDetailsTr").style.visibility = 'hidden';
//                    document.getElementById('ownerName').style.display='none'; 
//   document.getElementById('PANNumber').style.display='none'; 
//    document.getElementById('PANNumberlabel').style.display='none'; 
//   document.getElementById('ownerNamelabel').style.display='none'; 
               }*/
              
                 if(resText > 100000 ){
                   //  alert("restText")
                     document.getElementById("uploadTrForm12BB").style.visibility = "visible";
                     document.getElementById("Form12BB").innerHTML = "Form12BB <FONT color='red'  ><em>*</em></FONT>:";
                 //    showRow("label");
                //   document.getElementById('uploadTrForm12BB').style.visibility = "visible";
                    
               }else{
                   document.getElementById("uploadTrForm12BB").style.visibility = "hidden";
                //   hideRow("label");
               //     document.getElementById('uploadTrForm12BB').style.visibility = "hidden";
               }
            
    
}


function CalculateActualDetails(){
    var result1=false;
     if(document.getElementById("doRepaymentFlag").checked==false){
         x0p('Repayment is not checked.Do you want to continue without repayment?', '', 'warning',
     function(button) {
       
        if(button == 'warning') {
            result1 = true;
            //alert(button)
        }
        if(button == 'cancel') { 
             result1 = false;
        }
       // alert(result1);
           if(result1){
                 CalculateActualDetails1();
        
                }else
                {
                    return false;
                }
                
     }); 
     }else{
         CalculateActualDetails1();
     }
      
    
    
    
}
function CalculateActualDetails1(){
// alert("result1");
 var payRollId=document.getElementById('payRollId').value;
    var payPeriodStartDate=document.getElementById('payPeriodStartDate').value;
    var daysInMonth=document.getElementById('daysInMonth').value;
    var daysProject=document.getElementById('daysProject').value;
    var daysVacation=document.getElementById('daysVacation').value;
    var vactionsAvailable=document.getElementById('vactionsAvailable').value;
    var daysHolidays=document.getElementById('daysHolidays').value;
    var leavesApplied=document.getElementById('leavesApplied').value;
    var daysWeekends=document.getElementById('daysWeekends').value;
    var dedProfessionalTax=document.getElementById('dedProfessionalTax').value;
    var basic=document.getElementById('basic').value;
    var da=document.getElementById('da').value;
    var hra=document.getElementById('hra').value;
    var ta=document.getElementById('ta').value;
    var ra=document.getElementById('ra').value;
    var life=document.getElementById('life').value;
    var health=document.getElementById('dedHealth').value;
    var attendanceAllow=document.getElementById('attendanceAllow').value;
    var projectPay=document.getElementById('projectPay').value;
    var employeresi=document.getElementById('employeresi').value;
    var maidServices=document.getElementById('maidServices').value;
    var entertainment=document.getElementById('entertainment').value;
    var kidsEducation=document.getElementById('kidsEducation').value;
    var vehicleAllowance=document.getElementById('vehicleAllowance').value;
    var longTermBonus=document.getElementById('longTermBonus').value;
    var employerPf=document.getElementById('employerPf').value;
    var splAllowance=document.getElementById('splAllowance').value;
    var cca=document.getElementById('cca').value;
    var miscPay=document.getElementById('miscPay').value;
    var employeePfPayRollDetails=document.getElementById('employeePfPayRollDetails').value;
    var grossPayPayRollDetails=document.getElementById('grossPayPayRollDetails').value;
    var employeeesi=document.getElementById('employeeesi').value;
    var variablePay=document.getElementById('variablePay').value;
    var dedOthers=  document.getElementById("dedOthers").value;
    var daysWorked=  document.getElementById("daysWorked").value;
    var bonusCommission=  document.getElementById("bonusCommission1").value;
    var otherAdditions=  document.getElementById("otherAdditions1").value;
   
    var data = {};
    
    data["payRollId"] = payRollId;
    data["payPeriodStartDate"] = payPeriodStartDate;
    data["daysInMonth"] = daysInMonth;
    data["daysProject"] = daysProject;
    data["daysVacation"] = daysVacation;
    data["vactionsAvailable"] = vactionsAvailable;
    data["daysHolidays"] = daysHolidays;
    data["leavesApplied"] = leavesApplied;
    data["daysWeekends"] = daysWeekends;
    data["daysWorked"] = daysWorked;
    data["dedProfessionalTax"] = dedProfessionalTax;
    data["basic"] = basic;
    data["da"] = da;
    data["hra"] = hra;
    data["ta"] = ta;
    data["ra"] = ra;
    data["life"] = life;
    data["health"] = health;
    data["attendanceAllow"] = attendanceAllow;
    data["projectPay"] = projectPay;
    data["employeresi"] = employeresi;
    data["maidServices"] = maidServices;
    data["entertainment"] = entertainment;
    data["kidsEducation"] = kidsEducation;
    data["vehicleAllowance"] = vehicleAllowance;
    data["longTermBonus"] = longTermBonus;
    data["employerPf"] = employerPf;
    data["splAllowance"] = splAllowance;
    data["cca"] = cca;
    data["miscPay"] = miscPay;
    data["employeePfPayRollDetails"] = employeePfPayRollDetails;
    data["grossPayPayRollDetails"] = grossPayPayRollDetails;
    data["employeeesi"] = employeeesi;
    data["variablePay"] = variablePay;
    data["dedOthers"] = dedOthers;
    data["bonusCommission"] = bonusCommission;
    data["otherAdditions"] = otherAdditions;
    var json = JSON.stringify(data);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerText(req, CalculateActualDetailsResponse);
    var url = CONTENXT_PATH+"/calculateActualDetails.action?resultString="+json;

    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function CalculateActualDetailsResponse(responseText){
    //  alert(responseText);
    var  resultJson = JSON.parse(responseText);
    //alert(resultJson["earnedEmployeeesi"]);
    var grossPayActualDetails=document.getElementById("grossPayActualDetails").value;
    var netPaidActualDetails=document.getElementById("netPaidActualDetails").value;
   
    var earnedProjectPay=document.getElementById("earnedProjectPay").value;
    var earnedattallowance=document.getElementById("earnedattallowance").value;
    var earnedLongTermBonus=document.getElementById("earnedLongTermBonus").value;
   
    var EarnedVariablePay = parseFloat(earnedLongTermBonus)+parseFloat(earnedProjectPay)+parseFloat(earnedattallowance);
    var newEarnedVariablePay=parseFloat(resultJson["earnedLongTermBonus"])+parseFloat(resultJson["earnedProjectPay"])+parseFloat(resultJson["earnedattallowance"]);
    //alert(EarnedVariablePay);
    //alert(newEarnedVariablePay);
    var newgrossPayActualDetails=parseFloat(resultJson["grossPayActualDetails"]).toFixed(2);
    var newNetPaidActualDetails =parseFloat(resultJson["netPaidActualDetails"]).toFixed(2);
    
    var newGrossPay = parseFloat(resultJson["newgrossPayActualDetails"]).toFixed(2);
  //  alert(newGrossPay)
  
    document.getElementById("earnedBasic").value=parseFloat(resultJson["earnedBasic"]).toFixed(2);
    document.getElementById("earnedDa").value=parseFloat(resultJson["earnedDa"]).toFixed(2);
    document.getElementById("earnedHra").value=parseFloat(resultJson["earnedHra"]).toFixed(2);
    document.getElementById("earnedTa").value=parseFloat(resultJson["earnedTa"]).toFixed(2);
    document.getElementById("earnedRa").value=parseFloat(resultJson["earnedRa"]).toFixed(2);
    document.getElementById("earnedLife").value=parseFloat(resultJson["earnedLife"]).toFixed(2);
    document.getElementById("earnedHealth").value=parseFloat(resultJson["earnedHealth"]).toFixed(2);
    document.getElementById("earnedCCa").value=parseFloat(resultJson["earnedCCa"]).toFixed(2);
    document.getElementById("earnedProjectPay").value=parseFloat(resultJson["earnedProjectPay"]).toFixed(2);
    document.getElementById("earnedattallowance").value=parseFloat(resultJson["earnedattallowance"]).toFixed(2);
    document.getElementById("earnedEntertainment").value=parseFloat(resultJson["earnedEntertainment"]).toFixed(2);
    document.getElementById("earnedKidsEducation").value=parseFloat(resultJson["earnedKidsEducation"]).toFixed(2);
    document.getElementById("earnedVehicleAllowance").value=parseFloat(resultJson["earnedVehicleAllowance"]).toFixed(2);
    document.getElementById("earnedLongTermBonus").value=parseFloat(resultJson["earnedLongTermBonus"]).toFixed(2);
    document.getElementById("earnedMiscPay").value=parseFloat(resultJson["earnedMiscPay"]).toFixed(2);
    document.getElementById("earnedEmployerPf").value=parseFloat(resultJson["earnedEmployerPf"]).toFixed(2);
    document.getElementById("earnedsplallowance").value=parseFloat(resultJson["earnedsplallowance"]).toFixed(2);
    document.getElementById("tdsDeduction").value=parseFloat(resultJson["tdsDeduction"]).toFixed(2);
    document.getElementById("tdsDeductionHidden").value=parseFloat(resultJson["tdsDeduction"]).toFixed(2);
    
    document.getElementById("employeePfActualDetails").value=parseFloat(resultJson["employeePfActualDetails"]).toFixed(2);
    document.getElementById("grossPayActualDetails").value=parseFloat(resultJson["grossPayActualDetails"]).toFixed(2);
    document.getElementById("bonusCommission").value=parseFloat(resultJson["bonusCommission"]).toFixed(2);
    document.getElementById("netPaidActualDetails").value=parseFloat(resultJson["netPaidActualDetails"]).toFixed(2);
    document.getElementById("otherDeductions").value=parseFloat(resultJson["otherDeductions"]).toFixed(2);
    document.getElementById("taxableIncome").value=parseFloat(resultJson["taxableIncome"]).toFixed(2);
    document.getElementById("otherAdditions").value=parseFloat(resultJson["otherAdditions"]).toFixed(2);
    document.getElementById("earnedEmployeresi").value=parseFloat(resultJson["earnedEmployeresi"]).toFixed(2);
    document.getElementById("earnedEmployeeesi").value=parseFloat(resultJson["earnedEmployeeesi"]).toFixed(2);
      document.getElementById("newgrossPayActualDetails").value=parseFloat(resultJson["newgrossPayActualDetails"]).toFixed(2);
    if(document.getElementById("doRepaymentFlag").checked){
        var temv_repaymentGross=newgrossPayActualDetails-grossPayActualDetails;
        var temv_repaymentNet=newNetPaidActualDetails-netPaidActualDetails;
        
        var temv_repaymentVarriable=newEarnedVariablePay-EarnedVariablePay;
        //alert(temv_repaymentVarriable);
        document.getElementById("repaymentGross").value =temv_repaymentGross.toFixed(2);
        document.getElementById("repaymentNet").value =temv_repaymentNet.toFixed(2);
        document.getElementById("repaymentVariablePay").value =temv_repaymentVarriable.toFixed(2);
    }
}

function transperent(){
    
          

    var overlay = document.getElementById('displyID');
    var specialBox = document.getElementById('blockDiv');
          
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}



function toggleFreezeForWagesOverlay(){
    document.getElementById("freezeresultMessage").innerHTML = "";
    
    
    var overlay = document.getElementById('freezeOverlay');
    var specialBox = document.getElementById('freezePayrollOverlay');
        
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        window.location="getPayRollReports.action";
        overlay.style.display = "none";
        specialBox.style.display = "none";
                   
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }   
}

function toggleUnFreezeForWagesOverlay(){
    document.getElementById("unfreezeresultMessage").innerHTML = "";
    
    
    var overlay = document.getElementById('unfreezeOverlay');
    var specialBox = document.getElementById('unfreezePayrollOverlay');
        
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        window.location="getPayRollReports.action";
        overlay.style.display = "none";
        specialBox.style.display = "none";
                   
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }   
}

function togglecleanPayrollOverlay(){
    document.getElementById("cleanPayrollresultMessage").innerHTML = "";
    
    
    var overlay = document.getElementById('cleanPayrollOverlay');
    var specialBox = document.getElementById('cleanPayrollHubbleOverlay');
        
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        window.location="getPayRollReports.action";
        overlay.style.display = "none";
        specialBox.style.display = "none";
                   
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }   
}

function toggleGetPayslipOverlay(){
   // document.getElementById("payslipResultMessage").innerHTML = "";
    
    
    var overlay = document.getElementById('getPayslipOverlay');
    var specialBox = document.getElementById('getPayslipHubbleOverlay');
        
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        window.location="getPayRollReports.action";
        overlay.style.display = "none";
        specialBox.style.display = "none";
                   
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }   
}


function togglegetPayrollReportOverlay(){
  
    
    
    var overlay = document.getElementById('getPayrollReportOverlay');
    var specialBox = document.getElementById('getPayrollReportHubbleOverlay');
        
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        window.location="getPayRollReports.action";
        overlay.style.display = "none";
        specialBox.style.display = "none";
                   
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }   
}
function changeExemptionType(element){
    var exemptionType=element.value;
   // alert(exemptionType);
    if(exemptionType=="18"){
        $("#paymentDateTr").hide();
        $("#rentAmmount").show();
        $("#rentDatesId").show();
        $("#panDetailsTr").show();
        $("#houseTr").show();
         document.getElementById("savingAmountLable").innerHTML="Total&nbsp;Rent&nbsp;Projected&nbsp;:";
        document.getElementById("overlaySavingAmount").readOnly=true;
    }else{
        $("#paymentDateTr").show();
        $("#rentAmmount").hide();
        $("#rentDatesId").hide();
         $("#panDetailsTr").hide();
         $("#houseTr").hide();
        $("#overlaymonthlyAmount").val("");
        $("#overlaySavingAmount").val("");
        document.getElementById("savingAmountLable").innerHTML="AppliedAmount&nbsp;:";
        document.getElementById("overlaySavingAmount").readOnly=false;
    }
    if(exemptionType=="1"){
         $("#policyTdLable").show(); 
         $("#policyTdValue").show(); 
           $("#licPremiumTR").show(); 
    }else{
          $("#policyTdLable").hide();
          $("#policyTdValue").hide();
            $("#licPremiumTR").hide(); 
    }
}
function calculateYearlyAmmount(){
    var monthlyAmount=$("#overlaymonthlyAmount").val();
    
    if(monthlyAmount.trim()!=""){
    var rentStartDate=$("#overLayrentStartDate").val();
    var rentEndDate=$("#overLayrentEndDate").val();
    
    var startDate=new Date(rentStartDate);
    startDate.setDate(startDate.getDate()+5);
    var endDate=new Date(rentEndDate);
  
    var startdateMonth=startDate.getMonth()+1;
    var enddateMonth=endDate.getMonth()+1;

 
    
    
    if(startDate>endDate){
        x0p( '','End date should be greater than start date!','info'); 
        return false;
    }
    if(startdateMonth<4){
        startdateMonth+=12;
    }
    if(enddateMonth<4){
        enddateMonth+=12;
    }
      
   
    var months=parseInt(enddateMonth-startdateMonth+1);
 
    var savingAmount=parseFloat(months*parseFloat(monthlyAmount).toFixed(2)).toFixed(2);
 
    if(savingAmount=="NAN"){
        savingAmount="";
    }
    $("#overlaySavingAmount").val(savingAmount);
    
   //alert(monthlyAmount+" "+startdateMonth+" -"+enddateMonth +"="+ (enddateMonth-startdateMonth));
    enableHRExemptionField(document.getElementById("overlaySavingAmount"));
    }
     else{
          $("#overlaySavingAmount").val("");
    }
}

function deleteTefEmpDetailsFromEmployee(Id){
    
        x0p('Do you want to delete?', '', 'warning',
     function(button) {
       
        if(button == 'warning') {
            result1 = true;
            //alert(button)
        }
        if(button == 'cancel') { 
             result1 = false;
        }
           if(result1){
                   // doReRunWagesScript();
                  
                 gotoDeleteTefEmpDetailsFromEmployee(Id);
                   
                    return true;
        
                }else
                {
                    return false;
                }
     });  
}

function gotoDeleteTefEmpDetailsFromEmployee(id){
   
    var req = newXMLHttpRequest();
    req.onreadystatechange =onreadystatechange = function() {
    if (req.readyState == 4 && req.status == 200) {
   //  alert(req.responseText);
       document.getElementById("frmTaxAssumptionSearch").submit();
       
    }
  };
    var url = CONTENXT_PATH+"/deleteTefEmpDetails.action?id="+id;
 // alert(url);
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    
}
function deleteTefEmpDetails(Id){
    
        x0p('Do you want to delete?', '', 'warning',
     function(button) {
       
        if(button == 'warning') {
            result1 = true;
            //alert(button)
        }
        if(button == 'cancel') { 
             result1 = false;
        }
           if(result1){
                   // doReRunWagesScript();
                  
                 gotoDeleteTefEmpDetails(Id);
                   
                    return true;
        
                }else
                {
                    return false;
                }
     });  
}

function gotoDeleteTefEmpDetails(id){
   
    var req = newXMLHttpRequest();
    req.onreadystatechange =onreadystatechange = function() {
    if (req.readyState == 4 && req.status == 200) {
   //  alert(req.responseText);
   //    document.getElementById("frmTaxAssumptionSearch").submit();
          document.getElementById("PayrollTaxAssumptionSearch").submit();
    }
  };
    var url = CONTENXT_PATH+"/deleteTefEmpDetails.action?id="+id;
 // alert(url);
    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    
}

function calculatetds(){
    (document.getElementById("loadingMessageForFreeze")).style.display = "none";
    document.getElementById("resultMessage").innerHTML ="";
    var year=document.getElementById("yearOverlay").value;
 
    var month=document.getElementById("monthOverlay").value;   
    
    var noOfDays=document.getElementById("noOfDays").value;
    var noOfWeekedDays=document.getElementById("noOfWeekendDays").value;
    //var noOfHolidays=document.getElementById("noOfHolidays").value;
    var noOfWorkingDays=document.getElementById("noOfWorkingDays").value;
   var paymentDateEmp = document.getElementById("paymentDateEmp").value;
    var orgId = document.getElementById('orgId').value;
    if(year == ""){
          x0p('','Please enter Year!','info');
            return false;
    }else if(year.length > 4 || year.length<4){
        x0p('','Invalid Year!','info');
            return false;
    }
     if(month == 0 || month == ""){
          x0p('','Please select Month!','info');
            return false;
    }
   
       if(noOfDays == ""){
          x0p('','NoOfDays is Mandatory!','info');
            return false;
      }
       if(noOfWeekedDays == ""){
          x0p('','NoOfWeekedDays is Mandatory!','info');
            return false;
      }
       if(noOfWorkingDays == ""){
           x0p('','NoOfWorkingDays is Mandatory!','info');
            return false;
      }
       if(paymentDateEmp == ""){
          x0p('','Please select PaymentDate!','info');
            return false;
    }
     if(orgId.trim()==""){
            x0p('','Please select an Organization!','info');
            return false;
      }
       
      
      
      //alert(paymentDateEmp)
   

  
      window.location="tdsReportGeneration.action?year="+year+"&month="+month+"&noOfDays="+noOfDays+"&noOfWeekendDays="+noOfWeekedDays+"&noOfWorkingDays="+noOfWorkingDays+"&orgName="+orgId+"&paymentDateEmp="+paymentDateEmp;

     
}

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

function showNextTab(tab){
//    $("#employeeDetails").hide();
//    $("#contactDetails").hide();
//    $("#payrollDetails").hide();
//    $('#'+tab).show();
    $('#'+tab).click();
    
}
 function getReleaseDate(){
   // alert("test");
  
   if(document.getElementById('isBlock').checked){
       // document.getElementById('releasedDate').style.display = "block";
       // document.getElementById('releaseDateName').style.display = "block";
      //   document.getElementById('dateImg').style.display = "block";
         $("#releaseDateCol").show();
         $("#releaseDateName").show();
       //   $("#dateImg").show();
   }
   else{
//      document.getElementById('releasedDate').style.display = "none";
//      document.getElementById('releaseDateName').style.display = "none";
//       document.getElementById('dateImg').style.display = "none";
 $("#releaseDateCol").hide();
         $("#releaseDateName").hide();
        //   $("#dateImg").hide();
       
   }
    
}


function getSampleForm12BB(){
    window.location = "getSampleForm12BB.action";
}
function taxExemptiondownloadFileForm12BB(id){
    window.location = "taxExemptionDownloadForm12BB.action?exemptionId="+id;
}
function downloadTefAttachementForm12BB(tefId,empId){
    window.location="downloadTefAttachmentForm12BB.action?tefId="+tefId+"&empId="+empId+"&fromTef="+document.getElementById("fromTef").value;
    
}



function getEmpSavingsValidate(tefId,taxApprovedAmount,categoryId,exemptionTypeId,callback){
   var empId = document.getElementById("empId").value;
//alert("empId..."+empId)
var result;
 //   alert("---777777-----")
   var  url=CONTENXT_PATH+'/getEmpSavingsValidate.action?tefId='+tefId+'&taxableIncome='+taxApprovedAmount+'&categoryId='+categoryId+'&exemptionId='+exemptionTypeId+'&empId='+empId;
 // alert(url);
  $.ajax({
       // url:CONTENXT_PATH+"/getEmpSavingsValidate.action?tefId="+tefId+"&taxableIncome="+taxApprovedAmount+"&categoryId="+categoryId+"&exemptionId="+exemptionTypeId+"&empId="+empId,
      
       url:url,
       async: false,
       context: document.body,
        success: function(resText) {
                        // alert(resText)
  
    var exemptionTypeId = document.getElementById('exemptionTypeId').value; 
                if(categoryId == '1'){
                var savings1= parseFloat(resText)+ parseFloat(taxApprovedAmount); 
                if(savings1 > 150000){
                   // alert("EmpSavings1(80 C) should not exceed Rs150000/-");
                    x0p( '','EmpSavings1(80 C) should not exceed Rs150000/-','info'); 
                    
                    var savingDifference=parseFloat(savings1)-150000;
                    var allowedAmmount=parseFloat(taxApprovedAmount)-parseFloat(savingDifference); 
                    document.getElementById("taxApprovedAmount").value = allowedAmmount.toFixed(2);
                       
                    setTimeout(function(){
                        document.addTaxForm.taxApprovedAmount.focus()
                    }, 10);
                    result = "false";
                 return false;
                
             
                }
                else{
                    document.getElementById("empSaving1").value = savings1.toFixed(2);
                     result = "true";
                //  return true;
                }
            }
            else if(categoryId == '2'){
   
                var savings2= parseFloat(resText)+ parseFloat(taxApprovedAmount); 
                if(savings2 > 200000){
                   // alert("EmpSavings2(24B) should not exceed Rs200000/-");
                    x0p( '','EmpSavings2(24B) should not exceed Rs200000/-','info'); 
                    var savingDifference=parseFloat(savings2)-200000;
                    var allowedAmmount=parseFloat(taxApprovedAmount)-parseFloat(savingDifference); 
                    document.getElementById("taxApprovedAmount").value = allowedAmmount.toFixed(2);
                    setTimeout(function(){
                        document.addTaxForm.taxApprovedAmount.focus()
                    }, 10);
                      result = "false";
                    return false;
                  
                }else{
                    document.getElementById("empSaving2").value = savings2.toFixed(2);
                     result = "true";
                // return true;
                }
            }
           else if(categoryId == '3' && exemptionTypeId == '15'){
                // if(categoryId == '3'){
                var empSaving3_parents=  parseFloat(resText)+ parseFloat(taxApprovedAmount); 
                if(empSaving3_parents > 30000){
                   // alert("EmpSavings3(80D Parents Insurance) for selected exception category  should not exceed Rs30000/-");
                    x0p( '','EmpSavings3(80D Parents Insurance) for selected exception category  should not exceed Rs30000/-','info'); 
                    var savingDifference=parseFloat(empSaving3_parents)-30000;
                    var allowedAmmount=parseFloat(taxApprovedAmount)-parseFloat(savingDifference); 
                    document.getElementById("taxApprovedAmount").value = allowedAmmount.toFixed(2);
                    setTimeout(function(){
                        document.addTaxForm.taxApprovedAmount.focus()
                    }, 10);
                      result = "false";
                    return false;
                
                }
                else{
                    document.getElementById("empSaving3_parents").value = empSaving3_parents.toFixed(2);
                     result = "true";
                // return true;
                }
            }
           else if(categoryId == '3' && exemptionTypeId == '16'){
                var empSaving3_self=  parseFloat(resText)+ parseFloat(taxApprovedAmount)+parseFloat(document.getElementById("empSavingsHealthAmt").value); 
                if(empSaving3_self > 25000){
                   // alert("EmpSavings3(80D Self Insurance etc.) for selected exception category should not exceed Rs25000/-");
                    x0p( '','EmpSavings3(80D Self Insurance etc.) for selected exception category should not exceed Rs25000/-','info'); 
                    var savingDifference=parseFloat(empSaving3_self)-25000;
                    var allowedAmmount=parseFloat(taxApprovedAmount)-parseFloat(savingDifference);
                    allowedAmmount.toFixed(2);
                    document.getElementById("taxApprovedAmount").value = allowedAmmount.toFixed(2);
                    setTimeout(function(){
                        document.addTaxForm.taxApprovedAmount.focus()
                    }, 10);
                      result = "false";
                    return false;
                  
                }
                else{
                    document.getElementById("empSaving3_self").value = empSaving3_self.toFixed(2);
                     result = "true";
                //  return true;
                }
            }
   
            else if(categoryId == '4'){
                var savings4= parseFloat(resText)+ parseFloat(taxApprovedAmount); 
                document.getElementById("empSaving4").value = savings4.toFixed(2);
                 result = "true";
            // return true;
            }
           else if(categoryId == '5'){
                var savings5=  parseFloat(resText)+ parseFloat(taxApprovedAmount); 
                document.getElementById("empSaving5").value = savings5.toFixed(2);
                  result = "true";
            //  return true;
            }
            else if(categoryId == '6'){
   
                var savings6= parseFloat(resText)+ parseFloat(taxApprovedAmount); 
                if(savings6 > 50000){
                    //alert("EmpSavings2(24B) should not exceed Rs 200000/-");
                    x0p( '','National pension scheme(NPS)-80CCD should not exceed Rs 50000/-','info'); 
                    var savingDifference=parseFloat(savings6)-50000;
                    var allowedAmmount=parseFloat(taxApprovedAmount)-parseFloat(savingDifference); 
                    document.getElementById("taxApprovedAmount").value = allowedAmmount.toFixed(2);
                    setTimeout(function(){
                        document.addTaxForm.taxApprovedAmount.focus()
                    }, 10);
                      result = "false";
                    return false;
                    
                }else{
                    document.getElementById("empSaving6").value = savings6.toFixed(2);
                     result = "true";
                // return true;
                }
            }
         },
        error: function(e){
            
          //  alert("Please try again");
              x0p( '','Please try again','info');
        }
    });
    return result;
}

function setRentSratDateValues(id,dateVal){
   // alert(date);
 var index=id.selectedIndex;
 //alert("index"+index);
 if(index<0){

var d=new Date(dateVal);
 
  var month = d.getMonth()+1;
   var year = d.getFullYear();
   //alert("month "+month)
if(month>=3){
year=year;
}else{
year=year-1;
}
//alert("id=="+id);
$(id).html('');
 $(id).append("<option value='"+year+"-04-01'>Apr "+year+"</option>");
 $(id).append("<option value='"+year+"-05-01'>May "+year+"</option>");
 $(id).append("<option value='"+year+"-06-01'>June "+year+"</option>");
 $(id).append("<option value='"+year+"-07-01'>July "+year+"</option>");
 $(id).append("<option value='"+year+"-08-01'>Aug "+year+"</option>");
 $(id).append("<option value='"+year+"-09-01'>Sept "+year+"</option>");
 $(id).append("<option value='"+year+"-10-01'>Oct "+year+"</option>");
 $(id).append("<option value='"+year+"-11-01'>Nov "+year+"</option>");
 $(id).append("<option value='"+year+"-12-01'>Dec "+year+"</option>");
 $(id).append("<option value='"+(year+1)+"-01-01'>Jan "+(year+1)+"</option>");
 $(id).append("<option value='"+(year+1)+"-02-01'>Feb "+(year+1)+"</option>");
 $(id).append("<option value='"+(year+1)+"-03-01'>Mar "+(year+1)+"</option>");
           


id.value=dateVal;
 }
 
}

function setRentEndDateValues(id,dateVal){
  //  alert(dateVal);
 var index=id.selectedIndex;
// alert("index"+index);
 if(index<0){

var d=new Date(dateVal);
 
  var month = d.getMonth()+1;
   var year = d.getFullYear();
   //alert("month "+month)
if(month > 3){
             year=year;
         }else{
             year=year-1;
         }
//alert("id=="+id);
$(id).html('');
 $(id).append("<option value='"+year+"-04-30'>Apr "+year+"</option>");
 $(id).append("<option value='"+year+"-05-31'>May "+year+"</option>");
 $(id).append("<option value='"+year+"-06-30'>June "+year+"</option>");
 $(id).append("<option value='"+year+"-07-31'>July "+year+"</option>");
 $(id).append("<option value='"+year+"-08-31'>Aug "+year+"</option>");
 $(id).append("<option value='"+year+"-09-30'>Sept "+year+"</option>");
 $(id).append("<option value='"+year+"-10-31'>Oct "+year+"</option>");
 $(id).append("<option value='"+year+"-11-30'>Nov "+year+"</option>");
 $(id).append("<option value='"+year+"-12-31'>Dec "+year+"</option>");
 $(id).append("<option value='"+(year+1)+"-01-31'>Jan "+(year+1)+"</option>");
   if((year+1)%4==0){
 $(id).append("<option value='"+(year+1)+"-02-29'>Feb "+(year+1)+"</option>");
   }else{
       $(id).append("<option value='"+(year+1)+"-02-28'>Feb "+(year+1)+"</option>"); 
   }
 $(id).append("<option value='"+(year+1)+"-03-31'>Mar "+(year+1)+"</option>");
   

id.value=dateVal;
 }
 
}