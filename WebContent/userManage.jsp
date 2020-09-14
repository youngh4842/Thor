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
	<a href="rentlist.admin">렌터카 관리</a>
	<a href="rentRequestList.jsp">렌터카 대여 요청 목록</a>
	<a href="UserManagerList.admin">회원 관리</a>
	<a href="boardlist.common">상담 게시판</a>
	<br>
	<h2>회원정보 조회</h2>
	<hr width="500"/>
	<table border="1">

		<tr>
			<th>아이디</th>
			<td>${requestScope.meminfo.m_id}</td>
			<th>이름</th>
			<td>${requestScope.meminfo.m_name}</td>
		</tr>
		<tr>
			<th>번호</th>
			<td>${requestScope.meminfo.m_phone}</td>
			<th>이메일</th>
			<td>${requestScope.meminfo.m_email}</td>
		</tr>
		<tr>
			<th>포인트</th>
			<!--  <td>${requestScope.meminfo.m_point}</td>  -->
			<td><input type="number" name="point" value="${requestScope.meminfo.m_point }" /></td>
		</tr>
	
	</table>
	<br>
	<table border="1">
		<tr>
			<th colspan="4">포인트 사용 내역</th>
			
		</tr>
		<tr>
			<th>적립 날짜</th>
			<th>변경된 적립금</th>
			<th>적립 내용</th>
			<th>적립급</th>
		</tr>
		<c:forEach var="pList" items="${requestScope.pointinfo}">
			<tr>
				<td>${pList.p_days}</td>
				<td>${pList.p_changep}</td>
				<td>${pList.p_content}</td>
				<td>${pList.p_remainp}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<input type="button" value="적립내용변경하기" onclick="location.href='UserPCButton.admin?m_id=${requestScope.meminfo.m_id}';"/>
	<br>
	<br>
	<script type="text/javascript">
		function delUrl(){
			var check  = confirm("회원을 삭제하시겠습니까?");
			if (check == true){
				location.href='UserDelete.admin?m_id=${requestScope.meminfo.m_id}';
			}
			else{
				location.href='UserManage.admin?m_id=${requestScope.meminfo.m_id}';
			}	
			
		}
	</script>
	
	<input type="button" value="회원삭제" onclick="delUrl();"/>
	<input type="button" value="돌아가기" onclick="location.href='UserManagerList.admin'"/>
</body>
</html>