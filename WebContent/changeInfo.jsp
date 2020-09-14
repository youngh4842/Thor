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
	
	<nav>
		<ul>
			<li><a href="mypage_view.user">개인정보 조회</a></li>
			<li><a href="mypage_update.user">개인정보 수정</a></li>
			<li><a href="changePassword.jsp">비밀번호 변경</a></li>
			<li><a href="mypage_point.user">포인트 조회</a></li>
		</ul>
	</nav>
	
	<section>
		<form action="mypage_update_ok.user" method="post">
			<table>
				<tr>
					<td>아이디</td>
					<td>${requestScope.mVo.m_id }</td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="${requestScope.mVo.m_name }" required="required" /></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="email" value="${requestScope.mVo.m_email }" required="required" /></td>
				</tr>
				<tr>
					<td>핸드폰 번호</td>
					<td><input type="text" name="phone" value="${requestScope.mVo.m_phone }" required="required" /></td>
				</tr>
				<tr>
					<td>포인트</td>
					<td>${requestScope.mVo.m_point }</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="확인" /></td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>