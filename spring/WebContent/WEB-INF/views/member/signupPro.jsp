<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>signupPro</title>
</head>
<body>
	<c:if test="${sessionScope.memId != null}">
		<script>alert('접근할 수 없는 페이지입니다. 메인페이지로 이동합니다.'); location.href='main.git';</script>
	</c:if>
<%-- 
	<c:if test="${requestScope.result != 1}">
		<script>alert('회원가입이 실패했습니다. 다시 시도해주세요.'); history.back();</script>
	</c:if>	
 --%>
 	<c:if test="${result == 1}">
		<script>alert('회원가입이 완료되었습니다.'); location.href='main.git';</script>
	</c:if>
</body>
</html>