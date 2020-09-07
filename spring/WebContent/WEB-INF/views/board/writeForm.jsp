<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> 글쓰기 </title>
	<link rel="stylesheet" href="../resources/style.css" />
</head>
<body>
	<div id="contents">
		<c:if test="${ aticle.num > 0 }">
			<div id="titleDiv"><h1>답글 쓰기</h1></div>
		</c:if>
		<c:if test="${ aticle.num == 0 || empty aticle.num }">
			<c:set var="pageNum" value="1"></c:set>
			<div id="titleDiv"><h1>글 쓰기</h1></div>
		</c:if>
		<!-- RedirectAttributes 로 보낸 valid 결과를 alert으로 표시 -->
		<c:forEach items="${errorList}" var="eList">
			<script>
				alert("${eList}");
			</script>
		</c:forEach>
		<!-- 게시판 형태 -->
		<form action="writePro.git" method="post">
			<input type="hidden" name="pageNum" value="${pageNum}"/>
			<input type="hidden" name="num" value="${aticle.num}"/>
			<input type="hidden" name="ref" value="${aticle.ref}"/>
			<input type="hidden" name="re_step" value="${aticle.re_step}"/>
			<input type="hidden" name="re_level" value="${aticle.re_level}"/>
			
			<table class="tb_write">
				<colgroup>
					<col width="200px" />
					<col width="*" />
					<col width="200px" />
					<col width="*" />
				</colgroup>
				<tr>
					<td>작성자</td>
					<td class="inputTd"><input type="text" name="writer" style="width:250px;" /></td>
					<td>비밀번호</td>
					<td class="inputTd"><input type="password" name="pw" style="width:250px;" /></td>
				</tr>
				<tr>
					<td>email</td>
					<td class="inputTd" colspan=3><input type="text" name="email" style="width:250px;" /></td>
				</tr>
				<tr>
					<td>제목</td>
					<td class="inputTd" colspan=3><input type="text" name="subject" style="width:860px;" <c:if test="${aticle.num > 0}"> value="답글 : ${aticle.subject}"</c:if> /> </td>
					
				</tr>
				<tr>
					<td>내용</td>
					<c:set var="reply" value ="<br><br> -----Original Contents----- <br><br>" />
					<% 
					pageContext.setAttribute("crcn", "\r\n"); 
					pageContext.setAttribute("br", "<br>"); 
					%>
					<td class="inputTd" colspan=3>
						<textarea name="contents" class="contentsArea"><c:if test="${aticle.num > 0}">${fn:replace(reply, br, crcn)}</c:if>${aticle.contents}</textarea>
					</td> <!-- <c:if test="${num > 0}"> ${reply}</c:if> -->
				</tr>
				<tr>
					<td colspan=4>
						<input type="submit" value="저장" />
						<!-- <input type="reset" value="재작성" /> -->
						<input type="button" onclick="window.location='list.git?pageNum=${pageNum}'" value="취소" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>