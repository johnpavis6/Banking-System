<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
</head>
<body>
	<h1>Error Occurred</h1>
	<h1>
		Message :
		<%=request.getAttribute("message")%></h1>
	<a href="/BankingSystem">click here go to home page</a>
	<p>
		You will be redirected to homepage in <span id="secs">3</span> secs
	</p>
</body>
<script>
	var time = 2;
	var interval = setInterval(function() {
		if (time == 0) {
			location.replace("/BankingSystem");
			return;
		}
		document.getElementById("secs").innerHTML = time;
		console.log(time);
		time--;
	}, 1000);
</script>
</html>