/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/***************************************************************************
 File :menuSales.js -Left Menu Main Menu And Sub Mneu Links For Sales Role.
 
 ***************************************************************************/
  /******************************************
   *My -> Accounts
   *   -> UntouchedAccounts
   *   -> Activities
   *   -> Greensheets
   *   -> Calendar
   ******************************************/
  // My Menu Start

$  ( function( ) {
        var action=document.getElementById("action").innerHTML
        var teamValue=document.getElementById("greenSheets").innerHTML
        var usrManger=document.getElementById("userManager").value
         //alert(document.getElementById("greenSheets").innerHTML)
     if(action=="accountsListMy") //Accounts
         {
//             alert(action)
          document.getElementById("myDisplay").style.display="block";    
           document.getElementById("accountsListMy").style.color="#FFFF00"; 
             document.getElementById("accountsListMy").style.fontWeight="bold";
//           document.getElementById("iconToggleMy").css="#cssmenu > ul > li.has-sub.active > a span"; 
document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
     else if(action=="untouchedAccounts") //UntouchedAccounts
         {
//             alert(action)
          document.getElementById("myDisplay").style.display="block";    
           document.getElementById("untouchedAccounts").style.color="#FFFF00"; 
           document.getElementById("untouchedAccounts").style.fontWeight="bold";
           document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }  
          else if(action=="myActivitiesInfo") //Activities
         {
//             alert(action)
          document.getElementById("myDisplay").style.display="block";    
           document.getElementById("myActivitiesInfo").style.color="#FFFF00"; 
           document.getElementById("myActivitiesInfo").style.fontWeight="bold"; 
           document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
          else if(teamValue=="notAvailable") //Greensheets
         {
//             alert(action)
          document.getElementById("myDisplay").style.display="block";    
           document.getElementById("greenSheet").style.color="#FFFF00"; 
           document.getElementById("greenSheet").style.fontWeight="bold"; 
           document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
          else if(action=="getCalendar") //Calendar
         {
//             alert(action)
          document.getElementById("myDisplay").style.display="block";    
           document.getElementById("getCalendar").style.color="#FFFF00"; 
           document.getElementById("getCalendar").style.fontWeight="bold"; 
           document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 

         else if(action=="myOpportunities") //Oppurtunities
         {
//             alert(action)
          document.getElementById("myDisplay").style.display="block";    
           document.getElementById("myOpportunities").style.color="#FFFF00"; 
           document.getElementById("myOpportunities").style.fontWeight="bold"; 
           document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
                  // My Menu End
                  
   /********************************************
    *Team -> Account Operations
    *     -> Dashboard
    *     -> Accounts
    *     -> UntouchedAccounts
    *     -> Activities
    *     -> GreenSheets
    *     -> Calendar
    *     -> Hierarchy
   ******************************************/
   
   // Team Menu Start
          else if(action=="accountAssign") //Account Operations
         {
//             alert(action)
          document.getElementById("teamDisplay").style.display="block";    
           document.getElementById("accountAssign").style.color="#FFFF00"; 
           document.getElementById("accountAssign").style.fontWeight="bold"; 
           document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
         else if(action=="dashBoard") //Dashboard
         {
             if(usrManger!="0")
             {
//                  alert(action)
          document.getElementById("teamDisplay").style.display="block";
           document.getElementById("dashBoard").style.color="#FFFF00"; 
           document.getElementById("dashBoard").style.fontWeight="bold";
           document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
             }
             else
                 {
//                     alert(action)
                     document.getElementById("myDisplay").style.display="block";    
           document.getElementById("dashBoard").style.color="#FFFF00"; 
           document.getElementById("dashBoard").style.fontWeight="bold"; 
           document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
                 }
         } 
          else if(action=="accountsListMyTeam") //Accounts
         {
//             alert(action)
          document.getElementById("teamDisplay").style.display="block";    
           document.getElementById("accountsListMyTeam").style.color="#FFFF00"; 
           document.getElementById("accountsListMyTeam").style.fontWeight="bold"; 
           document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
          else if(action=="untouchedTeamAccounts") //UntouchedAccounts
         {
//             alert(action)
          document.getElementById("teamDisplay").style.display="block";    
           document.getElementById("untouchedTeamAccounts").style.color="#FFFF00"; 
           document.getElementById("untouchedTeamAccounts").style.fontWeight="bold"; 
           document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
          else if(action=="teamActivitiesInfo") //Activities
         {
//             alert(action)
          document.getElementById("teamDisplay").style.display="block";    
           document.getElementById("teamActivitiesInfo").style.color="#FFFF00"; 
           document.getElementById("teamActivitiesInfo").style.fontWeight="bold"; 
           document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
           else if(teamValue=="available") //GreenSheets
         {
//             alert(action)
          document.getElementById("teamDisplay").style.display="block";    
           document.getElementById("greenSheetTeam").style.color="#FFFF00"; 
           document.getElementById("greenSheetTeam").style.fontWeight="bold"; 
           document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
           else if(action=="accessTeamCalendar") //Calendar
         {
//             alert(action)
          document.getElementById("teamDisplay").style.display="block";    
           document.getElementById("accessTeamCalendar").style.color="#FFFF00"; 
           document.getElementById("accessTeamCalendar").style.fontWeight="bold"; 
           document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
           else if(action=="myTeamTree") //Hierarchy
         {
//             alert(action)
          document.getElementById("teamDisplay").style.display="block";    
           document.getElementById("myTeamTree").style.color="#FFFF00"; 
           document.getElementById("myTeamTree").style.fontWeight="bold";
           document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
          // Team Menu End
    
    /********************************************
    *Services -> Access Calendar
    *         -> Accounts Search
    *         -> New Dashboard
    *         -> Available Employee List
   ******************************************/
       // Services Menu Start
          else if(action=="accessCalendar") //Access Calendar
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("accessCalendar").style.color="#FFFF00"; 
           document.getElementById("accessCalendar").style.fontWeight="bold";
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
          else if(action=="accountSearch" ) //Accounts Search
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("accountSearch").style.color="#FFFF00"; 
           document.getElementById("accountSearch").style.fontWeight="bold"; 
                      document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
          else if(action=="bdmDashBoard") //New Dashboard
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("bdmDashBoard").style.color="#FFFF00"; 
           document.getElementById("bdmDashBoard").style.fontWeight="bold";
                      document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }
          else if(action=="pmoDashBoard") //Available Employee List
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("availableEmployeeList").style.color="#FFFF00"; 
           document.getElementById("availableEmployeeList").style.fontWeight="bold";
                      document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }
         
         else if(action=="contactSearch") //Available Employee List
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("contactSearch").style.color="#FFFF00"; 
           document.getElementById("contactSearch").style.fontWeight="bold";
                      document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }
         else if(action=="campaignSearchAction") //Available Employee List
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("campaignSearchAction").style.color="#FFFF00"; 
           document.getElementById("campaignSearchAction").style.fontWeight="bold";
                      document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }
  else if(action=="clientReqEngagementSearch") //Available Employee List
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("clientReqEngagementSearch").style.color="#FFFF00"; 
           document.getElementById("clientReqEngagementSearch").style.fontWeight="bold";
                      document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }
          // Services Menu End
          
          
    /********************************************
    *Requirement -> List
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
         // Requirement Menu End
         
         
//         else {
//           document.getElementById("myDisplay").style.display="none";    
//           document.getElementById("accountsListMy").style.color="white"; 
//    document.getElementById("myDisplay").style.display="none";    
//           document.getElementById("untouchedAccounts").style.color="white";    
// document.getElementById("myDisplay").style.display="none";    
//           document.getElementById("myActivitiesInfo").style.color="white";   
//  document.getElementById("myDisplay").style.display="none";    
//           document.getElementById("greenSheet").style.color="white";   
//document.getElementById("myDisplay").style.display="none";    
//           document.getElementById("getCalendar").style.color="white";
//             document.getElementById("myDisplay").style.display="none";    
//           document.getElementById("myOpportunities").style.color="white"; 
// document.getElementById("teamDisplay").style.display="none";    
//           document.getElementById("accountAssign").style.color="white"; 
// document.getElementById("teamDisplay").style.display="none";    
//           document.getElementById("dashBoard").style.color="white"; 
// document.getElementById("teamDisplay").style.display="none";    
//           document.getElementById("accountsListMyTeam").style.color="white"; 
// document.getElementById("teamDisplay").style.display="none";    
//           document.getElementById("untouchedTeamAccounts").style.color="white"; 
// document.getElementById("teamDisplay").style.display="none";    
//           document.getElementById("teamActivitiesInfo").style.color="white"; 
//document.getElementById("teamDisplay").style.display="none";    
//           document.getElementById("greenSheetTeam").style.color="white"; 
//document.getElementById("teamDisplay").style.display="none";    
//           document.getElementById("accessTeamCalendar").style.color="white"; 
// document.getElementById("teamDisplay").style.display="none";    
//           document.getElementById("myTeamTree").style.color="white"; 
//  document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("accessCalendar").style.color="white"; 
// document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("accountSearch").style.color="white";
// document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("bdmDashBoard").style.color="white"; 
//  document.getElementById("requirementDisplay").style.display="none";    
//           document.getElementById("requirementList").style.color="white"; 
//}
 
 
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
var usrTeamLead=document.getElementById("userTeamLead").value
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
       if(usrManger!="0" || usrTeamLead!="0")
              {
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("requirementAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
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
        document.getElementById("requirementAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
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
        if(usrManger!="0" || usrTeamLead!="0")
              {
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("requirementAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
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
        if(usrManger!="0" || usrTeamLead!="0")
              {
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        
        
    });
});

/***********************************************************************************************************
 The following function provides managing of background-image while toggling for the specified category End
 ***********************************************************************************************************/

