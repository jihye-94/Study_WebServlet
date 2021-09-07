<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>MVC 중에서 View 담당</h3>
	<h3>SimpleController에서 forward된 페이지 데이터 출력 가능</h3>
	요청한 결과를 출력합니다<%=request.getAttribute("result") %>
</body>
</html>