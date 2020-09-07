<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> 회원정보변경 </title>
	<link rel="stylesheet" href="../resources/style.css" />
</head>
<body>
	<div id="contents">
		<div id="titleDiv" class="fl"><h1>회원정보 수정 / 탈퇴</h1></div>
		<c:if test="${sessionScope.memId == null}">
			<script>alert('로그인이 되지 않았습니다. 메인페이지로 이동합니다.'); location.href='main.git';</script>
		</c:if>
		<c:if test="${sessionScope.memId != null}">
			<div class="header fr">
				${memId}님 환영합니다. 
				<input type="button" value="로그아웃" onclick="location.href='logout.git'"/>
				<input type="button" value="회원정보변경" onclick="location.href='modify.git'"/>
				<input type="button" value="게시판" onclick="location.href='../board/list.git'"/>
			</div>
		</c:if>
		
		<div class="cl">
			<table width="400px">
				<tr>
					<td><a href="modifyForm.git">정보수정</a></td>
				</tr>
				<tr>
					<td><a href="exitForm.git">회원탈퇴</a></td>
				</tr>
				<tr>
					<td><a href="main.git">메인으로</a></td>
				</tr>
			</table>
		</div>
		
	</div>	
</body>
</html>