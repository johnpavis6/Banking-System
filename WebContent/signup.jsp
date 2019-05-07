<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Signup</title>
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
		<form action="signup.do" method="post">
			<div>
				<label>Name</label> <input name="name" type="text"
					class="form-control">
				<div class="text-danger">
					<html:errors property="name" />
				</div>
			</div>
			<div>
				<label>Gender</label> <select name="gender" class="form-control">
					<option value="male">Male</option>
					<option value="female">Female</option>
					<option value="other">Other</option>
				</select>
				<div class="text-danger">
					<html:errors property="gender" />
				</div>
			</div>
			<div>
				<label>DOB</label> <input name="dob" type="date"
					class="form-control">
				<div class="text-danger">
					<html:errors property="dob" />
				</div>
			</div>
			<div>
				<label>Mobile no</label> <input name="mobile_no" type="text"
					class="form-control">
				<div class="text-danger">
					<html:errors property="mobile_no" />
				</div>
			</div>
			<div>
				<label>Email</label> <input name="email" type="email"
					class="form-control">
				<div class="text-danger">
					<html:errors property="email" />
				</div>
			</div>
			<div>
				<label>Password</label> <input name="password" type="password"
					class="form-control">
				<div class="text-danger">
					<html:errors property="password" />
				</div>
			</div>
			<div>
				<label>Address</label>
				<textarea name="address" class="form-control"></textarea>
				<div class="text-danger">
					<html:errors property="address" />
				</div>
			</div>
			<hr>
			<input type="submit" value="Signup" class="btn btn-outline-success">
		</form>
	</div>
</body>
</html>