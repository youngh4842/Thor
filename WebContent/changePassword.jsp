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
	<script>
		function checkPw() {
			var pw1 = document.frm.newPw1.value;
			var pw2 = document.frm.newPw2.value;
			
			if ( pw1 === pw2 ) {
				return true;
			}
			else {
				alert('새로운 비밀번호가 일치하지 않습니다');
				return false;
			}
		}
	</script>
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
		<form action="mypage_changepw.user" method="post" name="frm">
			<table>
				<tr>
					<td>현재 비밀번호</td>
					<td><input type="password" name="oldPw" required="required" /></td>
				</tr>
				<tr>
					<td>새로운 비밀번호</td>
					<td><input type="password" name="newPw1" required="required" /></td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td><input type="password" name="newPw2" required="required" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="확인" onclick="return checkPw();" /></td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>