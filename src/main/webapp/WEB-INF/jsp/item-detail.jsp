<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<%@include file="../fragments/header.jspf" %>
	
	<h1>Détail vente</h1>
	
	<%@include file="../fragments/filtres.jspf" %>
	
	<%@include file="../fragments/errors.jspf" %>
	
	<form action="article-detail" method="POST">
		<c:choose>
			<c:when test="${!empty article.image_article }">
				<img alt="article-pic" src="${pageContext.request.contextPath}/uploads/${ article.image_article }">
			</c:when>
			<c:otherwise>
				<img alt="default-picture" src="${pageContext.request.contextPath}/assets/default-picture.png">
			</c:otherwise>
		</c:choose>
		<p>${ selectedItem.name_article }</p>
		<p>Description : ${ selectedItem.descr_article }</p>
		<p>Catégorie : ${ selectedItem.category_Lib }</p>
		
		<p>Meilleure offre : </p>
		
		<fmt:parseDate value="${article.end_auction}" pattern="yyyy-MM-dd'T'HH:mm" var="end_auction"/>
		<fmt:formatDate value="${end_auction}" pattern="dd-MM-yyyy HH:mm" var="end_auction" />
		<p>Fin de l'enchère : ${end_auction}</p>
		
		<p>Retrait : </p>
		
		<p>Vendeur : ${article.user_Pseudo }</p>
		
		
		<c:choose>
			<c:when test="${ isConnected }">
				<label>Ma proposition : </label>
				<input type="number" name="bid"/>
				<button type="submit">Enchérir</button>
			</c:when>
			<c:otherwise>
				<a href="login">Connectez-vous pour enchérir</a>
			</c:otherwise>
		</c:choose>
		
	</form>