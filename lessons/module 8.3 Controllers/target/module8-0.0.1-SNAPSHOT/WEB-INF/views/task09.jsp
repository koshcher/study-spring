<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
<title>Пейджинация</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<body>
	<div class="container">
		<ul class="pagination">
			<c:url var="previous" value="task09">
				<c:param name="page" value="${pageCount-1}" />
			</c:url>
			<li class="page-item"><a class="page-link" href="${previous}">Previous</a></li>
			<c:forEach begin="1" end="5" var="count">
				<c:url var="url" value="task09">
					<c:param name="page" value="${count}" />
				</c:url>
				<c:if test="${pageCount eq count}">
					<li class="page-item active"><a class="page-link"
						href="${url}">${count}</a></li>
				</c:if>
				<c:if test="${pageCount ne count}">
					<li class="page-item"><a class="page-link" href="${url}">${count}</a></li>
				</c:if>
			</c:forEach>
			 <c:url var="next" value="task09">
                    <c:param name="page" value="${pageCount+1}" />
			 </c:url>
			<li class="page-item"><a class="page-link" href="${next}">Next</a></li>
		</ul>
	</div>
	<div class="alert alert-info">Исправь пример, чтобы кнопки Previous и Next передвигали указатель страниц</div>
	<c:import url="back.jsp" />
</body>
</html>