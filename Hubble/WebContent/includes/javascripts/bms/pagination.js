
(function($){
        $.fn.extend({
            
            tablePaginate: function(options,recordPage) {
               
               
                var defaults = {
						recordPerPage:recordPage,				// Display records per page
						pageNumber:1,					// GoTo Pagenumber - Default : 1
						fullData:false,					// True : Disable pagination, False - Enable Pagination
						buttonPosition:'before',		// before, after
						navigateType:'navigator'		// navigator (first,prev,next,last buttons), full (display page numbers)
						
				};
                
				var options = $.extend(defaults, options);
				var el = this;		
				
			// GET total Records length
				var totalRecords=$(el).find('tbody').find('tr').length;
				
			// Pagination off
				if (defaults.fullData == 'true'){
					defaults.pageNumber = 1;
					defaults.recordPerPage = totalRecords;
				}
							
			// Identify Start & End values
				var end = defaults.pageNumber * defaults.recordPerPage;
				var start = end - defaults.recordPerPage;
                                start=start;
				//alert(start+"end"+end);
			// Pagination button
				$('span.pagination').empty().remove();
				var buildButtons = "<span class='pagination'>";
                        	// Get Total Pages
				var totalPages = Math.ceil((totalRecords-1)/defaults.recordPerPage);
				var pno=parseInt(defaults.pageNumber);
				
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
				
				(defaults.buttonPosition == 'before') ? $(this).before(buildButtons) : $(buildButtons).append(this);
			

			// Display records based on pagination settings
                       
				$(el).find('tbody').find('tr').each(function(rowIndex,data) {
                                    
                                    if(rowIndex!=0){
					$(this).hide();}	
                                    
                                       if ((start+1) <= rowIndex && (end+1) > rowIndex ){
						$(this).show();
					}
                                });
				
			// Pagination button live click
				$(".pagination-btn").on("click",function(){ 
					var id = $(this).attr("id");
                                      
                                            
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
        
