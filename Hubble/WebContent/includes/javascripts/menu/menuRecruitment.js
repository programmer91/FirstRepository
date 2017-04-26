/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/****************************************************************************************
 File :menuRecruitment.js -Left Menu Main Menu And Sub Mneu Links For Recruitment Role.
 *****************************************************************************************/


   /********************************************
    *My   -> Time Sheets
    *     -> Consultant Details
   ******************************************/
  // My Menu Start
  $  ( function( ) {
        var action=document.getElementById("action").innerHTML
     
           if(action=="newtimeSheet") //Time Sheets
         {
//             alert(action)
          document.getElementById("myDisplay").style.display="block";    
           document.getElementById("newtimeSheet").style.color="#FFFF00"; 
           document.getElementById("newtimeSheet").style.fontWeight="bold"; 
           document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
          else if(action=="popUpConsultant") //Consultant Details
         {
//             alert(action)
          document.getElementById("myDisplay").style.display="block";    
           document.getElementById("popUpConsultant").style.color="#FFFF00"; 
           document.getElementById("popUpConsultant").style.fontWeight="bold";
           document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
         // My Menu End
         
         
   /********************************************
    *Team -> Hierarchy
    *     -> Dashboard
    *     -> Consultant Merge
   ******************************************/
   
   // Team Menu Start
         else if(action=="myRecruitmentTeamTree") //Hierarchy
         {
//             alert(action)
          document.getElementById("teamDisplay").style.display="block";    
           document.getElementById("myRecruitmentTeamTree").style.color="#FFFF00"; 
           document.getElementById("myRecruitmentTeamTree").style.fontWeight="bold"; 
           document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
          else if(action=="recruitmentDashboard") //Dashboard
         {
//             alert(action)
          document.getElementById("teamDisplay").style.display="block";    
           document.getElementById("recruitmentDashboard").style.color="#FFFF00"; 
           document.getElementById("recruitmentDashboard").style.fontWeight="bold"; 
           document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
          else if(action=="getConsultantMerge" ) //Consultant Merge
         {
//             alert(action)
          document.getElementById("teamDisplay").style.display="block";    
           document.getElementById("getConsultantMerge").style.color="#FFFF00"; 
           document.getElementById("getConsultantMerge").style.fontWeight="bold"; 
           document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
         // Team Menu End
    
    /********************************************
    *Services -> Employee Search
    *         -> Reset My Pwd
    *         -> Mail Service
    *         -> Survey Form
    *         -> Executive Dashboard
    *         -> Available Employee List
   ******************************************/
       // Services Menu Start
          else if(action=="employeeSearch") //Employee Search
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("employeeSearch").style.color="#FFFF00"; 
           document.getElementById("employeeSearch").style.fontWeight="bold"; 
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
         else if(action=="surveyFormList") //Survey Form
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("surveyFormList").style.color="#FFFF00"; 
           document.getElementById("surveyFormList").style.fontWeight="bold";
                      document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }
          else if(action=="executiveDashboardForRequirement") //Executive Dashboard
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("executiveDashboardForRequirement").style.color="#FFFF00"; 
           document.getElementById("executiveDashboardForRequirement").style.fontWeight="bold"; 
                      document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }
         else if(action=="pmoDashBoard") //Available Employee List
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("pmoDashBoard").style.color="#FFFF00"; 
           document.getElementById("pmoDashBoard").style.fontWeight="bold"; 
                      document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }
          // Services Menu End
          
    
         
    /********************************************
    *Recruitment -> Consultant Add
    *            -> Consultant Search
    *            -> Resume Search
    ******************************************/
       // Recruitment Menu Start
        else if(action=="consultantSearch")   //Consultant Add
         {
//             alert(action)
          document.getElementById("recruitmentDisplay").style.display="block";    
           document.getElementById("consultantSearch").style.color="#FFFF00"; 
             document.getElementById("consultantSearch").style.fontWeight="bold";
//           document.getElementById("iconToggleMy").css="#cssmenu > ul > li.has-sub.active > a span";  
 document.getElementById("recruitmentAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }
     else if(action=="getConsultant") //Consultant Search
         {
//             alert(action)
          document.getElementById("recruitmentDisplay").style.display="block";    
           document.getElementById("getConsultant").style.color="#FFFF00"; 
           document.getElementById("getConsultant").style.fontWeight="bold";
           document.getElementById("recruitmentAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }       else if(action=="untouchedConsultantsSearch") //Consultant Search
         {
//             alert(action)
          document.getElementById("recruitmentDisplay").style.display="block";    
           document.getElementById("untouchedConsultantsSearch").style.color="#FFFF00"; 
           document.getElementById("untouchedConsultantsSearch").style.fontWeight="bold";
           document.getElementById("recruitmentAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
          else if(action=="resumeSearch") //Resume Search
         {
//             alert(action)
          document.getElementById("recruitmentDisplay").style.display="block";    
           document.getElementById("resumeSearch").style.color="#FFFF00"; 
           document.getElementById("resumeSearch").style.fontWeight="bold"; 
           document.getElementById("recruitmentAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
           // Recruitment Menu End
           
           
    /********************************************
    *Requirement -> List
    *            -> All Requirements
    ******************************************/
       // Requirement Menu Start
   
          else if(action=="requirementList") //List
         {
//             alert(action)
          document.getElementById("requirementDisplay").style.display="block";    
           document.getElementById("requirementList").style.color="#FFFF00"; 
           document.getElementById("requirementList").style.fontWeight="bold"; 
           document.getElementById("requirementAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
          else if(action=="requirementAdminList") //All Requirements
         {
//             alert(action)
          document.getElementById("requirementDisplay").style.display="block";    
           document.getElementById("requirementAdminList").style.color="#FFFF00"; 
           document.getElementById("requirementAdminList").style.fontWeight="bold"; 
           document.getElementById("requirementAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
           // Requirement Menu End
         
         
    /********************************************
    *WebSite     -> Open Positions
    *            -> Job Applicants
    *            -> ConsultantList
    ******************************************/
       // WebSite Menu Start
          else if(action=="websiteJob") //Open Positions
         {
//             alert(action)
          document.getElementById("websiteDisplay").style.display="block";    
           document.getElementById("websiteJob").style.color="#FFFF00"; 
           document.getElementById("websiteJob").style.fontWeight="bold"; 
            document.getElementById("websiteAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
          else if(action=="websiteLatestJobApplications") //Job Applicants
         {
//             alert(action)
          document.getElementById("websiteDisplay").style.display="block";    
           document.getElementById("websiteLatestJobApplications").style.color="#FFFF00"; 
           document.getElementById("websiteLatestJobApplications").style.fontWeight="bold"; 
            document.getElementById("websiteAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
          else if(action=="consultantsFromWebsite") //ConsultantList
         {
//             alert(action)
          document.getElementById("websiteDisplay").style.display="block";    
           document.getElementById("consultantsFromWebsite").style.color="#FFFF00"; 
           document.getElementById("consultantsFromWebsite").style.fontWeight="bold"; 
           document.getElementById("websiteAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
          // WebSite Menu End
          
          
//         else {
//           document.getElementById("myDisplay").style.display="none";   
//           document.getElementById("newtimeSheet").style.color="white";  
//            document.getElementById("myDisplay").style.display="none";    
//           document.getElementById("popUpConsultant").style.color="white";
//           
//           
//           document.getElementById("teamDisplay").style.display="none";    
//           document.getElementById("myRecruitmentTeamTree").style.color="white"; 
//           document.getElementById("teamDisplay").style.display="none";    
//           document.getElementById("recruitmentDashboard").style.color="white"; 
//            document.getElementById("teamDisplay").style.display="none";    
//           document.getElementById("getConsultantMerge").style.color="white";
//           
//            document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("employeeSearch").style.color="white";
//           document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("resetPassword").style.color="white";
//            document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("surveyFormList").style.color="white";
//           document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("executiveDashboardForRequirement").style.color="white"; 
//           
//           document.getElementById("recruitmentDisplay").style.display="none";    
//           document.getElementById("getConsultant").style.color="white";
//             document.getElementById("recruitmentDisplay").style.display="none";    
//           document.getElementById("consultantSearch").style.color="white";
//             document.getElementById("recruitmentDisplay").style.display="none";    
//           document.getElementById("resumeSearch").style.color="white";
//           
//           document.getElementById("requirementDisplay").style.display="none";    
//           document.getElementById("requirementList").style.color="white"; 
//            document.getElementById("requirementDisplay").style.display="none";    
//           document.getElementById("requirementAdminList").style.color="white"; 
//           
//            document.getElementById("websiteDisplay").style.display="none";    
//           document.getElementById("websiteJob").style.color="white";
//           document.getElementById("websiteDisplay").style.display="none";    
//           document.getElementById("websiteLatestJobApplications").style.color="white"; 
//            document.getElementById("websiteDisplay").style.display="none";    
//           document.getElementById("consultantsFromWebsite").style.color="white";
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
 var teamLead=document.getElementById("teamLead").value
         //alert(teamLead)
         var usrManger=document.getElementById("userManager").value
         // alert(usrManger)
         var jobPostingFlag=document.getElementById("jobPostingFlag").value
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
        document.getElementById("recruitmentAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("requirementAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        if(jobPostingFlag=="1")
              {
          document.getElementById("websiteAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
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
        document.getElementById("recruitmentAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("requirementAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        if(jobPostingFlag=="1")
              {
          document.getElementById("websiteAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
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
        document.getElementById("recruitmentAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("requirementAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        if(jobPostingFlag=="1")
              {
          document.getElementById("websiteAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
    });
    $("#iconToggleRecruitment").click(function() {
        if(document.getElementById("recruitmentDisplay").style.display == 'block')
        {
            document.getElementById("recruitmentAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        }
        else
        {
            document.getElementById("recruitmentAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
        }
         if(teamLead=="1" || usrManger=="1")
              {
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("requirementAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        if(jobPostingFlag=="1")
              {
        document.getElementById("websiteAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
 
    });
    $("#iconToggleRequirement").click(function() {
        if(document.getElementById("requirementDisplay").style.display == 'block')
        {
            document.getElementById("requirementAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        }
        else
        {
            document.getElementById("requirementAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
        }
         if(teamLead=="1" || usrManger=="1")
              {
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
         document.getElementById("recruitmentAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
         if(jobPostingFlag=="1")
              {
         document.getElementById("websiteAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
    });
    $("#iconToggleWebsite").click(function() {
        if(document.getElementById("websiteDisplay").style.display == 'block')
        {
            document.getElementById("websiteAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        }
        else
        {
            document.getElementById("websiteAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
        }
         if(teamLead=="1" || usrManger=="1")
              {
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
         document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
         document.getElementById("recruitmentAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
         document.getElementById("requirementAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
    });
});


/***********************************************************************************************************
 The following function provides managing of background-image while toggling for the specified category End
 ***********************************************************************************************************/