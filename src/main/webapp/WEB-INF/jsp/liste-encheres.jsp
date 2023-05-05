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

<h1>Liste des encheres</h1>

<%@include file="../fragments/filtres.jspf" %>


<!-- Liste des articles en vente  -->
<div>
		
	<c:forEach var="article" items="${ articles }">
		<div>
			<img alt="article-pic" src="${ article.getImage_article() }">
			
			<p>${article.getName_article() }</p>
			<p>Prix : ${article.getSell_price() } points</p>
			
			<fmt:parseDate value="${article.end_auction}" pattern="yyyy-MM-dd'T'HH:mm" var="end_auction"/>
			<fmt:formatDate value="${end_auction}" pattern="dd-MM-yyyy HH:mm" var="end_auction" />
			<p><b>Fin de l'enchère : </b>${end_auction}</p>
			
			<p>Vendeur : ${article.getUser().getPseudo() }</p>
		</div>
	</c:forEach>
</div>



<%@include file="../fragments/footer.jspf" %>
