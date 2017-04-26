/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/****************************************************************************************
 File :menuHr.js -Left Menu Main Menu And Sub Mneu Links For Hr Role.
 *****************************************************************************************/


   /********************************************
    *My   -> Tasks
    *     -> Time Sheets
    *     -> Apply Leave
    *     -> Leave Approvals
   ******************************************/
  // My Menu Start

 $  ( function( ) {
        var action=document.getElementById("action").innerHTML
     if(action=="getTasks") //Tasks
         {
             
          document.getElementById("myDisplay").style.display="block";    
           document.getElementById("getTasks").style.color="#FFFF00"; 
             document.getElementById("getTasks").style.fontWeight="bold";
             document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

//           document.getElementById("iconToggleMy").css="#cssmenu > ul > li.has-sub.active > a span";  
         }
     else if(action=="newtimeSheet") //Time Sheets
         {
             
          document.getElementById("myDisplay").style.display="block";    
           document.getElementById("newtimeSheet").style.color="#FFFF00"; 
           document.getElementById("newtimeSheet").style.fontWeight="bold";
           document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }  
          else if(action=="leaveRequestList") //Apply Leave
         {
             
          document.getElementById("myDisplay").style.display="block";    
           document.getElementById("leaveRequestList").style.color="#FFFF00"; 
           document.getElementById("leaveRequestList").style.fontWeight="bold"; 
           document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }
          else if(action=="leaveApprovalList") //Leave Approvals
         {
           
          document.getElementById("myDisplay").style.display="block";    
           document.getElementById("leaveApprovalList").style.color="#FFFF00"; 
           document.getElementById("leaveApprovalList").style.fontWeight="bold"; 
           document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
         // My Menu End
         
         
   /********************************************
    *Team -> Hierarchy
   ******************************************/
   
   // Team Menu Start
         else if(action=="myGDCTeamTree") //Hierarchy
         {
            
          document.getElementById("teamDisplay").style.display="block";    
           document.getElementById("myGDCTeamTree").style.color="#FFFF00"; 
           document.getElementById("myGDCTeamTree").style.fontWeight="bold"; 
            document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
         // Team Menu End
    
    /********************************************
    *Services -> Reports
    *         -> VenusReport
    *         -> VenusReport (Emp Base)
    *         -> Leave Reports
    *         -> Employee Search
    *         -> Exam Result
    *         -> Generate Keys
    *         -> Key List
    *         -> Mail Service
   ******************************************/
       // Services Menu Start
          else if(action=="allReports") //Reports
         {
             
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("allReports").style.color="#FFFF00"; 
           document.getElementById("allReports").style.fontWeight="bold";
             document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
          else if(action=="mycopreport" ) //VenusReport
         {
            
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("mycopreport").style.color="#FFFF00"; 
           document.getElementById("mycopreport").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
          else if(action=="venusReport") //VenusReport (Emp Base)
         {
            
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("venusReport").style.color="#FFFF00"; 
           document.getElementById("venusReport").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
         else if(action=="leavereport") //Leave Reports
         {
            
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("leavereport").style.color="#FFFF00"; 
           document.getElementById("leavereport").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
         else if(action=="empSearchAll") //Employee Search
         {
             
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("empSearchAll").style.color="#FFFF00"; 
           document.getElementById("empSearchAll").style.fontWeight="bold";
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
          else if(action=="getExamResultsList") //Exam Result
         {
             
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("getExamResultsList").style.color="#FFFF00"; 
           document.getElementById("getExamResultsList").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
          else if(action=="createKeys") //Generate Keys
         {
            
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("createKeys").style.color="#FFFF00"; 
           document.getElementById("createKeys").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
          else if(action=="listKeys") //Key List
         {
             
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("listKeys").style.color="#FFFF00"; 
           document.getElementById("listKeys").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
         
//         else {
//           document.getElementById("myDisplay").style.display="none";   
//           document.getElementById("getTasks").style.color="white";  
//            document.getElementById("myDisplay").style.display="none";    
//           document.getElementById("newtimeSheet").style.color="white";
//            document.getElementById("myDisplay").style.display="none";    
//           document.getElementById("leaveRequestList").style.color="white";
//            document.getElementById("myDisplay").style.display="none";    
//           document.getElementById("leaveApprovalList").style.color="white";
//           
//           document.getElementById("teamDisplay").style.display="none";    
//           document.getElementById("myGDCTeamTree").style.color="white"; 
//          
//            document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("allReports").style.color="white"; 
//            document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("mycopreport").style.color="white";
//           document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("venusReport").style.color="white";
//            document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("leavereport").style.color="white";
//           document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("empSearchAll").style.color="white"; 
//           document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("getExamResultsList").style.color="white";
//             document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("createKeys").style.color="white";
//             document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("listKeys").style.color="white"; 
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
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
    });
});

/***********************************************************************************************************
 The following function provides managing of background-image while toggling for the specified category End
 ***********************************************************************************************************/