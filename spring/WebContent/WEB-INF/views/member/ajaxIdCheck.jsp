<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty trialId}">
아이디 입력 후 검사 버튼을 클릭해주세요.
</c:if>
<c:if test="${!empty trialId && result == 0}">
사용할 수 있는 아이디 입니다.
</c:if>
<c:if test="${result != 0}">
이미 존재하는 아이디 입니다. 다른 아이디를 입력 후 검사해보세요.
</c:if>
