<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
	<body>
		<h1>Пример Spring MVC internationalization</h1>
 		Language : <a href="?language=en">English</a> | <a href="?language=zh_CN">Chinese</a> | <a href="?language=ru">Rusian</a> | <a href="?language=uk_UA">Ukrainian</a>
		<h2>
			Message : <spring:message code="welcome.message" text="default text" />
		</h2>
		Current Locale : ${pageContext.response.locale}
	</body>
</html>