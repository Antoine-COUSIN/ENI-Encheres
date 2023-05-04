<%@include file="../fragments/header.jspf" %>
		

<h1>Liste des enchères</h1>

<form>
	<p>Filtres : </p>
	
	<div>
		<input type="text" placeholder="Le nom de l'article contient"/>	
		
		<label for="selected-category">Catégorie</label>
		<select name="category">
			<option value="">Toutes</option>
			
			<c:forEach var="cat" items="${categories}">
				<option value="${cat }">${cat }</option>
			</c:forEach>
			
		</select>
	</div>
	

</form>
		
		
<%@include file="../fragments/footer.jspf" %>