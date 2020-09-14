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
	 img { width:200px; height:50px; } 
</style>
<script type="text/javascript">
	function check(){
		
	}
</script>
<body>
	<a href="UserRentSelect.user?m_id=${sessionScope.m_id }">대여</a>&emsp;
	<a href="">연장/취소</a>&emsp;
	<a href="">상담 게시판</a>&emsp;
	<a href="">충전소 지도</a>
	
	<h2> 렌트카 대여</h2>
	<br>
	
	<form action="UserRent_costing.user" method="post" name="rentinfo">
		<input type="hidden" name="cSelected" value="${param.cSelected }" />
		<table border="1" align="center" >
			<tr>
				<th colspan="6"> 대여일자 </th>
				<th colspan="6"> 반납일자 </th>
			</tr>
			<tr>
				<th> <input type="text" name="sy" required="required" value="${sy }"> </th><th>년</th>
				<th> <input type="text" name="sm" required="required" value="${sm }"> </th><th>월</th>
				<th> <input type="text" name="sd" required="required" value="${sd }"> </th><th>일</th>
				<th> <input type="text" name="ey" required="required" value="${ey }"> </th><th>년</th>
				<th> <input type="text" name="em" required="required" value="${em }"> </th><th>월</th>
				<th> <input type="text" name="ed" required="required" value="${ed }"> </th><th>일</th>
				<!-- <th> <select name="sy" ><option>2018</option><option></option></select></th> -->
			</tr>
			<tr></tr>
			<tr>
				<th colspan="3"> 선택된 차량 </th>
				<th colspan="3"> 포인트 현황 </th>
				<th colspan="6"> 결제 수단 </th>
			</tr>
			<tr></tr>
			<tr>
				<td rowspan="2" colspan="3"><img src="${requestScope.carinfo.c_url}" /> </td>
				<td rowspan="1" colspan="3"> 사용 가능한 포인트 </td>
				<td colspan="6">결제를 진행하시겠습니까?<input type="submit" value="확인"></td>
							
			</tr>				
			<tr>
				<td rowspan="1" colspan="3"> ${requestScope.n_point } </td>
				<td colspan="6"> 최종 결제 금액</td>
				
			</tr>
			<tr>
				<td>차종</td>
				<td colspan="2">${requestScope.carinfo.c_name }</td>
				<td colspan="3"> 사용 할 포인트 </td>
				<td colspan="6"> ${requestScope.fincost}</td>
			</tr>
			<tr>
				<td>대여료</td>
				<td colspan="2">${requestScope.carinfo.c_cost }</td>
				<td colspan="3"> <input type="text" name="changep" value="${changep }"></td>
				
				<td colspan="6"> <select name="choose"> 
						<option>계좌 선택</option>
						<option value="nh"> 농협 계좌 : 888-1234</option>
						<option value="kk"> 국민 계좌 : 1234-888</option>
					</select>
				</td>	
			</tr>
		</table>	
	</form>
	<br>
	
	<input type="button" value="결제하기" onclick="location.href='rent_end.jsp'" />
	<input type="button" value="취소하기" onclick="location.href='UserRentSelect.user'"/>
	
</body>
</html>