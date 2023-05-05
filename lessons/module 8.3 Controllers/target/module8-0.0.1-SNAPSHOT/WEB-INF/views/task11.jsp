<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<head>



<title>Task10</title>
</head>
<body>
	<div class="container">
		<!-- Page Heading/Breadcrumbs -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Пример загрузки файла</h1>
				<ol class="breadcrumb">
					<li class="active">Пример загрузки файла</li>
				</ol>
			</div>
		</div>
		<!-- /.row -->

		<c:url value="/uploadFile" var="fileUploadControllerURL" />
		<!-- Content Row -->
		<div class="row">
			<div class="col-lg-12">
				<p>Пример загрузки файла с помощью Spring MVC</p>
				<form action="${fileUploadControllerURL}" method="post"
					enctype="multipart/form-data">
					<table>
						<tr>
							<td><b>File:</b></td>
							<td><input type="file" name="file"></td>
							<td><input type="submit" value="загрузить файл"></td>
						</tr>
					</table>
				</form>
				<br/>
			</div>
		</div>
		<div>
			<c:forEach items="${fileNames}" var="fileName">
			     <img src="images/${fileName}" class="img-thumbnail">
			</c:forEach>
		</div>
		<hr>
		<c:import url="back.jsp" />
	</div>
</body>
</html>