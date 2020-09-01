<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판 목록</title>
	<link rel="stylesheet" href="../resources/style.css" />
</head>

<body>
	<div id="contents" class="cl">
		<div id="titleDiv" class="fl"><h1>게시판 목록</h1></div>
		<c:if test="${sessionScope.memId != null}">
			<div class="header fr">
				${memId}님 환영합니다. 
				<input type="button" value="로그아웃" onclick="location.href='../member/logout.git'"/>
				<input type="button" value="회원정보변경" onclick="location.href='../member/modify.git'"/>
				<input type="button" value="메인으로" onclick="location.href='../member/main.git'"/>
			</div>
		</c:if>
		
		<c:if test="${empty sessionScope.memId}">
			<div class="header fr">
				<input type="button" value="로그인" onclick="location.href='../member/login.git'" />
				<a href="../member/signupForm.git">회원가입</a>
				<input type="button" value="메인으로" onclick="location.href='../member/main.git'"/>
			</div>
		</c:if>
		
	<div class="cl">
		<!-- 게시판 형태 -->
		<table class="tb_list">
			<colgroup>
				<col width="50px" />
				<col width="400px" />
				<col width="100px" />
				<col width="100px" />
				<col width="70px" />
				<col width="150px" />
			</colgroup>
			<tr>
				<td>No</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회수</td>
			</tr>
			
			<c:if test="${count == 0}">
			<tr>
				<td colspan="6">작성된 게시글이 없습니다.</td>
			</tr>
			</c:if>
			
			<c:if test="${count > 0}">
				<c:forEach var="aticle" items="${ aticleList }">
				
			<tr>
				<td>
					${ number } 
					<!-- number--가 안되므로 최초 한번은 Bean에서 넘어온 그대로 쓰고 이후는 c:set으로 처리한 것으로 사용 -->
					<c:set var="number" value="${number-1}" /> 
				</td>
				<td class="taLeft">
					<!-- 답글 제목 들여쓰기 -->
					<c:set var="wid" value="0" /> 
					<c:if test="${aticle.re_level > 0}">
						<c:set var="wid" value="${8 * aticle.re_level}" /> 
						<img src="../resources/img/replyImg.png" alt="reply" style="width:8px; margin-left:${wid}px" />
					</c:if>
					
					<a href="content.git?num=${aticle.num}&pageNum=${pageNum}">${aticle.subject}</a>
				</td>
				<td><a href="mailto:${aticle.email}">${aticle.writer}</a></td>
				<!-- 2020. 6. 25 오후 3:03:53 -->
				<%-- <td><fmt:formatDate value="${aticle.reg}" type="both"/></td> --%> 
				<!-- 2020-06-25 15:03 -->
				<td><fmt:formatDate value="${aticle.reg}" pattern="yyyy-MM-dd HH:mm"/></td> 
				
				<td>${aticle.readCount}</td>
			</tr>	
			
				</c:forEach>
			</c:if>
			
			
		</table>
		
		<div class="center pad-top10">
			<button onclick="window.location='writeForm.git?pageNum=${pageNum}'">글쓰기</button>
		</div>
	 
		<c:if test="${count > 0}">
		<%-- 
			<fmt:parseNumber var="res" value="${count / pageSize}" integerOnly="true" />
			<c:set var="pageCount" value="${ res + (count % pageSize == 0 ? 0 : 1) }" />
			<c:set var="pageBlock" value="5" />
			<fmt:parseNumber var="result" value="${ (currPage-1) / pageBlock }" integerOnly="true" />
			<fmt:parseNumber var="startPage" value="${ result * pageBlock + 1 }" integerOnly="true" />
			<fmt:parseNumber var="endPage" value="${ startPage + pageBlock - 1 }" integerOnly="true" />
			<c:if test="${endPage > pageCount}">
				<fmt:parseNumber var="endPage" value="${ pageCount }" />
			</c:if>
		--%>
			<div class="center pad-top10">
				
				<%-- 1 페이지로 가기 --%>
				<c:if test="${startPage > 1}"><a href="?pageNum=1">&nbsp;[첫페이지]&nbsp;</a></c:if>
				
				<%-- 이전 페이지 블럭으로 가기 --%>
				<c:if test="${startPage > pageBlock}"><a href="?pageNum=${startPage-1}">&nbsp;[이전]&nbsp;</a></c:if>
				
				<c:forEach var="i" begin="${ startPage }" end ="${ endPage }" step="1" >
					<c:if test="${i == currPage}"><span class="font-bold">&nbsp;${i}&nbsp;</span></c:if>
					<c:if test="${i != currPage}"><a href="?pageNum=${i}">&nbsp;${i}&nbsp;</a></c:if>	
				</c:forEach>
				
				<%-- 다음 페이지 블럭으로 가기 --%>
				<c:if test="${endPage < pageCount}"><a href="?pageNum=${endPage+1}">&nbsp;[다음]&nbsp;</a></c:if>
				
				<%-- 마지막 페이지로 가기 --%>
				<c:if test="${pageCount != endPage}"><a href="?pageNum=${pageCount}">&nbsp;[마지막페이지]&nbsp;</a></c:if>

			</div>
		</c:if>
	</div>
</body>
</html>