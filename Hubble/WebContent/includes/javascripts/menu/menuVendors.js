/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/********************************************************************************
 File :menuWebAdmin.js -Left Menu Main Menu And Sub Mneu Links For WebAdmin Role.
 ********************************************************************************/
  /******************************************
   *Services -> Email Campaign List
   *         -> WebSite jobs
   ******************************************/
        // Services Menu Start
$  ( function( ) {
        var action=document.getElementById("action").innerHTML
        
//        if(action=="emailCampaignList") //Email Campaign List
//         {
//            
//          document.getElementById("servicesDisplay").style.display="block";    
//           document.getElementById("emailCampaignList").style.color="#FFFF00"; 
//           document.getElementById("emailCampaignList").style.fontWeight="bold";
//           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/icon_minus.png ) 96% center no-repeat ";
//         } 
         
          if(action=="getTasks") //Email Campaign List
         {
            
          document.getElementById("myDisplay").style.display="block";    
           document.getElementById("myTasks").style.color="#FFFF00"; 
           document.getElementById("myTasks").style.fontWeight="bold";
           document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
        else if(action=="newtimeSheet") //Website jobs
         {
             
          document.getElementById("myDisplay").style.display="block";    
           document.getElementById("myTimeSheets").style.color="#FFFF00"; 
             document.getElementById("myTimeSheets").style.fontWeight="bold";

  document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";  
       }
      // Services Menu End
      
   /******************************************
   *Events   -> Upcoming Events
   *         -> Completed Events
   *         -> Webinar Series
   ******************************************/
     // Events Menu Start
          else if(action=="myRecruitmentTeamTree") //Upcoming Events
         {
             
          document.getElementById("teamDisplay").style.display="block";    
           document.getElementById("teamHierarchy").style.color="#FFFF00"; 
           document.getElementById("teamHierarchy").style.fontWeight="bold"; 
           document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
          else if(action=="account") //Completed Events
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("servicesAddVendor").style.color="#FFFF00"; 
           document.getElementById("servicesAddVendor").style.fontWeight="bold";
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
          else if(action=="vendorsList") //Webinar Series
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("servicesAllVendors").style.color="#FFFF00"; 
           document.getElementById("servicesAllVendors").style.fontWeight="bold";
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
         
//         else {
//            document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("websiteJob").style.color="white"; 
//            document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("emailCampaignList").style.color="white";
//           document.getElementById("EventsDisplay").style.display="none";    
//           document.getElementById("eventManagement").style.color="white";
//            document.getElementById("EventsDisplay").style.display="none";    
//           document.getElementById("completedEvents").style.color="white";
//           document.getElementById("EventsDisplay").style.display="none";    
//           document.getElementById("webinarSeries").style.color="white"; 
//             document.getElementById("ResourcesDisplay").style.display="none";    
//           document.getElementById("getLkpTrackNames").style.color="white";
//             document.getElementById("ResourcesDisplay").style.display="none";    
//           document.getElementById("getResources").style.color="white"; 
//           document.getElementById("ResourcesDisplay").style.display="none";    
//           document.getElementById("getPeople").style.color="white"; 
//            document.getElementById("ResourcesDisplay").style.display="none";    
//           document.getElementById("surveyFormList").style.color="white"; 
//            document.getElementById("webdataDisplay").style.display="none";    
//           document.getElementById("getContactUs").style.color="white";
//           document.getElementById("webdataDisplay").style.display="none";    
//           document.getElementById("getEmpVerification").style.color="white"; 
//            document.getElementById("webdataDisplay").style.display="none";    
//           document.getElementById("getResourceDepot").style.color="white";
//           document.getElementById("webdataDisplay").style.display="none";    
//           document.getElementById("getQuarterlyMeet").style.color="white";
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
var teamLead=document.getElementById("userTeamLead").value
var usrManger=document.getElementById("userManager").value
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

