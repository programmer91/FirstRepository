/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function addSurveyForm() {
    window.location='addSurveyForm.action';
}


function getQuestionnaire(surveyId){
    window.location='getQuestionnaireList.action?surveyId='+surveyId;
}

function addQuestionnaire(){
    var surveyId = document.getElementById("surveyId").value;
    window.location='addQuestionnaire.action?surveyId='+surveyId;
}

/*
function getQuestionnaireDetails(questionId) {
    var surveyId = document.getElementById("surveyId").value;
    window.location='editQuestionnaireDetails.action?questionId='+questionId+'&surveyId='+surveyId;
     
}*/

 
function checkOptionType(obj){
    resetOptionValues();
    showRow("placeholderTr");
     if(obj.value=='Slider'){
         hideRow("placeholderTr");
     }
      if(obj.value=='Checkbox'||obj.value=='RadioButton'){
        document.getElementById("optionCount").value=1;
        hideRow("dropdownoptionsRow");
        hideRow("placeholderTr");
        showRow("optionRow1");
        showRow("addButtonTr");
         hideRow("attachmentRow");
        document.getElementById("addbuttonDiv").style.display='';
        document.getElementById("removeDiv").style.display='none';
    }
    else if(obj.value == 'DropDown')
    {
        document.getElementById("optionCount").value=0;
        hideRow("optionRow1");
        hideRow("optionRow2");
        hideRow("optionRow3");
        hideRow("optionRow4");
        hideRow("optionRow5");
        hideRow("optionRow6");
        hideRow("addButtonTr");
        hideRow("placeholderTr");
        // document.getElementById("dropdown").value=1;
        showRow("dropdownoptionsRow");
          hideRow("attachmentRow");
    }else if(obj.value == 'Attachment')
    {
        showRow("attachmentRow");
             document.getElementById("optionCount").value=0;
        hideRow("optionRow1");
        hideRow("optionRow2");
        hideRow("optionRow3");
        hideRow("optionRow4");
        hideRow("optionRow5");
        hideRow("optionRow6");
        hideRow("addButtonTr");
        hideRow("placeholderTr");
        // document.getElementById("dropdown").value=1;
        hideRow("dropdownoptionsRow");
    }
    
  
    else {
        if(obj.value=='ANONYMOUS') {
            document.getElementById("questionTitle").value='Do you want to post anonymously?';  
        }
        hideRow("dropdownoptionsRow");
        document.getElementById("optionCount").value=0;
        hideRow("optionRow1");
        hideRow("optionRow2");
        hideRow("optionRow3");
        hideRow("optionRow4");
        hideRow("optionRow5");
        hideRow("optionRow6");
        hideRow("addButtonTr");
        hideRow("attachmentRow");
       // hideRow("attachmentRow");
       
    }
}

//addbuttonDiv
function addOption() {
    var optionCount = document.getElementById("optionCount").value;
    document.getElementById("optionCount").value = parseInt(optionCount,10)+1;
    var newOptionCount = document.getElementById("optionCount").value;
    document.getElementById("optionSequence"+newOptionCount).value = newOptionCount;
    showRow("optionRow"+newOptionCount);
    //showRow("removeDiv");
    document.getElementById("removeDiv").style.display='';
    if(parseInt(newOptionCount,10)==6){
        document.getElementById("addbuttonDiv").style.display='none';
    }
     
}
function removeOption() {
    
    var optionCount = document.getElementById("optionCount").value;
    
    document.getElementById("optionLabel"+optionCount).value='';
    hideRow("optionRow"+optionCount);
    document.getElementById("optionCount").value = parseInt(optionCount,10)-1;
    var newOptionCount = document.getElementById("optionCount").value;
    if(parseInt(newOptionCount,10)==1){
        document.getElementById("addbuttonDiv").style.display='';
          
        document.getElementById("removeDiv").style.display='none';
    }
    if(parseInt(newOptionCount,10)>1&&parseInt(newOptionCount,10)<6){
        document.getElementById("addbuttonDiv").style.display='';
    }
}
function resetOptionValues() {
    document.getElementById("optionLabel1").value='';
    document.getElementById("optionLabel2").value='';
    document.getElementById("optionLabel3").value='';
    document.getElementById("optionLabel4").value='';
    document.getElementById("optionLabel5").value='';
    document.getElementById("optionLabel6").value='';
    
}

function hideRow(id) {
    //alert(id);
    var row = document.getElementById(id);
    row.style.display = 'none';
}
function showRow(id) {
    //  alert(id);
    var row = document.getElementById(id);
    row.style.display = '';
} 



function displayOptions() {
    var optionType = document.getElementById("optionType").value;
    if(optionType=='Checkbox'||optionType=='RadioButton'){
        var optionCount = parseInt(document.getElementById("optionCount").value,10);
        showRow("optionRow1");
        showRow("addButtonTr");
        document.getElementById("addbuttonDiv").style.display='';
        document.getElementById("removeDiv").style.display='none';
        
        if(optionCount>1){
            showRow("optionRow2");
            document.getElementById("removeDiv").style.display='';
        }
        if(optionCount>2){
            showRow("optionRow3");
        }
        if(optionCount>3){
            showRow("optionRow4");
        }
        if(optionCount>4){
            showRow("optionRow5");
        }
        if(optionCount>5){
            document.getElementById("addbuttonDiv").style.display='none';
            showRow("optionRow6");
        }
        
          
    }
}




function getSurveyReviewList(surveyId){
    window.location='getSurveyReviewList.action?surveyId='+surveyId;
}


function doSurveyAttachmentDownload(surveyInfoId) {
    window.location='doSurveyAttachmentDownload.action?surveyInfoId='+surveyInfoId;
    
    
}



function addSurveyFormQuestion() {
    //hideRow('createdTr');
    //    hideRow('editTr');
    //    hideRow("createdTr");
          var totalQuestions = document.getElementById("totalQuestions").value;    
        //  alert(totalQuestions);
          if(parseInt(totalQuestions,10)<12){
            
          
    showRow('addTr');
           
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Add Question";
    // showRow('addTr');
            
    //------------------- Slider code start ---------------
            
    //getSlider(4,10);
            
                if($("#questionCount").length){
    var currentquestionCount = document.getElementById("questionCount").value;
    document.getElementById("querySequence").value = parseInt(currentquestionCount,10)+1;
                }else {
                    document.getElementById("querySequence").value = 1;
                }
            
    //--------------------Slider Code end -------------------
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');
    //  hideRow("approvedBy");
    //hideRow("tlcommentsTr");
    // hideRow("hrcommentsTr");
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
         
          }else {
            alert("New Questions are not allow to add.");
          }
}

function toggleCloseQuestionOverlay() {
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');

    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";

    //   document.getElementById("frmDBGrid").submit();

    }
    else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }

         
    //document.getElementById("frmDBGrid").submit();
    window.location = "getQuestionnaireList.action?surveyId="+document.getElementById("surveyId").value;
}



function doAddSurveyFormQuestion() {
    var surveyId=document.getElementById("surveyId").value;
  var currentStatus=document.getElementById("QuestionnaireStatus").value;
    var questionTitle=document.getElementById("questionTitle").value;
    var optionType=document.getElementById("optionType").value;
    var querySequence=document.getElementById("querySequence").value;
    var optionCount=document.getElementById("optionCount").value;
    var optionLabel1=document.getElementById("optionLabel1").value;
    var optionLabel2=document.getElementById("optionLabel2").value;
    var optionLabel3=document.getElementById("optionLabel3").value;
    var optionLabel4=document.getElementById("optionLabel4").value;
    var optionLabel5=document.getElementById("optionLabel5").value;
    var optionLabel6=document.getElementById("optionLabel6").value;

    var optionSequence1=document.getElementById("optionSequence1").value;
    var optionSequence2=document.getElementById("optionSequence2").value;
    var optionSequence3=document.getElementById("optionSequence3").value;
    var optionSequence4=document.getElementById("optionSequence4").value;
    var optionSequence5=document.getElementById("optionSequence5").value;
    var optionSequence6=document.getElementById("optionSequence6").value;
    
    var dropdownOptions=document.getElementById("dropdownOptions").value;
    var isRequired=document.getElementById("isRequired").checked;
    var placeHolder=document.getElementById("placeHolder").value;
    
    var isAllowDocuments=document.getElementById("allowDocuments").checked;
    var isAllowPictures=document.getElementById("allowPictures").checked;
   
    if(questionTitle.trim().length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please Query.</font>";
    }else if(optionType.trim().length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please select OptionType.</font>";
    }else if(optionType.trim()=='Attachment' && isAllowDocuments==false&&isAllowPictures==false){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please check atleast one attachment type.</font>";
    }else{
        document.getElementById("load").style.display = 'block';
        hideRow('addTr');
        $.ajax({
            url:'doAddQuestionnaire.action?surveyId='+surveyId+'&questionTitle='+escape(questionTitle)+"&placeHolder="+placeHolder+"&optionType="+optionType+"&querySequence="+querySequence+"&optionCount="+optionCount
            +"&optionLabel1="+encodeURIComponent(optionLabel1)+"&optionLabel2="+encodeURIComponent(optionLabel2)+"&optionLabel3="+encodeURIComponent(optionLabel3)+"&optionLabel4="+encodeURIComponent(optionLabel4)+"&optionLabel5="+encodeURIComponent(optionLabel5)+"&optionLabel6="+encodeURIComponent(optionLabel6)+
            "&optionSequence1="+optionSequence1+"&optionSequence2="+optionSequence2+"&optionSequence3="+optionSequence3+"&optionSequence4="+optionSequence4+"&optionSequence5="+optionSequence5+"&optionSequence6="+optionSequence6+"&isRequired="+isRequired+"&dropdownOptions="+encodeURIComponent(dropdownOptions)+"&questionStatus="+currentStatus+"&allowDocuments="+isAllowDocuments+"&allowPictures="+isAllowPictures,
            context: document.body,
            success: function(responseText) {
                document.getElementById("load").style.display = 'none';
                document.getElementById('resultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
            },
            error: function(e){
                showRow('addTr');
                document.getElementById("load").style.display = 'none';
                document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
            }
        });
    }
    return false;
}


function getQuestionnaireDetails(questionId){
    hideRow("addTr");
    hideRow("editTr");
    document.getElementById('resultMessage').innerHTML ='';
    var surveyId = document.getElementById("surveyId").value;
    //window.location='editQuestionnaireDetails.action?questionId='+questionId+'&surveyId='+surveyId;
    document.getElementById("load").style.display = 'block';
    $.ajax({
        url:'editQuestionnaireDetails.action?surveyId='+surveyId+'&questionId='+questionId,//
        context: document.body,
        success: function(responseText) {
            
            var json = $.parseJSON(responseText);
            //Id,event_id,event_speaker,event_designation,primary_speaker,Company
            var questId = json["Id"];
              
            var Query = json["Query"];
            var OptionType = json["OptionType"];
               
            var QueStatus = json["QuestionStatus"];
             
            var Sequence = json["Sequence"];
            //isRequired
            var RequiredFlag = json["RequiredFlag"];
            var PlaceHolder = json["PlaceHolder"];
            //var SurveyId = json["SurveyId"];
            document.getElementById("questionTitle").value =Query;
            document.getElementById("optionType").value =OptionType;
            document.getElementById("querySequence").value =Sequence;
            document.getElementById("questionId").value =questId;
            document.getElementById("QuestionnaireStatus").value =QueStatus;
            
            document.getElementById("placeHolder").value =PlaceHolder;
            if(RequiredFlag=='1')
                document.getElementById("isRequired").checked = true;
            else
                document.getElementById("isRequired").checked = false; 
            
             var AllowDocuments = json["AllowDocuments"];
            if(AllowDocuments=='1')
                document.getElementById("allowDocuments").checked = true;
            else
                document.getElementById("allowDocuments").checked = false; 
            
             var AllowPictures = json["AllowPictures"];
            if(AllowPictures=='1')
                document.getElementById("allowPictures").checked = true;
            else
                document.getElementById("allowPictures").checked = false; 
            
            
            if("Attachment" == OptionType)
                showRow("attachmentRow");
            
             if("Checkbox" == OptionType ||"RadioButton" == OptionType || "DropDown" == OptionType || "Slider" == OptionType){
                 hideRow("placeholderTr");  
             }
            if("Checkbox" == OptionType ||"RadioButton" == OptionType){
                     
                document.getElementById("optionLabel1").value = json["OptionLabel1"];
                document.getElementById("optionCount").value = json["OptionCount"];
                document.getElementById("optionSequence1").value = json["OptionSequence1"];
                showRow("optionRow1");
                if(document.getElementById("currStatus").value =='Active')
                    showRow("addButtonTr");
                document.getElementById("addbuttonDiv").style.display='';
                document.getElementById("removeDiv").style.display='none';
                if(parseInt(json["OptionCount"],10)>1){
                    document.getElementById("optionLabel2").value = json["OptionLabel2"];
                    document.getElementById("optionSequence2").value = json["OptionSequence2"];
                    showRow("optionRow2");
                    document.getElementById("removeDiv").style.display='';
                }
                if(parseInt(json["OptionCount"],10)>2){
                    document.getElementById("optionLabel3").value = json["OptionLabel3"];
                    document.getElementById("optionSequence3").value = json["OptionSequence3"];
                    showRow("optionRow3");
                }
                if(parseInt(json["OptionCount"],10)>3){
                    document.getElementById("optionLabel4").value = json["OptionLabel4"];
                    document.getElementById("optionSequence4").value = json["OptionSequence4"];
                    showRow("optionRow4");
                }
                if(parseInt(json["OptionCount"],10)>4){
                    document.getElementById("optionLabel5").value = json["OptionLabel5"];
                    document.getElementById("optionSequence5").value = json["OptionSequence5"];
                    showRow("optionRow5");
                }
                if(parseInt(json["OptionCount"],10)>5){
                    document.getElementById("optionLabel6").value = json["OptionLabel6"];
                    document.getElementById("optionSequence6").value = json["OptionSequence6"];
                    document.getElementById("addbuttonDiv").style.display='none';
                    showRow("optionRow6");
                }
             
            //OptionSequence1
            }
            
            else if("DropDown" == OptionType)
            {
                document.getElementById("dropdownOptions").value = json["dropdownOptions"];
                showRow("dropdownoptionsRow");
            }
                
                
                
                
             
           
            document.getElementById("load").style.display = 'none';
            
            if(document.getElementById("currStatus").value =='Active')
                showRow("editTr");
            showRow("createdTr");
                 
        }, 
        error: function(e){
            document.getElementById("load").style.display = 'none';
            alert("error-->"+e);
        }
    });
    
    
    
     
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Edit Question";
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
    
}



//doPublishSurveyForm
function doPublishSurveyForm(Element,serveyId,status){
    // alert('status-->'+status);
    if(Element.value = "P")
    {
        if(status=="Published"){
            alert("It is already Published");
        }
        else if(status=="Inactive")
        {
            //alert('in else iff');
            alert("InActive Record Cannot Be Published");
        }
        else{
            if(window.confirm("Do you want to change the status to publish")){
                window.location = "doPublishSurveyForm.action?surveyId="+serveyId;
            }
        }
    }
}



function doUpdateSurveyFormQuestion(){
    var surveyId=document.getElementById("surveyId").value;
    var questionId=document.getElementById("questionId").value;
 
    var questionTitle=document.getElementById("questionTitle").value;
    var optionType=document.getElementById("optionType").value;
    var querySequence=document.getElementById("querySequence").value;
    var optionCount=document.getElementById("optionCount").value;
    var optionLabel1=document.getElementById("optionLabel1").value;
    var optionLabel2=document.getElementById("optionLabel2").value;
    var optionLabel3=document.getElementById("optionLabel3").value;
    var optionLabel4=document.getElementById("optionLabel4").value;
    var optionLabel5=document.getElementById("optionLabel5").value;
    var optionLabel6=document.getElementById("optionLabel6").value;

    var optionSequence1=document.getElementById("optionSequence1").value;
    var optionSequence2=document.getElementById("optionSequence2").value;
    var optionSequence3=document.getElementById("optionSequence3").value;
    var optionSequence4=document.getElementById("optionSequence4").value;
    var optionSequence5=document.getElementById("optionSequence5").value;
    var optionSequence6=document.getElementById("optionSequence6").value;
    var dropdownOptions=document.getElementById("dropdownOptions").value;
    var isRequired=document.getElementById("isRequired").checked;
    var questionStatus=document.getElementById("QuestionnaireStatus").value;
    var placeHolder=document.getElementById("placeHolder").value;
    
 var isAllowDocuments=document.getElementById("allowDocuments").checked;
    var isAllowPictures=document.getElementById("allowPictures").checked;
    
    if(questionTitle.trim().length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please enter Query.</font>";
    }else if(optionType.trim().length==0){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please select OptionType.</font>";
    }else if(optionType.trim()=='Attachment' && isAllowDocuments==false&&isAllowPictures==false){
        document.getElementById('resultMessage').innerHTML = "<font color=red>Please check atleast one attachment type.</font>";
    }else
    {
        document.getElementById("load").style.display = 'block';
        hideRow('addTr');
        $.ajax({
            url:'doUpdateQuestionnaire.action?questionId='+questionId+'&surveyId='+surveyId+'&questionStatus='+questionStatus+'&placeHolder='+placeHolder+'&questionTitle='+escape(questionTitle)+"&optionType="+optionType+"&querySequence="+querySequence+"&optionCount="+optionCount
            +"&optionLabel1="+encodeURIComponent(optionLabel1)+"&optionLabel2="+encodeURIComponent(optionLabel2)+"&optionLabel3="+encodeURIComponent(optionLabel3)+"&optionLabel4="+encodeURIComponent(optionLabel4)+"&optionLabel5="+encodeURIComponent(optionLabel5)+"&optionLabel6="+encodeURIComponent(optionLabel6)+
            "&optionSequence1="+optionSequence1+"&optionSequence2="+optionSequence2+"&optionSequence3="+optionSequence3+"&optionSequence4="+optionSequence4+"&optionSequence5="+optionSequence5+"&optionSequence6="+optionSequence6+"&isRequired="+isRequired+"&dropdownOptions="+encodeURIComponent(dropdownOptions)+"&allowDocuments="+isAllowDocuments+"&allowPictures="+isAllowPictures,
            context: document.body,
            success: function(responseText) {
              
                document.getElementById("load").style.display = 'none';
                document.getElementById('resultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
            },
            error: function(e){
                showRow('addTr');
                document.getElementById("load").style.display = 'none';
                document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
            }
        });
    }
    return false;
}

//-------------------------------






function showServeyReviewDetails(surveyInfoId){
    
    var surveyId=document.getElementById("surveyId").value;
    //  var roleId = document.getElementById('roleId').value;
    //  hideRow('addTr');
    // hideRow('editTr');
    //  document.getElementById('resultMessage').innerHTML ='';
    //   var teamId = document.getElementById('teamId').value;
  
    //  document.getElementById("load").style.display = 'block';
  
    //    
    //    
    //    
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Servey Form Review Details";
    var overlay = document.getElementById('overlay');
    var specialBox = document.getElementById('specialBox');
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        //  window.location = "getSurveyReviewList.action?surveyId="+surveyId;
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
        document.getElementById("load").style.display = '';
        $.ajax({
            //url:'editJobposting.action?jobId='+jobId,//
            url:'showReviewDetails.action?surveyInfoId='+surveyInfoId+'&surveyId='+surveyId,//
            context: document.body,
            success: function(responseText) {
                // alert(responseText);
                var json = $.parseJSON(responseText);
                // alert("responseText--->"+responseText);
                //alert("json keys--->"+json.keys);
                var Name = json["Name"];
                
                var Phone = json["Phone"];
                var Email = json["Email"];
                var CreatedDate = json["CreatedDate"];
                // alert('CreatedDate-->'+CreatedDate)
                var Empno = json["Empno"];
                //  alert("Empno-->"+Empno);
                document.getElementById("sfName").value = Name;
                document.getElementById("sfPhone").value = Phone;
                document.getElementById("sfEmail").value = Email;
                document.getElementById("createdDate").innerHTML = CreatedDate;
                 if(Empno!='-1'){
                      document.getElementById("sfEmpno").value = Empno;
                }

                //document.getElementById("sfEmpno").value = Empno;
                var DynamicData = json["DynamicData"];
               
                 
                var key;
                for (key in DynamicData) {
                    if (DynamicData.hasOwnProperty(key)) {
        
                        var SubDynamicData =DynamicData[key];
                        
                        
                      
                        if(SubDynamicData["OptionType"]=='Checkbox'){
                            if($("#questionId"+key).length){
                                var selectedList = SubDynamicData["Answer"];
                                
                                var res = selectedList.split(","); 
                                for (var j = 0; j < res.length; ++j) {
                                    //  alert(res[j]);
                                    var options = document.getElementsByName("questionId"+key+"[]");
                                    //if($(options).length)
                                    // alert("hii");
                                    for(var i=0; options[i]; ++i){
                                          
                                        if(options[i].value==res[j]){
                                            options[i].checked=true;
                                        }
                                    }
                                }
                            }
                        
                       
                        }else if(SubDynamicData["OptionType"]=='RadioButton'){
                            if($("#questionId"+key).length){
                                var selectedList = SubDynamicData["Answer"];
                                var options = document.getElementsByName("questionId"+key);
                                for(var i=0; options[i]; ++i){
                                    if(options[i].value==selectedList){
                                        options[i].checked=true;
                                        break;
                                    }
                                }
                            }
                            
                        }else if(SubDynamicData["OptionType"]=='Attachment'){
                            document.getElementById("attachmentLink"+key).innerHTML='';
                            //attachmentLink
                           
                            if(SubDynamicData["Answer"]!='0'){
                                document.getElementById("attachmentLink"+key).innerHTML=" <font color=\"green\" size=\"2px\"><a style=\"color:#C00067;\" href=\"javascript:doSurveyAttachmentDownload('"+SubDynamicData["Answer"]+"');\">Download</a>";
                            }
                        }else if(SubDynamicData["OptionType"]=='Slider'){
                            if($("#questionId"+key).length)
                                document.getElementById("questionId"+key).value = SubDynamicData["Answer"];
                            document.getElementById("rangevalue"+key).innerHTML='';
                            //attachmentLink
                           
                            if(SubDynamicData["Answer"]!='0'){
                                document.getElementById("rangevalue"+key).innerHTML=SubDynamicData["Answer"];
                            }
                        }else if(SubDynamicData["OptionType"]=='ANONYMOUS'){
                            document.getElementById("anonymous"+key).innerHTML=" <font color=\"green\" size=\"2px\">"+SubDynamicData["Answer"]+"</font>";
                        }else {
                            //rangevalue
                            if($("#questionId"+key).length)
                                document.getElementById("questionId"+key).value = SubDynamicData["Answer"];
                        //}
                        // console.log(key + " = " + user[key]);
       
                        //}
                        }
                    }
                }
                // } 
                //                var jobExp = jobqulification.split("-");
                //                
                //                
                //                
                //                
                //               // var jobshifits = json["JobShifts"];
                //                var location = json["Location"];
                //               // var jobcountry = json["JobCountry"];
                //                var jobstatus = json["JobStatus"];
                //                var jobdescription = unescape(json["JobDescription"]);
                //                //var jobtechnical = unescape(json["JobTechnical"]);
                //               // var jobshiftskills = unescape(json["JobSoftSkills"]);
                //              
                //                var jobCreatedDate = json["JobCreatedDate"];
                //                var createdBy = json["CreatedBy"];
                //                var jobId = json["JobId"];
                //                var id = json["Id"];
                //                var Department = json["Department"];
                //                var HireType = json["HireType"];
               
           
                document.getElementById("load").style.display = 'none';
                 
            // getSlider(4,7);
                  
            }, 
            error: function(e){
                document.getElementById("load").style.display = 'none';
                alert("error-->"+e);
            }
        });
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
}




function surveyFieldsValidate() {
    var surveyType = document.getElementById("surveyType").value;
    var surveyTitle = document.getElementById("surveyTitle").value;
    var expiryDate = document.getElementById("expiryDate").value;
    var surveyDescription = document.getElementById("surveyDescription").value;

if(surveyType.trim().length!=0&&surveyTitle.trim().length!=0&&expiryDate.trim().length!=0&&surveyDescription.trim().length!=0){
    
    var one_day=1000*60*60*24;
    var selectExpiryDate  = new Date(expiryDate);
    
    var currentDate  = new Date(document.getElementById('currentDate').value);
       var timeDiff = parseInt((selectExpiryDate.getTime() - currentDate.getTime()),10);
var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
//return diffDays;
    if(parseInt(diffDays,10)<0){
        alert("Expiry date you selected is not valid.");
           return false;
       }else {
            return true;
       }
   
}else {
    alert("Please eneter mandatory fields!");
     return false;;
}


}


      function setRangeValue(rangeObj,val){
    

document.getElementById(val).value = rangeObj.value;
    
}


function surveyFormFieldsValidator(element) {
    var i=0;
    var fieldName='';
 //alert("In Field Length validator ESCV");
    if(element.value!=null&&(element.value!="")) {
        if(element.id=="surveyTitle") { 
            fieldName = 'Title';
            i=200;
        }
        if(element.id=="surveyDescription") { 
            fieldName = 'Description';
            i=5000;
        } if(element.id=="questionTitle") { 
            fieldName = 'Question';
            i=200;
        }
        
         if(element.value.replace(/^\s+|\s+$/g,"").length>i) {
            str=new String(element.value);
            element.value=str.substring(0,i);
            alert("The "+fieldName+" length must be less than "+i+" characters");
            element.focus();
            return false;
        }
        return true;
        
        
    }
}



function getSearchQuestionInfo(qobj,searchValue){

   //  document.getElementById("searchQLabelDiv").style.display='none'
                document.getElementById("searchLabelId").innerHTML = '';
            //    document.getElementById("searchQOptionDiv").style.display='none';
                document.getElementById("searchOptionId").innerHTML = '';
    if(qobj.value!=''){
      $.ajax({
            
            url:'getSearchQuestionInfo.action?questionId='+qobj.value,
           
            context: document.body,
            success: function(responseText) {
                // displaymessage = responseText;
         var json = $.parseJSON(responseText);
                // alert("responseText--->"+responseText);
                //alert("json keys--->"+json.keys);
                var Id = json["Id"];
                var Query = json["Query"];
                var OptionType = json["OptionType"];
                var CurrStatus = json["CurrStatus"];
                var Sequence = json["Sequence"];
                var SurveyId = json["SurveyId"];
                var RequiredFlag = json["RequiredFlag"];
           //  alert("Id-->"+Id+" Query-->"+Query+" OptionType-->"+OptionType+" CurrStatus-->"+CurrStatus+" Sequence-->"+Sequence+" SurveyId-->"+SurveyId+" RequiredFlag-->"+RequiredFlag);
              var OptionCount =0;
               var OptionLabel1 = '';
                var OptionSequence1 ='';
               var OptionLabel2 = '';
                var OptionSequence2 =''; 
                var OptionLabel3 = '';
                var OptionSequence3 =''; 
                var OptionLabel4 = '';
                var OptionSequence4 =''; 
                var OptionLabel5 = '';
                var OptionSequence5 ='';
                 var OptionLabel6 = '';
                var OptionSequence6 ='';
                if((OptionType == "Checkbox")||(OptionType == "RadioButton")){
                    
                    
                
                  OptionCount = json["OptionCount"];
                  OptionLabel1 = json["OptionLabel1"];
                  OptionSequence1 = json["OptionSequence1"];
                 if(parseInt(OptionCount,10)>1){
                       OptionLabel2 = json["OptionLabel2"];
                  OptionSequence2 = json["OptionSequence2"];
                 }if(parseInt(OptionCount,10)>2){
                       OptionLabel3 = json["OptionLabel3"];
                  OptionSequence3 = json["OptionSequence3"];
                 }if(parseInt(OptionCount,10)>3){
                       OptionLabel4 = json["OptionLabel4"];
                  OptionSequence4 = json["OptionSequence4"];
                 }if(parseInt(OptionCount,10)>4){
                       OptionLabel5 = json["OptionLabel5"];
                  OptionSequence5 = json["OptionSequence5"];
                 }if(parseInt(OptionCount,10)>5){
                       OptionLabel6 = json["OptionLabel6"];
                  OptionSequence6 = json["OptionSequence6"];
                 }
                 
                }
              //  document.getElementById("searchQLabelDiv").style.display='block'
                document.getElementById("searchLabelId").innerHTML = Query+"&nbsp;:";
               // document.getElementById("searchQOptionDiv").style.display='block'
                if(OptionType == "Textbox"){
                 document.getElementById("searchOptionId").innerHTML = "<input type='text' name='searchQuestion' id='searchQuestion' class='inputTextBlue' value='"+searchValue+"'/>";
                }else if(OptionType == "RadioButton"){
                    var optionData = '';
                    if(searchValue==OptionLabel1)
                    optionData = "<input type='radio' name='searchQuestion'  value='"+OptionLabel1+"' checked />&nbsp;&nbsp;"+OptionLabel1;
                else
                     optionData = "<input type='radio' name='searchQuestion'  value='"+OptionLabel1+"' />&nbsp;&nbsp;"+OptionLabel1;
                     if(parseInt(OptionCount,10)>1){
                         if(searchValue==OptionLabel2)
                          optionData = optionData+"<input type='radio' name='searchQuestion'  value='"+OptionLabel2+"' checked/>&nbsp;&nbsp;"+OptionLabel2;
                      else
                          optionData = optionData+"<input type='radio' name='searchQuestion'  value='"+OptionLabel2+"'/>&nbsp;&nbsp;"+OptionLabel2;
                     } if(parseInt(OptionCount,10)>2){
                         if(searchValue==OptionLabel3)
                          optionData = optionData+"<input type='radio' name='searchQuestion'  value='"+OptionLabel3+"' checked/>&nbsp;&nbsp;"+OptionLabel3;
                      else
                          optionData = optionData+"<input type='radio' name='searchQuestion'  value='"+OptionLabel3+"' />&nbsp;&nbsp;"+OptionLabel3;
                     } if(parseInt(OptionCount,10)>3){
                         if(searchValue==OptionLabel4)
                          optionData = optionData+"<input type='radio' name='searchQuestion'  value='"+OptionLabel4+"' checked/>&nbsp;&nbsp;"+OptionLabel4;
                      else
                         optionData = optionData+"<input type='radio' name='searchQuestion'  value='"+OptionLabel4+"' />&nbsp;&nbsp;"+OptionLabel4; 
                     } if(parseInt(OptionCount,10)>4){
                         if(searchValue==OptionLabel5)
                          optionData = optionData+"<input type='radio' name='searchQuestion'  value='"+OptionLabel5+"' checked/>&nbsp;&nbsp;"+OptionLabel5;
                      else
                           optionData = optionData+"<input type='radio' name='searchQuestion'  value='"+OptionLabel5+"' />&nbsp;&nbsp;"+OptionLabel5;
                     } if(parseInt(OptionCount,10)>5){
                         if(searchValue==OptionLabel6)
                          optionData = optionData+"<input type='radio' name='searchQuestion'  value='"+OptionLabel6+"' checked/>&nbsp;&nbsp;"+OptionLabel6;
                      else
                         optionData = optionData+"<input type='radio' name='searchQuestion'  value='"+OptionLabel6+"' />&nbsp;&nbsp;"+OptionLabel6; 
                     }
                     document.getElementById("searchOptionId").innerHTML = optionData;
                }else if(OptionType == "Checkbox"){
                    var result;
                   // if(searchValue.trim().length>0)
                     result = searchValue.split(", "); 
                    
                    var optionData = '';
                   // alert(result.length);
                    
                        if(result.indexOf(OptionLabel1)>-1)
                       optionData = "<input type='checkbox' name='searchQuestion'  value='"+OptionLabel1+"' checked/>&nbsp;&nbsp;"+OptionLabel1;
                   else
                      optionData = "<input type='checkbox' name='searchQuestion'  value='"+OptionLabel1+"' />&nbsp;&nbsp;"+OptionLabel1; 
                     if(parseInt(OptionCount,10)>1){
                         if(result.indexOf(OptionLabel2)>-1)
                          optionData = optionData+"<input type='checkbox' name='searchQuestion'  value='"+OptionLabel2+"' checked/>&nbsp;&nbsp;"+OptionLabel2;
                      else
                          optionData = optionData+"<input type='checkbox' name='searchQuestion'  value='"+OptionLabel2+"'/>&nbsp;&nbsp;"+OptionLabel2;
                     } if(parseInt(OptionCount,10)>2){
                         if(result.indexOf(OptionLabel3)>-1)
                          optionData = optionData+"<input type='checkbox' name='searchQuestion'  value='"+OptionLabel3+"' checked/>&nbsp;&nbsp;"+OptionLabel3;
                      else
                          optionData = optionData+"<input type='checkbox' name='searchQuestion'  value='"+OptionLabel3+"'/>&nbsp;&nbsp;"+OptionLabel3;
                     } if(parseInt(OptionCount,10)>3){
                          if(result.indexOf(OptionLabel4)>-1)
                          optionData = optionData+"<input type='checkbox' name='searchQuestion'  value='"+OptionLabel4+"' checked/>&nbsp;&nbsp;"+OptionLabel4;
                      else
                          optionData = optionData+"<input type='checkbox' name='searchQuestion'  value='"+OptionLabel4+"'/>&nbsp;&nbsp;"+OptionLabel4;
                     } if(parseInt(OptionCount,10)>4){
                         if(result.indexOf(OptionLabel5)>-1)
                          optionData = optionData+"<input type='checkbox' name='searchQuestion'  value='"+OptionLabel5+"' checked/>&nbsp;&nbsp;"+OptionLabel5;
                      else
                          optionData = optionData+"<input type='checkbox' name='searchQuestion'  value='"+OptionLabel5+"'/>&nbsp;&nbsp;"+OptionLabel5;
                     } if(parseInt(OptionCount,10)>5){
                          if(result.indexOf(OptionLabel6)>-1)
                          optionData = optionData+"<input type='checkbox' name='searchQuestion'  value='"+OptionLabel6+"' checked/>&nbsp;&nbsp;"+OptionLabel6;
                      else
                           optionData = optionData+"<input type='checkbox' name='searchQuestion'  value='"+OptionLabel6+"' />&nbsp;&nbsp;"+OptionLabel6;
                     }  
                   
                    
                     document.getElementById("searchOptionId").innerHTML = optionData;
                }
               // document.getElementById("load").style.display = 'none';
                //document.getElementById('resultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
            // document.getElementById('fileType').value = "";
            },
            error: function(e){
               // showRow('addTr');
               // document.getElementById("load").style.display = 'none';
               // document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
     
                
            }
        });
    }
    
    
    
}

//---------------------



function getSearchQuestionInfo2(qobj,searchValue){

   //  document.getElementById("searchQLabelDiv").style.display='none'
                document.getElementById("searchLabelId2").innerHTML = '';
            //    document.getElementById("searchQOptionDiv").style.display='none';
                document.getElementById("searchOptionId2").innerHTML = '';
    if(qobj.value!=''){
      $.ajax({
            
            url:'getSearchQuestionInfo.action?questionId='+qobj.value,
           
            context: document.body,
            success: function(responseText) {
                // displaymessage = responseText;
         var json = $.parseJSON(responseText);
                // alert("responseText--->"+responseText);
                //alert("json keys--->"+json.keys);
                var Id = json["Id"];
                var Query = json["Query"];
                var OptionType = json["OptionType"];
                var CurrStatus = json["CurrStatus"];
                var Sequence = json["Sequence"];
                var SurveyId = json["SurveyId"];
                var RequiredFlag = json["RequiredFlag"];
      //     alert("Id-->"+Id+" Query-->"+Query+" OptionType-->"+OptionType+" CurrStatus-->"+CurrStatus+" Sequence-->"+Sequence+" SurveyId-->"+SurveyId+" RequiredFlag-->"+RequiredFlag);
              var OptionCount =0;
               var OptionLabel1 = '';
                var OptionSequence1 ='';
               var OptionLabel2 = '';
                var OptionSequence2 =''; 
                var OptionLabel3 = '';
                var OptionSequence3 =''; 
                var OptionLabel4 = '';
                var OptionSequence4 =''; 
                var OptionLabel5 = '';
                var OptionSequence5 ='';
                 var OptionLabel6 = '';
                var OptionSequence6 ='';
                if((OptionType == "Checkbox")||(OptionType == "RadioButton")){
                    
                    
                
                  OptionCount = json["OptionCount"];
                  OptionLabel1 = json["OptionLabel1"];
                  OptionSequence1 = json["OptionSequence1"];
                 if(parseInt(OptionCount,10)>1){
                       OptionLabel2 = json["OptionLabel2"];
                  OptionSequence2 = json["OptionSequence2"];
                 }if(parseInt(OptionCount,10)>2){
                       OptionLabel3 = json["OptionLabel3"];
                  OptionSequence3 = json["OptionSequence3"];
                 }if(parseInt(OptionCount,10)>3){
                       OptionLabel4 = json["OptionLabel4"];
                  OptionSequence4 = json["OptionSequence4"];
                 }if(parseInt(OptionCount,10)>4){
                       OptionLabel5 = json["OptionLabel5"];
                  OptionSequence5 = json["OptionSequence5"];
                 }if(parseInt(OptionCount,10)>5){
                       OptionLabel6 = json["OptionLabel6"];
                  OptionSequence6 = json["OptionSequence6"];
                 }
                 
                }
              //  document.getElementById("searchQLabelDiv").style.display='block'
                document.getElementById("searchLabelId2").innerHTML = Query+"&nbsp;:";
               // document.getElementById("searchQOptionDiv").style.display='block'
                if(OptionType == "Textbox"){
                 document.getElementById("searchOptionId2").innerHTML = "<input type='text' name='searchQuestion2' id='searchQuestion2' class='inputTextBlue' value='"+searchValue+"'/>";
                }else if(OptionType == "RadioButton"){
                    var optionData = '';
                    if(searchValue==OptionLabel1)
                    optionData = "<input type='radio' name='searchQuestion2'  value='"+OptionLabel1+"' checked />&nbsp;&nbsp;"+OptionLabel1;
                else
                     optionData = "<input type='radio' name='searchQuestion2'  value='"+OptionLabel1+"' />&nbsp;&nbsp;"+OptionLabel1;
                     if(parseInt(OptionCount,10)>1){
                         if(searchValue==OptionLabel2)
                          optionData = optionData+"<input type='radio' name='searchQuestion2'  value='"+OptionLabel2+"' checked/>&nbsp;&nbsp;"+OptionLabel2;
                      else
                          optionData = optionData+"<input type='radio' name='searchQuestion2'  value='"+OptionLabel2+"'/>&nbsp;&nbsp;"+OptionLabel2;
                     } if(parseInt(OptionCount,10)>2){
                         if(searchValue==OptionLabel3)
                          optionData = optionData+"<input type='radio' name='searchQuestion2'  value='"+OptionLabel3+"' checked/>&nbsp;&nbsp;"+OptionLabel3;
                      else
                          optionData = optionData+"<input type='radio' name='searchQuestion2'  value='"+OptionLabel3+"' />&nbsp;&nbsp;"+OptionLabel3;
                     } if(parseInt(OptionCount,10)>3){
                         if(searchValue==OptionLabel4)
                          optionData = optionData+"<input type='radio' name='searchQuestion2'  value='"+OptionLabel4+"' checked/>&nbsp;&nbsp;"+OptionLabel4;
                      else
                         optionData = optionData+"<input type='radio' name='searchQuestion2'  value='"+OptionLabel4+"' />&nbsp;&nbsp;"+OptionLabel4; 
                     } if(parseInt(OptionCount,10)>4){
                         if(searchValue==OptionLabel5)
                          optionData = optionData+"<input type='radio' name='searchQuestion2'  value='"+OptionLabel5+"' checked/>&nbsp;&nbsp;"+OptionLabel5;
                      else
                           optionData = optionData+"<input type='radio' name='searchQuestion2'  value='"+OptionLabel5+"' />&nbsp;&nbsp;"+OptionLabel5;
                     } if(parseInt(OptionCount,10)>5){
                         if(searchValue==OptionLabel6)
                          optionData = optionData+"<input type='radio' name='searchQuestion2'  value='"+OptionLabel6+"' checked/>&nbsp;&nbsp;"+OptionLabel6;
                      else
                         optionData = optionData+"<input type='radio' name='searchQuestion2'  value='"+OptionLabel6+"' />&nbsp;&nbsp;"+OptionLabel6; 
                     }
                     document.getElementById("searchOptionId2").innerHTML = optionData;
                }else if(OptionType == "Checkbox"){
                    var result;
                   // if(searchValue.trim().length>0)
                     result = searchValue.split(", "); 
                    
                    var optionData = '';
                   // alert(result.length);
                    
                        if(result.indexOf(OptionLabel1)>-1)
                       optionData = "<input type='checkbox' name='searchQuestion2'  value='"+OptionLabel1+"' checked/>&nbsp;&nbsp;"+OptionLabel1;
                   else
                      optionData = "<input type='checkbox' name='searchQuestion2'  value='"+OptionLabel1+"' />&nbsp;&nbsp;"+OptionLabel1; 
                     if(parseInt(OptionCount,10)>1){
                         if(result.indexOf(OptionLabel2)>-1)
                          optionData = optionData+"<input type='checkbox' name='searchQuestion2'  value='"+OptionLabel2+"' checked/>&nbsp;&nbsp;"+OptionLabel2;
                      else
                          optionData = optionData+"<input type='checkbox' name='searchQuestion2'  value='"+OptionLabel2+"'/>&nbsp;&nbsp;"+OptionLabel2;
                     } if(parseInt(OptionCount,10)>2){
                         if(result.indexOf(OptionLabel3)>-1)
                          optionData = optionData+"<input type='checkbox' name='searchQuestion2'  value='"+OptionLabel3+"' checked/>&nbsp;&nbsp;"+OptionLabel3;
                      else
                          optionData = optionData+"<input type='checkbox' name='searchQuestion2'  value='"+OptionLabel3+"'/>&nbsp;&nbsp;"+OptionLabel3;
                     } if(parseInt(OptionCount,10)>3){
                          if(result.indexOf(OptionLabel4)>-1)
                          optionData = optionData+"<input type='checkbox' name='searchQuestion2'  value='"+OptionLabel4+"' checked/>&nbsp;&nbsp;"+OptionLabel4;
                      else
                          optionData = optionData+"<input type='checkbox' name='searchQuestion2'  value='"+OptionLabel4+"'/>&nbsp;&nbsp;"+OptionLabel4;
                     } if(parseInt(OptionCount,10)>4){
                         if(result.indexOf(OptionLabel5)>-1)
                          optionData = optionData+"<input type='checkbox' name='searchQuestion2'  value='"+OptionLabel5+"' checked/>&nbsp;&nbsp;"+OptionLabel5;
                      else
                          optionData = optionData+"<input type='checkbox' name='searchQuestion2'  value='"+OptionLabel5+"'/>&nbsp;&nbsp;"+OptionLabel5;
                     } if(parseInt(OptionCount,10)>5){
                          if(result.indexOf(OptionLabel6)>-1)
                          optionData = optionData+"<input type='checkbox' name='searchQuestion2'  value='"+OptionLabel6+"' checked/>&nbsp;&nbsp;"+OptionLabel6;
                      else
                           optionData = optionData+"<input type='checkbox' name='searchQuestion2'  value='"+OptionLabel6+"' />&nbsp;&nbsp;"+OptionLabel6;
                     }  
                   
                    
                     document.getElementById("searchOptionId2").innerHTML = optionData;
                }
               // document.getElementById("load").style.display = 'none';
                //document.getElementById('resultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
            // document.getElementById('fileType').value = "";
            },
            error: function(e){
               // showRow('addTr');
               // document.getElementById("load").style.display = 'none';
               // document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
     
                
            }
        });
    }
    
    
    
    //------------------------------------------------------------------------
    
    
}



function checkSequance(obj){
    var seq = obj.value;
    
   if((seq=="1") || (seq=="2") || (seq=="3") || (seq=="4") || (seq=="5") || (seq=="6") || (seq=="7") || (seq=="8") || (seq=="9")){
       
   
      // return true;;
   }else {
       
       alert("Please enter sequance from 1-9");
       obj.value="";
     //  return false;
   }
    
    
}


function doUpdateSequance() {
    var questionCount = $("#questionCount").val();
    var surveyId = $("#surveyId").val();
    var myObj = {};
var index = 0;
    for ( var i = 1; i<= questionCount;  i++ ) {
        var qseq = $("#questSeq_"+i).val();
         if((qseq=="1") || (qseq=="2") || (qseq=="3") || (qseq=="4") || (qseq=="5") || (qseq=="6") || (qseq=="7") || (qseq=="8") || (qseq=="9")){
            var qId = $("#qestId_"+i).val();
            myObj[qId] = qseq;
        }else {
           // alert("Please enter numbers");
           index = parseInt(index,10)+1;
            
        }
        
       // alert(index);
        
    }
    
    if( parseInt(index,10)==0){
    var json = JSON.stringify(myObj);
    
   document.getElementById("loadMsg").style.display = 'block';
    $.ajax({
            // url:'editJobposting.action?jobId='+jobId,//
            //url:'addJobposting.action?jobtitle='+jobtitle+'&jobtagline='+jobtagline+'&jobposition='+jobposition+'&jobqulification='+jobqulification+'&jobshifits='+jobshifits+"&location="+location+"&jobcountry="+jobcountry+"&jobstatus="+jobstatus+"&jobdescription="+jobdescription+"&jobtechnical="+jobtechnical+"&jobshiftskills="+jobshiftskills,
            url:'doUpdateSequance.action',
            data:{questionSequanceData: json},
            contentType: 'application/json',
            type: 'GET',
            context: document.body,
            success: function(responseText) {
                  var json = $.parseJSON(responseText);
                // alert("responseText--->"+responseText);
                //alert("json keys--->"+json.keys);
                document.getElementById("loadMsg").style.display = 'none';
                
                window.location="getQuestionnaireList.action?surveyId="+surveyId;
             //  alert(json["message"]);
           
              //  document.getElementById("load").style.display = 'none';
               // document.getElementById('resultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
            // document.getElementById('fileType').value = "";
            },
            error: function(e){
                 document.getElementById("loadMsg").style.display = 'none';
                alert("Please try again");
               // showRow('addTr');
                //document.getElementById("load").style.display = 'none';
                //document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
     
                
            }
        });
    
    }else {
         document.getElementById("loadMsg").style.display = 'none';
         alert("Please enter numbers");
    }

}


var specialKeys = new Array();
        specialKeys.push(8); //Backspace
        function IsNumeric(e,obj) {
		var val=obj.value
		if(val.lenght==1){
		//alert(val);
            var keyCode = e.which ? e.which : e.keyCode
            var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1);
            //document.getElementById("error").style.display = ret ? "none" : "inline";
            return ret;
			}else{
			obj.value=val.substring(0,1);
		//	alert("hai");
        }
		}
                
                
function doDeleteQuestion(questionId) {
               
var surveyId=document.getElementById("surveyId").value;

var r=confirm("Are you sure, you want to delete Question");
if (r==true)
  {
  window.location = "doDeleteQuestion.action?questionId="+questionId+"&surveyId="+surveyId;
  }

}

 function updateSurveyFormExpiryDate(){    
     document.getElementById("resultMessage123").innerHTML = "";
     document.getElementById('resultMessage').innerHTML ="";
    var existingexpiryDate = document.getElementById("existedLabelId1").innerHTML;
    //  alert('existingexpiryDate-->'+existingexpiryDate);
    var expiryDate = document.getElementById("selectDateFrom").value;
    
    var currentDate=document.getElementById('currentDateCol').value;
    
    //  alert("expiryDate-->"+expiryDate);  
    if(expiryDate!="" && expiryDate!=null)
    {
        var date1 = new Date(currentDate.toString());
        var date2 = new Date(expiryDate.toString()); 
        var date1_ms = date1.getTime();
       // alert(date1_ms);
        var date2_ms = date2.getTime();
      //   alert(date2_ms);
        if(date1_ms<date2_ms) {       
            var difference_ms = Math.abs(date1_ms - date2_ms);             
            var ONE_DAY = 1000 * 60 * 60 * 24;        
            var totalDays= Math.round(difference_ms/ONE_DAY);
            var totMon= Math.round(totalDays/30);                  
            var surveyId=document.getElementById("survyIdCol").value;
            
                 $.ajax({
            url:'updateSurveyFormExpiryDate.action?expiryDate='+expiryDate+'&surveyId='+surveyId,
            context: document.body,
            success: function(responseText) {
               
                document.getElementById("load").style.display = 'none';
                document.getElementById('resultMessage').innerHTML = responseText;//"<font color=green>File uploaded successfully</font>";
            },
            error: function(e){
                document.getElementById("load").style.display = 'none';
                document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";  
            }
        });
        }
        else{
             document.getElementById("resultMessage123").innerHTML="<font color=red>Expiry Date Should Be Greater Than The Current Date.</font>";
           
        }
    }
    else{
        document.getElementById("resultMessage123").innerHTML="<font color=red>Expiry Date Should Not Be Empty.</font>";
   
    }
       return false; 
}

function serviceFunctionality(Element,serveyId,status)
{
     
    if(Element.value="I")
    {
        if(status=="InActive"){
            alert("It is already in InActive");
        }
        else
        {
            if(window.confirm("Do you want to change the status to InActive")){
                window.location = "doInactiveSurveyForm.action?surveyId="+serveyId;
            }
            
        }

    }
}
function serviceRPFunctionality(Element,serveyId,ExpiryDate,type)
{
    // alert(serveyId);
     document.getElementById("resultMessage123").innerHTML="";
     document.getElementById('resultMessage').innerHTML ="";
    if(Element.value = "RP")
    {
        //alert(Element.value);
       
        document.getElementById("headerLabel").style.color="white";
        if(type=='Republish'){
            document.getElementById("headerLabel").innerHTML="Republish the Survey Form";
        }else{
            document.getElementById("headerLabel").innerHTML="Expiry Date Updation";
        }
        
        document.getElementById("selectDateFrom").value = "";
        document.getElementById('existedLabelId1').innerHTML =ExpiryDate;
        
        showRow('addTr');
               
        document.getElementById('survyIdCol').value=serveyId;
        var overlay = document.getElementById('overlay');
        var specialBox1 = document.getElementById('specialBox1');

        overlay.style.opacity = .8;
        if(overlay.style.display == "block"){
            overlay.style.display = "none";
            specialBox1.style.display = "none";

        //   document.getElementById("frmDBGrid").submit();

        }
        else {
            overlay.style.display = "block";
            specialBox1.style.display = "block";
        }
    }
}
 function serviceRAFunctionality(Element,serveyId,status)
{
   
    if(Element.value = "RA")
    {
        if(status=="Active"){
            alert("It is already in Active")
        }
        else{
            if(window.confirm("Do you want to change the status to Active click yes to continue")){
                window.location = "doActiveSurveyForm.action?surveyId="+serveyId;
            }
   
        }
    }
}
 function toggleOverlay(){
    //alert("hi");
    document.getElementById('resultMessage').innerHTML ='';
               
    // hideRow('downloadTr');
    // showRow('uploadTr');
    //document.getElementById("downloadTr").style.display = 'none';
    //document.getElementById("uploadTr").style.display = '';
         
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="RP";
    showRow('addTr');
    var overlay = document.getElementById('overlay');
    var specialBox1 = document.getElementById('specialBox1');
    //  hideRow("approvedBy");
    //hideRow("tlcommentsTr");
    // hideRow("hrcommentsTr");
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox1.style.display = "none";
              
        document.getElementById("frmDBGrid").submit();
                
    }
    else {
        overlay.style.display = "block";
        specialBox1.style.display = "block";
    }
            
    //  window.location = "jobSearch.action";
    document.getElementById("frmDBGrid").submit();
}
 function internalType()
{
    //  alert("internalType-->"+res.value);
            
    var survey=document.getElementById('surveyType').value;
                 
    if(survey=="I")
    {
        //  alert("in if ");
        document.getElementById('anonymousLabelPost').style.display="block";
        document.getElementById('anonymousCheckBox').style.display="block";
                      
    }
    else if(survey=="E")
    {
        document.getElementById('anonymousLabelPost').style.display="none";
        document.getElementById('anonymousCheckBox').style.display="none";
                          
    }
}

 function customizeTextMessage() {
    var checkbox = document.getElementById("customCheckBox").checked;   
    if (checkbox) {             
        document.getElementById('my_box').style.display = "block";
    }
    else {            
        //  document.getElementById('surveyFormCustomizedTextBox').value="";
        document.getElementById('my_box').style.display = "none";
                
    }
}

function commaValidator(element) {
    var x = element;

    var f=x.value;

    var ch1=f.substring(f.length-2,f.length-1);

    var ch2=f.substring(f.length-1,f.length);

    if(((ch1==ch2) && (ch1==',')) || (f.charAt(0)==',')){

        x.value = f.substring(0,f.length-1);
    }else{
        x.value = f.substring(0,f.length);
    }

    var r=x.value;
    if(ch2==','){
        r=r.substring(0,r.length-1);
        var n = r.lastIndexOf(",")+1;
        var lastSkill=r.substring(n,r.length);
        var finalval=r.substring(0,n);
        var a=r.split(',');
        var i=a.length;
        if(lastSkill.length>35){
            lastSkill=lastSkill.substring(0,34);
            finalval=finalval+lastSkill;
            alert('skill length should be less than 35 characters');
        }
        else{
            finalval=finalval+lastSkill+",";
        }
        // finalval=finalval+lastSkill;
        //alert(lastSkill);
        x.value=finalval;
    //                    for( n=0;n<i;n++){
    //                        if(a[n].length>34){
    //                            alert('skill length should be less than 35 characters');
    //                            x.value = r.substring(0,r.length-1);
    //                        }
    //                    } 
    }
}

