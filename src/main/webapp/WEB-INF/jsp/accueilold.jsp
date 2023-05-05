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
			<p>Fin de l'enchère : ${article.getEnd_auction() }</p>
			<p>Vendeur : ${article.getUser().getPseudo() }</p>
		</div>
	</c:forEach>

</div>
		
		
<%@include file="../fragments/footer.jspf" %>