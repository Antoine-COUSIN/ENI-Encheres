<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<body>
	<%@include file="../fragments/header.jspf" %>
	
	<section class="register container">
	<h2>Inscription</h2>
	
	
	<%@include file="../fragments/errors.jspf" %>
	<c:if test="${ logError }">
		<h3>Echec de la création, les 2 mots de passe ne correpondent pas</h3>
	</c:if>
	
	<form method="POST" action="${ previous == 'create-user' ? createUser : updateProfile }">
		<div class="form-box">
			<!-- Left side -->
			<div class="input-box">
				<div class="left-side-box">
					<div>
						<label for="firstName">Prénom :</label>
						<input type="text" id="firstName_id" name="firstName" value="${!empty user.firstName ? user.firstName : ''}"/>
			 		</div>
					<div>
						<label for="pseudo">Pseudo :</label>
						<input type="text" id="pseudo_id" name="pseudo" value="${ !empty user.pseudo ? user.pseudo : ''}"/>
			 		</div>
			 		<div>
						<label for="phone">Téléphone :</label>
						<input type="text" id="phone_id" name="phone" value="${!empty user.phoneNumber ? user.phoneNumber : ''}"/>
			 		</div>
			 		<div>
						<label for="postalCode">Code postal :</label>
						<input type="text" id="postalCode_id" name="postalCode" value="${!empty user.postalCodeAddress ? user.postalCodeAddress : ''}"/>
			 		</div>
			 		<div>
						<label for="password">Mot de passe :</label>
						<input type="password" id="password_id" name="password" />
			 		</div>
			 		<div>
			 			<label for="newPassword">Nouveau mot de passe :</label>
			 			<input type="password" id="newPassword_id" name="newPassword" />
			 		</div>
				</div>
				
				
				<!-- Right side -->
				<div class="right-side-box">
					<div>
						<label for="lastName">Nom :</label>
						<input type="text" id="lastName_id" name="lastName" value="${!empty user.lastName ? user.lastName : ''}"/>
			 		</div>
			 		<div>
						<label for="email">Email :</label>
						<input type="text" id="email_id" name="email" value="${!empty user.email ? user.email : ''}"/>
			 		</div>
			 		<div>
						<label for="address">Rue :</label>
						<input type="text" id="address_id" name="address" value="${!empty user.streetAddress ? user.streetAddress : ''}"/>
			 		</div>
			 		<div>
						<label for="city">Ville :</label>
						<input type="text" id="city_id" name="city" value="${!empty user.cityAddress ? user.cityAddress : ''}"/>
			 		</div>
			 		<div>
						<label for="confirmPassword">Confirmation :</label>
						<input type="password" id="confirmPassword_id" name="confirmPassword" />
			 		</div>
					
				</div>
			</div>
			
			<c:choose>
				<c:when test="${ previousPage == 'create-user' }">
					<div class="submit-box">
						<input type="submit" value="Créer"/>
						<a href="homepage">Annuler</a>
					</div>
				</c:when>
				
				<c:when test="${ previousPage == 'update-user' }">
					<div>
						<button type="submit" value="save" name="action" >Enregistrer</button>
						<button type="submit" value="delete" name="action" >Supprimer mon compte</button>
					</div>
				</c:when>
				
				<c:otherwise>
				</c:otherwise>
			</c:choose>
			
			
		</div>
	</form>
	</section>
	
	<%@include file="../fragments/footer.jspf" %>