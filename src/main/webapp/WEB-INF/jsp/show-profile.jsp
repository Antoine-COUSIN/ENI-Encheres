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