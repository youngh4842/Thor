<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script>
		function findIDCheck(){
			if (document.frm.name.value.length==0){
				alert('이름을 입력해주세요!');
				frm.name.focus();
				return false;
			}
			else if(document.frm.phone.value.length==0){
				alert('전화번호를 입력해주세요!');
				frm.phone.focus();
				return false;
			}
		}
	</script>
</head>

<body>
	<c:choose>
		<c:when test="${sessionScope.m_id == null }">
			<form action="findID.common" method="post" name="frm">
				NAME <input type="text" name="name"/><br>
				PHONE NUMBER <input type="text" name="phone"/><br>
				<input type="submit" value="아이디찾기" onclick="return findIDCheck()"/><br>
			</form>
		</c:when>
	</c:choose>
</body>
</html>