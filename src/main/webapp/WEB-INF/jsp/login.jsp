<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

	<%@include file="../fragments/header.jspf" %>

	<%@include file="../fragments/errors.jspf" %>
	
	<c:if test="${ logError }">
		<h2>Erreur de connexion, veuillez vérifier votre mot de passe ou votre identifiant</h2>
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
			</div>	
			<div>
				<a href="#">Mot de passe oublié</a>
			</div>
	  	</form>
	  	
	  	<a href="createUser">Créer un compte</a>
	</div>
	
	<%@include file="../fragments/footer.jspf" %>