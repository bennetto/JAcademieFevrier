<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<title>${album.nom} : </title>
</head>
<body>
<a href="albums.do?artiste=${artisteId}">liste des albums</a>
<h1>${album.nom}:</h1>
<c:if test="${not empty chansons}">
	<ul>
		<c:forEach var="chanson" items="${chansons}">
			<li>${chanson.titre}</li></a>
		</c:forEach>
	</ul>
</c:if>
</body>
</html>