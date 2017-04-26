/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/********************************************************************************
 File :menuEmployee.js -Left Menu Main Menu And Sub Mneu Links For Employee Role.
 
 *******************************************************************************/
  /******************************************
   *My -> Create Task
   *   -> Tasks
   *   -> Time Sheets
   *   -> Apply Leave
   *   -> Profile
   *   -> Authored Exams
   *   -> E Certification
   *   -> Calendar
   *   -> Projects Dashboard
   *   -> PMO Activity
   *   -> Create Project
   *   -> PMO Dashboard
   *   -> Perf.Review 
   *   -> No Dues 
   *   -> Appreciations 
   ******************************************/
  // My Menu Start


$  ( function( ) {
        var action=document.getElementById("actionForLeftMenu").innerHTML
        var searchflag=document.getElementById("searchFlagForLeftMenu").innerHTML
       
     if(action=="newCreateTask") //Create Task
         {
            // alert("myCreateTask")
          document.getElementById("myDisplay").style.display="block";    
           document.getElementById("myCreateTask").style.color="#FFFF00"; 
             document.getElementById("myCreateTask").style.fontWeight="bold";
              document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
   else if(action=="getTasks") //Tasks
    {
//            alert("myTasks")
        document.getElementById("myDisplay").style.display="block";    
        document.getElementById("myTasks").style.color="#FFFF00"; 
        document.getElementById("myTasks").style.fontWeight="bold";
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }  
    else if(action=="newtimeSheet") //Time Sheets
    {
            
        document.getElementById("myDisplay").style.display="block";    
        document.getElementById("myTimeSheets").style.color="#FFFF00"; 
        document.getElementById("myTimeSheets").style.fontWeight="bold"; 
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
    else if(action=="leaveRequestList") //Apply Leave
    {
            
        document.getElementById("myDisplay").style.display="block";    
        document.getElementById("myApplyLeave").style.color="#FFFF00"; 
        document.getElementById("myApplyLeave").style.fontWeight="bold"; 
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } 
    else if(action=="getProfile") //Profile
    {
             
        document.getElementById("myDisplay").style.display="block";    
        document.getElementById("myProfile").style.color="#FFFF00"; 
        document.getElementById("myProfile").style.fontWeight="bold"; 
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } 
     else if(action=="getMyAuthoredExamsList") //Authored Exams
    {
             
        document.getElementById("myDisplay").style.display="block";    
        document.getElementById("myAuthoredTasks").style.color="#FFFF00"; 
        document.getElementById("myAuthoredTasks").style.fontWeight="bold"; 
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } 
     else if(action=="getEcertification") //E Certification
    {
             
        document.getElementById("myDisplay").style.display="block";    
        document.getElementById("myECertification").style.color="#FFFF00"; 
        document.getElementById("myECertification").style.fontWeight="bold"; 
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } 
     else if(action=="empCalendar") //Calendar
    {
             
        document.getElementById("myDisplay").style.display="block";    
        document.getElementById("myCalendar").style.color="#FFFF00"; 
        document.getElementById("myCalendar").style.fontWeight="bold"; 
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } 
     
    
    else if(action=="getCustomerProjectsList") //PMO Activity
    {
             
        document.getElementById("myDisplay").style.display="block";    
        document.getElementById("myCreateProject").style.color="#FFFF00"; 
        document.getElementById("myCreateProject").style.fontWeight="bold"; 
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
    
   
     else if(action=="giveMyReview") //Perf.Review
    {
             
        document.getElementById("myDisplay").style.display="block";    
        document.getElementById("myPerfReview").style.color="#FFFF00"; 
        document.getElementById("myPerfReview").style.fontWeight="bold"; 
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } 
     else if(action=="getEmployeeNoDues") //No Dues
    {
             
        document.getElementById("myDisplay").style.display="block";    
        document.getElementById("myNoDues").style.color="#FFFF00"; 
        document.getElementById("myNoDues").style.fontWeight="bold"; 
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } 
      else if(searchflag=="my")  //Appreciations
         {
//             alert(action)
          document.getElementById("myDisplay").style.display="block";   
           document.getElementById("myAppreciations").style.color="#FFFF00";
           document.getElementById("myAppreciations").style.fontWeight="bold";
            document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
       
       else if(action=="getEmployeePayRollDashBoard") //No Dues
    {
             
        document.getElementById("myDisplay").style.display="block";    
        document.getElementById("myPayroll").style.color="#FFFF00"; 
        document.getElementById("myPayroll").style.fontWeight="bold"; 
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } 
 // My Menu End
 
     
 /********************************************
    *Team -> Tasks
    *     -> Leave Approvals
    *     -> Leaves Applied
    *     -> Time Sheets
    *     -> Hierarchy
    *     -> Perf.Review
    *     -> Appreciations
    *     -> Dashboard
   ******************************************/
   
   // Team Menu Start
    else if(action=="getTeamTasks") //Tasks
    {
//            alert("start page")
        document.getElementById("teamDisplay").style.display="block";    
        document.getElementById("teamTasks").style.color="#FFFF00"; 
        document.getElementById("teamTasks").style.fontWeight="bold"; 
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } 
   else if(action=="leaveApprovalList") //Leave Approvals
    {
            
        document.getElementById("teamDisplay").style.display="block";    
        document.getElementById("teamLeaveApprovals").style.color="#FFFF00"; 
        document.getElementById("teamLeaveApprovals").style.fontWeight="bold"; 
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }  
    else if(action=="employeeLeave") //Leaves Applied
    {
            
        document.getElementById("teamDisplay").style.display="block";    
        document.getElementById("teamLeavesApplied").style.color="#FFFF00"; 
        document.getElementById("teamLeavesApplied").style.fontWeight="bold"; 
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } 
    else if(action=="newEmployeeteamTimeSheets") //Time Sheets
    {
            
        document.getElementById("teamDisplay").style.display="block";    
        document.getElementById("teamTimeSheets").style.color="#FFFF00"; 
        document.getElementById("teamTimeSheets").style.fontWeight="bold"; 
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } 
    else if(action=="myGDCTeamTree") //Hierarchy
    {
            
        document.getElementById("teamDisplay").style.display="block";    
        document.getElementById("teamHierarchy").style.color="#FFFF00"; 
        document.getElementById("teamHierarchy").style.fontWeight="bold"; 
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } 
    else if(action=="teamReviewList") //Perf.Review
    {
            
        document.getElementById("teamDisplay").style.display="block";    
        document.getElementById("teamPerfReview").style.color="#FFFF00"; 
        document.getElementById("teamPerfReview").style.fontWeight="bold"; 
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } 
    else if(searchflag=="team") //Appreciations
    {
//            alert("search flag team")
        document.getElementById("teamDisplay").style.display="block";    
        document.getElementById("teamAppreciations").style.color="#FFFF00"; 
        document.getElementById("teamAppreciations").style.fontWeight="bold"; 
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } 
    else if(action=="getManagerDashBoard") //Perf.Review
    {
            
        document.getElementById("teamDisplay").style.display="block";    
        document.getElementById("teamDashboard").style.color="#FFFF00"; 
        document.getElementById("teamDashboard").style.fontWeight="bold"; 
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } 
     // Team Menu End
     
     /********************************************
    *Services -> Suggestion Box Data
    *         -> MyCopReport 
    *         -> Employee Search
    *         -> Reset My Pwd
    *         -> Mail Service
    *         -> Tech reviews
    *         -> Release Notes
    *         -> Bridge
    *         -> BMS
   ******************************************/
       // Services Menu Start

    else if(action=="getSuggestionBox") //Suggestion Box Data
    {
             
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("servicesSuggestionBoxData").style.color="#FFFF00"; 
        document.getElementById("servicesSuggestionBoxData").style.fontWeight="bold"; 
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
    else if(action=="mycopreport") //MyCopReport
    {
             
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("servicesMyCopReport").style.color="#FFFF00"; 
        document.getElementById("servicesMyCopReport").style.fontWeight="bold";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } 
//    else if(action=="bridgeScheduleList") 
//    {
//            
//        document.getElementById("servicesDisplay").style.display="block";    
//        document.getElementById("servicesBridgeConference").style.color="#FFFF00"; 
//        document.getElementById("servicesBridgeConference").style.fontWeight="bold";
//        document.getElementById("servicesAdmin").style.background = "url(/Hubble/includes/images/icon_minus.png ) 96% center no-repeat ";
//    }
    else if(action=="employeeSearch") //Employee Search
    {
             
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("servicesEmployeeSearch").style.color="#FFFF00"; 
        document.getElementById("servicesEmployeeSearch").style.fontWeight="bold"; 
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
    else if(action=="resetPassword") //Reset My Pwd
    {
             
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("servicesResetMyPwd").style.color="#FFFF00"; 
        document.getElementById("servicesResetMyPwd").style.fontWeight="bold";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
      else if(action=="consultantTechReviews") //Tech Reviews
    {
            
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("servicesTechReviews").style.color="#FFFF00"; 
        document.getElementById("servicesTechReviews").style.fontWeight="bold";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
    else if(action=="releaseNotes") //Release Notes
    {
        
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("servicesReleaseNotes").style.color="#FFFF00"; 
        document.getElementById("servicesReleaseNotes").style.fontWeight="bold";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
    else if(action=="bridgeSearch") //Bridge
    {
        
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("servicesBridge").style.color="#FFFF00"; 
        document.getElementById("servicesBridge").style.fontWeight="bold";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
    else if(action=="bmsEvent") //BMS
    {
        
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("servicesBMS").style.color="#FFFF00"; 
        document.getElementById("servicesBMS").style.fontWeight="bold";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
else if(action=="clientReqEngagementApprovalsSearch") //BMS
    {
        
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("clientReqEngagementApprovalsSearch").style.color="#FFFF00"; 
        document.getElementById("clientReqEngagementApprovalsSearch").style.fontWeight="bold";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
    // Services Menu End
    //Mile Menu Starts
    else if(action=="creConsultantList") //CRE Consultents
    {
             
        document.getElementById("mileDisplay").style.display="block";    
        document.getElementById("mileCreConsultents").style.color="#FFFF00"; 
        document.getElementById("mileCreConsultents").style.fontWeight="bold"; 
        document.getElementById("mileAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
    else if(action=="getCreRecords") //Get Consultant
    {
       
        document.getElementById("mileDisplay").style.display="block";    
        document.getElementById("mileGetConsultant").style.color="#FFFF00"; 
        document.getElementById("mileGetConsultant").style.fontWeight="bold"; 
        document.getElementById("mileAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
    else if(action=="setCreExam") //Set CRE Exam
    {
            
        document.getElementById("mileDisplay").style.display="block";    
        document.getElementById("mileSetCreExam").style.color="#FFFF00"; 
        document.getElementById("mileSetCreExam").style.fontWeight="bold"; 
        document.getElementById("mileAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
  else if(action=="getTeamHirearchy") //Appreciations
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("getTeamHirearchy").style.color="#FFFF00"; 
           document.getElementById("getTeamHirearchy").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
		 
		 
      //Mile Menu Ends
      
        else if(action=="getMcertRecords") //Set CRE Exam
    {
            
        document.getElementById("mcertDisplay").style.display="block";    
        document.getElementById("getMcertRecords").style.color="#FFFF00"; 
        document.getElementById("getMcertRecords").style.fontWeight="bold"; 
        document.getElementById("mcertAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
      else if(action=="setMcertExam") //Set CRE Exam
    {
            
        document.getElementById("mcertDisplay").style.display="block";    
        document.getElementById("setMcertExam").style.color="#FFFF00"; 
        document.getElementById("setMcertExam").style.fontWeight="bold"; 
        document.getElementById("mcertAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } else if(action=="getMcertExamResultsList") //Set CRE Exam
    {
            
        document.getElementById("mcertDisplay").style.display="block";    
        document.getElementById("getMcertExamResultsList").style.color="#FFFF00"; 
        document.getElementById("getMcertExamResultsList").style.fontWeight="bold"; 
        document.getElementById("mcertAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }else if(action=="mcertConsultantList") //Set CRE Exam
    {
            
        document.getElementById("mcertDisplay").style.display="block";    
        document.getElementById("mcertConsultantList").style.color="#FFFF00"; 
        document.getElementById("mcertConsultantList").style.fontWeight="bold"; 
        document.getElementById("mcertAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }else if(action=="pmoDashBoard") //Dashbaord Tab
    {
            
        document.getElementById("dashboardDisplay").style.display="block";    
        document.getElementById("pmoDashBoard").style.color="#FFFF00"; 
        document.getElementById("pmoDashBoard").style.fontWeight="bold"; 
        document.getElementById("dasboardAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }else if(action=="getTaskDashBoard") //Dashbaord Tab
    {
            
        document.getElementById("dashboardDisplay").style.display="block";    
        document.getElementById("getTaskDashBoard").style.color="#FFFF00"; 
        document.getElementById("getTaskDashBoard").style.fontWeight="bold"; 
        document.getElementById("dasboardAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }else if(action=="projectDashBoard") //Dashbaord Tab
    {
            
        document.getElementById("dashboardDisplay").style.display="block";    
        document.getElementById("projectDashBoard").style.color="#FFFF00"; 
        document.getElementById("projectDashBoard").style.fontWeight="bold"; 
        document.getElementById("dasboardAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } else if(action=="pmoActivity") //PMO Activity
    {
             
        document.getElementById("dashboardDisplay").style.display="block";    
        document.getElementById("myPmoActivity").style.color="#FFFF00"; 
        document.getElementById("myPmoActivity").style.fontWeight="bold"; 
        document.getElementById("dasboardAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }  else if(action=="projectPortfolioReport") //Appreciations
         {
//             alert(action)
          document.getElementById("dashboardDisplay").style.display="block";    
           document.getElementById("projectPortfolioReport").style.color="#FFFF00"; 
           document.getElementById("projectPortfolioReport").style.fontWeight="bold"; 
           document.getElementById("dasboardAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
    
    /*
     *Dashbaord Tab
     */
    
    
	
//         else {
//           document.getElementById("myDisplay").style.display="none";   
//           document.getElementById("myCreateTask").style.color="white";  
//            document.getElementById("myDisplay").style.display="none";   
//           document.getElementById("myTasks").style.color="white"; 
//            document.getElementById("myDisplay").style.display="none";    
//        document.getElementById("myTimeSheets").style.color="white"; 
//          document.getElementById("myDisplay").style.display="none";    
//        document.getElementById("myApplyLeave").style.color="white"; 
//             document.getElementById("myDisplay").style.display="none";    
//        document.getElementById("myProfile").style.color="white"; 
//        document.getElementById("myDisplay").style.display="none";    
//        document.getElementById("myAuthoredTasks").style.color="white"; 
//          document.getElementById("myDisplay").style.display="none";    
//        document.getElementById("myECertification").style.color="white"; 
//         document.getElementById("myDisplay").style.display="none";    
//        document.getElementById("myCalendar").style.color="white"; 
//         document.getElementById("myDisplay").style.display="none";    
//        document.getElementById("myProjectDashboard").style.color="white"; 
//          document.getElementById("myDisplay").style.display="none";    
//        document.getElementById("myPmoActivity").style.color="white"; 
//        document.getElementById("myDisplay").style.display="none";    
//        document.getElementById("myProjectDashboard").style.color="white"; 
//        document.getElementById("myDisplay").style.display="none";    
//        document.getElementById("myPerfReview").style.color="white"; 
//         document.getElementById("myDisplay").style.display="none";    
//        document.getElementById("myNoDues").style.color="white"; 
//        document.getElementById("myDisplay").style.display="none";   
//           document.getElementById("myAppreciations").style.color="white";
//             document.getElementById("teamDisplay").style.display="none";    
//        document.getElementById("teamTasks").style.color="white"; 
//        document.getElementById("teamDisplay").style.display="none";    
//        document.getElementById("teamLeaveApprovals").style.color="white"; 
//         document.getElementById("teamDisplay").style.display="none";    
//        document.getElementById("teamLeavesApplied").style.color="white"; 
//          document.getElementById("teamDisplay").style.display="none";    
//        document.getElementById("teamTimeSheets").style.color="white"; 
//        document.getElementById("teamDisplay").style.display="none";    
//        document.getElementById("teamHierarchy").style.color="white"; 
//        document.getElementById("teamDisplay").style.display="none";    
//        document.getElementById("teamPerfReview").style.color="white"; 
//         document.getElementById("teamDisplay").style.display="none";    
//        document.getElementById("teamAppreciations").style.color="white"; 
//         document.getElementById("servicesDisplay").style.display="none";    
//        document.getElementById("servicesSuggestionBoxData").style.color="white";
//          document.getElementById("servicesDisplay").style.display="none";    
//        document.getElementById("servicesMyCopReport").style.color="white";
////          document.getElementById("servicesDisplay").style.display="none";    
////        document.getElementById("servicesBridgeConference").style.color="white";
//         document.getElementById("servicesDisplay").style.display="none";    
//        document.getElementById("servicesEmployeeSearch").style.color="white"; 
//         document.getElementById("servicesDisplay").style.display="none";    
//        document.getElementById("servicesResetMyPwd").style.color="white"; 
//          document.getElementById("servicesDisplay").style.display="none";    
//        document.getElementById("servicesTechReviews").style.color="white"; 
//          document.getElementById("servicesDisplay").style.display="none";    
//        document.getElementById("servicesCreConsultents").style.color="white"; 
//          document.getElementById("servicesDisplay").style.display="none";    
//        document.getElementById("servicesGetConsultant").style.color="white"; 
//        document.getElementById("servicesDisplay").style.display="none";    
//        document.getElementById("servicesSetCreExam").style.color="white"; 
//        document.getElementById("servicesDisplay").style.display="none";    
//        document.getElementById("servicesReleaseNotes").style.color="white"; 
//         }
       
       
/**********************************************************************
 Purpose :The below function provides toggling functionality.
***********************************************************************/
       
$( document ).ready(function() {
$('#cssmenu > ul > li > a').click(function() {
  $('#cssmenu li').removeClass('active');
   
  $(this).closest('li').addClass('active');	
  var checkElement = $(this).next();
  if((checkElement.is('ul')) && (checkElement.is(':visible'))) {
    $(this).closest('li').removeClass('active');
    checkElement.slideUp('normal');
  }
  if((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
    $('#cssmenu ul ul:visible').slideUp('normal');
    checkElement.slideDown('normal');
  }
  if($(this).closest('li').find('ul').children().length == 0) {
    return true;
  } else {
    return false;	
  }		
});
});
} );


/*************************************************************************************************************
 The following function provides managing of background-image while toggling for the specified category Start
 *************************************************************************************************************/

	
$  ( function( ) {
var EmployeeTypeId=document.getElementById("EmployeeTypeId").value
//alert(EmployeeTypeId);

if(EmployeeTypeId == 'Contractor')
    {
       // alert("Contractor");
            $("#iconToggleMy").click(function() {  
        if(document.getElementById("myDisplay").style.display == 'block')
        {
            document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        }
        else
        {
            document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
        }
       // document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/icon_minus.png ) 96% center no-repeat ";
          
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        
        
    });
     $("#iconToggleServices").click(function() {
        if(document.getElementById("servicesDisplay").style.display == 'block')
        {
            document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        }
        else
        {
            document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
        }
         
       document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
       
    });
    
    }
    else
        {
         
        
  var teamLead=document.getElementById("teamLead").value
  
         //alert(teamLead)
         var usrManger=document.getElementById("userManager").value
         // alert(usrManger)
         var creTeam=document.getElementById("isCRETeam").value
         
    $("#iconToggleMy").click(function() {  
        if(document.getElementById("myDisplay").style.display == 'block')
        {
            document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        }
        else
        {
            document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
        }
       // document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/icon_minus.png ) 96% center no-repeat ";
          if(teamLead=="1" || usrManger=="1")
              {
             document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";      
              }
       
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        if(creTeam=="1")
              {
             document.getElementById("mileAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";   
               document.getElementById("mcertAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
            document.getElementById("dasboardAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        
    });
    $("#iconToggleTeam").click(function() {
        if(document.getElementById("teamDisplay").style.display == 'block')
        {
            document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        }
        else
        {
            document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
        }
       // document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/icon_minus.png ) 96% center no-repeat ";
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        if(creTeam=="1")
              {
             document.getElementById("mileAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat "; 
             document.getElementById("mcertAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
              document.getElementById("dasboardAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
    });
    $("#iconToggleServices").click(function() {
        if(document.getElementById("servicesDisplay").style.display == 'block')
        {
            document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        }
        else
        {
            document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
        }
         if(teamLead=="1" || usrManger=="1")
              {
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
       document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
       if(creTeam=="1")
              {
             document.getElementById("mileAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
             document.getElementById("mcertAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
              document.getElementById("dasboardAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
    });
     $("#iconToggleMile").click(function() {
         if(document.getElementById("mileDisplay").style.display == 'block')
        {
            document.getElementById("mileAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        }
        else
        {
            document.getElementById("mileAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
        }
         document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
         if(teamLead=="1" || usrManger=="1")
              {
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
              
              document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
         document.getElementById("mcertAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
         document.getElementById("dasboardAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
     });
     
     
     $("#iconToggleMcert").click(function() {
         if(document.getElementById("mcertDisplay").style.display == 'block')
        {
            document.getElementById("mcertAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        }
        else
        {
            document.getElementById("mcertAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
        }
         document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
         if(teamLead=="1" || usrManger=="1")
              {
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
              if(creTeam=="1")
              {
             document.getElementById("mileAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";      
              }
               document.getElementById("dasboardAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
         
     });
     
     
     
     $("#iconToggleDashboards").click(function() {  
        if(document.getElementById("dashboardDisplay").style.display == 'block')
        {
            document.getElementById("dasboardAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        }
        else
        {
            document.getElementById("dasboardAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
        }
         document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
       // document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/icon_minus.png ) 96% center no-repeat ";
          if(teamLead=="1" || usrManger=="1")
              {
             document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";      
              }
       
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        if(creTeam=="1")
              {
             document.getElementById("mileAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";   
               document.getElementById("mcertAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
              
              
            
        
    });
     
     
     
     
        }
});


/***********************************************************************************************************
 The following function provides managing of background-image while toggling for the specified category End
 ***********************************************************************************************************/
