<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>FindSC</title>
	</head>
	<link rel="stylesheet" type="text/css" href="/FindSC/style.css">
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,600" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Yantramanav:100,500" rel="stylesheet">
	<body class="homeBody">
		<div class="menuBar" >
			<ul class="homeMenuBar">
				<li class="logo"><a href="/FindSC"></a></li>
				<c:choose>
				    <c:when test="${user == null}">
						<li><a class="userOperation" href="login">Log in</a></li>
						<li><a  class="userOperation" href="signup">Sign up</a></li>  
				    </c:when>
				    <c:otherwise>
				        <li><a class="userOperation" href="logout">Log out</a></li>
				        <li><a class="userOperation">${user.username}</a></li>
				    </c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div class="bodyContent">
			<p class="mainMessage">How can we help you?</p>
			<ul class="bodyBox">
				<li id="lostIcon"></li>
				<li><a href="post">I lost something</a></li>
				<li id="verticalLine"></li>
				<li id="foundIcon"></li>
				<li><a href="view_items">I found something</a></li>
			</ul>
		</div>
		
	</body>
</html>