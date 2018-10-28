<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>${item.getName()}</title>
</head>

<link rel="stylesheet" type="text/css" href="style.css">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,600"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Yantramanav:100,300,500"
	rel="stylesheet">

<body class="homeBody" id="darkBackground">
	<div class="menuBar">
		<ul class="homeMenuBar">
			<li class="logo"><a href="index.jsp"></a></li>
			<c:choose>
				<c:when test="${user == null}">
					<li><a class="userOperation" href="login">Log in</a></li>
					<li><a class="userOperation" href="signup">Sign up</a></li>
					<li><a class="menuBarOption" id="highlight">Lost</a></li>
					<li><a class="menuBarOption" href="view_items">Found</a></li>
				</c:when>
				<c:otherwise>
					<li><a class="userOperation">${user.username}</a></li>
					<li><a class="userOperation" href="logout">Log out</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	<div class="bodyContent">
		<form id="viewItemForm" method="POST" action="view_item">
			<div id="itemViewingBox">
				<div id="attributeFields">
					<div class="inputField" id="postingMessage">${message}</div>
					<div class="inputField">
						<div class="icon" id="pencilIcon"
							style="display: inline-block; margin-bottom: -5px;"></div>
						<div style="display: inline-block">
							<div class="text">${item.getName()}</div>
						</div>
					</div>
					<div class="inputField">
						<div class="icon" id="locationIcon"
							style="display: inline-block; margin-bottom: -5px;"></div>
						<div style="display: inline-block">
							<div class="text">${item.getLocation()}</div>
						</div>
					</div>
					<div class="inputField">
						<div class="icon" id="timeIcon"
							style="display: inline-block; margin-bottom: -5px;"></div>
						<div style="display: inline-block">
							<div class="text">${item.getTimestamp()}</div>
						</div>
					</div>
					<div class="inputField">
						<div class="icon" id="rewardIcon"
							style="display: inline-block; margin-bottom: -5px;"></div>
						<div style="display: inline-block">
							<div class="text">${item.getReward()}</div>
						</div>
					</div>
					<div class="inputField">
						<div class="icon" id="emailIcon"
							style="display: inline-block; margin-bottom: -5px;"></div>
						<div style="display: inline-block">
							<div class="text">${item.getAuthor().getUscEmail()}</div>
						</div>
					</div>
					<div class="inputField">
						<div class="icon" id="phoneIcon"
							style="display: inline-block; margin-bottom: -5px;"></div>
						<div style="display: inline-block">
							<div class="text">${item.getAuthor().getPhoneNumber()}</div>
						</div>
					</div>

				</div>
				<div id="viewItemImage">
					<img id="uploadImage" src="data:image;base64,${item.getImage()}" />
				</div>
				<img id="horizontalLine" src="img/horizontal_line.png" />
				<div id="commentField">
					<c:forEach items="${item.getComments()}" var="comment"
						varStatus="commentLoop">
						<div class="comment">
							<img class="commentIcon" src="img/comment_icon.png" />
							<div class="textArea">
								<div class="commentUser">${comment.getUser().getUsername()}</div>
								<div class="commentTime">${comment.getDate()}</div>
								<div class="commentContent">${comment.getComment()}</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<c:if test="${not empty user}">
					
				<textarea rows="4" cols="50" name="comment"
					placeholder="You can add a comment here..."></textarea>
				<input type="hidden" name="itemid" value="${item.getId()}">
				<input type="submit" value="Post Comment">
				</c:if>
			</div>
		</form>
	</div>

</body>
</html>