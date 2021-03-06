<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style>
		.right { text-align: right; }
		form { display: inline; }
	</style>
</head>
<body>
	<header>
		<a href="main.jsp"><font size="14pt">Thor</font></a>
		<c:if test="${sessionScope.m_id == 'root' }">
			<a href="rentlist.admin">관리자 페이지</a>
		</c:if>
		<c:choose>
			<c:when test="${sessionScope.m_id == null }">
				<a href="login.jsp">로그인</a>
			</c:when>
			<c:otherwise>
				<form action="logout.common" method="post">
					<input type="submit" value="로그아웃" />
				</form>
				<a href="mypage_view.user">마이 페이지</a>
			</c:otherwise>
		</c:choose>
		<div class="right">
			<a href="">대여</a>
			<a href="">연장/취소</a>
			<a href="boardlist.common">상담 게시판</a>
			<a href="https://www.ev.or.kr/portalmonitor">충전소 지도</a>
		</div>
	</header>
	<br>
	
	<section>
		<h2>상담게시판 글쓰기</h2>
		<hr width="500" align="left" />
		<form action="boardAWrite.common" method="post">
			<input type="hidden" name="a_qnum" value="${param.a_qnum }" />
			<table>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" size="35" required="required" /></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="content" rows="15" cols="80"></textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="확인" />
						<input type="button" value="돌아가기" onclick="history.back();" />
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>