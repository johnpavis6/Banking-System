
<%
	Integer account_no = (Integer) request.getSession().getAttribute(
			"account_no");
	if (account_no == null) {
		response.sendRedirect("/BankingSystem");
	}
%>
<%@taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Benifitary</title>
<tiles:insert page="includes.jsp" />
</head>
<body>
	<tiles:insert page="NavBar.jsp" />
	<div class="container">
		<c:if test="${requestScope.message!=null}">
			<div class="alert alert-danger">
				<c:out value="${requestScope.message }" />
			</div>
		</c:if>
		<form action="addBenifitary.do" method="post">
			<div>
				<label>Name : </label> <input name="name" class="form-control"
					type="text">
				<div class="text-danger">
					<html:errors property="name" />
				</div>
			</div>
			<div>
				<label>Account Number : </label> <input name="account_no"
					class="form-control" type="number">
				<div class="text-danger">
					<html:errors property="bAccountNo" />
				</div>
			</div>
			<hr>
			<div>
				<input type="submit" class="btn btn-outline-success" value="Add">
			</div>
		</form>
	</div>
</body>
</html>