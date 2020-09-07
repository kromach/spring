<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> </title>
	<link rel="stylesheet" href="../resources/style.css" />
	<script>
		function checkFrm() {
			var frm = document.modifyForm;
			if( !frm.pw.value || !frm.pwCh.value ) {
				alert("비밀번호를 입력하세요.");
				return false;
			} 
			if( frm.pw.value != frm.pwCh.value ) {
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
		} 
	</script>	
</head>
<body>
	<div id="contents">
		<div id="titleDiv" class="fl"><h1>회원정보 수정</h1></div>
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

		<form action="modifyPro.git" method="post" name="modifyForm" onsubmit="return checkFrm();" enctype="multipart/form-data" >
			<table class="cl">
				<tr>
					<td>아이디*</td>
					<td>${member.id}</td>
				</tr>
				<tr>
					<td>비밀번호*</td>
					<td><input type="password" name="pw" /></td>
				</tr>
				<tr>
					<td>비밀번호 확인*</td>
					<td><input type="password" name="pwCh" /></td>
				</tr>
				<tr>
					<td>이름*</td>
					<td>${member.name}</td>
				</tr>
				<tr>
					<td>나이</td>
					<td>
						<input type="text" name="age" value="${member.age}" />
					</td>
				</tr>
				<tr>
					<td>e-mail</td>
					<td>
						<input type="text" name="email" value="${member.email}" />
					</td>
				</tr>
				<tr>
					<td>image</td>
					<td>
						<img src="http://kromach.dothome.co.kr/upload/${member.image}" /> <br />
						<input type="file" name="img" />
					</td>
				</tr>
				<tr>
					<td>가입일</td>
					<td>${member.reg}</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="수정"/>
						<input type="button" value="취소" onclick="window.location='main.git'"/>
					</td>
				</tr>
			</table>
		</form>		
	</div>
</body>
</html>