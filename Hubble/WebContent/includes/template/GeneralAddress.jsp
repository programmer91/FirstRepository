<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<table width="415" cellpadding="1" cellspacing="1" border="0">
    <tr>
        <td class="fieldLabel">Add.L1:</td>
        <td colspan="3">
            <s:textfield name="addressLine1" id="addressLine1" cssClass="inputTextBlueAddress" onchange="fieldLengthValidator(this);" onkeypress="return handleEnter(this,event);" theme="simple"/>
        </td>
    </tr>
    <tr>
        <td class="fieldLabel">Add.L2:</td>
        <td colspan="3"><s:textfield name="addressLine2" id="addressLine2" cssClass="inputTextBlueAddress" onchange="fieldLengthValidator(this);" onkeypress="return handleEnter(this,event);" theme="simple"/> </td>
    </tr>
    <tr>
        <td class="fieldLabel">City:</td>
        <td><s:textfield name="city" id="city" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);" onkeypress="return handleEnter(this,event);" theme="simple"/> </td>
        <td class="fieldLabel">M.Stop:</td>
        <td><s:textfield name="mailStop" id="mailStop" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);" onkeypress="return handleEnter(this,event);" theme="simple"/></td>
    </tr>
    <tr>
        <td class="fieldLabel">State:<font color="red" style="bold">&curren;</font></td>
        
        <td>
            <s:select 
                list="statesList"   
                name="state" 
                id="state" 
                cssClass="inputSelect" 
                headerKey="-1"
                headerValue="--Please Select--"
                onkeypress="return handleEnter(this,event);"
                theme="simple"
                vlaue="%{currentAccount.state}"
            />
        </td>
        <td class="fieldLabel">Country:</td>
        <td>
            <s:if test="%{country == null}">
                <s:select 
                    list="countryList" 
                    name="country" 
                    id="country" 
                    onchange="getStateData(this),checkCountry(this);"
                    cssClass="inputSelect"
                    headerKey="USA"
                    headerValue="USA"
                    theme="simple"
                    value="USA"
                />
            </s:if>
            <s:else>
                
                <s:select 
                    list="countryList" 
                    name="country" 
                    id="country" 
                    cssClass="inputSelect"
                    headerKey="-1"
                    headerValue="--Please Select--"
                    onchange="getStateData(),checkCountry();"
                    onkeypress="return handleEnter(this,event);"
                    theme="simple"
                    value="%{country}"
                />
                
            </s:else>
        </td>
    </tr>
    <tr>
        <td class="fieldLabel">Zip:</td>
        <td><s:textfield name="zip" id="zip" cssClass="inputTextBlue" onchange="fieldLengthValidator(this);" onkeypress="return handleEnter(this,event);" theme="simple"/></td>
    </tr>    
</table> 
