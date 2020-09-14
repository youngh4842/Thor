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
		nav { float: left; }
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
		<h2>상담 게시판</h2>
		<hr width="500" align="left" />
		<table>
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>글쓴이</td>
				<td>날짜</td>
			</tr>
			<c:forEach var="question" items="${requestScope.qList }">
				<tr>
					<td>${question.q_num }</td>
					<td><a href="boardQview.common?q_num=${question.q_num }">${question.q_title }</a></td>
					<td>${question.q_ref_Mid }</td>
					<td>${question.q_days }</td>
				</tr>
				<c:forEach var="answer" items="${requestScope.aList }">
					<c:if test="${question.q_num == answer.a_qnum }">
						<tr>
							<td><img src="https://placehold.it/20x20" />${answer.a_num }</td>
							<td><a href="boardAview.common?a_num=${answer.a_num }">${answer.a_title }</a></td>
							<td>${answer.a_name }</td>
							<td>${answer.a_days }</td>
						</tr>
					</c:if>
				</c:forEach>
			</c:forEach>
			<tr>
				<td colspan="4">
					<a href="boardlist.common?page=1">처음</a>
					<c:if test="${startPage != 1 }"><a href="boardlist.common?page=${startPage - 1 }">이전</a></c:if>
					<c:forEach var="pageIndex" begin="${startPage }" end="${endPage }" varStatus="cnt">
						<c:if test="${page == pageIndex }">${pageIndex }</c:if>
						<c:if test="${page != pageIndex }"><a href="boardlist.common?page=${pageIndex }">${pageIndex }</a></c:if>
					</c:forEach>
					<c:if test="${endPage != totalPage }"><a href="boardlist.common?page=${endPage + 1 }">다음</a></c:if>
					<a href="boardlist.common?page=${totalPage }">끝</a>
				</td>
			</tr>
		</table>
		<form action="boardlist.common" method="get">
			<select name="searchType">
				<option value="q_title">제목</option>
				<option value="q_ref_Mid">아이디</option>
				<option value="q_content">본문</option>
			</select>
			<input type="text" name="searchTerm" />
			<input type="submit" value="검색" />
		</form>
		<br>
		<input type="button" value="글쓰기" onclick="location.href='boardQWrite.jsp';" />
	</section>
	
</body>
</html>