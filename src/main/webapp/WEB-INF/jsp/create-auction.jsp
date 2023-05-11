<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<%@include file="../fragments/header.jspf" %>
	
	
	
	<h1>Nouvelle vente</h1>
	
	<%@include file="../fragments/errors.jspf" %>
	
	<img id="uploadPreview" style="width: 100px; height: 100px" />
	
	<form action="createAuction" method="POST" enctype="multipart/form-data">
		
		<label for="articleName">Article :</label>
		<input type="text" name="articleName" />
		
		<label>Description :</label>
		<textarea name="descr" rows="5" cols="33"></textarea>
		
		<label for="selected-category">Catégories : </label>
			<select name="category">
				<option value="">Toutes</option>
				
				<c:forEach var="cat" items="${categories}">
					<option value="${cat.no_categorie }">${cat.getLibelle() }</option>
				</c:forEach>
			</select>
		
		<label for="photo">Photo de l'article</label>
		<input type="file" id="pictureFile" name="pictureFile" accept="image/png, image/jpeg" onchange="PreviewImage()"/>
		
		<label for="price">Mise à prix :</label>
		<input type="number" name="price" step=10 />
		
		<label for="startAuction">Début de l'enchère :</label>
		<input type="datetime-local" name="startAuction" value="${ currentDate }"/>
		
		<label for="endAuction">Fin de l'enchère :</label>
		<input type="datetime-local" name="endAuction" value="${ endAuctionDate }"/>
		
		<p>Retrait</p>
		<label for="streetAddress">Rue :</label>
		<input type="text" name="streetAddress" value="${user.cityAddress }" />
		
		<label for="postalCode">Code postal :</label>
		<input type="text" name="postalCode" value="${ user.postalCodeAddress }" />
		
		<label for="cityAddress">Ville :</label>
		<input type="text" name="cityAddress" value="${ user.cityAddress }" />
		
		
		<button type="submit" value="save" name="action" >Enregistrer</button>
		<button type="submit" value="cancel" name="action" >Annuler</button>
		
	</form>
	
			
	<%@include file="../fragments/footer.jspf" %>
