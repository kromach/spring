<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> </title>
</head>
<body>
	<c:if test="${sessionScope.memId == null}">
		<script>alert('로그인이 되지 않았습니다. 메인페이지로 이동합니다.'); location.href='main.git';</script>
	</c:if>
	
	<c:if test="${result != 1}">
		<script>alert('회원정보가 업데이트가 실패했습니다.'); history.back();</script>
	</c:if>	
	
	<c:if test="${result == 1}">
		<script>
			alert("회원정보가 업데이트 되었습니다.");
			location.href='modifyForm.git';
		</script>
	</c:if>
</body>
</html>