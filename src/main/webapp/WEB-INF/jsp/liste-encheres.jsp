<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="../fragments/head.jspf" %> 
	
<head>
	<title>Liste des enchère</title>
</head>

<body> 
	<%@include file="../fragments/header.jspf" %>

	<section class="auction-list-section container">
		<div class="row">
			<div class=col-12>
				<h2>Liste des enchères</h2>
			</div>
		</div>
		
		<%@include file="../fragments/filtres.jspf" %>
		
		<div class="row">
			<div class="col-12">
				<div class="auction-list-storage">
					<c:forEach var="article" items="${ articles }">	
						<div style="cursor: pointer;" class="div-item" onclick="location.href='${pageContext.request.contextPath}/article-detail?id=${ article.no_article }';">
							<div class="auction-img">
								<c:choose>
									<c:when test="${!empty article.image_article }">
										<img alt="article-pic" src="${pageContext.request.contextPath}/uploads/${ article.image_article }">
									</c:when>
									<c:otherwise>
										<img alt="default-picture" src="${pageContext.request.contextPath}/assets/default-picture.png">
									</c:otherwise>
								</c:choose>
							</div>
							
							<div class="auction-desciption">
								<p>${article.getName_article() }</p>
								<p>Prix : ${article.initial_price } points</p>
								
								<fmt:parseDate value="${article.end_auction}" pattern="yyyy-MM-dd'T'HH:mm" var="end_auction"/>
								<fmt:formatDate value="${end_auction}" pattern="dd-MM-yyyy HH:mm" var="end_auction" />
								<p><b>Fin de l'enchère : </b>${end_auction}</p>
								
								<p>Vendeur : ${article.user_Pseudo }</p>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</section>
	
	<%@include file="../fragments/footer.jspf" %>
</body>