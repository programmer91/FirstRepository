<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<s:form theme="simple">
<table width="30%" >
    <tr>
    <td class="fieldLabel"  style="text-align:left;">Employee&nbsp;No:</td><td><s:property value="%{empNo}"/></td>
                    </tr>
                    <tr>
                    <td class="fieldLabel"  style="text-align:left;">Login Id:</td><td><s:property value="%{id}"/></td>
                    </tr>
                    <tr>
                    <td class="fieldLabel" style="text-align:left;">Name:</td><td><s:property value="%{name}" /></td>
                    </tr>
                    <tr>
                    <td class="fieldLabel" style="text-align:left;">Department&nbsp;Id:</td><td><s:property value="%{departmentId}"/></td>
                    </tr>
                    <tr>
                        <td class="fieldLabel" style="text-align:left;">Working&nbsp;Country:</td><td><s:property value="%{WorkingCountry}"/></td>
                    </tr>
                    <tr>
                        <td class="fieldLabel" style="text-align:left;">Phone&nbsp;Number: </td><td><s:property value="%{phoneNo}"/></td>
                    </tr>
                    <tr>
                        <td class="fieldLabel" style="text-align:left;">Mail&nbsp;Id: </td><td><s:property value="%{email}"/></td>
                    </tr>
                    <tr>
                        <td class="fieldLabel" style="text-align:left;">Operation&nbsp;Contact: </td><td><s:property value="%{opsContactName}"/></td>
                    </tr>
                    <tr>
                        <td class="fieldLabel" style="text-align:left;">Practice: </td><td><s:property value="%{practiceName}"/></td>
                    </tr>
                    <tr>
                        <td class="fieldLabel" style="text-align:left;">Sub Practice: </td><td><s:property value="%{subPraticename}"/></td>
                    </tr>
                    <tr>
                        <td class="fieldLabel" style="text-align:left;">Current Status: </td><td><s:property value="%{currentStatus}"/></td>
                    </tr>
                    <tr>
                        <td class="fieldLabel" style="text-align:left;">Start date: </td><td><s:property value="%{startDate}"/></td>
                    </tr>

</table>
                    </s:form>

<%--Name:<s:property value="%{nodeName}" /><br/>--%>
