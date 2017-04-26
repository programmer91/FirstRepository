/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/*****************************************************************************
 File :MenuAdmin.js -Left Menu Main Menu And Sub Mneu Links For Admin Role.
 
 *****************************************************************************/
  /******************************************
   *My -> Team
   *   -> Accounts
   *   -> Time Sheets
   *   -> Calendar
   ******************************************/
  // My Menu Start
$  ( function( ) {
    var action=document.getElementById("action").innerHTML
    
     if(action=="empSearchMyTeam") //Team
    {
            
        document.getElementById("myDisplay").style.display="block";    
        document.getElementById("empSearchTeam").style.color="#FFFF00"; 
        document.getElementById("empSearchTeam").style.fontWeight="bold";
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }  
   else if(action=="accountsListMy") // Accounts
    {
            
        document.getElementById("myDisplay").style.display="block";    
        document.getElementById("accountList").style.color="#FFFF00"; 
        document.getElementById("accountList").style.fontWeight="bold";
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
    else if(action=="newtimeSheet") //Time Sheets
    {
            
        document.getElementById("myDisplay").style.display="block";    
        document.getElementById("newtimeSheet").style.color="#FFFF00"; 
        document.getElementById("newtimeSheet").style.fontWeight="bold"; 
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
    else if(action=="calendar") //Calendar
    {
            
        document.getElementById("myDisplay").style.display="block";    
        document.getElementById("calendarId").style.color="#FFFF00"; 
        document.getElementById("calendarId").style.fontWeight="bold"; 
        document.getElementById("myAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } 
     // My Menu End
     
 /********************************************
    *Team -> Accounts
    *     -> Assign Accounts
   ******************************************/
   
   // Team Menu Start
    
    else if(action=="accountsListMyTeam") //Accounts
    {
             
        document.getElementById("teamDisplay").style.display="block";    
        document.getElementById("teamAccounts").style.color="#FFFF00"; 
        document.getElementById("teamAccounts").style.fontWeight="bold"; 
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } 
    else if(action=="reAssign") //Assign Accounts
    {
            
        document.getElementById("teamDisplay").style.display="block";    
        document.getElementById("teamreAssign").style.color="#FFFF00"; 
        document.getElementById("teamreAssign").style.fontWeight="bold"; 
        document.getElementById("teamAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } 
     // Team Menu End
    
    /********************************************
    *Services -> Add Account
    *         -> All Accounts
    *         -> Accounts Search
    *         -> Merge Accounts
    *         -> Greensheets
    *         -> Reset My Pwd
    *         -> Reset User Pwd
    *         -> Reset Cust Pwd
    *         -> Emp Search
    *         -> Emp Report
    *         -> Exams List
    *         -> Mail Service
    *         -> Contacts Summary
    *         -> Executive Dashboard
   ******************************************/
       // Services Menu Start

    else if(action=="account") //Add Account
    {
            
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("servicesaddAccount").style.color="#FFFF00"; 
        document.getElementById("servicesaddAccount").style.fontWeight="bold"; 
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } 
    
    else if(action=="accountsListAll" ) //All Accounts
    {
             
//        alert("hi")
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("servicesallAccounts").style.color="#FFFF00"; 
        document.getElementById("servicesallAccounts").style.fontWeight="bold"; 
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } 
    else if(action=="accountSearch") //Accounts Search
    {
             
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("servicesaccountsSearch").style.color="#FFFF00"; 
        document.getElementById("servicesaccountsSearch").style.fontWeight="bold"; 
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
    else if(action=="mergeAccount") //Merge Accounts
    {
             
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("servicesmergeAccounts").style.color="#FFFF00"; 
        document.getElementById("servicesmergeAccounts").style.fontWeight="bold";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    } 
    else if(action=="greenSheet") //Greensheets
    {
            
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("servicesGreensheets").style.color="#FFFF00"; 
        document.getElementById("servicesGreensheets").style.fontWeight="bold";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
    else if(action=="resetPassword") //Reset My Pwd
    {
             
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("servicesResetMyPwd").style.color="#FFFF00"; 
        document.getElementById("servicesResetMyPwd").style.fontWeight="bold"; 
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
    else if(action=="changePassword") //Reset User Pwd
    {
             
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("servicesResetUserPwd").style.color="#FFFF00"; 
        document.getElementById("servicesResetUserPwd").style.fontWeight="bold";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
    else if(action=="changeCustPassword") //Reset Cust Pwd
    {
            
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("servicesResetCustPwd").style.color="#FFFF00"; 
        document.getElementById("servicesResetCustPwd").style.fontWeight="bold";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
    else if(action=="empSearchAll") //Emp Search
    {
             
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("servicesEmpSearch").style.color="#FFFF00"; 
        document.getElementById("servicesEmpSearch").style.fontWeight="bold"; 
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
    else if(action=="employeeReports") //Emp Report
    {
       
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("servicesEmpReport").style.color="#FFFF00"; 
        document.getElementById("servicesEmpReport").style.fontWeight="bold"; 
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
    else if(action=="examsList") //Exams List
    {
            
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("servicesExamsList").style.color="#FFFF00"; 
        document.getElementById("servicesExamsList").style.fontWeight="bold"; 
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
    else if(action=="accountsContacts") //Contacts Summary
    {
        
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("servicesContactsSummary").style.color="#FFFF00"; 
        document.getElementById("servicesContactsSummary").style.fontWeight="bold";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
    else if(action=="getExecutiveDashBoard") //Executive Dashboard
    {
        
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("servicesExecutiveDashboard").style.color="#FFFF00"; 
        document.getElementById("servicesExecutiveDashboard").style.fontWeight="bold";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
     else if(action=="excelUpload") //Executive Dashboard
    {
        
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("servicesExcelUpload").style.color="#FFFF00"; 
        document.getElementById("servicesExcelUpload").style.fontWeight="bold";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }
      else if(action=="getNewsLetters") //Executive Dashboard
    {
        
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("getNewsLetters").style.color="#FFFF00"; 
        document.getElementById("getNewsLetters").style.fontWeight="bold";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }  else if(action=="getBdmAssociates") //Executive Dashboard
    {
        
        document.getElementById("servicesDisplay").style.display="block";    
        document.getElementById("getBdmAssociates").style.color="#FFFF00"; 
        document.getElementById("getBdmAssociates").style.fontWeight="bold";
        document.getElementById("servicesAdmin").style.background = "url("+CONTENXT_PATH+"/includes/images/menu/icon_minus.png ) 96% center no-repeat ";
    }

    
         // Services Menu End

//    else {
//        document.getElementById("myDisplay").style.display="none";   
//        document.getElementById("accountList").style.color="white";  
//        document.getElementById("myDisplay").style.display="none";    
//        document.getElementById("empSearchTeam").style.color="white";
//        document.getElementById("myDisplay").style.display="none";    
//        document.getElementById("newtimeSheet").style.color="white";
//        document.getElementById("myDisplay").style.display="none";    
//        document.getElementById("calendarId").style.color="white";
//        document.getElementById("teamDisplay").style.display="none";    
//        document.getElementById("teamAccounts").style.color="white"; 
//        document.getElementById("teamDisplay").style.display="none";    
//        document.getElementById("teamreAssign").style.color="white"; 
//        document.getElementById("servicesDisplay").style.display="none";    
//        document.getElementById("servicesaddAccount").style.color="white"; 
//        document.getElementById("servicesDisplay").style.display="none";    
//        document.getElementById("servicesallAccounts").style.color="white";
//        document.getElementById("servicesDisplay").style.display="none";    
//        document.getElementById("servicesaccountsSearch").style.color="white";
//        document.getElementById("servicesDisplay").style.display="none";    
//        document.getElementById("servicesmergeAccounts").style.color="white";
//        document.getElementById("servicesDisplay").style.display="none";    
//        document.getElementById("servicesGreensheets").style.color="white"; 
//        document.getElementById("servicesDisplay").style.display="none";    
//        document.getElementById("servicesResetMyPwd").style.color="white";
//        document.getElementById("servicesDisplay").style.display="none";    
//        document.getElementById("servicesResetUserPwd").style.color="white";
//        document.getElementById("servicesDisplay").style.display="none";    
//        document.getElementById("servicesResetCustPwd").style.color="white"; 
//        document.getElementById("servicesDisplay").style.display="none";    
//        document.getElementById("servicesEmpSearch").style.color="white"; 
//        document.getElementById("servicesDisplay").style.display="none";    
//        document.getElementById("servicesEmpReport").style.color="white"; 
//        document.getElementById("servicesDisplay").style.display="none";    
//        document.getElementById("servicesExamsList").style.color="white";
//        document.getElementById("servicesDisplay").style.display="none";    
//        document.getElementById("servicesContactsSummary").style.color="white"; 
//        document.getElementById("servicesDisplay").style.display="none";    
//        document.getElementById("servicesExecutiveDashboard").style.color="white";
//    }
       
       
/**********************************************************************
 Purpose :The below function provides toggling functionality.
 ***********************************************************************/
       
       
    $( document ).ready(function() {
        $('#cssmenu > ul > li > a').click(function() {
            $('#cssmenu li').removeClass('active');
   
            $(this).closest('li').addClass('active');	
            var checkElement = $(this).next();
            if((checkElement.is('ul')) && (checkElement.is(':visible'))) {
                //   alert("slide up click-->"+checkElement.is(':visible'));
                $(this).closest('li').removeClass('active');
                checkElement.slideUp('normal');
            }
            if((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
                //  alert("click");
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