<%@include file="../fragments/header.jspf" %>



<h1>Mon profil</h1>


<%@include file="../fragments/errors.jspf" %>
<c:if test="${ logError }">
	<h3>Echec de la création, les 2 mots de passe ne correpondent pas</h3>
</c:if>

<form method="POST" action="createUser">
	<!-- Left side -->
	<div>
		<div>
			<label for="pseudo">Pseudo :</label>
			<input type="text" id="pseudo_id" name="pseudo" value="${ !empty partialLoggedUser.getPseudo() ? partialLoggedUser.getPseudo() : ''}"/>
 		</div>
 		<div>
			<label for="firstName">Prénom :</label>
			<input type="text" id="firstName_id" name="firstName" value="${!empty partialLoggedUser.getFirstName() ? partialLoggedUser.getFirstName() : ''}"/>
 		</div>
 		<div>
			<label for="phone">Téléphone :</label>
			<input type="text" id="phone_id" name="phone" value="${!empty partialLoggedUser.getPhoneNumber() ? partialLoggedUser.getPhoneNumber() : ''}"/>
 		</div>
 		<div>
			<label for="postalCode">Code postal :</label>
			<input type="text" id="postalCode_id" name="postalCode" value="${!empty partialLoggedUser.getPostalCodeAddress() ? partialLoggedUser.getPostalCodeAddress() : ''}"/>
 		</div>
 		<div>
			<label for="password">Mot de passe :</label>
			<input type="password" id="password_id" name="password"/>
 		</div>
	</div>
	
	
	<!-- Right side -->
	<div>
		<div>
			<label for="lastName">Nom :</label>
			<input type="text" id="lastName_id" name="lastName" value="${!empty partialLoggedUser.getLastName() ? partialLoggedUser.getLastName() : ''}"/>
 		</div>
 		<div>
			<label for="email">Email :</label>
			<input type="text" id="email_id" name="email" value="${!empty partialLoggedUser.getEmail() ? partialLoggedUser.getEmail() : ''}"/>
 		</div>
 		<div>
			<label for="address">Adresse :</label>
			<input type="text" id="address_id" name="address" value="${!empty partialLoggedUser.getCityAddress() ? partialLoggedUser.getCityAddress() : ''}"/>
 		</div>
 		<div>
			<label for="city">Ville :</label>
			<input type="text" id="city_id" name="city" value="${!empty partialLoggedUser.getCityAddress() ? partialLoggedUser.getCityAddress() : ''}"/>
 		</div>
 		<div>
			<label for="confirmPassword">Confirmation :</label>
			<input type="password" id="confirmPassword_id" name="confirmPassword"/>
 		</div>
		
	</div>
	
	<div>
		<input type="submit" value="Créer"/>
		<a href="homePage">Annuler</a>
	</div>
	
</form>




<%@include file="../fragments/footer.jspf" %>