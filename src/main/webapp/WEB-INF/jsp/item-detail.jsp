<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@include file="../fragments/head.jspf" %> 
	
<head>
	<title>item detail</title>
</head>

<body>
	<%@include file="../fragments/header.jspf" %>
	
	<section class="item-detail-section container">
		<div class="row">
			<div class="col-12">
				<h2>Détail vente</h2>
			</div>
		</div>
		
		<div class="row">
			<div class="col-6">
				<div class="article-img-box">
					<c:choose>
						<c:when test="${!empty selectedItem.image_article }">
							<img alt="article-pic" src="${pageContext.request.contextPath}/uploads/${ selectedItem.image_article }">
						</c:when>
						<c:otherwise>
							<img alt="default-picture" src="${pageContext.request.contextPath}/assets/default-picture.png">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			
			<div class="col-6">
				<p>${ selectedItem.name_article }</p>
				<p>Description : ${ selectedItem.descr_article }</p>
				<p>Catégorie : ${ selectedItem.category_Lib }</p>
				
				<p>Meilleure offre : ${selectedBid.bid_amount } €</p>
				
				<fmt:parseDate value="${selectedItem.end_auction}" pattern="yyyy-MM-dd'T'HH:mm" var="end_auction"/>
				<fmt:formatDate value="${end_auction}" pattern="dd-MM-yyyy HH:mm" var="end_auction" />
				<p>Fin de l'enchère : </p><p data-end="${selectedItem.end_auction}">${end_auction}</p>
				<p>Temps restant : <span id="timer"></span></p>
				
				<p>Retrait : ${!empty selectedPickupPoint.streetAddress ? selectedPickupPoint.streetAddress : '' }</p>
				<p>${ !empty selectedPickupPoint.postalCode ? selectedPickupPoint.postalCode : '' } ${ !empty selectedPickupPoint.cityAddress ? selectedPickupPoint.cityAddress : '' }</p>
				
				<p>Vendeur : ${selectedItem.user_Pseudo }</p>
			</div>
		</div>
		
		<div class="row">
			<div class="col-12">
				<div class="article-auction-box">
					<form action="article-detail" method="POST">
						<%@include file="../fragments/errors.jspf" %>
						
						<c:choose>
						    <c:when test="${isConnected}">
						        <label>Ma proposition : </label>
						        <input type="number" name="bid"/>
						        <button class="btn btn-primary" type="submit">Enchérir</button>
						    </c:when>
						    <c:otherwise>
						        <a href="login">Connectez-vous pour enchérir</a>
						    </c:otherwise>
						</c:choose>
					</form>				
				</div>
			</div>
		</div>
	</section>
</body>