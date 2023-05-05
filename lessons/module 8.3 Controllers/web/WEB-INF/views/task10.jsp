<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Task10</title>
</head>
<body>
	<c:url var="countriesUrl" value="/countries" />
	<div class="container">
		<div class="form-group">
			<sf:form modelAttribute="user" method="POST" class="form-horizontal">
				<div class="form-group">
					<sf:label for="userName" path="name" class="control-label col-sm-2">Name:</sf:label>
					<div class="col-sm-10">
						<sf:input id="userName" path="name" class="form-control" />
					</div>
					<sf:errors path="name" cssClass="error" class="alert alert-danger" />
				</div>
				<div class="form-group">
					<sf:label path="email" for="email" class="control-label col-sm-2">E-mail:</sf:label>
					<div class="col-sm-10">
						<sf:input id="email" path="email" class="form-control" />
					</div>
					<sf:errors path="email" cssClass="error" class="alert alert-danger" />
				</div>
				<div class="form-group">
                    <sf:label path="age" for="age" class="control-label col-sm-2">Age:</sf:label>
                    <div class="col-sm-10">
                        <sf:input id="age" path="age" class="form-control" />
                    </div>
                    <sf:errors path="age" cssClass="age" class="alert alert-danger" />
                </div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<input type="submit" value="Save" class="btn btn-primary" />
					</div>
				</div>
			</sf:form>
		</div>
	</div>
	<c:import url="back.jsp" />
</body>
</html>