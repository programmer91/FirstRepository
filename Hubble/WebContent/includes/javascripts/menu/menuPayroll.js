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
//   var menuId =     sessionStorage.getItem("menuId");
//var currentId = sessionStorage.getItem("currentId");
//var    parentId=     sessionStorage.getItem("parentId");
//alert(menuId+" "+currentId+ " "+ parentId)
//if(menuId!=null && currentId!=null && parentId!=null ){
//document.getElementById(parentId).style.display="block";    
//          document.getElementById(currentId).style.color="#FFFF00"; 
//          document.getElementById(currentId).style.fontWeight="bold";
//
//           document.getElementById(menuId).style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
// }
////

     if(action=="getAllEmployeePayroll") //Add Account
         {
//             if(menuId!=null && currentId!=null && parentId!=null ){
//document.getElementById(parentId).style.display="none";    
//          document.getElementById(currentId).style.color="White"; 
//          document.getElementById(currentId).style.fontWeight="normal";
//
//           document.getElementById(menuId).style.background = "";
// }
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("servicesgetAllEmployeePayroll").style.color="#FFFF00"; 
             document.getElementById("servicesgetAllEmployeePayroll").style.fontWeight="bold";
//           document.getElementById("iconToggleMy").css="#cssmenu > ul > li.has-sub.active > a span";  
document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

//sessionStorage.setItem("menuId","servicesAdmin");
//sessionStorage.setItem("currentId","accountsListAll");
//sessionStorage.setItem("parentId","servicesDisplay");
         }
     else if(action=="getPayRollReports") //All Accounts
         {
             //alert(action)
         document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("servicesCampaigngetPayRollReports").style.color="#FFFF00"; 
          document.getElementById("servicesCampaigngetPayRollReports").style.fontWeight="bold";
//           currentId.style.display="block"; 
//           currentId.style.color="#FFFF00"; 
//          currentId.style.fontWeight="bold"; 
          document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }  
          else if(action=="payrollCheck") //Activity Manager
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("servicespayrollCheck").style.color="#FFFF00"; 
           document.getElementById("servicespayrollCheck").style.fontWeight="bold";
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         }
          else if(action=="getPayRollDashBoard") //Account Operations
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("servicesgetPayRollDashBoard").style.color="#FFFF00"; 
           document.getElementById("servicesgetPayRollDashBoard").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
         else if(action=="changeMyPayrollPassword") //Account Operations
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("serviceschangeMyPayrollPassword").style.color="#FFFF00"; 
           document.getElementById("serviceschangeMyPayrollPassword").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
         else if(action=="getDeductionsAndCommissions") //Account Operations
         {
//             alert(action)
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("servicesgetDeductionsAndCommissions").style.color="#FFFF00"; 
           document.getElementById("servicesgetDeductionsAndCommissions").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 
         else if(action=="tdsGeneration") //Account Operations
         {
          document.getElementById("servicesDisplay").style.display="block";    
           document.getElementById("serviceschangeTDSGeneration").style.color="#FFFF00"; 
           document.getElementById("serviceschangeTDSGeneration").style.fontWeight="bold"; 
           document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

         } 

         
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

//function leftMenuToggleId(el,s)
//{
//    
//           
////         document.getElementById(s).style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
//         
//alert(s);
//    var currentId = $(el).attr('id');
//    document.location.hash = currentId;
//    alert(currentId);
//        
////    jumma(currentId);
//var pp = $(el).parent().parent().attr('id');
//alert("parent is "+pp);
// 
////           document.getElementById(currentId).style.color="#FFFF00"; 
////          document.getElementById(currentId).style.fontWeight="bold";
////document.getElementById(pp).style.display="block";
//sessionStorage.setItem("menuId",s);
//sessionStorage.setItem("currentId",currentId);
//sessionStorage.setItem("parentId",pp);
//
//
//}
// function generateUrl1(el)
//{
//    var currentId1 = $(el).attr('id');
//    document.location.hash = currentId1;
//    alert(currentId1);
//var  s  =   $(el).closest('ul').attr('id');
//alert(s);
////    jumma(currentId);
//}


/***********************************************************************************************************
 The following function provides managing of background-image while toggling for the specified category Start
 ***********************************************************************************************************/


$  ( function( ) {       
  
  
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
       //        document.getElementById("leadGenAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
//        document.getElementById("webSiteAdmin").style.background = "url(/Hubble/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
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
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
//        document.getElementById("leadGenAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
//        document.getElementById("webSiteAdmin").style.background = "url(/Hubble/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
    });
//     $("#iconToggleLeadGen").click(function() {
//        if(document.getElementById("leadGenDisplay").style.display == 'block')
//        {
//            document.getElementById("leadGenAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
//        }
//        else
//        {
//            document.getElementById("leadGenAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
//        }
//        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
//        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
//        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
//        document.getElementById("webSiteAdmin").style.background = "url(/Hubble/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
//    });
//    $("#iconTogglewebSite").click(function() {
//        if(document.getElementById("webSiteDisplay").style.display == 'block')
//        {
//            document.getElementById("webSiteAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
//        }
//        else
//        {
//            document.getElementById("webSiteAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
//        }
//        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
//        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
//        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
//        document.getElementById("leadGenAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
//});
});
/***********************************************************************************************************
 The following function provides managing of background-image while toggling for the specified category End
 ***********************************************************************************************************/



