<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<%@include file="../fragments/head.jspf" %> 
	
<head>
	<title>Accueil</title>
</head>

<body>
	<%@include file="../fragments/header.jspf" %>
	
	<div>
		<p>Pseudo : ${ user.pseudo }</p>
		<p>Nom : ${ user.lastName }</p>
		<p>Prénom : ${ user.firstName }</p>
		<p>Email : ${ user.email }</p>
		<p>Téléphone : ${ user.phoneNumber }</p>
		<p>Rue : ${ user.streetAddress }</p>
		<p>Code postal : ${ user.postalCodeAddress }</p>
		<p>Ville : ${ user.cityAddress }</p>
			
		<a href="updateProfile" >Modifier</a>
			
	</div>
		
	<%@include file="../fragments/footer.jspf" %>
</body>