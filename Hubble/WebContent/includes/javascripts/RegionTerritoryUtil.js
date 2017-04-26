var territories=new Array();
territories.Central=new Array("North Central","Great Lakes","Great Plains","Central1","Central2","Central3","Central4");
territories.East=new Array("Mid-Atlantic","NY NJ Metro","WallStreet","South","Upstate NY/NE","GreaterNewyork","Boston","Northeast","Washington","Southeast","PanHandle");
territories.Enterprise=new Array("IBM-TBD","SAP-TBD","Healthcare-TBD","Automotive-TBD","CSC","Accenture","IBM","Ford","Delphi Automotive","Target");
territories.West=new Array("Pacific NW","Pacific SW","South West","West1","West2","West3","West4","West5","West6","West7");


function getTerritories(form,index){
    //cntrySel=document.frmSearch.region;
    
   cntrySel=document.getElementById('region');
   cntrySel = form.elements['region'];
    //alert("cntrySel"+cntrySel.value);    
    terrirotyList=territories[cntrySel.value];
    
    if(terrirotyList!=null){
        setTerritories("territory",terrirotyList,terrirotyList);
    
    }else{
        selectedField=document.getElementById('territory');
         selectedField.options.length=0;
          selectedField.options[selectedField.length]=new Option("-- please select --","");
    }
}

function setTerritories(fieldId,newOptions,newValues){
  //  alert(fieldId+"@@"+newOptions+"&&"+newValues);
   // selectedField=document.frmSearch.territory;
   selectedField=document.getElementById('territory');
   //alert("target-->"+selectedField);
    selectedField.options.length=0;
    for(i=0; i<newOptions.length;i++){
        selectedField.options[selectedField.length]=new Option(newOptions[i],newValues[i]);
    }
}