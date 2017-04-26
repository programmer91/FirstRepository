
// for checking the null valus

        function checkBlankFeilds(){
           var test=false;
           var temp=false;
           if(document.timeSheetSubmit.checkDate[0].checked) {
             if(document.getElementById("fillTimeSheet").value != ""){
             temp=true;
             }else{
             temp=false;
            }
             test=temp;
           } 
           if(document.timeSheetSubmit.checkDate[1].checked){     
              test=true;
             }
            return test;
         }
   
 function checkForNum(td){
 if(isNaN(td.value)) {
                alert("Sorry Enter a Valid Value between 1 and 24");	
                td.value=0;
                }else if(parseInt(td.value)<0 || parseInt(td.value)>24){
                alert("Sorry Enter a Valid Value between 1 and 24");	
                td.value=0;
                } else{
                    td.value=parseFloat(td.value).toFixed(1);
                }
}

    
 function computeProj1(td){
           // var test;
             checkForNum(td);
               if(document.frmAddTimeSheet.proj1TotalHrs.value=='0.0') {
                document.frmAddTimeSheet.proj1TotalHrs.value =Number(td.value);
                }

                //  If the Total for a Day exceeds the 24 then Reset that Current Cell to be Zero
               checkSun(td);
              if ( (sumSun()) <24)
               {
                   document.frmAddTimeSheet.totalSun.value=sumSun();
                   proj1Total();
               }
               checkMon(td);
              if ( (sumMon() ) <24)
               {
                 document.frmAddTimeSheet.totalMon.value=sumMon();
                 proj1Total();
               }              
              checkTus(td);
              if ( (sumTus() ) <24)
               {
                document.frmAddTimeSheet.totalTus.value=sumTus();
                 proj1Total();
               }  
               checkWed(td);
              if ( (sumWed() ) <24)
               {
                document.frmAddTimeSheet.totalWed.value=sumWed() ;
                proj1Total();
               }  
             checkThur(td);
              if ( (sumThur() ) <24)
               {
                 document.frmAddTimeSheet.totalThur.value=sumThur();
                 proj1Total();
               } 
              checkFri(td);
              if ( (sumFri() ) <24)
               {
                  document.frmAddTimeSheet.totalFri.value=sumFri();
                  proj1Total();
               }   
             checkSat(td);
              if ( (sumSat() ) <24)
               {
                    document.frmAddTimeSheet.totalSat.value=sumSat() ;
                    proj1Total();
               }   
              
              // for calculating the total billing hours
             sumOfBillingHours();
         }
   
          function maxExceedde(td){
                     alert("Maximium Working Hours in a Day Exceeded.."); 
                       td.value=0;
             }
                function sumSun(){
              var sum=parseFloat(Number(document.frmAddTimeSheet.proj1Sun.value) +  Number(document.frmAddTimeSheet.proj2Sun.value) + Number(document.frmAddTimeSheet.internalSun.value) + Number(document.frmAddTimeSheet.vacationSun.value)+ Number(document.frmAddTimeSheet.holiSun.value)+ Number(document.frmAddTimeSheet.compSun.value) +  Number(document.frmAddTimeSheet.proj3Sun.value) +  Number(document.frmAddTimeSheet.proj4Sun.value)+  Number(document.frmAddTimeSheet.proj5Sun.value)).toFixed(1);
                return sum;
              }
              function sumMon(){
              var sum=parseFloat(Number(document.frmAddTimeSheet.proj1Mon.value) +  Number(document.frmAddTimeSheet.proj2Mon.value) + Number(document.frmAddTimeSheet.proj3Mon.value)+ Number(document.frmAddTimeSheet.proj4Mon.value)+Number(document.frmAddTimeSheet.proj5Mon.value)+  Number(document.frmAddTimeSheet.internalMon.value) + Number(document.frmAddTimeSheet.vacationMon.value)+ Number(document.frmAddTimeSheet.holiMon.value)+ Number(document.frmAddTimeSheet.compMon.value)).toFixed(1);
                return sum;
              }
               function sumTus(){
              var sum=parseFloat(Number(document.frmAddTimeSheet.proj1Tus.value) +  Number(document.frmAddTimeSheet.proj2Tus.value)+  Number(document.frmAddTimeSheet.proj3Tus.value)+  Number(document.frmAddTimeSheet.proj4Tus.value)+  Number(document.frmAddTimeSheet.proj5Tus.value) + Number(document.frmAddTimeSheet.internalTus.value) + Number(document.frmAddTimeSheet.vacationTus.value)+ Number(document.frmAddTimeSheet.holiTus.value)+ Number(document.frmAddTimeSheet.compTus.value)).toFixed(1);
                return sum;
              }
            function sumWed(){
              var sum=parseFloat(Number(document.frmAddTimeSheet.proj1Wed.value) +  Number(document.frmAddTimeSheet.proj2Wed.value) +  Number(document.frmAddTimeSheet.proj3Wed.value)+  Number(document.frmAddTimeSheet.proj4Wed.value)+  Number(document.frmAddTimeSheet.proj5Wed.value)+ Number(document.frmAddTimeSheet.internalWed.value) + Number(document.frmAddTimeSheet.vacationWed.value)+ Number(document.frmAddTimeSheet.holiWed.value)+ Number(document.frmAddTimeSheet.compWed.value)).toFixed(1);
                return sum;
              }
            function sumThur(){
              var sum=parseFloat(Number(document.frmAddTimeSheet.proj1Thur.value) +  Number(document.frmAddTimeSheet.proj2Thur.value)+  Number(document.frmAddTimeSheet.proj3Thur.value)+  Number(document.frmAddTimeSheet.proj4Thur.value)+  Number(document.frmAddTimeSheet.proj5Thur.value) + Number(document.frmAddTimeSheet.internalThur.value) + Number(document.frmAddTimeSheet.vacationThur.value)+ Number(document.frmAddTimeSheet.holiThur.value)+ Number(document.frmAddTimeSheet.compThur.value)).toFixed(1);
                return sum;
              }
            function sumFri(){
              var sum=parseFloat(Number(document.frmAddTimeSheet.proj1Fri.value) +  Number(document.frmAddTimeSheet.proj2Fri.value)+  Number(document.frmAddTimeSheet.proj3Fri.value) +  Number(document.frmAddTimeSheet.proj4Fri.value) +  Number(document.frmAddTimeSheet.proj5Fri.value)  + Number(document.frmAddTimeSheet.internalFri.value) + Number(document.frmAddTimeSheet.vacationFri.value)+ Number(document.frmAddTimeSheet.holiFri.value) + Number(document.frmAddTimeSheet.compFri.value)).toFixed(1);
                return sum;
              }
          function sumSat(){
              var sum=parseFloat(Number(document.frmAddTimeSheet.proj1Sat.value) +  Number(document.frmAddTimeSheet.proj2Sat.value)+  Number(document.frmAddTimeSheet.proj3Sat.value) +  Number(document.frmAddTimeSheet.proj4Sat.value) +  Number(document.frmAddTimeSheet.proj5Sat.value)  + Number(document.frmAddTimeSheet.internalSat.value) + Number(document.frmAddTimeSheet.vacationSat.value)+ Number(document.frmAddTimeSheet.holiSat.value)+ Number(document.frmAddTimeSheet.compSat.value)).toFixed(1);
                return sum;
              }

               function checkSun(td){
               if ( (sumSun() ) >24)
               {
                    maxExceedde(td);
                 }
              }
                function checkMon(td){
                if ( (sumMon() ) >24)
               {
                    maxExceedde(td);
                 }
              }
              function checkTus(td){
                     if ( (sumTus() ) >24)
                  {
                    maxExceedde(td);
                 }
              }
           function checkWed(td){
                  if ( (sumWed() ) >24)
               {
                    maxExceedde(td);
                 }
              }
           function checkThur(td){
                  if ( (sumThur() ) >24)  {
                    maxExceedde(td);
                 }
              }
           function checkFri(td) {
                  if ( (sumFri() ) >24) {
                    maxExceedde(td);
                 }
              }
          function checkSat(td){
                   if ( (sumSat() ) >24) {
                    maxExceedde(td);
                     }
                 }
              function proj1Total(){
              document.frmAddTimeSheet.proj1TotalHrs.value=parseFloat(Number(document.frmAddTimeSheet.proj1Sun.value) +  Number(document.frmAddTimeSheet.proj1Mon.value) + Number(document.frmAddTimeSheet.proj1Tus.value) + Number(document.frmAddTimeSheet.proj1Wed.value)+ Number(document.frmAddTimeSheet.proj1Thur.value)+ Number(document.frmAddTimeSheet.proj1Fri.value) +Number(document.frmAddTimeSheet.proj1Sat.value)).toFixed(1);
             }
           
                function proj2Total(){
                document.frmAddTimeSheet.proj2TotalHrs.value=parseFloat(Number(document.frmAddTimeSheet.proj2Sun.value) +  Number(document.frmAddTimeSheet.proj2Mon.value) + Number(document.frmAddTimeSheet.proj2Tus.value) + Number(document.frmAddTimeSheet.proj2Wed.value)+ Number(document.frmAddTimeSheet.proj2Thur.value)+ Number(document.frmAddTimeSheet.proj2Fri.value) +Number(document.frmAddTimeSheet.proj2Sat.value)).toFixed(1); 
             }
             function proj3Total(){
                document.frmAddTimeSheet.proj3TotalHrs.value=parseFloat(Number(document.frmAddTimeSheet.proj3Sun.value) +  Number(document.frmAddTimeSheet.proj3Mon.value) + Number(document.frmAddTimeSheet.proj3Tus.value) + Number(document.frmAddTimeSheet.proj3Wed.value)+ Number(document.frmAddTimeSheet.proj3Thur.value)+ Number(document.frmAddTimeSheet.proj3Fri.value) +Number(document.frmAddTimeSheet.proj3Sat.value)).toFixed(1);
             }
             function proj4Total(){
                document.frmAddTimeSheet.proj4TotalHrs.value=parseFloat(Number(document.frmAddTimeSheet.proj4Sun.value) +  Number(document.frmAddTimeSheet.proj4Mon.value) + Number(document.frmAddTimeSheet.proj4Tus.value) + Number(document.frmAddTimeSheet.proj4Wed.value)+ Number(document.frmAddTimeSheet.proj4Thur.value)+ Number(document.frmAddTimeSheet.proj4Fri.value) +Number(document.frmAddTimeSheet.proj4Sat.value)).toFixed(1); 
             }
             function proj5Total(){
                document.frmAddTimeSheet.proj5TotalHrs.value=parseFloat(Number(document.frmAddTimeSheet.proj5Sun.value) +  Number(document.frmAddTimeSheet.proj5Mon.value) + Number(document.frmAddTimeSheet.proj5Tus.value) + Number(document.frmAddTimeSheet.proj5Wed.value)+ Number(document.frmAddTimeSheet.proj5Thur.value)+ Number(document.frmAddTimeSheet.proj5Fri.value) +Number(document.frmAddTimeSheet.proj5Sat.value)).toFixed(1); 
             }
            function internTotal(){
             document.frmAddTimeSheet.internalTotalHrs.value=parseFloat(Number(document.frmAddTimeSheet.internalSun.value) +  Number(document.frmAddTimeSheet.internalMon.value) + Number(document.frmAddTimeSheet.internalTus.value) + Number(document.frmAddTimeSheet.internalWed.value)+ Number(document.frmAddTimeSheet.internalThur.value)+ Number(document.frmAddTimeSheet.internalFri.value) +Number(document.frmAddTimeSheet.internalSat.value)).toFixed(1); 
             }
               function vacTotal(){
             document.frmAddTimeSheet.vacationTotalHrs.value =parseFloat(Number(document.frmAddTimeSheet.vacationSun.value) +  Number(document.frmAddTimeSheet.vacationMon.value) + Number(document.frmAddTimeSheet.vacationTus.value) + Number(document.frmAddTimeSheet.vacationWed.value)+ Number(document.frmAddTimeSheet.vacationThur.value)+ Number(document.frmAddTimeSheet.vacationFri.value) +Number(document.frmAddTimeSheet.vacationSat.value)).toFixed(1); 
             }
     function holiTotal(){
               document.frmAddTimeSheet.holiTotalHrs.value =parseFloat(Number(document.frmAddTimeSheet.holiSun.value) +  Number(document.frmAddTimeSheet.holiMon.value) + Number(document.frmAddTimeSheet.holiTus.value) + Number(document.frmAddTimeSheet.holiWed.value)+ Number(document.frmAddTimeSheet.holiThur.value)+ Number(document.frmAddTimeSheet.holiFri.value) +Number(document.frmAddTimeSheet.holiSat.value)).toFixed(1); 
             }
      function compTotal(){
               document.frmAddTimeSheet.compTotalHrs.value =parseFloat(Number(document.frmAddTimeSheet.compSun.value) +  Number(document.frmAddTimeSheet.compMon.value) + Number(document.frmAddTimeSheet.compTus.value) + Number(document.frmAddTimeSheet.compWed.value)+ Number(document.frmAddTimeSheet.compThur.value)+ Number(document.frmAddTimeSheet.compFri.value) +Number(document.frmAddTimeSheet.compSat.value)).toFixed(1); 
             }
       function sumOfBillingHours() {
          document.frmAddTimeSheet.totalBillHrs.value=parseFloat(Number(document.frmAddTimeSheet.proj1TotalHrs.value)+Number(document.frmAddTimeSheet.proj2TotalHrs.value)+Number(document.frmAddTimeSheet.proj3TotalHrs.value)+Number(document.frmAddTimeSheet.proj4TotalHrs.value)+Number(document.frmAddTimeSheet.proj5TotalHrs.value)+Number(document.frmAddTimeSheet.internalTotalHrs.value)).toFixed(1);
          document.frmAddTimeSheet.totalHoliHrs.value=parseFloat(document.frmAddTimeSheet.holiTotalHrs.value).toFixed(1);
          document.frmAddTimeSheet.totalVacationHrs.value=parseFloat(document.frmAddTimeSheet.vacationTotalHrs.value).toFixed(1);
          document.frmAddTimeSheet.totalComptimeHrs.value=parseFloat(document.frmAddTimeSheet.compTotalHrs.value).toFixed(1);
          document.frmAddTimeSheet.allWeekendTotalHors.value=parseFloat(Number(document.frmAddTimeSheet.totalSun.value)+Number(document.frmAddTimeSheet.totalMon.value)+Number(document.frmAddTimeSheet.totalTus.value)+Number(document.frmAddTimeSheet.totalWed.value)+Number(document.frmAddTimeSheet.totalThur.value)+Number(document.frmAddTimeSheet.totalFri.value)+Number(document.frmAddTimeSheet.totalSat.value)).toFixed(1);
         }


         function computeProj2(td)
         {
             checkForNum(td);
                if(document.frmAddTimeSheet.proj2TotalHrs.value=='0.0') {
                document.frmAddTimeSheet.proj2TotalHrs.value =Number(td.value);
                }
                          
                  //  If the Total for a Day exceeds the 24 then Reset that Current Cell to be Zero
              
                 checkSun(td);
              if ( (sumSun()) <24)
               {
                   document.frmAddTimeSheet.totalSun.value=sumSun();
                   proj2Total();
               }
               checkMon(td);
              if ( (sumMon() ) <24)
               {
                 document.frmAddTimeSheet.totalMon.value=sumMon();
                 proj2Total();
               }              
              checkTus(td);
              if ( (sumTus() ) <24)
               {
                document.frmAddTimeSheet.totalTus.value=sumTus();
                 proj2Total();
               }  
               checkWed(td);
              if ( (sumWed() ) <24)
               {
                document.frmAddTimeSheet.totalWed.value=sumWed() ;
                proj2Total();
               }  
             checkThur(td);
              if ( (sumThur() ) <24)
               {
                 document.frmAddTimeSheet.totalThur.value=sumThur();
                 proj2Total();
               } 
              checkFri(td);
              if ( (sumFri() ) <24)
               {
                  document.frmAddTimeSheet.totalFri.value=sumFri();
                  proj2Total();
               }   
             checkSat(td);
              if ( (sumSat() ) <24)
               {
                    document.frmAddTimeSheet.totalSat.value=sumSat() ;
                    proj2Total();
               }   
              
              // for calculating the total billing hours
             sumOfBillingHours();
         }
   
      function computeInternal(td)
         {
            checkForNum(td);
            
                if(document.frmAddTimeSheet.internalTotalHrs.value=='0.0') {
                document.frmAddTimeSheet.internalTotalHrs.value =Number(td.value);
                }
              
                  //  If the Total for a Day exceeds the 24 then Reset that Current Cell to be Zero
              
                    checkSun(td);
              if ( (sumSun()) <24)
               {
                   document.frmAddTimeSheet.totalSun.value=sumSun();
                 internTotal();
               }
               checkMon(td);
              if ( (sumMon() ) <24)
               {
                 document.frmAddTimeSheet.totalMon.value=sumMon();
                internTotal();
               }              
              checkTus(td);
              if ( (sumTus() ) <24)
               {
                document.frmAddTimeSheet.totalTus.value=sumTus();
                 internTotal();
               }  
               checkWed(td);
              if ( (sumWed() ) <24)
               {
                document.frmAddTimeSheet.totalWed.value=sumWed() ;
                internTotal();
               }  
             checkThur(td);
              if ( (sumThur() ) <24)
               {
               document.frmAddTimeSheet.totalThur.value=sumThur();
               internTotal();
               } 
              checkFri(td);
              if ( (sumFri() ) <24)
               {
                  document.frmAddTimeSheet.totalFri.value=sumFri();
                internTotal();
               }   
             checkSat(td);
              if ( (sumSat() ) <24)
               {
                    document.frmAddTimeSheet.totalSat.value=sumSat() ;
                   internTotal();
               }   
              
              // for calculating the total billing hours
             sumOfBillingHours();
         }
   
// for vaction 

 function computeVacation(td)
         {
             checkForNum(td);
                if(document.frmAddTimeSheet.vacationTotalHrs.value=='0') {
                document.frmAddTimeSheet.vacationTotalHrs.value =Number(td.value);
                }
                          
                 //  If the Total for a Day exceeds the 24 then Reset that Current Cell to be Zero
              
                    checkSun(td);
              if ( (sumSun()) <24)  {
                 document.frmAddTimeSheet.totalSun.value=sumSun();
                vacTotal();
               }
               checkMon(td);
              if ( (sumMon() ) <24) {
                 document.frmAddTimeSheet.totalMon.value=sumMon();
                   vacTotal();
               }              
              checkTus(td);
              if ( (sumTus() ) <24) {
                document.frmAddTimeSheet.totalTus.value=sumTus();
                vacTotal();
               }  
               checkWed(td);
              if ( (sumWed() ) <24) {
                document.frmAddTimeSheet.totalWed.value=sumWed() ;
                vacTotal();
               }  
             checkThur(td);
              if ( (sumThur() ) <24)
               {
                 document.frmAddTimeSheet.totalThur.value=sumThur();
                 vacTotal();
               } 
              checkFri(td);
              if ( (sumFri() ) <24)  {
                  document.frmAddTimeSheet.totalFri.value=sumFri();
               vacTotal();
               }   
             checkSat(td);
              if ( (sumSat() ) <24) {
                 document.frmAddTimeSheet.totalSat.value=sumSat() ;
                 vacTotal();
               }   
              
              // for calculating the total billing hours
             sumOfBillingHours();
           }
   
// for Holidays

 function computeHolidy(td)
         {
             checkForNum(td);
                if(document.frmAddTimeSheet.holiTotalHrs.value=='0'){
                document.frmAddTimeSheet.holiTotalHrs.value =Number(td.value);
                }
            
                    //  If the Total for a Day exceeds the 24 then Reset that Current Cell to be Zero
              
                    checkSun(td);
              if ( (sumSun()) <24)  {
                   document.frmAddTimeSheet.totalSun.value=sumSun();
                 holiTotal();
               }
               checkMon(td);
              if ( (sumMon() ) <24)  {
                 document.frmAddTimeSheet.totalMon.value=sumMon();
                holiTotal();
               }              
              checkTus(td);
              if ( (sumTus() ) <24)   {
                document.frmAddTimeSheet.totalTus.value=sumTus();
                  holiTotal();
               }  
               checkWed(td);
              if ( (sumWed() ) <24)  {
                document.frmAddTimeSheet.totalWed.value=sumWed() ;
                 holiTotal();
               }  
             checkThur(td);
              if ( (sumThur() ) <24)  {
                 document.frmAddTimeSheet.totalThur.value=sumThur();
               internTotal();
               } 
              checkFri(td);
              if ( (sumFri() ) <24)   {
                  document.frmAddTimeSheet.totalFri.value=sumFri();
                 holiTotal();
               }   
             checkSat(td);
              if ( (sumSat() ) <24)  {
                    document.frmAddTimeSheet.totalSat.value=sumSat() ;
                    holiTotal();
               }   
              
              // for calculating the total billing hours
             sumOfBillingHours();
                    
         }
      // for checking approved date 

              function checkBeforeSubmit(){
                if(document.frmAddTimeSheet.approveDate.value!=" " && document.frmAddTimeSheet.submittedDate.value!=" ") {
                if((Date.parse(document.frmAddTimeSheet.approveDate.value)) >= (Date.parse(document.frmAddTimeSheet.submittedDate.value))  )
                {
                alert("Approved Date must be Greater than the Submitted date");
                return false;
                }
                return true;
                }
                }
                
// new methods for comptime

function computeComptime(td)
         {
             checkForNum(td);
                if(document.frmAddTimeSheet.compTotalHrs.value=='0'){
                document.frmAddTimeSheet.compTotalHrs.value =Number(td.value);
                }
            
                    //  If the Total for a Day exceeds the 24 then Reset that Current Cell to be Zero
              
                    checkSun(td);
              if ( (sumSun()) <24)  {
                   document.frmAddTimeSheet.totalSun.value=sumSun();
                 compTotal();
               }
               checkMon(td);
              if ( (sumMon() ) <24)  {
                 document.frmAddTimeSheet.totalMon.value=sumMon();
                compTotal();
               }              
              checkTus(td);
              if ( (sumTus() ) <24)   {
                document.frmAddTimeSheet.totalTus.value=sumTus();
                  compTotal();
               }  
               checkWed(td);
              if ( (sumWed() ) <24)  {
                document.frmAddTimeSheet.totalWed.value=sumWed() ;
                 compTotal();
               }  
             checkThur(td);
              if ( (sumThur() ) <24)  {
                 document.frmAddTimeSheet.totalThur.value=sumThur();
               compTotal();
               } 
              checkFri(td);
              if ( (sumFri() ) <24)   {
                  document.frmAddTimeSheet.totalFri.value=sumFri();
                 compTotal();
               }   
             checkSat(td);
              if ( (sumSat() ) <24)  {
                    document.frmAddTimeSheet.totalSat.value=sumSat() ;
                    compTotal();
               }   
              
              // for calculating the total billing hours
             sumOfBillingHours();
                    
         }
         
         
         
// new methods for projects

 function computeProj3(td)
         {
             checkForNum(td);
                if(document.frmAddTimeSheet.proj3TotalHrs.value=='0.0') {
                document.frmAddTimeSheet.proj3TotalHrs.value =Number(td.value);
                }
                          
                  //  If the Total for a Day exceeds the 24 then Reset that Current Cell to be Zero
              
                 checkSun(td);
              if ( (sumSun()) <24)
               {
                   document.frmAddTimeSheet.totalSun.value=sumSun();
                   proj3Total();
               }
               checkMon(td);
              if ( (sumMon() ) <24)
               {
                 document.frmAddTimeSheet.totalMon.value=sumMon();
                 proj3Total();
               }              
              checkTus(td);
              if ( (sumTus() ) <24)
               {
                document.frmAddTimeSheet.totalTus.value=sumTus();
                 proj3Total();
               }  
               checkWed(td);
              if ( (sumWed() ) <24)
               {
                document.frmAddTimeSheet.totalWed.value=sumWed() ;
                proj3Total();
               }  
             checkThur(td);
              if ( (sumThur() ) <24)
               {
                 document.frmAddTimeSheet.totalThur.value=sumThur();
                 proj3Total();
               } 
              checkFri(td);
              if ( (sumFri() ) <24)
               {
                  document.frmAddTimeSheet.totalFri.value=sumFri();
                  proj3Total();
               }   
             checkSat(td);
              if ( (sumSat() ) <24)
               {
                    document.frmAddTimeSheet.totalSat.value=sumSat() ;
                    proj3Total();
               }   
              
              // for calculating the total billing hours
             sumOfBillingHours();
         }
   
 function computeProj4(td)
         {
             checkForNum(td);
                if(document.frmAddTimeSheet.proj4TotalHrs.value=='0.0') {
                document.frmAddTimeSheet.proj4TotalHrs.value =Number(td.value);
                }
                          
                  //  If the Total for a Day exceeds the 24 then Reset that Current Cell to be Zero
              
                 checkSun(td);
              if ( (sumSun()) <24)
               {
                   document.frmAddTimeSheet.totalSun.value=sumSun();
                   proj4Total();
               }
               checkMon(td);
              if ( (sumMon() ) <24)
               {
                 document.frmAddTimeSheet.totalMon.value=sumMon();
                 proj4Total();
               }              
              checkTus(td);
              if ( (sumTus() ) <24)
               {
                document.frmAddTimeSheet.totalTus.value=sumTus();
                 proj4Total();
               }  
               checkWed(td);
              if ( (sumWed() ) <24)
               {
                document.frmAddTimeSheet.totalWed.value=sumWed() ;
                proj4Total();
               }  
             checkThur(td);
              if ( (sumThur() ) <24)
               {
                 document.frmAddTimeSheet.totalThur.value=sumThur();
                 proj4Total();
               } 
              checkFri(td);
              if ( (sumFri() ) <24)
               {
                  document.frmAddTimeSheet.totalFri.value=sumFri();
                  proj4Total();
               }   
             checkSat(td);
              if ( (sumSat() ) <24)
               {
                    document.frmAddTimeSheet.totalSat.value=sumSat() ;
                    proj4Total();
               }   
              
              // for calculating the total billing hours
             sumOfBillingHours();
         }
   
 function computeProj5(td)
         {
             checkForNum(td);
                if(document.frmAddTimeSheet.proj5TotalHrs.value=='0.0') {
                document.frmAddTimeSheet.proj5TotalHrs.value =Number(td.value);
                }
                          
                  //  If the Total for a Day exceeds the 24 then Reset that Current Cell to be Zero
              
                 checkSun(td);
              if ( (sumSun()) <24)
               {
                   document.frmAddTimeSheet.totalSun.value=sumSun();
                   proj5Total();
               }
               checkMon(td);
              if ( (sumMon() ) <24)
               {
                 document.frmAddTimeSheet.totalMon.value=sumMon();
                 proj5Total();
               }              
              checkTus(td);
              if ( (sumTus() ) <24)
               {
                document.frmAddTimeSheet.totalTus.value=sumTus();
                 proj5Total();
               }  
               checkWed(td);
              if ( (sumWed() ) <24)
               {
                document.frmAddTimeSheet.totalWed.value=sumWed() ;
                proj5Total();
               }  
             checkThur(td);
              if ( (sumThur() ) <24)
               {
                 document.frmAddTimeSheet.totalThur.value=sumThur();
                 proj5Total();
               } 
              checkFri(td);
              if ( (sumFri() ) <24)
               {
                  document.frmAddTimeSheet.totalFri.value=sumFri();
                  proj5Total();
               }   
             checkSat(td);
              if ( (sumSat() ) <24)
               {
                    document.frmAddTimeSheet.totalSat.value=sumSat() ;
                    proj5Total();
               }   
              
              // for calculating the total billing hours
             sumOfBillingHours();
         }
   