<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome Page</title>
</head>
<body>
	<h1>Welcome Page <c:out value="${currentUser.firstName}"></c:out></h1>
	<fieldset>
		<p>Name: <c:out value="${currentUser.firstName}"></c:out><c:out value="${currentUser.lastName}"></c:out></p>
		<p>Email: <c:out value="${currentUser.email}"></c:out></p>
		<p>Sign up Date: <c:out value="${currentUser.createdAt}"></c:out></p>
		<p>Last signed in: <c:out value="${currentUser.updatedAt}"></c:out></p>
	</fieldset>
    
    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
</body>
</html>