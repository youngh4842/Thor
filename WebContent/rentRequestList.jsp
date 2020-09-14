<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style>
		.car { width: 120px; height: 90px; }
		td {
			padding: 5px;
			text-align: center;
		}
	</style>
	<script>
		function approveRent(frm) {
			frm.action = 'approverent.admin';
			frm.submit();
			
			return true;
		}
		function denyRent(frm) {
			frm.action = 'denyrent.admin';
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
	<br>
	<h2>렌터카 대여 요청 목록</h2>
	<hr width="300" align="left" />
	<form action="rentlist.admin" method="post">
		<table>
			<tr>
				<td></td>
				<td></td>
				<td>차종/대여기간</td>
				<td>아이디</td>
				<td>상태</td>
			</tr>
			<c:forEach var="list" items="${requestScope.rentList }">
				<tr>
					<td><input type="checkbox" name="select" value="${list.r_num }" /></td>
					<td><img src="${list.r_carUrl }" class="car" /></td>
					<td>
						${list.r_name } <br>
						${list.r_startDays } - ${list.r_endDays }
					</td>
					<td>${list.r_ref_Mid }</td>
					<c:choose>
						<c:when test="${list.r_status == 1 }">
							<td>승인</td>
						</c:when>
						<c:when test="${list.r_status == 0 }">
							<td>미승인</td>
						</c:when>
						<c:otherwise>
							<td>사용 중</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="5">
					<a href="rentlist.admin?page=1">처음</a>
					<c:if test="${startPage != 1 }"><a href="rentlist.admin?page=${startPage - 1 }">이전</a></c:if>
					<c:forEach var="pageIndex" begin="${startPage }" end="${endPage }" varStatus="cnt">
						<c:if test="${page == pageIndex }">${pageIndex }</c:if>
						<c:if test="${page != pageIndex }"><a href="rentlist.admin?page=${pageIndex }">${pageIndex }</a></c:if>
					</c:forEach>
					<c:if test="${endPage != totalPage }"><a href="rentlist.admin?page=${endPage + 1 }">다음</a></c:if>
					<a href="rentlist.admin?page=${totalPage }">끝</a>
				</td>
			</tr>
		</table>
		<select name="searchType">
			<option value="">목록</option>
			<option value="r_name">차 이름</option>
			<option value="r_ref_Mid">아이디</option>
			<option value="r_status">상태</option>
		</select>
		<input type="text" name="searchTerm" />
		<input type="submit" value="검색" /><br>
		<input type="button" value="승인" onclick="return approveRent(this.form);"/>
		<input type="button" value="승인 취소" onclick="return denyRent(this.form);"/>
	</form>
</body>
</html>