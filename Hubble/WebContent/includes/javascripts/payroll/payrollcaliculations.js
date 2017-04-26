/*
 *
 *DESC : Basic on change ..calculation of DA and HRA
 *
 *
 */

function BasicChangeCheck(){
    
    var v_basic =  document.getElementById("basic").value;
    document.getElementById("basic").value=parseFloat(v_basic).toFixed(2)
    // DA = 50% of Basic

    var v_da  = parseFloat(Math.round(parseFloat(v_basic) * 50) / 100).toFixed(2);
    document.getElementById("da").value=v_da;
    // HRA = (Basic+DA)*40%
    var v_hra = parseFloat(Math.round((parseFloat(v_basic)+parseFloat(v_da))*40)/100).toFixed(2);
    document.getElementById("hra").value=v_hra;
}

/*
 *Refresh payroll tab in payroll edit
 *
 *
 **/
function refreshPayroll(){
    var v_basic =  document.getElementById("basic").value;
    var v_da  =  document.getElementById("da").value;
    var v_hra = document.getElementById("hra").value;
    var v_ta =   document.getElementById("ta").value;
    var v_ra= document.getElementById("ra").value;
    var v_entertainment=document.getElementById("entertainment").value;
    var v_kidsEdu=document.getElementById("kidsEducation").value;
    var v_vehicle = document.getElementById("vehicleAllowance").value;
    var v_cca =document.getElementById("cca").value;
    var v_miscpay=document.getElementById("miscPay").value;
    var v_splAllowance = document.getElementById("splAllowance").value;
    var v_health = document.getElementById("health").value;
    var v_life = document.getElementById("life").value;
    var v_employerPf=   document.getElementById("employerPf").value;
    var v_employeePf=   document.getElementById("employeePf").value;
    var v_longTermBonus = document.getElementById("longTermBonus").value;
    //   var v_otherDeductions = document.getElementById("otherDeductions").value;
    var v_onProjectInd = document.getElementById("onProjectInd").checked;
    var v_projectPay = document.getElementById("projectPay").value;
    var v_attendanceAllow = document.getElementById("attendanceAllow").value;
    var v_diffPF = document.getElementById("diffPF").value;
	 var v_isFixedSalary = document.getElementById("isFixedSalary").checked;
	
    // alert("v_totalCost="+v_totalCost + "--------v_variablePay="+v_variablePay+"---------v_basic="+v_basic+"----------v_employerPf="+v_employerPf+"-----v_gross="+v_gross);
    // gross cal
    var temv_v_grossPay = calGrosspay(v_basic,v_da,v_hra,v_ta,v_ra,v_entertainment,v_kidsEdu,v_vehicle,v_cca,v_miscpay,v_splAllowance,v_health,v_life,v_diffPF);
	
    document.getElementById("grossPay").value= temv_v_grossPay.toFixed(2);
    // variable pay call
    var temv_v_variablePay = calVariablepay(v_onProjectInd,v_projectPay,v_attendanceAllow,v_longTermBonus,v_isFixedSalary);
	
    document.getElementById("variablePay").value = temv_v_variablePay;
    
    // pf calls
    calemployeerpf();
    
    
    //alert("after pf cal ");
    // professional tax cal
    Compute_Professional_Tax();
    //alert("after process ");
    
    //esi calculations
    computeESIContributions();
   
    //gross calculation after esi
   
    computeTotalGrossAfterEsiDeduction();
    
    
    // cal for total cost
    compute_netPaid_afterDed();
    //alert("after process ");
    ComputeTotalnetPaidAfterOnProjectDeduction();
        
    computeTotalCost();
    
   
   
 
}

/**
 * DESC : to calculate gross pay.
 *
 *Formula : 
 *Gross Pay = Basic + DA + HRA + TA + RA + Entertainment + Kids Education + Vehicle Allowance + CCA + Misc Pay + Spl. Allowance+Life + Health
 **/
function calGrosspay(v_basic,v_da,v_hra,v_ta,v_ra,v_entertainment,v_kidsEdu,v_vehicle,v_cca,v_miscpay,v_splAllowance,v_health,v_life,v_diffPF){
    //alert("gross pay");
    return parseFloat(v_basic) + parseFloat(v_da) + parseFloat(v_hra) + parseFloat(v_ta) + parseFloat(v_ra) + parseFloat(v_entertainment) + parseFloat(v_kidsEdu) + parseFloat(v_vehicle) + parseFloat(v_cca) + parseFloat(v_miscpay) + parseFloat(v_splAllowance) + parseFloat(v_health) + parseFloat(v_life) ;
}


/**
 *DESC : to calculate variable pay 
 *
 *Formula :
 *Variable Pay = Long term + Project Pay + Attendance allowance
 */

function calVariablepay(v_onProjectInd,v_projectPay,v_attendanceAllow,v_longTermBonus,v_isFixedSalary){
    
    var v_variablePay = 0.00;
    if(v_onProjectInd==true || v_isFixedSalary==true){
        var temv_v_variablePay = parseFloat(v_longTermBonus)+parseFloat(v_projectPay)+parseFloat(v_attendanceAllow);
        v_variablePay = parseFloat(temv_v_variablePay).toFixed(2);
    }

    else{
        v_variablePay = parseFloat(v_attendanceAllow).toFixed(2);
    }
        
    return v_variablePay;
}


/**
 *DESC : to calculate employee and emploer pf's
 *
 *
 **/
function calemployeerpf(){
    var v_grossPay = document.getElementById("grossPay").value;
    var v_hra =    document.getElementById("hra").value;
    var v_pfflag =    document.getElementById("isPfFlag").checked;
    var v_classificationId = document.getElementById("classificationId").value;
    var temv_v_pfgross =parseFloat(v_grossPay) - parseFloat(v_hra);
	  
    var v_Employer_PF;
    var v_Employee_PF;
      
    var temv_v_Employer_PF=0,temv_v_Employee_PF=0;
    // alert(v_classificationId);
    if(v_classificationId != 3){
        if(temv_v_pfgross<15000){
            temv_v_Employer_PF = parseFloat(Math.round(parseFloat(temv_v_pfgross) * 0.1361));
            v_Employer_PF = temv_v_Employer_PF.toFixed(2);
            temv_v_Employee_PF = parseFloat(Math.round(parseFloat(temv_v_pfgross) * 0.12));
            v_Employee_PF = temv_v_Employee_PF.toFixed(2);
        }else{
            if(v_pfflag==true){
                temv_v_Employer_PF = parseFloat(Math.round(parseFloat(15000 * 0.1361)));
                v_Employer_PF = temv_v_Employer_PF.toFixed(2);
                temv_v_Employee_PF = parseFloat(Math.round(parseFloat(15000 * 0.12))); 
                v_Employee_PF = temv_v_Employee_PF.toFixed(2);
            }else{
                v_Employer_PF="0.00"; 
                v_Employee_PF="0.00"; 
            }
		  
        }
    }else{
        v_Employer_PF="0.00"; 
        v_Employee_PF="0.00"; 
    }
    document.getElementById("employerPf").value=parseFloat(v_Employer_PF).toFixed(2);
    document.getElementById("employeePf").value=parseFloat(v_Employee_PF).toFixed(2);
}


/**
 * DESC : Compute_Professional_Tax
 *
 * Update this computation based on 1987 Schedule 1 Section 3 Publication the code to set ptax to Zero for active employees whose pay is
 * less than 5000 is kept in basic after update module
 *
 *
 **/
function Compute_Professional_Tax(){
    var v_grossPay = document.getElementById("grossPay").value;
    var v_variablePay = document.getElementById("variablePay").value;
    var v_professionalTax;
	   
    var temv_v_new_grossPay = parseFloat(v_grossPay) + parseFloat(v_variablePay);
	   
    if(parseFloat(temv_v_new_grossPay)<=15000){
        v_professionalTax = "0.00";
    }else if(parseFloat(temv_v_new_grossPay)>15000 && parseFloat(temv_v_new_grossPay)<=20000){
        v_professionalTax = "150.00";
    }else{
        v_professionalTax = "200.00";
    }
    document.getElementById("professionalTax").value=parseFloat(v_professionalTax).toFixed(2);
    
}


/**
 *  DESC : to calculate net paid after deductions 
 * 
 *
 **/
  
function compute_netPaid_afterDed(){
    //alert("hiii"); 
    var v_employeePf = document.getElementById("employeePf").value;
    var v_longTermBonus = document.getElementById("longTermBonus").value;
    // var v_otherDeductions = document.getElementById("otherDeductions").value;
    var v_professionalTax = document.getElementById("professionalTax").value;
    var v_life = document.getElementById("life").value;
    var v_health = document.getElementById("health").value;
    // var v_employerPf =  document.getElementById("employerPf").value;
	 
    var v_variablePay = document.getElementById("variablePay").value;
    var v_grossPay = document.getElementById("grossPay").value;
    var v_employerPf = document.getElementById("employerPf").value;
	 
    var temv_v_totalCost = parseFloat(v_variablePay) + parseFloat(v_grossPay) + parseFloat(v_employerPf);
    //var temv_v_totalCost = parseFloat(v_variablePay) + parseFloat(v_grossPay) ;	 
    //   var temv_v_totalded = parseFloat(v_employeePf) + parseFloat(v_longTermBonus) + parseFloat(v_otherDeductions) + parseFloat(v_professionalTax) + parseFloat(v_life) + parseFloat(v_health) + parseFloat(v_employerPf);
    var temv_v_totalded = parseFloat(v_employeePf) + parseFloat(v_longTermBonus) + parseFloat(v_professionalTax) + parseFloat(v_life) + parseFloat(v_health) + parseFloat(v_employerPf);
    // alert("total cost-->"+temv_v_totalCost)
    // alert("total deductionss--->"+temv_v_totalded)
    var temv_v_totalCost_after_ded = parseFloat(temv_v_totalCost) - parseFloat(temv_v_totalded);
	 
    document.getElementById("netPaidPayroll").value =  temv_v_totalCost_after_ded.toFixed(2);
		
}

/*
 *To calculate emp savings1 in payroll edit
 *
 *
function addToEmpSavings1(eve){
  
    var tutionfees = document.getElementById("tutionfees").value;
    var ppf = document.getElementById("ppf").value;
    var lifeIns = document.getElementById("lifeIns").value;
    var premium = document.getElementById("premium").value;
    var fd = document.getElementById("fd").value;
    var hbLoanPrinciple = document.getElementById("hbLoanPrinciple").value;
    var mutualFunds = document.getElementById("mutualFunds").value;
    var nsc = document.getElementById("nsc").value;

    var temv_v_final_svaings  = parseFloat(tutionfees) + parseFloat(ppf) + parseFloat(lifeIns) + parseFloat(premium) + parseFloat(fd) + parseFloat(hbLoanPrinciple) + parseFloat(mutualFunds) + parseFloat(nsc);
    document.getElementById("empSaving1").value = temv_v_final_svaings.toFixed(2);
}

 *To calculate emp savings2 in payroll edit
 *
 *
function addToEmpSavings2(eve){
    var hbLoanInterst = document.getElementById("hbLoanInterst").value;
    var medicalIns = document.getElementById("medicalIns").value;
    var hraLifeInsuranceSavings = document.getElementById("hraLifeInsuranceSavings").value;
    var eduInterest = document.getElementById("eduInterest").value;
    
    var temv_v_final_svaings  = parseFloat(hbLoanInterst) + parseFloat(medicalIns)+parseFloat(hraLifeInsuranceSavings) + parseFloat(eduInterest);
    document.getElementById("empSaving2").value  = temv_v_final_svaings.toFixed(2);
}

/*
 *Desc: if days vactaion changes based on the changed value earned values to be calculated in wages edit process
 *
 **/

function changeEarnedValues(eve)
{
    var v_leavesCount = eve;
    var temv_EarnedBasic="0";
    var temv_EarnedDa="0";
    var temv_EarnedHra="0";
    var temv_EarnedTa="0";
    var temv_EarnedRa="0";
    var temv_EarnedLife="0";
    var temv_EarnedHealth="0";
    var temv_EarnedCCa="0";
    var temv_EarnedEntertainment="0";
    var temv_EarnedKidsEdu="0";
    var temv_EarnedVehicleAllowance="0";
    var temv_EarnedLongtermBonus="0";
    var temv_EarnedMiscPay="0";
    var temv_EarnedAttAllowance;
    var temv_EarnedSplAllowance="0";
    var temv_EarnedEmpOthers="0";
    var temv_EarnedProjectPay="0";
    var temv_EarnedGrossPay = "0";
    var temv_EarnedVariablePay = "0";
    var temv_EarnedNetPaid = "0";
    var temv_repaymentGross = "0";
    var temv_repaymentVarriable = "0";
    var temv_repaymentNet = "0";
    var v_daysInMonth=document.getElementById("daysInMonth").value; 
    var v_daysWorked=document.getElementById("daysWorked").value; 
    var v_daysWeekend =document.getElementById("daysWeekends").value;
    var v_daysVacation =document.getElementById("daysVacation").value;
    var v_daysHolidays =document.getElementById("daysHolidays").value;
    var v_daysCount =parseInt(v_daysWorked)+parseInt(v_daysWeekend)+parseInt(v_daysVacation)+parseInt(v_daysHolidays);
  //  alert(v_daysCount);
    var v_basicPay = document.getElementById("basic").value;
    var v_da = document.getElementById("da").value;
    var v_entertainment = document.getElementById("entertainment").value;
    var v_hra = document.getElementById("hra").value;
    var v_kidsEducation = document.getElementById("kidsEducation").value;
    var v_ta = document.getElementById("ta").value;
    var v_vehicleAllowance = document.getElementById("vehicleAllowance").value;
    var v_ra = document.getElementById("ra").value;
    var v_longTermBonus = document.getElementById("longTermBonus").value;
    var v_life = document.getElementById("life").value;
    var v_health = document.getElementById("health").value;
    var v_splAllowance = document.getElementById("splAllowance").value;
    var v_attendanceAllow = document.getElementById("attendanceAllow").value;
    var v_miscPay = document.getElementById("miscPay").value;
    var v_netPaid = document.getElementById("netPaid").value;
    var v_cca = document.getElementById("cca").value;  
    var v_gross = document.getElementById("grossPayPayRollDetails").value;  
    var v_daysInMonths = document.getElementById("daysInMonth").value;  
    var v_projectPay = document.getElementById("projectPay").value;  
    var v_employeePf = document.getElementById("employeePfPayRollDetails").value;  
    var v_employerPf = document.getElementById("employerPf").value;  
    var v_ded_Others = document.getElementById("dedOthers").value;  
    var v_ProfessionalTax = document.getElementById("dedProfessionalTax").value;  
    var v_grossPayActualDetails = document.getElementById("grossPayActualDetails").value; 
    var v_netPaidActualDetails = document.getElementById("netPaidActualDetails").value; 
    var old_variablePay = parseFloat(document.getElementById("earnedLongTermBonus").value)+parseFloat(document.getElementById("earnedProjectPay").value)+parseFloat("0");
    //dw dv dh dw basic = basic/daysinmonth*(count)
    temv_EarnedBasic =     Math.round((parseFloat(v_basicPay)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
    temv_EarnedDa =    Math.round((parseFloat(v_da)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
    temv_EarnedHra =      Math.round((parseFloat(v_hra)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
    temv_EarnedTa =     Math.round((parseFloat(v_ta)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
    temv_EarnedRa =     Math.round((parseFloat(v_ra)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
    temv_EarnedLife =     Math.round((parseFloat(v_life)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
    temv_EarnedHealth =     Math.round((parseFloat(v_health)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
    temv_EarnedCCa =    Math.round((parseFloat(v_cca)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
    temv_EarnedEntertainment =    Math.round((parseFloat(v_entertainment)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
    temv_EarnedKidsEdu =     Math.round((parseFloat(v_kidsEducation)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
    temv_EarnedVehicleAllowance =     Math.round((parseFloat(v_vehicleAllowance)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
    temv_EarnedMiscPay =  Math.round((parseFloat(v_miscPay)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
    temv_EarnedSplAllowance=Math.round((parseFloat(v_splAllowance)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
    temv_EarnedProjectPay =  Math.round((parseFloat(v_projectPay)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
    temv_EarnedLongtermBonus=Math.round((parseFloat(v_longTermBonus)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
    
    //calculation of earned gross pay
    
    temv_EarnedGrossPay = parseFloat(temv_EarnedBasic)+parseFloat(temv_EarnedDa)+parseFloat(temv_EarnedHra)+parseFloat(temv_EarnedTa)+parseFloat(temv_EarnedRa)+parseFloat(temv_EarnedEntertainment)+parseFloat(temv_EarnedCCa)+parseFloat(temv_EarnedMiscPay)+parseFloat(temv_EarnedSplAllowance)+parseFloat(temv_EarnedLife)+parseFloat(temv_EarnedHealth)+parseFloat(temv_EarnedKidsEdu)+parseFloat(temv_EarnedVehicleAllowance);
    //  alert(temv_EarnedGrossPay);
    
    //earned employee esi dedcution from gross..
    
    var v_earnedEmployeresi = document.getElementById("earnedEmployeresi").value;  
    var v_earnedEmployeeesi = document.getElementById("earnedEmployeeesi").value;  
    
    temv_EarnedGrossPay = parseFloat(temv_EarnedGrossPay) - (parseFloat(v_earnedEmployeresi)+parseFloat(v_earnedEmployeeesi));
   
    //calaculation of earned variable pay	
    
    
    if(v_leavesCount>0){
        temv_EarnedAttAllowance="0";
       
    }
    else
    {
        temv_EarnedAttAllowance=document.getElementById("attendanceAllow").value;
       
    }
   
    
    temv_EarnedVariablePay = parseFloat(temv_EarnedLongtermBonus)+parseFloat(temv_EarnedProjectPay)+parseFloat(temv_EarnedAttAllowance);
    
    // calculation of earned net paid
    
    temv_EarnedNetPaid = (parseFloat(temv_EarnedVariablePay)+parseFloat(temv_EarnedGrossPay)+parseFloat(v_employerPf))-(parseFloat(v_employeePf)+parseFloat(temv_EarnedLongtermBonus)+parseFloat(v_ded_Others)+parseFloat(v_ProfessionalTax)+parseFloat(temv_EarnedLife)+parseFloat(temv_EarnedHealth)+parseFloat(v_employerPf));
    
    //calculation of repayment gross
    // alert(v_gross +"------"+ v_grossPayActualDetails)
    temv_repaymentGross = parseFloat(temv_EarnedGrossPay) -  parseFloat(v_grossPayActualDetails); 
    
    //calculation of repayment varriable pay
    temv_repaymentVarriable = (parseFloat(temv_EarnedVariablePay)) - parseFloat(old_variablePay);
    
    //calculation of repayment net paid
    
    temv_repaymentNet = parseFloat(temv_EarnedNetPaid) - parseFloat(v_netPaidActualDetails);
        
    var result = confirm("Based on the vacations entered earned values will be changed.. do you want to continue ?");
        
    if(result)
    {
        document.getElementById("earnedBasic").value = temv_EarnedBasic.toFixed(2);
        document.getElementById("earnedDa").value = temv_EarnedDa.toFixed(2);
        document.getElementById("earnedHra").value = temv_EarnedHra.toFixed(2);
        document.getElementById("earnedTa").value = temv_EarnedTa.toFixed(2);
        document.getElementById("earnedRa").value = temv_EarnedRa.toFixed(2);
        document.getElementById("earnedLife").value = temv_EarnedLife.toFixed(2);
        document.getElementById("earnedHealth").value = temv_EarnedHealth.toFixed(2);
        document.getElementById("earnedCCa").value = temv_EarnedCCa.toFixed(2);
        document.getElementById("earnedEntertainment").value = temv_EarnedEntertainment.toFixed(2);
        document.getElementById("earnedKidsEducation").value = temv_EarnedKidsEdu.toFixed(2);
        document.getElementById("earnedVehicleAllowance").value = temv_EarnedVehicleAllowance.toFixed(2);
        document.getElementById("earnedMiscPay").value = temv_EarnedMiscPay.toFixed(2);
        document.getElementById("earnedLongTermBonus").value = temv_EarnedLongtermBonus.toFixed(2);
        document.getElementById("earnedProjectPay").value = temv_EarnedProjectPay.toFixed(2);
        document.getElementById("earnedsplallowance").value = temv_EarnedSplAllowance.toFixed(2);
        document.getElementById("grossPayActualDetails").value = temv_EarnedGrossPay.toFixed(2);
        document.getElementById("netPaidActualDetails").value = temv_EarnedNetPaid.toFixed(2);
        // document.getElementById("earnedsplallowance").value = temv_EarnedSplAllowance.toFixed(2);
        document.getElementById("earnedattallowance").value = parseFloat(temv_EarnedAttAllowance).toFixed(2);
         
        //doRepaymentFlag
        if(document.getElementById("doRepaymentFlag").checked){
            /* var repaymentGross = parseFloat(temv_EarnedGrossPay)-parseFloat(v_grossPayActualDetails);
            var repaymentNet = parseFloat(temv_EarnedNetPaid)-parseFloat(v_netPaidActualDetails);
            var new_variablePay = temv_EarnedVariablePay ;
            var repaymentVariablePay = parseFloat(new_variablePay)-parseFloat(old_variablePay);
             */
            document.getElementById("repaymentGross").value =temv_repaymentGross.toFixed(2);
            document.getElementById("repaymentNet").value =temv_repaymentNet.toFixed(2);
            document.getElementById("repaymentVariablePay").value =temv_repaymentVarriable.toFixed(2);
        }
        
        //daysWorked1();
        return true;
        
    }else
    {
        document.getElementById('daysVacation').value =  document.getElementById('daysVacationHidden').value;
        document.getElementById('daysWorked').value =  document.getElementById('daysWorkedHidden').value;
        document.getElementById('vactionsAvailable').value =document.getElementById('vactionsAvailableHidden').value;
        setTimeout(function(){
            document.employeeWageDetails.daysVacation.focus()
        }, 10);
        return false;
    }
           
           
}

/*
 *
 *Function for TDS calculations in both payroll and wages edit
 *
 *

function tdsCalculation(){
    
    var v_lifeInsurancePremium  =  document.getElementById("lifeInsurancePremium").value;
    var v_housingLoanRepay =  document.getElementById("housingLoanRepay").value;
    var v_nsc =  document.getElementById("nscTds").value;
    var v_ppfContribution =  document.getElementById("ppfContribution").value;
    var v_tutionFee =  document.getElementById("tutionFee").value;
    var v_elss =  document.getElementById("elss").value;
    var v_postOfficeTerm =  document.getElementById("postOfficeTerm").value;
    var v_bankDepositTax =  document.getElementById("bankDepositTax").value;
    var v_othersTDS =  document.getElementById("othersTDS").value;
    var v_contributionToPf =  document.getElementById("contributionToPf").value;
    var v_npsEmployeeContr =  document.getElementById("npsEmployeeContr").value;
    var v_licFromSal = document.getElementById("licFromSal").value;
    var v_temp_totalTds = parseFloat(v_lifeInsurancePremium) + parseFloat(v_housingLoanRepay)+ parseFloat(v_nsc)+parseFloat(v_ppfContribution)+parseFloat(v_tutionFee)+parseFloat(v_elss)+parseFloat(v_postOfficeTerm)+parseFloat(v_bankDepositTax)+parseFloat(v_othersTDS)+parseFloat(v_contributionToPf)+parseFloat(v_npsEmployeeContr)+parseFloat(v_licFromSal);

    document.getElementById("totalTds").value = parseFloat(v_temp_totalTds).toFixed(2);
    if(v_temp_totalTds>150000){
        document.getElementById("totalTdsDeductable").value="150000.00";
    }
    else{
    
        document.getElementById("totalTdsDeductable").value=parseFloat(v_temp_totalTds).toFixed(2);
    }
    var v_interstOnBorrowed =  document.getElementById("interstOnBorrowed").value;
    if(v_interstOnBorrowed > 200000){
        document.getElementById("interstOnBorrowedDeductable").value = "200000.00";
    }
    else{
        document.getElementById("interstOnBorrowedDeductable").value = parseFloat(v_interstOnBorrowed).toFixed(2);
    }

    var v_insuranceForParents =  document.getElementById("insuranceForParents").value;
    if(v_insuranceForParents > 20000){
        document.getElementById("insuranceForParentsDeduc").value = "20000.00";
    }
    else{
        document.getElementById("insuranceForParentsDeduc").value = parseFloat(v_insuranceForParents).toFixed(2);
    }

    var v_insuranceOthers =  document.getElementById("insuranceOthers").value;
    if(v_insuranceOthers > 15000){
        document.getElementById("insuranceOthersDeduc").value = "15000.00";
    }
    else{
        document.getElementById("insuranceOthersDeduc").value = parseFloat(v_insuranceOthers).toFixed(2);
    }



    
}*/
/*
 *Desc :Function to calculate net paid after on project deduction
 **/
function ComputeTotalnetPaidAfterOnProjectDeduction(){
    
    var onProjectIndValue1  =  document.getElementById("onProjectIndValue1").value;
    var onProjectIndValue2 =  document.getElementById("onProjectIndValue2").value;
    var netPaidPayroll =document.getElementById("netPaidPayroll").value;
    // alert("in-->"+onProjectIndValue1);
    var v_onProjectInd = document.getElementById("onProjectInd").checked;
    if(v_onProjectInd==true){
        //   alert("in if-->"+onProjectIndValue1);
        netPaidPayroll =parseFloat(netPaidPayroll) - (parseFloat(onProjectIndValue1)+parseFloat(onProjectIndValue2));
    }

    document.getElementById("netPaidPayroll").value = parseFloat(netPaidPayroll).toFixed(2);
}

/*
 *function to calculate total cost for the payroll of a particular employee
 *
 **/

function computeTotalCost(){
    
    //Totalcost=grosspay+variablepay+EmployeerPf
    var v_variablePay = document.getElementById("variablePay").value;
    var v_grossPay = document.getElementById("grossPay").value;
    var v_employerPf = document.getElementById("employerPf").value;
    var v_diffPF = document.getElementById("diffPF").value;
    var v_temp_totalcost = parseFloat(v_grossPay) +parseFloat(v_variablePay) +parseFloat(v_employerPf) -  parseFloat(v_diffPF);
    // document.getElementById("totalCost").value = parseFloat(v_temp_totalcost).toFixed(2);
    //   var v_enteredTotalCost =document.getElementById("totalCost").value;
    /*if(v_temp_totalcost == v_enteredTotalCost){
       document.getElementById("totalCostMatchDv").innerHTML = "<font style='color:#1C7120'>calculated cost is"+parseFloat(v_temp_totalcost).toFixed(2)+"</font>";
   }
    else
        {
             document.getElementById("totalCostMatchDv").innerHTML = "<font style='color:red'>calculated cost is"+parseFloat(v_temp_totalcost).toFixed(2)+"</font>";
        }*/
    //total cost calculation after deductions
   
    ComputeTotalCostAfterOnProjectDeduction(v_temp_totalcost);
}

/*
 *Calculation of projectPay for project End Date
 *
 *
 **/

function computeProjectPay(){
    //   alert("in projectpay");
    /* IF v_ProjectEndDate!='1951-01-01' THEN
	SET v_ProjectPay = ROUND((v_ProjectPay*DAY(v_ProjectEndDate))/frmNoOfDays); 
	END IF;*/
    var v_projectEndDate = document.getElementById("projectEndDate").value;
    var v_projectPayEarned =  document.getElementById("earnedProjectPay").value;
    var v_projectPay = document.getElementById("projectPay").value;  
    var v_NoOfDays =  document.getElementById("daysInMonth").value;
    var v_daysVacation = document.getElementById("daysVacation").value;
    var temv_EarnedProjectPay="";
    if(v_projectEndDate.length == 0){
        // alert(v_projectPay+"=====");
        temv_EarnedProjectPay = parseFloat(v_projectPay) - ((parseFloat(v_projectPay)/parseFloat(v_NoOfDays))*parseFloat(v_daysVacation));
        //  alert(temv_EarnedProjectPay+"=====");
        document.getElementById("earnedProjectPay").value = parseFloat(temv_EarnedProjectPay).toFixed(2);
    }
    else{
  
        var dArray = v_projectEndDate.split("/");  
        //  alert(" "+dArray[0]+"----"+dArray[1]+"========="+dArray[2]);
        var myDate=new Date(dArray[2],dArray[0]-1,dArray[1]);  
    
      
        var v_tempProjectPay = ((parseFloat(v_projectPayEarned)*parseFloat(myDate.getDate()))/parseFloat(v_NoOfDays));
        document.getElementById("earnedProjectPay").value =parseFloat(v_tempProjectPay).toFixed(2);
    }
}

/*
 *
 *Calculation for Days Worked while days vactaion changed
 *
 **/

function daysWorked1(daysVactaion){
    
    var v_NoOfDaysInMonth =  document.getElementById("daysInMonth").value;
    var v_daysVacation =  document.getElementById("daysVacation").value;
    var v_daysVactaionHidden = document.getElementById("daysVacationHidden").value;
    var v_daysHolidays =  document.getElementById("daysHolidays").value;
    var v_daysWeekends =  document.getElementById("daysWeekends").value;
    var temp_DaysVacations = parseInt(v_daysVactaionHidden)-parseInt(daysVactaion);
    // var v_daysworked = parseInt(v_NoOfDaysInMonth)-(parseInt(v_daysVacation)+parseInt(v_daysHolidays)+parseInt(v_daysWeekends));   
    var v_daysworked = parseInt(document.getElementById("daysWorked").value) + parseInt(temp_DaysVacations);
    //if()    
    // alert(v_daysworked);
    document.getElementById("daysWorked").value= v_daysworked;
    changeEarnedValues(v_daysVacation);
}
////function to calculate yearly gross value for payroll
//function computeExpectedYearlyValues(){
//    var v_ExpectedYearlyGross ;
//    var v_grossPay = document.getElementById("grossPay").value;
//    var v_Basic = document.getElementById("basic").value;
//    var v_DA = document.getElementById("da").value;
//    v_ExpectedYearlyGross = parseFloat(v_grossPay)*12;
//    document.getElementById("expectedYearlyCost").value = parseFloat(v_ExpectedYearlyGross).toFixed(2);
//    var v_HrExemptions = document.getElementById("interstOnHrAssumptionsInv").value;
//    //YHR= Houserent reciptampunt-10%0f(Basic+DA) 
//    var v_HrExemptionsDeductable = parseFloat(v_HrExemptions) -Math.round(((parseFloat(v_Basic))+(parseFloat(v_DA)))*0.1) ;
//    // document.getElementById("interstOnHrAssumptions").value = parseFloat(v_HrExemptionsDeductable).toFixed(2);
//    var v_expectedYearlyTA = document.getElementById("ta").value;
//    var v_expectedYearlyProfessionalTax = document.getElementById("professionalTax").value;
//    var v_tempYearlyPay =  (parseFloat(v_grossPay)) - (parseFloat(v_expectedYearlyTA)) - (parseFloat(v_expectedYearlyProfessionalTax)) ;
//    alert(v_tempYearlyPay);
//}

////function to calculate yearly gross value for wages
//function computeExpectedYearlyValues1(){
//    var v_ExpectedYearlyGross ;
//    var v_grossPay = document.getElementById("grossPay").value;
//    var v_Basic = document.getElementById("basic").value;
//    var v_DA = document.getElementById("da").value;
//    v_ExpectedYearlyGross = parseFloat(v_grossPay)*12;
//    alert(v_ExpectedYearlyGross);
//    document.getElementById("expectedYearlyCost").value = parseFloat(v_ExpectedYearlyGross).toFixed(2);
//    var v_HrExemptions = document.getElementById("interstOnHrAssumptionsInv").value;
//    //YHR= Houserent reciptampunt-10%0f(Basic+DA) 
//    var v_HrExemptionsDeductable = parseFloat(v_HrExemptions) -Math.round(((parseFloat(v_Basic))+(parseFloat(v_DA)))*0.1) ;
//    // document.getElementById("interstOnHrAssumptions").value = parseFloat(v_HrExemptionsDeductable).toFixed(2);
//    var v_expectedYearlyTA = document.getElementById("ta").value;
//    var v_expectedYearlyProfessionalTax = document.getElementById("dedProfessionalTax").value;
//    var v_tempYearlyPay =  (parseFloat(v_grossPay)) - (parseFloat(v_expectedYearlyTA)) - (parseFloat(v_expectedYearlyProfessionalTax)) ;
//    alert(v_tempYearlyPay);
//}

/**
 * Desc: calculation of  esi contributions based on gross pay
 */
function computeESIContributions(){
    var v_grossPay = document.getElementById("grossPay").value;
    var v_esiFlag = document.getElementById("esiFlag").checked;
    var v_EmployerShare = "0.00";
    var v_EmployeeShare = "0.00";
    if(v_esiFlag)
    {
        v_EmployerShare = parseFloat(v_grossPay)*0.0475;
        v_EmployeeShare = parseFloat(v_grossPay)*0.0175;
    }
    
    document.getElementById("employeresi").value = parseFloat(v_EmployerShare).toFixed(2);
    document.getElementById("employeeesi").value = parseFloat(v_EmployeeShare).toFixed(2);
}

//compute total cost after esi deductions

function computeTotalGrossAfterEsiDeduction(){
    var v_grossPay = document.getElementById("grossPay").value;
    var v_employerESI = document.getElementById("employeresi").value;
    var v_employeeESI = document.getElementById("employeeesi").value;
    // alert(v_employerESI+'-'+v_employeeESI);
    var v_FinalGrossAfterDeduction = parseFloat(v_grossPay) - (parseFloat(v_employerESI)+parseFloat(v_employeeESI));
    // alert(v_FinalGrossAfterDeduction);
    document.getElementById("grossPay").value = parseFloat(v_FinalGrossAfterDeduction).toFixed(2);
}


////calculation for HR deduction
//
//function computeHrExemtionsDed(){
//    var v_HrExemptions = document.getElementById("interstOnHrAssumptionsInv").value;
//    if(v_HrExemptions==0){
//        document.getElementById("interstOnHrAssumptions").value = parseFloat(v_HrExemptions).toFixed(2);
//    }
//    else{
//        var v_Basic = document.getElementById("basic").value;
//        var v_DA = document.getElementById("da").value;
//        var v_HrExemptionsDeductable = parseFloat(v_HrExemptions) -Math.round(((parseFloat(v_Basic))+(parseFloat(v_DA)))*0.1) ;
//        document.getElementById("interstOnHrAssumptions").value = parseFloat(v_HrExemptionsDeductable).toFixed(2);
//    }
//}


/*
 *Function to calculate total cost after on project deduction
 **/
function ComputeTotalCostAfterOnProjectDeduction(v_temp_totalcost){
    
    var onProjectIndValue1  =  document.getElementById("onProjectIndValue1").value;
    var onProjectIndValue2 =  document.getElementById("onProjectIndValue2").value;
    var v_enteredTotalCost =document.getElementById("totalCostCalc").value;
    var totalCost=parseFloat(v_temp_totalcost);
    //alert(totalCost);
    var v_onProjectInd = document.getElementById("onProjectInd").checked;
    if(v_onProjectInd==true){
        //   alert("in if-->"+onProjectIndValue1);
        totalCost =parseFloat(v_temp_totalcost) - (parseFloat(onProjectIndValue1)+parseFloat(onProjectIndValue2));
    }
    document.getElementById("totalCost").value=parseFloat(totalCost).toFixed(2);
//alert(parseFloat(totalCost).toFixed(2));
/*  if(parseFloat(totalCost).toFixed(2) != v_enteredTotalCost){
        //  document.getElementById("totalCostMatchDv").innerHTML = "<font style='color:#1C7120'>calculated cost is"+parseFloat(totalCost).toFixed(2)+"</font>";
        alert("Total cost is not matched with calculated cost");
        document.getElementById("update00").disabled=true;
        document.getElementById("update11").disabled=true;
        document.getElementById("update22").disabled=true;
       document.getElementById("update33").disabled=true;
        document.getElementById("save00").disabled=true;
        document.getElementById("save11").disabled=true;
        document.getElementById("save22").disabled=true;
        document.getElementById("save33").disabled=true;
        
     
    }
    else
    {
        document.getElementById("save00").disabled=false;
        document.getElementById("save11").disabled=false;
        document.getElementById("save22").disabled=false;
        document.getElementById("save33").disabled=false;
        document.getElementById("update00").disabled=false;
        document.getElementById("update11").disabled=false;
        document.getElementById("update22").disabled=false;
        document.getElementById("update33").disabled=false;
    }
 else
        {
             document.getElementById("totalCostMatchDv").innerHTML = "<font style='color:red'>calculated cost is"+parseFloat(totalCost).toFixed(2)+"</font>";
        }*/
}


/*
 *Payroll Calculator
 *Desc: Calculator used to calculate all the payroll values and then entering these values in Original Payroll Details Tex Boxes
 **/


function valuesToBeCalcForCompare(){
    var v_Employer_PF="1665.00";
    // var v_Employee_PF="885.00";
     var v_TA="1600.00";
    document.getElementById("employerPfCalc").value=parseFloat(v_Employer_PF).toFixed(2);
    // document.getElementById("employeePfCalc").value=parseFloat(v_Employee_PF).toFixed(2);
    document.getElementById("pfTaxCalc").value="200.00";
    document.getElementById("taCalc").value=parseFloat(v_TA).toFixed(2);
    var totalCostCalc  =  document.getElementById("totalCostCalc").value;
    var longBonusCalc =  document.getElementById("longBonusCalc").value;
    var employerPfCalc =document.getElementById("employerPfCalc").value;
    var attAllowCalc  =  document.getElementById("attAllowCalc").value;
    var healthCalc =  document.getElementById("healthCalc").value;
    var pfTaxCalc =document.getElementById("pfTaxCalc").value;
    var v_temp_Cost = parseFloat(totalCostCalc)-parseFloat(longBonusCalc)-parseFloat(employerPfCalc)-parseFloat(attAllowCalc)-parseFloat(healthCalc)-parseFloat(pfTaxCalc);
    document.getElementById("monthlySalary").value = parseFloat(v_temp_Cost).toFixed(2);
   
    var v_Basic = Math.ceil(parseFloat(parseFloat(v_temp_Cost)*(22/100)));
    var v_DA = Math.ceil(parseFloat(parseFloat(v_Basic)*(50/100)));
    var v_HRA = Math.ceil(parseFloat((parseFloat(v_Basic)+parseFloat(v_DA))*(40/100)));
    var v_ProjectAllowance = Math.ceil(parseFloat(parseFloat(v_temp_Cost)*(40/100)));
    var v_cca = Math.round(parseFloat(parseFloat(v_temp_Cost)*(1/100)));
    var v_Entertainment = Math.round(parseFloat(parseFloat(v_temp_Cost)*(3/100)));
    var v_VehicleAllowance = Math.round(parseFloat(parseFloat(v_temp_Cost)*(3/100)));
    var v_MiscPay = Math.ceil(parseFloat(parseFloat(v_temp_Cost)*(2/100)));
    var v_TDS = Math.ceil(parseFloat(parseFloat(v_temp_Cost)*(20/100)));
    

    document.getElementById("basicCalc").value = parseFloat(v_Basic).toFixed(2);
    document.getElementById("daCalac").value = parseFloat(v_DA).toFixed(2);
    document.getElementById("hraCalc").value = parseFloat(v_HRA).toFixed(2);
    document.getElementById("prjPayCalc").value = parseFloat(v_ProjectAllowance).toFixed(2);
    document.getElementById("ccaCalc").value = parseFloat(v_cca).toFixed(2);
    document.getElementById("entertainmentCalc").value = parseFloat(v_Entertainment).toFixed(2);
    document.getElementById("vehAllCal").value = parseFloat(v_VehicleAllowance).toFixed(2);
    document.getElementById("miscCal").value = parseFloat(v_MiscPay).toFixed(2);
    //  document.getElementById("tdsCalc").value = parseFloat(v_TDS).toFixed(2);
    //    document.getElementById("healthDedCalc").value = parseFloat(healthCalc).toFixed(2);
    //  document.getElementById("proftaxCalc").value = parseFloat(pfTaxCalc).toFixed(2);
    
    
    var v_grossPay = parseFloat(totalCostCalc)-parseFloat(longBonusCalc)-parseFloat(attAllowCalc) - parseFloat(v_ProjectAllowance);
/*    var v_pfflag =    document.getElementById("isPfFlag").checked;
   var v_classificationId = document.getElementById("classificationId").value;
    var temv_v_pfgross =parseFloat(v_grossPay) - parseFloat(v_HRA);
	  
    var v_Employer_PF;
    var v_Employee_PF;
      
    var temv_v_Employer_PF=0,temv_v_Employee_PF=0;
    // alert(v_classificationId);
    if(v_classificationId != 3){
        if(temv_v_pfgross<15000){
            temv_v_Employer_PF = parseFloat(Math.round(parseFloat(temv_v_pfgross) * 0.1361));
            v_Employer_PF = temv_v_Employer_PF.toFixed(2);
            temv_v_Employee_PF = parseFloat(Math.round(parseFloat(temv_v_pfgross) * 0.12));
            v_Employee_PF = temv_v_Employee_PF.toFixed(2);
        }else{
            if(v_pfflag==true){
                temv_v_Employer_PF = parseFloat(Math.round(parseFloat(15000 * 0.1361)));
                v_Employer_PF = temv_v_Employer_PF.toFixed(2);
                temv_v_Employee_PF = parseFloat(Math.round(parseFloat(15000 * 0.12))); 
                v_Employee_PF = temv_v_Employee_PF.toFixed(2);
            }else{
                v_Employer_PF="0.00"; 
                v_Employee_PF="0.00"; 
            }
		  
        }
    }else{
        v_Employer_PF="0.00"; 
        v_Employee_PF="0.00"; 
    }
    document.getElementById("employerPfCalc").value=parseFloat(v_Employer_PF).toFixed(2);
    document.getElementById("employeePfCalc").value=parseFloat(v_Employee_PF).toFixed(2);
    
    */
}

//function to calculate vacations available as well as to check the days entered when days vactaion changes
function checkTotalDays(eve){
    
    var daysVactaion = eve.value;
    //var daysVactaion = document.getElementById('daysVacation').value;
    var daysInMonth = document.getElementById('daysInMonth').value;
    var daysWorked = document.getElementById('daysWorked').value;
    var daysHolidays = document.getElementById('daysHolidays').value;
    var daysWeekends = document.getElementById('daysWeekends').value;
    var vactaionsAvailable =document.getElementById('vactionsAvailable').value;
    var vacationsAvailableOrginal = document.getElementById("vactionsAvailableHidden").value;
    var temp_Count = parseInt(daysWorked)+parseInt(daysHolidays)+parseInt(daysWeekends)+parseInt(daysVactaion);
    if(temp_Count>daysInMonth){
      //  alert("No of days exceeding the total days in the month");
        x0p( '','No of days exceeding the total days in the month','info');
        document.getElementById('daysVacation').value =  document.getElementById('daysVacationHidden').value;
        document.getElementById('daysWorked').value =  document.getElementById('daysWorkedHidden').value;
        document.getElementById('vactionsAvailable').value =document.getElementById('vactionsAvailableHidden').value;
        setTimeout(function(){
            document.employeeWageDetails.daysVacation.focus()
        }, 10);
        return false;
    }
    else if(daysVactaion < vactaionsAvailable){
      //  alert("Vacation days should not be less than vacations available");
        x0p( '','Vacation days should not be less than vacations available','info');
        document.getElementById('daysVacation').value =  document.getElementById('daysVacationHidden').value;
        document.getElementById('daysWorked').value =  document.getElementById('daysWorkedHidden').value;
        document.getElementById('vactionsAvailable').value =document.getElementById('vactionsAvailableHidden').value;
        setTimeout(function(){
            document.employeeWageDetails.daysVacation.focus()
        }, 10);
        return false;
    }
    else
    {
        
        var daysVacationOriginal =  document.getElementById('daysVacationHidden').value;
        var daysVacationChanged =  document.getElementById('daysVacation').value;
        var temp_DiffOfDays = parseInt(daysVacationOriginal)-parseInt(daysVacationChanged) ;
        document.getElementById('vactionsAvailable').value =parseInt(vacationsAvailableOrginal) + parseInt(temp_DiffOfDays);
        // daysWorked1(daysVactaion);
        changeEarnedValues(daysVactaion);

    }
}


/*
 * Function to change earned values when days worked changes
 **/

function changeEarnedValuesBasedOnDaysWorked(eve){
    var daysWorked =eve.value;
    var temv_EarnedBasic="0";
    var temv_EarnedDa="0";
    var temv_EarnedHra="0";
    var temv_EarnedTa="0";
    var temv_EarnedRa="0";
    var temv_EarnedLife="0";
    var temv_EarnedHealth="0";
    var temv_EarnedCCa="0";
    var temv_EarnedEntertainment="0";
    var temv_EarnedKidsEdu="0";
    var temv_EarnedVehicleAllowance="0";
    var temv_EarnedLongtermBonus="0";
    var temv_EarnedMiscPay="0";
    var temv_EarnedAttAllowance;
    var temv_EarnedSplAllowance="0";
    var temv_EarnedEmpOthers="0";
    var temv_EarnedProjectPay="0";
    var temv_EarnedGrossPay = "0";
    var temv_EarnedVariablePay = "0";
    var temv_EarnedNetPaid = "0";
    var temv_repaymentGross = "0";
    var temv_repaymentVarriable = "0";
    var temv_repaymentNet = "0";
    var daysVactaion = document.getElementById('daysVacation').value;
    var daysProject = document.getElementById('daysProject').value;
    var daysProjectHiddenValue = document.getElementById('daysProjectHiddenValue').value;
    var daysInMonth = document.getElementById('daysInMonth').value;
    var daysHolidays = document.getElementById('daysHolidays').value;
    var daysWeekends = document.getElementById('daysWeekends').value;
    var daysWorkedHidden = document.getElementById('daysWorkedHidden').value;
    var daysVacationHidden = document.getElementById('daysVacationHidden').value;
    var tdsDeduction = document.getElementById('tdsDeduction').value;
    var days_count_afterDaysWorkedChanged = parseInt(daysWorked) + parseInt(daysHolidays) + parseInt(daysWeekends) + parseInt(daysVactaion) ;
    var days_count_beforeDaysWorkedChanged = parseInt(daysWorkedHidden) + parseInt(daysHolidays) + parseInt(daysWeekends) + parseInt(daysVacationHidden) ;
    var result;
    //  if(days_count_afterDaysWorkedChanged!=days_count_beforeDaysWorkedChanged){
    result  = confirm("Days worked in this month is changed.. do you want to continue?");
    // }

    if(result)
    {
        var v_daysCount = days_count_afterDaysWorkedChanged;
        if(parseInt(daysProject)>0){
            document.getElementById('daysProject').value =parseInt(daysProjectHiddenValue) + (parseInt(days_count_afterDaysWorkedChanged) - parseInt(days_count_beforeDaysWorkedChanged));
        }
        var v_basicPay = document.getElementById("basic").value;
        var v_da = document.getElementById("da").value;
        var v_entertainment = document.getElementById("entertainment").value;
        var v_hra = document.getElementById("hra").value;
        var v_kidsEducation = document.getElementById("kidsEducation").value;
        var v_ta = document.getElementById("ta").value;
        var v_vehicleAllowance = document.getElementById("vehicleAllowance").value;
        var v_ra = document.getElementById("ra").value;
        var v_longTermBonus = document.getElementById("longTermBonus").value;
        var v_life = document.getElementById("life").value;
        var v_health = document.getElementById("health").value;
        var v_splAllowance = document.getElementById("splAllowance").value;
        var v_attendanceAllow = document.getElementById("attendanceAllow").value;
        var v_miscPay = document.getElementById("miscPay").value;
        var v_netPaid = document.getElementById("netPaid").value;
        var v_cca = document.getElementById("cca").value;  
        var v_gross = document.getElementById("grossPayPayRollDetails").value;  
        var v_daysInMonths = document.getElementById("daysInMonth").value;  
        var v_projectPay = document.getElementById("projectPay").value;  
        var v_employeePf = document.getElementById("employeePfPayRollDetails").value;  
        var v_employerPf = document.getElementById("employerPf").value;  
        var v_ded_Others = document.getElementById("dedOthers").value;  
        var v_ProfessionalTax = document.getElementById("dedProfessionalTax").value;  
        var v_grossPayActualDetails = document.getElementById("grossPayActualDetails").value; 
        var v_netPaidActualDetails = document.getElementById("netPaidActualDetails").value; 
        var old_variablePay = parseFloat(document.getElementById("earnedLongTermBonus").value)+parseFloat(document.getElementById("earnedProjectPay").value)+parseFloat("0");
        temv_EarnedBasic =     Math.round((parseFloat(v_basicPay)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
        temv_EarnedDa =    Math.round((parseFloat(v_da)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
        temv_EarnedHra =      Math.round((parseFloat(v_hra)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
        temv_EarnedTa =     Math.round((parseFloat(v_ta)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
        temv_EarnedRa =     Math.round((parseFloat(v_ra)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
        // temv_EarnedLife =     Math.round((parseFloat(v_life)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
        //  temv_EarnedHealth =     Math.round((parseFloat(v_health)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
        temv_EarnedLife = v_life;
        temv_EarnedHealth = v_health;
        temv_EarnedCCa =    Math.round((parseFloat(v_cca)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
        temv_EarnedEntertainment =    Math.round((parseFloat(v_entertainment)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
        temv_EarnedKidsEdu =     Math.round((parseFloat(v_kidsEducation)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
        temv_EarnedVehicleAllowance =     Math.round((parseFloat(v_vehicleAllowance)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
        temv_EarnedMiscPay =  Math.round((parseFloat(v_miscPay)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
        temv_EarnedSplAllowance=Math.round((parseFloat(v_splAllowance)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
        temv_EarnedProjectPay =  Math.round((parseFloat(v_projectPay)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
        temv_EarnedLongtermBonus=Math.round((parseFloat(v_longTermBonus)/parseFloat(v_daysInMonths))*parseFloat(v_daysCount));
    
        //calculation of earned gross pay
        //SET v_Earned_GrossPay = (v_Earned_Basic + v_Earned_DA + v_Earned_HRA + v_Earned_TA + v_Earned_RA + v_Earned_Entertainment + v_Earned_KidsEducation + v_Earned_VehicleAllowance + v_Earned_CCA + v_Earned_MiscPay + v_Earned_SplAllowance + v_Earned_Health + v_Earned_Life);
        temv_EarnedGrossPay = parseFloat(temv_EarnedBasic)+parseFloat(temv_EarnedDa)+parseFloat(temv_EarnedHra)+parseFloat(temv_EarnedTa)+parseFloat(temv_EarnedRa)+parseFloat(temv_EarnedEntertainment)+parseFloat(temv_EarnedCCa)+parseFloat(temv_EarnedMiscPay)+parseFloat(temv_EarnedSplAllowance)+parseFloat(temv_EarnedLife)+parseFloat(temv_EarnedHealth)+parseFloat(temv_EarnedKidsEdu)+parseFloat(temv_EarnedVehicleAllowance);
        //  alert(temv_EarnedGrossPay);
    
        //earned employee esi dedcution from gross..
    
        var v_earnedEmployeresi = document.getElementById("earnedEmployeresi").value;  
        var v_earnedEmployeeesi = document.getElementById("earnedEmployeeesi").value;  
    
        temv_EarnedGrossPay = parseFloat(temv_EarnedGrossPay) - (parseFloat(v_earnedEmployeresi)+parseFloat(v_earnedEmployeeesi));
   
        //calaculation of earned variable pay	

        temv_EarnedAttAllowance=document.getElementById("attendanceAllow").value;
       
        
   
    
        temv_EarnedVariablePay = parseFloat(temv_EarnedLongtermBonus)+parseFloat(temv_EarnedProjectPay)+parseFloat(temv_EarnedAttAllowance);
    
        // calculation of earned net paid
    
        temv_EarnedNetPaid = (parseFloat(temv_EarnedVariablePay)+parseFloat(temv_EarnedGrossPay)+parseFloat(v_employerPf))-(parseFloat(v_employeePf)+parseFloat(temv_EarnedLongtermBonus)+parseFloat(v_ded_Others)+parseFloat(v_ProfessionalTax)+parseFloat(temv_EarnedLife)+parseFloat(temv_EarnedHealth)+parseFloat(v_employerPf)) -(parseFloat(tdsDeduction));
    
        //calculation of repayment gross
        temv_repaymentGross = parseFloat(temv_EarnedGrossPay) -  parseFloat(v_grossPayActualDetails); 
    
        //calculation of repayment varriable pay
        temv_repaymentVarriable = (parseFloat(temv_EarnedVariablePay)) - parseFloat(old_variablePay);
    
        //calculation of repayment net paid
    
        temv_repaymentNet = parseFloat(temv_EarnedNetPaid) - parseFloat(v_netPaidActualDetails);
        
        var  temv_v_pfgross = parseFloat(temv_EarnedGrossPay) -  parseFloat(temv_EarnedHra);
        var v_earned_employeePf=0;
        var v_earned_employerPf=0;
        if (temv_v_pfgross <15000){
            v_earned_employerPf=parseFloat(Math.round(parseFloat(temv_v_pfgross) * 0.1361));
            v_earned_employeePf=parseFloat(Math.round(parseFloat(temv_v_pfgross) * 0.12));
        }
        else{
            v_earned_employerPf=parseFloat(Math.round(parseFloat(15000 * 0.1361)));
            v_earned_employeePf=parseFloat(Math.round(parseFloat(15000 * 0.12))); 
        }
        var result = confirm("Based on the days worked entered earned values will be changed.. do you want to continue ?");
        
        if(result)
        {
            document.getElementById("earnedBasic").value = temv_EarnedBasic.toFixed(2);
            document.getElementById("earnedDa").value = temv_EarnedDa.toFixed(2);
            document.getElementById("earnedHra").value = temv_EarnedHra.toFixed(2);
            document.getElementById("earnedTa").value = temv_EarnedTa.toFixed(2);
            document.getElementById("earnedRa").value = temv_EarnedRa.toFixed(2);
            // document.getElementById("earnedLife").value = temv_EarnedLife.toFixed(2);
            //  document.getElementById("earnedHealth").value = temv_EarnedHealth.toFixed(2);
            document.getElementById("earnedCCa").value = temv_EarnedCCa.toFixed(2);
            document.getElementById("earnedEntertainment").value = temv_EarnedEntertainment.toFixed(2);
            document.getElementById("earnedKidsEducation").value = temv_EarnedKidsEdu.toFixed(2);
            document.getElementById("earnedVehicleAllowance").value = temv_EarnedVehicleAllowance.toFixed(2);
            document.getElementById("earnedMiscPay").value = temv_EarnedMiscPay.toFixed(2);
            document.getElementById("earnedLongTermBonus").value = temv_EarnedLongtermBonus.toFixed(2);
            document.getElementById("earnedProjectPay").value = temv_EarnedProjectPay.toFixed(2);
            document.getElementById("earnedsplallowance").value = temv_EarnedSplAllowance.toFixed(2);
            document.getElementById("grossPayActualDetails").value = temv_EarnedGrossPay.toFixed(2);
            document.getElementById("netPaidActualDetails").value = temv_EarnedNetPaid.toFixed(2);
            // document.getElementById("earnedsplallowance").value = temv_EarnedSplAllowance.toFixed(2);
            document.getElementById("earnedattallowance").value = parseFloat(temv_EarnedAttAllowance).toFixed(2);
            document.getElementById("earnedEmployerPf").value = parseFloat(v_earned_employerPf).toFixed(2);
            document.getElementById("employeePfActualDetails").value = parseFloat(v_earned_employeePf).toFixed(2);
       
            if(temv_EarnedGrossPay)
                //doRepaymentFlag
                if(document.getElementById("doRepaymentFlag").checked){
                    document.getElementById("repaymentGross").value =temv_repaymentGross.toFixed(2);
                    document.getElementById("repaymentNet").value =temv_repaymentNet.toFixed(2);
                    document.getElementById("repaymentVariablePay").value =temv_repaymentVarriable.toFixed(2);
                }
        
        //daysWorked1();
        // return true;
        
        }else
        {
            // document.getElementById('daysVacation').value =  document.getElementById('daysVacationHidden').value;
            document.getElementById('daysWorked').value =  document.getElementById('daysWorkedHidden').value;
            document.getElementById('daysProject').value =document.getElementById('daysProjectHiddenValue').value;
            setTimeout(function(){
                document.employeeWageDetails.daysWorked.focus()
            }, 10);
            return false;
        }
    //  return true;
    }else
    {
        document.getElementById('daysWorked').value = document.getElementById('daysWorkedHidden').value;
        document.getElementById('daysProject').value =document.getElementById('daysProjectHiddenValue').value;
        setTimeout(function(){
            document.employeeWageDetails.daysWorked.focus()
        }, 10);
        return false;
    }  
}