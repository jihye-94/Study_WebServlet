<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>servlet 요청</h3>
	<h3>getContextPath</h3>
	<%=request.getContextPath() %>
	<hr>
	<a href="<%=request.getContextPath() %>/simple">/simple 요청하기(GET)</a>
	<br>
	<a href="<%=request.getContextPath() %>/simple?type=date">날짜 요청하기(GET)</a>
	<br>
	<a href="<%=request.getContextPath() %>/simple?type=abcd">비정상적 요청하기(GET)</a>
	<br>
	<hr>
	<h3>FrontServletController</h3>
	<a href="<%=request.getContextPath() %>/action.do?cmd=greeting">Front 요청하기(GET)</a>
	
	<hr>
	<h3>FrontBoardController</h3>
	<a href="<%=request.getContextPath() %>/board?cmd=boardlist">게시판 목록보기</a>
	<br>
	<a href="<%=request.getContextPath() %>/board?cmd=boardwrite">게시판 글쓰기</a>
	<br>
	<a href="<%=request.getContextPath() %>/board?cmd=error">에러페이지</a>
	<br>
	<a href="<%=request.getContextPath() %>/board">파라메터없이, cmd의 null처리 유도</a>
	<br>
	<a href="<%=request.getContextPath() %>/board?cmd=boarddelete">게시판 삭제하기(없는페이지)</a>
	<br>
	<a href="${pageContext.request.contextPath}/board?cmd=login">보안페이지-로그인(EL로 작성)</a>
	<hr>
	EL${pageContext.request.contextPath} <br>
	
	
</body>
</html>