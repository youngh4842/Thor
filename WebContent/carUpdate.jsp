<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action = "carUpdateOK.admin" method = "post" enctype="multipart/form-data">
		자동차명  <input type="text"   name="name"    value="${requestScope.cVo.c_name}"    required="required"/><br>   
		회사        <input type="text"   name="company" value="${requestScope.cVo.c_company}" required="required"/><br>
		연비        <input type="number" name="fuel"    value="${requestScope.cVo.c_fuel}"    required="required"/>km/L<br>
		대여료     <input type="number" name="cost"    value="${requestScope.cVo.c_cost}"    required="required"/>원/h<br>
		사진	  <input type="file"   name="url"     value="${requestScope.cVo.c_url}" 	required="required" /><br>
			  <input type="hidden" name="oldName" value="${requestScope.cVo.c_name }" />
		
		<input type = "submit" value = "확인"/>
		<input type = "button" value = "취소" onclick = "history.back();"/>
	</form>
</body>
</html>