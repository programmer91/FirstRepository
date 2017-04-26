/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/****************************************************************************************
 File :menuCustomer.js -Left Menu Main Menu And Sub Mneu Links For Customer Role.
 *****************************************************************************************/


   /********************************************
    *My   -> Create Task
    *     -> Tasks
    *     -> Time Sheets
   ******************************************/
  // My Menu Start
 $  ( function( ) {
        var action=document.getElementById("action").innerHTML
     if(action=="newCreateTask") //Create Task
         {
             
          document.getElementById("myDisplay").style.display="block";    
           document.getElementById("newCreateTask").style.color="#FFFF00"; 
             document.getElementById("newCreateTask").style.fontWeight="bold";
             document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

//           document.getElementById("iconToggleMy").css="#cssmenu > ul > li.has-sub.active > a span";  
         }
     else if(action=="getTasks") //Tasks
         {
             
          document.getElementById("myDisplay").style.display="block";    
           document.getElementById("getTasks").style.color="#FFFF00"; 
           document.getElementById("getTasks").style.fontWeight="bold";
           document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }  
          else if(action=="newtimeSheet") //Time Sheets
         {
             
          document.getElementById("myDisplay").style.display="block";    
           document.getElementById("newtimeSheet").style.color="#FFFF00"; 
           document.getElementById("newtimeSheet").style.fontWeight="bold"; 
           document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }
         // My Menu End
 
     
 /********************************************
    *Team -> Tasks
    *     -> Time Sheets
   ******************************************/
   
   // Team Menu Start
         else if(action=="getTeamTasks") //Tasks
         {
            
          document.getElementById("teamDisplay").style.display="block";    
           document.getElementById("getTeamTasks").style.color="#FFFF00"; 
           document.getElementById("getTeamTasks").style.fontWeight="bold"; 
            document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
          else if(action=="newCustomerTeamTimeSheets") //Time Sheets
         {
            
          document.getElementById("teamDisplay").style.display="block";    
           document.getElementById("newCustomerTeamTimeSheets").style.color="#FFFF00"; 
           document.getElementById("newCustomerTeamTimeSheets").style.fontWeight="bold"; 
            document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
          // Team Menu End
     
     /********************************************
    *Services -> Change My Pwd
   ******************************************/
       // Services Menu Start

          else if(action=="newempTimeSheets") //Emp TimeSheets
         {
             
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("newempTimeSheets").style.color="#FFFF00"; 
           document.getElementById("newempTimeSheets").style.fontWeight="bold";
             document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
          else if(action=="resetCustPassword" ) //Change My Pwd 
         {
            
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("resetCustPassword").style.color="#FFFF00"; 
           document.getElementById("resetCustPassword").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
         else if(action=="myCustOpportunities" ) //Change My Pwd 
         {
            
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("myCustOpportunities").style.color="#FFFF00"; 
           document.getElementById("myCustOpportunities").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         } 
          
//         else {
//                document.getElementById("myDisplay").style.display="none";    
//             document.getElementById("newCreateTask").style.color="white"; 
//               document.getElementById("myDisplay").style.display="none";    
//           document.getElementById("getTasks").style.color="white"; 
//              document.getElementById("myDisplay").style.display="none";    
//           document.getElementById("newtimeSheet").style.color="white"; 
//            document.getElementById("teamDisplay").style.display="none";    
//           document.getElementById("getTeamTasks").style.color="white"; 
//            document.getElementById("teamDisplay").style.display="none";    
//           document.getElementById("newCustomerTeamTimeSheets").style.color="white"; 
//            document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("newempTimeSheets").style.color="white"; 
//           document.getElementById("servicesDisplay").style.display="none";    
//           document.getElementById("resetCustPassword").style.color="white"; 
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
 var customerRole=document.getElementById("customerRole").value
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
       if(customerRole !="1" && customerRole !="8")
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
         if(customerRole !="1" && customerRole !="8")
              {
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
    });
});

/***********************************************************************************************************
 The following function provides managing of background-image while toggling for the specified category End
 ***********************************************************************************************************/