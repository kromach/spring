<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<link rel="stylesheet" href="../resources/style.css" />
	<script>
		function checkFrm() {
			var frm = document.inputForm;
			
			if( !frm.id.value ) {
				alert("아이디가 입력되지 않았습니다.");
				return false;
			} 
			if( !frm.pw.value || !frm.pwCh.value ) {
				alert("비밀번호를 입력하세요.");
				return false;
			} 
			if( !frm.name.value ) {
				alert("이름을 입력하세요.");
				return false;
			}
			if( frm.pw.value != frm.pwCh.value ) {
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
		} 
		
		function confirmID(frm) {
			var url ="idCheckPop.git?id=" + frm.id.value;
			console.log(url);
			open(url, "ID 중복 검사", "width=400, height=400, left=20, top=20, toolbar=no, menubar=no, scrollbars=no, location=no, status=no");
			
		}
		
		$(function(){
			$("#id").change(function(){
				
				var id = $("#id").val();
				
				if(id){
					
					$.ajax({
						type : "post",
						url: "/spring/member/ajaxIdCheck.git",
						data : {id : id},
						success : function(data) {
							console.log(data);
							
							$("#idCheckRst").text(data);
						}
						
					})
					
				}
			});
		});
	</script>
</head>
<body>
<%-- 
	<c:if test="${sessionScope.memId != null}">
		<script>alert('이미 로그인 되어 있습니다. 메인페이지로 이동합니다.'); location.href='../main.do';</script>
	</c:if>
	
 --%>
 	<br />
	<h1 align="center"> 회원가입 </h1>
	<form action="signupPro.git" method="post" name="inputForm" onsubmit="return checkFrm();" enctype="multipart/form-data">
		<table>
			<tr>
				<td>아이디*</td>
				<td><input type="text" name="id" id="id" />
					<!-- 아이디중복체크 -->
					<!-- <input type="button" value="중복체크" onclick="confirmID(this.form)"/> -->
					<span id="idCheckRst"></span>
				</td>
			</tr>
			<tr>
				<td>비밀번호*</td>
				<td><input type="password" name="pw" /></td>
			</tr>
			<tr>
				<td>비밀번호 확인*</td>
				<td><input type="password" name="pwCh" /></td>
			</tr>
			<tr>
				<td>이름*</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>나이</td>
				<td><input type="text" name="age" maxlength="8" /></td>
			</tr>
			<tr>
				<td>e-mail</td>
				<td><input type="text" name="email"  /></td>
			</tr>
			<tr>
				<td>사진</td>
				<td><input type="file" name="img"  /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="가입"/>
					<input type="reset" name="reset" value="재입력" />
					<input type="button" value="취소" onclick="window.location='main.git'"/>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>