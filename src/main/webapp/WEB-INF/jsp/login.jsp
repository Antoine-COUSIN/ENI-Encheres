<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%@include file="../fragments/head.jspf" %> 

<head>
	<title>Connexion</title>
</head>

<body>
	<%@include file="../fragments/header.jspf" %>

	<section class="connection container">
		<%@include file="../fragments/errors.jspf" %>
		
		<div class="row">
			<div class="col-12">
				<h2>Connexion</h2>
			</div>
		</div>
		
		<c:if test="${ logError }">
			<h2>Erreur de connexion, veuillez vérifier votre mot de passe ou votre identifiant</h2>
		</c:if>
		
		<div class="row">
			<div class="col-12">
				<form action="login" method="POST">
				<div class="row">
					<div class="col-12 login-form-box">
						<div>
							<input type="text" id="login" name="login" placeholder="Identifiant *"/>
						</div>
						<div>
							<input type="password" id="password" name="password" placeholder="Mot de Passe *" />
						</div>
					
						<div class="submit-box">
							<button class="btn btn-primary" type="submit" >Connexion</button>
							<a href="#">Mot de passe oublié</a>
							<a href="createUser">Créer un compte</a>
						</div>
					</div>
				</div>
		  	</form>
		  	
		  	
			</div>
		</div>
	</section>
	
	<%@include file="../fragments/footer.jspf" %>
</body>