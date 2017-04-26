/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/***********************************************************************************
 File :menuPre-Sales.js -Left Menu Main Menu And Sub Mneu Links For Pre-Sales Role.
 ***********************************************************************************/
/******************************************
   *My -> Requirement List
   *   -> Tech Reviews
   ******************************************/
  // My Menu Start
 $  ( function( ) {
        var action=document.getElementById("action").innerHTML
     if(action=="requirementListForPresalesMy") //Requirement List
         {
//             alert(action)
          document.getElementById("myDisplay").style.display="block";    
           document.getElementById("requirementListForPresalesMy").style.color="#FFFF00"; 
             document.getElementById("requirementListForPresalesMy").style.fontWeight="bold";
//           document.getElementById("iconToggleMy").css="#cssmenu > ul > li.has-sub.active > a span";  
document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }
     else if(action=="consultantTechReviews") //Tech Reviews
         {
//             alert(action)
          document.getElementById("myDisplay").style.display="block";    
           document.getElementById("techreviews").style.color="#FFFF00"; 
           document.getElementById("techreviews").style.fontWeight="bold";
           document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }  
          // My Menu End
          
   /********************************************
    *Services -> DashBoard
    *         -> Available Employee List
   ******************************************/
       // Services Menu Start
          else if(action=="myOpportunities") //DashBoard
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("servicesdashboard").style.color="#FFFF00"; 
           document.getElementById("servicesdashboard").style.fontWeight="bold";
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }  
         else if(action=="pmoDashBoard") //Available Employee List
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("servicespmodashboard").style.color="#FFFF00"; 
           document.getElementById("servicespmodashboard").style.fontWeight="bold";
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }  
else if(action=="clientReqEngagementPreSalesSearch") //Available Employee List
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("clientReqEngagementPreSalesSearch").style.color="#FFFF00"; 
           document.getElementById("clientReqEngagementPreSalesSearch").style.fontWeight="bold";
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
         }
        // Services Menu End
        
//         else {
//           document.getElementById("myDisplay").style.display="none";   
//           document.getElementById("requirementlist").style.color="white";  
//            document.getElementById("myDisplay").style.display="none";    
//           document.getElementById("techreviews").style.color="white";
//             document.getElementById("servicesDisplay").style.display="none";    
//            document.getElementById("servicesdashboard").style.color="white";   
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
