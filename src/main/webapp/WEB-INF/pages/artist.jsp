<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<title>${artiste.nom} : </title>
</head>
<body>
<a href="artistes.do">liste des artistes</a>
<h1>${artiste.nom}:</h1>
<c:if test="${not empty albums}">
	<ul>
		<c:forEach var="album" items="${albums}">
			<a href="chansons.do?album=${album.codeAlbum}" value="${album.nom}"><li>${album.nom}</li></a>
		</c:forEach>
	</ul>
</c:if>
</body>
</html>