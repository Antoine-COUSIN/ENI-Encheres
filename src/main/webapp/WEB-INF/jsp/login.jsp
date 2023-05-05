<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Inscription</title>
	<link href="<%=request.getContextPath() %>/styles/styles.css" rel="stylesheet" />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
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
			</div>	
			<div>
				<a href="#">Mot de passe oublié</a>
			</div>
	  	</form>
	  	
	  	<a href="createUser">Créer un compte</a>
	</div>
	
	<%@include file="../fragments/footer.jspf" %>