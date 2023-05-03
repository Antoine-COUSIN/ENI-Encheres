<%@include file="../fragments/header.jspf" %>



<h1>Mon profil</h1>


<%@include file="../fragments/errors.jspf" %>


<form method="POST" action="createUser">
	<!-- Left side -->
	<div>
		<div>
			<label for="pseudo">Pseudo :</label>
			<input type="text" id="pseudo_id" name="pseudo"/>
 		</div>
 		<div>
			<label for="firstName">Prénom :</label>
			<input type="text" id="firstName_id" name="firstName"/>
 		</div>
 		<div>
			<label for="phone">Teléphone :</label>
			<input type="text" id="phone_id" name="phone"/>
 		</div>
 		<div>
			<label for="postalCode">Code postal :</label>
			<input type="text" id="postalCode_id" name="postalCode"/>
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
			<input type="text" id="lastName_id" name="lastName"/>
 		</div>
 		<div>
			<label for="email">Email :</label>
			<input type="text" id="email_id" name="email"/>
 		</div>
 		<div>
			<label for="address">Addresse :</label>
			<input type="text" id="address_id" name="address"/>
 		</div>
 		<div>
			<label for="city">Ville :</label>
			<input type="text" id="city_id" name="city"/>
 		</div>
 		<div>
			<label for="confirmPassword">Confirmation :</label>
			<input type="password" id="confirmPassword_id" name="confirmPassword"/>
 		</div>
		
	</div>
	
	<div>
		<input type="submit" value="Créer"/>
		<a href="login">Annuler</a>
	</div>
	
</form>




<%@include file="../fragments/footer.jspf" %>