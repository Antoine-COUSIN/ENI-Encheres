<%@include file="../fragments/header.jspf" %>



<%@include file="../fragments/errors.jspf" %>

<c:if test="${ logError }">
	<h2>Erreur de connexion, veuillez v�rifier votre mot de passe ou votre identifiant</h2>
</c:if>

<div class="login-container">
	<form action="login" method="POST">
		<div>
			<label for="login">Identifiant :</label>
			<input type="text" id="login" name="login"/>
		</div>
		<div>
			<label for="password">Mot de Passe</label>
			<input type="password" id="password" name="password" />
		</div>
		
		<div>
			<input type="submit" value="Connexion"/>
			
			<div>
				<label for="rememberLog">Se souvenir de moi</label>
				<input type="checkbox" id="rememberLog" name="rememberLog" />
				
				<a href="#">Mot de passe oublié</a>
			</div>
		</div>
  	</form>
  	
  	<a href="createUser">Créer un compte</a>
</div>



<%@include file="../fragments/footer.jspf" %>