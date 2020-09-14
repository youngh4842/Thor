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
	<script>
		function deleteQuestion(frm) {
			frm.action = 'boardQDelete.common?' + ${qVo.q_num };
			frm.submit();
			
			return true;
		}
		function deleteAnswer(frm) {
			frm.action = 'boardADelete.common?' + ${aVo.a_num };
			frm.submit();
			
			return true;
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
	
	<section>
		<c:if test="${requestScope.qVo != null && requestScope.aVo == null }">
			<h2>${qVo.q_title }</h2>
			<hr width="500" align="left" />
			<strong>작성자</strong> ${qVo.q_ref_Mid }
			<strong>작성일</strong> ${qVo.q_days } <br>
			<hr width="500" align="left" />
			${qVo.q_content } <br>
			<form action="boardQUpdate.common" method="post">
				<input type="hidden" name="q_num" value="${qVo.q_num }" />
				<c:if test="${sessionScope.m_id == qVo.q_ref_Mid || sessionScope.m_id == 'root' }">
					<input type="submit" value="수정" />
					<input type="button" value="삭제" onclick="location.href='boardQDelete.common?q_num=${qVo.q_num }';" />
				</c:if>
				<c:if test="${sessionScope.m_id == 'root' }">
					<input type="button" value="답변 달기" onclick="location.href='boardAWrite.jsp?a_qnum=${qVo.q_num }';" />
				</c:if>
				<input type="button" value="목록" onclick="location.href='boardlist.common';" />
			</form>
		</c:if>
		
		<c:if test="${requestScope.qVo == null && requestScope.aVo != null }">	
			<h2>${aVo.a_title }</h2>
			<hr width="500" align="left" />
			<strong>작성자</strong> ${aVo.a_name }
			<strong>작성일</strong> ${aVo.a_days } <br>
			<hr width="500" align="left" />
			${aVo.a_content } <br>
			<form action="boardAUpdate.common" method="post">
				<input type="hidden" name="a_num" value="${aVo.a_num }" />
				<c:if test="${sessionScope.m_id == 'root' }">
					<input type="submit" value="수정" />
					<input type="button" value="삭제" onclick="location.href='boardADelete.common?a_num=${aVo.a_num }';" />
				</c:if>
				<input type="button" value="목록" onclick="location.href='boardlist.common';" />
			</form>
		</c:if>
	</section>
</body>
</html>