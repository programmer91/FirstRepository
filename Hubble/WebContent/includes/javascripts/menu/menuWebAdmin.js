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
         
          if(action=="campaignSearchAction") //Email Campaign List
         {
            
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("servicesCampaign").style.color="#FFFF00"; 
           document.getElementById("servicesCampaign").style.fontWeight="bold";
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
        else if(action=="websiteJob") //Website jobs
         {
             
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("websiteJob").style.color="#FFFF00"; 
             document.getElementById("websiteJob").style.fontWeight="bold";

  document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";  
       } else if(action=="getConstnatList") 
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("getConstnatList").style.color="#FFFF00"; 
           document.getElementById("getConstnatList").style.fontWeight="bold";
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
      // Services Menu End
      
   /******************************************
   *Events   -> Upcoming Events
   *         -> Completed Events
   *         -> Webinar Series
   ******************************************/
     // Events Menu Start
          else if(action=="eventManagement") //Upcoming Events
         {
             
          document.getElementById("EventsDisplay").style.display="block";    
           document.getElementById("eventManagement").style.color="#FFFF00"; 
           document.getElementById("eventManagement").style.fontWeight="bold"; 
           document.getElementById("eventsAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
          else if(action=="completedEvents") //Completed Events
         {
//             alert(action)
          document.getElementById("EventsDisplay").style.display="block";    
           document.getElementById("completedEvents").style.color="#FFFF00"; 
           document.getElementById("completedEvents").style.fontWeight="bold";
           document.getElementById("eventsAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
          else if(action=="webinarSeries") //Webinar Series
         {
//             alert(action)
          document.getElementById("EventsDisplay").style.display="block";    
           document.getElementById("webinarSeries").style.color="#FFFF00"; 
           document.getElementById("webinarSeries").style.fontWeight="bold";
           document.getElementById("eventsAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
          else if(action=="emeetList") //Webinar Series
         {
//             alert(action)
          document.getElementById("EventsDisplay").style.display="block";    
           document.getElementById("eMeets").style.color="#FFFF00"; 
           document.getElementById("eMeets").style.fontWeight="bold";
           document.getElementById("eventsAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }else if(action=="internalEvents") //Webinar Series
         {
//             alert(action)
          document.getElementById("EventsDisplay").style.display="block";    
           document.getElementById("internalEvents").style.color="#FFFF00"; 
           document.getElementById("internalEvents").style.fontWeight="bold";
           document.getElementById("eventsAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
         else if(action=="externalEvents") //Webinar Series
         {
//             alert(action)
          document.getElementById("EventsDisplay").style.display="block";    
           document.getElementById("externalEvents").style.color="#FFFF00"; 
           document.getElementById("externalEvents").style.fontWeight="bold";
           document.getElementById("eventsAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
          // Events Menu End
          
  /******************************************
   *Resources   -> Tracks
   *            -> Library
   *            -> People
   *            -> Survey Form
  ******************************************/
     // Resources Menu Start
         else if(action=="getLkpTrackNames") 
         {
           //  alert(action)
          document.getElementById("ResourcesDisplay").style.display="block"; 
        //  alert("block")
           document.getElementById("getLkpTrackNames").style.color="#FFFF00"; 
         //  alert("color")
           document.getElementById("getLkpTrackNames").style.fontWeight="bold";
          // alert("bold")
           document.getElementById("resourcesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
        //   alert("image")
         } 
          else if(action=="getResources") 
         {
//             alert(action)
          document.getElementById("ResourcesDisplay").style.display="block";    
           document.getElementById("getResources").style.color="#FFFF00"; 
           document.getElementById("getResources").style.fontWeight="bold"; 
           document.getElementById("resourcesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
          else if(action=="getPeople" ) 
         {
//             alert(action)
          document.getElementById("ResourcesDisplay").style.display="block";    
           document.getElementById("getPeople").style.color="#FFFF00"; 
           document.getElementById("getPeople").style.fontWeight="bold"; 
           document.getElementById("resourcesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
          else if(action=="surveyFormList") 
         {
//             alert(action)
          document.getElementById("ResourcesDisplay").style.display="block";    
           document.getElementById("surveyFormList").style.color="#FFFF00"; 
           document.getElementById("surveyFormList").style.fontWeight="bold"; 
           document.getElementById("resourcesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
         
         else if(action=="emeetAttendiesList") 
         {
//             alert(action)
          document.getElementById("ResourcesDisplay").style.display="block";    
           document.getElementById("emeetAttendiesList").style.color="#FFFF00"; 
           document.getElementById("emeetAttendiesList").style.fontWeight="bold"; 
           document.getElementById("resourcesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
          // Resources Menu End
          
   /******************************************
   *Website Data   -> ContactUs Data
   *               -> EmpVerification Data
   *               -> Resource Depot Data
   *               -> QuarterlyMeet Data
  ******************************************/
     // Website Data Menu Start
         else if(action=="getContactUs") 
         {
//             alert(action)
          document.getElementById("webdataDisplay").style.display="block";    
           document.getElementById("getContactUs").style.color="#FFFF00"; 
           document.getElementById("getContactUs").style.fontWeight="bold"; 
           document.getElementById("websitedataAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
         else if(action=="getEmpVerification") 
         {
//             alert(action)
          document.getElementById("webdataDisplay").style.display="block";    
           document.getElementById("getEmpVerification").style.color="#FFFF00"; 
           document.getElementById("getEmpVerification").style.fontWeight="bold"; 
           document.getElementById("websitedataAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
          else if(action=="getResourceDepot") 
         {
//             alert(action)
          document.getElementById("webdataDisplay").style.display="block";    
           document.getElementById("getResourceDepot").style.color="#FFFF00"; 
           document.getElementById("getResourceDepot").style.fontWeight="bold"; 
           document.getElementById("websitedataAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
          else if(action=="getQuarterlyMeet") 
         {
//             alert(action)
          document.getElementById("webdataDisplay").style.display="block";    
           document.getElementById("getQuarterlyMeet").style.color="#FFFF00"; 
           document.getElementById("getQuarterlyMeet").style.fontWeight="bold";
           document.getElementById("websitedataAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
         
         else if(action=="getIOTDetails") 
         {
//             alert(action)
          document.getElementById("webdataDisplay").style.display="block";    
           document.getElementById("getIotDetails").style.color="#FFFF00"; 
           document.getElementById("getIotDetails").style.fontWeight="bold";
           document.getElementById("websitedataAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } else if(action=="getSignatureDetails") 
         {
//             alert(action)
          document.getElementById("webdataDisplay").style.display="block";    
           document.getElementById("getSignatureDetails").style.color="#FFFF00"; 
           document.getElementById("getSignatureDetails").style.fontWeight="bold";
           document.getElementById("websitedataAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
         // Website Data Menu End
         
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
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("resourcesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("websitedataAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
          document.getElementById("eventsAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
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
        document.getElementById("resourcesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("websitedataAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
          document.getElementById("eventsAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
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
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("resourcesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("websitedataAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
          document.getElementById("eventsAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
    });
    $("#iconToggleEvents").click(function() {
//        alert("event")
        if(document.getElementById("EventsDisplay").style.display == 'block')
        {
            document.getElementById("eventsAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        }
        else
        {
            document.getElementById("eventsAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
        }
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("resourcesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("websitedataAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
 
    });
    $("#iconToggleResources").click(function() {
        if(document.getElementById("ResourcesDisplay").style.display == 'block')
        {
            document.getElementById("resourcesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        }
        else
        {
            document.getElementById("resourcesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
        }
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
         document.getElementById("eventsAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
         document.getElementById("websitedataAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
    });
    $("#iconToggleWebsiteData").click(function() {
        if(document.getElementById("webdataDisplay").style.display == 'block')
        {
            document.getElementById("websitedataAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        }
        else
        {
            document.getElementById("websitedataAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
        }
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
         document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
         document.getElementById("eventsAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
         document.getElementById("resourcesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
    });
});

/***********************************************************************************************************
 The following function provides managing of background-image while toggling for the specified category End
 ***********************************************************************************************************/

