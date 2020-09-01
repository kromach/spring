<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("replaceChar", "\n"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> 내용보기 </title>
	<link rel="stylesheet" href="../resources/style.css" />
</head>
<body>
	<div id="contents">
		<div id="titleDiv"><h1>내용보기</h1></div>
		<table class="tb_write">
			<colgroup>
				<col width="200px" />
				<col width="350px" />
				<col width="200px" />
				<col width="*" />
			</colgroup>
			<tr>
				<td>작성자</td>
				<td class="inputTd">${aticle.writer}</td>
				<td>email</td>
				<td class="inputTd">${aticle.email}</td>
			</tr>
			<tr>
				<td>작성일</td>
				<td class="inputTd">${aticle.reg}</td>
				<td>조회수</td>
				<td class="inputTd">${aticle.readCount}</td>
			</tr>	
			<tr>
				<td>제목</td>
				<td class="inputTd" colspan=3>${aticle.subject}</td>
			</tr>
			<tr>
				<td class="inputTd" colspan=4>
					${fn:replace(aticle.contents, replaceChar, "<br />")}
				</td>
			</tr>
		</table>
		<div class="center pad-top10">
			<input type="button" onclick="window.location='modifyForm.git?num=${aticle.num}&pageNum=${pageNum}'" value="수정" />
			<input type="button" onclick="window.location='deleteForm.git?num=${aticle.num}&pageNum=${pageNum}'" value="삭제" />
			<input type="button" onclick="window.location='writeForm.git?num=${aticle.num}&pageNum=${pageNum}'" value="답글" />
			<input type="button" onclick="window.location='list.git?pageNum=${pageNum}'" value="목록" />
		</div>
	</div>
</body>
</html>