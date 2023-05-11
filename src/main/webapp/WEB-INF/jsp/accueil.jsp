<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<%@include file="../fragments/header.jspf" %>
	
	<h1>Liste des enchères</h1>
	
	<%@include file="../fragments/filtres.jspf" %>
	
	<div>	
		<!-- Liste des articles en vente  -->
		<c:forEach var="article" items="${ articles }">	
			<div>
				<c:choose>
					<c:when test="${!empty article.image_article }">
						<img alt="article-pic" src="${pageContext.request.contextPath}/uploads/${ article.image_article }">
					</c:when>
					<c:otherwise>
						<img alt="default-picture" src="${pageContext.request.contextPath}/assets/default-picture.png">
					</c:otherwise>
				</c:choose>
				
				<p>${article.getName_article() }</p>
				<p>Prix : ${article.getSell_price() } points</p>
				
				<fmt:parseDate value="${article.end_auction}" pattern="yyyy-MM-dd'T'HH:mm" var="end_auction"/>
				<fmt:formatDate value="${end_auction}" pattern="dd-MM-yyyy HH:mm" var="end_auction" />
				<p><b>Fin de l'enchère : </b>${end_auction}</p>
				
				<p>Vendeur : ${article.user_Pseudo }</p>
			</div>
		</c:forEach>
	
	</div>
			
	<%@include file="../fragments/footer.jspf" %>
