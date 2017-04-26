 (function($){
    $.fn.extend({
            
        tablePaginate: function(options,recordPage,table) {
               
         //   alert(table)
            var defaults = {
                recordPerPage:recordPage,				// Display records per page
                pageNumber:document.getElementById("pgNo").value,					// GoTo Pagenumber - Default : 1
                fullData:false,					// True : Disable pagination, False - Enable Pagination
                buttonPosition:'after',		// before, after
                navigateType:'navigator',		// navigator (first,prev,next,last buttons), full (display page numbers)
		table:	table			
            };
                
          
            var el = this;		
				
         
         if(defaults.table.id=="tblRequirementOverviewDetails"){
              var totalRecords=document.getElementById('totalRecords1').value;
          } else{
         var totalRecords=document.getElementById('totalRecords').value;
          }
        
            if(totalRecords<1){
                totalRecords=10;
            }
            if (defaults.fullData == 'true'){
                defaults.pageNumber = 1;
                defaults.recordPerPage = totalRecords;
            }
							
            // Identify Start & End values
            var end = defaults.pageNumber * defaults.recordPerPage;
            var start = end - defaults.recordPerPage;
            start=start;
           
            $('span.pagination').empty().remove();
            var buildButtons = "<span class='pagination'>";
            var totalPages =0;
             // alert("default.table1111..."+defaults.table.id)
        if(defaults.table.id=="tblPresalesRequirementList" || defaults.table.id=="tblRequirementOverviewDetails" ){
             totalPages = Math.ceil((totalRecords-1)/defaults.recordPerPage);
          }   else if(defaults.table.id=="tblPSCERDetailsPresales" || defaults.table.id=="tblPSCERDetailsEmployee" || defaults.table.id=="tblPSCERDetailsSales"){
               totalPages = Math.ceil((totalRecords)/defaults.recordPerPage);
         
          }
            var pno=parseInt(defaults.pageNumber);
            
            document.getElementById("pgNo").value=pno;
            
                             
            if (defaults.navigateType == 'navigator'){
                var firstPage = 1;
                var nextPage = parseInt(defaults.pageNumber) + 1;
                var prevPage = parseInt(defaults.pageNumber) - 1;
                nextPage = (nextPage >= totalPages) ? totalPages : nextPage;
                prevPage = (prevPage < firstPage) ? firstPage : prevPage;
                
                buildButtons += "<a href='javascript:void(0);' id='"+firstPage+"' class='btn btn-warning pagination-btn'><i style='font-size: 14px; background-color: #396E9D; color: white; border-radius: 13px; width: 165%;'><<</i></a>";
                buildButtons += "<a href='javascript:void(0);' id='"+prevPage+"' class='btn btn-warning pagination-btn'><i style='font-size: 14px; background-color:#56a5ec; color: white; border-radius: 13px; width: 165%;'><</i></a>";
                if(totalPages>5) {
                    if((totalPages-pno)==3)
                    {
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-1)+"' class='btn btn-warning pagination-btn'>"+(pno-1)+"</a>";
                    }
                    if((totalPages-pno)==2)
                    {
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-2)+"' class='btn btn-warning pagination-btn'>"+(pno-2)+"</a>";
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-1)+"' class='btn btn-warning pagination-btn'>"+(pno-1)+"</a>";
                    }
                    if((totalPages-pno)==1)
                    {
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-3)+"' class='btn btn-warning pagination-btn'>"+(pno-3)+"</a>";
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-2)+"' class='btn btn-warning pagination-btn'>"+(pno-2)+"</a>";
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-1)+"' class='btn btn-warning pagination-btn'>"+(pno-1)+"</a>";
                    }
                    if((totalPages-pno)==0)
                    {
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-4)+"' class='btn btn-warning pagination-btn'>"+(pno-4)+"</a>";
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-3)+"' class='btn btn-warning pagination-btn'>"+(pno-3)+"</a>";
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-2)+"' class='btn btn-warning pagination-btn'>"+(pno-2)+"</a>";
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-1)+"' class='btn btn-warning pagination-btn'>"+(pno-1)+"</a>";
                    }
                }
                if(totalPages==5) 
                {
                    // alert(totalPages-pno);
                    if((totalPages-pno)==2)
                    {
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-1)+"' class='btn btn-warning pagination-btn'>"+(pno-1)+"</a>";
                    }
                    if((totalPages-pno)==1)
                    {
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-2)+"' class='btn btn-warning pagination-btn'>"+(pno-2)+"</a>";
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-1)+"' class='btn btn-warning pagination-btn'>"+(pno-1)+"</a>";
                    }
                    if((totalPages-pno)==0)
                    {
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-3)+"' class='btn btn-warning pagination-btn'>"+(pno-3)+"</a>";
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-2)+"' class='btn btn-warning pagination-btn'>"+(pno-2)+"</a>";
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-1)+"' class='btn btn-warning pagination-btn'>"+(pno-1)+"</a>";
                    }
                                        
                }
                if(totalPages==4) 
                {
                    // alert(totalPages-pno);
                    if((totalPages-pno)==2)
                    {
                    //  buildButtons += "<a href='javascript:void(0);' id='"+(pno-1)+"' class='btn btn-warning pagination-btn'>"+(pno-1)+"</a>";
                    }
                    if((totalPages-pno)==1)
                    {
                        //  buildButtons += "<a href='javascript:void(0);' id='"+(pno-2)+"' class='btn btn-warning pagination-btn'>"+(pno-2)+"</a>";
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-1)+"' class='btn btn-warning pagination-btn'>"+(pno-1)+"</a>";
                    }
                    if((totalPages-pno)==0)
                    {
                        // buildButtons += "<a href='javascript:void(0);' id='"+(pno-3)+"' class='btn btn-warning pagination-btn'>"+(pno-3)+"</a>";
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-2)+"' class='btn btn-warning pagination-btn'>"+(pno-2)+"</a>";
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-1)+"' class='btn btn-warning pagination-btn'>"+(pno-1)+"</a>";
                    }
                                        
                }
                if(totalPages==3) 
                {
                    // alert(totalPages-pno);
                    if((totalPages-pno)==2)
                    {
                    //  buildButtons += "<a href='javascript:void(0);' id='"+(pno-1)+"' class='btn btn-warning pagination-btn'>"+(pno-1)+"</a>";
                    }
                    if((totalPages-pno)==1)
                    {
                        //  buildButtons += "<a href='javascript:void(0);' id='"+(pno-2)+"' class='btn btn-warning pagination-btn'>"+(pno-2)+"</a>";
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-1)+"' class='btn btn-warning pagination-btn'>"+(pno-1)+"</a>";
                    }
                    if((totalPages-pno)==0)
                    {
                        // buildButtons += "<a href='javascript:void(0);' id='"+(pno-3)+"' class='btn btn-warning pagination-btn'>"+(pno-3)+"</a>";
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-2)+"' class='btn btn-warning pagination-btn'>"+(pno-2)+"</a>";
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-1)+"' class='btn btn-warning pagination-btn'>"+(pno-1)+"</a>";
                    }
                                        
                }
                if(totalPages==2) 
                {
                    // alert(totalPages-pno);
                    if((totalPages-pno)==2)
                    {
                    //  buildButtons += "<a href='javascript:void(0);' id='"+(pno-1)+"' class='btn btn-warning pagination-btn'>"+(pno-1)+"</a>";
                    }
                    if((totalPages-pno)==1)
                    {
                    //  buildButtons += "<a href='javascript:void(0);' id='"+(pno-2)+"' class='btn btn-warning pagination-btn'>"+(pno-2)+"</a>";
                    //   buildButtons += "<a href='javascript:void(0);' id='"+(pno-1)+"' class='btn btn-warning pagination-btn'>"+(pno-1)+"</a>";
                    }
                    if((totalPages-pno)==0)
                    {
                        // buildButtons += "<a href='javascript:void(0);' id='"+(pno-3)+"' class='btn btn-warning pagination-btn'>"+(pno-3)+"</a>";
                        //  buildButtons += "<a href='javascript:void(0);' id='"+(pno-2)+"' class='btn btn-warning pagination-btn'>"+(pno-2)+"</a>";
                        buildButtons += "<a href='javascript:void(0);' id='"+(pno-1)+"' class='btn btn-warning pagination-btn'>"+(pno-1)+"</a>";
                    }
                                        
                }
                for(var i=pno;i<=pno+4&&i<=totalPages;i++){	
                                        
                    buildButtons += "<a href='javascript:void(0);' id='"+i+"' class='btn btn-warning pagination-btn'>"
                    if(i==pno){
                        buildButtons += "<u>"+i+"</u>"   ;
                    }
                    else
                    {
                        buildButtons += i   ;  
                    }
                    buildButtons +="</a>";
                }
                buildButtons += "<a href='javascript:void(0);' id='"+nextPage+"' class='btn btn-warning pagination-btn'><i style='font-size: 14px; background-color: #56a5ec; color: white; border-radius: 13px; width: 165%;'>></i></a>";				
                buildButtons += "<a href='javascript:void(0);' id='"+totalPages+"' class='btn btn-warning pagination-btn'><i style='font-size: 14px; background-color: #396E9D; color: white; border-radius: 13px; width: 165%;'>>></i></a>";
            }else{				
                // Display page numbers
                for(var i=1;i<=totalPages;i++){	
                                            
                    buildButtons += "<button type='button' id='"+i+"' class='btn btn-warning pagination-btn'>"+i+"</button>";
                }
            }
            buildButtons += "</span>";
				
       //   (defaults.buttonPosition == 'after') ? $(this).after(buildButtons) : $(buildButtons).append(this);
	// document.getElementById("button_pageNation").innerHTML=buildButtons;	
 if(defaults.table.id=="tblRequirementOverviewDetails"){
    document.getElementById("button_pageNation1").innerHTML=buildButtons;
       }
       else{
           document.getElementById("button_pageNation").innerHTML=buildButtons;
       }
        	
 //alert("default.table1....."+defaults.table.id)
            // Pagination button live click
            $(".pagination-btn").on("click",function(){ 
                var id = $(this).attr("id");
                var pastPage=document.getElementById("pgNo").value;
              //  alert(pastPage);
                document.getElementById("pgNo").value=id;

                var rowscount=parseInt(pastPage);

                var total=Math.ceil(parseInt(totalRecords)/defaults.recordPerPage);
                
            
                if(id==rowscount){
                    if(total==rowscount ){
                                    
                                    
                        alert("Oops! No pages exists");
                        return false;
                    }
                    if(rowscount=="1"){
                                    
                                    
                        alert("Oops! No pages exists");
                        return false;
                    }
                }
                 if(defaults.table.id=="tblPresalesRequirementList"){
       getPresalesRequiremntList('0');}
      else if(defaults.table.id=="tblPSCERDetailsPresales"){
   getClientrequestDetailsForPresales('0');}
else if( defaults.table.id=="tblPSCERDetailsEmployee"){
   getClientrequestDetailsForEmployee('0');}
else if( defaults.table.id=="tblPSCERDetailsSales"){
   getClientrequestDetailsForSales('0');}
else if( defaults.table.id=="tblRequirementOverviewDetails"){
   getRequirementOverviewDetails('0');}
                $(el).tablePaginate({
                    pageNumber:id,
                    recordPerPage:defaults.recordPerPage,
                    fullData:defaults.fullData,
                    buttonPosition:defaults.buttonPosition,
                    navigateType:defaults.navigateType
                });
									
            });
				
				
        }
    });
})(jQuery);
        


