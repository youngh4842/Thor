<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<form action="login.common" method="post">
		ID <input type="text" name="id" required="required" /><br>
		PW <input type="password" name="pw" required="required" /><br>
		<input type="submit" value="로그인" onclick="return loginCheck()" /><br>
	</form>
	<a href="findID.jsp">아이디 찾기</a>
	<a href="findPassword.jsp">비밀번호 찾기</a><br>
	<input type="button" value="회원 가입" onclick="location.href='signup.jsp'" />
</body>
</html>