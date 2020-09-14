<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="carManage.admin">렌터카 관리</a>
	<a href="rentlist.admin">렌터카 대여 요청 목록</a>
	<a href="UserManagerList.admin">회원 관리</a>
	<a href="boardlist.common">상담 게시판</a>
	<br>
	<h2>회원정보 조회</h2>
	<h3> ( 회원 포인트 변경 ) </h3>
		
	<form name="changepoint" method="post" action=UserPointChange.admin?m_id=${requestScope.meminfo.m_id}>
		<table>	<!-- width="600" -->
		<tr>
			<td>아이디</td>
			<td>${requestScope.meminfo.m_id}</td>
		</tr>
		<tr>
			<td>변경 날짜</td>
			<td><input type="text" name="days" id="days" /></td>
		</tr>
		<tr>
			<td>변경 할 포인트</td>
			<td><input type="text" name="change" id="change"/></td>
		</tr>
		<tr>
			<td>변경 사유</td>
			<td><input type="text" name="content" id="content"/></td>
		</tr>
		</table>
		<br>
		<input type="submit" value="확인" />
	</form>
	
	<input type="button" value="돌아가기" onclick="location.href='UserManage.admin?m_id=${requestScope.meminfo.m_id}';"/>
	
</body>
</html>