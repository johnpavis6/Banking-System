<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Signin</title>
<tiles:insert page="includes.jsp" />
</head>
<body>
	<tiles:insert page="NavBarOnly.jsp" />
	<div class="container">
		<c:if test="${requestScope.error!=null}">
			<div class="alert alert-danger">
				<c:out value="${requestScope.error }" />
			</div>
		</c:if>
		<form action="signin.do" method="post">
			<div>
				<label>Email</label> <input name="email" type="email"
					class="form-control" required>
				<div class="text-danger">
					<html:errors property="email" />
				</div>
			</div>
			<div>
				<label>Password</label> <input name="password" type="password"
					class="form-control" required>
				<div class="text-danger">
					<html:errors property="password" />
				</div>
			</div>
			<hr>
			<input type="submit" value="Signin" class="btn btn-outline-success">
		</form>
	</div>
</body>
</html>