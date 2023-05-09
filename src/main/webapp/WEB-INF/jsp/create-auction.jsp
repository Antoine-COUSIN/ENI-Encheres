<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<%@include file="../fragments/header.jspf" %>
	
	
	
	<h1>Nouvelle vente</h1>
	
	<img id="uploadPreview" style="width: 100px; height: 100px" />
	
	<form action="createAuction" method="POST">
		
		<label for="articleName">Article :</label>
		<input type="text" name="articleName" />
		
		<label>Description :</label>
		<textarea name="descr" rows="5" cols="33"></textarea>
		
		<label for="selected-category">Catégories : </label>
			<select name="category">
				<option value="">Toutes</option>
				
				<c:forEach var="cat" items="${categories}">
					<option value="${cat }">${cat.getLibelle() }</option>
				</c:forEach>
			</select>
		
		<label for="photo">Photo de l'article</label>
		<input type="file" id="pictureFile" name="pictureFile" accept="image/png, image/jpeg" onchange="PreviewImage()"/>
		
		<button type="submit" name="save">Enregistrer</button>
		<button type="submit" name="cancel">Annuler</button>
		
	</form>
	
			
	<%@include file="../fragments/footer.jspf" %>
