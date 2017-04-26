var categories = new Array();

categories['Hubble'] = new Array('Employee','Sales','Operations','Marketing','HR');

categories['Office Supplier'] = new Array('Paper','IDCards');

categories['HR'] = new Array('Salary','PayRoll','Healthcare');

categories['SysAdmin'] = new Array('SoftwareInstall', 'Systems', 'Network');

categories['MiracleCity'] = new Array('Air Conditioning','Audit','Carpentry','Civil Works','Electrical','Equipment','Gardening','Inventory Control','Janitors','Painting','Pest Control','Plumbing','Security','Sweepers','Vehicles');

categories['Recruiting'] = new Array('Consultant','Requirement','Activity'); 

categories['Sales'] = new Array('Activity','Opportunties','Requirement','Activity'); 

// For Configuring Catagories and Subcatagories Options in Select Box


function getSubCatagories(form,index) {
    cntrySel = document.getElementById('categoryId');
    
    catagoryList = categories[cntrySel.value];
    if(catagoryList != null){
        setSubCatagories('subCategoryId', catagoryList, catagoryList);
    }
}

// Update Options Of Given Region

function setSubCatagories(fieldId, newOptions, newValues) {
    selectedField = document.getElementById(fieldId);
    selectedField.options.length = 0;
    for (i=0; i<newOptions.length; i++) {
        selectedField.options[selectedField.length] = new Option(newOptions[i], newValues[i]);
    }
}

function validateIssueForm(){
var issueName=document.getElementById("issueName").value;
var customerName=document.getElementById("customerName").value;
var customerId=document.getElementById("customerId").value; 

var completeTable=document.getElementById("completeTable");

var assignedToUID=document.getElementById("assignedToUID").value;
var preAssignEmpId=document.getElementById("preAssignEmpId").value;

var postAssignedToUID=document.getElementById("postAssignedToUID").value;
var postAssignEmpId=document.getElementById("postAssignEmpId").value;

    if(customerName == "") {
        document.getElementById("customerName").value = "";
        document.getElementById("customerId").value = "";
        clearTable();
        hideScrollBar();
        alert('please select customerName from Suggestion List');
        return false;
        }else if(customerId == "") {
        document.getElementById("customerName").value = "";
        document.getElementById("customerId").value = "";
        clearTable();
        hideScrollBar();
        alert('please Re select customerName from Suggestion List');
        return false;
        }if(issueName==""){
        alert('please enter issueName');
        return false;
        }else if(completeTable.style.visible == true) {
        alert('Please Select From Suggestion List');
        return false;
        }else if(postAssignedToUID != "") {
                if(assignedToUID == "") {
                    document.getElementById("postAssignedToUID").value="";
                    document.getElementById("postAssignEmpId").value="";

                    document.getElementById("assignedToUID").value="";
                    document.getElementById("preAssignEmpId").value="";

                alert('please select Primary Assign Employee from Suggestion List');
                return false;
                }else if(preAssignEmpId == "") {
                     document.getElementById("postAssignedToUID").value="";
                    document.getElementById("postAssignEmpId").value="";

                    document.getElementById("assignedToUID").value="";
                    document.getElementById("preAssignEmpId").value="";

                alert('please Re select Primary Assign Employee from Suggestion List');
                return false;
                }
        }else if((assignedToUID != "")&&(preAssignEmpId == "")) {
        
        var r=confirm("You are not selected primary person from suggestion list.Do you want to Continue ?");
        if(r==false) {
        document.getElementById("assignedToUID").value = "";
        document.getElementById("preAssignEmpId").value = "";
        clearTable();
        hideScrollBar();
        }
        return r;
        }
        else if((postAssignedToUID != "")&&(postAssignEmpId == "")) {
        
        var r=confirm("You are not selected Secondary person from suggestion list.Do you want to Continue ?");
        if(r==false) {
        document.getElementById("postAssignedToUID").value = "";
       document.getElementById("postAssignEmpId").value = "";
        clearTable();
        hideScrollBar();
        }
        return r;
        }



//completeTable.style.visible = false;


    return true;
}

 function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode!=46 && charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    
    return true;
}

function validateTaskForm(){
var title=document.getElementById("title").value;
var resourceType = document.getElementById("resourceType").value;
//alert(resourceType);
var customerName = "";
    if(resourceType == 'e') {
       customerName =document.getElementById("customerName").value;
    }
var customerId=document.getElementById("customerId").value; 

var completeTable=document.getElementById("completeTable");

var assignedToUID=document.getElementById("assignedToUID").value;
var preAssignEmpId=document.getElementById("preAssignEmpId").value;

var postAssignedToUID=document.getElementById("postAssignedToUID").value;
var postAssignEmpId=document.getElementById("postAssignEmpId").value;

//var projectName = document.getElementById("projectName").value;
var projectId = document.getElementById("projectId").value;

/*if(projectName.length > 0 )
{
    if(projectId.length <= 0)
    {
        alert("Please select From Selection List Only!");
document.getElementById("projectName").value = "";
document.getElementById("projectId").value = "";
        return false;
    }
}*/
if(resourceType == 'e') {
    if(customerName == "") {
        document.getElementById("customerName").value = "";
        document.getElementById("customerId").value = "";
        clearTable();
        hideScrollBar();
        alert('please select customerName from Suggestion List');
        return false;
        }else if(customerId == "") {
        document.getElementById("customerName").value = "";
        document.getElementById("customerId").value = "";
        clearTable();
        hideScrollBar();
        alert('please Re select customerName from Suggestion List');
        return false;
        }
}else {
    if(customerId == "-1") {
         alert('please select customerName');
         return false;
    }
} 
        
        
        
        if(title==""){
        
        alert('please enter title');
        return false;
        }else if(completeTable.style.visible == true) {
        alert('Please Select From Suggestion List');
        return false;
        }else if(postAssignedToUID != "") {
                if(assignedToUID == "") {
                    document.getElementById("postAssignedToUID").value="";
                    document.getElementById("postAssignEmpId").value="";

                    document.getElementById("assignedToUID").value="";
                    document.getElementById("preAssignEmpId").value="";

                alert('please select Primary Assign Employee from Suggestion List');
                return false;
                }else if(preAssignEmpId == "") {
                     document.getElementById("postAssignedToUID").value="";
                    document.getElementById("postAssignEmpId").value="";

                    document.getElementById("assignedToUID").value="";
                    document.getElementById("preAssignEmpId").value="";

                alert('please Re select Primary Assign Employee from Suggestion List');
                return false;
                }
        }else if((assignedToUID != "")&&(preAssignEmpId == "")) {
        
        var r=confirm("You are not selected primary person from suggestion list.Do you want to Continue ?");
        if(r==false) {
        document.getElementById("assignedToUID").value = "";
        document.getElementById("preAssignEmpId").value = "";
        clearTable();
        hideScrollBar();
        }
        return r;
        }
        else if((postAssignedToUID != "")&&(postAssignEmpId == "")) {
        
        var r=confirm("You are not selected Secondary person from suggestion list.Do you want to Continue ?");
        if(r==false) {
        document.getElementById("postAssignedToUID").value = "";
       document.getElementById("postAssignEmpId").value = "";
        clearTable();
        hideScrollBar();
        }
        return r;
        }



//completeTable.style.visible = false;
return true;

    //return true;
}
