<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>View lost items</title>
</head>

<link rel="stylesheet" type="text/css" href="/FindSC/style.css">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,600"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Yantramanav:100,300,500"
	rel="stylesheet">

<body class="homeBody" id="darkBackground">
	<div class="menuBar">
		<ul class="homeMenuBar">
			<li class="logo"><a href="/FindSC"></a></li>
			<c:choose>
				<c:when test="${user == null}">
					<li><a class="userOperation" href="login">Log in</a></li>
					<li><a class="userOperation" href="signup">Sign up</a></li>
					<li><a class="menuBarOption" href="post">Lost</a></li>
					<li><a class="menuBarOption" id="highlight">Found</a></li>
				</c:when>
				<c:otherwise>
					<li><a class="userOperation" href="logout">Log out</a></li>
					<li><a class="userOperation">${user.username}</a></li>
					<li><a class="menuBarOption" href="post">Lost</a></li>
					<li><a class="menuBarOption" id="highlight">Found</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	<div class="bodyContent">
		<form id="viewItemsForm" method="GET">
			<div id="itemsViewingBox">
				<h1>Lost Items</h1>
				<div id="filter">
					<input type="button" value="Clear"
						onclick="window.location = '/FindSC/view_items'"></input> <input
						type="submit" value="Filter"></input> <select name="category">
						<c:set var="options"
							value="${['Bags', 'Clothing', 'Documents/Books', 'Electronics', 'Jewelry', 'Medical', 'Musical Equipment', 'Personal Accessories', 'Sporting Goods', 'Toys', 'Others']}"
							scope="request" />
						<option value="" disabled selected>Select a category</option>
						<c:forEach items="${options}" var="option" varStatus="optionLoop">
							<option value="${option}"
								${(filter != null && filter.equals(option)) ? 'selected' : ''}>${option}</option>
						</c:forEach>
					</select>
				</div>
				<div id="items">
					<c:forEach items="${items}" var="item" varStatus="itemLoop">
						<a href="/FindSC/view_item?itemid=${item.getId()}" class="item">
							<img src="data:image;base64,${item.getImage()}" />
							<div class="itemName">${item.getName()}</div>
						</a>
					</c:forEach>
				</div>
			</div>
		</form>
	</div>
</body>
</html>