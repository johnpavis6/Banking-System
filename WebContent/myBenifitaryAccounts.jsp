
<%
	Integer account_no = (Integer) request.getSession().getAttribute(
			"account_no");
	if (account_no == null) {
		response.sendRedirect("/BankingSystem");
	}
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Benifitary Accounts</title>
<tiles:insert page="includes.jsp" />
</head>
<body>
	<tiles:insert page="NavBar.jsp" />
	<div class="container">
		<c:set var="bNames" value="${requestScope['bNames']}" />
		<c:set var="bAccountNos" value="${requestScope['bAccountNos']}" />
		<table class="table table-hover table-bordered">
			<tr class="thead-light">
				<th>Name</th>
				<th>Account No</th>
			</tr>
			<c:forEach var="i" begin="1" end="${fn:length(bNames)}">
				<tr>
					<td><c:out value="${bNames[i-1]}" /></td>
					<td><c:out value="${bAccountNos[i-1]}" /></td>
				<tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>