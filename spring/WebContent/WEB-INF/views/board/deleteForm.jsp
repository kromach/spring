<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> 글 삭제 </title>
	<link rel="stylesheet" href="../resources/style.css" />
</head>
<body>
	<div id="contents">
		<div id="titleDiv"><h1>글 삭제</h1></div>
		<form action="deletePro.git?pageNum=${pageNum}" method="post">
			<input type="hidden" name="num" value="${num}" />
			<table class="tb_write">
				<colgroup>
					<col width="200px" />
					<col width="350px" />
				</colgroup>
				<tr>
					<td>비밀번호</td>
					<td class="inputTd"><input type="password" name="pw" style="width:250px;" /></td>
				</tr>
			</table>
			<p class="center">작성한 글을 삭제하기 위해 비밀번호를 입력해주세요.</p>
			<div class="center pad-top10">
				<input type="submit" value="삭제" />
			</div>
		</form>
	</div>
</body>
</html>