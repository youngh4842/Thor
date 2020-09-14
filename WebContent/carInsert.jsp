<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<form action="carInsert.admin" method="post" name="frm" enctype="multipart/form-data">
		자동차명  <input type="text" name="name" required="required"/><br>   
		회사        <input type="text" name="company" required="required"/><br>
		연비        <input type="number" name="fuel" required="required"/>km/L<br>
		대여료     <input type="number" name="cost" required="required"/>원/h<br>
		사진	  <input type="file" name="url"/><br>
		
		<input type="submit" value="확인" "/>
		<input type="button" value="취소" onclick="history.back();"/>
	</form>
</body>
</html> 
