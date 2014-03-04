<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<title>Liste des artistes : </title>
</head>
<body>
<h1>Liste des artistes :</h1>
<c:if test="${not empty artistes}">
	<ul>
		<c:forEach var="artiste" items="${artistes}">
			<li><div>
			<a href="albums.do?artiste=${artiste.codeArtiste}" value="${artiste.codeArtiste}">${artiste.nom}</a>
			<a  href="delete_artiste.do?artiste=${artiste.codeArtiste}" value="${artiste.codeArtiste}">Delete</a>
			</div></li>
		</c:forEach>
	</ul>
</c:if>

<form:form role="form" style="width:400px;"
		modelAttribute="artiste" method="POST" action="addArtiste.do">
<table style="border:0px">
 <thead> <!-- En-tête du tableau -->
       <tr>
          <th>Code Artiste</th>
           <th>Nom Artiste</th>
       </tr>
   </thead>
   <tbody> <!-- Corps du tableau -->
   <tr>
   	<td><form:input class="form-control" path="codeArtiste" id="codeartiste" type="number"/></td>
	<td><form:input class="form-control" path="nom" id="nom" /></td>
	<td><button type="submit" class="btn btn-primary">Ajouter</button></td>
   </tr>
</tbody>
</table>
</form:form>



</body>
</html>