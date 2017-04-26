/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/************************************************************************************
 File :menuOperations.js -Left Menu Main Menu And Sub Mneu Links For Operations Role.
 
 ************************************************************************************/

   /********************************************
    *Team -> Assign Roles
    *     -> Hierarchy
   ******************************************/
   
   // Team Menu Start

$  ( function( ) {
        var action=document.getElementById("action").innerHTML
         var noDueApprovers=document.getElementById("noDueApprovers").value
         //alert(teamLead)
         var noDueRemainders=document.getElementById("noDueRemainders").value
         // alert(usrManger)
     
     if(action=="myOperationsTeamTree") 
         {
//             alert(action)
          document.getElementById("teamDisplay").style.display="block";    
           document.getElementById("myOperationsTeamTree").style.color="#FFFF00"; 
           document.getElementById("myOperationsTeamTree").style.fontWeight="bold";
           document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }  
         // Team Menu End
         
         
   /********************************************
    *Services -> Appraisals
    *         -> Perf.Review
    *         -> Perf.Dashboard
    *         -> Survey Form
    *         -> Emp Leaves
    *         -> Leaves Report
    *         -> Accounts Search
    *         -> Assign Accounts
    *         -> GreenSheets
    *         -> GreenSheet Reports
    *         -> Emp TimeSheets
    *         -> Reset My Pwd
    *         -> Reset Cust Pwd
    *         -> Emp Search
    *         -> Mail Service
    *         -> Email Check
    *         -> Performance Review
    *         -> Dashboard
    *         -> Appreciations
   ******************************************/
       // Services Menu Start
if(action=="userSearch") 
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("userSearch").style.color="#FFFF00"; 
             document.getElementById("userSearch").style.fontWeight="bold";
//           document.getElementById("iconToggleMy").css="#cssmenu > ul > li.has-sub.active > a span";  
document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }

          else if(action=="getAppraisalsList") //Appraisals
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("getAppraisalsList").style.color="#FFFF00"; 
           document.getElementById("getAppraisalsList").style.fontWeight="bold";
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }
          else if(action=="teamReviewList") //Perf.Review
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("teamReviewList").style.color="#FFFF00"; 
           document.getElementById("teamReviewList").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
         else if(action=="perfDashboard") //Perf.Dashboard
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("perfDashboard").style.color="#FFFF00"; 
           document.getElementById("perfDashboard").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
         else if(action=="surveyFormList") //Survey Form
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("surveyFormList").style.color="#FFFF00"; 
           document.getElementById("surveyFormList").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
         else if(action=="empLeaveReport") //Emp Leaves
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("empLeaveReport").style.color="#FFFF00"; 
           document.getElementById("empLeaveReport").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
           else if(action=="GenerateEmpLeaveReport") //Leaves Report
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("GenerateEmpLeaveReport").style.color="#FFFF00"; 
           document.getElementById("GenerateEmpLeaveReport").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
          else if(action=="accountSearch") //Accounts Search
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("accountSearch").style.color="#FFFF00"; 
           document.getElementById("accountSearch").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
          else if(action=="accountAssign") //Assign Accounts
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("accountAssign").style.color="#FFFF00"; 
           document.getElementById("accountAssign").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
         else if(action=="greenSheet") //Greensheets
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("greenSheet").style.color="#FFFF00"; 
           document.getElementById("greenSheet").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
         else if(action=="greensheetReport") //Greensheet Reports
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("empTimeSheets").style.color="#FFFF00"; 
           document.getElementById("empTimeSheets").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
         else if(action=="newempTimeSheetSearch") //Emp Timesheets
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("newempTimeSheetSearch").style.color="#FFFF00"; 
           document.getElementById("newempTimeSheetSearch").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
         else if(action=="resetPassword") //Reset My Pwd
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("resetPassword").style.color="#FFFF00"; 
           document.getElementById("resetPassword").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
         else if(action=="changeCustPassword") //Reset Cust Pwd
         {          
             //  alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("changeCustPassword").style.color="#FFFF00"; 
           document.getElementById("changeCustPassword").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
         else if(action=="empSearchAll") //Emp Search
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";  
           document.getElementById("empSearchAll").style.color="#FFFF00"; 
           document.getElementById("empSearchAll").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
          else if(action=="empEmailCheck") //Email Check
         {
           //  alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("empEmailCheck").style.color="#FFFF00"; 
           document.getElementById("empEmailCheck").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
           else if(action=="givePerformanceReview") //Performance Review
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("givePerformanceReview").style.color="#FFFF00"; 
           document.getElementById("givePerformanceReview").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
          else if(action=="getDashBoardForEmpReports") //DashBoard
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("getDashBoardForEmpReports").style.color="#FFFF00"; 
           document.getElementById("getDashBoardForEmpReports").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
         else if(action=="getEmployeeNoDuesOperations") //No Dues
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("getEmployeeNoDuesOperations").style.color="#FFFF00"; 
           document.getElementById("getEmployeeNoDuesOperations").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
          else if(action=="getMyAppreciation") //Appreciations
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("getMyAppreciation").style.color="#FFFF00"; 
           document.getElementById("getMyAppreciation").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
          else if(action=="teamQuaterAppraisalSearch") //Appreciations
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("teamQuaterAppraisalSearch").style.color="#FFFF00"; 
           document.getElementById("teamQuaterAppraisalSearch").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }   else if(action=="getBdmAssociates") //Executive Dashboard
    {
        
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("getBdmAssociates").style.color="#FFFF00"; 
        document.getElementById("getBdmAssociates").style.fontWeight="bold";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }

          // Services Menu End
           
          

//         else {
         //    alert("In else");
//            document.getElementById("teamDisplay").style.display="none";    
//           document.getElementById("userSearch").style.color="white"; 
//            document.getElementById("teamDisplay").style.display="none";    
//           document.getElementById("myOperationsTeamTree").style.color="white";
//           document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("getAppraisalsList").style.color="white";
//            document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("teamReviewList").style.color="white";
//            document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("perfDashboard").style.color="white";
//             document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("surveyFormList").style.color="white";
//            document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("empLeaveReport").style.color="white";
//           document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("GenerateEmpLeaveReport").style.color="white";
//            document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("accountSearch").style.color="white";
//           document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("accountAssign").style.color="white";
//            document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("greenSheet").style.color="white";
//            document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("empTimeSheets").style.color="white";
//           document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("newempTimeSheetSearch").style.color="white";
//           document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("resetPassword").style.color="white";
//           document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("changeCustPassword").style.color="white";
//            document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("empSearchAll").style.color="white";
//           document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("empEmailCheck").style.color="white";
//            document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("givePerformanceReview").style.color="white";
//            document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("getDashBoardForEmpReports").style.color="white";
//       //   document.getElementById("servicesDisplay").style.display="none";    
//         //  document.getElementById("getEmployeeNoDuesOperations").style.color="white"; 
//           document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("getMyAppreciation").style.color="white";
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


/***********************************************************************************************************
 The following function provides managing of background-image while toggling for the specified category Start
 ***********************************************************************************************************/

$  ( function( ) {    
         var usrManger=document.getElementById("userManager").value
       var teamLead=document.getElementById("teamLead").value
    $("#iconToggleMy").click(function() {  
        if(document.getElementById("myDisplay").style.display == 'block')
        {
            document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        }
        else
        {
            document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
        }
       // document.getElementById("myAdmin").style.background = "url(/Hubble/includes/images/icon_minus.png ) 96% center no-repeat ";
       if(teamLead=="1" || usrManger=="1")
              {
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
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
       // document.getElementById("teamAdmin").style.background = "url(/Hubble/includes/images/icon_minus.png ) 96% center no-repeat ";
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
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
        if(teamLead=="1" || usrManger=="1")
              {
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
    });
});


/***********************************************************************************************************
 The following function provides managing of background-image while toggling for the specified category End
 ***********************************************************************************************************/