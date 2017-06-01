<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ssi.holiday.service.model.Holiday"%>
<%@ include file="/init.jsp" %>
<%@page import="java.util.List"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<html>
<head>

<script>

</script>
<style>
</style>
<%

List<Holiday>holidayList = (List<Holiday>)renderRequest.getAttribute("holidayList");
pageContext.setAttribute("holidayList", holidayList);
%>
</head>
<body>
	<table style="background-color: FF5733" border="1">
	
	 <c:forEach items="${holidayList}" var="holiday">
	  		<tr bordercolor="050001">
	            <td> <c:out value="${holiday.holidayName}"/></td>
	           <td> <fmt:formatDate pattern="EEE, dd/MMM/yyyy" value="${holiday.holidayDate}" /></td>
	        </tr>
	    </c:forEach>
		
	</table>


</body>
</html>