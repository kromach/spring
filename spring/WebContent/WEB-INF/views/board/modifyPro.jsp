<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> 수정 처리 </title>
</head>
<body>
	<c:if test="${result == 1}">
		<script>alert('수정되었습니다.'); window.location.href='../board/content.do?num=${num}&pageNum=${pageNum}';</script>
	</c:if>
	<c:if test="${result != 1}">
		<script>alert('비밀번호가 일치하지 않습니다.'); history.back();</script>
	</c:if>
</body>
</html>