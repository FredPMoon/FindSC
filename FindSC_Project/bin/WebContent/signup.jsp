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
		<title>FindSC - Sign up</title>
	</head>
	<link rel="stylesheet" type="text/css" href="style.css">
	<link href="https://fonts.googleapis.com/css?family=Yantramanav:100,300,500" rel="stylesheet">
	<body class="loginBody">
		<div class="loginContent">
			<div class="menuBar">
				<ul class="loginMenuBar">
					<li class="logo"><a href="index.jsp"></a></li>
					<li><a class="userOperation" href="post">Lost</a></li>
					<li><a  class="userOperation" href="view_items">Found</a></li>
				</ul>
			</div>
			<div class="bodyContent">
				<form method="POST" action="signup" >
					<ul class="bodyBox" id="signupBox">
						<li id="greeting"><p>Welcome, Trojan!</p></li>
						<li id="errorMessage">${message}</li>
						<li class="icon" id="userIcon"></li>
						<li><input type="text" value="" name="username" placeholder="username"></li>
						<li class="icon" id="lockIcon"></li>
						<li><input type="password" value="" name="password" placeholder="password"></li>
						<li class="icon" id="lockIcon"></li>
						<li><input type="password" value="" name="confirm password" placeholder="confirm password"></li>
						<li class="icon" id="emailIcon"></li>
						<li><input type="email" value="" name="usc_email" placeholder="USC email"></li>
						<li class="icon" id="phoneIcon"></li>
						<li><input type="text" value="" name="phone_number" placeholder="Phone Number"></li>
						<li><input type="submit" value="Sign up"></li>
						<li class="loginRedirect"><a href="login.jsp">Already have an account? Log in here</a></li>
					</ul>
				</form>
			</div>
		</div>
	</body>
</html>