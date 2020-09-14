<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<form action="findpw.common" method="post" name="frm">
		아이디 <input type="text" name="id" required="required" /><br>
		이름 <input type="text" name="name" required="required" /><br>
		휴대폰 번호 
		<input type="number" name="phone1" required="required" />-
		<input type="number" name="phone2" required="required" />-
		<input type="number" name="phone3" required="required" />
		<br>
		<input type="submit" value="확인" onclick="return check();" />
		<input type="button" value="돌아가기" onclick="location.href='login.jsp';" />
	</form>
</body>
</html>