
<%
	Integer account_no = (Integer) request.getSession().getAttribute(
			"account_no");
	if (account_no == null) {
		response.sendRedirect("/BankingSystem");
	}
%>
<%@taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Money Transfer</title>
<tiles:insert page="includes.jsp" />
</head>
<body>
	<tiles:insert page="NavBar.jsp" />
	<div class="container">
		<c:set var="bNames" value="${requestScope['bNames']}" />
		<c:set var="bAccountNos" value="${requestScope['bAccountNos']}" />
		<c:if test="${requestScope.message!=null}">
			<div class="alert alert-danger">
				<c:out value="${requestScope.message }" />
			</div>
		</c:if>
		<form action="moneytransfer.do" method="post">
			<input type="hidden" name="to_account" value="" id="to_account">
			<div id="benifitary-accounts">
				<label>Benifitary Accounts : </label> <select
					onchange="sCheck(this)" name="bAccountNo" class="form-control"
					type="number">
					<option></option>
					<c:forEach var="i" begin="1" end="${fn:length(bNames)}">
						<option value='<c:out value="${bAccountNos[i-1]}" />'>
							<c:out value="${bNames[i-1]}" />
						</option>
					</c:forEach>
				</select>
			</div>
			<div id="account-number">
				<label>Account Number : </label> <input name="account_no"
					class="form-control" type="number" onkeyup="iCheck(this)">
				<div class="text-danger">
					<html:errors property="to_account" />
				</div>
			</div>
			<div>
				<label>Amount : </label> <input name="amount" class="form-control"
					type="text" min=1>
				<div class="text-danger">
					<html:errors property="amount" />
				</div>
			</div>
			<hr>
			<div>
				<input type="submit" class="btn btn-outline-success"
					value="Transfer">
			</div>
		</form>
	</div>
</body>
<script>
	function sCheck(obj) {
		var val = $(obj).val().trim();
		console.log(val);
		if (val && val.length != 0) {
			$("#to_account").val(val);
			$("#account-number").hide();
		} else {
			$("#account-number").show();
		}
	}
	function iCheck(obj) {
		var val = $(obj).val().trim();
		console.log(val.length);
		if (val && val.length != 0) {
			$("#benifitary-accounts").hide();
			$("#to_account").val(val);
		} else {
			$("#benifitary-accounts").show();
		}
	}
</script>
</html>