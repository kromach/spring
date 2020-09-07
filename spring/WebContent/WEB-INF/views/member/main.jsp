<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> main </title>
	<link rel="stylesheet" href="../resources/style.css" />
</head>
<body>
	<div id="contents">
		<div id="titleDiv" class="fl"><h1>메인 페이지</h1></div>
		<%-- 
		<c:if test="${check == 0}">
			<c:redirect url="loginPro.git" />
		</c:if>
		 --%>
		<c:if test="${sessionScope.memId != null}">
			<div class="header fr">
				${memId}님 환영합니다. 
				<input type="button" value="로그아웃" onclick="location.href='logout.git'"/>
				<input type="button" value="회원정보변경" onclick="location.href='modify.git'"/>
				<input type="button" value="게시판" onclick="location.href='../board/list.git'"/>
			</div>
		</c:if>
		
		<c:if test="${sessionScope.memId == null}">
			<div class="header fr">
				<input type="button" value="로그인" onclick="location.href='login.git'" />
				<a href="signupForm.git" style="">회원가입</a>
				<input type="button" value="게시판" onclick="location.href='../board/list.git'"/>
			</div>
		</c:if>
		
		<div id="contents" class="cl" style="padding:10px 5px;">
			contents
			<img src="../resources/img/beach.jpg" />
		</div>
	</div>
</body>
</html>