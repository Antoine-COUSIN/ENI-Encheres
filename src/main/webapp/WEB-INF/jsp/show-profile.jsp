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