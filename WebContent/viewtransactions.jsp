
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transactions</title>
<tiles:insert page="includes.jsp" />
</head>
<body>
	<tiles:insert page="NavBar.jsp" />
	<div class="container">
		<c:set var="id" value="${requestScope['id']}" />
		<c:set var="fromAccount" value="${requestScope['fromAccount']}" />
		<c:set var="toAccount" value="${requestScope['toAccount']}" />
		<c:set var="amount" value="${requestScope['amount']}" />
		<c:set var="timestamp" value="${requestScope['timestamp']}" />
		<table class="table table-hover table-bordered">
			<tr class="thead-light">
				<th>Transaction ID</th>
				<th>From Account</th>
				<th>To Account</th>
				<th>Amount</th>
				<th>Type</th>
				<th>DateTime</th>
			</tr>
			<tr class="thead-light">
				<th><input class="form-control search" id="tid"></th>
				<th><input class="form-control search" id="fa"></th>
				<th><input class="form-control search" id="ta"></th>
				<th><input class="form-control search" id="a"></th>
				<th><input class="form-control search" id="t"></th>
				<th><input class="form-control search" id="dt"></th>
			</tr>
			<c:forEach var="i" begin="1" end="${fn:length(id)}">
				<tr id='entry-<c:out value="${id[i-1]}" />' class="entries">
					<td class="tid"><c:out value="${id[i-1]}" /></td>
					<td class="fa"><c:out value="${fromAccount[i-1]}" /></td>
					<td class="ta"><c:out value="${toAccount[i-1]}" /></td>
					<td class="a"><c:out value="${amount[i-1]}" /></td>
					<td class="t"><c:choose>
							<c:when test="${account_no==fromAccount[i-1]}">
							Debit
							</c:when>
							<c:otherwise>  
       Credit
    </c:otherwise>
						</c:choose></td>
					<td class="dt"><fmt:formatDate type="both" timeStyle="short"
							value="${timestamp[i-1]}" /></td>
				<tr>
			</c:forEach>
		</table>
	</div>
</body>
<script>
	$(document).ready(function() {
		var dic = {
			'tid' : [],
			'fa' : [],
			'ta' : [],
			'a' : [],
			't' : [],
			'dt' : []
		};
		for (key in dic) {
			var elements = $("." + key);
			for (var i = 0; i < elements.length; i++) {
				dic[key].push($(elements[i]).html().trim());
			}
		}
		$(".search").keyup(search);
		function search() {
			var values = {};
			for (key in dic) {
				values[key] = $("#" + key).val().trim();
			}
			$(".entries").show();
			for (var i = 0; i < dic.tid.length; i++) {
				for (key in values) {
					if (!key.length) {
						continue;
					}
					if (!dic[key][i].includes(values[key])) {
						$("#entry-" + dic['tid'][i]).hide();
					}
				}
			}
		}
	});
</script>
</html>