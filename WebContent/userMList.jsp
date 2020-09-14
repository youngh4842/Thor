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
	<a href="carManage.admin">렌터카 관리</a>
	<a href="rentlist.admin">렌터카 대여 요청 목록</a>
	<a href="UserManagerList.admin">회원 관리</a>
	<a href="boardlist.common">상담 게시판</a>
	<br>
	<h2>회원 관리</h2>
	<hr width="500"/>
	<table border="1">
		
      	<tr>
        	 <th>아이디</th>
        	 <th>이름</th>
        	 <th>휴대폰번호</th>
      	</tr>
      	<c:forEach var="list" items="${requestScope.mem }">
	      	<tr>
		         <td><a href="UserManage.admin?m_id=${list.m_id }">${list.m_id}</a></td>
		         <td>${list.m_name}</td>
		         <td>${list.m_phone}</td>
	    	</tr>
	    </c:forEach>
	    
   	</table>
   	
   	<br>
   	
   	<div>
   	<form name="filter" method="post" action="Searchfilter.admin">		
   				
   		
   		<select name="filter" id="filter" >
   			<option value="select">목록</option>
   			<option value="m_id">아이디</option>
   			<option value="m_name">이름</option>
   			<option value="m_phone">휴대폰번호</option>
   		</select>
   		
   		<input type="text" name="find" id="find"/>
   		<input type="submit" value="검색">
   		
   	</form>
   	</div>
</body>


</html>