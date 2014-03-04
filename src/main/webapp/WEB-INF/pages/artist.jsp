<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
		
		<li><div>
			<a href="chansons.do?album=${album.codeAlbum}" value="${album.nom}">${album.nom}</a>
			<a  href="delete_album.do?artiste=${artiste.codeArtiste}&album=${album.codeAlbum}" value="${album.codeAlbum}">Delete</a>
		</div></li>
		</c:forEach>
	</ul>
</c:if>


<form:form role="form" style="width:400px;"
		modelAttribute="album" method="POST" action="addAlbum.do?artiste=${artiste.codeArtiste}">
<table style="border:0px">
 <thead> <!-- En-tête du tableau -->
       <tr>
           <th>Code Artiste</th>
           <th>Nom Artiste</th>
       </tr>
   </thead>
   <tbody> <!-- Corps du tableau -->
   <tr>
   	<td><form:input class="form-control" path="codeAlbum" id="codealbum" type="number"/></td>
	<td><form:input class="form-control" path="nom" id="nom" /></td>
	<td><button type="submit" class="btn btn-primary">Ajouter</button></td>
   </tr>
</tbody>
</table>
</form:form>

</body>
</html>