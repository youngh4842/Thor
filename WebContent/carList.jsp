<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function check() {
		var value1 = document.frm.car;
		var value2 = document.frm.car.value;
		
		if( value1 == undefined ) {
			alert('차량 등록을 먼저 해주세요.');
			return false;
		}
		else {
			if ( value2 == '' ) {
				alert('수정할 차량을 선택해주세요.');
				return false;
			}
			else {
				return true;
			}
		}
	}
	
	
	function deleteCar(frm) {
		frm.action = 'carDelete.admin';
		frm.submit();
		return true;
	}
</script>
</head>

<body>
	<a href="carManage.admin">렌터카 관리</a>
	<a href="rentlist.admin">렌터카 대여 요청 목록</a>
	<a href="UserManagerList.admin">회원 관리</a>
	<a href="boardlist.common">상담 게시판</a>
	<form action="carUpdate.admin" method="post" name="frm">
	<table>
		<tr>
			<td></td>
			<td></td>
			<td>차종</td>
			<td>대여료(원/h)</td>
			<td>연비(km/L)</td>
		</tr>
		
			<c:forEach var="list" items="${requestScope.car}">
				<tr>
					<td><input type="radio" name="car" value="${list.c_name}"/></td>
					<td><img src="${list.c_url}"/></td>
					<td>${list.c_name}</td>
					<td>${list.c_cost}</td>
					<td>${list.c_fuel}</td>
				</tr>
			</c:forEach>
		
	</table>
			
		<input type="button" value="등록" onclick="location.href='carInsert.jsp';"/>
		<input type="submit" value="수정" onclick="return check()"/>
		<input type="button" value="삭제" onclick="return deleteCar(this.form);"/>
	</form>
</body>
</html>