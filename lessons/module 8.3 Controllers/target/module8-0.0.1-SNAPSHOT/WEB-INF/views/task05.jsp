<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Task05</title>
</head>
<body>
	<c:url var="countriesUrl" value="/countries" />
	<div class="container" >
		<div class="list-group pb-3">
			<c:forEach items="${countries}" var="country">
				<c:url var="countryCodeUrl" value="/countries/${country.code}" />
				<a href="${countryCodeUrl}"
					class="list-group-item list-group-item-action">${country.name}</a>
			</c:forEach>
		</div>
		<div class="list-group pb-3">
			<c:forEach items="${cities}" var="city">
				<c:url var="cityCodeUrl"
					value="/countries/${countryCode}/${city.code}" />
				<a href="${cityCodeUrl}"
					class="list-group-item list-group-item-action">${city.name}</a>
			</c:forEach>
		</div>
		<c:if test="${not empty cityCode}">
			<div class="alert alert-success pb-3" role="alert">
				Телефонный код города
				<c:out value="${cityName}" />
				+
				<c:out value="${countryCode} ${cityCode}" />
			</div>
		</c:if>
	</div>
	<c:import url="back.jsp" />
</body>
</html>