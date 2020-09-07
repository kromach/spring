<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
	<link rel="stylesheet" href="../resources/style.css" />
	<script>
		function checkFrm() {
			var frm = document.loginForm;
			
			if( !frm.id.value ) {
				alert("아이디를 입력하세요.");
				return false;
			}
			if( !frm.pw.value ) {
				alert("비밀번호를 입력하세요.");
				return false;
			} 
		} 
	</script>	
</head>
<body>

<c:if test="${sessionScope.memId != null}">
	<c:redirect url="main.git" /> <!-- context root는 쓰지 않는다. (/web/) -->
</c:if>

<c:if test="${sessionScope.memId == null}">
	<h1 align="center">로그인</h1>
	<form action="loginPro.git" method="post" name="loginForm" onsubmit="return checkFrm();" >
		<table border=1 width="500px" align="center">
			<tr>
				<td height="40px">아이디</td>
				<td><input type="text" name="id" /></td>
			</tr>
			<tr>
				<td height="40px">패스워드</td>
				<td><input type="password" name="pw" /></td>
			</tr>
			<tr>
				<td height="40px" colspan=2 >
					<label style="font-size:10pt; line-height: 14pt;">
						<input type="checkbox" name="auto" value="1" /> 
						<span style="height:19px;line-height: 19px;">자동로그인</span>
					</label> 
				</td>
			</tr>
			<tr>
				<td height="40px" colspan=2 align="center"> 
					<input type="submit" value="로그인" /> 
					<input type="button" value="회원가입" onclick="location.href='signupForm.git'" /> 
				</td>
			</tr>
		</table>
	</form>
</c:if>
</body>
</html>