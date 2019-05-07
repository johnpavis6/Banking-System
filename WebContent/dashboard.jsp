<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Integer account_no = (Integer) request.getSession().getAttribute(
			"account_no");
	if (account_no == null) {
		response.sendRedirect("/BankingSystem");
	}
%>
<%@taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
<tiles:insert page="includes.jsp" />
</head>
<body>
	<tiles:insert page="NavBar.jsp" />
	<div class="container">
		<table class="table table-bordered">
			<tr>
				<th>My Balance</th>
				<td id="balance">Click Refresh to Get Balanace</td>
				<td>
					<button class="btn btn-outline-secondary" onclick="fetchBalance()">Refersh</button>
				</td>
			</tr>
		</table>
	</div>
</body>
<script>
	function fetchBalance() {
		$("#balance").html("Loading...");
		$.ajax({
			url : "fetchbalance.do",
			method : "get"
		}).done(function(res) {
			$("#balance").html("Rs. " + res);
		});
	}
</script>
</html>