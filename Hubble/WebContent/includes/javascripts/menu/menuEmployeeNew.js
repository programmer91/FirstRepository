 /* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$  ( function( ) {
    var action=document.getElementById("actionForLeftMenu").innerHTML
        var searchflag=document.getElementById("searchFlagForLeftMenu").innerHTML
    var menuId =     sessionStorage.getItem("menuId");
    var currentId = sessionStorage.getItem("currentId");
    var    parentId=     sessionStorage.getItem("parentId");
   // alert(menuId+" "+currentId+ " "+ parentId)
   
    if(menuId!=null && currentId!=null && parentId!=null ){
        if(document.getElementById(parentId)!=null)
            {
                   document.getElementById(parentId).style.display="block";    
            }
//        document.getElementById(parentId).style.display="block";  
if(document.getElementById(currentId)!=null)
            {
        document.getElementById(currentId).style.color="#FFFF00"; 
        document.getElementById(currentId).style.fontWeight="bold";
            }
            if(document.getElementById(menuId)!=null)
            {
        document.getElementById(menuId).style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
            }

    }




    if(action=="getTasks") //Add Account
    {
    
         if(menuId!=null && currentId!=null && parentId!=null ){
             if(document.getElementById(parentId)!=null)
                 {
                      document.getElementById(parentId).style.display="none";   
                 }
    if(document.getElementById(currentId)!=null)
                 {
            document.getElementById(currentId).style.color="White"; 
            document.getElementById(currentId).style.fontWeight="normal";
                 }
                     if(document.getElementById(menuId)!=null)
                 {
            document.getElementById(menuId).style.background = "";
                 }
        }
        document.getElementById("myDisplay").style.display="block";    
        document.getElementById("myTasks").style.color="#FFFF00"; 
        document.getElementById("myTasks").style.fontWeight="bold";
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";

        sessionStorage.setItem("menuId","myAdmin");
        sessionStorage.setItem("currentId","myTasks");
        sessionStorage.setItem("parentId","myDisplay");
         
    }
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



function leftMenuToggleId(el,s)
{
//    alert("el is---->"+el);
    document.getElementById(s).style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
  //  alert(s);
    var currentId = $(el).attr('id');
    document.location.hash = currentId;
 //   alert(currentId);
    var pp = $(el).parent().parent().attr('id');
 //   alert("parent is "+pp);
    document.getElementById(currentId).style.color="#FFFF00"; 
    document.getElementById(currentId).style.fontWeight="bold";
    document.getElementById(pp).style.display="block";
    sessionStorage.setItem("menuId",s);
    sessionStorage.setItem("currentId",currentId);
    sessionStorage.setItem("parentId",pp);
//sessionStorage.clear();
}



$  ( function( ) {
var EmployeeTypeId=document.getElementById("EmployeeTypeId").value
//alert(EmployeeTypeId);

if(EmployeeTypeId == 'Contractor')
    {
       // alert("Contractor");
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
         
       document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
       
    });
    
    }
    else
        {
         
        
  var teamLead=document.getElementById("teamLead").value
  
         //alert(teamLead)
         var usrManger=document.getElementById("userManager").value
         // alert(usrManger)
         var creTeam=document.getElementById("isCRETeam").value
         //My
         
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
        if(creTeam=="1")
              {
             document.getElementById("mileAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";  
             document.getElementById("mcertAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
        
    });
    //Team
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
        if(creTeam=="1")
              {
             document.getElementById("mileAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";      
             document.getElementById("mcertAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
    });
    //Services
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
       if(creTeam=="1")
              {
             document.getElementById("mileAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";      
             document.getElementById("mcertAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
    });
    //Mile
     $("#iconToggleMile").click(function() {
         if(document.getElementById("mileDisplay").style.display == 'block')
        {
            document.getElementById("mileAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        }
        else
        {
            document.getElementById("mileAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
        }
         document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
         if(teamLead=="1" || usrManger=="1")
              {
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
              document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
         document.getElementById("mcertAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
     });
     
     $("#iconToggleMcert").click(function() {
         if(document.getElementById("mcertDisplay").style.display == 'block')
        {
            document.getElementById("mcertAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        }
        else
        {
            document.getElementById("mcertAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
        }
         document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
         if(teamLead=="1" || usrManger=="1")
              {
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
              if(creTeam=="1")
              {
             document.getElementById("mileAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";      
              }
              
         
     });
     
     $("#iconToggleMcert").click(function() {
         if(document.getElementById("mcertDisplay").style.display == 'block')
        {
            document.getElementById("mcertAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        }
        else
        {
            document.getElementById("mcertAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
        }
         document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
         if(teamLead=="1" || usrManger=="1")
              {
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
              if(creTeam=="1")
              {
             document.getElementById("mileAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";      
              }
               document.getElementById("dasboardAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
         
     });
     
     
     
     $("#iconToggleDashboards").click(function() {  
        if(document.getElementById("dashboardDisplay").style.display == 'block')
        {
            document.getElementById("dasboardAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        }
        else
        {
            document.getElementById("dasboardAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
        }
       // document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/icon_minus.png ) 96% center no-repeat ";
           document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
          if(teamLead=="1" || usrManger=="1")
              {
             document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";      
              }
       
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
        if(creTeam=="1")
              {
             document.getElementById("mileAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";   
               document.getElementById("mcertAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_plus.png ) 96% center no-repeat ";
              }
            
        
    });

     
        }
});
