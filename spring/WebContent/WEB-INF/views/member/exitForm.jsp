<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> 회원탈퇴 </title>
	<link rel="stylesheet" href="../resources/style.css" />
	<script>
		function checkFrm() {
			var frm = document.exitForm;
			
			if( !frm.pw.value ) {
				alert("비밀번호를 입력하세요.");
				return false;
			} 
		} 
	</script>		
</head>
<body>
	<div id="contents">
		<div id="titleDiv" class="fl"><h1>회원탈퇴</h1></div>
		<c:if test="${sessionScope.memId != null}">
			<div class="header fr">
				${sessionScope.memId}님 환영합니다. 
				<input type="button" value="로그아웃" onclick="window.location.href='logout.git'"/>
			</div>
		</c:if>
		
		<div class="cl">
			<form action="exitPro.git" method="post" name="exitForm" onsubmit="return checkFrm();">
			<table>
				<tr>
					<td>아이디</td>
					<td>${sessionScope.memId}</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="pw" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="수정"/>
						<input type="button" value="취소" onclick="window.location='main.git'"/>
					</td>
				</tr>
			</table>
			</form>
			<div style="padding-top: 10px; text-align: center;"><a href="main.git">메인으로 돌아가기</a></div>
		</div>
	</div>
</body>
</html>