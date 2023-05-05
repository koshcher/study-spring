<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="refresh" content="1" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Task01</title>
</head>
<body>
	<h1 align="center">Hello World!</h1>
	<!-- значение переменной serverTime передается из сервлета-->
	<h2 align="center">Server time: ${serverTime}.</h2>
	<c:import url="back.jsp" />
</body>
</html>