<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link class="jsbin" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
		<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
		<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
		<script>
			function readURL(input) {
		        if (input.files && input.files[0]) {
		            var reader = new FileReader();
	
		            reader.onload = function (e) {
		                $('#uploadImage')
		                    .attr('src', e.target.result)
		                    .width(300)
		                    .height(300);
		            };
	
		            reader.readAsDataURL(input.files[0]);
		        }
		    }
		</script>
		<meta charset="UTF-8">
		<title>Post your lost item</title>
	</head>
	<link rel="stylesheet" type="text/css" href="/FindSC/style.css">
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,600" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Yantramanav:100,300,500" rel="stylesheet">
	<body class="homeBody" id="darkBackground">
		<div class="menuBar" >
			<ul class="homeMenuBar">
				<li class="logo"><a href="/FindSC"></a></li>
				<c:choose>
				    <c:when test="${user == null}">
						<li><a class="userOperation" href="login">Log in</a></li>
						<li><a  class="userOperation" href="signup">Sign up</a></li>  
						<li><a class="menuBarOption" id="highlight" href="post">Lost</a></li>
						<li><a  class="menuBarOption" href="view_items">Found</a></li>
				    </c:when>
				    <c:otherwise>
				        <li><a class="userOperation" href="logout">Log out</a></li>
				        <li><a class="userOperation">${user.username}</a></li>
						<li><a class="menuBarOption" id="highlight" href="post">Lost</a></li>
						<li><a  class="menuBarOption" href="view_items">Found</a></li>
				    </c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div class="bodyContent">
			<form method="POST" action="post" enctype="multipart/form-data" >
				<div id="itemPostingBox">
					<div id="inputFields">
						<div class="inputField" id="postingMessage">
							${message}
						</div>
						<div class="inputField">
							<div class="icon" id="pencilIcon" style="display: inline-block;margin-bottom: -8px;"></div>
							<div style="display: inline-block"><input type="text" name="name" placeholder="What did you lose?"></div>
						</div>
						<div class="inputField">
							<div class="icon" id="locationIcon" style="display: inline-block;margin-bottom: -8px;"></div>
							<div style="display: inline-block"><input type="text" name="location" placeholder="Where did you lose it?"></div>
						</div>
						<!-- <div class="inputField">
							<div class="icon" id="timeIcon" style="display: inline-block;margin-bottom: -8px;"></div>
							<div style="display: inline-block"><input type="date" value="" name="date"></div>
						</div> -->
						<div class="inputField">
							<div class="icon" id="tagIcon" style="display: inline-block;margin-bottom: -8px;"></div>
							<div style="display: inline-block">
								<select name="category">
								   <option value="" disabled selected>Select a category</option>
							       <option value = "Bags">Bags</option>
							       <option value = "Clothing">Clothing</option>
							       <option value = "Documents/Books">Documents/Books</option>
							       <option value = "Electronics">Electronics</option>
							       <option value = "Jewelry">Jewelry</option>
							       <option value = "Medical">Medical</option>
							       <option value = "Musical Equipment">Musical Equipment</option>
							       <option value = "Personal Accessories">Personal Accessories</option>
							       <option value = "Sporting Goods">Sporting Goods</option>
							       <option value = "Toys">Toys</option>
							       <option value = "Others">Others</option>
							     </select>			
							</div>
						</div>
						<div class = inputField>
							<div class="icon" id="pencilIcon" style="display: inline-block;margin-bottom: -8px;"></div>
							<div style="display: inline-block"><input type="text" value="" name="description" placeholder="Please provide a description"></div>
						</div>
						<div class="inputField">
							<div class="icon" id="rewardIcon" style="display: inline-block;margin-bottom: -8px;"></div>
							<div style="display: inline-block"><input type="text" value="" name="reward" placeholder="You can provide an optional reward"></div>
						</div>
						<div class="inputField">
							<input type="submit" value="Post">
						</div>
					</div>
					<div id="image">
    						<img id="uploadImage" src="img/image_placeholder.png" alt="Invalid format. Please upload another image" />
    						<label for="file-upload" class="custom-file-upload">
						    Upload Image
						</label>
    						<input type='file' id="file-upload" name="image" onchange="readURL(this);" />
					</div>
				</div>
				
			</form>
		</div>
		
	</body>
</html>