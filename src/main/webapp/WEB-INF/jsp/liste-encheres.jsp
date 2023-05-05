<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

	<%@include file="../fragments/header.jspf" %>

	<h1>Liste des enchères</h1>
	
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