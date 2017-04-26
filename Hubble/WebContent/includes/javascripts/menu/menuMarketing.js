/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/***********************************************************************************
 File :menuMarketing.js -Left Menu Main Menu And Sub Mneu Links For Marketing Role.
 
 ***********************************************************************************/
  /******************************************
   *Services -> Add Account
   *         -> All Accounts
   *         -> Activity Manager
   *         -> Account Operations
   ******************************************/
  // Services Menu Start
  
 $  ( function( ) {
        var action=document.getElementById("action").innerHTML
     if(action=="accountsListAll") //Add Account
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("accountsListAll").style.color="#FFFF00"; 
             document.getElementById("accountsListAll").style.fontWeight="bold";
//           document.getElementById("iconToggleMy").css="#cssmenu > ul > li.has-sub.active > a span";  
document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }
     else if(action=="account") //All Accounts
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("account").style.color="#FFFF00"; 
           document.getElementById("account").style.fontWeight="bold";
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }  
          else if(action=="activityManager") //Activity Manager
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("activityManager").style.color="#FFFF00"; 
           document.getElementById("activityManager").style.fontWeight="bold";
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }
          else if(action=="accountAssign") //Account Operations
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("accountAssign").style.color="#FFFF00"; 
           document.getElementById("accountAssign").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
         else if(action=="contactSearch") //Account Operations
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("contactSearch").style.color="#FFFF00"; 
           document.getElementById("contactSearch").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
         else if(action=="campaignSearchAction") //Account Operations
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("campaignSearchAction").style.color="#FFFF00"; 
           document.getElementById("campaignSearchAction").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
         else if(action=="emeetList") //Account Operations
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("emeetList").style.color="#FFFF00"; 
           document.getElementById("emeetList").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
         else if(action=="emeetAttendiesList") //Account Operations
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("emeetAttendiesList").style.color="#FFFF00"; 
           document.getElementById("emeetAttendiesList").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }
         else if(action=="getInsideSalesStatus") //Account Operations
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("getInsideSalesStatus").style.color="#FFFF00"; 
           document.getElementById("getInsideSalesStatus").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }else if(action=="internalEvents") //Account Operations
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("internalEvents").style.color="#FFFF00"; 
           document.getElementById("internalEvents").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }
          // Services Menu End
           
           else if(action=="leadGen") //Account Operations
         {
//             alert(action)
          document.getElementById("leadGenDisplay").style.display="block";    
           document.getElementById("leadGen").style.color="#FFFF00"; 
           document.getElementById("leadGen").style.fontWeight="bold"; 
           document.getElementById("leadGenAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }  else if(action=="leadDashboard") //Account Operations
         {
//             alert(action)
          document.getElementById("leadGenDisplay").style.display="block";    
           document.getElementById("leadDashboard").style.color="#FFFF00"; 
           document.getElementById("leadDashboard").style.fontWeight="bold"; 
           document.getElementById("leadGenAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
          else if(action=="getContactUs") //Account Operations
         {
//             alert(action)
          document.getElementById("webSiteDisplay").style.display="block";    
           document.getElementById("getContactUs").style.color="#FFFF00"; 
           document.getElementById("getContactUs").style.fontWeight="bold"; 
           document.getElementById("webSiteAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
          else if(action=="getResourceDepot") //Account Operations
         {
//             alert(action)
          document.getElementById("webSiteDisplay").style.display="block";    
           document.getElementById("getResourceDepot").style.color="#FFFF00"; 
           document.getElementById("getResourceDepot").style.fontWeight="bold"; 
           document.getElementById("webSiteAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
          else if(action=="getQuarterlyMeet") //Account Operations
         {
//             alert(action)
          document.getElementById("webSiteDisplay").style.display="block";    
           document.getElementById("getQuarterlyMeet").style.color="#FFFF00"; 
           document.getElementById("getQuarterlyMeet").style.fontWeight="bold"; 
           document.getElementById("webSiteAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
         
//         else {
//            document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("accountsListAll").style.color="white"; 
//            document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("account").style.color="white";
//           document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("activityManager").style.color="white";
//            document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("accountAssign").style.color="white";
//           
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
        document.getElementById("servicesAdmin").style.background = "url(/Hubble/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("leadGenAdmin").style.background = "url(/Hubble/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("webSiteAdmin").style.background = "url(/Hubble/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
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
        document.getElementById("leadGenAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("webSiteAdmin").style.background = "url(/Hubble/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
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
        document.getElementById("leadGenAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("webSiteAdmin").style.background = "url(/Hubble/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
    });
     $("#iconToggleLeadGen").click(function() {
        if(document.getElementById("leadGenDisplay").style.display == 'block')
        {
            document.getElementById("leadGenAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        }
        else
        {
            document.getElementById("leadGenAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
        }
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("webSiteAdmin").style.background = "url(/Hubble/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
    });
    $("#iconTogglewebSite").click(function() {
        if(document.getElementById("webSiteDisplay").style.display == 'block')
        {
            document.getElementById("webSiteAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        }
        else
        {
            document.getElementById("webSiteAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
        }
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("leadGenAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
});
});
/***********************************************************************************************************
 The following function provides managing of background-image while toggling for the specified category End
 ***********************************************************************************************************/


