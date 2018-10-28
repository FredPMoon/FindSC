<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>View lost items</title>
	</head>
	
	<link rel="stylesheet" type="text/css" href="style.css">
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,600" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Yantramanav:100,300,500" rel="stylesheet">
	
	<body class="homeBody" id="darkBackground">
		<div class="menuBar" >
			<ul class="homeMenuBar">
				<li class="logo"><a href="index.jsp"></a></li>
				<c:choose>
				    <c:when test="${user == null}">
						<li><a class="userOperation" href="login.jsp">Log in</a></li>
						<li><a  class="userOperation" href="signup.jsp">Sign up</a></li>  
						<li><a class="menuBarOption" href="item_posting.jsp">Lost</a></li>
						<li><a  class="menuBarOption" id="highlight">Found</a></li>
				    </c:when>
				    <c:otherwise>
				        <li><a class="userOperation">${user.username}</a></li>
				    </c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div class="bodyContent">
			<form id="viewItemsForm" method="POST" action="notify">
				<div id="itemsViewingBox">
					<h1>Lost Items</h1>
					<div id="filter">
						<input type="button" value="Clear" onclick="view_items">
						<input type="submit" value="Filter">
						<select name="category">
						   <option value="" disabled selected>Select a category</option>
					       <option value = "Bags">Bags</option>
					       <option value = "Clothing">Clothing</option>
					       <option value = "Documents/Books">Documents/Books</option>
					       <option value = "Electronics">Electronics</option>
					       <option value = "Jewelry">Jewelry</option>
					       <option value = "Medical">Medical</option>
					       <option value = "Musical Equipment">Documents/Books</option>
					       <option value = "Personal Accessories">Personal Accessories</option>
					       <option value = "Sporting Goods">Sporting Goods</option>
					       <option value = "Toys">Toys</option>
					       <option value = "Others">Others</option>
					    </select>
					</div>
					<div id="items">
						<div class="item">
							<img src="img/image_placeholder.png"/>
							<div class="itemName"><a href="">Item Name</a></div>
						</div>
						<div class="item">
							<img src="img/image_placeholder.png"/>
							<div class="itemName"><a href="">Item Name2</a></div>
						</div>
						<div class="item">
							<img src="img/image_placeholder.png"/>
							<div class="itemName"><a href="">Item Name3</a></div>
						</div>
						<div class="item">
							<img src="img/image_placeholder.png"/>
							<div class="itemName"><a href="">Item Name4</a></div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>