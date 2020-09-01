<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> 수정 </title>
	<link rel="stylesheet" href="../resources/style.css" />
</head>
<body>

	<div id="contents">
		<div id="titleDiv"><h1>글 수정</h1></div>
		<!-- 게시판 형태 -->
		<form action="modifyPro.git?pageNum=${pageNum}" method="post">
			<input type="hidden" name="num" value="${article.num}"/>
			
			<table class="tb_write">
				<colgroup>
					<col width="200px" />
					<col width="*" />
					<col width="200px" />
					<col width="*" />
				</colgroup>
				<tr>
					<td>작성자</td>
					<td class="inputTd"><input type="text" name="writer" style="width:250px;" value="${article.writer}" /></td>
					<td>비밀번호</td>
					<td class="inputTd"><input type="password" name="pw" style="width:250px;" /></td>
				</tr>
				<tr>
					<td>email</td>
					<td class="inputTd" colspan=3><input type="text" name="email" style="width:250px;" value="${article.email}" /></td>
				</tr>
				<tr>
					<td>제목</td>
					<td class="inputTd" colspan=3><input type="text" name="subject" style="width:860px;" value="${article.subject}" /></td>
				</tr>
				<tr>
					<td>내용</td>
					<td class="inputTd" colspan=3><textarea name="contents" id="contents">${article.contents}</textarea></td>
				</tr>
				<tr>
					<td colspan=4>
						<input type="submit" value="수정" />
						<input type="button" onclick="window.location='list.git?pageNum=${pageNum}'" value="취소" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>