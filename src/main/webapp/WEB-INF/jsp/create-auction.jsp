<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@include file="../fragments/head.jspf" %> 
	
<head>
	<title>Accueil</title>
</head>

<body>
	<%@include file="../fragments/header.jspf" %>
	
	<section class="create-auction-section container">
		<div class="row">
			<div class="col-12">
				<h2>Nouvelle vente</h2>
			</div>
		</div>
		<%@include file="../fragments/errors.jspf" %>
		
		<div class="row">
			<div class="col-12">
			</div>
		</div>
		
		<div class="row">
			<div class="col-6">
				<div class="upload-preview-img-box">
					<img id="uploadPreview"/>
				</div>
			</div>
			
			<div class="col-6">
				<form action="createAuction" method="POST" enctype="multipart/form-data">
					<div class="row">
						<div class="col-4">
							<label for="articleName">Article :</label>
						</div>
						<div class="col-8">
							<input type="text" name="articleName" />
						</div>
					</div>
					<div class="row">
						<div class="col-4">
							<label>Description :</label>
						</div>
						<div class="col-8">
							<textarea name="descr" rows="5" cols="33"></textarea>
						</div>
					</div>
					<div class="row">
						<div class="col-4">
							<label for="selected-category">Catégories : </label>
						</div>
						<div class="col-8">
							<select name="category">
								<option value="">Toutes</option>
								
								<c:forEach var="cat" items="${categories}">
									<option value="${cat.no_categorie }">${cat.getLibelle() }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-4">
							<label for="photo">Photo de l'article</label>
						</div>
						<div class="col-8">
							<input type="file" id="pictureFile" name="pictureFile" accept="image/png, image/jpeg" onchange="PreviewImage()"/>
						</div>
					</div>
					<div class="row">
						<div class="col-4">
							<label for="price">Mise à prix :</label>
						</div>
						<div class="col-8">
							<input type="number" name="price" step=10 />
						</div>
					</div>
					<div class="row">
						<div class="col-4">
							<label for="startAuction">Début de l'enchère :</label>
						</div>
						<div class="col-8">
							<input type="datetime-local" name="startAuction" value="${ currentDate }"/>
						</div>
					</div>
					<div class="row">
						<div class="col-4">
							<label for="endAuction">Fin de l'enchère :</label>
						</div>
						<div class="col-8">
							<input type="datetime-local" name="endAuction" value="${ endAuctionDate }"/>
						</div>
					</div>
					
					<div class="row">
						<div class="col-12">
							<fieldset><legend>Retrait</legend>
								<div class="row">
									<div class="col-4">
										<label for="streetAddress">Rue :</label>
									</div>
									<div class="col-8">
										<input type="text" name="streetAddress" value="${user.streetAddress }" />
									</div>
								</div>
								<div class="row">
									<div class="col-4">
										<label for="postalCode">Code postal :</label>
									</div>
									<div class="col-8">
										<input type="text" name="postalCode" value="${ user.postalCodeAddress }" />
									</div>
								</div>
								<div class="row">
									<div class="col-4">
										<label for="cityAddress">Ville :</label>
									</div>
									<div class="col-8">
										<input type="text" name="cityAddress" value="${ user.cityAddress }" />
									</div>
								</div>
							</fieldset>
						</div>
					</div>
					
					<div class="row">
						<div class="col-12">
							<div class="create-auction-button-box">
								<button class="btn btn-primary" type="submit" value="save" name="action" >Enregistrer</button>
								<button class="btn btn-primary" type="submit" value="cancel" name="action" >Annuler</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</section>
	
	<%@include file="../fragments/footer.jspf" %>
</body>