<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
		<li><div>
			${chanson.titre}
			<a  href="delete_chanson.do?chanson=${chanson.idChanson}&album=${album.codeAlbum}" value="${chanson.idChanson}">Delete</a>
		</div></li>
		</c:forEach>
	</ul>
</c:if>


<form:form role="form" style="width:400px;"
		modelAttribute="chanson" method="POST" action="addChanson.do?album=${album.codeAlbum}">
<table style="border:0px">
 <thead> <!-- En-tête du tableau -->
       <tr>
           <th>Numero</th>
           <th>titre</th>
           <th>duree</th>
		<th>idChanson</th>
           
       </tr>
   </thead>
   <tbody> <!-- Corps du tableau -->
   <tr>
   	<td><form:input class="form-control" path="numero" id="numero" type="number"/></td>
	<td><form:input class="form-control" path="titre" id="titre" /></td>
	<td><form:input class="form-control" path="idChanson" id="idchanson" type="number"/></td>
	<td><form:input class="form-control" path="duree" id="duree" type="number"/></td>
	<td><button type="submit" class="btn btn-primary">Ajouter</button></td>
   </tr>
</tbody>
</table>
</form:form>

</body>
</html>