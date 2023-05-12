<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<%@include file="../fragments/head.jspf" %> 
	
<head>
	<title>Accueil</title>
</head>

<body>
	<%@include file="../fragments/header.jspf" %>
	
	<section class="show-profil container">
		<div class="row">
			<div class="col-12">
				<h2>Mon profil</h2>
			</div>
		</div>
		
		<div class="row">
			<div class="col-4"></div>
			<div class="col-2">
				<div class="profile-label">
					<span>Pseudo :</span>
					<span>Nom :</span>
					<span>Prénom :</span>
					<span>Email :</span>
					<span>Téléphone :</span>
					<span>Rue :</span>
					<span>Code postal :</span>
					<span>Ville :</span>
					<span>Crédits :</span>
				</div>
			</div>
			
			<div class="col-2">
				<div class="profile-value">
					<span>${ user.pseudo }</span>
					<span>${ user.lastName }</span>
					<span>${ user.firstName }</span>
					<span>${ user.email }</span>
					<span>${ user.phoneNumber }</span>
					<span>${ user.streetAddress }</span>
					<span>${ user.postalCodeAddress }</span>
					<span>${ user.cityAddress }</span>
					<span>${ user.credit } points</span>
				</div>
			</div>
			<div class="col-4"></div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="update-profile">
					<a href="updateProfile" ><button class="btn btn-primary">Modifier</button></a>
				</div>
			</div>
		</div>
	</section>
	<%@include file="../fragments/footer.jspf" %>
</body>