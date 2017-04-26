<%-- 
    Document   : Demo
    Created on : Aug 25, 2013, 2:08:23 AM
    Author     : itg51
--%>

<%@page import="org.apache.struts2.ServletActionContext"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Technical Reviews Consultant </title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>"></link>

<script type="text/javascript">
    function showRating()
    {
        var selectedValue=document.getElementById("operationType").value;
        //alert("selectedvalue"+selectedValue);
        if(selectedValue=="Review")
            {
        document.getElementById("ratingtr").style.display='table-row';
        document.getElementById("refertr").style.display='none';
       // return false;
    }
    else if(selectedValue=="Forward")
    {
        document.getElementById("refertr").style.display='table-row';
        document.getElementById("ratingtr").style.display='none';
        //return false;
    }
    
}
    function validateFields()
    {
    //alert("lhkhkj");
      var selectedValue=document.getElementById("operationType").value;
   // var rating;
    var email;
      var comments=document.getElementById("comments").value;
      
   // alert("lhkhkj"+selectedValue);
      if(selectedValue==null || selectedValue=="")
          {
              alert("Please Select the Review Type");
              return false;
          }
       else if(selectedValue=="Review")
           {
             //  alert("review");
                var rating=document.frmConsultantForm.rateing.value;
               // alert("djfgdhgfdfh"+rating);
              if(rating==null||rating=="")
                  {
                      alert("Please give rating");
                      return false;
                  }
               else if(comments==null || comments=="")
                   {
                       alert("Please give comments");
                       return false;
                   }
           }
        else if(selectedValue == "Forward")
           {
               email=document.getElementById("referTo").value;
              if(email==null || email=="")
                  {
                      alert("Please give an email id");
                      return false;
                  }
             else if(comments==null || comments=="")
                  {
                      alert("Please give comments");
                      return false;
                  }
    }
    return true;
}

function checkEmail(element)
 {
     if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(element.value)){return(true);
 }element.value="";
 alert("Invalid E-mail Address! Please re-enter.");
 return(false);
 } 
</script>


    </head>
    <body  class="bodyGeneral" oncontextmenu="return false;">
        <table class="templateTable705x440" align="center" cellpadding="0" cellspacing="0">
    <tr class="headerBg">
                     <td valign="top">
                              <s:include value="/includes/template/TechReviewsHeader.jsp"/>
                     </td>
    </tr>

    <tr>
        <td width="135px">
            <img alt="Hubble" 
                 src="<s:url value="/includes/images/img1.jpg"/>"    
                 width="1020px" 
                 height="190px">
        </td>
    </tr>
        <tr>
            <td  class="fieldLabelCenter">
                <b><% if(request.getAttribute("resultMessage") != null){
                                            out.println(request.getAttribute("resultMessage"));
                                            session.removeAttribute("resultMessage");
                                        }%></b>
            </td>
        </tr>
      
        <tr>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
        </tr>
           <tr class="footerBg">
    <td align="center"><s:include value="/includes/template/TechReviewsFooter.jsp"/></td>
</tr>
        </table>


     
       
<!--<p>If you click on the "Hide" button, I will disappear.</p>

<button id="show">Show</button>-->

    </body>
</html>
