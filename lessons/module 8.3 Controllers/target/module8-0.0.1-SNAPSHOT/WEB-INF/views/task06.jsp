<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Updatable Collections</title>
</head>
<body>
	<c:url var="task6Url" value="/task06" />
	<div class="container">
		<form:form modelAttribute="country" method="POST" action="${task6Url}"
			class="form-horizontal">
			<div class="form-group">
				<form:label for="countryName" path="name"
					class="control-label col-sm-2">Country name:</form:label>
				<div class="col-sm-10">
					<form:input id="countryName" path="name" class="form-control" />
				</div>
			</div>
			<div class="form-group">
				<form:label path="code" for="code" class="control-label col-sm-2">Country code:</form:label>
				<div class="col-sm-10">
					<form:input id="code" path="code" class="form-control" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" value="Save" class="btn btn-primary" />
				</div>
			</div>
		</form:form>
		<ul>
			<c:forEach items="${countries}" var="country">
				<li><c:out value="${country.code}" /> <c:out
						value="${country.name}" /></li>
			</c:forEach>
		</ul>
	</div>
	<c:import url="back.jsp" />
</body>
</html>