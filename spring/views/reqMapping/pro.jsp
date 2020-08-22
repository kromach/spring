<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pro</title>
</head>
<body>
	<!-- 파라미터로 일반 변수를 바로 던진 경우 -->
	<h2>id : ${ id }</h2>
	<h2>pw : ${ pw }</h2>
	<h2>memid : ${ memId }</h2>
	<!-- 파라미터로 DTO를 던진 경우 -->
	<h2>dto id : ${ dto.id }</h2>
	<h2>dto pw : ${ dto.pw }</h2>
</body>
</html>