<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<title>Hello SpringMVC</title>
</head>
<body>
<c:if test="${not empty books}">
	<ul>
		<c:forEach var="book" items="${books}">
			<a href="/book.do" value="${book.id}"><li>${book.title} de ${book.author}</li></a>
		</c:forEach>
	</ul>
</c:if>
</body>
</html>