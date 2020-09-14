<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script>
		function check() {
			var value = document.frm.id.value;
				location.href = "duplicate.common?id=" + value;
			}
		
		function pwCheck() {
			var pw  = document.frm.pw.value;
			var pw2 = document.frm.pw2.value;
			
			if ( pw != pw2 ) {
				alert('비밀번호가 동일하지 않습니다!');
				frm.pw2.focus();
				return false;
			}
			return true;
		}
	</script>
</head>
<body>
	<form action="signup.common" method="post" name="frm">
		아이디 	   <input type="text" name="id" required="required"/>
				   <input type="button" value="중복 체크" onclick="check();"><br>
		비밀번호    	   <input type="password" name="pw" required="required"/><br>
		비밀번호 재입력  <input type="password" name="pw2" required="required"/><br>
		이름		   <input type="text" name="name" required="required"/><br>
		휴대폰 번호	   <input type="text" name="phone" required="required"/><br>
		이메일	   <input type="email" name="email" required="required"/><br>
		<input type="submit" value="확인" onclick="return pwCheck()" />
		<input type="button" value="취소" onclick="history.back();"/>
	</form>
</body>
</html>