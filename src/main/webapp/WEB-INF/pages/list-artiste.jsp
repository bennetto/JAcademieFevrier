<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<title>Liste des artistes : </title>
</head>
<body>
<h1>Liste des artistes :</h1>
<c:if test="${not empty artistes}">
	<ul>
		<c:forEach var="artiste" items="${artistes}">
			<a href="/artiste.do/${artiste.codeArtiste}/" value="${artiste.codeArtiste}"><li>${artiste.nom}</li></a>
		</c:forEach>
	</ul>
</c:if>
</body>
</html>