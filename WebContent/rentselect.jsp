<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<style>
	img { width: 200px; height : 150px; }
</style>
<script type="text/javascript">
	function check(){
		var checked = document.carL.cSelected.value;
		
		if (checked == ''){
			alert('차량을 선택해 주세요');
			return false;
		}
		else{
			return true;
		}
	}

</script>
<body>
	<a href="UserRentSelect.user?m_id=${sessionScope.m_id }">대여</a>&emsp;
	<a href="">연장/취소</a>&emsp;
	<a href="">상담 게시판</a>&emsp;
	<a href="">충전소 지도</a>
	
	<h2> 렌트카 조회</h2>
	<br><br>			
	<form action="UserRent_ing.user?m_id=${sessionScope.m_id}" method="post" name="carL">
	<table border="1" align="center">
		<c:forEach var="carList" items="${requestScope.carList}">
			<tr>
				<th colspan="3"> <input type="radio" name="cSelected" value="${carList.c_name}" /> </th>
			</tr>
			<tr>
				<td rowspan="3"><img src="${carList.c_url}"/> </td>
				<th>차 종</th>
				<td>${carList.c_name}</td>
				
			</tr>	
			<tr>
				<th>연 비</th>
				<td>${carList.c_fuel} km/h</td>
			</tr>	
			<tr>
				<th>대여료</th>
				<td>${carList.c_cost} \</td>
			</tr>	
	
		</c:forEach>	
	</table>
	<br>
	
	<center>
		<input type="submit" value="대여" onclick="return check()"/> 

	</center>
	</form>
			
</body>
</html>